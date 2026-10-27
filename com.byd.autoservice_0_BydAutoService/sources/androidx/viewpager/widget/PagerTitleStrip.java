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
import n0.i;

/* JADX INFO: loaded from: classes.dex */
@ViewPager.e
public class PagerTitleStrip extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f1642b = {R.attr.textAppearance, R.attr.textSize, R.attr.textColor, R.attr.gravity};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f1643c = {R.attr.textAllCaps};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ViewPager f1644d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f1645e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f1646f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f1647g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1648h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f1649i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1650j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1651k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f1652l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f1653m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final a f1654n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public WeakReference<b1.a> f1655o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1656p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1657q;

    public class a extends DataSetObserver implements ViewPager.j, ViewPager.i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1658a;

        public a() {
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void a(int i3, float f3, int i4) {
            if (f3 > 0.5f) {
                i3++;
            }
            PagerTitleStrip.this.d(i3, f3, false);
        }

        @Override // androidx.viewpager.widget.ViewPager.i
        public void b(ViewPager viewPager, b1.a aVar, b1.a aVar2) {
            PagerTitleStrip.this.b(aVar, aVar2);
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void c(int i3) {
            this.f1658a = i3;
        }

        @Override // androidx.viewpager.widget.ViewPager.j
        public void d(int i3) {
            if (this.f1658a == 0) {
                PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
                pagerTitleStrip.c(pagerTitleStrip.f1644d.getCurrentItem(), PagerTitleStrip.this.f1644d.getAdapter());
                PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
                float f3 = pagerTitleStrip2.f1649i;
                if (f3 < 0.0f) {
                    f3 = 0.0f;
                }
                pagerTitleStrip2.d(pagerTitleStrip2.f1644d.getCurrentItem(), f3, true);
            }
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            PagerTitleStrip pagerTitleStrip = PagerTitleStrip.this;
            pagerTitleStrip.c(pagerTitleStrip.f1644d.getCurrentItem(), PagerTitleStrip.this.f1644d.getAdapter());
            PagerTitleStrip pagerTitleStrip2 = PagerTitleStrip.this;
            float f3 = pagerTitleStrip2.f1649i;
            if (f3 < 0.0f) {
                f3 = 0.0f;
            }
            pagerTitleStrip2.d(pagerTitleStrip2.f1644d.getCurrentItem(), f3, true);
        }
    }

    public static class b extends SingleLineTransformationMethod {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public Locale f1660b;

        public b(Context context) {
            this.f1660b = context.getResources().getConfiguration().locale;
        }

        @Override // android.text.method.ReplacementTransformationMethod, android.text.method.TransformationMethod
        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            if (transformation != null) {
                return transformation.toString().toUpperCase(this.f1660b);
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

    public void a(int i3, float f3) {
        this.f1645e.setTextSize(i3, f3);
        this.f1646f.setTextSize(i3, f3);
        this.f1647g.setTextSize(i3, f3);
    }

    public void b(b1.a aVar, b1.a aVar2) {
        if (aVar != null) {
            aVar.t(this.f1654n);
            this.f1655o = null;
        }
        if (aVar2 != null) {
            aVar2.l(this.f1654n);
            this.f1655o = new WeakReference<>(aVar2);
        }
        ViewPager viewPager = this.f1644d;
        if (viewPager != null) {
            this.f1648h = -1;
            this.f1649i = -1.0f;
            c(viewPager.getCurrentItem(), aVar2);
            requestLayout();
        }
    }

    public void c(int i3, b1.a aVar) {
        int iE = aVar != null ? aVar.e() : 0;
        this.f1652l = true;
        CharSequence charSequenceG = null;
        this.f1645e.setText((i3 < 1 || aVar == null) ? null : aVar.g(i3 - 1));
        this.f1646f.setText((aVar == null || i3 >= iE) ? null : aVar.g(i3));
        int i4 = i3 + 1;
        if (i4 < iE && aVar != null) {
            charSequenceG = aVar.g(i4);
        }
        this.f1647g.setText(charSequenceG);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, (int) (((getWidth() - getPaddingLeft()) - getPaddingRight()) * 0.8f)), Integer.MIN_VALUE);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, (getHeight() - getPaddingTop()) - getPaddingBottom()), Integer.MIN_VALUE);
        this.f1645e.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f1646f.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f1647g.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.f1648h = i3;
        if (!this.f1653m) {
            d(i3, this.f1649i, false);
        }
        this.f1652l = false;
    }

    public void d(int i3, float f3, boolean z3) {
        int i4;
        int i5;
        int i6;
        int i7;
        if (i3 != this.f1648h) {
            c(i3, this.f1644d.getAdapter());
        } else if (!z3 && f3 == this.f1649i) {
            return;
        }
        this.f1653m = true;
        int measuredWidth = this.f1645e.getMeasuredWidth();
        int measuredWidth2 = this.f1646f.getMeasuredWidth();
        int measuredWidth3 = this.f1647g.getMeasuredWidth();
        int i8 = measuredWidth2 / 2;
        int width = getWidth();
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int i9 = paddingRight + i8;
        int i10 = (width - (paddingLeft + i8)) - i9;
        float f4 = 0.5f + f3;
        if (f4 > 1.0f) {
            f4 -= 1.0f;
        }
        int i11 = ((width - i9) - ((int) (i10 * f4))) - i8;
        int i12 = measuredWidth2 + i11;
        int baseline = this.f1645e.getBaseline();
        int baseline2 = this.f1646f.getBaseline();
        int baseline3 = this.f1647g.getBaseline();
        int iMax = Math.max(Math.max(baseline, baseline2), baseline3);
        int i13 = iMax - baseline;
        int i14 = iMax - baseline2;
        int i15 = iMax - baseline3;
        int iMax2 = Math.max(Math.max(this.f1645e.getMeasuredHeight() + i13, this.f1646f.getMeasuredHeight() + i14), this.f1647g.getMeasuredHeight() + i15);
        int i16 = this.f1651k & 112;
        if (i16 == 16) {
            i4 = (((height - paddingTop) - paddingBottom) - iMax2) / 2;
        } else {
            if (i16 != 80) {
                i5 = i13 + paddingTop;
                i6 = i14 + paddingTop;
                i7 = paddingTop + i15;
                TextView textView = this.f1646f;
                textView.layout(i11, i6, i12, textView.getMeasuredHeight() + i6);
                int iMin = Math.min(paddingLeft, (i11 - this.f1650j) - measuredWidth);
                TextView textView2 = this.f1645e;
                textView2.layout(iMin, i5, measuredWidth + iMin, textView2.getMeasuredHeight() + i5);
                int iMax3 = Math.max((width - paddingRight) - measuredWidth3, i12 + this.f1650j);
                TextView textView3 = this.f1647g;
                textView3.layout(iMax3, i7, iMax3 + measuredWidth3, textView3.getMeasuredHeight() + i7);
                this.f1649i = f3;
                this.f1653m = false;
            }
            i4 = (height - paddingBottom) - iMax2;
        }
        i5 = i13 + i4;
        i6 = i14 + i4;
        i7 = i4 + i15;
        TextView textView4 = this.f1646f;
        textView4.layout(i11, i6, i12, textView4.getMeasuredHeight() + i6);
        int iMin2 = Math.min(paddingLeft, (i11 - this.f1650j) - measuredWidth);
        TextView textView22 = this.f1645e;
        textView22.layout(iMin2, i5, measuredWidth + iMin2, textView22.getMeasuredHeight() + i5);
        int iMax32 = Math.max((width - paddingRight) - measuredWidth3, i12 + this.f1650j);
        TextView textView32 = this.f1647g;
        textView32.layout(iMax32, i7, iMax32 + measuredWidth3, textView32.getMeasuredHeight() + i7);
        this.f1649i = f3;
        this.f1653m = false;
    }

    public int getMinHeight() {
        Drawable background = getBackground();
        if (background != null) {
            return background.getIntrinsicHeight();
        }
        return 0;
    }

    public int getTextSpacing() {
        return this.f1650j;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (!(parent instanceof ViewPager)) {
            throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
        }
        ViewPager viewPager = (ViewPager) parent;
        b1.a adapter = viewPager.getAdapter();
        viewPager.Q(this.f1654n);
        viewPager.b(this.f1654n);
        this.f1644d = viewPager;
        WeakReference<b1.a> weakReference = this.f1655o;
        b(weakReference != null ? weakReference.get() : null, adapter);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewPager viewPager = this.f1644d;
        if (viewPager != null) {
            b(viewPager.getAdapter(), null);
            this.f1644d.Q(null);
            this.f1644d.I(this.f1654n);
            this.f1644d = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        if (this.f1644d != null) {
            float f3 = this.f1649i;
            if (f3 < 0.0f) {
                f3 = 0.0f;
            }
            d(this.f1648h, f3, true);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i3, int i4) {
        int iMax;
        if (View.MeasureSpec.getMode(i3) != 1073741824) {
            throw new IllegalStateException("Must measure with an exact width");
        }
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i4, paddingTop, -2);
        int size = View.MeasureSpec.getSize(i3);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, (int) (size * 0.2f), -2);
        this.f1645e.measure(childMeasureSpec2, childMeasureSpec);
        this.f1646f.measure(childMeasureSpec2, childMeasureSpec);
        this.f1647g.measure(childMeasureSpec2, childMeasureSpec);
        if (View.MeasureSpec.getMode(i4) == 1073741824) {
            iMax = View.MeasureSpec.getSize(i4);
        } else {
            iMax = Math.max(getMinHeight(), this.f1646f.getMeasuredHeight() + paddingTop);
        }
        setMeasuredDimension(size, View.resolveSizeAndState(iMax, i4, this.f1646f.getMeasuredState() << 16));
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.f1652l) {
            return;
        }
        super.requestLayout();
    }

    public void setGravity(int i3) {
        this.f1651k = i3;
        requestLayout();
    }

    public void setNonPrimaryAlpha(float f3) {
        int i3 = ((int) (f3 * 255.0f)) & 255;
        this.f1656p = i3;
        int i4 = (i3 << 24) | (this.f1657q & 16777215);
        this.f1645e.setTextColor(i4);
        this.f1647g.setTextColor(i4);
    }

    public void setTextColor(int i3) {
        this.f1657q = i3;
        this.f1646f.setTextColor(i3);
        int i4 = (this.f1656p << 24) | (this.f1657q & 16777215);
        this.f1645e.setTextColor(i4);
        this.f1647g.setTextColor(i4);
    }

    public void setTextSpacing(int i3) {
        this.f1650j = i3;
        requestLayout();
    }

    public PagerTitleStrip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1648h = -1;
        this.f1649i = -1.0f;
        this.f1654n = new a();
        TextView textView = new TextView(context);
        this.f1645e = textView;
        addView(textView);
        TextView textView2 = new TextView(context);
        this.f1646f = textView2;
        addView(textView2);
        TextView textView3 = new TextView(context);
        this.f1647g = textView3;
        addView(textView3);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1642b);
        boolean z3 = false;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            i.q(this.f1645e, resourceId);
            i.q(this.f1646f, resourceId);
            i.q(this.f1647g, resourceId);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        if (dimensionPixelSize != 0) {
            a(0, dimensionPixelSize);
        }
        if (typedArrayObtainStyledAttributes.hasValue(2)) {
            int color = typedArrayObtainStyledAttributes.getColor(2, 0);
            this.f1645e.setTextColor(color);
            this.f1646f.setTextColor(color);
            this.f1647g.setTextColor(color);
        }
        this.f1651k = typedArrayObtainStyledAttributes.getInteger(3, 80);
        typedArrayObtainStyledAttributes.recycle();
        this.f1657q = this.f1646f.getTextColors().getDefaultColor();
        setNonPrimaryAlpha(0.6f);
        this.f1645e.setEllipsize(TextUtils.TruncateAt.END);
        this.f1646f.setEllipsize(TextUtils.TruncateAt.END);
        this.f1647g.setEllipsize(TextUtils.TruncateAt.END);
        if (resourceId != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(resourceId, f1643c);
            z3 = typedArrayObtainStyledAttributes2.getBoolean(0, false);
            typedArrayObtainStyledAttributes2.recycle();
        }
        if (z3) {
            setSingleLineAllCaps(this.f1645e);
            setSingleLineAllCaps(this.f1646f);
            setSingleLineAllCaps(this.f1647g);
        } else {
            this.f1645e.setSingleLine();
            this.f1646f.setSingleLine();
            this.f1647g.setSingleLine();
        }
        this.f1650j = (int) (context.getResources().getDisplayMetrics().density * 16.0f);
    }
}
