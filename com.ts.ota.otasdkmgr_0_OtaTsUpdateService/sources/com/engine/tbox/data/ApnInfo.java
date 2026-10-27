package com.engine.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public class ApnInfo {
    private static final int APN_DNS_FAILED = 4;
    private static final int APN_LOGIN_FAILED = 6;
    private static final int APN_REGISTER_FAILED = 5;
    private static final int APN_RESULT_CONNECT_SUCCESS = 0;
    private static final int APN_RESULT_DIAL_FAILED = 1;
    private static final int APN_RESULT_NO_DIAL_ARGS = 3;
    private static final int APN_RESULT_REGISTER_FAILED = 2;
    private static final int APN_UUID_FAILED = 3;
    private static final int APN_VIN_FAILED = 2;
    private int apConnectState;
    private int apn;
    private int result;
    private int ip = 0;
    private String ipString = "";
    private int errorCode = 0;
    private String uuid = "";

    public int getApn() {
        return this.apn;
    }

    public void setApn(int apn) {
        this.apn = apn;
    }

    public int getApConnectState() {
        return this.apConnectState;
    }

    public void setApConnectState(int apConnectState) {
        this.apConnectState = apConnectState;
    }

    public boolean isConnectOk() {
        return this.apConnectState == 0;
    }

    public int getIp() {
        return this.ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
        this.ipString = intToIp(ip);
    }

    public String getIpString() {
        return this.ipString;
    }

    public void setIpString(String ipString) {
        this.ipString = ipString;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return this.result;
    }

    private String maskUuid(String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            return uuid;
        }
        if (uuid.length() > 4) {
            return uuid.substring(0, 4) + "********" + uuid.substring(uuid.length() - 4);
        }
        return "not full uuid, length:" + uuid.length();
    }

    public String toString() {
        return "Apn1Info [apConnectState=" + apnConnectStateToString(this.apConnectState) + ", ip=" + this.ip + " result:" + apnResultToString(this.result) + ", errorCode:" + this.errorCode + ", uuid=" + maskUuid(this.uuid) + "]";
    }

    public static String intToIp(int ip) {
        return ((ip >> 24) & 255) + "." + ((ip >> 16) & 255) + "." + ((ip >> 8) & 255) + "." + (ip & 255);
    }

    public static String apnConnectStateToString(int state) {
        if (state == 2) {
            return "APN_VIN_FAILED";
        }
        if (state == 3) {
            return "APN_UUID_FAILED";
        }
        if (state == 4) {
            return "APN_DNS_FAILED";
        }
        if (state == 5) {
            return "APN_REGISTER_FAILED";
        }
        if (state == 6) {
            return "APN_LOGIN_FAILED";
        }
        return "unknown apn connect state";
    }

    public static String apnResultToString(int result) {
        if (result == 0) {
            return "APN_CONNECT_SUCCESS";
        }
        if (result == 1) {
            return "APN_DIAL_FAILED";
        }
        if (result == 2) {
            return "APN_RESULT_REGISTER_FAILED";
        }
        if (result == 3) {
            return "APN_RESULT_NO_DIAL_ARGS";
        }
        return "unknown apn result";
    }
}
