package java9.util;

import java.lang.reflect.Array;
import java.util.Comparator;

/* JADX INFO: loaded from: classes3.dex */
class TimSort<T> {
    private static final int INITIAL_TMP_STORAGE_LENGTH = 256;
    private static final int MIN_GALLOP = 7;
    private static final int MIN_MERGE = 32;
    private final T[] a;
    private final Comparator<? super T> c;
    private final int[] runBase;
    private final int[] runLen;
    private T[] tmp;
    private int tmpBase;
    private int tmpLen;
    private int minGallop = 7;
    private int stackSize = 0;

    private static int minRunLength(int i) {
        int i2 = 0;
        while (i >= 32) {
            i2 |= i & 1;
            i >>= 1;
        }
        return i + i2;
    }

    private TimSort(T[] tArr, Comparator<? super T> comparator, T[] tArr2, int i, int i2) {
        this.a = tArr;
        this.c = comparator;
        int length = tArr.length;
        int i3 = length < 512 ? length >>> 1 : 256;
        if (tArr2 == null || i2 < i3 || i + i3 > tArr2.length) {
            this.tmp = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i3));
            this.tmpBase = 0;
            this.tmpLen = i3;
        } else {
            this.tmp = tArr2;
            this.tmpBase = i;
            this.tmpLen = i2;
        }
        int i4 = length < 120 ? 5 : length < 1542 ? 10 : length < 119151 ? 24 : 49;
        this.runBase = new int[i4];
        this.runLen = new int[i4];
    }

    static <T> void sort(T[] tArr, int i, int i2, Comparator<? super T> comparator, T[] tArr2, int i3, int i4) {
        int i5 = i2 - i;
        if (i5 < 2) {
            return;
        }
        if (i5 < 32) {
            binarySort(tArr, i, i2, countRunAndMakeAscending(tArr, i, i2, comparator) + i, comparator);
            return;
        }
        TimSort timSort = new TimSort(tArr, comparator, tArr2, i3, i4);
        int iMinRunLength = minRunLength(i5);
        do {
            int iCountRunAndMakeAscending = countRunAndMakeAscending(tArr, i, i2, comparator);
            if (iCountRunAndMakeAscending < iMinRunLength) {
                int i6 = i5 <= iMinRunLength ? i5 : iMinRunLength;
                binarySort(tArr, i, i + i6, iCountRunAndMakeAscending + i, comparator);
                iCountRunAndMakeAscending = i6;
            }
            timSort.pushRun(i, iCountRunAndMakeAscending);
            timSort.mergeCollapse();
            i += iCountRunAndMakeAscending;
            i5 -= iCountRunAndMakeAscending;
        } while (i5 != 0);
        timSort.mergeForceCollapse();
    }

    private static <T> void binarySort(T[] tArr, int i, int i2, int i3, Comparator<? super T> comparator) {
        if (i3 == i) {
            i3++;
        }
        while (i3 < i2) {
            T t = tArr[i3];
            int i4 = i;
            int i5 = i3;
            while (i4 < i5) {
                int i6 = (i4 + i5) >>> 1;
                if (comparator.compare(t, tArr[i6]) < 0) {
                    i5 = i6;
                } else {
                    i4 = i6 + 1;
                }
            }
            int i7 = i3 - i4;
            if (i7 == 1) {
                tArr[i4 + 1] = tArr[i4];
            } else if (i7 == 2) {
                tArr[i4 + 2] = tArr[i4 + 1];
                tArr[i4 + 1] = tArr[i4];
            } else {
                System.arraycopy(tArr, i4, tArr, i4 + 1, i7);
            }
            tArr[i4] = t;
            i3++;
        }
    }

    private static <T> int countRunAndMakeAscending(T[] tArr, int i, int i2, Comparator<? super T> comparator) {
        int i3 = i + 1;
        if (i3 == i2) {
            return 1;
        }
        int i4 = i3 + 1;
        if (comparator.compare(tArr[i3], tArr[i]) < 0) {
            while (i4 < i2 && comparator.compare(tArr[i4], tArr[i4 - 1]) < 0) {
                i4++;
            }
            reverseRange(tArr, i, i4);
        } else {
            while (i4 < i2 && comparator.compare(tArr[i4], tArr[i4 - 1]) >= 0) {
                i4++;
            }
        }
        return i4 - i;
    }

    private static void reverseRange(Object[] objArr, int i, int i2) {
        int i3 = i2 - 1;
        while (i < i3) {
            Object obj = objArr[i];
            objArr[i] = objArr[i3];
            objArr[i3] = obj;
            i3--;
            i++;
        }
    }

    private void pushRun(int i, int i2) {
        int[] iArr = this.runBase;
        int i3 = this.stackSize;
        iArr[i3] = i;
        this.runLen[i3] = i2;
        this.stackSize = i3 + 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void mergeCollapse() {
        /*
            r6 = this;
        L0:
            int r0 = r6.stackSize
            r1 = 1
            if (r0 <= r1) goto L49
            int r0 = r0 + (-2)
            if (r0 <= 0) goto L18
            int[] r2 = r6.runLen
            int r3 = r0 + (-1)
            r3 = r2[r3]
            r4 = r2[r0]
            int r5 = r0 + 1
            r2 = r2[r5]
            int r4 = r4 + r2
            if (r3 <= r4) goto L29
        L18:
            if (r0 <= r1) goto L38
            int[] r1 = r6.runLen
            int r2 = r0 + (-2)
            r2 = r1[r2]
            r3 = r1[r0]
            int r4 = r0 + (-1)
            r1 = r1[r4]
            int r3 = r3 + r1
            if (r2 > r3) goto L38
        L29:
            int[] r1 = r6.runLen
            int r2 = r0 + (-1)
            r2 = r1[r2]
            int r3 = r0 + 1
            r1 = r1[r3]
            if (r2 >= r1) goto L45
            int r0 = r0 + (-1)
            goto L45
        L38:
            if (r0 < 0) goto L49
            int[] r1 = r6.runLen
            r2 = r1[r0]
            int r3 = r0 + 1
            r1 = r1[r3]
            if (r2 <= r1) goto L45
            goto L49
        L45:
            r6.mergeAt(r0)
            goto L0
        L49:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.TimSort.mergeCollapse():void");
    }

    private void mergeForceCollapse() {
        while (true) {
            int i = this.stackSize;
            if (i <= 1) {
                return;
            }
            int i2 = i - 2;
            if (i2 > 0) {
                int[] iArr = this.runLen;
                if (iArr[i2 - 1] < iArr[i2 + 1]) {
                    i2--;
                }
            }
            mergeAt(i2);
        }
    }

    private void mergeAt(int i) {
        int[] iArr = this.runBase;
        int i2 = iArr[i];
        int[] iArr2 = this.runLen;
        int i3 = iArr2[i];
        int i4 = i + 1;
        int i5 = iArr[i4];
        int i6 = iArr2[i4];
        iArr2[i] = i3 + i6;
        int i7 = this.stackSize;
        if (i == i7 - 3) {
            int i8 = i + 2;
            iArr[i4] = iArr[i8];
            iArr2[i4] = iArr2[i8];
        }
        this.stackSize = i7 - 1;
        T[] tArr = this.a;
        int iGallopRight = gallopRight(tArr[i5], tArr, i2, i3, 0, this.c);
        int i9 = i2 + iGallopRight;
        int i10 = i3 - iGallopRight;
        if (i10 == 0) {
            return;
        }
        T[] tArr2 = this.a;
        int iGallopLeft = gallopLeft(tArr2[(i9 + i10) - 1], tArr2, i5, i6, i6 - 1, this.c);
        if (iGallopLeft == 0) {
            return;
        }
        if (i10 <= iGallopLeft) {
            mergeLo(i9, i10, i5, iGallopLeft);
        } else {
            mergeHi(i9, i10, i5, iGallopLeft);
        }
    }

    private static <T> int gallopLeft(T t, T[] tArr, int i, int i2, int i3, Comparator<? super T> comparator) {
        int i4;
        int i5;
        int i6 = i + i3;
        if (comparator.compare(t, tArr[i6]) > 0) {
            int i7 = i2 - i3;
            int i8 = 0;
            int i9 = 1;
            while (i9 < i7 && comparator.compare(t, tArr[i6 + i9]) > 0) {
                int i10 = (i9 << 1) + 1;
                if (i10 <= 0) {
                    i8 = i9;
                    i9 = i7;
                } else {
                    int i11 = i9;
                    i9 = i10;
                    i8 = i11;
                }
            }
            if (i9 <= i7) {
                i7 = i9;
            }
            i4 = i8 + i3;
            i5 = i7 + i3;
        } else {
            int i12 = i3 + 1;
            int i13 = 0;
            int i14 = 1;
            while (i14 < i12 && comparator.compare(t, tArr[i6 - i14]) <= 0) {
                int i15 = (i14 << 1) + 1;
                if (i15 <= 0) {
                    i13 = i14;
                    i14 = i12;
                } else {
                    int i16 = i14;
                    i14 = i15;
                    i13 = i16;
                }
            }
            if (i14 <= i12) {
                i12 = i14;
            }
            int i17 = i3 - i12;
            int i18 = i3 - i13;
            i4 = i17;
            i5 = i18;
        }
        int i19 = i4 + 1;
        while (i19 < i5) {
            int i20 = ((i5 - i19) >>> 1) + i19;
            if (comparator.compare(t, tArr[i + i20]) > 0) {
                i19 = i20 + 1;
            } else {
                i5 = i20;
            }
        }
        return i5;
    }

    private static <T> int gallopRight(T t, T[] tArr, int i, int i2, int i3, Comparator<? super T> comparator) {
        int i4;
        int i5;
        int i6 = i + i3;
        if (comparator.compare(t, tArr[i6]) < 0) {
            int i7 = i3 + 1;
            int i8 = 0;
            int i9 = 1;
            while (i9 < i7 && comparator.compare(t, tArr[i6 - i9]) < 0) {
                int i10 = (i9 << 1) + 1;
                if (i10 <= 0) {
                    i8 = i9;
                    i9 = i7;
                } else {
                    int i11 = i9;
                    i9 = i10;
                    i8 = i11;
                }
            }
            if (i9 <= i7) {
                i7 = i9;
            }
            i5 = i3 - i7;
            i4 = i3 - i8;
        } else {
            int i12 = i2 - i3;
            int i13 = 0;
            int i14 = 1;
            while (i14 < i12 && comparator.compare(t, tArr[i6 + i14]) >= 0) {
                int i15 = (i14 << 1) + 1;
                if (i15 <= 0) {
                    i13 = i14;
                    i14 = i12;
                } else {
                    int i16 = i14;
                    i14 = i15;
                    i13 = i16;
                }
            }
            if (i14 <= i12) {
                i12 = i14;
            }
            int i17 = i13 + i3;
            i4 = i3 + i12;
            i5 = i17;
        }
        int i18 = i5 + 1;
        while (i18 < i4) {
            int i19 = ((i4 - i18) >>> 1) + i18;
            if (comparator.compare(t, tArr[i + i19]) < 0) {
                i4 = i19;
            } else {
                i18 = i19 + 1;
            }
        }
        return i4;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0069, code lost:
    
        r12 = r1;
        r13 = r2;
        r14 = r3;
        r15 = r4;
        r16 = r5;
        r17 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0071, code lost:
    
        r6 = gallopRight(r7[r16], r8, r14, r13, 0, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x007c, code lost:
    
        if (r6 == 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x007e, code lost:
    
        java.lang.System.arraycopy(r8, r14, r7, r15, r6);
        r1 = r15 + r6;
        r3 = r14 + r6;
        r2 = r13 - r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0087, code lost:
    
        if (r2 > r9) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0089, code lost:
    
        r5 = r16;
        r6 = r17;
        r18 = r12;
        r12 = r1;
        r1 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0094, code lost:
    
        r15 = r1;
        r13 = r2;
        r14 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0097, code lost:
    
        r5 = r15 + 1;
        r4 = r16 + 1;
        r7[r15] = r7[r16];
        r12 = r12 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00a1, code lost:
    
        if (r12 != 0) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a3, code lost:
    
        r1 = r12;
        r2 = r13;
        r3 = r14;
        r6 = r17;
        r12 = r5;
        r5 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ab, code lost:
    
        r1 = gallopLeft(r8[r14], r7, r4, r12, 0, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ba, code lost:
    
        if (r1 == 0) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00bc, code lost:
    
        java.lang.System.arraycopy(r7, r4, r7, r5, r1);
        r2 = r5 + r1;
        r5 = r4 + r1;
        r3 = r12 - r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00c5, code lost:
    
        if (r3 != 0) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c7, code lost:
    
        r12 = r2;
        r1 = r3;
        r2 = r13;
        r3 = r14;
        r6 = r17;
        r9 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00cf, code lost:
    
        r12 = r3;
        r16 = r5;
        r5 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00d4, code lost:
    
        r5 = r5;
        r16 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d7, code lost:
    
        r2 = r5 + 1;
        r3 = r14 + 1;
        r7[r5] = r8[r14];
        r13 = r13 - 1;
        r9 = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00e2, code lost:
    
        if (r13 != 1) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00e4, code lost:
    
        r1 = r12;
        r5 = r16;
        r6 = r17;
        r12 = r2;
        r2 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0109, code lost:
    
        r17 = r17 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x010c, code lost:
    
        if (r6 < 7) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x010e, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0110, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0111, code lost:
    
        if (r1 < 7) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0113, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0115, code lost:
    
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0117, code lost:
    
        if ((r1 | r5) != false) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0119, code lost:
    
        if (r17 >= 0) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x011b, code lost:
    
        r11 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x011d, code lost:
    
        r11 = r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0128, code lost:
    
        r15 = r2;
        r14 = r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0069 A[EDGE_INSN: B:76:0x0069->B:24:0x0069 BREAK  A[LOOP:1: B:12:0x0033->B:77:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:? A[LOOP:1: B:12:0x0033->B:77:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void mergeLo(int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 300
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.TimSort.mergeLo(int, int, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0077, code lost:
    
        r12 = r1;
        r13 = r2;
        r14 = r3;
        r15 = r4;
        r16 = r5;
        r17 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x007f, code lost:
    
        r6 = r12 - gallopRight(r8[r15], r7, r21, r12, r12 - 1, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008e, code lost:
    
        if (r6 == 0) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0090, code lost:
    
        r1 = r16 - r6;
        r2 = r17 - r6;
        r3 = r12 - r6;
        java.lang.System.arraycopy(r7, r2 + 1, r7, r1 + 1, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x009d, code lost:
    
        if (r3 != 0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x009f, code lost:
    
        r12 = r1;
        r6 = r2;
        r1 = r3;
        r2 = r13;
        r3 = r14;
        r4 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a7, code lost:
    
        r16 = r1;
        r17 = r2;
        r12 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00ac, code lost:
    
        r18 = r16 - 1;
        r19 = r15 - 1;
        r7[r16] = r8[r15];
        r13 = r13 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b6, code lost:
    
        if (r13 != 1) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b8, code lost:
    
        r1 = r12;
        r2 = r13;
        r3 = r14;
        r6 = r17;
        r12 = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c2, code lost:
    
        r1 = r13 - gallopLeft(r7[r17], r8, r9, r13, r13 - 1, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00d1, code lost:
    
        if (r1 == 0) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00d3, code lost:
    
        r2 = r18 - r1;
        r4 = r19 - r1;
        r3 = r13 - r1;
        java.lang.System.arraycopy(r8, r4 + 1, r7, r2 + 1, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00e0, code lost:
    
        if (r3 > 1) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00e2, code lost:
    
        r1 = r12;
        r6 = r17;
        r12 = r2;
        r2 = r3;
        r3 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00e9, code lost:
    
        r18 = r2;
        r13 = r3;
        r19 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ee, code lost:
    
        r16 = r18 - 1;
        r2 = r17 - 1;
        r7[r18] = r7[r17];
        r12 = r12 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00f8, code lost:
    
        if (r12 != 0) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00fa, code lost:
    
        r6 = r2;
        r1 = r12;
        r2 = r13;
        r3 = r14;
        r12 = r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0126, code lost:
    
        r14 = r14 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0129, code lost:
    
        if (r6 < 7) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x012b, code lost:
    
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x012d, code lost:
    
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x012e, code lost:
    
        if (r1 < 7) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0130, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0132, code lost:
    
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0134, code lost:
    
        if ((r1 | r4) != false) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0136, code lost:
    
        if (r14 >= 0) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0138, code lost:
    
        r14 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0144, code lost:
    
        r17 = r2;
        r15 = r19;
     */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0077 A[EDGE_INSN: B:74:0x0077->B:23:0x0077 BREAK  A[LOOP:1: B:11:0x0042->B:75:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[LOOP:1: B:11:0x0042->B:75:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void mergeHi(int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: java9.util.TimSort.mergeHi(int, int, int, int):void");
    }

    private T[] ensureCapacity(int i) {
        if (this.tmpLen < i) {
            int i2 = (i >> 1) | i;
            int i3 = i2 | (i2 >> 2);
            int i4 = i3 | (i3 >> 4);
            int i5 = i4 | (i4 >> 8);
            int i6 = (i5 | (i5 >> 16)) + 1;
            if (i6 >= 0) {
                i = Math.min(i6, this.a.length >>> 1);
            }
            this.tmp = (T[]) ((Object[]) Array.newInstance(this.a.getClass().getComponentType(), i));
            this.tmpLen = i;
            this.tmpBase = 0;
        }
        return this.tmp;
    }
}
