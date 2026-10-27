package com.android.car.ui.recyclerview;

/* JADX INFO: loaded from: classes2.dex */
public interface RangeFilter {
    public static final int INVALID_INDEX = -1;
    public static final int INVALID_POSITION = -1;

    void applyFilter();

    int getFilteredCount();

    int indexToPosition(int i);

    void invalidateMessagePositions();

    void notifyPivotIndexChanged(int i);

    int positionToIndex(int i);

    void recompute(int i, int i2);

    void removeFilter();
}
