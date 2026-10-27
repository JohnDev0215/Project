package i7;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;
import okio.ByteString;

/* JADX INFO: compiled from: Options.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f extends AbstractList<ByteString> implements RandomAccess {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ByteString[] f5517d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int[] f5518e;

    public f(ByteString[] byteStringArr, int[] iArr) {
        this.f5517d = byteStringArr;
        this.f5518e = iArr;
    }

    public static void a(long j8, b bVar, int i8, List<ByteString> list, int i9, int i10, List<Integer> list2) {
        int i11;
        int i12;
        int i13;
        b bVar2;
        int i14 = i9;
        if (i14 >= i10) {
            throw new AssertionError();
        }
        for (int i15 = i14; i15 < i10; i15++) {
            if (list.get(i15).size() < i8) {
                throw new AssertionError();
            }
        }
        ByteString byteString = list.get(i9);
        ByteString byteString2 = list.get(i10 - 1);
        int iIntValue = -1;
        if (i8 == byteString.size()) {
            iIntValue = list2.get(i14).intValue();
            i14++;
            byteString = list.get(i14);
        }
        int i16 = i14;
        if (byteString.getByte(i8) == byteString2.getByte(i8)) {
            int i17 = 0;
            int iMin = Math.min(byteString.size(), byteString2.size());
            for (int i18 = i8; i18 < iMin && byteString.getByte(i18) == byteString2.getByte(i18); i18++) {
                i17++;
            }
            long jC = 1 + j8 + ((long) c(bVar)) + 2 + ((long) i17);
            bVar.V(-i17);
            bVar.V(iIntValue);
            int i19 = i8;
            while (true) {
                i11 = i8 + i17;
                if (i19 >= i11) {
                    break;
                }
                bVar.V(byteString.getByte(i19) & 255);
                i19++;
            }
            if (i16 + 1 == i10) {
                if (i11 != list.get(i16).size()) {
                    throw new AssertionError();
                }
                bVar.V(list2.get(i16).intValue());
                return;
            } else {
                b bVar3 = new b();
                bVar.V((int) ((((long) c(bVar3)) + jC) * (-1)));
                a(jC, bVar3, i11, list, i16, i10, list2);
                bVar.R(bVar3, bVar3.I());
                return;
            }
        }
        int i20 = 1;
        for (int i21 = i16 + 1; i21 < i10; i21++) {
            if (list.get(i21 - 1).getByte(i8) != list.get(i21).getByte(i8)) {
                i20++;
            }
        }
        long jC2 = j8 + ((long) c(bVar)) + 2 + ((long) (i20 * 2));
        bVar.V(i20);
        bVar.V(iIntValue);
        for (int i22 = i16; i22 < i10; i22++) {
            byte b8 = list.get(i22).getByte(i8);
            if (i22 == i16 || b8 != list.get(i22 - 1).getByte(i8)) {
                bVar.V(b8 & 255);
            }
        }
        b bVar4 = new b();
        int i23 = i16;
        while (i23 < i10) {
            byte b9 = list.get(i23).getByte(i8);
            int i24 = i23 + 1;
            int i25 = i24;
            while (true) {
                if (i25 >= i10) {
                    i12 = i10;
                    break;
                } else {
                    if (b9 != list.get(i25).getByte(i8)) {
                        i12 = i25;
                        break;
                    }
                    i25++;
                }
            }
            if (i24 == i12 && i8 + 1 == list.get(i23).size()) {
                bVar.V(list2.get(i23).intValue());
                i13 = i12;
                bVar2 = bVar4;
            } else {
                bVar.V((int) ((((long) c(bVar4)) + jC2) * (-1)));
                i13 = i12;
                bVar2 = bVar4;
                a(jC2, bVar4, i8 + 1, list, i23, i12, list2);
            }
            bVar4 = bVar2;
            i23 = i13;
        }
        b bVar5 = bVar4;
        bVar.R(bVar5, bVar5.I());
    }

    public static int c(b bVar) {
        return (int) (bVar.I() / 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00bc, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static i7.f d(okio.ByteString... r10) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: i7.f.d(okio.ByteString[]):i7.f");
    }

    @Override // java.util.AbstractList, java.util.List
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public ByteString get(int i8) {
        return this.f5517d[i8];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f5517d.length;
    }
}
