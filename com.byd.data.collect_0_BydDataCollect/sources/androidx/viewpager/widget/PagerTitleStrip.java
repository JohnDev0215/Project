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
import androidx.core.widget.i;
import androidx.viewpager.widget.ViewPager;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
@ViewPager.e
public class PagerTitleStrip extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f2053b = {R.attr.textAppearance, R.attr.textSize, R.attr.textColor, R.attr.gravity};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f2054c = {R.attr.textAllCaps};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ViewPager f2055d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    TextView f2056e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    TextView f2057f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    TextView f2058g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f2059h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    float f2060i;
    private int j;
    private int k;
    private boolean l;
    private boolean m;
    private final a n;
    private WeakReference<androidx.viewpager.widget.a> o;
    private int p;
    int q;

    private class a extends DataSetObserver implements ViewPager.j, ViewPager.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f2061a;

        a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void a(int i2, float f2, int i3) {
            if (f2 > 0.5f) {
                i2++;
            }
            PagerTitleStrip.this.d(i2, f2, false);
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void b(ViewPager viewPager, androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2) {
            PagerTitleStrip.this.b(aVar, aVar2);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void c(int i2) {
            this.f2061a = i2;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void d(int i2) {
            if (this.f2061a == 0) {
                PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
                pagerTitleStrip.c(pagerTitleStrip.f2055d.getCurrentItem(), PagerTitleStrip.this.f2055d.getAdapter());
                PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
                float f2 = pagerTitleStrip2.f2060i;
                if (f2 < 0.0f) {
                    f2 = 0.0f;
                }
                pagerTitleStrip2.d(pagerTitleStrip2.f2055d.getCurrentItem(), f2, true);
            }
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
            pagerTitleStrip.c(pagerTitleStrip.f2055d.getCurrentItem(), PagerTitleStrip.this.f2055d.getAdapter());
            PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
            float f2 = pagerTitleStrip2.f2060i;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            pagerTitleStrip2.d(pagerTitleStrip2.f2055d.getCurrentItem(), f2, true);
        }
    }

    private static class b extends SingleLineTransformationMethod {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Locale f2063b;

        b(Context context) {
            this.f2063b = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            if (transformation != null) {
                return transformation.toString().toUpperCase(this.f2063b);
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
        this.f2056e.setTextSize(i2, f2);
        this.f2057f.setTextSize(i2, f2);
        this.f2058g.setTextSize(i2, f2);
    }

    void b(androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2) {
        if (aVar != null) {
            aVar.t(this.n);
            this.o = null;
        }
        if (aVar2 != null) {
            aVar2.l(this.n);
            this.o = new WeakReference<>(aVar2);
        }
        ViewPager viewPager = this.f2055d;
        if (viewPager != null) {
            this.f2059h = -1;
            this.f2060i = -1.0f;
            c(viewPager.getCurrentItem(), aVar2);
            requestLayout();
        }
    }

    void c(int i2, androidx.viewpager.widget.a aVar) {
        int iE = aVar != null ? aVar.e() : 0;
        this.l = true;
        CharSequence charSequenceG = null;
        this.f2056e.setText((i2 < 1 || aVar == null) ? null : aVar.g(i2 - 1));
        this.f2057f.setText((aVar == null || i2 >= iE) ? null : aVar.g(i2));
        int i3 = i2 + 1;
        if (i3 < iE && aVar != null) {
            charSequenceG = aVar.g(i3);
        }
        this.f2058g.setText(charSequenceG);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.f2056e.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f2057f.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f2058g.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f2059h = i2;
        if (!this.m) {
            d(i2, this.f2060i, false);
        }
        this.l = false;
    }

    void d(int i2, float f2, boolean z) {
        int i3;
        int i4;
        int i5;
        int i6;
        if (i2 != this.f2059h) {
            c(i2, this.f2055d.getAdapter());
        } else if (!z && f2 == this.f2060i) {
            return;
        }
        this.m = true;
        int measuredWidth = this.f2056e.getMeasuredWidth();
        int measuredWidth2 = this.f2057f.getMeasuredWidth();
        int measuredWidth3 = this.f2058g.getMeasuredWidth();
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
        int baseline = this.f2056e.getBaseline();
        int baseline2 = this.f2057f.getBaseline();
        int baseline3 = this.f2058g.getBaseline();
        int iMax = Math.max(Math.max(baseline, baseline2), baseline3);
        int i12 = iMax - baseline;
        int i13 = iMax - baseline2;
        int i14 = iMax - baseline3;
        int iMax2 = Math.max(Math.max(this.f2056e.getMeasuredHeight() + i12, this.f2057f.getMeasuredHeight() + i13), this.f2058g.getMeasuredHeight() + i14);
        int i15 = this.k & 112;
        if (i15 == 16) {
            i3 = (((height - paddingTop) - paddingBottom) - iMax2) / 2;
        } else {
            if (i15 != 80) {
                i4 = i12 + paddingTop;
                i5 = i13 + paddingTop;
                i6 = paddingTop + i14;
                TextView textView = this.f2057f;
                textView.layout(i10, i5, i11, textView.getMeasuredHeight() + i5);
                int iMin = Math.min(paddingLeft, (i10 - this.j) - measuredWidth);
                TextView textView2 = this.f2056e;
                textView2.layout(iMin, i4, measuredWidth + iMin, textView2.getMeasuredHeight() + i4);
                int iMax3 = Math.max((width - paddingRight) - measuredWidth3, i11 + this.j);
                TextView textView3 = this.f2058g;
                textView3.layout(iMax3, i6, iMax3 + measuredWidth3, textView3.getMeasuredHeight() + i6);
                this.f2060i = f2;
                this.m = false;
            }
            i3 = (height - paddingBottom) - iMax2;
        }
        i4 = i12 + i3;
        i5 = i13 + i3;
        i6 = i3 + i14;
        TextView textView4 = this.f2057f;
        textView4.layout(i10, i5, i11, textView4.getMeasuredHeight() + i5);
        int iMin2 = Math.min(paddingLeft, (i10 - this.j) - measuredWidth);
        TextView textView22 = this.f2056e;
        textView22.layout(iMin2, i4, measuredWidth + iMin2, textView22.getMeasuredHeight() + i4);
        int iMax32 = Math.max((width - paddingRight) - measuredWidth3, i11 + this.j);
        TextView textView32 = this.f2058g;
        textView32.layout(iMax32, i6, iMax32 + measuredWidth3, textView32.getMeasuredHeight() + i6);
        this.f2060i = f2;
        this.m = false;
    }

    int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.j;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        ViewPager viewPager = (ViewPager) parent;
        androidx.viewpager.widget.a adapter = viewPager.getAdapter();
        viewPager.Q(this.n);
        viewPager.b(this.n);
        this.f2055d = viewPager;
        WeakReference<androidx.viewpager.widget.a> weakReference = this.o;
        b(weakReference != null ? weakReference.get() : null, adapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.f2055d;
        if (viewPager != null) {
            b(viewPager.getAdapter(), null);
            this.f2055d.Q(null);
            this.f2055d.I(this.n);
            this.f2055d = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.f2055d != null) {
            float f2 = this.f2060i;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            d(this.f2059h, f2, true);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int iMax;
        if (View.MeasureSpec.getMode(i2) != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i3, paddingTop, -2);
        int size = View.MeasureSpec.getSize(i2);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i2, (int) (size * 0.2f), -2);
        this.f2056e.measure(childMeasureSpec2, childMeasureSpec);
        this.f2057f.measure(childMeasureSpec2, childMeasureSpec);
        this.f2058g.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i3) == 1073741824) {
            iMax = View.MeasureSpec.getSize(i3);
        } else {
            iMax = Math.max(getMinHeight(), this.f2057f.getMeasuredHeight() + paddingTop);
        }
        setMeasuredDimension(size, View.resolveSizeAndState(iMax, i3, this.f2057f.getMeasuredState() << 16));
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.l) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i2) {
        this.k = i2;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float f2) {
        int i2 = ((int) (f2 * 255.0f)) & 255;
        this.p = i2;
        int i3 = (i2 << 24) | (this.q & 16777215);
        this.f2056e.setTextColor(i3);
        this.f2058g.setTextColor(i3);
    }

    public void setTextColor(int i2) {
        this.q = i2;
        this.f2057f.setTextColor(i2);
        int i3 = (this.p << 24) | (this.q & 16777215);
        this.f2056e.setTextColor(i3);
        this.f2058g.setTextColor(i3);
    }

    public void setTextSpacing(int i2) {
        this.j = i2;
        requestLayout();
    }

    public PagerTitleStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2059h = -1;
        this.f2060i = -1.0f;
        this.n = new a();
        TextView textView = new TextView(context);
        this.f2056e = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.f2057f = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.f2058g = textView3;
        addView(textView3);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f2053b);
        boolean z = false;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            i.q(this.f2056e, resourceId);
            i.q(this.f2057f, resourceId);
            i.q(this.f2058g, resourceId);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            a(0, dimensionPixelSize);
        }
        if (typedArrayObtainStyledAttributes.hasValue(2)) {
            int color = typedArrayObtainStyledAttributes.getColor(2, 0);
            this.f2056e.setTextColor(color);
            this.f2057f.setTextColor(color);
            this.f2058g.setTextColor(color);
        }
        this.k = typedArrayObtainStyledAttributes.getInteger(3, 80);
        typedArrayObtainStyledAttributes.recycle();
        this.q = this.f2057f.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        this.f2056e.setEllipsize(TextUtils.TruncateAt.END);
        this.f2057f.setEllipsize(TextUtils.TruncateAt.END);
        this.f2058g.setEllipsize(TextUtils.TruncateAt.END);
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, f2054c);
            z = typedArrayObtainStyledAttributes2.getBoolean(0, false);
            typedArrayObtainStyledAttributes2.recycle();
        }
        if (z) {
            setSingleLineAllCaps(this.f2056e);
            setSingleLineAllCaps(this.f2057f);
            setSingleLineAllCaps(this.f2058g);
        } else {
            this.f2056e.setSingleLine();
            this.f2057f.setSingleLine();
            this.f2058g.setSingleLine();
        }
        this.j = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }
}
