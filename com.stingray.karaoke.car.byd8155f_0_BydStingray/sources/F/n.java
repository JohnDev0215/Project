package F;

import java.util.List;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class n extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final n f1913n = new n(1, 0);
    public static final n o = new n(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final n f1914p = new n(1, 2);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1915m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(int i7, int i10) {
        super(i7);
        this.f1915m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f1915m) {
            case 0:
                return m7.q.f19267a;
            case 1:
                List list = (List) obj;
                return new y(((Number) list.get(0)).intValue(), ((Number) list.get(1)).intValue());
            default:
                ((Number) obj).intValue();
                return n7.u.l;
        }
    }
}
