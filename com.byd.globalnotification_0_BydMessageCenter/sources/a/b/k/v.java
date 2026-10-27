package a.b.k;

import a.b.k.e;
import a.b.o.a;
import a.b.o.i.g;
import a.b.p.d0;
import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class v extends e implements ActionBarOverlayLayout.d {
    public static final Interpolator A = new AccelerateInterpolator();
    public static final Interpolator B = new DecelerateInterpolator();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f73a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f74b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ActionBarOverlayLayout f75c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ActionBarContainer f76d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d0 f77e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ActionBarContextView f78f;
    public View g;
    public boolean h;
    public d i;
    public a.b.o.a j;
    public a.InterfaceC0001a k;
    public boolean l;
    public ArrayList<e.b> m;
    public boolean n;
    public int o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public boolean t;
    public a.b.o.g u;
    public boolean v;
    public boolean w;
    public final a.h.k.t x;
    public final a.h.k.t y;
    public final a.h.k.v z;

    public class a extends a.h.k.u {
        public a() {
        }

        @Override // a.h.k.t
        public void a(View view) {
            View view2;
            v vVar = v.this;
            if (vVar.p && (view2 = vVar.g) != null) {
                view2.setTranslationY(0.0f);
                v.this.f76d.setTranslationY(0.0f);
            }
            v.this.f76d.setVisibility(8);
            v.this.f76d.setTransitioning(false);
            v vVar2 = v.this;
            vVar2.u = null;
            a.InterfaceC0001a interfaceC0001a = vVar2.k;
            if (interfaceC0001a != null) {
                interfaceC0001a.b(vVar2.j);
                vVar2.j = null;
                vVar2.k = null;
            }
            ActionBarOverlayLayout actionBarOverlayLayout = v.this.f75c;
            if (actionBarOverlayLayout != null) {
                a.h.k.q.S(actionBarOverlayLayout);
            }
        }
    }

    public class b extends a.h.k.u {
        public b() {
        }

        @Override // a.h.k.t
        public void a(View view) {
            v vVar = v.this;
            vVar.u = null;
            vVar.f76d.requestLayout();
        }
    }

    public class c implements a.h.k.v {
        public c() {
        }
    }

    public class d extends a.b.o.a implements g.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Context f82c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a.b.o.i.g f83d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public a.InterfaceC0001a f84e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public WeakReference<View> f85f;

        public d(Context context, a.InterfaceC0001a interfaceC0001a) {
            this.f82c = context;
            this.f84e = interfaceC0001a;
            a.b.o.i.g gVar = new a.b.o.i.g(context);
            gVar.l = 1;
            this.f83d = gVar;
            gVar.f180e = this;
        }

        @Override // a.b.o.i.g.a
        public void a(a.b.o.i.g gVar) {
            if (this.f84e == null) {
                return;
            }
            i();
            a.b.p.c cVar = v.this.f78f.f222d;
            if (cVar != null) {
                cVar.n();
            }
        }

        @Override // a.b.o.i.g.a
        public boolean b(a.b.o.i.g gVar, MenuItem menuItem) {
            a.InterfaceC0001a interfaceC0001a = this.f84e;
            if (interfaceC0001a != null) {
                return interfaceC0001a.c(this, menuItem);
            }
            return false;
        }

        @Override // a.b.o.a
        public void c() {
            v vVar = v.this;
            if (vVar.i != this) {
                return;
            }
            if ((vVar.q || vVar.r) ? false : true) {
                this.f84e.b(this);
            } else {
                v vVar2 = v.this;
                vVar2.j = this;
                vVar2.k = this.f84e;
            }
            this.f84e = null;
            v.this.k(false);
            ActionBarContextView actionBarContextView = v.this.f78f;
            if (actionBarContextView.k == null) {
                actionBarContextView.h();
            }
            v.this.f77e.p().sendAccessibilityEvent(32);
            v vVar3 = v.this;
            vVar3.f75c.setHideOnContentScrollEnabled(vVar3.w);
            v.this.i = null;
        }

        @Override // a.b.o.a
        public View d() {
            WeakReference<View> weakReference = this.f85f;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // a.b.o.a
        public Menu e() {
            return this.f83d;
        }

        @Override // a.b.o.a
        public MenuInflater f() {
            return new a.b.o.f(this.f82c);
        }

        @Override // a.b.o.a
        public CharSequence g() {
            return v.this.f78f.getSubtitle();
        }

        @Override // a.b.o.a
        public CharSequence h() {
            return v.this.f78f.getTitle();
        }

        @Override // a.b.o.a
        public void i() {
            if (v.this.i != this) {
                return;
            }
            this.f83d.z();
            try {
                this.f84e.a(this, this.f83d);
            } finally {
                this.f83d.y();
            }
        }

        @Override // a.b.o.a
        public boolean j() {
            return v.this.f78f.r;
        }

        @Override // a.b.o.a
        public void k(View view) {
            v.this.f78f.setCustomView(view);
            this.f85f = new WeakReference<>(view);
        }

        @Override // a.b.o.a
        public void l(int i) {
            v.this.f78f.setSubtitle(v.this.f73a.getResources().getString(i));
        }

        @Override // a.b.o.a
        public void m(CharSequence charSequence) {
            v.this.f78f.setSubtitle(charSequence);
        }

        @Override // a.b.o.a
        public void n(int i) {
            v.this.f78f.setTitle(v.this.f73a.getResources().getString(i));
        }

        @Override // a.b.o.a
        public void o(CharSequence charSequence) {
            v.this.f78f.setTitle(charSequence);
        }

        @Override // a.b.o.a
        public void p(boolean z) {
            this.f91b = z;
            v.this.f78f.setTitleOptional(z);
        }
    }

    public v(Activity activity, boolean z) {
        new ArrayList();
        this.m = new ArrayList<>();
        this.o = 0;
        this.p = true;
        this.t = true;
        this.x = new a();
        this.y = new b();
        this.z = new c();
        View decorView = activity.getWindow().getDecorView();
        l(decorView);
        if (z) {
            return;
        }
        this.g = decorView.findViewById(R.id.content);
    }

    public v(Dialog dialog) {
        new ArrayList();
        this.m = new ArrayList<>();
        this.o = 0;
        this.p = true;
        this.t = true;
        this.x = new a();
        this.y = new b();
        this.z = new c();
        l(dialog.getWindow().getDecorView());
    }

    @Override // a.b.k.e
    public boolean a() {
        d0 d0Var = this.f77e;
        if (d0Var == null || !d0Var.m()) {
            return false;
        }
        this.f77e.collapseActionView();
        return true;
    }

    @Override // a.b.k.e
    public void b(boolean z) {
        if (z == this.l) {
            return;
        }
        this.l = z;
        int size = this.m.size();
        for (int i = 0; i < size; i++) {
            this.m.get(i).a(z);
        }
    }

    @Override // a.b.k.e
    public int c() {
        return this.f77e.j();
    }

    @Override // a.b.k.e
    public Context d() {
        if (this.f74b == null) {
            TypedValue typedValue = new TypedValue();
            this.f73a.getTheme().resolveAttribute(a.b.a.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.f74b = new ContextThemeWrapper(this.f73a, i);
            } else {
                this.f74b = this.f73a;
            }
        }
        return this.f74b;
    }

    @Override // a.b.k.e
    public void e(Configuration configuration) {
        m(this.f73a.getResources().getBoolean(a.b.b.abc_action_bar_embed_tabs));
    }

    @Override // a.b.k.e
    public boolean f(int i, KeyEvent keyEvent) {
        a.b.o.i.g gVar;
        d dVar = this.i;
        if (dVar == null || (gVar = dVar.f83d) == null) {
            return false;
        }
        gVar.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return gVar.performShortcut(i, keyEvent, 0);
    }

    @Override // a.b.k.e
    public void g(boolean z) {
        if (this.h) {
            return;
        }
        int i = z ? 4 : 0;
        int iJ = this.f77e.j();
        this.h = true;
        this.f77e.u((i & 4) | (iJ & (-5)));
    }

    @Override // a.b.k.e
    public void h(boolean z) {
        a.b.o.g gVar;
        this.v = z;
        if (z || (gVar = this.u) == null) {
            return;
        }
        gVar.a();
    }

    @Override // a.b.k.e
    public void i(CharSequence charSequence) {
        this.f77e.setWindowTitle(charSequence);
    }

    @Override // a.b.k.e
    public a.b.o.a j(a.InterfaceC0001a interfaceC0001a) {
        d dVar = this.i;
        if (dVar != null) {
            dVar.c();
        }
        this.f75c.setHideOnContentScrollEnabled(false);
        this.f78f.h();
        d dVar2 = new d(this.f78f.getContext(), interfaceC0001a);
        dVar2.f83d.z();
        try {
            if (!dVar2.f84e.d(dVar2, dVar2.f83d)) {
                return null;
            }
            this.i = dVar2;
            dVar2.i();
            this.f78f.f(dVar2);
            k(true);
            this.f78f.sendAccessibilityEvent(32);
            return dVar2;
        } finally {
            dVar2.f83d.y();
        }
    }

    public void k(boolean z) {
        a.h.k.s sVarE;
        a.h.k.s sVarE2;
        if (z) {
            if (!this.s) {
                this.s = true;
                ActionBarOverlayLayout actionBarOverlayLayout = this.f75c;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.setShowingForActionMode(true);
                }
                n(false);
            }
        } else if (this.s) {
            this.s = false;
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.f75c;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.setShowingForActionMode(false);
            }
            n(false);
        }
        if (!a.h.k.q.C(this.f76d)) {
            if (z) {
                this.f77e.k(4);
                this.f78f.setVisibility(0);
                return;
            } else {
                this.f77e.k(0);
                this.f78f.setVisibility(8);
                return;
            }
        }
        if (z) {
            sVarE = this.f77e.i(4, 100L);
            sVarE2 = this.f78f.e(0, 200L);
        } else {
            a.h.k.s sVarI = this.f77e.i(0, 200L);
            sVarE = this.f78f.e(8, 100L);
            sVarE2 = sVarI;
        }
        a.b.o.g gVar = new a.b.o.g();
        gVar.f122a.add(sVarE);
        View view = sVarE.f835a.get();
        long duration = view != null ? view.animate().getDuration() : 0L;
        View view2 = sVarE2.f835a.get();
        if (view2 != null) {
            view2.animate().setStartDelay(duration);
        }
        gVar.f122a.add(sVarE2);
        gVar.b();
    }

    public final void l(View view) {
        d0 wrapper;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(a.b.f.decor_content_parent);
        this.f75c = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        KeyEvent.Callback callbackFindViewById = view.findViewById(a.b.f.action_bar);
        if (callbackFindViewById instanceof d0) {
            wrapper = (d0) callbackFindViewById;
        } else {
            if (!(callbackFindViewById instanceof Toolbar)) {
                StringBuilder sbD = b.a.a.a.a.d("Can't make a decor toolbar out of ");
                sbD.append(callbackFindViewById != null ? callbackFindViewById.getClass().getSimpleName() : "null");
                throw new IllegalStateException(sbD.toString());
            }
            wrapper = ((Toolbar) callbackFindViewById).getWrapper();
        }
        this.f77e = wrapper;
        this.f78f = (ActionBarContextView) view.findViewById(a.b.f.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(a.b.f.action_bar_container);
        this.f76d = actionBarContainer;
        d0 d0Var = this.f77e;
        if (d0Var == null || this.f78f == null || actionBarContainer == null) {
            throw new IllegalStateException(v.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f73a = d0Var.t();
        boolean z = (this.f77e.j() & 4) != 0;
        if (z) {
            this.h = true;
        }
        Context context = this.f73a;
        this.f77e.q((context.getApplicationInfo().targetSdkVersion < 14) || z);
        m(context.getResources().getBoolean(a.b.b.abc_action_bar_embed_tabs));
        TypedArray typedArrayObtainStyledAttributes = this.f73a.obtainStyledAttributes(null, a.b.j.ActionBar, a.b.a.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(a.b.j.ActionBar_hideOnContentScroll, false)) {
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.f75c;
            if (!actionBarOverlayLayout2.h) {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
            this.w = true;
            actionBarOverlayLayout2.setHideOnContentScrollEnabled(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(a.b.j.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            a.h.k.q.Z(this.f76d, dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void m(boolean z) {
        this.n = z;
        if (z) {
            this.f76d.setTabContainer(null);
            this.f77e.o(null);
        } else {
            this.f77e.o(null);
            this.f76d.setTabContainer(null);
        }
        boolean z2 = this.f77e.v() == 2;
        this.f77e.s(!this.n && z2);
        this.f75c.setHasNonEmbeddedTabs(!this.n && z2);
    }

    public final void n(boolean z) {
        View view;
        View view2;
        View view3;
        if (!(this.s || !this.r)) {
            if (this.t) {
                this.t = false;
                a.b.o.g gVar = this.u;
                if (gVar != null) {
                    gVar.a();
                }
                if (this.o != 0 || (!this.v && !z)) {
                    this.x.a(null);
                    return;
                }
                this.f76d.setAlpha(1.0f);
                this.f76d.setTransitioning(true);
                a.b.o.g gVar2 = new a.b.o.g();
                float f2 = -this.f76d.getHeight();
                if (z) {
                    this.f76d.getLocationInWindow(new int[]{0, 0});
                    f2 -= r9[1];
                }
                a.h.k.s sVarB = a.h.k.q.b(this.f76d);
                sVarB.g(f2);
                sVarB.f(this.z);
                if (!gVar2.f126e) {
                    gVar2.f122a.add(sVarB);
                }
                if (this.p && (view = this.g) != null) {
                    a.h.k.s sVarB2 = a.h.k.q.b(view);
                    sVarB2.g(f2);
                    if (!gVar2.f126e) {
                        gVar2.f122a.add(sVarB2);
                    }
                }
                Interpolator interpolator = A;
                if (!gVar2.f126e) {
                    gVar2.f124c = interpolator;
                }
                if (!gVar2.f126e) {
                    gVar2.f123b = 250L;
                }
                a.h.k.t tVar = this.x;
                if (!gVar2.f126e) {
                    gVar2.f125d = tVar;
                }
                this.u = gVar2;
                gVar2.b();
                return;
            }
            return;
        }
        if (this.t) {
            return;
        }
        this.t = true;
        a.b.o.g gVar3 = this.u;
        if (gVar3 != null) {
            gVar3.a();
        }
        this.f76d.setVisibility(0);
        if (this.o == 0 && (this.v || z)) {
            this.f76d.setTranslationY(0.0f);
            float f3 = -this.f76d.getHeight();
            if (z) {
                this.f76d.getLocationInWindow(new int[]{0, 0});
                f3 -= r9[1];
            }
            this.f76d.setTranslationY(f3);
            a.b.o.g gVar4 = new a.b.o.g();
            a.h.k.s sVarB3 = a.h.k.q.b(this.f76d);
            sVarB3.g(0.0f);
            sVarB3.f(this.z);
            if (!gVar4.f126e) {
                gVar4.f122a.add(sVarB3);
            }
            if (this.p && (view3 = this.g) != null) {
                view3.setTranslationY(f3);
                a.h.k.s sVarB4 = a.h.k.q.b(this.g);
                sVarB4.g(0.0f);
                if (!gVar4.f126e) {
                    gVar4.f122a.add(sVarB4);
                }
            }
            Interpolator interpolator2 = B;
            if (!gVar4.f126e) {
                gVar4.f124c = interpolator2;
            }
            if (!gVar4.f126e) {
                gVar4.f123b = 250L;
            }
            a.h.k.t tVar2 = this.y;
            if (!gVar4.f126e) {
                gVar4.f125d = tVar2;
            }
            this.u = gVar4;
            gVar4.b();
        } else {
            this.f76d.setAlpha(1.0f);
            this.f76d.setTranslationY(0.0f);
            if (this.p && (view2 = this.g) != null) {
                view2.setTranslationY(0.0f);
            }
            this.y.a(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f75c;
        if (actionBarOverlayLayout != null) {
            a.h.k.q.S(actionBarOverlayLayout);
        }
    }
}
