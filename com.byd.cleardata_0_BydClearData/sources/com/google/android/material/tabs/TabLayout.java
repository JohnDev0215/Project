package com.google.android.material.tabs;

import a.g.k.c0.c;
import a.g.k.r;
import a.g.k.t;
import a.p.a.b;
import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.x0;
import b.a.a.a.k;
import b.a.a.a.l;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
@b.c
public class TabLayout extends HorizontalScrollView {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f3177b = k.h;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final a.g.j.e<g> f3178c = new a.g.j.g(16);
    int A;
    int B;
    int C;
    boolean D;
    boolean E;
    boolean F;
    private c G;
    private final ArrayList<c> H;
    private c I;
    private ValueAnimator J;
    a.p.a.b K;
    private a.p.a.a L;
    private DataSetObserver M;
    private h N;
    private b O;
    private boolean P;
    private final a.g.j.e<i> Q;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final ArrayList<g> f3179d;
    private g e;
    private final RectF f;
    final f g;
    int h;
    int i;
    int j;
    int k;
    int l;
    ColorStateList m;
    ColorStateList n;
    ColorStateList o;
    Drawable p;
    PorterDuff.Mode q;
    float r;
    float s;
    final int t;
    int u;
    private final int v;
    private final int w;
    private final int x;
    private int y;
    int z;

    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            TabLayout.this.scrollTo(((Integer) valueAnimator.getAnimatedValue()).intValue(), 0);
        }
    }

    private class b implements b.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f3181a;

        b() {
        }

        @Override // a.p.a.b.f
        public void a(a.p.a.b bVar, a.p.a.a aVar, a.p.a.a aVar2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.K == bVar) {
                tabLayout.F(aVar2, this.f3181a);
            }
        }

        void b(boolean z) {
            this.f3181a = z;
        }
    }

    @Deprecated
    public interface c<T extends g> {
        void a(T t);

        void b(T t);

        void c(T t);
    }

    public interface d extends c<g> {
    }

    private class e extends DataSetObserver {
        e() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            TabLayout.this.y();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            TabLayout.this.y();
        }
    }

    class f extends LinearLayout {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3184b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Paint f3185c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final GradientDrawable f3186d;
        int e;
        float f;
        private int g;
        int h;
        int i;
        ValueAnimator j;
        private int k;
        private int l;

        class a implements ValueAnimator.AnimatorUpdateListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f3187a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f3188b;

            a(int i, int i2) {
                this.f3187a = i;
                this.f3188b = i2;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedFraction = valueAnimator.getAnimatedFraction();
                f fVar = f.this;
                fVar.f(b.a.a.a.m.a.b(fVar.k, this.f3187a, animatedFraction), b.a.a.a.m.a.b(f.this.l, this.f3188b, animatedFraction));
            }
        }

        class b extends AnimatorListenerAdapter {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f3190a;

            b(int i) {
                this.f3190a = i;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                f fVar = f.this;
                fVar.e = this.f3190a;
                fVar.f = 0.0f;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                f.this.e = this.f3190a;
            }
        }

        f(Context context) {
            super(context);
            this.e = -1;
            this.g = -1;
            this.h = -1;
            this.i = -1;
            this.k = -1;
            this.l = -1;
            setWillNotDraw(false);
            this.f3185c = new Paint();
            this.f3186d = new GradientDrawable();
        }

        private void d(i iVar, RectF rectF) {
            int contentWidth = iVar.getContentWidth();
            int iB = (int) com.google.android.material.internal.j.b(getContext(), 24);
            if (contentWidth < iB) {
                contentWidth = iB;
            }
            int left = (iVar.getLeft() + iVar.getRight()) / 2;
            int i = contentWidth / 2;
            rectF.set(left - i, 0.0f, left + i, 0.0f);
        }

        private void j() {
            int left;
            int right;
            View childAt = getChildAt(this.e);
            if (childAt == null || childAt.getWidth() <= 0) {
                left = -1;
                right = -1;
            } else {
                left = childAt.getLeft();
                right = childAt.getRight();
                TabLayout tabLayout = TabLayout.this;
                if (!tabLayout.E && (childAt instanceof i)) {
                    d((i) childAt, tabLayout.f);
                    left = (int) TabLayout.this.f.left;
                    right = (int) TabLayout.this.f.right;
                }
                if (this.f > 0.0f && this.e < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.e + 1);
                    int left2 = childAt2.getLeft();
                    int right2 = childAt2.getRight();
                    TabLayout tabLayout2 = TabLayout.this;
                    if (!tabLayout2.E && (childAt2 instanceof i)) {
                        d((i) childAt2, tabLayout2.f);
                        left2 = (int) TabLayout.this.f.left;
                        right2 = (int) TabLayout.this.f.right;
                    }
                    float f = this.f;
                    left = (int) ((left2 * f) + ((1.0f - f) * left));
                    right = (int) ((right2 * f) + ((1.0f - f) * right));
                }
            }
            f(left, right);
        }

        private void k(boolean z, int i, int i2) {
            View childAt = getChildAt(i);
            if (childAt == null) {
                j();
                return;
            }
            int left = childAt.getLeft();
            int right = childAt.getRight();
            TabLayout tabLayout = TabLayout.this;
            if (!tabLayout.E && (childAt instanceof i)) {
                d((i) childAt, tabLayout.f);
                left = (int) TabLayout.this.f.left;
                right = (int) TabLayout.this.f.right;
            }
            int i3 = this.h;
            int i4 = this.i;
            if (i3 == left && i4 == right) {
                return;
            }
            if (z) {
                this.k = i3;
                this.l = i4;
            }
            a aVar = new a(left, right);
            if (!z) {
                this.j.removeAllUpdateListeners();
                this.j.addUpdateListener(aVar);
                return;
            }
            ValueAnimator valueAnimator = new ValueAnimator();
            this.j = valueAnimator;
            valueAnimator.setInterpolator(b.a.a.a.m.a.f1733b);
            valueAnimator.setDuration(i2);
            valueAnimator.setFloatValues(0.0f, 1.0f);
            valueAnimator.addUpdateListener(aVar);
            valueAnimator.addListener(new b(i));
            valueAnimator.start();
        }

        void c(int i, int i2) {
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.j.cancel();
            }
            k(true, i, i2);
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x0049  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x005e  */
        @Override // android.view.View
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void draw(android.graphics.Canvas r6) {
            /*
                r5 = this;
                com.google.android.material.tabs.TabLayout r0 = com.google.android.material.tabs.TabLayout.this
                android.graphics.drawable.Drawable r0 = r0.p
                r1 = 0
                if (r0 == 0) goto Lc
                int r0 = r0.getIntrinsicHeight()
                goto Ld
            Lc:
                r0 = r1
            Ld:
                int r2 = r5.f3184b
                if (r2 < 0) goto L12
                r0 = r2
            L12:
                com.google.android.material.tabs.TabLayout r2 = com.google.android.material.tabs.TabLayout.this
                int r2 = r2.B
                if (r2 == 0) goto L31
                r3 = 1
                r4 = 2
                if (r2 == r3) goto L23
                if (r2 == r4) goto L3a
                r0 = 3
                if (r2 == r0) goto L36
                r0 = r1
                goto L3a
            L23:
                int r1 = r5.getHeight()
                int r1 = r1 - r0
                int r1 = r1 / r4
                int r2 = r5.getHeight()
                int r2 = r2 + r0
                int r0 = r2 / 2
                goto L3a
            L31:
                int r1 = r5.getHeight()
                int r1 = r1 - r0
            L36:
                int r0 = r5.getHeight()
            L3a:
                int r2 = r5.h
                if (r2 < 0) goto L74
                int r3 = r5.i
                if (r3 <= r2) goto L74
                com.google.android.material.tabs.TabLayout r2 = com.google.android.material.tabs.TabLayout.this
                android.graphics.drawable.Drawable r2 = r2.p
                if (r2 == 0) goto L49
                goto L4b
            L49:
                android.graphics.drawable.GradientDrawable r2 = r5.f3186d
            L4b:
                android.graphics.drawable.Drawable r2 = androidx.core.graphics.drawable.a.r(r2)
                android.graphics.drawable.Drawable r2 = r2.mutate()
                int r3 = r5.h
                int r4 = r5.i
                r2.setBounds(r3, r1, r4, r0)
                android.graphics.Paint r0 = r5.f3185c
                if (r0 == 0) goto L71
                int r1 = android.os.Build.VERSION.SDK_INT
                r3 = 21
                int r0 = r0.getColor()
                if (r1 != r3) goto L6e
                android.graphics.PorterDuff$Mode r1 = android.graphics.PorterDuff.Mode.SRC_IN
                r2.setColorFilter(r0, r1)
                goto L71
            L6e:
                androidx.core.graphics.drawable.a.n(r2, r0)
            L71:
                r2.draw(r6)
            L74:
                super.draw(r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.f.draw(android.graphics.Canvas):void");
        }

        boolean e() {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (getChildAt(i).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        void f(int i, int i2) {
            if (i == this.h && i2 == this.i) {
                return;
            }
            this.h = i;
            this.i = i2;
            t.Y(this);
        }

        void g(int i, float f) {
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.j.cancel();
            }
            this.e = i;
            this.f = f;
            j();
        }

        void h(int i) {
            if (this.f3185c.getColor() != i) {
                this.f3185c.setColor(i);
                t.Y(this);
            }
        }

        void i(int i) {
            if (this.f3184b != i) {
                this.f3184b = i;
                t.Y(this);
            }
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            ValueAnimator valueAnimator = this.j;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                j();
            } else {
                k(false, this.e, -1);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            if (View.MeasureSpec.getMode(i) != 1073741824) {
                return;
            }
            TabLayout tabLayout = TabLayout.this;
            boolean z = true;
            if (tabLayout.z == 1 || tabLayout.C == 2) {
                int childCount = getChildCount();
                int iMax = 0;
                for (int i3 = 0; i3 < childCount; i3++) {
                    View childAt = getChildAt(i3);
                    if (childAt.getVisibility() == 0) {
                        iMax = Math.max(iMax, childAt.getMeasuredWidth());
                    }
                }
                if (iMax <= 0) {
                    return;
                }
                if (iMax * childCount <= getMeasuredWidth() - (((int) com.google.android.material.internal.j.b(getContext(), 16)) * 2)) {
                    boolean z2 = false;
                    for (int i4 = 0; i4 < childCount; i4++) {
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getChildAt(i4).getLayoutParams();
                        if (layoutParams.width != iMax || layoutParams.weight != 0.0f) {
                            layoutParams.width = iMax;
                            layoutParams.weight = 0.0f;
                            z2 = true;
                        }
                    }
                    z = z2;
                } else {
                    TabLayout tabLayout2 = TabLayout.this;
                    tabLayout2.z = 0;
                    tabLayout2.M(false);
                }
                if (z) {
                    super.onMeasure(i, i2);
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i) {
            super.onRtlPropertiesChanged(i);
            if (Build.VERSION.SDK_INT >= 23 || this.g == i) {
                return;
            }
            requestLayout();
            this.g = i;
        }
    }

    public static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Object f3192a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Drawable f3193b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private CharSequence f3194c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private CharSequence f3195d;
        private View f;
        public TabLayout h;
        public i i;
        private int e = -1;
        private int g = 1;

        public View d() {
            return this.f;
        }

        public Drawable e() {
            return this.f3193b;
        }

        public int f() {
            return this.e;
        }

        public int g() {
            return this.g;
        }

        public CharSequence h() {
            return this.f3194c;
        }

        public boolean i() {
            TabLayout tabLayout = this.h;
            if (tabLayout != null) {
                return tabLayout.getSelectedTabPosition() == this.e;
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        void j() {
            this.h = null;
            this.i = null;
            this.f3192a = null;
            this.f3193b = null;
            this.f3194c = null;
            this.f3195d = null;
            this.e = -1;
            this.f = null;
        }

        public void k() {
            TabLayout tabLayout = this.h;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.D(this);
        }

        public g l(CharSequence charSequence) {
            this.f3195d = charSequence;
            r();
            return this;
        }

        public g m(int i) {
            return n(LayoutInflater.from(this.i.getContext()).inflate(i, (ViewGroup) this.i, false));
        }

        public g n(View view) {
            this.f = view;
            r();
            return this;
        }

        public g o(Drawable drawable) {
            this.f3193b = drawable;
            TabLayout tabLayout = this.h;
            if (tabLayout.z == 1 || tabLayout.C == 2) {
                tabLayout.M(true);
            }
            r();
            if (b.a.a.a.n.b.f1759a && this.i.l() && this.i.f.isVisible()) {
                this.i.invalidate();
            }
            return this;
        }

        void p(int i) {
            this.e = i;
        }

        public g q(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.f3195d) && !TextUtils.isEmpty(charSequence)) {
                this.i.setContentDescription(charSequence);
            }
            this.f3194c = charSequence;
            r();
            return this;
        }

        void r() {
            i iVar = this.i;
            if (iVar != null) {
                iVar.t();
            }
        }
    }

    public static class h implements b.g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<TabLayout> f3196a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3197b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3198c;

        public h(TabLayout tabLayout) {
            this.f3196a = new WeakReference<>(tabLayout);
        }

        @Override // a.p.a.b.g
        public void a(int i, float f, int i2) {
            TabLayout tabLayout = this.f3196a.get();
            if (tabLayout != null) {
                int i3 = this.f3198c;
                tabLayout.H(i, f, i3 != 2 || this.f3197b == 1, (i3 == 2 && this.f3197b == 0) ? false : true);
            }
        }

        @Override // a.p.a.b.g
        public void b(int i) {
            this.f3197b = this.f3198c;
            this.f3198c = i;
        }

        @Override // a.p.a.b.g
        public void c(int i) {
            TabLayout tabLayout = this.f3196a.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i || i >= tabLayout.getTabCount()) {
                return;
            }
            int i2 = this.f3198c;
            tabLayout.E(tabLayout.w(i), i2 == 0 || (i2 == 2 && this.f3197b == 0));
        }

        void d() {
            this.f3198c = 0;
            this.f3197b = 0;
        }
    }

    public final class i extends LinearLayout {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private g f3199b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private TextView f3200c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private ImageView f3201d;
        private View e;
        private b.a.a.a.n.a f;
        private View g;
        private TextView h;
        private ImageView i;
        private Drawable j;
        private int k;

        class a implements View.OnLayoutChangeListener {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ View f3202a;

            a(View view) {
                this.f3202a = view;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (this.f3202a.getVisibility() == 0) {
                    i.this.s(this.f3202a);
                }
            }
        }

        public i(Context context) {
            super(context);
            this.k = 2;
            u(context);
            t.t0(this, TabLayout.this.h, TabLayout.this.i, TabLayout.this.j, TabLayout.this.k);
            setGravity(17);
            setOrientation(!TabLayout.this.D ? 1 : 0);
            setClickable(true);
            t.u0(this, r.b(getContext(), 1002));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void drawBackground(Canvas canvas) {
            Drawable drawable = this.j;
            if (drawable != null) {
                drawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
                this.j.draw(canvas);
            }
        }

        private void g(View view) {
            if (view == null) {
                return;
            }
            view.addOnLayoutChangeListener(new a(view));
        }

        private b.a.a.a.n.a getBadge() {
            return this.f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getContentWidth() {
            View[] viewArr = {this.f3200c, this.f3201d, this.g};
            int iMax = 0;
            int iMin = 0;
            boolean z = false;
            for (int i = 0; i < 3; i++) {
                View view = viewArr[i];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z ? Math.min(iMin, view.getLeft()) : view.getLeft();
                    iMax = z ? Math.max(iMax, view.getRight()) : view.getRight();
                    z = true;
                }
            }
            return iMax - iMin;
        }

        private b.a.a.a.n.a getOrCreateBadge() {
            if (this.f == null) {
                this.f = b.a.a.a.n.a.c(getContext());
            }
            r();
            b.a.a.a.n.a aVar = this.f;
            if (aVar != null) {
                return aVar;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        private float h(Layout layout, int i, float f) {
            return layout.getLineWidth(i) * (f / layout.getPaint().getTextSize());
        }

        private void i(boolean z) {
            setClipChildren(z);
            setClipToPadding(z);
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.setClipChildren(z);
                viewGroup.setClipToPadding(z);
            }
        }

        private FrameLayout j() {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            return frameLayout;
        }

        private FrameLayout k(View view) {
            if ((view == this.f3201d || view == this.f3200c) && b.a.a.a.n.b.f1759a) {
                return (FrameLayout) view.getParent();
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean l() {
            return this.f != null;
        }

        private void m() {
            ViewGroup viewGroup;
            if (b.a.a.a.n.b.f1759a) {
                FrameLayout frameLayoutJ = j();
                addView(frameLayoutJ, 0);
                viewGroup = frameLayoutJ;
            } else {
                viewGroup = this;
            }
            ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(b.a.a.a.h.f1715a, viewGroup, false);
            this.f3201d = imageView;
            viewGroup.addView(imageView, 0);
        }

        private void n() {
            ViewGroup viewGroup;
            if (b.a.a.a.n.b.f1759a) {
                FrameLayout frameLayoutJ = j();
                addView(frameLayoutJ);
                viewGroup = frameLayoutJ;
            } else {
                viewGroup = this;
            }
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(b.a.a.a.h.f1716b, viewGroup, false);
            this.f3200c = textView;
            viewGroup.addView(textView);
        }

        private void p(View view) {
            if (l() && view != null) {
                i(false);
                b.a.a.a.n.b.a(this.f, view, k(view));
                this.e = view;
            }
        }

        private void q() {
            if (l()) {
                i(true);
                View view = this.e;
                if (view != null) {
                    b.a.a.a.n.b.b(this.f, view, k(view));
                    this.e = null;
                }
            }
        }

        private void r() {
            g gVar;
            View view;
            View view2;
            g gVar2;
            if (l()) {
                if (this.g == null) {
                    if (this.f3201d != null && (gVar2 = this.f3199b) != null && gVar2.e() != null) {
                        View view3 = this.e;
                        view = this.f3201d;
                        if (view3 != view) {
                            q();
                            view2 = this.f3201d;
                            p(view2);
                            return;
                        }
                        s(view);
                        return;
                    }
                    if (this.f3200c != null && (gVar = this.f3199b) != null && gVar.g() == 1) {
                        View view4 = this.e;
                        view = this.f3200c;
                        if (view4 != view) {
                            q();
                            view2 = this.f3200c;
                            p(view2);
                            return;
                        }
                        s(view);
                        return;
                    }
                }
                q();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void s(View view) {
            if (l() && view == this.e) {
                b.a.a.a.n.b.c(this.f, view, k(view));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void u(Context context) {
            int i = TabLayout.this.t;
            if (i != 0) {
                Drawable drawableD = a.a.k.a.a.d(context, i);
                this.j = drawableD;
                if (drawableD != null && drawableD.isStateful()) {
                    this.j.setState(getDrawableState());
                }
            } else {
                this.j = null;
            }
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(0);
            Drawable layerDrawable = gradientDrawable;
            if (TabLayout.this.o != null) {
                GradientDrawable gradientDrawable2 = new GradientDrawable();
                gradientDrawable2.setCornerRadius(1.0E-5f);
                gradientDrawable2.setColor(-1);
                ColorStateList colorStateListA = b.a.a.a.z.b.a(TabLayout.this.o);
                if (Build.VERSION.SDK_INT >= 21) {
                    boolean z = TabLayout.this.F;
                    GradientDrawable gradientDrawable3 = gradientDrawable;
                    if (z) {
                        gradientDrawable3 = null;
                    }
                    layerDrawable = new RippleDrawable(colorStateListA, gradientDrawable3, z ? null : gradientDrawable2);
                } else {
                    Drawable drawableR = androidx.core.graphics.drawable.a.r(gradientDrawable2);
                    androidx.core.graphics.drawable.a.o(drawableR, colorStateListA);
                    layerDrawable = new LayerDrawable(new Drawable[]{gradientDrawable, drawableR});
                }
            }
            t.k0(this, layerDrawable);
            TabLayout.this.invalidate();
        }

        private void w(TextView textView, ImageView imageView) {
            g gVar = this.f3199b;
            Drawable drawableMutate = (gVar == null || gVar.e() == null) ? null : androidx.core.graphics.drawable.a.r(this.f3199b.e()).mutate();
            g gVar2 = this.f3199b;
            CharSequence charSequenceH = gVar2 != null ? gVar2.h() : null;
            if (imageView != null) {
                if (drawableMutate != null) {
                    imageView.setImageDrawable(drawableMutate);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
            }
            boolean z = !TextUtils.isEmpty(charSequenceH);
            if (textView != null) {
                if (z) {
                    textView.setText(charSequenceH);
                    if (this.f3199b.g == 1) {
                        textView.setVisibility(0);
                    } else {
                        textView.setVisibility(8);
                    }
                    setVisibility(0);
                } else {
                    textView.setVisibility(8);
                    textView.setText((CharSequence) null);
                }
            }
            if (imageView != null) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
                int iB = (z && imageView.getVisibility() == 0) ? (int) com.google.android.material.internal.j.b(getContext(), 8) : 0;
                if (TabLayout.this.D) {
                    if (iB != a.g.k.g.a(marginLayoutParams)) {
                        a.g.k.g.c(marginLayoutParams, iB);
                        marginLayoutParams.bottomMargin = 0;
                        imageView.setLayoutParams(marginLayoutParams);
                        imageView.requestLayout();
                    }
                } else if (iB != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = iB;
                    a.g.k.g.c(marginLayoutParams, 0);
                    imageView.setLayoutParams(marginLayoutParams);
                    imageView.requestLayout();
                }
            }
            g gVar3 = this.f3199b;
            x0.a(this, z ? null : gVar3 != null ? gVar3.f3195d : null);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void drawableStateChanged() {
            super.drawableStateChanged();
            int[] drawableState = getDrawableState();
            Drawable drawable = this.j;
            boolean state = false;
            if (drawable != null && drawable.isStateful()) {
                state = false | this.j.setState(drawableState);
            }
            if (state) {
                invalidate();
                TabLayout.this.invalidate();
            }
        }

        public g getTab() {
            return this.f3199b;
        }

        void o() {
            setTab(null);
            setSelected(false);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            b.a.a.a.n.a aVar = this.f;
            if (aVar != null && aVar.isVisible()) {
                accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + ", " + ((Object) this.f.g()));
            }
            a.g.k.c0.c cVarV0 = a.g.k.c0.c.v0(accessibilityNodeInfo);
            cVarV0.a0(c.C0014c.a(0, 1, this.f3199b.f(), 1, false, isSelected()));
            if (isSelected()) {
                cVarV0.Y(false);
                cVarV0.P(c.a.e);
            }
            cVarV0.n0("Tab");
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i, int i2) {
            Layout layout;
            int size = View.MeasureSpec.getSize(i);
            int mode = View.MeasureSpec.getMode(i);
            int tabMaxWidth = TabLayout.this.getTabMaxWidth();
            if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i = View.MeasureSpec.makeMeasureSpec(TabLayout.this.u, Integer.MIN_VALUE);
            }
            super.onMeasure(i, i2);
            if (this.f3200c != null) {
                float f = TabLayout.this.r;
                int i3 = this.k;
                ImageView imageView = this.f3201d;
                boolean z = true;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView = this.f3200c;
                    if (textView != null && textView.getLineCount() > 1) {
                        f = TabLayout.this.s;
                    }
                } else {
                    i3 = 1;
                }
                float textSize = this.f3200c.getTextSize();
                int lineCount = this.f3200c.getLineCount();
                int iD = androidx.core.widget.i.d(this.f3200c);
                if (f != textSize || (iD >= 0 && i3 != iD)) {
                    if (TabLayout.this.C == 1 && f > textSize && lineCount == 1 && ((layout = this.f3200c.getLayout()) == null || h(layout, 0, f) > (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight())) {
                        z = false;
                    }
                    if (z) {
                        this.f3200c.setTextSize(0, f);
                        this.f3200c.setMaxLines(i3);
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        @Override // android.view.View
        public boolean performClick() {
            boolean zPerformClick = super.performClick();
            if (this.f3199b == null) {
                return zPerformClick;
            }
            if (!zPerformClick) {
                playSoundEffect(0);
            }
            this.f3199b.k();
            return true;
        }

        @Override // android.view.View
        public void setSelected(boolean z) {
            boolean z2 = isSelected() != z;
            super.setSelected(z);
            if (z2 && z && Build.VERSION.SDK_INT < 16) {
                sendAccessibilityEvent(4);
            }
            TextView textView = this.f3200c;
            if (textView != null) {
                textView.setSelected(z);
            }
            ImageView imageView = this.f3201d;
            if (imageView != null) {
                imageView.setSelected(z);
            }
            View view = this.g;
            if (view != null) {
                view.setSelected(z);
            }
        }

        void setTab(g gVar) {
            if (gVar != this.f3199b) {
                this.f3199b = gVar;
                t();
            }
        }

        final void t() {
            g gVar = this.f3199b;
            Drawable drawableMutate = null;
            View viewD = gVar != null ? gVar.d() : null;
            if (viewD != null) {
                ViewParent parent = viewD.getParent();
                if (parent != this) {
                    if (parent != null) {
                        ((ViewGroup) parent).removeView(viewD);
                    }
                    addView(viewD);
                }
                this.g = viewD;
                TextView textView = this.f3200c;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.f3201d;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.f3201d.setImageDrawable(null);
                }
                TextView textView2 = (TextView) viewD.findViewById(R.id.text1);
                this.h = textView2;
                if (textView2 != null) {
                    this.k = androidx.core.widget.i.d(textView2);
                }
                this.i = (ImageView) viewD.findViewById(R.id.icon);
            } else {
                View view = this.g;
                if (view != null) {
                    removeView(view);
                    this.g = null;
                }
                this.h = null;
                this.i = null;
            }
            if (this.g == null) {
                if (this.f3201d == null) {
                    m();
                }
                if (gVar != null && gVar.e() != null) {
                    drawableMutate = androidx.core.graphics.drawable.a.r(gVar.e()).mutate();
                }
                if (drawableMutate != null) {
                    androidx.core.graphics.drawable.a.o(drawableMutate, TabLayout.this.n);
                    PorterDuff.Mode mode = TabLayout.this.q;
                    if (mode != null) {
                        androidx.core.graphics.drawable.a.p(drawableMutate, mode);
                    }
                }
                if (this.f3200c == null) {
                    n();
                    this.k = androidx.core.widget.i.d(this.f3200c);
                }
                androidx.core.widget.i.q(this.f3200c, TabLayout.this.l);
                ColorStateList colorStateList = TabLayout.this.m;
                if (colorStateList != null) {
                    this.f3200c.setTextColor(colorStateList);
                }
                w(this.f3200c, this.f3201d);
                r();
                g(this.f3201d);
                g(this.f3200c);
            } else {
                TextView textView3 = this.h;
                if (textView3 != null || this.i != null) {
                    w(textView3, this.i);
                }
            }
            if (gVar != null && !TextUtils.isEmpty(gVar.f3195d)) {
                setContentDescription(gVar.f3195d);
            }
            setSelected(gVar != null && gVar.i());
        }

        final void v() {
            ImageView imageView;
            setOrientation(!TabLayout.this.D ? 1 : 0);
            TextView textView = this.h;
            if (textView == null && this.i == null) {
                textView = this.f3200c;
                imageView = this.f3201d;
            } else {
                imageView = this.i;
            }
            w(textView, imageView);
        }
    }

    public static class j implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final a.p.a.b f3204a;

        public j(a.p.a.b bVar) {
            this.f3204a = bVar;
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void a(g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void b(g gVar) {
            this.f3204a.setCurrentItem(gVar.f());
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public void c(g gVar) {
        }
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.a.a.a.b.z);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TabLayout(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f3177b;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f3179d = new ArrayList<>();
        this.f = new RectF();
        this.u = Integer.MAX_VALUE;
        this.H = new ArrayList<>();
        this.Q = new a.g.j.f(12);
        Context context2 = getContext();
        setHorizontalScrollBarEnabled(false);
        f fVar = new f(context2);
        this.g = fVar;
        super.addView(fVar, 0, new FrameLayout.LayoutParams(-2, -1));
        int[] iArr = l.t3;
        int i4 = l.Q3;
        TypedArray typedArrayH = com.google.android.material.internal.i.h(context2, attributeSet, iArr, i2, i3, i4);
        if (getBackground() instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) getBackground();
            b.a.a.a.b0.g gVar = new b.a.a.a.b0.g();
            gVar.W(ColorStateList.valueOf(colorDrawable.getColor()));
            gVar.M(context2);
            gVar.V(t.t(this));
            t.k0(this, gVar);
        }
        fVar.i(typedArrayH.getDimensionPixelSize(l.E3, -1));
        fVar.h(typedArrayH.getColor(l.B3, 0));
        setSelectedTabIndicator(b.a.a.a.y.c.d(context2, typedArrayH, l.z3));
        setSelectedTabIndicatorGravity(typedArrayH.getInt(l.D3, 0));
        setTabIndicatorFullWidth(typedArrayH.getBoolean(l.C3, true));
        int dimensionPixelSize = typedArrayH.getDimensionPixelSize(l.J3, 0);
        this.k = dimensionPixelSize;
        this.j = dimensionPixelSize;
        this.i = dimensionPixelSize;
        this.h = dimensionPixelSize;
        this.h = typedArrayH.getDimensionPixelSize(l.M3, dimensionPixelSize);
        this.i = typedArrayH.getDimensionPixelSize(l.N3, this.i);
        this.j = typedArrayH.getDimensionPixelSize(l.L3, this.j);
        this.k = typedArrayH.getDimensionPixelSize(l.K3, this.k);
        int resourceId = typedArrayH.getResourceId(i4, k.f1725b);
        this.l = resourceId;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(resourceId, a.a.j.J2);
        try {
            this.r = typedArrayObtainStyledAttributes.getDimensionPixelSize(a.a.j.K2, 0);
            this.m = b.a.a.a.y.c.a(context2, typedArrayObtainStyledAttributes, a.a.j.N2);
            typedArrayObtainStyledAttributes.recycle();
            int i5 = l.R3;
            if (typedArrayH.hasValue(i5)) {
                this.m = b.a.a.a.y.c.a(context2, typedArrayH, i5);
            }
            int i6 = l.P3;
            if (typedArrayH.hasValue(i6)) {
                this.m = o(this.m.getDefaultColor(), typedArrayH.getColor(i6, 0));
            }
            this.n = b.a.a.a.y.c.a(context2, typedArrayH, l.x3);
            this.q = com.google.android.material.internal.j.e(typedArrayH.getInt(l.y3, -1), null);
            this.o = b.a.a.a.y.c.a(context2, typedArrayH, l.O3);
            this.A = typedArrayH.getInt(l.A3, 300);
            this.v = typedArrayH.getDimensionPixelSize(l.H3, -1);
            this.w = typedArrayH.getDimensionPixelSize(l.G3, -1);
            this.t = typedArrayH.getResourceId(l.u3, 0);
            this.y = typedArrayH.getDimensionPixelSize(l.v3, 0);
            this.C = typedArrayH.getInt(l.I3, 1);
            this.z = typedArrayH.getInt(l.w3, 0);
            this.D = typedArrayH.getBoolean(l.F3, false);
            this.F = typedArrayH.getBoolean(l.S3, false);
            typedArrayH.recycle();
            Resources resources = getResources();
            this.s = resources.getDimensionPixelSize(b.a.a.a.d.i);
            this.x = resources.getDimensionPixelSize(b.a.a.a.d.h);
            l();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    private void C(int i2) {
        i iVar = (i) this.g.getChildAt(i2);
        this.g.removeViewAt(i2);
        if (iVar != null) {
            iVar.o();
            this.Q.a(iVar);
        }
        requestLayout();
    }

    private void J(a.p.a.b bVar, boolean z, boolean z2) {
        a.p.a.b bVar2 = this.K;
        if (bVar2 != null) {
            h hVar = this.N;
            if (hVar != null) {
                bVar2.D(hVar);
            }
            b bVar3 = this.O;
            if (bVar3 != null) {
                this.K.C(bVar3);
            }
        }
        c cVar = this.I;
        if (cVar != null) {
            B(cVar);
            this.I = null;
        }
        if (bVar != null) {
            this.K = bVar;
            if (this.N == null) {
                this.N = new h(this);
            }
            this.N.d();
            bVar.b(this.N);
            j jVar = new j(bVar);
            this.I = jVar;
            b(jVar);
            a.p.a.a adapter = bVar.getAdapter();
            if (adapter != null) {
                F(adapter, z);
            }
            if (this.O == null) {
                this.O = new b();
            }
            this.O.b(z);
            bVar.a(this.O);
            G(bVar.getCurrentItem(), 0.0f, true);
        } else {
            this.K = null;
            F(null, false);
        }
        this.P = z2;
    }

    private void K() {
        int size = this.f3179d.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f3179d.get(i2).r();
        }
    }

    private void L(LinearLayout.LayoutParams layoutParams) {
        float f2;
        if (this.C == 1 && this.z == 0) {
            layoutParams.width = 0;
            f2 = 1.0f;
        } else {
            layoutParams.width = -2;
            f2 = 0.0f;
        }
        layoutParams.weight = f2;
    }

    private void g(com.google.android.material.tabs.a aVar) {
        g gVarX = x();
        CharSequence charSequence = aVar.f3205b;
        if (charSequence != null) {
            gVarX.q(charSequence);
        }
        Drawable drawable = aVar.f3206c;
        if (drawable != null) {
            gVarX.o(drawable);
        }
        int i2 = aVar.f3207d;
        if (i2 != 0) {
            gVarX.m(i2);
        }
        if (!TextUtils.isEmpty(aVar.getContentDescription())) {
            gVarX.l(aVar.getContentDescription());
        }
        d(gVarX);
    }

    private int getDefaultHeight() {
        int size = this.f3179d.size();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 < size) {
                g gVar = this.f3179d.get(i2);
                if (gVar != null && gVar.e() != null && !TextUtils.isEmpty(gVar.h())) {
                    z = true;
                    break;
                }
                i2++;
            } else {
                break;
            }
        }
        return (!z || this.D) ? 48 : 72;
    }

    private int getTabMinWidth() {
        int i2 = this.v;
        if (i2 != -1) {
            return i2;
        }
        int i3 = this.C;
        if (i3 == 0 || i3 == 2) {
            return this.x;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.g.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void h(g gVar) {
        i iVar = gVar.i;
        iVar.setSelected(false);
        iVar.setActivated(false);
        this.g.addView(iVar, gVar.f(), p());
    }

    private void i(View view) {
        if (!(view instanceof com.google.android.material.tabs.a)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        g((com.google.android.material.tabs.a) view);
    }

    private void j(int i2) {
        if (i2 == -1) {
            return;
        }
        if (getWindowToken() == null || !t.N(this) || this.g.e()) {
            G(i2, 0.0f, true);
            return;
        }
        int scrollX = getScrollX();
        int iM = m(i2, 0.0f);
        if (scrollX != iM) {
            v();
            this.J.setIntValues(scrollX, iM);
            this.J.start();
        }
        this.g.c(i2, this.A);
    }

    private void k(int i2) {
        f fVar;
        int i3;
        if (i2 != 0) {
            i3 = 1;
            if (i2 == 1) {
                fVar = this.g;
                fVar.setGravity(i3);
            } else if (i2 != 2) {
                return;
            }
        } else {
            Log.w("TabLayout", "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
        }
        fVar = this.g;
        i3 = 8388611;
        fVar.setGravity(i3);
    }

    private void l() {
        int i2 = this.C;
        t.t0(this.g, (i2 == 0 || i2 == 2) ? Math.max(0, this.y - this.h) : 0, 0, 0, 0);
        int i3 = this.C;
        if (i3 == 0) {
            k(this.z);
        } else if (i3 == 1 || i3 == 2) {
            if (this.z == 2) {
                Log.w("TabLayout", "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead");
            }
            this.g.setGravity(1);
        }
        M(true);
    }

    private int m(int i2, float f2) {
        int i3 = this.C;
        if (i3 != 0 && i3 != 2) {
            return 0;
        }
        View childAt = this.g.getChildAt(i2);
        int i4 = i2 + 1;
        View childAt2 = i4 < this.g.getChildCount() ? this.g.getChildAt(i4) : null;
        int width = childAt != null ? childAt.getWidth() : 0;
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = (childAt.getLeft() + (width / 2)) - (getWidth() / 2);
        int i5 = (int) ((width + width2) * 0.5f * f2);
        return t.y(this) == 0 ? left + i5 : left - i5;
    }

    private void n(g gVar, int i2) {
        gVar.p(i2);
        this.f3179d.add(i2, gVar);
        int size = this.f3179d.size();
        while (true) {
            i2++;
            if (i2 >= size) {
                return;
            } else {
                this.f3179d.get(i2).p(i2);
            }
        }
    }

    private static ColorStateList o(int i2, int i3) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i3, i2});
    }

    private LinearLayout.LayoutParams p() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        L(layoutParams);
        return layoutParams;
    }

    private i r(g gVar) {
        a.g.j.e<i> eVar = this.Q;
        i iVarB = eVar != null ? eVar.b() : null;
        if (iVarB == null) {
            iVarB = new i(getContext());
        }
        iVarB.setTab(gVar);
        iVarB.setFocusable(true);
        iVarB.setMinimumWidth(getTabMinWidth());
        iVarB.setContentDescription(TextUtils.isEmpty(gVar.f3195d) ? gVar.f3194c : gVar.f3195d);
        return iVarB;
    }

    private void s(g gVar) {
        for (int size = this.H.size() - 1; size >= 0; size--) {
            this.H.get(size).c(gVar);
        }
    }

    private void setSelectedTabView(int i2) {
        int childCount = this.g.getChildCount();
        if (i2 < childCount) {
            int i3 = 0;
            while (i3 < childCount) {
                View childAt = this.g.getChildAt(i3);
                boolean z = true;
                childAt.setSelected(i3 == i2);
                if (i3 != i2) {
                    z = false;
                }
                childAt.setActivated(z);
                i3++;
            }
        }
    }

    private void t(g gVar) {
        for (int size = this.H.size() - 1; size >= 0; size--) {
            this.H.get(size).b(gVar);
        }
    }

    private void u(g gVar) {
        for (int size = this.H.size() - 1; size >= 0; size--) {
            this.H.get(size).a(gVar);
        }
    }

    private void v() {
        if (this.J == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.J = valueAnimator;
            valueAnimator.setInterpolator(b.a.a.a.m.a.f1733b);
            this.J.setDuration(this.A);
            this.J.addUpdateListener(new a());
        }
    }

    public void A() {
        for (int childCount = this.g.getChildCount() - 1; childCount >= 0; childCount--) {
            C(childCount);
        }
        Iterator<g> it = this.f3179d.iterator();
        while (it.hasNext()) {
            g next = it.next();
            it.remove();
            next.j();
            z(next);
        }
        this.e = null;
    }

    @Deprecated
    public void B(c cVar) {
        this.H.remove(cVar);
    }

    public void D(g gVar) {
        E(gVar, true);
    }

    public void E(g gVar, boolean z) {
        g gVar2 = this.e;
        if (gVar2 == gVar) {
            if (gVar2 != null) {
                s(gVar);
                j(gVar.f());
                return;
            }
            return;
        }
        int iF = gVar != null ? gVar.f() : -1;
        if (z) {
            if ((gVar2 == null || gVar2.f() == -1) && iF != -1) {
                G(iF, 0.0f, true);
            } else {
                j(iF);
            }
            if (iF != -1) {
                setSelectedTabView(iF);
            }
        }
        this.e = gVar;
        if (gVar2 != null) {
            u(gVar2);
        }
        if (gVar != null) {
            t(gVar);
        }
    }

    void F(a.p.a.a aVar, boolean z) {
        if (this.L != null && this.M != null) {
            throw null;
        }
        if (!z || aVar == null) {
            y();
        } else {
            if (this.M != null) {
                throw null;
            }
            this.M = new e();
            throw null;
        }
    }

    public void G(int i2, float f2, boolean z) {
        H(i2, f2, z, true);
    }

    public void H(int i2, float f2, boolean z, boolean z2) {
        int iRound = Math.round(i2 + f2);
        if (iRound < 0 || iRound >= this.g.getChildCount()) {
            return;
        }
        if (z2) {
            this.g.g(i2, f2);
        }
        ValueAnimator valueAnimator = this.J;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.J.cancel();
        }
        scrollTo(m(i2, f2), 0);
        if (z) {
            setSelectedTabView(iRound);
        }
    }

    public void I(a.p.a.b bVar, boolean z) {
        J(bVar, z, false);
    }

    void M(boolean z) {
        for (int i2 = 0; i2 < this.g.getChildCount(); i2++) {
            View childAt = this.g.getChildAt(i2);
            childAt.setMinimumWidth(getTabMinWidth());
            L((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        i(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2) {
        i(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        i(view);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        i(view);
    }

    @Deprecated
    public void b(c cVar) {
        if (this.H.contains(cVar)) {
            return;
        }
        this.H.add(cVar);
    }

    public void c(d dVar) {
        b(dVar);
    }

    public void d(g gVar) {
        f(gVar, this.f3179d.isEmpty());
    }

    public void e(g gVar, int i2, boolean z) {
        if (gVar.h != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        n(gVar, i2);
        h(gVar);
        if (z) {
            gVar.k();
        }
    }

    public void f(g gVar, boolean z) {
        e(gVar, this.f3179d.size(), z);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    public int getSelectedTabPosition() {
        g gVar = this.e;
        if (gVar != null) {
            return gVar.f();
        }
        return -1;
    }

    public int getTabCount() {
        return this.f3179d.size();
    }

    public int getTabGravity() {
        return this.z;
    }

    public ColorStateList getTabIconTint() {
        return this.n;
    }

    public int getTabIndicatorGravity() {
        return this.B;
    }

    int getTabMaxWidth() {
        return this.u;
    }

    public int getTabMode() {
        return this.C;
    }

    public ColorStateList getTabRippleColor() {
        return this.o;
    }

    public Drawable getTabSelectedIndicator() {
        return this.p;
    }

    public ColorStateList getTabTextColors() {
        return this.m;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b.a.a.a.b0.h.e(this);
        if (this.K == null) {
            ViewParent parent = getParent();
            if (parent instanceof a.p.a.b) {
                J((a.p.a.b) parent, true, true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.P) {
            setupWithViewPager(null);
            this.P = false;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        for (int i2 = 0; i2 < this.g.getChildCount(); i2++) {
            View childAt = this.g.getChildAt(i2);
            if (childAt instanceof i) {
                ((i) childAt).drawBackground(canvas);
            }
        }
        super.onDraw(canvas);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        a.g.k.c0.c.v0(accessibilityNodeInfo).Z(c.b.a(1, getTabCount(), false, 1));
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0082  */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r7, int r8) {
        /*
            r6 = this;
            android.content.Context r0 = r6.getContext()
            int r1 = r6.getDefaultHeight()
            float r0 = com.google.android.material.internal.j.b(r0, r1)
            int r0 = java.lang.Math.round(r0)
            int r1 = android.view.View.MeasureSpec.getMode(r8)
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = 0
            r5 = 1
            if (r1 == r2) goto L2e
            if (r1 == 0) goto L1f
            goto L41
        L1f:
            int r8 = r6.getPaddingTop()
            int r0 = r0 + r8
            int r8 = r6.getPaddingBottom()
            int r0 = r0 + r8
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r3)
            goto L41
        L2e:
            int r1 = r6.getChildCount()
            if (r1 != r5) goto L41
            int r1 = android.view.View.MeasureSpec.getSize(r8)
            if (r1 < r0) goto L41
            android.view.View r1 = r6.getChildAt(r4)
            r1.setMinimumHeight(r0)
        L41:
            int r0 = android.view.View.MeasureSpec.getSize(r7)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            if (r1 == 0) goto L5f
            int r1 = r6.w
            if (r1 <= 0) goto L50
            goto L5d
        L50:
            float r0 = (float) r0
            android.content.Context r1 = r6.getContext()
            r2 = 56
            float r1 = com.google.android.material.internal.j.b(r1, r2)
            float r0 = r0 - r1
            int r1 = (int) r0
        L5d:
            r6.u = r1
        L5f:
            super.onMeasure(r7, r8)
            int r7 = r6.getChildCount()
            if (r7 != r5) goto Lad
            android.view.View r7 = r6.getChildAt(r4)
            int r0 = r6.C
            if (r0 == 0) goto L82
            if (r0 == r5) goto L76
            r1 = 2
            if (r0 == r1) goto L82
            goto L8d
        L76:
            int r0 = r7.getMeasuredWidth()
            int r1 = r6.getMeasuredWidth()
            if (r0 == r1) goto L8d
        L80:
            r4 = r5
            goto L8d
        L82:
            int r0 = r7.getMeasuredWidth()
            int r1 = r6.getMeasuredWidth()
            if (r0 >= r1) goto L8d
            goto L80
        L8d:
            if (r4 == 0) goto Lad
            int r0 = r6.getPaddingTop()
            int r1 = r6.getPaddingBottom()
            int r0 = r0 + r1
            android.view.ViewGroup$LayoutParams r1 = r7.getLayoutParams()
            int r1 = r1.height
            int r8 = android.widget.HorizontalScrollView.getChildMeasureSpec(r8, r0, r1)
            int r0 = r6.getMeasuredWidth()
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r3)
            r7.measure(r0, r8)
        Lad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.onMeasure(int, int):void");
    }

    protected g q() {
        g gVarB = f3178c.b();
        return gVarB == null ? new g() : gVarB;
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        b.a.a.a.b0.h.d(this, f2);
    }

    public void setInlineLabel(boolean z) {
        if (this.D != z) {
            this.D = z;
            for (int i2 = 0; i2 < this.g.getChildCount(); i2++) {
                View childAt = this.g.getChildAt(i2);
                if (childAt instanceof i) {
                    ((i) childAt).v();
                }
            }
            l();
        }
    }

    public void setInlineLabelResource(int i2) {
        setInlineLabel(getResources().getBoolean(i2));
    }

    @Deprecated
    public void setOnTabSelectedListener(c cVar) {
        c cVar2 = this.G;
        if (cVar2 != null) {
            B(cVar2);
        }
        this.G = cVar;
        if (cVar != null) {
            b(cVar);
        }
    }

    @Deprecated
    public void setOnTabSelectedListener(d dVar) {
        setOnTabSelectedListener((c) dVar);
    }

    void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        v();
        this.J.addListener(animatorListener);
    }

    public void setSelectedTabIndicator(int i2) {
        setSelectedTabIndicator(i2 != 0 ? a.a.k.a.a.d(getContext(), i2) : null);
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (this.p != drawable) {
            this.p = drawable;
            t.Y(this.g);
        }
    }

    public void setSelectedTabIndicatorColor(int i2) {
        this.g.h(i2);
    }

    public void setSelectedTabIndicatorGravity(int i2) {
        if (this.B != i2) {
            this.B = i2;
            t.Y(this.g);
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i2) {
        this.g.i(i2);
    }

    public void setTabGravity(int i2) {
        if (this.z != i2) {
            this.z = i2;
            l();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.n != colorStateList) {
            this.n = colorStateList;
            K();
        }
    }

    public void setTabIconTintResource(int i2) {
        setTabIconTint(a.a.k.a.a.c(getContext(), i2));
    }

    public void setTabIndicatorFullWidth(boolean z) {
        this.E = z;
        t.Y(this.g);
    }

    public void setTabMode(int i2) {
        if (i2 != this.C) {
            this.C = i2;
            l();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.o != colorStateList) {
            this.o = colorStateList;
            for (int i2 = 0; i2 < this.g.getChildCount(); i2++) {
                View childAt = this.g.getChildAt(i2);
                if (childAt instanceof i) {
                    ((i) childAt).u(getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(int i2) {
        setTabRippleColor(a.a.k.a.a.c(getContext(), i2));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.m != colorStateList) {
            this.m = colorStateList;
            K();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(a.p.a.a aVar) {
        F(aVar, false);
    }

    public void setUnboundedRipple(boolean z) {
        if (this.F != z) {
            this.F = z;
            for (int i2 = 0; i2 < this.g.getChildCount(); i2++) {
                View childAt = this.g.getChildAt(i2);
                if (childAt instanceof i) {
                    ((i) childAt).u(getContext());
                }
            }
        }
    }

    public void setUnboundedRippleResource(int i2) {
        setUnboundedRipple(getResources().getBoolean(i2));
    }

    public void setupWithViewPager(a.p.a.b bVar) {
        I(bVar, true);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    public g w(int i2) {
        if (i2 < 0 || i2 >= getTabCount()) {
            return null;
        }
        return this.f3179d.get(i2);
    }

    public g x() {
        g gVarQ = q();
        gVarQ.h = this;
        gVarQ.i = r(gVarQ);
        return gVarQ;
    }

    void y() {
        A();
        if (this.L != null) {
            throw null;
        }
    }

    protected boolean z(g gVar) {
        return f3178c.a(gVar);
    }
}
