package a.b.p;

import a.b.o.i.m;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.Toolbar.d;

/* JADX INFO: loaded from: classes.dex */
public class z0 implements d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Toolbar f408a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f409b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f410c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public View f411d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Drawable f412e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f413f;
    public Drawable g;
    public boolean h;
    public CharSequence i;
    public CharSequence j;
    public CharSequence k;
    public Window.Callback l;
    public boolean m;
    public c n;
    public int o;
    public int p;
    public Drawable q;

    public class a extends a.h.k.u {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f414a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f415b;

        public a(int i) {
            this.f415b = i;
        }

        @Override // a.h.k.t
        public void a(View view) {
            if (this.f414a) {
                return;
            }
            z0.this.f408a.setVisibility(this.f415b);
        }

        @Override // a.h.k.u, a.h.k.t
        public void b(View view) {
            z0.this.f408a.setVisibility(0);
        }

        @Override // a.h.k.u, a.h.k.t
        public void c(View view) {
            this.f414a = true;
        }
    }

    public z0(Toolbar toolbar, boolean z) {
        int i;
        Drawable drawable;
        int i2 = a.b.h.abc_action_bar_up_description;
        this.o = 0;
        this.p = 0;
        this.f408a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.h = this.i != null;
        this.g = toolbar.getNavigationIcon();
        x0 x0VarQ = x0.q(toolbar.getContext(), null, a.b.j.ActionBar, a.b.a.actionBarStyle, 0);
        this.q = x0VarQ.g(a.b.j.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence charSequenceN = x0VarQ.n(a.b.j.ActionBar_title);
            if (!TextUtils.isEmpty(charSequenceN)) {
                this.h = true;
                this.i = charSequenceN;
                if ((this.f409b & 8) != 0) {
                    this.f408a.setTitle(charSequenceN);
                }
            }
            CharSequence charSequenceN2 = x0VarQ.n(a.b.j.ActionBar_subtitle);
            if (!TextUtils.isEmpty(charSequenceN2)) {
                this.j = charSequenceN2;
                if ((this.f409b & 8) != 0) {
                    this.f408a.setSubtitle(charSequenceN2);
                }
            }
            Drawable drawableG = x0VarQ.g(a.b.j.ActionBar_logo);
            if (drawableG != null) {
                this.f413f = drawableG;
                y();
            }
            Drawable drawableG2 = x0VarQ.g(a.b.j.ActionBar_icon);
            if (drawableG2 != null) {
                this.f412e = drawableG2;
                y();
            }
            if (this.g == null && (drawable = this.q) != null) {
                this.g = drawable;
                x();
            }
            u(x0VarQ.j(a.b.j.ActionBar_displayOptions, 0));
            int iL = x0VarQ.l(a.b.j.ActionBar_customNavigationLayout, 0);
            if (iL != 0) {
                View viewInflate = LayoutInflater.from(this.f408a.getContext()).inflate(iL, (ViewGroup) this.f408a, false);
                View view = this.f411d;
                if (view != null && (this.f409b & 16) != 0) {
                    this.f408a.removeView(view);
                }
                this.f411d = viewInflate;
                if (viewInflate != null && (this.f409b & 16) != 0) {
                    this.f408a.addView(viewInflate);
                }
                u(this.f409b | 16);
            }
            int iK = x0VarQ.k(a.b.j.ActionBar_height, 0);
            if (iK > 0) {
                ViewGroup.LayoutParams layoutParams = this.f408a.getLayoutParams();
                layoutParams.height = iK;
                this.f408a.setLayoutParams(layoutParams);
            }
            int iE = x0VarQ.e(a.b.j.ActionBar_contentInsetStart, -1);
            int iE2 = x0VarQ.e(a.b.j.ActionBar_contentInsetEnd, -1);
            if (iE >= 0 || iE2 >= 0) {
                Toolbar toolbar2 = this.f408a;
                int iMax = Math.max(iE, 0);
                int iMax2 = Math.max(iE2, 0);
                toolbar2.d();
                toolbar2.t.a(iMax, iMax2);
            }
            int iL2 = x0VarQ.l(a.b.j.ActionBar_titleTextStyle, 0);
            if (iL2 != 0) {
                Toolbar toolbar3 = this.f408a;
                Context context = toolbar3.getContext();
                toolbar3.l = iL2;
                TextView textView = toolbar3.f1467b;
                if (textView != null) {
                    textView.setTextAppearance(context, iL2);
                }
            }
            int iL3 = x0VarQ.l(a.b.j.ActionBar_subtitleTextStyle, 0);
            if (iL3 != 0) {
                Toolbar toolbar4 = this.f408a;
                Context context2 = toolbar4.getContext();
                toolbar4.m = iL3;
                TextView textView2 = toolbar4.f1468c;
                if (textView2 != null) {
                    textView2.setTextAppearance(context2, iL3);
                }
            }
            int iL4 = x0VarQ.l(a.b.j.ActionBar_popupTheme, 0);
            if (iL4 != 0) {
                this.f408a.setPopupTheme(iL4);
            }
        } else {
            if (this.f408a.getNavigationIcon() != null) {
                i = 15;
                this.q = this.f408a.getNavigationIcon();
            } else {
                i = 11;
            }
            this.f409b = i;
        }
        x0VarQ.f390b.recycle();
        if (i2 != this.p) {
            this.p = i2;
            if (TextUtils.isEmpty(this.f408a.getNavigationContentDescription())) {
                int i3 = this.p;
                this.k = i3 != 0 ? t().getString(i3) : null;
                w();
            }
        }
        this.k = this.f408a.getNavigationContentDescription();
        this.f408a.setNavigationOnClickListener(new y0(this));
    }

    @Override // a.b.p.d0
    public boolean a() {
        return this.f408a.v();
    }

    @Override // a.b.p.d0
    public void b(Menu menu, m.a aVar) {
        a.b.o.i.i iVar;
        if (this.n == null) {
            c cVar = new c(this.f408a.getContext());
            this.n = cVar;
            if (cVar == null) {
                throw null;
            }
        }
        c cVar2 = this.n;
        cVar2.f142e = aVar;
        Toolbar toolbar = this.f408a;
        a.b.o.i.g gVar = (a.b.o.i.g) menu;
        if (gVar == null && toolbar.f1466a == null) {
            return;
        }
        toolbar.f();
        a.b.o.i.g gVar2 = toolbar.f1466a.p;
        if (gVar2 == gVar) {
            return;
        }
        if (gVar2 != null) {
            gVar2.u(toolbar.J);
            gVar2.u(toolbar.K);
        }
        if (toolbar.K == null) {
            toolbar.K = toolbar.new d();
        }
        cVar2.q = true;
        if (gVar != null) {
            gVar.b(cVar2, toolbar.j);
            gVar.b(toolbar.K, toolbar.j);
        } else {
            cVar2.j(toolbar.j, null);
            Toolbar.d dVar = toolbar.K;
            a.b.o.i.g gVar3 = dVar.f1475a;
            if (gVar3 != null && (iVar = dVar.f1476b) != null) {
                gVar3.d(iVar);
            }
            dVar.f1475a = null;
            cVar2.h(true);
            toolbar.K.h(true);
        }
        toolbar.f1466a.setPopupTheme(toolbar.k);
        toolbar.f1466a.setPresenter(cVar2);
        toolbar.J = cVar2;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    @Override // a.b.p.d0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean c() {
        /*
            r3 = this;
            androidx.appcompat.widget.Toolbar r3 = r3.f408a
            androidx.appcompat.widget.ActionMenuView r3 = r3.f1466a
            r0 = 1
            r1 = 0
            if (r3 == 0) goto L22
            a.b.p.c r3 = r3.t
            if (r3 == 0) goto L1e
            a.b.p.c$c r2 = r3.v
            if (r2 != 0) goto L19
            boolean r3 = r3.m()
            if (r3 == 0) goto L17
            goto L19
        L17:
            r3 = r1
            goto L1a
        L19:
            r3 = r0
        L1a:
            if (r3 == 0) goto L1e
            r3 = r0
            goto L1f
        L1e:
            r3 = r1
        L1f:
            if (r3 == 0) goto L22
            goto L23
        L22:
            r0 = r1
        L23:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.p.z0.c():boolean");
    }

    @Override // a.b.p.d0
    public void collapseActionView() {
        Toolbar.d dVar = this.f408a.K;
        a.b.o.i.i iVar = dVar == null ? null : dVar.f1476b;
        if (iVar != null) {
            iVar.collapseActionView();
        }
    }

    @Override // a.b.p.d0
    public void d() {
        this.m = true;
    }

    @Override // a.b.p.d0
    public boolean e() {
        return this.f408a.p();
    }

    @Override // a.b.p.d0
    public boolean f() {
        ActionMenuView actionMenuView;
        Toolbar toolbar = this.f408a;
        return toolbar.getVisibility() == 0 && (actionMenuView = toolbar.f1466a) != null && actionMenuView.s;
    }

    @Override // a.b.p.d0
    public boolean g() {
        ActionMenuView actionMenuView = this.f408a.f1466a;
        if (actionMenuView != null) {
            c cVar = actionMenuView.t;
            if (cVar != null && cVar.i()) {
                return true;
            }
        }
        return false;
    }

    @Override // a.b.p.d0
    public CharSequence getTitle() {
        return this.f408a.getTitle();
    }

    @Override // a.b.p.d0
    public void h() {
        c cVar;
        ActionMenuView actionMenuView = this.f408a.f1466a;
        if (actionMenuView == null || (cVar = actionMenuView.t) == null) {
            return;
        }
        cVar.e();
    }

    @Override // a.b.p.d0
    public a.h.k.s i(int i, long j) {
        a.h.k.s sVarB = a.h.k.q.b(this.f408a);
        sVarB.a(i == 0 ? 1.0f : 0.0f);
        sVarB.c(j);
        a aVar = new a(i);
        View view = sVarB.f835a.get();
        if (view != null) {
            sVarB.e(view, aVar);
        }
        return sVarB;
    }

    @Override // a.b.p.d0
    public int j() {
        return this.f409b;
    }

    @Override // a.b.p.d0
    public void k(int i) {
        this.f408a.setVisibility(i);
    }

    @Override // a.b.p.d0
    public void l() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // a.b.p.d0
    public boolean m() {
        Toolbar.d dVar = this.f408a.K;
        return (dVar == null || dVar.f1476b == null) ? false : true;
    }

    @Override // a.b.p.d0
    public void n(int i) {
        this.f413f = i != 0 ? a.b.l.a.a.b(t(), i) : null;
        y();
    }

    @Override // a.b.p.d0
    public void o(q0 q0Var) {
        View view = this.f410c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f408a;
            if (parent == toolbar) {
                toolbar.removeView(this.f410c);
            }
        }
        this.f410c = null;
    }

    @Override // a.b.p.d0
    public ViewGroup p() {
        return this.f408a;
    }

    @Override // a.b.p.d0
    public void q(boolean z) {
    }

    @Override // a.b.p.d0
    public void r() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // a.b.p.d0
    public void s(boolean z) {
        this.f408a.setCollapsible(z);
    }

    @Override // a.b.p.d0
    public void setIcon(int i) {
        this.f412e = i != 0 ? a.b.l.a.a.b(t(), i) : null;
        y();
    }

    @Override // a.b.p.d0
    public void setIcon(Drawable drawable) {
        this.f412e = drawable;
        y();
    }

    @Override // a.b.p.d0
    public void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // a.b.p.d0
    public void setWindowTitle(CharSequence charSequence) {
        if (this.h) {
            return;
        }
        this.i = charSequence;
        if ((this.f409b & 8) != 0) {
            this.f408a.setTitle(charSequence);
        }
    }

    @Override // a.b.p.d0
    public Context t() {
        return this.f408a.getContext();
    }

    @Override // a.b.p.d0
    public void u(int i) {
        View view;
        CharSequence charSequence;
        Toolbar toolbar;
        int i2 = this.f409b ^ i;
        this.f409b = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    w();
                }
                x();
            }
            if ((i2 & 3) != 0) {
                y();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.f408a.setTitle(this.i);
                    toolbar = this.f408a;
                    charSequence = this.j;
                } else {
                    charSequence = null;
                    this.f408a.setTitle((CharSequence) null);
                    toolbar = this.f408a;
                }
                toolbar.setSubtitle(charSequence);
            }
            if ((i2 & 16) == 0 || (view = this.f411d) == null) {
                return;
            }
            int i3 = i & 16;
            Toolbar toolbar2 = this.f408a;
            if (i3 != 0) {
                toolbar2.addView(view);
            } else {
                toolbar2.removeView(view);
            }
        }
    }

    @Override // a.b.p.d0
    public int v() {
        return this.o;
    }

    public final void w() {
        if ((this.f409b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.f408a.setNavigationContentDescription(this.p);
            } else {
                this.f408a.setNavigationContentDescription(this.k);
            }
        }
    }

    public final void x() {
        if ((this.f409b & 4) == 0) {
            this.f408a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f408a;
        Drawable drawable = this.g;
        if (drawable == null) {
            drawable = this.q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    public final void y() {
        Drawable drawable;
        int i = this.f409b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.f413f) == null) {
            drawable = this.f412e;
        }
        this.f408a.setLogo(drawable);
    }
}
