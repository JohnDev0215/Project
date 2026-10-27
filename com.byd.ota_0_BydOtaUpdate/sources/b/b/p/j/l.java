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

/* JADX INFO: compiled from: MenuPopupHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f970a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f971b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f972c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f973d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f974e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f975f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f976g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f977h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public m.a f978i;
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
        Display defaultDisplay = ((WindowManager) this.f970a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        k dVar = Math.min(point.x, point.y) >= this.f970a.getResources().getDimensionPixelSize(b.b.d.f724c) ? new d(this.f970a, this.f975f, this.f973d, this.f974e, this.f972c) : new q(this.f970a, this.f971b, this.f975f, this.f973d, this.f974e, this.f972c);
        dVar.l(this.f971b);
        dVar.u(this.l);
        dVar.p(this.f975f);
        dVar.h(this.f978i);
        dVar.r(this.f977h);
        dVar.s(this.f976g);
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
        this.f975f = view;
    }

    public void g(boolean z) {
        this.f977h = z;
        k kVar = this.j;
        if (kVar != null) {
            kVar.r(z);
        }
    }

    public void h(int i2) {
        this.f976g = i2;
    }

    public void i(PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public void j(m.a aVar) {
        this.f978i = aVar;
        k kVar = this.j;
        if (kVar != null) {
            kVar.h(aVar);
        }
    }

    public void k() {
        if (!m()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public final void l(int i2, int i3, boolean z, boolean z2) {
        k kVarC = c();
        kVarC.v(z2);
        if (z) {
            if ((b.f.m.d.a(this.f976g, b.f.m.s.v(this.f975f)) & 7) == 5) {
                i2 -= this.f975f.getWidth();
            }
            kVarC.t(i2);
            kVarC.w(i3);
            int i4 = (int) ((this.f970a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            kVarC.q(new Rect(i2 - i4, i3 - i4, i2 + i4, i3 + i4));
        }
        kVarC.f();
    }

    public boolean m() {
        if (d()) {
            return true;
        }
        if (this.f975f == null) {
            return false;
        }
        l(0, 0, false, false);
        return true;
    }

    public boolean n(int i2, int i3) {
        if (d()) {
            return true;
        }
        if (this.f975f == null) {
            return false;
        }
        l(i2, i3, true, true);
        return true;
    }

    public l(Context context, g gVar, View view, boolean z, int i2, int i3) {
        this.f976g = 8388611;
        this.l = new a();
        this.f970a = context;
        this.f971b = gVar;
        this.f975f = view;
        this.f972c = z;
        this.f973d = i2;
        this.f974e = i3;
    }
}
