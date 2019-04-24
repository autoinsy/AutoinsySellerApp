package com.autionsy.seller.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.autionsy.seller.R;
import com.autionsy.seller.adapter.UploadImageAdapter;
import com.autionsy.seller.constant.Constant;
import com.autionsy.seller.dialog.PhotoPickDialog;
import com.autionsy.seller.entity.Goods;
import com.autionsy.seller.entity.Login;
import com.autionsy.seller.utils.OkHttp3UploadFileUtil;
import com.autionsy.seller.utils.OkHttp3Utils;
import com.autionsy.seller.views.GridViewInScrollView;
import com.scrat.app.selectorlibrary.ImageSelector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PublishGoodsActivity extends BaseActivity{
    @BindView(R.id.upload_image_gv)
    GridViewInScrollView upload_image_gv;
    @BindView(R.id.title_tv)
    TextView title_tv;
    @BindView(R.id.submit_tv)
    TextView submit_tv;

    @BindView(R.id.goods_name_et)
    EditText goods_name_et;
    @BindView(R.id.goods_quantity_et)
    EditText goods_quantity_et;
    @BindView(R.id.goods_product_place_et)
    EditText goods_product_place_et;
    @BindView(R.id.goods_price_et)
    EditText goods_price_et;
    @BindView(R.id.motocycle_frame_code_et)
    EditText motocycle_frame_code_et;

    @BindView(R.id.goods_type_tv)
    TextView goods_type_tv;
    @BindView(R.id.goods_brand_tv)
    TextView goods_brand_tv;

    @BindView(R.id.goods_upload_image_iv)
    ImageView goods_upload_image_iv;

    private String goodsName;
    private String goodsQuantity;
    private String goodsProductPlace;
    private String goodsPrice;
    private String goodsFrameCode;

    private static final int REQUEST_CODE_SELECT_IMG = 1;
    private static final int MAX_SELECT_COUNT = 9;
    private File file;
    private List<String> path;//路径集合

    private static final int PHOTO_REQUEST_CAREMA = 2;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 3;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 4;// 结果
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;

    private Intent intent;

    private Goods goods;

    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_publish_goods);

        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        title_tv.setVisibility(View.VISIBLE);
        title_tv.setText(R.string.publish_goods);
//        submit_tv.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back_btn,
            R.id.image_selector_layout,
            R.id.submit_tv,
            R.id.type_selector_layout,
            R.id.brand_selector_layout,
            R.id.goods_upload_image_iv})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.image_selector_layout:
                ImageSelector.show(this, REQUEST_CODE_SELECT_IMG, MAX_SELECT_COUNT);
                break;
//            case R.id.submit_tv:
//                postAsynHttpGoods();
//                break;
            case R.id.type_selector_layout:
                intent = new Intent(PublishGoodsActivity.this, CategoryActivity.class);
                startActivity(intent);
                break;
            case R.id.brand_selector_layout:
                intent = new Intent(PublishGoodsActivity.this, BrandActivity.class);
                startActivity(intent);
                break;
            case R.id.goods_upload_image_iv:
                UploadSingleImage();
                break;
        }
    }

    //====Okhttp3上传单张图片======================================================================================================
    private void UploadSingleImage(){
        new PhotoPickDialog(PublishGoodsActivity.this).builder().setCancelable(true).setTakePhotoListener(new PhotoPickDialog.TakePhotoListener() {
            @Override
            public void takePhoto() {
                // 激活相机
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                // 判断存储卡是否可以用，可用进行存储
                if (hasSdcard()) {
                    tempFile = new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME);
                    // 从文件中创建uri
                    Uri uri = Uri.fromFile(tempFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                }
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
            }

            @Override
            public void pickPhoto() {
                // 激活系统图库，选择一张图片
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent1, PHOTO_REQUEST_GALLERY);
            }
        }).show();
    }

    /*
     * 判断sdcard是否被挂载
     */
    private boolean hasSdcard() {
        //判断ＳＤ卡手否是安装好的　　　media_mounted
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
    //====Okhttp3上传单张图片======================================================================================================

    /**回调并上传多张或者单张图片*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //====================okhttp3上传多张图片=====================================================================================
        if (requestCode == REQUEST_CODE_SELECT_IMG) {
            showImage(data); //设置图片 跟图片目录
            uploadImage(data);
            return;
            //====================okhttp3上传多张图片=====================================================================================
        }else if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }
        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (hasSdcard()) {
                crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(PublishGoodsActivity.this, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                /**
                 * 获得图片
                 */
                goods_upload_image_iv.setImageBitmap(bitmap);
                //保存到SharedPreferences
                saveBitmapToSharedPreferences(bitmap);
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //====================okhttp3上传多张图片=====================================================================================
    /**okhttp3上传多张图片*/
    private void uploadImage(Intent data) {

        goods = new Goods();
        goodsName = goods_name_et.getText().toString().trim();
        goodsQuantity = goods_quantity_et.getText().toString().trim();
        goodsProductPlace = goods_product_place_et.getText().toString().trim();
        goodsPrice = goods_price_et.getText().toString().trim();
        goodsFrameCode = motocycle_frame_code_et.getText().toString().trim();

        //同样，在读取SharedPreferences数据前要实例化出一个SharedPreferences对象
        sharedPreferences = getSharedPreferences("seller_login_data", Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        String username = sharedPreferences.getString("USERNAME", "");

        String url = Constant.HTTP_URL + "addGoods";

        if (data != null) {
            path = ImageSelector.getImagePaths(data);

            //初始化OkHttpClient
            OkHttpClient client = new OkHttpClient();
            // form 表单形式上传
            MultipartBody.Builder requestBody = new MultipartBody.Builder();
            requestBody.setType(MultipartBody.FORM);
            //pathList是文件路径对应的列表
            if (null != path && path.size() > 0) {
                for (String path : path) {
                    File file = new File(path);
                    if (file != null) {
                        // MediaType.parse() 里面是上传的文件类型。
                        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
                        // 参数分别为， 请求key ，文件名称 ， RequestBody
                        requestBody.addFormDataPart("upload_goods_image", file.getName(), body);
                    }
                }
            }
            //要上传的文字参数
            Map<String, String> map = new HashMap<>();
            map.put("userName", username);
            map.put("goodName",goodsName);
            map.put("goodPrice",goodsPrice);
            map.put("quantity",goodsQuantity);
            map.put("productPlace",goodsProductPlace);
            map.put("motorcycleFrameNumber",goodsFrameCode);
            map.put("subType","轮胎");
            map.put("brand","米其林");
            map.put("upload_type","3");
            if (map != null) {
                for (String key : map.keySet()) {
                    requestBody.addFormDataPart(key, map.get(key));
                }
            }
            //创建Request对象
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody.build())
                    .build();
            // readTimeout("请求超时时间" , 时间单位);
            client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //请求失败处理
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        final String str = response.body().string();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if("200".equals(str)){
                                    Toast.makeText(getApplicationContext(),"上传图片成功",Toast.LENGTH_SHORT).show();
                                }else if("403".equals(str)){
                                    Toast.makeText(getApplicationContext(),"参数错误",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            });

        }
        UploadImageAdapter adapter = new UploadImageAdapter(path, PublishGoodsActivity.this);
        upload_image_gv.setAdapter(adapter);
    }

    private void showImage(Intent data) {
        path = ImageSelector.getImagePaths(data); //集合获取path(这里的path是集合)
    }

//    public String getFileName(String pathandname) {
//        int start = pathandname.lastIndexOf("/");
//        if (start != -1) {
//            return pathandname.substring(start + 1);
//        } else {
//            return null;
//        }
//    }
//    private void setResult(String string, final boolean success) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                if (success) {
//                    Toast.makeText(PublishGoodsActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(PublishGoodsActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
    //====================okhttp3上传多张图片=====================================================================================

    //=====================okhttp3上传单张图片====================================================================================
    //保存图片到SharedPreferences
    private void saveBitmapToSharedPreferences(Bitmap bitmap) {
        // Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        //第三步:将String保持至SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("upload_goods_image", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("goods_image", imageString);
        editor.commit();

        //上传头像
        setImgByStr(imageString,"");
    }


    /**
     * 上传头像
     * @param imgStr
     * @param imgName
     */
    public  void setImgByStr(String imgStr, String imgName) {
        String url = "http://appserver.1035.mobi/MobiSoft/User_upLogo";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", "11460047");// 11459832
        params.put("data", imgStr);
        OkHttp3UploadFileUtil.postAsync(url, params, new OkHttp3UploadFileUtil.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                Log.i("上传失败", "失败" + request.toString() + e.toString());
            }
            @Override
            public void requestSuccess(String result) throws Exception {
                Log.i("上传成功", result);
            }
        });
    }

//    //从SharedPreferences获取图片
//    private void getBitmapFromSharedPreferences(){
//        SharedPreferences sharedPreferences=getSharedPreferences("upload_goods_image", Context.MODE_PRIVATE);
//        //第一步:取出字符串形式的Bitmap
//        String imageString=sharedPreferences.getString("goods_image", "");
//        //第二步:利用Base64将字符串转换为ByteArrayInputStream
//        byte[] byteArray=Base64.decode(imageString, Base64.DEFAULT);
//        if(byteArray.length==0){
//            goods_upload_image_iv.setImageResource(R.mipmap.upload_image_default);
//        }else{
//            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);
//
//            //第三步:利用ByteArrayInputStream生成Bitmap
//            Bitmap bitmap= BitmapFactory.decodeStream(byteArrayInputStream);
//            goods_upload_image_iv.setImageBitmap(bitmap);
//        }
//    }
    //=====================okhttp3上传单张图片====================================================================================
}
