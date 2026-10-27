package K;

import T.C0735d;
import T.C0761q;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class H extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3163m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f3164n;
    public final /* synthetic */ int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f3165p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(O.K k10, boolean z6, int i7) {
        super(2);
        this.f3165p = k10;
        this.f3164n = z6;
        this.o = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7 = this.f3163m;
        C0761q c0761q = (C0761q) obj;
        ((Number) obj2).intValue();
        switch (i7) {
            case 0:
                X.h((O.K) this.f3165p, this.f3164n, c0761q, C0735d.Z(this.o | 1));
                break;
            default:
                h6.q.f(this.f3164n, (InterfaceC2785n) this.f3165p, c0761q, this.o | 1);
                break;
        }
        return m7.q.f19267a;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public H(boolean z6, InterfaceC2785n interfaceC2785n, int i7) {
        super(2);
        this.f3164n = z6;
        this.f3165p = interfaceC2785n;
        this.o = i7;
    }
}
