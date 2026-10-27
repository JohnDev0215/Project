package n7;

import java.util.RandomAccess;

/* JADX INFO: renamed from: n7.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1997l extends AbstractC1989d implements RandomAccess {
    public final /* synthetic */ int[] l;

    public C1997l(int[] iArr) {
        this.l = iArr;
    }

    @Override // n7.AbstractC1986a, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (!(obj instanceof Integer)) {
            return false;
        }
        int iIntValue = ((Number) obj).intValue();
        int[] iArr = this.l;
        A7.m.f(iArr, "<this>");
        int length = iArr.length;
        int i7 = 0;
        while (true) {
            if (i7 >= length) {
                i7 = -1;
                break;
            }
            if (iIntValue == iArr[i7]) {
                break;
            }
            i7++;
        }
        return i7 >= 0;
    }

    @Override // n7.AbstractC1986a
    public final int d() {
        return this.l.length;
    }

    @Override // java.util.List
    public final Object get(int i7) {
        return Integer.valueOf(this.l[i7]);
    }

    @Override // n7.AbstractC1989d, java.util.List
    public final int indexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int iIntValue = ((Number) obj).intValue();
        int[] iArr = this.l;
        A7.m.f(iArr, "<this>");
        int length = iArr.length;
        for (int i7 = 0; i7 < length; i7++) {
            if (iIntValue == iArr[i7]) {
                return i7;
            }
        }
        return -1;
    }

    @Override // n7.AbstractC1986a, java.util.Collection
    public final boolean isEmpty() {
        return this.l.length == 0;
    }

    @Override // n7.AbstractC1989d, java.util.List
    public final int lastIndexOf(Object obj) {
        if (!(obj instanceof Integer)) {
            return -1;
        }
        int iIntValue = ((Number) obj).intValue();
        int[] iArr = this.l;
        A7.m.f(iArr, "<this>");
        int length = iArr.length - 1;
        if (length < 0) {
            return -1;
        }
        while (true) {
            int i7 = length - 1;
            if (iIntValue == iArr[length]) {
                return length;
            }
            if (i7 < 0) {
                return -1;
            }
            length = i7;
        }
    }
}
