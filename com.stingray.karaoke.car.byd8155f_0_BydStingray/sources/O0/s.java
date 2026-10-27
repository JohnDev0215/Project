package O0;

import android.graphics.Rect;
import android.text.Layout;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* JADX INFO: loaded from: classes.dex */
public abstract class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o f5026a = new o();

    public static final Rect a(TextPaint textPaint, CharSequence charSequence, int i7, int i10) {
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            if (spanned.nextSpanTransition(i7 - 1, i10, MetricAffectingSpan.class) != i10) {
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                TextPaint textPaint2 = new TextPaint();
                while (i7 < i10) {
                    int iNextSpanTransition = spanned.nextSpanTransition(i7, i10, MetricAffectingSpan.class);
                    MetricAffectingSpan[] metricAffectingSpanArr = (MetricAffectingSpan[]) spanned.getSpans(i7, iNextSpanTransition, MetricAffectingSpan.class);
                    textPaint2.set(textPaint);
                    for (MetricAffectingSpan metricAffectingSpan : metricAffectingSpanArr) {
                        if (spanned.getSpanStart(metricAffectingSpan) != spanned.getSpanEnd(metricAffectingSpan)) {
                            metricAffectingSpan.updateMeasureState(textPaint2);
                        }
                    }
                    n.a(textPaint2, charSequence, i7, iNextSpanTransition, rect2);
                    rect.right = rect2.width() + rect.right;
                    rect.top = Math.min(rect.top, rect2.top);
                    rect.bottom = Math.max(rect.bottom, rect2.bottom);
                    i7 = iNextSpanTransition;
                }
                return rect;
            }
        }
        Rect rect3 = new Rect();
        n.a(textPaint, charSequence, i7, i10, rect3);
        return rect3;
    }

    public static final float b(int i7, int i10, float[] fArr) {
        return fArr[((i7 - i10) * 2) + 1];
    }

    public static final int c(Layout layout, int i7, boolean z6) {
        if (i7 <= 0) {
            return 0;
        }
        if (i7 >= layout.getText().length()) {
            return layout.getLineCount() - 1;
        }
        int lineForOffset = layout.getLineForOffset(i7);
        int lineStart = layout.getLineStart(lineForOffset);
        return (lineStart == i7 || layout.getLineEnd(lineForOffset) == i7) ? lineStart == i7 ? z6 ? lineForOffset - 1 : lineForOffset : z6 ? lineForOffset : lineForOffset + 1 : lineForOffset;
    }

    /* JADX WARN: Removed duplicated region for block: B:143:0x0262 A[EDGE_INSN: B:201:0x0262->B:143:0x0262 BREAK  A[LOOP:5: B:153:0x027e->B:204:0x027e], EDGE_INSN: B:202:0x0262->B:143:0x0262 BREAK  A[LOOP:5: B:153:0x027e->B:204:0x027e]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final int d(O0.x r19, android.text.Layout r20, N0.q r21, int r22, android.graphics.RectF r23, P0.c r24, A.G0 r25, boolean r26) {
        /*
            Method dump skipped, instruction units count: 728
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: O0.s.d(O0.x, android.text.Layout, N0.q, int, android.graphics.RectF, P0.c, A.G0, boolean):int");
    }

    public static final boolean e(Spanned spanned, Class cls) {
        return spanned.nextSpanTransition(-1, spanned.length(), cls) != spanned.length();
    }
}
