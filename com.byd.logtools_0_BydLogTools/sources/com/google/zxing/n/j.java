package com.google.zxing.n;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: compiled from: MultiFormatUPCEANReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final p[] f4340a;

    public j(Map<DecodeHintType, ?> map) {
        Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(BarcodeFormat.EAN_13)) {
                arrayList.add(new e());
            } else if (collection.contains(BarcodeFormat.UPC_A)) {
                arrayList.add(new l());
            }
            if (collection.contains(BarcodeFormat.EAN_8)) {
                arrayList.add(new f());
            }
            if (collection.contains(BarcodeFormat.UPC_E)) {
                arrayList.add(new q());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new e());
            arrayList.add(new f());
            arrayList.add(new q());
        }
        this.f4340a = (p[]) arrayList.toArray(new p[arrayList.size()]);
    }

    @Override // com.google.zxing.n.k
    public com.google.zxing.h b(int i, com.google.zxing.common.a aVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        int[] iArrO = p.o(aVar);
        for (p pVar : this.f4340a) {
            try {
                com.google.zxing.h hVarL = pVar.l(i, aVar, iArrO, map);
                boolean z = hVarL.b() == BarcodeFormat.EAN_13 && hVarL.f().charAt(0) == '0';
                Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
                boolean z2 = collection == null || collection.contains(BarcodeFormat.UPC_A);
                if (!z || !z2) {
                    return hVarL;
                }
                com.google.zxing.h hVar = new com.google.zxing.h(hVarL.f().substring(1), hVarL.c(), hVarL.e(), BarcodeFormat.UPC_A);
                hVar.g(hVarL.d());
                return hVar;
            } catch (ReaderException unused) {
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.n.k, com.google.zxing.g
    public void reset() {
        for (p pVar : this.f4340a) {
            pVar.reset();
        }
    }
}
