package X0;

import A7.m;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import n0.AbstractC1861K;
import p0.e;
import p0.g;
import p0.h;

/* JADX INFO: loaded from: classes.dex */
public final class a extends CharacterStyle implements UpdateAppearance {
    public final e l;

    public a(e eVar) {
        this.l = eVar;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        if (textPaint != null) {
            g gVar = g.f20641a;
            e eVar = this.l;
            if (m.a(eVar, gVar)) {
                textPaint.setStyle(Paint.Style.FILL);
                return;
            }
            if (eVar instanceof h) {
                textPaint.setStyle(Paint.Style.STROKE);
                h hVar = (h) eVar;
                textPaint.setStrokeWidth(hVar.f20642a);
                textPaint.setStrokeMiter(hVar.f20643b);
                int i7 = hVar.d;
                textPaint.setStrokeJoin(AbstractC1861K.t(i7, 0) ? Paint.Join.MITER : AbstractC1861K.t(i7, 1) ? Paint.Join.ROUND : AbstractC1861K.t(i7, 2) ? Paint.Join.BEVEL : Paint.Join.MITER);
                int i10 = hVar.c;
                textPaint.setStrokeCap(AbstractC1861K.s(i10, 0) ? Paint.Cap.BUTT : AbstractC1861K.s(i10, 1) ? Paint.Cap.ROUND : AbstractC1861K.s(i10, 2) ? Paint.Cap.SQUARE : Paint.Cap.BUTT);
                hVar.getClass();
                textPaint.setPathEffect(null);
            }
        }
    }
}
