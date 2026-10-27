package b6;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import com.google.android.material.R$styleable;
import f0.f;

/* JADX INFO: compiled from: TextAppearance.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f2105a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f2106b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ColorStateList f2107c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ColorStateList f2108d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2109e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2110f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f2111g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f2112h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ColorStateList f2113i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float f2114j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final float f2115k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final float f2116l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f2117m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f2118n = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Typeface f2119o;

    /* JADX INFO: compiled from: TextAppearance.java */
    public class a extends f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ f f2120a;

        public a(f fVar) {
            this.f2120a = fVar;
        }

        @Override // f0.f.a
        public void c(int i8) {
            d.this.f2118n = true;
            this.f2120a.a(i8);
        }

        @Override // f0.f.a
        public void d(Typeface typeface) {
            d dVar = d.this;
            dVar.f2119o = Typeface.create(typeface, dVar.f2109e);
            d.this.f2118n = true;
            this.f2120a.b(d.this.f2119o, false);
        }
    }

    /* JADX INFO: compiled from: TextAppearance.java */
    public class b extends f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextPaint f2122a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ f f2123b;

        public b(TextPaint textPaint, f fVar) {
            this.f2122a = textPaint;
            this.f2123b = fVar;
        }

        @Override // b6.f
        public void a(int i8) {
            this.f2123b.a(i8);
        }

        @Override // b6.f
        public void b(Typeface typeface, boolean z7) {
            d.this.k(this.f2122a, typeface);
            this.f2123b.b(typeface, z7);
        }
    }

    public d(Context context, int i8) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i8, R$styleable.TextAppearance);
        this.f2105a = typedArrayObtainStyledAttributes.getDimension(R$styleable.TextAppearance_android_textSize, 0.0f);
        this.f2106b = c.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColor);
        this.f2107c = c.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColorHint);
        this.f2108d = c.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColorLink);
        this.f2109e = typedArrayObtainStyledAttributes.getInt(R$styleable.TextAppearance_android_textStyle, 0);
        this.f2110f = typedArrayObtainStyledAttributes.getInt(R$styleable.TextAppearance_android_typeface, 1);
        int iE = c.e(typedArrayObtainStyledAttributes, R$styleable.TextAppearance_fontFamily, R$styleable.TextAppearance_android_fontFamily);
        this.f2117m = typedArrayObtainStyledAttributes.getResourceId(iE, 0);
        this.f2111g = typedArrayObtainStyledAttributes.getString(iE);
        this.f2112h = typedArrayObtainStyledAttributes.getBoolean(R$styleable.TextAppearance_textAllCaps, false);
        this.f2113i = c.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_shadowColor);
        this.f2114j = typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDx, 0.0f);
        this.f2115k = typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDy, 0.0f);
        this.f2116l = typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowRadius, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void d() {
        String str;
        if (this.f2119o == null && (str = this.f2111g) != null) {
            this.f2119o = Typeface.create(str, this.f2109e);
        }
        if (this.f2119o == null) {
            int i8 = this.f2110f;
            if (i8 == 1) {
                this.f2119o = Typeface.SANS_SERIF;
            } else if (i8 == 2) {
                this.f2119o = Typeface.SERIF;
            } else if (i8 != 3) {
                this.f2119o = Typeface.DEFAULT;
            } else {
                this.f2119o = Typeface.MONOSPACE;
            }
            this.f2119o = Typeface.create(this.f2119o, this.f2109e);
        }
    }

    public Typeface e() {
        d();
        return this.f2119o;
    }

    public Typeface f(Context context) {
        if (this.f2118n) {
            return this.f2119o;
        }
        if (!context.isRestricted()) {
            try {
                Typeface typefaceB = f0.f.b(context, this.f2117m);
                this.f2119o = typefaceB;
                if (typefaceB != null) {
                    this.f2119o = Typeface.create(typefaceB, this.f2109e);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e8) {
                Log.d("TextAppearance", "Error loading font " + this.f2111g, e8);
            }
        }
        d();
        this.f2118n = true;
        return this.f2119o;
    }

    public void g(Context context, TextPaint textPaint, f fVar) {
        k(textPaint, e());
        h(context, new b(textPaint, fVar));
    }

    public void h(Context context, f fVar) {
        if (e.a()) {
            f(context);
        } else {
            d();
        }
        int i8 = this.f2117m;
        if (i8 == 0) {
            this.f2118n = true;
        }
        if (this.f2118n) {
            fVar.b(this.f2119o, true);
            return;
        }
        try {
            f0.f.d(context, i8, new a(fVar), null);
        } catch (Resources.NotFoundException unused) {
            this.f2118n = true;
            fVar.a(1);
        } catch (Exception e8) {
            Log.d("TextAppearance", "Error loading font " + this.f2111g, e8);
            this.f2118n = true;
            fVar.a(-3);
        }
    }

    public void i(Context context, TextPaint textPaint, f fVar) {
        j(context, textPaint, fVar);
        ColorStateList colorStateList = this.f2106b;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        float f8 = this.f2116l;
        float f9 = this.f2114j;
        float f10 = this.f2115k;
        ColorStateList colorStateList2 = this.f2113i;
        textPaint.setShadowLayer(f8, f9, f10, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public void j(Context context, TextPaint textPaint, f fVar) {
        if (e.a()) {
            k(textPaint, f(context));
        } else {
            g(context, textPaint, fVar);
        }
    }

    public void k(TextPaint textPaint, Typeface typeface) {
        textPaint.setTypeface(typeface);
        int i8 = (~typeface.getStyle()) & this.f2109e;
        textPaint.setFakeBoldText((i8 & 1) != 0);
        textPaint.setTextSkewX((i8 & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.f2105a);
    }
}
