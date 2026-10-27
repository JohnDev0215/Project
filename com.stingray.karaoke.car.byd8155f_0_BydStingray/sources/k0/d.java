package K0;

import A7.o;
import m7.q;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class d extends o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final d f3488n = new d(1, 0);
    public static final d o = new d(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final d f3489p = new d(1, 2);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3490m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(int i7, int i10) {
        super(i7);
        this.f3490m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f3490m) {
            case 0:
                ((Number) obj).longValue();
                return q.f19267a;
            case 1:
                return Integer.valueOf(((m) obj).f3505b);
            default:
                a1.i iVar = ((m) obj).c;
                return Integer.valueOf(iVar.d - iVar.f11036b);
        }
    }
}
