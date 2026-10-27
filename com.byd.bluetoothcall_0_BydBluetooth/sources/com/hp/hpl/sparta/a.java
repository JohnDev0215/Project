package com.hp.hpl.sparta;

/* JADX INFO: loaded from: classes.dex */
class a implements l, j {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final k f4164c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private d f4165d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final c f4166e;
    private l f;

    public a() {
        this(null);
    }

    public a(k kVar) {
        this.f4165d = null;
        this.f4166e = new c();
        this.f = null;
        this.f4164c = kVar == null ? l.f4181a : kVar;
    }

    @Override // com.hp.hpl.sparta.j
    public void a() {
    }

    @Override // com.hp.hpl.sparta.j
    public void b(l lVar) {
        this.f = lVar;
        this.f4166e.r(lVar.toString());
    }

    @Override // com.hp.hpl.sparta.j
    public void c(d dVar) {
        this.f4165d = this.f4165d.d();
    }

    @Override // com.hp.hpl.sparta.j
    public void d(d dVar) {
        d dVar2 = this.f4165d;
        if (dVar2 == null) {
            this.f4166e.q(dVar);
        } else {
            dVar2.o(dVar);
        }
        this.f4165d = dVar;
    }

    @Override // com.hp.hpl.sparta.j
    public void e(char[] cArr, int i, int i2) {
        d dVar = this.f4165d;
        if (dVar.u() instanceof p) {
            ((p) dVar.u()).o(cArr, i, i2);
        } else {
            dVar.p(new p(new String(cArr, i, i2)));
        }
    }

    @Override // com.hp.hpl.sparta.j
    public void f() {
    }

    public c g() {
        return this.f4166e;
    }

    @Override // com.hp.hpl.sparta.l
    public String toString() {
        if (this.f == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("BuildDoc: ");
        stringBuffer.append(this.f.toString());
        return stringBuffer.toString();
    }
}
