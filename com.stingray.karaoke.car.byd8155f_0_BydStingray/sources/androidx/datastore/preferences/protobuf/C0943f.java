package androidx.datastore.preferences.protobuf;

/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0943f extends C0944g {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f11839p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f11840q;

    public C0943f(int i7, int i10, byte[] bArr) {
        super(bArr);
        C0944g.d(i7, i7 + i10, bArr.length);
        this.f11839p = i7;
        this.f11840q = i10;
    }

    @Override // androidx.datastore.preferences.protobuf.C0944g
    public final byte a(int i7) {
        int i10 = this.f11840q;
        if (((i10 - (i7 + 1)) | i7) >= 0) {
            return this.f11845m[this.f11839p + i7];
        }
        if (i7 < 0) {
            throw new ArrayIndexOutOfBoundsException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "Index < 0: "));
        }
        throw new ArrayIndexOutOfBoundsException(A7.k.i("Index > length: ", ", ", i7, i10));
    }

    @Override // androidx.datastore.preferences.protobuf.C0944g
    public final void m(int i7, byte[] bArr) {
        System.arraycopy(this.f11845m, this.f11839p, bArr, 0, i7);
    }

    @Override // androidx.datastore.preferences.protobuf.C0944g
    public final int q() {
        return this.f11839p;
    }

    @Override // androidx.datastore.preferences.protobuf.C0944g
    public final byte r(int i7) {
        return this.f11845m[this.f11839p + i7];
    }

    @Override // androidx.datastore.preferences.protobuf.C0944g
    public final int size() {
        return this.f11840q;
    }
}
