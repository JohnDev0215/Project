package androidx.core.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
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
import o0.j;
import o0.k;
import o0.m;
import o0.o;
import o0.t;
import p0.c;
import p0.e;
import r0.d;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: loaded from: classes.dex */
public class NestedScrollView extends FrameLayout implements m, j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f1270d = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f1271e = {R.attr.fillViewport};
    public SavedState A;
    public final o B;
    public final k C;
    public float D;
    public b E;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f1272f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Rect f1273g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public OverScroller f1274h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public EdgeEffect f1275i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public EdgeEffect f1276j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1277k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f1278l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1279m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public View f1280n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1281o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public VelocityTracker f1282p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1283q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1284r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1285s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1286t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1287u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1288v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int[] f1289w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int[] f1290x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1291y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f1292z;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1293d;

        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i8) {
                return new SavedState[i8];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.f1293d + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i8) {
            super.writeToParcel(parcel, i8);
            parcel.writeInt(this.f1293d);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f1293d = parcel.readInt();
        }
    }

    public static class a extends o0.a {
        @Override // o0.a
        public void f(View view, AccessibilityEvent accessibilityEvent) {
            super.f(view, accessibilityEvent);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            accessibilityEvent.setClassName(ScrollView.class.getName());
            accessibilityEvent.setScrollable(nestedScrollView.getScrollRange() > 0);
            accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
            accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
            e.a(accessibilityEvent, nestedScrollView.getScrollX());
            e.b(accessibilityEvent, nestedScrollView.getScrollRange());
        }

        @Override // o0.a
        public void g(View view, c cVar) {
            int scrollRange;
            super.g(view, cVar);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            cVar.c0(ScrollView.class.getName());
            if (!nestedScrollView.isEnabled() || (scrollRange = nestedScrollView.getScrollRange()) <= 0) {
                return;
            }
            cVar.x0(true);
            if (nestedScrollView.getScrollY() > 0) {
                cVar.b(c.a.f6981n);
                cVar.b(c.a.f6992y);
            }
            if (nestedScrollView.getScrollY() < scrollRange) {
                cVar.b(c.a.f6980m);
                cVar.b(c.a.A);
            }
        }

        @Override // o0.a
        public boolean j(View view, int i8, Bundle bundle) {
            if (super.j(view, i8, bundle)) {
                return true;
            }
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            if (!nestedScrollView.isEnabled()) {
                return false;
            }
            if (i8 != 4096) {
                if (i8 == 8192 || i8 == 16908344) {
                    int iMax = Math.max(nestedScrollView.getScrollY() - ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
                    if (iMax == nestedScrollView.getScrollY()) {
                        return false;
                    }
                    nestedScrollView.O(0, iMax, true);
                    return true;
                }
                if (i8 != 16908346) {
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
        void a(NestedScrollView nestedScrollView, int i8, int i9, int i10, int i11);
    }

    public NestedScrollView(Context context) {
        this(context, null);
    }

    public static boolean A(View view, View view2) {
        if (view == view2) {
            return true;
        }
        Object parent = view.getParent();
        return (parent instanceof ViewGroup) && A((View) parent, view2);
    }

    public static int d(int i8, int i9, int i10) {
        if (i9 >= i10 || i8 < 0) {
            return 0;
        }
        return i9 + i8 > i10 ? i10 - i9 : i8;
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

    public final boolean B(View view, int i8, int i9) {
        view.getDrawingRect(this.f1273g);
        offsetDescendantRectToMyCoords(view, this.f1273g);
        return this.f1273g.bottom + i8 >= getScrollY() && this.f1273g.top - i8 <= getScrollY() + i9;
    }

    public final void C(int i8, int i9, int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i8);
        int scrollY2 = getScrollY() - scrollY;
        if (iArr != null) {
            iArr[1] = iArr[1] + scrollY2;
        }
        this.C.e(0, scrollY2, 0, i8 - scrollY2, null, i9, iArr);
    }

    public final void D(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f1288v) {
            int i8 = actionIndex == 0 ? 1 : 0;
            this.f1277k = (int) motionEvent.getY(i8);
            this.f1288v = motionEvent.getPointerId(i8);
            VelocityTracker velocityTracker = this.f1282p;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public boolean E(int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, boolean z7) {
        boolean z8;
        boolean z9;
        int overScrollMode = getOverScrollMode();
        boolean z10 = computeHorizontalScrollRange() > computeHorizontalScrollExtent();
        boolean z11 = computeVerticalScrollRange() > computeVerticalScrollExtent();
        boolean z12 = overScrollMode == 0 || (overScrollMode == 1 && z10);
        boolean z13 = overScrollMode == 0 || (overScrollMode == 1 && z11);
        int i16 = i10 + i8;
        int i17 = !z12 ? 0 : i14;
        int i18 = i11 + i9;
        int i19 = !z13 ? 0 : i15;
        int i20 = -i17;
        int i21 = i17 + i12;
        int i22 = -i19;
        int i23 = i19 + i13;
        if (i16 > i21) {
            i16 = i21;
            z8 = true;
        } else if (i16 < i20) {
            z8 = true;
            i16 = i20;
        } else {
            z8 = false;
        }
        if (i18 > i23) {
            i18 = i23;
            z9 = true;
        } else if (i18 < i22) {
            z9 = true;
            i18 = i22;
        } else {
            z9 = false;
        }
        if (z9 && !u(1)) {
            this.f1274h.springBack(i16, i18, 0, 0, 0, getScrollRange());
        }
        onOverScrolled(i16, i18, z8, z9);
        return z8 || z9;
    }

    public boolean F(int i8) {
        boolean z7 = i8 == 130;
        int height = getHeight();
        if (z7) {
            this.f1273g.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
                Rect rect = this.f1273g;
                if (rect.top + height > bottom) {
                    rect.top = bottom - height;
                }
            }
        } else {
            this.f1273g.top = getScrollY() - height;
            Rect rect2 = this.f1273g;
            if (rect2.top < 0) {
                rect2.top = 0;
            }
        }
        Rect rect3 = this.f1273g;
        int i9 = rect3.top;
        int i10 = height + i9;
        rect3.bottom = i10;
        return I(i8, i9, i10);
    }

    public final void G() {
        VelocityTracker velocityTracker = this.f1282p;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f1282p = null;
        }
    }

    public final void H(boolean z7) {
        if (z7) {
            P(2, 1);
        } else {
            Q(1);
        }
        this.f1292z = getScrollY();
        t.b0(this);
    }

    public final boolean I(int i8, int i9, int i10) {
        int height = getHeight();
        int scrollY = getScrollY();
        int i11 = height + scrollY;
        boolean z7 = false;
        boolean z8 = i8 == 33;
        View viewR = r(z8, i9, i10);
        if (viewR == null) {
            viewR = this;
        }
        if (i9 < scrollY || i10 > i11) {
            k(z8 ? i9 - scrollY : i10 - i11);
            z7 = true;
        }
        if (viewR != findFocus()) {
            viewR.requestFocus(i8);
        }
        return z7;
    }

    public final void J(View view) {
        view.getDrawingRect(this.f1273g);
        offsetDescendantRectToMyCoords(view, this.f1273g);
        int iE = e(this.f1273g);
        if (iE != 0) {
            scrollBy(0, iE);
        }
    }

    public final boolean K(Rect rect, boolean z7) {
        int iE = e(rect);
        boolean z8 = iE != 0;
        if (z8) {
            if (z7) {
                scrollBy(0, iE);
            } else {
                L(0, iE);
            }
        }
        return z8;
    }

    public final void L(int i8, int i9) {
        M(i8, i9, 250, false);
    }

    public final void M(int i8, int i9, int i10, boolean z7) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.f1272f > 250) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            this.f1274h.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(i9 + scrollY, Math.max(0, height - height2))) - scrollY, i10);
            H(z7);
        } else {
            if (!this.f1274h.isFinished()) {
                a();
            }
            scrollBy(i8, i9);
        }
        this.f1272f = AnimationUtils.currentAnimationTimeMillis();
    }

    public void N(int i8, int i9, int i10, boolean z7) {
        M(i8 - getScrollX(), i9 - getScrollY(), i10, z7);
    }

    public void O(int i8, int i9, boolean z7) {
        N(i8, i9, 250, z7);
    }

    public boolean P(int i8, int i9) {
        return this.C.q(i8, i9);
    }

    public void Q(int i8) {
        this.C.s(i8);
    }

    public final void a() {
        this.f1274h.abortAnimation();
        Q(1);
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    public boolean b(int i8) {
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i8);
        int maxScrollAmount = getMaxScrollAmount();
        if (viewFindNextFocus == null || !B(viewFindNextFocus, maxScrollAmount, getHeight())) {
            if (i8 == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i8 == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getScrollY() + getHeight()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i8 != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            k(maxScrollAmount);
        } else {
            viewFindNextFocus.getDrawingRect(this.f1273g);
            offsetDescendantRectToMyCoords(viewFindNextFocus, this.f1273g);
            k(e(this.f1273g));
            viewFindNextFocus.requestFocus(i8);
        }
        if (viewFindFocus == null || !viewFindFocus.isFocused() || !z(viewFindFocus)) {
            return true;
        }
        int descendantFocusability = getDescendantFocusability();
        setDescendantFocusability(CarPlayConfig.IapFeature.IAP_FEATURE_APP_LAUNCH);
        requestFocus();
        setDescendantFocusability(descendantFocusability);
        return true;
    }

    public final boolean c() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom();
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
        if (this.f1274h.isFinished()) {
            return;
        }
        this.f1274h.computeScrollOffset();
        int currY = this.f1274h.getCurrY();
        int i8 = currY - this.f1292z;
        this.f1292z = currY;
        int[] iArr = this.f1290x;
        boolean z7 = false;
        iArr[1] = 0;
        f(0, i8, iArr, null, 1);
        int i9 = i8 - this.f1290x[1];
        int scrollRange = getScrollRange();
        if (i9 != 0) {
            int scrollY = getScrollY();
            E(0, i9, getScrollX(), scrollY, 0, scrollRange, 0, 0, false);
            int scrollY2 = getScrollY() - scrollY;
            int i10 = i9 - scrollY2;
            int[] iArr2 = this.f1290x;
            iArr2[1] = 0;
            g(0, scrollY2, 0, i10, this.f1289w, 1, iArr2);
            i9 = i10 - this.f1290x[1];
        }
        if (i9 != 0) {
            int overScrollMode = getOverScrollMode();
            if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                z7 = true;
            }
            if (z7) {
                p();
                if (i9 < 0) {
                    if (this.f1275i.isFinished()) {
                        this.f1275i.onAbsorb((int) this.f1274h.getCurrVelocity());
                    }
                } else if (this.f1276j.isFinished()) {
                    this.f1276j.onAbsorb((int) this.f1274h.getCurrVelocity());
                }
            }
            a();
        }
        if (this.f1274h.isFinished()) {
            Q(1);
        } else {
            t.b0(this);
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
    public boolean dispatchNestedFling(float f8, float f9, boolean z7) {
        return this.C.a(f8, f9, z7);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f8, float f9) {
        return this.C.b(f8, f9);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i8, int i9, int[] iArr, int[] iArr2) {
        return f(i8, i9, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i8, int i9, int i10, int i11, int[] iArr) {
        return this.C.f(i8, i9, i10, i11, iArr);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int paddingLeft;
        super.draw(canvas);
        if (this.f1275i != null) {
            int scrollY = getScrollY();
            int paddingLeft2 = 0;
            if (!this.f1275i.isFinished()) {
                int iSave = canvas.save();
                int width = getWidth();
                int height = getHeight();
                int iMin = Math.min(0, scrollY);
                int i8 = Build.VERSION.SDK_INT;
                if (i8 < 21 || getClipToPadding()) {
                    width -= getPaddingLeft() + getPaddingRight();
                    paddingLeft = getPaddingLeft() + 0;
                } else {
                    paddingLeft = 0;
                }
                if (i8 >= 21 && getClipToPadding()) {
                    height -= getPaddingTop() + getPaddingBottom();
                    iMin += getPaddingTop();
                }
                canvas.translate(paddingLeft, iMin);
                this.f1275i.setSize(width, height);
                if (this.f1275i.draw(canvas)) {
                    t.b0(this);
                }
                canvas.restoreToCount(iSave);
            }
            if (this.f1276j.isFinished()) {
                return;
            }
            int iSave2 = canvas.save();
            int width2 = getWidth();
            int height2 = getHeight();
            int iMax = Math.max(getScrollRange(), scrollY) + height2;
            int i9 = Build.VERSION.SDK_INT;
            if (i9 < 21 || getClipToPadding()) {
                width2 -= getPaddingLeft() + getPaddingRight();
                paddingLeft2 = 0 + getPaddingLeft();
            }
            if (i9 >= 21 && getClipToPadding()) {
                height2 -= getPaddingTop() + getPaddingBottom();
                iMax -= getPaddingBottom();
            }
            canvas.translate(paddingLeft2 - width2, iMax);
            canvas.rotate(180.0f, width2, 0.0f);
            this.f1276j.setSize(width2, height2);
            if (this.f1276j.draw(canvas)) {
                t.b0(this);
            }
            canvas.restoreToCount(iSave2);
        }
    }

    public int e(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i8 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i9 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i8 - verticalFadingEdgeLength : i8;
        int i10 = rect.bottom;
        if (i10 > i9 && rect.top > scrollY) {
            return Math.min((rect.height() > height ? rect.top - scrollY : rect.bottom - i9) + 0, (childAt.getBottom() + layoutParams.bottomMargin) - i8);
        }
        if (rect.top >= scrollY || i10 >= i9) {
            return 0;
        }
        return Math.max(rect.height() > height ? 0 - (i9 - rect.bottom) : 0 - (scrollY - rect.top), -getScrollY());
    }

    public boolean f(int i8, int i9, int[] iArr, int[] iArr2, int i10) {
        return this.C.d(i8, i9, iArr, iArr2, i10);
    }

    public void g(int i8, int i9, int i10, int i11, int[] iArr, int i12, int[] iArr2) {
        this.C.e(i8, i9, i10, i11, iArr, i12, iArr2);
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
        return this.B.a();
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

    @Override // o0.l
    public void h(View view, View view2, int i8, int i9) {
        this.B.c(view, view2, i8, i9);
        P(2, i9);
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return u(0);
    }

    @Override // o0.l
    public void i(View view, int i8) {
        this.B.e(view, i8);
        Q(i8);
    }

    @Override // android.view.View, o0.j
    public boolean isNestedScrollingEnabled() {
        return this.C.m();
    }

    @Override // o0.l
    public void j(View view, int i8, int i9, int[] iArr, int i10) {
        f(i8, i9, iArr, null, i10);
    }

    public final void k(int i8) {
        if (i8 != 0) {
            if (this.f1284r) {
                L(0, i8);
            } else {
                scrollBy(0, i8);
            }
        }
    }

    public final void l() {
        this.f1281o = false;
        G();
        Q(0);
        EdgeEffect edgeEffect = this.f1275i;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            this.f1276j.onRelease();
        }
    }

    @Override // o0.m
    public void m(View view, int i8, int i9, int i10, int i11, int i12, int[] iArr) {
        C(i11, i12, iArr);
    }

    @Override // android.view.ViewGroup
    public void measureChild(View view, int i8, int i9) {
        view.measure(FrameLayout.getChildMeasureSpec(i8, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    public void measureChildWithMargins(View view, int i8, int i9, int i10, int i11) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(FrameLayout.getChildMeasureSpec(i8, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i9, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    @Override // o0.l
    public void n(View view, int i8, int i9, int i10, int i11, int i12) {
        C(i11, i12, null);
    }

    @Override // o0.l
    public boolean o(View view, View view2, int i8, int i9) {
        return (i8 & 2) != 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1279m = false;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.f1281o) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                int verticalScrollFactorCompat = (int) (axisValue * getVerticalScrollFactorCompat());
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int i8 = scrollY - verticalScrollFactorCompat;
                if (i8 < 0) {
                    scrollRange = 0;
                } else if (i8 <= scrollRange) {
                    scrollRange = i8;
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
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        super.onLayout(z7, i8, i9, i10, i11);
        int measuredHeight = 0;
        this.f1278l = false;
        View view = this.f1280n;
        if (view != null && A(view, this)) {
            J(this.f1280n);
        }
        this.f1280n = null;
        if (!this.f1279m) {
            if (this.A != null) {
                scrollTo(getScrollX(), this.A.f1293d);
                this.A = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                measuredHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            }
            int paddingTop = ((i11 - i9) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            int iD = d(scrollY, paddingTop, measuredHeight);
            if (iD != scrollY) {
                scrollTo(getScrollX(), iD);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f1279m = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        if (this.f1283q && View.MeasureSpec.getMode(i9) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(FrameLayout.getChildMeasureSpec(i8, getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public boolean onNestedFling(View view, float f8, float f9, boolean z7) {
        if (z7) {
            return false;
        }
        dispatchNestedFling(0.0f, f9, true);
        s((int) f9);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public boolean onNestedPreFling(View view, float f8, float f9) {
        return dispatchNestedPreFling(f8, f9);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onNestedPreScroll(View view, int i8, int i9, int[] iArr) {
        j(view, i8, i9, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onNestedScroll(View view, int i8, int i9, int i10, int i11) {
        C(i11, 0, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onNestedScrollAccepted(View view, View view2, int i8) {
        h(view, view2, i8, 0);
    }

    @Override // android.view.View
    public void onOverScrolled(int i8, int i9, boolean z7, boolean z8) {
        super.scrollTo(i8, i9);
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i8, Rect rect) {
        if (i8 == 2) {
            i8 = 130;
        } else if (i8 == 1) {
            i8 = 33;
        }
        View viewFindNextFocus = rect == null ? FocusFinder.getInstance().findNextFocus(this, null, i8) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, i8);
        if (viewFindNextFocus == null || z(viewFindNextFocus)) {
            return false;
        }
        return viewFindNextFocus.requestFocus(i8, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
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
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1293d = getScrollY();
        return savedState;
    }

    @Override // android.view.View
    public void onScrollChanged(int i8, int i9, int i10, int i11) {
        super.onScrollChanged(i8, i9, i10, i11);
        b bVar = this.E;
        if (bVar != null) {
            bVar.a(this, i8, i9, i10, i11);
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i8, int i9, int i10, int i11) {
        super.onSizeChanged(i8, i9, i10, i11);
        View viewFindFocus = findFocus();
        if (viewFindFocus == null || this == viewFindFocus || !B(viewFindFocus, 0, i11)) {
            return;
        }
        viewFindFocus.getDrawingRect(this.f1273g);
        offsetDescendantRectToMyCoords(viewFindFocus, this.f1273g);
        k(e(this.f1273g));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public boolean onStartNestedScroll(View view, View view2, int i8) {
        return o(view, view2, i8, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, o0.n
    public void onStopNestedScroll(View view) {
        i(view, 0);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        y();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f1291y = 0;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(0.0f, this.f1291y);
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                VelocityTracker velocityTracker = this.f1282p;
                velocityTracker.computeCurrentVelocity(1000, this.f1287u);
                int yVelocity = (int) velocityTracker.getYVelocity(this.f1288v);
                if (Math.abs(yVelocity) >= this.f1286t) {
                    int i8 = -yVelocity;
                    float f8 = i8;
                    if (!dispatchNestedPreFling(0.0f, f8)) {
                        dispatchNestedFling(0.0f, f8, true);
                        s(i8);
                    }
                } else if (this.f1274h.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    t.b0(this);
                }
                this.f1288v = -1;
                l();
            } else if (actionMasked == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.f1288v);
                if (iFindPointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + this.f1288v + " in onTouchEvent");
                } else {
                    int y7 = (int) motionEvent.getY(iFindPointerIndex);
                    int i9 = this.f1277k - y7;
                    if (!this.f1281o && Math.abs(i9) > this.f1285s) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.f1281o = true;
                        i9 = i9 > 0 ? i9 - this.f1285s : i9 + this.f1285s;
                    }
                    int i10 = i9;
                    if (this.f1281o) {
                        if (f(0, i10, this.f1290x, this.f1289w, 0)) {
                            i10 -= this.f1290x[1];
                            this.f1291y += this.f1289w[1];
                        }
                        int i11 = i10;
                        this.f1277k = y7 - this.f1289w[1];
                        int scrollY = getScrollY();
                        int scrollRange = getScrollRange();
                        int overScrollMode = getOverScrollMode();
                        boolean z7 = overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0);
                        if (E(0, i11, 0, getScrollY(), 0, scrollRange, 0, 0, true) && !u(0)) {
                            this.f1282p.clear();
                        }
                        int scrollY2 = getScrollY() - scrollY;
                        int[] iArr = this.f1290x;
                        iArr[1] = 0;
                        g(0, scrollY2, 0, i11 - scrollY2, this.f1289w, 0, iArr);
                        int i12 = this.f1277k;
                        int[] iArr2 = this.f1289w;
                        this.f1277k = i12 - iArr2[1];
                        this.f1291y += iArr2[1];
                        if (z7) {
                            int i13 = i11 - this.f1290x[1];
                            p();
                            int i14 = scrollY + i13;
                            if (i14 < 0) {
                                d.a(this.f1275i, i13 / getHeight(), motionEvent.getX(iFindPointerIndex) / getWidth());
                                if (!this.f1276j.isFinished()) {
                                    this.f1276j.onRelease();
                                }
                            } else if (i14 > scrollRange) {
                                d.a(this.f1276j, i13 / getHeight(), 1.0f - (motionEvent.getX(iFindPointerIndex) / getWidth()));
                                if (!this.f1275i.isFinished()) {
                                    this.f1275i.onRelease();
                                }
                            }
                            EdgeEffect edgeEffect = this.f1275i;
                            if (edgeEffect != null && (!edgeEffect.isFinished() || !this.f1276j.isFinished())) {
                                t.b0(this);
                            }
                        }
                    }
                }
            } else if (actionMasked == 3) {
                if (this.f1281o && getChildCount() > 0 && this.f1274h.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    t.b0(this);
                }
                this.f1288v = -1;
                l();
            } else if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                this.f1277k = (int) motionEvent.getY(actionIndex);
                this.f1288v = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                D(motionEvent);
                this.f1277k = (int) motionEvent.getY(motionEvent.findPointerIndex(this.f1288v));
            }
        } else {
            if (getChildCount() == 0) {
                return false;
            }
            boolean z8 = !this.f1274h.isFinished();
            this.f1281o = z8;
            if (z8 && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.f1274h.isFinished()) {
                a();
            }
            this.f1277k = (int) motionEvent.getY();
            this.f1288v = motionEvent.getPointerId(0);
            P(2, 0);
        }
        VelocityTracker velocityTracker2 = this.f1282p;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEventObtain);
        }
        motionEventObtain.recycle();
        return true;
    }

    public final void p() {
        if (getOverScrollMode() == 2) {
            this.f1275i = null;
            this.f1276j = null;
        } else if (this.f1275i == null) {
            Context context = getContext();
            this.f1275i = new EdgeEffect(context);
            this.f1276j = new EdgeEffect(context);
        }
    }

    public boolean q(KeyEvent keyEvent) {
        this.f1273g.setEmpty();
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

    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View r(boolean r13, int r14, int r15) {
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

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (this.f1278l) {
            this.f1280n = view2;
        } else {
            J(view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z7) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return K(rect, z7);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z7) {
        if (z7) {
            G();
        }
        super.requestDisallowInterceptTouchEvent(z7);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.f1278l = true;
        super.requestLayout();
    }

    public void s(int i8) {
        if (getChildCount() > 0) {
            this.f1274h.fling(getScrollX(), getScrollY(), 0, i8, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            H(true);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i8, int i9) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int width2 = childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int height2 = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int iD = d(i8, width, width2);
            int iD2 = d(i9, height, height2);
            if (iD == getScrollX() && iD2 == getScrollY()) {
                return;
            }
            super.scrollTo(iD, iD2);
        }
    }

    public void setFillViewport(boolean z7) {
        if (z7 != this.f1283q) {
            this.f1283q = z7;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z7) {
        this.C.n(z7);
    }

    public void setOnScrollChangeListener(b bVar) {
        this.E = bVar;
    }

    public void setSmoothScrollingEnabled(boolean z7) {
        this.f1284r = z7;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i8) {
        return P(i8, 0);
    }

    @Override // android.view.View, o0.j
    public void stopNestedScroll() {
        Q(0);
    }

    public boolean t(int i8) {
        int childCount;
        boolean z7 = i8 == 130;
        int height = getHeight();
        Rect rect = this.f1273g;
        rect.top = 0;
        rect.bottom = height;
        if (z7 && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            this.f1273g.bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
            Rect rect2 = this.f1273g;
            rect2.top = rect2.bottom - height;
        }
        Rect rect3 = this.f1273g;
        return I(i8, rect3.top, rect3.bottom);
    }

    public boolean u(int i8) {
        return this.C.l(i8);
    }

    public final boolean v(int i8, int i9) {
        if (getChildCount() <= 0) {
            return false;
        }
        int scrollY = getScrollY();
        View childAt = getChildAt(0);
        return i9 >= childAt.getTop() - scrollY && i9 < childAt.getBottom() - scrollY && i8 >= childAt.getLeft() && i8 < childAt.getRight();
    }

    public final void w() {
        VelocityTracker velocityTracker = this.f1282p;
        if (velocityTracker == null) {
            this.f1282p = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    public final void x() {
        this.f1274h = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(CarPlayConfig.IapFeature.IAP_FEATURE_LIST_UPDATES);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f1285s = viewConfiguration.getScaledTouchSlop();
        this.f1286t = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f1287u = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    public final void y() {
        if (this.f1282p == null) {
            this.f1282p = VelocityTracker.obtain();
        }
    }

    public final boolean z(View view) {
        return !B(view, 0, getHeight());
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f1273g = new Rect();
        this.f1278l = true;
        this.f1279m = false;
        this.f1280n = null;
        this.f1281o = false;
        this.f1284r = true;
        this.f1288v = -1;
        this.f1289w = new int[2];
        this.f1290x = new int[2];
        x();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1271e, i8, 0);
        setFillViewport(typedArrayObtainStyledAttributes.getBoolean(0, false));
        typedArrayObtainStyledAttributes.recycle();
        this.B = new o(this);
        this.C = new k(this);
        setNestedScrollingEnabled(true);
        t.l0(this, f1270d);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i8) {
        if (getChildCount() <= 0) {
            super.addView(view, i8);
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
    public void addView(View view, int i8, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i8, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
}
