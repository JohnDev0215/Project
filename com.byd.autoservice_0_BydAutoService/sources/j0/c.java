package j0;

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
    public static final Object f3413b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Spannable f3414c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f3415d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final PrecomputedText f3416e;

    public a a() {
        return this.f3415d;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f3414c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i3) {
        return this.f3414c.charAt(i3);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f3414c.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f3414c.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f3414c.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i3, int i4, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.f3416e.getSpans(i3, i4, cls) : (T[]) this.f3414c.getSpans(i3, i4, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f3414c.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i3, int i4, Class cls) {
        return this.f3414c.nextSpanTransition(i3, i4, cls);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f3416e.removeSpan(obj);
        } else {
            this.f3414c.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i3, int i4, int i5) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f3416e.setSpan(obj, i3, i4, i5);
        } else {
            this.f3414c.setSpan(obj, i3, i4, i5);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i3, int i4) {
        return this.f3414c.subSequence(i3, i4);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f3414c.toString();
    }

    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextPaint f3417a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextDirectionHeuristic f3418b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f3419c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f3420d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final PrecomputedText.Params f3421e;

        /* JADX INFO: renamed from: j0.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PrecomputedTextCompat.java */
        public static class C0025a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final TextPaint f3422a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public TextDirectionHeuristic f3423b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f3424c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f3425d;

            public C0025a(TextPaint textPaint) {
                this.f3422a = textPaint;
                int i3 = Build.VERSION.SDK_INT;
                if (i3 >= 23) {
                    this.f3424c = 1;
                    this.f3425d = 1;
                } else {
                    this.f3425d = 0;
                    this.f3424c = 0;
                }
                if (i3 >= 18) {
                    this.f3423b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f3423b = null;
                }
            }

            public a a() {
                return new a(this.f3422a, this.f3423b, this.f3424c, this.f3425d);
            }

            public C0025a b(int i3) {
                this.f3424c = i3;
                return this;
            }

            public C0025a c(int i3) {
                this.f3425d = i3;
                return this;
            }

            public C0025a d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f3423b = textDirectionHeuristic;
                return this;
            }
        }

        @SuppressLint({"NewApi"})
        public a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i3, int i4) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.f3421e = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i3).setHyphenationFrequency(i4).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.f3421e = null;
            }
            this.f3417a = textPaint;
            this.f3418b = textDirectionHeuristic;
            this.f3419c = i3;
            this.f3420d = i4;
        }

        public boolean a(a aVar) {
            int i3 = Build.VERSION.SDK_INT;
            if ((i3 >= 23 && (this.f3419c != aVar.b() || this.f3420d != aVar.c())) || this.f3417a.getTextSize() != aVar.e().getTextSize() || this.f3417a.getTextScaleX() != aVar.e().getTextScaleX() || this.f3417a.getTextSkewX() != aVar.e().getTextSkewX()) {
                return false;
            }
            if ((i3 >= 21 && (this.f3417a.getLetterSpacing() != aVar.e().getLetterSpacing() || !TextUtils.equals(this.f3417a.getFontFeatureSettings(), aVar.e().getFontFeatureSettings()))) || this.f3417a.getFlags() != aVar.e().getFlags()) {
                return false;
            }
            if (i3 >= 24) {
                if (!this.f3417a.getTextLocales().equals(aVar.e().getTextLocales())) {
                    return false;
                }
            } else if (i3 >= 17 && !this.f3417a.getTextLocale().equals(aVar.e().getTextLocale())) {
                return false;
            }
            return this.f3417a.getTypeface() == null ? aVar.e().getTypeface() == null : this.f3417a.getTypeface().equals(aVar.e().getTypeface());
        }

        public int b() {
            return this.f3419c;
        }

        public int c() {
            return this.f3420d;
        }

        public TextDirectionHeuristic d() {
            return this.f3418b;
        }

        public TextPaint e() {
            return this.f3417a;
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
                return Build.VERSION.SDK_INT < 18 || this.f3418b == aVar.d();
            }
            return false;
        }

        public int hashCode() {
            int i3 = Build.VERSION.SDK_INT;
            return i3 >= 24 ? k0.c.b(Float.valueOf(this.f3417a.getTextSize()), Float.valueOf(this.f3417a.getTextScaleX()), Float.valueOf(this.f3417a.getTextSkewX()), Float.valueOf(this.f3417a.getLetterSpacing()), Integer.valueOf(this.f3417a.getFlags()), this.f3417a.getTextLocales(), this.f3417a.getTypeface(), Boolean.valueOf(this.f3417a.isElegantTextHeight()), this.f3418b, Integer.valueOf(this.f3419c), Integer.valueOf(this.f3420d)) : i3 >= 21 ? k0.c.b(Float.valueOf(this.f3417a.getTextSize()), Float.valueOf(this.f3417a.getTextScaleX()), Float.valueOf(this.f3417a.getTextSkewX()), Float.valueOf(this.f3417a.getLetterSpacing()), Integer.valueOf(this.f3417a.getFlags()), this.f3417a.getTextLocale(), this.f3417a.getTypeface(), Boolean.valueOf(this.f3417a.isElegantTextHeight()), this.f3418b, Integer.valueOf(this.f3419c), Integer.valueOf(this.f3420d)) : i3 >= 18 ? k0.c.b(Float.valueOf(this.f3417a.getTextSize()), Float.valueOf(this.f3417a.getTextScaleX()), Float.valueOf(this.f3417a.getTextSkewX()), Integer.valueOf(this.f3417a.getFlags()), this.f3417a.getTextLocale(), this.f3417a.getTypeface(), this.f3418b, Integer.valueOf(this.f3419c), Integer.valueOf(this.f3420d)) : i3 >= 17 ? k0.c.b(Float.valueOf(this.f3417a.getTextSize()), Float.valueOf(this.f3417a.getTextScaleX()), Float.valueOf(this.f3417a.getTextSkewX()), Integer.valueOf(this.f3417a.getFlags()), this.f3417a.getTextLocale(), this.f3417a.getTypeface(), this.f3418b, Integer.valueOf(this.f3419c), Integer.valueOf(this.f3420d)) : k0.c.b(Float.valueOf(this.f3417a.getTextSize()), Float.valueOf(this.f3417a.getTextScaleX()), Float.valueOf(this.f3417a.getTextSkewX()), Integer.valueOf(this.f3417a.getFlags()), this.f3417a.getTypeface(), this.f3418b, Integer.valueOf(this.f3419c), Integer.valueOf(this.f3420d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f3417a.getTextSize());
            sb.append(", textScaleX=" + this.f3417a.getTextScaleX());
            sb.append(", textSkewX=" + this.f3417a.getTextSkewX());
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 21) {
                sb.append(", letterSpacing=" + this.f3417a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f3417a.isElegantTextHeight());
            }
            if (i3 >= 24) {
                sb.append(", textLocale=" + this.f3417a.getTextLocales());
            } else if (i3 >= 17) {
                sb.append(", textLocale=" + this.f3417a.getTextLocale());
            }
            sb.append(", typeface=" + this.f3417a.getTypeface());
            if (i3 >= 26) {
                sb.append(", variationSettings=" + this.f3417a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f3418b);
            sb.append(", breakStrategy=" + this.f3419c);
            sb.append(", hyphenationFrequency=" + this.f3420d);
            sb.append("}");
            return sb.toString();
        }

        public a(PrecomputedText.Params params) {
            this.f3417a = params.getTextPaint();
            this.f3418b = params.getTextDirection();
            this.f3419c = params.getBreakStrategy();
            this.f3420d = params.getHyphenationFrequency();
            this.f3421e = Build.VERSION.SDK_INT < 29 ? null : params;
        }
    }
}
