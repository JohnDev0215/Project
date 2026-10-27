package K;

import N0.C0411f;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: K.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0352m extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3336m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ J0 f3337n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0352m(J0 j02, int i7) {
        super(0);
        this.f3336m = i7;
        this.f3337n = j02;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        N0.G g7;
        switch (this.f3336m) {
            case 0:
                J0 j02 = this.f3337n;
                return Boolean.valueOf(j02 != null ? ((Boolean) new C0352m(j02, 2).a()).booleanValue() : false);
            case 1:
                J0 j03 = this.f3337n;
                return Boolean.valueOf(j03 != null ? ((Boolean) new C0352m(j03, 2).a()).booleanValue() : false);
            default:
                J0 j04 = this.f3337n;
                C0411f c0411f = j04.c;
                N0.H h10 = (N0.H) j04.f3173b.getValue();
                return Boolean.valueOf(A7.m.a(c0411f, (h10 == null || (g7 = h10.f4348a) == null) ? null : g7.f4340a));
        }
    }
}
