package com.byd.dilink.app;

/* JADX INFO: loaded from: classes.dex */
public class BYDAuthServiceConstants {

    public static class AuthDesc {
        public static final String AUTH_DESC_CANCEL = "Auth cancel";
        public static final String AUTH_DESC_DEFAULT = "Auth description default";
        public static final String AUTH_DESC_EXCEPTION = "Auth exception, qrcode information error";
        public static final String AUTH_DESC_FAIL = "Auth fail";
        public static final String AUTH_DESC_NO_PERMISSION = "No permission";
        public static final String AUTH_DESC_SUCCESS = "Auth success";
        public static final String AUTH_DESC_TIMEOUT = "Auth timeout";
        public static final String AUTH_DESC_WAIT = "Only one request can be authenticated at a time";
    }

    public static class AuthResult {
        public static final int AUTH_RESULT_CANCEL = 5;
        public static final int AUTH_RESULT_DEFAULT = 0;
        public static final int AUTH_RESULT_EXCEPTION = 7;
        public static final int AUTH_RESULT_FAIL = 2;
        public static final int AUTH_RESULT_NO_PERMISSION = 3;
        public static final int AUTH_RESULT_SUCCESS = 1;
        public static final int AUTH_RESULT_TIMEOUT = 4;
        public static final int AUTH_RESULT_WAIT = 6;
    }
}
