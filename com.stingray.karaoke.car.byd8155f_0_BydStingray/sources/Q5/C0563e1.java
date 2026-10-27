package Q5;

import T.C0735d;
import T.C0761q;
import k6.AbstractC1692D;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.e1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0563e1 implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ b0.b f6771m;

    public /* synthetic */ C0563e1(b0.b bVar, int i7) {
        this.l = i7;
        this.f6771m = bVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    this.f6771m.invoke(c0761q, 6);
                }
                break;
            default:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    C0735d.a(AbstractC1692D.f18238a.a(h6.i.m(c0761q2)), b0.g.b(-2072367019, new C0563e1(this.f6771m, 0), c0761q2), c0761q2, 56);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
