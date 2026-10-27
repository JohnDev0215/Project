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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.view.menu.l;
import androidx.core.view.ViewCompat;
import androidx.core.view.z;
import com.byd.mediacenter.api.media.MediaControl;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class ActionBarOverlayLayout extends ViewGroup implements A, androidx.core.view.n, androidx.core.view.l, androidx.core.view.m {
    static final int[] H = {R$attr.actionBarSize, R.attr.windowContentOverlay};
    private d A;
    private OverScroller B;
    ViewPropertyAnimator C;
    final AnimatorListenerAdapter D;
    private final Runnable E;
    private final Runnable F;
    private final androidx.core.view.o G;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f244c;
    private int d;
    private ContentFrameLayout e;
    ActionBarContainer f;
    private B g;
    private Drawable h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    boolean m;
    private int n;
    private int o;
    private final Rect p;
    private final Rect q;
    private final Rect r;
    private final Rect s;
    private final Rect t;
    private final Rect u;
    private final Rect v;

    @NonNull
    private androidx.core.view.z w;

    @NonNull
    private androidx.core.view.z x;

    @NonNull
    private androidx.core.view.z y;

    @NonNull
    private androidx.core.view.z z;

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends AnimatorListenerAdapter {
        a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = null;
            actionBarOverlayLayout.m = false;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = null;
            actionBarOverlayLayout.m = false;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.x();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = actionBarOverlayLayout.f.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.D);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBarOverlayLayout.this.x();
            ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.this;
            actionBarOverlayLayout.C = actionBarOverlayLayout.f.animate().translationY(-ActionBarOverlayLayout.this.f.getHeight()).setListener(ActionBarOverlayLayout.this.D);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface d {
        void a(boolean z);

        void b();

        void c();

        void d(int i);

        void e();

        void f();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class e extends ViewGroup.MarginLayoutParams {
        public e(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public e(int i, int i2) {
            super(i, i2);
        }

        public e(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionBarOverlayLayout(@NonNull Context context) {
        this(context, null);
    }

    private void A() {
        x();
        postDelayed(this.F, 600L);
    }

    private void B() {
        x();
        postDelayed(this.E, 600L);
    }

    private void D() {
        x();
        this.E.run();
    }

    private boolean K(float f) {
        this.B.fling(0, 0, 0, (int) f, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.B.getFinalY() > this.f.getHeight();
    }

    private void r() {
        x();
        this.F.run();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean s(@androidx.annotation.NonNull android.view.View r2, @androidx.annotation.NonNull android.graphics.Rect r3, boolean r4, boolean r5, boolean r6, boolean r7) {
        /*
            r1 = this;
            android.view.ViewGroup$LayoutParams r1 = r2.getLayoutParams()
            androidx.appcompat.widget.ActionBarOverlayLayout$e r1 = (androidx.appcompat.widget.ActionBarOverlayLayout.e) r1
            r2 = 1
            if (r4 == 0) goto L13
            int r4 = r1.leftMargin
            int r0 = r3.left
            if (r4 == r0) goto L13
            r1.leftMargin = r0
            r4 = r2
            goto L14
        L13:
            r4 = 0
        L14:
            if (r5 == 0) goto L1f
            int r5 = r1.topMargin
            int r0 = r3.top
            if (r5 == r0) goto L1f
            r1.topMargin = r0
            r4 = r2
        L1f:
            if (r7 == 0) goto L2a
            int r5 = r1.rightMargin
            int r7 = r3.right
            if (r5 == r7) goto L2a
            r1.rightMargin = r7
            r4 = r2
        L2a:
            if (r6 == 0) goto L35
            int r5 = r1.bottomMargin
            int r3 = r3.bottom
            if (r5 == r3) goto L35
            r1.bottomMargin = r3
            goto L36
        L35:
            r2 = r4
        L36:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionBarOverlayLayout.s(android.view.View, android.graphics.Rect, boolean, boolean, boolean, boolean):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private B w(View view) {
        if (view instanceof B) {
            return (B) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).H();
        }
        throw new IllegalStateException("Can't make a decor toolbar out of " + view.getClass().getSimpleName());
    }

    private void y(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(H);
        this.f244c = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.h = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.i = context.getApplicationInfo().targetSdkVersion < 19;
        this.B = new OverScroller(context);
    }

    void C() {
        if (this.e == null) {
            this.e = (ContentFrameLayout) findViewById(R$id.action_bar_activity_content);
            this.f = (ActionBarContainer) findViewById(R$id.action_bar_container);
            this.g = w(findViewById(R$id.action_bar));
        }
    }

    public void E(int i) {
        x();
        this.f.setTranslationY(-Math.max(0, Math.min(i, this.f.getHeight())));
    }

    public void F(d dVar) {
        this.A = dVar;
        if (getWindowToken() != null) {
            this.A.d(this.d);
            int i = this.o;
            if (i != 0) {
                onWindowSystemUiVisibilityChanged(i);
                ViewCompat.J(this);
            }
        }
    }

    public void G(boolean z) {
        this.k = z;
    }

    public void H(boolean z) {
        if (z != this.l) {
            this.l = z;
            if (z) {
                return;
            }
            x();
            E(0);
        }
    }

    public void I(boolean z) {
        this.j = z;
        this.i = z && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void J(boolean z) {
    }

    @Override // androidx.appcompat.widget.A
    public void a(Menu menu, l.a aVar) {
        C();
        this.g.a(menu, aVar);
    }

    @Override // androidx.appcompat.widget.A
    public boolean b() {
        C();
        return this.g.b();
    }

    @Override // androidx.appcompat.widget.A
    public void c(CharSequence charSequence) {
        C();
        this.g.c(charSequence);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // androidx.appcompat.widget.A
    public boolean d() {
        C();
        return this.g.d();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.h == null || this.i) {
            return;
        }
        int bottom = this.f.getVisibility() == 0 ? (int) (this.f.getBottom() + this.f.getTranslationY() + 0.5f) : 0;
        this.h.setBounds(0, bottom, getWidth(), this.h.getIntrinsicHeight() + bottom);
        this.h.draw(canvas);
    }

    @Override // androidx.appcompat.widget.A
    public void e(Window.Callback callback) {
        C();
        this.g.e(callback);
    }

    @Override // androidx.appcompat.widget.A
    public boolean f() {
        C();
        return this.g.f();
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect rect) {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.fitSystemWindows(rect);
        }
        C();
        boolean zS = s(this.f, rect, true, true, false, true);
        this.s.set(rect);
        a0.a(this, this.s, this.p);
        if (!this.t.equals(this.s)) {
            this.t.set(this.s);
            zS = true;
        }
        if (!this.q.equals(this.p)) {
            this.q.set(this.p);
            zS = true;
        }
        if (zS) {
            requestLayout();
        }
        return true;
    }

    @Override // androidx.appcompat.widget.A
    public boolean g() {
        C();
        return this.g.g();
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.G.a();
    }

    @Override // androidx.appcompat.widget.A
    public void h() {
        C();
        this.g.h();
    }

    @Override // androidx.appcompat.widget.A
    public boolean i() {
        C();
        return this.g.i();
    }

    @Override // androidx.core.view.l
    public void j(View view, View view2, int i, int i2) {
        if (i2 == 0) {
            onNestedScrollAccepted(view, view2, i);
        }
    }

    @Override // androidx.core.view.l
    public void k(View view, int i) {
        if (i == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // androidx.core.view.l
    public void l(View view, int i, int i2, int[] iArr, int i3) {
        if (i3 == 0) {
            onNestedPreScroll(view, i, i2, iArr);
        }
    }

    @Override // androidx.appcompat.widget.A
    public void m(int i) {
        C();
        if (i == 2) {
            this.g.r();
        } else if (i == 5) {
            this.g.t();
        } else {
            if (i != 109) {
                return;
            }
            I(true);
        }
    }

    @Override // androidx.appcompat.widget.A
    public void n() {
        C();
        this.g.j();
    }

    @Override // androidx.core.view.m
    public void o(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        p(view, i, i2, i3, i4, i5);
    }

    @Override // android.view.View
    @RequiresApi(21)
    public WindowInsets onApplyWindowInsets(@NonNull WindowInsets windowInsets) {
        C();
        androidx.core.view.z zVarM = androidx.core.view.z.m(windowInsets);
        boolean zS = s(this.f, new Rect(zVarM.e(), zVarM.g(), zVarM.f(), zVarM.d()), true, true, false, true);
        ViewCompat.c(this, zVarM, this.p);
        Rect rect = this.p;
        androidx.core.view.z zVarI = zVarM.i(rect.left, rect.top, rect.right, rect.bottom);
        this.w = zVarI;
        boolean z = true;
        if (!this.x.equals(zVarI)) {
            this.x = this.w;
            zS = true;
        }
        if (this.q.equals(this.p)) {
            z = zS;
        } else {
            this.q.set(this.p);
        }
        if (z) {
            requestLayout();
        }
        return zVarM.a().c().b().l();
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        y(getContext());
        ViewCompat.J(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        x();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                e eVar = (e) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((ViewGroup.MarginLayoutParams) eVar).leftMargin + paddingLeft;
                int i7 = ((ViewGroup.MarginLayoutParams) eVar).topMargin + paddingTop;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int measuredHeight;
        int i3 = Build.VERSION.SDK_INT;
        C();
        measureChildWithMargins(this.f, i, 0, i2, 0);
        e eVar = (e) this.f.getLayoutParams();
        int iMax = Math.max(0, this.f.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar).leftMargin + ((ViewGroup.MarginLayoutParams) eVar).rightMargin);
        int iMax2 = Math.max(0, this.f.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar).topMargin + ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.f.getMeasuredState());
        boolean z = (ViewCompat.w(this) & MediaControl.ACTION_CUSTOM) != 0;
        if (z) {
            measuredHeight = this.f244c;
            if (this.k && this.f.b() != null) {
                measuredHeight += this.f244c;
            }
        } else {
            measuredHeight = this.f.getVisibility() != 8 ? this.f.getMeasuredHeight() : 0;
        }
        this.r.set(this.p);
        if (i3 >= 21) {
            this.y = this.w;
        } else {
            this.u.set(this.s);
        }
        if (!this.j && !z) {
            Rect rect = this.r;
            rect.top += measuredHeight;
            rect.bottom += 0;
            if (i3 >= 21) {
                this.y = this.y.i(0, measuredHeight, 0, 0);
            }
        } else if (i3 >= 21) {
            androidx.core.graphics.b bVarA = androidx.core.graphics.b.a(this.y.e(), this.y.g() + measuredHeight, this.y.f(), this.y.d() + 0);
            z.a aVar = new z.a(this.y);
            aVar.c(bVarA);
            this.y = aVar.a();
        } else {
            Rect rect2 = this.u;
            rect2.top += measuredHeight;
            rect2.bottom += 0;
        }
        s(this.e, this.r, true, true, true, true);
        if (i3 >= 21 && !this.z.equals(this.y)) {
            androidx.core.view.z zVar = this.y;
            this.z = zVar;
            ViewCompat.d(this.e, zVar);
        } else if (i3 < 21 && !this.v.equals(this.u)) {
            this.v.set(this.u);
            this.e.a(this.u);
        }
        measureChildWithMargins(this.e, i, 0, i2, 0);
        e eVar2 = (e) this.e.getLayoutParams();
        int iMax3 = Math.max(iMax, this.e.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) eVar2).leftMargin + ((ViewGroup.MarginLayoutParams) eVar2).rightMargin);
        int iMax4 = Math.max(iMax2, this.e.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) eVar2).topMargin + ((ViewGroup.MarginLayoutParams) eVar2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.e.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(iMax3 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(iMax4 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.n
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (!this.l || !z) {
            return false;
        }
        if (K(f2)) {
            r();
        } else {
            D();
        }
        this.m = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.n
    public boolean onNestedPreFling(View view, float f, float f2) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.n
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.n
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        int i5 = this.n + i2;
        this.n = i5;
        E(i5);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.n
    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.G.b(view, view2, i);
        this.n = v();
        x();
        d dVar = this.A;
        if (dVar != null) {
            dVar.c();
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.n
    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0 || this.f.getVisibility() != 0) {
            return false;
        }
        return this.l;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, androidx.core.view.n
    public void onStopNestedScroll(View view) {
        if (this.l && !this.m) {
            if (this.n <= this.f.getHeight()) {
                B();
            } else {
                A();
            }
        }
        d dVar = this.A;
        if (dVar != null) {
            dVar.f();
        }
    }

    @Override // android.view.View
    @Deprecated
    public void onWindowSystemUiVisibilityChanged(int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            super.onWindowSystemUiVisibilityChanged(i);
        }
        C();
        int i2 = this.o ^ i;
        this.o = i;
        boolean z = (i & 4) == 0;
        boolean z2 = (i & MediaControl.ACTION_CUSTOM) != 0;
        d dVar = this.A;
        if (dVar != null) {
            dVar.a(!z2);
            if (z || !z2) {
                this.A.b();
            } else {
                this.A.e();
            }
        }
        if ((i2 & MediaControl.ACTION_CUSTOM) == 0 || this.A == null) {
            return;
        }
        ViewCompat.J(this);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.d = i;
        d dVar = this.A;
        if (dVar != null) {
            dVar.d(i);
        }
    }

    @Override // androidx.core.view.l
    public void p(View view, int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            onNestedScroll(view, i, i2, i3, i4);
        }
    }

    @Override // androidx.core.view.l
    public boolean q(View view, View view2, int i, int i2) {
        return i2 == 0 && onStartNestedScroll(view, view2, i);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public e generateDefaultLayoutParams() {
        return new e(-1, -1);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: u, reason: merged with bridge method [inline-methods] */
    public e generateLayoutParams(AttributeSet attributeSet) {
        return new e(getContext(), attributeSet);
    }

    public int v() {
        ActionBarContainer actionBarContainer = this.f;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    void x() {
        removeCallbacks(this.E);
        removeCallbacks(this.F);
        ViewPropertyAnimator viewPropertyAnimator = this.C;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public boolean z() {
        return this.j;
    }

    public ActionBarOverlayLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = 0;
        this.p = new Rect();
        this.q = new Rect();
        this.r = new Rect();
        this.s = new Rect();
        this.t = new Rect();
        this.u = new Rect();
        this.v = new Rect();
        androidx.core.view.z zVar = androidx.core.view.z.f582b;
        this.w = zVar;
        this.x = zVar;
        this.y = zVar;
        this.z = zVar;
        this.D = new a();
        this.E = new b();
        this.F = new c();
        y(context);
        this.G = new androidx.core.view.o(this);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new e(layoutParams);
    }
}
