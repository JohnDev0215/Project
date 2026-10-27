package androidx.core.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
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
import defpackage.AbstractC1251o3;
import defpackage.C0862h3;
import defpackage.C0973j3;
import defpackage.C1690w3;
import defpackage.C1745x3;
import defpackage.InterfaceC0918i3;
import defpackage.Z7;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NestedScrollView extends FrameLayout implements InterfaceC0918i3 {
    public static final C1690w3 R = new C1690w3();
    public static final int[] S = {R.attr.fillViewport};
    public View A;
    public boolean B;
    public VelocityTracker C;
    public boolean D;
    public boolean E;
    public int F;
    public int G;
    public int H;
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final int[] f18J;
    public final int[] K;
    public int L;
    public int M;
    public SavedState N;
    public final C0973j3 O;
    public final C0862h3 P;
    public float Q;
    public long s;
    public final Rect t;
    public OverScroller u;
    public EdgeEffect v;
    public EdgeEffect w;
    public int x;
    public boolean y;
    public boolean z;

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new C1745x3();
        public int s;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder sbF = Z7.f("HorizontalScrollView.SavedState{");
            sbF.append(Integer.toHexString(System.identityHashCode(this)));
            sbF.append(" scrollPosition=");
            sbF.append(this.s);
            sbF.append("}");
            return sbF.toString();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.s);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.s = parcel.readInt();
        }
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.t = new Rect();
        this.y = true;
        this.z = false;
        this.A = null;
        this.B = false;
        this.E = true;
        this.I = -1;
        this.f18J = new int[2];
        this.K = new int[2];
        this.u = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(com.android.webview.R.bool.abc_action_bar_embed_tabs);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.F = viewConfiguration.getScaledTouchSlop();
        this.G = viewConfiguration.getScaledMinimumFlingVelocity();
        this.H = viewConfiguration.getScaledMaximumFlingVelocity();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, S, 0, 0);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(0, false);
        if (z != this.D) {
            this.D = z;
            requestLayout();
        }
        typedArrayObtainStyledAttributes.recycle();
        this.O = new C0973j3(this);
        this.P = new C0862h3(this);
        setNestedScrollingEnabled(true);
        AbstractC1251o3.B(this, R);
    }

    public static int g(int i, int i2, int i3) {
        if (i2 >= i3 || i < 0) {
            return 0;
        }
        return i2 + i > i3 ? i3 - i2 : i;
    }

    public static boolean r(View view, View view2) {
        if (view == view2) {
            return true;
        }
        Object parent = view.getParent();
        return (parent instanceof ViewGroup) && r((View) parent, view2);
    }

    public void A(int i) {
        this.P.h(i);
    }

    @Override // defpackage.InterfaceC0918i3
    public void a(View view, View view2, int i, int i2) {
        this.O.a = i;
        z(2, i2);
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    @Override // defpackage.InterfaceC0918i3
    public void b(View view, int i) {
        this.O.a = 0;
        this.P.h(i);
    }

    @Override // defpackage.InterfaceC0918i3
    public void c(View view, int i, int i2, int[] iArr, int i3) {
        i(i, i2, iArr, null, i3);
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @Override // android.view.View
    public void computeScroll() {
        boolean z = false;
        if (!this.u.computeScrollOffset()) {
            if (q(1)) {
                this.P.h(1);
            }
            this.M = 0;
            return;
        }
        this.u.getCurrX();
        int currY = this.u.getCurrY();
        int i = currY - this.M;
        if (i(0, i, this.K, null, 1)) {
            i -= this.K[1];
        }
        if (i != 0) {
            int iP = p();
            int scrollY = getScrollY();
            u(0, i, getScrollX(), scrollY, 0, iP, 0, 0);
            int scrollY2 = getScrollY() - scrollY;
            if (!j(0, scrollY2, 0, i - scrollY2, null, 1)) {
                int overScrollMode = getOverScrollMode();
                if (overScrollMode == 0 || (overScrollMode == 1 && iP > 0)) {
                    z = true;
                }
                if (z) {
                    m();
                    if (currY <= 0 && scrollY > 0) {
                        this.v.onAbsorb((int) this.u.getCurrVelocity());
                    } else if (currY >= iP && scrollY < iP) {
                        this.w.onAbsorb((int) this.u.getCurrVelocity());
                    }
                }
            }
        }
        this.M = currY;
        AbstractC1251o3.y(this);
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
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

    @Override // defpackage.InterfaceC0918i3
    public void d(View view, int i, int i2, int i3, int i4, int i5) {
        int scrollY = getScrollY();
        scrollBy(0, i4);
        int scrollY2 = getScrollY() - scrollY;
        j(0, scrollY2, 0, i4 - scrollY2, null, i5);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0039  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchKeyEvent(android.view.KeyEvent r7) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.P.a(f, f2, z);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.P.b(f, f2);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return i(i, i2, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return j(i, i2, i3, i4, iArr, 0);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int paddingLeft;
        super.draw(canvas);
        if (this.v != null) {
            int scrollY = getScrollY();
            int paddingLeft2 = 0;
            if (!this.v.isFinished()) {
                int iSave = canvas.save();
                int width = getWidth();
                int height = getHeight();
                int iMin = Math.min(0, scrollY);
                if (getClipToPadding()) {
                    width -= getPaddingRight() + getPaddingLeft();
                    paddingLeft = getPaddingLeft() + 0;
                } else {
                    paddingLeft = 0;
                }
                if (getClipToPadding()) {
                    height -= getPaddingBottom() + getPaddingTop();
                    iMin += getPaddingTop();
                }
                canvas.translate(paddingLeft, iMin);
                this.v.setSize(width, height);
                if (this.v.draw(canvas)) {
                    AbstractC1251o3.y(this);
                }
                canvas.restoreToCount(iSave);
            }
            if (this.w.isFinished()) {
                return;
            }
            int iSave2 = canvas.save();
            int width2 = getWidth();
            int height2 = getHeight();
            int iMax = Math.max(p(), scrollY) + height2;
            if (getClipToPadding()) {
                width2 -= getPaddingRight() + getPaddingLeft();
                paddingLeft2 = 0 + getPaddingLeft();
            }
            if (getClipToPadding()) {
                height2 -= getPaddingBottom() + getPaddingTop();
                iMax -= getPaddingBottom();
            }
            canvas.translate(paddingLeft2 - width2, iMax);
            canvas.rotate(180.0f, width2, 0.0f);
            this.w.setSize(width2, height2);
            if (this.w.draw(canvas)) {
                AbstractC1251o3.y(this);
            }
            canvas.restoreToCount(iSave2);
        }
    }

    @Override // defpackage.InterfaceC0918i3
    public boolean e(View view, View view2, int i, int i2) {
        return (i & 2) != 0;
    }

    public boolean f(int i) {
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i);
        int height = (int) (getHeight() * 0.5f);
        if (viewFindNextFocus == null || !s(viewFindNextFocus, height, getHeight())) {
            if (i == 33 && getScrollY() < height) {
                height = getScrollY();
            } else if (i == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                height = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getHeight() + getScrollY()) - getPaddingBottom()), height);
            }
            if (height == 0) {
                return false;
            }
            if (i != 130) {
                height = -height;
            }
            k(height);
        } else {
            viewFindNextFocus.getDrawingRect(this.t);
            offsetDescendantRectToMyCoords(viewFindNextFocus, this.t);
            k(h(this.t));
            viewFindNextFocus.requestFocus(i);
        }
        if (viewFindFocus != null && viewFindFocus.isFocused() && (!s(viewFindFocus, 0, getHeight()))) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(com.android.webview.R.animator.design_appbar_state_list_animator);
            requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
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

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.O.a;
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

    public int h(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i2 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i - verticalFadingEdgeLength : i;
        if (rect.bottom > i2 && rect.top > scrollY) {
            return Math.min((rect.height() > height ? rect.top - scrollY : rect.bottom - i2) + 0, (childAt.getBottom() + layoutParams.bottomMargin) - i);
        }
        if (rect.top >= scrollY || rect.bottom >= i2) {
            return 0;
        }
        return Math.max(rect.height() > height ? 0 - (i2 - rect.bottom) : 0 - (scrollY - rect.top), -getScrollY());
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return q(0);
    }

    public boolean i(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        return this.P.c(i, i2, iArr, iArr2, i3);
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.P.d;
    }

    public boolean j(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        return this.P.d(i, i2, i3, i4, iArr, i5);
    }

    public final void k(int i) {
        if (i != 0) {
            if (this.E) {
                y(0, i);
            } else {
                scrollBy(0, i);
            }
        }
    }

    public final void l() {
        this.B = false;
        v();
        this.P.h(0);
        EdgeEffect edgeEffect = this.v;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            this.w.onRelease();
        }
    }

    public final void m() {
        if (getOverScrollMode() == 2) {
            this.v = null;
            this.w = null;
        } else if (this.v == null) {
            Context context = getContext();
            this.v = new EdgeEffect(context);
            this.w = new EdgeEffect(context);
        }
    }

    @Override // android.view.ViewGroup
    public void measureChild(View view, int i, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.measure(FrameLayout.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft(), layoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    public void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(FrameLayout.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    public final void n(int i) {
        int scrollY = getScrollY();
        boolean z = (scrollY > 0 || i > 0) && (scrollY < p() || i < 0);
        float f = i;
        if (dispatchNestedPreFling(0.0f, f)) {
            return;
        }
        dispatchNestedFling(0.0f, f, z);
        if (getChildCount() > 0) {
            z(2, 1);
            this.u.fling(getScrollX(), getScrollY(), 0, i, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            this.M = getScrollY();
            AbstractC1251o3.y(this);
        }
    }

    public boolean o(int i) {
        int childCount;
        boolean z = i == 130;
        int height = getHeight();
        Rect rect = this.t;
        rect.top = 0;
        rect.bottom = height;
        if (z && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            this.t.bottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            Rect rect2 = this.t;
            rect2.top = rect2.bottom - height;
        }
        Rect rect3 = this.t;
        return w(i, rect3.top, rect3.bottom);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.z = false;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.B) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                if (this.Q == 0.0f) {
                    TypedValue typedValue = new TypedValue();
                    Context context = getContext();
                    if (!context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                        throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
                    }
                    this.Q = typedValue.getDimension(context.getResources().getDisplayMetrics());
                }
                int i = (int) (axisValue * this.Q);
                int iP = p();
                int scrollY = getScrollY();
                int i2 = scrollY - i;
                if (i2 < 0) {
                    iP = 0;
                } else if (i2 <= iP) {
                    iP = i2;
                }
                if (iP != scrollY) {
                    super.scrollTo(getScrollX(), iP);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e0  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r12) {
        /*
            Method dump skipped, instruction units count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int measuredHeight = 0;
        this.y = false;
        View view = this.A;
        if (view != null && r(view, this)) {
            x(this.A);
        }
        this.A = null;
        if (!this.z) {
            if (this.N != null) {
                scrollTo(getScrollX(), this.N.s);
                this.N = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                measuredHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            }
            int paddingTop = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            int iG = g(scrollY, paddingTop, measuredHeight);
            if (iG != scrollY) {
                scrollTo(getScrollX(), iG);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.z = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.D && View.MeasureSpec.getMode(i2) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(FrameLayout.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        if (z) {
            return false;
        }
        n((int) f2);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f, float f2) {
        return dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        i(i, i2, iArr, null, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        d(view, i, i2, i3, i4, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        this.O.a = i;
        z(2, 0);
    }

    @Override // android.view.View
    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.scrollTo(i, i2);
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i, Rect rect) {
        if (i == 2) {
            i = 130;
        } else if (i == 1) {
            i = 33;
        }
        View viewFindNextFocus = rect == null ? FocusFinder.getInstance().findNextFocus(this, null, i) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, i);
        if (viewFindNextFocus == null || (true ^ s(viewFindNextFocus, 0, getHeight()))) {
            return false;
        }
        return viewFindNextFocus.requestFocus(i, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.N = savedState;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.s = getScrollY();
        return savedState;
    }

    @Override // android.view.View
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        View viewFindFocus = findFocus();
        if (viewFindFocus == null || this == viewFindFocus || !s(viewFindFocus, 0, i4)) {
            return;
        }
        viewFindFocus.getDrawingRect(this.t);
        offsetDescendantRectToMyCoords(viewFindFocus, this.t);
        k(h(this.t));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return (i & 2) != 0;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        this.O.a = 0;
        A(0);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        if (this.C == null) {
            this.C = VelocityTracker.obtain();
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.L = 0;
        }
        motionEventObtain.offsetLocation(0.0f, this.L);
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                VelocityTracker velocityTracker = this.C;
                velocityTracker.computeCurrentVelocity(1000, this.H);
                int yVelocity = (int) velocityTracker.getYVelocity(this.I);
                if (Math.abs(yVelocity) > this.G) {
                    n(-yVelocity);
                } else if (this.u.springBack(getScrollX(), getScrollY(), 0, 0, 0, p())) {
                    AbstractC1251o3.y(this);
                }
                this.I = -1;
                l();
            } else if (actionMasked == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.I);
                if (iFindPointerIndex == -1) {
                    StringBuilder sbF = Z7.f("Invalid pointerId=");
                    sbF.append(this.I);
                    sbF.append(" in onTouchEvent");
                    Log.e("NestedScrollView", sbF.toString());
                } else {
                    int y = (int) motionEvent.getY(iFindPointerIndex);
                    int i = this.x - y;
                    if (i(0, i, this.K, this.f18J, 0)) {
                        i -= this.K[1];
                        motionEventObtain.offsetLocation(0.0f, this.f18J[1]);
                        this.L += this.f18J[1];
                    }
                    if (!this.B && Math.abs(i) > this.F) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.B = true;
                        i = i > 0 ? i - this.F : i + this.F;
                    }
                    int i2 = i;
                    if (this.B) {
                        this.x = y - this.f18J[1];
                        int scrollY = getScrollY();
                        int iP = p();
                        int overScrollMode = getOverScrollMode();
                        boolean z = overScrollMode == 0 || (overScrollMode == 1 && iP > 0);
                        if (u(0, i2, 0, getScrollY(), 0, iP, 0, 0) && !q(0)) {
                            this.C.clear();
                        }
                        int scrollY2 = getScrollY() - scrollY;
                        if (j(0, scrollY2, 0, i2 - scrollY2, this.f18J, 0)) {
                            this.x = this.x - this.f18J[1];
                            motionEventObtain.offsetLocation(0.0f, r1[1]);
                            this.L += this.f18J[1];
                        } else if (z) {
                            m();
                            int i3 = scrollY + i2;
                            if (i3 < 0) {
                                this.v.onPull(i2 / getHeight(), motionEvent.getX(iFindPointerIndex) / getWidth());
                                if (!this.w.isFinished()) {
                                    this.w.onRelease();
                                }
                            } else if (i3 > iP) {
                                this.w.onPull(i2 / getHeight(), 1.0f - (motionEvent.getX(iFindPointerIndex) / getWidth()));
                                if (!this.v.isFinished()) {
                                    this.v.onRelease();
                                }
                            }
                            EdgeEffect edgeEffect = this.v;
                            if (edgeEffect != null && (!edgeEffect.isFinished() || !this.w.isFinished())) {
                                AbstractC1251o3.y(this);
                            }
                        }
                    }
                }
            } else if (actionMasked == 3) {
                if (this.B && getChildCount() > 0 && this.u.springBack(getScrollX(), getScrollY(), 0, 0, 0, p())) {
                    AbstractC1251o3.y(this);
                }
                this.I = -1;
                l();
            } else if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                this.x = (int) motionEvent.getY(actionIndex);
                this.I = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                t(motionEvent);
                this.x = (int) motionEvent.getY(motionEvent.findPointerIndex(this.I));
            }
        } else {
            if (getChildCount() == 0) {
                return false;
            }
            boolean z2 = !this.u.isFinished();
            this.B = z2;
            if (z2 && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.u.isFinished()) {
                this.u.abortAnimation();
            }
            this.x = (int) motionEvent.getY();
            this.I = motionEvent.getPointerId(0);
            z(2, 0);
        }
        VelocityTracker velocityTracker2 = this.C;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEventObtain);
        }
        motionEventObtain.recycle();
        return true;
    }

    public int p() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    public boolean q(int i) {
        return this.P.e(i) != null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (this.y) {
            this.A = view2;
        } else {
            x(view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        int iH = h(rect);
        boolean z2 = iH != 0;
        if (z2) {
            if (z) {
                scrollBy(0, iH);
            } else {
                y(0, iH);
            }
        }
        return z2;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (z) {
            v();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.y = true;
        super.requestLayout();
    }

    public final boolean s(View view, int i, int i2) {
        view.getDrawingRect(this.t);
        offsetDescendantRectToMyCoords(view, this.t);
        return this.t.bottom + i >= getScrollY() && this.t.top - i <= getScrollY() + i2;
    }

    @Override // android.view.View
    public void scrollTo(int i, int i2) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int width2 = childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int height2 = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int iG = g(i, width, width2);
            int iG2 = g(i2, height, height2);
            if (iG == getScrollX() && iG2 == getScrollY()) {
                return;
            }
            super.scrollTo(iG, iG2);
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z) {
        C0862h3 c0862h3 = this.P;
        if (c0862h3.d) {
            AbstractC1251o3.J(c0862h3.c);
        }
        c0862h3.d = z;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i) {
        return this.P.g(i, 0);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        this.P.h(0);
    }

    public final void t(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.I) {
            int i = actionIndex == 0 ? 1 : 0;
            this.x = (int) motionEvent.getY(i);
            this.I = motionEvent.getPointerId(i);
            VelocityTracker velocityTracker = this.C;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0083 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean u(int r13, int r14, int r15, int r16, int r17, int r18, int r19, int r20) {
        /*
            r12 = this;
            r0 = r12
            int r1 = r12.getOverScrollMode()
            int r2 = r12.computeHorizontalScrollRange()
            int r3 = r12.computeHorizontalScrollExtent()
            r4 = 0
            r5 = 1
            if (r2 <= r3) goto L13
            r2 = 1
            goto L14
        L13:
            r2 = 0
        L14:
            int r3 = r12.computeVerticalScrollRange()
            int r6 = r12.computeVerticalScrollExtent()
            if (r3 <= r6) goto L20
            r3 = 1
            goto L21
        L20:
            r3 = 0
        L21:
            if (r1 == 0) goto L2a
            if (r1 != r5) goto L28
            if (r2 == 0) goto L28
            goto L2a
        L28:
            r2 = 0
            goto L2b
        L2a:
            r2 = 1
        L2b:
            if (r1 == 0) goto L34
            if (r1 != r5) goto L32
            if (r3 == 0) goto L32
            goto L34
        L32:
            r1 = 0
            goto L35
        L34:
            r1 = 1
        L35:
            int r3 = r15 + r13
            if (r2 != 0) goto L3b
            r2 = 0
            goto L3d
        L3b:
            r2 = r19
        L3d:
            int r6 = r16 + r14
            if (r1 != 0) goto L43
            r1 = 0
            goto L45
        L43:
            r1 = r20
        L45:
            int r7 = -r2
            int r2 = r2 + r17
            int r8 = -r1
            int r1 = r1 + r18
            if (r3 <= r2) goto L50
            r3 = r2
        L4e:
            r2 = 1
            goto L55
        L50:
            if (r3 >= r7) goto L54
            r3 = r7
            goto L4e
        L54:
            r2 = 0
        L55:
            if (r6 <= r1) goto L5a
            r6 = r1
        L58:
            r1 = 1
            goto L5f
        L5a:
            if (r6 >= r8) goto L5e
            r6 = r8
            goto L58
        L5e:
            r1 = 0
        L5f:
            if (r1 == 0) goto L7e
            boolean r7 = r12.q(r5)
            if (r7 != 0) goto L7e
            android.widget.OverScroller r7 = r0.u
            r8 = 0
            r9 = 0
            r10 = 0
            int r11 = r12.p()
            r13 = r7
            r14 = r3
            r15 = r6
            r16 = r8
            r17 = r9
            r18 = r10
            r19 = r11
            r13.springBack(r14, r15, r16, r17, r18, r19)
        L7e:
            r12.onOverScrolled(r3, r6, r2, r1)
            if (r2 != 0) goto L85
            if (r1 == 0) goto L86
        L85:
            r4 = 1
        L86:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.u(int, int, int, int, int, int, int, int):boolean");
    }

    public final void v() {
        VelocityTracker velocityTracker = this.C;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.C = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean w(int r18, int r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            int r4 = r17.getHeight()
            int r5 = r17.getScrollY()
            int r4 = r4 + r5
            r8 = 33
            if (r1 != r8) goto L17
            r8 = 1
            goto L18
        L17:
            r8 = 0
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
            int r6 = r14.getBottom()
            if (r2 >= r6) goto L69
            if (r15 >= r3) goto L69
            if (r2 >= r15) goto L3f
            if (r6 >= r3) goto L3f
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
            if (r8 == 0) goto L4f
            int r7 = r11.getTop()
            if (r15 < r7) goto L57
        L4f:
            if (r8 != 0) goto L59
            int r7 = r11.getBottom()
            if (r6 <= r7) goto L59
        L57:
            r6 = 1
            goto L5a
        L59:
            r6 = 0
        L5a:
            if (r13 == 0) goto L61
            if (r16 == 0) goto L69
            if (r6 == 0) goto L69
            goto L68
        L61:
            if (r16 == 0) goto L66
            r11 = r14
            r13 = 1
            goto L69
        L66:
            if (r6 == 0) goto L69
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
            r6 = 0
            goto L7f
        L75:
            if (r8 == 0) goto L79
            int r2 = r2 - r5
            goto L7b
        L79:
            int r2 = r3 - r4
        L7b:
            r0.k(r2)
            r6 = 1
        L7f:
            android.view.View r2 = r17.findFocus()
            if (r11 == r2) goto L88
            r11.requestFocus(r1)
        L88:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.w(int, int, int):boolean");
    }

    public final void x(View view) {
        view.getDrawingRect(this.t);
        offsetDescendantRectToMyCoords(view, this.t);
        int iH = h(this.t);
        if (iH != 0) {
            scrollBy(0, iH);
        }
    }

    public final void y(int i, int i2) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.s > 250) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            int iMax = Math.max(0, Math.min(i2 + scrollY, Math.max(0, height - height2))) - scrollY;
            this.M = getScrollY();
            this.u.startScroll(getScrollX(), scrollY, 0, iMax);
            AbstractC1251o3.y(this);
        } else {
            if (!this.u.isFinished()) {
                this.u.abortAnimation();
            }
            scrollBy(i, i2);
        }
        this.s = AnimationUtils.currentAnimationTimeMillis();
    }

    public boolean z(int i, int i2) {
        return this.P.g(i, i2);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i) {
        if (getChildCount() <= 0) {
            super.addView(view, i);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
}
