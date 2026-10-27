package k3;

import o2.C2024a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class T implements h2.l {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ e2.O f17858m;

    public /* synthetic */ T(int i7, e2.O o) {
        this.l = i7;
        this.f17858m = o;
    }

    @Override // h2.l
    public final void b(Object obj) {
        switch (this.l) {
            case 0:
                ((e2.U) obj).H(this.f17858m);
                break;
            case 1:
                ((e2.U) obj).f(this.f17858m);
                break;
            case 2:
                ((e2.U) obj).H(this.f17858m);
                break;
            case 3:
                ((e2.U) obj).f(this.f17858m);
                break;
            default:
                ((o2.j) obj).f20435n = this.f17858m;
                break;
        }
    }

    public /* synthetic */ T(C2024a c2024a, e2.O o) {
        this.l = 4;
        this.f17858m = o;
    }
}
