package g.a.e.b.e;

import g.a.e.b.e.k;

/* JADX INFO: loaded from: classes.dex */
public final class e extends k {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5621e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f5622f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f5623g;

    public static class b extends k.a<b> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5624e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5625f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f5626g;

        public b() {
            super(1);
            this.f5624e = 0;
            this.f5625f = 0;
            this.f5626g = 0;
        }

        public k l() {
            return new e(this);
        }

        @Override // g.a.e.b.e.k.a
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
        public b e() {
            return this;
        }

        public b n(int i2) {
            this.f5624e = i2;
            return this;
        }

        public b o(int i2) {
            this.f5625f = i2;
            return this;
        }

        public b p(int i2) {
            this.f5626g = i2;
            return this;
        }
    }

    public e(b bVar) {
        super(bVar);
        this.f5621e = bVar.f5624e;
        this.f5622f = bVar.f5625f;
        this.f5623g = bVar.f5626g;
    }

    @Override // g.a.e.b.e.k
    public byte[] d() {
        byte[] bArrD = super.d();
        g.a.f.f.d(this.f5621e, bArrD, 16);
        g.a.f.f.d(this.f5622f, bArrD, 20);
        g.a.f.f.d(this.f5623g, bArrD, 24);
        return bArrD;
    }

    public int e() {
        return this.f5621e;
    }

    public int f() {
        return this.f5622f;
    }

    public int g() {
        return this.f5623g;
    }
}
