package com.android.launcher3.dragndrop;

import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.DragSource;
import com.android.launcher3.DropTarget;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.R;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.accessibility.DragViewStateAnnouncer;
import com.android.launcher3.dragndrop.DragDriver;
import com.android.launcher3.testing.TestProtocol;
import com.android.launcher3.util.ItemInfoMatcher;
import com.android.launcher3.util.TouchController;
import com.android.launcher3.util.UiThreadHelper;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class DragController implements DragDriver.EventListener, TouchController {
    private static final boolean PROFILE_DRAWING_DURING_DRAG = false;
    private DropTarget.DragObject mDragObject;
    private FlingToDeleteHelper mFlingToDeleteHelper;
    private boolean mIsInPreDrag;
    private DropTarget mLastDropTarget;
    Launcher mLauncher;
    private int mMotionDownX;
    private int mMotionDownY;
    private View mMoveTarget;
    private DragOptions mOptions;
    private IBinder mWindowToken;
    private Rect mRectTemp = new Rect();
    private final int[] mCoordinatesTemp = new int[2];
    private DragDriver mDragDriver = null;
    private ArrayList<DropTarget> mDropTargets = new ArrayList<>();
    private ArrayList<DragListener> mListeners = new ArrayList<>();
    int[] mLastTouch = new int[2];
    long mLastTouchUpTime = -1;
    int mDistanceSinceScroll = 0;
    private int[] mTmpPoint = new int[2];
    private Rect mDragLayerRect = new Rect();

    public interface DragListener {
        void onDragEnd();

        void onDragStart(DropTarget.DragObject dragObject, DragOptions options);
    }

    public DragController(Launcher launcher) {
        this.mLauncher = launcher;
        this.mFlingToDeleteHelper = new FlingToDeleteHelper(launcher);
    }

    public DragView startDrag(Bitmap b, int dragLayerX, int dragLayerY, DragSource source, ItemInfo dragInfo, Point dragOffset, Rect dragRegion, float initialDragViewScale, float dragViewScaleOnDrop, DragOptions options) {
        UiThreadHelper.hideKeyboardAsync(this.mLauncher, this.mWindowToken);
        AbstractFloatingView.closeOpenViews(this.mLauncher, false, 64);
        this.mOptions = options;
        if (options.systemDndStartPoint != null) {
            this.mMotionDownX = this.mOptions.systemDndStartPoint.x;
            this.mMotionDownY = this.mOptions.systemDndStartPoint.y;
        }
        int i = this.mMotionDownX - dragLayerX;
        int i2 = this.mMotionDownY - dragLayerY;
        int i3 = dragRegion == null ? 0 : dragRegion.left;
        int i4 = dragRegion == null ? 0 : dragRegion.top;
        this.mLastDropTarget = null;
        this.mDragObject = new DropTarget.DragObject();
        this.mIsInPreDrag = (this.mOptions.preDragCondition == null || this.mOptions.preDragCondition.shouldStartDrag(0.0d)) ? false : true;
        float dimensionPixelSize = this.mIsInPreDrag ? this.mLauncher.getResources().getDimensionPixelSize(R.dimen.pre_drag_view_scale) : 0.0f;
        DropTarget.DragObject dragObject = this.mDragObject;
        DragView dragView = new DragView(this.mLauncher, b, i, i2, initialDragViewScale, dragViewScaleOnDrop, dimensionPixelSize);
        dragObject.dragView = dragView;
        dragView.setItemInfo(dragInfo);
        this.mDragObject.dragComplete = false;
        if (this.mOptions.isAccessibleDrag) {
            this.mDragObject.xOffset = b.getWidth() / 2;
            this.mDragObject.yOffset = b.getHeight() / 2;
            this.mDragObject.accessibleDrag = true;
        } else {
            this.mDragObject.xOffset = this.mMotionDownX - (dragLayerX + i3);
            this.mDragObject.yOffset = this.mMotionDownY - (dragLayerY + i4);
            this.mDragObject.stateAnnouncer = DragViewStateAnnouncer.createFor(dragView);
            this.mDragDriver = DragDriver.create(this.mLauncher, this, this.mDragObject, this.mOptions);
        }
        this.mDragObject.dragSource = source;
        this.mDragObject.dragInfo = dragInfo;
        this.mDragObject.originalDragInfo = new ItemInfo();
        this.mDragObject.originalDragInfo.copyFrom(dragInfo);
        if (dragOffset != null) {
            dragView.setDragVisualizeOffset(new Point(dragOffset));
        }
        if (dragRegion != null) {
            dragView.setDragRegion(new Rect(dragRegion));
        }
        this.mLauncher.getDragLayer().performHapticFeedback(0);
        dragView.show(this.mMotionDownX, this.mMotionDownY);
        this.mDistanceSinceScroll = 0;
        if (!this.mIsInPreDrag) {
            callOnDragStart();
        } else if (this.mOptions.preDragCondition != null) {
            this.mOptions.preDragCondition.onPreDragStart(this.mDragObject);
        }
        int[] iArr = this.mLastTouch;
        int i5 = this.mMotionDownX;
        iArr[0] = i5;
        int i6 = this.mMotionDownY;
        iArr[1] = i6;
        handleMoveEvent(i5, i6);
        this.mLauncher.getUserEventDispatcher().resetActionDurationMillis();
        return dragView;
    }

    private void callOnDragStart() {
        if (this.mOptions.preDragCondition != null) {
            this.mOptions.preDragCondition.onPreDragEnd(this.mDragObject, true);
        }
        this.mIsInPreDrag = false;
        Iterator it = new ArrayList(this.mListeners).iterator();
        while (it.hasNext()) {
            ((DragListener) it.next()).onDragStart(this.mDragObject, this.mOptions);
        }
    }

    public void addFirstFrameAnimationHelper(ValueAnimator anim) {
        DropTarget.DragObject dragObject = this.mDragObject;
        if (dragObject == null || dragObject.dragView == null) {
            return;
        }
        this.mDragObject.dragView.mFirstFrameAnimatorHelper.addTo(anim);
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        return this.mDragDriver != null;
    }

    public boolean isDragging() {
        DragOptions dragOptions;
        return this.mDragDriver != null || ((dragOptions = this.mOptions) != null && dragOptions.isAccessibleDrag);
    }

    public void cancelDrag() {
        if (isDragging()) {
            DropTarget dropTarget = this.mLastDropTarget;
            if (dropTarget != null) {
                dropTarget.onDragExit(this.mDragObject);
            }
            this.mDragObject.deferDragViewCleanupPostAnimation = false;
            this.mDragObject.cancelled = true;
            this.mDragObject.dragComplete = true;
            if (!this.mIsInPreDrag) {
                dispatchDropComplete(null, false);
            }
        }
        endDrag();
    }

    private void dispatchDropComplete(View dropTarget, boolean accepted) {
        if (!accepted) {
            this.mLauncher.getStateManager().goToState(LauncherState.NORMAL, 500L);
            this.mDragObject.deferDragViewCleanupPostAnimation = false;
        }
        this.mDragObject.dragSource.onDropCompleted(dropTarget, this.mDragObject, accepted);
    }

    public void onAppsRemoved(ItemInfoMatcher matcher) {
        ComponentName targetComponent;
        DropTarget.DragObject dragObject = this.mDragObject;
        if (dragObject != null) {
            ItemInfo itemInfo = dragObject.dragInfo;
            if ((itemInfo instanceof WorkspaceItemInfo) && (targetComponent = itemInfo.getTargetComponent()) != null && matcher.matches(itemInfo, targetComponent)) {
                cancelDrag();
            }
        }
    }

    private void endDrag() {
        if (isDragging()) {
            this.mDragDriver = null;
            boolean z = false;
            if (this.mDragObject.dragView != null) {
                z = this.mDragObject.deferDragViewCleanupPostAnimation;
                if (!z) {
                    this.mDragObject.dragView.remove();
                } else if (this.mIsInPreDrag) {
                    animateDragViewToOriginalPosition(null, null, -1);
                }
                this.mDragObject.dragView = null;
            }
            if (!z) {
                callOnDragEnd();
            }
        }
        this.mFlingToDeleteHelper.releaseVelocityTracker();
    }

    public void animateDragViewToOriginalPosition(final Runnable onComplete, final View originalIcon, int duration) {
        this.mDragObject.dragView.animateTo(this.mMotionDownX, this.mMotionDownY, new Runnable() { // from class: com.android.launcher3.dragndrop.DragController.1
            @Override // java.lang.Runnable
            public void run() {
                View view = originalIcon;
                if (view != null) {
                    view.setVisibility(0);
                }
                Runnable runnable = onComplete;
                if (runnable != null) {
                    runnable.run();
                }
            }
        }, duration);
    }

    private void callOnDragEnd() {
        if (this.mIsInPreDrag && this.mOptions.preDragCondition != null) {
            this.mOptions.preDragCondition.onPreDragEnd(this.mDragObject, false);
        }
        this.mIsInPreDrag = false;
        this.mOptions = null;
        Iterator it = new ArrayList(this.mListeners).iterator();
        while (it.hasNext()) {
            ((DragListener) it.next()).onDragEnd();
        }
    }

    void onDeferredEndDrag(DragView dragView) {
        dragView.remove();
        if (this.mDragObject.deferDragViewCleanupPostAnimation) {
            callOnDragEnd();
        }
    }

    private int[] getClampedDragLayerPos(float x, float y) {
        this.mLauncher.getDragLayer().getLocalVisibleRect(this.mDragLayerRect);
        this.mTmpPoint[0] = (int) Math.max(this.mDragLayerRect.left, Math.min(x, this.mDragLayerRect.right - 1));
        this.mTmpPoint[1] = (int) Math.max(this.mDragLayerRect.top, Math.min(y, this.mDragLayerRect.bottom - 1));
        return this.mTmpPoint;
    }

    public long getLastGestureUpTime() {
        if (this.mDragDriver != null) {
            return System.currentTimeMillis();
        }
        return this.mLastTouchUpTime;
    }

    public void resetLastGestureUpTime() {
        this.mLastTouchUpTime = -1L;
    }

    @Override // com.android.launcher3.dragndrop.DragDriver.EventListener
    public void onDriverDragMove(float x, float y) {
        int[] clampedDragLayerPos = getClampedDragLayerPos(x, y);
        handleMoveEvent(clampedDragLayerPos[0], clampedDragLayerPos[1]);
    }

    @Override // com.android.launcher3.dragndrop.DragDriver.EventListener
    public void onDriverDragExitWindow() {
        DropTarget dropTarget = this.mLastDropTarget;
        if (dropTarget != null) {
            dropTarget.onDragExit(this.mDragObject);
            this.mLastDropTarget = null;
        }
    }

    @Override // com.android.launcher3.dragndrop.DragDriver.EventListener
    public void onDriverDragEnd(float x, float y) {
        DropTarget dropTargetFindDropTarget;
        Runnable flingAnimation = this.mFlingToDeleteHelper.getFlingAnimation(this.mDragObject, this.mOptions);
        if (flingAnimation != null) {
            dropTargetFindDropTarget = this.mFlingToDeleteHelper.getDropTarget();
        } else {
            dropTargetFindDropTarget = findDropTarget((int) x, (int) y, this.mCoordinatesTemp);
        }
        drop(dropTargetFindDropTarget, flingAnimation);
        endDrag();
    }

    @Override // com.android.launcher3.dragndrop.DragDriver.EventListener
    public void onDriverDragCancel() {
        cancelDrag();
    }

    @Override // com.android.launcher3.util.TouchController
    public boolean onControllerInterceptTouchEvent(MotionEvent ev) {
        DragOptions dragOptions = this.mOptions;
        if (dragOptions != null && dragOptions.isAccessibleDrag) {
            return false;
        }
        this.mFlingToDeleteHelper.recordMotionEvent(ev);
        int action = ev.getAction();
        int[] clampedDragLayerPos = getClampedDragLayerPos(ev.getX(), ev.getY());
        int i = clampedDragLayerPos[0];
        int i2 = clampedDragLayerPos[1];
        if (action == 0) {
            this.mMotionDownX = i;
            this.mMotionDownY = i2;
        } else if (action == 1) {
            this.mLastTouchUpTime = System.currentTimeMillis();
        }
        DragDriver dragDriver = this.mDragDriver;
        return dragDriver != null && dragDriver.onInterceptTouchEvent(ev);
    }

    public boolean onDragEvent(long dragStartTime, DragEvent event) {
        this.mFlingToDeleteHelper.recordDragEvent(dragStartTime, event);
        DragDriver dragDriver = this.mDragDriver;
        return dragDriver != null && dragDriver.onDragEvent(event);
    }

    public void onDragViewAnimationEnd() {
        DragDriver dragDriver = this.mDragDriver;
        if (dragDriver != null) {
            dragDriver.onDragViewAnimationEnd();
        }
    }

    public void setMoveTarget(View view) {
        this.mMoveTarget = view;
    }

    public boolean dispatchUnhandledMove(View focused, int direction) {
        View view = this.mMoveTarget;
        return view != null && view.dispatchUnhandledMove(focused, direction);
    }

    private void handleMoveEvent(int x, int y) {
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_DRAG_TAG, "handleMoveEvent 1");
        }
        this.mDragObject.dragView.move(x, y);
        int[] iArr = this.mCoordinatesTemp;
        DropTarget dropTargetFindDropTarget = findDropTarget(x, y, iArr);
        boolean z = false;
        this.mDragObject.x = iArr[0];
        this.mDragObject.y = iArr[1];
        checkTouchMove(dropTargetFindDropTarget);
        double d = this.mDistanceSinceScroll;
        int[] iArr2 = this.mLastTouch;
        this.mDistanceSinceScroll = (int) (d + Math.hypot(iArr2[0] - x, iArr2[1] - y));
        int[] iArr3 = this.mLastTouch;
        iArr3[0] = x;
        iArr3[1] = y;
        if (TestProtocol.sDebugTracing) {
            StringBuilder sbAppend = new StringBuilder().append("handleMoveEvent Conditions ").append(this.mIsInPreDrag).append(", ").append(this.mIsInPreDrag && this.mOptions.preDragCondition != null).append(", ");
            if (this.mIsInPreDrag && this.mOptions.preDragCondition != null && this.mOptions.preDragCondition.shouldStartDrag(this.mDistanceSinceScroll)) {
                z = true;
            }
            Log.d(TestProtocol.NO_DRAG_TAG, sbAppend.append(z).toString());
        }
        if (this.mIsInPreDrag && this.mOptions.preDragCondition != null && this.mOptions.preDragCondition.shouldStartDrag(this.mDistanceSinceScroll)) {
            if (TestProtocol.sDebugTracing) {
                Log.d(TestProtocol.NO_DRAG_TAG, "handleMoveEvent 2");
            }
            callOnDragStart();
        }
    }

    public float getDistanceDragged() {
        return this.mDistanceSinceScroll;
    }

    public void forceTouchMove() {
        int[] iArr = this.mCoordinatesTemp;
        int[] iArr2 = this.mLastTouch;
        DropTarget dropTargetFindDropTarget = findDropTarget(iArr2[0], iArr2[1], iArr);
        this.mDragObject.x = iArr[0];
        this.mDragObject.y = iArr[1];
        checkTouchMove(dropTargetFindDropTarget);
    }

    private void checkTouchMove(DropTarget dropTarget) {
        if (dropTarget != null) {
            DropTarget dropTarget2 = this.mLastDropTarget;
            if (dropTarget2 != dropTarget) {
                if (dropTarget2 != null) {
                    dropTarget2.onDragExit(this.mDragObject);
                }
                dropTarget.onDragEnter(this.mDragObject);
            }
            dropTarget.onDragOver(this.mDragObject);
        } else {
            DropTarget dropTarget3 = this.mLastDropTarget;
            if (dropTarget3 != null) {
                dropTarget3.onDragExit(this.mDragObject);
            }
        }
        this.mLastDropTarget = dropTarget;
    }

    @Override // com.android.launcher3.util.TouchController
    public boolean onControllerTouchEvent(MotionEvent ev) {
        DragOptions dragOptions;
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_DRAG_TAG, "onControllerTouchEvent");
        }
        if (this.mDragDriver == null || (dragOptions = this.mOptions) == null || dragOptions.isAccessibleDrag) {
            return false;
        }
        this.mFlingToDeleteHelper.recordMotionEvent(ev);
        int action = ev.getAction();
        int[] clampedDragLayerPos = getClampedDragLayerPos(ev.getX(), ev.getY());
        int i = clampedDragLayerPos[0];
        int i2 = clampedDragLayerPos[1];
        if (action == 0) {
            this.mMotionDownX = i;
            this.mMotionDownY = i2;
        }
        return this.mDragDriver.onTouchEvent(ev);
    }

    public void prepareAccessibleDrag(int x, int y) {
        this.mMotionDownX = x;
        this.mMotionDownY = y;
    }

    public void completeAccessibleDrag(int[] location) {
        int[] iArr = this.mCoordinatesTemp;
        DropTarget dropTargetFindDropTarget = findDropTarget(location[0], location[1], iArr);
        this.mDragObject.x = iArr[0];
        this.mDragObject.y = iArr[1];
        checkTouchMove(dropTargetFindDropTarget);
        dropTargetFindDropTarget.prepareAccessibilityDrop();
        drop(dropTargetFindDropTarget, null);
        endDrag();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void drop(DropTarget dropTarget, Runnable flingAnimation) {
        int[] iArr = this.mCoordinatesTemp;
        boolean z = false;
        this.mDragObject.x = iArr[0];
        this.mDragObject.y = iArr[1];
        DropTarget dropTarget2 = this.mLastDropTarget;
        if (dropTarget != dropTarget2) {
            if (dropTarget2 != null) {
                dropTarget2.onDragExit(this.mDragObject);
            }
            this.mLastDropTarget = dropTarget;
            if (dropTarget != 0) {
                dropTarget.onDragEnter(this.mDragObject);
            }
        }
        this.mDragObject.dragComplete = true;
        if (this.mIsInPreDrag) {
            if (dropTarget != 0) {
                dropTarget.onDragExit(this.mDragObject);
                return;
            }
            return;
        }
        if (dropTarget != 0) {
            dropTarget.onDragExit(this.mDragObject);
            if (dropTarget.acceptDrop(this.mDragObject)) {
                if (flingAnimation != null) {
                    flingAnimation.run();
                } else {
                    dropTarget.onDrop(this.mDragObject, this.mOptions);
                }
                z = true;
            }
        }
        View view = dropTarget instanceof View ? (View) dropTarget : null;
        this.mLauncher.getUserEventDispatcher().logDragNDrop(this.mDragObject, view);
        dispatchDropComplete(view, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private DropTarget findDropTarget(int x, int y, int[] dropCoordinates) {
        this.mDragObject.x = x;
        this.mDragObject.y = y;
        Rect rect = this.mRectTemp;
        ArrayList<DropTarget> arrayList = this.mDropTargets;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            DropTarget dropTarget = arrayList.get(size);
            if (dropTarget.isDropEnabled()) {
                dropTarget.getHitRectRelativeToDragLayer(rect);
                if (rect.contains(x, y)) {
                    dropCoordinates[0] = x;
                    dropCoordinates[1] = y;
                    this.mLauncher.getDragLayer().mapCoordInSelfToDescendant((View) dropTarget, dropCoordinates);
                    return dropTarget;
                }
            }
        }
        dropCoordinates[0] = x;
        dropCoordinates[1] = y;
        this.mLauncher.getDragLayer().mapCoordInSelfToDescendant((View) this.mLauncher.getWorkspace(), dropCoordinates);
        return this.mLauncher.getWorkspace();
    }

    public void setWindowToken(IBinder token) {
        this.mWindowToken = token;
    }

    public void addDragListener(DragListener l) {
        this.mListeners.add(l);
    }

    public void removeDragListener(DragListener l) {
        this.mListeners.remove(l);
    }

    public void addDropTarget(DropTarget target) {
        this.mDropTargets.add(target);
    }

    public void removeDropTarget(DropTarget target) {
        this.mDropTargets.remove(target);
    }
}
