package T;

import C0.C0113u;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: T.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0745i extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0745i f8260n = new C0745i(2, 0);
    public static final C0745i o = new C0745i(2, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f8261m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0745i(int i7, int i10) {
        super(i7);
        this.f8261m = i10;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8261m) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                }
                break;
            case 1:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                }
                break;
            default:
                C0113u.f913n.b(obj);
                break;
        }
        return m7.q.f19267a;
    }
}
