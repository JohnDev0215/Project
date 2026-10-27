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
    private static final Object f1678b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Spannable f1679c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f1680d;
    private final PrecomputedText e;

    public a a() {
        return this.f1680d;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f1679c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.f1679c.charAt(i);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f1679c.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f1679c.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f1679c.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.e.getSpans(i, i2, cls) : (T[]) this.f1679c.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f1679c.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f1679c.nextSpanTransition(i, i2, cls);
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
            this.f1679c.removeSpan(obj);
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
            this.f1679c.setSpan(obj, i, i2, i3);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.f1679c.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f1679c.toString();
    }

    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final TextPaint f1681a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final TextDirectionHeuristic f1682b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f1683c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f1684d;
        final PrecomputedText.Params e;

        /* JADX INFO: renamed from: b.g.i.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PrecomputedTextCompat.java */
        public static class C0036a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final TextPaint f1685a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private TextDirectionHeuristic f1686b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private int f1687c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private int f1688d;

            public C0036a(TextPaint textPaint) {
                this.f1685a = textPaint;
                int i = Build.VERSION.SDK_INT;
                if (i >= 23) {
                    this.f1687c = 1;
                    this.f1688d = 1;
                } else {
                    this.f1688d = 0;
                    this.f1687c = 0;
                }
                if (i >= 18) {
                    this.f1686b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f1686b = null;
                }
            }

            public a a() {
                return new a(this.f1685a, this.f1686b, this.f1687c, this.f1688d);
            }

            public C0036a b(int i) {
                this.f1687c = i;
                return this;
            }

            public C0036a c(int i) {
                this.f1688d = i;
                return this;
            }

            public C0036a d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f1686b = textDirectionHeuristic;
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
            this.f1681a = textPaint;
            this.f1682b = textDirectionHeuristic;
            this.f1683c = i;
            this.f1684d = i2;
        }

        public boolean a(a aVar) {
            int i = Build.VERSION.SDK_INT;
            if ((i >= 23 && (this.f1683c != aVar.b() || this.f1684d != aVar.c())) || this.f1681a.getTextSize() != aVar.e().getTextSize() || this.f1681a.getTextScaleX() != aVar.e().getTextScaleX() || this.f1681a.getTextSkewX() != aVar.e().getTextSkewX()) {
                return false;
            }
            if ((i >= 21 && (this.f1681a.getLetterSpacing() != aVar.e().getLetterSpacing() || !TextUtils.equals(this.f1681a.getFontFeatureSettings(), aVar.e().getFontFeatureSettings()))) || this.f1681a.getFlags() != aVar.e().getFlags()) {
                return false;
            }
            if (i >= 24) {
                if (!this.f1681a.getTextLocales().equals(aVar.e().getTextLocales())) {
                    return false;
                }
            } else if (i >= 17 && !this.f1681a.getTextLocale().equals(aVar.e().getTextLocale())) {
                return false;
            }
            return this.f1681a.getTypeface() == null ? aVar.e().getTypeface() == null : this.f1681a.getTypeface().equals(aVar.e().getTypeface());
        }

        public int b() {
            return this.f1683c;
        }

        public int c() {
            return this.f1684d;
        }

        public TextDirectionHeuristic d() {
            return this.f1682b;
        }

        public TextPaint e() {
            return this.f1681a;
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
                return Build.VERSION.SDK_INT < 18 || this.f1682b == aVar.d();
            }
            return false;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            return i >= 24 ? b.g.j.c.b(Float.valueOf(this.f1681a.getTextSize()), Float.valueOf(this.f1681a.getTextScaleX()), Float.valueOf(this.f1681a.getTextSkewX()), Float.valueOf(this.f1681a.getLetterSpacing()), Integer.valueOf(this.f1681a.getFlags()), this.f1681a.getTextLocales(), this.f1681a.getTypeface(), Boolean.valueOf(this.f1681a.isElegantTextHeight()), this.f1682b, Integer.valueOf(this.f1683c), Integer.valueOf(this.f1684d)) : i >= 21 ? b.g.j.c.b(Float.valueOf(this.f1681a.getTextSize()), Float.valueOf(this.f1681a.getTextScaleX()), Float.valueOf(this.f1681a.getTextSkewX()), Float.valueOf(this.f1681a.getLetterSpacing()), Integer.valueOf(this.f1681a.getFlags()), this.f1681a.getTextLocale(), this.f1681a.getTypeface(), Boolean.valueOf(this.f1681a.isElegantTextHeight()), this.f1682b, Integer.valueOf(this.f1683c), Integer.valueOf(this.f1684d)) : i >= 18 ? b.g.j.c.b(Float.valueOf(this.f1681a.getTextSize()), Float.valueOf(this.f1681a.getTextScaleX()), Float.valueOf(this.f1681a.getTextSkewX()), Integer.valueOf(this.f1681a.getFlags()), this.f1681a.getTextLocale(), this.f1681a.getTypeface(), this.f1682b, Integer.valueOf(this.f1683c), Integer.valueOf(this.f1684d)) : i >= 17 ? b.g.j.c.b(Float.valueOf(this.f1681a.getTextSize()), Float.valueOf(this.f1681a.getTextScaleX()), Float.valueOf(this.f1681a.getTextSkewX()), Integer.valueOf(this.f1681a.getFlags()), this.f1681a.getTextLocale(), this.f1681a.getTypeface(), this.f1682b, Integer.valueOf(this.f1683c), Integer.valueOf(this.f1684d)) : b.g.j.c.b(Float.valueOf(this.f1681a.getTextSize()), Float.valueOf(this.f1681a.getTextScaleX()), Float.valueOf(this.f1681a.getTextSkewX()), Integer.valueOf(this.f1681a.getFlags()), this.f1681a.getTypeface(), this.f1682b, Integer.valueOf(this.f1683c), Integer.valueOf(this.f1684d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f1681a.getTextSize());
            sb.append(", textScaleX=" + this.f1681a.getTextScaleX());
            sb.append(", textSkewX=" + this.f1681a.getTextSkewX());
            int i = Build.VERSION.SDK_INT;
            if (i >= 21) {
                sb.append(", letterSpacing=" + this.f1681a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f1681a.isElegantTextHeight());
            }
            if (i >= 24) {
                sb.append(", textLocale=" + this.f1681a.getTextLocales());
            } else if (i >= 17) {
                sb.append(", textLocale=" + this.f1681a.getTextLocale());
            }
            sb.append(", typeface=" + this.f1681a.getTypeface());
            if (i >= 26) {
                sb.append(", variationSettings=" + this.f1681a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f1682b);
            sb.append(", breakStrategy=" + this.f1683c);
            sb.append(", hyphenationFrequency=" + this.f1684d);
            sb.append("}");
            return sb.toString();
        }

        public a(PrecomputedText.Params params) {
            this.f1681a = params.getTextPaint();
            this.f1682b = params.getTextDirection();
            this.f1683c = params.getBreakStrategy();
            this.f1684d = params.getHyphenationFrequency();
            this.e = Build.VERSION.SDK_INT < 29 ? null : params;
        }
    }
}
