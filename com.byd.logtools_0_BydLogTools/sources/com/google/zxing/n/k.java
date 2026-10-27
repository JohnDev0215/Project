package com.google.zxing.n;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultMetadataType;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: compiled from: OneDReader.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class k implements com.google.zxing.g {
    /* JADX WARN: Removed duplicated region for block: B:38:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.google.zxing.h c(com.google.zxing.b r22, java.util.Map<com.google.zxing.DecodeHintType, ?> r23) throws com.google.zxing.NotFoundException {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.n.k.c(com.google.zxing.b, java.util.Map):com.google.zxing.h");
    }

    protected static float d(int[] iArr, int[] iArr2, float f2) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f3 = i;
        float f4 = f3 / i2;
        float f5 = f2 * f4;
        float f6 = 0.0f;
        for (int i4 = 0; i4 < length; i4++) {
            float f7 = iArr2[i4] * f4;
            float f8 = iArr[i4];
            float f9 = f8 > f7 ? f8 - f7 : f7 - f8;
            if (f9 > f5) {
                return Float.POSITIVE_INFINITY;
            }
            f6 += f9;
        }
        return f6 / f3;
    }

    protected static void e(com.google.zxing.common.a aVar, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        int i2 = 0;
        Arrays.fill(iArr, 0, length, 0);
        int iL = aVar.l();
        if (i >= iL) {
            throw NotFoundException.getNotFoundInstance();
        }
        boolean z = !aVar.h(i);
        while (i < iL) {
            if (aVar.h(i) == z) {
                i2++;
                if (i2 == length) {
                    break;
                }
                iArr[i2] = 1;
                z = !z;
            } else {
                iArr[i2] = iArr[i2] + 1;
            }
            i++;
        }
        if (i2 != length) {
            if (i2 != length - 1 || i != iL) {
                throw NotFoundException.getNotFoundInstance();
            }
        }
    }

    protected static void f(com.google.zxing.common.a aVar, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean zH = aVar.h(i);
        while (i > 0 && length >= 0) {
            i--;
            if (aVar.h(i) != zH) {
                length--;
                zH = !zH;
            }
        }
        if (length >= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        e(aVar, i + 1, iArr);
    }

    @Override // com.google.zxing.g
    public com.google.zxing.h a(com.google.zxing.b bVar, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        try {
            return c(bVar, map);
        } catch (NotFoundException e2) {
            if (!(map != null && map.containsKey(DecodeHintType.TRY_HARDER)) || !bVar.e()) {
                throw e2;
            }
            com.google.zxing.b bVarF = bVar.f();
            com.google.zxing.h hVarC = c(bVarF, map);
            Map<ResultMetadataType, Object> mapD = hVarC.d();
            int iIntValue = 270;
            if (mapD != null) {
                ResultMetadataType resultMetadataType = ResultMetadataType.ORIENTATION;
                if (mapD.containsKey(resultMetadataType)) {
                    iIntValue = (((Integer) mapD.get(resultMetadataType)).intValue() + 270) % 360;
                }
            }
            hVarC.h(ResultMetadataType.ORIENTATION, Integer.valueOf(iIntValue));
            com.google.zxing.i[] iVarArrE = hVarC.e();
            if (iVarArrE != null) {
                int iC = bVarF.c();
                for (int i = 0; i < iVarArrE.length; i++) {
                    iVarArrE[i] = new com.google.zxing.i((iC - iVarArrE[i].d()) - 1.0f, iVarArrE[i].c());
                }
            }
            return hVarC;
        }
    }

    public abstract com.google.zxing.h b(int i, com.google.zxing.common.a aVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    @Override // com.google.zxing.g
    public void reset() {
    }
}
