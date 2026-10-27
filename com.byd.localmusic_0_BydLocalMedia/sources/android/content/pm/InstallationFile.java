package android.content.pm;

import android.annotation.SystemApi;

/* JADX INFO: loaded from: classes.dex */
@SystemApi
public final class InstallationFile {
    private final InstallationFileParcel mParcel;

    public InstallationFile(int i, String str, long j, byte[] bArr, byte[] bArr2) {
        InstallationFileParcel installationFileParcel = new InstallationFileParcel();
        this.mParcel = installationFileParcel;
        installationFileParcel.location = i;
        this.mParcel.name = str;
        this.mParcel.size = j;
        this.mParcel.metadata = bArr;
        this.mParcel.signature = bArr2;
    }

    public int getLocation() {
        return this.mParcel.location;
    }

    public String getName() {
        return this.mParcel.name;
    }

    public long getLengthBytes() {
        return this.mParcel.size;
    }

    public byte[] getMetadata() {
        return this.mParcel.metadata;
    }

    public byte[] getSignature() {
        return this.mParcel.signature;
    }

    public InstallationFileParcel getData() {
        return this.mParcel;
    }
}
