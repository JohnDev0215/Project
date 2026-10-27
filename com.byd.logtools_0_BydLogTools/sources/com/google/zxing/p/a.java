package com.google.zxing.p;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.common.f;
import com.google.zxing.g;
import com.google.zxing.h;
import com.google.zxing.i;
import com.google.zxing.qrcode.decoder.d;
import com.google.zxing.qrcode.detector.c;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: QRCodeReader.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final i[] f4422a = new i[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f4423b = new d();

    private static com.google.zxing.common.b b(com.google.zxing.common.b bVar) throws NotFoundException {
        int[] iArrJ = bVar.j();
        int[] iArrF = bVar.f();
        if (iArrJ == null || iArrF == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        float fC = c(iArrJ, bVar);
        int i = iArrJ[1];
        int i2 = iArrF[1];
        int i3 = iArrJ[0];
        int i4 = iArrF[0];
        if (i3 >= i4 || i >= i2) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i5 = i2 - i;
        if (i5 != i4 - i3 && (i4 = i3 + i5) >= bVar.k()) {
            throw NotFoundException.getNotFoundInstance();
        }
        int iRound = Math.round(((i4 - i3) + 1) / fC);
        int iRound2 = Math.round((i5 + 1) / fC);
        if (iRound <= 0 || iRound2 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (iRound2 != iRound) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i6 = (int) (fC / 2.0f);
        int i7 = i + i6;
        int i8 = i3 + i6;
        int i9 = (((int) ((iRound - 1) * fC)) + i8) - i4;
        if (i9 > 0) {
            if (i9 > i6) {
                throw NotFoundException.getNotFoundInstance();
            }
            i8 -= i9;
        }
        int i10 = (((int) ((iRound2 - 1) * fC)) + i7) - i2;
        if (i10 > 0) {
            if (i10 > i6) {
                throw NotFoundException.getNotFoundInstance();
            }
            i7 -= i10;
        }
        com.google.zxing.common.b bVar2 = new com.google.zxing.common.b(iRound, iRound2);
        for (int i11 = 0; i11 < iRound2; i11++) {
            int i12 = ((int) (i11 * fC)) + i7;
            for (int i13 = 0; i13 < iRound; i13++) {
                if (bVar.d(((int) (i13 * fC)) + i8, i12)) {
                    bVar2.m(i13, i11);
                }
            }
        }
        return bVar2;
    }

    private static float c(int[] iArr, com.google.zxing.common.b bVar) throws NotFoundException {
        int iH = bVar.h();
        int iK = bVar.k();
        int i = iArr[0];
        boolean z = true;
        int i2 = iArr[1];
        int i3 = 0;
        while (i < iK && i2 < iH) {
            if (z != bVar.d(i, i2)) {
                i3++;
                if (i3 == 5) {
                    break;
                }
                z = !z;
            }
            i++;
            i2++;
        }
        if (i == iK || i2 == iH) {
            throw NotFoundException.getNotFoundInstance();
        }
        return (i - iArr[0]) / 7.0f;
    }

    @Override // com.google.zxing.g
    public final h a(com.google.zxing.b bVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        i[] iVarArrB;
        com.google.zxing.common.d dVarB;
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            f fVarE = new c(bVar.a()).e(map);
            com.google.zxing.common.d dVarB2 = this.f4423b.b(fVarE.a(), map);
            iVarArrB = fVarE.b();
            dVarB = dVarB2;
        } else {
            dVarB = this.f4423b.b(b(bVar.a()), map);
            iVarArrB = f4422a;
        }
        if (dVarB.d() instanceof com.google.zxing.qrcode.decoder.f) {
            ((com.google.zxing.qrcode.decoder.f) dVarB.d()).a(iVarArrB);
        }
        h hVar = new h(dVarB.h(), dVarB.e(), iVarArrB, BarcodeFormat.QR_CODE);
        List<byte[]> listA = dVarB.a();
        if (listA != null) {
            hVar.h(ResultMetadataType.BYTE_SEGMENTS, listA);
        }
        String strB = dVarB.b();
        if (strB != null) {
            hVar.h(ResultMetadataType.ERROR_CORRECTION_LEVEL, strB);
        }
        if (dVarB.i()) {
            hVar.h(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(dVarB.g()));
            hVar.h(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(dVarB.f()));
        }
        return hVar;
    }

    @Override // com.google.zxing.g
    public void reset() {
    }
}
