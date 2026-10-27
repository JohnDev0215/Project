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
    private static final int[] f1415b = {R.attr.textAppearance, R.attr.textSize, R.attr.textColor, R.attr.gravity};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f1416c = {R.attr.textAllCaps};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ViewPager f1417d;
    TextView e;
    TextView f;
    TextView g;
    private int h;
    float i;
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
        private int f1418a;

        a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void a(int i, float f, int i2) {
            if (f > 0.5f) {
                i++;
            }
            PagerTitleStrip.this.d(i, f, false);
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void b(ViewPager viewPager, androidx.viewpager.widget.a aVar, androidx.viewpager.widget.a aVar2) {
            PagerTitleStrip.this.b(aVar, aVar2);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void c(int i) {
            this.f1418a = i;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void d(int i) {
            if (this.f1418a == 0) {
                PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
                pagerTitleStrip.c(pagerTitleStrip.f1417d.getCurrentItem(), PagerTitleStrip.this.f1417d.getAdapter());
                PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
                float f = pagerTitleStrip2.i;
                if (f < 0.0f) {
                    f = 0.0f;
                }
                pagerTitleStrip2.d(pagerTitleStrip2.f1417d.getCurrentItem(), f, true);
            }
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
            pagerTitleStrip.c(pagerTitleStrip.f1417d.getCurrentItem(), PagerTitleStrip.this.f1417d.getAdapter());
            PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
            float f = pagerTitleStrip2.i;
            if (f < 0.0f) {
                f = 0.0f;
            }
            pagerTitleStrip2.d(pagerTitleStrip2.f1417d.getCurrentItem(), f, true);
        }
    }

    private static class b extends SingleLineTransformationMethod {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Locale f1420b;

        b(Context context) {
            this.f1420b = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            if (transformation != null) {
                return transformation.toString().toUpperCase(this.f1420b);
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

    public void a(int i, float f) {
        this.e.setTextSize(i, f);
        this.f.setTextSize(i, f);
        this.g.setTextSize(i, f);
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
        ViewPager viewPager = this.f1417d;
        if (viewPager != null) {
            this.h = -1;
            this.i = -1.0f;
            c(viewPager.getCurrentItem(), aVar2);
            requestLayout();
        }
    }

    void c(int i, androidx.viewpager.widget.a aVar) {
        int iE = aVar != null ? aVar.e() : 0;
        this.l = true;
        CharSequence charSequenceG = null;
        this.e.setText((i < 1 || aVar == null) ? null : aVar.g(i - 1));
        this.f.setText((aVar == null || i >= iE) ? null : aVar.g(i));
        int i2 = i + 1;
        if (i2 < iE && aVar != null) {
            charSequenceG = aVar.g(i2);
        }
        this.g.setText(charSequenceG);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.e.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.g.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.h = i;
        if (!this.m) {
            d(i, this.i, false);
        }
        this.l = false;
    }

    void d(int i, float f, boolean z) {
        int i2;
        int i3;
        int i4;
        int i5;
        if (i != this.h) {
            c(i, this.f1417d.getAdapter());
        } else if (!z && f == this.i) {
            return;
        }
        this.m = true;
        int measuredWidth = this.e.getMeasuredWidth();
        int measuredWidth2 = this.f.getMeasuredWidth();
        int measuredWidth3 = this.g.getMeasuredWidth();
        int i6 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i7 = paddingRight + i6;
        int i8 = (width - (paddingLeft + i6)) - i7;
        float f2 = 0.5f + f;
        if (f2 > 1.0f) {
            f2 -= 1.0f;
        }
        int i9 = ((width - i7) - ((int) (i8 * f2))) - i6;
        int i10 = measuredWidth2 + i9;
        int baseline = this.e.getBaseline();
        int baseline2 = this.f.getBaseline();
        int baseline3 = this.g.getBaseline();
        int iMax = Math.max(Math.max(baseline, baseline2), baseline3);
        int i11 = iMax - baseline;
        int i12 = iMax - baseline2;
        int i13 = iMax - baseline3;
        int iMax2 = Math.max(Math.max(this.e.getMeasuredHeight() + i11, this.f.getMeasuredHeight() + i12), this.g.getMeasuredHeight() + i13);
        int i14 = this.k & 112;
        if (i14 == 16) {
            i2 = (((height - paddingTop) - paddingBottom) - iMax2) / 2;
        } else {
            if (i14 != 80) {
                i3 = i11 + paddingTop;
                i4 = i12 + paddingTop;
                i5 = paddingTop + i13;
                TextView textView = this.f;
                textView.layout(i9, i4, i10, textView.getMeasuredHeight() + i4);
                int iMin = Math.min(paddingLeft, (i9 - this.j) - measuredWidth);
                TextView textView2 = this.e;
                textView2.layout(iMin, i3, measuredWidth + iMin, textView2.getMeasuredHeight() + i3);
                int iMax3 = Math.max((width - paddingRight) - measuredWidth3, i10 + this.j);
                TextView textView3 = this.g;
                textView3.layout(iMax3, i5, iMax3 + measuredWidth3, textView3.getMeasuredHeight() + i5);
                this.i = f;
                this.m = false;
            }
            i2 = (height - paddingBottom) - iMax2;
        }
        i3 = i11 + i2;
        i4 = i12 + i2;
        i5 = i2 + i13;
        TextView textView4 = this.f;
        textView4.layout(i9, i4, i10, textView4.getMeasuredHeight() + i4);
        int iMin2 = Math.min(paddingLeft, (i9 - this.j) - measuredWidth);
        TextView textView22 = this.e;
        textView22.layout(iMin2, i3, measuredWidth + iMin2, textView22.getMeasuredHeight() + i3);
        int iMax32 = Math.max((width - paddingRight) - measuredWidth3, i10 + this.j);
        TextView textView32 = this.g;
        textView32.layout(iMax32, i5, iMax32 + measuredWidth3, textView32.getMeasuredHeight() + i5);
        this.i = f;
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
        this.f1417d = viewPager;
        WeakReference<androidx.viewpager.widget.a> weakReference = this.o;
        b(weakReference != null ? weakReference.get() : null, adapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.f1417d;
        if (viewPager != null) {
            b(viewPager.getAdapter(), null);
            this.f1417d.Q(null);
            this.f1417d.I(this.n);
            this.f1417d = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f1417d != null) {
            float f = this.i;
            if (f < 0.0f) {
                f = 0.0f;
            }
            d(this.h, f, true);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int iMax;
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, paddingTop, -2);
        int size = View.MeasureSpec.getSize(i);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i, (int) (size * 0.2f), -2);
        this.e.measure(childMeasureSpec2, childMeasureSpec);
        this.f.measure(childMeasureSpec2, childMeasureSpec);
        this.g.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i2) == 1073741824) {
            iMax = View.MeasureSpec.getSize(i2);
        } else {
            iMax = Math.max(getMinHeight(), this.f.getMeasuredHeight() + paddingTop);
        }
        setMeasuredDimension(size, View.resolveSizeAndState(iMax, i2, this.f.getMeasuredState() << 16));
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.l) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i) {
        this.k = i;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float f) {
        int i = ((int) (f * 255.0f)) & 255;
        this.p = i;
        int i2 = (i << 24) | (this.q & 16777215);
        this.e.setTextColor(i2);
        this.g.setTextColor(i2);
    }

    public void setTextColor(int i) {
        this.q = i;
        this.f.setTextColor(i);
        int i2 = (this.p << 24) | (this.q & 16777215);
        this.e.setTextColor(i2);
        this.g.setTextColor(i2);
    }

    public void setTextSpacing(int i) {
        this.j = i;
        requestLayout();
    }

    public PagerTitleStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = -1;
        this.i = -1.0f;
        this.n = new a();
        TextView textView = new TextView(context);
        this.e = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.f = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.g = textView3;
        addView(textView3);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1415b);
        boolean z = false;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            i.q(this.e, resourceId);
            i.q(this.f, resourceId);
            i.q(this.g, resourceId);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            a(0, dimensionPixelSize);
        }
        if (typedArrayObtainStyledAttributes.hasValue(2)) {
            int color = typedArrayObtainStyledAttributes.getColor(2, 0);
            this.e.setTextColor(color);
            this.f.setTextColor(color);
            this.g.setTextColor(color);
        }
        this.k = typedArrayObtainStyledAttributes.getInteger(3, 80);
        typedArrayObtainStyledAttributes.recycle();
        this.q = this.f.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        this.e.setEllipsize(TextUtils.TruncateAt.END);
        this.f.setEllipsize(TextUtils.TruncateAt.END);
        this.g.setEllipsize(TextUtils.TruncateAt.END);
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, f1416c);
            z = typedArrayObtainStyledAttributes2.getBoolean(0, false);
            typedArrayObtainStyledAttributes2.recycle();
        }
        if (z) {
            setSingleLineAllCaps(this.e);
            setSingleLineAllCaps(this.f);
            setSingleLineAllCaps(this.g);
        } else {
            this.e.setSingleLine();
            this.f.setSingleLine();
            this.g.setSingleLine();
        }
        this.j = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }
}
