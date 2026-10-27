package com.visualon.OSMPUtils;

import android.util.Log;

/* JADX INFO: loaded from: classes7.dex */
public interface voOSIOFailedDesc {
    public static final String TAG = "@@@voOSIOFailedDesc";

    VOOSMP_SRC_IO_DOWNLOAD_FAIL_REASON getReason();

    String getResponse();

    byte[] getResponsePayload();

    String getURL();

    public enum VOOSMP_SRC_IO_DOWNLOAD_FAIL_REASON {
        VOOSMP_IO_HTTP_CONNECT_FAILED(0),
        VOOSMP_IO_HTTP_INVALID_RESPONSE(1),
        VOOSMP_IO_HTTP_CLIENT_ERROR(2),
        VOOSMP_IO_HTTP_SERVER_ERROR(3),
        VOOSMP_IO_HTTP_FAIL_REASON_MAX_VALUE(Integer.MAX_VALUE);

        private int value;

        VOOSMP_SRC_IO_DOWNLOAD_FAIL_REASON(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static VOOSMP_SRC_IO_DOWNLOAD_FAIL_REASON valueOf(int i) {
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].getValue() == i) {
                    return values()[i2];
                }
            }
            Log.e(voOSIOFailedDesc.TAG, "VO_OSMP_CB_EVENT_ID isn't match. id = " + Integer.toHexString(i));
            return VOOSMP_IO_HTTP_FAIL_REASON_MAX_VALUE;
        }
    }
}
