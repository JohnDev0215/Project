package Q;

import n0.AbstractC1861K;
import x.C2620I;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class F extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f5515m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ p0.h f5516n;
    public final /* synthetic */ C2620I o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C2620I f5517p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ C2620I f5518q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ C2620I f5519r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ float f5520s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ long f5521t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public F(long j3, p0.h hVar, C2620I c2620i, C2620I c2620i2, C2620I c2620i3, C2620I c2620i4, float f10, long j10) {
        super(1);
        this.f5515m = j3;
        this.f5516n = hVar;
        this.o = c2620i;
        this.f5517p = c2620i2;
        this.f5518q = c2620i3;
        this.f5519r = c2620i4;
        this.f5520s = f10;
        this.f5521t = j10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        float f10;
        p0.d dVar = (p0.d) obj;
        p0.h hVar = this.f5516n;
        J.b(dVar, 0.0f, 360.0f, this.f5515m, hVar);
        float fFloatValue = (((Number) this.o.o.getValue()).floatValue() * 216.0f) % 360.0f;
        float fFloatValue2 = ((Number) this.f5517p.o.getValue()).floatValue();
        C2620I c2620i = this.f5518q;
        float fAbs = Math.abs(fFloatValue2 - ((Number) c2620i.o.getValue()).floatValue());
        float fFloatValue3 = ((Number) c2620i.o.getValue()).floatValue() + ((Number) this.f5519r.o.getValue()).floatValue() + (fFloatValue - 90.0f);
        if (AbstractC1861K.s(hVar.c, 0)) {
            f10 = 0.0f;
        } else {
            f10 = ((this.f5520s / (J.f5558b / 2)) * 57.29578f) / 2.0f;
        }
        J.b(dVar, f10 + fFloatValue3, Math.max(fAbs, 0.1f), this.f5521t, hVar);
        return m7.q.f19267a;
    }
}
