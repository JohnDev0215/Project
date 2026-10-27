package o;

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

/* JADX INFO: compiled from: AppCompatTextViewAutoSizeHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final RectF f4343a = new RectF();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ConcurrentHashMap<String, Method> f4344b = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static ConcurrentHashMap<String, Field> f4345c = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4346d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f4347e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f4348f = -1.0f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f4349g = -1.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f4350h = -1.0f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f4351i = new int[0];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f4352j = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public TextPaint f4353k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final TextView f4354l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Context f4355m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final c f4356n;

    /* JADX INFO: compiled from: AppCompatTextViewAutoSizeHelper.java */
    public static class a extends c {
        @Override // o.n.c
        public void a(StaticLayout.Builder builder, TextView textView) {
            builder.setTextDirection((TextDirectionHeuristic) n.r(textView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR));
        }
    }

    /* JADX INFO: compiled from: AppCompatTextViewAutoSizeHelper.java */
    public static class b extends a {
        @Override // o.n.a, o.n.c
        public void a(StaticLayout.Builder builder, TextView textView) {
            builder.setTextDirection(textView.getTextDirectionHeuristic());
        }

        @Override // o.n.c
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
        this.f4354l = textView;
        this.f4355m = textView.getContext();
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            this.f4356n = new b();
        } else if (i3 >= 23) {
            this.f4356n = new a();
        } else {
            this.f4356n = new c();
        }
    }

    public static <T> T a(Object obj, String str, T t3) {
        try {
            Field fieldO = o(str);
            return fieldO == null ? t3 : (T) fieldO.get(obj);
        } catch (IllegalAccessException e3) {
            Log.w("ACTVAutoSizeHelper", "Failed to access TextView#" + str + " member", e3);
            return t3;
        }
    }

    public static Field o(String str) {
        try {
            Field declaredField = f4345c.get(str);
            if (declaredField == null && (declaredField = TextView.class.getDeclaredField(str)) != null) {
                declaredField.setAccessible(true);
                f4345c.put(str, declaredField);
            }
            return declaredField;
        } catch (NoSuchFieldException e3) {
            Log.w("ACTVAutoSizeHelper", "Failed to access TextView#" + str + " member", e3);
            return null;
        }
    }

    public static Method p(String str) {
        try {
            Method declaredMethod = f4344b.get(str);
            if (declaredMethod == null && (declaredMethod = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                declaredMethod.setAccessible(true);
                f4344b.put(str, declaredMethod);
            }
            return declaredMethod;
        } catch (Exception e3) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e3);
            return null;
        }
    }

    public static <T> T r(Object obj, String str, T t3) {
        try {
            return (T) p(str).invoke(obj, new Object[0]);
        } catch (Exception e3) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e3);
            return t3;
        }
    }

    public final void A(TypedArray typedArray) {
        int length = typedArray.length();
        int[] iArr = new int[length];
        if (length > 0) {
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = typedArray.getDimensionPixelSize(i3, -1);
            }
            this.f4351i = c(iArr);
            B();
        }
    }

    public final boolean B() {
        boolean z3 = this.f4351i.length > 0;
        this.f4352j = z3;
        if (z3) {
            this.f4346d = 1;
            this.f4349g = r0[0];
            this.f4350h = r0[r1 - 1];
            this.f4348f = -1.0f;
        }
        return z3;
    }

    public final boolean C(int i3, RectF rectF) {
        CharSequence transformation;
        CharSequence text = this.f4354l.getText();
        TransformationMethod transformationMethod = this.f4354l.getTransformationMethod();
        if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, this.f4354l)) != null) {
            text = transformation;
        }
        int maxLines = Build.VERSION.SDK_INT >= 16 ? this.f4354l.getMaxLines() : -1;
        q(i3);
        StaticLayout staticLayoutE = e(text, (Layout.Alignment) r(this.f4354l, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL), Math.round(rectF.right), maxLines);
        return (maxLines == -1 || (staticLayoutE.getLineCount() <= maxLines && staticLayoutE.getLineEnd(staticLayoutE.getLineCount() - 1) == text.length())) && ((float) staticLayoutE.getHeight()) <= rectF.bottom;
    }

    public final boolean D() {
        return !(this.f4354l instanceof AppCompatEditText);
    }

    public final void E(float f3, float f4, float f5) throws IllegalArgumentException {
        if (f3 <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f3 + "px) is less or equal to (0px)");
        }
        if (f4 <= f3) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f4 + "px) is less or equal to minimum auto-size text size (" + f3 + "px)");
        }
        if (f5 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f5 + "px) is less or equal to (0px)");
        }
        this.f4346d = 1;
        this.f4349g = f3;
        this.f4350h = f4;
        this.f4348f = f5;
        this.f4352j = false;
    }

    public void b() {
        if (s()) {
            if (this.f4347e) {
                if (this.f4354l.getMeasuredHeight() <= 0 || this.f4354l.getMeasuredWidth() <= 0) {
                    return;
                }
                int measuredWidth = this.f4356n.b(this.f4354l) ? 1048576 : (this.f4354l.getMeasuredWidth() - this.f4354l.getTotalPaddingLeft()) - this.f4354l.getTotalPaddingRight();
                int height = (this.f4354l.getHeight() - this.f4354l.getCompoundPaddingBottom()) - this.f4354l.getCompoundPaddingTop();
                if (measuredWidth <= 0 || height <= 0) {
                    return;
                }
                RectF rectF = f4343a;
                synchronized (rectF) {
                    rectF.setEmpty();
                    rectF.right = measuredWidth;
                    rectF.bottom = height;
                    float fI = i(rectF);
                    if (fI != this.f4354l.getTextSize()) {
                        y(0, fI);
                    }
                }
            }
            this.f4347e = true;
        }
    }

    public final int[] c(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i3 : iArr) {
            if (i3 > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i3)) < 0) {
                arrayList.add(Integer.valueOf(i3));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i4 = 0; i4 < size; i4++) {
            iArr2[i4] = ((Integer) arrayList.get(i4)).intValue();
        }
        return iArr2;
    }

    public final void d() {
        this.f4346d = 0;
        this.f4349g = -1.0f;
        this.f4350h = -1.0f;
        this.f4348f = -1.0f;
        this.f4351i = new int[0];
        this.f4347e = false;
    }

    public StaticLayout e(CharSequence charSequence, Layout.Alignment alignment, int i3, int i4) {
        int i5 = Build.VERSION.SDK_INT;
        return i5 >= 23 ? f(charSequence, alignment, i3, i4) : i5 >= 16 ? h(charSequence, alignment, i3) : g(charSequence, alignment, i3);
    }

    public final StaticLayout f(CharSequence charSequence, Layout.Alignment alignment, int i3, int i4) {
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequence, 0, charSequence.length(), this.f4353k, i3);
        StaticLayout.Builder hyphenationFrequency = builderObtain.setAlignment(alignment).setLineSpacing(this.f4354l.getLineSpacingExtra(), this.f4354l.getLineSpacingMultiplier()).setIncludePad(this.f4354l.getIncludeFontPadding()).setBreakStrategy(this.f4354l.getBreakStrategy()).setHyphenationFrequency(this.f4354l.getHyphenationFrequency());
        if (i4 == -1) {
            i4 = Integer.MAX_VALUE;
        }
        hyphenationFrequency.setMaxLines(i4);
        try {
            this.f4356n.a(builderObtain, this.f4354l);
        } catch (ClassCastException unused) {
            Log.w("ACTVAutoSizeHelper", "Failed to obtain TextDirectionHeuristic, auto size may be incorrect");
        }
        return builderObtain.build();
    }

    public final StaticLayout g(CharSequence charSequence, Layout.Alignment alignment, int i3) {
        return new StaticLayout(charSequence, this.f4353k, i3, alignment, ((Float) a(this.f4354l, "mSpacingMult", Float.valueOf(1.0f))).floatValue(), ((Float) a(this.f4354l, "mSpacingAdd", Float.valueOf(0.0f))).floatValue(), ((Boolean) a(this.f4354l, "mIncludePad", Boolean.TRUE)).booleanValue());
    }

    public final StaticLayout h(CharSequence charSequence, Layout.Alignment alignment, int i3) {
        return new StaticLayout(charSequence, this.f4353k, i3, alignment, this.f4354l.getLineSpacingMultiplier(), this.f4354l.getLineSpacingExtra(), this.f4354l.getIncludeFontPadding());
    }

    public final int i(RectF rectF) {
        int i3;
        int length = this.f4351i.length;
        if (length == 0) {
            throw new IllegalStateException("No available text sizes to choose from.");
        }
        int i4 = 0;
        int i5 = 1;
        int i6 = length - 1;
        while (true) {
            int i7 = i5;
            int i8 = i4;
            i4 = i7;
            while (i4 <= i6) {
                i3 = (i4 + i6) / 2;
                if (C(this.f4351i[i3], rectF)) {
                    break;
                }
                i8 = i3 - 1;
                i6 = i8;
            }
            return this.f4351i[i8];
            i5 = i3 + 1;
        }
    }

    public int j() {
        return Math.round(this.f4350h);
    }

    public int k() {
        return Math.round(this.f4349g);
    }

    public int l() {
        return Math.round(this.f4348f);
    }

    public int[] m() {
        return this.f4351i;
    }

    public int n() {
        return this.f4346d;
    }

    public void q(int i3) {
        TextPaint textPaint = this.f4353k;
        if (textPaint == null) {
            this.f4353k = new TextPaint();
        } else {
            textPaint.reset();
        }
        this.f4353k.set(this.f4354l.getPaint());
        this.f4353k.setTextSize(i3);
    }

    public boolean s() {
        return D() && this.f4346d != 0;
    }

    public void t(AttributeSet attributeSet, int i3) {
        int resourceId;
        Context context = this.f4355m;
        int[] iArr = R$styleable.AppCompatTextView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i3, 0);
        TextView textView = this.f4354l;
        l0.t.h0(textView, textView.getContext(), iArr, attributeSet, typedArrayObtainStyledAttributes, i3, 0);
        int i4 = R$styleable.AppCompatTextView_autoSizeTextType;
        if (typedArrayObtainStyledAttributes.hasValue(i4)) {
            this.f4346d = typedArrayObtainStyledAttributes.getInt(i4, 0);
        }
        int i5 = R$styleable.AppCompatTextView_autoSizeStepGranularity;
        float dimension = typedArrayObtainStyledAttributes.hasValue(i5) ? typedArrayObtainStyledAttributes.getDimension(i5, -1.0f) : -1.0f;
        int i6 = R$styleable.AppCompatTextView_autoSizeMinTextSize;
        float dimension2 = typedArrayObtainStyledAttributes.hasValue(i6) ? typedArrayObtainStyledAttributes.getDimension(i6, -1.0f) : -1.0f;
        int i7 = R$styleable.AppCompatTextView_autoSizeMaxTextSize;
        float dimension3 = typedArrayObtainStyledAttributes.hasValue(i7) ? typedArrayObtainStyledAttributes.getDimension(i7, -1.0f) : -1.0f;
        int i8 = R$styleable.AppCompatTextView_autoSizePresetSizes;
        if (typedArrayObtainStyledAttributes.hasValue(i8) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(i8, 0)) > 0) {
            TypedArray typedArrayObtainTypedArray = typedArrayObtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            A(typedArrayObtainTypedArray);
            typedArrayObtainTypedArray.recycle();
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!D()) {
            this.f4346d = 0;
            return;
        }
        if (this.f4346d == 1) {
            if (!this.f4352j) {
                DisplayMetrics displayMetrics = this.f4355m.getResources().getDisplayMetrics();
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

    public void u(int i3, int i4, int i5, int i6) throws IllegalArgumentException {
        if (D()) {
            DisplayMetrics displayMetrics = this.f4355m.getResources().getDisplayMetrics();
            E(TypedValue.applyDimension(i6, i3, displayMetrics), TypedValue.applyDimension(i6, i4, displayMetrics), TypedValue.applyDimension(i6, i5, displayMetrics));
            if (z()) {
                b();
            }
        }
    }

    public void v(int[] iArr, int i3) throws IllegalArgumentException {
        if (D()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArrCopyOf = new int[length];
                if (i3 == 0) {
                    iArrCopyOf = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = this.f4355m.getResources().getDisplayMetrics();
                    for (int i4 = 0; i4 < length; i4++) {
                        iArrCopyOf[i4] = Math.round(TypedValue.applyDimension(i3, iArr[i4], displayMetrics));
                    }
                }
                this.f4351i = c(iArrCopyOf);
                if (!B()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                this.f4352j = false;
            }
            if (z()) {
                b();
            }
        }
    }

    public void w(int i3) {
        if (D()) {
            if (i3 == 0) {
                d();
                return;
            }
            if (i3 != 1) {
                throw new IllegalArgumentException("Unknown auto-size text type: " + i3);
            }
            DisplayMetrics displayMetrics = this.f4355m.getResources().getDisplayMetrics();
            E(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (z()) {
                b();
            }
        }
    }

    public final void x(float f3) {
        if (f3 != this.f4354l.getPaint().getTextSize()) {
            this.f4354l.getPaint().setTextSize(f3);
            boolean zIsInLayout = Build.VERSION.SDK_INT >= 18 ? this.f4354l.isInLayout() : false;
            if (this.f4354l.getLayout() != null) {
                this.f4347e = false;
                try {
                    Method methodP = p("nullLayouts");
                    if (methodP != null) {
                        methodP.invoke(this.f4354l, new Object[0]);
                    }
                } catch (Exception e3) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e3);
                }
                if (zIsInLayout) {
                    this.f4354l.forceLayout();
                } else {
                    this.f4354l.requestLayout();
                }
                this.f4354l.invalidate();
            }
        }
    }

    public void y(int i3, float f3) {
        Context context = this.f4355m;
        x(TypedValue.applyDimension(i3, f3, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics()));
    }

    public final boolean z() {
        if (D() && this.f4346d == 1) {
            if (!this.f4352j || this.f4351i.length == 0) {
                int iFloor = ((int) Math.floor((this.f4350h - this.f4349g) / this.f4348f)) + 1;
                int[] iArr = new int[iFloor];
                for (int i3 = 0; i3 < iFloor; i3++) {
                    iArr[i3] = Math.round(this.f4349g + (i3 * this.f4348f));
                }
                this.f4351i = c(iArr);
            }
            this.f4347e = true;
        } else {
            this.f4347e = false;
        }
        return this.f4347e;
    }
}
