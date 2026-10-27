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
    private static final Object f2631b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Spannable f2632c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f2633d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final PrecomputedText f2634e;

    public a a() {
        return this.f2633d;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f2632c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i2) {
        return this.f2632c.charAt(i2);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f2632c.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f2632c.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f2632c.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i2, int i3, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.f2634e.getSpans(i2, i3, cls) : (T[]) this.f2632c.getSpans(i2, i3, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f2632c.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i2, int i3, Class cls) {
        return this.f2632c.nextSpanTransition(i2, i3, cls);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f2634e.removeSpan(obj);
        } else {
            this.f2632c.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i2, int i3, int i4) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f2634e.setSpan(obj, i2, i3, i4);
        } else {
            this.f2632c.setSpan(obj, i2, i3, i4);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i2, int i3) {
        return this.f2632c.subSequence(i2, i3);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f2632c.toString();
    }

    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final TextPaint f2635a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final TextDirectionHeuristic f2636b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f2637c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f2638d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final PrecomputedText.Params f2639e;

        /* JADX INFO: renamed from: b.g.i.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PrecomputedTextCompat.java */
        public static class C0043a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final TextPaint f2640a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private TextDirectionHeuristic f2641b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private int f2642c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private int f2643d;

            public C0043a(TextPaint textPaint) {
                this.f2640a = textPaint;
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 23) {
                    this.f2642c = 1;
                    this.f2643d = 1;
                } else {
                    this.f2643d = 0;
                    this.f2642c = 0;
                }
                if (i2 >= 18) {
                    this.f2641b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f2641b = null;
                }
            }

            public a a() {
                return new a(this.f2640a, this.f2641b, this.f2642c, this.f2643d);
            }

            public C0043a b(int i2) {
                this.f2642c = i2;
                return this;
            }

            public C0043a c(int i2) {
                this.f2643d = i2;
                return this;
            }

            public C0043a d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f2641b = textDirectionHeuristic;
                return this;
            }
        }

        @SuppressLint({"NewApi"})
        a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i2, int i3) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.f2639e = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i2).setHyphenationFrequency(i3).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.f2639e = null;
            }
            this.f2635a = textPaint;
            this.f2636b = textDirectionHeuristic;
            this.f2637c = i2;
            this.f2638d = i3;
        }

        public boolean a(a aVar) {
            int i2 = Build.VERSION.SDK_INT;
            if ((i2 >= 23 && (this.f2637c != aVar.b() || this.f2638d != aVar.c())) || this.f2635a.getTextSize() != aVar.e().getTextSize() || this.f2635a.getTextScaleX() != aVar.e().getTextScaleX() || this.f2635a.getTextSkewX() != aVar.e().getTextSkewX()) {
                return false;
            }
            if ((i2 >= 21 && (this.f2635a.getLetterSpacing() != aVar.e().getLetterSpacing() || !TextUtils.equals(this.f2635a.getFontFeatureSettings(), aVar.e().getFontFeatureSettings()))) || this.f2635a.getFlags() != aVar.e().getFlags()) {
                return false;
            }
            if (i2 >= 24) {
                if (!this.f2635a.getTextLocales().equals(aVar.e().getTextLocales())) {
                    return false;
                }
            } else if (i2 >= 17 && !this.f2635a.getTextLocale().equals(aVar.e().getTextLocale())) {
                return false;
            }
            return this.f2635a.getTypeface() == null ? aVar.e().getTypeface() == null : this.f2635a.getTypeface().equals(aVar.e().getTypeface());
        }

        public int b() {
            return this.f2637c;
        }

        public int c() {
            return this.f2638d;
        }

        public TextDirectionHeuristic d() {
            return this.f2636b;
        }

        public TextPaint e() {
            return this.f2635a;
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
                return Build.VERSION.SDK_INT < 18 || this.f2636b == aVar.d();
            }
            return false;
        }

        public int hashCode() {
            int i2 = Build.VERSION.SDK_INT;
            return i2 >= 24 ? b.g.j.c.b(Float.valueOf(this.f2635a.getTextSize()), Float.valueOf(this.f2635a.getTextScaleX()), Float.valueOf(this.f2635a.getTextSkewX()), Float.valueOf(this.f2635a.getLetterSpacing()), Integer.valueOf(this.f2635a.getFlags()), this.f2635a.getTextLocales(), this.f2635a.getTypeface(), Boolean.valueOf(this.f2635a.isElegantTextHeight()), this.f2636b, Integer.valueOf(this.f2637c), Integer.valueOf(this.f2638d)) : i2 >= 21 ? b.g.j.c.b(Float.valueOf(this.f2635a.getTextSize()), Float.valueOf(this.f2635a.getTextScaleX()), Float.valueOf(this.f2635a.getTextSkewX()), Float.valueOf(this.f2635a.getLetterSpacing()), Integer.valueOf(this.f2635a.getFlags()), this.f2635a.getTextLocale(), this.f2635a.getTypeface(), Boolean.valueOf(this.f2635a.isElegantTextHeight()), this.f2636b, Integer.valueOf(this.f2637c), Integer.valueOf(this.f2638d)) : i2 >= 18 ? b.g.j.c.b(Float.valueOf(this.f2635a.getTextSize()), Float.valueOf(this.f2635a.getTextScaleX()), Float.valueOf(this.f2635a.getTextSkewX()), Integer.valueOf(this.f2635a.getFlags()), this.f2635a.getTextLocale(), this.f2635a.getTypeface(), this.f2636b, Integer.valueOf(this.f2637c), Integer.valueOf(this.f2638d)) : i2 >= 17 ? b.g.j.c.b(Float.valueOf(this.f2635a.getTextSize()), Float.valueOf(this.f2635a.getTextScaleX()), Float.valueOf(this.f2635a.getTextSkewX()), Integer.valueOf(this.f2635a.getFlags()), this.f2635a.getTextLocale(), this.f2635a.getTypeface(), this.f2636b, Integer.valueOf(this.f2637c), Integer.valueOf(this.f2638d)) : b.g.j.c.b(Float.valueOf(this.f2635a.getTextSize()), Float.valueOf(this.f2635a.getTextScaleX()), Float.valueOf(this.f2635a.getTextSkewX()), Integer.valueOf(this.f2635a.getFlags()), this.f2635a.getTypeface(), this.f2636b, Integer.valueOf(this.f2637c), Integer.valueOf(this.f2638d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f2635a.getTextSize());
            sb.append(", textScaleX=" + this.f2635a.getTextScaleX());
            sb.append(", textSkewX=" + this.f2635a.getTextSkewX());
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 21) {
                sb.append(", letterSpacing=" + this.f2635a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f2635a.isElegantTextHeight());
            }
            if (i2 >= 24) {
                sb.append(", textLocale=" + this.f2635a.getTextLocales());
            } else if (i2 >= 17) {
                sb.append(", textLocale=" + this.f2635a.getTextLocale());
            }
            sb.append(", typeface=" + this.f2635a.getTypeface());
            if (i2 >= 26) {
                sb.append(", variationSettings=" + this.f2635a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f2636b);
            sb.append(", breakStrategy=" + this.f2637c);
            sb.append(", hyphenationFrequency=" + this.f2638d);
            sb.append("}");
            return sb.toString();
        }

        public a(PrecomputedText.Params params) {
            this.f2635a = params.getTextPaint();
            this.f2636b = params.getTextDirection();
            this.f2637c = params.getBreakStrategy();
            this.f2638d = params.getHyphenationFrequency();
            this.f2639e = Build.VERSION.SDK_INT < 29 ? null : params;
        }
    }
}
