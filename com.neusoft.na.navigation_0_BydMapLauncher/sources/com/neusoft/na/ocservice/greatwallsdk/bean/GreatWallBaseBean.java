package com.neusoft.na.ocservice.greatwallsdk.bean;

/* JADX INFO: loaded from: classes2.dex */
public class GreatWallBaseBean {
    private int actionType;
    private String auth = "";
    private int moduleType;
    private int operaType;
    private int statusCode;

    public int getActionType() {
        return this.actionType;
    }

    public void setActionType(int i) {
        this.actionType = i;
    }

    public String getAuth() {
        return this.auth;
    }

    public void setAuth(String str) {
        this.auth = str;
    }

    public int getModuleType() {
        return this.moduleType;
    }

    public void setModuleType(int i) {
        this.moduleType = i;
    }

    public int getOperaType() {
        return this.operaType;
    }

    public void setOperaType(int i) {
        this.operaType = i;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }
}
