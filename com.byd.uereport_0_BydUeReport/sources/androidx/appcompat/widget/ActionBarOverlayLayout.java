package androidx.appcompat.widget;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.OverScroller;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import o0.b0;
import o0.l;
import o0.m;
import o0.n;
import o0.t;
import q.m;
import r.k0;
import r.o;
import r.p;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements o, n, l, m {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f450d = {R$attr.actionBarSize, R.attr.windowContentOverlay};
    public b0 A;
    public b0 B;
    public d C;
    public OverScroller D;
    public ViewPropertyAnimator E;
    public final AnimatorListenerAdapter F;
    public final Runnable G;
    public final Runnable H;
    public final o0.o I;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f451e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f452f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ContentFrameLayout f453g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ActionBarContainer f454h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public p f455i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Drawable f456j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f457k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f458l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f459m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f460n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f461o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f462p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f463q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Rect f464r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Rect f465s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Rect f466t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Rect f467u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Rect f468v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final Rect f469w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final Rect f470x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public b0 f471y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public b0 f472z;

    public class a extends AnimatorListenerAdapter {
        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.E = null;
            actionBarOverlayLayout.f461o = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.E = null;
            actionBarOverlayLayout.f461o = false;
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.u();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.E = actionBarOverlayLayout.f454h.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.F);
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.u();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.E = actionBarOverlayLayout.f454h.animate().translationY(-ActionBarOverlayLayout.this.f454h.getHeight()).setListener(ActionBarOverlayLayout.this.F);
        }
    }

    public interface d {
        void a();

        void b();

        void c(int i8);

        void d();

        void e(boolean z7);

        void f();
    }

    public static class e extends ViewGroup.MarginLayoutParams {
        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public e(int i8, int i9) {
            super(i8, i9);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(Context context) {
        this(context, null);
    }

    public final void A() {
        u();
        this.G.run();
    }

    public final boolean B(float f8) {
        this.D.fling(0, 0, 0, (int) f8, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.D.getFinalY() > this.f454h.getHeight();
    }

    @Override // r.o
    public void a(Menu menu, m.a aVar) {
        z();
        this.f455i.a(menu, aVar);
    }

    @Override // r.o
    public boolean b() {
        z();
        return this.f455i.b();
    }

    @Override // r.o
    public void c() {
        z();
        this.f455i.c();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // r.o
    public boolean d() {
        z();
        return this.f455i.d();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.f456j == null || this.f457k) {
            return;
        }
        int bottom = this.f454h.getVisibility() == 0 ? (int) (this.f454h.getBottom() + this.f454h.getTranslationY() + 0.5f) : 0;
        this.f456j.setBounds(0, bottom, getWidth(), this.f456j.getIntrinsicHeight() + bottom);
        this.f456j.draw(canvas);
    }

    @Override // r.o
    public boolean e() {
        z();
        return this.f455i.e();
    }

    @Override // r.o
    public boolean f() {
        z();
        return this.f455i.f();
    }

    @Override // android.view.View
    public boolean fitSystemWindows(Rect rect) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.fitSystemWindows(rect);
        }
        z();
        boolean zQ = q(this.f454h, rect, true, true, false, true);
        this.f467u.set(rect);
        k0.a(this, this.f467u, this.f464r);
        if (!this.f468v.equals(this.f467u)) {
            this.f468v.set(this.f467u);
            zQ = true;
        }
        if (!this.f465s.equals(this.f464r)) {
            this.f465s.set(this.f464r);
            zQ = true;
        }
        if (zQ) {
            requestLayout();
        }
        return true;
    }

    @Override // r.o
    public boolean g() {
        z();
        return this.f455i.g();
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.f454h;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.I.a();
    }

    public CharSequence getTitle() {
        z();
        return this.f455i.getTitle();
    }

    @Override // o0.l
    public void h(View view, View view2, int i8, int i9) {
        if (i9 == 0) {
            onNestedScrollAccepted(view, view2, i8);
        }
    }

    @Override // o0.l
    public void i(View view, int i8) {
        if (i8 == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // o0.l
    public void j(View view, int i8, int i9, int[] iArr, int i10) {
        if (i10 == 0) {
            onNestedPreScroll(view, i8, i9, iArr);
        }
    }

    @Override // r.o
    public void k(int i8) {
        z();
        if (i8 == 2) {
            this.f455i.v();
        } else if (i8 == 5) {
            this.f455i.w();
        } else {
            if (i8 != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // r.o
    public void l() {
        z();
        this.f455i.h();
    }

    @Override // o0.m
    public void m(View view, int i8, int i9, int i10, int i11, int i12, int[] iArr) {
        n(view, i8, i9, i10, i11, i12);
    }

    @Override // o0.l
    public void n(View view, int i8, int i9, int i10, int i11, int i12) {
        if (i12 == 0) {
            onNestedScroll(view, i8, i9, i10, i11);
        }
    }

    @Override // o0.l
    public boolean o(View view, View view2, int i8, int i9) {
        return i9 == 0 && onStartNestedScroll(view, view2, i8);
    }

    @Override // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        z();
        b0 b0VarP = b0.p(windowInsets);
        boolean zQ = q(this.f454h, new Rect(b0VarP.f(), b0VarP.h(), b0VarP.g(), b0VarP.e()), true, true, false, true);
        t.g(this, b0VarP, this.f464r);
        Rect rect = this.f464r;
        b0 b0VarK = b0VarP.k(rect.left, rect.top, rect.right, rect.bottom);
        this.f471y = b0VarK;
        boolean z7 = true;
        if (!this.f472z.equals(b0VarK)) {
            this.f472z = this.f471y;
            zQ = true;
        }
        if (this.f465s.equals(this.f464r)) {
            z7 = zQ;
        } else {
            this.f465s.set(this.f464r);
        }
        if (z7) {
            requestLayout();
        }
        return b0VarP.a().c().b().o();
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        v(getContext());
        t.i0(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        u();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i13 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin + paddingLeft;
                int i14 = ((ViewGroup.MarginLayoutParams) eVar).topMargin + paddingTop;
                childAt.layout(i13, i14, measuredWidth + i13, measuredHeight + i14);
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        int measuredHeight;
        z();
        measureChildWithMargins(this.f454h, i8, 0, i9, 0);
        e eVar = (e) this.f454h.getLayoutParams();
        int iMax = Math.max(0, this.f454h.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin);
        int iMax2 = Math.max(0, this.f454h.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.f454h.getMeasuredState());
        boolean z7 = (t.J(this) & 256) != 0;
        if (z7) {
            measuredHeight = this.f451e;
            if (this.f459m && this.f454h.getTabContainer() != null) {
                measuredHeight += this.f451e;
            }
        } else {
            measuredHeight = this.f454h.getVisibility() != 8 ? this.f454h.getMeasuredHeight() : 0;
        }
        this.f466t.set(this.f464r);
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 21) {
            this.A = this.f471y;
        } else {
            this.f469w.set(this.f467u);
        }
        if (!this.f458l && !z7) {
            Rect rect = this.f466t;
            rect.top += measuredHeight;
            rect.bottom += 0;
            if (i10 >= 21) {
                this.A = this.A.k(0, measuredHeight, 0, 0);
            }
        } else if (i10 >= 21) {
            this.A = new b0.a(this.A).c(g0.b.a(this.A.f(), this.A.h() + measuredHeight, this.A.g(), this.A.e() + 0)).a();
        } else {
            Rect rect2 = this.f469w;
            rect2.top += measuredHeight;
            rect2.bottom += 0;
        }
        q(this.f453g, this.f466t, true, true, true, true);
        if (i10 >= 21 && !this.B.equals(this.A)) {
            b0 b0Var = this.A;
            this.B = b0Var;
            t.h(this.f453g, b0Var);
        } else if (i10 < 21 && !this.f470x.equals(this.f469w)) {
            this.f470x.set(this.f469w);
            this.f453g.a(this.f469w);
        }
        measureChildWithMargins(this.f453g, i8, 0, i9, 0);
        e eVar2 = (e) this.f453g.getLayoutParams();
        int iMax3 = Math.max(iMax, this.f453g.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin + ((ViewGroup.MarginLayoutParams) eVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.f453g.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f453g.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i8, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(iMax4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i9, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public boolean onNestedFling(View view, float f8, float f9, boolean z7) {
        if (!this.f460n || !z7) {
            return false;
        }
        if (B(f9)) {
            p();
        } else {
            A();
        }
        this.f461o = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public boolean onNestedPreFling(View view, float f8, float f9) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onNestedPreScroll(View view, int i8, int i9, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onNestedScroll(View view, int i8, int i9, int i10, int i11) {
        int i12 = this.f462p + i9;
        this.f462p = i12;
        setActionBarHideOffset(i12);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onNestedScrollAccepted(View view, View view2, int i8) {
        this.I.b(view, view2, i8);
        this.f462p = getActionBarHideOffset();
        u();
        d dVar = this.C;
        if (dVar != null) {
            dVar.b();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public boolean onStartNestedScroll(View view, View view2, int i8) {
        if ((i8 & 2) == 0 || this.f454h.getVisibility() != 0) {
            return false;
        }
        return this.f460n;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onStopNestedScroll(View view) {
        if (this.f460n && !this.f461o) {
            if (this.f462p <= this.f454h.getHeight()) {
                y();
            } else {
                x();
            }
        }
        d dVar = this.C;
        if (dVar != null) {
            dVar.d();
        }
    }

    @Override // android.view.View
    public void onWindowSystemUiVisibilityChanged(int i8) {
        if (Build.VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i8);
        }
        z();
        int i9 = this.f463q ^ i8;
        this.f463q = i8;
        boolean z7 = (i8 & 4) == 0;
        boolean z8 = (i8 & 256) != 0;
        d dVar = this.C;
        if (dVar != null) {
            dVar.e(!z8);
            if (z7 || !z8) {
                this.C.a();
            } else {
                this.C.f();
            }
        }
        if ((i9 & 256) == 0 || this.C == null) {
            return;
        }
        t.i0(this);
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i8) {
        super.onWindowVisibilityChanged(i8);
        this.f452f = i8;
        d dVar = this.C;
        if (dVar != null) {
            dVar.c(i8);
        }
    }

    public final void p() {
        u();
        this.H.run();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean q(android.view.View r3, android.graphics.Rect r4, boolean r5, boolean r6, boolean r7, boolean r8) {
        /*
            r2 = this;
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            androidx.appcompat.widget.ActionBarOverlayLayout$e r3 = (androidx.appcompat.widget.ActionBarOverlayLayout.e) r3
            r0 = 1
            if (r5 == 0) goto L13
            int r5 = r3.leftMargin
            int r1 = r4.left
            if (r5 == r1) goto L13
            r3.leftMargin = r1
            r5 = r0
            goto L14
        L13:
            r5 = 0
        L14:
            if (r6 == 0) goto L1f
            int r6 = r3.topMargin
            int r1 = r4.top
            if (r6 == r1) goto L1f
            r3.topMargin = r1
            r5 = r0
        L1f:
            if (r8 == 0) goto L2a
            int r6 = r3.rightMargin
            int r8 = r4.right
            if (r6 == r8) goto L2a
            r3.rightMargin = r8
            r5 = r0
        L2a:
            if (r7 == 0) goto L35
            int r6 = r3.bottomMargin
            int r4 = r4.bottom
            if (r6 == r4) goto L35
            r3.bottomMargin = r4
            goto L36
        L35:
            r0 = r5
        L36:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarOverlayLayout.q(android.view.View, android.graphics.Rect, boolean, boolean, boolean, boolean):boolean");
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: r, reason: merged with bridge method [inline-methods] */
    public e generateDefaultLayoutParams() {
        return new e(-1, -1);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    public void setActionBarHideOffset(int i8) {
        u();
        this.f454h.setTranslationY(-Math.max(0, Math.min(i8, this.f454h.getHeight())));
    }

    public void setActionBarVisibilityCallback(d dVar) {
        this.C = dVar;
        if (getWindowToken() != null) {
            this.C.c(this.f452f);
            int i8 = this.f463q;
            if (i8 != 0) {
                onWindowSystemUiVisibilityChanged(i8);
                t.i0(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z7) {
        this.f459m = z7;
    }

    public void setHideOnContentScrollEnabled(boolean z7) {
        if (z7 != this.f460n) {
            this.f460n = z7;
            if (z7) {
                return;
            }
            u();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i8) {
        z();
        this.f455i.setIcon(i8);
    }

    public void setLogo(int i8) {
        z();
        this.f455i.s(i8);
    }

    public void setOverlayMode(boolean z7) {
        this.f458l = z7;
        this.f457k = z7 && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z7) {
    }

    public void setUiOptions(int i8) {
    }

    @Override // r.o
    public void setWindowCallback(Window.Callback callback) {
        z();
        this.f455i.setWindowCallback(callback);
    }

    @Override // r.o
    public void setWindowTitle(CharSequence charSequence) {
        z();
        this.f455i.setWindowTitle(charSequence);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final p t(View view) {
        if (view instanceof p) {
            return (p) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    public void u() {
        removeCallbacks(this.G);
        removeCallbacks(this.H);
        ViewPropertyAnimator viewPropertyAnimator = this.E;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final void v(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f450d);
        this.f451e = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f456j = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.f457k = context.getApplicationInfo().targetSdkVersion < 19;
        this.D = new OverScroller(context);
    }

    public boolean w() {
        return this.f458l;
    }

    public final void x() {
        u();
        postDelayed(this.H, 600L);
    }

    public final void y() {
        u();
        postDelayed(this.G, 600L);
    }

    public void z() {
        if (this.f453g == null) {
            this.f453g = (ContentFrameLayout) findViewById(R$id.action_bar_activity_content);
            this.f454h = (ActionBarContainer) findViewById(R$id.action_bar_container);
            this.f455i = t(findViewById(R$id.action_bar));
        }
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f452f = 0;
        this.f464r = new Rect();
        this.f465s = new Rect();
        this.f466t = new Rect();
        this.f467u = new Rect();
        this.f468v = new Rect();
        this.f469w = new Rect();
        this.f470x = new Rect();
        b0 b0Var = b0.f6664a;
        this.f471y = b0Var;
        this.f472z = b0Var;
        this.A = b0Var;
        this.B = b0Var;
        this.F = new a();
        this.G = new b();
        this.H = new c();
        v(context);
        this.I = new o0.o(this);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new e(layoutParams);
    }

    public void setIcon(Drawable drawable) {
        z();
        this.f455i.setIcon(drawable);
    }
}
