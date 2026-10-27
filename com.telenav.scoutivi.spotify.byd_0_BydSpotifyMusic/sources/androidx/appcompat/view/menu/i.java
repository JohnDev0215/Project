package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.j;
import i0.u;

/* JADX INFO: compiled from: MenuPopupHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f720a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f721b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f722c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f723d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f724e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private View f725f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f726g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f727h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private j.a f728i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private h f729j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private PopupWindow.OnDismissListener f730k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final PopupWindow.OnDismissListener f731l;

    /* JADX INFO: compiled from: MenuPopupHelper.java */
    class a implements PopupWindow.OnDismissListener {
        a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            i.this.e();
        }
    }

    public i(Context context, e eVar, View view, boolean z6, int i7) {
        this(context, eVar, view, z6, i7, 0);
    }

    private h a() {
        Display defaultDisplay = ((WindowManager) this.f720a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getRealSize(point);
        h bVar = Math.min(point.x, point.y) >= this.f720a.getResources().getDimensionPixelSize(e.d.abc_cascading_menus_min_smallest_width) ? new b(this.f720a, this.f725f, this.f723d, this.f724e, this.f722c) : new l(this.f720a, this.f721b, this.f725f, this.f723d, this.f724e, this.f722c);
        bVar.k(this.f721b);
        bVar.u(this.f731l);
        bVar.p(this.f725f);
        bVar.h(this.f728i);
        bVar.r(this.f727h);
        bVar.s(this.f726g);
        return bVar;
    }

    private void l(int i7, int i8, boolean z6, boolean z7) {
        h hVarC = c();
        hVarC.v(z7);
        if (z6) {
            if ((i0.e.b(this.f726g, u.y(this.f725f)) & 7) == 5) {
                i7 -= this.f725f.getWidth();
            }
            hVarC.t(i7);
            hVarC.w(i8);
            int i9 = (int) ((this.f720a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            hVarC.q(new Rect(i7 - i9, i8 - i9, i7 + i9, i8 + i9));
        }
        hVarC.g();
    }

    public void b() {
        if (d()) {
            this.f729j.dismiss();
        }
    }

    public h c() {
        if (this.f729j == null) {
            this.f729j = a();
        }
        return this.f729j;
    }

    public boolean d() {
        h hVar = this.f729j;
        return hVar != null && hVar.b();
    }

    protected void e() {
        this.f729j = null;
        PopupWindow.OnDismissListener onDismissListener = this.f730k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void f(View view) {
        this.f725f = view;
    }

    public void g(boolean z6) {
        this.f727h = z6;
        h hVar = this.f729j;
        if (hVar != null) {
            hVar.r(z6);
        }
    }

    public void h(int i7) {
        this.f726g = i7;
    }

    public void i(PopupWindow.OnDismissListener onDismissListener) {
        this.f730k = onDismissListener;
    }

    public void j(j.a aVar) {
        this.f728i = aVar;
        h hVar = this.f729j;
        if (hVar != null) {
            hVar.h(aVar);
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
        if (this.f725f == null) {
            return false;
        }
        l(0, 0, false, false);
        return true;
    }

    public boolean n(int i7, int i8) {
        if (d()) {
            return true;
        }
        if (this.f725f == null) {
            return false;
        }
        l(i7, i8, true, true);
        return true;
    }

    public i(Context context, e eVar, View view, boolean z6, int i7, int i8) {
        this.f726g = 8388611;
        this.f731l = new a();
        this.f720a = context;
        this.f721b = eVar;
        this.f725f = view;
        this.f722c = z6;
        this.f723d = i7;
        this.f724e = i8;
    }
}
