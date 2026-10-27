package com.android.car.ui.recyclerview;

/* JADX INFO: loaded from: classes6.dex */
public class PassThroughFilter implements RangeFilter {
    private int mCount;

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public void recompute(int newCount, int pivotIndex) {
        this.mCount = newCount;
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public void notifyPivotIndexChanged(int pivotIndex) {
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public int getFilteredCount() {
        return this.mCount;
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public int indexToPosition(int index) {
        return index;
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public int positionToIndex(int position) {
        return position;
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public void invalidateMessagePositions() {
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public void applyFilter() {
    }

    @Override // com.android.car.ui.recyclerview.RangeFilter
    public void removeFilter() {
    }
}
