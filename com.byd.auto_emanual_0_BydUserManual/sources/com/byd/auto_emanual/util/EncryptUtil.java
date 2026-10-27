package com.byd.auto_emanual.util;

import com.byd.auto_emanual.MApp;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class EncryptUtil {
    public static final String USER_NAME = "user_DiLink";

    public static String encrypt(String para) {
        String strStringAESDecipher;
        try {
            strStringAESDecipher = AES256Encrypt.stringAESDecipher(HttpUtil.KEY, MApp.context.getPackageName());
        } catch (Exception unused) {
            MLog.e("", "decode error!!!");
            strStringAESDecipher = "null";
        }
        String strStringAESEncrypt = AES256Encrypt.stringAESEncrypt(para, strStringAESDecipher);
        return new EncryptBody(USER_NAME, UUID.randomUUID().toString(), MD5Encryption.encodeMD5String(para), strStringAESEncrypt).toString();
    }

    public static class EncryptBody {
        private String enCodeData;
        private String sign;
        private String userName;
        private String uuid;

        public EncryptBody(String userName, String uuid, String sign, String enCodeData) {
            this.userName = userName;
            this.uuid = uuid;
            this.sign = sign;
            this.enCodeData = enCodeData;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUuid() {
            return this.uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getSign() {
            return this.sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getEnCodeData() {
            return this.enCodeData;
        }

        public void setEnCodeData(String enCodeData) {
            this.enCodeData = enCodeData;
        }

        public String toString() {
            return "{\"userName\":\"" + this.userName + "\",\"uuid\":\"" + this.uuid + "\",\"sign\":\"" + this.sign + "\",\"enCodeData\":\"" + this.enCodeData + "\"}";
        }
    }
}
