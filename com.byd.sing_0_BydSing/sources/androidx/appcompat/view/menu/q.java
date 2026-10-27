package androidx.appcompat.view.menu;

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
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.MenuPopupWindow;
import c.g.k.t;

/* JADX INFO: compiled from: StandardMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
final class q extends k implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, m, View.OnKeyListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f772c = c.a.g.o;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Context f773d;
    private final g e;
    private final f f;
    private final boolean g;
    private final int h;
    private final int i;
    private final int j;
    final MenuPopupWindow k;
    private PopupWindow.OnDismissListener n;
    private View o;
    View p;
    private m.a q;
    ViewTreeObserver r;
    private boolean s;
    private boolean t;
    private int u;
    private boolean w;
    final ViewTreeObserver.OnGlobalLayoutListener l = new a();
    private final View.OnAttachStateChangeListener m = new b();
    private int v = 0;

    /* JADX INFO: compiled from: StandardMenuPopup.java */
    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!q.this.c() || q.this.k.B()) {
                return;
            }
            View view = q.this.p;
            if (view == null || !view.isShown()) {
                q.this.dismiss();
            } else {
                q.this.k.e();
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
            ViewTreeObserver viewTreeObserver = q.this.r;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    q.this.r = view.getViewTreeObserver();
                }
                q qVar = q.this;
                qVar.r.removeGlobalOnLayoutListener(qVar.l);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public q(Context context, g gVar, View view, int i, int i2, boolean z) {
        this.f773d = context;
        this.e = gVar;
        this.g = z;
        this.f = new f(gVar, LayoutInflater.from(context), z, f772c);
        this.i = i;
        this.j = i2;
        Resources resources = context.getResources();
        this.h = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(c.a.d.f1977d));
        this.o = view;
        this.k = new MenuPopupWindow(context, null, i, i2);
        gVar.c(this, context);
    }

    private boolean C() {
        View view;
        if (c()) {
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
            this.u = k.r(this.f, null, this.f773d, this.h);
            this.t = true;
        }
        this.k.F(this.u);
        this.k.I(2);
        this.k.H(q());
        this.k.e();
        ListView listViewH = this.k.h();
        listViewH.setOnKeyListener(this);
        if (this.w && this.e.z() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f773d).inflate(c.a.g.n, (ViewGroup) listViewH, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            if (textView != null) {
                textView.setText(this.e.z());
            }
            frameLayout.setEnabled(false);
            listViewH.addHeaderView(frameLayout, null, false);
        }
        this.k.p(this.f);
        this.k.e();
        return true;
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
        if (gVar != this.e) {
            return;
        }
        dismiss();
        m.a aVar = this.q;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean c() {
        return !this.s && this.k.c();
    }

    @Override // androidx.appcompat.view.menu.m
    public void d(Parcelable parcelable) {
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        if (c()) {
            this.k.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.p
    public void e() {
        if (!C()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean f(r rVar) {
        if (rVar.hasVisibleItems()) {
            l lVar = new l(this.f773d, rVar, this.p, this.g, this.i, this.j);
            lVar.j(this.q);
            lVar.g(k.A(rVar));
            lVar.i(this.n);
            this.n = null;
            this.e.e(false);
            int iD = this.k.d();
            int iN = this.k.n();
            if ((Gravity.getAbsoluteGravity(this.v, t.z(this.o)) & 7) == 5) {
                iD += this.o.getWidth();
            }
            if (lVar.n(iD, iN)) {
                m.a aVar = this.q;
                if (aVar == null) {
                    return true;
                }
                aVar.b(rVar);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void g(boolean z) {
        this.t = false;
        f fVar = this.f;
        if (fVar != null) {
            fVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView h() {
        return this.k.h();
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean j() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public Parcelable k() {
        return null;
    }

    @Override // androidx.appcompat.view.menu.m
    public void n(m.a aVar) {
        this.q = aVar;
    }

    @Override // androidx.appcompat.view.menu.k
    public void o(g gVar) {
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

    @Override // androidx.appcompat.view.menu.k
    public void s(View view) {
        this.o = view;
    }

    @Override // androidx.appcompat.view.menu.k
    public void u(boolean z) {
        this.f.d(z);
    }

    @Override // androidx.appcompat.view.menu.k
    public void v(int i) {
        this.v = i;
    }

    @Override // androidx.appcompat.view.menu.k
    public void w(int i) {
        this.k.l(i);
    }

    @Override // androidx.appcompat.view.menu.k
    public void x(PopupWindow.OnDismissListener onDismissListener) {
        this.n = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.k
    public void y(boolean z) {
        this.w = z;
    }

    @Override // androidx.appcompat.view.menu.k
    public void z(int i) {
        this.k.j(i);
    }
}
