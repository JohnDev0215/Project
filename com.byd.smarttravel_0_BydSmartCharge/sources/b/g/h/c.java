package b.g.h;

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
    public static final Object f1061b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Spannable f1062c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a f1063d;
    public final PrecomputedText e;

    public a a() {
        return this.f1063d;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f1062c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.f1062c.charAt(i);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f1062c.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f1062c.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f1062c.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.e.getSpans(i, i2, cls) : (T[]) this.f1062c.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f1062c.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f1062c.nextSpanTransition(i, i2, cls);
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
            this.f1062c.removeSpan(obj);
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
            this.f1062c.setSpan(obj, i, i2, i3);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.f1062c.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f1062c.toString();
    }

    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextPaint f1064a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextDirectionHeuristic f1065b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f1066c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f1067d;
        public final PrecomputedText.Params e;

        /* JADX INFO: renamed from: b.g.h.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PrecomputedTextCompat.java */
        public static class C0020a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final TextPaint f1068a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public TextDirectionHeuristic f1069b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f1070c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f1071d;

            public C0020a(TextPaint textPaint) {
                this.f1068a = textPaint;
                int i = Build.VERSION.SDK_INT;
                if (i >= 23) {
                    this.f1070c = 1;
                    this.f1071d = 1;
                } else {
                    this.f1071d = 0;
                    this.f1070c = 0;
                }
                if (i >= 18) {
                    this.f1069b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f1069b = null;
                }
            }

            public a a() {
                return new a(this.f1068a, this.f1069b, this.f1070c, this.f1071d);
            }

            public C0020a b(int i) {
                this.f1070c = i;
                return this;
            }

            public C0020a c(int i) {
                this.f1071d = i;
                return this;
            }

            public C0020a d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f1069b = textDirectionHeuristic;
                return this;
            }
        }

        @SuppressLint({"NewApi"})
        public a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.e = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.e = null;
            }
            this.f1064a = textPaint;
            this.f1065b = textDirectionHeuristic;
            this.f1066c = i;
            this.f1067d = i2;
        }

        public boolean a(a aVar) {
            int i = Build.VERSION.SDK_INT;
            if ((i >= 23 && (this.f1066c != aVar.b() || this.f1067d != aVar.c())) || this.f1064a.getTextSize() != aVar.e().getTextSize() || this.f1064a.getTextScaleX() != aVar.e().getTextScaleX() || this.f1064a.getTextSkewX() != aVar.e().getTextSkewX()) {
                return false;
            }
            if ((i >= 21 && (this.f1064a.getLetterSpacing() != aVar.e().getLetterSpacing() || !TextUtils.equals(this.f1064a.getFontFeatureSettings(), aVar.e().getFontFeatureSettings()))) || this.f1064a.getFlags() != aVar.e().getFlags()) {
                return false;
            }
            if (i >= 24) {
                if (!this.f1064a.getTextLocales().equals(aVar.e().getTextLocales())) {
                    return false;
                }
            } else if (i >= 17 && !this.f1064a.getTextLocale().equals(aVar.e().getTextLocale())) {
                return false;
            }
            return this.f1064a.getTypeface() == null ? aVar.e().getTypeface() == null : this.f1064a.getTypeface().equals(aVar.e().getTypeface());
        }

        public int b() {
            return this.f1066c;
        }

        public int c() {
            return this.f1067d;
        }

        public TextDirectionHeuristic d() {
            return this.f1065b;
        }

        public TextPaint e() {
            return this.f1064a;
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
                return Build.VERSION.SDK_INT < 18 || this.f1065b == aVar.d();
            }
            return false;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            return i >= 24 ? b.g.i.c.b(Float.valueOf(this.f1064a.getTextSize()), Float.valueOf(this.f1064a.getTextScaleX()), Float.valueOf(this.f1064a.getTextSkewX()), Float.valueOf(this.f1064a.getLetterSpacing()), Integer.valueOf(this.f1064a.getFlags()), this.f1064a.getTextLocales(), this.f1064a.getTypeface(), Boolean.valueOf(this.f1064a.isElegantTextHeight()), this.f1065b, Integer.valueOf(this.f1066c), Integer.valueOf(this.f1067d)) : i >= 21 ? b.g.i.c.b(Float.valueOf(this.f1064a.getTextSize()), Float.valueOf(this.f1064a.getTextScaleX()), Float.valueOf(this.f1064a.getTextSkewX()), Float.valueOf(this.f1064a.getLetterSpacing()), Integer.valueOf(this.f1064a.getFlags()), this.f1064a.getTextLocale(), this.f1064a.getTypeface(), Boolean.valueOf(this.f1064a.isElegantTextHeight()), this.f1065b, Integer.valueOf(this.f1066c), Integer.valueOf(this.f1067d)) : i >= 18 ? b.g.i.c.b(Float.valueOf(this.f1064a.getTextSize()), Float.valueOf(this.f1064a.getTextScaleX()), Float.valueOf(this.f1064a.getTextSkewX()), Integer.valueOf(this.f1064a.getFlags()), this.f1064a.getTextLocale(), this.f1064a.getTypeface(), this.f1065b, Integer.valueOf(this.f1066c), Integer.valueOf(this.f1067d)) : i >= 17 ? b.g.i.c.b(Float.valueOf(this.f1064a.getTextSize()), Float.valueOf(this.f1064a.getTextScaleX()), Float.valueOf(this.f1064a.getTextSkewX()), Integer.valueOf(this.f1064a.getFlags()), this.f1064a.getTextLocale(), this.f1064a.getTypeface(), this.f1065b, Integer.valueOf(this.f1066c), Integer.valueOf(this.f1067d)) : b.g.i.c.b(Float.valueOf(this.f1064a.getTextSize()), Float.valueOf(this.f1064a.getTextScaleX()), Float.valueOf(this.f1064a.getTextSkewX()), Integer.valueOf(this.f1064a.getFlags()), this.f1064a.getTypeface(), this.f1065b, Integer.valueOf(this.f1066c), Integer.valueOf(this.f1067d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f1064a.getTextSize());
            sb.append(", textScaleX=" + this.f1064a.getTextScaleX());
            sb.append(", textSkewX=" + this.f1064a.getTextSkewX());
            int i = Build.VERSION.SDK_INT;
            if (i >= 21) {
                sb.append(", letterSpacing=" + this.f1064a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f1064a.isElegantTextHeight());
            }
            if (i >= 24) {
                sb.append(", textLocale=" + this.f1064a.getTextLocales());
            } else if (i >= 17) {
                sb.append(", textLocale=" + this.f1064a.getTextLocale());
            }
            sb.append(", typeface=" + this.f1064a.getTypeface());
            if (i >= 26) {
                sb.append(", variationSettings=" + this.f1064a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f1065b);
            sb.append(", breakStrategy=" + this.f1066c);
            sb.append(", hyphenationFrequency=" + this.f1067d);
            sb.append("}");
            return sb.toString();
        }

        public a(PrecomputedText.Params params) {
            this.f1064a = params.getTextPaint();
            this.f1065b = params.getTextDirection();
            this.f1066c = params.getBreakStrategy();
            this.f1067d = params.getHyphenationFrequency();
            this.e = Build.VERSION.SDK_INT < 29 ? null : params;
        }
    }
}
