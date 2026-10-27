package p;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import com.spotify.music.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class hj1 {
    public final Context a;
    public final si1 b;
    public final boolean c;
    public final int d;
    public final int e;
    public View f;
    public boolean h;
    public nj1 i;
    public ej1 j;
    public PopupWindow.OnDismissListener k;
    public int g = 8388611;
    public final fj1 l = new fj1(this);

    public hj1(int i, int i2, Context context, View view, si1 si1Var, boolean z) {
        this.a = context;
        this.b = si1Var;
        this.f = view;
        this.c = z;
        this.d = i;
        this.e = i2;
    }

    public final ej1 a() {
        ej1 vf2Var;
        if (this.j == null) {
            Context context = this.a;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            gj1.a(defaultDisplay, point);
            if (Math.min(point.x, point.y) >= context.getResources().getDimensionPixelSize(R.dimen.abc_cascading_menus_min_smallest_width)) {
                vf2Var = new lq(this.a, this.f, this.d, this.e, this.c);
            } else {
                Context context2 = this.a;
                si1 si1Var = this.b;
                vf2Var = new vf2(this.d, this.e, context2, this.f, si1Var, this.c);
            }
            vf2Var.l(this.b);
            vf2Var.r(this.l);
            vf2Var.n(this.f);
            vf2Var.j(this.i);
            vf2Var.o(this.h);
            vf2Var.p(this.g);
            this.j = vf2Var;
        }
        return this.j;
    }

    public final boolean b() {
        ej1 ej1Var = this.j;
        return ej1Var != null && ej1Var.a();
    }

    public void c() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public final void d(int i, int i2, boolean z, boolean z2) {
        ej1 ej1VarA = a();
        ej1VarA.s(z2);
        if (z) {
            int i3 = this.g;
            View view = this.f;
            WeakHashMap weakHashMap = zt2.a;
            if ((Gravity.getAbsoluteGravity(i3, jt2.d(view)) & 7) == 5) {
                i -= this.f.getWidth();
            }
            ej1VarA.q(i);
            ej1VarA.t(i2);
            int i4 = (int) ((this.a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            ej1VarA.c = new Rect(i - i4, i2 - i4, i + i4, i2 + i4);
        }
        ej1VarA.c();
    }
}
