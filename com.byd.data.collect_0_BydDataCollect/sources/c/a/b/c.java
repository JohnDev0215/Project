package c.a.b;

import java.security.Provider;

/* JADX INFO: compiled from: GlobalBouncyCastleProvider.java */
/* JADX INFO: loaded from: classes.dex */
public enum c {
    INSTANCE;


    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f3088c = true;
    private Provider provider;

    c() {
        try {
            this.provider = g.a();
        } catch (NoClassDefFoundError unused) {
        }
    }

    public Provider a() {
        if (f3088c) {
            return this.provider;
        }
        return null;
    }
}
