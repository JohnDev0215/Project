package q;

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
import o0.t;
import q.m;

/* JADX INFO: compiled from: StandardMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
public final class q extends k implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, m, View.OnKeyListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f7213e = R$layout.abc_popup_menu_item_layout;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Context f7214f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final g f7215g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final f f7216h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f7217i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f7218j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f7219k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f7220l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final MenuPopupWindow f7221m;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public PopupWindow.OnDismissListener f7224p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public View f7225q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public View f7226r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public m.a f7227s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public ViewTreeObserver f7228t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f7229u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f7230v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f7231w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f7233y;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ViewTreeObserver.OnGlobalLayoutListener f7222n = new a();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final View.OnAttachStateChangeListener f7223o = new b();

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f7232x = 0;

    /* JADX INFO: compiled from: StandardMenuPopup.java */
    public class a implements ViewTreeObserver.OnGlobalLayoutListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!q.this.c() || q.this.f7221m.B()) {
                return;
            }
            View view = q.this.f7226r;
            if (view == null || !view.isShown()) {
                q.this.dismiss();
            } else {
                q.this.f7221m.e();
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
            ViewTreeObserver viewTreeObserver = q.this.f7228t;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    q.this.f7228t = view.getViewTreeObserver();
                }
                q qVar = q.this;
                qVar.f7228t.removeGlobalOnLayoutListener(qVar.f7222n);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public q(Context context, g gVar, View view, int i8, int i9, boolean z7) {
        this.f7214f = context;
        this.f7215g = gVar;
        this.f7217i = z7;
        this.f7216h = new f(gVar, LayoutInflater.from(context), z7, f7213e);
        this.f7219k = i8;
        this.f7220l = i9;
        Resources resources = context.getResources();
        this.f7218j = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.f7225q = view;
        this.f7221m = new MenuPopupWindow(context, null, i8, i9);
        gVar.c(this, context);
    }

    public final boolean C() {
        View view;
        if (c()) {
            return true;
        }
        if (this.f7229u || (view = this.f7225q) == null) {
            return false;
        }
        this.f7226r = view;
        this.f7221m.K(this);
        this.f7221m.L(this);
        this.f7221m.J(true);
        View view2 = this.f7226r;
        boolean z7 = this.f7228t == null;
        ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
        this.f7228t = viewTreeObserver;
        if (z7) {
            viewTreeObserver.addOnGlobalLayoutListener(this.f7222n);
        }
        view2.addOnAttachStateChangeListener(this.f7223o);
        this.f7221m.D(view2);
        this.f7221m.G(this.f7232x);
        if (!this.f7230v) {
            this.f7231w = k.r(this.f7216h, null, this.f7214f, this.f7218j);
            this.f7230v = true;
        }
        this.f7221m.F(this.f7231w);
        this.f7221m.I(2);
        this.f7221m.H(q());
        this.f7221m.e();
        ListView listViewH = this.f7221m.h();
        listViewH.setOnKeyListener(this);
        if (this.f7233y && this.f7215g.z() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f7214f).inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) listViewH, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            if (textView != null) {
                textView.setText(this.f7215g.z());
            }
            frameLayout.setEnabled(false);
            listViewH.addHeaderView(frameLayout, null, false);
        }
        this.f7221m.p(this.f7216h);
        this.f7221m.e();
        return true;
    }

    @Override // q.m
    public void a(g gVar, boolean z7) {
        if (gVar != this.f7215g) {
            return;
        }
        dismiss();
        m.a aVar = this.f7227s;
        if (aVar != null) {
            aVar.a(gVar, z7);
        }
    }

    @Override // q.p
    public boolean c() {
        return !this.f7229u && this.f7221m.c();
    }

    @Override // q.m
    public void d(Parcelable parcelable) {
    }

    @Override // q.p
    public void dismiss() {
        if (c()) {
            this.f7221m.dismiss();
        }
    }

    @Override // q.p
    public void e() {
        if (!C()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // q.m
    public boolean f(r rVar) {
        if (rVar.hasVisibleItems()) {
            l lVar = new l(this.f7214f, rVar, this.f7226r, this.f7217i, this.f7219k, this.f7220l);
            lVar.j(this.f7227s);
            lVar.g(k.A(rVar));
            lVar.i(this.f7224p);
            this.f7224p = null;
            this.f7215g.e(false);
            int iD = this.f7221m.d();
            int iN = this.f7221m.n();
            if ((Gravity.getAbsoluteGravity(this.f7232x, t.A(this.f7225q)) & 7) == 5) {
                iD += this.f7225q.getWidth();
            }
            if (lVar.n(iD, iN)) {
                m.a aVar = this.f7227s;
                if (aVar == null) {
                    return true;
                }
                aVar.b(rVar);
                return true;
            }
        }
        return false;
    }

    @Override // q.m
    public void g(boolean z7) {
        this.f7230v = false;
        f fVar = this.f7216h;
        if (fVar != null) {
            fVar.notifyDataSetChanged();
        }
    }

    @Override // q.p
    public ListView h() {
        return this.f7221m.h();
    }

    @Override // q.m
    public boolean j() {
        return false;
    }

    @Override // q.m
    public Parcelable k() {
        return null;
    }

    @Override // q.m
    public void n(m.a aVar) {
        this.f7227s = aVar;
    }

    @Override // q.k
    public void o(g gVar) {
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.f7229u = true;
        this.f7215g.close();
        ViewTreeObserver viewTreeObserver = this.f7228t;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.f7228t = this.f7226r.getViewTreeObserver();
            }
            this.f7228t.removeGlobalOnLayoutListener(this.f7222n);
            this.f7228t = null;
        }
        this.f7226r.removeOnAttachStateChangeListener(this.f7223o);
        PopupWindow.OnDismissListener onDismissListener = this.f7224p;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i8, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i8 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // q.k
    public void s(View view) {
        this.f7225q = view;
    }

    @Override // q.k
    public void u(boolean z7) {
        this.f7216h.d(z7);
    }

    @Override // q.k
    public void v(int i8) {
        this.f7232x = i8;
    }

    @Override // q.k
    public void w(int i8) {
        this.f7221m.l(i8);
    }

    @Override // q.k
    public void x(PopupWindow.OnDismissListener onDismissListener) {
        this.f7224p = onDismissListener;
    }

    @Override // q.k
    public void y(boolean z7) {
        this.f7233y = z7;
    }

    @Override // q.k
    public void z(int i8) {
        this.f7221m.j(i8);
    }
}
