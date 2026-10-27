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
import java.lang.ref.WeakReference;
import java.util.Locale;
import r0.i;

/* JADX INFO: loaded from: classes.dex */
@ViewPager.e
public class PagerTitleStrip extends ViewGroup {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f1847d = {R.attr.textAppearance, R.attr.textSize, R.attr.textColor, R.attr.gravity};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int[] f1848e = {R.attr.textAllCaps};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ViewPager f1849f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f1850g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public TextView f1851h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public TextView f1852i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1853j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f1854k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1855l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1856m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f1857n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1858o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final a f1859p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public WeakReference<n1.a> f1860q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1861r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1862s;

    public class a extends DataSetObserver implements ViewPager.j, ViewPager.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1863a;

        public a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void a(ViewPager viewPager, n1.a aVar, n1.a aVar2) {
            PagerTitleStrip.this.b(aVar, aVar2);
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
            pagerTitleStrip.c(pagerTitleStrip.f1849f.getCurrentItem(), PagerTitleStrip.this.f1849f.getAdapter());
            PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
            float f8 = pagerTitleStrip2.f1854k;
            if (f8 < 0.0f) {
                f8 = 0.0f;
            }
            pagerTitleStrip2.d(pagerTitleStrip2.f1849f.getCurrentItem(), f8, true);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageScrollStateChanged(int i8) {
            this.f1863a = i8;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageScrolled(int i8, float f8, int i9) {
            if (f8 > 0.5f) {
                i8++;
            }
            PagerTitleStrip.this.d(i8, f8, false);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void onPageSelected(int i8) {
            if (this.f1863a == 0) {
                PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
                pagerTitleStrip.c(pagerTitleStrip.f1849f.getCurrentItem(), PagerTitleStrip.this.f1849f.getAdapter());
                PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
                float f8 = pagerTitleStrip2.f1854k;
                if (f8 < 0.0f) {
                    f8 = 0.0f;
                }
                pagerTitleStrip2.d(pagerTitleStrip2.f1849f.getCurrentItem(), f8, true);
            }
        }
    }

    public static class b extends SingleLineTransformationMethod {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public Locale f1865d;

        public b(Context context) {
            this.f1865d = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            if (transformation != null) {
                return transformation.toString().toUpperCase(this.f1865d);
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

    public void a(int i8, float f8) {
        this.f1850g.setTextSize(i8, f8);
        this.f1851h.setTextSize(i8, f8);
        this.f1852i.setTextSize(i8, f8);
    }

    public void b(n1.a aVar, n1.a aVar2) {
        if (aVar != null) {
            aVar.u(this.f1859p);
            this.f1860q = null;
        }
        if (aVar2 != null) {
            aVar2.m(this.f1859p);
            this.f1860q = new WeakReference<>(aVar2);
        }
        ViewPager viewPager = this.f1849f;
        if (viewPager != null) {
            this.f1853j = -1;
            this.f1854k = -1.0f;
            c(viewPager.getCurrentItem(), aVar2);
            requestLayout();
        }
    }

    public void c(int i8, n1.a aVar) {
        int iE = aVar != null ? aVar.e() : 0;
        this.f1857n = true;
        CharSequence charSequenceG = null;
        this.f1850g.setText((i8 < 1 || aVar == null) ? null : aVar.g(i8 - 1));
        this.f1851h.setText((aVar == null || i8 >= iE) ? null : aVar.g(i8));
        int i9 = i8 + 1;
        if (i9 < iE && aVar != null) {
            charSequenceG = aVar.g(i9);
        }
        this.f1852i.setText(charSequenceG);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.f1850g.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f1851h.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f1852i.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f1853j = i8;
        if (!this.f1858o) {
            d(i8, this.f1854k, false);
        }
        this.f1857n = false;
    }

    public void d(int i8, float f8, boolean z7) {
        int i9;
        int i10;
        int i11;
        int i12;
        if (i8 != this.f1853j) {
            c(i8, this.f1849f.getAdapter());
        } else if (!z7 && f8 == this.f1854k) {
            return;
        }
        this.f1858o = true;
        int measuredWidth = this.f1850g.getMeasuredWidth();
        int measuredWidth2 = this.f1851h.getMeasuredWidth();
        int measuredWidth3 = this.f1852i.getMeasuredWidth();
        int i13 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i14 = paddingRight + i13;
        int i15 = (width - (paddingLeft + i13)) - i14;
        float f9 = 0.5f + f8;
        if (f9 > 1.0f) {
            f9 -= 1.0f;
        }
        int i16 = ((width - i14) - ((int) (i15 * f9))) - i13;
        int i17 = measuredWidth2 + i16;
        int baseline = this.f1850g.getBaseline();
        int baseline2 = this.f1851h.getBaseline();
        int baseline3 = this.f1852i.getBaseline();
        int iMax = Math.max(Math.max(baseline, baseline2), baseline3);
        int i18 = iMax - baseline;
        int i19 = iMax - baseline2;
        int i20 = iMax - baseline3;
        int iMax2 = Math.max(Math.max(this.f1850g.getMeasuredHeight() + i18, this.f1851h.getMeasuredHeight() + i19), this.f1852i.getMeasuredHeight() + i20);
        int i21 = this.f1856m & 112;
        if (i21 == 16) {
            i9 = (((height - paddingTop) - paddingBottom) - iMax2) / 2;
        } else {
            if (i21 != 80) {
                i10 = i18 + paddingTop;
                i11 = i19 + paddingTop;
                i12 = paddingTop + i20;
                TextView textView = this.f1851h;
                textView.layout(i16, i11, i17, textView.getMeasuredHeight() + i11);
                int iMin = Math.min(paddingLeft, (i16 - this.f1855l) - measuredWidth);
                TextView textView2 = this.f1850g;
                textView2.layout(iMin, i10, measuredWidth + iMin, textView2.getMeasuredHeight() + i10);
                int iMax3 = Math.max((width - paddingRight) - measuredWidth3, i17 + this.f1855l);
                TextView textView3 = this.f1852i;
                textView3.layout(iMax3, i12, iMax3 + measuredWidth3, textView3.getMeasuredHeight() + i12);
                this.f1854k = f8;
                this.f1858o = false;
            }
            i9 = (height - paddingBottom) - iMax2;
        }
        i10 = i18 + i9;
        i11 = i19 + i9;
        i12 = i9 + i20;
        TextView textView4 = this.f1851h;
        textView4.layout(i16, i11, i17, textView4.getMeasuredHeight() + i11);
        int iMin2 = Math.min(paddingLeft, (i16 - this.f1855l) - measuredWidth);
        TextView textView22 = this.f1850g;
        textView22.layout(iMin2, i10, measuredWidth + iMin2, textView22.getMeasuredHeight() + i10);
        int iMax32 = Math.max((width - paddingRight) - measuredWidth3, i17 + this.f1855l);
        TextView textView32 = this.f1852i;
        textView32.layout(iMax32, i12, iMax32 + measuredWidth3, textView32.getMeasuredHeight() + i12);
        this.f1854k = f8;
        this.f1858o = false;
    }

    public int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.f1855l;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        ViewPager viewPager = (ViewPager) parent;
        n1.a adapter = viewPager.getAdapter();
        viewPager.Q(this.f1859p);
        viewPager.b(this.f1859p);
        this.f1849f = viewPager;
        WeakReference<n1.a> weakReference = this.f1860q;
        b(weakReference != null ? weakReference.get() : null, adapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.f1849f;
        if (viewPager != null) {
            b(viewPager.getAdapter(), null);
            this.f1849f.Q(null);
            this.f1849f.I(this.f1859p);
            this.f1849f = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        if (this.f1849f != null) {
            float f8 = this.f1854k;
            if (f8 < 0.0f) {
                f8 = 0.0f;
            }
            d(this.f1853j, f8, true);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        int iMax;
        if (View.MeasureSpec.getMode(i8) != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i9, paddingTop, -2);
        int size = View.MeasureSpec.getSize(i8);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i8, (int) (size * 0.2f), -2);
        this.f1850g.measure(childMeasureSpec2, childMeasureSpec);
        this.f1851h.measure(childMeasureSpec2, childMeasureSpec);
        this.f1852i.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i9) == 1073741824) {
            iMax = View.MeasureSpec.getSize(i9);
        } else {
            iMax = Math.max(getMinHeight(), this.f1851h.getMeasuredHeight() + paddingTop);
        }
        setMeasuredDimension(size, View.resolveSizeAndState(iMax, i9, this.f1851h.getMeasuredState() << 16));
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.f1857n) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i8) {
        this.f1856m = i8;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float f8) {
        int i8 = ((int) (f8 * 255.0f)) & 255;
        this.f1861r = i8;
        int i9 = (i8 << 24) | (this.f1862s & 16777215);
        this.f1850g.setTextColor(i9);
        this.f1852i.setTextColor(i9);
    }

    public void setTextColor(int i8) {
        this.f1862s = i8;
        this.f1851h.setTextColor(i8);
        int i9 = (this.f1861r << 24) | (this.f1862s & 16777215);
        this.f1850g.setTextColor(i9);
        this.f1852i.setTextColor(i9);
    }

    public void setTextSpacing(int i8) {
        this.f1855l = i8;
        requestLayout();
    }

    public PagerTitleStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1853j = -1;
        this.f1854k = -1.0f;
        this.f1859p = new a();
        TextView textView = new TextView(context);
        this.f1850g = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.f1851h = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.f1852i = textView3;
        addView(textView3);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1847d);
        boolean z7 = false;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            i.q(this.f1850g, resourceId);
            i.q(this.f1851h, resourceId);
            i.q(this.f1852i, resourceId);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            a(0, dimensionPixelSize);
        }
        if (typedArrayObtainStyledAttributes.hasValue(2)) {
            int color = typedArrayObtainStyledAttributes.getColor(2, 0);
            this.f1850g.setTextColor(color);
            this.f1851h.setTextColor(color);
            this.f1852i.setTextColor(color);
        }
        this.f1856m = typedArrayObtainStyledAttributes.getInteger(3, 80);
        typedArrayObtainStyledAttributes.recycle();
        this.f1862s = this.f1851h.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        this.f1850g.setEllipsize(TextUtils.TruncateAt.END);
        this.f1851h.setEllipsize(TextUtils.TruncateAt.END);
        this.f1852i.setEllipsize(TextUtils.TruncateAt.END);
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, f1848e);
            z7 = typedArrayObtainStyledAttributes2.getBoolean(0, false);
            typedArrayObtainStyledAttributes2.recycle();
        }
        if (z7) {
            setSingleLineAllCaps(this.f1850g);
            setSingleLineAllCaps(this.f1851h);
            setSingleLineAllCaps(this.f1852i);
        } else {
            this.f1850g.setSingleLine();
            this.f1851h.setSingleLine();
            this.f1852i.setSingleLine();
        }
        this.f1855l = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }
}
