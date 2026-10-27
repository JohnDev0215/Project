package Q5;

import D.C0142y;
import T.C0735d;
import T.C0761q;
import k6.AbstractC1692D;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class S implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f6533m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f6534n;
    public final /* synthetic */ Object o;

    public /* synthetic */ S(boolean z6, Object obj, String str, int i7) {
        this.l = i7;
        this.f6533m = z6;
        this.o = obj;
        this.f6534n = str;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    l6.d.b(this.f6533m, false, b0.g.b(82696911, new I5.u((C0142y) this.o, 1, this.f6534n), c0761q), c0761q, 384, 2);
                }
                break;
            default:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    C0735d.a(AbstractC1692D.f18239b.a(Boolean.valueOf(this.f6533m)), b0.g.b(1484860411, new I5.u((b0.b) this.o, 4, this.f6534n), c0761q2), c0761q2, 56);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
