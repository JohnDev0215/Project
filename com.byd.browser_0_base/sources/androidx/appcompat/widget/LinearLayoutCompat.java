package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.LinearLayout;
import defpackage.in3;
import defpackage.om3;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    public boolean e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public float k;
    public boolean l;
    public int[] m;
    public int[] n;
    public Drawable o;
    public int p;
    public int q;
    public int r;
    public int s;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<LinearLayoutCompat> {
        public boolean a = false;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;

        @Override // android.view.inspector.InspectionCompanion
        public final void mapProperties(PropertyMapper propertyMapper) {
            this.b = propertyMapper.mapBoolean("baselineAligned", R.attr.baselineAligned);
            this.c = propertyMapper.mapInt("baselineAlignedChildIndex", R.attr.baselineAlignedChildIndex);
            this.d = propertyMapper.mapGravity("gravity", R.attr.gravity);
            this.e = propertyMapper.mapIntEnum("orientation", R.attr.orientation, new IntFunction<String>() { // from class: androidx.appcompat.widget.LinearLayoutCompat.InspectionCompanion.1
                @Override // java.util.function.IntFunction
                public final String apply(int i) {
                    return i != 0 ? i != 1 ? String.valueOf(i) : "vertical" : "horizontal";
                }
            });
            this.f = propertyMapper.mapFloat("weightSum", R.attr.weightSum);
            this.g = propertyMapper.mapObject("divider", androidx.appcompat.R.attr.divider);
            this.h = propertyMapper.mapInt("dividerPadding", androidx.appcompat.R.attr.dividerPadding);
            this.i = propertyMapper.mapBoolean("measureWithLargestChild", androidx.appcompat.R.attr.measureWithLargestChild);
            this.j = propertyMapper.mapIntFlag("showDividers", androidx.appcompat.R.attr.showDividers, new IntFunction<Set<String>>() { // from class: androidx.appcompat.widget.LinearLayoutCompat.InspectionCompanion.2
                @Override // java.util.function.IntFunction
                public final Set<String> apply(int i) {
                    HashSet hashSet = new HashSet();
                    if (i == 0) {
                        hashSet.add("none");
                    }
                    if (i == 1) {
                        hashSet.add("beginning");
                    }
                    if (i == 2) {
                        hashSet.add("middle");
                    }
                    if (i == 4) {
                        hashSet.add("end");
                    }
                    return hashSet;
                }
            });
            this.a = true;
        }

        @Override // android.view.inspector.InspectionCompanion
        public final void readProperties(LinearLayoutCompat linearLayoutCompat, PropertyReader propertyReader) {
            LinearLayoutCompat linearLayoutCompat2 = linearLayoutCompat;
            if (!this.a) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readBoolean(this.b, linearLayoutCompat2.e);
            propertyReader.readInt(this.c, linearLayoutCompat2.getBaselineAlignedChildIndex());
            propertyReader.readGravity(this.d, linearLayoutCompat2.getGravity());
            propertyReader.readIntEnum(this.e, linearLayoutCompat2.getOrientation());
            propertyReader.readFloat(this.f, linearLayoutCompat2.getWeightSum());
            propertyReader.readObject(this.g, linearLayoutCompat2.getDividerDrawable());
            propertyReader.readInt(this.h, linearLayoutCompat2.getDividerPadding());
            propertyReader.readBoolean(this.i, linearLayoutCompat2.l);
            propertyReader.readIntFlag(this.j, linearLayoutCompat2.getShowDividers());
        }
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(float f) {
            super(0, -2, f);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void d(Canvas canvas, int i) {
        this.o.setBounds(getPaddingLeft() + this.s, i, (getWidth() - getPaddingRight()) - this.s, this.q + i);
        this.o.draw(canvas);
    }

    public final void e(Canvas canvas, int i) {
        this.o.setBounds(i, getPaddingTop() + this.s, this.p + i, (getHeight() - getPaddingBottom()) - this.s);
        this.o.draw(canvas);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateDefaultLayoutParams() {
        int i = this.h;
        if (i == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.f < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.f;
        if (childCount <= i2) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i2);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.f == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.g;
        if (this.h == 1 && (i = this.i & 112) != 48) {
            if (i == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.j) / 2;
            } else if (i == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.j;
            }
        }
        return bottom + ((LinearLayout.LayoutParams) ((LayoutParams) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.f;
    }

    public Drawable getDividerDrawable() {
        return this.o;
    }

    public int getDividerPadding() {
        return this.s;
    }

    public int getDividerWidth() {
        return this.p;
    }

    public int getGravity() {
        return this.i;
    }

    public int getOrientation() {
        return this.h;
    }

    public int getShowDividers() {
        return this.r;
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.k;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public final boolean i(int i) {
        if (i == 0) {
            return (this.r & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.r & 4) != 0;
        }
        if ((this.r & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int right;
        int left;
        int i;
        if (this.o == null) {
            return;
        }
        int i2 = 0;
        if (this.h == 1) {
            int virtualChildCount = getVirtualChildCount();
            while (i2 < virtualChildCount) {
                View childAt = getChildAt(i2);
                if (childAt != null && childAt.getVisibility() != 8 && i(i2)) {
                    d(canvas, (childAt.getTop() - ((LinearLayout.LayoutParams) ((LayoutParams) childAt.getLayoutParams())).topMargin) - this.q);
                }
                i2++;
            }
            if (i(virtualChildCount)) {
                View childAt2 = getChildAt(virtualChildCount - 1);
                d(canvas, childAt2 == null ? (getHeight() - getPaddingBottom()) - this.q : childAt2.getBottom() + ((LinearLayout.LayoutParams) ((LayoutParams) childAt2.getLayoutParams())).bottomMargin);
                return;
            }
            return;
        }
        int virtualChildCount2 = getVirtualChildCount();
        boolean zA = ViewUtils.a(this);
        while (i2 < virtualChildCount2) {
            View childAt3 = getChildAt(i2);
            if (childAt3 != null && childAt3.getVisibility() != 8 && i(i2)) {
                LayoutParams layoutParams = (LayoutParams) childAt3.getLayoutParams();
                e(canvas, zA ? childAt3.getRight() + ((LinearLayout.LayoutParams) layoutParams).rightMargin : (childAt3.getLeft() - ((LinearLayout.LayoutParams) layoutParams).leftMargin) - this.p);
            }
            i2++;
        }
        if (i(virtualChildCount2)) {
            View childAt4 = getChildAt(virtualChildCount2 - 1);
            if (childAt4 != null) {
                LayoutParams layoutParams2 = (LayoutParams) childAt4.getLayoutParams();
                if (zA) {
                    left = childAt4.getLeft() - ((LinearLayout.LayoutParams) layoutParams2).leftMargin;
                    i = this.p;
                    right = left - i;
                } else {
                    right = childAt4.getRight() + ((LinearLayout.LayoutParams) layoutParams2).rightMargin;
                }
            } else if (zA) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i = this.p;
                right = left - i;
            }
            e(canvas, right);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01ab  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onLayout(boolean r26, int r27, int r28, int r29, int r30) {
        /*
            Method dump skipped, instruction units count: 470
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:152:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x049e  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x04a3  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x04cb  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x04d8  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x04e6  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x04fa  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0551  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0563  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x056b  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0579  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x060f  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x06c4  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x06cb  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x06e7  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x07fc  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x084a  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x0881  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x08a4  */
    /* JADX WARN: Removed duplicated region for block: B:448:? A[RETURN, SYNTHETIC] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r39, int r40) {
        /*
            Method dump skipped, instruction units count: 2274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.onMeasure(int, int):void");
    }

    public void setBaselineAligned(boolean z) {
        this.e = z;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i >= 0 && i < getChildCount()) {
            this.f = i;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.o) {
            return;
        }
        this.o = drawable;
        if (drawable != null) {
            this.p = drawable.getIntrinsicWidth();
            this.q = drawable.getIntrinsicHeight();
        } else {
            this.p = 0;
            this.q = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i) {
        this.s = i;
    }

    public void setGravity(int i) {
        if (this.i != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.i = i;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        int i3 = this.i;
        if ((8388615 & i3) != i2) {
            this.i = i2 | ((-8388616) & i3);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.l = z;
    }

    public void setOrientation(int i) {
        if (this.h != i) {
            this.h = i;
            requestLayout();
        }
    }

    public void setShowDividers(int i) {
        if (i != this.r) {
            requestLayout();
        }
        this.r = i;
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.i;
        if ((i3 & 112) != i2) {
            this.i = i2 | (i3 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f) {
        this.k = Math.max(0.0f, f);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = true;
        this.f = -1;
        this.g = 0;
        this.i = 8388659;
        int[] iArr = androidx.appcompat.R.styleable.LinearLayoutCompat;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        TintTypedArray tintTypedArray = new TintTypedArray(context, typedArrayObtainStyledAttributes);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.n.b(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        int iH = tintTypedArray.h(androidx.appcompat.R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (iH >= 0) {
            setOrientation(iH);
        }
        int iH2 = tintTypedArray.h(androidx.appcompat.R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (iH2 >= 0) {
            setGravity(iH2);
        }
        boolean zA = tintTypedArray.a(androidx.appcompat.R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!zA) {
            setBaselineAligned(zA);
        }
        this.k = typedArrayObtainStyledAttributes.getFloat(androidx.appcompat.R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.f = tintTypedArray.h(androidx.appcompat.R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.l = tintTypedArray.a(androidx.appcompat.R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(tintTypedArray.e(androidx.appcompat.R.styleable.LinearLayoutCompat_divider));
        this.r = tintTypedArray.h(androidx.appcompat.R.styleable.LinearLayoutCompat_showDividers, 0);
        this.s = tintTypedArray.d(androidx.appcompat.R.styleable.LinearLayoutCompat_dividerPadding, 0);
        tintTypedArray.n();
    }
}
