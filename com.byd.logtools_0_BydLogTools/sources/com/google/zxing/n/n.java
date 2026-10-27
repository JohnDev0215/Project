package com.google.zxing.n;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultMetadataType;
import java.util.EnumMap;
import java.util.Map;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: UPCEANExtension5Support.java */
/* JADX INFO: loaded from: classes.dex */
final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f4343a = {24, 20, 18, 17, 12, 6, 3, 10, 9, 5};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f4344b = new int[4];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final StringBuilder f4345c = new StringBuilder();

    n() {
    }

    private int a(com.google.zxing.common.a aVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        int[] iArr2 = this.f4344b;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int iL = aVar.l();
        int iK = iArr[1];
        int i = 0;
        for (int i2 = 0; i2 < 5 && iK < iL; i2++) {
            int i3 = p.i(aVar, iArr2, iK, p.f4353e);
            sb.append((char) ((i3 % 10) + 48));
            for (int i4 : iArr2) {
                iK += i4;
            }
            if (i3 >= 10) {
                i |= 1 << (4 - i2);
            }
            if (i2 != 4) {
                iK = aVar.k(aVar.j(iK));
            }
        }
        if (sb.length() != 5) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (d(sb.toString()) == c(i)) {
            return iK;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int c(int i) throws NotFoundException {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == f4343a[i2]) {
                return i2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int d(CharSequence charSequence) {
        int length = charSequence.length();
        int iCharAt = 0;
        for (int i = length - 2; i >= 0; i -= 2) {
            iCharAt += charSequence.charAt(i) - '0';
        }
        int iCharAt2 = iCharAt * 3;
        for (int i2 = length - 1; i2 >= 0; i2 -= 2) {
            iCharAt2 += charSequence.charAt(i2) - '0';
        }
        return (iCharAt2 * 3) % 10;
    }

    private static String e(String str) {
        char cCharAt = str.charAt(0);
        String str2 = HttpUrl.FRAGMENT_ENCODE_SET;
        if (cCharAt == '0') {
            str2 = "£";
        } else if (cCharAt == '5') {
            str2 = "$";
        } else if (cCharAt == '9') {
            switch (str) {
                case "90000":
                    return null;
                case "99990":
                    return "Used";
                case "99991":
                    return "0.00";
            }
        }
        int i = Integer.parseInt(str.substring(1));
        String strValueOf = String.valueOf(i / 100);
        int i2 = i % 100;
        return str2 + strValueOf + '.' + (i2 < 10 ? "0".concat(String.valueOf(i2)) : String.valueOf(i2));
    }

    private static Map<ResultMetadataType, Object> f(String str) {
        String strE;
        if (str.length() != 5 || (strE = e(str)) == null) {
            return null;
        }
        EnumMap enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.SUGGESTED_PRICE, strE);
        return enumMap;
    }

    com.google.zxing.h b(int i, com.google.zxing.common.a aVar, int[] iArr) throws NotFoundException {
        StringBuilder sb = this.f4345c;
        sb.setLength(0);
        int iA = a(aVar, iArr, sb);
        String string = sb.toString();
        Map<ResultMetadataType, Object> mapF = f(string);
        float f2 = i;
        com.google.zxing.h hVar = new com.google.zxing.h(string, null, new com.google.zxing.i[]{new com.google.zxing.i((iArr[0] + iArr[1]) / 2.0f, f2), new com.google.zxing.i(iA, f2)}, BarcodeFormat.UPC_EAN_EXTENSION);
        if (mapF != null) {
            hVar.g(mapF);
        }
        return hVar;
    }
}
