package androidx.appcompat.app;

import android.R;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.ViewPropertyAnimatorCompatSet;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.DecorToolbar;
import androidx.appcompat.widget.Toolbar;
import defpackage.in3;
import defpackage.jn3;
import defpackage.kn3;
import defpackage.ln3;
import defpackage.om3;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class WindowDecorActionBar extends ActionBar implements ActionBarOverlayLayout.ActionBarVisibilityCallback {
    public Context a;
    public Context b;
    public ActionBarOverlayLayout c;
    public ActionBarContainer d;
    public DecorToolbar e;
    public ActionBarContextView f;
    public final View g;
    public boolean h;
    public ActionModeImpl i;
    public ActionModeImpl j;
    public ActionMode.Callback k;
    public boolean l;
    public final ArrayList<ActionBar.OnMenuVisibilityListener> m;
    public boolean n;
    public int o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public ViewPropertyAnimatorCompatSet t;
    public boolean u;
    public boolean v;
    public final jn3 w;
    public final jn3 x;
    public final ln3 y;
    public static final AccelerateInterpolator z = new AccelerateInterpolator();
    public static final DecelerateInterpolator A = new DecelerateInterpolator();

    /* JADX INFO: renamed from: androidx.appcompat.app.WindowDecorActionBar$1, reason: invalid class name */
    public class AnonymousClass1 extends kn3 {
        public AnonymousClass1() {
        }

        @Override // defpackage.kn3, defpackage.jn3
        public final void a() {
            View view;
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            if (windowDecorActionBar.p && (view = windowDecorActionBar.g) != null) {
                view.setTranslationY(0.0f);
                windowDecorActionBar.d.setTranslationY(0.0f);
            }
            windowDecorActionBar.d.setVisibility(8);
            windowDecorActionBar.d.setTransitioning(false);
            windowDecorActionBar.t = null;
            ActionMode.Callback callback = windowDecorActionBar.k;
            if (callback != null) {
                callback.a(windowDecorActionBar.j);
                windowDecorActionBar.j = null;
                windowDecorActionBar.k = null;
            }
            ActionBarOverlayLayout actionBarOverlayLayout = windowDecorActionBar.c;
            if (actionBarOverlayLayout != null) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.h.c(actionBarOverlayLayout);
            }
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.app.WindowDecorActionBar$2, reason: invalid class name */
    public class AnonymousClass2 extends kn3 {
        public AnonymousClass2() {
        }

        @Override // defpackage.kn3, defpackage.jn3
        public final void a() {
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            windowDecorActionBar.t = null;
            windowDecorActionBar.d.requestLayout();
        }
    }

    public class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {
        public final Context g;
        public final MenuBuilder h;
        public ActionMode.Callback i;
        public WeakReference<View> j;

        public ActionModeImpl(Context context, ActionMode.Callback callback) {
            this.g = context;
            this.i = callback;
            MenuBuilder menuBuilder = new MenuBuilder(context);
            menuBuilder.l = 1;
            this.h = menuBuilder;
            menuBuilder.e = this;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public final boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
            ActionMode.Callback callback = this.i;
            if (callback != null) {
                return callback.c(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public final void b(MenuBuilder menuBuilder) {
            if (this.i == null) {
                return;
            }
            i();
            WindowDecorActionBar.this.f.i();
        }

        @Override // androidx.appcompat.view.ActionMode
        public final void c() {
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            if (windowDecorActionBar.i != this) {
                return;
            }
            if (!windowDecorActionBar.q) {
                this.i.a(this);
            } else {
                windowDecorActionBar.j = this;
                windowDecorActionBar.k = this.i;
            }
            this.i = null;
            windowDecorActionBar.z(false);
            ActionBarContextView actionBarContextView = windowDecorActionBar.f;
            if (actionBarContextView.o == null) {
                actionBarContextView.g();
            }
            windowDecorActionBar.c.setHideOnContentScrollEnabled(windowDecorActionBar.v);
            windowDecorActionBar.i = null;
        }

        @Override // androidx.appcompat.view.ActionMode
        public final View d() {
            WeakReference<View> weakReference = this.j;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // androidx.appcompat.view.ActionMode
        public final MenuBuilder e() {
            return this.h;
        }

        @Override // androidx.appcompat.view.ActionMode
        public final MenuInflater f() {
            return new SupportMenuInflater(this.g);
        }

        @Override // androidx.appcompat.view.ActionMode
        public final CharSequence g() {
            return WindowDecorActionBar.this.f.getSubtitle();
        }

        @Override // androidx.appcompat.view.ActionMode
        public final CharSequence h() {
            return WindowDecorActionBar.this.f.getTitle();
        }

        @Override // androidx.appcompat.view.ActionMode
        public final void i() {
            if (WindowDecorActionBar.this.i != this) {
                return;
            }
            MenuBuilder menuBuilder = this.h;
            menuBuilder.y();
            try {
                this.i.d(this, menuBuilder);
            } finally {
                menuBuilder.x();
            }
        }

        @Override // androidx.appcompat.view.ActionMode
        public final boolean j() {
            return WindowDecorActionBar.this.f.w;
        }

        @Override // androidx.appcompat.view.ActionMode
        public final void k(View view) {
            WindowDecorActionBar.this.f.setCustomView(view);
            this.j = new WeakReference<>(view);
        }

        @Override // androidx.appcompat.view.ActionMode
        public final void l(int i) {
            m(WindowDecorActionBar.this.a.getResources().getString(i));
        }

        @Override // androidx.appcompat.view.ActionMode
        public final void m(CharSequence charSequence) {
            WindowDecorActionBar.this.f.setSubtitle(charSequence);
        }

        @Override // androidx.appcompat.view.ActionMode
        public final void n(int i) {
            o(WindowDecorActionBar.this.a.getResources().getString(i));
        }

        @Override // androidx.appcompat.view.ActionMode
        public final void o(CharSequence charSequence) {
            WindowDecorActionBar.this.f.setTitle(charSequence);
        }

        @Override // androidx.appcompat.view.ActionMode
        public final void p(boolean z) {
            this.f = z;
            WindowDecorActionBar.this.f.setTitleOptional(z);
        }
    }

    public WindowDecorActionBar(Activity activity, boolean z2) {
        new ArrayList();
        this.m = new ArrayList<>();
        this.o = 0;
        this.p = true;
        this.s = true;
        this.w = new AnonymousClass1();
        this.x = new AnonymousClass2();
        this.y = new ln3() { // from class: androidx.appcompat.app.WindowDecorActionBar.3
            @Override // defpackage.ln3
            public final void a() {
                ((View) WindowDecorActionBar.this.d.getParent()).invalidate();
            }
        };
        View decorView = activity.getWindow().getDecorView();
        A(decorView);
        if (z2) {
            return;
        }
        this.g = decorView.findViewById(R.id.content);
    }

    public final void A(View view) {
        DecorToolbar wrapper;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(androidx.appcompat.R.id.decor_content_parent);
        this.c = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        KeyEvent.Callback callbackFindViewById = view.findViewById(androidx.appcompat.R.id.action_bar);
        if (callbackFindViewById instanceof DecorToolbar) {
            wrapper = (DecorToolbar) callbackFindViewById;
        } else {
            if (!(callbackFindViewById instanceof Toolbar)) {
                throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById != null ? callbackFindViewById.getClass().getSimpleName() : "null"));
            }
            wrapper = ((Toolbar) callbackFindViewById).getWrapper();
        }
        this.e = wrapper;
        this.f = (ActionBarContextView) view.findViewById(androidx.appcompat.R.id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(androidx.appcompat.R.id.action_bar_container);
        this.d = actionBarContainer;
        DecorToolbar decorToolbar = this.e;
        if (decorToolbar == null || this.f == null || actionBarContainer == null) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with a compatible window decor layout"));
        }
        this.a = decorToolbar.getContext();
        if ((this.e.p() & 4) != 0) {
            this.h = true;
        }
        Context context = this.a;
        if (context.getApplicationInfo().targetSdkVersion < 14) {
        }
        this.e.i();
        B(context.getResources().getBoolean(androidx.appcompat.R.bool.abc_action_bar_embed_tabs));
        TypedArray typedArrayObtainStyledAttributes = this.a.obtainStyledAttributes(null, androidx.appcompat.R.styleable.ActionBar, androidx.appcompat.R.attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(androidx.appcompat.R.styleable.ActionBar_hideOnContentScroll, false)) {
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.c;
            if (!actionBarOverlayLayout2.l) {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
            this.v = true;
            actionBarOverlayLayout2.setHideOnContentScrollEnabled(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(androidx.appcompat.R.styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            ActionBarContainer actionBarContainer2 = this.d;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.i.l(actionBarContainer2, dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void B(boolean z2) {
        this.n = z2;
        if (z2) {
            this.d.setTabContainer(null);
            this.e.l();
        } else {
            this.e.l();
            this.d.setTabContainer(null);
        }
        this.e.n();
        DecorToolbar decorToolbar = this.e;
        boolean z3 = this.n;
        decorToolbar.t(false);
        ActionBarOverlayLayout actionBarOverlayLayout = this.c;
        boolean z4 = this.n;
        actionBarOverlayLayout.setHasNonEmbeddedTabs(false);
    }

    public final void C(boolean z2) {
        boolean z3 = this.r || !this.q;
        final ln3 ln3Var = this.y;
        View view = this.g;
        if (!z3) {
            if (this.s) {
                this.s = false;
                ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.t;
                if (viewPropertyAnimatorCompatSet != null) {
                    viewPropertyAnimatorCompatSet.a();
                }
                int i = this.o;
                jn3 jn3Var = this.w;
                if (i != 0 || (!this.u && !z2)) {
                    ((AnonymousClass1) jn3Var).a();
                    return;
                }
                this.d.setAlpha(1.0f);
                this.d.setTransitioning(true);
                ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet2 = new ViewPropertyAnimatorCompatSet();
                float f = -this.d.getHeight();
                if (z2) {
                    this.d.getLocationInWindow(new int[]{0, 0});
                    f -= r12[1];
                }
                in3 in3VarB = om3.b(this.d);
                in3VarB.e(f);
                final View view2 = in3VarB.a.get();
                if (view2 != null) {
                    view2.animate().setUpdateListener(ln3Var != null ? new ValueAnimator.AnimatorUpdateListener(view2) { // from class: gn3
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                            this.e.a();
                        }
                    } : null);
                }
                boolean z4 = viewPropertyAnimatorCompatSet2.e;
                ArrayList<in3> arrayList = viewPropertyAnimatorCompatSet2.a;
                if (!z4) {
                    arrayList.add(in3VarB);
                }
                if (this.p && view != null) {
                    in3 in3VarB2 = om3.b(view);
                    in3VarB2.e(f);
                    if (!viewPropertyAnimatorCompatSet2.e) {
                        arrayList.add(in3VarB2);
                    }
                }
                AccelerateInterpolator accelerateInterpolator = z;
                boolean z5 = viewPropertyAnimatorCompatSet2.e;
                if (!z5) {
                    viewPropertyAnimatorCompatSet2.c = accelerateInterpolator;
                }
                if (!z5) {
                    viewPropertyAnimatorCompatSet2.b = 250L;
                }
                kn3 kn3Var = (kn3) jn3Var;
                if (!z5) {
                    viewPropertyAnimatorCompatSet2.d = kn3Var;
                }
                this.t = viewPropertyAnimatorCompatSet2;
                viewPropertyAnimatorCompatSet2.b();
                return;
            }
            return;
        }
        if (this.s) {
            return;
        }
        this.s = true;
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet3 = this.t;
        if (viewPropertyAnimatorCompatSet3 != null) {
            viewPropertyAnimatorCompatSet3.a();
        }
        this.d.setVisibility(0);
        int i2 = this.o;
        jn3 jn3Var2 = this.x;
        if (i2 == 0 && (this.u || z2)) {
            this.d.setTranslationY(0.0f);
            float f2 = -this.d.getHeight();
            if (z2) {
                this.d.getLocationInWindow(new int[]{0, 0});
                f2 -= r12[1];
            }
            this.d.setTranslationY(f2);
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet4 = new ViewPropertyAnimatorCompatSet();
            in3 in3VarB3 = om3.b(this.d);
            in3VarB3.e(0.0f);
            final View view3 = in3VarB3.a.get();
            if (view3 != null) {
                view3.animate().setUpdateListener(ln3Var != null ? new ValueAnimator.AnimatorUpdateListener(view3) { // from class: gn3
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        this.e.a();
                    }
                } : null);
            }
            boolean z6 = viewPropertyAnimatorCompatSet4.e;
            ArrayList<in3> arrayList2 = viewPropertyAnimatorCompatSet4.a;
            if (!z6) {
                arrayList2.add(in3VarB3);
            }
            if (this.p && view != null) {
                view.setTranslationY(f2);
                in3 in3VarB4 = om3.b(view);
                in3VarB4.e(0.0f);
                if (!viewPropertyAnimatorCompatSet4.e) {
                    arrayList2.add(in3VarB4);
                }
            }
            DecelerateInterpolator decelerateInterpolator = A;
            boolean z7 = viewPropertyAnimatorCompatSet4.e;
            if (!z7) {
                viewPropertyAnimatorCompatSet4.c = decelerateInterpolator;
            }
            if (!z7) {
                viewPropertyAnimatorCompatSet4.b = 250L;
            }
            kn3 kn3Var2 = (kn3) jn3Var2;
            if (!z7) {
                viewPropertyAnimatorCompatSet4.d = kn3Var2;
            }
            this.t = viewPropertyAnimatorCompatSet4;
            viewPropertyAnimatorCompatSet4.b();
        } else {
            this.d.setAlpha(1.0f);
            this.d.setTranslationY(0.0f);
            if (this.p && view != null) {
                view.setTranslationY(0.0f);
            }
            ((AnonymousClass2) jn3Var2).a();
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.c;
        if (actionBarOverlayLayout != null) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.h.c(actionBarOverlayLayout);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public final void a() {
        if (this.q) {
            this.q = false;
            C(true);
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.m.add(onMenuVisibilityListener);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public final void c(boolean z2) {
        this.p = z2;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public final void d() {
        if (this.q) {
            return;
        }
        this.q = true;
        C(true);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public final void e() {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = this.t;
        if (viewPropertyAnimatorCompatSet != null) {
            viewPropertyAnimatorCompatSet.a();
            this.t = null;
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public final void f(int i) {
        this.o = i;
    }

    @Override // androidx.appcompat.app.ActionBar
    public final boolean h() {
        DecorToolbar decorToolbar = this.e;
        if (decorToolbar == null || !decorToolbar.j()) {
            return false;
        }
        this.e.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void i(boolean z2) {
        if (z2 == this.l) {
            return;
        }
        this.l = z2;
        ArrayList<ActionBar.OnMenuVisibilityListener> arrayList = this.m;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList.get(i).a();
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public final int j() {
        return this.e.p();
    }

    @Override // androidx.appcompat.app.ActionBar
    public final int k() {
        return this.d.getHeight();
    }

    @Override // androidx.appcompat.app.ActionBar
    public final Context l() {
        if (this.b == null) {
            TypedValue typedValue = new TypedValue();
            this.a.getTheme().resolveAttribute(androidx.appcompat.R.attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.b = new ContextThemeWrapper(this.a, i);
            } else {
                this.b = this.a;
            }
        }
        return this.b;
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void n() {
        B(this.a.getResources().getBoolean(androidx.appcompat.R.bool.abc_action_bar_embed_tabs));
    }

    @Override // androidx.appcompat.app.ActionBar
    public final boolean p(int i, KeyEvent keyEvent) {
        MenuBuilder menuBuilder;
        ActionModeImpl actionModeImpl = this.i;
        if (actionModeImpl == null || (menuBuilder = actionModeImpl.h) == null) {
            return false;
        }
        menuBuilder.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuBuilder.performShortcut(i, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.ActionBar
    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.m.remove(onMenuVisibilityListener);
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void s(boolean z2) {
        if (this.h) {
            return;
        }
        t(z2);
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void t(boolean z2) {
        int i = z2 ? 4 : 0;
        int iP = this.e.p();
        this.h = true;
        this.e.k((i & 4) | ((-5) & iP));
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void u(int i) {
        this.e.q(i);
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void v(boolean z2) {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet;
        this.u = z2;
        if (z2 || (viewPropertyAnimatorCompatSet = this.t) == null) {
            return;
        }
        viewPropertyAnimatorCompatSet.a();
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void w() {
        this.e.setTitle("");
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void x(CharSequence charSequence) {
        this.e.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.ActionBar
    public final ActionMode y(ActionMode.Callback callback) {
        ActionModeImpl actionModeImpl = this.i;
        if (actionModeImpl != null) {
            actionModeImpl.c();
        }
        this.c.setHideOnContentScrollEnabled(false);
        this.f.g();
        ActionModeImpl actionModeImpl2 = new ActionModeImpl(this.f.getContext(), callback);
        MenuBuilder menuBuilder = actionModeImpl2.h;
        menuBuilder.y();
        try {
            if (!actionModeImpl2.i.b(actionModeImpl2, menuBuilder)) {
                return null;
            }
            this.i = actionModeImpl2;
            actionModeImpl2.i();
            this.f.e(actionModeImpl2);
            z(true);
            return actionModeImpl2;
        } finally {
            menuBuilder.x();
        }
    }

    public final void z(boolean z2) {
        in3 in3VarH;
        in3 in3VarH2;
        if (z2) {
            if (!this.r) {
                this.r = true;
                ActionBarOverlayLayout actionBarOverlayLayout = this.c;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.setShowingForActionMode(true);
                }
                C(false);
            }
        } else if (this.r) {
            this.r = false;
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.c;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.setShowingForActionMode(false);
            }
            C(false);
        }
        ActionBarContainer actionBarContainer = this.d;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (!om3.g.c(actionBarContainer)) {
            if (z2) {
                this.e.setVisibility(4);
                this.f.setVisibility(0);
                return;
            } else {
                this.e.setVisibility(0);
                this.f.setVisibility(8);
                return;
            }
        }
        if (z2) {
            in3VarH = this.e.o(4, 100L);
            in3VarH2 = this.f.h(0, 200L);
        } else {
            in3 in3VarO = this.e.o(0, 200L);
            in3VarH = this.f.h(8, 100L);
            in3VarH2 = in3VarO;
        }
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
        ArrayList<in3> arrayList = viewPropertyAnimatorCompatSet.a;
        arrayList.add(in3VarH);
        View view = in3VarH.a.get();
        long duration = view != null ? view.animate().getDuration() : 0L;
        View view2 = in3VarH2.a.get();
        if (view2 != null) {
            view2.animate().setStartDelay(duration);
        }
        arrayList.add(in3VarH2);
        viewPropertyAnimatorCompatSet.b();
    }

    public WindowDecorActionBar(Dialog dialog) {
        new ArrayList();
        this.m = new ArrayList<>();
        this.o = 0;
        this.p = true;
        this.s = true;
        this.w = new AnonymousClass1();
        this.x = new AnonymousClass2();
        this.y = new ln3() { // from class: androidx.appcompat.app.WindowDecorActionBar.3
            @Override // defpackage.ln3
            public final void a() {
                ((View) WindowDecorActionBar.this.d.getParent()).invalidate();
            }
        };
        A(dialog.getWindow().getDecorView());
    }

    public class TabImpl extends ActionBar.Tab {
        @Override // androidx.appcompat.app.ActionBar.Tab
        public final void e() {
            throw null;
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public final void a() {
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public final void b() {
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public final void c() {
        }

        @Override // androidx.appcompat.app.ActionBar.Tab
        public final void d() {
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback
    public final void b() {
    }
}
