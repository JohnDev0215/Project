package defpackage;

import java.security.SecureRandom;

/* JADX INFO: compiled from: IntentWithRequestMetadataHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class r11 {
    public static final Object e = new Object();
    public static r11 f;
    public final SecureRandom a = new SecureRandom();
    public a b;
    public byte[] c;
    public String d;

    /* JADX INFO: compiled from: IntentWithRequestMetadataHandler.java */
    public static class a {
        public final boolean a;
        public final boolean b;

        public a(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }
    }

    public static r11 a() {
        synchronized (e) {
            if (f == null) {
                f = new r11();
            }
        }
        return f;
    }
}
