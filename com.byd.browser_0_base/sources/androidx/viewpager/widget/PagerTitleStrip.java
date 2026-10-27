package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.method.SingleLineTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import defpackage.in3;
import defpackage.kv1;
import defpackage.om3;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@ViewPager.d
public class PagerTitleStrip extends ViewGroup {
    public static final int[] s = {R.attr.textAppearance, R.attr.textSize, R.attr.textColor, R.attr.gravity};
    public static final int[] t = {R.attr.textAllCaps};
    public ViewPager e;
    public final TextView f;
    public final TextView g;
    public final TextView h;
    public int i;
    public float j;
    public int k;
    public int l;
    public boolean m;
    public boolean n;
    public final a o;
    public WeakReference<kv1> p;
    public int q;
    public int r;

    public class a extends DataSetObserver implements ViewPager.h, ViewPager.g {
        public int e;

        public a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.g
        public final void a(ViewPager viewPager, kv1 kv1Var, kv1 kv1Var2) {
            PagerTitleStrip.this.a(kv1Var, kv1Var2);
        }

        @Override // androidx.viewpager.widget.ViewPager.h
        public final void b(int i) {
            this.e = i;
        }

        @Override // androidx.viewpager.widget.ViewPager.h
        public final void c(int i) {
            if (this.e == 0) {
                PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
                pagerTitleStrip.b(pagerTitleStrip.e.getCurrentItem(), pagerTitleStrip.e.getAdapter());
                float f = pagerTitleStrip.j;
                if (f < 0.0f) {
                    f = 0.0f;
                }
                pagerTitleStrip.c(f, pagerTitleStrip.e.getCurrentItem(), true);
            }
        }

        @Override // androidx.viewpager.widget.ViewPager.h
        public final void d(int i, float f) {
            if (f > 0.5f) {
                i++;
            }
            PagerTitleStrip.this.c(f, i, false);
        }

        @Override // android.database.DataSetObserver
        public final void onChanged() {
            PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
            pagerTitleStrip.b(pagerTitleStrip.e.getCurrentItem(), pagerTitleStrip.e.getAdapter());
            float f = pagerTitleStrip.j;
            if (f < 0.0f) {
                f = 0.0f;
            }
            pagerTitleStrip.c(f, pagerTitleStrip.e.getCurrentItem(), true);
        }
    }

    public static class b extends SingleLineTransformationMethod {
        public final Locale e;

        public b(Context context) {
            this.e = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public final CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            if (transformation != null) {
                return transformation.toString().toUpperCase(this.e);
            }
            return null;
        }
    }

    public PagerTitleStrip(Context context) {
        this(context, null);
    }

    private static void setSingleLineAllCaps(TextView textView) {
        textView.setTransformationMethod(new b(textView.getContext()));
    }

    public final void a(kv1 kv1Var, kv1 kv1Var2) {
        a aVar = this.o;
        if (kv1Var != null) {
            kv1Var.a.unregisterObserver(aVar);
            this.p = null;
        }
        if (kv1Var2 != null) {
            kv1Var2.a.registerObserver(aVar);
            this.p = new WeakReference<>(kv1Var2);
        }
        ViewPager viewPager = this.e;
        if (viewPager != null) {
            this.i = -1;
            this.j = -1.0f;
            b(viewPager.getCurrentItem(), kv1Var2);
            requestLayout();
        }
    }

    public final void b(int i, kv1 kv1Var) {
        int iB = kv1Var != null ? kv1Var.b() : 0;
        this.m = true;
        CharSequence charSequenceC = null;
        CharSequence charSequenceC2 = (i < 1 || kv1Var == null) ? null : kv1Var.c(i - 1);
        TextView textView = this.f;
        textView.setText(charSequenceC2);
        TextView textView2 = this.g;
        textView2.setText((kv1Var == null || i >= iB) ? null : kv1Var.c(i));
        int i2 = i + 1;
        if (i2 < iB && kv1Var != null) {
            charSequenceC = kv1Var.c(i2);
        }
        TextView textView3 = this.h;
        textView3.setText(charSequenceC);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        textView.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        textView2.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        textView3.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.i = i;
        if (!this.n) {
            c(this.j, i, false);
        }
        this.m = false;
    }

    public void c(float f, int i, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (i != this.i) {
            b(i, this.e.getAdapter());
        } else if (!z && f == this.j) {
            return;
        }
        this.n = true;
        TextView textView = this.f;
        int measuredWidth = textView.getMeasuredWidth();
        TextView textView2 = this.g;
        int measuredWidth2 = textView2.getMeasuredWidth();
        TextView textView3 = this.h;
        int measuredWidth3 = textView3.getMeasuredWidth();
        int i6 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i7 = paddingRight + i6;
        int i8 = (width - (paddingLeft + i6)) - i7;
        float f2 = f + 0.5f;
        if (f2 > 1.0f) {
            f2 -= 1.0f;
        }
        int i9 = ((width - i7) - ((int) (i8 * f2))) - i6;
        int i10 = measuredWidth2 + i9;
        int baseline = textView.getBaseline();
        int baseline2 = textView2.getBaseline();
        int baseline3 = textView3.getBaseline();
        int iMax = Math.max(Math.max(baseline, baseline2), baseline3);
        int i11 = iMax - baseline;
        int i12 = iMax - baseline2;
        int i13 = iMax - baseline3;
        int iMax2 = Math.max(Math.max(textView.getMeasuredHeight() + i11, textView2.getMeasuredHeight() + i12), textView3.getMeasuredHeight() + i13);
        int i14 = this.l & 112;
        if (i14 == 16) {
            i2 = (((height - paddingTop) - paddingBottom) - iMax2) / 2;
        } else {
            if (i14 != 80) {
                i3 = i11 + paddingTop;
                i4 = i12 + paddingTop;
                i5 = paddingTop + i13;
                textView2.layout(i9, i4, i10, textView2.getMeasuredHeight() + i4);
                int iMin = Math.min(paddingLeft, (i9 - this.k) - measuredWidth);
                textView.layout(iMin, i3, measuredWidth + iMin, textView.getMeasuredHeight() + i3);
                int iMax3 = Math.max((width - paddingRight) - measuredWidth3, i10 + this.k);
                textView3.layout(iMax3, i5, iMax3 + measuredWidth3, textView3.getMeasuredHeight() + i5);
                this.j = f;
                this.n = false;
            }
            i2 = (height - paddingBottom) - iMax2;
        }
        i3 = i11 + i2;
        i4 = i12 + i2;
        i5 = i2 + i13;
        textView2.layout(i9, i4, i10, textView2.getMeasuredHeight() + i4);
        int iMin2 = Math.min(paddingLeft, (i9 - this.k) - measuredWidth);
        textView.layout(iMin2, i3, measuredWidth + iMin2, textView.getMeasuredHeight() + i3);
        int iMax32 = Math.max((width - paddingRight) - measuredWidth3, i10 + this.k);
        textView3.layout(iMax32, i5, iMax32 + measuredWidth3, textView3.getMeasuredHeight() + i5);
        this.j = f;
        this.n = false;
    }

    public int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.k;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        ViewPager viewPager = (ViewPager) parent;
        kv1 adapter = viewPager.getAdapter();
        a aVar = this.o;
        viewPager.W = aVar;
        viewPager.addOnAdapterChangeListener(aVar);
        this.e = viewPager;
        WeakReference<kv1> weakReference = this.p;
        a(weakReference != null ? weakReference.get() : null, adapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.e;
        if (viewPager != null) {
            a(viewPager.getAdapter(), null);
            ViewPager viewPager2 = this.e;
            ViewPager.h hVar = viewPager2.W;
            viewPager2.W = null;
            viewPager2.removeOnAdapterChangeListener(this.o);
            this.e = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.e != null) {
            float f = this.j;
            if (f < 0.0f) {
                f = 0.0f;
            }
            c(f, this.i, true);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int iMax;
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingBottom, -2);
        int size = View.MeasureSpec.getSize(i);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i, (int) (size * 0.2f), -2);
        this.f.measure(childMeasureSpec2, childMeasureSpec);
        TextView textView = this.g;
        textView.measure(childMeasureSpec2, childMeasureSpec);
        this.h.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            iMax = View.MeasureSpec.getSize(i2);
        } else {
            iMax = Math.max(getMinHeight(), textView.getMeasuredHeight() + paddingBottom);
        }
        setMeasuredDimension(size, View.resolveSizeAndState(iMax, i2, textView.getMeasuredState() << 16));
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        if (this.m) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i) {
        this.l = i;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float f) {
        int i = ((int) (f * 255.0f)) & 255;
        this.q = i;
        int i2 = (i << 24) | (this.r & 16777215);
        this.f.setTextColor(i2);
        this.h.setTextColor(i2);
    }

    public void setTextColor(int i) {
        this.r = i;
        this.g.setTextColor(i);
        int i2 = (this.q << 24) | (this.r & 16777215);
        this.f.setTextColor(i2);
        this.h.setTextColor(i2);
    }

    public void setTextSpacing(int i) {
        this.k = i;
        requestLayout();
    }

    public PagerTitleStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = -1;
        this.j = -1.0f;
        this.o = new a();
        TextView textView = new TextView(context);
        this.f = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.g = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.h = textView3;
        addView(textView3);
        int[] iArr = s;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.n.b(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        boolean z = false;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            textView.setTextAppearance(resourceId);
            textView2.setTextAppearance(resourceId);
            textView3.setTextAppearance(resourceId);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            float f = dimensionPixelSize;
            textView.setTextSize(0, f);
            textView2.setTextSize(0, f);
            textView3.setTextSize(0, f);
        }
        if (typedArrayObtainStyledAttributes.hasValue(2)) {
            int color = typedArrayObtainStyledAttributes.getColor(2, 0);
            textView.setTextColor(color);
            textView2.setTextColor(color);
            textView3.setTextColor(color);
        }
        this.l = typedArrayObtainStyledAttributes.getInteger(3, 80);
        typedArrayObtainStyledAttributes.recycle();
        this.r = textView2.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView2.setEllipsize(TextUtils.TruncateAt.END);
        textView3.setEllipsize(TextUtils.TruncateAt.END);
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, t);
            z = typedArrayObtainStyledAttributes2.getBoolean(0, false);
            typedArrayObtainStyledAttributes2.recycle();
        }
        if (z) {
            setSingleLineAllCaps(textView);
            setSingleLineAllCaps(textView2);
            setSingleLineAllCaps(textView3);
        } else {
            textView.setSingleLine();
            textView2.setSingleLine();
            textView3.setSingleLine();
        }
        this.k = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }
}
