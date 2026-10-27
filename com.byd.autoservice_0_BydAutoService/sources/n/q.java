package n;

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
import l0.t;
import n.m;

/* JADX INFO: compiled from: StandardMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
public final class q extends k implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, m, View.OnKeyListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int f4016c = R$layout.abc_popup_menu_item_layout;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f4017d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g f4018e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f f4019f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f4020g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f4021h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f4022i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f4023j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final MenuPopupWindow f4024k;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public PopupWindow.OnDismissListener f4027n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public View f4028o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public View f4029p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public m.a f4030q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ViewTreeObserver f4031r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f4032s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f4033t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f4034u;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f4036w;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ViewTreeObserver.OnGlobalLayoutListener f4025l = new a();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final View.OnAttachStateChangeListener f4026m = new b();

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f4035v = 0;

    /* JADX INFO: compiled from: StandardMenuPopup.java */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!q.this.b() || q.this.f4024k.B()) {
                return;
            }
            View view = q.this.f4029p;
            if (view == null || !view.isShown()) {
                q.this.dismiss();
            } else {
                q.this.f4024k.g();
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
            ViewTreeObserver viewTreeObserver = q.this.f4031r;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    q.this.f4031r = view.getViewTreeObserver();
                }
                q qVar = q.this;
                qVar.f4031r.removeGlobalOnLayoutListener(qVar.f4025l);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public q(Context context, g gVar, View view, int i3, int i4, boolean z3) {
        this.f4017d = context;
        this.f4018e = gVar;
        this.f4020g = z3;
        this.f4019f = new f(gVar, LayoutInflater.from(context), z3, f4016c);
        this.f4022i = i3;
        this.f4023j = i4;
        Resources resources = context.getResources();
        this.f4021h = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.f4028o = view;
        this.f4024k = new MenuPopupWindow(context, null, i3, i4);
        gVar.c(this, context);
    }

    public final boolean C() {
        View view;
        if (b()) {
            return true;
        }
        if (this.f4032s || (view = this.f4028o) == null) {
            return false;
        }
        this.f4029p = view;
        this.f4024k.K(this);
        this.f4024k.L(this);
        this.f4024k.J(true);
        View view2 = this.f4029p;
        boolean z3 = this.f4031r == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.f4031r = viewTreeObserver;
        if (z3) {
            viewTreeObserver.addOnGlobalLayoutListener(this.f4025l);
        }
        view2.addOnAttachStateChangeListener(this.f4026m);
        this.f4024k.D(view2);
        this.f4024k.G(this.f4035v);
        if (!this.f4033t) {
            this.f4034u = k.r(this.f4019f, null, this.f4017d, this.f4021h);
            this.f4033t = true;
        }
        this.f4024k.F(this.f4034u);
        this.f4024k.I(2);
        this.f4024k.H(q());
        this.f4024k.g();
        ListView listViewL = this.f4024k.l();
        listViewL.setOnKeyListener(this);
        if (this.f4036w && this.f4018e.z() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f4017d).inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) listViewL, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            if (textView != null) {
                textView.setText(this.f4018e.z());
            }
            frameLayout.setEnabled(false);
            listViewL.addHeaderView(frameLayout, null, false);
        }
        this.f4024k.o(this.f4019f);
        this.f4024k.g();
        return true;
    }

    @Override // n.m
    public void a(g gVar, boolean z3) {
        if (gVar != this.f4018e) {
            return;
        }
        dismiss();
        m.a aVar = this.f4030q;
        if (aVar != null) {
            aVar.a(gVar, z3);
        }
    }

    @Override // n.p
    public boolean b() {
        return !this.f4032s && this.f4024k.b();
    }

    @Override // n.m
    public boolean d() {
        return false;
    }

    @Override // n.p
    public void dismiss() {
        if (b()) {
            this.f4024k.dismiss();
        }
    }

    @Override // n.m
    public Parcelable e() {
        return null;
    }

    @Override // n.p
    public void g() {
        if (!C()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // n.m
    public void h(Parcelable parcelable) {
    }

    @Override // n.m
    public void k(m.a aVar) {
        this.f4030q = aVar;
    }

    @Override // n.p
    public ListView l() {
        return this.f4024k.l();
    }

    @Override // n.m
    public boolean m(r rVar) {
        if (rVar.hasVisibleItems()) {
            l lVar = new l(this.f4017d, rVar, this.f4029p, this.f4020g, this.f4022i, this.f4023j);
            lVar.j(this.f4030q);
            lVar.g(k.A(rVar));
            lVar.i(this.f4027n);
            this.f4027n = null;
            this.f4018e.e(false);
            int iE = this.f4024k.e();
            int iH = this.f4024k.h();
            if ((Gravity.getAbsoluteGravity(this.f4035v, t.z(this.f4028o)) & 7) == 5) {
                iE += this.f4028o.getWidth();
            }
            if (lVar.n(iE, iH)) {
                m.a aVar = this.f4030q;
                if (aVar == null) {
                    return true;
                }
                aVar.b(rVar);
                return true;
            }
        }
        return false;
    }

    @Override // n.m
    public void n(boolean z3) {
        this.f4033t = false;
        f fVar = this.f4019f;
        if (fVar != null) {
            fVar.notifyDataSetChanged();
        }
    }

    @Override // n.k
    public void o(g gVar) {
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.f4032s = true;
        this.f4018e.close();
        ViewTreeObserver viewTreeObserver = this.f4031r;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f4031r = this.f4029p.getViewTreeObserver();
            }
            this.f4031r.removeGlobalOnLayoutListener(this.f4025l);
            this.f4031r = null;
        }
        this.f4029p.removeOnAttachStateChangeListener(this.f4026m);
        PopupWindow.OnDismissListener onDismissListener = this.f4027n;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i3, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i3 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // n.k
    public void s(View view) {
        this.f4028o = view;
    }

    @Override // n.k
    public void u(boolean z3) {
        this.f4019f.d(z3);
    }

    @Override // n.k
    public void v(int i3) {
        this.f4035v = i3;
    }

    @Override // n.k
    public void w(int i3) {
        this.f4024k.d(i3);
    }

    @Override // n.k
    public void x(PopupWindow.OnDismissListener onDismissListener) {
        this.f4027n = onDismissListener;
    }

    @Override // n.k
    public void y(boolean z3) {
        this.f4036w = z3;
    }

    @Override // n.k
    public void z(int i3) {
        this.f4024k.n(i3);
    }
}
