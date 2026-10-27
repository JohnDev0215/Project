package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: AppCompatTextViewAutoSizeHelper.java */
/* JADX INFO: loaded from: classes.dex */
class x {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private static final RectF f1244l = new RectF();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    @SuppressLint({"BanConcurrentHashMap"})
    private static ConcurrentHashMap<String, Method> f1245m = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    @SuppressLint({"BanConcurrentHashMap"})
    private static ConcurrentHashMap<String, Field> f1246n = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1247a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f1248b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f1249c = -1.0f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f1250d = -1.0f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float f1251e = -1.0f;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int[] f1252f = new int[0];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f1253g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private TextPaint f1254h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final TextView f1255i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final Context f1256j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private final c f1257k;

    /* JADX INFO: compiled from: AppCompatTextViewAutoSizeHelper.java */
    private static class a extends c {
        a() {
        }

        @Override // androidx.appcompat.widget.x.c
        void a(StaticLayout.Builder builder, TextView textView) {
            builder.setTextDirection((TextDirectionHeuristic) x.n(textView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR));
        }
    }

    /* JADX INFO: compiled from: AppCompatTextViewAutoSizeHelper.java */
    private static class b extends a {
        b() {
        }

        @Override // androidx.appcompat.widget.x.a, androidx.appcompat.widget.x.c
        void a(StaticLayout.Builder builder, TextView textView) {
            builder.setTextDirection(textView.getTextDirectionHeuristic());
        }

        @Override // androidx.appcompat.widget.x.c
        boolean b(TextView textView) {
            return textView.isHorizontallyScrollable();
        }
    }

    /* JADX INFO: compiled from: AppCompatTextViewAutoSizeHelper.java */
    private static class c {
        c() {
        }

        void a(StaticLayout.Builder builder, TextView textView) {
            throw null;
        }

        boolean b(TextView textView) {
            return ((Boolean) x.n(textView, "getHorizontallyScrolling", Boolean.FALSE)).booleanValue();
        }
    }

    x(TextView textView) {
        this.f1255i = textView;
        this.f1256j = textView.getContext();
        if (Build.VERSION.SDK_INT >= 29) {
            this.f1257k = new b();
        } else {
            this.f1257k = new a();
        }
    }

    private void A(float f7, float f8, float f9) {
        if (f7 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f7 + "px) is less or equal to (0px)");
        }
        if (f8 <= f7) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f8 + "px) is less or equal to minimum auto-size text size (" + f7 + "px)");
        }
        if (f9 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f9 + "px) is less or equal to (0px)");
        }
        this.f1247a = 1;
        this.f1250d = f7;
        this.f1251e = f8;
        this.f1249c = f9;
        this.f1253g = false;
    }

    private int[] b(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i7 : iArr) {
            if (i7 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i7)) < 0) {
                arrayList.add(Integer.valueOf(i7));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i8 = 0; i8 < size; i8++) {
            iArr2[i8] = ((Integer) arrayList.get(i8)).intValue();
        }
        return iArr2;
    }

    private void c() {
        this.f1247a = 0;
        this.f1250d = -1.0f;
        this.f1251e = -1.0f;
        this.f1249c = -1.0f;
        this.f1252f = new int[0];
        this.f1248b = false;
    }

    private StaticLayout e(CharSequence charSequence, Layout.Alignment alignment, int i7, int i8) {
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), this.f1254h, i7);
        StaticLayout.Builder hyphenationFrequency = builderObtain.setAlignment(alignment).setLineSpacing(this.f1255i.getLineSpacingExtra(), this.f1255i.getLineSpacingMultiplier()).setIncludePad(this.f1255i.getIncludeFontPadding()).setBreakStrategy(this.f1255i.getBreakStrategy()).setHyphenationFrequency(this.f1255i.getHyphenationFrequency());
        if (i8 == -1) {
            i8 = Integer.MAX_VALUE;
        }
        hyphenationFrequency.setMaxLines(i8);
        try {
            this.f1257k.a(builderObtain, this.f1255i);
        } catch (ClassCastException unused) {
            Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
        }
        return builderObtain.build();
    }

    private int f(RectF rectF) {
        int i7;
        int length = this.f1252f.length;
        if (length == 0) {
            throw new IllegalStateException("No available text sizes to choose from.");
        }
        int i8 = 0;
        int i9 = 1;
        int i10 = length - 1;
        while (true) {
            int i11 = i9;
            int i12 = i8;
            i8 = i11;
            while (i8 <= i10) {
                i7 = (i8 + i10) / 2;
                if (y(this.f1252f[i7], rectF)) {
                    break;
                }
                i12 = i7 - 1;
                i10 = i12;
            }
            return this.f1252f[i12];
            i9 = i7 + 1;
        }
    }

    private static Method l(String str) {
        try {
            Method declaredMethod = f1245m.get(str);
            if (declaredMethod == null && (declaredMethod = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                declaredMethod.setAccessible(true);
                f1245m.put(str, declaredMethod);
            }
            return declaredMethod;
        } catch (Exception e7) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e7);
            return null;
        }
    }

    static <T> T n(Object obj, String str, T t6) {
        try {
            return (T) l(str).invoke(obj, new Object[0]);
        } catch (Exception e7) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e7);
            return t6;
        }
    }

    private void t(float f7) {
        if (f7 != this.f1255i.getPaint().getTextSize()) {
            this.f1255i.getPaint().setTextSize(f7);
            boolean zIsInLayout = this.f1255i.isInLayout();
            if (this.f1255i.getLayout() != null) {
                this.f1248b = false;
                try {
                    Method methodL = l("nullLayouts");
                    if (methodL != null) {
                        methodL.invoke(this.f1255i, new Object[0]);
                    }
                } catch (Exception e7) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e7);
                }
                if (zIsInLayout) {
                    this.f1255i.forceLayout();
                } else {
                    this.f1255i.requestLayout();
                }
                this.f1255i.invalidate();
            }
        }
    }

    private boolean v() {
        if (z() && this.f1247a == 1) {
            if (!this.f1253g || this.f1252f.length == 0) {
                int iFloor = ((int) Math.floor((this.f1251e - this.f1250d) / this.f1249c)) + 1;
                int[] iArr = new int[iFloor];
                for (int i7 = 0; i7 < iFloor; i7++) {
                    iArr[i7] = Math.round(this.f1250d + (i7 * this.f1249c));
                }
                this.f1252f = b(iArr);
            }
            this.f1248b = true;
        } else {
            this.f1248b = false;
        }
        return this.f1248b;
    }

    private void w(TypedArray typedArray) {
        int length = typedArray.length();
        int[] iArr = new int[length];
        if (length > 0) {
            for (int i7 = 0; i7 < length; i7++) {
                iArr[i7] = typedArray.getDimensionPixelSize(i7, -1);
            }
            this.f1252f = b(iArr);
            x();
        }
    }

    private boolean x() {
        boolean z6 = this.f1252f.length > 0;
        this.f1253g = z6;
        if (z6) {
            this.f1247a = 1;
            this.f1250d = r0[0];
            this.f1251e = r0[r1 - 1];
            this.f1249c = -1.0f;
        }
        return z6;
    }

    private boolean y(int i7, RectF rectF) {
        CharSequence transformation;
        CharSequence text = this.f1255i.getText();
        TransformationMethod transformationMethod = this.f1255i.getTransformationMethod();
        if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, this.f1255i)) != null) {
            text = transformation;
        }
        int maxLines = this.f1255i.getMaxLines();
        m(i7);
        StaticLayout staticLayoutD = d(text, (Layout.Alignment) n(this.f1255i, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL), Math.round(rectF.right), maxLines);
        return (maxLines == -1 || (staticLayoutD.getLineCount() <= maxLines && staticLayoutD.getLineEnd(staticLayoutD.getLineCount() - 1) == text.length())) && ((float) staticLayoutD.getHeight()) <= rectF.bottom;
    }

    private boolean z() {
        return !(this.f1255i instanceof AppCompatEditText);
    }

    void a() {
        if (o()) {
            if (this.f1248b) {
                if (this.f1255i.getMeasuredHeight() <= 0 || this.f1255i.getMeasuredWidth() <= 0) {
                    return;
                }
                int measuredWidth = this.f1257k.b(this.f1255i) ? 1048576 : (this.f1255i.getMeasuredWidth() - this.f1255i.getTotalPaddingLeft()) - this.f1255i.getTotalPaddingRight();
                int height = (this.f1255i.getHeight() - this.f1255i.getCompoundPaddingBottom()) - this.f1255i.getCompoundPaddingTop();
                if (measuredWidth <= 0 || height <= 0) {
                    return;
                }
                RectF rectF = f1244l;
                synchronized (rectF) {
                    rectF.setEmpty();
                    rectF.right = measuredWidth;
                    rectF.bottom = height;
                    float f7 = f(rectF);
                    if (f7 != this.f1255i.getTextSize()) {
                        u(0, f7);
                    }
                }
            }
            this.f1248b = true;
        }
    }

    StaticLayout d(CharSequence charSequence, Layout.Alignment alignment, int i7, int i8) {
        return e(charSequence, alignment, i7, i8);
    }

    int g() {
        return Math.round(this.f1251e);
    }

    int h() {
        return Math.round(this.f1250d);
    }

    int i() {
        return Math.round(this.f1249c);
    }

    int[] j() {
        return this.f1252f;
    }

    int k() {
        return this.f1247a;
    }

    void m(int i7) {
        TextPaint textPaint = this.f1254h;
        if (textPaint == null) {
            this.f1254h = new TextPaint();
        } else {
            textPaint.reset();
        }
        this.f1254h.set(this.f1255i.getPaint());
        this.f1254h.setTextSize(i7);
    }

    boolean o() {
        return z() && this.f1247a != 0;
    }

    void p(AttributeSet attributeSet, int i7) {
        int resourceId;
        Context context = this.f1256j;
        int[] iArr = e.j.AppCompatTextView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i7, 0);
        TextView textView = this.f1255i;
        i0.u.i0(textView, textView.getContext(), iArr, attributeSet, typedArrayObtainStyledAttributes, i7, 0);
        int i8 = e.j.AppCompatTextView_autoSizeTextType;
        if (typedArrayObtainStyledAttributes.hasValue(i8)) {
            this.f1247a = typedArrayObtainStyledAttributes.getInt(i8, 0);
        }
        int i9 = e.j.AppCompatTextView_autoSizeStepGranularity;
        float dimension = typedArrayObtainStyledAttributes.hasValue(i9) ? typedArrayObtainStyledAttributes.getDimension(i9, -1.0f) : -1.0f;
        int i10 = e.j.AppCompatTextView_autoSizeMinTextSize;
        float dimension2 = typedArrayObtainStyledAttributes.hasValue(i10) ? typedArrayObtainStyledAttributes.getDimension(i10, -1.0f) : -1.0f;
        int i11 = e.j.AppCompatTextView_autoSizeMaxTextSize;
        float dimension3 = typedArrayObtainStyledAttributes.hasValue(i11) ? typedArrayObtainStyledAttributes.getDimension(i11, -1.0f) : -1.0f;
        int i12 = e.j.AppCompatTextView_autoSizePresetSizes;
        if (typedArrayObtainStyledAttributes.hasValue(i12) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(i12, 0)) > 0) {
            TypedArray typedArrayObtainTypedArray = typedArrayObtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            w(typedArrayObtainTypedArray);
            typedArrayObtainTypedArray.recycle();
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!z()) {
            this.f1247a = 0;
            return;
        }
        if (this.f1247a == 1) {
            if (!this.f1253g) {
                DisplayMetrics displayMetrics = this.f1256j.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                A(dimension2, dimension3, dimension);
            }
            v();
        }
    }

    void q(int i7, int i8, int i9, int i10) {
        if (z()) {
            DisplayMetrics displayMetrics = this.f1256j.getResources().getDisplayMetrics();
            A(TypedValue.applyDimension(i10, i7, displayMetrics), TypedValue.applyDimension(i10, i8, displayMetrics), TypedValue.applyDimension(i10, i9, displayMetrics));
            if (v()) {
                a();
            }
        }
    }

    void r(int[] iArr, int i7) {
        if (z()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArrCopyOf = new int[length];
                if (i7 == 0) {
                    iArrCopyOf = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = this.f1256j.getResources().getDisplayMetrics();
                    for (int i8 = 0; i8 < length; i8++) {
                        iArrCopyOf[i8] = Math.round(TypedValue.applyDimension(i7, iArr[i8], displayMetrics));
                    }
                }
                this.f1252f = b(iArrCopyOf);
                if (!x()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.f1253g = false;
            }
            if (v()) {
                a();
            }
        }
    }

    void s(int i7) {
        if (z()) {
            if (i7 == 0) {
                c();
                return;
            }
            if (i7 != 1) {
                throw new IllegalArgumentException("Unknown auto-size text type: " + i7);
            }
            DisplayMetrics displayMetrics = this.f1256j.getResources().getDisplayMetrics();
            A(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (v()) {
                a();
            }
        }
    }

    void u(int i7, float f7) {
        Context context = this.f1256j;
        t(TypedValue.applyDimension(i7, f7, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics()));
    }
}
