package okio;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: Options.java */
/* JADX INFO: loaded from: classes.dex */
public final class f extends AbstractList<ByteString> implements RandomAccess {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ByteString[] f4654b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int[] f4655c;

    private f(ByteString[] byteStringArr, int[] iArr) {
        this.f4654b = byteStringArr;
        this.f4655c = iArr;
    }

    private static void a(long j, b bVar, int i, List<ByteString> list, int i2, int i3, List<Integer> list2) {
        int i4;
        int i5;
        int i6;
        b bVar2;
        int i7 = i2;
        if (i7 >= i3) {
            throw new AssertionError();
        }
        for (int i8 = i7; i8 < i3; i8++) {
            if (list.get(i8).size() < i) {
                throw new AssertionError();
            }
        }
        ByteString byteString = list.get(i2);
        ByteString byteString2 = list.get(i3 - 1);
        int iIntValue = -1;
        if (i == byteString.size()) {
            iIntValue = list2.get(i7).intValue();
            i7++;
            byteString = list.get(i7);
        }
        int i9 = i7;
        if (byteString.getByte(i) == byteString2.getByte(i)) {
            int i10 = 0;
            int iMin = Math.min(byteString.size(), byteString2.size());
            for (int i11 = i; i11 < iMin && byteString.getByte(i11) == byteString2.getByte(i11); i11++) {
                i10++;
            }
            long jC = 1 + j + ((long) c(bVar)) + 2 + ((long) i10);
            bVar.J(-i10);
            bVar.J(iIntValue);
            int i12 = i;
            while (true) {
                i4 = i + i10;
                if (i12 >= i4) {
                    break;
                }
                bVar.J(byteString.getByte(i12) & 255);
                i12++;
            }
            if (i9 + 1 == i3) {
                if (i4 != list.get(i9).size()) {
                    throw new AssertionError();
                }
                bVar.J(list2.get(i9).intValue());
                return;
            } else {
                b bVar3 = new b();
                bVar.J((int) ((((long) c(bVar3)) + jC) * (-1)));
                a(jC, bVar3, i4, list, i9, i3, list2);
                bVar.H(bVar3, bVar3.B());
                return;
            }
        }
        int i13 = 1;
        for (int i14 = i9 + 1; i14 < i3; i14++) {
            if (list.get(i14 - 1).getByte(i) != list.get(i14).getByte(i)) {
                i13++;
            }
        }
        long jC2 = j + ((long) c(bVar)) + 2 + ((long) (i13 * 2));
        bVar.J(i13);
        bVar.J(iIntValue);
        for (int i15 = i9; i15 < i3; i15++) {
            byte b2 = list.get(i15).getByte(i);
            if (i15 == i9 || b2 != list.get(i15 - 1).getByte(i)) {
                bVar.J(b2 & 255);
            }
        }
        b bVar4 = new b();
        int i16 = i9;
        while (i16 < i3) {
            byte b3 = list.get(i16).getByte(i);
            int i17 = i16 + 1;
            int i18 = i17;
            while (true) {
                if (i18 >= i3) {
                    i5 = i3;
                    break;
                } else {
                    if (b3 != list.get(i18).getByte(i)) {
                        i5 = i18;
                        break;
                    }
                    i18++;
                }
            }
            if (i17 == i5 && i + 1 == list.get(i16).size()) {
                bVar.J(list2.get(i16).intValue());
                i6 = i5;
                bVar2 = bVar4;
            } else {
                bVar.J((int) ((((long) c(bVar4)) + jC2) * (-1)));
                i6 = i5;
                bVar2 = bVar4;
                a(jC2, bVar4, i + 1, list, i16, i5, list2);
            }
            bVar4 = bVar2;
            i16 = i6;
        }
        b bVar5 = bVar4;
        bVar.H(bVar5, bVar5.B());
    }

    private static int c(b bVar) {
        return (int) (bVar.B() / 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00bc, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static okio.f d(okio.ByteString... r10) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.f.d(okio.ByteString[]):okio.f");
    }

    @Override // java.util.AbstractList, java.util.List
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ByteString get(int i) {
        return this.f4654b[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f4654b.length;
    }
}
