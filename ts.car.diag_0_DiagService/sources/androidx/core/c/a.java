package androidx.core.c;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class a implements Spannable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @NonNull
    private final Spannable f440c;

    @NonNull
    private final C0009a d;

    @Nullable
    private final PrecomputedText e;

    @NonNull
    public C0009a a() {
        return this.d;
    }

    @Nullable
    @RequiresApi(28)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public PrecomputedText b() {
        Spannable spannable = this.f440c;
        if (spannable instanceof PrecomputedText) {
            return (PrecomputedText) spannable;
        }
        return null;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return this.f440c.charAt(i);
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        return this.f440c.getSpanEnd(obj);
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        return this.f440c.getSpanFlags(obj);
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        return this.f440c.getSpanStart(obj);
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        return Build.VERSION.SDK_INT >= 29 ? (T[]) this.e.getSpans(i, i2, cls) : (T[]) this.f440c.getSpans(i, i2, cls);
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.f440c.length();
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        return this.f440c.nextSpanTransition(i, i2, cls);
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
            this.f440c.removeSpan(obj);
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
            this.f440c.setSpan(obj, i, i2, i3);
        }
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return this.f440c.subSequence(i, i2);
    }

    @Override // java.lang.CharSequence
    @NonNull
    public String toString() {
        return this.f440c.toString();
    }

    /* JADX INFO: renamed from: androidx.core.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    public static final class C0009a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @NonNull
        private final TextPaint f441a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Nullable
        private final TextDirectionHeuristic f442b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f443c;
        private final int d;

        /* JADX INFO: renamed from: androidx.core.c.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: caradapter-bug */
        public static class C0010a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            @NonNull
            private final TextPaint f444a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private TextDirectionHeuristic f445b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private int f446c;
            private int d;

            public C0010a(@NonNull TextPaint textPaint) {
                int i = Build.VERSION.SDK_INT;
                this.f444a = textPaint;
                if (i >= 23) {
                    this.f446c = 1;
                    this.d = 1;
                } else {
                    this.d = 0;
                    this.f446c = 0;
                }
                if (i >= 18) {
                    this.f445b = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                } else {
                    this.f445b = null;
                }
            }

            @NonNull
            public C0009a a() {
                return new C0009a(this.f444a, this.f445b, this.f446c, this.d);
            }

            @RequiresApi(23)
            public C0010a b(int i) {
                this.f446c = i;
                return this;
            }

            @RequiresApi(23)
            public C0010a c(int i) {
                this.d = i;
                return this;
            }

            @RequiresApi(18)
            public C0010a d(@NonNull TextDirectionHeuristic textDirectionHeuristic) {
                this.f445b = textDirectionHeuristic;
                return this;
            }
        }

        @SuppressLint({"NewApi"})
        C0009a(@NonNull TextPaint textPaint, @NonNull TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            if (Build.VERSION.SDK_INT >= 29) {
                new PrecomputedText.Params.Builder(textPaint).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(textDirectionHeuristic).build();
            }
            this.f441a = textPaint;
            this.f442b = textDirectionHeuristic;
            this.f443c = i;
            this.d = i2;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public boolean a(@NonNull C0009a c0009a) {
            int i = Build.VERSION.SDK_INT;
            if ((i >= 23 && (this.f443c != c0009a.b() || this.d != c0009a.c())) || this.f441a.getTextSize() != c0009a.e().getTextSize() || this.f441a.getTextScaleX() != c0009a.e().getTextScaleX() || this.f441a.getTextSkewX() != c0009a.e().getTextSkewX()) {
                return false;
            }
            if ((i >= 21 && (this.f441a.getLetterSpacing() != c0009a.e().getLetterSpacing() || !TextUtils.equals(this.f441a.getFontFeatureSettings(), c0009a.e().getFontFeatureSettings()))) || this.f441a.getFlags() != c0009a.e().getFlags()) {
                return false;
            }
            if (i >= 24) {
                if (!this.f441a.getTextLocales().equals(c0009a.e().getTextLocales())) {
                    return false;
                }
            } else if (i >= 17 && !this.f441a.getTextLocale().equals(c0009a.e().getTextLocale())) {
                return false;
            }
            return this.f441a.getTypeface() == null ? c0009a.e().getTypeface() == null : this.f441a.getTypeface().equals(c0009a.e().getTypeface());
        }

        @RequiresApi(23)
        public int b() {
            return this.f443c;
        }

        @RequiresApi(23)
        public int c() {
            return this.d;
        }

        @Nullable
        @RequiresApi(18)
        public TextDirectionHeuristic d() {
            return this.f442b;
        }

        @NonNull
        public TextPaint e() {
            return this.f441a;
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof C0009a)) {
                return false;
            }
            C0009a c0009a = (C0009a) obj;
            if (a(c0009a)) {
                return Build.VERSION.SDK_INT < 18 || this.f442b == c0009a.d();
            }
            return false;
        }

        public int hashCode() {
            int i = Build.VERSION.SDK_INT;
            return i >= 24 ? androidx.core.d.a.b(Float.valueOf(this.f441a.getTextSize()), Float.valueOf(this.f441a.getTextScaleX()), Float.valueOf(this.f441a.getTextSkewX()), Float.valueOf(this.f441a.getLetterSpacing()), Integer.valueOf(this.f441a.getFlags()), this.f441a.getTextLocales(), this.f441a.getTypeface(), Boolean.valueOf(this.f441a.isElegantTextHeight()), this.f442b, Integer.valueOf(this.f443c), Integer.valueOf(this.d)) : i >= 21 ? androidx.core.d.a.b(Float.valueOf(this.f441a.getTextSize()), Float.valueOf(this.f441a.getTextScaleX()), Float.valueOf(this.f441a.getTextSkewX()), Float.valueOf(this.f441a.getLetterSpacing()), Integer.valueOf(this.f441a.getFlags()), this.f441a.getTextLocale(), this.f441a.getTypeface(), Boolean.valueOf(this.f441a.isElegantTextHeight()), this.f442b, Integer.valueOf(this.f443c), Integer.valueOf(this.d)) : i >= 18 ? androidx.core.d.a.b(Float.valueOf(this.f441a.getTextSize()), Float.valueOf(this.f441a.getTextScaleX()), Float.valueOf(this.f441a.getTextSkewX()), Integer.valueOf(this.f441a.getFlags()), this.f441a.getTextLocale(), this.f441a.getTypeface(), this.f442b, Integer.valueOf(this.f443c), Integer.valueOf(this.d)) : i >= 17 ? androidx.core.d.a.b(Float.valueOf(this.f441a.getTextSize()), Float.valueOf(this.f441a.getTextScaleX()), Float.valueOf(this.f441a.getTextSkewX()), Integer.valueOf(this.f441a.getFlags()), this.f441a.getTextLocale(), this.f441a.getTypeface(), this.f442b, Integer.valueOf(this.f443c), Integer.valueOf(this.d)) : androidx.core.d.a.b(Float.valueOf(this.f441a.getTextSize()), Float.valueOf(this.f441a.getTextScaleX()), Float.valueOf(this.f441a.getTextSkewX()), Integer.valueOf(this.f441a.getFlags()), this.f441a.getTypeface(), this.f442b, Integer.valueOf(this.f443c), Integer.valueOf(this.d));
        }

        public String toString() {
            int i = Build.VERSION.SDK_INT;
            StringBuilder sb = new StringBuilder("{");
            sb.append("textSize=" + this.f441a.getTextSize());
            sb.append(", textScaleX=" + this.f441a.getTextScaleX());
            sb.append(", textSkewX=" + this.f441a.getTextSkewX());
            if (i >= 21) {
                sb.append(", letterSpacing=" + this.f441a.getLetterSpacing());
                sb.append(", elegantTextHeight=" + this.f441a.isElegantTextHeight());
            }
            if (i >= 24) {
                sb.append(", textLocale=" + this.f441a.getTextLocales());
            } else if (i >= 17) {
                sb.append(", textLocale=" + this.f441a.getTextLocale());
            }
            sb.append(", typeface=" + this.f441a.getTypeface());
            if (i >= 26) {
                sb.append(", variationSettings=" + this.f441a.getFontVariationSettings());
            }
            sb.append(", textDir=" + this.f442b);
            sb.append(", breakStrategy=" + this.f443c);
            sb.append(", hyphenationFrequency=" + this.d);
            sb.append("}");
            return sb.toString();
        }

        @RequiresApi(28)
        public C0009a(@NonNull PrecomputedText.Params params) {
            this.f441a = params.getTextPaint();
            this.f442b = params.getTextDirection();
            this.f443c = params.getBreakStrategy();
            this.d = params.getHyphenationFrequency();
            int i = Build.VERSION.SDK_INT;
        }
    }
}
