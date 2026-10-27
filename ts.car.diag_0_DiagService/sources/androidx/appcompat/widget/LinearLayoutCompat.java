package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$styleable;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f289c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private float i;
    private boolean j;
    private int[] k;
    private int[] l;
    private Drawable m;
    private int n;
    private int o;
    private int p;
    private int q;

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface DividerMode {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public @interface OrientationMode {
    }

    public LinearLayoutCompat(@NonNull Context context) {
        this(context, null);
    }

    private void D(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    private void i(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View viewT = t(i3);
            if (viewT.getVisibility() != 8) {
                a aVar = (a) viewT.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).height == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) aVar).width;
                    ((ViewGroup.MarginLayoutParams) aVar).width = viewT.getMeasuredWidth();
                    measureChildWithMargins(viewT, i2, 0, iMakeMeasureSpec, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).width = i4;
                }
            }
        }
    }

    private void j(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View viewT = t(i3);
            if (viewT.getVisibility() != 8) {
                a aVar = (a) viewT.getLayoutParams();
                if (((ViewGroup.MarginLayoutParams) aVar).width == -1) {
                    int i4 = ((ViewGroup.MarginLayoutParams) aVar).height;
                    ((ViewGroup.MarginLayoutParams) aVar).height = viewT.getMeasuredHeight();
                    measureChildWithMargins(viewT, iMakeMeasureSpec, 0, i2, 0);
                    ((ViewGroup.MarginLayoutParams) aVar).height = i4;
                }
            }
        }
    }

    int A(int i) {
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:150:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0335  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void B(int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 916
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.B(int, int):void");
    }

    public void C(boolean z) {
        this.f289c = z;
    }

    public void E(Drawable drawable) {
        if (drawable == this.m) {
            return;
        }
        this.m = drawable;
        if (drawable != null) {
            this.n = drawable.getIntrinsicWidth();
            this.o = drawable.getIntrinsicHeight();
        } else {
            this.n = 0;
            this.o = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void F(int i) {
        if (this.g != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.g = i;
            requestLayout();
        }
    }

    public void G(int i) {
        if (this.f != i) {
            this.f = i;
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a;
    }

    void e(Canvas canvas) {
        int right;
        int left;
        int i;
        int iU = u();
        boolean zB = a0.b(this);
        for (int i2 = 0; i2 < iU; i2++) {
            View viewT = t(i2);
            if (viewT != null && viewT.getVisibility() != 8 && v(i2)) {
                a aVar = (a) viewT.getLayoutParams();
                h(canvas, zB ? viewT.getRight() + ((ViewGroup.MarginLayoutParams) aVar).rightMargin : (viewT.getLeft() - ((ViewGroup.MarginLayoutParams) aVar).leftMargin) - this.n);
            }
        }
        if (v(iU)) {
            View viewT2 = t(iU - 1);
            if (viewT2 != null) {
                a aVar2 = (a) viewT2.getLayoutParams();
                if (zB) {
                    left = viewT2.getLeft() - ((ViewGroup.MarginLayoutParams) aVar2).leftMargin;
                    i = this.n;
                    right = left - i;
                } else {
                    right = viewT2.getRight() + ((ViewGroup.MarginLayoutParams) aVar2).rightMargin;
                }
            } else if (zB) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i = this.n;
                right = left - i;
            }
            h(canvas, right);
        }
    }

    void f(Canvas canvas) {
        int iU = u();
        for (int i = 0; i < iU; i++) {
            View viewT = t(i);
            if (viewT != null && viewT.getVisibility() != 8 && v(i)) {
                g(canvas, (viewT.getTop() - ((ViewGroup.MarginLayoutParams) ((a) viewT.getLayoutParams())).topMargin) - this.o);
            }
        }
        if (v(iU)) {
            View viewT2 = t(iU - 1);
            g(canvas, viewT2 == null ? (getHeight() - getPaddingBottom()) - this.o : viewT2.getBottom() + ((ViewGroup.MarginLayoutParams) ((a) viewT2.getLayoutParams())).bottomMargin);
        }
    }

    void g(Canvas canvas, int i) {
        this.m.setBounds(getPaddingLeft() + this.q, i, (getWidth() - getPaddingRight()) - this.q, this.o + i);
        this.m.draw(canvas);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.d < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.d;
        if (childCount <= i2) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i2);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.d == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.e;
        if (this.f == 1 && (i = this.g & 112) != 48) {
            if (i == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.h) / 2;
            } else if (i == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.h;
            }
        }
        return bottom + ((ViewGroup.MarginLayoutParams) ((a) childAt.getLayoutParams())).topMargin + baseline;
    }

    void h(Canvas canvas, int i) {
        this.m.setBounds(i, getPaddingTop() + this.q, this.n + i, (getHeight() - getPaddingBottom()) - this.q);
        this.m.draw(canvas);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public a generateDefaultLayoutParams() {
        int i = this.f;
        if (i == 0) {
            return new a(-2, -2);
        }
        if (i == 1) {
            return new a(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public a generateLayoutParams(AttributeSet attributeSet) {
        return new a(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public a generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new a(layoutParams);
    }

    int n(View view, int i) {
        return 0;
    }

    public Drawable o() {
        return this.m;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.m == null) {
            return;
        }
        if (this.f == 1) {
            f(canvas);
        } else {
            e(canvas);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f == 1) {
            x(i, i2, i3, i4);
        } else {
            w(i, i2, i3, i4);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.f == 1) {
            B(i, i2);
        } else {
            z(i, i2);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public int p() {
        return this.n;
    }

    public int q() {
        return this.g;
    }

    int r(View view) {
        return 0;
    }

    int s(View view) {
        return 0;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    View t(int i) {
        return getChildAt(i);
    }

    int u() {
        return getChildCount();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    protected boolean v(int i) {
        if (i == 0) {
            return (this.p & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.p & 4) != 0;
        }
        if ((this.p & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ff  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void w(int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 329
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.w(int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void x(int r18, int r19, int r20, int r21) {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.x(int, int, int, int):void");
    }

    void y(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* JADX WARN: Removed duplicated region for block: B:197:0x0455  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void z(int r39, int r40) {
        /*
            Method dump skipped, instruction units count: 1296
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.z(int, int):void");
    }

    public LinearLayoutCompat(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f289c = true;
        this.d = -1;
        this.e = 0;
        this.g = 8388659;
        U u = U.u(context, attributeSet, R$styleable.LinearLayoutCompat, i, 0);
        ViewCompat.K(this, context, R$styleable.LinearLayoutCompat, attributeSet, u.q(), i, 0);
        int iJ = u.j(R$styleable.LinearLayoutCompat_android_orientation, -1);
        if (iJ >= 0) {
            G(iJ);
        }
        int iJ2 = u.j(R$styleable.LinearLayoutCompat_android_gravity, -1);
        if (iJ2 >= 0) {
            F(iJ2);
        }
        boolean zA = u.a(R$styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!zA) {
            C(zA);
        }
        this.i = u.h(R$styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.d = u.j(R$styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.j = u.a(R$styleable.LinearLayoutCompat_measureWithLargestChild, false);
        E(u.f(R$styleable.LinearLayoutCompat_divider));
        this.p = u.j(R$styleable.LinearLayoutCompat_showDividers, 0);
        this.q = u.e(R$styleable.LinearLayoutCompat_dividerPadding, 0);
        u.v();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class a extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f290a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f291b;

        public a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f291b = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.LinearLayoutCompat_Layout);
            this.f290a = typedArrayObtainStyledAttributes.getFloat(R$styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.f291b = typedArrayObtainStyledAttributes.getInt(R$styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            typedArrayObtainStyledAttributes.recycle();
        }

        public a(int i, int i2) {
            super(i, i2);
            this.f291b = -1;
            this.f290a = 0.0f;
        }

        public a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f291b = -1;
        }
    }
}
