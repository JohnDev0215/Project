package com.shopify.reactnative.flash_list;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoLayoutShadow.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0019\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&¢\u0006\u0002\u0010(J\u001e\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0004J\u0010\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020'H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0006\"\u0004\b\u0015\u0010\bR\u000e\u0010\u0016\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0006\"\u0004\b\u0019\u0010\bR\u001a\u0010\u001a\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0006\"\u0004\b\u001c\u0010\bR\u001a\u0010\u001d\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0006\"\u0004\b\u001f\u0010\bR\u001a\u0010 \u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0006\"\u0004\b\"\u0010\b¨\u0006/"}, d2 = {"Lcom/shopify/reactnative/flash_list/AutoLayoutShadow;", "", "()V", "blankOffsetAtEnd", "", "getBlankOffsetAtEnd", "()I", "setBlankOffsetAtEnd", "(I)V", "blankOffsetAtStart", "getBlankOffsetAtStart", "setBlankOffsetAtStart", "horizontal", "", "getHorizontal", "()Z", "setHorizontal", "(Z)V", "lastMaxBound", "lastMaxBoundOverall", "getLastMaxBoundOverall", "setLastMaxBoundOverall", "lastMinBound", "offsetFromStart", "getOffsetFromStart", "setOffsetFromStart", "renderOffset", "getRenderOffset", "setRenderOffset", "scrollOffset", "getScrollOffset", "setScrollOffset", "windowSize", "getWindowSize", "setWindowSize", "clearGapsAndOverlaps", "", "sortedItems", "", "Lcom/shopify/reactnative/flash_list/CellContainer;", "([Lcom/shopify/reactnative/flash_list/CellContainer;)V", "computeBlankFromGivenOffset", "actualScrollOffset", "distanceFromWindowStart", "distanceFromWindowEnd", "isWithinBounds", "cell", "shopify_flash-list_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AutoLayoutShadow {
    private int blankOffsetAtEnd;
    private int blankOffsetAtStart;
    private boolean horizontal;
    private int lastMaxBound;
    private int lastMaxBoundOverall;
    private int lastMinBound;
    private int offsetFromStart;
    private int renderOffset;
    private int scrollOffset;
    private int windowSize;

    public final boolean getHorizontal() {
        return this.horizontal;
    }

    public final void setHorizontal(boolean z) {
        this.horizontal = z;
    }

    public final int getScrollOffset() {
        return this.scrollOffset;
    }

    public final void setScrollOffset(int i) {
        this.scrollOffset = i;
    }

    public final int getOffsetFromStart() {
        return this.offsetFromStart;
    }

    public final void setOffsetFromStart(int i) {
        this.offsetFromStart = i;
    }

    public final int getWindowSize() {
        return this.windowSize;
    }

    public final void setWindowSize(int i) {
        this.windowSize = i;
    }

    public final int getRenderOffset() {
        return this.renderOffset;
    }

    public final void setRenderOffset(int i) {
        this.renderOffset = i;
    }

    public final int getBlankOffsetAtStart() {
        return this.blankOffsetAtStart;
    }

    public final void setBlankOffsetAtStart(int i) {
        this.blankOffsetAtStart = i;
    }

    public final int getBlankOffsetAtEnd() {
        return this.blankOffsetAtEnd;
    }

    public final void setBlankOffsetAtEnd(int i) {
        this.blankOffsetAtEnd = i;
    }

    public final int getLastMaxBoundOverall() {
        return this.lastMaxBoundOverall;
    }

    public final void setLastMaxBoundOverall(int i) {
        this.lastMaxBoundOverall = i;
    }

    public final void clearGapsAndOverlaps(CellContainer[] sortedItems) {
        int iMin;
        int iMax;
        Intrinsics.checkNotNullParameter(sortedItems, "sortedItems");
        int i = 0;
        this.lastMaxBoundOverall = 0;
        int length = sortedItems.length - 1;
        int i2 = 0;
        int i3 = Integer.MAX_VALUE;
        int iMax2 = 0;
        while (i < length) {
            int i4 = i + 1;
            CellContainer cellContainer = sortedItems[i];
            CellContainer cellContainer2 = sortedItems[i4];
            if (isWithinBounds(cellContainer)) {
                if (!this.horizontal) {
                    iMax2 = Math.max(i2, cellContainer.getBottom());
                    iMin = Math.min(i3, cellContainer.getTop());
                    if (cellContainer.getLeft() < cellContainer2.getLeft()) {
                        if (cellContainer.getRight() != cellContainer2.getLeft()) {
                            cellContainer2.setRight(cellContainer.getRight() + cellContainer2.getWidth());
                            cellContainer2.setLeft(cellContainer.getRight());
                        }
                        if (cellContainer.getTop() != cellContainer2.getTop()) {
                            cellContainer2.setBottom(cellContainer.getTop() + cellContainer2.getHeight());
                            cellContainer2.setTop(cellContainer.getTop());
                        }
                    } else {
                        cellContainer2.setBottom(cellContainer2.getHeight() + iMax2);
                        cellContainer2.setTop(iMax2);
                    }
                    if (isWithinBounds(cellContainer2)) {
                        iMax = Math.max(iMax2, cellContainer2.getBottom());
                        int i5 = iMin;
                        i2 = iMax2;
                        iMax2 = iMax;
                        i3 = i5;
                    }
                    i3 = iMin;
                    i2 = iMax2;
                } else {
                    iMax2 = Math.max(i2, cellContainer.getRight());
                    iMin = Math.min(i3, cellContainer.getLeft());
                    if (cellContainer.getTop() < cellContainer2.getTop()) {
                        if (cellContainer.getBottom() != cellContainer2.getTop()) {
                            cellContainer2.setBottom(cellContainer.getBottom() + cellContainer2.getHeight());
                            cellContainer2.setTop(cellContainer.getBottom());
                        }
                        if (cellContainer.getLeft() != cellContainer2.getLeft()) {
                            cellContainer2.setRight(cellContainer.getLeft() + cellContainer2.getWidth());
                            cellContainer2.setLeft(cellContainer.getLeft());
                        }
                    } else {
                        cellContainer2.setRight(cellContainer2.getWidth() + iMax2);
                        cellContainer2.setLeft(iMax2);
                    }
                    if (isWithinBounds(cellContainer2)) {
                        iMax = Math.max(iMax2, cellContainer2.getRight());
                        int i52 = iMin;
                        i2 = iMax2;
                        iMax2 = iMax;
                        i3 = i52;
                    }
                    i3 = iMin;
                    i2 = iMax2;
                }
            }
            int iMax3 = Math.max(this.lastMaxBoundOverall, this.horizontal ? cellContainer.getRight() : cellContainer.getBottom());
            this.lastMaxBoundOverall = iMax3;
            this.lastMaxBoundOverall = Math.max(iMax3, this.horizontal ? cellContainer2.getRight() : cellContainer2.getBottom());
            i = i4;
        }
        this.lastMaxBound = iMax2;
        this.lastMinBound = i3;
    }

    public final int computeBlankFromGivenOffset(int actualScrollOffset, int distanceFromWindowStart, int distanceFromWindowEnd) {
        int i = actualScrollOffset - this.offsetFromStart;
        int i2 = (this.lastMinBound - i) - distanceFromWindowStart;
        this.blankOffsetAtStart = i2;
        int i3 = (((i + this.windowSize) - this.renderOffset) - this.lastMaxBound) - distanceFromWindowEnd;
        this.blankOffsetAtEnd = i3;
        return Math.max(i2, i3);
    }

    private final boolean isWithinBounds(CellContainer cell) {
        int i = this.scrollOffset - this.offsetFromStart;
        if (!this.horizontal) {
            if (cell.getTop() < i - this.renderOffset && cell.getBottom() < i - this.renderOffset) {
                return false;
            }
            if (cell.getTop() > this.windowSize + i && cell.getBottom() > i + this.windowSize) {
                return false;
            }
        } else {
            if (cell.getLeft() < i - this.renderOffset && cell.getRight() < i - this.renderOffset) {
                return false;
            }
            if (cell.getLeft() > this.windowSize + i && cell.getRight() > i + this.windowSize) {
                return false;
            }
        }
        return true;
    }
}
