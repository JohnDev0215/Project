package net.lingala.zip4j.model.enums;

import net.lingala.zip4j.exception.ZipException;

/* JADX INFO: loaded from: classes.dex */
public enum AesVersion {
    ONE(1),
    TWO(2);

    private int versionNumber;

    AesVersion(int i) {
        this.versionNumber = i;
    }

    public static AesVersion getFromVersionNumber(int i) throws ZipException {
        AesVersion[] aesVersionArrValues = values();
        for (int i2 = 0; i2 < 2; i2++) {
            AesVersion aesVersion = aesVersionArrValues[i2];
            if (aesVersion.versionNumber == i) {
                return aesVersion;
            }
        }
        throw new ZipException("Unsupported Aes version");
    }

    public int getVersionNumber() {
        return this.versionNumber;
    }
}
