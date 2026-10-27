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
    private static final Object f2265b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Spannable f2266c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f2267d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final PrecomputedText f2268e;

    public a a() {
        return this.f2267d;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f2266c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.f2266c.charAt(i);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f2266c.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f2266c.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f2266c.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.f2268e.getSpans(i, i2, cls) : (T[]) this.f2266c.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f2266c.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f2266c.nextSpanTransition(i, i2, cls);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f2268e.removeSpan(obj);
        } else {
            this.f2266c.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i, int i2, int i3) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f2268e.setSpan(obj, i, i2, i3);
        } else {
            this.f2266c.setSpan(obj, i, i2, i3);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.f2266c.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f2266c.toString();
    }

    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final TextPaint f2269a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final TextDirectionHeuristic f2270b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f2271c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f2272d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final PrecomputedText.Params f2273e;

        /* JADX INFO: renamed from: b.g.i.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PrecomputedTextCompat.java */
        public static class C0050a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final TextPaint f2274a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private TextDirectionHeuristic f2275b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private int f2276c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private int f2277d;

            public C0050a(TextPaint textPaint) {
                this.f2274a = textPaint;
                int i = Build.VERSION.SDK_INT;
                if (i >= 23) {
                    this.f2276c = 1;
                    this.f2277d = 1;
                } else {
                    this.f2277d = 0;
                    this.f2276c = 0;
                }
                if (i >= 18) {
                    this.f2275b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f2275b = null;
                }
            }

            public a a() {
                return new a(this.f2274a, this.f2275b, this.f2276c, this.f2277d);
            }

            public C0050a b(int i) {
                this.f2276c = i;
                return this;
            }

            public C0050a c(int i) {
                this.f2277d = i;
                return this;
            }

            public C0050a d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f2275b = textDirectionHeuristic;
                return this;
            }
        }

        @SuppressLint({"NewApi"})
        a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.f2273e = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.f2273e = null;
            }
            this.f2269a = textPaint;
            this.f2270b = textDirectionHeuristic;
            this.f2271c = i;
            this.f2272d = i2;
        }

        public boolean a(a aVar) {
            int i = Build.VERSION.SDK_INT;
            if ((i >= 23 && (this.f2271c != aVar.b() || this.f2272d != aVar.c())) || this.f2269a.getTextSize() != aVar.e().getTextSize() || this.f2269a.getTextScaleX() != aVar.e().getTextScaleX() || this.f2269a.getTextSkewX() != aVar.e().getTextSkewX()) {
                return false;
            }
            if ((i >= 21 && (this.f2269a.getLetterSpacing() != aVar.e().getLetterSpacing() || !TextUtils.equals(this.f2269a.getFontFeatureSettings(), aVar.e().getFontFeatureSettings()))) || this.f2269a.getFlags() != aVar.e().getFlags()) {
                return false;
            }
            if (i >= 24) {
                if (!this.f2269a.getTextLocales().equals(aVar.e().getTextLocales())) {
                    return false;
                }
            } else if (i >= 17 && !this.f2269a.getTextLocale().equals(aVar.e().getTextLocale())) {
                return false;
            }
            return this.f2269a.getTypeface() == null ? aVar.e().getTypeface() == null : this.f2269a.getTypeface().equals(aVar.e().getTypeface());
        }

        public int b() {
            return this.f2271c;
        }

        public int c() {
            return this.f2272d;
        }

        public TextDirectionHeuristic d() {
            return this.f2270b;
        }

        public TextPaint e() {
            return this.f2269a;
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
                return Build.VERSION.SDK_INT < 18 || this.f2270b == aVar.d();
            }
            return false;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            return i >= 24 ? b.g.j.c.b(Float.valueOf(this.f2269a.getTextSize()), Float.valueOf(this.f2269a.getTextScaleX()), Float.valueOf(this.f2269a.getTextSkewX()), Float.valueOf(this.f2269a.getLetterSpacing()), Integer.valueOf(this.f2269a.getFlags()), this.f2269a.getTextLocales(), this.f2269a.getTypeface(), Boolean.valueOf(this.f2269a.isElegantTextHeight()), this.f2270b, Integer.valueOf(this.f2271c), Integer.valueOf(this.f2272d)) : i >= 21 ? b.g.j.c.b(Float.valueOf(this.f2269a.getTextSize()), Float.valueOf(this.f2269a.getTextScaleX()), Float.valueOf(this.f2269a.getTextSkewX()), Float.valueOf(this.f2269a.getLetterSpacing()), Integer.valueOf(this.f2269a.getFlags()), this.f2269a.getTextLocale(), this.f2269a.getTypeface(), Boolean.valueOf(this.f2269a.isElegantTextHeight()), this.f2270b, Integer.valueOf(this.f2271c), Integer.valueOf(this.f2272d)) : i >= 18 ? b.g.j.c.b(Float.valueOf(this.f2269a.getTextSize()), Float.valueOf(this.f2269a.getTextScaleX()), Float.valueOf(this.f2269a.getTextSkewX()), Integer.valueOf(this.f2269a.getFlags()), this.f2269a.getTextLocale(), this.f2269a.getTypeface(), this.f2270b, Integer.valueOf(this.f2271c), Integer.valueOf(this.f2272d)) : i >= 17 ? b.g.j.c.b(Float.valueOf(this.f2269a.getTextSize()), Float.valueOf(this.f2269a.getTextScaleX()), Float.valueOf(this.f2269a.getTextSkewX()), Integer.valueOf(this.f2269a.getFlags()), this.f2269a.getTextLocale(), this.f2269a.getTypeface(), this.f2270b, Integer.valueOf(this.f2271c), Integer.valueOf(this.f2272d)) : b.g.j.c.b(Float.valueOf(this.f2269a.getTextSize()), Float.valueOf(this.f2269a.getTextScaleX()), Float.valueOf(this.f2269a.getTextSkewX()), Integer.valueOf(this.f2269a.getFlags()), this.f2269a.getTypeface(), this.f2270b, Integer.valueOf(this.f2271c), Integer.valueOf(this.f2272d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f2269a.getTextSize());
            sb.append(", textScaleX=" + this.f2269a.getTextScaleX());
            sb.append(", textSkewX=" + this.f2269a.getTextSkewX());
            int i = Build.VERSION.SDK_INT;
            if (i >= 21) {
                sb.append(", letterSpacing=" + this.f2269a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f2269a.isElegantTextHeight());
            }
            if (i >= 24) {
                sb.append(", textLocale=" + this.f2269a.getTextLocales());
            } else if (i >= 17) {
                sb.append(", textLocale=" + this.f2269a.getTextLocale());
            }
            sb.append(", typeface=" + this.f2269a.getTypeface());
            if (i >= 26) {
                sb.append(", variationSettings=" + this.f2269a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f2270b);
            sb.append(", breakStrategy=" + this.f2271c);
            sb.append(", hyphenationFrequency=" + this.f2272d);
            sb.append("}");
            return sb.toString();
        }

        public a(PrecomputedText.Params params) {
            this.f2269a = params.getTextPaint();
            this.f2270b = params.getTextDirection();
            this.f2271c = params.getBreakStrategy();
            this.f2272d = params.getHyphenationFrequency();
            this.f2273e = Build.VERSION.SDK_INT < 29 ? null : params;
        }
    }
}
