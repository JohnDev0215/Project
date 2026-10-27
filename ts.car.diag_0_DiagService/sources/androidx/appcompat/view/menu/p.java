package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
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
import androidx.appcompat.view.menu.l;
import androidx.appcompat.widget.I;
import androidx.core.view.ViewCompat;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
final class p extends j implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, l, View.OnKeyListener {
    private static final int x = R$layout.abc_popup_menu_item_layout;
    private final Context d;
    private final f e;
    private final e f;
    private final boolean g;
    private final int h;
    private final int i;
    private final int j;
    final I k;
    private PopupWindow.OnDismissListener n;
    private View o;
    View p;
    private l.a q;
    ViewTreeObserver r;
    private boolean s;
    private boolean t;
    private int u;
    private boolean w;
    final ViewTreeObserver.OnGlobalLayoutListener l = new a();
    private final View.OnAttachStateChangeListener m = new b();
    private int v = 0;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!p.this.b() || p.this.k.x()) {
                return;
            }
            View view = p.this.p;
            if (view == null || !view.isShown()) {
                p.this.dismiss();
            } else {
                p.this.k.f();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements View.OnAttachStateChangeListener {
        b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = p.this.r;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    p.this.r = view.getViewTreeObserver();
                }
                p pVar = p.this;
                pVar.r.removeGlobalOnLayoutListener(pVar.l);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public p(Context context, f fVar, View view, int i, int i2, boolean z) {
        this.d = context;
        this.e = fVar;
        this.g = z;
        this.f = new e(fVar, LayoutInflater.from(context), this.g, x);
        this.i = i;
        this.j = i2;
        Resources resources = context.getResources();
        this.h = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.o = view;
        this.k = new I(this.d, null, this.i, this.j);
        fVar.c(this, context);
    }

    private boolean z() {
        View view;
        if (b()) {
            return true;
        }
        if (this.s || (view = this.o) == null) {
            return false;
        }
        this.p = view;
        this.k.G(this);
        this.k.H(this);
        this.k.F(true);
        View view2 = this.p;
        boolean z = this.r == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.r = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.l);
        }
        view2.addOnAttachStateChangeListener(this.m);
        this.k.z(view2);
        this.k.C(this.v);
        if (!this.t) {
            this.u = j.o(this.f, null, this.d, this.h);
            this.t = true;
        }
        this.k.B(this.u);
        this.k.E(2);
        this.k.D(n());
        this.k.f();
        ListView listViewK = this.k.k();
        listViewK.setOnKeyListener(this);
        if (this.w && this.e.x() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.d).inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) listViewK, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            if (textView != null) {
                textView.setText(this.e.x());
            }
            frameLayout.setEnabled(false);
            listViewK.addHeaderView(frameLayout, null, false);
        }
        this.k.o(this.f);
        this.k.f();
        return true;
    }

    @Override // androidx.appcompat.view.menu.l
    public void a(f fVar, boolean z) {
        if (fVar != this.e) {
            return;
        }
        dismiss();
        l.a aVar = this.q;
        if (aVar != null) {
            aVar.a(fVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.o
    public boolean b() {
        return !this.s && this.k.b();
    }

    @Override // androidx.appcompat.view.menu.l
    public boolean c() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.o
    public void dismiss() {
        if (b()) {
            this.k.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.o
    public void f() {
        if (!z()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // androidx.appcompat.view.menu.l
    public void h(l.a aVar) {
        this.q = aVar;
    }

    @Override // androidx.appcompat.view.menu.l
    public boolean i(q qVar) {
        if (qVar.hasVisibleItems()) {
            k kVar = new k(this.d, qVar, this.p, this.g, this.i, this.j);
            kVar.j(this.q);
            kVar.g(j.x(qVar));
            kVar.i(this.n);
            this.n = null;
            this.e.e(false);
            int iC = this.k.c();
            int iG = this.k.g();
            if ((Gravity.getAbsoluteGravity(this.v, ViewCompat.q(this.o)) & 7) == 5) {
                iC += this.o.getWidth();
            }
            if (kVar.n(iC, iG)) {
                l.a aVar = this.q;
                if (aVar == null) {
                    return true;
                }
                aVar.b(qVar);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.l
    public void j(boolean z) {
        this.t = false;
        e eVar = this.f;
        if (eVar != null) {
            eVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.o
    public ListView k() {
        return this.k.k();
    }

    @Override // androidx.appcompat.view.menu.j
    public void l(f fVar) {
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

    @Override // androidx.appcompat.view.menu.j
    public void p(View view) {
        this.o = view;
    }

    @Override // androidx.appcompat.view.menu.j
    public void r(boolean z) {
        this.f.d(z);
    }

    @Override // androidx.appcompat.view.menu.j
    public void s(int i) {
        this.v = i;
    }

    @Override // androidx.appcompat.view.menu.j
    public void t(int i) {
        this.k.a(i);
    }

    @Override // androidx.appcompat.view.menu.j
    public void u(PopupWindow.OnDismissListener onDismissListener) {
        this.n = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.j
    public void v(boolean z) {
        this.w = z;
    }

    @Override // androidx.appcompat.view.menu.j
    public void w(int i) {
        this.k.n(i);
    }
}
