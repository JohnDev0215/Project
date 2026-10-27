package defpackage;

import android.animation.TypeEvaluator;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class H7 implements TypeEvaluator {
    public C1305p2[] a;

    @Override // android.animation.TypeEvaluator
    public Object evaluate(float f, Object obj, Object obj2) {
        C1305p2[] c1305p2Arr = (C1305p2[]) obj;
        C1305p2[] c1305p2Arr2 = (C1305p2[]) obj2;
        if (!AbstractC1360q2.a(c1305p2Arr, c1305p2Arr2)) {
            throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
        }
        C1305p2[] c1305p2Arr3 = this.a;
        if (c1305p2Arr3 == null || !AbstractC1360q2.a(c1305p2Arr3, c1305p2Arr)) {
            this.a = AbstractC1360q2.e(c1305p2Arr);
        }
        for (int i = 0; i < c1305p2Arr.length; i++) {
            C1305p2 c1305p2 = this.a[i];
            C1305p2 c1305p22 = c1305p2Arr[i];
            C1305p2 c1305p23 = c1305p2Arr2[i];
            Objects.requireNonNull(c1305p2);
            int i2 = 0;
            while (true) {
                float[] fArr = c1305p22.b;
                if (i2 < fArr.length) {
                    c1305p2.b[i2] = (c1305p23.b[i2] * f) + ((1.0f - f) * fArr[i2]);
                    i2++;
                }
            }
        }
        return this.a;
    }
}
