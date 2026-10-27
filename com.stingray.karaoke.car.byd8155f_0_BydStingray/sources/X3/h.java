package X3;

import a4.C0920a;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes.dex */
public final class h extends b {

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final RectF f10061D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final P3.a f10062E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final float[] f10063F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final Path f10064G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final e f10065H;

    public h(O3.j jVar, e eVar) {
        super(jVar, eVar);
        this.f10061D = new RectF();
        P3.a aVar = new P3.a();
        this.f10062E = aVar;
        this.f10063F = new float[8];
        this.f10064G = new Path();
        this.f10065H = eVar;
        aVar.setAlpha(0);
        aVar.setStyle(Paint.Style.FILL);
        aVar.setColor(eVar.l);
    }

    @Override // X3.b, Q3.e
    public final void a(RectF rectF, Matrix matrix, boolean z6) {
        super.a(rectF, matrix, z6);
        RectF rectF2 = this.f10061D;
        e eVar = this.f10065H;
        rectF2.set(0.0f, 0.0f, eVar.f10044j, eVar.f10045k);
        this.f10007n.mapRect(rectF2);
        rectF.set(rectF2);
    }

    @Override // X3.b
    public final void h(Canvas canvas, Matrix matrix, int i7, C0920a c0920a) {
        e eVar = this.f10065H;
        int iAlpha = Color.alpha(eVar.l);
        if (iAlpha == 0) {
            return;
        }
        P3.a aVar = this.f10062E;
        aVar.setColor(eVar.l);
        int iIntValue = (int) ((((iAlpha / 255.0f) * (this.f10015w.f7422j == null ? 100 : ((Integer) r3.d()).intValue())) / 100.0f) * (i7 / 255.0f) * 255.0f);
        aVar.setAlpha(iIntValue);
        if (c0920a == null || Color.alpha(c0920a.d) <= 0) {
            aVar.clearShadowLayer();
        } else {
            aVar.setShadowLayer(Math.max(c0920a.f11090a, Float.MIN_VALUE), c0920a.f11091b, c0920a.c, c0920a.d);
        }
        if (iIntValue > 0) {
            float[] fArr = this.f10063F;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            float f10 = eVar.f10044j;
            fArr[2] = f10;
            fArr[3] = 0.0f;
            fArr[4] = f10;
            float f11 = eVar.f10045k;
            fArr[5] = f11;
            fArr[6] = 0.0f;
            fArr[7] = f11;
            matrix.mapPoints(fArr);
            Path path = this.f10064G;
            path.reset();
            path.moveTo(fArr[0], fArr[1]);
            path.lineTo(fArr[2], fArr[3]);
            path.lineTo(fArr[4], fArr[5]);
            path.lineTo(fArr[6], fArr[7]);
            path.lineTo(fArr[0], fArr[1]);
            path.close();
            canvas.drawPath(path, aVar);
        }
    }
}
