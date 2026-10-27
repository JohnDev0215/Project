package r;

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
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.AppCompatEditText;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: compiled from: AppCompatTextViewAutoSizeHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final RectF f7602a = new RectF();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ConcurrentHashMap<String, Method> f7603b = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static ConcurrentHashMap<String, Field> f7604c = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f7605d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f7606e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f7607f = -1.0f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f7608g = -1.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f7609h = -1.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f7610i = new int[0];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f7611j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public TextPaint f7612k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final TextView f7613l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Context f7614m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final c f7615n;

    /* JADX INFO: compiled from: AppCompatTextViewAutoSizeHelper.java */
    public static class a extends c {
        @Override // r.n.c
        public void a(StaticLayout.Builder builder, TextView textView) {
            builder.setTextDirection((TextDirectionHeuristic) n.r(textView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR));
        }
    }

    /* JADX INFO: compiled from: AppCompatTextViewAutoSizeHelper.java */
    public static class b extends a {
        @Override // r.n.a, r.n.c
        public void a(StaticLayout.Builder builder, TextView textView) {
            builder.setTextDirection(textView.getTextDirectionHeuristic());
        }

        @Override // r.n.c
        public boolean b(TextView textView) {
            return textView.isHorizontallyScrollable();
        }
    }

    /* JADX INFO: compiled from: AppCompatTextViewAutoSizeHelper.java */
    public static class c {
        public void a(StaticLayout.Builder builder, TextView textView) {
        }

        public boolean b(TextView textView) {
            return ((Boolean) n.r(textView, "getHorizontallyScrolling", Boolean.FALSE)).booleanValue();
        }
    }

    public n(TextView textView) {
        this.f7613l = textView;
        this.f7614m = textView.getContext();
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 29) {
            this.f7615n = new b();
        } else if (i8 >= 23) {
            this.f7615n = new a();
        } else {
            this.f7615n = new c();
        }
    }

    public static <T> T a(Object obj, String str, T t7) {
        try {
            Field fieldO = o(str);
            return fieldO == null ? t7 : (T) fieldO.get(obj);
        } catch (IllegalAccessException e8) {
            Log.w("ACTVAutoSizeHelper", "Failed to access TextView#" + str + " member", e8);
            return t7;
        }
    }

    public static Field o(String str) {
        try {
            Field declaredField = f7604c.get(str);
            if (declaredField == null && (declaredField = TextView.class.getDeclaredField(str)) != null) {
                declaredField.setAccessible(true);
                f7604c.put(str, declaredField);
            }
            return declaredField;
        } catch (NoSuchFieldException e8) {
            Log.w("ACTVAutoSizeHelper", "Failed to access TextView#" + str + " member", e8);
            return null;
        }
    }

    public static Method p(String str) {
        try {
            Method declaredMethod = f7603b.get(str);
            if (declaredMethod == null && (declaredMethod = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                declaredMethod.setAccessible(true);
                f7603b.put(str, declaredMethod);
            }
            return declaredMethod;
        } catch (Exception e8) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e8);
            return null;
        }
    }

    public static <T> T r(Object obj, String str, T t7) {
        try {
            return (T) p(str).invoke(obj, new Object[0]);
        } catch (Exception e8) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e8);
            return t7;
        }
    }

    public final void A(TypedArray typedArray) {
        int length = typedArray.length();
        int[] iArr = new int[length];
        if (length > 0) {
            for (int i8 = 0; i8 < length; i8++) {
                iArr[i8] = typedArray.getDimensionPixelSize(i8, -1);
            }
            this.f7610i = c(iArr);
            B();
        }
    }

    public final boolean B() {
        boolean z7 = this.f7610i.length > 0;
        this.f7611j = z7;
        if (z7) {
            this.f7605d = 1;
            this.f7608g = r0[0];
            this.f7609h = r0[r1 - 1];
            this.f7607f = -1.0f;
        }
        return z7;
    }

    public final boolean C(int i8, RectF rectF) {
        CharSequence transformation;
        CharSequence text = this.f7613l.getText();
        TransformationMethod transformationMethod = this.f7613l.getTransformationMethod();
        if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, this.f7613l)) != null) {
            text = transformation;
        }
        int maxLines = Build.VERSION.SDK_INT >= 16 ? this.f7613l.getMaxLines() : -1;
        q(i8);
        StaticLayout staticLayoutE = e(text, (Layout.Alignment) r(this.f7613l, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL), Math.round(rectF.right), maxLines);
        return (maxLines == -1 || (staticLayoutE.getLineCount() <= maxLines && staticLayoutE.getLineEnd(staticLayoutE.getLineCount() - 1) == text.length())) && ((float) staticLayoutE.getHeight()) <= rectF.bottom;
    }

    public final boolean D() {
        return !(this.f7613l instanceof AppCompatEditText);
    }

    public final void E(float f8, float f9, float f10) throws IllegalArgumentException {
        if (f8 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f8 + "px) is less or equal to (0px)");
        }
        if (f9 <= f8) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f9 + "px) is less or equal to minimum auto-size text size (" + f8 + "px)");
        }
        if (f10 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f10 + "px) is less or equal to (0px)");
        }
        this.f7605d = 1;
        this.f7608g = f8;
        this.f7609h = f9;
        this.f7607f = f10;
        this.f7611j = false;
    }

    public void b() {
        if (s()) {
            if (this.f7606e) {
                if (this.f7613l.getMeasuredHeight() <= 0 || this.f7613l.getMeasuredWidth() <= 0) {
                    return;
                }
                int measuredWidth = this.f7615n.b(this.f7613l) ? CarPlayConfig.IapFeature.IAP_FEATURE_DEVICE_TRANSPORT_NOTIFY : (this.f7613l.getMeasuredWidth() - this.f7613l.getTotalPaddingLeft()) - this.f7613l.getTotalPaddingRight();
                int height = (this.f7613l.getHeight() - this.f7613l.getCompoundPaddingBottom()) - this.f7613l.getCompoundPaddingTop();
                if (measuredWidth <= 0 || height <= 0) {
                    return;
                }
                RectF rectF = f7602a;
                synchronized (rectF) {
                    rectF.setEmpty();
                    rectF.right = measuredWidth;
                    rectF.bottom = height;
                    float fI = i(rectF);
                    if (fI != this.f7613l.getTextSize()) {
                        y(0, fI);
                    }
                }
            }
            this.f7606e = true;
        }
    }

    public final int[] c(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i8 : iArr) {
            if (i8 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i8)) < 0) {
                arrayList.add(Integer.valueOf(i8));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i9 = 0; i9 < size; i9++) {
            iArr2[i9] = ((Integer) arrayList.get(i9)).intValue();
        }
        return iArr2;
    }

    public final void d() {
        this.f7605d = 0;
        this.f7608g = -1.0f;
        this.f7609h = -1.0f;
        this.f7607f = -1.0f;
        this.f7610i = new int[0];
        this.f7606e = false;
    }

    public StaticLayout e(CharSequence charSequence, Layout.Alignment alignment, int i8, int i9) {
        int i10 = Build.VERSION.SDK_INT;
        return i10 >= 23 ? f(charSequence, alignment, i8, i9) : i10 >= 16 ? h(charSequence, alignment, i8) : g(charSequence, alignment, i8);
    }

    public final StaticLayout f(CharSequence charSequence, Layout.Alignment alignment, int i8, int i9) {
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), this.f7612k, i8);
        StaticLayout.Builder hyphenationFrequency = builderObtain.setAlignment(alignment).setLineSpacing(this.f7613l.getLineSpacingExtra(), this.f7613l.getLineSpacingMultiplier()).setIncludePad(this.f7613l.getIncludeFontPadding()).setBreakStrategy(this.f7613l.getBreakStrategy()).setHyphenationFrequency(this.f7613l.getHyphenationFrequency());
        if (i9 == -1) {
            i9 = Integer.MAX_VALUE;
        }
        hyphenationFrequency.setMaxLines(i9);
        try {
            this.f7615n.a(builderObtain, this.f7613l);
        } catch (ClassCastException unused) {
            Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
        }
        return builderObtain.build();
    }

    public final StaticLayout g(CharSequence charSequence, Layout.Alignment alignment, int i8) {
        return new StaticLayout(charSequence, this.f7612k, i8, alignment, ((Float) a(this.f7613l, "mSpacingMult", Float.valueOf(1.0f))).floatValue(), ((Float) a(this.f7613l, "mSpacingAdd", Float.valueOf(0.0f))).floatValue(), ((Boolean) a(this.f7613l, "mIncludePad", Boolean.TRUE)).booleanValue());
    }

    public final StaticLayout h(CharSequence charSequence, Layout.Alignment alignment, int i8) {
        return new StaticLayout(charSequence, this.f7612k, i8, alignment, this.f7613l.getLineSpacingMultiplier(), this.f7613l.getLineSpacingExtra(), this.f7613l.getIncludeFontPadding());
    }

    public final int i(RectF rectF) {
        int i8;
        int length = this.f7610i.length;
        if (length == 0) {
            throw new IllegalStateException("No available text sizes to choose from.");
        }
        int i9 = 0;
        int i10 = 1;
        int i11 = length - 1;
        while (true) {
            int i12 = i10;
            int i13 = i9;
            i9 = i12;
            while (i9 <= i11) {
                i8 = (i9 + i11) / 2;
                if (C(this.f7610i[i8], rectF)) {
                    break;
                }
                i13 = i8 - 1;
                i11 = i13;
            }
            return this.f7610i[i13];
            i10 = i8 + 1;
        }
    }

    public int j() {
        return Math.round(this.f7609h);
    }

    public int k() {
        return Math.round(this.f7608g);
    }

    public int l() {
        return Math.round(this.f7607f);
    }

    public int[] m() {
        return this.f7610i;
    }

    public int n() {
        return this.f7605d;
    }

    public void q(int i8) {
        TextPaint textPaint = this.f7612k;
        if (textPaint == null) {
            this.f7612k = new TextPaint();
        } else {
            textPaint.reset();
        }
        this.f7612k.set(this.f7613l.getPaint());
        this.f7612k.setTextSize(i8);
    }

    public boolean s() {
        return D() && this.f7605d != 0;
    }

    public void t(AttributeSet attributeSet, int i8) {
        int resourceId;
        Context context = this.f7614m;
        int[] iArr = R$styleable.AppCompatTextView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i8, 0);
        TextView textView = this.f7613l;
        o0.t.j0(textView, textView.getContext(), iArr, attributeSet, typedArrayObtainStyledAttributes, i8, 0);
        int i9 = R$styleable.AppCompatTextView_autoSizeTextType;
        if (typedArrayObtainStyledAttributes.hasValue(i9)) {
            this.f7605d = typedArrayObtainStyledAttributes.getInt(i9, 0);
        }
        int i10 = R$styleable.AppCompatTextView_autoSizeStepGranularity;
        float dimension = typedArrayObtainStyledAttributes.hasValue(i10) ? typedArrayObtainStyledAttributes.getDimension(i10, -1.0f) : -1.0f;
        int i11 = R$styleable.AppCompatTextView_autoSizeMinTextSize;
        float dimension2 = typedArrayObtainStyledAttributes.hasValue(i11) ? typedArrayObtainStyledAttributes.getDimension(i11, -1.0f) : -1.0f;
        int i12 = R$styleable.AppCompatTextView_autoSizeMaxTextSize;
        float dimension3 = typedArrayObtainStyledAttributes.hasValue(i12) ? typedArrayObtainStyledAttributes.getDimension(i12, -1.0f) : -1.0f;
        int i13 = R$styleable.AppCompatTextView_autoSizePresetSizes;
        if (typedArrayObtainStyledAttributes.hasValue(i13) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(i13, 0)) > 0) {
            TypedArray typedArrayObtainTypedArray = typedArrayObtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            A(typedArrayObtainTypedArray);
            typedArrayObtainTypedArray.recycle();
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!D()) {
            this.f7605d = 0;
            return;
        }
        if (this.f7605d == 1) {
            if (!this.f7611j) {
                DisplayMetrics displayMetrics = this.f7614m.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                E(dimension2, dimension3, dimension);
            }
            z();
        }
    }

    public void u(int i8, int i9, int i10, int i11) throws IllegalArgumentException {
        if (D()) {
            DisplayMetrics displayMetrics = this.f7614m.getResources().getDisplayMetrics();
            E(TypedValue.applyDimension(i11, i8, displayMetrics), TypedValue.applyDimension(i11, i9, displayMetrics), TypedValue.applyDimension(i11, i10, displayMetrics));
            if (z()) {
                b();
            }
        }
    }

    public void v(int[] iArr, int i8) throws IllegalArgumentException {
        if (D()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArrCopyOf = new int[length];
                if (i8 == 0) {
                    iArrCopyOf = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = this.f7614m.getResources().getDisplayMetrics();
                    for (int i9 = 0; i9 < length; i9++) {
                        iArrCopyOf[i9] = Math.round(TypedValue.applyDimension(i8, iArr[i9], displayMetrics));
                    }
                }
                this.f7610i = c(iArrCopyOf);
                if (!B()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.f7611j = false;
            }
            if (z()) {
                b();
            }
        }
    }

    public void w(int i8) {
        if (D()) {
            if (i8 == 0) {
                d();
                return;
            }
            if (i8 != 1) {
                throw new IllegalArgumentException("Unknown auto-size text type: " + i8);
            }
            DisplayMetrics displayMetrics = this.f7614m.getResources().getDisplayMetrics();
            E(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (z()) {
                b();
            }
        }
    }

    public final void x(float f8) {
        if (f8 != this.f7613l.getPaint().getTextSize()) {
            this.f7613l.getPaint().setTextSize(f8);
            boolean zIsInLayout = Build.VERSION.SDK_INT >= 18 ? this.f7613l.isInLayout() : false;
            if (this.f7613l.getLayout() != null) {
                this.f7606e = false;
                try {
                    Method methodP = p("nullLayouts");
                    if (methodP != null) {
                        methodP.invoke(this.f7613l, new Object[0]);
                    }
                } catch (Exception e8) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e8);
                }
                if (zIsInLayout) {
                    this.f7613l.forceLayout();
                } else {
                    this.f7613l.requestLayout();
                }
                this.f7613l.invalidate();
            }
        }
    }

    public void y(int i8, float f8) {
        Context context = this.f7614m;
        x(TypedValue.applyDimension(i8, f8, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics()));
    }

    public final boolean z() {
        if (D() && this.f7605d == 1) {
            if (!this.f7611j || this.f7610i.length == 0) {
                int iFloor = ((int) Math.floor((this.f7609h - this.f7608g) / this.f7607f)) + 1;
                int[] iArr = new int[iFloor];
                for (int i8 = 0; i8 < iFloor; i8++) {
                    iArr[i8] = Math.round(this.f7608g + (i8 * this.f7607f));
                }
                this.f7610i = c(iArr);
            }
            this.f7606e = true;
        } else {
            this.f7606e = false;
        }
        return this.f7606e;
    }
}
