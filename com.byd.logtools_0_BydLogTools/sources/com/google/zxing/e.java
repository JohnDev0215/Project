package com.google.zxing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: compiled from: MultiFormatReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class e implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<DecodeHintType, ?> f4279a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private g[] f4280b;

    private h b(b bVar) throws NotFoundException {
        g[] gVarArr = this.f4280b;
        if (gVarArr != null) {
            for (g gVar : gVarArr) {
                try {
                    return gVar.a(bVar, this.f4279a);
                } catch (ReaderException unused) {
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.g
    public h a(b bVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        d(map);
        return b(bVar);
    }

    public h c(b bVar) throws NotFoundException {
        if (this.f4280b == null) {
            d(null);
        }
        return b(bVar);
    }

    public void d(Map<DecodeHintType, ?> map) {
        this.f4279a = map;
        boolean z = true;
        boolean z2 = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        Collection collection = map == null ? null : (Collection) map.get(DecodeHintType.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (!collection.contains(BarcodeFormat.UPC_A) && !collection.contains(BarcodeFormat.UPC_E) && !collection.contains(BarcodeFormat.EAN_13) && !collection.contains(BarcodeFormat.EAN_8) && !collection.contains(BarcodeFormat.CODABAR) && !collection.contains(BarcodeFormat.CODE_39) && !collection.contains(BarcodeFormat.CODE_93) && !collection.contains(BarcodeFormat.CODE_128) && !collection.contains(BarcodeFormat.ITF) && !collection.contains(BarcodeFormat.RSS_14) && !collection.contains(BarcodeFormat.RSS_EXPANDED)) {
                z = false;
            }
            if (z && !z2) {
                arrayList.add(new com.google.zxing.n.i(map));
            }
            if (collection.contains(BarcodeFormat.QR_CODE)) {
                arrayList.add(new com.google.zxing.p.a());
            }
            if (collection.contains(BarcodeFormat.DATA_MATRIX)) {
                arrayList.add(new com.google.zxing.l.a());
            }
            if (collection.contains(BarcodeFormat.AZTEC)) {
                arrayList.add(new com.google.zxing.k.b());
            }
            if (collection.contains(BarcodeFormat.PDF_417)) {
                arrayList.add(new com.google.zxing.o.b());
            }
            if (collection.contains(BarcodeFormat.MAXICODE)) {
                arrayList.add(new com.google.zxing.m.a());
            }
            if (z && z2) {
                arrayList.add(new com.google.zxing.n.i(map));
            }
        }
        if (arrayList.isEmpty()) {
            if (!z2) {
                arrayList.add(new com.google.zxing.n.i(map));
            }
            arrayList.add(new com.google.zxing.p.a());
            arrayList.add(new com.google.zxing.l.a());
            arrayList.add(new com.google.zxing.k.b());
            arrayList.add(new com.google.zxing.o.b());
            arrayList.add(new com.google.zxing.m.a());
            if (z2) {
                arrayList.add(new com.google.zxing.n.i(map));
            }
        }
        this.f4280b = (g[]) arrayList.toArray(new g[arrayList.size()]);
    }

    @Override // com.google.zxing.g
    public void reset() {
        g[] gVarArr = this.f4280b;
        if (gVarArr != null) {
            for (g gVar : gVarArr) {
                gVar.reset();
            }
        }
    }
}
