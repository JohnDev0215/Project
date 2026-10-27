package b.b.p.j;

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
import androidx.appcompat.widget.MenuPopupWindow;
import b.b.p.j.m;
import b.h.m.t;

/* JADX INFO: compiled from: StandardMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
public final class q extends k implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, m, View.OnKeyListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f1678c = b.b.g.abc_popup_menu_item_layout;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f1679d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g f1680e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f f1681f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f1682g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f1683h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f1684i;
    public final int j;
    public final MenuPopupWindow k;
    public PopupWindow.OnDismissListener n;
    public View o;
    public View p;
    public m.a q;
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
            if (!q.this.b() || q.this.k.B()) {
                return;
            }
            View view = q.this.p;
            if (view == null || !view.isShown()) {
                q.this.dismiss();
            } else {
                q.this.k.g();
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

    public q(Context context, g gVar, View view, int i2, int i3, boolean z) {
        this.f1679d = context;
        this.f1680e = gVar;
        this.f1682g = z;
        this.f1681f = new f(gVar, LayoutInflater.from(context), z, f1678c);
        this.f1684i = i2;
        this.j = i3;
        Resources resources = context.getResources();
        this.f1683h = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(b.b.d.abc_config_prefDialogWidth));
        this.o = view;
        this.k = new MenuPopupWindow(context, null, i2, i3);
        gVar.c(this, context);
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
            this.u = k.r(this.f1681f, null, this.f1679d, this.f1683h);
            this.t = true;
        }
        this.k.F(this.u);
        this.k.I(2);
        this.k.H(q());
        this.k.g();
        ListView listViewL = this.k.l();
        listViewL.setOnKeyListener(this);
        if (this.w && this.f1680e.z() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f1679d).inflate(b.b.g.abc_popup_menu_header_item_layout, (ViewGroup) listViewL, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            if (textView != null) {
                textView.setText(this.f1680e.z());
            }
            frameLayout.setEnabled(false);
            listViewL.addHeaderView(frameLayout, null, false);
        }
        this.k.o(this.f1681f);
        this.k.g();
        return true;
    }

    @Override // b.b.p.j.m
    public void a(g gVar, boolean z) {
        if (gVar != this.f1680e) {
            return;
        }
        dismiss();
        m.a aVar = this.q;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // b.b.p.j.p
    public boolean b() {
        return !this.s && this.k.b();
    }

    @Override // b.b.p.j.m
    public boolean d() {
        return false;
    }

    @Override // b.b.p.j.p
    public void dismiss() {
        if (b()) {
            this.k.dismiss();
        }
    }

    @Override // b.b.p.j.m
    public Parcelable e() {
        return null;
    }

    @Override // b.b.p.j.p
    public void g() {
        if (!C()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // b.b.p.j.m
    public void h(Parcelable parcelable) {
    }

    @Override // b.b.p.j.m
    public void k(m.a aVar) {
        this.q = aVar;
    }

    @Override // b.b.p.j.p
    public ListView l() {
        return this.k.l();
    }

    @Override // b.b.p.j.m
    public boolean m(r rVar) {
        if (rVar.hasVisibleItems()) {
            l lVar = new l(this.f1679d, rVar, this.p, this.f1682g, this.f1684i, this.j);
            lVar.j(this.q);
            lVar.g(k.A(rVar));
            lVar.i(this.n);
            this.n = null;
            this.f1680e.e(false);
            int iE = this.k.e();
            int iH = this.k.h();
            if ((Gravity.getAbsoluteGravity(this.v, t.z(this.o)) & 7) == 5) {
                iE += this.o.getWidth();
            }
            if (lVar.n(iE, iH)) {
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

    @Override // b.b.p.j.m
    public void n(boolean z) {
        this.t = false;
        f fVar = this.f1681f;
        if (fVar != null) {
            fVar.notifyDataSetChanged();
        }
    }

    @Override // b.b.p.j.k
    public void o(g gVar) {
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.s = true;
        this.f1680e.close();
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
    public boolean onKey(View view, int i2, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i2 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // b.b.p.j.k
    public void s(View view) {
        this.o = view;
    }

    @Override // b.b.p.j.k
    public void u(boolean z) {
        this.f1681f.d(z);
    }

    @Override // b.b.p.j.k
    public void v(int i2) {
        this.v = i2;
    }

    @Override // b.b.p.j.k
    public void w(int i2) {
        this.k.d(i2);
    }

    @Override // b.b.p.j.k
    public void x(PopupWindow.OnDismissListener onDismissListener) {
        this.n = onDismissListener;
    }

    @Override // b.b.p.j.k
    public void y(boolean z) {
        this.w = z;
    }

    @Override // b.b.p.j.k
    public void z(int i2) {
        this.k.n(i2);
    }
}
