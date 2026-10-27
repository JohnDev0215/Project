package E;

import java.util.List;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class r extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final r f1402n = new r(1, 0);
    public static final r o = new r(1, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f1403m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(int i7, int i10) {
        super(i7);
        this.f1403m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f1403m) {
            case 0:
                return m7.q.f19267a;
            default:
                List list = (List) obj;
                return new D(((Number) list.get(0)).intValue(), ((Number) list.get(1)).intValue());
        }
    }
}
