package cn.hutool.crypto;

import java.security.Provider;

/* JADX INFO: loaded from: classes.dex */
public enum GlobalBouncyCastleProvider {
    INSTANCE;


    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f2954b = true;
    private Provider provider;

    GlobalBouncyCastleProvider() {
        try {
            this.provider = c.a();
        } catch (NoClassDefFoundError unused) {
        }
    }

    public static void setUseBouncyCastle(boolean z) {
        f2954b = z;
    }

    public Provider getProvider() {
        if (f2954b) {
            return this.provider;
        }
        return null;
    }
}
