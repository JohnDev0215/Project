package Q0;

import A7.m;
import O0.w;
import O0.y;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

/* JADX INFO: loaded from: classes.dex */
public final class c implements LeadingMarginSpan {
    @Override // android.text.style.LeadingMarginSpan
    public final void drawLeadingMargin(Canvas canvas, Paint paint, int i7, int i10, int i11, int i12, int i13, CharSequence charSequence, int i14, int i15, boolean z6, Layout layout) {
        int lineForOffset;
        if (layout == null || paint == null || (lineForOffset = layout.getLineForOffset(i14)) != layout.getLineCount() - 1) {
            return;
        }
        w wVar = y.f5060a;
        if (layout.getEllipsisCount(lineForOffset) > 0) {
            float fE0 = com.bumptech.glide.c.e0(layout, lineForOffset, paint) + com.bumptech.glide.c.d0(layout, lineForOffset, paint);
            if (fE0 == 0.0f) {
                return;
            }
            m.c(canvas);
            canvas.translate(fE0, 0.0f);
        }
    }

    @Override // android.text.style.LeadingMarginSpan
    public final int getLeadingMargin(boolean z6) {
        return 0;
    }
}
