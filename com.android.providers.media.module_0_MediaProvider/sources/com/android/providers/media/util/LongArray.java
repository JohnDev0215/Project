package com.android.providers.media.util;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class LongArray implements Cloneable {
    private static final int MIN_CAPACITY_INCREMENT = 12;
    private int mSize;
    private long[] mValues;

    private LongArray(long[] jArr, int i) {
        this.mValues = jArr;
        this.mSize = checkArgumentInRange(i, 0, jArr.length, "size");
    }

    public LongArray() {
        this(10);
    }

    public LongArray(int i) {
        if (i == 0) {
            this.mValues = new long[0];
        } else {
            this.mValues = new long[i];
        }
        this.mSize = 0;
    }

    public static LongArray wrap(long[] jArr) {
        return new LongArray(jArr, jArr.length);
    }

    public static LongArray fromArray(long[] jArr, int i) {
        return wrap(Arrays.copyOf(jArr, i));
    }

    public void resize(int i) {
        checkArgumentNonnegative(i);
        long[] jArr = this.mValues;
        if (i <= jArr.length) {
            Arrays.fill(jArr, i, jArr.length, 0L);
        } else {
            ensureCapacity(i - this.mSize);
        }
        this.mSize = i;
    }

    public void add(long j) {
        add(this.mSize, j);
    }

    public void add(int i, long j) {
        ensureCapacity(1);
        int i2 = this.mSize;
        int i3 = i2 - i;
        int i4 = i2 + 1;
        this.mSize = i4;
        checkBounds(i4, i);
        if (i3 != 0) {
            long[] jArr = this.mValues;
            System.arraycopy(jArr, i, jArr, i + 1, i3);
        }
        this.mValues[i] = j;
    }

    public void addAll(LongArray longArray) {
        int i = longArray.mSize;
        ensureCapacity(i);
        System.arraycopy(longArray.mValues, 0, this.mValues, this.mSize, i);
        this.mSize += i;
    }

    private void ensureCapacity(int i) {
        int i2 = this.mSize;
        int i3 = i + i2;
        if (i3 >= this.mValues.length) {
            int i4 = (i2 < 6 ? 12 : i2 >> 1) + i2;
            if (i4 > i3) {
                i3 = i4;
            }
            long[] jArr = new long[i3];
            System.arraycopy(this.mValues, 0, jArr, 0, i2);
            this.mValues = jArr;
        }
    }

    public void clear() {
        this.mSize = 0;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public LongArray m3clone() {
        LongArray longArray;
        LongArray longArray2 = null;
        try {
            longArray = (LongArray) super.clone();
        } catch (CloneNotSupportedException unused) {
        }
        try {
            longArray.mValues = (long[]) this.mValues.clone();
            return longArray;
        } catch (CloneNotSupportedException unused2) {
            longArray2 = longArray;
            return longArray2;
        }
    }

    public long get(int i) {
        checkBounds(this.mSize, i);
        return this.mValues[i];
    }

    public void set(int i, long j) {
        checkBounds(this.mSize, i);
        this.mValues[i] = j;
    }

    public int indexOf(long j) {
        int i = this.mSize;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.mValues[i2] == j) {
                return i2;
            }
        }
        return -1;
    }

    public void remove(int i) {
        checkBounds(this.mSize, i);
        long[] jArr = this.mValues;
        System.arraycopy(jArr, i + 1, jArr, i, (this.mSize - i) - 1);
        this.mSize--;
    }

    public int size() {
        return this.mSize;
    }

    public long[] toArray() {
        return Arrays.copyOf(this.mValues, this.mSize);
    }

    public static boolean elementsEqual(LongArray longArray, LongArray longArray2) {
        if (longArray == null || longArray2 == null) {
            return longArray == longArray2;
        }
        if (longArray.mSize != longArray2.mSize) {
            return false;
        }
        for (int i = 0; i < longArray.mSize; i++) {
            if (longArray.get(i) != longArray2.get(i)) {
                return false;
            }
        }
        return true;
    }

    public static int checkArgumentNonnegative(int i) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException();
    }

    public static int checkArgumentInRange(int i, int i2, int i3, String str) {
        if (i < i2) {
            throw new IllegalArgumentException(String.format("%s is out of range of [%d, %d] (too low)", str, Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        if (i <= i3) {
            return i;
        }
        throw new IllegalArgumentException(String.format("%s is out of range of [%d, %d] (too high)", str, Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    public static void checkBounds(int i, int i2) {
        if (i2 < 0 || i <= i2) {
            throw new ArrayIndexOutOfBoundsException("length=" + i + "; index=" + i2);
        }
    }
}
