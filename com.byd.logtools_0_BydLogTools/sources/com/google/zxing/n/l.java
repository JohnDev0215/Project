package com.google.zxing.n;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import java.util.Map;

/* JADX INFO: compiled from: UPCAReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class l extends p {
    private final p i = new e();

    private static com.google.zxing.h r(com.google.zxing.h hVar) throws FormatException {
        String strF = hVar.f();
        if (strF.charAt(0) != '0') {
            throw FormatException.getFormatInstance();
        }
        com.google.zxing.h hVar2 = new com.google.zxing.h(strF.substring(1), null, hVar.e(), BarcodeFormat.UPC_A);
        if (hVar.d() != null) {
            hVar2.g(hVar.d());
        }
        return hVar2;
    }

    @Override // com.google.zxing.n.k, com.google.zxing.g
    public com.google.zxing.h a(com.google.zxing.b bVar, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        return r(this.i.a(bVar, map));
    }

    @Override // com.google.zxing.n.p, com.google.zxing.n.k
    public com.google.zxing.h b(int i, com.google.zxing.common.a aVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        return r(this.i.b(i, aVar, map));
    }

    @Override // com.google.zxing.n.p
    protected int k(com.google.zxing.common.a aVar, int[] iArr, StringBuilder sb) throws NotFoundException {
        return this.i.k(aVar, iArr, sb);
    }

    @Override // com.google.zxing.n.p
    public com.google.zxing.h l(int i, com.google.zxing.common.a aVar, int[] iArr, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        return r(this.i.l(i, aVar, iArr, map));
    }

    @Override // com.google.zxing.n.p
    BarcodeFormat p() {
        return BarcodeFormat.UPC_A;
    }
}
