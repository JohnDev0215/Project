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
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.widget.h0;
import i0.u;

/* JADX INFO: compiled from: StandardMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
final class l extends h implements PopupWindow.OnDismissListener, View.OnKeyListener {
    private static final int A = e.g.abc_popup_menu_item_layout;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Context f733g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final e f734h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final d f735i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final boolean f736j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final int f737k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private final int f738l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final int f739m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    final h0 f740n;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private PopupWindow.OnDismissListener f743q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private View f744r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    View f745s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private j.a f746t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    ViewTreeObserver f747u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private boolean f748v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private boolean f749w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private int f750x;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private boolean f752z;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    final ViewTreeObserver.OnGlobalLayoutListener f741o = new a();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private final View.OnAttachStateChangeListener f742p = new b();

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private int f751y = 0;

    /* JADX INFO: compiled from: StandardMenuPopup.java */
    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!l.this.b() || l.this.f740n.B()) {
                return;
            }
            View view = l.this.f745s;
            if (view == null || !view.isShown()) {
                l.this.dismiss();
            } else {
                l.this.f740n.g();
            }
        }
    }

    /* JADX INFO: compiled from: StandardMenuPopup.java */
    class b implements View.OnAttachStateChangeListener {
        b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = l.this.f747u;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    l.this.f747u = view.getViewTreeObserver();
                }
                l lVar = l.this;
                lVar.f747u.removeGlobalOnLayoutListener(lVar.f741o);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public l(Context context, e eVar, View view, int i7, int i8, boolean z6) {
        this.f733g = context;
        this.f734h = eVar;
        this.f736j = z6;
        this.f735i = new d(eVar, LayoutInflater.from(context), z6, A);
        this.f738l = i7;
        this.f739m = i8;
        Resources resources = context.getResources();
        this.f737k = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(e.d.abc_config_prefDialogWidth));
        this.f744r = view;
        this.f740n = new h0(context, null, i7, i8);
        eVar.c(this, context);
    }

    private boolean z() {
        View view;
        if (b()) {
            return true;
        }
        if (this.f748v || (view = this.f744r) == null) {
            return false;
        }
        this.f745s = view;
        this.f740n.K(this);
        this.f740n.L(this);
        this.f740n.J(true);
        View view2 = this.f745s;
        boolean z6 = this.f747u == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.f747u = viewTreeObserver;
        if (z6) {
            viewTreeObserver.addOnGlobalLayoutListener(this.f741o);
        }
        view2.addOnAttachStateChangeListener(this.f742p);
        this.f740n.D(view2);
        this.f740n.G(this.f751y);
        if (!this.f749w) {
            this.f750x = h.o(this.f735i, null, this.f733g, this.f737k);
            this.f749w = true;
        }
        this.f740n.F(this.f750x);
        this.f740n.I(2);
        this.f740n.H(n());
        this.f740n.g();
        ListView listViewL = this.f740n.l();
        listViewL.setOnKeyListener(this);
        if (this.f752z && this.f734h.x() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f733g).inflate(e.g.abc_popup_menu_header_item_layout, (ViewGroup) listViewL, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            if (textView != null) {
                textView.setText(this.f734h.x());
            }
            frameLayout.setEnabled(false);
            listViewL.addHeaderView(frameLayout, null, false);
        }
        this.f740n.o(this.f735i);
        this.f740n.g();
        return true;
    }

    @Override // androidx.appcompat.view.menu.j
    public void a(e eVar, boolean z6) {
        if (eVar != this.f734h) {
            return;
        }
        dismiss();
        j.a aVar = this.f746t;
        if (aVar != null) {
            aVar.a(eVar, z6);
        }
    }

    @Override // j.e
    public boolean b() {
        return !this.f748v && this.f740n.b();
    }

    @Override // androidx.appcompat.view.menu.j
    public boolean c() {
        return false;
    }

    @Override // j.e
    public void dismiss() {
        if (b()) {
            this.f740n.dismiss();
        }
    }

    @Override // j.e
    public void g() {
        if (!z()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // androidx.appcompat.view.menu.j
    public void h(j.a aVar) {
        this.f746t = aVar;
    }

    @Override // androidx.appcompat.view.menu.j
    public boolean i(m mVar) {
        if (mVar.hasVisibleItems()) {
            i iVar = new i(this.f733g, mVar, this.f745s, this.f736j, this.f738l, this.f739m);
            iVar.j(this.f746t);
            iVar.g(h.x(mVar));
            iVar.i(this.f743q);
            this.f743q = null;
            this.f734h.e(false);
            int iE = this.f740n.e();
            int iH = this.f740n.h();
            if ((Gravity.getAbsoluteGravity(this.f751y, u.y(this.f744r)) & 7) == 5) {
                iE += this.f744r.getWidth();
            }
            if (iVar.n(iE, iH)) {
                j.a aVar = this.f746t;
                if (aVar == null) {
                    return true;
                }
                aVar.b(mVar);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.j
    public void j(boolean z6) {
        this.f749w = false;
        d dVar = this.f735i;
        if (dVar != null) {
            dVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.h
    public void k(e eVar) {
    }

    @Override // j.e
    public ListView l() {
        return this.f740n.l();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.f748v = true;
        this.f734h.close();
        ViewTreeObserver viewTreeObserver = this.f747u;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f747u = this.f745s.getViewTreeObserver();
            }
            this.f747u.removeGlobalOnLayoutListener(this.f741o);
            this.f747u = null;
        }
        this.f745s.removeOnAttachStateChangeListener(this.f742p);
        PopupWindow.OnDismissListener onDismissListener = this.f743q;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i7, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i7 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.h
    public void p(View view) {
        this.f744r = view;
    }

    @Override // androidx.appcompat.view.menu.h
    public void r(boolean z6) {
        this.f735i.d(z6);
    }

    @Override // androidx.appcompat.view.menu.h
    public void s(int i7) {
        this.f751y = i7;
    }

    @Override // androidx.appcompat.view.menu.h
    public void t(int i7) {
        this.f740n.d(i7);
    }

    @Override // androidx.appcompat.view.menu.h
    public void u(PopupWindow.OnDismissListener onDismissListener) {
        this.f743q = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.h
    public void v(boolean z6) {
        this.f752z = z6;
    }

    @Override // androidx.appcompat.view.menu.h
    public void w(int i7) {
        this.f740n.n(i7);
    }
}
