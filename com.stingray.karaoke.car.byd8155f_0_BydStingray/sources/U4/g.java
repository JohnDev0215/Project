package U4;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CharSequence f9062a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TextPaint f9063b;
    public final int c;
    public int d;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f9069j;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Layout.Alignment f9064e = Layout.Alignment.ALIGN_NORMAL;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f9065f = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f9066g = 1.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f9067h = 1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f9068i = true;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public TextUtils.TruncateAt f9070k = null;

    public g(CharSequence charSequence, TextPaint textPaint, int i7) {
        this.f9062a = charSequence;
        this.f9063b = textPaint;
        this.c = i7;
        this.d = charSequence.length();
    }

    public final StaticLayout a() {
        if (this.f9062a == null) {
            this.f9062a = "";
        }
        int iMax = Math.max(0, this.c);
        CharSequence charSequenceEllipsize = this.f9062a;
        int i7 = this.f9065f;
        TextPaint textPaint = this.f9063b;
        if (i7 == 1) {
            charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, textPaint, iMax, this.f9070k);
        }
        int iMin = Math.min(charSequenceEllipsize.length(), this.d);
        this.d = iMin;
        if (this.f9069j && this.f9065f == 1) {
            this.f9064e = Layout.Alignment.ALIGN_OPPOSITE;
        }
        StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(charSequenceEllipsize, 0, iMin, textPaint, iMax);
        builderObtain.setAlignment(this.f9064e);
        builderObtain.setIncludePad(this.f9068i);
        builderObtain.setTextDirection(this.f9069j ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
        TextUtils.TruncateAt truncateAt = this.f9070k;
        if (truncateAt != null) {
            builderObtain.setEllipsize(truncateAt);
        }
        builderObtain.setMaxLines(this.f9065f);
        float f10 = this.f9066g;
        if (f10 != 1.0f) {
            builderObtain.setLineSpacing(0.0f, f10);
        }
        if (this.f9065f > 1) {
            builderObtain.setHyphenationFrequency(this.f9067h);
        }
        return builderObtain.build();
    }
}
