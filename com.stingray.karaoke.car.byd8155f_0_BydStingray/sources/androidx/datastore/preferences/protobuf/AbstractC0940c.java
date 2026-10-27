package androidx.datastore.preferences.protobuf;

/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0940c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class f11830a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f11831b;

    static {
        Class<?> cls;
        Class<?> cls2 = null;
        try {
            cls = Class.forName("libcore.io.Memory");
        } catch (Throwable unused) {
            cls = null;
        }
        f11830a = cls;
        try {
            cls2 = Class.forName("org.robolectric.Robolectric");
        } catch (Throwable unused2) {
        }
        f11831b = cls2 != null;
    }

    public static boolean a() {
        return (f11830a == null || f11831b) ? false : true;
    }
}
