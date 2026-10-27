package androidx.core.widget;

import B1.b;
import B2.O;
import F1.A;
import F1.C0248g;
import F1.C0251j;
import F1.C0254m;
import F1.H;
import F1.InterfaceC0253l;
import H1.a;
import I1.c;
import I1.e;
import I1.f;
import I1.h;
import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import androidx.core.view.ScrollingView;
import com.bumptech.glide.d;

/* JADX INFO: loaded from: classes.dex */
public class NestedScrollView extends FrameLayout implements InterfaceC0253l, ScrollingView {

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public static final float f11751M = (float) (Math.log(0.78d) / Math.log(0.9d));

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public static final e f11752N = new e(0);

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public static final int[] f11753O = {R.attr.fillViewport};

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final int f11754A;
    public final int B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f11755C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final int[] f11756D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final int[] f11757E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public int f11758F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public int f11759G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public h f11760H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final C0254m f11761I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final C0251j f11762J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public float f11763K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final C0248g f11764L;
    public final float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f11765m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Rect f11766n;
    public final OverScroller o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final EdgeEffect f11767p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final EdgeEffect f11768q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f11769r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f11770s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f11771t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public View f11772u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f11773v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public VelocityTracker f11774w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f11775x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f11776y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int f11777z;

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, com.stingray.drivemodedetector.R.attr.nestedScrollViewStyle);
        this.f11766n = new Rect();
        this.f11770s = true;
        this.f11771t = false;
        this.f11772u = null;
        this.f11773v = false;
        this.f11776y = true;
        this.f11755C = -1;
        this.f11756D = new int[2];
        this.f11757E = new int[2];
        this.f11764L = new C0248g(getContext(), new O(this, 19));
        int i7 = Build.VERSION.SDK_INT;
        this.f11767p = i7 >= 31 ? c.a(context, attributeSet) : new EdgeEffect(context);
        this.f11768q = i7 >= 31 ? c.a(context, attributeSet) : new EdgeEffect(context);
        this.l = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        this.o = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f11777z = viewConfiguration.getScaledTouchSlop();
        this.f11754A = viewConfiguration.getScaledMinimumFlingVelocity();
        this.B = viewConfiguration.getScaledMaximumFlingVelocity();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f11753O, com.stingray.drivemodedetector.R.attr.nestedScrollViewStyle, 0);
        setFillViewport(typedArrayObtainStyledAttributes.getBoolean(0, false));
        typedArrayObtainStyledAttributes.recycle();
        this.f11761I = new C0254m(0);
        this.f11762J = new C0251j(this);
        setNestedScrollingEnabled(true);
        H.g(this, f11752N);
    }

    public static boolean k(View view, NestedScrollView nestedScrollView) {
        if (view == nestedScrollView) {
            return true;
        }
        Object parent = view.getParent();
        return (parent instanceof ViewGroup) && k((View) parent, nestedScrollView);
    }

    @Override // F1.InterfaceC0252k
    public final void a(View view, View view2, int i7, int i10) {
        C0254m c0254m = this.f11761I;
        if (i10 == 1) {
            c0254m.c = i7;
        } else {
            c0254m.f2343b = i7;
        }
        this.f11762J.g(2, i10);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    @Override // F1.InterfaceC0252k
    public final void b(View view, int i7) {
        C0254m c0254m = this.f11761I;
        if (i7 == 1) {
            c0254m.c = 0;
        } else {
            c0254m.f2343b = 0;
        }
        v(i7);
    }

    @Override // F1.InterfaceC0252k
    public final void c(View view, int i7, int i10, int[] iArr, int i11) {
        this.f11762J.c(i7, i10, i11, iArr, null);
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f9  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void computeScroll() {
        /*
            Method dump skipped, instruction units count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.computeScroll():void");
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override // android.view.View, androidx.core.view.ScrollingView
    public final int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int iMax = Math.max(0, bottom - height);
        return scrollY < 0 ? bottom - scrollY : scrollY > iMax ? bottom + (scrollY - iMax) : bottom;
    }

    public final boolean d(int i7) {
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i7);
        int maxScrollAmount = getMaxScrollAmount();
        if (viewFindNextFocus == null || !l(viewFindNextFocus, maxScrollAmount, getHeight())) {
            if (i7 == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i7 == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getHeight() + getScrollY()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i7 != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            r(maxScrollAmount, 0, 1, true);
        } else {
            Rect rect = this.f11766n;
            viewFindNextFocus.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(viewFindNextFocus, rect);
            r(e(rect), 0, 1, true);
            viewFindNextFocus.requestFocus(i7);
        }
        if (viewFindFocus != null && viewFindFocus.isFocused() && !l(viewFindFocus, 0, getHeight())) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(131072);
            requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00c9  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean dispatchKeyEvent(android.view.KeyEvent r7) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f10, float f11, boolean z6) {
        return this.f11762J.a(f10, f11, z6);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f10, float f11) {
        return this.f11762J.b(f10, f11);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i7, int i10, int[] iArr, int[] iArr2) {
        return this.f11762J.c(i7, i10, 0, iArr, iArr2);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i7, int i10, int i11, int i12, int[] iArr) {
        return this.f11762J.d(i7, i10, i11, i12, iArr, 0, null);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int paddingLeft;
        super.draw(canvas);
        int scrollY = getScrollY();
        EdgeEffect edgeEffect = this.f11767p;
        int paddingLeft2 = 0;
        if (!edgeEffect.isFinished()) {
            int iSave = canvas.save();
            int width = getWidth();
            int height = getHeight();
            int iMin = Math.min(0, scrollY);
            if (getClipToPadding()) {
                width -= getPaddingRight() + getPaddingLeft();
                paddingLeft = getPaddingLeft();
            } else {
                paddingLeft = 0;
            }
            if (getClipToPadding()) {
                height -= getPaddingBottom() + getPaddingTop();
                iMin += getPaddingTop();
            }
            canvas.translate(paddingLeft, iMin);
            edgeEffect.setSize(width, height);
            if (edgeEffect.draw(canvas)) {
                postInvalidateOnAnimation();
            }
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect2 = this.f11768q;
        if (edgeEffect2.isFinished()) {
            return;
        }
        int iSave2 = canvas.save();
        int width2 = getWidth();
        int height2 = getHeight();
        int iMax = Math.max(getScrollRange(), scrollY) + height2;
        if (getClipToPadding()) {
            width2 -= getPaddingRight() + getPaddingLeft();
            paddingLeft2 = getPaddingLeft();
        }
        if (getClipToPadding()) {
            height2 -= getPaddingBottom() + getPaddingTop();
            iMax -= getPaddingBottom();
        }
        canvas.translate(paddingLeft2 - width2, iMax);
        canvas.rotate(180.0f, width2, 0.0f);
        edgeEffect2.setSize(width2, height2);
        if (edgeEffect2.draw(canvas)) {
            postInvalidateOnAnimation();
        }
        canvas.restoreToCount(iSave2);
    }

    public final int e(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i7 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i10 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i7 - verticalFadingEdgeLength : i7;
        int i11 = rect.bottom;
        if (i11 > i10 && rect.top > scrollY) {
            return Math.min(rect.height() > height ? rect.top - scrollY : rect.bottom - i10, (childAt.getBottom() + layoutParams.bottomMargin) - i7);
        }
        if (rect.top >= scrollY || i11 >= i10) {
            return 0;
        }
        return Math.max(rect.height() > height ? 0 - (i10 - rect.bottom) : 0 - (scrollY - rect.top), -getScrollY());
    }

    public final void f(int i7) {
        if (getChildCount() > 0) {
            this.o.fling(getScrollX(), getScrollY(), 0, i7, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            this.f11762J.g(2, 1);
            this.f11759G = getScrollY();
            postInvalidateOnAnimation();
            if (b.a()) {
                a.a(this, Math.abs(this.o.getCurrVelocity()));
            }
        }
    }

    @Override // F1.InterfaceC0253l
    public final void g(View view, int i7, int i10, int i11, int i12, int i13, int[] iArr) {
        m(i12, i13, iArr);
    }

    @Override // android.view.View
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + layoutParams.bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return bottom / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (getHeight() * 0.5f);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        C0254m c0254m = this.f11761I;
        return c0254m.c | c0254m.f2343b;
    }

    public int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    @Override // android.view.View
    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return scrollY / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public float getVerticalScrollFactorCompat() {
        if (this.f11763K == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (!context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.f11763K = typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.f11763K;
    }

    @Override // F1.InterfaceC0252k
    public final void h(View view, int i7, int i10, int i11, int i12, int i13) {
        m(i12, i13, null);
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return this.f11762J.f(0);
    }

    @Override // F1.InterfaceC0252k
    public final boolean i(View view, View view2, int i7, int i10) {
        return (i7 & 2) != 0;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return this.f11762J.d;
    }

    public final boolean j(int i7) {
        int childCount;
        boolean z6 = i7 == 130;
        int height = getHeight();
        Rect rect = this.f11766n;
        rect.top = 0;
        rect.bottom = height;
        if (z6 && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            int paddingBottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            rect.bottom = paddingBottom;
            rect.top = paddingBottom - height;
        }
        return q(i7, rect.top, rect.bottom);
    }

    public final boolean l(View view, int i7, int i10) {
        Rect rect = this.f11766n;
        view.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(view, rect);
        return rect.bottom + i7 >= getScrollY() && rect.top - i7 <= getScrollY() + i10;
    }

    public final void m(int i7, int i10, int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i7);
        int scrollY2 = getScrollY() - scrollY;
        if (iArr != null) {
            iArr[1] = iArr[1] + scrollY2;
        }
        this.f11762J.d(0, scrollY2, 0, i7 - scrollY2, null, i10, iArr);
    }

    @Override // android.view.ViewGroup
    public final void measureChild(View view, int i7, int i10) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i7, getPaddingRight() + getPaddingLeft(), layoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    public final void measureChildWithMargins(View view, int i7, int i10, int i11, int i12) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i7, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i10, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public final void n(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f11755C) {
            int i7 = actionIndex == 0 ? 1 : 0;
            this.f11769r = (int) motionEvent.getY(i7);
            this.f11755C = motionEvent.getPointerId(i7);
            VelocityTracker velocityTracker = this.f11774w;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public final boolean o(int i7, int i10, int i11, int i12) {
        boolean z6;
        boolean z10;
        getOverScrollMode();
        super.computeHorizontalScrollRange();
        super.computeHorizontalScrollExtent();
        computeVerticalScrollRange();
        super.computeVerticalScrollExtent();
        int i13 = i11 + i7;
        if (i10 <= 0 && i10 >= 0) {
            z6 = false;
        } else {
            i10 = 0;
            z6 = true;
        }
        if (i13 > i12) {
            z10 = true;
        } else if (i13 < 0) {
            i12 = 0;
            z10 = true;
        } else {
            i12 = i13;
            z10 = false;
        }
        if (z10 && !this.f11762J.f(1)) {
            this.o.springBack(i10, i12, 0, 0, 0, getScrollRange());
        }
        super.scrollTo(i10, i12);
        return z6 || z10;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f11771t = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00d1  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onGenericMotionEvent(android.view.MotionEvent r27) {
        /*
            Method dump skipped, instruction units count: 837
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0119  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r13) {
        /*
            Method dump skipped, instruction units count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        int measuredHeight;
        super.onLayout(z6, i7, i10, i11, i12);
        int i13 = 0;
        this.f11770s = false;
        View view = this.f11772u;
        if (view != null && k(view, this)) {
            View view2 = this.f11772u;
            Rect rect = this.f11766n;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int iE = e(rect);
            if (iE != 0) {
                scrollBy(0, iE);
            }
        }
        this.f11772u = null;
        if (!this.f11771t) {
            if (this.f11760H != null) {
                scrollTo(getScrollX(), this.f11760H.l);
                this.f11760H = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                measuredHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            } else {
                measuredHeight = 0;
            }
            int paddingTop = ((i12 - i10) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            if (paddingTop < measuredHeight && scrollY >= 0) {
                i13 = paddingTop + scrollY > measuredHeight ? measuredHeight - paddingTop : scrollY;
            }
            if (i13 != scrollY) {
                scrollTo(getScrollX(), i13);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f11771t = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i7, int i10) {
        super.onMeasure(i7, i10);
        if (this.f11775x && View.MeasureSpec.getMode(i10) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(ViewGroup.getChildMeasureSpec(i7, getPaddingRight() + getPaddingLeft() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f10, float f11, boolean z6) {
        if (z6) {
            return false;
        }
        dispatchNestedFling(0.0f, f11, true);
        f((int) f11);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f10, float f11) {
        return this.f11762J.b(f10, f11);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i7, int i10, int[] iArr) {
        this.f11762J.c(i7, i10, 0, iArr, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i7, int i10, int i11, int i12) {
        m(i12, 0, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i7) {
        a(view, view2, i7, 0);
    }

    @Override // android.view.View
    public final void onOverScrolled(int i7, int i10, boolean z6, boolean z10) {
        super.scrollTo(i7, i10);
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i7, Rect rect) {
        if (i7 == 2) {
            i7 = 130;
        } else if (i7 == 1) {
            i7 = 33;
        }
        View viewFindNextFocus = rect == null ? FocusFinder.getInstance().findNextFocus(this, null, i7) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, i7);
        if (viewFindNextFocus != null && l(viewFindNextFocus, 0, getHeight())) {
            return viewFindNextFocus.requestFocus(i7, rect);
        }
        return false;
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof h)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        h hVar = (h) parcelable;
        super.onRestoreInstanceState(hVar.getSuperState());
        this.f11760H = hVar;
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        h hVar = new h(super.onSaveInstanceState());
        hVar.l = getScrollY();
        return hVar;
    }

    @Override // android.view.View
    public final void onScrollChanged(int i7, int i10, int i11, int i12) {
        super.onScrollChanged(i7, i10, i11, i12);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i7, int i10, int i11, int i12) {
        super.onSizeChanged(i7, i10, i11, i12);
        View viewFindFocus = findFocus();
        if (viewFindFocus == null || this == viewFindFocus || !l(viewFindFocus, 0, i12)) {
            return;
        }
        Rect rect = this.f11766n;
        viewFindFocus.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(viewFindFocus, rect);
        int iE = e(rect);
        if (iE != 0) {
            if (this.f11776y) {
                t(0, iE, false);
            } else {
                scrollBy(0, iE);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i7) {
        return i(view, view2, i7, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        b(view, 0);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        if (this.f11774w == null) {
            this.f11774w = VelocityTracker.obtain();
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f11758F = 0;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        float f10 = 0.0f;
        motionEventObtain.offsetLocation(0.0f, this.f11758F);
        C0251j c0251j = this.f11762J;
        if (actionMasked != 0) {
            EdgeEffect edgeEffect = this.f11768q;
            EdgeEffect edgeEffect2 = this.f11767p;
            if (actionMasked == 1) {
                VelocityTracker velocityTracker = this.f11774w;
                velocityTracker.computeCurrentVelocity(1000, this.B);
                int yVelocity = (int) velocityTracker.getYVelocity(this.f11755C);
                if (Math.abs(yVelocity) >= this.f11754A) {
                    if (d.C(edgeEffect2) != 0.0f) {
                        if (s(edgeEffect2, yVelocity)) {
                            edgeEffect2.onAbsorb(yVelocity);
                        } else {
                            f(-yVelocity);
                        }
                    } else if (d.C(edgeEffect) != 0.0f) {
                        int i7 = -yVelocity;
                        if (s(edgeEffect, i7)) {
                            edgeEffect.onAbsorb(i7);
                        } else {
                            f(i7);
                        }
                    } else {
                        int i10 = -yVelocity;
                        float f11 = i10;
                        if (!c0251j.b(0.0f, f11)) {
                            dispatchNestedFling(0.0f, f11, true);
                            f(i10);
                        }
                    }
                } else if (this.o.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    postInvalidateOnAnimation();
                }
                this.f11755C = -1;
                this.f11773v = false;
                VelocityTracker velocityTracker2 = this.f11774w;
                if (velocityTracker2 != null) {
                    velocityTracker2.recycle();
                    this.f11774w = null;
                }
                v(0);
                this.f11767p.onRelease();
                this.f11768q.onRelease();
            } else if (actionMasked == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.f11755C);
                if (iFindPointerIndex == -1) {
                    android.hardware.bydauto.ac.tsimpl.a.v(new StringBuilder("Invalid pointerId="), this.f11755C, " in onTouchEvent", "NestedScrollView");
                } else {
                    int y10 = (int) motionEvent.getY(iFindPointerIndex);
                    int i11 = this.f11769r - y10;
                    float x8 = motionEvent.getX(iFindPointerIndex) / getWidth();
                    float height = i11 / getHeight();
                    if (d.C(edgeEffect2) != 0.0f) {
                        float f12 = -d.K(edgeEffect2, -height, x8);
                        if (d.C(edgeEffect2) == 0.0f) {
                            edgeEffect2.onRelease();
                        }
                        f10 = f12;
                    } else if (d.C(edgeEffect) != 0.0f) {
                        float fK = d.K(edgeEffect, height, 1.0f - x8);
                        if (d.C(edgeEffect) == 0.0f) {
                            edgeEffect.onRelease();
                        }
                        f10 = fK;
                    }
                    int iRound = Math.round(f10 * getHeight());
                    if (iRound != 0) {
                        invalidate();
                    }
                    int i12 = i11 - iRound;
                    if (!this.f11773v && Math.abs(i12) > this.f11777z) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.f11773v = true;
                        i12 = i12 > 0 ? i12 - this.f11777z : i12 + this.f11777z;
                    }
                    if (this.f11773v) {
                        int iR = r(i12, (int) motionEvent.getX(iFindPointerIndex), 0, false);
                        this.f11769r = y10 - iR;
                        this.f11758F += iR;
                    }
                }
            } else if (actionMasked == 3) {
                if (this.f11773v && getChildCount() > 0 && this.o.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    postInvalidateOnAnimation();
                }
                this.f11755C = -1;
                this.f11773v = false;
                VelocityTracker velocityTracker3 = this.f11774w;
                if (velocityTracker3 != null) {
                    velocityTracker3.recycle();
                    this.f11774w = null;
                }
                v(0);
                this.f11767p.onRelease();
                this.f11768q.onRelease();
            } else if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                this.f11769r = (int) motionEvent.getY(actionIndex);
                this.f11755C = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                n(motionEvent);
                this.f11769r = (int) motionEvent.getY(motionEvent.findPointerIndex(this.f11755C));
            }
        } else {
            if (getChildCount() == 0) {
                return false;
            }
            if (this.f11773v && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.o.isFinished()) {
                this.o.abortAnimation();
                v(1);
            }
            int y11 = (int) motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            this.f11769r = y11;
            this.f11755C = pointerId;
            c0251j.g(2, 0);
        }
        VelocityTracker velocityTracker4 = this.f11774w;
        if (velocityTracker4 != null) {
            velocityTracker4.addMovement(motionEventObtain);
        }
        motionEventObtain.recycle();
        return true;
    }

    public final void p(int i7) {
        boolean z6 = i7 == 130;
        int height = getHeight();
        Rect rect = this.f11766n;
        if (z6) {
            rect.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                int paddingBottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
                if (rect.top + height > paddingBottom) {
                    rect.top = paddingBottom - height;
                }
            }
        } else {
            int scrollY = getScrollY() - height;
            rect.top = scrollY;
            if (scrollY < 0) {
                rect.top = 0;
            }
        }
        int i10 = rect.top;
        int i11 = height + i10;
        rect.bottom = i11;
        q(i7, i10, i11);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean q(int r18, int r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            int r4 = r17.getHeight()
            int r5 = r17.getScrollY()
            int r4 = r4 + r5
            r6 = 33
            if (r1 != r6) goto L17
            r6 = 1
            goto L18
        L17:
            r6 = 0
        L18:
            r9 = 2
            java.util.ArrayList r9 = r0.getFocusables(r9)
            int r10 = r9.size()
            r11 = 0
            r12 = 0
            r13 = 0
        L24:
            if (r12 >= r10) goto L6c
            java.lang.Object r14 = r9.get(r12)
            android.view.View r14 = (android.view.View) r14
            int r15 = r14.getTop()
            int r7 = r14.getBottom()
            if (r2 >= r7) goto L69
            if (r15 >= r3) goto L69
            if (r2 >= r15) goto L3f
            if (r7 >= r3) goto L3f
            r16 = 1
            goto L41
        L3f:
            r16 = 0
        L41:
            if (r11 != 0) goto L47
            r11 = r14
            r13 = r16
            goto L69
        L47:
            if (r6 == 0) goto L4f
            int r8 = r11.getTop()
            if (r15 < r8) goto L57
        L4f:
            if (r6 != 0) goto L59
            int r8 = r11.getBottom()
            if (r7 <= r8) goto L59
        L57:
            r7 = 1
            goto L5a
        L59:
            r7 = 0
        L5a:
            if (r13 == 0) goto L61
            if (r16 == 0) goto L69
            if (r7 == 0) goto L69
            goto L68
        L61:
            if (r16 == 0) goto L66
            r11 = r14
            r13 = 1
            goto L69
        L66:
            if (r7 == 0) goto L69
        L68:
            r11 = r14
        L69:
            int r12 = r12 + 1
            goto L24
        L6c:
            if (r11 != 0) goto L6f
            r11 = r0
        L6f:
            if (r2 < r5) goto L75
            if (r3 > r4) goto L75
            r7 = 0
            goto L82
        L75:
            if (r6 == 0) goto L7b
            int r2 = r2 - r5
        L78:
            r3 = 0
            r4 = 1
            goto L7e
        L7b:
            int r2 = r3 - r4
            goto L78
        L7e:
            r0.r(r2, r3, r4, r4)
            r7 = r4
        L82:
            android.view.View r0 = r17.findFocus()
            if (r11 == r0) goto L8b
            r11.requestFocus(r1)
        L8b:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.q(int, int, int):boolean");
    }

    public final int r(int i7, int i10, int i11, boolean z6) {
        int i12;
        int i13;
        boolean z10;
        VelocityTracker velocityTracker;
        C0251j c0251j = this.f11762J;
        if (i11 == 1) {
            c0251j.g(2, i11);
        }
        boolean zC = this.f11762J.c(0, i7, i11, this.f11757E, this.f11756D);
        int[] iArr = this.f11757E;
        int[] iArr2 = this.f11756D;
        if (zC) {
            i12 = i7 - iArr[1];
            i13 = iArr2[1];
        } else {
            i12 = i7;
            i13 = 0;
        }
        int scrollY = getScrollY();
        int scrollRange = getScrollRange();
        int overScrollMode = getOverScrollMode();
        boolean z11 = (overScrollMode == 0 || (overScrollMode == 1 && getScrollRange() > 0)) && !z6;
        boolean z12 = o(i12, 0, scrollY, scrollRange) && !c0251j.f(i11);
        int scrollY2 = getScrollY() - scrollY;
        iArr[1] = 0;
        this.f11762J.d(0, scrollY2, 0, i12 - scrollY2, this.f11756D, i11, iArr);
        int i14 = i13 + iArr2[1];
        int i15 = i12 - iArr[1];
        int i16 = scrollY + i15;
        EdgeEffect edgeEffect = this.f11768q;
        EdgeEffect edgeEffect2 = this.f11767p;
        if (i16 < 0) {
            if (z11) {
                d.K(edgeEffect2, (-i15) / getHeight(), i10 / getWidth());
                if (!edgeEffect.isFinished()) {
                    edgeEffect.onRelease();
                }
            }
        } else if (i16 > scrollRange && z11) {
            d.K(edgeEffect, i15 / getHeight(), 1.0f - (i10 / getWidth()));
            if (!edgeEffect2.isFinished()) {
                edgeEffect2.onRelease();
            }
        }
        if (edgeEffect2.isFinished() && edgeEffect.isFinished()) {
            z10 = z12;
        } else {
            postInvalidateOnAnimation();
            z10 = false;
        }
        if (z10 && i11 == 0 && (velocityTracker = this.f11774w) != null) {
            velocityTracker.clear();
        }
        if (i11 == 1) {
            v(i11);
            edgeEffect2.onRelease();
            edgeEffect.onRelease();
        }
        return i14;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        if (this.f11770s) {
            this.f11772u = view2;
        } else {
            Rect rect = this.f11766n;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int iE = e(rect);
            if (iE != 0) {
                scrollBy(0, iE);
            }
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z6) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        int iE = e(rect);
        boolean z10 = iE != 0;
        if (z10) {
            if (z6) {
                scrollBy(0, iE);
            } else {
                t(0, iE, false);
            }
        }
        return z10;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z6) {
        VelocityTracker velocityTracker;
        if (z6 && (velocityTracker = this.f11774w) != null) {
            velocityTracker.recycle();
            this.f11774w = null;
        }
        super.requestDisallowInterceptTouchEvent(z6);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        this.f11770s = true;
        super.requestLayout();
    }

    public final boolean s(EdgeEffect edgeEffect, int i7) {
        if (i7 > 0) {
            return true;
        }
        float fC = d.C(edgeEffect) * getHeight();
        float fAbs = Math.abs(-i7) * 0.35f;
        float f10 = this.l * 0.015f;
        double dLog = Math.log(fAbs / f10);
        double d = f11751M;
        return ((float) (Math.exp((d / (d - 1.0d)) * dLog) * ((double) f10))) < fC;
    }

    @Override // android.view.View
    public final void scrollTo(int i7, int i10) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int width2 = childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int height2 = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (width >= width2 || i7 < 0) {
                i7 = 0;
            } else if (width + i7 > width2) {
                i7 = width2 - width;
            }
            if (height >= height2 || i10 < 0) {
                i10 = 0;
            } else if (height + i10 > height2) {
                i10 = height2 - height;
            }
            if (i7 == getScrollX() && i10 == getScrollY()) {
                return;
            }
            super.scrollTo(i7, i10);
        }
    }

    public void setFillViewport(boolean z6) {
        if (z6 != this.f11775x) {
            this.f11775x = z6;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z6) {
        C0251j c0251j = this.f11762J;
        if (c0251j.d) {
            int[] iArr = H.f2303a;
            A.l(c0251j.c);
        }
        c0251j.d = z6;
    }

    public void setOnScrollChangeListener(f fVar) {
    }

    public void setSmoothScrollingEnabled(boolean z6) {
        this.f11776y = z6;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i7) {
        return this.f11762J.g(i7, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        v(0);
    }

    public final void t(int i7, int i10, boolean z6) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.f11765m > 250) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            this.o.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(i10 + scrollY, Math.max(0, height - height2))) - scrollY, 250);
            if (z6) {
                this.f11762J.g(2, 1);
            } else {
                v(1);
            }
            this.f11759G = getScrollY();
            postInvalidateOnAnimation();
        } else {
            if (!this.o.isFinished()) {
                this.o.abortAnimation();
                v(1);
            }
            scrollBy(i7, i10);
        }
        this.f11765m = AnimationUtils.currentAnimationTimeMillis();
    }

    public final boolean u(MotionEvent motionEvent) {
        boolean z6;
        EdgeEffect edgeEffect = this.f11767p;
        if (d.C(edgeEffect) != 0.0f) {
            d.K(edgeEffect, 0.0f, motionEvent.getX() / getWidth());
            z6 = true;
        } else {
            z6 = false;
        }
        EdgeEffect edgeEffect2 = this.f11768q;
        if (d.C(edgeEffect2) == 0.0f) {
            return z6;
        }
        d.K(edgeEffect2, 0.0f, 1.0f - (motionEvent.getX() / getWidth()));
        return true;
    }

    public final void v(int i7) {
        this.f11762J.h(i7);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7) {
        if (getChildCount() <= 0) {
            super.addView(view, i7);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i7, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
}
