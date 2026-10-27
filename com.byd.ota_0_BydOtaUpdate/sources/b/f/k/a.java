package b.f.k;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import b.f.l.c;

/* JADX INFO: compiled from: PrecomputedTextCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements Spannable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Object f1716c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Spannable f1717d;
    public final C0026a s;
    public final PrecomputedText x;

    public C0026a a() {
        return this.s;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f1717d;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i2) {
        return this.f1717d.charAt(i2);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f1717d.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f1717d.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f1717d.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i2, int i3, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.x.getSpans(i2, i3, cls) : (T[]) this.f1717d.getSpans(i2, i3, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f1717d.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i2, int i3, Class cls) {
        return this.f1717d.nextSpanTransition(i2, i3, cls);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.x.removeSpan(obj);
        } else {
            this.f1717d.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i2, int i3, int i4) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.x.setSpan(obj, i2, i3, i4);
        } else {
            this.f1717d.setSpan(obj, i2, i3, i4);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i2, int i3) {
        return this.f1717d.subSequence(i2, i3);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f1717d.toString();
    }

    /* JADX INFO: renamed from: b.f.k.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class C0026a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextPaint f1718a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextDirectionHeuristic f1719b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1720c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f1721d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final PrecomputedText.Params f1722e;

        /* JADX INFO: renamed from: b.f.k.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PrecomputedTextCompat.java */
        public static class C0027a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final TextPaint f1723a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public TextDirectionHeuristic f1724b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f1725c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f1726d;

            public C0027a(TextPaint textPaint) {
                this.f1723a = textPaint;
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 23) {
                    this.f1725c = 1;
                    this.f1726d = 1;
                } else {
                    this.f1726d = 0;
                    this.f1725c = 0;
                }
                if (i2 >= 18) {
                    this.f1724b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f1724b = null;
                }
            }

            public C0026a a() {
                return new C0026a(this.f1723a, this.f1724b, this.f1725c, this.f1726d);
            }

            public C0027a b(int i2) {
                this.f1725c = i2;
                return this;
            }

            public C0027a c(int i2) {
                this.f1726d = i2;
                return this;
            }

            public C0027a d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f1724b = textDirectionHeuristic;
                return this;
            }
        }

        @SuppressLint({"NewApi"})
        public C0026a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i2, int i3) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.f1722e = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i2).setHyphenationFrequency(i3).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.f1722e = null;
            }
            this.f1718a = textPaint;
            this.f1719b = textDirectionHeuristic;
            this.f1720c = i2;
            this.f1721d = i3;
        }

        public boolean a(C0026a c0026a) {
            int i2 = Build.VERSION.SDK_INT;
            if ((i2 >= 23 && (this.f1720c != c0026a.b() || this.f1721d != c0026a.c())) || this.f1718a.getTextSize() != c0026a.e().getTextSize() || this.f1718a.getTextScaleX() != c0026a.e().getTextScaleX() || this.f1718a.getTextSkewX() != c0026a.e().getTextSkewX()) {
                return false;
            }
            if ((i2 >= 21 && (this.f1718a.getLetterSpacing() != c0026a.e().getLetterSpacing() || !TextUtils.equals(this.f1718a.getFontFeatureSettings(), c0026a.e().getFontFeatureSettings()))) || this.f1718a.getFlags() != c0026a.e().getFlags()) {
                return false;
            }
            if (i2 >= 24) {
                if (!this.f1718a.getTextLocales().equals(c0026a.e().getTextLocales())) {
                    return false;
                }
            } else if (i2 >= 17 && !this.f1718a.getTextLocale().equals(c0026a.e().getTextLocale())) {
                return false;
            }
            return this.f1718a.getTypeface() == null ? c0026a.e().getTypeface() == null : this.f1718a.getTypeface().equals(c0026a.e().getTypeface());
        }

        public int b() {
            return this.f1720c;
        }

        public int c() {
            return this.f1721d;
        }

        public TextDirectionHeuristic d() {
            return this.f1719b;
        }

        public TextPaint e() {
            return this.f1718a;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof C0026a)) {
                return false;
            }
            C0026a c0026a = (C0026a) obj;
            if (a(c0026a)) {
                return Build.VERSION.SDK_INT < 18 || this.f1719b == c0026a.d();
            }
            return false;
        }

        public int hashCode() {
            int i2 = Build.VERSION.SDK_INT;
            return i2 >= 24 ? c.b(Float.valueOf(this.f1718a.getTextSize()), Float.valueOf(this.f1718a.getTextScaleX()), Float.valueOf(this.f1718a.getTextSkewX()), Float.valueOf(this.f1718a.getLetterSpacing()), Integer.valueOf(this.f1718a.getFlags()), this.f1718a.getTextLocales(), this.f1718a.getTypeface(), Boolean.valueOf(this.f1718a.isElegantTextHeight()), this.f1719b, Integer.valueOf(this.f1720c), Integer.valueOf(this.f1721d)) : i2 >= 21 ? c.b(Float.valueOf(this.f1718a.getTextSize()), Float.valueOf(this.f1718a.getTextScaleX()), Float.valueOf(this.f1718a.getTextSkewX()), Float.valueOf(this.f1718a.getLetterSpacing()), Integer.valueOf(this.f1718a.getFlags()), this.f1718a.getTextLocale(), this.f1718a.getTypeface(), Boolean.valueOf(this.f1718a.isElegantTextHeight()), this.f1719b, Integer.valueOf(this.f1720c), Integer.valueOf(this.f1721d)) : i2 >= 18 ? c.b(Float.valueOf(this.f1718a.getTextSize()), Float.valueOf(this.f1718a.getTextScaleX()), Float.valueOf(this.f1718a.getTextSkewX()), Integer.valueOf(this.f1718a.getFlags()), this.f1718a.getTextLocale(), this.f1718a.getTypeface(), this.f1719b, Integer.valueOf(this.f1720c), Integer.valueOf(this.f1721d)) : i2 >= 17 ? c.b(Float.valueOf(this.f1718a.getTextSize()), Float.valueOf(this.f1718a.getTextScaleX()), Float.valueOf(this.f1718a.getTextSkewX()), Integer.valueOf(this.f1718a.getFlags()), this.f1718a.getTextLocale(), this.f1718a.getTypeface(), this.f1719b, Integer.valueOf(this.f1720c), Integer.valueOf(this.f1721d)) : c.b(Float.valueOf(this.f1718a.getTextSize()), Float.valueOf(this.f1718a.getTextScaleX()), Float.valueOf(this.f1718a.getTextSkewX()), Integer.valueOf(this.f1718a.getFlags()), this.f1718a.getTypeface(), this.f1719b, Integer.valueOf(this.f1720c), Integer.valueOf(this.f1721d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f1718a.getTextSize());
            sb.append(", textScaleX=" + this.f1718a.getTextScaleX());
            sb.append(", textSkewX=" + this.f1718a.getTextSkewX());
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 21) {
                sb.append(", letterSpacing=" + this.f1718a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f1718a.isElegantTextHeight());
            }
            if (i2 >= 24) {
                sb.append(", textLocale=" + this.f1718a.getTextLocales());
            } else if (i2 >= 17) {
                sb.append(", textLocale=" + this.f1718a.getTextLocale());
            }
            sb.append(", typeface=" + this.f1718a.getTypeface());
            if (i2 >= 26) {
                sb.append(", variationSettings=" + this.f1718a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f1719b);
            sb.append(", breakStrategy=" + this.f1720c);
            sb.append(", hyphenationFrequency=" + this.f1721d);
            sb.append("}");
            return sb.toString();
        }

        public C0026a(PrecomputedText.Params params) {
            this.f1718a = params.getTextPaint();
            this.f1719b = params.getTextDirection();
            this.f1720c = params.getBreakStrategy();
            this.f1721d = params.getHyphenationFrequency();
            this.f1722e = Build.VERSION.SDK_INT < 29 ? null : params;
        }
    }
}
