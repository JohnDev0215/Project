package androidx.leanback.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class HorizontalHoverCardSwitcher extends PresenterSwitcher {
    int mCardLeft;
    int mCardRight;
    private int[] mTmpOffsets = new int[2];
    private Rect mTmpRect = new Rect();

    @Override // androidx.leanback.widget.PresenterSwitcher
    protected void insertView(View view) {
        getParentViewGroup().addView(view);
    }

    @Override // androidx.leanback.widget.PresenterSwitcher
    protected void onViewSelected(View view) {
        int rightLimit = getParentViewGroup().getWidth() - getParentViewGroup().getPaddingRight();
        int leftLimit = getParentViewGroup().getPaddingLeft();
        view.measure(0, 0);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        boolean isRtl = ViewCompat.getLayoutDirection(view) == 1;
        if (!isRtl && this.mCardLeft + view.getMeasuredWidth() > rightLimit) {
            params.leftMargin = rightLimit - view.getMeasuredWidth();
        } else if (isRtl && this.mCardLeft < leftLimit) {
            params.leftMargin = leftLimit;
        } else if (isRtl) {
            params.leftMargin = this.mCardRight - view.getMeasuredWidth();
        } else {
            params.leftMargin = this.mCardLeft;
        }
        view.requestLayout();
    }

    public void select(HorizontalGridView gridView, View childView, Object object) {
        ViewGroup parent = getParentViewGroup();
        gridView.getViewSelectedOffsets(childView, this.mTmpOffsets);
        this.mTmpRect.set(0, 0, childView.getWidth(), childView.getHeight());
        parent.offsetDescendantRectToMyCoords(childView, this.mTmpRect);
        this.mCardLeft = this.mTmpRect.left - this.mTmpOffsets[0];
        this.mCardRight = this.mTmpRect.right - this.mTmpOffsets[0];
        select(object);
    }
}
