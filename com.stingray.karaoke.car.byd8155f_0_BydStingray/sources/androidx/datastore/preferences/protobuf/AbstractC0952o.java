package androidx.datastore.preferences.protobuf;

/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0952o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0951n f11872a = new C0951n();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0951n f11873b;

    static {
        C0951n c0951n = null;
        try {
            c0951n = (C0951n) Class.forName("androidx.datastore.preferences.protobuf.ExtensionSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        f11873b = c0951n;
    }
}
