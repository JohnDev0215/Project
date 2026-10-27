package com.amazonaws.mobileconnectors.remoteconfiguration.internal;

/* JADX INFO: loaded from: classes5.dex */
public class Arn {
    private final String account;
    private final String id;
    private final String region;
    private final String type;
    private final String vendor;

    public Arn(String str, String str2, String str3, String str4, String str5) {
        this.vendor = str;
        this.region = str2;
        this.account = str3;
        this.type = str4;
        this.id = str5;
    }

    public static Arn fromArn(String str) {
        String[] strArrSplit = str.split(":", 6);
        if (strArrSplit.length != 6) {
            throw new IllegalArgumentException("Invalid ARN");
        }
        String[] strArrSplit2 = strArrSplit[5].split("[:/]", 2);
        return new Arn(strArrSplit[2], strArrSplit[3], strArrSplit[4], strArrSplit2[0], strArrSplit2[1]);
    }

    public String toArn() {
        return String.format("arn:aws:%s:%s:%s:%s:%s", this.vendor, this.region, this.account, this.type, this.id);
    }

    public String getVendor() {
        return this.vendor;
    }

    public String getRegion() {
        return this.region;
    }

    public String getAccount() {
        return this.account;
    }

    public String getType() {
        return this.type;
    }

    public String getId() {
        return this.id;
    }
}
