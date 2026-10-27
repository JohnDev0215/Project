package q;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.R$dimen;
import o0.t;
import q.m;

/* JADX INFO: compiled from: MenuPopupHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f7199a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f7200b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f7201c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f7202d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f7203e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f7204f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f7205g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f7206h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public m.a f7207i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public k f7208j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public PopupWindow.OnDismissListener f7209k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final PopupWindow.OnDismissListener f7210l;

    /* JADX INFO: compiled from: MenuPopupHelper.java */
    public class a implements PopupWindow.OnDismissListener {
        public a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            l.this.e();
        }
    }

    public l(Context context, g gVar, View view, boolean z7, int i8) {
        this(context, gVar, view, z7, i8, 0);
    }

    public final k a() {
        Display defaultDisplay = ((WindowManager) this.f7199a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        k dVar = Math.min(point.x, point.y) >= this.f7199a.getResources().getDimensionPixelSize(R$dimen.abc_cascading_menus_min_smallest_width) ? new d(this.f7199a, this.f7204f, this.f7202d, this.f7203e, this.f7201c) : new q(this.f7199a, this.f7200b, this.f7204f, this.f7202d, this.f7203e, this.f7201c);
        dVar.o(this.f7200b);
        dVar.x(this.f7210l);
        dVar.s(this.f7204f);
        dVar.n(this.f7207i);
        dVar.u(this.f7206h);
        dVar.v(this.f7205g);
        return dVar;
    }

    public void b() {
        if (d()) {
            this.f7208j.dismiss();
        }
    }

    public k c() {
        if (this.f7208j == null) {
            this.f7208j = a();
        }
        return this.f7208j;
    }

    public boolean d() {
        k kVar = this.f7208j;
        return kVar != null && kVar.c();
    }

    public void e() {
        this.f7208j = null;
        PopupWindow.OnDismissListener onDismissListener = this.f7209k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void f(View view) {
        this.f7204f = view;
    }

    public void g(boolean z7) {
        this.f7206h = z7;
        k kVar = this.f7208j;
        if (kVar != null) {
            kVar.u(z7);
        }
    }

    public void h(int i8) {
        this.f7205g = i8;
    }

    public void i(PopupWindow.OnDismissListener onDismissListener) {
        this.f7209k = onDismissListener;
    }

    public void j(m.a aVar) {
        this.f7207i = aVar;
        k kVar = this.f7208j;
        if (kVar != null) {
            kVar.n(aVar);
        }
    }

    public void k() {
        if (!m()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public final void l(int i8, int i9, boolean z7, boolean z8) {
        k kVarC = c();
        kVarC.y(z8);
        if (z7) {
            if ((o0.d.b(this.f7205g, t.A(this.f7204f)) & 7) == 5) {
                i8 -= this.f7204f.getWidth();
            }
            kVarC.w(i8);
            kVarC.z(i9);
            int i10 = (int) ((this.f7199a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            kVarC.t(new Rect(i8 - i10, i9 - i10, i8 + i10, i9 + i10));
        }
        kVarC.e();
    }

    public boolean m() {
        if (d()) {
            return true;
        }
        if (this.f7204f == null) {
            return false;
        }
        l(0, 0, false, false);
        return true;
    }

    public boolean n(int i8, int i9) {
        if (d()) {
            return true;
        }
        if (this.f7204f == null) {
            return false;
        }
        l(i8, i9, true, true);
        return true;
    }

    public l(Context context, g gVar, View view, boolean z7, int i8, int i9) {
        this.f7205g = 8388611;
        this.f7210l = new a();
        this.f7199a = context;
        this.f7200b = gVar;
        this.f7204f = view;
        this.f7201c = z7;
        this.f7202d = i8;
        this.f7203e = i9;
    }
}
