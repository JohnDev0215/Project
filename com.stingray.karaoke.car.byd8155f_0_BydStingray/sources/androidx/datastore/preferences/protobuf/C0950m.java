package androidx.datastore.preferences.protobuf;

import java.util.Collections;

/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0950m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile C0950m f11870a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0950m f11871b;

    static {
        C0950m c0950m = new C0950m();
        Collections.emptyMap();
        f11871b = c0950m;
    }

    public static C0950m a() {
        C0950m c0950m = f11870a;
        if (c0950m == null) {
            synchronized (C0950m.class) {
                try {
                    c0950m = f11870a;
                    if (c0950m == null) {
                        Class cls = AbstractC0949l.f11869a;
                        C0950m c0950m2 = null;
                        if (cls != null) {
                            try {
                                c0950m2 = (C0950m) cls.getDeclaredMethod("getEmptyRegistry", null).invoke(null, null);
                            } catch (Exception unused) {
                            }
                        }
                        if (c0950m2 == null) {
                            c0950m2 = f11871b;
                        }
                        f11870a = c0950m2;
                        c0950m = c0950m2;
                    }
                } finally {
                }
            }
        }
        return c0950m;
    }
}
