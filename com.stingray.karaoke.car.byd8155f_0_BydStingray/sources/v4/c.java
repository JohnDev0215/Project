package V4;

import S0.C0690b;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import android.util.TypedValue;
import com.bumptech.glide.d;
import x1.AbstractC2671j;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ColorStateList f9465a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f9466b;
    public final String c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9467e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f9468f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f9469g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f9470h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f9471i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float f9472j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f9473k;
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f9474m = false;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Typeface f9475n;

    public c(Context context, int i7) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i7, K4.a.f3600t);
        this.f9473k = typedArrayObtainStyledAttributes.getDimension(0, 0.0f);
        this.f9465a = com.bumptech.glide.c.Z(context, typedArrayObtainStyledAttributes, 3);
        com.bumptech.glide.c.Z(context, typedArrayObtainStyledAttributes, 4);
        com.bumptech.glide.c.Z(context, typedArrayObtainStyledAttributes, 5);
        this.d = typedArrayObtainStyledAttributes.getInt(2, 0);
        this.f9467e = typedArrayObtainStyledAttributes.getInt(1, 1);
        int i10 = typedArrayObtainStyledAttributes.hasValue(12) ? 12 : 10;
        this.l = typedArrayObtainStyledAttributes.getResourceId(i10, 0);
        this.c = typedArrayObtainStyledAttributes.getString(i10);
        typedArrayObtainStyledAttributes.getBoolean(14, false);
        this.f9466b = com.bumptech.glide.c.Z(context, typedArrayObtainStyledAttributes, 6);
        this.f9468f = typedArrayObtainStyledAttributes.getFloat(7, 0.0f);
        this.f9469g = typedArrayObtainStyledAttributes.getFloat(8, 0.0f);
        this.f9470h = typedArrayObtainStyledAttributes.getFloat(9, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(i7, K4.a.f3594m);
        this.f9471i = typedArrayObtainStyledAttributes2.hasValue(0);
        this.f9472j = typedArrayObtainStyledAttributes2.getFloat(0, 0.0f);
        typedArrayObtainStyledAttributes2.recycle();
    }

    public final void a() {
        String str;
        Typeface typeface = this.f9475n;
        int i7 = this.d;
        if (typeface == null && (str = this.c) != null) {
            this.f9475n = Typeface.create(str, i7);
        }
        if (this.f9475n == null) {
            int i10 = this.f9467e;
            if (i10 == 1) {
                this.f9475n = Typeface.SANS_SERIF;
            } else if (i10 == 2) {
                this.f9475n = Typeface.SERIF;
            } else if (i10 != 3) {
                this.f9475n = Typeface.DEFAULT;
            } else {
                this.f9475n = Typeface.MONOSPACE;
            }
            this.f9475n = Typeface.create(this.f9475n, i7);
        }
    }

    public final Typeface b(Context context) {
        if (this.f9474m) {
            return this.f9475n;
        }
        if (!context.isRestricted()) {
            try {
                Typeface typefaceA = AbstractC2671j.a(context, this.l);
                this.f9475n = typefaceA;
                if (typefaceA != null) {
                    this.f9475n = Typeface.create(typefaceA, this.d);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e10) {
                Log.d("TextAppearance", "Error loading font " + this.c, e10);
            }
        }
        a();
        this.f9474m = true;
        return this.f9475n;
    }

    public final void c(Context context, d dVar) {
        if (d(context)) {
            b(context);
        } else {
            a();
        }
        int i7 = this.l;
        if (i7 == 0) {
            this.f9474m = true;
        }
        if (this.f9474m) {
            dVar.J(this.f9475n, true);
            return;
        }
        try {
            C0690b c0690b = new C0690b(this, dVar);
            ThreadLocal threadLocal = AbstractC2671j.f23708a;
            if (context.isRestricted()) {
                c0690b.a(-4);
            } else {
                AbstractC2671j.b(context, i7, new TypedValue(), 0, c0690b, false, false);
            }
        } catch (Resources.NotFoundException unused) {
            this.f9474m = true;
            dVar.I(1);
        } catch (Exception e10) {
            Log.d("TextAppearance", "Error loading font " + this.c, e10);
            this.f9474m = true;
            dVar.I(-3);
        }
    }

    public final boolean d(Context context) {
        Typeface typefaceB = null;
        int i7 = this.l;
        if (i7 != 0) {
            ThreadLocal threadLocal = AbstractC2671j.f23708a;
            if (!context.isRestricted()) {
                typefaceB = AbstractC2671j.b(context, i7, new TypedValue(), 0, null, false, true);
            }
        }
        return typefaceB != null;
    }

    public final void e(Context context, TextPaint textPaint, d dVar) {
        f(context, textPaint, dVar);
        ColorStateList colorStateList = this.f9465a;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        ColorStateList colorStateList2 = this.f9466b;
        textPaint.setShadowLayer(this.f9470h, this.f9468f, this.f9469g, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public final void f(Context context, TextPaint textPaint, d dVar) {
        if (d(context)) {
            g(textPaint, b(context));
            return;
        }
        a();
        g(textPaint, this.f9475n);
        c(context, new b(this, textPaint, dVar));
    }

    public final void g(TextPaint textPaint, Typeface typeface) {
        textPaint.setTypeface(typeface);
        int i7 = (~typeface.getStyle()) & this.d;
        textPaint.setFakeBoldText((i7 & 1) != 0);
        textPaint.setTextSkewX((i7 & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.f9473k);
        if (this.f9471i) {
            textPaint.setLetterSpacing(this.f9472j);
        }
    }
}
