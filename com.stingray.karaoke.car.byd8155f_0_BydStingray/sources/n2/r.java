package n2;

import e2.C1206n;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r implements h2.m, h2.l {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C1923x f19719m;

    public /* synthetic */ r(C1923x c1923x, int i7) {
        this.l = i7;
        this.f19719m = c1923x;
    }

    @Override // h2.l
    public void b(Object obj) {
        e2.U u4 = (e2.U) obj;
        switch (this.l) {
            case 2:
                u4.Q(this.f19719m.f19739N);
                break;
            default:
                u4.z(this.f19719m.f19741P);
                break;
        }
    }

    @Override // h2.m
    public void c(Object obj, C1206n c1206n) {
        this.f19719m.getClass();
        ((e2.U) obj).E(new e2.T(c1206n));
    }
}
