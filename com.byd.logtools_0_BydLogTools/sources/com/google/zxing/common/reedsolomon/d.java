package com.google.zxing.common.reedsolomon;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ReedSolomonEncoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f4245a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<b> f4246b;

    public d(a aVar) {
        this.f4245a = aVar;
        ArrayList arrayList = new ArrayList();
        this.f4246b = arrayList;
        arrayList.add(new b(aVar, new int[]{1}));
    }

    private b a(int i) {
        if (i >= this.f4246b.size()) {
            List<b> list = this.f4246b;
            b bVarI = list.get(list.size() - 1);
            for (int size = this.f4246b.size(); size <= i; size++) {
                a aVar = this.f4245a;
                bVarI = bVarI.i(new b(aVar, new int[]{1, aVar.c((size - 1) + aVar.d())}));
                this.f4246b.add(bVarI);
            }
        }
        return this.f4246b.get(i);
    }

    public void b(int[] iArr, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("No error correction bytes");
        }
        int length = iArr.length - i;
        if (length <= 0) {
            throw new IllegalArgumentException("No data bytes provided");
        }
        b bVarA = a(i);
        int[] iArr2 = new int[length];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        int[] iArrE = new b(this.f4245a, iArr2).j(i, 1).b(bVarA)[1].e();
        int length2 = i - iArrE.length;
        for (int i2 = 0; i2 < length2; i2++) {
            iArr[length + i2] = 0;
        }
        System.arraycopy(iArrE, 0, iArr, length + length2, iArrE.length);
    }
}
