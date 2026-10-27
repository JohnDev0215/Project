package b.a.j;

import android.graphics.Point;

/* JADX INFO: loaded from: classes2.dex */
public class c {
    public float a(float f, float f2, float f3, Point point) {
        float degrees = ((float) Math.toDegrees((float) Math.atan(((f2 - point.y) + f3) / Math.abs(f - point.x)))) * 2.0f;
        return f < ((float) point.x) ? 360.0f - degrees : degrees;
    }

    public float b(float f, float f2, Point point) {
        return (float) Math.sqrt(Math.pow(f - point.x, 2.0d) + Math.pow(f2 - point.y, 2.0d));
    }

    public float c(double d, float f) {
        float f2 = (float) (d - ((double) f));
        return ((f2 <= 0.0f || f2 >= 90.0f) && f2 >= -270.0f) ? (((f2 >= 0.0f || f2 <= -90.0f) && f2 <= 270.0f) || f2 <= 0.0f) ? f2 : f2 - 360.0f : f2 < 0.0f ? f2 + 360.0f : f2;
    }
}
