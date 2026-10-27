package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes.dex */
public abstract class L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final K f11798a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final K f11799b;

    static {
        K k10 = null;
        try {
            k10 = (K) Class.forName("androidx.datastore.preferences.protobuf.MapFieldSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        f11798a = k10;
        f11799b = new K();
    }
}
