package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import b.g.k.t;
import c.a.a.a.k;
import c.a.a.a.l;
import c.a.a.a.w.m;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.b {
    private static final int s = k.z;
    static final Property<View, Float> t = new d(Float.class, "width");
    static final Property<View, Float> u = new e(Float.class, "height");
    private final com.google.android.material.floatingactionbutton.f A;
    private final CoordinatorLayout.c<ExtendedFloatingActionButton> B;
    private boolean C;
    private int v;
    private final com.google.android.material.floatingactionbutton.a w;
    private final com.google.android.material.floatingactionbutton.f x;
    private final com.google.android.material.floatingactionbutton.f y;
    private final com.google.android.material.floatingactionbutton.f z;

    class a implements j {
        a() {
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public int a() {
            return ExtendedFloatingActionButton.this.getMeasuredWidth();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public int b() {
            return ExtendedFloatingActionButton.this.getMeasuredHeight();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public ViewGroup.LayoutParams c() {
            return new ViewGroup.LayoutParams(-2, -2);
        }
    }

    class b implements j {
        b() {
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public int a() {
            return ExtendedFloatingActionButton.this.getCollapsedSize();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public int b() {
            return ExtendedFloatingActionButton.this.getCollapsedSize();
        }

        @Override // com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.j
        public ViewGroup.LayoutParams c() {
            return new ViewGroup.LayoutParams(a(), b());
        }
    }

    class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f2356a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ com.google.android.material.floatingactionbutton.f f2357b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ h f2358c;

        c(com.google.android.material.floatingactionbutton.f fVar, h hVar) {
            this.f2357b = fVar;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f2356a = true;
            this.f2357b.d();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f2357b.c();
            if (this.f2356a) {
                return;
            }
            this.f2357b.f(this.f2358c);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f2357b.onAnimationStart(animator);
            this.f2356a = false;
        }
    }

    static class d extends Property<View, Float> {
        d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(view.getLayoutParams().width);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f) {
            view.getLayoutParams().width = f.intValue();
            view.requestLayout();
        }
    }

    static class e extends Property<View, Float> {
        e(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(view.getLayoutParams().height);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f) {
            view.getLayoutParams().height = f.intValue();
            view.requestLayout();
        }
    }

    class f extends com.google.android.material.floatingactionbutton.b {
        private final j g;
        private final boolean h;

        f(com.google.android.material.floatingactionbutton.a aVar, j jVar, boolean z) {
            super(ExtendedFloatingActionButton.this, aVar);
            this.g = jVar;
            this.h = z;
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public AnimatorSet b() {
            c.a.a.a.m.h hVarL = l();
            if (hVarL.j("width")) {
                PropertyValuesHolder[] propertyValuesHolderArrG = hVarL.g("width");
                propertyValuesHolderArrG[0].setFloatValues(ExtendedFloatingActionButton.this.getWidth(), this.g.a());
                hVarL.l("width", propertyValuesHolderArrG);
            }
            if (hVarL.j("height")) {
                PropertyValuesHolder[] propertyValuesHolderArrG2 = hVarL.g("height");
                propertyValuesHolderArrG2[0].setFloatValues(ExtendedFloatingActionButton.this.getHeight(), this.g.b());
                hVarL.l("height", propertyValuesHolderArrG2);
            }
            return super.k(hVarL);
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public void c() {
            super.c();
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(false);
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            layoutParams.width = this.g.c().width;
            layoutParams.height = this.g.c().height;
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public void f(h hVar) {
            if (hVar == null) {
                return;
            }
            if (!this.h) {
                throw null;
            }
            throw null;
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public boolean g() {
            return this.h == ExtendedFloatingActionButton.this.C || ExtendedFloatingActionButton.this.getIcon() == null || TextUtils.isEmpty(ExtendedFloatingActionButton.this.getText());
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public int i() {
            return c.a.a.a.a.f1969c;
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public void j() {
            ExtendedFloatingActionButton.this.C = this.h;
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams == null) {
                return;
            }
            layoutParams.width = this.g.c().width;
            layoutParams.height = this.g.c().height;
            ExtendedFloatingActionButton.this.requestLayout();
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.C = this.h;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(true);
        }
    }

    class g extends com.google.android.material.floatingactionbutton.b {
        private boolean g;

        public g(com.google.android.material.floatingactionbutton.a aVar) {
            super(ExtendedFloatingActionButton.this, aVar);
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public void c() {
            super.c();
            ExtendedFloatingActionButton.this.v = 0;
            if (this.g) {
                return;
            }
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public void d() {
            super.d();
            this.g = true;
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public void f(h hVar) {
            if (hVar != null) {
                throw null;
            }
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public boolean g() {
            return ExtendedFloatingActionButton.this.s();
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public int i() {
            return c.a.a.a.a.f1970d;
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public void j() {
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            this.g = false;
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.v = 1;
        }
    }

    public static abstract class h {
    }

    class i extends com.google.android.material.floatingactionbutton.b {
        public i(com.google.android.material.floatingactionbutton.a aVar) {
            super(ExtendedFloatingActionButton.this, aVar);
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public void c() {
            super.c();
            ExtendedFloatingActionButton.this.v = 0;
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public void f(h hVar) {
            if (hVar != null) {
                throw null;
            }
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public boolean g() {
            return ExtendedFloatingActionButton.this.t();
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public int i() {
            return c.a.a.a.a.e;
        }

        @Override // com.google.android.material.floatingactionbutton.f
        public void j() {
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.setAlpha(1.0f);
            ExtendedFloatingActionButton.this.setScaleY(1.0f);
            ExtendedFloatingActionButton.this.setScaleX(1.0f);
        }

        @Override // com.google.android.material.floatingactionbutton.b, com.google.android.material.floatingactionbutton.f
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.v = 2;
        }
    }

    interface j {
        int a();

        int b();

        ViewGroup.LayoutParams c();
    }

    public ExtendedFloatingActionButton(Context context) {
        this(context, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean s() {
        return getVisibility() == 0 ? this.v == 1 : this.v != 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean t() {
        return getVisibility() != 0 ? this.v == 2 : this.v != 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(com.google.android.material.floatingactionbutton.f fVar, h hVar) {
        if (fVar.g()) {
            return;
        }
        if (!v()) {
            fVar.j();
            fVar.f(hVar);
            return;
        }
        measure(0, 0);
        AnimatorSet animatorSetB = fVar.b();
        animatorSetB.addListener(new c(fVar, hVar));
        Iterator<Animator.AnimatorListener> it = fVar.e().iterator();
        while (it.hasNext()) {
            animatorSetB.addListener(it.next());
        }
        animatorSetB.start();
    }

    private boolean v() {
        return t.P(this) && !isInEditMode();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.b
    public CoordinatorLayout.c<ExtendedFloatingActionButton> getBehavior() {
        return this.B;
    }

    int getCollapsedSize() {
        return (Math.min(t.E(this), t.D(this)) * 2) + getIconSize();
    }

    public c.a.a.a.m.h getExtendMotionSpec() {
        return this.y.a();
    }

    public c.a.a.a.m.h getHideMotionSpec() {
        return this.A.a();
    }

    public c.a.a.a.m.h getShowMotionSpec() {
        return this.z.a();
    }

    public c.a.a.a.m.h getShrinkMotionSpec() {
        return this.x.a();
    }

    @Override // com.google.android.material.button.MaterialButton, android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.C && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.C = false;
            this.x.j();
        }
    }

    public void setExtendMotionSpec(c.a.a.a.m.h hVar) {
        this.y.h(hVar);
    }

    public void setExtendMotionSpecResource(int i2) {
        setExtendMotionSpec(c.a.a.a.m.h.d(getContext(), i2));
    }

    public void setExtended(boolean z) {
        if (this.C == z) {
            return;
        }
        com.google.android.material.floatingactionbutton.f fVar = z ? this.y : this.x;
        if (fVar.g()) {
            return;
        }
        fVar.j();
    }

    public void setHideMotionSpec(c.a.a.a.m.h hVar) {
        this.A.h(hVar);
    }

    public void setHideMotionSpecResource(int i2) {
        setHideMotionSpec(c.a.a.a.m.h.d(getContext(), i2));
    }

    public void setShowMotionSpec(c.a.a.a.m.h hVar) {
        this.z.h(hVar);
    }

    public void setShowMotionSpecResource(int i2) {
        setShowMotionSpec(c.a.a.a.m.h.d(getContext(), i2));
    }

    public void setShrinkMotionSpec(c.a.a.a.m.h hVar) {
        this.x.h(hVar);
    }

    public void setShrinkMotionSpecResource(int i2) {
        setShrinkMotionSpec(c.a.a.a.m.h.d(getContext(), i2));
    }

    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, c.a.a.a.b.t);
    }

    protected static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.c<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Rect f2350a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private h f2351b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private h f2352c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f2353d;
        private boolean e;

        public ExtendedFloatingActionButtonBehavior() {
            this.f2353d = false;
            this.e = true;
        }

        private static boolean G(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.f) {
                return ((CoordinatorLayout.f) layoutParams).f() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private boolean J(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            return (this.f2353d || this.e) && ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams()).e() == view.getId();
        }

        private boolean L(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!J(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.f2350a == null) {
                this.f2350a = new Rect();
            }
            Rect rect = this.f2350a;
            com.google.android.material.internal.c.a(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                K(extendedFloatingActionButton);
                return true;
            }
            E(extendedFloatingActionButton);
            return true;
        }

        private boolean M(View view, ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!J(view, extendedFloatingActionButton)) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.f) extendedFloatingActionButton.getLayoutParams())).topMargin) {
                K(extendedFloatingActionButton);
                return true;
            }
            E(extendedFloatingActionButton);
            return true;
        }

        protected void E(ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z = this.e;
            extendedFloatingActionButton.u(z ? extendedFloatingActionButton.y : extendedFloatingActionButton.z, z ? this.f2352c : this.f2351b);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: F, reason: merged with bridge method [inline-methods] */
        public boolean b(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, Rect rect) {
            return super.b(coordinatorLayout, extendedFloatingActionButton, rect);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: H, reason: merged with bridge method [inline-methods] */
        public boolean h(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                L(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton);
                return false;
            }
            if (!G(view)) {
                return false;
            }
            M(view, extendedFloatingActionButton);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        /* JADX INFO: renamed from: I, reason: merged with bridge method [inline-methods] */
        public boolean l(CoordinatorLayout coordinatorLayout, ExtendedFloatingActionButton extendedFloatingActionButton, int i) {
            List<View> listV = coordinatorLayout.v(extendedFloatingActionButton);
            int size = listV.size();
            for (int i2 = 0; i2 < size; i2++) {
                View view = listV.get(i2);
                if (!(view instanceof AppBarLayout)) {
                    if (G(view) && M(view, extendedFloatingActionButton)) {
                        break;
                    }
                } else {
                    if (L(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton)) {
                        break;
                    }
                }
            }
            coordinatorLayout.M(extendedFloatingActionButton, i);
            return true;
        }

        protected void K(ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z = this.e;
            extendedFloatingActionButton.u(z ? extendedFloatingActionButton.x : extendedFloatingActionButton.A, z ? this.f2352c : this.f2351b);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.c
        public void g(CoordinatorLayout.f fVar) {
            if (fVar.h == 0) {
                fVar.h = 80;
            }
        }

        public ExtendedFloatingActionButtonBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, l.b2);
            this.f2353d = typedArrayObtainStyledAttributes.getBoolean(l.c2, false);
            this.e = typedArrayObtainStyledAttributes.getBoolean(l.d2, true);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ExtendedFloatingActionButton(Context context, AttributeSet attributeSet, int i2) {
        int i3 = s;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.v = 0;
        com.google.android.material.floatingactionbutton.a aVar = new com.google.android.material.floatingactionbutton.a();
        this.w = aVar;
        i iVar = new i(aVar);
        this.z = iVar;
        g gVar = new g(aVar);
        this.A = gVar;
        this.C = true;
        Context context2 = getContext();
        this.B = new ExtendedFloatingActionButtonBehavior(context2, attributeSet);
        TypedArray typedArrayH = com.google.android.material.internal.k.h(context2, attributeSet, l.W1, i2, i3, new int[0]);
        c.a.a.a.m.h hVarC = c.a.a.a.m.h.c(context2, typedArrayH, l.Z1);
        c.a.a.a.m.h hVarC2 = c.a.a.a.m.h.c(context2, typedArrayH, l.Y1);
        c.a.a.a.m.h hVarC3 = c.a.a.a.m.h.c(context2, typedArrayH, l.X1);
        c.a.a.a.m.h hVarC4 = c.a.a.a.m.h.c(context2, typedArrayH, l.a2);
        com.google.android.material.floatingactionbutton.a aVar2 = new com.google.android.material.floatingactionbutton.a();
        f fVar = new f(aVar2, new a(), true);
        this.y = fVar;
        f fVar2 = new f(aVar2, new b(), false);
        this.x = fVar2;
        iVar.h(hVarC);
        gVar.h(hVarC2);
        fVar.h(hVarC3);
        fVar2.h(hVarC4);
        typedArrayH.recycle();
        setShapeAppearanceModel(m.g(context2, attributeSet, i2, i3, m.f2081a).m());
    }
}
