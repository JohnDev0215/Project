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
import l0.j;
import l0.k;
import l0.m;
import l0.o;
import l0.t;
import m0.c;
import m0.e;
import n0.d;

/* JADX INFO: loaded from: classes.dex */
public class NestedScrollView extends FrameLayout implements m, j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f1181b = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f1182c = {R.attr.fillViewport};
    public final k A;
    public float B;
    public b C;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f1183d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f1184e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public OverScroller f1185f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public EdgeEffect f1186g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public EdgeEffect f1187h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1188i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f1189j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f1190k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public View f1191l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1192m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public VelocityTracker f1193n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1194o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1195p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1196q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1197r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1198s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1199t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int[] f1200u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int[] f1201v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1202w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f1203x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public SavedState f1204y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final o f1205z;

    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1206b;

        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.f1206b + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeInt(this.f1206b);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f1206b = parcel.readInt();
        }
    }

    public static class a extends l0.a {
        @Override // l0.a
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

        @Override // l0.a
        public void g(View view, c cVar) {
            int scrollRange;
            super.g(view, cVar);
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            cVar.b0(ScrollView.class.getName());
            if (!nestedScrollView.isEnabled() || (scrollRange = nestedScrollView.getScrollRange()) <= 0) {
                return;
            }
            cVar.v0(true);
            if (nestedScrollView.getScrollY() > 0) {
                cVar.b(c.a.f3815n);
                cVar.b(c.a.f3826y);
            }
            if (nestedScrollView.getScrollY() < scrollRange) {
                cVar.b(c.a.f3814m);
                cVar.b(c.a.A);
            }
        }

        @Override // l0.a
        public boolean j(View view, int i3, Bundle bundle) {
            if (super.j(view, i3, bundle)) {
                return true;
            }
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            if (!nestedScrollView.isEnabled()) {
                return false;
            }
            if (i3 != 4096) {
                if (i3 == 8192 || i3 == 16908344) {
                    int iMax = Math.max(nestedScrollView.getScrollY() - ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
                    if (iMax == nestedScrollView.getScrollY()) {
                        return false;
                    }
                    nestedScrollView.O(0, iMax, true);
                    return true;
                }
                if (i3 != 16908346) {
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
        void a(NestedScrollView nestedScrollView, int i3, int i4, int i5, int i6);
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

    public static int d(int i3, int i4, int i5) {
        if (i4 >= i5 || i3 < 0) {
            return 0;
        }
        return i4 + i3 > i5 ? i5 - i4 : i3;
    }

    private float getVerticalScrollFactorCompat() {
        if (this.B == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (!context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.B = typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.B;
    }

    public final boolean B(View view, int i3, int i4) {
        view.getDrawingRect(this.f1184e);
        offsetDescendantRectToMyCoords(view, this.f1184e);
        return this.f1184e.bottom + i3 >= getScrollY() && this.f1184e.top - i3 <= getScrollY() + i4;
    }

    public final void C(int i3, int i4, int[] iArr) {
        int scrollY = getScrollY();
        scrollBy(0, i3);
        int scrollY2 = getScrollY() - scrollY;
        if (iArr != null) {
            iArr[1] = iArr[1] + scrollY2;
        }
        this.A.e(0, scrollY2, 0, i3 - scrollY2, null, i4, iArr);
    }

    public final void D(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f1199t) {
            int i3 = actionIndex == 0 ? 1 : 0;
            this.f1188i = (int) motionEvent.getY(i3);
            this.f1199t = motionEvent.getPointerId(i3);
            VelocityTracker velocityTracker = this.f1193n;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public boolean E(int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, boolean z3) {
        boolean z4;
        boolean z5;
        int overScrollMode = getOverScrollMode();
        boolean z6 = computeHorizontalScrollRange() > computeHorizontalScrollExtent();
        boolean z7 = computeVerticalScrollRange() > computeVerticalScrollExtent();
        boolean z8 = overScrollMode == 0 || (overScrollMode == 1 && z6);
        boolean z9 = overScrollMode == 0 || (overScrollMode == 1 && z7);
        int i11 = i5 + i3;
        int i12 = !z8 ? 0 : i9;
        int i13 = i6 + i4;
        int i14 = !z9 ? 0 : i10;
        int i15 = -i12;
        int i16 = i12 + i7;
        int i17 = -i14;
        int i18 = i14 + i8;
        if (i11 > i16) {
            i11 = i16;
            z4 = true;
        } else if (i11 < i15) {
            z4 = true;
            i11 = i15;
        } else {
            z4 = false;
        }
        if (i13 > i18) {
            i13 = i18;
            z5 = true;
        } else if (i13 < i17) {
            z5 = true;
            i13 = i17;
        } else {
            z5 = false;
        }
        if (z5 && !u(1)) {
            this.f1185f.springBack(i11, i13, 0, 0, 0, getScrollRange());
        }
        onOverScrolled(i11, i13, z4, z5);
        return z4 || z5;
    }

    public boolean F(int i3) {
        boolean z3 = i3 == 130;
        int height = getHeight();
        if (z3) {
            this.f1184e.top = getScrollY() + height;
            int childCount = getChildCount();
            if (childCount > 0) {
                View childAt = getChildAt(childCount - 1);
                int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
                Rect rect = this.f1184e;
                if (rect.top + height > bottom) {
                    rect.top = bottom - height;
                }
            }
        } else {
            this.f1184e.top = getScrollY() - height;
            Rect rect2 = this.f1184e;
            if (rect2.top < 0) {
                rect2.top = 0;
            }
        }
        Rect rect3 = this.f1184e;
        int i4 = rect3.top;
        int i5 = height + i4;
        rect3.bottom = i5;
        return I(i3, i4, i5);
    }

    public final void G() {
        VelocityTracker velocityTracker = this.f1193n;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f1193n = null;
        }
    }

    public final void H(boolean z3) {
        if (z3) {
            P(2, 1);
        } else {
            Q(1);
        }
        this.f1203x = getScrollY();
        t.a0(this);
    }

    public final boolean I(int i3, int i4, int i5) {
        int height = getHeight();
        int scrollY = getScrollY();
        int i6 = height + scrollY;
        boolean z3 = false;
        boolean z4 = i3 == 33;
        View viewR = r(z4, i4, i5);
        if (viewR == null) {
            viewR = this;
        }
        if (i4 < scrollY || i5 > i6) {
            k(z4 ? i4 - scrollY : i5 - i6);
            z3 = true;
        }
        if (viewR != findFocus()) {
            viewR.requestFocus(i3);
        }
        return z3;
    }

    public final void J(View view) {
        view.getDrawingRect(this.f1184e);
        offsetDescendantRectToMyCoords(view, this.f1184e);
        int iE = e(this.f1184e);
        if (iE != 0) {
            scrollBy(0, iE);
        }
    }

    public final boolean K(Rect rect, boolean z3) {
        int iE = e(rect);
        boolean z4 = iE != 0;
        if (z4) {
            if (z3) {
                scrollBy(0, iE);
            } else {
                L(0, iE);
            }
        }
        return z4;
    }

    public final void L(int i3, int i4) {
        M(i3, i4, 250, false);
    }

    public final void M(int i3, int i4, int i5, boolean z3) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.f1183d > 250) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            this.f1185f.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(i4 + scrollY, Math.max(0, height - height2))) - scrollY, i5);
            H(z3);
        } else {
            if (!this.f1185f.isFinished()) {
                a();
            }
            scrollBy(i3, i4);
        }
        this.f1183d = AnimationUtils.currentAnimationTimeMillis();
    }

    public void N(int i3, int i4, int i5, boolean z3) {
        M(i3 - getScrollX(), i4 - getScrollY(), i5, z3);
    }

    public void O(int i3, int i4, boolean z3) {
        N(i3, i4, 250, z3);
    }

    public boolean P(int i3, int i4) {
        return this.A.p(i3, i4);
    }

    public void Q(int i3) {
        this.A.r(i3);
    }

    public final void a() {
        this.f1185f.abortAnimation();
        Q(1);
    }

    @Override // android.view.ViewGroup
    public void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    public boolean b(int i3) {
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i3);
        int maxScrollAmount = getMaxScrollAmount();
        if (viewFindNextFocus == null || !B(viewFindNextFocus, maxScrollAmount, getHeight())) {
            if (i3 == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i3 == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getScrollY() + getHeight()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i3 != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            k(maxScrollAmount);
        } else {
            viewFindNextFocus.getDrawingRect(this.f1184e);
            offsetDescendantRectToMyCoords(viewFindNextFocus, this.f1184e);
            k(e(this.f1184e));
            viewFindNextFocus.requestFocus(i3);
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
        if (this.f1185f.isFinished()) {
            return;
        }
        this.f1185f.computeScrollOffset();
        int currY = this.f1185f.getCurrY();
        int i3 = currY - this.f1203x;
        this.f1203x = currY;
        int[] iArr = this.f1201v;
        boolean z3 = false;
        iArr[1] = 0;
        f(0, i3, iArr, null, 1);
        int i4 = i3 - this.f1201v[1];
        int scrollRange = getScrollRange();
        if (i4 != 0) {
            int scrollY = getScrollY();
            E(0, i4, getScrollX(), scrollY, 0, scrollRange, 0, 0, false);
            int scrollY2 = getScrollY() - scrollY;
            int i5 = i4 - scrollY2;
            int[] iArr2 = this.f1201v;
            iArr2[1] = 0;
            g(0, scrollY2, 0, i5, this.f1200u, 1, iArr2);
            i4 = i5 - this.f1201v[1];
        }
        if (i4 != 0) {
            int overScrollMode = getOverScrollMode();
            if (overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0)) {
                z3 = true;
            }
            if (z3) {
                p();
                if (i4 < 0) {
                    if (this.f1186g.isFinished()) {
                        this.f1186g.onAbsorb((int) this.f1185f.getCurrVelocity());
                    }
                } else if (this.f1187h.isFinished()) {
                    this.f1187h.onAbsorb((int) this.f1185f.getCurrVelocity());
                }
            }
            a();
        }
        if (this.f1185f.isFinished()) {
            Q(1);
        } else {
            t.a0(this);
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
    public boolean dispatchNestedFling(float f3, float f4, boolean z3) {
        return this.A.a(f3, f4, z3);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f3, float f4) {
        return this.A.b(f3, f4);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i3, int i4, int[] iArr, int[] iArr2) {
        return f(i3, i4, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i3, int i4, int i5, int i6, int[] iArr) {
        return this.A.f(i3, i4, i5, i6, iArr);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        int paddingLeft;
        super.draw(canvas);
        if (this.f1186g != null) {
            int scrollY = getScrollY();
            int paddingLeft2 = 0;
            if (!this.f1186g.isFinished()) {
                int iSave = canvas.save();
                int width = getWidth();
                int height = getHeight();
                int iMin = Math.min(0, scrollY);
                int i3 = Build.VERSION.SDK_INT;
                if (i3 < 21 || getClipToPadding()) {
                    width -= getPaddingLeft() + getPaddingRight();
                    paddingLeft = getPaddingLeft() + 0;
                } else {
                    paddingLeft = 0;
                }
                if (i3 >= 21 && getClipToPadding()) {
                    height -= getPaddingTop() + getPaddingBottom();
                    iMin += getPaddingTop();
                }
                canvas.translate(paddingLeft, iMin);
                this.f1186g.setSize(width, height);
                if (this.f1186g.draw(canvas)) {
                    t.a0(this);
                }
                canvas.restoreToCount(iSave);
            }
            if (this.f1187h.isFinished()) {
                return;
            }
            int iSave2 = canvas.save();
            int width2 = getWidth();
            int height2 = getHeight();
            int iMax = Math.max(getScrollRange(), scrollY) + height2;
            int i4 = Build.VERSION.SDK_INT;
            if (i4 < 21 || getClipToPadding()) {
                width2 -= getPaddingLeft() + getPaddingRight();
                paddingLeft2 = 0 + getPaddingLeft();
            }
            if (i4 >= 21 && getClipToPadding()) {
                height2 -= getPaddingTop() + getPaddingBottom();
                iMax -= getPaddingBottom();
            }
            canvas.translate(paddingLeft2 - width2, iMax);
            canvas.rotate(180.0f, width2, 0.0f);
            this.f1187h.setSize(width2, height2);
            if (this.f1187h.draw(canvas)) {
                t.a0(this);
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
        int i3 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i4 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i3 - verticalFadingEdgeLength : i3;
        int i5 = rect.bottom;
        if (i5 > i4 && rect.top > scrollY) {
            return Math.min((rect.height() > height ? rect.top - scrollY : rect.bottom - i4) + 0, (childAt.getBottom() + layoutParams.bottomMargin) - i3);
        }
        if (rect.top >= scrollY || i5 >= i4) {
            return 0;
        }
        return Math.max(rect.height() > height ? 0 - (i4 - rect.bottom) : 0 - (scrollY - rect.top), -getScrollY());
    }

    public boolean f(int i3, int i4, int[] iArr, int[] iArr2, int i5) {
        return this.A.d(i3, i4, iArr, iArr2, i5);
    }

    public void g(int i3, int i4, int i5, int i6, int[] iArr, int i7, int[] iArr2) {
        this.A.e(i3, i4, i5, i6, iArr, i7, iArr2);
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
        return this.f1205z.a();
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

    @Override // l0.l
    public void h(View view, View view2, int i3, int i4) {
        this.f1205z.c(view, view2, i3, i4);
        P(2, i4);
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return u(0);
    }

    @Override // l0.l
    public void i(View view, int i3) {
        this.f1205z.d(view, i3);
        Q(i3);
    }

    @Override // android.view.View, l0.j
    public boolean isNestedScrollingEnabled() {
        return this.A.l();
    }

    @Override // l0.l
    public void j(View view, int i3, int i4, int[] iArr, int i5) {
        f(i3, i4, iArr, null, i5);
    }

    public final void k(int i3) {
        if (i3 != 0) {
            if (this.f1195p) {
                L(0, i3);
            } else {
                scrollBy(0, i3);
            }
        }
    }

    public final void l() {
        this.f1192m = false;
        G();
        Q(0);
        EdgeEffect edgeEffect = this.f1186g;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            this.f1187h.onRelease();
        }
    }

    @Override // l0.m
    public void m(View view, int i3, int i4, int i5, int i6, int i7, int[] iArr) {
        C(i6, i7, iArr);
    }

    @Override // android.view.ViewGroup
    public void measureChild(View view, int i3, int i4) {
        view.measure(FrameLayout.getChildMeasureSpec(i3, getPaddingLeft() + getPaddingRight(), view.getLayoutParams().width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    public void measureChildWithMargins(View view, int i3, int i4, int i5, int i6) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(FrameLayout.getChildMeasureSpec(i3, getPaddingLeft() + getPaddingRight() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i4, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    @Override // l0.l
    public void n(View view, int i3, int i4, int i5, int i6, int i7) {
        C(i6, i7, null);
    }

    @Override // l0.l
    public boolean o(View view, View view2, int i3, int i4) {
        return (i3 & 2) != 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1190k = false;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8 && !this.f1192m) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                int verticalScrollFactorCompat = (int) (axisValue * getVerticalScrollFactorCompat());
                int scrollRange = getScrollRange();
                int scrollY = getScrollY();
                int i3 = scrollY - verticalScrollFactorCompat;
                if (i3 < 0) {
                    scrollRange = 0;
                } else if (i3 <= scrollRange) {
                    scrollRange = i3;
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
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        super.onLayout(z3, i3, i4, i5, i6);
        int measuredHeight = 0;
        this.f1189j = false;
        View view = this.f1191l;
        if (view != null && A(view, this)) {
            J(this.f1191l);
        }
        this.f1191l = null;
        if (!this.f1190k) {
            if (this.f1204y != null) {
                scrollTo(getScrollX(), this.f1204y.f1206b);
                this.f1204y = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                measuredHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            }
            int paddingTop = ((i6 - i4) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            int iD = d(scrollY, paddingTop, measuredHeight);
            if (iD != scrollY) {
                scrollTo(getScrollX(), iD);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f1190k = true;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i3, int i4) {
        super.onMeasure(i3, i4);
        if (this.f1194o && View.MeasureSpec.getMode(i4) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(FrameLayout.getChildMeasureSpec(i3, getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public boolean onNestedFling(View view, float f3, float f4, boolean z3) {
        if (z3) {
            return false;
        }
        dispatchNestedFling(0.0f, f4, true);
        s((int) f4);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public boolean onNestedPreFling(View view, float f3, float f4) {
        return dispatchNestedPreFling(f3, f4);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public void onNestedPreScroll(View view, int i3, int i4, int[] iArr) {
        j(view, i3, i4, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public void onNestedScroll(View view, int i3, int i4, int i5, int i6) {
        C(i6, 0, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public void onNestedScrollAccepted(View view, View view2, int i3) {
        h(view, view2, i3, 0);
    }

    @Override // android.view.View
    public void onOverScrolled(int i3, int i4, boolean z3, boolean z4) {
        super.scrollTo(i3, i4);
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i3, Rect rect) {
        if (i3 == 2) {
            i3 = 130;
        } else if (i3 == 1) {
            i3 = 33;
        }
        View viewFindNextFocus = rect == null ? FocusFinder.getInstance().findNextFocus(this, null, i3) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, i3);
        if (viewFindNextFocus == null || z(viewFindNextFocus)) {
            return false;
        }
        return viewFindNextFocus.requestFocus(i3, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f1204y = savedState;
        requestLayout();
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f1206b = getScrollY();
        return savedState;
    }

    @Override // android.view.View
    public void onScrollChanged(int i3, int i4, int i5, int i6) {
        super.onScrollChanged(i3, i4, i5, i6);
        b bVar = this.C;
        if (bVar != null) {
            bVar.a(this, i3, i4, i5, i6);
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i3, int i4, int i5, int i6) {
        super.onSizeChanged(i3, i4, i5, i6);
        View viewFindFocus = findFocus();
        if (viewFindFocus == null || this == viewFindFocus || !B(viewFindFocus, 0, i6)) {
            return;
        }
        viewFindFocus.getDrawingRect(this.f1184e);
        offsetDescendantRectToMyCoords(viewFindFocus, this.f1184e);
        k(e(this.f1184e));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public boolean onStartNestedScroll(View view, View view2, int i3) {
        return o(view, view2, i3, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent, l0.n
    public void onStopNestedScroll(View view) {
        i(view, 0);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        y();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f1202w = 0;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.offsetLocation(0.0f, this.f1202w);
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                VelocityTracker velocityTracker = this.f1193n;
                velocityTracker.computeCurrentVelocity(1000, this.f1198s);
                int yVelocity = (int) velocityTracker.getYVelocity(this.f1199t);
                if (Math.abs(yVelocity) >= this.f1197r) {
                    int i3 = -yVelocity;
                    float f3 = i3;
                    if (!dispatchNestedPreFling(0.0f, f3)) {
                        dispatchNestedFling(0.0f, f3, true);
                        s(i3);
                    }
                } else if (this.f1185f.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    t.a0(this);
                }
                this.f1199t = -1;
                l();
            } else if (actionMasked == 2) {
                int iFindPointerIndex = motionEvent.findPointerIndex(this.f1199t);
                if (iFindPointerIndex == -1) {
                    Log.e("NestedScrollView", "Invalid pointerId=" + this.f1199t + " in onTouchEvent");
                } else {
                    int y3 = (int) motionEvent.getY(iFindPointerIndex);
                    int i4 = this.f1188i - y3;
                    if (!this.f1192m && Math.abs(i4) > this.f1196q) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.f1192m = true;
                        i4 = i4 > 0 ? i4 - this.f1196q : i4 + this.f1196q;
                    }
                    int i5 = i4;
                    if (this.f1192m) {
                        if (f(0, i5, this.f1201v, this.f1200u, 0)) {
                            i5 -= this.f1201v[1];
                            this.f1202w += this.f1200u[1];
                        }
                        int i6 = i5;
                        this.f1188i = y3 - this.f1200u[1];
                        int scrollY = getScrollY();
                        int scrollRange = getScrollRange();
                        int overScrollMode = getOverScrollMode();
                        boolean z3 = overScrollMode == 0 || (overScrollMode == 1 && scrollRange > 0);
                        if (E(0, i6, 0, getScrollY(), 0, scrollRange, 0, 0, true) && !u(0)) {
                            this.f1193n.clear();
                        }
                        int scrollY2 = getScrollY() - scrollY;
                        int[] iArr = this.f1201v;
                        iArr[1] = 0;
                        g(0, scrollY2, 0, i6 - scrollY2, this.f1200u, 0, iArr);
                        int i7 = this.f1188i;
                        int[] iArr2 = this.f1200u;
                        this.f1188i = i7 - iArr2[1];
                        this.f1202w += iArr2[1];
                        if (z3) {
                            int i8 = i6 - this.f1201v[1];
                            p();
                            int i9 = scrollY + i8;
                            if (i9 < 0) {
                                d.a(this.f1186g, i8 / getHeight(), motionEvent.getX(iFindPointerIndex) / getWidth());
                                if (!this.f1187h.isFinished()) {
                                    this.f1187h.onRelease();
                                }
                            } else if (i9 > scrollRange) {
                                d.a(this.f1187h, i8 / getHeight(), 1.0f - (motionEvent.getX(iFindPointerIndex) / getWidth()));
                                if (!this.f1186g.isFinished()) {
                                    this.f1186g.onRelease();
                                }
                            }
                            EdgeEffect edgeEffect = this.f1186g;
                            if (edgeEffect != null && (!edgeEffect.isFinished() || !this.f1187h.isFinished())) {
                                t.a0(this);
                            }
                        }
                    }
                }
            } else if (actionMasked == 3) {
                if (this.f1192m && getChildCount() > 0 && this.f1185f.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                    t.a0(this);
                }
                this.f1199t = -1;
                l();
            } else if (actionMasked == 5) {
                int actionIndex = motionEvent.getActionIndex();
                this.f1188i = (int) motionEvent.getY(actionIndex);
                this.f1199t = motionEvent.getPointerId(actionIndex);
            } else if (actionMasked == 6) {
                D(motionEvent);
                this.f1188i = (int) motionEvent.getY(motionEvent.findPointerIndex(this.f1199t));
            }
        } else {
            if (getChildCount() == 0) {
                return false;
            }
            boolean z4 = !this.f1185f.isFinished();
            this.f1192m = z4;
            if (z4 && (parent = getParent()) != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
            if (!this.f1185f.isFinished()) {
                a();
            }
            this.f1188i = (int) motionEvent.getY();
            this.f1199t = motionEvent.getPointerId(0);
            P(2, 0);
        }
        VelocityTracker velocityTracker2 = this.f1193n;
        if (velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEventObtain);
        }
        motionEventObtain.recycle();
        return true;
    }

    public final void p() {
        if (getOverScrollMode() == 2) {
            this.f1186g = null;
            this.f1187h = null;
        } else if (this.f1186g == null) {
            Context context = getContext();
            this.f1186g = new EdgeEffect(context);
            this.f1187h = new EdgeEffect(context);
        }
    }

    public boolean q(KeyEvent keyEvent) {
        this.f1184e.setEmpty();
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
        if (this.f1189j) {
            this.f1191l = view2;
        } else {
            J(view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z3) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        return K(rect, z3);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z3) {
        if (z3) {
            G();
        }
        super.requestDisallowInterceptTouchEvent(z3);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.f1189j = true;
        super.requestLayout();
    }

    public void s(int i3) {
        if (getChildCount() > 0) {
            this.f1185f.fling(getScrollX(), getScrollY(), 0, i3, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            H(true);
        }
    }

    @Override // android.view.View
    public void scrollTo(int i3, int i4) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int width2 = childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int height2 = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int iD = d(i3, width, width2);
            int iD2 = d(i4, height, height2);
            if (iD == getScrollX() && iD2 == getScrollY()) {
                return;
            }
            super.scrollTo(iD, iD2);
        }
    }

    public void setFillViewport(boolean z3) {
        if (z3 != this.f1194o) {
            this.f1194o = z3;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z3) {
        this.A.m(z3);
    }

    public void setOnScrollChangeListener(b bVar) {
        this.C = bVar;
    }

    public void setSmoothScrollingEnabled(boolean z3) {
        this.f1195p = z3;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i3) {
        return P(i3, 0);
    }

    @Override // android.view.View, l0.j
    public void stopNestedScroll() {
        Q(0);
    }

    public boolean t(int i3) {
        int childCount;
        boolean z3 = i3 == 130;
        int height = getHeight();
        Rect rect = this.f1184e;
        rect.top = 0;
        rect.bottom = height;
        if (z3 && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            this.f1184e.bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin + getPaddingBottom();
            Rect rect2 = this.f1184e;
            rect2.top = rect2.bottom - height;
        }
        Rect rect3 = this.f1184e;
        return I(i3, rect3.top, rect3.bottom);
    }

    public boolean u(int i3) {
        return this.A.k(i3);
    }

    public final boolean v(int i3, int i4) {
        if (getChildCount() <= 0) {
            return false;
        }
        int scrollY = getScrollY();
        View childAt = getChildAt(0);
        return i4 >= childAt.getTop() - scrollY && i4 < childAt.getBottom() - scrollY && i3 >= childAt.getLeft() && i3 < childAt.getRight();
    }

    public final void w() {
        VelocityTracker velocityTracker = this.f1193n;
        if (velocityTracker == null) {
            this.f1193n = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
    }

    public final void x() {
        this.f1185f = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f1196q = viewConfiguration.getScaledTouchSlop();
        this.f1197r = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f1198s = viewConfiguration.getScaledMaximumFlingVelocity();
    }

    public final void y() {
        if (this.f1193n == null) {
            this.f1193n = VelocityTracker.obtain();
        }
    }

    public final boolean z(View view) {
        return !B(view, 0, getHeight());
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f1184e = new Rect();
        this.f1189j = true;
        this.f1190k = false;
        this.f1191l = null;
        this.f1192m = false;
        this.f1195p = true;
        this.f1199t = -1;
        this.f1200u = new int[2];
        this.f1201v = new int[2];
        x();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1182c, i3, 0);
        setFillViewport(typedArrayObtainStyledAttributes.getBoolean(0, false));
        typedArrayObtainStyledAttributes.recycle();
        this.f1205z = new o(this);
        this.A = new k(this);
        setNestedScrollingEnabled(true);
        t.j0(this, f1181b);
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i3) {
        if (getChildCount() <= 0) {
            super.addView(view, i3);
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
    public void addView(View view, int i3, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i3, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
}
