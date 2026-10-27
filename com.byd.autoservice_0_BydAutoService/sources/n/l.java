package n;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.R$dimen;
import l0.t;
import n.m;

/* JADX INFO: compiled from: MenuPopupHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f4002a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f4003b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f4004c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f4005d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f4006e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f4007f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4008g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4009h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public m.a f4010i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public k f4011j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public PopupWindow.OnDismissListener f4012k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final PopupWindow.OnDismissListener f4013l;

    /* JADX INFO: compiled from: MenuPopupHelper.java */
    public class a implements PopupWindow.OnDismissListener {
        public a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            l.this.e();
        }
    }

    public l(Context context, g gVar, View view, boolean z3, int i3) {
        this(context, gVar, view, z3, i3, 0);
    }

    public final k a() {
        Display defaultDisplay = ((WindowManager) this.f4002a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        k dVar = Math.min(point.x, point.y) >= this.f4002a.getResources().getDimensionPixelSize(R$dimen.abc_cascading_menus_min_smallest_width) ? new d(this.f4002a, this.f4007f, this.f4005d, this.f4006e, this.f4004c) : new q(this.f4002a, this.f4003b, this.f4007f, this.f4005d, this.f4006e, this.f4004c);
        dVar.o(this.f4003b);
        dVar.x(this.f4013l);
        dVar.s(this.f4007f);
        dVar.k(this.f4010i);
        dVar.u(this.f4009h);
        dVar.v(this.f4008g);
        return dVar;
    }

    public void b() {
        if (d()) {
            this.f4011j.dismiss();
        }
    }

    public k c() {
        if (this.f4011j == null) {
            this.f4011j = a();
        }
        return this.f4011j;
    }

    public boolean d() {
        k kVar = this.f4011j;
        return kVar != null && kVar.b();
    }

    public void e() {
        this.f4011j = null;
        PopupWindow.OnDismissListener onDismissListener = this.f4012k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void f(View view) {
        this.f4007f = view;
    }

    public void g(boolean z3) {
        this.f4009h = z3;
        k kVar = this.f4011j;
        if (kVar != null) {
            kVar.u(z3);
        }
    }

    public void h(int i3) {
        this.f4008g = i3;
    }

    public void i(PopupWindow.OnDismissListener onDismissListener) {
        this.f4012k = onDismissListener;
    }

    public void j(m.a aVar) {
        this.f4010i = aVar;
        k kVar = this.f4011j;
        if (kVar != null) {
            kVar.k(aVar);
        }
    }

    public void k() {
        if (!m()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public final void l(int i3, int i4, boolean z3, boolean z4) {
        k kVarC = c();
        kVarC.y(z4);
        if (z3) {
            if ((l0.d.b(this.f4008g, t.z(this.f4007f)) & 7) == 5) {
                i3 -= this.f4007f.getWidth();
            }
            kVarC.w(i3);
            kVarC.z(i4);
            int i5 = (int) ((this.f4002a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            kVarC.t(new Rect(i3 - i5, i4 - i5, i3 + i5, i4 + i5));
        }
        kVarC.g();
    }

    public boolean m() {
        if (d()) {
            return true;
        }
        if (this.f4007f == null) {
            return false;
        }
        l(0, 0, false, false);
        return true;
    }

    public boolean n(int i3, int i4) {
        if (d()) {
            return true;
        }
        if (this.f4007f == null) {
            return false;
        }
        l(i3, i4, true, true);
        return true;
    }

    public l(Context context, g gVar, View view, boolean z3, int i3, int i4) {
        this.f4008g = 8388611;
        this.f4013l = new a();
        this.f4002a = context;
        this.f4003b = gVar;
        this.f4007f = view;
        this.f4004c = z3;
        this.f4005d = i3;
        this.f4006e = i4;
    }
}
