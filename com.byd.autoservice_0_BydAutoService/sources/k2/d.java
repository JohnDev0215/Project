package k2;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import c0.f;
import com.google.android.material.R$styleable;

/* JADX INFO: compiled from: TextAppearance.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3587a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f3588b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ColorStateList f3589c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ColorStateList f3590d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f3591e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f3592f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f3593g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f3594h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ColorStateList f3595i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float f3596j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final float f3597k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final float f3598l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f3599m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3600n = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Typeface f3601o;

    /* JADX INFO: compiled from: TextAppearance.java */
    public class a extends f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ f f3602a;

        public a(f fVar) {
            this.f3602a = fVar;
        }

        @Override // c0.f.a
        public void c(int i3) {
            d.this.f3600n = true;
            this.f3602a.a(i3);
        }

        @Override // c0.f.a
        public void d(Typeface typeface) {
            d dVar = d.this;
            dVar.f3601o = Typeface.create(typeface, dVar.f3591e);
            d.this.f3600n = true;
            this.f3602a.b(d.this.f3601o, false);
        }
    }

    /* JADX INFO: compiled from: TextAppearance.java */
    public class b extends f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ TextPaint f3604a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ f f3605b;

        public b(TextPaint textPaint, f fVar) {
            this.f3604a = textPaint;
            this.f3605b = fVar;
        }

        @Override // k2.f
        public void a(int i3) {
            this.f3605b.a(i3);
        }

        @Override // k2.f
        public void b(Typeface typeface, boolean z3) {
            d.this.k(this.f3604a, typeface);
            this.f3605b.b(typeface, z3);
        }
    }

    public d(Context context, int i3) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i3, R$styleable.TextAppearance);
        this.f3587a = typedArrayObtainStyledAttributes.getDimension(R$styleable.TextAppearance_android_textSize, 0.0f);
        this.f3588b = c.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColor);
        this.f3589c = c.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColorHint);
        this.f3590d = c.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColorLink);
        this.f3591e = typedArrayObtainStyledAttributes.getInt(R$styleable.TextAppearance_android_textStyle, 0);
        this.f3592f = typedArrayObtainStyledAttributes.getInt(R$styleable.TextAppearance_android_typeface, 1);
        int iE = c.e(typedArrayObtainStyledAttributes, R$styleable.TextAppearance_fontFamily, R$styleable.TextAppearance_android_fontFamily);
        this.f3599m = typedArrayObtainStyledAttributes.getResourceId(iE, 0);
        this.f3593g = typedArrayObtainStyledAttributes.getString(iE);
        this.f3594h = typedArrayObtainStyledAttributes.getBoolean(R$styleable.TextAppearance_textAllCaps, false);
        this.f3595i = c.a(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_shadowColor);
        this.f3596j = typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDx, 0.0f);
        this.f3597k = typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDy, 0.0f);
        this.f3598l = typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowRadius, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void d() {
        String str;
        if (this.f3601o == null && (str = this.f3593g) != null) {
            this.f3601o = Typeface.create(str, this.f3591e);
        }
        if (this.f3601o == null) {
            int i3 = this.f3592f;
            if (i3 == 1) {
                this.f3601o = Typeface.SANS_SERIF;
            } else if (i3 == 2) {
                this.f3601o = Typeface.SERIF;
            } else if (i3 != 3) {
                this.f3601o = Typeface.DEFAULT;
            } else {
                this.f3601o = Typeface.MONOSPACE;
            }
            this.f3601o = Typeface.create(this.f3601o, this.f3591e);
        }
    }

    public Typeface e() {
        d();
        return this.f3601o;
    }

    public Typeface f(Context context) {
        if (this.f3600n) {
            return this.f3601o;
        }
        if (!context.isRestricted()) {
            try {
                Typeface typefaceB = c0.f.b(context, this.f3599m);
                this.f3601o = typefaceB;
                if (typefaceB != null) {
                    this.f3601o = Typeface.create(typefaceB, this.f3591e);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e3) {
                Log.d("TextAppearance", "Error loading font " + this.f3593g, e3);
            }
        }
        d();
        this.f3600n = true;
        return this.f3601o;
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
        int i3 = this.f3599m;
        if (i3 == 0) {
            this.f3600n = true;
        }
        if (this.f3600n) {
            fVar.b(this.f3601o, true);
            return;
        }
        try {
            c0.f.d(context, i3, new a(fVar), null);
        } catch (Resources.NotFoundException unused) {
            this.f3600n = true;
            fVar.a(1);
        } catch (Exception e3) {
            Log.d("TextAppearance", "Error loading font " + this.f3593g, e3);
            this.f3600n = true;
            fVar.a(-3);
        }
    }

    public void i(Context context, TextPaint textPaint, f fVar) {
        j(context, textPaint, fVar);
        ColorStateList colorStateList = this.f3588b;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        float f3 = this.f3598l;
        float f4 = this.f3596j;
        float f5 = this.f3597k;
        ColorStateList colorStateList2 = this.f3595i;
        textPaint.setShadowLayer(f3, f4, f5, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
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
        int i3 = (~typeface.getStyle()) & this.f3591e;
        textPaint.setFakeBoldText((i3 & 1) != 0);
        textPaint.setTextSkewX((i3 & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.f3587a);
    }
}
