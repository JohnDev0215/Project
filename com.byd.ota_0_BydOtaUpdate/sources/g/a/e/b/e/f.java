package g.a.e.b.e;

import g.a.e.b.e.k;

/* JADX INFO: loaded from: classes.dex */
public final class f extends k {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5627e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f5628f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f5629g;

    public static class b extends k.a<b> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5630e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5631f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f5632g;

        public b() {
            super(0);
            this.f5630e = 0;
            this.f5631f = 0;
            this.f5632g = 0;
        }

        public k l() {
            return new f(this);
        }

        @Override // g.a.e.b.e.k.a
        /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
        public b e() {
            return this;
        }

        public b n(int i2) {
            this.f5631f = i2;
            return this;
        }

        public b o(int i2) {
            this.f5632g = i2;
            return this;
        }

        public b p(int i2) {
            this.f5630e = i2;
            return this;
        }
    }

    public f(b bVar) {
        super(bVar);
        this.f5627e = bVar.f5630e;
        this.f5628f = bVar.f5631f;
        this.f5629g = bVar.f5632g;
    }

    @Override // g.a.e.b.e.k
    public byte[] d() {
        byte[] bArrD = super.d();
        g.a.f.f.d(this.f5627e, bArrD, 16);
        g.a.f.f.d(this.f5628f, bArrD, 20);
        g.a.f.f.d(this.f5629g, bArrD, 24);
        return bArrD;
    }

    public int e() {
        return this.f5628f;
    }

    public int f() {
        return this.f5629g;
    }

    public int g() {
        return this.f5627e;
    }
}
