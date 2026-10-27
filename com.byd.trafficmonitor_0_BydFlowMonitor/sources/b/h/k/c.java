package b.h.k;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;

/* JADX INFO: compiled from: PrecomputedTextCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements Spannable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f2549b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Spannable f2550c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f2551d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final PrecomputedText f2552e;

    public a a() {
        return this.f2551d;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f2550c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i2) {
        return this.f2550c.charAt(i2);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f2550c.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f2550c.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f2550c.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i2, int i3, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.f2552e.getSpans(i2, i3, cls) : (T[]) this.f2550c.getSpans(i2, i3, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f2550c.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i2, int i3, Class cls) {
        return this.f2550c.nextSpanTransition(i2, i3, cls);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f2552e.removeSpan(obj);
        } else {
            this.f2550c.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i2, int i3, int i4) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f2552e.setSpan(obj, i2, i3, i4);
        } else {
            this.f2550c.setSpan(obj, i2, i3, i4);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i2, int i3) {
        return this.f2550c.subSequence(i2, i3);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f2550c.toString();
    }

    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextPaint f2553a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextDirectionHeuristic f2554b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f2555c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f2556d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final PrecomputedText.Params f2557e;

        /* JADX INFO: renamed from: b.h.k.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PrecomputedTextCompat.java */
        public static class C0040a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final TextPaint f2558a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public TextDirectionHeuristic f2559b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f2560c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f2561d;

            public C0040a(TextPaint textPaint) {
                this.f2558a = textPaint;
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 23) {
                    this.f2560c = 1;
                    this.f2561d = 1;
                } else {
                    this.f2561d = 0;
                    this.f2560c = 0;
                }
                if (i2 >= 18) {
                    this.f2559b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f2559b = null;
                }
            }

            public a a() {
                return new a(this.f2558a, this.f2559b, this.f2560c, this.f2561d);
            }

            public C0040a b(int i2) {
                this.f2560c = i2;
                return this;
            }

            public C0040a c(int i2) {
                this.f2561d = i2;
                return this;
            }

            public C0040a d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f2559b = textDirectionHeuristic;
                return this;
            }
        }

        @SuppressLint({"NewApi"})
        public a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i2, int i3) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.f2557e = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i2).setHyphenationFrequency(i3).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.f2557e = null;
            }
            this.f2553a = textPaint;
            this.f2554b = textDirectionHeuristic;
            this.f2555c = i2;
            this.f2556d = i3;
        }

        public boolean a(a aVar) {
            int i2 = Build.VERSION.SDK_INT;
            if ((i2 >= 23 && (this.f2555c != aVar.b() || this.f2556d != aVar.c())) || this.f2553a.getTextSize() != aVar.e().getTextSize() || this.f2553a.getTextScaleX() != aVar.e().getTextScaleX() || this.f2553a.getTextSkewX() != aVar.e().getTextSkewX()) {
                return false;
            }
            if ((i2 >= 21 && (this.f2553a.getLetterSpacing() != aVar.e().getLetterSpacing() || !TextUtils.equals(this.f2553a.getFontFeatureSettings(), aVar.e().getFontFeatureSettings()))) || this.f2553a.getFlags() != aVar.e().getFlags()) {
                return false;
            }
            if (i2 >= 24) {
                if (!this.f2553a.getTextLocales().equals(aVar.e().getTextLocales())) {
                    return false;
                }
            } else if (i2 >= 17 && !this.f2553a.getTextLocale().equals(aVar.e().getTextLocale())) {
                return false;
            }
            return this.f2553a.getTypeface() == null ? aVar.e().getTypeface() == null : this.f2553a.getTypeface().equals(aVar.e().getTypeface());
        }

        public int b() {
            return this.f2555c;
        }

        public int c() {
            return this.f2556d;
        }

        public TextDirectionHeuristic d() {
            return this.f2554b;
        }

        public TextPaint e() {
            return this.f2553a;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (a(aVar)) {
                return Build.VERSION.SDK_INT < 18 || this.f2554b == aVar.d();
            }
            return false;
        }

        public int hashCode() {
            int i2 = Build.VERSION.SDK_INT;
            return i2 >= 24 ? b.h.l.b.b(Float.valueOf(this.f2553a.getTextSize()), Float.valueOf(this.f2553a.getTextScaleX()), Float.valueOf(this.f2553a.getTextSkewX()), Float.valueOf(this.f2553a.getLetterSpacing()), Integer.valueOf(this.f2553a.getFlags()), this.f2553a.getTextLocales(), this.f2553a.getTypeface(), Boolean.valueOf(this.f2553a.isElegantTextHeight()), this.f2554b, Integer.valueOf(this.f2555c), Integer.valueOf(this.f2556d)) : i2 >= 21 ? b.h.l.b.b(Float.valueOf(this.f2553a.getTextSize()), Float.valueOf(this.f2553a.getTextScaleX()), Float.valueOf(this.f2553a.getTextSkewX()), Float.valueOf(this.f2553a.getLetterSpacing()), Integer.valueOf(this.f2553a.getFlags()), this.f2553a.getTextLocale(), this.f2553a.getTypeface(), Boolean.valueOf(this.f2553a.isElegantTextHeight()), this.f2554b, Integer.valueOf(this.f2555c), Integer.valueOf(this.f2556d)) : i2 >= 18 ? b.h.l.b.b(Float.valueOf(this.f2553a.getTextSize()), Float.valueOf(this.f2553a.getTextScaleX()), Float.valueOf(this.f2553a.getTextSkewX()), Integer.valueOf(this.f2553a.getFlags()), this.f2553a.getTextLocale(), this.f2553a.getTypeface(), this.f2554b, Integer.valueOf(this.f2555c), Integer.valueOf(this.f2556d)) : i2 >= 17 ? b.h.l.b.b(Float.valueOf(this.f2553a.getTextSize()), Float.valueOf(this.f2553a.getTextScaleX()), Float.valueOf(this.f2553a.getTextSkewX()), Integer.valueOf(this.f2553a.getFlags()), this.f2553a.getTextLocale(), this.f2553a.getTypeface(), this.f2554b, Integer.valueOf(this.f2555c), Integer.valueOf(this.f2556d)) : b.h.l.b.b(Float.valueOf(this.f2553a.getTextSize()), Float.valueOf(this.f2553a.getTextScaleX()), Float.valueOf(this.f2553a.getTextSkewX()), Integer.valueOf(this.f2553a.getFlags()), this.f2553a.getTypeface(), this.f2554b, Integer.valueOf(this.f2555c), Integer.valueOf(this.f2556d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f2553a.getTextSize());
            sb.append(", textScaleX=" + this.f2553a.getTextScaleX());
            sb.append(", textSkewX=" + this.f2553a.getTextSkewX());
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 21) {
                sb.append(", letterSpacing=" + this.f2553a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f2553a.isElegantTextHeight());
            }
            if (i2 >= 24) {
                sb.append(", textLocale=" + this.f2553a.getTextLocales());
            } else if (i2 >= 17) {
                sb.append(", textLocale=" + this.f2553a.getTextLocale());
            }
            sb.append(", typeface=" + this.f2553a.getTypeface());
            if (i2 >= 26) {
                sb.append(", variationSettings=" + this.f2553a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f2554b);
            sb.append(", breakStrategy=" + this.f2555c);
            sb.append(", hyphenationFrequency=" + this.f2556d);
            sb.append("}");
            return sb.toString();
        }

        public a(PrecomputedText.Params params) {
            this.f2553a = params.getTextPaint();
            this.f2554b = params.getTextDirection();
            this.f2555c = params.getBreakStrategy();
            this.f2556d = params.getHyphenationFrequency();
            this.f2557e = Build.VERSION.SDK_INT < 29 ? null : params;
        }
    }
}
