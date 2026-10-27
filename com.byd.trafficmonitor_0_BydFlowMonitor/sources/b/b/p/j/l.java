package b.b.p.j;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import b.b.p.j.m;
import b.h.m.t;

/* JADX INFO: compiled from: MenuPopupHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1667a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f1668b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f1669c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1670d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1671e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f1672f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1673g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1674h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public m.a f1675i;
    public k j;
    public PopupWindow.OnDismissListener k;
    public final PopupWindow.OnDismissListener l;

    /* JADX INFO: compiled from: MenuPopupHelper.java */
    public class a implements PopupWindow.OnDismissListener {
        public a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            l.this.e();
        }
    }

    public l(Context context, g gVar, View view, boolean z, int i2) {
        this(context, gVar, view, z, i2, 0);
    }

    public final k a() {
        Display defaultDisplay = ((WindowManager) this.f1667a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        k dVar = Math.min(point.x, point.y) >= this.f1667a.getResources().getDimensionPixelSize(b.b.d.abc_cascading_menus_min_smallest_width) ? new d(this.f1667a, this.f1672f, this.f1670d, this.f1671e, this.f1669c) : new q(this.f1667a, this.f1668b, this.f1672f, this.f1670d, this.f1671e, this.f1669c);
        dVar.o(this.f1668b);
        dVar.x(this.l);
        dVar.s(this.f1672f);
        dVar.k(this.f1675i);
        dVar.u(this.f1674h);
        dVar.v(this.f1673g);
        return dVar;
    }

    public void b() {
        if (d()) {
            this.j.dismiss();
        }
    }

    public k c() {
        if (this.j == null) {
            this.j = a();
        }
        return this.j;
    }

    public boolean d() {
        k kVar = this.j;
        return kVar != null && kVar.b();
    }

    public void e() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void f(View view) {
        this.f1672f = view;
    }

    public void g(boolean z) {
        this.f1674h = z;
        k kVar = this.j;
        if (kVar != null) {
            kVar.u(z);
        }
    }

    public void h(int i2) {
        this.f1673g = i2;
    }

    public void i(PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public void j(m.a aVar) {
        this.f1675i = aVar;
        k kVar = this.j;
        if (kVar != null) {
            kVar.k(aVar);
        }
    }

    public void k() {
        if (!m()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public final void l(int i2, int i3, boolean z, boolean z2) {
        k kVarC = c();
        kVarC.y(z2);
        if (z) {
            if ((b.h.m.d.b(this.f1673g, t.z(this.f1672f)) & 7) == 5) {
                i2 -= this.f1672f.getWidth();
            }
            kVarC.w(i2);
            kVarC.z(i3);
            int i4 = (int) ((this.f1667a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            kVarC.t(new Rect(i2 - i4, i3 - i4, i2 + i4, i3 + i4));
        }
        kVarC.g();
    }

    public boolean m() {
        if (d()) {
            return true;
        }
        if (this.f1672f == null) {
            return false;
        }
        l(0, 0, false, false);
        return true;
    }

    public boolean n(int i2, int i3) {
        if (d()) {
            return true;
        }
        if (this.f1672f == null) {
            return false;
        }
        l(i2, i3, true, true);
        return true;
    }

    public l(Context context, g gVar, View view, boolean z, int i2, int i3) {
        this.f1673g = 8388611;
        this.l = new a();
        this.f1667a = context;
        this.f1668b = gVar;
        this.f1672f = view;
        this.f1669c = z;
        this.f1670d = i2;
        this.f1671e = i3;
    }
}
