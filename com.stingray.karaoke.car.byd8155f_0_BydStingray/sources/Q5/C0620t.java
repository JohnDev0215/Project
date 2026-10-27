package Q5;

import T.C0735d;
import T.C0761q;
import a.AbstractC0904a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0620t implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f7064m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f7065n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f7066p;

    public /* synthetic */ C0620t(Object obj, boolean z6, int i7, int i10, int i11) {
        this.l = i11;
        this.f7066p = obj;
        this.f7064m = z6;
        this.f7065n = i7;
        this.o = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = this.l;
        C0761q c0761q = (C0761q) obj;
        ((Integer) obj2).getClass();
        switch (i7) {
            case 0:
                int iZ = C0735d.Z(this.o | 1);
                l9.d.y((g0.o) this.f7066p, this.f7064m, this.f7065n, c0761q, iZ);
                break;
            default:
                int iZ2 = C0735d.Z(this.f7065n | 1);
                AbstractC0904a.c((e6.g) this.f7066p, this.f7064m, c0761q, iZ2, this.o);
                break;
        }
        return m7.q.f19267a;
    }
}
