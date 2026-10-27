package a.g.i;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;

/* JADX INFO: loaded from: classes.dex */
public class c implements Spannable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Object f342b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Spannable f343c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final a f344d;
    private final PrecomputedText e;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final TextPaint f345a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final TextDirectionHeuristic f346b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f347c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int f348d;
        final PrecomputedText.Params e;

        /* JADX INFO: renamed from: a.g.i.c$a$a, reason: collision with other inner class name */
        public static class C0010a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final TextPaint f349a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private TextDirectionHeuristic f350b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private int f351c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private int f352d;

            public C0010a(TextPaint textPaint) {
                this.f349a = textPaint;
                int i = Build.VERSION.SDK_INT;
                if (i >= 23) {
                    this.f351c = 1;
                    this.f352d = 1;
                } else {
                    this.f352d = 0;
                    this.f351c = 0;
                }
                this.f350b = i >= 18 ? TextDirectionHeuristics.FIRSTSTRONG_LTR : null;
            }

            public a a() {
                return new a(this.f349a, this.f350b, this.f351c, this.f352d);
            }

            public C0010a b(int i) {
                this.f351c = i;
                return this;
            }

            public C0010a c(int i) {
                this.f352d = i;
                return this;
            }

            public C0010a d(TextDirectionHeuristic textDirectionHeuristic) {
                this.f350b = textDirectionHeuristic;
                return this;
            }
        }

        public a(PrecomputedText.Params params) {
            this.f345a = params.getTextPaint();
            this.f346b = params.getTextDirection();
            this.f347c = params.getBreakStrategy();
            this.f348d = params.getHyphenationFrequency();
            this.e = Build.VERSION.SDK_INT < 29 ? null : params;
        }

        @SuppressLint({"NewApi"})
        a(TextPaint textPaint, TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            this.e = Build.VERSION.SDK_INT >= 29 ? new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build() : null;
            this.f345a = textPaint;
            this.f346b = textDirectionHeuristic;
            this.f347c = i;
            this.f348d = i2;
        }

        public boolean a(a aVar) {
            int i = Build.VERSION.SDK_INT;
            if ((i >= 23 && (this.f347c != aVar.b() || this.f348d != aVar.c())) || this.f345a.getTextSize() != aVar.e().getTextSize() || this.f345a.getTextScaleX() != aVar.e().getTextScaleX() || this.f345a.getTextSkewX() != aVar.e().getTextSkewX()) {
                return false;
            }
            if ((i >= 21 && (this.f345a.getLetterSpacing() != aVar.e().getLetterSpacing() || !TextUtils.equals(this.f345a.getFontFeatureSettings(), aVar.e().getFontFeatureSettings()))) || this.f345a.getFlags() != aVar.e().getFlags()) {
                return false;
            }
            if (i >= 24) {
                if (!this.f345a.getTextLocales().equals(aVar.e().getTextLocales())) {
                    return false;
                }
            } else if (i >= 17 && !this.f345a.getTextLocale().equals(aVar.e().getTextLocale())) {
                return false;
            }
            return this.f345a.getTypeface() == null ? aVar.e().getTypeface() == null : this.f345a.getTypeface().equals(aVar.e().getTypeface());
        }

        public int b() {
            return this.f347c;
        }

        public int c() {
            return this.f348d;
        }

        public TextDirectionHeuristic d() {
            return this.f346b;
        }

        public TextPaint e() {
            return this.f345a;
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
                return Build.VERSION.SDK_INT < 18 || this.f346b == aVar.d();
            }
            return false;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 24) {
                return a.g.j.c.b(Float.valueOf(this.f345a.getTextSize()), Float.valueOf(this.f345a.getTextScaleX()), Float.valueOf(this.f345a.getTextSkewX()), Float.valueOf(this.f345a.getLetterSpacing()), Integer.valueOf(this.f345a.getFlags()), this.f345a.getTextLocales(), this.f345a.getTypeface(), Boolean.valueOf(this.f345a.isElegantTextHeight()), this.f346b, Integer.valueOf(this.f347c), Integer.valueOf(this.f348d));
            }
            if (i >= 21) {
                return a.g.j.c.b(Float.valueOf(this.f345a.getTextSize()), Float.valueOf(this.f345a.getTextScaleX()), Float.valueOf(this.f345a.getTextSkewX()), Float.valueOf(this.f345a.getLetterSpacing()), Integer.valueOf(this.f345a.getFlags()), this.f345a.getTextLocale(), this.f345a.getTypeface(), Boolean.valueOf(this.f345a.isElegantTextHeight()), this.f346b, Integer.valueOf(this.f347c), Integer.valueOf(this.f348d));
            }
            if (i < 18 && i < 17) {
                return a.g.j.c.b(Float.valueOf(this.f345a.getTextSize()), Float.valueOf(this.f345a.getTextScaleX()), Float.valueOf(this.f345a.getTextSkewX()), Integer.valueOf(this.f345a.getFlags()), this.f345a.getTypeface(), this.f346b, Integer.valueOf(this.f347c), Integer.valueOf(this.f348d));
            }
            return a.g.j.c.b(Float.valueOf(this.f345a.getTextSize()), Float.valueOf(this.f345a.getTextScaleX()), Float.valueOf(this.f345a.getTextSkewX()), Integer.valueOf(this.f345a.getFlags()), this.f345a.getTextLocale(), this.f345a.getTypeface(), this.f346b, Integer.valueOf(this.f347c), Integer.valueOf(this.f348d));
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x00df  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String toString() {
            /*
                Method dump skipped, instruction units count: 325
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: a.g.i.c.a.toString():java.lang.String");
        }
    }

    public a a() {
        return this.f344d;
    }

    public PrecomputedText b() {
        Spannable spannable = this.f343c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.f343c.charAt(i);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f343c.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f343c.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f343c.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.e.getSpans(i, i2, cls) : (T[]) this.f343c.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f343c.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f343c.nextSpanTransition(i, i2, cls);
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
            this.f343c.removeSpan(obj);
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
            this.f343c.setSpan(obj, i, i2, i3);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.f343c.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.f343c.toString();
    }
}
