package com.google.zxing.o;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.common.d;
import com.google.zxing.g;
import com.google.zxing.h;
import com.google.zxing.i;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: compiled from: PDF417Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements g {
    private static h[] b(com.google.zxing.b bVar, Map<DecodeHintType, ?> map, boolean z) throws NotFoundException, ChecksumException, FormatException {
        ArrayList arrayList = new ArrayList();
        com.google.zxing.o.d.b bVarB = com.google.zxing.o.d.a.b(bVar, map, z);
        for (i[] iVarArr : bVarB.b()) {
            d dVarI = com.google.zxing.pdf417.decoder.i.i(bVarB.a(), iVarArr[4], iVarArr[5], iVarArr[6], iVarArr[7], e(iVarArr), c(iVarArr));
            h hVar = new h(dVarI.h(), dVarI.e(), iVarArr, BarcodeFormat.PDF_417);
            hVar.h(ResultMetadataType.ERROR_CORRECTION_LEVEL, dVarI.b());
            c cVar = (c) dVarI.d();
            if (cVar != null) {
                hVar.h(ResultMetadataType.PDF417_EXTRA_METADATA, cVar);
            }
            arrayList.add(hVar);
        }
        return (h[]) arrayList.toArray(new h[arrayList.size()]);
    }

    private static int c(i[] iVarArr) {
        return Math.max(Math.max(d(iVarArr[0], iVarArr[4]), (d(iVarArr[6], iVarArr[2]) * 17) / 18), Math.max(d(iVarArr[1], iVarArr[5]), (d(iVarArr[7], iVarArr[3]) * 17) / 18));
    }

    private static int d(i iVar, i iVar2) {
        if (iVar == null || iVar2 == null) {
            return 0;
        }
        return (int) Math.abs(iVar.c() - iVar2.c());
    }

    private static int e(i[] iVarArr) {
        return Math.min(Math.min(f(iVarArr[0], iVarArr[4]), (f(iVarArr[6], iVarArr[2]) * 17) / 18), Math.min(f(iVarArr[1], iVarArr[5]), (f(iVarArr[7], iVarArr[3]) * 17) / 18));
    }

    private static int f(i iVar, i iVar2) {
        if (iVar == null || iVar2 == null) {
            return Integer.MAX_VALUE;
        }
        return (int) Math.abs(iVar.c() - iVar2.c());
    }

    @Override // com.google.zxing.g
    public h a(com.google.zxing.b bVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        h[] hVarArrB = b(bVar, map, false);
        if (hVarArrB == null || hVarArrB.length == 0 || hVarArrB[0] == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        return hVarArrB[0];
    }

    @Override // com.google.zxing.g
    public void reset() {
    }
}
