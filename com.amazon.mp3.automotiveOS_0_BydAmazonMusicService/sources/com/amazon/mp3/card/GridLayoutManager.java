package com.amazon.mp3.card;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes3.dex */
public class GridLayoutManager extends AbstractCardLayoutManager {
    private final int mCellSpace;
    private final int mColumnCount;
    private ViewGroup mRow;
    private int mRowColumnCount;

    public GridLayoutManager(ViewGroup parent, int contentIdx, int columnCount, float cellSpace) {
        super(parent, contentIdx);
        this.mRowColumnCount = 0;
        this.mColumnCount = columnCount;
        this.mCellSpace = (int) cellSpace;
    }

    @Override // com.amazon.mp3.card.AbstractCardLayoutManager
    protected ViewGroup createContentContainer() {
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setOrientation(1);
        return linearLayout;
    }

    private ViewGroup createRow() {
        LinearLayout linearLayout = new LinearLayout(this.mContext);
        linearLayout.setOrientation(0);
        for (int i = 0; i < this.mColumnCount; i++) {
            linearLayout.addView(new View(this.mContext), getLayoutParamsForACell());
            if (i != this.mColumnCount - 1 && this.mCellSpace > 0) {
                linearLayout.addView(createColumnPadding());
            }
        }
        return linearLayout;
    }

    private View createColumnPadding() {
        View view = new View(this.mContext);
        view.setLayoutParams(new LinearLayout.LayoutParams(this.mCellSpace, -1));
        return view;
    }

    private View createRowPadding() {
        View view = new View(this.mContext);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, this.mCellSpace));
        return view;
    }

    private LinearLayout.LayoutParams getLayoutParamsForACell() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.weight = 1.0f;
        return layoutParams;
    }

    @Override // com.amazon.mp3.card.AbstractCardLayoutManager, com.amazon.mp3.card.LayoutManager
    public void addView(final View itemView) {
        ViewGroup viewGroup = this.mRow;
        if (viewGroup == null || this.mRowColumnCount == this.mColumnCount) {
            if (viewGroup != null) {
                this.mContainer.addView(createRowPadding());
            }
            ViewGroup viewGroupCreateRow = createRow();
            this.mRow = viewGroupCreateRow;
            this.mContainer.addView(viewGroupCreateRow);
            this.mRowColumnCount = 0;
        }
        LinearLayout.LayoutParams layoutParamsForACell = getLayoutParamsForACell();
        this.mRow.removeViewAt(this.mRowColumnCount * 2);
        this.mRow.addView(itemView, this.mRowColumnCount * 2, layoutParamsForACell);
        this.mRowColumnCount++;
    }

    @Override // com.amazon.mp3.card.AbstractCardLayoutManager, com.amazon.mp3.card.LayoutManager
    public void clearAll() {
        super.clearAll();
        this.mRowColumnCount = 0;
        this.mRow = null;
    }
}
