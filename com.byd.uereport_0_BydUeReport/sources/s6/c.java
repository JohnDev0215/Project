package s6;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ReedSolomonEncoder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f7937a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List<b> f7938b;

    public c(a aVar) {
        this.f7937a = aVar;
        ArrayList arrayList = new ArrayList();
        this.f7938b = arrayList;
        arrayList.add(new b(aVar, new int[]{1}));
    }

    public final b a(int i8) {
        if (i8 >= this.f7938b.size()) {
            List<b> list = this.f7938b;
            b bVarG = list.get(list.size() - 1);
            for (int size = this.f7938b.size(); size <= i8; size++) {
                a aVar = this.f7937a;
                bVarG = bVarG.g(new b(aVar, new int[]{1, aVar.c((size - 1) + aVar.d())}));
                this.f7938b.add(bVarG);
            }
        }
        return this.f7938b.get(i8);
    }

    public void b(int[] iArr, int i8) {
        if (i8 == 0) {
            throw new IllegalArgumentException("No error correction bytes");
        }
        int length = iArr.length - i8;
        if (length <= 0) {
            throw new IllegalArgumentException("No data bytes provided");
        }
        b bVarA = a(i8);
        int[] iArr2 = new int[length];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        int[] iArrD = new b(this.f7937a, iArr2).h(i8, 1).b(bVarA)[1].d();
        int length2 = i8 - iArrD.length;
        for (int i9 = 0; i9 < length2; i9++) {
            iArr[length + i9] = 0;
        }
        System.arraycopy(iArrD, 0, iArr, length + length2, iArrD.length);
    }
}
