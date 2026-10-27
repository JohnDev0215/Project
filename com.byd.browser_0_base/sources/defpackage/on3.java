package defpackage;

import android.view.View;
import defpackage.om3;
import defpackage.qh3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ViewResizer.java */
/* JADX INFO: loaded from: classes2.dex */
public class on3 implements db0 {
    public final int e;
    public final int f;
    public final View g;
    public final eb0 h;
    public final qh3 i;
    public int j;

    public on3(View view, qh3 qh3Var, int i, int i2) {
        this.g = view;
        this.e = i;
        this.f = i2;
        this.i = qh3Var;
        this.h = new eb0(view, qh3Var, this);
    }

    public final void a() {
        eb0 eb0Var = this.h;
        eb0Var.h.a(eb0Var);
    }

    public int b() {
        if (!(this.j == 2)) {
            return this.e;
        }
        return Math.max(this.f, (int) (((r0.getConfiguration().screenWidthDp - 600) / 2.0f) * this.i.c.getResources().getDisplayMetrics().density));
    }

    @Override // defpackage.db0
    public final void d(qh3.b bVar) {
        this.j = bVar.a;
        int iB = b();
        View view = this.g;
        int paddingTop = view.getPaddingTop();
        int paddingBottom = view.getPaddingBottom();
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(view, iB, paddingTop, iB, paddingBottom);
    }
}
