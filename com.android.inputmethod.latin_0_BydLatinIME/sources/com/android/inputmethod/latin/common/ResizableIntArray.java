package com.android.inputmethod.latin.common;

import java.util.Arrays;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class ResizableIntArray {

    @Nonnull
    private int[] mArray;
    private int mLength;

    public ResizableIntArray(final int capacity) {
        reset(capacity);
    }

    public int get(final int index) {
        if (index < this.mLength) {
            return this.mArray[index];
        }
        throw new ArrayIndexOutOfBoundsException("length=" + this.mLength + "; index=" + index);
    }

    public void addAt(final int index, final int val) {
        if (index < this.mLength) {
            this.mArray[index] = val;
        } else {
            this.mLength = index;
            add(val);
        }
    }

    public void add(final int val) {
        int i = this.mLength;
        int i2 = i + 1;
        ensureCapacity(i2);
        this.mArray[i] = val;
        this.mLength = i2;
    }

    private int calculateCapacity(final int minimumCapacity) {
        int length = this.mArray.length;
        if (length >= minimumCapacity) {
            return 0;
        }
        int i = length * 2;
        return minimumCapacity > i ? minimumCapacity : i;
    }

    private void ensureCapacity(final int minimumCapacity) {
        int iCalculateCapacity = calculateCapacity(minimumCapacity);
        if (iCalculateCapacity > 0) {
            this.mArray = Arrays.copyOf(this.mArray, iCalculateCapacity);
        }
    }

    public int getLength() {
        return this.mLength;
    }

    public void setLength(final int newLength) {
        ensureCapacity(newLength);
        this.mLength = newLength;
    }

    public void reset(final int capacity) {
        this.mArray = new int[capacity];
        this.mLength = 0;
    }

    @Nonnull
    public int[] getPrimitiveArray() {
        return this.mArray;
    }

    public void set(@Nonnull final ResizableIntArray ip) {
        this.mArray = ip.mArray;
        this.mLength = ip.mLength;
    }

    public void copy(@Nonnull final ResizableIntArray ip) {
        int iCalculateCapacity = calculateCapacity(ip.mLength);
        if (iCalculateCapacity > 0) {
            this.mArray = new int[iCalculateCapacity];
        }
        System.arraycopy(ip.mArray, 0, this.mArray, 0, ip.mLength);
        this.mLength = ip.mLength;
    }

    public void append(@Nonnull final ResizableIntArray src, final int startPos, final int length) {
        if (length == 0) {
            return;
        }
        int i = this.mLength;
        int i2 = i + length;
        ensureCapacity(i2);
        System.arraycopy(src.mArray, startPos, this.mArray, i, length);
        this.mLength = i2;
    }

    public void fill(final int value, final int startPos, final int length) {
        if (startPos < 0 || length < 0) {
            throw new IllegalArgumentException("startPos=" + startPos + "; length=" + length);
        }
        int i = length + startPos;
        ensureCapacity(i);
        Arrays.fill(this.mArray, startPos, i, value);
        if (this.mLength < i) {
            this.mLength = i;
        }
    }

    public void shift(final int elementCount) {
        int[] iArr = this.mArray;
        System.arraycopy(iArr, elementCount, iArr, 0, this.mLength - elementCount);
        this.mLength -= elementCount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.mLength; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(this.mArray[i]);
        }
        return "[" + ((Object) sb) + "]";
    }
}
