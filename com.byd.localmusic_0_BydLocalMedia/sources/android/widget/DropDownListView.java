package android.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.android.internal.widget.AutoScrollHelper;

/* JADX INFO: loaded from: classes3.dex */
public class DropDownListView extends ListView {
    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private boolean mListSelectionHidden;
    private ResolveHoverRunnable mResolveHoverRunnable;
    private AutoScrollHelper.AbsListViewAutoScroller mScrollHelper;

    public DropDownListView(Context context, boolean z) {
        this(context, z, 16842861);
    }

    public DropDownListView(Context context, boolean z, int i) {
        super(context, null, i);
        this.mHijackFocus = z;
        setCacheColorHint(0);
    }

    @Override // android.widget.AbsListView
    boolean shouldShowSelector() {
        return isHovered() || super.shouldShowSelector();
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ResolveHoverRunnable resolveHoverRunnable = this.mResolveHoverRunnable;
        if (resolveHoverRunnable != null) {
            resolveHoverRunnable.cancel();
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 10 && this.mResolveHoverRunnable == null) {
            ResolveHoverRunnable resolveHoverRunnable = new ResolveHoverRunnable();
            this.mResolveHoverRunnable = resolveHoverRunnable;
            resolveHoverRunnable.post();
        }
        boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
        if (actionMasked == 9 || actionMasked == 7) {
            int iPointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
            if (iPointToPosition != -1 && iPointToPosition != this.mSelectedPosition) {
                View childAt = getChildAt(iPointToPosition - getFirstVisiblePosition());
                if (childAt.isEnabled()) {
                    requestFocus();
                    positionSelector(iPointToPosition, childAt);
                    setSelectedPositionInt(iPointToPosition);
                    setNextSelectedPositionInt(iPointToPosition);
                }
                updateSelectorState();
            }
        } else if (!super.shouldShowSelector()) {
            setSelectedPositionInt(-1);
            setNextSelectedPositionInt(-1);
        }
        return zOnHoverEvent;
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        if (this.mResolveHoverRunnable == null) {
            super.drawableStateChanged();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onForwardedEvent(android.view.MotionEvent r9, int r10) {
        /*
            r8 = this;
            int r0 = r9.getActionMasked()
            r1 = 0
            r2 = 1
            if (r0 == r2) goto L16
            r3 = 2
            if (r0 == r3) goto L14
            r10 = 3
            if (r0 == r10) goto L11
        Le:
            r10 = r1
            r3 = r2
            goto L4a
        L11:
            r10 = r1
            r3 = r10
            goto L4a
        L14:
            r3 = r2
            goto L17
        L16:
            r3 = r1
        L17:
            int r10 = r9.findPointerIndex(r10)
            if (r10 >= 0) goto L1e
            goto L11
        L1e:
            float r4 = r9.getX(r10)
            int r4 = (int) r4
            float r10 = r9.getY(r10)
            int r10 = (int) r10
            int r5 = r8.pointToPosition(r4, r10)
            r6 = -1
            if (r5 != r6) goto L31
            r10 = r2
            goto L4a
        L31:
            int r3 = r8.getFirstVisiblePosition()
            int r3 = r5 - r3
            android.view.View r3 = r8.getChildAt(r3)
            float r4 = (float) r4
            float r10 = (float) r10
            r8.setPressedItem(r3, r5, r4, r10)
            if (r0 != r2) goto Le
            long r6 = r8.getItemIdAtPosition(r5)
            r8.performItemClick(r3, r5, r6)
            goto Le
        L4a:
            if (r3 == 0) goto L4e
            if (r10 == 0) goto L51
        L4e:
            r8.clearPressedItem()
        L51:
            if (r3 == 0) goto L69
            com.android.internal.widget.AutoScrollHelper$AbsListViewAutoScroller r10 = r8.mScrollHelper
            if (r10 != 0) goto L5e
            com.android.internal.widget.AutoScrollHelper$AbsListViewAutoScroller r10 = new com.android.internal.widget.AutoScrollHelper$AbsListViewAutoScroller
            r10.<init>(r8)
            r8.mScrollHelper = r10
        L5e:
            com.android.internal.widget.AutoScrollHelper$AbsListViewAutoScroller r10 = r8.mScrollHelper
            r10.setEnabled(r2)
            com.android.internal.widget.AutoScrollHelper$AbsListViewAutoScroller r10 = r8.mScrollHelper
            r10.onTouch(r8, r9)
            goto L70
        L69:
            com.android.internal.widget.AutoScrollHelper$AbsListViewAutoScroller r9 = r8.mScrollHelper
            if (r9 == 0) goto L70
            r9.setEnabled(r1)
        L70:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.DropDownListView.onForwardedEvent(android.view.MotionEvent, int):boolean");
    }

    public void setListSelectionHidden(boolean z) {
        this.mListSelectionHidden = z;
    }

    private void clearPressedItem() {
        this.mDrawsInPressedState = false;
        setPressed(false);
        updateSelectorState();
        View childAt = getChildAt(this.mMotionPosition - this.mFirstPosition);
        if (childAt != null) {
            childAt.setPressed(false);
        }
    }

    private void setPressedItem(View view, int i, float f, float f2) {
        this.mDrawsInPressedState = true;
        drawableHotspotChanged(f, f2);
        if (!isPressed()) {
            setPressed(true);
        }
        if (this.mDataChanged) {
            layoutChildren();
        }
        View childAt = getChildAt(this.mMotionPosition - this.mFirstPosition);
        if (childAt != null && childAt != view && childAt.isPressed()) {
            childAt.setPressed(false);
        }
        this.mMotionPosition = i;
        view.drawableHotspotChanged(f - view.getLeft(), f2 - view.getTop());
        if (!view.isPressed()) {
            view.setPressed(true);
        }
        setSelectedPositionInt(i);
        positionSelectorLikeTouch(i, view, f, f2);
        refreshDrawableState();
    }

    @Override // android.widget.AbsListView
    boolean touchModeDrawsInPressedState() {
        return this.mDrawsInPressedState || super.touchModeDrawsInPressedState();
    }

    @Override // android.widget.AbsListView
    View obtainView(int i, boolean[] zArr) {
        View viewObtainView = super.obtainView(i, zArr);
        if (viewObtainView instanceof TextView) {
            ((TextView) viewObtainView).setHorizontallyScrolling(true);
        }
        return viewObtainView;
    }

    @Override // android.view.View
    public boolean isInTouchMode() {
        return (this.mHijackFocus && this.mListSelectionHidden) || super.isInTouchMode();
    }

    @Override // android.view.View
    public boolean hasWindowFocus() {
        return this.mHijackFocus || super.hasWindowFocus();
    }

    @Override // android.view.View
    public boolean isFocused() {
        return this.mHijackFocus || super.isFocused();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean hasFocus() {
        return this.mHijackFocus || super.hasFocus();
    }

    private class ResolveHoverRunnable implements Runnable {
        private ResolveHoverRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DropDownListView.this.mResolveHoverRunnable = null;
            DropDownListView.this.drawableStateChanged();
        }

        public void cancel() {
            DropDownListView.this.mResolveHoverRunnable = null;
            DropDownListView.this.removeCallbacks(this);
        }

        public void post() {
            DropDownListView.this.post(this);
        }
    }
}
