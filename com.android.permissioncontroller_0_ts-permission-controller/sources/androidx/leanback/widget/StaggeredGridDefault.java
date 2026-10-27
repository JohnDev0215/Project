package androidx.leanback.widget;

import androidx.leanback.widget.StaggeredGrid;

/* JADX INFO: loaded from: classes3.dex */
final class StaggeredGridDefault extends StaggeredGrid {
    StaggeredGridDefault() {
    }

    int getRowMax(int rowIndex) {
        StaggeredGrid.Location loc;
        if (this.mFirstVisibleIndex < 0) {
            return Integer.MIN_VALUE;
        }
        if (this.mReversedFlow) {
            int edge = this.mProvider.getEdge(this.mFirstVisibleIndex);
            if (getLocation(this.mFirstVisibleIndex).row == rowIndex) {
                return edge;
            }
            int i = this.mFirstVisibleIndex;
            do {
                i++;
                if (i <= getLastIndex()) {
                    loc = getLocation(i);
                    edge += loc.offset;
                }
            } while (loc.row != rowIndex);
            return edge;
        }
        int edge2 = this.mProvider.getEdge(this.mLastVisibleIndex);
        StaggeredGrid.Location loc2 = getLocation(this.mLastVisibleIndex);
        if (loc2.row == rowIndex) {
            return loc2.size + edge2;
        }
        int i2 = this.mLastVisibleIndex;
        do {
            i2--;
            if (i2 >= getFirstIndex()) {
                edge2 -= loc2.offset;
                loc2 = getLocation(i2);
            }
        } while (loc2.row != rowIndex);
        return loc2.size + edge2;
        return Integer.MIN_VALUE;
    }

    int getRowMin(int rowIndex) {
        StaggeredGrid.Location loc;
        if (this.mFirstVisibleIndex < 0) {
            return Integer.MAX_VALUE;
        }
        if (this.mReversedFlow) {
            int edge = this.mProvider.getEdge(this.mLastVisibleIndex);
            StaggeredGrid.Location loc2 = getLocation(this.mLastVisibleIndex);
            if (loc2.row == rowIndex) {
                return edge - loc2.size;
            }
            int i = this.mLastVisibleIndex;
            do {
                i--;
                if (i >= getFirstIndex()) {
                    edge -= loc2.offset;
                    loc2 = getLocation(i);
                }
            } while (loc2.row != rowIndex);
            return edge - loc2.size;
        }
        int edge2 = this.mProvider.getEdge(this.mFirstVisibleIndex);
        if (getLocation(this.mFirstVisibleIndex).row == rowIndex) {
            return edge2;
        }
        int i2 = this.mFirstVisibleIndex;
        do {
            i2++;
            if (i2 <= getLastIndex()) {
                loc = getLocation(i2);
                edge2 += loc.offset;
            }
        } while (loc.row != rowIndex);
        return edge2;
        return Integer.MAX_VALUE;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0068  */
    @Override // androidx.leanback.widget.Grid
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int findRowMax(boolean r10, int r11, int[] r12) {
        /*
            r9 = this;
            androidx.leanback.widget.Grid$Provider r0 = r9.mProvider
            int r0 = r0.getEdge(r11)
            androidx.leanback.widget.StaggeredGrid$Location r1 = r9.getLocation(r11)
            int r2 = r1.row
            r3 = r11
            r4 = 1
            r5 = r2
            boolean r6 = r9.mReversedFlow
            if (r6 == 0) goto L3a
            r6 = r0
            int r7 = r11 + 1
        L16:
            int r8 = r9.mNumRows
            if (r4 >= r8) goto L6e
            int r8 = r9.mLastVisibleIndex
            if (r7 > r8) goto L6e
            androidx.leanback.widget.StaggeredGrid$Location r1 = r9.getLocation(r7)
            int r8 = r1.offset
            int r0 = r0 + r8
            int r8 = r1.row
            if (r8 == r5) goto L37
            int r5 = r1.row
            int r4 = r4 + 1
            if (r10 == 0) goto L32
            if (r0 <= r6) goto L37
            goto L34
        L32:
            if (r0 >= r6) goto L37
        L34:
            r2 = r5
            r6 = r0
            r3 = r7
        L37:
            int r7 = r7 + 1
            goto L16
        L3a:
            androidx.leanback.widget.Grid$Provider r6 = r9.mProvider
            int r6 = r6.getSize(r11)
            int r6 = r6 + r0
            int r7 = r11 + (-1)
        L43:
            int r8 = r9.mNumRows
            if (r4 >= r8) goto L6e
            int r8 = r9.mFirstVisibleIndex
            if (r7 < r8) goto L6e
            int r8 = r1.offset
            int r0 = r0 - r8
            androidx.leanback.widget.StaggeredGrid$Location r1 = r9.getLocation(r7)
            int r8 = r1.row
            if (r8 == r5) goto L6b
            int r5 = r1.row
            int r4 = r4 + 1
            androidx.leanback.widget.Grid$Provider r8 = r9.mProvider
            int r8 = r8.getSize(r7)
            int r8 = r8 + r0
            if (r10 == 0) goto L66
            if (r8 <= r6) goto L6b
            goto L68
        L66:
            if (r8 >= r6) goto L6b
        L68:
            r2 = r5
            r6 = r8
            r3 = r7
        L6b:
            int r7 = r7 + (-1)
            goto L43
        L6e:
            if (r12 == 0) goto L76
            r7 = 0
            r12[r7] = r2
            r7 = 1
            r12[r7] = r3
        L76:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.StaggeredGridDefault.findRowMax(boolean, int, int[]):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006a  */
    @Override // androidx.leanback.widget.Grid
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int findRowMin(boolean r10, int r11, int[] r12) {
        /*
            r9 = this;
            androidx.leanback.widget.Grid$Provider r0 = r9.mProvider
            int r0 = r0.getEdge(r11)
            androidx.leanback.widget.StaggeredGrid$Location r1 = r9.getLocation(r11)
            int r2 = r1.row
            r3 = r11
            r4 = 1
            r5 = r2
            boolean r6 = r9.mReversedFlow
            if (r6 == 0) goto L49
            androidx.leanback.widget.Grid$Provider r6 = r9.mProvider
            int r6 = r6.getSize(r11)
            int r6 = r0 - r6
            int r7 = r11 + (-1)
        L1d:
            int r8 = r9.mNumRows
            if (r4 >= r8) goto L70
            int r8 = r9.mFirstVisibleIndex
            if (r7 < r8) goto L70
            int r8 = r1.offset
            int r0 = r0 - r8
            androidx.leanback.widget.StaggeredGrid$Location r1 = r9.getLocation(r7)
            int r8 = r1.row
            if (r8 == r5) goto L46
            int r5 = r1.row
            int r4 = r4 + 1
            androidx.leanback.widget.Grid$Provider r8 = r9.mProvider
            int r8 = r8.getSize(r7)
            int r8 = r0 - r8
            if (r10 == 0) goto L41
            if (r8 <= r6) goto L46
            goto L43
        L41:
            if (r8 >= r6) goto L46
        L43:
            r6 = r8
            r2 = r5
            r3 = r7
        L46:
            int r7 = r7 + (-1)
            goto L1d
        L49:
            r6 = r0
            int r7 = r11 + 1
        L4c:
            int r8 = r9.mNumRows
            if (r4 >= r8) goto L70
            int r8 = r9.mLastVisibleIndex
            if (r7 > r8) goto L70
            androidx.leanback.widget.StaggeredGrid$Location r1 = r9.getLocation(r7)
            int r8 = r1.offset
            int r0 = r0 + r8
            int r8 = r1.row
            if (r8 == r5) goto L6d
            int r5 = r1.row
            int r4 = r4 + 1
            if (r10 == 0) goto L68
            if (r0 <= r6) goto L6d
            goto L6a
        L68:
            if (r0 >= r6) goto L6d
        L6a:
            r6 = r0
            r2 = r5
            r3 = r7
        L6d:
            int r7 = r7 + 1
            goto L4c
        L70:
            if (r12 == 0) goto L78
            r7 = 0
            r12[r7] = r2
            r7 = 1
            r12[r7] = r3
        L78:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.StaggeredGridDefault.findRowMin(boolean, int, int[]):int");
    }

    private int findRowEdgeLimitSearchIndex(boolean append) {
        boolean wrapped = false;
        if (append) {
            for (int index = this.mLastVisibleIndex; index >= this.mFirstVisibleIndex; index--) {
                int row = getLocation(index).row;
                if (row == 0) {
                    wrapped = true;
                } else if (wrapped && row == this.mNumRows - 1) {
                    return index;
                }
            }
            return -1;
        }
        for (int index2 = this.mFirstVisibleIndex; index2 <= this.mLastVisibleIndex; index2++) {
            int row2 = getLocation(index2).row;
            if (row2 == this.mNumRows - 1) {
                wrapped = true;
            } else if (wrapped && row2 == 0) {
                return index2;
            }
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:108:0x013f, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0156, code lost:
    
        return r10;
     */
    @Override // androidx.leanback.widget.StaggeredGrid
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean appendVisibleItemsWithoutCache(int r16, boolean r17) {
        /*
            Method dump skipped, instruction units count: 363
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.StaggeredGridDefault.appendVisibleItemsWithoutCache(int, boolean):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:0x013a, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0151, code lost:
    
        return r8;
     */
    @Override // androidx.leanback.widget.StaggeredGrid
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected boolean prependVisibleItemsWithoutCache(int r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.StaggeredGridDefault.prependVisibleItemsWithoutCache(int, boolean):boolean");
    }
}
