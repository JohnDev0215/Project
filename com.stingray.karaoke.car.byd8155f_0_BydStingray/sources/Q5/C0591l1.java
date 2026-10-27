package Q5;

import z7.InterfaceC2772a;

/* JADX INFO: renamed from: Q5.l1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0591l1 implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ b6.f f6943m;

    public /* synthetic */ C0591l1(b6.f fVar, int i7) {
        this.l = i7;
        this.f6943m = fVar;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.l) {
            case 0:
                this.f6943m.i(false);
                break;
            case 1:
                this.f6943m.i(true);
                break;
            default:
                this.f6943m.f12234b.f();
                break;
        }
        return m7.q.f19267a;
    }
}
