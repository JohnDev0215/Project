package defpackage;

import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import java.util.Objects;

/* JADX INFO: compiled from: PrecomputedTextCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class j32 implements Spannable {

    /* JADX INFO: compiled from: PrecomputedTextCompat.java */
    public static final class a {
        public final TextPaint a;
        public final TextDirectionHeuristic b;
        public final int c;
        public final int d;

        public a(PrecomputedText.Params params) {
            this.a = params.getTextPaint();
            this.b = params.getTextDirection();
            this.c = params.getBreakStrategy();
            this.d = params.getHyphenationFrequency();
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0012  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean equals(java.lang.Object r7) {
            /*
                r6 = this;
                r0 = 1
                if (r7 != r6) goto L4
                return r0
            L4:
                boolean r1 = r7 instanceof j32.a
                r2 = 0
                if (r1 != 0) goto La
                return r2
            La:
                j32$a r7 = (j32.a) r7
                int r1 = r7.c
                int r3 = r6.c
                if (r3 == r1) goto L15
            L12:
                r1 = r2
                goto L9b
            L15:
                int r1 = r6.d
                int r3 = r7.d
                if (r1 == r3) goto L1c
                goto L12
            L1c:
                android.text.TextPaint r1 = r6.a
                float r3 = r1.getTextSize()
                android.text.TextPaint r4 = r7.a
                float r5 = r4.getTextSize()
                int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r3 == 0) goto L2d
                goto L12
            L2d:
                float r3 = r1.getTextScaleX()
                float r5 = r4.getTextScaleX()
                int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r3 == 0) goto L3a
                goto L12
            L3a:
                float r3 = r1.getTextSkewX()
                float r5 = r4.getTextSkewX()
                int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r3 == 0) goto L47
                goto L12
            L47:
                float r3 = r1.getLetterSpacing()
                float r5 = r4.getLetterSpacing()
                int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r3 == 0) goto L54
                goto L12
            L54:
                java.lang.String r3 = r1.getFontFeatureSettings()
                java.lang.String r5 = r4.getFontFeatureSettings()
                boolean r3 = android.text.TextUtils.equals(r3, r5)
                if (r3 != 0) goto L63
                goto L12
            L63:
                int r3 = r1.getFlags()
                int r5 = r4.getFlags()
                if (r3 == r5) goto L6e
                goto L12
            L6e:
                android.os.LocaleList r3 = r1.getTextLocales()
                android.os.LocaleList r5 = r4.getTextLocales()
                boolean r3 = r3.equals(r5)
                if (r3 != 0) goto L7d
                goto L12
            L7d:
                android.graphics.Typeface r3 = r1.getTypeface()
                if (r3 != 0) goto L8a
                android.graphics.Typeface r1 = r4.getTypeface()
                if (r1 == 0) goto L9a
                goto L12
            L8a:
                android.graphics.Typeface r1 = r1.getTypeface()
                android.graphics.Typeface r3 = r4.getTypeface()
                boolean r1 = r1.equals(r3)
                if (r1 != 0) goto L9a
                goto L12
            L9a:
                r1 = r0
            L9b:
                if (r1 != 0) goto L9e
                return r2
            L9e:
                android.text.TextDirectionHeuristic r6 = r6.b
                android.text.TextDirectionHeuristic r7 = r7.b
                if (r6 == r7) goto La5
                return r2
            La5:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: j32.a.equals(java.lang.Object):boolean");
        }

        public final int hashCode() {
            TextPaint textPaint = this.a;
            return Objects.hash(Float.valueOf(textPaint.getTextSize()), Float.valueOf(textPaint.getTextScaleX()), Float.valueOf(textPaint.getTextSkewX()), Float.valueOf(textPaint.getLetterSpacing()), Integer.valueOf(textPaint.getFlags()), textPaint.getTextLocales(), textPaint.getTypeface(), Boolean.valueOf(textPaint.isElegantTextHeight()), this.b, Integer.valueOf(this.c), Integer.valueOf(this.d));
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("{");
            StringBuilder sb2 = new StringBuilder("textSize=");
            TextPaint textPaint = this.a;
            sb2.append(textPaint.getTextSize());
            sb.append(sb2.toString());
            sb.append(", textScaleX=" + textPaint.getTextScaleX());
            sb.append(", textSkewX=" + textPaint.getTextSkewX());
            sb.append(", letterSpacing=" + textPaint.getLetterSpacing());
            sb.append(", elegantTextHeight=" + textPaint.isElegantTextHeight());
            sb.append(", textLocale=" + textPaint.getTextLocales());
            sb.append(", typeface=" + textPaint.getTypeface());
            sb.append(", variationSettings=" + textPaint.getFontVariationSettings());
            sb.append(", textDir=" + this.b);
            sb.append(", breakStrategy=" + this.c);
            sb.append(", hyphenationFrequency=" + this.d);
            sb.append("}");
            return sb.toString();
        }
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i) {
        throw null;
    }

    @Override // android.text.Spanned
    public final int getSpanEnd(Object obj) {
        throw null;
    }

    @Override // android.text.Spanned
    public final int getSpanFlags(Object obj) {
        throw null;
    }

    @Override // android.text.Spanned
    public final int getSpanStart(Object obj) {
        throw null;
    }

    @Override // android.text.Spanned
    public final <T> T[] getSpans(int i, int i2, Class<T> cls) {
        throw null;
    }

    @Override // java.lang.CharSequence
    public final int length() {
        throw null;
    }

    @Override // android.text.Spanned
    public final int nextSpanTransition(int i, int i2, Class cls) {
        throw null;
    }

    @Override // android.text.Spannable
    public final void removeSpan(Object obj) {
        if (!(obj instanceof MetricAffectingSpan)) {
            throw null;
        }
        throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
    }

    @Override // android.text.Spannable
    public final void setSpan(Object obj, int i, int i2, int i3) {
        if (!(obj instanceof MetricAffectingSpan)) {
            throw null;
        }
        throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
    }

    @Override // java.lang.CharSequence
    public final CharSequence subSequence(int i, int i2) {
        throw null;
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        throw null;
    }
}
