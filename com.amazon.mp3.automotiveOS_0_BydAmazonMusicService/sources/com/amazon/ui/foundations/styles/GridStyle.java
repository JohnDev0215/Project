package com.amazon.ui.foundations.styles;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GridStyle.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b \u0010!J\\\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u000e\u001a\u00020\rHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0007\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0016\u001a\u0004\b\u001c\u0010\u0018R\u0017\u0010\b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\b\u0010\u0016\u001a\u0004\b\u001d\u0010\u0018R\u0019\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0019\u001a\u0004\b\u001e\u0010\u001bR\u0019\u0010\n\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010\u0019\u001a\u0004\b\u001f\u0010\u001b¨\u0006\""}, d2 = {"Lcom/amazon/ui/foundations/styles/GridStyle;", "", "Lcom/amazon/ui/foundations/styles/GridSize;", "gridSize", "", "rowSpacing", "columnSpacing", "rowCount", "columnCount", "horizontalScrollRowCount", "peekSize", "copy", "(Lcom/amazon/ui/foundations/styles/GridSize;ILjava/lang/Integer;IILjava/lang/Integer;Ljava/lang/Integer;)Lcom/amazon/ui/foundations/styles/GridStyle;", "", "toString", "hashCode", "other", "", "equals", "Lcom/amazon/ui/foundations/styles/GridSize;", "getGridSize", "()Lcom/amazon/ui/foundations/styles/GridSize;", "I", "getRowSpacing", "()I", "Ljava/lang/Integer;", "getColumnSpacing", "()Ljava/lang/Integer;", "getRowCount", "getColumnCount", "getHorizontalScrollRowCount", "getPeekSize", "<init>", "(Lcom/amazon/ui/foundations/styles/GridSize;ILjava/lang/Integer;IILjava/lang/Integer;Ljava/lang/Integer;)V", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class GridStyle {
    private final int columnCount;
    private final Integer columnSpacing;
    private final GridSize gridSize;
    private final Integer horizontalScrollRowCount;
    private final Integer peekSize;
    private final int rowCount;
    private final int rowSpacing;

    public static /* synthetic */ GridStyle copy$default(GridStyle gridStyle, GridSize gridSize, int i, Integer num, int i2, int i3, Integer num2, Integer num3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            gridSize = gridStyle.gridSize;
        }
        if ((i4 & 2) != 0) {
            i = gridStyle.rowSpacing;
        }
        int i5 = i;
        if ((i4 & 4) != 0) {
            num = gridStyle.columnSpacing;
        }
        Integer num4 = num;
        if ((i4 & 8) != 0) {
            i2 = gridStyle.rowCount;
        }
        int i6 = i2;
        if ((i4 & 16) != 0) {
            i3 = gridStyle.columnCount;
        }
        int i7 = i3;
        if ((i4 & 32) != 0) {
            num2 = gridStyle.horizontalScrollRowCount;
        }
        Integer num5 = num2;
        if ((i4 & 64) != 0) {
            num3 = gridStyle.peekSize;
        }
        return gridStyle.copy(gridSize, i5, num4, i6, i7, num5, num3);
    }

    public final GridStyle copy(GridSize gridSize, int rowSpacing, Integer columnSpacing, int rowCount, int columnCount, Integer horizontalScrollRowCount, Integer peekSize) {
        Intrinsics.checkNotNullParameter(gridSize, "gridSize");
        return new GridStyle(gridSize, rowSpacing, columnSpacing, rowCount, columnCount, horizontalScrollRowCount, peekSize);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GridStyle)) {
            return false;
        }
        GridStyle gridStyle = (GridStyle) other;
        return Intrinsics.areEqual(this.gridSize, gridStyle.gridSize) && this.rowSpacing == gridStyle.rowSpacing && Intrinsics.areEqual(this.columnSpacing, gridStyle.columnSpacing) && this.rowCount == gridStyle.rowCount && this.columnCount == gridStyle.columnCount && Intrinsics.areEqual(this.horizontalScrollRowCount, gridStyle.horizontalScrollRowCount) && Intrinsics.areEqual(this.peekSize, gridStyle.peekSize);
    }

    public int hashCode() {
        int iHashCode = ((this.gridSize.hashCode() * 31) + Integer.hashCode(this.rowSpacing)) * 31;
        Integer num = this.columnSpacing;
        int iHashCode2 = (((((iHashCode + (num == null ? 0 : num.hashCode())) * 31) + Integer.hashCode(this.rowCount)) * 31) + Integer.hashCode(this.columnCount)) * 31;
        Integer num2 = this.horizontalScrollRowCount;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.peekSize;
        return iHashCode3 + (num3 != null ? num3.hashCode() : 0);
    }

    public String toString() {
        return "GridStyle(gridSize=" + this.gridSize + ", rowSpacing=" + this.rowSpacing + ", columnSpacing=" + this.columnSpacing + ", rowCount=" + this.rowCount + ", columnCount=" + this.columnCount + ", horizontalScrollRowCount=" + this.horizontalScrollRowCount + ", peekSize=" + this.peekSize + ')';
    }

    public GridStyle(GridSize gridSize, int i, Integer num, int i2, int i3, Integer num2, Integer num3) {
        Intrinsics.checkNotNullParameter(gridSize, "gridSize");
        this.gridSize = gridSize;
        this.rowSpacing = i;
        this.columnSpacing = num;
        this.rowCount = i2;
        this.columnCount = i3;
        this.horizontalScrollRowCount = num2;
        this.peekSize = num3;
    }

    public final GridSize getGridSize() {
        return this.gridSize;
    }

    public final int getRowSpacing() {
        return this.rowSpacing;
    }

    public /* synthetic */ GridStyle(GridSize gridSize, int i, Integer num, int i2, int i3, Integer num2, Integer num3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(gridSize, i, (i4 & 4) != 0 ? Integer.valueOf(gridSize.getColumnSpace()) : num, i2, i3, (i4 & 32) != 0 ? 1 : num2, (i4 & 64) != 0 ? 0 : num3);
    }

    public final Integer getColumnSpacing() {
        return this.columnSpacing;
    }

    public final int getRowCount() {
        return this.rowCount;
    }

    public final int getColumnCount() {
        return this.columnCount;
    }

    public final Integer getHorizontalScrollRowCount() {
        return this.horizontalScrollRowCount;
    }

    public final Integer getPeekSize() {
        return this.peekSize;
    }
}
