package com.amazon.mp3.card;

/* JADX INFO: loaded from: classes3.dex */
public class GridCardLayout extends CardLayout {
    private final int cardColumns;
    private final float cellSpace;

    public GridCardLayout(final int cardColumns, final float cellSpace) {
        super(CardLayoutType.GRID);
        this.cardColumns = cardColumns;
        this.cellSpace = cellSpace;
    }

    public int getCardColumns() {
        return this.cardColumns;
    }

    public float getCellSpace() {
        return this.cellSpace;
    }
}
