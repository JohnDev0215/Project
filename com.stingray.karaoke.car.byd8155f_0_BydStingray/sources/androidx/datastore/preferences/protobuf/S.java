package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes.dex */
public abstract class S {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Q f11815a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Q f11816b;

    static {
        Q q4 = null;
        try {
            q4 = (Q) Class.forName("androidx.datastore.preferences.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        f11815a = q4;
        f11816b = new Q();
    }
}
