package b.g.i;

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
    private static final Object f1909b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Spannable f1910c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f1911d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final PrecomputedText f1912e;

    public a a() {
        return this.f1911d;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f1910c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.f1910c.charAt(i);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f1910c.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f1910c.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f1910c.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.f1912e.getSpans(i, i2, cls) : (T[]) this.f1910c.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f1910c.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f1910c.nextSpanTransition(i, i2, cls);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f1912e.removeSpan(obj);
        } else {
            this.f1910c.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i, int i2, int i3) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f1912e.setSpan(obj, i, i2, i3);
        } else {
            this.f1910c.setSpan(obj, i, i2, i3);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.f1910c.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f1910c.toString();
    }

    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final TextPaint f1913a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final TextDirectionHeuristic f1914b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f1915c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f1916d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final PrecomputedText.Params f1917e;

        /* JADX INFO: renamed from: b.g.i.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PrecomputedTextCompat.java */
        public static class C0034a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final TextPaint f1918a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private TextDirectionHeuristic f1919b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private int f1920c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private int f1921d;

            public C0034a(TextPaint textPaint) {
                this.f1918a = textPaint;
                int i = Build.VERSION.SDK_INT;
                if (i >= 23) {
                    this.f1920c = 1;
                    this.f1921d = 1;
                } else {
                    this.f1921d = 0;
                    this.f1920c = 0;
                }
                if (i >= 18) {
                    this.f1919b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f1919b = null;
                }
            }

            public a a() {
                return new a(this.f1918a, this.f1919b, this.f1920c, this.f1921d);
            }

            public C0034a b(int i) {
                this.f1920c = i;
                return this;
            }

            public C0034a c(int i) {
                this.f1921d = i;
                return this;
            }

            public C0034a d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f1919b = textDirectionHeuristic;
                return this;
            }
        }

        @SuppressLint({"NewApi"})
        a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.f1917e = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.f1917e = null;
            }
            this.f1913a = textPaint;
            this.f1914b = textDirectionHeuristic;
            this.f1915c = i;
            this.f1916d = i2;
        }

        public boolean a(a aVar) {
            int i = Build.VERSION.SDK_INT;
            if ((i >= 23 && (this.f1915c != aVar.b() || this.f1916d != aVar.c())) || this.f1913a.getTextSize() != aVar.e().getTextSize() || this.f1913a.getTextScaleX() != aVar.e().getTextScaleX() || this.f1913a.getTextSkewX() != aVar.e().getTextSkewX()) {
                return false;
            }
            if ((i >= 21 && (this.f1913a.getLetterSpacing() != aVar.e().getLetterSpacing() || !TextUtils.equals(this.f1913a.getFontFeatureSettings(), aVar.e().getFontFeatureSettings()))) || this.f1913a.getFlags() != aVar.e().getFlags()) {
                return false;
            }
            if (i >= 24) {
                if (!this.f1913a.getTextLocales().equals(aVar.e().getTextLocales())) {
                    return false;
                }
            } else if (i >= 17 && !this.f1913a.getTextLocale().equals(aVar.e().getTextLocale())) {
                return false;
            }
            return this.f1913a.getTypeface() == null ? aVar.e().getTypeface() == null : this.f1913a.getTypeface().equals(aVar.e().getTypeface());
        }

        public int b() {
            return this.f1915c;
        }

        public int c() {
            return this.f1916d;
        }

        public TextDirectionHeuristic d() {
            return this.f1914b;
        }

        public TextPaint e() {
            return this.f1913a;
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
                return Build.VERSION.SDK_INT < 18 || this.f1914b == aVar.d();
            }
            return false;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            return i >= 24 ? b.g.j.c.b(Float.valueOf(this.f1913a.getTextSize()), Float.valueOf(this.f1913a.getTextScaleX()), Float.valueOf(this.f1913a.getTextSkewX()), Float.valueOf(this.f1913a.getLetterSpacing()), Integer.valueOf(this.f1913a.getFlags()), this.f1913a.getTextLocales(), this.f1913a.getTypeface(), Boolean.valueOf(this.f1913a.isElegantTextHeight()), this.f1914b, Integer.valueOf(this.f1915c), Integer.valueOf(this.f1916d)) : i >= 21 ? b.g.j.c.b(Float.valueOf(this.f1913a.getTextSize()), Float.valueOf(this.f1913a.getTextScaleX()), Float.valueOf(this.f1913a.getTextSkewX()), Float.valueOf(this.f1913a.getLetterSpacing()), Integer.valueOf(this.f1913a.getFlags()), this.f1913a.getTextLocale(), this.f1913a.getTypeface(), Boolean.valueOf(this.f1913a.isElegantTextHeight()), this.f1914b, Integer.valueOf(this.f1915c), Integer.valueOf(this.f1916d)) : i >= 18 ? b.g.j.c.b(Float.valueOf(this.f1913a.getTextSize()), Float.valueOf(this.f1913a.getTextScaleX()), Float.valueOf(this.f1913a.getTextSkewX()), Integer.valueOf(this.f1913a.getFlags()), this.f1913a.getTextLocale(), this.f1913a.getTypeface(), this.f1914b, Integer.valueOf(this.f1915c), Integer.valueOf(this.f1916d)) : i >= 17 ? b.g.j.c.b(Float.valueOf(this.f1913a.getTextSize()), Float.valueOf(this.f1913a.getTextScaleX()), Float.valueOf(this.f1913a.getTextSkewX()), Integer.valueOf(this.f1913a.getFlags()), this.f1913a.getTextLocale(), this.f1913a.getTypeface(), this.f1914b, Integer.valueOf(this.f1915c), Integer.valueOf(this.f1916d)) : b.g.j.c.b(Float.valueOf(this.f1913a.getTextSize()), Float.valueOf(this.f1913a.getTextScaleX()), Float.valueOf(this.f1913a.getTextSkewX()), Integer.valueOf(this.f1913a.getFlags()), this.f1913a.getTypeface(), this.f1914b, Integer.valueOf(this.f1915c), Integer.valueOf(this.f1916d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f1913a.getTextSize());
            sb.append(", textScaleX=" + this.f1913a.getTextScaleX());
            sb.append(", textSkewX=" + this.f1913a.getTextSkewX());
            int i = Build.VERSION.SDK_INT;
            if (i >= 21) {
                sb.append(", letterSpacing=" + this.f1913a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f1913a.isElegantTextHeight());
            }
            if (i >= 24) {
                sb.append(", textLocale=" + this.f1913a.getTextLocales());
            } else if (i >= 17) {
                sb.append(", textLocale=" + this.f1913a.getTextLocale());
            }
            sb.append(", typeface=" + this.f1913a.getTypeface());
            if (i >= 26) {
                sb.append(", variationSettings=" + this.f1913a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f1914b);
            sb.append(", breakStrategy=" + this.f1915c);
            sb.append(", hyphenationFrequency=" + this.f1916d);
            sb.append("}");
            return sb.toString();
        }

        public a(PrecomputedText.Params params) {
            this.f1913a = params.getTextPaint();
            this.f1914b = params.getTextDirection();
            this.f1915c = params.getBreakStrategy();
            this.f1916d = params.getHyphenationFrequency();
            this.f1917e = Build.VERSION.SDK_INT < 29 ? null : params;
        }
    }
}
