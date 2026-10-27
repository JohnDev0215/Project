package org.apache.commons.compress.harmony.pack200;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class IntList {
    private int[] array;
    private int firstIndex;
    private int lastIndex;
    private int modCount;

    public IntList() {
        this(10);
    }

    public IntList(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        this.lastIndex = 0;
        this.firstIndex = 0;
        this.array = new int[i];
    }

    public boolean add(int i) {
        if (this.lastIndex == this.array.length) {
            growAtEnd(1);
        }
        int[] iArr = this.array;
        int i2 = this.lastIndex;
        this.lastIndex = i2 + 1;
        iArr[i2] = i;
        this.modCount++;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void add(int r7, int r8) {
        /*
            r6 = this;
            int r0 = r6.lastIndex
            int r1 = r6.firstIndex
            int r2 = r0 - r1
            r3 = 1
            if (r7 <= 0) goto L3f
            if (r7 >= r2) goto L3f
            if (r1 != 0) goto L16
            int[] r4 = r6.array
            int r4 = r4.length
            if (r0 != r4) goto L16
            r6.growForInsert(r7, r3)
            goto L37
        L16:
            int r4 = r2 / 2
            if (r7 >= r4) goto L1c
            if (r1 > 0) goto L21
        L1c:
            int[] r4 = r6.array
            int r5 = r4.length
            if (r0 != r5) goto L2b
        L21:
            int[] r0 = r6.array
            int r2 = r1 + (-1)
            r6.firstIndex = r2
            java.lang.System.arraycopy(r0, r1, r0, r2, r7)
            goto L37
        L2b:
            int r1 = r1 + r7
            int r0 = r1 + 1
            int r2 = r2 - r7
            java.lang.System.arraycopy(r4, r1, r4, r0, r2)
            int r0 = r6.lastIndex
            int r0 = r0 + r3
            r6.lastIndex = r0
        L37:
            int[] r0 = r6.array
            int r1 = r6.firstIndex
            int r7 = r7 + r1
            r0[r7] = r8
            goto L64
        L3f:
            if (r7 != 0) goto L50
            if (r1 != 0) goto L46
            r6.growAtFront(r3)
        L46:
            int[] r7 = r6.array
            int r0 = r6.firstIndex
            int r0 = r0 - r3
            r6.firstIndex = r0
            r7[r0] = r8
            goto L64
        L50:
            if (r7 != r2) goto L6a
            int[] r7 = r6.array
            int r7 = r7.length
            if (r0 != r7) goto L5a
            r6.growAtEnd(r3)
        L5a:
            int[] r7 = r6.array
            int r0 = r6.lastIndex
            int r1 = r0 + 1
            r6.lastIndex = r1
            r7[r0] = r8
        L64:
            int r7 = r6.modCount
            int r7 = r7 + r3
            r6.modCount = r7
            return
        L6a:
            java.lang.IndexOutOfBoundsException r7 = new java.lang.IndexOutOfBoundsException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.IntList.add(int, int):void");
    }

    public void clear() {
        int i = this.firstIndex;
        int i2 = this.lastIndex;
        if (i != i2) {
            Arrays.fill(this.array, i, i2, -1);
            this.lastIndex = 0;
            this.firstIndex = 0;
            this.modCount++;
        }
    }

    public int get(int i) {
        if (i >= 0) {
            int i2 = this.lastIndex;
            int i3 = this.firstIndex;
            if (i < i2 - i3) {
                return this.array[i3 + i];
            }
        }
        throw new IndexOutOfBoundsException("" + i);
    }

    private void growAtEnd(int i) {
        int i2 = this.lastIndex;
        int i3 = this.firstIndex;
        int i4 = i2 - i3;
        int[] iArr = this.array;
        if (i3 >= i - (iArr.length - i2)) {
            int i5 = i2 - i3;
            if (i4 > 0) {
                System.arraycopy(iArr, i3, iArr, 0, i4);
            }
            this.firstIndex = 0;
            this.lastIndex = i5;
            return;
        }
        int i6 = i4 / 2;
        if (i <= i6) {
            i = i6;
        }
        if (i < 12) {
            i = 12;
        }
        int[] iArr2 = new int[i + i4];
        if (i4 > 0) {
            System.arraycopy(iArr, i3, iArr2, 0, i4);
            this.firstIndex = 0;
            this.lastIndex = i4;
        }
        this.array = iArr2;
    }

    private void growAtFront(int i) {
        int i2 = this.lastIndex;
        int i3 = this.firstIndex;
        int i4 = i2 - i3;
        int[] iArr = this.array;
        if ((iArr.length - i2) + i3 >= i) {
            int length = iArr.length - i4;
            if (i4 > 0) {
                System.arraycopy(iArr, i3, iArr, length, i4);
            }
            this.firstIndex = length;
            this.lastIndex = this.array.length;
            return;
        }
        int i5 = i4 / 2;
        if (i <= i5) {
            i = i5;
        }
        if (i < 12) {
            i = 12;
        }
        int i6 = i + i4;
        int[] iArr2 = new int[i6];
        if (i4 > 0) {
            System.arraycopy(iArr, i3, iArr2, i6 - i4, i4);
        }
        this.firstIndex = i6 - i4;
        this.lastIndex = i6;
        this.array = iArr2;
    }

    private void growForInsert(int i, int i2) {
        int i3 = this.lastIndex;
        int i4 = this.firstIndex;
        int i5 = i3 - i4;
        int i6 = i5 / 2;
        if (i2 > i6) {
            i6 = i2;
        }
        if (i6 < 12) {
            i6 = 12;
        }
        int i7 = i5 + i6;
        int[] iArr = new int[i7];
        int i8 = i6 - i2;
        System.arraycopy(this.array, i4 + i, iArr, i8 + i + i2, i5 - i);
        System.arraycopy(this.array, this.firstIndex, iArr, i8, i);
        this.firstIndex = i8;
        this.lastIndex = i7;
        this.array = iArr;
    }

    public void increment(int i) {
        if (i >= 0) {
            int i2 = this.lastIndex;
            int i3 = this.firstIndex;
            if (i < i2 - i3) {
                int[] iArr = this.array;
                int i4 = i3 + i;
                iArr[i4] = iArr[i4] + 1;
                return;
            }
        }
        throw new IndexOutOfBoundsException("" + i);
    }

    public boolean isEmpty() {
        return this.lastIndex == this.firstIndex;
    }

    public int remove(int i) {
        int i2;
        int i3 = this.lastIndex;
        int i4 = this.firstIndex;
        int i5 = i3 - i4;
        if (i < 0 || i >= i5) {
            throw new IndexOutOfBoundsException();
        }
        if (i == i5 - 1) {
            int[] iArr = this.array;
            int i6 = i3 - 1;
            this.lastIndex = i6;
            i2 = iArr[i6];
            iArr[i6] = 0;
        } else if (i == 0) {
            int[] iArr2 = this.array;
            int i7 = iArr2[i4];
            this.firstIndex = i4 + 1;
            iArr2[i4] = 0;
            i2 = i7;
        } else {
            int i8 = i4 + i;
            int[] iArr3 = this.array;
            int i9 = iArr3[i8];
            if (i < i5 / 2) {
                System.arraycopy(iArr3, i4, iArr3, i4 + 1, i);
                int[] iArr4 = this.array;
                int i10 = this.firstIndex;
                this.firstIndex = i10 + 1;
                iArr4[i10] = 0;
            } else {
                System.arraycopy(iArr3, i8 + 1, iArr3, i8, (i5 - i) - 1);
                int[] iArr5 = this.array;
                int i11 = this.lastIndex - 1;
                this.lastIndex = i11;
                iArr5[i11] = 0;
            }
            i2 = i9;
        }
        if (this.firstIndex == this.lastIndex) {
            this.lastIndex = 0;
            this.firstIndex = 0;
        }
        this.modCount++;
        return i2;
    }

    public int size() {
        return this.lastIndex - this.firstIndex;
    }

    public int[] toArray() {
        int i = this.lastIndex;
        int i2 = this.firstIndex;
        int i3 = i - i2;
        int[] iArr = new int[i3];
        System.arraycopy(this.array, i2, iArr, 0, i3);
        return iArr;
    }

    public void addAll(IntList intList) {
        growAtEnd(intList.size());
        for (int i = 0; i < intList.size(); i++) {
            add(intList.get(i));
        }
    }
}
