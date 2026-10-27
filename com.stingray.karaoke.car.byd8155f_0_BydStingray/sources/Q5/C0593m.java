package Q5;

import T.C0735d;
import T.C0761q;
import n7.AbstractC1985B;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C0593m implements InterfaceC2785n {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ g0.o f6950m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f6951n;
    public final /* synthetic */ long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ long f6952p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ N0.L f6953q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ int f6954r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f6955s;

    public /* synthetic */ C0593m(g0.o oVar, int i7, long j3, long j10, N0.L l, Y0.i iVar, int i10) {
        this.f6950m = oVar;
        this.f6951n = i7;
        this.o = j3;
        this.f6952p = j10;
        this.f6953q = l;
        this.f6955s = iVar;
        this.f6954r = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                ((Integer) obj2).getClass();
                int iZ = C0735d.Z(this.f6951n | 1);
                N0.L l = this.f6953q;
                io.sentry.config.a.c(this.f6950m, (String) this.f6955s, this.o, this.f6952p, l, (C0761q) obj, iZ, this.f6954r);
                break;
            default:
                ((Integer) obj2).getClass();
                int iZ2 = C0735d.Z(this.f6954r | 1);
                N0.L l10 = this.f6953q;
                Y0.i iVar = (Y0.i) this.f6955s;
                AbstractC1985B.b(this.f6950m, this.f6951n, this.o, this.f6952p, l10, iVar, (C0761q) obj, iZ2);
                break;
        }
        return m7.q.f19267a;
    }

    public /* synthetic */ C0593m(g0.o oVar, String str, long j3, long j10, N0.L l, int i7, int i10) {
        this.f6950m = oVar;
        this.f6955s = str;
        this.o = j3;
        this.f6952p = j10;
        this.f6953q = l;
        this.f6951n = i7;
        this.f6954r = i10;
    }
}
