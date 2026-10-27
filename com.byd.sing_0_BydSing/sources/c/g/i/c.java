package c.g.i;

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
    private static final Object f2245b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Spannable f2246c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f2247d;
    private final PrecomputedText e;

    public a a() {
        return this.f2247d;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f2246c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.f2246c.charAt(i);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f2246c.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f2246c.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f2246c.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.e.getSpans(i, i2, cls) : (T[]) this.f2246c.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f2246c.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f2246c.nextSpanTransition(i, i2, cls);
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
            this.f2246c.removeSpan(obj);
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
            this.f2246c.setSpan(obj, i, i2, i3);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.f2246c.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f2246c.toString();
    }

    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final TextPaint f2248a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final TextDirectionHeuristic f2249b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f2250c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f2251d;
        final PrecomputedText.Params e;

        /* JADX INFO: renamed from: c.g.i.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PrecomputedTextCompat.java */
        public static class C0063a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final TextPaint f2252a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private TextDirectionHeuristic f2253b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private int f2254c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private int f2255d;

            public C0063a(TextPaint textPaint) {
                this.f2252a = textPaint;
                int i = Build.VERSION.SDK_INT;
                if (i >= 23) {
                    this.f2254c = 1;
                    this.f2255d = 1;
                } else {
                    this.f2255d = 0;
                    this.f2254c = 0;
                }
                if (i >= 18) {
                    this.f2253b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f2253b = null;
                }
            }

            public a a() {
                return new a(this.f2252a, this.f2253b, this.f2254c, this.f2255d);
            }

            public C0063a b(int i) {
                this.f2254c = i;
                return this;
            }

            public C0063a c(int i) {
                this.f2255d = i;
                return this;
            }

            public C0063a d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f2253b = textDirectionHeuristic;
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
            this.f2248a = textPaint;
            this.f2249b = textDirectionHeuristic;
            this.f2250c = i;
            this.f2251d = i2;
        }

        public boolean a(a aVar) {
            int i = Build.VERSION.SDK_INT;
            if ((i >= 23 && (this.f2250c != aVar.b() || this.f2251d != aVar.c())) || this.f2248a.getTextSize() != aVar.e().getTextSize() || this.f2248a.getTextScaleX() != aVar.e().getTextScaleX() || this.f2248a.getTextSkewX() != aVar.e().getTextSkewX()) {
                return false;
            }
            if ((i >= 21 && (this.f2248a.getLetterSpacing() != aVar.e().getLetterSpacing() || !TextUtils.equals(this.f2248a.getFontFeatureSettings(), aVar.e().getFontFeatureSettings()))) || this.f2248a.getFlags() != aVar.e().getFlags()) {
                return false;
            }
            if (i >= 24) {
                if (!this.f2248a.getTextLocales().equals(aVar.e().getTextLocales())) {
                    return false;
                }
            } else if (i >= 17 && !this.f2248a.getTextLocale().equals(aVar.e().getTextLocale())) {
                return false;
            }
            return this.f2248a.getTypeface() == null ? aVar.e().getTypeface() == null : this.f2248a.getTypeface().equals(aVar.e().getTypeface());
        }

        public int b() {
            return this.f2250c;
        }

        public int c() {
            return this.f2251d;
        }

        public TextDirectionHeuristic d() {
            return this.f2249b;
        }

        public TextPaint e() {
            return this.f2248a;
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
                return Build.VERSION.SDK_INT < 18 || this.f2249b == aVar.d();
            }
            return false;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            return i >= 24 ? c.g.j.c.b(Float.valueOf(this.f2248a.getTextSize()), Float.valueOf(this.f2248a.getTextScaleX()), Float.valueOf(this.f2248a.getTextSkewX()), Float.valueOf(this.f2248a.getLetterSpacing()), Integer.valueOf(this.f2248a.getFlags()), this.f2248a.getTextLocales(), this.f2248a.getTypeface(), Boolean.valueOf(this.f2248a.isElegantTextHeight()), this.f2249b, Integer.valueOf(this.f2250c), Integer.valueOf(this.f2251d)) : i >= 21 ? c.g.j.c.b(Float.valueOf(this.f2248a.getTextSize()), Float.valueOf(this.f2248a.getTextScaleX()), Float.valueOf(this.f2248a.getTextSkewX()), Float.valueOf(this.f2248a.getLetterSpacing()), Integer.valueOf(this.f2248a.getFlags()), this.f2248a.getTextLocale(), this.f2248a.getTypeface(), Boolean.valueOf(this.f2248a.isElegantTextHeight()), this.f2249b, Integer.valueOf(this.f2250c), Integer.valueOf(this.f2251d)) : i >= 18 ? c.g.j.c.b(Float.valueOf(this.f2248a.getTextSize()), Float.valueOf(this.f2248a.getTextScaleX()), Float.valueOf(this.f2248a.getTextSkewX()), Integer.valueOf(this.f2248a.getFlags()), this.f2248a.getTextLocale(), this.f2248a.getTypeface(), this.f2249b, Integer.valueOf(this.f2250c), Integer.valueOf(this.f2251d)) : i >= 17 ? c.g.j.c.b(Float.valueOf(this.f2248a.getTextSize()), Float.valueOf(this.f2248a.getTextScaleX()), Float.valueOf(this.f2248a.getTextSkewX()), Integer.valueOf(this.f2248a.getFlags()), this.f2248a.getTextLocale(), this.f2248a.getTypeface(), this.f2249b, Integer.valueOf(this.f2250c), Integer.valueOf(this.f2251d)) : c.g.j.c.b(Float.valueOf(this.f2248a.getTextSize()), Float.valueOf(this.f2248a.getTextScaleX()), Float.valueOf(this.f2248a.getTextSkewX()), Integer.valueOf(this.f2248a.getFlags()), this.f2248a.getTypeface(), this.f2249b, Integer.valueOf(this.f2250c), Integer.valueOf(this.f2251d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f2248a.getTextSize());
            sb.append(", textScaleX=" + this.f2248a.getTextScaleX());
            sb.append(", textSkewX=" + this.f2248a.getTextSkewX());
            int i = Build.VERSION.SDK_INT;
            if (i >= 21) {
                sb.append(", letterSpacing=" + this.f2248a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f2248a.isElegantTextHeight());
            }
            if (i >= 24) {
                sb.append(", textLocale=" + this.f2248a.getTextLocales());
            } else if (i >= 17) {
                sb.append(", textLocale=" + this.f2248a.getTextLocale());
            }
            sb.append(", typeface=" + this.f2248a.getTypeface());
            if (i >= 26) {
                sb.append(", variationSettings=" + this.f2248a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f2249b);
            sb.append(", breakStrategy=" + this.f2250c);
            sb.append(", hyphenationFrequency=" + this.f2251d);
            sb.append("}");
            return sb.toString();
        }

        public a(PrecomputedText.Params params) {
            this.f2248a = params.getTextPaint();
            this.f2249b = params.getTextDirection();
            this.f2250c = params.getBreakStrategy();
            this.f2251d = params.getHyphenationFrequency();
            this.e = Build.VERSION.SDK_INT < 29 ? null : params;
        }
    }
}
