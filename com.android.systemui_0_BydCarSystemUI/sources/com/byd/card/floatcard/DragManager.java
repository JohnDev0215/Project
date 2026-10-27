package com.byd.card.floatcard;

import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.graphics.Rect;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.view.DragEvent;
import android.view.GestureManager;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Constants;
import com.android.systemui.util.GuideUtils;
import com.byd.card.bean.AppInfo;
import com.byd.card.bean.Constant;
import com.byd.card.utils.AppUtils;
import com.byd.profile.ProfileManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class DragManager implements View.OnDragListener {
    private static final int SCROLLER_DISTANCE = 80;
    private static final String TAG = "DragManager";
    private static final int TOUCH_PADDING_RIGHT = 20;
    private Context mContext;
    private float mCurrentX;
    private float mCurrentY;
    private List<Integer> mDistances;
    private final DragListener mDragListener;
    private long mDragScrollStartTimeInMs;
    private View mDragView;
    private float mDragY;
    private float mDx;
    private GestureManager mGestureManager;
    private final RecyclerView mRecyclerView;
    private float mStartX;
    private float mStartY;
    private List<RecyclerView.ViewHolder> mSwapTargets;
    private Rect mTmpRect;
    private int mToPosition;
    private RecyclerView.ViewHolder mSelected = null;
    final Runnable mScrollRunnable = new Runnable() { // from class: com.byd.card.floatcard.DragManager.1
        @Override // java.lang.Runnable
        public void run() {
            if (DragManager.this.mSelected == null || !DragManager.this.scrollIfNecessary()) {
                return;
            }
            DragManager.this.mRecyclerView.removeCallbacks(DragManager.this.mScrollRunnable);
            ViewCompat.postOnAnimation(DragManager.this.mRecyclerView, this);
        }
    };
    private GestureManager.GestureChangeListener mGestureChangeListener = new GestureManager.GestureChangeListener() { // from class: com.byd.card.floatcard.DragManager.2
        public void onGestureChanged(MotionEvent event) {
            boolean zEquals = TextUtils.equals(SystemProperties.get(Constants.SYS_GESTURE_SYSTEMUI_KEY, Constant.CarType.CAR_TYPE_FUEL), Constant.CarType.CAR_TYPE_FUEL);
            if (!GuideUtils.isProvisionFinished(DragManager.this.mContext) || !GuideUtils.isSetupFinished(DragManager.this.mContext) || !zEquals || ProfileManager.getInstance(DragManager.this.mContext).isCleanScreen() || event.getPointerCount() < 2 || DragManager.this.mDragView == null) {
                return;
            }
            DragManager.this.mDragView.cancelDragAndDrop();
        }
    };

    public DragManager(RecyclerView recyclerView, DragListener dragListener, Context context) {
        this.mRecyclerView = recyclerView;
        this.mDragListener = dragListener;
        this.mContext = context;
        try {
            GestureManager gestureManager = (GestureManager) context.getSystemService("gesture");
            this.mGestureManager = gestureManager;
            if (gestureManager != null) {
                gestureManager.subscribeGestureChangeListener(this.mGestureChangeListener);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean scrollIfNecessary() {
        /*
            r15 = this;
            androidx.recyclerview.widget.RecyclerView$ViewHolder r0 = r15.mSelected
            r1 = 0
            r2 = -9223372036854775808
            if (r0 != 0) goto La
            r15.mDragScrollStartTimeInMs = r2
            return r1
        La:
            long r4 = java.lang.System.currentTimeMillis()
            long r6 = r15.mDragScrollStartTimeInMs
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 != 0) goto L17
            r6 = 0
            goto L19
        L17:
            long r6 = r4 - r6
        L19:
            r13 = r6
            androidx.recyclerview.widget.RecyclerView r0 = r15.mRecyclerView
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            android.graphics.Rect r6 = r15.mTmpRect
            if (r6 != 0) goto L2b
            android.graphics.Rect r6 = new android.graphics.Rect
            r6.<init>()
            r15.mTmpRect = r6
        L2b:
            if (r0 == 0) goto L91
            androidx.recyclerview.widget.RecyclerView$ViewHolder r6 = r15.mSelected
            android.view.View r6 = r6.itemView
            android.graphics.Rect r7 = r15.mTmpRect
            r0.calculateItemDecorationsForChild(r6, r7)
            boolean r0 = r0.canScrollHorizontally()
            if (r0 == 0) goto L91
            float r0 = r15.mCurrentX
            int r6 = (int) r0
            float r7 = r15.mDx
            r8 = 0
            int r9 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r9 < 0) goto L82
            float r9 = r15.mStartX
            float r0 = r0 - r9
            int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r0 >= 0) goto L4e
            goto L82
        L4e:
            int r0 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r0 <= 0) goto L91
            androidx.recyclerview.widget.RecyclerView$ViewHolder r0 = r15.mSelected
            android.view.View r0 = r0.itemView
            int r0 = r0.getWidth()
            int r0 = r0 + r6
            android.graphics.Rect r7 = r15.mTmpRect
            int r7 = r7.right
            int r0 = r0 + r7
            androidx.recyclerview.widget.RecyclerView r7 = r15.mRecyclerView
            int r7 = r7.getWidth()
            androidx.recyclerview.widget.RecyclerView r8 = r15.mRecyclerView
            int r8 = r8.getPaddingRight()
            int r7 = r7 - r8
            int r0 = r0 - r7
            int r6 = r6 + 80
            androidx.recyclerview.widget.RecyclerView r7 = r15.mRecyclerView
            int r7 = r7.getWidth()
            androidx.recyclerview.widget.RecyclerView r8 = r15.mRecyclerView
            int r8 = r8.getPaddingRight()
            int r7 = r7 - r8
            int r6 = r6 - r7
            if (r6 <= 0) goto L91
            r11 = r0
            goto L92
        L82:
            int r0 = r6 + (-80)
            if (r0 >= 0) goto L91
            androidx.recyclerview.widget.RecyclerView$ViewHolder r0 = r15.mSelected
            android.view.View r0 = r0.itemView
            int r0 = r0.getWidth()
            int r6 = r6 - r0
            r11 = r6
            goto L92
        L91:
            r11 = r1
        L92:
            if (r11 == 0) goto La9
            androidx.recyclerview.widget.RecyclerView r9 = r15.mRecyclerView
            androidx.recyclerview.widget.RecyclerView$ViewHolder r0 = r15.mSelected
            android.view.View r0 = r0.itemView
            int r10 = r0.getWidth()
            androidx.recyclerview.widget.RecyclerView r0 = r15.mRecyclerView
            int r12 = r0.getWidth()
            r8 = r15
            int r11 = r8.interpolateOutOfBoundsScroll(r9, r10, r11, r12, r13)
        La9:
            if (r11 == 0) goto Lba
            long r6 = r15.mDragScrollStartTimeInMs
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 != 0) goto Lb3
            r15.mDragScrollStartTimeInMs = r4
        Lb3:
            androidx.recyclerview.widget.RecyclerView r0 = r15.mRecyclerView
            r0.scrollBy(r11, r1)
            r0 = 1
            return r0
        Lba:
            r15.mDragScrollStartTimeInMs = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.card.floatcard.DragManager.scrollIfNecessary():boolean");
    }

    public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int viewSize, int viewSizeOutOfBounds, int totalSize, long msSinceStartScroll) {
        return ((int) Math.signum(viewSizeOutOfBounds)) * 5;
    }

    @Override // android.view.View.OnDragListener
    public boolean onDrag(View v, DragEvent event) {
        if (!(event.getLocalState() instanceof RecyclerView.ViewHolder)) {
            return false;
        }
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) event.getLocalState();
        this.mDragView = viewHolder.itemView;
        this.mSelected = viewHolder;
        int action = event.getAction();
        if (action == 1) {
            this.mStartX = event.getX();
            this.mStartY = event.getY();
            if (this.mDragView.getVisibility() == 0 && this.mDragY != this.mStartY) {
                this.mDragView.setVisibility(4);
            }
            return true;
        }
        if (action == 2) {
            updateDxDy(event);
            moveIfNecessary(viewHolder);
            this.mRecyclerView.removeCallbacks(this.mScrollRunnable);
            if (Math.abs(this.mCurrentX - this.mStartX) > 20.0f) {
                this.mScrollRunnable.run();
            }
            return true;
        }
        if (action == 3) {
            this.mDragView.setVisibility(0);
            this.mRecyclerView.removeCallbacks(this.mScrollRunnable);
            return true;
        }
        if (action != 4) {
            if (action != 5 && action == 6) {
            }
            return true;
        }
        this.mDragView.setVisibility(0);
        this.mRecyclerView.removeCallbacks(this.mScrollRunnable);
        AppUtils.updateMediaCard(this.mRecyclerView.getContext());
        return true;
    }

    public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder selected, List<RecyclerView.ViewHolder> dropTargets, int curX, int curY) {
        int iAbs;
        int iAbs2;
        int left = curX - selected.itemView.getLeft();
        int size = dropTargets.size();
        int i = -1;
        RecyclerView.ViewHolder viewHolder = null;
        int left2 = 0;
        for (int i2 = 0; i2 < size; i2++) {
            RecyclerView.ViewHolder viewHolder2 = dropTargets.get(i2);
            if (left > 0 && viewHolder2.itemView.getRight() - curX < 0 && viewHolder2.itemView.getRight() > selected.itemView.getRight() && (iAbs2 = Math.abs(left2)) > i) {
                viewHolder = viewHolder2;
                i = iAbs2;
            }
            if (left < 0 && (left2 = viewHolder2.itemView.getLeft() - curX) > 0 && viewHolder2.itemView.getLeft() < selected.itemView.getLeft() && (iAbs = Math.abs(left2)) > i) {
                viewHolder = viewHolder2;
                i = iAbs;
            }
        }
        return viewHolder;
    }

    private void moveIfNecessary(RecyclerView.ViewHolder viewHolder) {
        if (this.mRecyclerView.isLayoutRequested()) {
            return;
        }
        if (Math.abs(this.mCurrentY - viewHolder.itemView.getTop()) >= viewHolder.itemView.getHeight() * 0.5f || Math.abs(this.mCurrentX - viewHolder.itemView.getLeft()) >= viewHolder.itemView.getWidth() * 0.5f) {
            List<RecyclerView.ViewHolder> listFindSwapTargets = findSwapTargets(viewHolder, (int) this.mCurrentX, (int) this.mCurrentY);
            if (listFindSwapTargets.size() == 0) {
                return;
            }
            RecyclerView.ViewHolder viewHolderChooseDropTarget = chooseDropTarget(viewHolder, listFindSwapTargets, (int) this.mCurrentX, (int) this.mCurrentY);
            if (viewHolderChooseDropTarget == null) {
                this.mSwapTargets.clear();
                this.mDistances.clear();
                return;
            }
            this.mToPosition = viewHolderChooseDropTarget.getAdapterPosition();
            int adapterPosition = viewHolder.getAdapterPosition();
            CardAdapter cardAdapter = (CardAdapter) this.mRecyclerView.getAdapter();
            if (adapterPosition == -1 || this.mToPosition == -1 || cardAdapter == null) {
                return;
            }
            CopyOnWriteArrayList<Object> data = cardAdapter.getData();
            if (data != null && this.mToPosition < data.size()) {
                Object obj = data.get(this.mToPosition);
                if (obj instanceof AppInfo) {
                    AppInfo appInfo = (AppInfo) obj;
                    if ("com.ts.carplay.app".equals(appInfo.packageName) || "com.ts.androidauto.app".equals(appInfo.packageName)) {
                        return;
                    }
                }
                if (obj instanceof AppWidgetProviderInfo) {
                    AppWidgetProviderInfo appWidgetProviderInfo = (AppWidgetProviderInfo) obj;
                    if ("com.neusoft.na.navigation".equals(appWidgetProviderInfo.provider.getPackageName()) || "com.huawei.maps.auto.app".equals(appWidgetProviderInfo.provider.getPackageName()) || Constant.WidgetName.AUTO_MAP_FAKE_WIDGET.equals(appWidgetProviderInfo.provider.getClassName())) {
                        return;
                    }
                }
                if (obj instanceof AddViewBean) {
                    return;
                }
            }
            if (this.mDragListener.onMoveItem(adapterPosition, this.mToPosition)) {
                int i = (int) this.mCurrentX;
                onMoved(this.mRecyclerView, viewHolder, adapterPosition, viewHolderChooseDropTarget, this.mToPosition, i, i);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof ItemTouchHelper.ViewDropHandler) {
            ((ItemTouchHelper.ViewDropHandler) layoutManager).prepareForDrop(viewHolder.itemView, target.itemView, x, y);
            return;
        }
        if (layoutManager.canScrollHorizontally()) {
            if (layoutManager.getDecoratedLeft(target.itemView) <= recyclerView.getPaddingLeft()) {
                recyclerView.scrollToPosition(toPos);
            }
            if (layoutManager.getDecoratedRight(target.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                recyclerView.scrollToPosition(toPos);
            }
        }
        if (layoutManager.canScrollVertically()) {
            if (layoutManager.getDecoratedTop(target.itemView) <= recyclerView.getPaddingTop()) {
                recyclerView.scrollToPosition(toPos);
            }
            if (layoutManager.getDecoratedBottom(target.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                recyclerView.scrollToPosition(toPos);
            }
        }
    }

    void updateDxDy(DragEvent ev) {
        this.mCurrentX = ev.getX();
        this.mCurrentY = ev.getY();
        this.mDx = this.mCurrentX - this.mSelected.itemView.getLeft();
        if (this.mDragView.getVisibility() == 0) {
            if (Math.abs(this.mCurrentX - this.mStartX) > 2.0f || Math.abs(this.mCurrentY - this.mStartY) > 2.0f) {
                this.mDragView.setVisibility(4);
            }
        }
    }

    private List<RecyclerView.ViewHolder> findSwapTargets(RecyclerView.ViewHolder viewHolder, int currentX, int currentY) {
        List<RecyclerView.ViewHolder> list = this.mSwapTargets;
        if (list == null) {
            this.mSwapTargets = new ArrayList();
        } else {
            list.clear();
        }
        List<Integer> list2 = this.mDistances;
        if (list2 == null) {
            this.mDistances = new ArrayList();
        } else {
            list2.clear();
        }
        int width = (currentX + (viewHolder.itemView.getWidth() + currentX)) / 2;
        int height = (currentY + (viewHolder.itemView.getHeight() + currentY)) / 2;
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        int childCount = layoutManager.getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = layoutManager.getChildAt(i2);
            if (childAt != viewHolder.itemView) {
                RecyclerView.ViewHolder childViewHolder = this.mRecyclerView.getChildViewHolder(childAt);
                int iAbs = Math.abs(width - ((childAt.getLeft() + childAt.getRight()) / 2));
                int iAbs2 = Math.abs(height - ((childAt.getTop() + childAt.getBottom()) / 2));
                int i3 = (iAbs * iAbs) + (iAbs2 * iAbs2);
                int size = this.mSwapTargets.size();
                while (iAbs < size && i3 > this.mDistances.get(iAbs).intValue()) {
                    i++;
                }
                this.mSwapTargets.add(i, childViewHolder);
                this.mDistances.add(i, Integer.valueOf(i3));
            }
        }
        return this.mSwapTargets;
    }

    public void updateDragTouchPoint(MotionEvent event) {
        this.mDragY = event.getY();
    }

    public void unregisterGesture() {
        try {
            GestureManager gestureManager = this.mGestureManager;
            if (gestureManager != null) {
                gestureManager.unSubscribeGestureChangeListener(this.mGestureChangeListener);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
