package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
class LayoutState {
    public static final int INVALID_LAYOUT = Integer.MIN_VALUE;
    public static final int ITEM_DIRECTION_HEAD = -1;
    public static final int ITEM_DIRECTION_TAIL = 1;
    public static final int LAYOUT_END = 1;
    public static final int LAYOUT_START = -1;
    public int mAvailable;
    public int mCurrentPosition;
    public boolean mInfinite;
    public int mItemDirection;
    public int mLayoutDirection;
    public boolean mStopInFocusable;
    public boolean mRecycle = true;
    public int mStartLine = 0;
    public int mEndLine = 0;

    public boolean hasMore(RecyclerView.State state) {
        int i = this.mCurrentPosition;
        return i >= 0 && i < state.getItemCount();
    }

    public View next(RecyclerView.Recycler recycler) {
        View viewForPosition = recycler.getViewForPosition(this.mCurrentPosition);
        this.mCurrentPosition += this.mItemDirection;
        return viewForPosition;
    }

    public String toString() {
        StringBuilder sbK = e.k("LayoutState{mAvailable=");
        sbK.append(this.mAvailable);
        sbK.append(", mCurrentPosition=");
        sbK.append(this.mCurrentPosition);
        sbK.append(", mItemDirection=");
        sbK.append(this.mItemDirection);
        sbK.append(", mLayoutDirection=");
        sbK.append(this.mLayoutDirection);
        sbK.append(", mStartLine=");
        sbK.append(this.mStartLine);
        sbK.append(", mEndLine=");
        sbK.append(this.mEndLine);
        sbK.append('}');
        return sbK.toString();
    }
}
