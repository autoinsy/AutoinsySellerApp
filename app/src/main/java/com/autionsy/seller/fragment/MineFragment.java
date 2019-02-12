package com.autionsy.seller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.autionsy.seller.R;
import com.autionsy.seller.activity.OrderListActivity;
import com.autionsy.seller.activity.SettingActivity;
import com.autionsy.seller.entity.UserInfo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineFragment extends BaseFragment {
    private View view;

    @BindView(R.id.mine_header_iv)
    ImageView mine_header_iv;
    @BindView(R.id.mine_username_tv)
    TextView mine_username_tv;

    private Intent intent;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.frag_mine, null);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView(){

        UserInfo userInfo = new UserInfo();
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.default_header)
                .error(R.mipmap.default_header);
        Glide.with(getActivity())
                .load(userInfo.getHeaderImage())
                .apply(RequestOptions.circleCropTransform())
                .apply(options)
                .into(mine_header_iv);
    }

    @OnClick({R.id.mine_setting_iv,
            R.id.wait_send_stock,
            R.id.wait_receive_stock,
            R.id.wait_comment_stock,
            R.id.exchange_stock,
            R.id.check_all_order_tv,
            R.id.mine_product_management_layout,
            R.id.mine_ornament_management_text_layout,
            R.id.mine_service_management_text_layout,
            R.id.mine_lease_management_text_layout,
            R.id.mine_recuit_management_text_layout,
            R.id.mine_rescue_management_text_layout,})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.mine_setting_iv:
                intent = new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.wait_send_stock:
                intent = new Intent(getActivity(), OrderListActivity.class);
                intent.putExtra("order_state","1");
                startActivity(intent);
                break;
            case R.id.wait_receive_stock:
                intent = new Intent(getActivity(), OrderListActivity.class);
                intent.putExtra("order_state","2");
                startActivity(intent);
                break;
            case R.id.wait_comment_stock:
                intent = new Intent(getActivity(), OrderListActivity.class);
                intent.putExtra("order_state","3");
                startActivity(intent);
                break;
            case R.id.exchange_stock:
                intent = new Intent(getActivity(), OrderListActivity.class);
                intent.putExtra("order_state","4");
                startActivity(intent);
                break;
            case R.id.check_all_order_tv:
                intent = new Intent(getActivity(), OrderListActivity.class);
                intent.putExtra("order_state","0");
                startActivity(intent);
                break;
            case R.id.mine_product_management_layout:

                break;
            case R.id.mine_ornament_management_text_layout:

                break;
            case R.id.mine_service_management_text_layout:

                break;
            case R.id.mine_lease_management_text_layout:

                break;
            case  R.id.mine_recuit_management_text_layout:

                break;
            case R.id.mine_rescue_management_text_layout:

                break;
        }
    }
}
