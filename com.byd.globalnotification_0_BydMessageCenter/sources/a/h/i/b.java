package a.h.i;

import android.annotation.SuppressLint;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.TextDirectionHeuristic;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class b implements Spannable {

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextPaint f787a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextDirectionHeuristic f788b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f789c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f790d;

        public a(PrecomputedText.Params params) {
            this.f787a = params.getTextPaint();
            this.f788b = params.getTextDirection();
            this.f789c = params.getBreakStrategy();
            this.f790d = params.getHyphenationFrequency();
        }

        public boolean a(a aVar) {
            if (this.f789c == aVar.f789c && this.f790d == aVar.f790d && this.f787a.getTextSize() == aVar.f787a.getTextSize() && this.f787a.getTextScaleX() == aVar.f787a.getTextScaleX() && this.f787a.getTextSkewX() == aVar.f787a.getTextSkewX() && this.f787a.getLetterSpacing() == aVar.f787a.getLetterSpacing() && TextUtils.equals(this.f787a.getFontFeatureSettings(), aVar.f787a.getFontFeatureSettings()) && this.f787a.getFlags() == aVar.f787a.getFlags() && this.f787a.getTextLocales().equals(aVar.f787a.getTextLocales())) {
                return this.f787a.getTypeface() == null ? aVar.f787a.getTypeface() == null : this.f787a.getTypeface().equals(aVar.f787a.getTypeface());
            }
            return false;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return a(aVar) && this.f788b == aVar.f788b;
        }

        public int hashCode() {
            return Objects.hash(Float.valueOf(this.f787a.getTextSize()), Float.valueOf(this.f787a.getTextScaleX()), Float.valueOf(this.f787a.getTextSkewX()), Float.valueOf(this.f787a.getLetterSpacing()), Integer.valueOf(this.f787a.getFlags()), this.f787a.getTextLocales(), this.f787a.getTypeface(), Boolean.valueOf(this.f787a.isElegantTextHeight()), this.f788b, Integer.valueOf(this.f789c), Integer.valueOf(this.f790d));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            StringBuilder sbD = b.a.a.a.a.d("textSize=");
            sbD.append(this.f787a.getTextSize());
            sb.append(sbD.toString());
            sb.append(", textScaleX=" + this.f787a.getTextScaleX());
            sb.append(", textSkewX=" + this.f787a.getTextSkewX());
            sb.append(", letterSpacing=" + this.f787a.getLetterSpacing());
            sb.append(", elegantTextHeight=" + this.f787a.isElegantTextHeight());
            sb.append(", textLocale=" + this.f787a.getTextLocales());
            sb.append(", typeface=" + this.f787a.getTypeface());
            sb.append(", variationSettings=" + this.f787a.getFontVariationSettings());
            sb.append(", textDir=" + this.f788b);
            sb.append(", breakStrategy=" + this.f789c);
            sb.append(", hyphenationFrequency=" + this.f790d);
            sb.append("}");
            return sb.toString();
        }
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        throw null;
    }

    @Override // android.text.Spanned
    public int getSpanEnd(Object obj) {
        throw null;
    }

    @Override // android.text.Spanned
    public int getSpanFlags(Object obj) {
        throw null;
    }

    @Override // android.text.Spanned
    public int getSpanStart(Object obj) {
        throw null;
    }

    @Override // android.text.Spanned
    @SuppressLint({"NewApi"})
    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        throw null;
    }

    @Override // java.lang.CharSequence
    public int length() {
        throw null;
    }

    @Override // android.text.Spanned
    public int nextSpanTransition(int i, int i2, Class cls) {
        throw null;
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void removeSpan(Object obj) {
        if (!(obj instanceof MetricAffectingSpan)) {
            throw null;
        }
        throw new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
    }

    @Override // android.text.Spannable
    @SuppressLint({"NewApi"})
    public void setSpan(Object obj, int i, int i2, int i3) {
        if (!(obj instanceof MetricAffectingSpan)) {
            throw null;
        }
        throw new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        throw null;
    }

    @Override // java.lang.CharSequence
    public String toString() {
        throw null;
    }
}
