package b.b.g.r;

import android.content.Context;
import android.graphics.Paint;

/* JADX INFO: compiled from: DimensionUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class m {
    public static float a(String str, int i) {
        Paint paint = new Paint();
        paint.setTextSize(i);
        int length = str.length();
        float[] fArr = new float[length];
        paint.getTextWidths(str, fArr);
        float f2 = 0.0f;
        for (int i2 = 0; i2 < length; i2++) {
            f2 += fArr[i2];
        }
        return f2;
    }

    public static boolean b(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
}
