package m1;

import g1.q;

/* JADX INFO: loaded from: classes.dex */
public final class g implements d, l1.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f18940a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f18941b;
    public o1.h c;
    public int d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f18942e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f18943f = 0.0f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f18944g;

    public g(q qVar) {
        this.f18940a = qVar;
    }

    @Override // m1.d, l1.h
    public final void a() {
        this.c.W(this.f18941b);
        int i7 = this.d;
        if (i7 != -1) {
            o1.h hVar = this.c;
            if (i7 <= -1) {
                hVar.getClass();
                return;
            }
            hVar.f20379r0 = -1.0f;
            hVar.f20380s0 = i7;
            hVar.f20381t0 = -1;
            return;
        }
        int i10 = this.f18942e;
        if (i10 != -1) {
            o1.h hVar2 = this.c;
            if (i10 <= -1) {
                hVar2.getClass();
                return;
            }
            hVar2.f20379r0 = -1.0f;
            hVar2.f20380s0 = -1;
            hVar2.f20381t0 = i10;
            return;
        }
        o1.h hVar3 = this.c;
        float f10 = this.f18943f;
        if (f10 <= -1.0f) {
            hVar3.getClass();
            return;
        }
        hVar3.f20379r0 = f10;
        hVar3.f20380s0 = -1;
        hVar3.f20381t0 = -1;
    }

    @Override // l1.h
    public final o1.d b() {
        if (this.c == null) {
            this.c = new o1.h();
        }
        return this.c;
    }

    @Override // l1.h
    public final void c(o1.d dVar) {
        if (dVar instanceof o1.h) {
            this.c = (o1.h) dVar;
        } else {
            this.c = null;
        }
    }

    @Override // l1.h
    public final d d() {
        return null;
    }

    @Override // l1.h
    public final Object getKey() {
        return this.f18944g;
    }
}
