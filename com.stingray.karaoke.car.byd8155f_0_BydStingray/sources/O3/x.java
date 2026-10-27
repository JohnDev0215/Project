package o3;

import n3.C1945j;
import w.AbstractC2565A;
import x.AbstractC2631e;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class x extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final x f20511n = new x(1, 0);
    public static final x o = new x(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final x f20512p = new x(1, 2);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f20513m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ x(int i7, int i10) {
        super(i7);
        this.f20513m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f20513m) {
            case 0:
                return ((C1945j) obj).f19868q;
            case 1:
                return AbstractC2565A.b(AbstractC2631e.q(700, 0, null, 6), 2);
            default:
                return AbstractC2565A.c(AbstractC2631e.q(700, 0, null, 6), 2);
        }
    }
}
