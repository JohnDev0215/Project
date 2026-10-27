package com.byd.systemsettings.wifi2.model;

import com.byd.mvp.model.BydData;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkParams extends BydData {
    private String name;
    private String pwd;
    private CipherType type;
    private boolean hidePwd = true;
    private boolean nameErrShow = false;
    private boolean pwdErrShow = false;

    public NetworkParams() {
    }

    public void clear() {
        this.name = null;
        this.type = CipherType.OPEN;
        this.pwd = null;
        this.hidePwd = true;
        this.nameErrShow = false;
        this.pwdErrShow = false;
    }

    public void copyFrom(NetworkParams networkParams) {
        if (networkParams == null) {
            return;
        }
        this.name = networkParams.name;
        this.type = networkParams.type;
        this.pwd = networkParams.pwd;
        this.hidePwd = networkParams.hidePwd;
        this.nameErrShow = networkParams.nameErrShow;
        this.pwdErrShow = networkParams.pwdErrShow;
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

    public boolean isHidePwd() {
        return this.hidePwd;
    }

    public boolean isNameErrShow() {
        return this.nameErrShow;
    }

    public boolean isPwdErrShow() {
        return this.pwdErrShow;
    }

    public void setHidePwd(boolean z7) {
        this.hidePwd = z7;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNameErrShow(boolean z7) {
        this.nameErrShow = z7;
    }

    public void setPwd(String str) {
        this.pwd = str;
    }

    public void setPwdErrShow(boolean z7) {
        this.pwdErrShow = z7;
    }

    public void setType(CipherType cipherType) {
        this.type = cipherType;
    }

    public String toString() {
        return "NetworkParams{name='" + this.name + "', type=" + this.type + ", hidePwd=" + this.hidePwd + ", nameErrShow=" + this.nameErrShow + ", pwdErrShow=" + this.pwdErrShow + '}';
    }

    public NetworkParams(String str, CipherType cipherType, String str2) {
        this.name = str;
        this.type = cipherType;
        this.pwd = str2;
    }
}
