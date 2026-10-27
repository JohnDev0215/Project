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
    private final Context f247a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g f248b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f249c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f250d;
    private final int e;
    private View f;
    private int g;
    private boolean h;
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
        Display defaultDisplay = ((WindowManager) this.f247a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        k dVar = Math.min(point.x, point.y) >= this.f247a.getResources().getDimensionPixelSize(b.a.d.f1409c) ? new d(this.f247a, this.f, this.f250d, this.e, this.f249c) : new q(this.f247a, this.f248b, this.f, this.f250d, this.e, this.f249c);
        dVar.o(this.f248b);
        dVar.x(this.l);
        dVar.s(this.f);
        dVar.k(this.i);
        dVar.u(this.h);
        dVar.v(this.g);
        return dVar;
    }

    private void l(int i, int i2, boolean z, boolean z2) {
        k kVarC = c();
        kVarC.y(z2);
        if (z) {
            if ((b.g.k.d.b(this.g, t.z(this.f)) & 7) == 5) {
                i -= this.f.getWidth();
            }
            kVarC.w(i);
            kVarC.z(i2);
            int i3 = (int) ((this.f247a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
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
        this.f = view;
    }

    public void g(boolean z) {
        this.h = z;
        k kVar = this.j;
        if (kVar != null) {
            kVar.u(z);
        }
    }

    public void h(int i) {
        this.g = i;
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

    public l(Context context, g gVar, View view, boolean z, int i, int i2) {
        this.g = 8388611;
        this.l = new a();
        this.f247a = context;
        this.f248b = gVar;
        this.f = view;
        this.f249c = z;
        this.f250d = i;
        this.e = i2;
    }
}
