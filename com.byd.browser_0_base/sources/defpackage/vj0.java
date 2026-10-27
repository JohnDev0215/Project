package defpackage;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;

/* JADX INFO: compiled from: FadingShadow.java */
/* JADX INFO: loaded from: classes2.dex */
public final class vj0 {
    public final Paint a = new Paint();
    public final Matrix b = new Matrix();
    public final LinearGradient c;

    public vj0(int i) {
        float[] fArr = new float[8];
        int[] iArr = new int[8];
        int i2 = 16777215 & i;
        int iAlpha = Color.alpha(i);
        for (int i3 = 0; i3 < 8; i3++) {
            float f = i3 / 7.0f;
            fArr[i3] = f;
            iArr[i3] = (Math.round(iAlpha * (((f * f) * (1.8f - (0.6f * f))) + (1.0f - (2.2f * f)))) << 24) | i2;
        }
        this.c = new LinearGradient(0.0f, 0.0f, 0.0f, 1.0f, iArr, fArr, Shader.TileMode.CLAMP);
    }
}
