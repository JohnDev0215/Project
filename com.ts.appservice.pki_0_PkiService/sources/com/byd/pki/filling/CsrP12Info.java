package com.byd.pki.filling;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class CsrP12Info {
    private String csrPath;
    private String privateKeyP12Secret;
    private String privateKeyP12path;
    private String secretType;

    public String getCsrPath() {
        return this.csrPath;
    }

    public String getPrivateKeyP12Secret() {
        return this.privateKeyP12Secret;
    }

    public String getPrivateKeyP12path() {
        return this.privateKeyP12path;
    }

    public String getSecretType() {
        return this.secretType;
    }

    public void setCsrPath(String str) {
        this.csrPath = str;
    }

    public void setPrivateKeyP12Secret(String str) {
        this.privateKeyP12Secret = str;
    }

    public void setPrivateKeyP12path(String str) {
        this.privateKeyP12path = str;
    }

    public void setSecretType(String str) {
        this.secretType = str;
    }

    public String toString() {
        return "CsrP12Info{csrPath='" + this.csrPath + "', privateKeyP12path='" + this.privateKeyP12path + "', privateKeyP12Secret='" + this.privateKeyP12Secret + "', secretType='" + this.secretType + "'}";
    }
}
