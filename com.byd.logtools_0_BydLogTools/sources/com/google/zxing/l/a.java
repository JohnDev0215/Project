package com.google.zxing.l;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.common.b;
import com.google.zxing.common.d;
import com.google.zxing.common.f;
import com.google.zxing.datamatrix.decoder.c;
import com.google.zxing.datamatrix.detector.Detector;
import com.google.zxing.g;
import com.google.zxing.h;
import com.google.zxing.i;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: DataMatrixReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final i[] f4307a = new i[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f4308b = new c();

    private static b b(b bVar) throws NotFoundException {
        int[] iArrJ = bVar.j();
        int[] iArrF = bVar.f();
        if (iArrJ == null || iArrF == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        int iC = c(iArrJ, bVar);
        int i = iArrJ[1];
        int i2 = iArrF[1];
        int i3 = iArrJ[0];
        int i4 = ((iArrF[0] - i3) + 1) / iC;
        int i5 = ((i2 - i) + 1) / iC;
        if (i4 <= 0 || i5 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i6 = iC / 2;
        int i7 = i + i6;
        int i8 = i3 + i6;
        b bVar2 = new b(i4, i5);
        for (int i9 = 0; i9 < i5; i9++) {
            int i10 = (i9 * iC) + i7;
            for (int i11 = 0; i11 < i4; i11++) {
                if (bVar.d((i11 * iC) + i8, i10)) {
                    bVar2.m(i11, i9);
                }
            }
        }
        return bVar2;
    }

    private static int c(int[] iArr, b bVar) throws NotFoundException {
        int iK = bVar.k();
        int i = iArr[0];
        int i2 = iArr[1];
        while (i < iK && bVar.d(i, i2)) {
            i++;
        }
        if (i == iK) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i3 = i - iArr[0];
        if (i3 != 0) {
            return i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.g
    public h a(com.google.zxing.b bVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        i[] iVarArrB;
        d dVarB;
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            f fVarC = new Detector(bVar.a()).c();
            d dVarB2 = this.f4308b.b(fVarC.a());
            iVarArrB = fVarC.b();
            dVarB = dVarB2;
        } else {
            dVarB = this.f4308b.b(b(bVar.a()));
            iVarArrB = f4307a;
        }
        h hVar = new h(dVarB.h(), dVarB.e(), iVarArrB, BarcodeFormat.DATA_MATRIX);
        List<byte[]> listA = dVarB.a();
        if (listA != null) {
            hVar.h(ResultMetadataType.BYTE_SEGMENTS, listA);
        }
        String strB = dVarB.b();
        if (strB != null) {
            hVar.h(ResultMetadataType.ERROR_CORRECTION_LEVEL, strB);
        }
        return hVar;
    }

    @Override // com.google.zxing.g
    public void reset() {
    }
}
