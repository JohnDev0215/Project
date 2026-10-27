package com.byd.systemsettings.wifi2.model;

/* JADX INFO: loaded from: classes.dex */
public class NetworkParams {
    private String name;
    private String pwd;
    private CipherType type;

    public NetworkParams() {
    }

    public String getName() {
        return this.name;
    }

    public String getPwd() {
        return this.pwd;
    }

    public CipherType getType() {
        return this.type;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPwd(String str) {
        this.pwd = str;
    }

    public void setType(CipherType cipherType) {
        this.type = cipherType;
    }

    public String toString() {
        return "NetworkParams{name='" + this.name + "', type=" + this.type + '}';
    }

    public NetworkParams(String str, CipherType cipherType, String str2) {
        this.name = str;
        this.type = cipherType;
        this.pwd = str2;
    }
}
