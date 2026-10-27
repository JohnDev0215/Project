package androidx.core.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import i0.k;
import i0.l;
import i0.n;
import i0.o;
import i0.u;
import j0.c;

/* JADX INFO: loaded from: classes.dex */
public class NestedScrollView extends FrameLayout implements n, k {
    private static final a F = new a();
    private static final int[] G = {R.attr.fillViewport};
    private SavedState A;
    private final o B;
    private final l C;
    private float D;
    private b E;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f1966e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Rect f1967g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private OverScroller f1968h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private EdgeEffect f1969i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private EdgeEffect f1970j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f1971k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f1972l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f1973m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private View f1974n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private boolean f1975o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private VelocityTracker f1976p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private boolean f1977q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f1978r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private int f1979s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private int f1980t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private int f1981u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private int f1982v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    private final int[] f1983w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    private final int[] f1984x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    private int f1985y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private int f1986z;

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1987e;

        class a implements Parcelable.Creator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i7) {
                return new SavedState[i7];
            }
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.f1987e + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i7) {
            super.writeToParcel(parcel, i7);
            parcel.writeInt(this.f1987e);
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.f1987e = parcel.readInt();
        }
    }

    static class a extends i0.a {
        a() {
        }

        @Override // i0.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            accessibilityEvent.setClassName(ScrollView.class.getName());
            accessibilityEvent.setScrollable(nestedScrollView.getScrollRange() > 0);
            accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
            accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
            j0.e.a(accessibilityEvent, nestedScrollView.getScrollX());
            j0.e.b(accessibilityEvent, nestedScrollView.getScrollRange());
        }

        @Override // i0.a
        public void g(View view, j0.c cVar) {
            int scrollRange;
            super.g(view, cVar);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            cVar.P(ScrollView.class.getName());
            if (!nestedScrollView.isEnabled() || (scrollRange = nestedScrollView.getScrollRange()) <= 0) {
                return;
            }
            cVar.h0(true);
            if (nestedScrollView.getScrollY() > 0) {
                cVar.b(c.a.f7126r);
                cVar.b(c.a.C);
            }
            if (nestedScrollView.getScrollY() < scrollRange) {
                cVar.b(c.a.f7125q);
                cVar.b(c.a.E);
            }
        }

        @Override // i0.a
        public boolean j(View view, int i7, Bundle bundle) {
            if (super.j(view, i7, bundle)) {
                return true;
            }
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            if (!nestedScrollView.isEnabled()) {
                return false;
            }
            if (i7 != 4096) {
                if (i7 == 8192 || i7 == 16908344) {
                    int iMax = Math.max(nestedScrollView.getScrollY() - ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
                    if (iMax == nestedScrollView.getScrollY()) {
                        return false;
                    }
                    nestedScrollView.O(0, iMax, true);
                    return true;
                }
                if (i7 != 16908346) {
                    return false;
                }
            }
            int iMin = Math.min(nestedScrollView.getScrollY() + ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), nestedScrollView.getScrollRange());
            if (iMin == nestedScrollView.getScrollY()) {
                return false;
            }
            nestedScrollView.O(0, iMin, true);
            return true;
        }
    }

    public interface b {
        void a(NestedScrollView nestedScrollView, int i7, int i8, int i9, int i10);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private static boolean A(View view, View view2) {
        if (view == view2) {
            return true;
        }
        Object parent = view.getParent();
        return (parent instanceof ViewGroup) && A((View) parent, view2);
    }

    private boolean B(View view, int i7, int i8) {
        view.getDrawingRect(this.f1967g);
        offsetDescendantRectToMyCoords(view, this.f1967g);
        return this.f1967g.bottom + i7 >= getScrollY() && this.f1967g.top - i7 <= getScrollY() + i8;
    }

    private void C(int i7, int i8, int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i7);
        int scrollY2 = getScrollY() - scrollY;
        if (iArr != null) {
            iArr[1] = iArr[1] + scrollY2;
        }
        this.C.e(0, scrollY2, 0, i7 - scrollY2, null, i8, iArr);
    }

    private void D(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f1982v) {
            int i7 = actionIndex == 0 ? 1 : 0;
            this.f1971k = (int) motionEvent.getY(i7);
            this.f1982v = motionEvent.getPointerId(i7);
            VelocityTracker velocityTracker = this.f1976p;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private void G() {
        VelocityTracker velocityTracker = this.f1976p;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f1976p = null;
        }
    }

    private void H(boolean z6) {
        if (z6) {
            P(2, 1);
        } else {
            Q(1);
        }
        this.f1986z = getScrollY();
        u.b0(this);
    }

    private boolean I(int i7, int i8, int i9) {
        int height = getHeight();
        int scrollY = getScrollY();
        int i10 = height + scrollY;
        boolean z6 = false;
        boolean z7 = i7 == 33;
        View viewR = r(z7, i8, i9);
        if (viewR == null) {
            viewR = this;
        }
        if (i8 < scrollY || i9 > i10) {
            k(z7 ? i8 - scrollY : i9 - i10);
            z6 = true;
        }
        if (viewR != findFocus()) {
            viewR.requestFocus(i7);
        }
        return z6;
    }

    private void J(View view) {
        view.getDrawingRect(this.f1967g);
        offsetDescendantRectToMyCoords(view, this.f1967g);
        int iE = e(this.f1967g);
        if (iE != 0) {
            scrollBy(0, iE);
        }
    }

    private boolean K(Rect rect, boolean z6) {
        int iE = e(rect);
        boolean z7 = iE != 0;
        if (z7) {
            if (z6) {
                scrollBy(0, iE);
            } else {
                L(0, iE);
            }
        }
        return z7;
    }

    private void M(int i7, int i8, int i9, boolean z6) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.f1966e > 250) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            this.f1968h.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(i8 + scrollY, Math.max(0, height - height2))) - scrollY, i9);
            H(z6);
        } else {
            if (!this.f1968h.isFinished()) {
                a();
            }
            scrollBy(i7, i8);
        }
        this.f1966e = AnimationUtils.currentAnimationTimeMillis();
    }

    private void a() {
        this.f1968h.abortAnimation();
        Q(1);
    }

    private boolean c() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private static int d(int i7, int i8, int i9) {
        if (i8 >= i9 || i7 < 0) {
            return 0;
        }
        return i8 + i7 > i9 ? i9 - i8 : i7;
    }

    private float getVerticalScrollFactorCompat() {
        if (this.D == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (!context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.D = typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.D;
    }

    private void k(int i7) {
        if (i7 != 0) {
            if (this.f1978r) {
                L(0, i7);
            } else {
                scrollBy(0, i7);
            }
        }
    }

    private void l() {
        this.f1975o = false;
        G();
        Q(0);
        EdgeEffect edgeEffect = this.f1969i;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            this.f1970j.onRelease();
        }
    }

    private void p() {
        if (getOverScrollMode() == 2) {
            this.f1969i = null;
            this.f1970j = null;
        } else if (this.f1969i == null) {
            Context context = getContext();
            this.f1969i = new EdgeEffect(context);
            this.f1970j = new EdgeEffect(context);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.View r(boolean r13, int r14, int r15) {
        /*
            r12 = this;
            r0 = 2
            java.util.ArrayList r0 = r12.getFocusables(r0)
            int r1 = r0.size()
            r2 = 0
            r3 = 0
            r4 = r2
            r5 = r4
        Ld:
            if (r4 >= r1) goto L53
            java.lang.Object r6 = r0.get(r4)
            android.view.View r6 = (android.view.View) r6
            int r7 = r6.getTop()
            int r8 = r6.getBottom()
            r9 = 1
            if (r14 >= r8) goto L50
            if (r7 >= r15) goto L50
            if (r14 >= r7) goto L28
            if (r8 >= r15) goto L28
            r10 = r9
            goto L29
        L28:
            r10 = r2
        L29:
            if (r3 != 0) goto L2e
            r3 = r6
            r5 = r10
            goto L50
        L2e:
            if (r13 == 0) goto L36
            int r11 = r3.getTop()
            if (r7 < r11) goto L3e
        L36:
            if (r13 != 0) goto L40
            int r7 = r3.getBottom()
            if (r8 <= r7) goto L40
        L3e:
            r7 = r9
            goto L41
        L40:
            r7 = r2
        L41:
            if (r5 == 0) goto L48
            if (r10 == 0) goto L50
            if (r7 == 0) goto L50
            goto L4f
        L48:
            if (r10 == 0) goto L4d
            r3 = r6
            r5 = r9
            goto L50
        L4d:
            if (r7 == 0) goto L50
        L4f:
            r3 = r6
        L50:
            int r4 = r4 + 1
            goto Ld
        L53:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.r(boolean, int, int):android.view.View");
    }

    private boolean v(int i7, int i8) {
        if (getChildCount() <= 0) {
            return false;
        }
        int scrollY = getScrollY();
        View childAt = getChildAt(0);
        return i8 >= childAt.getTop() - scrollY && i8 < childAt.getBottom() - scrollY && i7 >= childAt.getLeft() && i7 < childAt.getRight();
    }

    private void w() {
        VelocityTracker velocityTracker = this.f1976p;
        if (velocityTracker == null) {
            this.f1976p = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    private void x() {
        this.f1968h = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f1979s = viewConfiguration.getScaledTouchSlop();
        this.f1980t = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f1981u = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    private void y() {
        if (this.f1976p == null) {
            this.f1976p = VelocityTracker.obtain();
        }
    }

    private boolean z(View view) {
        return !B(view, 0, getHeight());
    }

    boolean E(int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, boolean z6) {
        boolean z7;
        boolean z8;
        int overScrollMode = getOverScrollMode();
        boolean z9 = computeHorizontalScrollRange() > computeHorizontalScrollExtent();
        boolean z10 = computeVerticalScrollRange() > computeVerticalScrollExtent();
        boolean z11 = overScrollMode == 0 || (overScrollMode == 1 && z9);
        boolean z12 = overScrollMode == 0 || (overScrollMode == 1 && z10);
        int i15 = i9 + i7;
        int i16 = !z11 ? 0 : i13;
        int i17 = i10 + i8;
        int i18 = !z12 ? 0 : i14;
        int i19 = -i16;
        int i20 = i16 + i11;
        int i21 = -i18;
        int i22 = i18 + i12;
        if (i15 > i20) {
            i15 = i20;
            z7 = true;
        } else if (i15 < i19) {
            z7 = true;
            i15 = i19;
        } else {
            z7 = false;
        }
        if (i17 > i22) {
            i17 = i22;
            z8 = true;
        } else if (i17 < i21) {
            z8 = true;
            i17 = i21;
        } else {
            z8 = false;
        }
        if (z8 && !u(1)) {
            this.f1968h.springBack(i15, i17, 0, 0, 0, getScrollRange());
        }
        onOverScrolled(i15, i17, z7, z8);
        return z7 || z8;
    }

    public boolean F(int i7) {
        boolean z6 = i7 == 130;
        int height = getHeight();
        if (z6) {
            this.f1967g.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
                Rect rect = this.f1967g;
                if (rect.top + height > bottom) {
                    rect.top = bottom - height;
                }
            }
        } else {
            this.f1967g.top = getScrollY() - height;
            Rect rect2 = this.f1967g;
            if (rect2.top < 0) {
                rect2.top = 0;
            }
        }
        Rect rect3 = this.f1967g;
        int i8 = rect3.top;
        int i9 = height + i8;
        rect3.bottom = i9;
        return I(i7, i8, i9);
    }

    public final void L(int i7, int i8) {
        M(i7, i8, 250, false);
    }

    void N(int i7, int i8, int i9, boolean z6) {
        M(i7 - getScrollX(), i8 - getScrollY(), i9, z6);
    }

    void O(int i7, int i8, boolean z6) {
        N(i7, i8, 250, z6);
    }

    public boolean P(int i7, int i8) {
        return this.C.p(i7, i8);
    }

    public void Q(int i7) {
        this.C.r(i7);
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    public boolean b(int i7) {
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i7);
        int maxScrollAmount = getMaxScrollAmount();
        if (viewFindNextFocus == null || !B(viewFindNextFocus, maxScrollAmount, getHeight())) {
            if (i7 == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i7 == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getScrollY() + getHeight()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i7 != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            k(maxScrollAmount);
        } else {
            viewFindNextFocus.getDrawingRect(this.f1967g);
            offsetDescendantRectToMyCoords(viewFindNextFocus, this.f1967g);
            k(e(this.f1967g));
            viewFindNextFocus.requestFocus(i7);
        }
        if (viewFindFocus == null || !viewFindFocus.isFocused() || !z(viewFindFocus)) {
            return true;
        }
        int descendantFocusability = getDescendantFocusability();
        setDescendantFocusability(131072);
        requestFocus();
        setDescendantFocusability(descendantFocusability);
        return true;
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
        if (this.f1968h.isFinished()) {
            return;
        }
        this.f1968h.computeScrollOffset();
        int currY = this.f1968h.getCurrY();
        int i7 = currY - this.f1986z;
        this.f1986z = currY;
        int[] iArr = this.f1984x;
        boolean z6 = false;
        iArr[1] = 0;
        f(0, i7, iArr, null, 1);
        int i8 = i7 - this.f1984x[1];
        int scrollRange = getScrollRange();
        if (i8 != 0) {
            int scrollY = getScrollY();
            E(0, i8, getScrollX(), scrollY, 0, scrollRange, 0, 0, false);
            int scrollY2 = getScrollY() - scrollY;
            int i9 = i8 - scrollY2;
            int[] iArr2 = this.f1984x;
            iArr2[1] = 0;
            g(0, scrollY2, 0, i9, this.f1983w, 1, iArr2);
            i8 = i9 - this.f1984x[1];
        }
        if (i8 != 0) {
            int overScrollMode = getOverScrollMode();
            if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                z6 = true;
            }
            if (z6) {
                p();
                if (i8 < 0) {
                    if (this.f1969i.isFinished()) {
                        this.f1969i.onAbsorb((int) this.f1968h.getCurrVelocity());
                    }
                } else if (this.f1970j.isFinished()) {
                    this.f1970j.onAbsorb((int) this.f1968h.getCurrVelocity());
                }
            }
            a();
        }
        if (this.f1968h.isFinished()) {
            Q(1);
        } else {
            u.b0(this);
        }
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

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || q(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f7, float f8, boolean z6) {
        return this.C.a(f7, f8, z6);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f7, float f8) {
        return this.C.b(f7, f8);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i7, int i8, int[] iArr, int[] iArr2) {
        return f(i7, i8, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i7, int i8, int i9, int i10, int[] iArr) {
        return this.C.f(i7, i8, i9, i10, iArr);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int paddingLeft;
        super.draw(canvas);
        if (this.f1969i != null) {
            int scrollY = getScrollY();
            int paddingLeft2 = 0;
            if (!this.f1969i.isFinished()) {
                int iSave = canvas.save();
                int width = getWidth();
                int height = getHeight();
                int iMin = Math.min(0, scrollY);
                if (getClipToPadding()) {
                    width -= getPaddingLeft() + getPaddingRight();
                    paddingLeft = getPaddingLeft() + 0;
                } else {
                    paddingLeft = 0;
                }
                if (getClipToPadding()) {
                    height -= getPaddingTop() + getPaddingBottom();
                    iMin += getPaddingTop();
                }
                canvas.translate(paddingLeft, iMin);
                this.f1969i.setSize(width, height);
                if (this.f1969i.draw(canvas)) {
                    u.b0(this);
                }
                canvas.restoreToCount(iSave);
            }
            if (this.f1970j.isFinished()) {
                return;
            }
            int iSave2 = canvas.save();
            int width2 = getWidth();
            int height2 = getHeight();
            int iMax = Math.max(getScrollRange(), scrollY) + height2;
            if (getClipToPadding()) {
                width2 -= getPaddingLeft() + getPaddingRight();
                paddingLeft2 = 0 + getPaddingLeft();
            }
            if (getClipToPadding()) {
                height2 -= getPaddingTop() + getPaddingBottom();
                iMax -= getPaddingBottom();
            }
            canvas.translate(paddingLeft2 - width2, iMax);
            canvas.rotate(180.0f, width2, 0.0f);
            this.f1970j.setSize(width2, height2);
            if (this.f1970j.draw(canvas)) {
                u.b0(this);
            }
            canvas.restoreToCount(iSave2);
        }
    }

    protected int e(Rect rect) {
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
        int i8 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i7 - verticalFadingEdgeLength : i7;
        int i9 = rect.bottom;
        if (i9 > i8 && rect.top > scrollY) {
            return Math.min((rect.height() > height ? rect.top - scrollY : rect.bottom - i8) + 0, (childAt.getBottom() + layoutParams.bottomMargin) - i7);
        }
        if (rect.top >= scrollY || i9 >= i8) {
            return 0;
        }
        return Math.max(rect.height() > height ? 0 - (i8 - rect.bottom) : 0 - (scrollY - rect.top), -getScrollY());
    }

    public boolean f(int i7, int i8, int[] iArr, int[] iArr2, int i9) {
        return this.C.d(i7, i8, iArr, iArr2, i9);
    }

    public void g(int i7, int i8, int i9, int i10, int[] iArr, int i11, int[] iArr2) {
        this.C.e(i7, i8, i9, i10, iArr, i11, iArr2);
    }

    @Override // android.view.View
    protected float getBottomFadingEdgeStrength() {
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
        return this.B.a();
    }

    int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    @Override // android.view.View
    protected float getTopFadingEdgeStrength() {
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

    @Override // i0.m
    public void h(View view, View view2, int i7, int i8) {
        this.B.c(view, view2, i7, i8);
        P(2, i8);
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return u(0);
    }

    @Override // i0.m
    public void i(View view, int i7) {
        this.B.d(view, i7);
        Q(i7);
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return this.C.l();
    }

    @Override // i0.m
    public void j(View view, int i7, int i8, int[] iArr, int i9) {
        f(i7, i8, iArr, null, i9);
    }

    @Override // i0.n
    public void m(View view, int i7, int i8, int i9, int i10, int i11, int[] iArr) {
        C(i10, i11, iArr);
    }

    @Override // android.view.ViewGroup
    protected void measureChild(View view, int i7, int i8) {
        view.measure(FrameLayout.getChildMeasureSpec(i7, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    protected void measureChildWithMargins(View view, int i7, int i8, int i9, int i10) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(FrameLayout.getChildMeasureSpec(i7, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i8, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    @Override // i0.m
    public void n(View view, int i7, int i8, int i9, int i10, int i11) {
        C(i10, i11, null);
    }

    @Override // i0.m
    public boolean o(View view, View view2, int i7, int i8) {
        return (i7 & 2) != 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1973m = false;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.f1975o) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                int verticalScrollFactorCompat = (int) (axisValue * getVerticalScrollFactorCompat());
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int i7 = scrollY - verticalScrollFactorCompat;
                if (i7 < 0) {
                    scrollRange = 0;
                } else if (i7 <= scrollRange) {
                    scrollRange = i7;
                }
                if (scrollRange != scrollY) {
                    super.scrollTo(getScrollX(), scrollRange);
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x007d  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r12) {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        int measuredHeight = 0;
        this.f1972l = false;
        View view = this.f1974n;
        if (view != null && A(view, this)) {
            J(this.f1974n);
        }
        this.f1974n = null;
        if (!this.f1973m) {
            if (this.A != null) {
                scrollTo(getScrollX(), this.A.f1987e);
                this.A = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                measuredHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            }
            int paddingTop = ((i10 - i8) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            int iD = d(scrollY, paddingTop, measuredHeight);
            if (iD != scrollY) {
                scrollTo(getScrollX(), iD);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f1973m = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i7, int i8) {
        super.onMeasure(i7, i8);
        if (this.f1977q && View.MeasureSpec.getMode(i8) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(FrameLayout.getChildMeasureSpec(i7, getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f7, float f8, boolean z6) {
        if (z6) {
            return false;
        }
        dispatchNestedFling(0.0f, f8, true);
        s((int) f8);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f7, float f8) {
        return dispatchNestedPreFling(f7, f8);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i7, int i8, int[] iArr) {
        j(view, i7, i8, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i7, int i8, int i9, int i10) {
        C(i10, 0, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i7) {
        h(view, view2, i7, 0);
    }

    @Override // android.view.View
    protected void onOverScrolled(int i7, int i8, boolean z6, boolean z7) {
        super.scrollTo(i7, i8);
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int i7, Rect rect) {
        if (i7 == 2) {
            i7 = 130;
        } else if (i7 == 1) {
            i7 = 33;
        }
        View viewFindNextFocus = rect == null ? FocusFinder.getInstance().findNextFocus(this, null, i7) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, i7);
        if (viewFindNextFocus == null || z(viewFindNextFocus)) {
            return false;
        }
        return viewFindNextFocus.requestFocus(i7, rect);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.A = savedState;
        requestLayout();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1987e = getScrollY();
        return savedState;
    }

    @Override // android.view.View
    protected void onScrollChanged(int i7, int i8, int i9, int i10) {
        super.onScrollChanged(i7, i8, i9, i10);
        b bVar = this.E;
        if (bVar != null) {
            bVar.a(this, i7, i8, i9, i10);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i7, int i8, int i9, int i10) {
        super.onSizeChanged(i7, i8, i9, i10);
        View viewFindFocus = findFocus();
        if (viewFindFocus == null || this == viewFindFocus || !B(viewFindFocus, 0, i10)) {
            return;
        }
        viewFindFocus.getDrawingRect(this.f1967g);
        offsetDescendantRectToMyCoords(viewFindFocus, this.f1967g);
        k(e(this.f1967g));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i7) {
        return o(view, view2, i7, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        i(view, 0);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        y();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f1985y = 0;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(0.0f, this.f1985y);
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                VelocityTracker velocityTracker = this.f1976p;
                velocityTracker.computeCurrentVelocity(1000, this.f1981u);
                int yVelocity = (int) velocityTracker.getYVelocity(this.f1982v);
                if (Math.abs(yVelocity) >= this.f1980t) {
                    int i7 = -yVelocity;
                    float f7 = i7;
                    if (!dispatchNestedPreFling(0.0f, f7)) {
                        dispatchNestedFling(0.0f, f7, true);
                        s(i7);
                    }
                } else if (this.f1968h.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    u.b0(this);
                }
                this.f1982v = -1;
                l();
            } else if (actionMasked == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.f1982v);
                if (iFindPointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + this.f1982v + " in onTouchEvent");
                } else {
                    int y6 = (int) motionEvent.getY(iFindPointerIndex);
                    int i8 = this.f1971k - y6;
                    if (!this.f1975o && Math.abs(i8) > this.f1979s) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.f1975o = true;
                        i8 = i8 > 0 ? i8 - this.f1979s : i8 + this.f1979s;
                    }
                    int i9 = i8;
                    if (this.f1975o) {
                        if (f(0, i9, this.f1984x, this.f1983w, 0)) {
                            i9 -= this.f1984x[1];
                            this.f1985y += this.f1983w[1];
                        }
                        int i10 = i9;
                        this.f1971k = y6 - this.f1983w[1];
                        int scrollY = getScrollY();
                        int scrollRange = getScrollRange();
                        int overScrollMode = getOverScrollMode();
                        boolean z6 = overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0);
                        if (E(0, i10, 0, getScrollY(), 0, scrollRange, 0, 0, true) && !u(0)) {
                            this.f1976p.clear();
                        }
                        int scrollY2 = getScrollY() - scrollY;
                        int[] iArr = this.f1984x;
                        iArr[1] = 0;
                        g(0, scrollY2, 0, i10 - scrollY2, this.f1983w, 0, iArr);
                        int i11 = this.f1971k;
                        int[] iArr2 = this.f1983w;
                        this.f1971k = i11 - iArr2[1];
                        this.f1985y += iArr2[1];
                        if (z6) {
                            int i12 = i10 - this.f1984x[1];
                            p();
                            int i13 = scrollY + i12;
                            if (i13 < 0) {
                                d.a(this.f1969i, i12 / getHeight(), motionEvent.getX(iFindPointerIndex) / getWidth());
                                if (!this.f1970j.isFinished()) {
                                    this.f1970j.onRelease();
                                }
                            } else if (i13 > scrollRange) {
                                d.a(this.f1970j, i12 / getHeight(), 1.0f - (motionEvent.getX(iFindPointerIndex) / getWidth()));
                                if (!this.f1969i.isFinished()) {
                                    this.f1969i.onRelease();
                                }
                            }
                            EdgeEffect edgeEffect = this.f1969i;
                            if (edgeEffect != null && (!edgeEffect.isFinished() || !this.f1970j.isFinished())) {
                                u.b0(this);
                            }
                        }
                    }
                }
            } else if (actionMasked == 3) {
                if (this.f1975o && getChildCount() > 0 && this.f1968h.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    u.b0(this);
                }
                this.f1982v = -1;
                l();
            } else if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                this.f1971k = (int) motionEvent.getY(actionIndex);
                this.f1982v = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                D(motionEvent);
                this.f1971k = (int) motionEvent.getY(motionEvent.findPointerIndex(this.f1982v));
            }
        } else {
            if (getChildCount() == 0) {
                return false;
            }
            boolean z7 = !this.f1968h.isFinished();
            this.f1975o = z7;
            if (z7 && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.f1968h.isFinished()) {
                a();
            }
            this.f1971k = (int) motionEvent.getY();
            this.f1982v = motionEvent.getPointerId(0);
            P(2, 0);
        }
        VelocityTracker velocityTracker2 = this.f1976p;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEventObtain);
        }
        motionEventObtain.recycle();
        return true;
    }

    public boolean q(KeyEvent keyEvent) {
        this.f1967g.setEmpty();
        if (!c()) {
            if (!isFocused() || keyEvent.getKeyCode() == 4) {
                return false;
            }
            View viewFindFocus = findFocus();
            if (viewFindFocus == this) {
                viewFindFocus = null;
            }
            View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, 130);
            return (viewFindNextFocus == null || viewFindNextFocus == this || !viewFindNextFocus.requestFocus(130)) ? false : true;
        }
        if (keyEvent.getAction() != 0) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 19) {
            return !keyEvent.isAltPressed() ? b(33) : t(33);
        }
        if (keyCode == 20) {
            return !keyEvent.isAltPressed() ? b(130) : t(130);
        }
        if (keyCode != 62) {
            return false;
        }
        F(keyEvent.isShiftPressed() ? 33 : 130);
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (this.f1972l) {
            this.f1974n = view2;
        } else {
            J(view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z6) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return K(rect, z6);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z6) {
        if (z6) {
            G();
        }
        super.requestDisallowInterceptTouchEvent(z6);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.f1972l = true;
        super.requestLayout();
    }

    public void s(int i7) {
        if (getChildCount() > 0) {
            this.f1968h.fling(getScrollX(), getScrollY(), 0, i7, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            H(true);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i7, int i8) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int width2 = childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int height2 = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int iD = d(i7, width, width2);
            int iD2 = d(i8, height, height2);
            if (iD == getScrollX() && iD2 == getScrollY()) {
                return;
            }
            super.scrollTo(iD, iD2);
        }
    }

    public void setFillViewport(boolean z6) {
        if (z6 != this.f1977q) {
            this.f1977q = z6;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z6) {
        this.C.m(z6);
    }

    public void setOnScrollChangeListener(b bVar) {
        this.E = bVar;
    }

    public void setSmoothScrollingEnabled(boolean z6) {
        this.f1978r = z6;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i7) {
        return P(i7, 0);
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        Q(0);
    }

    public boolean t(int i7) {
        int childCount;
        boolean z6 = i7 == 130;
        int height = getHeight();
        Rect rect = this.f1967g;
        rect.top = 0;
        rect.bottom = height;
        if (z6 && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            this.f1967g.bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
            Rect rect2 = this.f1967g;
            rect2.top = rect2.bottom - height;
        }
        Rect rect3 = this.f1967g;
        return I(i7, rect3.top, rect3.bottom);
    }

    public boolean u(int i7) {
        return this.C.k(i7);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f1967g = new Rect();
        this.f1972l = true;
        this.f1973m = false;
        this.f1974n = null;
        this.f1975o = false;
        this.f1978r = true;
        this.f1982v = -1;
        this.f1983w = new int[2];
        this.f1984x = new int[2];
        x();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, G, i7, 0);
        setFillViewport(typedArrayObtainStyledAttributes.getBoolean(0, false));
        typedArrayObtainStyledAttributes.recycle();
        this.B = new o(this);
        this.C = new l(this);
        setNestedScrollingEnabled(true);
        u.k0(this, F);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i7) {
        if (getChildCount() <= 0) {
            super.addView(view, i7);
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
    public void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i7, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
}
