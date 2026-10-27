package g0;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;

/* JADX INFO: compiled from: PrecomputedTextCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements Spannable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private static final Object f6441i = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Spannable f6442e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final a f6443g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final PrecomputedText f6444h;

    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final TextPaint f6445a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final TextDirectionHeuristic f6446b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f6447c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f6448d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final PrecomputedText.Params f6449e;

        public a(PrecomputedText.Params params) {
            this.f6445a = params.getTextPaint();
            this.f6446b = params.getTextDirection();
            this.f6447c = params.getBreakStrategy();
            this.f6448d = params.getHyphenationFrequency();
            this.f6449e = Build.VERSION.SDK_INT < 29 ? null : params;
        }

        public boolean a(a aVar) {
            if (this.f6447c == aVar.b() && this.f6448d == aVar.c() && this.f6445a.getTextSize() == aVar.e().getTextSize() && this.f6445a.getTextScaleX() == aVar.e().getTextScaleX() && this.f6445a.getTextSkewX() == aVar.e().getTextSkewX() && this.f6445a.getLetterSpacing() == aVar.e().getLetterSpacing() && TextUtils.equals(this.f6445a.getFontFeatureSettings(), aVar.e().getFontFeatureSettings()) && this.f6445a.getFlags() == aVar.e().getFlags() && this.f6445a.getTextLocales().equals(aVar.e().getTextLocales())) {
                return this.f6445a.getTypeface() == null ? aVar.e().getTypeface() == null : this.f6445a.getTypeface().equals(aVar.e().getTypeface());
            }
            return false;
        }

        public int b() {
            return this.f6447c;
        }

        public int c() {
            return this.f6448d;
        }

        public TextDirectionHeuristic d() {
            return this.f6446b;
        }

        public TextPaint e() {
            return this.f6445a;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return a(aVar) && this.f6446b == aVar.d();
        }

        public int hashCode() {
            return h0.c.b(Float.valueOf(this.f6445a.getTextSize()), Float.valueOf(this.f6445a.getTextScaleX()), Float.valueOf(this.f6445a.getTextSkewX()), Float.valueOf(this.f6445a.getLetterSpacing()), Integer.valueOf(this.f6445a.getFlags()), this.f6445a.getTextLocales(), this.f6445a.getTypeface(), Boolean.valueOf(this.f6445a.isElegantTextHeight()), this.f6446b, Integer.valueOf(this.f6447c), Integer.valueOf(this.f6448d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f6445a.getTextSize());
            sb.append(", textScaleX=" + this.f6445a.getTextScaleX());
            sb.append(", textSkewX=" + this.f6445a.getTextSkewX());
            sb.append(", letterSpacing=" + this.f6445a.getLetterSpacing());
            sb.append(", elegantTextHeight=" + this.f6445a.isElegantTextHeight());
            sb.append(", textLocale=" + this.f6445a.getTextLocales());
            sb.append(", typeface=" + this.f6445a.getTypeface());
            sb.append(", variationSettings=" + this.f6445a.getFontVariationSettings());
            sb.append(", textDir=" + this.f6446b);
            sb.append(", breakStrategy=" + this.f6447c);
            sb.append(", hyphenationFrequency=" + this.f6448d);
            sb.append("}");
            return sb.toString();
        }
    }

    public a a() {
        return this.f6443g;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f6442e;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i7) {
        return this.f6442e.charAt(i7);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f6442e.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f6442e.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f6442e.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i7, int i8, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.f6444h.getSpans(i7, i8, cls) : (T[]) this.f6442e.getSpans(i7, i8, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f6442e.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i7, int i8, Class cls) {
        return this.f6442e.nextSpanTransition(i7, i8, cls);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f6444h.removeSpan(obj);
        } else {
            this.f6442e.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i7, int i8, int i9) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f6444h.setSpan(obj, i7, i8, i9);
        } else {
            this.f6442e.setSpan(obj, i7, i8, i9);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i7, int i8) {
        return this.f6442e.subSequence(i7, i8);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f6442e.toString();
    }
}
