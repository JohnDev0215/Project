package com.byd.pki.filling;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class CertP12Info {
    private String certP12Path;
    private String certP12Secret;
    private String secretType;

    public String getCertP12Path() {
        return this.certP12Path;
    }

    public String getCertP12Secret() {
        return this.certP12Secret;
    }

    public String getSecretType() {
        return this.secretType;
    }

    public void setCertP12Path(String str) {
        this.certP12Path = str;
    }

    public void setCertP12Secret(String str) {
        this.certP12Secret = str;
    }

    public void setSecretType(String str) {
        this.secretType = str;
    }

    public String toString() {
        return "CertP12Info{certP12Path='" + this.certP12Path + "', certP12Secret='" + this.certP12Secret + "', secretType='" + this.secretType + "'}";
    }
}
