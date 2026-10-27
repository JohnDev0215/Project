package com.google.zxing.n;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultMetadataType;
import java.util.EnumMap;
import java.util.Map;

/* JADX INFO: compiled from: UPCEANExtension2Support.java */
/* JADX INFO: loaded from: classes.dex */
final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int[] f4341a = new int[4];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final StringBuilder f4342b = new StringBuilder();

    m() {
    }

    private int a(com.google.zxing.common.a aVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.f4341a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int iL = aVar.l();
        int iK = iArr[1];
        int i = 0;
        for (int i2 = 0; i2 < 2 && iK < iL; i2++) {
            int i3 = p.i(aVar, iArr2, iK, p.f4353e);
            sb.append((char) ((i3 % 10) + 48));
            for (int i4 : iArr2) {
                iK += i4;
            }
            if (i3 >= 10) {
                i |= 1 << (1 - i2);
            }
            if (i2 != 1) {
                iK = aVar.k(aVar.j(iK));
            }
        }
        if (sb.length() != 2) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (Integer.parseInt(sb.toString()) % 4 == i) {
            return iK;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static Map<ResultMetadataType, Object> c(String str) {
        if (str.length() != 2) {
            return null;
        }
        EnumMap enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.ISSUE_NUMBER, Integer.valueOf(str));
        return enumMap;
    }

    com.google.zxing.h b(int i, com.google.zxing.common.a aVar, int[] iArr) throws NotFoundException {
        StringBuilder sb = this.f4342b;
        sb.setLength(0);
        int iA = a(aVar, iArr, sb);
        String string = sb.toString();
        Map<ResultMetadataType, Object> mapC = c(string);
        float f2 = i;
        com.google.zxing.h hVar = new com.google.zxing.h(string, null, new com.google.zxing.i[]{new com.google.zxing.i((iArr[0] + iArr[1]) / 2.0f, f2), new com.google.zxing.i(iA, f2)}, BarcodeFormat.UPC_EAN_EXTENSION);
        if (mapC != null) {
            hVar.g(mapC);
        }
        return hVar;
    }
}
