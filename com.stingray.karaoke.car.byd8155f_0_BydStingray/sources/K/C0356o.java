package K;

import T.C0761q;
import android.R;
import x3.AbstractC2673a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: K.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0356o extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3361m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0356o(int i7) {
        super(2);
        this.f3361m = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        int i7;
        C0761q c0761q = (C0761q) obj;
        ((Number) obj2).intValue();
        c0761q.Q(-1451087197);
        int i10 = this.f3361m;
        if (i10 == 1) {
            i7 = R.string.cut;
        } else if (i10 == 2) {
            i7 = R.string.copy;
        } else if (i10 == 3) {
            i7 = R.string.paste;
        } else {
            if (i10 != 4) {
                throw null;
            }
            i7 = R.string.selectAll;
        }
        String strX = AbstractC2673a.X(i7, c0761q);
        c0761q.p(false);
        return strX;
    }
}
