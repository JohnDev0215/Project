package defpackage;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.R$dimen;
import defpackage.v1;

/* JADX INFO: compiled from: MenuPopupHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class u1 {
    public final Context a;
    public final p1 b;
    public final boolean c;
    public final int d;
    public final int e;
    public View f;
    public int g;
    public boolean h;
    public v1.a i;
    public t1 j;
    public PopupWindow.OnDismissListener k;
    public final PopupWindow.OnDismissListener l;

    /* JADX INFO: compiled from: MenuPopupHelper.java */
    public class a implements PopupWindow.OnDismissListener {
        public a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            u1.this.e();
        }
    }

    public u1(Context context, p1 p1Var, View view, boolean z, int i) {
        this(context, p1Var, view, z, i, 0);
    }

    public final t1 a() {
        Display defaultDisplay = ((WindowManager) this.a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        t1 m1Var = Math.min(point.x, point.y) >= this.a.getResources().getDimensionPixelSize(R$dimen.abc_cascading_menus_min_smallest_width) ? new m1(this.a, this.f, this.d, this.e, this.c) : new z1(this.a, this.b, this.f, this.d, this.e, this.c);
        m1Var.o(this.b);
        m1Var.x(this.l);
        m1Var.s(this.f);
        m1Var.k(this.i);
        m1Var.u(this.h);
        m1Var.v(this.g);
        return m1Var;
    }

    public void b() {
        if (d()) {
            this.j.dismiss();
        }
    }

    public t1 c() {
        if (this.j == null) {
            this.j = a();
        }
        return this.j;
    }

    public boolean d() {
        t1 t1Var = this.j;
        return t1Var != null && t1Var.b();
    }

    public void e() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void f(View view) {
        this.f = view;
    }

    public void g(boolean z) {
        this.h = z;
        t1 t1Var = this.j;
        if (t1Var != null) {
            t1Var.u(z);
        }
    }

    public void h(int i) {
        this.g = i;
    }

    public void i(PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public void j(v1.a aVar) {
        this.i = aVar;
        t1 t1Var = this.j;
        if (t1Var != null) {
            t1Var.k(aVar);
        }
    }

    public void k() {
        if (!m()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public final void l(int i, int i2, boolean z, boolean z2) {
        t1 t1VarC = c();
        t1VarC.y(z2);
        if (z) {
            if ((d9.b(this.g, t9.z(this.f)) & 7) == 5) {
                i -= this.f.getWidth();
            }
            t1VarC.w(i);
            t1VarC.z(i2);
            int i3 = (int) ((this.a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            t1VarC.t(new Rect(i - i3, i2 - i3, i + i3, i2 + i3));
        }
        t1VarC.g();
    }

    public boolean m() {
        if (d()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        l(0, 0, false, false);
        return true;
    }

    public boolean n(int i, int i2) {
        if (d()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        l(i, i2, true, true);
        return true;
    }

    public u1(Context context, p1 p1Var, View view, boolean z, int i, int i2) {
        this.g = 8388611;
        this.l = new a();
        this.a = context;
        this.b = p1Var;
        this.f = view;
        this.c = z;
        this.d = i;
        this.e = i2;
    }
}
