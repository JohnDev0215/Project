package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes.dex */
public final class F implements M {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public M[] f11791a;

    @Override // androidx.datastore.preferences.protobuf.M
    public final X a(Class cls) {
        for (M m10 : this.f11791a) {
            if (m10.b(cls)) {
                return m10.a(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }

    @Override // androidx.datastore.preferences.protobuf.M
    public final boolean b(Class cls) {
        for (M m10 : this.f11791a) {
            if (m10.b(cls)) {
                return true;
            }
        }
        return false;
    }
}
