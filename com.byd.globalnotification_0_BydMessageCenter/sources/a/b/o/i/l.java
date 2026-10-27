package a.b.o.i;

import a.b.o.i.m;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f203a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g f204b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f205c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f206d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f207e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f208f;
    public boolean h;
    public m.a i;
    public k j;
    public PopupWindow.OnDismissListener k;
    public int g = 8388611;
    public final PopupWindow.OnDismissListener l = new a();

    public class a implements PopupWindow.OnDismissListener {
        public a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            l.this.c();
        }
    }

    public l(Context context, g gVar, View view, boolean z, int i, int i2) {
        this.f203a = context;
        this.f204b = gVar;
        this.f208f = view;
        this.f205c = z;
        this.f206d = i;
        this.f207e = i2;
    }

    public k a() {
        if (this.j == null) {
            Display defaultDisplay = ((WindowManager) this.f203a.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            k dVar = Math.min(point.x, point.y) >= this.f203a.getResources().getDimensionPixelSize(a.b.d.abc_cascading_menus_min_smallest_width) ? new d(this.f203a, this.f208f, this.f206d, this.f207e, this.f205c) : new q(this.f203a, this.f204b, this.f208f, this.f206d, this.f207e, this.f205c);
            dVar.l(this.f204b);
            dVar.s(this.l);
            dVar.o(this.f208f);
            dVar.d(this.i);
            dVar.p(this.h);
            dVar.q(this.g);
            this.j = dVar;
        }
        return this.j;
    }

    public boolean b() {
        k kVar = this.j;
        return kVar != null && kVar.a();
    }

    public void c() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void d(m.a aVar) {
        this.i = aVar;
        k kVar = this.j;
        if (kVar != null) {
            kVar.d(aVar);
        }
    }

    public final void e(int i, int i2, boolean z, boolean z2) {
        k kVarA = a();
        kVarA.t(z2);
        if (z) {
            if ((Gravity.getAbsoluteGravity(this.g, a.h.k.q.p(this.f208f)) & 7) == 5) {
                i -= this.f208f.getWidth();
            }
            kVarA.r(i);
            kVarA.u(i2);
            int i3 = (int) ((this.f203a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            kVarA.f202a = new Rect(i - i3, i2 - i3, i + i3, i2 + i3);
        }
        kVarA.i();
    }

    public boolean f() {
        if (b()) {
            return true;
        }
        if (this.f208f == null) {
            return false;
        }
        e(0, 0, false, false);
        return true;
    }
}
