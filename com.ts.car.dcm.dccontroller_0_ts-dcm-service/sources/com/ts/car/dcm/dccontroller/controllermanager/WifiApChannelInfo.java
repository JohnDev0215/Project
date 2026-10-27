package com.ts.car.dcm.dccontroller.controllermanager;

/* JADX INFO: loaded from: classes.dex */
public class WifiApChannelInfo {
    private static final int AA_2G_CHN = 5;
    private static final int AA_5G_CHN = 2;
    private static final int CL_2G_CHN = 6;
    private static final int CL_5G_CHN = 3;
    private static final int COUNTRY_CODE = 0;
    private static final int COUNTRY_NAME = 7;
    private static final int CP_2G_CHN = 4;
    private static final int CP_5G_CHN = 1;
    private int m2gAachannel;
    private int m2gClchannel;
    private int m2gCpchannel;
    private int m5gAachannel;
    private int m5gClchannel;
    private int m5gCpchannel;
    private String mCountryCode;
    private String mCountryName;

    public WifiApChannelInfo(String[] info) {
        this.mCountryCode = info[0];
        this.m5gCpchannel = Integer.parseInt(info[1]);
        this.m5gAachannel = Integer.parseInt(info[2]);
        this.m5gClchannel = Integer.parseInt(info[3]);
        this.m2gCpchannel = Integer.parseInt(info[4]);
        this.m2gAachannel = Integer.parseInt(info[5]);
        this.m2gClchannel = Integer.parseInt(info[6]);
        this.mCountryName = info[7];
    }

    public int get2gAachannel() {
        return this.m2gAachannel;
    }

    public int get2gClchannel() {
        return this.m2gClchannel;
    }

    public int get2gCpchannel() {
        return this.m2gCpchannel;
    }

    public int get5gAachannel() {
        return this.m5gAachannel;
    }

    public int get5gClchannel() {
        return this.m5gClchannel;
    }

    public int get5gCpchannel() {
        return this.m5gCpchannel;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public String getCountryName() {
        return this.mCountryName;
    }

    public String toString() {
        return "WifiApChannelInfo{mCountryCode='" + this.mCountryCode + "'m5gCpchannel='" + this.m5gCpchannel + "', m5gAachannel='" + this.m5gAachannel + "', m5gClchannel='" + this.m5gClchannel + "', m2gCpchannel='" + this.m2gCpchannel + "', m2gAachannel='" + this.m2gAachannel + "', m2gClchannel='" + this.m2gClchannel + "', mCountryName=" + this.mCountryName + '}';
    }
}
