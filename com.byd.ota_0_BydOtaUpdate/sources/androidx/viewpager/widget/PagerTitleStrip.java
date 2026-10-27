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
import b.f.n.i;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
@ViewPager.e
public class PagerTitleStrip extends ViewGroup {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f679c = {R.attr.textAppearance, R.attr.textSize, R.attr.textColor, R.attr.gravity};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f680d = {R.attr.textAllCaps};
    public TextView E3;
    public int F3;
    public float G3;
    public int H3;
    public int I3;
    public boolean J3;
    public boolean K3;
    public final a L3;
    public WeakReference<b.r.a.a> M3;
    public int N3;
    public int O3;
    public ViewPager s;
    public TextView x;
    public TextView y;

    public class a extends DataSetObserver implements ViewPager.j, ViewPager.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f681a;

        public a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void a(int i2, float f2, int i3) {
            if (f2 > 0.5f) {
                i2++;
            }
            PagerTitleStrip.this.d(i2, f2, false);
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void b(ViewPager viewPager, b.r.a.a aVar, b.r.a.a aVar2) {
            PagerTitleStrip.this.b(aVar, aVar2);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void c(int i2) {
            this.f681a = i2;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void d(int i2) {
            if (this.f681a == 0) {
                PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
                pagerTitleStrip.c(pagerTitleStrip.s.getCurrentItem(), PagerTitleStrip.this.s.getAdapter());
                PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
                float f2 = pagerTitleStrip2.G3;
                if (f2 < 0.0f) {
                    f2 = 0.0f;
                }
                pagerTitleStrip2.d(pagerTitleStrip2.s.getCurrentItem(), f2, true);
            }
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
            pagerTitleStrip.c(pagerTitleStrip.s.getCurrentItem(), PagerTitleStrip.this.s.getAdapter());
            PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
            float f2 = pagerTitleStrip2.G3;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            pagerTitleStrip2.d(pagerTitleStrip2.s.getCurrentItem(), f2, true);
        }
    }

    public static class b extends SingleLineTransformationMethod {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Locale f683c;

        public b(Context context) {
            this.f683c = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            if (transformation != null) {
                return transformation.toString().toUpperCase(this.f683c);
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

    public void a(int i2, float f2) {
        this.x.setTextSize(i2, f2);
        this.y.setTextSize(i2, f2);
        this.E3.setTextSize(i2, f2);
    }

    public void b(b.r.a.a aVar, b.r.a.a aVar2) {
        if (aVar != null) {
            aVar.t(this.L3);
            this.M3 = null;
        }
        if (aVar2 != null) {
            aVar2.l(this.L3);
            this.M3 = new WeakReference<>(aVar2);
        }
        ViewPager viewPager = this.s;
        if (viewPager != null) {
            this.F3 = -1;
            this.G3 = -1.0f;
            c(viewPager.getCurrentItem(), aVar2);
            requestLayout();
        }
    }

    public void c(int i2, b.r.a.a aVar) {
        int iE = aVar != null ? aVar.e() : 0;
        this.J3 = true;
        CharSequence charSequenceG = null;
        this.x.setText((i2 < 1 || aVar == null) ? null : aVar.g(i2 - 1));
        this.y.setText((aVar == null || i2 >= iE) ? null : aVar.g(i2));
        int i3 = i2 + 1;
        if (i3 < iE && aVar != null) {
            charSequenceG = aVar.g(i3);
        }
        this.E3.setText(charSequenceG);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.x.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.y.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.E3.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.F3 = i2;
        if (!this.K3) {
            d(i2, this.G3, false);
        }
        this.J3 = false;
    }

    public void d(int i2, float f2, boolean z) {
        int i3;
        int i4;
        int i5;
        int i6;
        if (i2 != this.F3) {
            c(i2, this.s.getAdapter());
        } else if (!z && f2 == this.G3) {
            return;
        }
        this.K3 = true;
        int measuredWidth = this.x.getMeasuredWidth();
        int measuredWidth2 = this.y.getMeasuredWidth();
        int measuredWidth3 = this.E3.getMeasuredWidth();
        int i7 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i8 = paddingRight + i7;
        int i9 = (width - (paddingLeft + i7)) - i8;
        float f3 = 0.5f + f2;
        if (f3 > 1.0f) {
            f3 -= 1.0f;
        }
        int i10 = ((width - i8) - ((int) (i9 * f3))) - i7;
        int i11 = measuredWidth2 + i10;
        int baseline = this.x.getBaseline();
        int baseline2 = this.y.getBaseline();
        int baseline3 = this.E3.getBaseline();
        int iMax = Math.max(Math.max(baseline, baseline2), baseline3);
        int i12 = iMax - baseline;
        int i13 = iMax - baseline2;
        int i14 = iMax - baseline3;
        int iMax2 = Math.max(Math.max(this.x.getMeasuredHeight() + i12, this.y.getMeasuredHeight() + i13), this.E3.getMeasuredHeight() + i14);
        int i15 = this.I3 & 112;
        if (i15 == 16) {
            i3 = (((height - paddingTop) - paddingBottom) - iMax2) / 2;
        } else {
            if (i15 != 80) {
                i4 = i12 + paddingTop;
                i5 = i13 + paddingTop;
                i6 = paddingTop + i14;
                TextView textView = this.y;
                textView.layout(i10, i5, i11, textView.getMeasuredHeight() + i5);
                int iMin = Math.min(paddingLeft, (i10 - this.H3) - measuredWidth);
                TextView textView2 = this.x;
                textView2.layout(iMin, i4, measuredWidth + iMin, textView2.getMeasuredHeight() + i4);
                int iMax3 = Math.max((width - paddingRight) - measuredWidth3, i11 + this.H3);
                TextView textView3 = this.E3;
                textView3.layout(iMax3, i6, iMax3 + measuredWidth3, textView3.getMeasuredHeight() + i6);
                this.G3 = f2;
                this.K3 = false;
            }
            i3 = (height - paddingBottom) - iMax2;
        }
        i4 = i12 + i3;
        i5 = i13 + i3;
        i6 = i3 + i14;
        TextView textView4 = this.y;
        textView4.layout(i10, i5, i11, textView4.getMeasuredHeight() + i5);
        int iMin2 = Math.min(paddingLeft, (i10 - this.H3) - measuredWidth);
        TextView textView22 = this.x;
        textView22.layout(iMin2, i4, measuredWidth + iMin2, textView22.getMeasuredHeight() + i4);
        int iMax32 = Math.max((width - paddingRight) - measuredWidth3, i11 + this.H3);
        TextView textView32 = this.E3;
        textView32.layout(iMax32, i6, iMax32 + measuredWidth3, textView32.getMeasuredHeight() + i6);
        this.G3 = f2;
        this.K3 = false;
    }

    public int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.H3;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        ViewPager viewPager = (ViewPager) parent;
        b.r.a.a adapter = viewPager.getAdapter();
        viewPager.O(this.L3);
        viewPager.b(this.L3);
        this.s = viewPager;
        WeakReference<b.r.a.a> weakReference = this.M3;
        b(weakReference != null ? weakReference.get() : null, adapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.s;
        if (viewPager != null) {
            b(viewPager.getAdapter(), null);
            this.s.O(null);
            this.s.H(this.L3);
            this.s = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.s != null) {
            float f2 = this.G3;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            d(this.F3, f2, true);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        int iMax;
        if (View.MeasureSpec.getMode(i2) != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i3, paddingTop, -2);
        int size = View.MeasureSpec.getSize(i2);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, (int) (size * 0.2f), -2);
        this.x.measure(childMeasureSpec2, childMeasureSpec);
        this.y.measure(childMeasureSpec2, childMeasureSpec);
        this.E3.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i3) == 1073741824) {
            iMax = View.MeasureSpec.getSize(i3);
        } else {
            iMax = Math.max(getMinHeight(), this.y.getMeasuredHeight() + paddingTop);
        }
        setMeasuredDimension(size, View.resolveSizeAndState(iMax, i3, this.y.getMeasuredState() << 16));
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.J3) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i2) {
        this.I3 = i2;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float f2) {
        int i2 = ((int) (f2 * 255.0f)) & 255;
        this.N3 = i2;
        int i3 = (i2 << 24) | (this.O3 & 16777215);
        this.x.setTextColor(i3);
        this.E3.setTextColor(i3);
    }

    public void setTextColor(int i2) {
        this.O3 = i2;
        this.y.setTextColor(i2);
        int i3 = (this.N3 << 24) | (this.O3 & 16777215);
        this.x.setTextColor(i3);
        this.E3.setTextColor(i3);
    }

    public void setTextSpacing(int i2) {
        this.H3 = i2;
        requestLayout();
    }

    public PagerTitleStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.F3 = -1;
        this.G3 = -1.0f;
        this.L3 = new a();
        TextView textView = new TextView(context);
        this.x = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.y = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.E3 = textView3;
        addView(textView3);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f679c);
        boolean z = false;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            i.l(this.x, resourceId);
            i.l(this.y, resourceId);
            i.l(this.E3, resourceId);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            a(0, dimensionPixelSize);
        }
        if (typedArrayObtainStyledAttributes.hasValue(2)) {
            int color = typedArrayObtainStyledAttributes.getColor(2, 0);
            this.x.setTextColor(color);
            this.y.setTextColor(color);
            this.E3.setTextColor(color);
        }
        this.I3 = typedArrayObtainStyledAttributes.getInteger(3, 80);
        typedArrayObtainStyledAttributes.recycle();
        this.O3 = this.y.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        this.x.setEllipsize(TextUtils.TruncateAt.END);
        this.y.setEllipsize(TextUtils.TruncateAt.END);
        this.E3.setEllipsize(TextUtils.TruncateAt.END);
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, f680d);
            z = typedArrayObtainStyledAttributes2.getBoolean(0, false);
            typedArrayObtainStyledAttributes2.recycle();
        }
        if (z) {
            setSingleLineAllCaps(this.x);
            setSingleLineAllCaps(this.y);
            setSingleLineAllCaps(this.E3);
        } else {
            this.x.setSingleLine();
            this.y.setSingleLine();
            this.E3.setSingleLine();
        }
        this.H3 = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }
}
