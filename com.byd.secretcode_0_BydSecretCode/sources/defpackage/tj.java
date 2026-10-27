package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import com.google.android.material.R$styleable;
import defpackage.l7;

/* JADX INFO: compiled from: TextAppearance.java */
/* JADX INFO: loaded from: classes.dex */
public class tj {
    public final float a;
    public final ColorStateList b;
    public final ColorStateList c;
    public final ColorStateList d;
    public final int e;
    public final int f;
    public final String g;
    public final boolean h;
    public final ColorStateList i;
    public final float j;
    public final float k;
    public final float l;
    public final int m;
    public boolean n = false;
    public Typeface o;

    /* JADX INFO: compiled from: TextAppearance.java */
    public class a extends l7.a {
        public final /* synthetic */ vj a;

        public a(vj vjVar) {
            this.a = vjVar;
        }

        @Override // l7.a
        public void c(int i) {
            tj.this.n = true;
            this.a.a(i);
        }

        @Override // l7.a
        public void d(Typeface typeface) {
            tj tjVar = tj.this;
            tjVar.o = Typeface.create(typeface, tjVar.e);
            tj.this.n = true;
            this.a.b(tj.this.o, false);
        }
    }

    /* JADX INFO: compiled from: TextAppearance.java */
    public class b extends vj {
        public final /* synthetic */ TextPaint a;
        public final /* synthetic */ vj b;

        public b(TextPaint textPaint, vj vjVar) {
            this.a = textPaint;
            this.b = vjVar;
        }

        @Override // defpackage.vj
        public void a(int i) {
            this.b.a(i);
        }

        @Override // defpackage.vj
        public void b(Typeface typeface, boolean z) {
            tj.this.k(this.a, typeface);
            this.b.b(typeface, z);
        }
    }

    public tj(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.TextAppearance);
        this.a = typedArrayObtainStyledAttributes.getDimension(R$styleable.TextAppearance_android_textSize, 0.0f);
        this.b = sj.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColor);
        this.c = sj.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColorHint);
        this.d = sj.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColorLink);
        this.e = typedArrayObtainStyledAttributes.getInt(R$styleable.TextAppearance_android_textStyle, 0);
        this.f = typedArrayObtainStyledAttributes.getInt(R$styleable.TextAppearance_android_typeface, 1);
        int iE = sj.e(typedArrayObtainStyledAttributes, R$styleable.TextAppearance_fontFamily, R$styleable.TextAppearance_android_fontFamily);
        this.m = typedArrayObtainStyledAttributes.getResourceId(iE, 0);
        this.g = typedArrayObtainStyledAttributes.getString(iE);
        this.h = typedArrayObtainStyledAttributes.getBoolean(R$styleable.TextAppearance_textAllCaps, false);
        this.i = sj.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_shadowColor);
        this.j = typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDx, 0.0f);
        this.k = typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDy, 0.0f);
        this.l = typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowRadius, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void d() {
        String str;
        if (this.o == null && (str = this.g) != null) {
            this.o = Typeface.create(str, this.e);
        }
        if (this.o == null) {
            int i = this.f;
            if (i == 1) {
                this.o = Typeface.SANS_SERIF;
            } else if (i == 2) {
                this.o = Typeface.SERIF;
            } else if (i != 3) {
                this.o = Typeface.DEFAULT;
            } else {
                this.o = Typeface.MONOSPACE;
            }
            this.o = Typeface.create(this.o, this.e);
        }
    }

    public Typeface e() {
        d();
        return this.o;
    }

    public Typeface f(Context context) {
        if (this.n) {
            return this.o;
        }
        if (!context.isRestricted()) {
            try {
                Typeface typefaceB = l7.b(context, this.m);
                this.o = typefaceB;
                if (typefaceB != null) {
                    this.o = Typeface.create(typefaceB, this.e);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e) {
                Log.d("TextAppearance", "Error loading font " + this.g, e);
            }
        }
        d();
        this.n = true;
        return this.o;
    }

    public void g(Context context, TextPaint textPaint, vj vjVar) {
        k(textPaint, e());
        h(context, new b(textPaint, vjVar));
    }

    public void h(Context context, vj vjVar) {
        if (uj.a()) {
            f(context);
        } else {
            d();
        }
        int i = this.m;
        if (i == 0) {
            this.n = true;
        }
        if (this.n) {
            vjVar.b(this.o, true);
            return;
        }
        try {
            l7.d(context, i, new a(vjVar), null);
        } catch (Resources.NotFoundException unused) {
            this.n = true;
            vjVar.a(1);
        } catch (Exception e) {
            Log.d("TextAppearance", "Error loading font " + this.g, e);
            this.n = true;
            vjVar.a(-3);
        }
    }

    public void i(Context context, TextPaint textPaint, vj vjVar) {
        j(context, textPaint, vjVar);
        ColorStateList colorStateList = this.b;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        float f = this.l;
        float f2 = this.j;
        float f3 = this.k;
        ColorStateList colorStateList2 = this.i;
        textPaint.setShadowLayer(f, f2, f3, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public void j(Context context, TextPaint textPaint, vj vjVar) {
        if (uj.a()) {
            k(textPaint, f(context));
        } else {
            g(context, textPaint, vjVar);
        }
    }

    public void k(TextPaint textPaint, Typeface typeface) {
        textPaint.setTypeface(typeface);
        int i = (~typeface.getStyle()) & this.e;
        textPaint.setFakeBoldText((i & 1) != 0);
        textPaint.setTextSkewX((i & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.a);
    }
}
