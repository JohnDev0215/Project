package a.b.p;

import a.b.k.l;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.LocaleList;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f392a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public v0 f393b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v0 f394c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public v0 f395d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public v0 f396e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public v0 f397f;
    public v0 g;
    public v0 h;
    public final a0 i;
    public int j = 0;
    public int k = -1;
    public Typeface l;
    public boolean m;

    public class a extends a.h.d.b.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f398a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f399b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ WeakReference f400c;

        public a(int i, int i2, WeakReference weakReference) {
            this.f398a = i;
            this.f399b = i2;
            this.f400c = weakReference;
        }

        @Override // a.h.d.b.e
        public void d(int i) {
        }

        @Override // a.h.d.b.e
        public void e(Typeface typeface) {
            int i = this.f398a;
            if (i != -1) {
                typeface = Typeface.create(typeface, i, (this.f399b & 2) != 0);
            }
            y yVar = y.this;
            WeakReference weakReference = this.f400c;
            if (yVar.m) {
                yVar.l = typeface;
                TextView textView = (TextView) weakReference.get();
                if (textView != null) {
                    textView.setTypeface(typeface, yVar.j);
                }
            }
        }
    }

    public y(TextView textView) {
        this.f392a = textView;
        this.i = new a0(this.f392a);
    }

    public static v0 c(Context context, j jVar, int i) {
        ColorStateList colorStateListD = jVar.d(context, i);
        if (colorStateListD == null) {
            return null;
        }
        v0 v0Var = new v0();
        v0Var.f369d = true;
        v0Var.f366a = colorStateListD;
        return v0Var;
    }

    public final void a(Drawable drawable, v0 v0Var) {
        if (drawable == null || v0Var == null) {
            return;
        }
        j.f(drawable, v0Var, this.f392a.getDrawableState());
    }

    public void b() {
        if (this.f393b != null || this.f394c != null || this.f395d != null || this.f396e != null) {
            Drawable[] compoundDrawables = this.f392a.getCompoundDrawables();
            a(compoundDrawables[0], this.f393b);
            a(compoundDrawables[1], this.f394c);
            a(compoundDrawables[2], this.f395d);
            a(compoundDrawables[3], this.f396e);
        }
        if (this.f397f == null && this.g == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = this.f392a.getCompoundDrawablesRelative();
        a(compoundDrawablesRelative[0], this.f397f);
        a(compoundDrawablesRelative[2], this.g);
    }

    @SuppressLint({"NewApi"})
    public void d(AttributeSet attributeSet, int i) {
        boolean zA;
        boolean z;
        String strM;
        String strM2;
        ColorStateList colorStateList;
        int resourceId;
        int resourceId2;
        Context context = this.f392a.getContext();
        j jVarA = j.a();
        x0 x0VarQ = x0.q(context, attributeSet, a.b.j.AppCompatTextHelper, i, 0);
        TextView textView = this.f392a;
        a.h.k.q.T(textView, textView.getContext(), a.b.j.AppCompatTextHelper, attributeSet, x0VarQ.f390b, i, 0);
        int iL = x0VarQ.l(a.b.j.AppCompatTextHelper_android_textAppearance, -1);
        if (x0VarQ.o(a.b.j.AppCompatTextHelper_android_drawableLeft)) {
            this.f393b = c(context, jVarA, x0VarQ.l(a.b.j.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (x0VarQ.o(a.b.j.AppCompatTextHelper_android_drawableTop)) {
            this.f394c = c(context, jVarA, x0VarQ.l(a.b.j.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (x0VarQ.o(a.b.j.AppCompatTextHelper_android_drawableRight)) {
            this.f395d = c(context, jVarA, x0VarQ.l(a.b.j.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (x0VarQ.o(a.b.j.AppCompatTextHelper_android_drawableBottom)) {
            this.f396e = c(context, jVarA, x0VarQ.l(a.b.j.AppCompatTextHelper_android_drawableBottom, 0));
        }
        if (x0VarQ.o(a.b.j.AppCompatTextHelper_android_drawableStart)) {
            this.f397f = c(context, jVarA, x0VarQ.l(a.b.j.AppCompatTextHelper_android_drawableStart, 0));
        }
        if (x0VarQ.o(a.b.j.AppCompatTextHelper_android_drawableEnd)) {
            this.g = c(context, jVarA, x0VarQ.l(a.b.j.AppCompatTextHelper_android_drawableEnd, 0));
        }
        x0VarQ.f390b.recycle();
        boolean z2 = this.f392a.getTransformationMethod() instanceof PasswordTransformationMethod;
        if (iL != -1) {
            x0 x0Var = new x0(context, context.obtainStyledAttributes(iL, a.b.j.TextAppearance));
            if (z2 || !x0Var.o(a.b.j.TextAppearance_textAllCaps)) {
                zA = false;
                z = false;
            } else {
                zA = x0Var.a(a.b.j.TextAppearance_textAllCaps, false);
                z = true;
            }
            h(context, x0Var);
            strM = x0Var.o(a.b.j.TextAppearance_textLocale) ? x0Var.m(a.b.j.TextAppearance_textLocale) : null;
            strM2 = x0Var.o(a.b.j.TextAppearance_fontVariationSettings) ? x0Var.m(a.b.j.TextAppearance_fontVariationSettings) : null;
            x0Var.f390b.recycle();
        } else {
            zA = false;
            z = false;
            strM = null;
            strM2 = null;
        }
        x0 x0Var2 = new x0(context, context.obtainStyledAttributes(attributeSet, a.b.j.TextAppearance, i, 0));
        if (!z2 && x0Var2.o(a.b.j.TextAppearance_textAllCaps)) {
            zA = x0Var2.a(a.b.j.TextAppearance_textAllCaps, false);
            z = true;
        }
        if (x0Var2.o(a.b.j.TextAppearance_textLocale)) {
            strM = x0Var2.m(a.b.j.TextAppearance_textLocale);
        }
        if (x0Var2.o(a.b.j.TextAppearance_fontVariationSettings)) {
            strM2 = x0Var2.m(a.b.j.TextAppearance_fontVariationSettings);
        }
        if (x0Var2.o(a.b.j.TextAppearance_android_textSize) && x0Var2.f(a.b.j.TextAppearance_android_textSize, -1) == 0) {
            this.f392a.setTextSize(0, 0.0f);
        }
        h(context, x0Var2);
        x0Var2.f390b.recycle();
        if (!z2 && z) {
            this.f392a.setAllCaps(zA);
        }
        Typeface typeface = this.l;
        if (typeface != null) {
            if (this.k == -1) {
                this.f392a.setTypeface(typeface, this.j);
            } else {
                this.f392a.setTypeface(typeface);
            }
        }
        if (strM2 != null) {
            this.f392a.setFontVariationSettings(strM2);
        }
        if (strM != null) {
            this.f392a.setTextLocales(LocaleList.forLanguageTags(strM));
        }
        a0 a0Var = this.i;
        TypedArray typedArrayObtainStyledAttributes = a0Var.i.obtainStyledAttributes(attributeSet, a.b.j.AppCompatTextView, i, 0);
        TextView textView2 = a0Var.h;
        textView2.saveAttributeDataForStyleable(textView2.getContext(), a.b.j.AppCompatTextView, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        if (typedArrayObtainStyledAttributes.hasValue(a.b.j.AppCompatTextView_autoSizeTextType)) {
            a0Var.f228a = typedArrayObtainStyledAttributes.getInt(a.b.j.AppCompatTextView_autoSizeTextType, 0);
        }
        float dimension = typedArrayObtainStyledAttributes.hasValue(a.b.j.AppCompatTextView_autoSizeStepGranularity) ? typedArrayObtainStyledAttributes.getDimension(a.b.j.AppCompatTextView_autoSizeStepGranularity, -1.0f) : -1.0f;
        float dimension2 = typedArrayObtainStyledAttributes.hasValue(a.b.j.AppCompatTextView_autoSizeMinTextSize) ? typedArrayObtainStyledAttributes.getDimension(a.b.j.AppCompatTextView_autoSizeMinTextSize, -1.0f) : -1.0f;
        float dimension3 = typedArrayObtainStyledAttributes.hasValue(a.b.j.AppCompatTextView_autoSizeMaxTextSize) ? typedArrayObtainStyledAttributes.getDimension(a.b.j.AppCompatTextView_autoSizeMaxTextSize, -1.0f) : -1.0f;
        if (typedArrayObtainStyledAttributes.hasValue(a.b.j.AppCompatTextView_autoSizePresetSizes) && (resourceId2 = typedArrayObtainStyledAttributes.getResourceId(a.b.j.AppCompatTextView_autoSizePresetSizes, 0)) > 0) {
            TypedArray typedArrayObtainTypedArray = typedArrayObtainStyledAttributes.getResources().obtainTypedArray(resourceId2);
            int length = typedArrayObtainTypedArray.length();
            int[] iArr = new int[length];
            if (length > 0) {
                for (int i2 = 0; i2 < length; i2++) {
                    iArr[i2] = typedArrayObtainTypedArray.getDimensionPixelSize(i2, -1);
                }
                a0Var.f233f = a0Var.a(iArr);
                a0Var.c();
            }
            typedArrayObtainTypedArray.recycle();
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!a0Var.d()) {
            a0Var.f228a = 0;
        } else if (a0Var.f228a == 1) {
            if (!a0Var.g) {
                DisplayMetrics displayMetrics = a0Var.i.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                a0Var.e(dimension2, dimension3, dimension);
            }
            a0Var.b();
        }
        a0 a0Var2 = this.i;
        if (a0Var2.f228a != 0) {
            int[] iArr2 = a0Var2.f233f;
            if (iArr2.length > 0) {
                if (this.f392a.getAutoSizeStepGranularity() != -1.0f) {
                    this.f392a.setAutoSizeTextTypeUniformWithConfiguration(Math.round(this.i.f231d), Math.round(this.i.f232e), Math.round(this.i.f230c), 0);
                } else {
                    this.f392a.setAutoSizeTextTypeUniformWithPresetSizes(iArr2, 0);
                }
            }
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, a.b.j.AppCompatTextView);
        int resourceId3 = typedArrayObtainStyledAttributes2.getResourceId(a.b.j.AppCompatTextView_drawableLeftCompat, -1);
        Drawable drawableB = resourceId3 != -1 ? jVarA.b(context, resourceId3) : null;
        int resourceId4 = typedArrayObtainStyledAttributes2.getResourceId(a.b.j.AppCompatTextView_drawableTopCompat, -1);
        Drawable drawableB2 = resourceId4 != -1 ? jVarA.b(context, resourceId4) : null;
        int resourceId5 = typedArrayObtainStyledAttributes2.getResourceId(a.b.j.AppCompatTextView_drawableRightCompat, -1);
        Drawable drawableB3 = resourceId5 != -1 ? jVarA.b(context, resourceId5) : null;
        int resourceId6 = typedArrayObtainStyledAttributes2.getResourceId(a.b.j.AppCompatTextView_drawableBottomCompat, -1);
        Drawable drawableB4 = resourceId6 != -1 ? jVarA.b(context, resourceId6) : null;
        int resourceId7 = typedArrayObtainStyledAttributes2.getResourceId(a.b.j.AppCompatTextView_drawableStartCompat, -1);
        Drawable drawableB5 = resourceId7 != -1 ? jVarA.b(context, resourceId7) : null;
        int resourceId8 = typedArrayObtainStyledAttributes2.getResourceId(a.b.j.AppCompatTextView_drawableEndCompat, -1);
        Drawable drawableB6 = resourceId8 != -1 ? jVarA.b(context, resourceId8) : null;
        if (drawableB5 != null || drawableB6 != null) {
            Drawable[] compoundDrawablesRelative = this.f392a.getCompoundDrawablesRelative();
            TextView textView3 = this.f392a;
            if (drawableB5 == null) {
                drawableB5 = compoundDrawablesRelative[0];
            }
            if (drawableB2 == null) {
                drawableB2 = compoundDrawablesRelative[1];
            }
            if (drawableB6 == null) {
                drawableB6 = compoundDrawablesRelative[2];
            }
            if (drawableB4 == null) {
                drawableB4 = compoundDrawablesRelative[3];
            }
            textView3.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableB5, drawableB2, drawableB6, drawableB4);
        } else if (drawableB != null || drawableB2 != null || drawableB3 != null || drawableB4 != null) {
            Drawable[] compoundDrawablesRelative2 = this.f392a.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative2[0] == null && compoundDrawablesRelative2[2] == null) {
                Drawable[] compoundDrawables = this.f392a.getCompoundDrawables();
                TextView textView4 = this.f392a;
                if (drawableB == null) {
                    drawableB = compoundDrawables[0];
                }
                if (drawableB2 == null) {
                    drawableB2 = compoundDrawables[1];
                }
                if (drawableB3 == null) {
                    drawableB3 = compoundDrawables[2];
                }
                if (drawableB4 == null) {
                    drawableB4 = compoundDrawables[3];
                }
                textView4.setCompoundDrawablesWithIntrinsicBounds(drawableB, drawableB2, drawableB3, drawableB4);
            } else {
                TextView textView5 = this.f392a;
                Drawable drawable = compoundDrawablesRelative2[0];
                if (drawableB2 == null) {
                    drawableB2 = compoundDrawablesRelative2[1];
                }
                Drawable drawable2 = compoundDrawablesRelative2[2];
                if (drawableB4 == null) {
                    drawableB4 = compoundDrawablesRelative2[3];
                }
                textView5.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawableB2, drawable2, drawableB4);
            }
        }
        if (typedArrayObtainStyledAttributes2.hasValue(a.b.j.AppCompatTextView_drawableTint)) {
            int i3 = a.b.j.AppCompatTextView_drawableTint;
            if (!typedArrayObtainStyledAttributes2.hasValue(i3) || (resourceId = typedArrayObtainStyledAttributes2.getResourceId(i3, 0)) == 0 || (colorStateList = a.b.l.a.a.a(context, resourceId)) == null) {
                colorStateList = typedArrayObtainStyledAttributes2.getColorStateList(i3);
            }
            TextView textView6 = this.f392a;
            if (textView6 == null) {
                throw null;
            }
            textView6.setCompoundDrawableTintList(colorStateList);
        }
        if (typedArrayObtainStyledAttributes2.hasValue(a.b.j.AppCompatTextView_drawableTintMode)) {
            PorterDuff.Mode modeC = e0.c(typedArrayObtainStyledAttributes2.getInt(a.b.j.AppCompatTextView_drawableTintMode, -1), null);
            TextView textView7 = this.f392a;
            if (textView7 == null) {
                throw null;
            }
            textView7.setCompoundDrawableTintMode(modeC);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes2.getDimensionPixelSize(a.b.j.AppCompatTextView_firstBaselineToTopHeight, -1);
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes2.getDimensionPixelSize(a.b.j.AppCompatTextView_lastBaselineToBottomHeight, -1);
        int dimensionPixelSize3 = typedArrayObtainStyledAttributes2.getDimensionPixelSize(a.b.j.AppCompatTextView_lineHeight, -1);
        typedArrayObtainStyledAttributes2.recycle();
        if (dimensionPixelSize != -1) {
            TextView textView8 = this.f392a;
            l.i.g(dimensionPixelSize);
            textView8.setFirstBaselineToTopHeight(dimensionPixelSize);
        }
        if (dimensionPixelSize2 != -1) {
            l.i.o0(this.f392a, dimensionPixelSize2);
        }
        if (dimensionPixelSize3 != -1) {
            l.i.p0(this.f392a, dimensionPixelSize3);
        }
    }

    public void e(Context context, int i) {
        String strM;
        x0 x0Var = new x0(context, context.obtainStyledAttributes(i, a.b.j.TextAppearance));
        if (x0Var.o(a.b.j.TextAppearance_textAllCaps)) {
            this.f392a.setAllCaps(x0Var.a(a.b.j.TextAppearance_textAllCaps, false));
        }
        if (x0Var.o(a.b.j.TextAppearance_android_textSize) && x0Var.f(a.b.j.TextAppearance_android_textSize, -1) == 0) {
            this.f392a.setTextSize(0, 0.0f);
        }
        h(context, x0Var);
        if (x0Var.o(a.b.j.TextAppearance_fontVariationSettings) && (strM = x0Var.m(a.b.j.TextAppearance_fontVariationSettings)) != null) {
            this.f392a.setFontVariationSettings(strM);
        }
        x0Var.f390b.recycle();
        Typeface typeface = this.l;
        if (typeface != null) {
            this.f392a.setTypeface(typeface, this.j);
        }
    }

    public void f(ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new v0();
        }
        v0 v0Var = this.h;
        v0Var.f366a = colorStateList;
        v0Var.f369d = colorStateList != null;
        v0 v0Var2 = this.h;
        this.f393b = v0Var2;
        this.f394c = v0Var2;
        this.f395d = v0Var2;
        this.f396e = v0Var2;
        this.f397f = v0Var2;
        this.g = v0Var2;
    }

    public void g(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new v0();
        }
        v0 v0Var = this.h;
        v0Var.f367b = mode;
        v0Var.f368c = mode != null;
        v0 v0Var2 = this.h;
        this.f393b = v0Var2;
        this.f394c = v0Var2;
        this.f395d = v0Var2;
        this.f396e = v0Var2;
        this.f397f = v0Var2;
        this.g = v0Var2;
    }

    public final void h(Context context, x0 x0Var) {
        String strM;
        Typeface typefaceCreate;
        Typeface typeface;
        this.j = x0Var.j(a.b.j.TextAppearance_android_textStyle, this.j);
        int iJ = x0Var.j(a.b.j.TextAppearance_android_textFontWeight, -1);
        this.k = iJ;
        if (iJ != -1) {
            this.j = (this.j & 2) | 0;
        }
        if (!x0Var.o(a.b.j.TextAppearance_android_fontFamily) && !x0Var.o(a.b.j.TextAppearance_fontFamily)) {
            if (x0Var.o(a.b.j.TextAppearance_android_typeface)) {
                this.m = false;
                int iJ2 = x0Var.j(a.b.j.TextAppearance_android_typeface, 1);
                if (iJ2 == 1) {
                    typeface = Typeface.SANS_SERIF;
                } else if (iJ2 == 2) {
                    typeface = Typeface.SERIF;
                } else if (iJ2 != 3) {
                    return;
                } else {
                    typeface = Typeface.MONOSPACE;
                }
                this.l = typeface;
                return;
            }
            return;
        }
        this.l = null;
        int i = x0Var.o(a.b.j.TextAppearance_fontFamily) ? a.b.j.TextAppearance_fontFamily : a.b.j.TextAppearance_android_fontFamily;
        int i2 = this.k;
        int i3 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceI = x0Var.i(i, this.j, new a(i2, i3, new WeakReference(this.f392a)));
                if (typefaceI != null) {
                    if (this.k != -1) {
                        typefaceI = Typeface.create(Typeface.create(typefaceI, 0), this.k, (this.j & 2) != 0);
                    }
                    this.l = typefaceI;
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (strM = x0Var.m(i)) == null) {
            return;
        }
        if (this.k != -1) {
            typefaceCreate = Typeface.create(Typeface.create(strM, 0), this.k, (this.j & 2) != 0);
        } else {
            typefaceCreate = Typeface.create(strM, this.j);
        }
        this.l = typefaceCreate;
    }
}
