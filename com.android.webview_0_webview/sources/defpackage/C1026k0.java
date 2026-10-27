package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: renamed from: k0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1026k0 {
    public final TextView a;
    public J0 b;
    public J0 c;
    public J0 d;
    public J0 e;
    public J0 f;
    public J0 g;
    public final C1138m0 h;
    public int i = 0;
    public Typeface j;
    public boolean k;

    public C1026k0(TextView textView) {
        this.a = textView;
        this.h = new C1138m0(this.a);
    }

    public static J0 c(Context context, C0636d0 c0636d0, int i) {
        ColorStateList colorStateListL = c0636d0.l(context, i);
        if (colorStateListL == null) {
            return null;
        }
        J0 j0 = new J0();
        j0.d = true;
        j0.a = colorStateListL;
        return j0;
    }

    public final void a(Drawable drawable, J0 j0) {
        if (drawable == null || j0 == null) {
            return;
        }
        C0636d0.p(drawable, j0, this.a.getDrawableState());
    }

    public void b() {
        if (this.b != null || this.c != null || this.d != null || this.e != null) {
            Drawable[] compoundDrawables = this.a.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.e);
        }
        if (this.f == null && this.g == null) {
            return;
        }
        Drawable[] compoundDrawablesRelative = this.a.getCompoundDrawablesRelative();
        a(compoundDrawablesRelative[0], this.f);
        a(compoundDrawablesRelative[2], this.g);
    }

    public boolean d() {
        C1138m0 c1138m0 = this.h;
        return c1138m0.i() && c1138m0.a != 0;
    }

    public void e(AttributeSet attributeSet, int i) {
        ColorStateList colorStateListC;
        ColorStateList colorStateListC2;
        boolean zA;
        boolean z;
        int i2;
        int resourceId;
        ColorStateList colorStateListC3;
        int[] iArr = AbstractC0678dn.q;
        int[] iArr2 = AbstractC0678dn.C0;
        int i3 = Build.VERSION.SDK_INT;
        Context context = this.a.getContext();
        C0636d0 c0636d0G = C0636d0.g();
        L0 l0L = L0.l(context, attributeSet, AbstractC0678dn.p, i, 0);
        int i4 = l0L.i(0, -1);
        if (l0L.k(3)) {
            this.b = c(context, c0636d0G, l0L.i(3, 0));
        }
        if (l0L.k(1)) {
            this.c = c(context, c0636d0G, l0L.i(1, 0));
        }
        if (l0L.k(4)) {
            this.d = c(context, c0636d0G, l0L.i(4, 0));
        }
        if (l0L.k(2)) {
            this.e = c(context, c0636d0G, l0L.i(2, 0));
        }
        if (l0L.k(5)) {
            this.f = c(context, c0636d0G, l0L.i(5, 0));
        }
        if (l0L.k(6)) {
            this.g = c(context, c0636d0G, l0L.i(6, 0));
        }
        l0L.b.recycle();
        boolean z2 = this.a.getTransformationMethod() instanceof PasswordTransformationMethod;
        ColorStateList colorStateListC4 = null;
        if (i4 != -1) {
            L0 l0 = new L0(context, context.obtainStyledAttributes(i4, iArr2));
            if (z2 || !l0.k(1)) {
                zA = false;
                z = false;
            } else {
                zA = l0.a(1, false);
                z = true;
            }
            j(context, l0);
            if (i3 < 23) {
                colorStateListC3 = l0.k(5) ? l0.c(5) : null;
                colorStateListC2 = l0.k(6) ? l0.c(6) : null;
                if (l0.k(7)) {
                    colorStateListC4 = l0.c(7);
                }
            } else {
                colorStateListC3 = null;
                colorStateListC2 = null;
            }
            l0.b.recycle();
            colorStateListC = colorStateListC4;
            colorStateListC4 = colorStateListC3;
        } else {
            colorStateListC = null;
            colorStateListC2 = null;
            zA = false;
            z = false;
        }
        L0 l02 = new L0(context, context.obtainStyledAttributes(attributeSet, iArr2, i, 0));
        if (!z2 && l02.k(1)) {
            zA = l02.a(1, false);
            z = true;
        }
        if (i3 < 23) {
            if (l02.k(5)) {
                colorStateListC4 = l02.c(5);
            }
            if (l02.k(6)) {
                colorStateListC2 = l02.c(6);
            }
            if (l02.k(7)) {
                colorStateListC = l02.c(7);
            }
        }
        ColorStateList colorStateList = colorStateListC4;
        ColorStateList colorStateList2 = colorStateListC2;
        if (i3 >= 28 && l02.k(2) && l02.e(2, -1) == 0) {
            this.a.setTextSize(0, 0.0f);
        }
        j(context, l02);
        l02.b.recycle();
        if (colorStateList != null) {
            this.a.setTextColor(colorStateList);
        }
        if (colorStateList2 != null) {
            this.a.setHintTextColor(colorStateList2);
        }
        if (colorStateListC != null) {
            this.a.setLinkTextColor(colorStateListC);
        }
        if (!z2 && z) {
            this.a.setAllCaps(zA);
        }
        Typeface typeface = this.j;
        if (typeface != null) {
            this.a.setTypeface(typeface, this.i);
        }
        C1138m0 c1138m0 = this.h;
        TypedArray typedArrayObtainStyledAttributes = c1138m0.j.obtainStyledAttributes(attributeSet, iArr, i, 0);
        if (typedArrayObtainStyledAttributes.hasValue(4)) {
            c1138m0.a = typedArrayObtainStyledAttributes.getInt(4, 0);
        }
        float dimension = typedArrayObtainStyledAttributes.hasValue(3) ? typedArrayObtainStyledAttributes.getDimension(3, -1.0f) : -1.0f;
        float dimension2 = typedArrayObtainStyledAttributes.hasValue(1) ? typedArrayObtainStyledAttributes.getDimension(1, -1.0f) : -1.0f;
        float dimension3 = typedArrayObtainStyledAttributes.hasValue(0) ? typedArrayObtainStyledAttributes.getDimension(0, -1.0f) : -1.0f;
        if (typedArrayObtainStyledAttributes.hasValue(2) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(2, 0)) > 0) {
            TypedArray typedArrayObtainTypedArray = typedArrayObtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            int length = typedArrayObtainTypedArray.length();
            int[] iArr3 = new int[length];
            if (length > 0) {
                for (int i5 = 0; i5 < length; i5++) {
                    iArr3[i5] = typedArrayObtainTypedArray.getDimensionPixelSize(i5, -1);
                }
                c1138m0.f = c1138m0.b(iArr3);
                c1138m0.h();
            }
            typedArrayObtainTypedArray.recycle();
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!c1138m0.i()) {
            c1138m0.a = 0;
        } else if (c1138m0.a == 1) {
            if (!c1138m0.g) {
                DisplayMetrics displayMetrics = c1138m0.j.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    i2 = 2;
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                } else {
                    i2 = 2;
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(i2, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                c1138m0.j(dimension2, dimension3, dimension);
            }
            c1138m0.g();
        }
        if (InterfaceC1635v3.a) {
            C1138m0 c1138m02 = this.h;
            if (c1138m02.a != 0) {
                int[] iArr4 = c1138m02.f;
                if (iArr4.length > 0) {
                    if (this.a.getAutoSizeStepGranularity() != -1.0f) {
                        this.a.setAutoSizeTextTypeUniformWithConfiguration(Math.round(this.h.d), Math.round(this.h.e), Math.round(this.h.c), 0);
                    } else {
                        this.a.setAutoSizeTextTypeUniformWithPresetSizes(iArr4, 0);
                    }
                }
            }
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr);
        int dimensionPixelSize = typedArrayObtainStyledAttributes2.getDimensionPixelSize(5, -1);
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes2.getDimensionPixelSize(7, -1);
        int dimensionPixelSize3 = typedArrayObtainStyledAttributes2.getDimensionPixelSize(8, -1);
        typedArrayObtainStyledAttributes2.recycle();
        if (dimensionPixelSize != -1) {
            AbstractC1855z3.a(this.a, dimensionPixelSize);
        }
        if (dimensionPixelSize2 != -1) {
            AbstractC1855z3.b(this.a, dimensionPixelSize2);
        }
        if (dimensionPixelSize3 != -1) {
            AbstractC1855z3.c(this.a, dimensionPixelSize3);
        }
    }

    public void f(Context context, int i) {
        ColorStateList colorStateListC;
        L0 l0 = new L0(context, context.obtainStyledAttributes(i, AbstractC0678dn.C0));
        if (l0.k(1)) {
            this.a.setAllCaps(l0.a(1, false));
        }
        if (Build.VERSION.SDK_INT < 23 && l0.k(5) && (colorStateListC = l0.c(5)) != null) {
            this.a.setTextColor(colorStateListC);
        }
        if (l0.k(2) && l0.e(2, -1) == 0) {
            this.a.setTextSize(0, 0.0f);
        }
        j(context, l0);
        l0.b.recycle();
        Typeface typeface = this.j;
        if (typeface != null) {
            this.a.setTypeface(typeface, this.i);
        }
    }

    public void g(int i, int i2, int i3, int i4) {
        C1138m0 c1138m0 = this.h;
        if (c1138m0.i()) {
            DisplayMetrics displayMetrics = c1138m0.j.getResources().getDisplayMetrics();
            c1138m0.j(TypedValue.applyDimension(i4, i, displayMetrics), TypedValue.applyDimension(i4, i2, displayMetrics), TypedValue.applyDimension(i4, i3, displayMetrics));
            if (c1138m0.g()) {
                c1138m0.a();
            }
        }
    }

    public void h(int[] iArr, int i) {
        C1138m0 c1138m0 = this.h;
        if (c1138m0.i()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArrCopyOf = new int[length];
                if (i == 0) {
                    iArrCopyOf = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = c1138m0.j.getResources().getDisplayMetrics();
                    for (int i2 = 0; i2 < length; i2++) {
                        iArrCopyOf[i2] = Math.round(TypedValue.applyDimension(i, iArr[i2], displayMetrics));
                    }
                }
                c1138m0.f = c1138m0.b(iArrCopyOf);
                if (!c1138m0.h()) {
                    StringBuilder sbF = Z7.f("None of the preset sizes is valid: ");
                    sbF.append(Arrays.toString(iArr));
                    throw new IllegalArgumentException(sbF.toString());
                }
            } else {
                c1138m0.g = false;
            }
            if (c1138m0.g()) {
                c1138m0.a();
            }
        }
    }

    public void i(int i) {
        C1138m0 c1138m0 = this.h;
        if (c1138m0.i()) {
            if (i == 0) {
                c1138m0.a = 0;
                c1138m0.d = -1.0f;
                c1138m0.e = -1.0f;
                c1138m0.c = -1.0f;
                c1138m0.f = new int[0];
                c1138m0.b = false;
                return;
            }
            if (i != 1) {
                throw new IllegalArgumentException(Z7.a("Unknown auto-size text type: ", i));
            }
            DisplayMetrics displayMetrics = c1138m0.j.getResources().getDisplayMetrics();
            c1138m0.j(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (c1138m0.g()) {
                c1138m0.a();
            }
        }
    }

    public final void j(Context context, L0 l0) {
        String string;
        this.i = l0.h(4, this.i);
        if (l0.k(12) || l0.k(0)) {
            this.j = null;
            int i = l0.k(0) ? 0 : 12;
            if (!context.isRestricted()) {
                try {
                    Typeface typefaceG = l0.g(i, this.i, new C0970j0(this, new WeakReference(this.a)));
                    this.j = typefaceG;
                    this.k = typefaceG == null;
                } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
                }
            }
            if (this.j != null || (string = l0.b.getString(i)) == null) {
                return;
            }
            this.j = Typeface.create(string, this.i);
            return;
        }
        if (l0.k(3)) {
            this.k = false;
            int iH = l0.h(3, 1);
            if (iH == 1) {
                this.j = Typeface.SANS_SERIF;
            } else if (iH == 2) {
                this.j = Typeface.SERIF;
            } else {
                if (iH != 3) {
                    return;
                }
                this.j = Typeface.MONOSPACE;
            }
        }
    }
}
