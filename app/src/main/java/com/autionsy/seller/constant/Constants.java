package com.autionsy.seller.constant;

public class Constants {
    public static String ID_FRONT = "idCardFrontCrop.jpg";
    public static String ID_BACK = "idCardBackCrop.jpg";

    public static String HTTP_URL = "http://192.168.0.102:6888/seller/api/";

    // request参数
    public static final int REQ_QR_CODE = 11002; // // 打开扫描界面请求码
    public static final int REQ_PERM_CAMERA = 11003; // 打开摄像头

    public static final String INTENT_EXTRA_KEY_QR_SCAN = "qr_scan_result";

    /**认证*/
    public static final String UPLOAD_ID_CARD_FRONT = "uploadIDCardFront";
    public static final String UPLOAD_ID_CARD_BACK = "uploadIDCardBack";
    public static final String UPLOAD_BUSINESS_LICENCE = "uploadBusinessLicence";

}
