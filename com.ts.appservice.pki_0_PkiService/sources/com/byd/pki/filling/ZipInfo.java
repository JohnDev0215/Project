package com.byd.pki.filling;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class ZipInfo {
    public static final int AES_DECODE_ERROR = 2;
    public static final int CERTIFICATE_VERIFY_ERROR = 4;
    public static final int ECC_DECODE_ERROR = 1;
    public static final int NO_ERROR = 0;
    public static final int UPGRADE_ZIP_VERIFY_ERROR = 3;
    private int errorType;
    private boolean verifyResult;
    private String zipPath;

    public int getErrorType() {
        return this.errorType;
    }

    public boolean getVerifyResult() {
        return this.verifyResult;
    }

    public String getZipPath() {
        return this.zipPath;
    }

    public void setErrorType(int i) {
        this.errorType = i;
    }

    public void setVerifyResult(boolean z) {
        this.verifyResult = z;
    }

    public void setZipPath(String str) {
        this.zipPath = str;
    }

    public String toString() {
        return "ZipInfo{zipPath='" + this.zipPath + "', verifyResult=" + this.verifyResult + ", errorType=" + this.errorType + '}';
    }
}
