package d.b.a.a.u;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import androidx.core.content.c.f;
import d.b.a.a.l;

/* JADX INFO: compiled from: TextAppearance.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f4711a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f4712b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ColorStateList f4713c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ColorStateList f4714d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f4715e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4716f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f4717g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f4718h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ColorStateList f4719i;
    public final float j;
    public final float k;
    public final float l;
    private final int m;
    private boolean n = false;
    private Typeface o;

    /* JADX INFO: compiled from: TextAppearance.java */
    class a extends f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ f f4720a;

        a(f fVar) {
            this.f4720a = fVar;
        }

        @Override // androidx.core.content.c.f.a
        public void c(int i2) {
            d.this.n = true;
            this.f4720a.a(i2);
        }

        @Override // androidx.core.content.c.f.a
        public void d(Typeface typeface) {
            d dVar = d.this;
            dVar.o = Typeface.create(typeface, dVar.f4715e);
            d.this.n = true;
            this.f4720a.b(d.this.o, false);
        }
    }

    /* JADX INFO: compiled from: TextAppearance.java */
    class b extends f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ TextPaint f4722a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ f f4723b;

        b(TextPaint textPaint, f fVar) {
            this.f4722a = textPaint;
            this.f4723b = fVar;
        }

        @Override // d.b.a.a.u.f
        public void a(int i2) {
            this.f4723b.a(i2);
        }

        @Override // d.b.a.a.u.f
        public void b(Typeface typeface, boolean z) {
            d.this.k(this.f4722a, typeface);
            this.f4723b.b(typeface, z);
        }
    }

    public d(Context context, int i2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i2, l.TextAppearance);
        this.f4711a = typedArrayObtainStyledAttributes.getDimension(l.TextAppearance_android_textSize, 0.0f);
        this.f4712b = c.a(context, typedArrayObtainStyledAttributes, l.TextAppearance_android_textColor);
        this.f4713c = c.a(context, typedArrayObtainStyledAttributes, l.TextAppearance_android_textColorHint);
        this.f4714d = c.a(context, typedArrayObtainStyledAttributes, l.TextAppearance_android_textColorLink);
        this.f4715e = typedArrayObtainStyledAttributes.getInt(l.TextAppearance_android_textStyle, 0);
        this.f4716f = typedArrayObtainStyledAttributes.getInt(l.TextAppearance_android_typeface, 1);
        int iE = c.e(typedArrayObtainStyledAttributes, l.TextAppearance_fontFamily, l.TextAppearance_android_fontFamily);
        this.m = typedArrayObtainStyledAttributes.getResourceId(iE, 0);
        this.f4717g = typedArrayObtainStyledAttributes.getString(iE);
        this.f4718h = typedArrayObtainStyledAttributes.getBoolean(l.TextAppearance_textAllCaps, false);
        this.f4719i = c.a(context, typedArrayObtainStyledAttributes, l.TextAppearance_android_shadowColor);
        this.j = typedArrayObtainStyledAttributes.getFloat(l.TextAppearance_android_shadowDx, 0.0f);
        this.k = typedArrayObtainStyledAttributes.getFloat(l.TextAppearance_android_shadowDy, 0.0f);
        this.l = typedArrayObtainStyledAttributes.getFloat(l.TextAppearance_android_shadowRadius, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void d() {
        String str;
        if (this.o == null && (str = this.f4717g) != null) {
            this.o = Typeface.create(str, this.f4715e);
        }
        if (this.o == null) {
            int i2 = this.f4716f;
            if (i2 == 1) {
                this.o = Typeface.SANS_SERIF;
            } else if (i2 == 2) {
                this.o = Typeface.SERIF;
            } else if (i2 != 3) {
                this.o = Typeface.DEFAULT;
            } else {
                this.o = Typeface.MONOSPACE;
            }
            this.o = Typeface.create(this.o, this.f4715e);
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
                Typeface typefaceB = androidx.core.content.c.f.b(context, this.m);
                this.o = typefaceB;
                if (typefaceB != null) {
                    this.o = Typeface.create(typefaceB, this.f4715e);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e2) {
                Log.d("TextAppearance", "Error loading font " + this.f4717g, e2);
            }
        }
        d();
        this.n = true;
        return this.o;
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
        int i2 = this.m;
        if (i2 == 0) {
            this.n = true;
        }
        if (this.n) {
            fVar.b(this.o, true);
            return;
        }
        try {
            androidx.core.content.c.f.d(context, i2, new a(fVar), null);
        } catch (Resources.NotFoundException unused) {
            this.n = true;
            fVar.a(1);
        } catch (Exception e2) {
            Log.d("TextAppearance", "Error loading font " + this.f4717g, e2);
            this.n = true;
            fVar.a(-3);
        }
    }

    public void i(Context context, TextPaint textPaint, f fVar) {
        j(context, textPaint, fVar);
        ColorStateList colorStateList = this.f4712b;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        float f2 = this.l;
        float f3 = this.j;
        float f4 = this.k;
        ColorStateList colorStateList2 = this.f4719i;
        textPaint.setShadowLayer(f2, f3, f4, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
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
        int i2 = (~typeface.getStyle()) & this.f4715e;
        textPaint.setFakeBoldText((i2 & 1) != 0);
        textPaint.setTextSkewX((i2 & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.f4711a);
    }
}
