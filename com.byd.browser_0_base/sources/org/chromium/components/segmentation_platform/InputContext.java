package org.chromium.components.segmentation_platform;

import J.N;
import defpackage.c62;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class InputContext {
    public final HashMap<String, c62> a = new HashMap<>();

    @CalledByNative
    public void fillNativeInputContext(long j) {
        HashMap<String, c62> map = this.a;
        Iterator<Map.Entry<String, c62>> it = map.entrySet().iterator();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (it.hasNext()) {
            switch (it.next().getValue().a) {
                case 1:
                    i++;
                    break;
                case 2:
                    i2++;
                    break;
                case 3:
                    i3++;
                    break;
                case 4:
                    i4++;
                    break;
                case 5:
                    i5++;
                    break;
                case 6:
                    i6++;
                    break;
                case 7:
                    i7++;
                    break;
                case 8:
                    i8++;
                    break;
                default:
                    throw new IllegalArgumentException("Metadata value type not supported");
            }
        }
        String[] strArr = new String[i];
        boolean[] zArr = new boolean[i];
        String[] strArr2 = new String[i2];
        int[] iArr = new int[i2];
        String[] strArr3 = new String[i3];
        float[] fArr = new float[i3];
        String[] strArr4 = new String[i4];
        double[] dArr = new double[i4];
        String[] strArr5 = new String[i5];
        String[] strArr6 = new String[i5];
        String[] strArr7 = new String[i6];
        long[] jArr = new long[i6];
        String[] strArr8 = new String[i7];
        long[] jArr2 = new long[i7];
        String[] strArr9 = new String[i8];
        GURL[] gurlArr = new GURL[i8];
        Iterator<Map.Entry<String, c62>> it2 = map.entrySet().iterator();
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (it2.hasNext()) {
            Map.Entry<String, c62> next = it2.next();
            String key = next.getKey();
            Iterator<Map.Entry<String, c62>> it3 = it2;
            c62 value = next.getValue();
            String[] strArr10 = strArr;
            switch (value.a) {
                case 1:
                    strArr10[i9] = key;
                    zArr[i9] = false;
                    i9++;
                    continue;
                    strArr = strArr10;
                    it2 = it3;
                    break;
                case 2:
                    strArr2[i10] = key;
                    iArr[i10] = 0;
                    i10++;
                    continue;
                    strArr = strArr10;
                    it2 = it3;
                    break;
                case 3:
                    strArr3[i11] = key;
                    fArr[i11] = value.b;
                    i11++;
                    break;
                case 4:
                    strArr4[i12] = key;
                    dArr[i12] = 0.0d;
                    i12++;
                    break;
                case 5:
                    strArr5[i13] = key;
                    strArr6[i13] = null;
                    i13++;
                    break;
                case 6:
                    strArr7[i14] = key;
                    jArr[i14] = 0;
                    i14++;
                    break;
                case 7:
                    strArr8[i15] = key;
                    jArr2[i15] = 0;
                    i15++;
                    break;
                case 8:
                    strArr9[i16] = key;
                    gurlArr[i16] = value.c;
                    i16++;
                    break;
                default:
                    throw new IllegalArgumentException("Type not supported");
            }
            strArr = strArr10;
            it2 = it3;
        }
        N.MV2MMUx8(j, strArr, zArr, strArr2, iArr, strArr3, fArr, strArr4, dArr, strArr5, strArr6, strArr7, jArr, strArr8, jArr2, strArr9, gurlArr);
    }
}
