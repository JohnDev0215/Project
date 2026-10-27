package com.engine.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public class Apn1Info {
    private static final int APN_CONNECT_SUCCESS = 0;
    private static final int APN_DIAL_FAILED = 1;
    private static final int APN_DNS_FAILED = 4;
    private static final int APN_LOGIN_FAILED = 6;
    private static final int APN_REGISTER_FAILED = 5;
    private static final int APN_UUID_FAILED = 3;
    private static final int APN_VIN_FAILED = 2;
    private int apConnectState;
    private int ip = 0;
    private String ipString = "";
    private String uuid = "";

    public int getApConnectState() {
        return this.apConnectState;
    }

    public void setApConnectState(int apConnectState) {
        this.apConnectState = apConnectState;
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
        return "Apn1Info [apConnectState=" + apnConnectStateToString(this.apConnectState) + ", ip=" + this.ip + ", uuid=" + maskUuid(this.uuid) + "]";
    }

    public static String intToIp(int ip) {
        return ((ip >> 24) & 255) + "." + ((ip >> 16) & 255) + "." + ((ip >> 8) & 255) + "." + (ip & 255);
    }

    public static String apnConnectStateToString(int state) {
        switch (state) {
            case 0:
                return "APN_CONNECT_SUCCESS";
            case 1:
                return "APN_DIAL_FAILED";
            case 2:
                return "APN_VIN_FAILED";
            case 3:
                return "APN_UUID_FAILED";
            case 4:
                return "APN_DNS_FAILED";
            case 5:
                return "APN_REGISTER_FAILED";
            case 6:
                return "APN_LOGIN_FAILED";
            default:
                return "unknow apn connect state";
        }
    }
}
