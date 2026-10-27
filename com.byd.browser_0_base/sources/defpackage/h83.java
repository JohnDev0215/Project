package defpackage;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

/* JADX INFO: compiled from: TextHighlightSpan.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h83 implements LineBackgroundSpan {
    public final int e;
    public final int f;
    public final boolean g;
    public final boolean h;

    public h83(int i, int i2, int i3, boolean z) {
        this.e = i;
        this.f = i2;
        boolean z2 = true;
        this.g = i3 == 3;
        if ((z || i3 != 2) && (!z || i3 != 4)) {
            z2 = false;
        }
        this.h = z2;
    }

    @Override // android.text.style.LineBackgroundSpan
    public final void drawBackground(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, int i8) {
        int i9;
        int i10;
        if (charSequence.subSequence(i6, i7).toString().trim().length() == 0) {
            return;
        }
        int iRound = Math.round(paint.measureText(charSequence, i6, i7));
        int color = paint.getColor();
        paint.setColor(this.f);
        if (this.g) {
            int i11 = ((i2 - i) / 2) + i;
            int i12 = iRound / 2;
            i9 = i11 - i12;
            i10 = i12 + i11;
        } else if (this.h) {
            i10 = iRound + i;
            i9 = i;
        } else {
            i9 = i2 - iRound;
            i10 = i2;
        }
        canvas.drawRect(i9, this.e == 3 ? ((i5 - i3) / 2) + i3 : i3, i10 + 10, i5, paint);
        paint.setColor(color);
    }
}
