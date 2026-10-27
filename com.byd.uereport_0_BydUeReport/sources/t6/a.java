package t6;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Map;
import r6.b;
import v6.c;
import v6.f;

/* JADX INFO: compiled from: QRCodeWriter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public static b b(f fVar, int i8, int i9, int i10) {
        v6.b bVarA = fVar.a();
        if (bVarA == null) {
            throw new IllegalStateException();
        }
        int iE = bVarA.e();
        int iD = bVarA.d();
        int i11 = i10 * 2;
        int i12 = iE + i11;
        int i13 = i11 + iD;
        int iMax = Math.max(i8, i12);
        int iMax2 = Math.max(i9, i13);
        int iMin = Math.min(iMax / i12, iMax2 / i13);
        int i14 = (iMax - (iE * iMin)) / 2;
        int i15 = (iMax2 - (iD * iMin)) / 2;
        b bVar = new b(iMax, iMax2);
        int i16 = 0;
        while (i16 < iD) {
            int i17 = 0;
            int i18 = i14;
            while (i17 < iE) {
                if (bVarA.b(i17, i16) == 1) {
                    bVar.d(i18, i15, iMin, iMin);
                }
                i17++;
                i18 += iMin;
            }
            i16++;
            i15 += iMin;
        }
        return bVar;
    }

    public b a(String str, BarcodeFormat barcodeFormat, int i8, int i9, Map<EncodeHintType, ?> map) throws WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        }
        if (barcodeFormat != BarcodeFormat.QR_CODE) {
            throw new IllegalArgumentException("Can only encode QR_CODE, but got " + barcodeFormat);
        }
        if (i8 < 0 || i9 < 0) {
            throw new IllegalArgumentException("Requested dimensions are too small: " + i8 + 'x' + i9);
        }
        ErrorCorrectionLevel errorCorrectionLevelValueOf = ErrorCorrectionLevel.L;
        int i10 = 4;
        if (map != null) {
            EncodeHintType encodeHintType = EncodeHintType.ERROR_CORRECTION;
            if (map.containsKey(encodeHintType)) {
                errorCorrectionLevelValueOf = ErrorCorrectionLevel.valueOf(map.get(encodeHintType).toString());
            }
            EncodeHintType encodeHintType2 = EncodeHintType.MARGIN;
            if (map.containsKey(encodeHintType2)) {
                i10 = Integer.parseInt(map.get(encodeHintType2).toString());
            }
        }
        return b(c.n(str, errorCorrectionLevelValueOf, map), i8, i9, i10);
    }
}
