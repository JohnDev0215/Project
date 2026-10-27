package defpackage;

import android.content.Context;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.MetricAffectingSpan;
import android.text.style.TextAppearanceSpan;

/* JADX INFO: compiled from: AnswerText.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a6 {
    public final Context a;
    public final float b;
    public String d;
    public int e;
    public final SpannableStringBuilder c = new SpannableStringBuilder();
    public int f = 1;

    public a6(Context context) {
        this.a = context;
        this.b = context.getResources().getDisplayMetrics().density;
    }

    public final void a(String str, MetricAffectingSpan[] metricAffectingSpanArr) {
        int textSize;
        String strB = b(Html.fromHtml(str).toString());
        for (MetricAffectingSpan metricAffectingSpan : metricAffectingSpanArr) {
            if ((metricAffectingSpan instanceof TextAppearanceSpan) && this.e < (textSize = (int) (((TextAppearanceSpan) r3).getTextSize() / this.b))) {
                this.e = textSize;
            }
        }
        SpannableStringBuilder spannableStringBuilder = this.c;
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append((CharSequence) strB);
        int length2 = spannableStringBuilder.length();
        for (MetricAffectingSpan metricAffectingSpan2 : metricAffectingSpanArr) {
            spannableStringBuilder.setSpan(metricAffectingSpan2, length, length2, 33);
        }
    }

    public abstract String b(String str);
}
