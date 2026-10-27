package p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class ca {
    public final TextView a;
    public xl2 b;
    public xl2 c;
    public xl2 d;
    public xl2 e;
    public xl2 f;
    public xl2 g;
    public xl2 h;
    private final ma i;
    public int j = 0;
    public int k = -1;
    public Typeface l;
    public boolean m;

    public ca(TextView textView) {
        this.a = textView;
        this.i = new ma(textView);
    }

    public static xl2 d(Context context, u8 u8Var, int i) {
        ColorStateList colorStateListH;
        synchronized (u8Var) {
            colorStateListH = u8Var.a.h(context, i);
        }
        if (colorStateListH == null) {
            return null;
        }
        xl2 xl2Var = new xl2(0);
        xl2Var.b = true;
        xl2Var.c = colorStateListH;
        return xl2Var;
    }

    public static void o(TextView textView, InputConnection inputConnection, EditorInfo editorInfo) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30 || inputConnection == null) {
            return;
        }
        CharSequence text = textView.getText();
        if (i >= 30) {
            ac0.a(editorInfo, text);
            return;
        }
        text.getClass();
        if (i >= 30) {
            ac0.a(editorInfo, text);
            return;
        }
        int i2 = editorInfo.initialSelStart;
        int i3 = editorInfo.initialSelEnd;
        int i4 = i2 > i3 ? i3 : i2;
        if (i2 <= i3) {
            i2 = i3;
        }
        int length = text.length();
        if (i4 < 0 || i2 > length) {
            p02.q(editorInfo, null, 0, 0);
            return;
        }
        int i5 = editorInfo.inputType & 4095;
        if (i5 == 129 || i5 == 225 || i5 == 18) {
            p02.q(editorInfo, null, 0, 0);
            return;
        }
        if (length <= 2048) {
            p02.q(editorInfo, text, i4, i2);
            return;
        }
        int i6 = i2 - i4;
        int i7 = i6 > 1024 ? 0 : i6;
        int i8 = 2048 - i7;
        int iMin = Math.min(text.length() - i2, i8 - Math.min(i4, (int) (((double) i8) * 0.8d)));
        int iMin2 = Math.min(i4, i8 - iMin);
        int i9 = i4 - iMin2;
        if (Character.isLowSurrogate(text.charAt(i9))) {
            i9++;
            iMin2--;
        }
        if (Character.isHighSurrogate(text.charAt((i2 + iMin) - 1))) {
            iMin--;
        }
        int i10 = iMin2 + i7;
        p02.q(editorInfo, i7 != i6 ? TextUtils.concat(text.subSequence(i9, i9 + iMin2), text.subSequence(i2, iMin + i2)) : text.subSequence(i9, i10 + iMin + i9), iMin2, i10);
    }

    public final void a(Drawable drawable, xl2 xl2Var) {
        if (drawable == null || xl2Var == null) {
            return;
        }
        u8.d(drawable, xl2Var, this.a.getDrawableState());
    }

    public final void b() {
        xl2 xl2Var = this.b;
        TextView textView = this.a;
        if (xl2Var != null || this.c != null || this.d != null || this.e != null) {
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.e);
        }
        if (this.f == null && this.g == null) {
            return;
        }
        Drawable[] drawableArrA = y9.a(textView);
        a(drawableArrA[0], this.f);
        a(drawableArrA[2], this.g);
    }

    public final void c() {
        this.i.a();
    }

    public final int e() {
        return Math.round(this.i.e);
    }

    public final int f() {
        return Math.round(this.i.d);
    }

    public final int g() {
        return Math.round(this.i.c);
    }

    public final int[] h() {
        return this.i.f;
    }

    public final int i() {
        return this.i.a;
    }

    public final ColorStateList j() {
        xl2 xl2Var = this.h;
        if (xl2Var != null) {
            return (ColorStateList) xl2Var.c;
        }
        return null;
    }

    public final PorterDuff.Mode k() {
        xl2 xl2Var = this.h;
        if (xl2Var != null) {
            return (PorterDuff.Mode) xl2Var.d;
        }
        return null;
    }

    public final boolean l() {
        ma maVar = this.i;
        return maVar.i() && maVar.a != 0;
    }

    public final void m(AttributeSet attributeSet, int i) {
        boolean zP;
        boolean z;
        String strZ;
        String strZ2;
        int i2;
        int i3;
        int resourceId;
        int i4;
        TextView textView = this.a;
        Context context = textView.getContext();
        u8 u8VarA = u8.a();
        int[] iArr = yz1.h;
        v4 v4VarJ = v4.J(context, attributeSet, iArr, i);
        zt2.j(textView, textView.getContext(), iArr, attributeSet, (TypedArray) v4VarJ.k, i);
        int iY = v4VarJ.y(0, -1);
        if (v4VarJ.G(3)) {
            this.b = d(context, u8VarA, v4VarJ.y(3, 0));
        }
        if (v4VarJ.G(1)) {
            this.c = d(context, u8VarA, v4VarJ.y(1, 0));
        }
        if (v4VarJ.G(4)) {
            this.d = d(context, u8VarA, v4VarJ.y(4, 0));
        }
        if (v4VarJ.G(2)) {
            this.e = d(context, u8VarA, v4VarJ.y(2, 0));
        }
        int i5 = Build.VERSION.SDK_INT;
        if (v4VarJ.G(5)) {
            this.f = d(context, u8VarA, v4VarJ.y(5, 0));
        }
        if (v4VarJ.G(6)) {
            this.g = d(context, u8VarA, v4VarJ.y(6, 0));
        }
        v4VarJ.L();
        boolean z2 = textView.getTransformationMethod() instanceof PasswordTransformationMethod;
        int[] iArr2 = yz1.w;
        if (iY != -1) {
            v4 v4Var = new v4(context, context.obtainStyledAttributes(iY, iArr2));
            if (z2 || !v4Var.G(14)) {
                zP = false;
                z = false;
            } else {
                zP = v4Var.p(14, false);
                z = true;
            }
            v(context, v4Var);
            if (v4Var.G(15)) {
                strZ = v4Var.z(15);
                i4 = 13;
            } else {
                i4 = 13;
                strZ = null;
            }
            strZ2 = v4Var.G(i4) ? v4Var.z(i4) : null;
            v4Var.L();
        } else {
            zP = false;
            z = false;
            strZ = null;
            strZ2 = null;
        }
        v4 v4Var2 = new v4(context, context.obtainStyledAttributes(attributeSet, iArr2, i, 0));
        if (!z2 && v4Var2.G(14)) {
            zP = v4Var2.p(14, false);
            z = true;
        }
        if (v4Var2.G(15)) {
            strZ = v4Var2.z(15);
        }
        if (v4Var2.G(13)) {
            strZ2 = v4Var2.z(13);
        }
        String str = strZ2;
        if (i5 >= 28 && v4Var2.G(0) && v4Var2.s(0, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        v(context, v4Var2);
        v4Var2.L();
        if (!z2 && z) {
            textView.setAllCaps(zP);
        }
        Typeface typeface = this.l;
        if (typeface != null) {
            if (this.k == -1) {
                textView.setTypeface(typeface, this.j);
            } else {
                textView.setTypeface(typeface);
            }
        }
        if (str != null) {
            aa.d(textView, str);
        }
        if (strZ != null) {
            z9.b(textView, z9.a(strZ));
        }
        ma maVar = this.i;
        int[] iArr3 = yz1.i;
        Context context2 = maVar.j;
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, iArr3, i, 0);
        TextView textView2 = maVar.i;
        zt2.j(textView2, textView2.getContext(), iArr3, attributeSet, typedArrayObtainStyledAttributes, i);
        if (typedArrayObtainStyledAttributes.hasValue(5)) {
            maVar.a = typedArrayObtainStyledAttributes.getInt(5, 0);
        }
        float dimension = typedArrayObtainStyledAttributes.hasValue(4) ? typedArrayObtainStyledAttributes.getDimension(4, -1.0f) : -1.0f;
        float dimension2 = typedArrayObtainStyledAttributes.hasValue(2) ? typedArrayObtainStyledAttributes.getDimension(2, -1.0f) : -1.0f;
        float dimension3 = typedArrayObtainStyledAttributes.hasValue(1) ? typedArrayObtainStyledAttributes.getDimension(1, -1.0f) : -1.0f;
        if (typedArrayObtainStyledAttributes.hasValue(3) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(3, 0)) > 0) {
            TypedArray typedArrayObtainTypedArray = typedArrayObtainStyledAttributes.getResources().obtainTypedArray(resourceId);
            int length = typedArrayObtainTypedArray.length();
            int[] iArr4 = new int[length];
            if (length > 0) {
                for (int i6 = 0; i6 < length; i6++) {
                    iArr4[i6] = typedArrayObtainTypedArray.getDimensionPixelSize(i6, -1);
                }
                maVar.f = ma.b(iArr4);
                maVar.h();
            }
            typedArrayObtainTypedArray.recycle();
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!maVar.i()) {
            maVar.a = 0;
        } else if (maVar.a == 1) {
            if (!maVar.g) {
                DisplayMetrics displayMetrics = context2.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    i3 = 2;
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                } else {
                    i3 = 2;
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(i3, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                maVar.j(dimension2, dimension3, dimension);
            }
            maVar.g();
        }
        if (ev2.b) {
            ma maVar2 = this.i;
            if (maVar2.a != 0) {
                int[] iArr5 = maVar2.f;
                if (iArr5.length > 0) {
                    if (aa.a(textView) != -1.0f) {
                        aa.b(textView, Math.round(this.i.d), Math.round(this.i.e), Math.round(this.i.c), 0);
                    } else {
                        aa.c(textView, iArr5, 0);
                    }
                }
            }
        }
        v4 v4Var3 = new v4(context, context.obtainStyledAttributes(attributeSet, iArr3));
        int iY2 = v4Var3.y(8, -1);
        Drawable drawableB = iY2 != -1 ? u8VarA.b(context, iY2) : null;
        int iY3 = v4Var3.y(13, -1);
        Drawable drawableB2 = iY3 != -1 ? u8VarA.b(context, iY3) : null;
        int iY4 = v4Var3.y(9, -1);
        Drawable drawableB3 = iY4 != -1 ? u8VarA.b(context, iY4) : null;
        int iY5 = v4Var3.y(6, -1);
        Drawable drawableB4 = iY5 != -1 ? u8VarA.b(context, iY5) : null;
        int iY6 = v4Var3.y(10, -1);
        Drawable drawableB5 = iY6 != -1 ? u8VarA.b(context, iY6) : null;
        int iY7 = v4Var3.y(7, -1);
        Drawable drawableB6 = iY7 != -1 ? u8VarA.b(context, iY7) : null;
        if (drawableB5 != null || drawableB6 != null) {
            Drawable[] drawableArrA = y9.a(textView);
            if (drawableB5 == null) {
                drawableB5 = drawableArrA[0];
            }
            if (drawableB2 == null) {
                drawableB2 = drawableArrA[1];
            }
            if (drawableB6 == null) {
                drawableB6 = drawableArrA[2];
            }
            if (drawableB4 == null) {
                drawableB4 = drawableArrA[3];
            }
            y9.b(textView, drawableB5, drawableB2, drawableB6, drawableB4);
        } else if (drawableB != null || drawableB2 != null || drawableB3 != null || drawableB4 != null) {
            Drawable[] drawableArrA2 = y9.a(textView);
            Drawable drawable = drawableArrA2[0];
            if (drawable == null && drawableArrA2[2] == null) {
                Drawable[] compoundDrawables = textView.getCompoundDrawables();
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
                textView.setCompoundDrawablesWithIntrinsicBounds(drawableB, drawableB2, drawableB3, drawableB4);
            } else {
                if (drawableB2 == null) {
                    drawableB2 = drawableArrA2[1];
                }
                Drawable drawable2 = drawableArrA2[2];
                if (drawableB4 == null) {
                    drawableB4 = drawableArrA2[3];
                }
                y9.b(textView, drawable, drawableB2, drawable2, drawableB4);
            }
        }
        if (v4Var3.G(11)) {
            ck2.f(textView, v4Var3.q(11));
        }
        if (v4Var3.G(12)) {
            i2 = -1;
            ck2.g(textView, cb0.b(v4Var3.w(12, -1), null));
        } else {
            i2 = -1;
        }
        int iS = v4Var3.s(15, i2);
        int iS2 = v4Var3.s(18, i2);
        int iS3 = v4Var3.s(19, i2);
        v4Var3.L();
        if (iS != i2) {
            f60.G(textView, iS);
        }
        if (iS2 != i2) {
            f60.H(textView, iS2);
        }
        if (iS3 != i2) {
            p02.d(iS3);
            if (iS3 != textView.getPaint().getFontMetricsInt(null)) {
                textView.setLineSpacing(iS3 - r1, 1.0f);
            }
        }
    }

    public final void n(Context context, int i) {
        String strZ;
        v4 v4Var = new v4(context, context.obtainStyledAttributes(i, yz1.w));
        boolean zG = v4Var.G(14);
        TextView textView = this.a;
        if (zG) {
            textView.setAllCaps(v4Var.p(14, false));
        }
        if (v4Var.G(0) && v4Var.s(0, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        v(context, v4Var);
        if (v4Var.G(13) && (strZ = v4Var.z(13)) != null) {
            aa.d(textView, strZ);
        }
        v4Var.L();
        Typeface typeface = this.l;
        if (typeface != null) {
            textView.setTypeface(typeface, this.j);
        }
    }

    public final void p(int i, int i2, int i3, int i4) {
        ma maVar = this.i;
        if (maVar.i()) {
            DisplayMetrics displayMetrics = maVar.j.getResources().getDisplayMetrics();
            maVar.j(TypedValue.applyDimension(i4, i, displayMetrics), TypedValue.applyDimension(i4, i2, displayMetrics), TypedValue.applyDimension(i4, i3, displayMetrics));
            if (maVar.g()) {
                maVar.a();
            }
        }
    }

    public final void q(int[] iArr, int i) {
        ma maVar = this.i;
        if (maVar.i()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArrCopyOf = new int[length];
                if (i == 0) {
                    iArrCopyOf = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = maVar.j.getResources().getDisplayMetrics();
                    for (int i2 = 0; i2 < length; i2++) {
                        iArrCopyOf[i2] = Math.round(TypedValue.applyDimension(i, iArr[i2], displayMetrics));
                    }
                }
                maVar.f = ma.b(iArrCopyOf);
                if (!maVar.h()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                maVar.g = false;
            }
            if (maVar.g()) {
                maVar.a();
            }
        }
    }

    public final void r(int i) {
        ma maVar = this.i;
        if (maVar.i()) {
            if (i == 0) {
                maVar.a = 0;
                maVar.d = -1.0f;
                maVar.e = -1.0f;
                maVar.c = -1.0f;
                maVar.f = new int[0];
                maVar.b = false;
                return;
            }
            if (i != 1) {
                throw new IllegalArgumentException(u4.e("Unknown auto-size text type: ", i));
            }
            DisplayMetrics displayMetrics = maVar.j.getResources().getDisplayMetrics();
            maVar.j(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (maVar.g()) {
                maVar.a();
            }
        }
    }

    public final void s(ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new xl2(0);
        }
        xl2 xl2Var = this.h;
        xl2Var.c = colorStateList;
        xl2Var.b = colorStateList != null;
        this.b = xl2Var;
        this.c = xl2Var;
        this.d = xl2Var;
        this.e = xl2Var;
        this.f = xl2Var;
        this.g = xl2Var;
    }

    public final void t(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new xl2(0);
        }
        xl2 xl2Var = this.h;
        xl2Var.d = mode;
        xl2Var.a = mode != null;
        this.b = xl2Var;
        this.c = xl2Var;
        this.d = xl2Var;
        this.e = xl2Var;
        this.f = xl2Var;
        this.g = xl2Var;
    }

    public final void u(int i, float f) {
        if (ev2.b) {
            return;
        }
        ma maVar = this.i;
        if (!maVar.i() || maVar.a == 0) {
            this.i.f(f, i);
        }
    }

    public final void v(Context context, v4 v4Var) {
        String strZ;
        this.j = v4Var.w(2, this.j);
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            int iW = v4Var.w(11, -1);
            this.k = iW;
            if (iW != -1) {
                this.j &= 2;
            }
        }
        if (!v4Var.G(10) && !v4Var.G(12)) {
            if (v4Var.G(1)) {
                this.m = false;
                int iW2 = v4Var.w(1, 1);
                if (iW2 == 1) {
                    this.l = Typeface.SANS_SERIF;
                    return;
                } else if (iW2 == 2) {
                    this.l = Typeface.SERIF;
                    return;
                } else {
                    if (iW2 != 3) {
                        return;
                    }
                    this.l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.l = null;
        int i2 = v4Var.G(12) ? 12 : 10;
        int i3 = this.k;
        int i4 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceV = v4Var.v(i2, this.j, new w9(this, i3, i4, new WeakReference(this.a)));
                if (typefaceV != null) {
                    if (i < 28 || this.k == -1) {
                        this.l = typefaceV;
                    } else {
                        this.l = ba.a(Typeface.create(typefaceV, 0), this.k, (this.j & 2) != 0);
                    }
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (strZ = v4Var.z(i2)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.k == -1) {
            this.l = Typeface.create(strZ, this.j);
        } else {
            this.l = ba.a(Typeface.create(strZ, 0), this.k, (this.j & 2) != 0);
        }
    }
}
