package com.google.zxing.m;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.common.b;
import com.google.zxing.common.d;
import com.google.zxing.g;
import com.google.zxing.h;
import com.google.zxing.i;
import com.google.zxing.m.b.c;
import java.util.Map;

/* JADX INFO: compiled from: MaxiCodeReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final i[] f4309a = new i[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final c f4310b = new c();

    private static b b(b bVar) throws NotFoundException {
        int[] iArrG = bVar.g();
        if (iArrG == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i = iArrG[0];
        int i2 = iArrG[1];
        int i3 = iArrG[2];
        int i4 = iArrG[3];
        b bVar2 = new b(30, 33);
        for (int i5 = 0; i5 < 33; i5++) {
            int i6 = (((i5 * i4) + (i4 / 2)) / 33) + i2;
            for (int i7 = 0; i7 < 30; i7++) {
                if (bVar.d(((((i7 * i3) + (i3 / 2)) + (((i5 & 1) * i3) / 2)) / 30) + i, i6)) {
                    bVar2.m(i7, i5);
                }
            }
        }
        return bVar2;
    }

    @Override // com.google.zxing.g
    public h a(com.google.zxing.b bVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            throw NotFoundException.getNotFoundInstance();
        }
        d dVarB = this.f4310b.b(b(bVar.a()), map);
        h hVar = new h(dVarB.h(), dVarB.e(), f4309a, BarcodeFormat.MAXICODE);
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
