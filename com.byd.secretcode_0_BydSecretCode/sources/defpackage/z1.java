package defpackage;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$layout;
import androidx.appcompat.widget.MenuPopupWindow;
import defpackage.v1;

/* JADX INFO: compiled from: StandardMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
public final class z1 extends t1 implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, v1, View.OnKeyListener {
    public static final int c = R$layout.abc_popup_menu_item_layout;
    public final Context d;
    public final p1 e;
    public final o1 f;
    public final boolean g;
    public final int h;
    public final int i;
    public final int j;
    public final MenuPopupWindow k;
    public PopupWindow.OnDismissListener n;
    public View o;
    public View p;
    public v1.a q;
    public ViewTreeObserver r;
    public boolean s;
    public boolean t;
    public int u;
    public boolean w;
    public final ViewTreeObserver.OnGlobalLayoutListener l = new a();
    public final View.OnAttachStateChangeListener m = new b();
    public int v = 0;

    /* JADX INFO: compiled from: StandardMenuPopup.java */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!z1.this.b() || z1.this.k.B()) {
                return;
            }
            View view = z1.this.p;
            if (view == null || !view.isShown()) {
                z1.this.dismiss();
            } else {
                z1.this.k.g();
            }
        }
    }

    /* JADX INFO: compiled from: StandardMenuPopup.java */
    public class b implements View.OnAttachStateChangeListener {
        public b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = z1.this.r;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    z1.this.r = view.getViewTreeObserver();
                }
                z1 z1Var = z1.this;
                z1Var.r.removeGlobalOnLayoutListener(z1Var.l);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public z1(Context context, p1 p1Var, View view, int i, int i2, boolean z) {
        this.d = context;
        this.e = p1Var;
        this.g = z;
        this.f = new o1(p1Var, LayoutInflater.from(context), z, c);
        this.i = i;
        this.j = i2;
        Resources resources = context.getResources();
        this.h = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.o = view;
        this.k = new MenuPopupWindow(context, null, i, i2);
        p1Var.c(this, context);
    }

    public final boolean C() {
        View view;
        if (b()) {
            return true;
        }
        if (this.s || (view = this.o) == null) {
            return false;
        }
        this.p = view;
        this.k.K(this);
        this.k.L(this);
        this.k.J(true);
        View view2 = this.p;
        boolean z = this.r == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.r = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.l);
        }
        view2.addOnAttachStateChangeListener(this.m);
        this.k.D(view2);
        this.k.G(this.v);
        if (!this.t) {
            this.u = t1.r(this.f, null, this.d, this.h);
            this.t = true;
        }
        this.k.F(this.u);
        this.k.I(2);
        this.k.H(q());
        this.k.g();
        ListView listViewL = this.k.l();
        listViewL.setOnKeyListener(this);
        if (this.w && this.e.z() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.d).inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) listViewL, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            if (textView != null) {
                textView.setText(this.e.z());
            }
            frameLayout.setEnabled(false);
            listViewL.addHeaderView(frameLayout, null, false);
        }
        this.k.o(this.f);
        this.k.g();
        return true;
    }

    @Override // defpackage.v1
    public void a(p1 p1Var, boolean z) {
        if (p1Var != this.e) {
            return;
        }
        dismiss();
        v1.a aVar = this.q;
        if (aVar != null) {
            aVar.a(p1Var, z);
        }
    }

    @Override // defpackage.y1
    public boolean b() {
        return !this.s && this.k.b();
    }

    @Override // defpackage.v1
    public boolean d() {
        return false;
    }

    @Override // defpackage.y1
    public void dismiss() {
        if (b()) {
            this.k.dismiss();
        }
    }

    @Override // defpackage.v1
    public Parcelable e() {
        return null;
    }

    @Override // defpackage.y1
    public void g() {
        if (!C()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // defpackage.v1
    public void h(Parcelable parcelable) {
    }

    @Override // defpackage.v1
    public void k(v1.a aVar) {
        this.q = aVar;
    }

    @Override // defpackage.y1
    public ListView l() {
        return this.k.l();
    }

    @Override // defpackage.v1
    public boolean m(a2 a2Var) {
        if (a2Var.hasVisibleItems()) {
            u1 u1Var = new u1(this.d, a2Var, this.p, this.g, this.i, this.j);
            u1Var.j(this.q);
            u1Var.g(t1.A(a2Var));
            u1Var.i(this.n);
            this.n = null;
            this.e.e(false);
            int iE = this.k.e();
            int iH = this.k.h();
            if ((Gravity.getAbsoluteGravity(this.v, t9.z(this.o)) & 7) == 5) {
                iE += this.o.getWidth();
            }
            if (u1Var.n(iE, iH)) {
                v1.a aVar = this.q;
                if (aVar == null) {
                    return true;
                }
                aVar.b(a2Var);
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.v1
    public void n(boolean z) {
        this.t = false;
        o1 o1Var = this.f;
        if (o1Var != null) {
            o1Var.notifyDataSetChanged();
        }
    }

    @Override // defpackage.t1
    public void o(p1 p1Var) {
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.s = true;
        this.e.close();
        ViewTreeObserver viewTreeObserver = this.r;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.r = this.p.getViewTreeObserver();
            }
            this.r.removeGlobalOnLayoutListener(this.l);
            this.r = null;
        }
        this.p.removeOnAttachStateChangeListener(this.m);
        PopupWindow.OnDismissListener onDismissListener = this.n;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // defpackage.t1
    public void s(View view) {
        this.o = view;
    }

    @Override // defpackage.t1
    public void u(boolean z) {
        this.f.d(z);
    }

    @Override // defpackage.t1
    public void v(int i) {
        this.v = i;
    }

    @Override // defpackage.t1
    public void w(int i) {
        this.k.d(i);
    }

    @Override // defpackage.t1
    public void x(PopupWindow.OnDismissListener onDismissListener) {
        this.n = onDismissListener;
    }

    @Override // defpackage.t1
    public void y(boolean z) {
        this.w = z;
    }

    @Override // defpackage.t1
    public void z(int i) {
        this.k.n(i);
    }
}
