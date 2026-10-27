package com.google.firebase.installations.remote;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class InstallationResponse$ResponseCode {
    public static final InstallationResponse$ResponseCode c;
    public static final InstallationResponse$ResponseCode e;
    public static final /* synthetic */ InstallationResponse$ResponseCode[] k;

    static {
        InstallationResponse$ResponseCode installationResponse$ResponseCode = new InstallationResponse$ResponseCode("OK", 0);
        c = installationResponse$ResponseCode;
        InstallationResponse$ResponseCode installationResponse$ResponseCode2 = new InstallationResponse$ResponseCode("BAD_CONFIG", 1);
        e = installationResponse$ResponseCode2;
        k = new InstallationResponse$ResponseCode[]{installationResponse$ResponseCode, installationResponse$ResponseCode2};
    }

    public static InstallationResponse$ResponseCode valueOf(String str) {
        return (InstallationResponse$ResponseCode) Enum.valueOf(InstallationResponse$ResponseCode.class, str);
    }

    public static InstallationResponse$ResponseCode[] values() {
        return (InstallationResponse$ResponseCode[]) k.clone();
    }
}
