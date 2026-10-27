package c.a.a.a.t;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import androidx.core.content.c.f;
import c.a.a.a.l;

/* JADX INFO: compiled from: TextAppearance.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f2338a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f2339b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ColorStateList f2340c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ColorStateList f2341d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2342e;
    public final int f;
    public final String g;
    public final boolean h;
    public final ColorStateList i;
    public final float j;
    public final float k;
    public final float l;
    private final int m;
    private boolean n = false;
    private Typeface o;

    /* JADX INFO: compiled from: TextAppearance.java */
    class a extends f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ f f2343a;

        a(f fVar) {
            this.f2343a = fVar;
        }

        @Override // androidx.core.content.c.f.a
        public void c(int i) {
            d.this.n = true;
            this.f2343a.a(i);
        }

        @Override // androidx.core.content.c.f.a
        public void d(Typeface typeface) {
            d dVar = d.this;
            dVar.o = Typeface.create(typeface, dVar.f2342e);
            d.this.n = true;
            this.f2343a.b(d.this.o, false);
        }
    }

    /* JADX INFO: compiled from: TextAppearance.java */
    class b extends f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ TextPaint f2345a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ f f2346b;

        b(TextPaint textPaint, f fVar) {
            this.f2345a = textPaint;
            this.f2346b = fVar;
        }

        @Override // c.a.a.a.t.f
        public void a(int i) {
            this.f2346b.a(i);
        }

        @Override // c.a.a.a.t.f
        public void b(Typeface typeface, boolean z) {
            d.this.k(this.f2345a, typeface);
            this.f2346b.b(typeface, z);
        }
    }

    public d(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, l.s6);
        this.f2338a = typedArrayObtainStyledAttributes.getDimension(l.t6, 0.0f);
        this.f2339b = c.a(context, typedArrayObtainStyledAttributes, l.w6);
        this.f2340c = c.a(context, typedArrayObtainStyledAttributes, l.x6);
        this.f2341d = c.a(context, typedArrayObtainStyledAttributes, l.y6);
        this.f2342e = typedArrayObtainStyledAttributes.getInt(l.v6, 0);
        this.f = typedArrayObtainStyledAttributes.getInt(l.u6, 1);
        int iE = c.e(typedArrayObtainStyledAttributes, l.E6, l.D6);
        this.m = typedArrayObtainStyledAttributes.getResourceId(iE, 0);
        this.g = typedArrayObtainStyledAttributes.getString(iE);
        this.h = typedArrayObtainStyledAttributes.getBoolean(l.F6, false);
        this.i = c.a(context, typedArrayObtainStyledAttributes, l.z6);
        this.j = typedArrayObtainStyledAttributes.getFloat(l.A6, 0.0f);
        this.k = typedArrayObtainStyledAttributes.getFloat(l.B6, 0.0f);
        this.l = typedArrayObtainStyledAttributes.getFloat(l.C6, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void d() {
        String str;
        if (this.o == null && (str = this.g) != null) {
            this.o = Typeface.create(str, this.f2342e);
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
            this.o = Typeface.create(this.o, this.f2342e);
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
                    this.o = Typeface.create(typefaceB, this.f2342e);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e2) {
                Log.d("TextAppearance", "Error loading font " + this.g, e2);
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
        int i = this.m;
        if (i == 0) {
            this.n = true;
        }
        if (this.n) {
            fVar.b(this.o, true);
            return;
        }
        try {
            androidx.core.content.c.f.d(context, i, new a(fVar), null);
        } catch (Resources.NotFoundException unused) {
            this.n = true;
            fVar.a(1);
        } catch (Exception e2) {
            Log.d("TextAppearance", "Error loading font " + this.g, e2);
            this.n = true;
            fVar.a(-3);
        }
    }

    public void i(Context context, TextPaint textPaint, f fVar) {
        j(context, textPaint, fVar);
        ColorStateList colorStateList = this.f2339b;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        float f = this.l;
        float f2 = this.j;
        float f3 = this.k;
        ColorStateList colorStateList2 = this.i;
        textPaint.setShadowLayer(f, f2, f3, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
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
        int i = (~typeface.getStyle()) & this.f2342e;
        textPaint.setFakeBoldText((i & 1) != 0);
        textPaint.setTextSkewX((i & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.f2338a);
    }
}
