package b.d.a.a.b0;

import a.b.k.l;
import a.h.d.b.e;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import android.util.TypedValue;
import b.d.a.a.k;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ColorStateList f1804a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f1805b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f1806c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1807d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1808e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f1809f;
    public final float g;
    public final float h;
    public final boolean i;
    public final float j;
    public float k;
    public final int l;
    public boolean m = false;
    public Typeface n;

    public class a extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d f1810a;

        public a(d dVar) {
            this.f1810a = dVar;
        }

        @Override // a.h.d.b.e
        public void d(int i) {
            b.this.m = true;
            this.f1810a.a(i);
        }

        @Override // a.h.d.b.e
        public void e(Typeface typeface) {
            b bVar = b.this;
            bVar.n = Typeface.create(typeface, bVar.f1807d);
            b bVar2 = b.this;
            bVar2.m = true;
            this.f1810a.b(bVar2.n, false);
        }
    }

    public b(Context context, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, k.TextAppearance);
        this.k = typedArrayObtainStyledAttributes.getDimension(k.TextAppearance_android_textSize, 0.0f);
        this.f1804a = l.i.y(context, typedArrayObtainStyledAttributes, k.TextAppearance_android_textColor);
        l.i.y(context, typedArrayObtainStyledAttributes, k.TextAppearance_android_textColorHint);
        l.i.y(context, typedArrayObtainStyledAttributes, k.TextAppearance_android_textColorLink);
        this.f1807d = typedArrayObtainStyledAttributes.getInt(k.TextAppearance_android_textStyle, 0);
        this.f1808e = typedArrayObtainStyledAttributes.getInt(k.TextAppearance_android_typeface, 1);
        int i2 = k.TextAppearance_fontFamily;
        i2 = typedArrayObtainStyledAttributes.hasValue(i2) ? i2 : k.TextAppearance_android_fontFamily;
        this.l = typedArrayObtainStyledAttributes.getResourceId(i2, 0);
        this.f1806c = typedArrayObtainStyledAttributes.getString(i2);
        typedArrayObtainStyledAttributes.getBoolean(k.TextAppearance_textAllCaps, false);
        this.f1805b = l.i.y(context, typedArrayObtainStyledAttributes, k.TextAppearance_android_shadowColor);
        this.f1809f = typedArrayObtainStyledAttributes.getFloat(k.TextAppearance_android_shadowDx, 0.0f);
        this.g = typedArrayObtainStyledAttributes.getFloat(k.TextAppearance_android_shadowDy, 0.0f);
        this.h = typedArrayObtainStyledAttributes.getFloat(k.TextAppearance_android_shadowRadius, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(i, k.MaterialTextAppearance);
        this.i = typedArrayObtainStyledAttributes2.hasValue(k.MaterialTextAppearance_android_letterSpacing);
        this.j = typedArrayObtainStyledAttributes2.getFloat(k.MaterialTextAppearance_android_letterSpacing, 0.0f);
        typedArrayObtainStyledAttributes2.recycle();
    }

    public final void a() {
        String str;
        if (this.n == null && (str = this.f1806c) != null) {
            this.n = Typeface.create(str, this.f1807d);
        }
        if (this.n == null) {
            int i = this.f1808e;
            this.n = i != 1 ? i != 2 ? i != 3 ? Typeface.DEFAULT : Typeface.MONOSPACE : Typeface.SERIF : Typeface.SANS_SERIF;
            this.n = Typeface.create(this.n, this.f1807d);
        }
    }

    public Typeface b(Context context) {
        if (this.m) {
            return this.n;
        }
        if (!context.isRestricted()) {
            try {
                Typeface typefaceU = context.isRestricted() ? null : l.i.U(context, this.l, new TypedValue(), 0, null, null, false, false);
                this.n = typefaceU;
                if (typefaceU != null) {
                    this.n = Typeface.create(typefaceU, this.f1807d);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e2) {
                StringBuilder sbD = b.a.a.a.a.d("Error loading font ");
                sbD.append(this.f1806c);
                Log.d("TextAppearance", sbD.toString(), e2);
            }
        }
        a();
        this.m = true;
        return this.n;
    }

    public void c(Context context, d dVar) {
        if (d(context)) {
            b(context);
        } else {
            a();
        }
        if (this.l == 0) {
            this.m = true;
        }
        if (this.m) {
            dVar.b(this.n, true);
            return;
        }
        try {
            int i = this.l;
            a aVar = new a(dVar);
            if (context.isRestricted()) {
                aVar.a(-4, null);
            } else {
                l.i.U(context, i, new TypedValue(), 0, aVar, null, false, false);
            }
        } catch (Resources.NotFoundException unused) {
            this.m = true;
            dVar.a(1);
        } catch (Exception e2) {
            StringBuilder sbD = b.a.a.a.a.d("Error loading font ");
            sbD.append(this.f1806c);
            Log.d("TextAppearance", sbD.toString(), e2);
            this.m = true;
            dVar.a(-3);
        }
    }

    public final boolean d(Context context) {
        int i = this.l;
        Typeface typefaceU = null;
        if (i != 0 && !context.isRestricted()) {
            typefaceU = l.i.U(context, i, new TypedValue(), 0, null, null, false, true);
        }
        return typefaceU != null;
    }

    public void e(Context context, TextPaint textPaint, d dVar) {
        f(context, textPaint, dVar);
        ColorStateList colorStateList = this.f1804a;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        float f2 = this.h;
        float f3 = this.f1809f;
        float f4 = this.g;
        ColorStateList colorStateList2 = this.f1805b;
        textPaint.setShadowLayer(f2, f3, f4, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public void f(Context context, TextPaint textPaint, d dVar) {
        if (d(context)) {
            g(textPaint, b(context));
            return;
        }
        a();
        g(textPaint, this.n);
        c(context, new c(this, textPaint, dVar));
    }

    public void g(TextPaint textPaint, Typeface typeface) {
        textPaint.setTypeface(typeface);
        int i = (~typeface.getStyle()) & this.f1807d;
        textPaint.setFakeBoldText((i & 1) != 0);
        textPaint.setTextSkewX((i & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.k);
        if (this.i) {
            textPaint.setLetterSpacing(this.j);
        }
    }
}
