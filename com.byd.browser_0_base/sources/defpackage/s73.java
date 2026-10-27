package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import com.google.android.material.R$styleable;
import defpackage.rb2;

/* JADX INFO: compiled from: TextAppearance.java */
/* JADX INFO: loaded from: classes.dex */
public final class s73 {
    public final ColorStateList a;
    public final String b;
    public final int c;
    public final int d;
    public final float e;
    public final float f;
    public final float g;
    public final boolean h;
    public final float i;
    public ColorStateList j;
    public float k;
    public final int l;
    public boolean m = false;
    public Typeface n;

    /* JADX INFO: compiled from: TextAppearance.java */
    public class a extends rb2.c {
        public final /* synthetic */ vd1 a;

        public a(vd1 vd1Var) {
            this.a = vd1Var;
        }

        @Override // rb2.c
        public final void c(int i) {
            s73.this.m = true;
            this.a.s(i);
        }

        @Override // rb2.c
        public final void d(Typeface typeface) {
            s73 s73Var = s73.this;
            s73Var.n = Typeface.create(typeface, s73Var.c);
            s73Var.m = true;
            this.a.w(s73Var.n, false);
        }
    }

    public s73(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.TextAppearance);
        this.k = typedArrayObtainStyledAttributes.getDimension(R$styleable.TextAppearance_android_textSize, 0.0f);
        this.j = rc1.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColor);
        rc1.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColorHint);
        rc1.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColorLink);
        this.c = typedArrayObtainStyledAttributes.getInt(R$styleable.TextAppearance_android_textStyle, 0);
        this.d = typedArrayObtainStyledAttributes.getInt(R$styleable.TextAppearance_android_typeface, 1);
        int i2 = R$styleable.TextAppearance_fontFamily;
        i2 = typedArrayObtainStyledAttributes.hasValue(i2) ? i2 : R$styleable.TextAppearance_android_fontFamily;
        this.l = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
        this.b = typedArrayObtainStyledAttributes.getString(i2);
        typedArrayObtainStyledAttributes.getBoolean(R$styleable.TextAppearance_textAllCaps, false);
        this.a = rc1.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_shadowColor);
        this.e = typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDx, 0.0f);
        this.f = typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDy, 0.0f);
        this.g = typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowRadius, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(i, R$styleable.MaterialTextAppearance);
        int i3 = R$styleable.MaterialTextAppearance_android_letterSpacing;
        this.h = typedArrayObtainStyledAttributes2.hasValue(i3);
        this.i = typedArrayObtainStyledAttributes2.getFloat(i3, 0.0f);
        typedArrayObtainStyledAttributes2.recycle();
    }

    public final void a() {
        String str;
        Typeface typeface = this.n;
        int i = this.c;
        if (typeface == null && (str = this.b) != null) {
            this.n = Typeface.create(str, i);
        }
        if (this.n == null) {
            int i2 = this.d;
            if (i2 == 1) {
                this.n = Typeface.SANS_SERIF;
            } else if (i2 == 2) {
                this.n = Typeface.SERIF;
            } else if (i2 != 3) {
                this.n = Typeface.DEFAULT;
            } else {
                this.n = Typeface.MONOSPACE;
            }
            this.n = Typeface.create(this.n, i);
        }
    }

    public final Typeface b(Context context) {
        if (this.m) {
            return this.n;
        }
        if (!context.isRestricted()) {
            try {
                Typeface typefaceA = rb2.a(context, this.l);
                this.n = typefaceA;
                if (typefaceA != null) {
                    this.n = Typeface.create(typefaceA, this.c);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException | Exception unused) {
            }
        }
        a();
        this.m = true;
        return this.n;
    }

    public final void c(Context context, vd1 vd1Var) {
        if (d(context)) {
            b(context);
        } else {
            a();
        }
        int i = this.l;
        if (i == 0) {
            this.m = true;
        }
        if (this.m) {
            vd1Var.w(this.n, true);
            return;
        }
        try {
            rb2.b(context, i, new a(vd1Var));
        } catch (Resources.NotFoundException unused) {
            this.m = true;
            vd1Var.s(1);
        } catch (Exception unused2) {
            this.m = true;
            vd1Var.s(-3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean d(android.content.Context r8) {
        /*
            r7 = this;
            int r1 = r7.l
            if (r1 == 0) goto L1c
            java.lang.ThreadLocal<android.util.TypedValue> r7 = defpackage.rb2.a
            boolean r7 = r8.isRestricted()
            if (r7 == 0) goto Ld
            goto L1c
        Ld:
            android.util.TypedValue r2 = new android.util.TypedValue
            r2.<init>()
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 1
            r0 = r8
            android.graphics.Typeface r7 = defpackage.rb2.c(r0, r1, r2, r3, r4, r5, r6)
            goto L1d
        L1c:
            r7 = 0
        L1d:
            if (r7 == 0) goto L21
            r7 = 1
            goto L22
        L21:
            r7 = 0
        L22:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.s73.d(android.content.Context):boolean");
    }

    public final void e(Context context, TextPaint textPaint, vd1 vd1Var) {
        f(context, textPaint, vd1Var);
        ColorStateList colorStateList = this.j;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        ColorStateList colorStateList2 = this.a;
        textPaint.setShadowLayer(this.g, this.e, this.f, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public final void f(Context context, TextPaint textPaint, vd1 vd1Var) {
        if (d(context)) {
            g(context, textPaint, b(context));
            return;
        }
        a();
        g(context, textPaint, this.n);
        c(context, new t73(this, context, textPaint, vd1Var));
    }

    public final void g(Context context, TextPaint textPaint, Typeface typeface) {
        Typeface typefaceA = oh3.a(context.getResources().getConfiguration(), typeface);
        if (typefaceA != null) {
            typeface = typefaceA;
        }
        textPaint.setTypeface(typeface);
        int i = (~typeface.getStyle()) & this.c;
        textPaint.setFakeBoldText((i & 1) != 0);
        textPaint.setTextSkewX((i & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.k);
        if (this.h) {
            textPaint.setLetterSpacing(this.i);
        }
    }
}
