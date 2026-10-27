package com.google.common.cache;

import com.google.common.cache.Striped64;

/* JADX INFO: loaded from: classes6.dex */
final class LongAdder extends Striped64 implements LongAddable {
    @Override // com.google.common.cache.Striped64
    final long fn(long j, long j2) {
        return j + j2;
    }

    @Override // com.google.common.cache.LongAddable
    public void add(long j) {
        int length;
        Striped64.Cell cell;
        Striped64.Cell[] cellArr = this.cells;
        if (cellArr == null) {
            long j2 = this.base;
            if (casBase(j2, j2 + j)) {
                return;
            }
        }
        int[] iArr = Striped64.threadHashCode.get();
        boolean zCas = true;
        if (iArr != null && cellArr != null && (length = cellArr.length) >= 1 && (cell = cellArr[(length - 1) & iArr[0]]) != null) {
            long j3 = cell.value;
            zCas = cell.cas(j3, j3 + j);
            if (zCas) {
                return;
            }
        }
        retryUpdate(j, iArr, zCas);
    }

    @Override // com.google.common.cache.LongAddable
    public void increment() {
        add(1L);
    }

    public long sum() {
        long j = this.base;
        Striped64.Cell[] cellArr = this.cells;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j += cell.value;
                }
            }
        }
        return j;
    }

    public String toString() {
        return Long.toString(sum());
    }

    @Override // java.lang.Number
    public long longValue() {
        return sum();
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) sum();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return sum();
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return sum();
    }
}
