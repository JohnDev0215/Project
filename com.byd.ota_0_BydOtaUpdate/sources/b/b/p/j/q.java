package b.b.p.j;

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
import androidx.appcompat.widget.MenuPopupWindow;
import b.b.p.j.m;

/* JADX INFO: compiled from: StandardMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
public final class q extends k implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, m, View.OnKeyListener {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f981d = b.b.g.o;
    public final boolean E3;
    public final int F3;
    public final int G3;
    public final int H3;
    public final MenuPopupWindow I3;
    public PopupWindow.OnDismissListener L3;
    public View M3;
    public View N3;
    public m.a O3;
    public ViewTreeObserver P3;
    public boolean Q3;
    public boolean R3;
    public int S3;
    public boolean U3;
    public final Context s;
    public final g x;
    public final f y;
    public final ViewTreeObserver.OnGlobalLayoutListener J3 = new a();
    public final View.OnAttachStateChangeListener K3 = new b();
    public int T3 = 0;

    /* JADX INFO: compiled from: StandardMenuPopup.java */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!q.this.b() || q.this.I3.x()) {
                return;
            }
            View view = q.this.N3;
            if (view == null || !view.isShown()) {
                q.this.dismiss();
            } else {
                q.this.I3.f();
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
            ViewTreeObserver viewTreeObserver = q.this.P3;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    q.this.P3 = view.getViewTreeObserver();
                }
                q qVar = q.this;
                qVar.P3.removeGlobalOnLayoutListener(qVar.J3);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public q(Context context, g gVar, View view, int i2, int i3, boolean z) {
        this.s = context;
        this.x = gVar;
        this.E3 = z;
        this.y = new f(gVar, LayoutInflater.from(context), z, f981d);
        this.G3 = i2;
        this.H3 = i3;
        Resources resources = context.getResources();
        this.F3 = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(b.b.d.f725d));
        this.M3 = view;
        this.I3 = new MenuPopupWindow(context, null, i2, i3);
        gVar.c(this, context);
    }

    @Override // b.b.p.j.m
    public void a(g gVar, boolean z) {
        if (gVar != this.x) {
            return;
        }
        dismiss();
        m.a aVar = this.O3;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // b.b.p.j.p
    public boolean b() {
        return !this.Q3 && this.I3.b();
    }

    @Override // b.b.p.j.m
    public boolean c() {
        return false;
    }

    @Override // b.b.p.j.p
    public void dismiss() {
        if (b()) {
            this.I3.dismiss();
        }
    }

    @Override // b.b.p.j.p
    public void f() {
        if (!z()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // b.b.p.j.m
    public void h(m.a aVar) {
        this.O3 = aVar;
    }

    @Override // b.b.p.j.m
    public boolean i(r rVar) {
        if (rVar.hasVisibleItems()) {
            l lVar = new l(this.s, rVar, this.N3, this.E3, this.G3, this.H3);
            lVar.j(this.O3);
            lVar.g(k.x(rVar));
            lVar.i(this.L3);
            this.L3 = null;
            this.x.e(false);
            int iC = this.I3.c();
            int iG = this.I3.g();
            if ((Gravity.getAbsoluteGravity(this.T3, b.f.m.s.v(this.M3)) & 7) == 5) {
                iC += this.M3.getWidth();
            }
            if (lVar.n(iC, iG)) {
                m.a aVar = this.O3;
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
    public void j(boolean z) {
        this.R3 = false;
        f fVar = this.y;
        if (fVar != null) {
            fVar.notifyDataSetChanged();
        }
    }

    @Override // b.b.p.j.p
    public ListView k() {
        return this.I3.k();
    }

    @Override // b.b.p.j.k
    public void l(g gVar) {
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.Q3 = true;
        this.x.close();
        ViewTreeObserver viewTreeObserver = this.P3;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.P3 = this.N3.getViewTreeObserver();
            }
            this.P3.removeGlobalOnLayoutListener(this.J3);
            this.P3 = null;
        }
        this.N3.removeOnAttachStateChangeListener(this.K3);
        PopupWindow.OnDismissListener onDismissListener = this.L3;
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
    public void p(View view) {
        this.M3 = view;
    }

    @Override // b.b.p.j.k
    public void r(boolean z) {
        this.y.d(z);
    }

    @Override // b.b.p.j.k
    public void s(int i2) {
        this.T3 = i2;
    }

    @Override // b.b.p.j.k
    public void t(int i2) {
        this.I3.a(i2);
    }

    @Override // b.b.p.j.k
    public void u(PopupWindow.OnDismissListener onDismissListener) {
        this.L3 = onDismissListener;
    }

    @Override // b.b.p.j.k
    public void v(boolean z) {
        this.U3 = z;
    }

    @Override // b.b.p.j.k
    public void w(int i2) {
        this.I3.n(i2);
    }

    public final boolean z() {
        View view;
        if (b()) {
            return true;
        }
        if (this.Q3 || (view = this.M3) == null) {
            return false;
        }
        this.N3 = view;
        this.I3.G(this);
        this.I3.H(this);
        this.I3.F(true);
        View view2 = this.N3;
        boolean z = this.P3 == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.P3 = viewTreeObserver;
        if (z) {
            viewTreeObserver.addOnGlobalLayoutListener(this.J3);
        }
        view2.addOnAttachStateChangeListener(this.K3);
        this.I3.z(view2);
        this.I3.C(this.T3);
        if (!this.R3) {
            this.S3 = k.o(this.y, null, this.s, this.F3);
            this.R3 = true;
        }
        this.I3.B(this.S3);
        this.I3.E(2);
        this.I3.D(n());
        this.I3.f();
        ListView listViewK = this.I3.k();
        listViewK.setOnKeyListener(this);
        if (this.U3 && this.x.x() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.s).inflate(b.b.g.n, (ViewGroup) listViewK, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            if (textView != null) {
                textView.setText(this.x.x());
            }
            frameLayout.setEnabled(false);
            listViewK.addHeaderView(frameLayout, null, false);
        }
        this.I3.o(this.y);
        this.I3.f();
        return true;
    }
}
