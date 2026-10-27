package defpackage;

import android.text.TextPaint;

/* JADX INFO: compiled from: TypefaceEmojiSpan.java */
/* JADX INFO: loaded from: classes.dex */
public final class lh3 extends pg0 {
    public TextPaint i;

    public lh3(kh3 kh3Var) {
        super(kh3Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003d  */
    @Override // android.text.style.ReplacementSpan
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void draw(android.graphics.Canvas r13, @android.annotation.SuppressLint({"UnknownNullness"}) java.lang.CharSequence r14, int r15, int r16, float r17, int r18, int r19, int r20, android.graphics.Paint r21) {
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
            android.text.TextPaint r3 = r0.i
            if (r3 != 0) goto L2e
            android.text.TextPaint r3 = new android.text.TextPaint
            r3.<init>()
            r0.i = r3
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
            short r1 = r0.g
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
            androidx.emoji2.text.d r1 = androidx.emoji2.text.d.a()
            r1.getClass()
            r1 = r19
            float r10 = (float) r1
            if (r4 == 0) goto L89
            r2 = r4
        L89:
            kh3 r0 = r0.f
            androidx.emoji2.text.h r1 = r0.b
            android.graphics.Typeface r3 = r1.d
            android.graphics.Typeface r4 = r2.getTypeface()
            r2.setTypeface(r3)
            int r0 = r0.a
            int r7 = r0 * 2
            char[] r6 = r1.b
            r8 = 2
            r5 = r13
            r9 = r17
            r11 = r2
            r5.drawText(r6, r7, r8, r9, r10, r11)
            r2.setTypeface(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.lh3.draw(android.graphics.Canvas, java.lang.CharSequence, int, int, float, int, int, int, android.graphics.Paint):void");
    }
}
