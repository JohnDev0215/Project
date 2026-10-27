package g.a.e.b.e;

import g.a.e.b.e.k;

/* JADX INFO: loaded from: classes.dex */
public final class c extends k {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5614e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f5615f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f5616g;

    public static class b extends k.a<b> {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5617e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5618f;

        public b() {
            super(2);
            this.f5617e = 0;
            this.f5618f = 0;
        }

        public k k() {
            return new c(this);
        }

        @Override // g.a.e.b.e.k.a
        /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
        public b e() {
            return this;
        }

        public b m(int i2) {
            this.f5617e = i2;
            return this;
        }

        public b n(int i2) {
            this.f5618f = i2;
            return this;
        }
    }

    public c(b bVar) {
        super(bVar);
        this.f5614e = 0;
        this.f5615f = bVar.f5617e;
        this.f5616g = bVar.f5618f;
    }

    @Override // g.a.e.b.e.k
    public byte[] d() {
        byte[] bArrD = super.d();
        g.a.f.f.d(this.f5614e, bArrD, 16);
        g.a.f.f.d(this.f5615f, bArrD, 20);
        g.a.f.f.d(this.f5616g, bArrD, 24);
        return bArrD;
    }

    public int e() {
        return this.f5615f;
    }

    public int f() {
        return this.f5616g;
    }
}
