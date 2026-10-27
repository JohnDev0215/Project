package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes.dex */
public final class r implements M {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final r f11881b = new r(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f11882a;

    public /* synthetic */ r(int i7) {
        this.f11882a = i7;
    }

    @Override // androidx.datastore.preferences.protobuf.M
    public final X a(Class cls) {
        switch (this.f11882a) {
            case 0:
                if (!AbstractC0957u.class.isAssignableFrom(cls)) {
                    throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
                }
                try {
                    return (X) AbstractC0957u.f(cls.asSubclass(AbstractC0957u.class)).e(3);
                } catch (Exception e10) {
                    throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e10);
                }
            default:
                throw new IllegalStateException("This should never be called.");
        }
    }

    @Override // androidx.datastore.preferences.protobuf.M
    public final boolean b(Class cls) {
        switch (this.f11882a) {
            case 0:
                return AbstractC0957u.class.isAssignableFrom(cls);
            default:
                return false;
        }
    }
}
