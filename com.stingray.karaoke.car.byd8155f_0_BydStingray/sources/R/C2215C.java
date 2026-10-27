package r;

import F1.AbstractC0260t;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import g1.AbstractC1274a;
import java.lang.ref.WeakReference;
import n.AbstractC1850a;

/* JADX INFO: renamed from: r.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2215C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f21418a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c9.m f21419b;
    public c9.m c;
    public c9.m d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c9.m f21420e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c9.m f21421f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c9.m f21422g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c9.m f21423h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C2222J f21424i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f21425j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f21426k = -1;
    public Typeface l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f21427m;

    public C2215C(TextView textView) {
        this.f21418a = textView;
        this.f21424i = new C2222J(textView);
    }

    public static c9.m c(Context context, C2258q c2258q, int i7) {
        ColorStateList colorStateListF;
        synchronized (c2258q) {
            colorStateListF = c2258q.f21547a.f(context, i7);
        }
        if (colorStateListF == null) {
            return null;
        }
        c9.m mVar = new c9.m();
        mVar.f12588b = true;
        mVar.c = colorStateListF;
        return mVar;
    }

    public final void a(Drawable drawable, c9.m mVar) {
        if (drawable == null || mVar == null) {
            return;
        }
        C2258q.d(drawable, mVar, this.f21418a.getDrawableState());
    }

    public final void b() {
        c9.m mVar = this.f21419b;
        TextView textView = this.f21418a;
        if (mVar != null || this.c != null || this.d != null || this.f21420e != null) {
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            a(compoundDrawables[0], this.f21419b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.f21420e);
        }
        if (this.f21421f == null && this.f21422g == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = textView.getCompoundDrawablesRelative();
        a(compoundDrawablesRelative[0], this.f21421f);
        a(compoundDrawablesRelative[2], this.f21422g);
    }

    public final void d(AttributeSet attributeSet, int i7) {
        C2258q c2258q;
        boolean z6;
        boolean z10;
        String string;
        String string2;
        boolean z11;
        int i10;
        int i11;
        int i12;
        float dimensionPixelSize;
        ColorStateList colorStateList;
        int resourceId;
        int i13;
        int resourceId2;
        int i14;
        TextView textView = this.f21418a;
        Context context = textView.getContext();
        PorterDuff.Mode mode = C2258q.f21546b;
        synchronized (C2258q.class) {
            try {
                if (C2258q.c == null) {
                    C2258q.c();
                }
                c2258q = C2258q.c;
            } catch (Throwable th) {
                throw th;
            }
        }
        int[] iArr = AbstractC1850a.f19355f;
        h3.y yVarF = h3.y.F(context, attributeSet, iArr, i7);
        TextView textView2 = this.f21418a;
        Context context2 = textView2.getContext();
        int[] iArr2 = F1.H.f2303a;
        F1.E.b(textView2, context2, iArr, attributeSet, (TypedArray) yVarF.f15500n, i7, 0);
        TypedArray typedArray = (TypedArray) yVarF.f15500n;
        int resourceId3 = typedArray.getResourceId(0, -1);
        if (typedArray.hasValue(3)) {
            this.f21419b = c(context, c2258q, typedArray.getResourceId(3, 0));
        }
        if (typedArray.hasValue(1)) {
            this.c = c(context, c2258q, typedArray.getResourceId(1, 0));
        }
        if (typedArray.hasValue(4)) {
            this.d = c(context, c2258q, typedArray.getResourceId(4, 0));
        }
        if (typedArray.hasValue(2)) {
            this.f21420e = c(context, c2258q, typedArray.getResourceId(2, 0));
        }
        if (typedArray.hasValue(5)) {
            this.f21421f = c(context, c2258q, typedArray.getResourceId(5, 0));
        }
        if (typedArray.hasValue(6)) {
            this.f21422g = c(context, c2258q, typedArray.getResourceId(6, 0));
        }
        yVarF.G();
        boolean z12 = textView.getTransformationMethod() instanceof PasswordTransformationMethod;
        int[] iArr3 = AbstractC1850a.f19365r;
        if (resourceId3 != -1) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(resourceId3, iArr3);
            h3.y yVar = new h3.y(context, typedArrayObtainStyledAttributes);
            if (z12 || !typedArrayObtainStyledAttributes.hasValue(14)) {
                z6 = false;
                z10 = false;
            } else {
                z10 = typedArrayObtainStyledAttributes.getBoolean(14, false);
                z6 = true;
            }
            h(context, yVar);
            if (typedArrayObtainStyledAttributes.hasValue(15)) {
                string2 = typedArrayObtainStyledAttributes.getString(15);
                i14 = 13;
            } else {
                i14 = 13;
                string2 = null;
            }
            string = typedArrayObtainStyledAttributes.hasValue(i14) ? typedArrayObtainStyledAttributes.getString(i14) : null;
            yVar.G();
        } else {
            z6 = false;
            z10 = false;
            string = null;
            string2 = null;
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr3, i7, 0);
        h3.y yVar2 = new h3.y(context, typedArrayObtainStyledAttributes2);
        if (z12 || !typedArrayObtainStyledAttributes2.hasValue(14)) {
            z11 = z10;
        } else {
            z11 = typedArrayObtainStyledAttributes2.getBoolean(14, false);
            z6 = true;
        }
        if (typedArrayObtainStyledAttributes2.hasValue(15)) {
            string2 = typedArrayObtainStyledAttributes2.getString(15);
        }
        if (typedArrayObtainStyledAttributes2.hasValue(13)) {
            string = typedArrayObtainStyledAttributes2.getString(13);
        }
        if (typedArrayObtainStyledAttributes2.hasValue(0) && typedArrayObtainStyledAttributes2.getDimensionPixelSize(0, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        h(context, yVar2);
        yVar2.G();
        if (!z12 && z6) {
            this.f21418a.setAllCaps(z11);
        }
        Typeface typeface = this.l;
        if (typeface != null) {
            if (this.f21426k == -1) {
                textView.setTypeface(typeface, this.f21425j);
            } else {
                textView.setTypeface(typeface);
            }
        }
        if (string != null) {
            AbstractC2213A.d(textView, string);
        }
        if (string2 != null) {
            AbstractC2267z.b(textView, AbstractC2267z.a(string2));
        }
        int[] iArr4 = AbstractC1850a.f19356g;
        C2222J c2222j = this.f21424i;
        Context context3 = c2222j.f21439h;
        TypedArray typedArrayObtainStyledAttributes3 = context3.obtainStyledAttributes(attributeSet, iArr4, i7, 0);
        TextView textView3 = c2222j.f21438g;
        int i15 = -1;
        F1.E.b(textView3, textView3.getContext(), iArr4, attributeSet, typedArrayObtainStyledAttributes3, i7, 0);
        if (typedArrayObtainStyledAttributes3.hasValue(5)) {
            c2222j.f21434a = typedArrayObtainStyledAttributes3.getInt(5, 0);
        }
        float dimension = typedArrayObtainStyledAttributes3.hasValue(4) ? typedArrayObtainStyledAttributes3.getDimension(4, -1.0f) : -1.0f;
        float dimension2 = typedArrayObtainStyledAttributes3.hasValue(2) ? typedArrayObtainStyledAttributes3.getDimension(2, -1.0f) : -1.0f;
        float dimension3 = typedArrayObtainStyledAttributes3.hasValue(1) ? typedArrayObtainStyledAttributes3.getDimension(1, -1.0f) : -1.0f;
        if (typedArrayObtainStyledAttributes3.hasValue(3) && (resourceId2 = typedArrayObtainStyledAttributes3.getResourceId(3, 0)) > 0) {
            TypedArray typedArrayObtainTypedArray = typedArrayObtainStyledAttributes3.getResources().obtainTypedArray(resourceId2);
            int length = typedArrayObtainTypedArray.length();
            int[] iArr5 = new int[length];
            if (length > 0) {
                int i16 = 0;
                while (i16 < length) {
                    iArr5[i16] = typedArrayObtainTypedArray.getDimensionPixelSize(i16, i15);
                    i16++;
                    i15 = -1;
                }
                int[] iArrA = C2222J.a(iArr5);
                c2222j.f21436e = iArrA;
                boolean z13 = iArrA.length > 0;
                c2222j.f21437f = z13;
                if (z13) {
                    c2222j.f21434a = 1;
                    c2222j.c = iArrA[0];
                    c2222j.d = iArrA[r3 - 1];
                    c2222j.f21435b = -1.0f;
                }
            }
            typedArrayObtainTypedArray.recycle();
        }
        typedArrayObtainStyledAttributes3.recycle();
        if (!c2222j.b()) {
            c2222j.f21434a = 0;
        } else if (c2222j.f21434a == 1) {
            if (!c2222j.f21437f) {
                DisplayMetrics displayMetrics = context3.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    i13 = 2;
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                } else {
                    i13 = 2;
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(i13, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                if (dimension2 <= 0.0f) {
                    throw new IllegalArgumentException("Minimum auto-size text size (" + dimension2 + "px) is less or equal to (0px)");
                }
                if (dimension3 <= dimension2) {
                    throw new IllegalArgumentException("Maximum auto-size text size (" + dimension3 + "px) is less or equal to minimum auto-size text size (" + dimension2 + "px)");
                }
                if (dimension <= 0.0f) {
                    throw new IllegalArgumentException("The auto-size step granularity (" + dimension + "px) is less or equal to (0px)");
                }
                c2222j.f21434a = 1;
                c2222j.c = dimension2;
                c2222j.d = dimension3;
                c2222j.f21435b = dimension;
                c2222j.f21437f = false;
            }
            if (c2222j.b() && c2222j.f21434a == 1 && (!c2222j.f21437f || c2222j.f21436e.length == 0)) {
                int iFloor = ((int) Math.floor((c2222j.d - c2222j.c) / c2222j.f21435b)) + 1;
                int[] iArr6 = new int[iFloor];
                for (int i17 = 0; i17 < iFloor; i17++) {
                    iArr6[i17] = Math.round((i17 * c2222j.f21435b) + c2222j.c);
                }
                c2222j.f21436e = C2222J.a(iArr6);
            }
        }
        if (c2222j.f21434a != 0) {
            int[] iArr7 = c2222j.f21436e;
            if (iArr7.length > 0) {
                if (AbstractC2213A.a(textView) != -1.0f) {
                    AbstractC2213A.b(textView, Math.round(c2222j.c), Math.round(c2222j.d), Math.round(c2222j.f21435b), 0);
                } else {
                    AbstractC2213A.c(textView, iArr7, 0);
                }
            }
        }
        TypedArray typedArrayObtainStyledAttributes4 = context.obtainStyledAttributes(attributeSet, iArr4);
        int resourceId4 = typedArrayObtainStyledAttributes4.getResourceId(8, -1);
        Drawable drawableA = resourceId4 != -1 ? c2258q.a(context, resourceId4) : null;
        int resourceId5 = typedArrayObtainStyledAttributes4.getResourceId(13, -1);
        Drawable drawableA2 = resourceId5 != -1 ? c2258q.a(context, resourceId5) : null;
        int resourceId6 = typedArrayObtainStyledAttributes4.getResourceId(9, -1);
        Drawable drawableA3 = resourceId6 != -1 ? c2258q.a(context, resourceId6) : null;
        int resourceId7 = typedArrayObtainStyledAttributes4.getResourceId(6, -1);
        Drawable drawableA4 = resourceId7 != -1 ? c2258q.a(context, resourceId7) : null;
        int resourceId8 = typedArrayObtainStyledAttributes4.getResourceId(10, -1);
        Drawable drawableA5 = resourceId8 != -1 ? c2258q.a(context, resourceId8) : null;
        int resourceId9 = typedArrayObtainStyledAttributes4.getResourceId(7, -1);
        Drawable drawableA6 = resourceId9 != -1 ? c2258q.a(context, resourceId9) : null;
        if (drawableA5 != null || drawableA6 != null) {
            Drawable[] compoundDrawablesRelative = textView.getCompoundDrawablesRelative();
            if (drawableA5 == null) {
                drawableA5 = compoundDrawablesRelative[0];
            }
            if (drawableA2 == null) {
                drawableA2 = compoundDrawablesRelative[1];
            }
            if (drawableA6 == null) {
                drawableA6 = compoundDrawablesRelative[2];
            }
            if (drawableA4 == null) {
                drawableA4 = compoundDrawablesRelative[3];
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableA5, drawableA2, drawableA6, drawableA4);
        } else if (drawableA != null || drawableA2 != null || drawableA3 != null || drawableA4 != null) {
            Drawable[] compoundDrawablesRelative2 = textView.getCompoundDrawablesRelative();
            Drawable drawable = compoundDrawablesRelative2[0];
            if (drawable == null && compoundDrawablesRelative2[2] == null) {
                Drawable[] compoundDrawables = textView.getCompoundDrawables();
                if (drawableA == null) {
                    drawableA = compoundDrawables[0];
                }
                if (drawableA2 == null) {
                    drawableA2 = compoundDrawables[1];
                }
                if (drawableA3 == null) {
                    drawableA3 = compoundDrawables[2];
                }
                if (drawableA4 == null) {
                    drawableA4 = compoundDrawables[3];
                }
                textView.setCompoundDrawablesWithIntrinsicBounds(drawableA, drawableA2, drawableA3, drawableA4);
            } else {
                if (drawableA2 == null) {
                    drawableA2 = compoundDrawablesRelative2[1];
                }
                if (drawableA4 == null) {
                    drawableA4 = compoundDrawablesRelative2[3];
                }
                textView.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawableA2, compoundDrawablesRelative2[2], drawableA4);
            }
        }
        if (typedArrayObtainStyledAttributes4.hasValue(11)) {
            if (!typedArrayObtainStyledAttributes4.hasValue(11) || (resourceId = typedArrayObtainStyledAttributes4.getResourceId(11, 0)) == 0 || (colorStateList = h6.f.h(context, resourceId)) == null) {
                colorStateList = typedArrayObtainStyledAttributes4.getColorStateList(11);
            }
            textView.setCompoundDrawableTintList(colorStateList);
        }
        if (typedArrayObtainStyledAttributes4.hasValue(12)) {
            i10 = -1;
            textView.setCompoundDrawableTintMode(AbstractC2225M.b(typedArrayObtainStyledAttributes4.getInt(12, -1), null));
        } else {
            i10 = -1;
        }
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes4.getDimensionPixelSize(15, i10);
        int dimensionPixelSize3 = typedArrayObtainStyledAttributes4.getDimensionPixelSize(18, i10);
        if (typedArrayObtainStyledAttributes4.hasValue(19)) {
            TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes4.peekValue(19);
            if (typedValuePeekValue == null || typedValuePeekValue.type != 5) {
                i11 = -1;
                dimensionPixelSize = typedArrayObtainStyledAttributes4.getDimensionPixelSize(19, -1);
                i12 = -1;
            } else {
                int i18 = typedValuePeekValue.data;
                i12 = i18 & 15;
                dimensionPixelSize = TypedValue.complexToFloat(i18);
                i11 = -1;
            }
        } else {
            i11 = -1;
            i12 = -1;
            dimensionPixelSize = -1.0f;
        }
        typedArrayObtainStyledAttributes4.recycle();
        if (dimensionPixelSize2 != i11) {
            l9.l.D(dimensionPixelSize2);
            textView.setFirstBaselineToTopHeight(dimensionPixelSize2);
        }
        if (dimensionPixelSize3 != i11) {
            l9.l.D(dimensionPixelSize3);
            Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
            int i19 = textView.getIncludeFontPadding() ? fontMetricsInt.bottom : fontMetricsInt.descent;
            if (dimensionPixelSize3 > Math.abs(i19)) {
                textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), dimensionPixelSize3 - i19);
            }
        }
        if (dimensionPixelSize != -1.0f) {
            if (i12 == -1) {
                AbstractC1274a.a0(textView, (int) dimensionPixelSize);
            } else if (Build.VERSION.SDK_INT >= 34) {
                AbstractC0260t.h(textView, i12, dimensionPixelSize);
            } else {
                AbstractC1274a.a0(textView, Math.round(TypedValue.applyDimension(i12, dimensionPixelSize, textView.getResources().getDisplayMetrics())));
            }
        }
    }

    public final void e(Context context, int i7) {
        String string;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i7, AbstractC1850a.f19365r);
        h3.y yVar = new h3.y(context, typedArrayObtainStyledAttributes);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(14);
        TextView textView = this.f21418a;
        if (zHasValue) {
            textView.setAllCaps(typedArrayObtainStyledAttributes.getBoolean(14, false));
        }
        if (typedArrayObtainStyledAttributes.hasValue(0) && typedArrayObtainStyledAttributes.getDimensionPixelSize(0, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        h(context, yVar);
        if (typedArrayObtainStyledAttributes.hasValue(13) && (string = typedArrayObtainStyledAttributes.getString(13)) != null) {
            AbstractC2213A.d(textView, string);
        }
        yVar.G();
        Typeface typeface = this.l;
        if (typeface != null) {
            textView.setTypeface(typeface, this.f21425j);
        }
    }

    public final void f(ColorStateList colorStateList) {
        if (this.f21423h == null) {
            this.f21423h = new c9.m();
        }
        c9.m mVar = this.f21423h;
        mVar.c = colorStateList;
        mVar.f12588b = colorStateList != null;
        this.f21419b = mVar;
        this.c = mVar;
        this.d = mVar;
        this.f21420e = mVar;
        this.f21421f = mVar;
        this.f21422g = mVar;
    }

    public final void g(PorterDuff.Mode mode) {
        if (this.f21423h == null) {
            this.f21423h = new c9.m();
        }
        c9.m mVar = this.f21423h;
        mVar.d = mode;
        mVar.f12587a = mode != null;
        this.f21419b = mVar;
        this.c = mVar;
        this.d = mVar;
        this.f21420e = mVar;
        this.f21421f = mVar;
        this.f21422g = mVar;
    }

    public final void h(Context context, h3.y yVar) {
        String string;
        int i7 = this.f21425j;
        TypedArray typedArray = (TypedArray) yVar.f15500n;
        this.f21425j = typedArray.getInt(2, i7);
        int i10 = typedArray.getInt(11, -1);
        this.f21426k = i10;
        if (i10 != -1) {
            this.f21425j &= 2;
        }
        if (!typedArray.hasValue(10) && !typedArray.hasValue(12)) {
            if (typedArray.hasValue(1)) {
                this.f21427m = false;
                int i11 = typedArray.getInt(1, 1);
                if (i11 == 1) {
                    this.l = Typeface.SANS_SERIF;
                    return;
                } else if (i11 == 2) {
                    this.l = Typeface.SERIF;
                    return;
                } else {
                    if (i11 != 3) {
                        return;
                    }
                    this.l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.l = null;
        int i12 = typedArray.hasValue(12) ? 12 : 10;
        int i13 = this.f21426k;
        int i14 = this.f21425j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceT = yVar.t(i12, this.f21425j, new C2266y(this, i13, i14, new WeakReference(this.f21418a)));
                if (typefaceT != null) {
                    if (this.f21426k != -1) {
                        this.l = AbstractC2214B.a(Typeface.create(typefaceT, 0), this.f21426k, (this.f21425j & 2) != 0);
                    } else {
                        this.l = typefaceT;
                    }
                }
                this.f21427m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (string = typedArray.getString(i12)) == null) {
            return;
        }
        if (this.f21426k != -1) {
            this.l = AbstractC2214B.a(Typeface.create(string, 0), this.f21426k, (this.f21425j & 2) != 0);
        } else {
            this.l = Typeface.create(string, this.f21425j);
        }
    }
}
