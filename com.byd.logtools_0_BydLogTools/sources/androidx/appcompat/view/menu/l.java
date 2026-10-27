package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.m;
import b.g.k.t;

/* JADX INFO: compiled from: MenuPopupHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f329a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g f330b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f331c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f332d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f333e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private View f334f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f335g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f336h;
    private m.a i;
    private k j;
    private PopupWindow.OnDismissListener k;
    private final PopupWindow.OnDismissListener l;

    /* JADX INFO: compiled from: MenuPopupHelper.java */
    class a implements PopupWindow.OnDismissListener {
        a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            l.this.e();
        }
    }

    public l(Context context, g gVar, View view, boolean z, int i) {
        this(context, gVar, view, z, i, 0);
    }

    private k a() {
        Display defaultDisplay = ((WindowManager) this.f329a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        k dVar = Math.min(point.x, point.y) >= this.f329a.getResources().getDimensionPixelSize(b.a.d.f2077c) ? new d(this.f329a, this.f334f, this.f332d, this.f333e, this.f331c) : new q(this.f329a, this.f330b, this.f334f, this.f332d, this.f333e, this.f331c);
        dVar.o(this.f330b);
        dVar.x(this.l);
        dVar.s(this.f334f);
        dVar.k(this.i);
        dVar.u(this.f336h);
        dVar.v(this.f335g);
        return dVar;
    }

    private void l(int i, int i2, boolean z, boolean z2) {
        k kVarC = c();
        kVarC.y(z2);
        if (z) {
            if ((b.g.k.d.b(this.f335g, t.z(this.f334f)) & 7) == 5) {
                i -= this.f334f.getWidth();
            }
            kVarC.w(i);
            kVarC.z(i2);
            int i3 = (int) ((this.f329a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            kVarC.t(new Rect(i - i3, i2 - i3, i + i3, i2 + i3));
        }
        kVarC.g();
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

    protected void e() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void f(View view) {
        this.f334f = view;
    }

    public void g(boolean z) {
        this.f336h = z;
        k kVar = this.j;
        if (kVar != null) {
            kVar.u(z);
        }
    }

    public void h(int i) {
        this.f335g = i;
    }

    public void i(PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public void j(m.a aVar) {
        this.i = aVar;
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

    public boolean m() {
        if (d()) {
            return true;
        }
        if (this.f334f == null) {
            return false;
        }
        l(0, 0, false, false);
        return true;
    }

    public boolean n(int i, int i2) {
        if (d()) {
            return true;
        }
        if (this.f334f == null) {
            return false;
        }
        l(i, i2, true, true);
        return true;
    }

    public l(Context context, g gVar, View view, boolean z, int i, int i2) {
        this.f335g = 8388611;
        this.l = new a();
        this.f329a = context;
        this.f330b = gVar;
        this.f334f = view;
        this.f331c = z;
        this.f332d = i;
        this.f333e = i2;
    }
}
