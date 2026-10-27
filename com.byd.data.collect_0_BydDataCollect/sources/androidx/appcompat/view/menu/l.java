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
    private final Context f341a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g f342b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f343c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f344d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f345e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private View f346f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f347g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f348h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private m.a f349i;
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

    public l(Context context, g gVar, View view, boolean z, int i2) {
        this(context, gVar, view, z, i2, 0);
    }

    private k a() {
        Display defaultDisplay = ((WindowManager) this.f341a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        k dVar = Math.min(point.x, point.y) >= this.f341a.getResources().getDimensionPixelSize(b.a.d.abc_cascading_menus_min_smallest_width) ? new d(this.f341a, this.f346f, this.f344d, this.f345e, this.f343c) : new q(this.f341a, this.f342b, this.f346f, this.f344d, this.f345e, this.f343c);
        dVar.o(this.f342b);
        dVar.x(this.l);
        dVar.s(this.f346f);
        dVar.k(this.f349i);
        dVar.u(this.f348h);
        dVar.v(this.f347g);
        return dVar;
    }

    private void l(int i2, int i3, boolean z, boolean z2) {
        k kVarC = c();
        kVarC.y(z2);
        if (z) {
            if ((b.g.k.d.b(this.f347g, t.z(this.f346f)) & 7) == 5) {
                i2 -= this.f346f.getWidth();
            }
            kVarC.w(i2);
            kVarC.z(i3);
            int i4 = (int) ((this.f341a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            kVarC.t(new Rect(i2 - i4, i3 - i4, i2 + i4, i3 + i4));
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
        this.f346f = view;
    }

    public void g(boolean z) {
        this.f348h = z;
        k kVar = this.j;
        if (kVar != null) {
            kVar.u(z);
        }
    }

    public void h(int i2) {
        this.f347g = i2;
    }

    public void i(PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public void j(m.a aVar) {
        this.f349i = aVar;
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
        if (this.f346f == null) {
            return false;
        }
        l(0, 0, false, false);
        return true;
    }

    public boolean n(int i2, int i3) {
        if (d()) {
            return true;
        }
        if (this.f346f == null) {
            return false;
        }
        l(i2, i3, true, true);
        return true;
    }

    public l(Context context, g gVar, View view, boolean z, int i2, int i3) {
        this.f347g = 8388611;
        this.l = new a();
        this.f341a = context;
        this.f342b = gVar;
        this.f346f = view;
        this.f343c = z;
        this.f344d = i2;
        this.f345e = i3;
    }
}
