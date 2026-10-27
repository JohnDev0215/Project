package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes.dex */
public final class P implements Y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0957u f11813a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final j0 f11814b;
    public final C0951n c;

    public P(j0 j0Var, C0951n c0951n, AbstractC0957u abstractC0957u) {
        this.f11814b = j0Var;
        c0951n.getClass();
        this.c = c0951n;
        this.f11813a = abstractC0957u;
    }

    @Override // androidx.datastore.preferences.protobuf.Y
    public final void a(Object obj, Object obj2) {
        Z.B(this.f11814b, obj, obj2);
    }

    @Override // androidx.datastore.preferences.protobuf.Y
    public final void b(Object obj, G g7) {
        this.c.getClass();
        A7.k.q(obj);
        throw null;
    }

    @Override // androidx.datastore.preferences.protobuf.Y
    public final int c(AbstractC0957u abstractC0957u) {
        this.f11814b.getClass();
        return abstractC0957u.unknownFields.hashCode();
    }

    @Override // androidx.datastore.preferences.protobuf.Y
    public final void d(Object obj) {
        this.f11814b.getClass();
        j0.b(obj);
        this.c.getClass();
        A7.k.q(obj);
        throw null;
    }

    @Override // androidx.datastore.preferences.protobuf.Y
    public final boolean e(AbstractC0957u abstractC0957u, Object obj) {
        this.f11814b.getClass();
        return abstractC0957u.unknownFields.equals(((AbstractC0957u) obj).unknownFields);
    }

    @Override // androidx.datastore.preferences.protobuf.Y
    public final boolean f(Object obj) {
        this.c.getClass();
        A7.k.q(obj);
        throw null;
    }

    @Override // androidx.datastore.preferences.protobuf.Y
    public final int g(AbstractC0957u abstractC0957u) {
        this.f11814b.getClass();
        i0 i0Var = abstractC0957u.unknownFields;
        int i7 = i0Var.d;
        if (i7 != -1) {
            return i7;
        }
        int iM = 0;
        for (int i10 = 0; i10 < i0Var.f11859a; i10++) {
            int i11 = i0Var.f11860b[i10] >>> 3;
            iM += C0948k.m(3, (C0944g) i0Var.c[i10]) + C0948k.E(2, i11) + (C0948k.D(1) * 2);
        }
        i0Var.d = iM;
        return iM;
    }

    @Override // androidx.datastore.preferences.protobuf.Y
    public final AbstractC0957u h() {
        AbstractC0957u abstractC0957u = this.f11813a;
        return abstractC0957u != null ? abstractC0957u.k() : ((AbstractC0955s) abstractC0957u.e(5)).b();
    }

    @Override // androidx.datastore.preferences.protobuf.Y
    public final void i(Object obj, E5.z zVar, C0950m c0950m) {
        this.f11814b.getClass();
        j0.a(obj);
        this.c.getClass();
        obj.getClass();
        throw new ClassCastException();
    }
}
