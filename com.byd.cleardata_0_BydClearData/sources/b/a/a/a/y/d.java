package b.a.a.a.y;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import androidx.core.content.c.f;
import b.a.a.a.l;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f1777a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f1778b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ColorStateList f1779c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ColorStateList f1780d;
    public final int e;
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

    class a extends f.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ f f1781a;

        a(f fVar) {
            this.f1781a = fVar;
        }

        @Override // androidx.core.content.c.f.a
        public void c(int i) {
            d.this.n = true;
            this.f1781a.a(i);
        }

        @Override // androidx.core.content.c.f.a
        public void d(Typeface typeface) {
            d dVar = d.this;
            dVar.o = Typeface.create(typeface, dVar.e);
            d.this.n = true;
            this.f1781a.b(d.this.o, false);
        }
    }

    class b extends f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ TextPaint f1783a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ f f1784b;

        b(TextPaint textPaint, f fVar) {
            this.f1783a = textPaint;
            this.f1784b = fVar;
        }

        @Override // b.a.a.a.y.f
        public void a(int i) {
            this.f1784b.a(i);
        }

        @Override // b.a.a.a.y.f
        public void b(Typeface typeface, boolean z) {
            d.this.k(this.f1783a, typeface);
            this.f1784b.b(typeface, z);
        }
    }

    public d(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, l.T3);
        this.f1777a = typedArrayObtainStyledAttributes.getDimension(l.U3, 0.0f);
        this.f1778b = c.a(context, typedArrayObtainStyledAttributes, l.X3);
        this.f1779c = c.a(context, typedArrayObtainStyledAttributes, l.Y3);
        this.f1780d = c.a(context, typedArrayObtainStyledAttributes, l.Z3);
        this.e = typedArrayObtainStyledAttributes.getInt(l.W3, 0);
        this.f = typedArrayObtainStyledAttributes.getInt(l.V3, 1);
        int iE = c.e(typedArrayObtainStyledAttributes, l.f4, l.e4);
        this.m = typedArrayObtainStyledAttributes.getResourceId(iE, 0);
        this.g = typedArrayObtainStyledAttributes.getString(iE);
        this.h = typedArrayObtainStyledAttributes.getBoolean(l.g4, false);
        this.i = c.a(context, typedArrayObtainStyledAttributes, l.a4);
        this.j = typedArrayObtainStyledAttributes.getFloat(l.b4, 0.0f);
        this.k = typedArrayObtainStyledAttributes.getFloat(l.c4, 0.0f);
        this.l = typedArrayObtainStyledAttributes.getFloat(l.d4, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
    }

    private void d() {
        String str;
        if (this.o == null && (str = this.g) != null) {
            this.o = Typeface.create(str, this.e);
        }
        if (this.o == null) {
            int i = this.f;
            this.o = i != 1 ? i != 2 ? i != 3 ? Typeface.DEFAULT : Typeface.MONOSPACE : Typeface.SERIF : Typeface.SANS_SERIF;
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
                Typeface typefaceB = androidx.core.content.c.f.b(context, this.m);
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
        } catch (Exception e) {
            Log.d("TextAppearance", "Error loading font " + this.g, e);
            this.n = true;
            fVar.a(-3);
        }
    }

    public void i(Context context, TextPaint textPaint, f fVar) {
        j(context, textPaint, fVar);
        ColorStateList colorStateList = this.f1778b;
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
        int i = (~typeface.getStyle()) & this.e;
        textPaint.setFakeBoldText((i & 1) != 0);
        textPaint.setTextSkewX((i & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.f1777a);
    }
}
