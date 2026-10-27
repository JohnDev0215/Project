package m0;

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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Object f6314d = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Spannable f6315e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final a f6316f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final PrecomputedText f6317g;

    public a a() {
        return this.f6316f;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f6315e;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i8) {
        return this.f6315e.charAt(i8);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f6315e.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f6315e.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f6315e.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i8, int i9, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.f6317g.getSpans(i8, i9, cls) : (T[]) this.f6315e.getSpans(i8, i9, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f6315e.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i8, int i9, Class cls) {
        return this.f6315e.nextSpanTransition(i8, i9, cls);
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f6317g.removeSpan(obj);
        } else {
            this.f6315e.removeSpan(obj);
        }
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i8, int i9, int i10) {
        if (obj instanceof MetricAffectingSpan) {
            throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.f6317g.setSpan(obj, i8, i9, i10);
        } else {
            this.f6315e.setSpan(obj, i8, i9, i10);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i8, int i9) {
        return this.f6315e.subSequence(i8, i9);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f6315e.toString();
    }

    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextPaint f6318a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextDirectionHeuristic f6319b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f6320c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f6321d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final PrecomputedText.Params f6322e;

        /* JADX INFO: renamed from: m0.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: PrecomputedTextCompat.java */
        public static class C0087a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final TextPaint f6323a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public TextDirectionHeuristic f6324b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public int f6325c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public int f6326d;

            public C0087a(TextPaint textPaint) {
                this.f6323a = textPaint;
                int i8 = Build.VERSION.SDK_INT;
                if (i8 >= 23) {
                    this.f6325c = 1;
                    this.f6326d = 1;
                } else {
                    this.f6326d = 0;
                    this.f6325c = 0;
                }
                if (i8 >= 18) {
                    this.f6324b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f6324b = null;
                }
            }

            public a a() {
                return new a(this.f6323a, this.f6324b, this.f6325c, this.f6326d);
            }

            public C0087a b(int i8) {
                this.f6325c = i8;
                return this;
            }

            public C0087a c(int i8) {
                this.f6326d = i8;
                return this;
            }

            public C0087a d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f6324b = textDirectionHeuristic;
                return this;
            }
        }

        @SuppressLint({"NewApi"})
        public a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i8, int i9) {
            if (Build.VERSION.SDK_INT >= 29) {
                this.f6322e = new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i8).setHyphenationFrequency(i9).setTextDirection(textDirectionHeuristic).build();
            } else {
                this.f6322e = null;
            }
            this.f6318a = textPaint;
            this.f6319b = textDirectionHeuristic;
            this.f6320c = i8;
            this.f6321d = i9;
        }

        public boolean a(a aVar) {
            int i8 = Build.VERSION.SDK_INT;
            if ((i8 >= 23 && (this.f6320c != aVar.b() || this.f6321d != aVar.c())) || this.f6318a.getTextSize() != aVar.e().getTextSize() || this.f6318a.getTextScaleX() != aVar.e().getTextScaleX() || this.f6318a.getTextSkewX() != aVar.e().getTextSkewX()) {
                return false;
            }
            if ((i8 >= 21 && (this.f6318a.getLetterSpacing() != aVar.e().getLetterSpacing() || !TextUtils.equals(this.f6318a.getFontFeatureSettings(), aVar.e().getFontFeatureSettings()))) || this.f6318a.getFlags() != aVar.e().getFlags()) {
                return false;
            }
            if (i8 >= 24) {
                if (!this.f6318a.getTextLocales().equals(aVar.e().getTextLocales())) {
                    return false;
                }
            } else if (i8 >= 17 && !this.f6318a.getTextLocale().equals(aVar.e().getTextLocale())) {
                return false;
            }
            return this.f6318a.getTypeface() == null ? aVar.e().getTypeface() == null : this.f6318a.getTypeface().equals(aVar.e().getTypeface());
        }

        public int b() {
            return this.f6320c;
        }

        public int c() {
            return this.f6321d;
        }

        public TextDirectionHeuristic d() {
            return this.f6319b;
        }

        public TextPaint e() {
            return this.f6318a;
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
                return Build.VERSION.SDK_INT < 18 || this.f6319b == aVar.d();
            }
            return false;
        }

        public int hashCode() {
            int i8 = Build.VERSION.SDK_INT;
            return i8 >= 24 ? n0.c.b(Float.valueOf(this.f6318a.getTextSize()), Float.valueOf(this.f6318a.getTextScaleX()), Float.valueOf(this.f6318a.getTextSkewX()), Float.valueOf(this.f6318a.getLetterSpacing()), Integer.valueOf(this.f6318a.getFlags()), this.f6318a.getTextLocales(), this.f6318a.getTypeface(), Boolean.valueOf(this.f6318a.isElegantTextHeight()), this.f6319b, Integer.valueOf(this.f6320c), Integer.valueOf(this.f6321d)) : i8 >= 21 ? n0.c.b(Float.valueOf(this.f6318a.getTextSize()), Float.valueOf(this.f6318a.getTextScaleX()), Float.valueOf(this.f6318a.getTextSkewX()), Float.valueOf(this.f6318a.getLetterSpacing()), Integer.valueOf(this.f6318a.getFlags()), this.f6318a.getTextLocale(), this.f6318a.getTypeface(), Boolean.valueOf(this.f6318a.isElegantTextHeight()), this.f6319b, Integer.valueOf(this.f6320c), Integer.valueOf(this.f6321d)) : i8 >= 18 ? n0.c.b(Float.valueOf(this.f6318a.getTextSize()), Float.valueOf(this.f6318a.getTextScaleX()), Float.valueOf(this.f6318a.getTextSkewX()), Integer.valueOf(this.f6318a.getFlags()), this.f6318a.getTextLocale(), this.f6318a.getTypeface(), this.f6319b, Integer.valueOf(this.f6320c), Integer.valueOf(this.f6321d)) : i8 >= 17 ? n0.c.b(Float.valueOf(this.f6318a.getTextSize()), Float.valueOf(this.f6318a.getTextScaleX()), Float.valueOf(this.f6318a.getTextSkewX()), Integer.valueOf(this.f6318a.getFlags()), this.f6318a.getTextLocale(), this.f6318a.getTypeface(), this.f6319b, Integer.valueOf(this.f6320c), Integer.valueOf(this.f6321d)) : n0.c.b(Float.valueOf(this.f6318a.getTextSize()), Float.valueOf(this.f6318a.getTextScaleX()), Float.valueOf(this.f6318a.getTextSkewX()), Integer.valueOf(this.f6318a.getFlags()), this.f6318a.getTypeface(), this.f6319b, Integer.valueOf(this.f6320c), Integer.valueOf(this.f6321d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f6318a.getTextSize());
            sb.append(", textScaleX=" + this.f6318a.getTextScaleX());
            sb.append(", textSkewX=" + this.f6318a.getTextSkewX());
            int i8 = Build.VERSION.SDK_INT;
            if (i8 >= 21) {
                sb.append(", letterSpacing=" + this.f6318a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f6318a.isElegantTextHeight());
            }
            if (i8 >= 24) {
                sb.append(", textLocale=" + this.f6318a.getTextLocales());
            } else if (i8 >= 17) {
                sb.append(", textLocale=" + this.f6318a.getTextLocale());
            }
            sb.append(", typeface=" + this.f6318a.getTypeface());
            if (i8 >= 26) {
                sb.append(", variationSettings=" + this.f6318a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f6319b);
            sb.append(", breakStrategy=" + this.f6320c);
            sb.append(", hyphenationFrequency=" + this.f6321d);
            sb.append("}");
            return sb.toString();
        }

        public a(PrecomputedText.Params params) {
            this.f6318a = params.getTextPaint();
            this.f6319b = params.getTextDirection();
            this.f6320c = params.getBreakStrategy();
            this.f6321d = params.getHyphenationFrequency();
            this.f6322e = Build.VERSION.SDK_INT < 29 ? null : params;
        }
    }
}
