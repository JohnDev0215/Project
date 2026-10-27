package p;

import android.view.View;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class na1 {
    public boolean a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public List k;
    public boolean l;

    public final void a(View view) {
        int iC;
        int size = this.k.size();
        View view2 = null;
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < size; i2++) {
            View view3 = ((n22) this.k.get(i2)).a;
            z12 z12Var = (z12) view3.getLayoutParams();
            if (view3 != view && !z12Var.a.j() && (iC = (z12Var.a.c() - this.d) * this.e) >= 0 && iC < i) {
                view2 = view3;
                if (iC == 0) {
                    break;
                } else {
                    i = iC;
                }
            }
        }
        if (view2 == null) {
            this.d = -1;
        } else {
            this.d = ((z12) view2.getLayoutParams()).a.c();
        }
    }

    public final View b(f22 f22Var) {
        List list = this.k;
        if (list == null) {
            View view = f22Var.i(this.d, Long.MAX_VALUE).a;
            this.d += this.e;
            return view;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            View view2 = ((n22) this.k.get(i)).a;
            z12 z12Var = (z12) view2.getLayoutParams();
            if (!z12Var.a.j() && this.d == z12Var.a.c()) {
                a(view2);
                return view2;
            }
        }
        return null;
    }
}
