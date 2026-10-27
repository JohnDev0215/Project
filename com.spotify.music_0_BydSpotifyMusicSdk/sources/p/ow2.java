package p;

import android.R;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ow2 extends zu implements w3 {
    public static final AccelerateInterpolator G = new AccelerateInterpolator();
    public static final DecelerateInterpolator H = new DecelerateInterpolator();
    public vu2 A;
    public boolean B;
    public boolean C;
    public final mw2 D;
    public final mw2 E;
    public final oh1 F;
    public Context i;
    public Context j;
    public ActionBarOverlayLayout k;
    public ActionBarContainer l;
    public j60 m;
    public ActionBarContextView n;
    public final View o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f164p;
    public nw2 q;
    public nw2 r;
    public k4 s;
    public boolean t;
    public final ArrayList u;
    public int v;
    public boolean w;
    public boolean x;
    public boolean y;
    public boolean z;

    public ow2(Activity activity, boolean z) {
        new ArrayList();
        this.u = new ArrayList();
        this.v = 0;
        int i = 1;
        this.w = true;
        this.z = true;
        this.D = new mw2(this, 0);
        this.E = new mw2(this, i);
        this.F = new oh1(i, this);
        View decorView = activity.getWindow().getDecorView();
        J0(decorView);
        if (z) {
            return;
        }
        this.o = decorView.findViewById(R.id.content);
    }

    public final void H0(boolean z) {
        tu2 tu2VarL;
        tu2 tu2VarL2;
        if (z) {
            if (!this.y) {
                this.y = true;
                ActionBarOverlayLayout actionBarOverlayLayout = this.k;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.setShowingForActionMode(true);
                }
                N0(false);
            }
        } else if (this.y) {
            this.y = false;
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.k;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.setShowingForActionMode(false);
            }
            N0(false);
        }
        ActionBarContainer actionBarContainer = this.l;
        WeakHashMap weakHashMap = zt2.a;
        if (!lt2.c(actionBarContainer)) {
            if (z) {
                ((hm2) this.m).a.setVisibility(4);
                this.n.setVisibility(0);
                return;
            } else {
                ((hm2) this.m).a.setVisibility(0);
                this.n.setVisibility(8);
                return;
            }
        }
        if (z) {
            hm2 hm2Var = (hm2) this.m;
            tu2VarL = zt2.a(hm2Var.a);
            tu2VarL.a(0.0f);
            tu2VarL.c(100L);
            tu2VarL.d(new uu2(hm2Var, 4));
            tu2VarL2 = this.n.l(0, 200L);
        } else {
            hm2 hm2Var2 = (hm2) this.m;
            tu2 tu2VarA = zt2.a(hm2Var2.a);
            tu2VarA.a(1.0f);
            tu2VarA.c(200L);
            tu2VarA.d(new uu2(hm2Var2, 0));
            tu2VarL = this.n.l(8, 100L);
            tu2VarL2 = tu2VarA;
        }
        vu2 vu2Var = new vu2();
        ArrayList arrayList = vu2Var.a;
        arrayList.add(tu2VarL);
        View view = (View) tu2VarL.a.get();
        long duration = view != null ? view.animate().getDuration() : 0L;
        View view2 = (View) tu2VarL2.a.get();
        if (view2 != null) {
            view2.animate().setStartDelay(duration);
        }
        arrayList.add(tu2VarL2);
        vu2Var.b();
    }

    public final Context I0() {
        if (this.j == null) {
            TypedValue typedValue = new TypedValue();
            this.i.getTheme().resolveAttribute(com.spotify.music.R.attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.j = new ContextThemeWrapper(this.i, i);
            } else {
                this.j = this.i;
            }
        }
        return this.j;
    }

    public final void J0(View view) {
        j60 wrapper;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(com.spotify.music.R.id.decor_content_parent);
        this.k = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        KeyEvent.Callback callbackFindViewById = view.findViewById(com.spotify.music.R.id.action_bar);
        if (callbackFindViewById instanceof j60) {
            wrapper = (j60) callbackFindViewById;
        } else {
            if (!(callbackFindViewById instanceof Toolbar)) {
                throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById != null ? callbackFindViewById.getClass().getSimpleName() : "null"));
            }
            wrapper = ((Toolbar) callbackFindViewById).getWrapper();
        }
        this.m = wrapper;
        this.n = (ActionBarContextView) view.findViewById(com.spotify.music.R.id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(com.spotify.music.R.id.action_bar_container);
        this.l = actionBarContainer;
        j60 j60Var = this.m;
        if (j60Var == null || this.n == null || actionBarContainer == null) {
            throw new IllegalStateException(ow2.class.getSimpleName().concat(" can only be used with a compatible window decor layout"));
        }
        Context context = ((hm2) j60Var).a.getContext();
        this.i = context;
        if ((((hm2) this.m).b & 4) != 0) {
            this.f164p = true;
        }
        int i = context.getApplicationInfo().targetSdkVersion;
        this.m.getClass();
        L0(context.getResources().getBoolean(com.spotify.music.R.bool.abc_action_bar_embed_tabs));
        TypedArray typedArrayObtainStyledAttributes = this.i.obtainStyledAttributes(null, yz1.a, com.spotify.music.R.attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(14, false)) {
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.k;
            if (!actionBarOverlayLayout2.f8p) {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
            this.C = true;
            actionBarOverlayLayout2.setHideOnContentScrollEnabled(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, 0);
        if (dimensionPixelSize != 0) {
            ActionBarContainer actionBarContainer2 = this.l;
            WeakHashMap weakHashMap = zt2.a;
            ot2.s(actionBarContainer2, dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void K0(boolean z) {
        if (this.f164p) {
            return;
        }
        int i = z ? 4 : 0;
        hm2 hm2Var = (hm2) this.m;
        int i2 = hm2Var.b;
        this.f164p = true;
        hm2Var.a((i & 4) | (i2 & (-5)));
    }

    public final void L0(boolean z) {
        if (z) {
            this.l.setTabContainer(null);
            ((hm2) this.m).getClass();
        } else {
            ((hm2) this.m).getClass();
            this.l.setTabContainer(null);
        }
        this.m.getClass();
        ((hm2) this.m).a.setCollapsible(false);
        this.k.setHasNonEmbeddedTabs(false);
    }

    public final void M0(CharSequence charSequence) {
        hm2 hm2Var = (hm2) this.m;
        if (hm2Var.g) {
            return;
        }
        hm2Var.h = charSequence;
        if ((hm2Var.b & 8) != 0) {
            Toolbar toolbar = hm2Var.a;
            toolbar.setTitle(charSequence);
            if (hm2Var.g) {
                zt2.l(toolbar.getRootView(), charSequence);
            }
        }
    }

    public final void N0(boolean z) {
        boolean z2 = this.y || !this.x;
        final oh1 oh1Var = this.F;
        View view = this.o;
        if (!z2) {
            if (this.z) {
                this.z = false;
                vu2 vu2Var = this.A;
                if (vu2Var != null) {
                    vu2Var.a();
                }
                int i = this.v;
                mw2 mw2Var = this.D;
                if (i != 0 || (!this.B && !z)) {
                    mw2Var.a();
                    return;
                }
                this.l.setAlpha(1.0f);
                this.l.setTransitioning(true);
                vu2 vu2Var2 = new vu2();
                float f = -this.l.getHeight();
                if (z) {
                    this.l.getLocationInWindow(new int[]{0, 0});
                    f -= r12[1];
                }
                tu2 tu2VarA = zt2.a(this.l);
                tu2VarA.e(f);
                final View view2 = (View) tu2VarA.a.get();
                if (view2 != null) {
                    su2.a(view2.animate(), oh1Var != null ? new ValueAnimator.AnimatorUpdateListener(view2) { // from class: p.qu2
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                            ((View) ((ow2) this.a.e).l.getParent()).invalidate();
                        }
                    } : null);
                }
                boolean z3 = vu2Var2.e;
                ArrayList arrayList = vu2Var2.a;
                if (!z3) {
                    arrayList.add(tu2VarA);
                }
                if (this.w && view != null) {
                    tu2 tu2VarA2 = zt2.a(view);
                    tu2VarA2.e(f);
                    if (!vu2Var2.e) {
                        arrayList.add(tu2VarA2);
                    }
                }
                AccelerateInterpolator accelerateInterpolator = G;
                boolean z4 = vu2Var2.e;
                if (!z4) {
                    vu2Var2.c = accelerateInterpolator;
                }
                if (!z4) {
                    vu2Var2.b = 250L;
                }
                if (!z4) {
                    vu2Var2.d = mw2Var;
                }
                this.A = vu2Var2;
                vu2Var2.b();
                return;
            }
            return;
        }
        if (this.z) {
            return;
        }
        this.z = true;
        vu2 vu2Var3 = this.A;
        if (vu2Var3 != null) {
            vu2Var3.a();
        }
        this.l.setVisibility(0);
        int i2 = this.v;
        mw2 mw2Var2 = this.E;
        if (i2 == 0 && (this.B || z)) {
            this.l.setTranslationY(0.0f);
            float f2 = -this.l.getHeight();
            if (z) {
                this.l.getLocationInWindow(new int[]{0, 0});
                f2 -= r12[1];
            }
            this.l.setTranslationY(f2);
            vu2 vu2Var4 = new vu2();
            tu2 tu2VarA3 = zt2.a(this.l);
            tu2VarA3.e(0.0f);
            final View view3 = (View) tu2VarA3.a.get();
            if (view3 != null) {
                su2.a(view3.animate(), oh1Var != null ? new ValueAnimator.AnimatorUpdateListener(view3) { // from class: p.qu2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        ((View) ((ow2) this.a.e).l.getParent()).invalidate();
                    }
                } : null);
            }
            boolean z5 = vu2Var4.e;
            ArrayList arrayList2 = vu2Var4.a;
            if (!z5) {
                arrayList2.add(tu2VarA3);
            }
            if (this.w && view != null) {
                view.setTranslationY(f2);
                tu2 tu2VarA4 = zt2.a(view);
                tu2VarA4.e(0.0f);
                if (!vu2Var4.e) {
                    arrayList2.add(tu2VarA4);
                }
            }
            DecelerateInterpolator decelerateInterpolator = H;
            boolean z6 = vu2Var4.e;
            if (!z6) {
                vu2Var4.c = decelerateInterpolator;
            }
            if (!z6) {
                vu2Var4.b = 250L;
            }
            if (!z6) {
                vu2Var4.d = mw2Var2;
            }
            this.A = vu2Var4;
            vu2Var4.b();
        } else {
            this.l.setAlpha(1.0f);
            this.l.setTranslationY(0.0f);
            if (this.w && view != null) {
                view.setTranslationY(0.0f);
            }
            mw2Var2.a();
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.k;
        if (actionBarOverlayLayout != null) {
            WeakHashMap weakHashMap = zt2.a;
            mt2.c(actionBarOverlayLayout);
        }
    }

    public ow2(Dialog dialog) {
        new ArrayList();
        this.u = new ArrayList();
        this.v = 0;
        int i = 1;
        this.w = true;
        this.z = true;
        this.D = new mw2(this, 0);
        this.E = new mw2(this, i);
        this.F = new oh1(i, this);
        J0(dialog.getWindow().getDecorView());
    }
}
