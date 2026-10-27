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
    private static final Object f2476b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Spannable f2477c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f2478d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final PrecomputedText f2479e;

    public a a() {
        return this.f2478d;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f2477c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.f2477c.charAt(i);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f2477c.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f2477c.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f2477c.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.f2479e.getSpans(i, i2, cls) : (T[]) this.f2477c.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f2477c.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f2477c.nextSpanTransition(i, i2, cls);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f2479e.removeSpan(obj);
        } else {
            this.f2477c.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i, int i2, int i3) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f2479e.setSpan(obj, i, i2, i3);
        } else {
            this.f2477c.setSpan(obj, i, i2, i3);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.f2477c.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f2477c.toString();
    }

    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final TextPaint f2480a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final TextDirectionHeuristic f2481b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f2482c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f2483d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final PrecomputedText.Params f2484e;

        /* JADX INFO: renamed from: b.g.i.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PrecomputedTextCompat.java */
        public static class C0037a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final TextPaint f2485a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private TextDirectionHeuristic f2486b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private int f2487c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private int f2488d;

            public C0037a(TextPaint textPaint) {
                this.f2485a = textPaint;
                int i = Build.VERSION.SDK_INT;
                if (i >= 23) {
                    this.f2487c = 1;
                    this.f2488d = 1;
                } else {
                    this.f2488d = 0;
                    this.f2487c = 0;
                }
                if (i >= 18) {
                    this.f2486b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f2486b = null;
                }
            }

            public a a() {
                return new a(this.f2485a, this.f2486b, this.f2487c, this.f2488d);
            }

            public C0037a b(int i) {
                this.f2487c = i;
                return this;
            }

            public C0037a c(int i) {
                this.f2488d = i;
                return this;
            }

            public C0037a d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f2486b = textDirectionHeuristic;
                return this;
            }
        }

        @SuppressLint({"NewApi"})
        a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.f2484e = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.f2484e = null;
            }
            this.f2480a = textPaint;
            this.f2481b = textDirectionHeuristic;
            this.f2482c = i;
            this.f2483d = i2;
        }

        public boolean a(a aVar) {
            int i = Build.VERSION.SDK_INT;
            if ((i >= 23 && (this.f2482c != aVar.b() || this.f2483d != aVar.c())) || this.f2480a.getTextSize() != aVar.e().getTextSize() || this.f2480a.getTextScaleX() != aVar.e().getTextScaleX() || this.f2480a.getTextSkewX() != aVar.e().getTextSkewX()) {
                return false;
            }
            if ((i >= 21 && (this.f2480a.getLetterSpacing() != aVar.e().getLetterSpacing() || !TextUtils.equals(this.f2480a.getFontFeatureSettings(), aVar.e().getFontFeatureSettings()))) || this.f2480a.getFlags() != aVar.e().getFlags()) {
                return false;
            }
            if (i >= 24) {
                if (!this.f2480a.getTextLocales().equals(aVar.e().getTextLocales())) {
                    return false;
                }
            } else if (i >= 17 && !this.f2480a.getTextLocale().equals(aVar.e().getTextLocale())) {
                return false;
            }
            return this.f2480a.getTypeface() == null ? aVar.e().getTypeface() == null : this.f2480a.getTypeface().equals(aVar.e().getTypeface());
        }

        public int b() {
            return this.f2482c;
        }

        public int c() {
            return this.f2483d;
        }

        public TextDirectionHeuristic d() {
            return this.f2481b;
        }

        public TextPaint e() {
            return this.f2480a;
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
                return Build.VERSION.SDK_INT < 18 || this.f2481b == aVar.d();
            }
            return false;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            return i >= 24 ? b.g.j.c.b(Float.valueOf(this.f2480a.getTextSize()), Float.valueOf(this.f2480a.getTextScaleX()), Float.valueOf(this.f2480a.getTextSkewX()), Float.valueOf(this.f2480a.getLetterSpacing()), Integer.valueOf(this.f2480a.getFlags()), this.f2480a.getTextLocales(), this.f2480a.getTypeface(), Boolean.valueOf(this.f2480a.isElegantTextHeight()), this.f2481b, Integer.valueOf(this.f2482c), Integer.valueOf(this.f2483d)) : i >= 21 ? b.g.j.c.b(Float.valueOf(this.f2480a.getTextSize()), Float.valueOf(this.f2480a.getTextScaleX()), Float.valueOf(this.f2480a.getTextSkewX()), Float.valueOf(this.f2480a.getLetterSpacing()), Integer.valueOf(this.f2480a.getFlags()), this.f2480a.getTextLocale(), this.f2480a.getTypeface(), Boolean.valueOf(this.f2480a.isElegantTextHeight()), this.f2481b, Integer.valueOf(this.f2482c), Integer.valueOf(this.f2483d)) : i >= 18 ? b.g.j.c.b(Float.valueOf(this.f2480a.getTextSize()), Float.valueOf(this.f2480a.getTextScaleX()), Float.valueOf(this.f2480a.getTextSkewX()), Integer.valueOf(this.f2480a.getFlags()), this.f2480a.getTextLocale(), this.f2480a.getTypeface(), this.f2481b, Integer.valueOf(this.f2482c), Integer.valueOf(this.f2483d)) : i >= 17 ? b.g.j.c.b(Float.valueOf(this.f2480a.getTextSize()), Float.valueOf(this.f2480a.getTextScaleX()), Float.valueOf(this.f2480a.getTextSkewX()), Integer.valueOf(this.f2480a.getFlags()), this.f2480a.getTextLocale(), this.f2480a.getTypeface(), this.f2481b, Integer.valueOf(this.f2482c), Integer.valueOf(this.f2483d)) : b.g.j.c.b(Float.valueOf(this.f2480a.getTextSize()), Float.valueOf(this.f2480a.getTextScaleX()), Float.valueOf(this.f2480a.getTextSkewX()), Integer.valueOf(this.f2480a.getFlags()), this.f2480a.getTypeface(), this.f2481b, Integer.valueOf(this.f2482c), Integer.valueOf(this.f2483d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f2480a.getTextSize());
            sb.append(", textScaleX=" + this.f2480a.getTextScaleX());
            sb.append(", textSkewX=" + this.f2480a.getTextSkewX());
            int i = Build.VERSION.SDK_INT;
            if (i >= 21) {
                sb.append(", letterSpacing=" + this.f2480a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f2480a.isElegantTextHeight());
            }
            if (i >= 24) {
                sb.append(", textLocale=" + this.f2480a.getTextLocales());
            } else if (i >= 17) {
                sb.append(", textLocale=" + this.f2480a.getTextLocale());
            }
            sb.append(", typeface=" + this.f2480a.getTypeface());
            if (i >= 26) {
                sb.append(", variationSettings=" + this.f2480a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f2481b);
            sb.append(", breakStrategy=" + this.f2482c);
            sb.append(", hyphenationFrequency=" + this.f2483d);
            sb.append("}");
            return sb.toString();
        }

        public a(PrecomputedText.Params params) {
            this.f2480a = params.getTextPaint();
            this.f2481b = params.getTextDirection();
            this.f2482c = params.getBreakStrategy();
            this.f2483d = params.getHyphenationFrequency();
            this.f2484e = Build.VERSION.SDK_INT < 29 ? null : params;
        }
    }
}
