package p;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.spotify.music.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class vf2 extends ej1 implements PopupWindow.OnDismissListener, View.OnKeyListener {
    public int A;
    public int B = 0;
    public boolean C;
    public final Context e;
    public final si1 k;
    public final pi1 l;
    public final boolean m;
    public final int n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f201p;
    public final mj1 q;
    public final hq r;
    public final iq s;
    public PopupWindow.OnDismissListener t;
    public View u;
    public View v;
    public nj1 w;
    public ViewTreeObserver x;
    public boolean y;
    public boolean z;

    public vf2(int i, int i2, Context context, View view, si1 si1Var, boolean z) {
        int i3 = 1;
        this.r = new hq(i3, this);
        this.s = new iq(this, i3);
        this.e = context;
        this.k = si1Var;
        this.m = z;
        this.l = new pi1(si1Var, LayoutInflater.from(context), z, R.layout.abc_popup_menu_item_layout);
        this.o = i;
        this.f201p = i2;
        Resources resources = context.getResources();
        this.n = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.u = view;
        this.q = new mj1(context, null, i, i2);
        si1Var.b(this, context);
    }

    @Override // p.jd2
    public final boolean a() {
        return !this.y && this.q.G.isShowing();
    }

    @Override // p.oj1
    public final void b(si1 si1Var, boolean z) {
        if (si1Var != this.k) {
            return;
        }
        dismiss();
        nj1 nj1Var = this.w;
        if (nj1Var != null) {
            nj1Var.b(si1Var, z);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // p.jd2
    public final void c() {
        View view;
        if (a()) {
            return;
        }
        if (this.y || (view = this.u) == null) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
        this.v = view;
        mj1 mj1Var = this.q;
        mj1Var.G.setOnDismissListener(this);
        mj1Var.x = this;
        mj1Var.F = true;
        mj1Var.G.setFocusable(true);
        View view2 = this.v;
        boolean z = this.x == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.x = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.r);
        }
        view2.addOnAttachStateChangeListener(this.s);
        mj1Var.w = view2;
        mj1Var.t = this.B;
        boolean z2 = this.z;
        Context context = this.e;
        pi1 pi1Var = this.l;
        if (!z2) {
            this.A = ej1.m(pi1Var, context, this.n);
            this.z = true;
        }
        mj1Var.r(this.A);
        mj1Var.G.setInputMethodMode(2);
        Rect rect = this.c;
        mj1Var.E = rect != null ? new Rect(rect) : null;
        mj1Var.c();
        ib0 ib0Var = mj1Var.k;
        ib0Var.setOnKeyListener(this);
        if (this.C) {
            si1 si1Var = this.k;
            if (si1Var.m != null) {
                FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) ib0Var, false);
                TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
                if (textView != null) {
                    textView.setText(si1Var.m);
                }
                frameLayout.setEnabled(false);
                ib0Var.addHeaderView(frameLayout, null, false);
            }
        }
        mj1Var.p(pi1Var);
        mj1Var.c();
    }

    @Override // p.oj1
    public final void d() {
        this.z = false;
        pi1 pi1Var = this.l;
        if (pi1Var != null) {
            pi1Var.notifyDataSetChanged();
        }
    }

    @Override // p.jd2
    public final void dismiss() {
        if (a()) {
            this.q.dismiss();
        }
    }

    @Override // p.jd2
    public final ib0 e() {
        return this.q.k;
    }

    @Override // p.oj1
    public final boolean f(qg2 qg2Var) {
        if (qg2Var.hasVisibleItems()) {
            hj1 hj1Var = new hj1(this.o, this.f201p, this.e, this.v, qg2Var, this.m);
            nj1 nj1Var = this.w;
            hj1Var.i = nj1Var;
            ej1 ej1Var = hj1Var.j;
            if (ej1Var != null) {
                ej1Var.j(nj1Var);
            }
            boolean zU = ej1.u(qg2Var);
            hj1Var.h = zU;
            ej1 ej1Var2 = hj1Var.j;
            if (ej1Var2 != null) {
                ej1Var2.o(zU);
            }
            hj1Var.k = this.t;
            this.t = null;
            this.k.c(false);
            mj1 mj1Var = this.q;
            int width = mj1Var.n;
            int iL = mj1Var.l();
            int i = this.B;
            View view = this.u;
            WeakHashMap weakHashMap = zt2.a;
            if ((Gravity.getAbsoluteGravity(i, jt2.d(view)) & 7) == 5) {
                width += this.u.getWidth();
            }
            if (!hj1Var.b()) {
                if (hj1Var.f != null) {
                    hj1Var.d(width, iL, true, true);
                }
            }
            nj1 nj1Var2 = this.w;
            if (nj1Var2 != null) {
                nj1Var2.s(qg2Var);
            }
            return true;
        }
        return false;
    }

    @Override // p.oj1
    public final boolean i() {
        return false;
    }

    @Override // p.oj1
    public final void j(nj1 nj1Var) {
        this.w = nj1Var;
    }

    @Override // p.ej1
    public final void l(si1 si1Var) {
    }

    @Override // p.ej1
    public final void n(View view) {
        this.u = view;
    }

    @Override // p.ej1
    public final void o(boolean z) {
        this.l.k = z;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.y = true;
        this.k.c(true);
        ViewTreeObserver viewTreeObserver = this.x;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.x = this.v.getViewTreeObserver();
            }
            this.x.removeGlobalOnLayoutListener(this.r);
            this.x = null;
        }
        this.v.removeOnAttachStateChangeListener(this.s);
        PopupWindow.OnDismissListener onDismissListener = this.t;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // p.ej1
    public final void p(int i) {
        this.B = i;
    }

    @Override // p.ej1
    public final void q(int i) {
        this.q.n = i;
    }

    @Override // p.ej1
    public final void r(PopupWindow.OnDismissListener onDismissListener) {
        this.t = onDismissListener;
    }

    @Override // p.ej1
    public final void s(boolean z) {
        this.C = z;
    }

    @Override // p.ej1
    public final void t(int i) {
        this.q.h(i);
    }
}
