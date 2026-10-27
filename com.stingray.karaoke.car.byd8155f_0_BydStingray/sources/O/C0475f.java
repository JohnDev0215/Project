package O;

import T.C0735d;
import T.C0761q;
import a.AbstractC0904a;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: O.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0475f extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4960m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f4961n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f4962p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f4963q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0475f(int i7, g0.o oVar, InterfaceC2772a interfaceC2772a, boolean z6) {
        super(2);
        this.f4962p = oVar;
        this.f4963q = interfaceC2772a;
        this.f4961n = z6;
        this.o = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = this.f4960m;
        C0761q c0761q = (C0761q) obj;
        ((Number) obj2).intValue();
        switch (i7) {
            case 0:
                AbstractC0904a.o(C0735d.Z(this.o | 1), c0761q, (g0.o) this.f4962p, (InterfaceC2772a) this.f4963q, this.f4961n);
                break;
            default:
                int iZ = C0735d.Z(this.o | 1);
                l9.l.y(this.f4961n, (Y0.h) this.f4962p, (K) this.f4963q, c0761q, iZ);
                break;
        }
        return m7.q.f19267a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0475f(boolean z6, Y0.h hVar, K k10, int i7) {
        super(2);
        this.f4961n = z6;
        this.f4962p = hVar;
        this.f4963q = k10;
        this.o = i7;
    }
}
