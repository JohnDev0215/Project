package com.android.inputmethod.latin.common;

import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class InputPointers {
    private static final boolean DEBUG_TIME = false;
    private final int mDefaultCapacity;
    private final ResizableIntArray mPointerIds;
    private final ResizableIntArray mTimes;
    private final ResizableIntArray mXCoordinates;
    private final ResizableIntArray mYCoordinates;

    public InputPointers(final int defaultCapacity) {
        this.mDefaultCapacity = defaultCapacity;
        this.mXCoordinates = new ResizableIntArray(defaultCapacity);
        this.mYCoordinates = new ResizableIntArray(defaultCapacity);
        this.mPointerIds = new ResizableIntArray(defaultCapacity);
        this.mTimes = new ResizableIntArray(defaultCapacity);
    }

    private void fillWithLastTimeUntil(final int index) {
        int i;
        int length = this.mTimes.getLength();
        if (length > 0 && (i = (index - length) + 1) > 0) {
            this.mTimes.fill(this.mTimes.get(length - 1), length, i);
        }
    }

    public void addPointerAt(final int index, final int x, final int y, final int pointerId, final int time) {
        this.mXCoordinates.addAt(index, x);
        this.mYCoordinates.addAt(index, y);
        this.mPointerIds.addAt(index, pointerId);
        this.mTimes.addAt(index, time);
    }

    public void addPointer(final int x, final int y, final int pointerId, final int time) {
        this.mXCoordinates.add(x);
        this.mYCoordinates.add(y);
        this.mPointerIds.add(pointerId);
        this.mTimes.add(time);
    }

    public void set(@Nonnull final InputPointers ip) {
        this.mXCoordinates.set(ip.mXCoordinates);
        this.mYCoordinates.set(ip.mYCoordinates);
        this.mPointerIds.set(ip.mPointerIds);
        this.mTimes.set(ip.mTimes);
    }

    public void copy(@Nonnull final InputPointers ip) {
        this.mXCoordinates.copy(ip.mXCoordinates);
        this.mYCoordinates.copy(ip.mYCoordinates);
        this.mPointerIds.copy(ip.mPointerIds);
        this.mTimes.copy(ip.mTimes);
    }

    public void append(final int pointerId, @Nonnull final ResizableIntArray times, @Nonnull final ResizableIntArray xCoordinates, @Nonnull final ResizableIntArray yCoordinates, final int startPos, final int length) {
        if (length == 0) {
            return;
        }
        this.mXCoordinates.append(xCoordinates, startPos, length);
        this.mYCoordinates.append(yCoordinates, startPos, length);
        ResizableIntArray resizableIntArray = this.mPointerIds;
        resizableIntArray.fill(pointerId, resizableIntArray.getLength(), length);
        this.mTimes.append(times, startPos, length);
    }

    public void shift(final int elementCount) {
        this.mXCoordinates.shift(elementCount);
        this.mYCoordinates.shift(elementCount);
        this.mPointerIds.shift(elementCount);
        this.mTimes.shift(elementCount);
    }

    public void reset() {
        int i = this.mDefaultCapacity;
        this.mXCoordinates.reset(i);
        this.mYCoordinates.reset(i);
        this.mPointerIds.reset(i);
        this.mTimes.reset(i);
    }

    public int getPointerSize() {
        return this.mXCoordinates.getLength();
    }

    @Nonnull
    public int[] getXCoordinates() {
        return this.mXCoordinates.getPrimitiveArray();
    }

    @Nonnull
    public int[] getYCoordinates() {
        return this.mYCoordinates.getPrimitiveArray();
    }

    @Nonnull
    public int[] getPointerIds() {
        return this.mPointerIds.getPrimitiveArray();
    }

    @Nonnull
    public int[] getTimes() {
        return this.mTimes.getPrimitiveArray();
    }

    public String toString() {
        return "size=" + getPointerSize() + " id=" + this.mPointerIds + " time=" + this.mTimes + " x=" + this.mXCoordinates + " y=" + this.mYCoordinates;
    }
}
