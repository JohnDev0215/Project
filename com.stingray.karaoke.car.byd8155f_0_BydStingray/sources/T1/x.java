package T1;

import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class x extends ReplacementSpan {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final w f8526m;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public TextPaint f8528p;
    public final Paint.FontMetricsInt l = new Paint.FontMetricsInt();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public short f8527n = -1;
    public float o = 1.0f;

    public x(w wVar) {
        l9.l.F(wVar, "rasterizer cannot be null");
        this.f8526m = wVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003d  */
    @Override // android.text.style.ReplacementSpan
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void draw(android.graphics.Canvas r13, java.lang.CharSequence r14, int r15, int r16, float r17, int r18, int r19, int r20, android.graphics.Paint r21) {
        /*
            r12 = this;
            r0 = r12
            r1 = r14
            r2 = r21
            boolean r3 = r1 instanceof android.text.Spanned
            r4 = 0
            if (r3 == 0) goto L45
            android.text.Spanned r1 = (android.text.Spanned) r1
            java.lang.Class<android.text.style.CharacterStyle> r3 = android.text.style.CharacterStyle.class
            r5 = r15
            r6 = r16
            java.lang.Object[] r1 = r1.getSpans(r15, r6, r3)
            android.text.style.CharacterStyle[] r1 = (android.text.style.CharacterStyle[]) r1
            int r3 = r1.length
            if (r3 == 0) goto L3d
            int r3 = r1.length
            r5 = 1
            r6 = 0
            if (r3 != r5) goto L23
            r3 = r1[r6]
            if (r3 != r0) goto L23
            goto L3d
        L23:
            android.text.TextPaint r3 = r0.f8528p
            if (r3 != 0) goto L2e
            android.text.TextPaint r3 = new android.text.TextPaint
            r3.<init>()
            r0.f8528p = r3
        L2e:
            r4 = r3
            r4.set(r2)
        L32:
            int r3 = r1.length
            if (r6 >= r3) goto L4c
            r3 = r1[r6]
            r3.updateDrawState(r4)
            int r6 = r6 + 1
            goto L32
        L3d:
            boolean r1 = r2 instanceof android.text.TextPaint
            if (r1 == 0) goto L4c
            r4 = r2
            android.text.TextPaint r4 = (android.text.TextPaint) r4
            goto L4c
        L45:
            boolean r1 = r2 instanceof android.text.TextPaint
            if (r1 == 0) goto L4c
            r4 = r2
            android.text.TextPaint r4 = (android.text.TextPaint) r4
        L4c:
            if (r4 == 0) goto L7c
            int r1 = r4.bgColor
            if (r1 == 0) goto L7c
            short r1 = r0.f8527n
            float r1 = (float) r1
            float r8 = r17 + r1
            r1 = r18
            float r7 = (float) r1
            r1 = r20
            float r9 = (float) r1
            int r1 = r4.getColor()
            android.graphics.Paint$Style r3 = r4.getStyle()
            int r5 = r4.bgColor
            r4.setColor(r5)
            android.graphics.Paint$Style r5 = android.graphics.Paint.Style.FILL
            r4.setStyle(r5)
            r5 = r13
            r6 = r17
            r10 = r4
            r5.drawRect(r6, r7, r8, r9, r10)
            r4.setStyle(r3)
            r4.setColor(r1)
        L7c:
            T1.i r1 = T1.i.a()
            r1.getClass()
            r1 = r19
            float r10 = (float) r1
            if (r4 == 0) goto L89
            r2 = r4
        L89:
            T1.w r0 = r0.f8526m
            B8.t r1 = r0.f8525b
            java.lang.Object r3 = r1.f807m
            android.graphics.Typeface r3 = (android.graphics.Typeface) r3
            android.graphics.Typeface r4 = r2.getTypeface()
            r2.setTypeface(r3)
            int r0 = r0.f8524a
            int r7 = r0 * 2
            r8 = 2
            java.lang.Object r0 = r1.o
            r6 = r0
            char[] r6 = (char[]) r6
            r5 = r13
            r9 = r17
            r11 = r2
            r5.drawText(r6, r7, r8, r9, r10, r11)
            r2.setTypeface(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: T1.x.draw(android.graphics.Canvas, java.lang.CharSequence, int, int, float, int, int, int, android.graphics.Paint):void");
    }

    @Override // android.text.style.ReplacementSpan
    public final int getSize(Paint paint, CharSequence charSequence, int i7, int i10, Paint.FontMetricsInt fontMetricsInt) {
        Paint.FontMetricsInt fontMetricsInt2 = this.l;
        paint.getFontMetricsInt(fontMetricsInt2);
        float fAbs = Math.abs(fontMetricsInt2.descent - fontMetricsInt2.ascent) * 1.0f;
        w wVar = this.f8526m;
        this.o = fAbs / (wVar.c().b(14) != 0 ? ((ByteBuffer) r8.o).getShort(r1 + r8.l) : (short) 0);
        U1.a aVarC = wVar.c();
        int iB = aVarC.b(14);
        if (iB != 0) {
            ((ByteBuffer) aVarC.o).getShort(iB + aVarC.l);
        }
        short s6 = (short) ((wVar.c().b(12) != 0 ? ((ByteBuffer) r5.o).getShort(r7 + r5.l) : (short) 0) * this.o);
        this.f8527n = s6;
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = fontMetricsInt2.ascent;
            fontMetricsInt.descent = fontMetricsInt2.descent;
            fontMetricsInt.top = fontMetricsInt2.top;
            fontMetricsInt.bottom = fontMetricsInt2.bottom;
        }
        return s6;
    }
}
