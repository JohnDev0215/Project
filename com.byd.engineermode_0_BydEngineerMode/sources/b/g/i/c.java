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
    private static final Object f1758b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Spannable f1759c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f1760d;
    private final PrecomputedText e;

    public a a() {
        return this.f1760d;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f1759c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.f1759c.charAt(i);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f1759c.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f1759c.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f1759c.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.e.getSpans(i, i2, cls) : (T[]) this.f1759c.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f1759c.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f1759c.nextSpanTransition(i, i2, cls);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.e.removeSpan(obj);
        } else {
            this.f1759c.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i, int i2, int i3) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.e.setSpan(obj, i, i2, i3);
        } else {
            this.f1759c.setSpan(obj, i, i2, i3);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.f1759c.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f1759c.toString();
    }

    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final TextPaint f1761a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final TextDirectionHeuristic f1762b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f1763c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f1764d;
        final PrecomputedText.Params e;

        /* JADX INFO: renamed from: b.g.i.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PrecomputedTextCompat.java */
        public static class C0038a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final TextPaint f1765a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private TextDirectionHeuristic f1766b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private int f1767c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private int f1768d;

            public C0038a(TextPaint textPaint) {
                this.f1765a = textPaint;
                int i = Build.VERSION.SDK_INT;
                if (i >= 23) {
                    this.f1767c = 1;
                    this.f1768d = 1;
                } else {
                    this.f1768d = 0;
                    this.f1767c = 0;
                }
                if (i >= 18) {
                    this.f1766b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f1766b = null;
                }
            }

            public a a() {
                return new a(this.f1765a, this.f1766b, this.f1767c, this.f1768d);
            }

            public C0038a b(int i) {
                this.f1767c = i;
                return this;
            }

            public C0038a c(int i) {
                this.f1768d = i;
                return this;
            }

            public C0038a d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f1766b = textDirectionHeuristic;
                return this;
            }
        }

        @SuppressLint({"NewApi"})
        a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.e = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.e = null;
            }
            this.f1761a = textPaint;
            this.f1762b = textDirectionHeuristic;
            this.f1763c = i;
            this.f1764d = i2;
        }

        public boolean a(a aVar) {
            int i = Build.VERSION.SDK_INT;
            if ((i >= 23 && (this.f1763c != aVar.b() || this.f1764d != aVar.c())) || this.f1761a.getTextSize() != aVar.e().getTextSize() || this.f1761a.getTextScaleX() != aVar.e().getTextScaleX() || this.f1761a.getTextSkewX() != aVar.e().getTextSkewX()) {
                return false;
            }
            if ((i >= 21 && (this.f1761a.getLetterSpacing() != aVar.e().getLetterSpacing() || !TextUtils.equals(this.f1761a.getFontFeatureSettings(), aVar.e().getFontFeatureSettings()))) || this.f1761a.getFlags() != aVar.e().getFlags()) {
                return false;
            }
            if (i >= 24) {
                if (!this.f1761a.getTextLocales().equals(aVar.e().getTextLocales())) {
                    return false;
                }
            } else if (i >= 17 && !this.f1761a.getTextLocale().equals(aVar.e().getTextLocale())) {
                return false;
            }
            return this.f1761a.getTypeface() == null ? aVar.e().getTypeface() == null : this.f1761a.getTypeface().equals(aVar.e().getTypeface());
        }

        public int b() {
            return this.f1763c;
        }

        public int c() {
            return this.f1764d;
        }

        public TextDirectionHeuristic d() {
            return this.f1762b;
        }

        public TextPaint e() {
            return this.f1761a;
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
                return Build.VERSION.SDK_INT < 18 || this.f1762b == aVar.d();
            }
            return false;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            return i >= 24 ? b.g.j.c.b(Float.valueOf(this.f1761a.getTextSize()), Float.valueOf(this.f1761a.getTextScaleX()), Float.valueOf(this.f1761a.getTextSkewX()), Float.valueOf(this.f1761a.getLetterSpacing()), Integer.valueOf(this.f1761a.getFlags()), this.f1761a.getTextLocales(), this.f1761a.getTypeface(), Boolean.valueOf(this.f1761a.isElegantTextHeight()), this.f1762b, Integer.valueOf(this.f1763c), Integer.valueOf(this.f1764d)) : i >= 21 ? b.g.j.c.b(Float.valueOf(this.f1761a.getTextSize()), Float.valueOf(this.f1761a.getTextScaleX()), Float.valueOf(this.f1761a.getTextSkewX()), Float.valueOf(this.f1761a.getLetterSpacing()), Integer.valueOf(this.f1761a.getFlags()), this.f1761a.getTextLocale(), this.f1761a.getTypeface(), Boolean.valueOf(this.f1761a.isElegantTextHeight()), this.f1762b, Integer.valueOf(this.f1763c), Integer.valueOf(this.f1764d)) : i >= 18 ? b.g.j.c.b(Float.valueOf(this.f1761a.getTextSize()), Float.valueOf(this.f1761a.getTextScaleX()), Float.valueOf(this.f1761a.getTextSkewX()), Integer.valueOf(this.f1761a.getFlags()), this.f1761a.getTextLocale(), this.f1761a.getTypeface(), this.f1762b, Integer.valueOf(this.f1763c), Integer.valueOf(this.f1764d)) : i >= 17 ? b.g.j.c.b(Float.valueOf(this.f1761a.getTextSize()), Float.valueOf(this.f1761a.getTextScaleX()), Float.valueOf(this.f1761a.getTextSkewX()), Integer.valueOf(this.f1761a.getFlags()), this.f1761a.getTextLocale(), this.f1761a.getTypeface(), this.f1762b, Integer.valueOf(this.f1763c), Integer.valueOf(this.f1764d)) : b.g.j.c.b(Float.valueOf(this.f1761a.getTextSize()), Float.valueOf(this.f1761a.getTextScaleX()), Float.valueOf(this.f1761a.getTextSkewX()), Integer.valueOf(this.f1761a.getFlags()), this.f1761a.getTypeface(), this.f1762b, Integer.valueOf(this.f1763c), Integer.valueOf(this.f1764d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f1761a.getTextSize());
            sb.append(", textScaleX=" + this.f1761a.getTextScaleX());
            sb.append(", textSkewX=" + this.f1761a.getTextSkewX());
            int i = Build.VERSION.SDK_INT;
            if (i >= 21) {
                sb.append(", letterSpacing=" + this.f1761a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f1761a.isElegantTextHeight());
            }
            if (i >= 24) {
                sb.append(", textLocale=" + this.f1761a.getTextLocales());
            } else if (i >= 17) {
                sb.append(", textLocale=" + this.f1761a.getTextLocale());
            }
            sb.append(", typeface=" + this.f1761a.getTypeface());
            if (i >= 26) {
                sb.append(", variationSettings=" + this.f1761a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f1762b);
            sb.append(", breakStrategy=" + this.f1763c);
            sb.append(", hyphenationFrequency=" + this.f1764d);
            sb.append("}");
            return sb.toString();
        }

        public a(PrecomputedText.Params params) {
            this.f1761a = params.getTextPaint();
            this.f1762b = params.getTextDirection();
            this.f1763c = params.getBreakStrategy();
            this.f1764d = params.getHyphenationFrequency();
            this.e = Build.VERSION.SDK_INT < 29 ? null : params;
        }
    }
}
