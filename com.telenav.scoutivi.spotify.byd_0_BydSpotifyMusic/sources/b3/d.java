package b3;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.Log;
import o2.l;
import y.b;

/* JADX INFO: compiled from: TextAppearance.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ColorStateList f3530a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ColorStateList f3531b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ColorStateList f3532c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ColorStateList f3533d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f3534e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f3535f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3536g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f3537h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f3538i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float f3539j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final float f3540k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f3541l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f3542m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f3543n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private final int f3544o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private boolean f3545p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private Typeface f3546q;

    /* JADX INFO: compiled from: TextAppearance.java */
    class a extends b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ f f3547a;

        a(f fVar) {
            this.f3547a = fVar;
        }

        @Override // y.b.a
        public void d(int i7) {
            d.this.f3545p = true;
            this.f3547a.a(i7);
        }

        @Override // y.b.a
        public void e(Typeface typeface) {
            d dVar = d.this;
            dVar.f3546q = Typeface.create(typeface, dVar.f3535f);
            d.this.f3545p = true;
            this.f3547a.b(d.this.f3546q, false);
        }
    }

    /* JADX INFO: compiled from: TextAppearance.java */
    class b extends f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ TextPaint f3549a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ f f3550b;

        b(TextPaint textPaint, f fVar) {
            this.f3549a = textPaint;
            this.f3550b = fVar;
        }

        @Override // b3.f
        public void a(int i7) {
            this.f3550b.a(i7);
        }

        @Override // b3.f
        public void b(Typeface typeface, boolean z6) {
            d.this.l(this.f3549a, typeface);
            this.f3550b.b(typeface, z6);
        }
    }

    public d(Context context, int i7) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i7, l.TextAppearance);
        this.f3543n = typedArrayObtainStyledAttributes.getDimension(l.TextAppearance_android_textSize, 0.0f);
        this.f3530a = c.a(context, typedArrayObtainStyledAttributes, l.TextAppearance_android_textColor);
        this.f3531b = c.a(context, typedArrayObtainStyledAttributes, l.TextAppearance_android_textColorHint);
        this.f3532c = c.a(context, typedArrayObtainStyledAttributes, l.TextAppearance_android_textColorLink);
        this.f3535f = typedArrayObtainStyledAttributes.getInt(l.TextAppearance_android_textStyle, 0);
        this.f3536g = typedArrayObtainStyledAttributes.getInt(l.TextAppearance_android_typeface, 1);
        int iE = c.e(typedArrayObtainStyledAttributes, l.TextAppearance_fontFamily, l.TextAppearance_android_fontFamily);
        this.f3544o = typedArrayObtainStyledAttributes.getResourceId(iE, 0);
        this.f3534e = typedArrayObtainStyledAttributes.getString(iE);
        this.f3537h = typedArrayObtainStyledAttributes.getBoolean(l.TextAppearance_textAllCaps, false);
        this.f3533d = c.a(context, typedArrayObtainStyledAttributes, l.TextAppearance_android_shadowColor);
        this.f3538i = typedArrayObtainStyledAttributes.getFloat(l.TextAppearance_android_shadowDx, 0.0f);
        this.f3539j = typedArrayObtainStyledAttributes.getFloat(l.TextAppearance_android_shadowDy, 0.0f);
        this.f3540k = typedArrayObtainStyledAttributes.getFloat(l.TextAppearance_android_shadowRadius, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(i7, l.MaterialTextAppearance);
        int i8 = l.MaterialTextAppearance_android_letterSpacing;
        this.f3541l = typedArrayObtainStyledAttributes2.hasValue(i8);
        this.f3542m = typedArrayObtainStyledAttributes2.getFloat(i8, 0.0f);
        typedArrayObtainStyledAttributes2.recycle();
    }

    private void d() {
        String str;
        if (this.f3546q == null && (str = this.f3534e) != null) {
            this.f3546q = Typeface.create(str, this.f3535f);
        }
        if (this.f3546q == null) {
            int i7 = this.f3536g;
            if (i7 == 1) {
                this.f3546q = Typeface.SANS_SERIF;
            } else if (i7 == 2) {
                this.f3546q = Typeface.SERIF;
            } else if (i7 != 3) {
                this.f3546q = Typeface.DEFAULT;
            } else {
                this.f3546q = Typeface.MONOSPACE;
            }
            this.f3546q = Typeface.create(this.f3546q, this.f3535f);
        }
    }

    private boolean i(Context context) {
        if (e.a()) {
            return true;
        }
        int i7 = this.f3544o;
        return (i7 != 0 ? y.b.a(context, i7) : null) != null;
    }

    public Typeface e() {
        d();
        return this.f3546q;
    }

    public Typeface f(Context context) {
        if (this.f3545p) {
            return this.f3546q;
        }
        if (!context.isRestricted()) {
            try {
                Typeface typefaceC = y.b.c(context, this.f3544o);
                this.f3546q = typefaceC;
                if (typefaceC != null) {
                    this.f3546q = Typeface.create(typefaceC, this.f3535f);
                }
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e7) {
                Log.d("TextAppearance", "Error loading font " + this.f3534e, e7);
            }
        }
        d();
        this.f3545p = true;
        return this.f3546q;
    }

    public void g(Context context, TextPaint textPaint, f fVar) {
        l(textPaint, e());
        h(context, new b(textPaint, fVar));
    }

    public void h(Context context, f fVar) {
        if (i(context)) {
            f(context);
        } else {
            d();
        }
        int i7 = this.f3544o;
        if (i7 == 0) {
            this.f3545p = true;
        }
        if (this.f3545p) {
            fVar.b(this.f3546q, true);
            return;
        }
        try {
            y.b.e(context, i7, new a(fVar), null);
        } catch (Resources.NotFoundException unused) {
            this.f3545p = true;
            fVar.a(1);
        } catch (Exception e7) {
            Log.d("TextAppearance", "Error loading font " + this.f3534e, e7);
            this.f3545p = true;
            fVar.a(-3);
        }
    }

    public void j(Context context, TextPaint textPaint, f fVar) {
        k(context, textPaint, fVar);
        ColorStateList colorStateList = this.f3530a;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, colorStateList.getDefaultColor()) : -16777216);
        float f7 = this.f3540k;
        float f8 = this.f3538i;
        float f9 = this.f3539j;
        ColorStateList colorStateList2 = this.f3533d;
        textPaint.setShadowLayer(f7, f8, f9, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, colorStateList2.getDefaultColor()) : 0);
    }

    public void k(Context context, TextPaint textPaint, f fVar) {
        if (i(context)) {
            l(textPaint, f(context));
        } else {
            g(context, textPaint, fVar);
        }
    }

    public void l(TextPaint textPaint, Typeface typeface) {
        textPaint.setTypeface(typeface);
        int i7 = (~typeface.getStyle()) & this.f3535f;
        textPaint.setFakeBoldText((i7 & 1) != 0);
        textPaint.setTextSkewX((i7 & 2) != 0 ? -0.25f : 0.0f);
        textPaint.setTextSize(this.f3543n);
        if (this.f3541l) {
            textPaint.setLetterSpacing(this.f3542m);
        }
    }
}
