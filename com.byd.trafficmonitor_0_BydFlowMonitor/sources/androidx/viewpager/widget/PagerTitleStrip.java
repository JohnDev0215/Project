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
import b.h.n.i;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
@ViewPager.e
public class PagerTitleStrip extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f1352b = {R.attr.textAppearance, R.attr.textSize, R.attr.textColor, R.attr.gravity};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f1353c = {R.attr.textAllCaps};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ViewPager f1354d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f1355e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f1356f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f1357g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1358h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f1359i;
    public int j;
    public int k;
    public boolean l;
    public boolean m;
    public final a n;
    public WeakReference<b.a0.a.a> o;
    public int p;
    public int q;

    public class a extends DataSetObserver implements ViewPager.j, ViewPager.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1360a;

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
        public void b(ViewPager viewPager, b.a0.a.a aVar, b.a0.a.a aVar2) {
            PagerTitleStrip.this.b(aVar, aVar2);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void c(int i2) {
            this.f1360a = i2;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void d(int i2) {
            if (this.f1360a == 0) {
                PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
                pagerTitleStrip.c(pagerTitleStrip.f1354d.getCurrentItem(), PagerTitleStrip.this.f1354d.getAdapter());
                PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
                float f2 = pagerTitleStrip2.f1359i;
                if (f2 < 0.0f) {
                    f2 = 0.0f;
                }
                pagerTitleStrip2.d(pagerTitleStrip2.f1354d.getCurrentItem(), f2, true);
            }
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
            pagerTitleStrip.c(pagerTitleStrip.f1354d.getCurrentItem(), PagerTitleStrip.this.f1354d.getAdapter());
            PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
            float f2 = pagerTitleStrip2.f1359i;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            pagerTitleStrip2.d(pagerTitleStrip2.f1354d.getCurrentItem(), f2, true);
        }
    }

    public static class b extends SingleLineTransformationMethod {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Locale f1362b;

        public b(Context context) {
            this.f1362b = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            if (transformation != null) {
                return transformation.toString().toUpperCase(this.f1362b);
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
        this.f1355e.setTextSize(i2, f2);
        this.f1356f.setTextSize(i2, f2);
        this.f1357g.setTextSize(i2, f2);
    }

    public void b(b.a0.a.a aVar, b.a0.a.a aVar2) {
        if (aVar != null) {
            aVar.t(this.n);
            this.o = null;
        }
        if (aVar2 != null) {
            aVar2.l(this.n);
            this.o = new WeakReference<>(aVar2);
        }
        ViewPager viewPager = this.f1354d;
        if (viewPager != null) {
            this.f1358h = -1;
            this.f1359i = -1.0f;
            c(viewPager.getCurrentItem(), aVar2);
            requestLayout();
        }
    }

    public void c(int i2, b.a0.a.a aVar) {
        int iE = aVar != null ? aVar.e() : 0;
        this.l = true;
        CharSequence charSequenceG = null;
        this.f1355e.setText((i2 < 1 || aVar == null) ? null : aVar.g(i2 - 1));
        this.f1356f.setText((aVar == null || i2 >= iE) ? null : aVar.g(i2));
        int i3 = i2 + 1;
        if (i3 < iE && aVar != null) {
            charSequenceG = aVar.g(i3);
        }
        this.f1357g.setText(charSequenceG);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.f1355e.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f1356f.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f1357g.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f1358h = i2;
        if (!this.m) {
            d(i2, this.f1359i, false);
        }
        this.l = false;
    }

    public void d(int i2, float f2, boolean z) {
        int i3;
        int i4;
        int i5;
        int i6;
        if (i2 != this.f1358h) {
            c(i2, this.f1354d.getAdapter());
        } else if (!z && f2 == this.f1359i) {
            return;
        }
        this.m = true;
        int measuredWidth = this.f1355e.getMeasuredWidth();
        int measuredWidth2 = this.f1356f.getMeasuredWidth();
        int measuredWidth3 = this.f1357g.getMeasuredWidth();
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
        int baseline = this.f1355e.getBaseline();
        int baseline2 = this.f1356f.getBaseline();
        int baseline3 = this.f1357g.getBaseline();
        int iMax = Math.max(Math.max(baseline, baseline2), baseline3);
        int i12 = iMax - baseline;
        int i13 = iMax - baseline2;
        int i14 = iMax - baseline3;
        int iMax2 = Math.max(Math.max(this.f1355e.getMeasuredHeight() + i12, this.f1356f.getMeasuredHeight() + i13), this.f1357g.getMeasuredHeight() + i14);
        int i15 = this.k & 112;
        if (i15 == 16) {
            i3 = (((height - paddingTop) - paddingBottom) - iMax2) / 2;
        } else {
            if (i15 != 80) {
                i4 = i12 + paddingTop;
                i5 = i13 + paddingTop;
                i6 = paddingTop + i14;
                TextView textView = this.f1356f;
                textView.layout(i10, i5, i11, textView.getMeasuredHeight() + i5);
                int iMin = Math.min(paddingLeft, (i10 - this.j) - measuredWidth);
                TextView textView2 = this.f1355e;
                textView2.layout(iMin, i4, measuredWidth + iMin, textView2.getMeasuredHeight() + i4);
                int iMax3 = Math.max((width - paddingRight) - measuredWidth3, i11 + this.j);
                TextView textView3 = this.f1357g;
                textView3.layout(iMax3, i6, iMax3 + measuredWidth3, textView3.getMeasuredHeight() + i6);
                this.f1359i = f2;
                this.m = false;
            }
            i3 = (height - paddingBottom) - iMax2;
        }
        i4 = i12 + i3;
        i5 = i13 + i3;
        i6 = i3 + i14;
        TextView textView4 = this.f1356f;
        textView4.layout(i10, i5, i11, textView4.getMeasuredHeight() + i5);
        int iMin2 = Math.min(paddingLeft, (i10 - this.j) - measuredWidth);
        TextView textView22 = this.f1355e;
        textView22.layout(iMin2, i4, measuredWidth + iMin2, textView22.getMeasuredHeight() + i4);
        int iMax32 = Math.max((width - paddingRight) - measuredWidth3, i11 + this.j);
        TextView textView32 = this.f1357g;
        textView32.layout(iMax32, i6, iMax32 + measuredWidth3, textView32.getMeasuredHeight() + i6);
        this.f1359i = f2;
        this.m = false;
    }

    public int getMinHeight() {
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
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        ViewPager viewPager = (ViewPager) parent;
        b.a0.a.a adapter = viewPager.getAdapter();
        viewPager.Q(this.n);
        viewPager.b(this.n);
        this.f1354d = viewPager;
        WeakReference<b.a0.a.a> weakReference = this.o;
        b(weakReference != null ? weakReference.get() : null, adapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.f1354d;
        if (viewPager != null) {
            b(viewPager.getAdapter(), null);
            this.f1354d.Q(null);
            this.f1354d.I(this.n);
            this.f1354d = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.f1354d != null) {
            float f2 = this.f1359i;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            d(this.f1358h, f2, true);
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
        this.f1355e.measure(childMeasureSpec2, childMeasureSpec);
        this.f1356f.measure(childMeasureSpec2, childMeasureSpec);
        this.f1357g.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i3) == 1073741824) {
            iMax = View.MeasureSpec.getSize(i3);
        } else {
            iMax = Math.max(getMinHeight(), this.f1356f.getMeasuredHeight() + paddingTop);
        }
        setMeasuredDimension(size, View.resolveSizeAndState(iMax, i3, this.f1356f.getMeasuredState() << 16));
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
        this.f1355e.setTextColor(i3);
        this.f1357g.setTextColor(i3);
    }

    public void setTextColor(int i2) {
        this.q = i2;
        this.f1356f.setTextColor(i2);
        int i3 = (this.p << 24) | (this.q & 16777215);
        this.f1355e.setTextColor(i3);
        this.f1357g.setTextColor(i3);
    }

    public void setTextSpacing(int i2) {
        this.j = i2;
        requestLayout();
    }

    public PagerTitleStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1358h = -1;
        this.f1359i = -1.0f;
        this.n = new a();
        TextView textView = new TextView(context);
        this.f1355e = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.f1356f = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.f1357g = textView3;
        addView(textView3);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1352b);
        boolean z = false;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            i.q(this.f1355e, resourceId);
            i.q(this.f1356f, resourceId);
            i.q(this.f1357g, resourceId);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            a(0, dimensionPixelSize);
        }
        if (typedArrayObtainStyledAttributes.hasValue(2)) {
            int color = typedArrayObtainStyledAttributes.getColor(2, 0);
            this.f1355e.setTextColor(color);
            this.f1356f.setTextColor(color);
            this.f1357g.setTextColor(color);
        }
        this.k = typedArrayObtainStyledAttributes.getInteger(3, 80);
        typedArrayObtainStyledAttributes.recycle();
        this.q = this.f1356f.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        this.f1355e.setEllipsize(TextUtils.TruncateAt.END);
        this.f1356f.setEllipsize(TextUtils.TruncateAt.END);
        this.f1357g.setEllipsize(TextUtils.TruncateAt.END);
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, f1353c);
            z = typedArrayObtainStyledAttributes2.getBoolean(0, false);
            typedArrayObtainStyledAttributes2.recycle();
        }
        if (z) {
            setSingleLineAllCaps(this.f1355e);
            setSingleLineAllCaps(this.f1356f);
            setSingleLineAllCaps(this.f1357g);
        } else {
            this.f1355e.setSingleLine();
            this.f1356f.setSingleLine();
            this.f1357g.setSingleLine();
        }
        this.j = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }
}
