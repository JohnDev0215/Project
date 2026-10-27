package okio;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class Options extends AbstractList<ByteString> implements RandomAccess {

    /* JADX INFO: renamed from: b */
    final ByteString[] f5813b;

    /* JADX INFO: renamed from: c */
    final int[] f5814c;

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.f5813b = byteStringArr;
        this.f5814c = iArr;
    }

    private static void a(long j, Buffer buffer, int i2, List<ByteString> list, int i3, int i4, List<Integer> list2) {
        int i5;
        int i6;
        int i7;
        Buffer buffer2;
        int i8 = i3;
        if (i8 >= i4) {
            throw new AssertionError();
        }
        for (int i9 = i8; i9 < i4; i9++) {
            if (list.get(i9).s() < i2) {
                throw new AssertionError();
            }
        }
        ByteString byteString = list.get(i3);
        ByteString byteString2 = list.get(i4 - 1);
        int iIntValue = -1;
        if (i2 == byteString.s()) {
            iIntValue = list2.get(i8).intValue();
            i8++;
            byteString = list.get(i8);
        }
        int i10 = i8;
        if (byteString.j(i2) == byteString2.j(i2)) {
            int i11 = 0;
            int iMin = Math.min(byteString.s(), byteString2.s());
            for (int i12 = i2; i12 < iMin && byteString.j(i12) == byteString2.j(i12); i12++) {
                i11++;
            }
            long jC = 1 + j + ((long) c(buffer)) + 2 + ((long) i11);
            buffer.x(-i11);
            buffer.x(iIntValue);
            int i13 = i2;
            while (true) {
                i5 = i2 + i11;
                if (i13 >= i5) {
                    break;
                }
                buffer.x(byteString.j(i13) & 255);
                i13++;
            }
            if (i10 + 1 == i4) {
                if (i5 != list.get(i10).s()) {
                    throw new AssertionError();
                }
                buffer.x(list2.get(i10).intValue());
                return;
            } else {
                Buffer buffer3 = new Buffer();
                buffer.x((int) ((((long) c(buffer3)) + jC) * (-1)));
                a(jC, buffer3, i5, list, i10, i4, list2);
                buffer.h(buffer3, buffer3.g0());
                return;
            }
        }
        int i14 = 1;
        for (int i15 = i10 + 1; i15 < i4; i15++) {
            if (list.get(i15 - 1).j(i2) != list.get(i15).j(i2)) {
                i14++;
            }
        }
        long jC2 = j + ((long) c(buffer)) + 2 + ((long) (i14 * 2));
        buffer.x(i14);
        buffer.x(iIntValue);
        for (int i16 = i10; i16 < i4; i16++) {
            byte bJ = list.get(i16).j(i2);
            if (i16 == i10 || bJ != list.get(i16 - 1).j(i2)) {
                buffer.x(bJ & 255);
            }
        }
        Buffer buffer4 = new Buffer();
        int i17 = i10;
        while (i17 < i4) {
            byte bJ2 = list.get(i17).j(i2);
            int i18 = i17 + 1;
            int i19 = i18;
            while (true) {
                if (i19 >= i4) {
                    i6 = i4;
                    break;
                } else {
                    if (bJ2 != list.get(i19).j(i2)) {
                        i6 = i19;
                        break;
                    }
                    i19++;
                }
            }
            if (i18 == i6 && i2 + 1 == list.get(i17).s()) {
                buffer.x(list2.get(i17).intValue());
                i7 = i6;
                buffer2 = buffer4;
            } else {
                buffer.x((int) ((((long) c(buffer4)) + jC2) * (-1)));
                i7 = i6;
                buffer2 = buffer4;
                a(jC2, buffer4, i2 + 1, list, i17, i6, list2);
            }
            buffer4 = buffer2;
            i17 = i7;
        }
        Buffer buffer5 = buffer4;
        buffer.h(buffer5, buffer5.g0());
    }

    private static int c(Buffer buffer) {
        return (int) (buffer.g0() / 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:0x00bc, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static okio.Options d(okio.ByteString... r10) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Options.d(okio.ByteString[]):okio.Options");
    }

    @Override // java.util.AbstractList, java.util.List
    /* JADX INFO: renamed from: b */
    public ByteString get(int i2) {
        return this.f5813b[i2];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f5813b.length;
    }
}
