package G;

import S8.AbstractC0727y;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class Z extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f2531m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ b0 f2532n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Z(b0 b0Var, int i7) {
        super(1);
        this.f2531m = i7;
        this.f2532n = b0Var;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f2531m) {
            case 0:
                I i7 = (I) this.f2532n.f2547y.a();
                int iB = i7.b();
                int i10 = 0;
                while (true) {
                    if (i10 >= iB) {
                        i10 = -1;
                    } else if (!i7.c(i10).equals(obj)) {
                        i10++;
                    }
                }
                return Integer.valueOf(i10);
            default:
                int iIntValue = ((Number) obj).intValue();
                b0 b0Var = this.f2532n;
                I i11 = (I) b0Var.f2547y.a();
                if (iIntValue >= 0 && iIntValue < i11.b()) {
                    AbstractC0727y.w(b0Var.v0(), null, null, new a0(b0Var, iIntValue, null), 3);
                    return Boolean.TRUE;
                }
                StringBuilder sbJ = M5.b.j("Can't scroll to index ", iIntValue, ", it is out of bounds [0, ");
                sbJ.append(i11.b());
                sbJ.append(')');
                throw new IllegalArgumentException(sbJ.toString().toString());
        }
    }
}
