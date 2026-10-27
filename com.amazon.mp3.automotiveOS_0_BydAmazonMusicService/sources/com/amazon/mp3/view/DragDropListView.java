package com.amazon.mp3.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.library.adapter.EditPlaylistListAdapter;
import com.amazon.mp3.util.ListViewUtil;

/* JADX INFO: loaded from: classes4.dex */
public class DragDropListView extends ListView {
    private static final String TAG = DragDropListView.class.getSimpleName();
    private boolean mDragDropInitialized;
    private int mDragHandleWidth;
    private DragListener mDragListener;
    private int mDragPointX;
    private int mDragPointY;
    private int mDragPos;
    private View mDragView;
    private DropListener mDropListener;
    private MotionEvent mEdgeMotionEvent;
    private int mHeight;
    private boolean mIsLongPress;
    private int mItemHeightExpanded;
    private int mItemHeightHalf;
    protected int mItemHeightNormal;
    private MotionEvent mLastMotionEvent;
    private int mLowerBound;
    private int mSrcDragPos;
    private Rect mTempRect;
    private int mTouchSlop;
    private int mUpperBound;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mWindowParams;
    private int mXOffset;
    private int mYOffset;

    public interface DragListener {
        void drag(int from, int to);
    }

    public interface DropListener {
        void drop(int from, int to);
    }

    protected boolean isValidPosition(int itemnum) {
        return itemnum != -1;
    }

    public DragDropListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTempRect = new Rect();
    }

    public void initDragDrop(int rowHeightPixels, int dragItemId) {
        Resources resources = getResources();
        this.mItemHeightNormal = rowHeightPixels;
        this.mItemHeightHalf = rowHeightPixels / 2;
        this.mItemHeightExpanded = rowHeightPixels * 2;
        this.mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mDragHandleWidth = resources.getDimensionPixelSize(dragItemId);
        this.mDragDropInitialized = true;
        this.mIsLongPress = false;
    }

    protected View getDragView(ViewGroup view) {
        EditPlaylistListAdapter.RowViewHolder rowViewHolder = (EditPlaylistListAdapter.RowViewHolder) view.getTag();
        View viewInflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.library_edit_playlist_row, (ViewGroup) null);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.AlbumArtwork);
        if (imageView != null) {
            imageView.setImageDrawable(rowViewHolder.getAlbumArt());
        }
        TextView textView = (TextView) viewInflate.findViewById(R.id.TrackDuration);
        if (textView != null) {
            textView.setVisibility(4);
        }
        ((TextView) viewInflate.findViewById(R.id.TrackTitle)).setText(rowViewHolder.getTitle());
        ((TextView) viewInflate.findViewById(R.id.ArtistName)).setText(rowViewHolder.getArtist());
        ((ImageView) viewInflate.findViewById(R.id.DeleteIcon)).setVisibility(4);
        return viewInflate;
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        this.mEdgeMotionEvent = null;
        this.mLastMotionEvent = MotionEvent.obtain(ev);
        if (!this.mDragDropInitialized) {
            return super.onInterceptTouchEvent(ev);
        }
        if ((this.mDragListener != null || this.mDropListener != null) && ev.getAction() == 0) {
            int x = (int) ev.getX();
            int y = (int) ev.getY();
            int iPointToPosition = pointToPosition(x, y);
            if (isValidPosition(iPointToPosition)) {
                ViewGroup viewGroup = (ViewGroup) getChildAt(iPointToPosition - getFirstVisiblePosition());
                this.mDragPointX = getDragPointX(x, viewGroup);
                this.mDragPointY = getDragPointY(y, viewGroup);
                this.mXOffset = ((int) ev.getRawX()) - x;
                this.mYOffset = ((int) ev.getRawY()) - y;
                if (x < this.mDragHandleWidth || this.mIsLongPress) {
                    startDragging(getDragView(viewGroup), x, y);
                    this.mDragPos = iPointToPosition;
                    this.mSrcDragPos = iPointToPosition;
                    int height = getHeight();
                    this.mHeight = height;
                    int i = this.mTouchSlop;
                    this.mUpperBound = Math.min(y - i, height / 3);
                    this.mLowerBound = Math.max(y + i, (this.mHeight * 2) / 3);
                    return false;
                }
                stopDragging();
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(final int visibility) {
        super.onWindowVisibilityChanged(visibility);
        setOverScrollMode(visibility == 0 ? 0 : 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:40:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void performDownOrMove(android.view.MotionEvent r7, int r8) {
        /*
            r6 = this;
            float r0 = r7.getX()
            int r0 = (int) r0
            float r1 = r7.getY()
            int r1 = (int) r1
            android.view.MotionEvent r2 = r6.mEdgeMotionEvent
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L12
            r2 = r3
            goto L13
        L12:
            r2 = r4
        L13:
            if (r2 != 0) goto L18
            r6.dragView(r0, r1)
        L18:
            int r0 = r6.getItemForPosition(r1)
            if (r0 < 0) goto L8c
            if (r8 == 0) goto L28
            int r8 = r6.mDragPos
            if (r0 != r8) goto L28
            boolean r8 = r6.mIsLongPress
            if (r8 == 0) goto L36
        L28:
            com.amazon.mp3.view.DragDropListView$DragListener r8 = r6.mDragListener
            if (r8 == 0) goto L31
            int r5 = r6.mDragPos
            r8.drag(r5, r0)
        L31:
            r6.mDragPos = r0
            r6.doExpansion(r2)
        L36:
            r6.adjustScrollBounds(r1)
            int r8 = r6.mLowerBound
            if (r1 <= r8) goto L57
            int r8 = r6.getLastVisiblePosition()
            int r0 = r6.getCount()
            int r0 = r0 - r3
            if (r8 >= r0) goto L7b
            int r8 = r6.mHeight
            int r0 = r6.mLowerBound
            int r8 = r8 + r0
            int r8 = r8 / 2
            if (r1 <= r8) goto L54
            r8 = 16
            goto L55
        L54:
            r8 = 4
        L55:
            r3 = r8
            goto L7b
        L57:
            int r8 = r6.mUpperBound
            if (r1 >= r8) goto L7a
            int r8 = r8 / 2
            if (r1 >= r8) goto L62
            r8 = -16
            goto L63
        L62:
            r8 = -4
        L63:
            r3 = r8
            android.view.View r8 = r6.getChildAt(r4)
            if (r8 == 0) goto L7a
            int r0 = r6.getFirstVisiblePosition()
            if (r0 != 0) goto L7b
            int r8 = r8.getTop()
            int r0 = r6.getPaddingTop()
            if (r8 < r0) goto L7b
        L7a:
            r3 = r4
        L7b:
            if (r3 == 0) goto L8c
            r6.mEdgeMotionEvent = r7
            com.amazon.mp3.view.DragDropListView$1 r7 = new com.amazon.mp3.view.DragDropListView$1
            r7.<init>()
            r0 = 50
            r6.postDelayed(r7, r0)
            r6.manualScrollBy(r3)
        L8c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.view.DragDropListView.performDownOrMove(android.view.MotionEvent, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005c  */
    @Override // android.widget.AbsListView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            r0 = 0
            r3.mEdgeMotionEvent = r0
            boolean r0 = r3.mDragDropInitialized
            if (r0 != 0) goto Lc
            boolean r4 = super.onTouchEvent(r4)
            return r4
        Lc:
            com.amazon.mp3.view.DragDropListView$DragListener r0 = r3.mDragListener
            if (r0 != 0) goto L14
            com.amazon.mp3.view.DragDropListView$DropListener r0 = r3.mDropListener
            if (r0 == 0) goto L60
        L14:
            android.view.View r0 = r3.mDragView
            if (r0 == 0) goto L60
            int r0 = r4.getAction()
            r1 = 1
            if (r0 == 0) goto L5c
            if (r0 == r1) goto L28
            r2 = 2
            if (r0 == r2) goto L5c
            r4 = 3
            if (r0 == r4) goto L28
            goto L5f
        L28:
            android.graphics.Rect r4 = r3.mTempRect
            android.view.View r0 = r3.mDragView
            r0.getDrawingRect(r4)
            r3.stopDragging()
            com.amazon.mp3.view.DragDropListView$DropListener r4 = r3.mDropListener
            if (r4 == 0) goto L57
            int r4 = r3.mDragPos
            if (r4 < 0) goto L57
            int r0 = r3.getCount()
            if (r4 >= r0) goto L57
            com.amazon.mp3.view.DragDropListView$DropListener r4 = r3.mDropListener
            int r0 = r3.mSrcDragPos
            int r2 = r3.mDragPos
            r4.drop(r0, r2)
            int r4 = r3.mDragPos
            int r0 = r3.getCount()
            int r0 = r0 - r1
            if (r4 != r0) goto L57
            int r4 = r3.mDragPos
            r3.setSelection(r4)
        L57:
            r4 = 0
            r3.unExpandViews(r4)
            goto L5f
        L5c:
            r3.performDownOrMove(r4, r0)
        L5f:
            return r1
        L60:
            boolean r4 = super.onTouchEvent(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.view.DragDropListView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setDragListener(DragListener l) {
        this.mDragListener = l;
    }

    public void setDropListener(DropListener l) {
        this.mDropListener = l;
    }

    private int myPointToPosition(int x, int y) {
        int iMyPointToPosition;
        if (y < 0 && (iMyPointToPosition = myPointToPosition(x, this.mItemHeightNormal + y)) > 0) {
            return iMyPointToPosition - 1;
        }
        Rect rect = this.mTempRect;
        int iMax = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            childAt.getHitRect(rect);
            iMax = Math.max(iMax, rect.bottom);
            if (rect.contains(x, y)) {
                return getPositionForView(childAt);
            }
        }
        if (y >= iMax) {
            return getLastVisiblePosition();
        }
        return -1;
    }

    private int getItemForPosition(int y) {
        int i = (y - this.mDragPointY) - this.mItemHeightHalf;
        int iMyPointToPosition = myPointToPosition(this.mWindowParams.x, i);
        if (iMyPointToPosition >= 0) {
            return iMyPointToPosition <= this.mSrcDragPos ? iMyPointToPosition + 1 : iMyPointToPosition;
        }
        if (i < 0) {
            return 0;
        }
        return iMyPointToPosition;
    }

    private void adjustScrollBounds(int y) {
        int i = this.mHeight;
        if (y >= i / 3) {
            this.mUpperBound = i / 3;
        }
        if (y <= (i * 2) / 3) {
            this.mLowerBound = (i * 2) / 3;
        }
    }

    private void unExpandViews(boolean deletion) {
        int i = 0;
        while (true) {
            View childAt = getChildAt(i);
            if (childAt == null) {
                if (deletion) {
                    int firstVisiblePosition = getFirstVisiblePosition();
                    int top = getChildAt(0).getTop();
                    ListViewUtil.stopScroll(this);
                    setAdapter(getAdapter());
                    setSelectionFromTop(firstVisiblePosition, top);
                }
                try {
                    layoutChildren();
                    childAt = getChildAt(i);
                } catch (IllegalStateException unused) {
                }
                if (childAt == null) {
                    return;
                }
            }
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            layoutParams.height = getHeight((ViewGroup) childAt);
            childAt.setLayoutParams(layoutParams);
            childAt.setVisibility(0);
            i++;
        }
    }

    private void doExpansion(boolean currentlyDragScrolling) {
        int headerViewsCount = getHeaderViewsCount();
        int i = this.mSrcDragPos < this.mDragPos ? 1 : 0;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null) {
                int positionForView = getPositionForView(childAt);
                int height = getHeight((ViewGroup) childAt);
                int i3 = this.mDragPos;
                int i4 = 4;
                if (i3 < headerViewsCount && i2 == headerViewsCount) {
                    if (positionForView != this.mSrcDragPos) {
                        if (!currentlyDragScrolling) {
                            height = this.mItemHeightExpanded;
                        }
                        i4 = 0;
                    }
                    ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                    layoutParams.height = height;
                    childAt.setLayoutParams(layoutParams);
                    childAt.setVisibility(i4);
                } else {
                    int i5 = this.mSrcDragPos;
                    if (positionForView == i5) {
                        if (i3 != i5 && positionForView != getCount() - 1) {
                            i4 = 0;
                            height = 1;
                        }
                        ViewGroup.LayoutParams layoutParams2 = childAt.getLayoutParams();
                        layoutParams2.height = height;
                        childAt.setLayoutParams(layoutParams2);
                        childAt.setVisibility(i4);
                    } else {
                        if (positionForView == i3 + i && !currentlyDragScrolling && i3 >= headerViewsCount && i3 < getCount() - 1) {
                            height = this.mItemHeightExpanded;
                        }
                        i4 = 0;
                        ViewGroup.LayoutParams layoutParams22 = childAt.getLayoutParams();
                        layoutParams22.height = height;
                        childAt.setLayoutParams(layoutParams22);
                        childAt.setVisibility(i4);
                    }
                }
            }
        }
    }

    private void manualScrollBy(int speed) {
        smoothScrollBy(speed, 30);
    }

    private void startDragging(View view, int x, int y) {
        stopDragging();
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mWindowParams = layoutParams;
        layoutParams.gravity = 8388659;
        layoutParams.x = (x - this.mDragPointX) + this.mXOffset;
        layoutParams.y = (y - this.mDragPointY) + this.mYOffset;
        layoutParams.height = -2;
        layoutParams.width = -1;
        layoutParams.flags = 920;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 0;
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        this.mWindowManager = windowManager;
        windowManager.addView(view, this.mWindowParams);
        this.mDragView = view;
    }

    private void dragView(int x, int y) {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        int i = iArr[1] - (this.mItemHeightNormal * 2);
        if (y < i) {
            y = i;
        }
        WindowManager.LayoutParams layoutParams = this.mWindowParams;
        layoutParams.y = (y - this.mDragPointY) + this.mYOffset;
        this.mWindowManager.updateViewLayout(this.mDragView, layoutParams);
    }

    private void stopDragging() {
        View view = this.mDragView;
        if (view != null) {
            view.setVisibility(8);
            ((WindowManager) getContext().getSystemService("window")).removeView(this.mDragView);
            this.mDragView = null;
        }
    }

    protected int getDragPointX(int x, ViewGroup view) {
        return x - view.getLeft();
    }

    protected int getDragPointY(int y, ViewGroup view) {
        return y - view.getTop();
    }

    protected int getHeight(ViewGroup vv) {
        return this.mItemHeightNormal;
    }

    public void setIsLongPress(boolean value) {
        this.mIsLongPress = value;
    }

    public MotionEvent getLastMotionEvent() {
        return this.mLastMotionEvent;
    }
}
