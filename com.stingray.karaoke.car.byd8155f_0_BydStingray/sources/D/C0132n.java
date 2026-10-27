package D;

import java.util.List;

/* JADX INFO: renamed from: D.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0132n implements C0.K {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0132n f1089b = new C0132n(0);
    public static final C0132n c = new C0132n(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1090a;

    public /* synthetic */ C0132n(int i7) {
        this.f1090a = i7;
    }

    @Override // C0.K
    public final C0.L h(C0.M m10, List list, long j3) {
        switch (this.f1090a) {
            case 0:
                return m10.m0(a1.a.j(j3), a1.a.i(j3), n7.v.l, C0131m.f1086n);
            default:
                return m10.m0(a1.a.f(j3) ? a1.a.h(j3) : 0, a1.a.e(j3) ? a1.a.g(j3) : 0, n7.v.l, C0131m.f1087p);
        }
    }
}
