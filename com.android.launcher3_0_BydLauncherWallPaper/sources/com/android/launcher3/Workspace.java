package com.android.launcher3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.WallpaperManager;
import android.app.job.JobInfo;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcelable;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;
import com.android.launcher3.CellLayout;
import com.android.launcher3.DropTarget;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppWidgetHost;
import com.android.launcher3.LauncherSettings;
import com.android.launcher3.LauncherStateManager;
import com.android.launcher3.accessibility.AccessibleDragListenerAdapter;
import com.android.launcher3.accessibility.WorkspaceAccessibilityHelper;
import com.android.launcher3.anim.AnimatorSetBuilder;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.compat.AppWidgetManagerCompat;
import com.android.launcher3.config.FeatureFlags;
import com.android.launcher3.dot.FolderDotInfo;
import com.android.launcher3.dragndrop.DragController;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.dragndrop.DragView;
import com.android.launcher3.dragndrop.SpringLoadedDragController;
import com.android.launcher3.folder.Folder;
import com.android.launcher3.folder.FolderIcon;
import com.android.launcher3.folder.PreviewBackground;
import com.android.launcher3.graphics.DragPreviewProvider;
import com.android.launcher3.graphics.PreloadIconDrawable;
import com.android.launcher3.graphics.RotationMode;
import com.android.launcher3.pageindicators.PageIndicatorDots;
import com.android.launcher3.testing.TestProtocol;
import com.android.launcher3.touch.WorkspaceTouchListener;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.util.IntArray;
import com.android.launcher3.util.IntSet;
import com.android.launcher3.util.IntSparseArrayMap;
import com.android.launcher3.util.ItemInfoMatcher;
import com.android.launcher3.util.PackageUserKey;
import com.android.launcher3.util.WallpaperOffsetInterpolator;
import com.android.launcher3.widget.LauncherAppWidgetHostView;
import com.android.launcher3.widget.PendingAddWidgetInfo;
import com.android.launcher3.widget.PendingAppWidgetHostView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes4.dex */
public class Workspace extends PagedView<PageIndicatorDots> implements DropTarget, DragSource, View.OnTouchListener, DragController.DragListener, Insettable, LauncherStateManager.StateHandler, WorkspaceLayoutManager {
    private static final int ADJACENT_SCREEN_DROP_DURATION = 300;
    private static final float ALLOW_DROP_TRANSITION_PROGRESS = 0.25f;
    public static final int ANIMATE_INTO_POSITION_AND_DISAPPEAR = 0;
    public static final int ANIMATE_INTO_POSITION_AND_REMAIN = 1;
    public static final int ANIMATE_INTO_POSITION_AND_RESIZE = 2;
    public static final int CANCEL_TWO_STAGE_WIDGET_DROP_ANIMATION = 4;
    public static final int COMPLETE_TWO_STAGE_WIDGET_DROP_ANIMATION = 3;
    private static final int DEFAULT_PAGE = 0;
    private static final int DRAG_MODE_ADD_TO_FOLDER = 2;
    private static final int DRAG_MODE_CREATE_FOLDER = 1;
    private static final int DRAG_MODE_NONE = 0;
    private static final int DRAG_MODE_REORDER = 3;
    private static final boolean ENFORCE_DRAG_EVENT_ORDER = false;
    private static final int FADE_EMPTY_SCREEN_DURATION = 150;
    private static final float FINISHED_SWITCHING_STATE_TRANSITION_PROGRESS = 0.5f;
    private static final int FOLDER_CREATION_TIMEOUT = 0;
    public static final boolean MAP_NO_RECURSE = false;
    public static final boolean MAP_RECURSE = true;
    static final float MAX_SWIPE_ANGLE = 1.0471976f;
    public static final int REORDER_TIMEOUT = 650;
    private static final int SNAP_OFF_EMPTY_SCREEN_DURATION = 400;
    static final float START_DAMPING_TOUCH_SLOP_ANGLE = 0.5235988f;
    static final float TOUCH_SLOP_DAMPING_FACTOR = 4.0f;
    private boolean mAddToExistingFolderOnDrop;
    boolean mChildrenLayersEnabled;
    private boolean mCreateUserFolderOnDrop;
    private float mCurrentScale;
    boolean mDeferRemoveExtraEmptyScreen;
    DragController mDragController;
    private CellLayout.CellInfo mDragInfo;
    private int mDragMode;
    private FolderIcon mDragOverFolderIcon;
    private int mDragOverX;
    private int mDragOverY;
    private CellLayout mDragOverlappingLayout;
    private ShortcutAndWidgetContainer mDragSourceInternal;
    CellLayout mDragTargetLayout;
    float[] mDragViewVisualCenter;
    private CellLayout mDropToLayout;
    private PreviewBackground mFolderCreateBg;
    private final Alarm mFolderCreationAlarm;
    private boolean mForceDrawAdjacentPages;
    private boolean mIsSwitchingState;
    float mLastOverlayScroll;
    int mLastReorderX;
    int mLastReorderY;
    final Launcher mLauncher;
    Launcher.LauncherOverlay mLauncherOverlay;
    private LayoutTransition mLayoutTransition;
    private float mMaxDistanceForFolderCreation;
    private Runnable mOnOverlayHiddenCallback;
    private DragPreviewProvider mOutlineProvider;
    boolean mOverlayShown;
    private float mOverlayTranslation;
    Runnable mRemoveEmptyScreenRunnable;
    private final Alarm mReorderAlarm;
    private final IntArray mRestoredPages;
    private SparseArray<Parcelable> mSavedStates;
    final IntArray mScreenOrder;
    boolean mScrollInteractionBegan;
    private SpringLoadedDragController mSpringLoadedDragController;
    boolean mStartedSendingScrollEvents;
    private final WorkspaceStateTransitionAnimation mStateTransitionAnimation;
    private boolean mStripScreensOnPageStopMoving;
    int[] mTargetCell;
    private final float[] mTempFXY;
    private final Rect mTempRect;
    private final float[] mTempTouchCoordinates;
    private final int[] mTempXY;
    private float mTransitionProgress;
    private boolean mUnlockWallpaperFromDefaultPageOnLayout;
    final WallpaperManager mWallpaperManager;
    final WallpaperOffsetInterpolator mWallpaperOffset;
    private boolean mWorkspaceFadeInAdjacentScreens;
    final IntSparseArrayMap<CellLayout> mWorkspaceScreens;
    private float mXDown;
    private float mYDown;

    public interface ItemOperator {
        boolean evaluate(ItemInfo info, View view);
    }

    public void bindAndInitFirstWorkspaceScreen(View qsb) {
    }

    @Override // com.android.launcher3.DropTarget
    public boolean isDropEnabled() {
        return true;
    }

    @Override // com.android.launcher3.DropTarget
    public void prepareAccessibilityDrop() {
    }

    public Workspace(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Workspace(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mWorkspaceScreens = new IntSparseArrayMap<>();
        this.mScreenOrder = new IntArray();
        this.mDeferRemoveExtraEmptyScreen = false;
        this.mTargetCell = new int[2];
        this.mDragOverX = -1;
        this.mDragOverY = -1;
        this.mDragTargetLayout = null;
        this.mDragOverlappingLayout = null;
        this.mDropToLayout = null;
        this.mTempRect = new Rect();
        this.mTempXY = new int[2];
        this.mTempFXY = new float[2];
        this.mDragViewVisualCenter = new float[2];
        this.mTempTouchCoordinates = new float[2];
        this.mIsSwitchingState = false;
        this.mChildrenLayersEnabled = true;
        this.mStripScreensOnPageStopMoving = false;
        this.mOutlineProvider = null;
        this.mFolderCreationAlarm = new Alarm();
        this.mReorderAlarm = new Alarm();
        this.mDragOverFolderIcon = null;
        this.mCreateUserFolderOnDrop = false;
        this.mAddToExistingFolderOnDrop = false;
        this.mDragMode = 0;
        this.mLastReorderX = -1;
        this.mLastReorderY = -1;
        this.mRestoredPages = new IntArray();
        this.mLastOverlayScroll = 0.0f;
        this.mOverlayShown = false;
        this.mForceDrawAdjacentPages = false;
        Launcher launcher = Launcher.getLauncher(context);
        this.mLauncher = launcher;
        this.mStateTransitionAnimation = new WorkspaceStateTransitionAnimation(launcher, this);
        this.mWallpaperManager = WallpaperManager.getInstance(context);
        this.mWallpaperOffset = new WallpaperOffsetInterpolator(this);
        setHapticFeedbackEnabled(false);
        initWorkspace();
        setMotionEventSplittingEnabled(true);
        setOnTouchListener(new WorkspaceTouchListener(launcher, this));
    }

    @Override // com.android.launcher3.Insettable
    public void setInsets(Rect insets) {
        float f;
        int i;
        DeviceProfile deviceProfile = this.mLauncher.getDeviceProfile();
        DeviceProfile wallpaperDeviceProfile = this.mLauncher.getWallpaperDeviceProfile();
        if (wallpaperDeviceProfile.isTablet) {
            f = 0.75f;
            i = wallpaperDeviceProfile.iconSizePx;
        } else {
            f = 0.55f;
            i = wallpaperDeviceProfile.iconSizePx;
        }
        this.mMaxDistanceForFolderCreation = i * f;
        this.mWorkspaceFadeInAdjacentScreens = deviceProfile.shouldFadeAdjacentWorkspaceScreens();
        Rect rect = wallpaperDeviceProfile.workspacePadding;
        RotationMode rotationMode = this.mLauncher.getRotationMode();
        rotationMode.mapRect(rect, this.mTempRect);
        setPadding(this.mTempRect.left, this.mTempRect.top, this.mTempRect.right, this.mTempRect.bottom);
        rotationMode.mapRect(wallpaperDeviceProfile.getInsets(), this.mInsets);
        if (this.mWorkspaceFadeInAdjacentScreens) {
            setPageSpacing(deviceProfile.edgeMarginPx);
        } else {
            setPageSpacing(Math.max(deviceProfile.edgeMarginPx, rect.left + 1));
        }
        int i2 = wallpaperDeviceProfile.cellLayoutPaddingLeftRightPx;
        int i3 = wallpaperDeviceProfile.cellLayoutBottomPaddingPx;
        for (int size = this.mWorkspaceScreens.size() - 1; size >= 0; size--) {
            CellLayout cellLayoutValueAt = this.mWorkspaceScreens.valueAt(size);
            cellLayoutValueAt.setRotationMode(rotationMode);
            cellLayoutValueAt.setPadding(i2, 0, i2, i3);
        }
    }

    public int[] estimateItemSize(ItemInfo itemInfo) {
        int[] iArr = new int[2];
        if (getChildCount() <= 0) {
            iArr[0] = Integer.MAX_VALUE;
            iArr[1] = Integer.MAX_VALUE;
            return iArr;
        }
        CellLayout cellLayout = (CellLayout) getChildAt(0);
        boolean z = itemInfo.itemType == 4;
        Rect rectEstimateItemPosition = estimateItemPosition(cellLayout, 0, 0, itemInfo.spanX, itemInfo.spanY);
        float fShrinkRect = 1.0f;
        if (z) {
            DeviceProfile wallpaperDeviceProfile = this.mLauncher.getWallpaperDeviceProfile();
            fShrinkRect = Utilities.shrinkRect(rectEstimateItemPosition, wallpaperDeviceProfile.appWidgetScale.x, wallpaperDeviceProfile.appWidgetScale.y);
        }
        iArr[0] = rectEstimateItemPosition.width();
        iArr[1] = rectEstimateItemPosition.height();
        if (z) {
            iArr[0] = (int) (iArr[0] / fShrinkRect);
            iArr[1] = (int) (iArr[1] / fShrinkRect);
        }
        return iArr;
    }

    public float getWallpaperOffsetForCenterPage() {
        return this.mWallpaperOffset.wallpaperOffsetForScroll(getScrollForPage(getPageNearestToCenterOfScreen()));
    }

    public Rect estimateItemPosition(CellLayout cl, int hCell, int vCell, int hSpan, int vSpan) {
        Rect rect = new Rect();
        cl.cellToRect(hCell, vCell, hSpan, vSpan, rect);
        return rect;
    }

    @Override // com.android.launcher3.dragndrop.DragController.DragListener
    public void onDragStart(DropTarget.DragObject dragObject, DragOptions options) {
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_DRAG_TAG, "onDragStart 1");
        }
        CellLayout.CellInfo cellInfo = this.mDragInfo;
        if (cellInfo != null && cellInfo.cell != null) {
            ((CellLayout) this.mDragInfo.cell.getParent().getParent()).markCellsAsUnoccupiedForView(this.mDragInfo.cell);
        }
        if (this.mOutlineProvider != null && dragObject.dragView != null) {
            this.mOutlineProvider.generateDragOutline(dragObject.dragView.getPreviewBitmap());
        }
        updateChildrenLayersEnabled();
        if (!options.isAccessibleDrag || dragObject.dragSource == this) {
            this.mDeferRemoveExtraEmptyScreen = false;
            addExtraEmptyScreenOnDrag();
            if (dragObject.dragInfo.itemType == 4 && dragObject.dragSource != this) {
                int pageNearestToCenterOfScreen = getPageNearestToCenterOfScreen();
                while (true) {
                    if (pageNearestToCenterOfScreen >= getPageCount()) {
                        break;
                    }
                    if (((CellLayout) getPageAt(pageNearestToCenterOfScreen)).hasReorderSolution(dragObject.dragInfo)) {
                        setCurrentPage(pageNearestToCenterOfScreen);
                        break;
                    }
                    pageNearestToCenterOfScreen++;
                }
            }
        }
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_DRAG_TAG, "onDragStart 2");
        }
        this.mLauncher.getStateManager().goToState(LauncherState.SPRING_LOADED);
    }

    public void deferRemoveExtraEmptyScreen() {
        this.mDeferRemoveExtraEmptyScreen = true;
    }

    @Override // com.android.launcher3.dragndrop.DragController.DragListener
    public void onDragEnd() {
        if (!this.mDeferRemoveExtraEmptyScreen) {
            removeExtraEmptyScreen(true, this.mDragSourceInternal != null);
        }
        updateChildrenLayersEnabled();
        this.mDragInfo = null;
        this.mOutlineProvider = null;
        this.mDragSourceInternal = null;
    }

    protected void initWorkspace() {
        this.mCurrentPage = 0;
        setClipToPadding(false);
        setupLayoutTransition();
        setWallpaperDimension();
    }

    private void setupLayoutTransition() {
        LayoutTransition layoutTransition = new LayoutTransition();
        this.mLayoutTransition = layoutTransition;
        layoutTransition.enableTransitionType(3);
        this.mLayoutTransition.enableTransitionType(1);
        this.mLayoutTransition.disableTransitionType(2);
        this.mLayoutTransition.disableTransitionType(0);
        setLayoutTransition(this.mLayoutTransition);
    }

    void enableLayoutTransitions() {
        setLayoutTransition(this.mLayoutTransition);
    }

    void disableLayoutTransitions() {
        setLayoutTransition(null);
    }

    @Override // com.android.launcher3.PagedView, android.view.ViewGroup
    public void onViewAdded(View child) {
        if (!(child instanceof CellLayout)) {
            throw new IllegalArgumentException("A Workspace can only have CellLayout children.");
        }
        CellLayout cellLayout = (CellLayout) child;
        cellLayout.setOnInterceptTouchListener(this);
        cellLayout.setImportantForAccessibility(2);
        super.onViewAdded(child);
    }

    public void removeAllWorkspaceScreens() {
        disableLayoutTransitions();
        View viewFindViewById = findViewById(R.id.search_container_workspace);
        if (viewFindViewById != null) {
            ((ViewGroup) viewFindViewById.getParent()).removeView(viewFindViewById);
        }
        removeFolderListeners();
        removeAllViews();
        this.mScreenOrder.clear();
        this.mWorkspaceScreens.clear();
        this.mLauncher.mHandler.removeCallbacksAndMessages(DeferredWidgetRefresh.class);
        bindAndInitFirstWorkspaceScreen(viewFindViewById);
        enableLayoutTransitions();
    }

    public void insertNewWorkspaceScreenBeforeEmptyScreen(int screenId) {
        int iIndexOf = this.mScreenOrder.indexOf(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID);
        if (iIndexOf < 0) {
            iIndexOf = this.mScreenOrder.size();
        }
        insertNewWorkspaceScreen(screenId, iIndexOf);
    }

    public void insertNewWorkspaceScreen(int screenId) {
        insertNewWorkspaceScreen(screenId, getChildCount());
    }

    public CellLayout insertNewWorkspaceScreen(int screenId, int insertIndex) {
        if (this.mWorkspaceScreens.containsKey(screenId)) {
            throw new RuntimeException("Screen id " + screenId + " already exists!");
        }
        CellLayout cellLayout = (CellLayout) LayoutInflater.from(getContext()).inflate(R.layout.workspace_screen, (ViewGroup) this, false);
        DeviceProfile wallpaperDeviceProfile = this.mLauncher.getWallpaperDeviceProfile();
        int i = wallpaperDeviceProfile.cellLayoutPaddingLeftRightPx;
        int i2 = wallpaperDeviceProfile.cellLayoutBottomPaddingPx;
        cellLayout.setRotationMode(this.mLauncher.getRotationMode());
        cellLayout.setPadding(i, 0, i, i2);
        this.mWorkspaceScreens.put(screenId, cellLayout);
        this.mScreenOrder.add(insertIndex, screenId);
        addView(cellLayout, insertIndex);
        this.mStateTransitionAnimation.applyChildState(this.mLauncher.getStateManager().getState(), cellLayout, insertIndex);
        if (this.mLauncher.getAccessibilityDelegate().isInAccessibleDrag()) {
            cellLayout.enableAccessibleDrag(true, 2);
        }
        return cellLayout;
    }

    public void addExtraEmptyScreenOnDrag() {
        boolean z;
        this.mRemoveEmptyScreenRunnable = null;
        ShortcutAndWidgetContainer shortcutAndWidgetContainer = this.mDragSourceInternal;
        boolean z2 = false;
        if (shortcutAndWidgetContainer != null) {
            boolean z3 = shortcutAndWidgetContainer.getChildCount() == 1;
            z = indexOfChild((CellLayout) this.mDragSourceInternal.getParent()) == getChildCount() - 1;
            z2 = z3;
        } else {
            z = false;
        }
        if ((z2 && z) || this.mWorkspaceScreens.containsKey(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID)) {
            return;
        }
        insertNewWorkspaceScreen(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID);
    }

    public boolean addExtraEmptyScreen() {
        if (this.mWorkspaceScreens.containsKey(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID)) {
            return false;
        }
        insertNewWorkspaceScreen(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID);
        return true;
    }

    private void convertFinalScreenToEmptyScreenIfNecessary() {
        if (this.mLauncher.isWorkspaceLoading() || hasExtraEmptyScreen() || this.mScreenOrder.size() == 0) {
            return;
        }
        int i = this.mScreenOrder.get(r0.size() - 1);
        CellLayout cellLayout = this.mWorkspaceScreens.get(i);
        if (cellLayout.getShortcutsAndWidgets().getChildCount() != 0 || cellLayout.isDropPending()) {
            return;
        }
        this.mWorkspaceScreens.remove(i);
        this.mScreenOrder.removeValue(i);
        this.mWorkspaceScreens.put(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID, cellLayout);
        this.mScreenOrder.add(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID);
    }

    public void removeExtraEmptyScreen(final boolean animate, boolean stripEmptyScreens) {
        removeExtraEmptyScreenDelayed(animate, null, 0, stripEmptyScreens);
    }

    public void removeExtraEmptyScreenDelayed(final boolean animate, final Runnable onComplete, final int delay, final boolean stripEmptyScreens) {
        if (this.mLauncher.isWorkspaceLoading()) {
            return;
        }
        if (delay > 0) {
            postDelayed(new Runnable() { // from class: com.android.launcher3.Workspace.1
                @Override // java.lang.Runnable
                public void run() {
                    Workspace.this.removeExtraEmptyScreenDelayed(animate, onComplete, 0, stripEmptyScreens);
                }
            }, delay);
            return;
        }
        convertFinalScreenToEmptyScreenIfNecessary();
        if (!hasExtraEmptyScreen()) {
            if (stripEmptyScreens) {
                stripEmptyScreens();
            }
            if (onComplete != null) {
                onComplete.run();
                return;
            }
            return;
        }
        if (getNextPage() == this.mScreenOrder.indexOf(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID)) {
            snapToPage(getNextPage() - 1, 400);
            fadeAndRemoveEmptyScreen(400, 150, onComplete, stripEmptyScreens);
        } else {
            snapToPage(getNextPage(), 0);
            fadeAndRemoveEmptyScreen(0, 150, onComplete, stripEmptyScreens);
        }
    }

    private void fadeAndRemoveEmptyScreen(int delay, int duration, final Runnable onComplete, final boolean stripEmptyScreens) {
        final CellLayout cellLayout = this.mWorkspaceScreens.get(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID);
        this.mRemoveEmptyScreenRunnable = new Runnable() { // from class: com.android.launcher3.Workspace.2
            @Override // java.lang.Runnable
            public void run() {
                if (Workspace.this.hasExtraEmptyScreen()) {
                    Workspace.this.mWorkspaceScreens.remove(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID);
                    Workspace.this.mScreenOrder.removeValue(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID);
                    Workspace.this.removeView(cellLayout);
                    if (stripEmptyScreens) {
                        Workspace.this.stripEmptyScreens();
                    }
                    Workspace.this.showPageIndicatorAtCurrentScroll();
                }
            }
        };
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(cellLayout, (Property<CellLayout, Float>) ALPHA, 0.0f);
        objectAnimatorOfFloat.setDuration(duration);
        objectAnimatorOfFloat.setStartDelay(delay);
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.Workspace.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                if (Workspace.this.mRemoveEmptyScreenRunnable != null) {
                    Workspace.this.mRemoveEmptyScreenRunnable.run();
                }
                Runnable runnable = onComplete;
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
        objectAnimatorOfFloat.start();
    }

    public boolean hasExtraEmptyScreen() {
        return this.mWorkspaceScreens.containsKey(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID) && getChildCount() > 1;
    }

    public int commitExtraEmptyScreen() {
        if (this.mLauncher.isWorkspaceLoading()) {
            return -1;
        }
        CellLayout cellLayout = this.mWorkspaceScreens.get(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID);
        this.mWorkspaceScreens.remove(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID);
        this.mScreenOrder.removeValue(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID);
        int i = LauncherSettings.Settings.call(getContext().getContentResolver(), LauncherSettings.Settings.METHOD_NEW_SCREEN_ID).getInt("value");
        this.mWorkspaceScreens.put(i, cellLayout);
        this.mScreenOrder.add(i);
        return i;
    }

    @Override // com.android.launcher3.WorkspaceLayoutManager
    public Hotseat getHotseat() {
        return this.mLauncher.getHotseat();
    }

    @Override // com.android.launcher3.WorkspaceLayoutManager
    public void onAddDropTarget(DropTarget target) {
        this.mDragController.addDropTarget(target);
    }

    @Override // com.android.launcher3.WorkspaceLayoutManager
    public CellLayout getScreenWithId(int screenId) {
        return this.mWorkspaceScreens.get(screenId);
    }

    public int getIdForScreen(CellLayout layout) {
        int iIndexOfValue = this.mWorkspaceScreens.indexOfValue(layout);
        if (iIndexOfValue != -1) {
            return this.mWorkspaceScreens.keyAt(iIndexOfValue);
        }
        return -1;
    }

    public int getPageIndexForScreenId(int screenId) {
        return indexOfChild(this.mWorkspaceScreens.get(screenId));
    }

    public int getScreenIdForPageIndex(int index) {
        if (index < 0 || index >= this.mScreenOrder.size()) {
            return -1;
        }
        return this.mScreenOrder.get(index);
    }

    public IntArray getScreenOrder() {
        return this.mScreenOrder;
    }

    public void stripEmptyScreens() {
        if (this.mLauncher.isWorkspaceLoading()) {
            return;
        }
        if (isPageInTransition()) {
            this.mStripScreensOnPageStopMoving = true;
            return;
        }
        int nextPage = getNextPage();
        IntArray intArray = new IntArray();
        int size = this.mWorkspaceScreens.size();
        for (int i = 0; i < size; i++) {
            int iKeyAt = this.mWorkspaceScreens.keyAt(i);
            if (this.mWorkspaceScreens.valueAt(i).getShortcutsAndWidgets().getChildCount() == 0) {
                intArray.add(iKeyAt);
            }
        }
        boolean zIsInAccessibleDrag = this.mLauncher.getAccessibilityDelegate().isInAccessibleDrag();
        int i2 = 0;
        for (int i3 = 0; i3 < intArray.size(); i3++) {
            int i4 = intArray.get(i3);
            CellLayout cellLayout = this.mWorkspaceScreens.get(i4);
            this.mWorkspaceScreens.remove(i4);
            this.mScreenOrder.removeValue(i4);
            if (getChildCount() > 1) {
                if (indexOfChild(cellLayout) < nextPage) {
                    i2++;
                }
                if (zIsInAccessibleDrag) {
                    cellLayout.enableAccessibleDrag(false, 2);
                }
                removeView(cellLayout);
            } else {
                this.mRemoveEmptyScreenRunnable = null;
                this.mWorkspaceScreens.put(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID, cellLayout);
                this.mScreenOrder.add(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID);
            }
        }
        if (i2 >= 0) {
            setCurrentPage(nextPage - i2);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        return shouldConsumeTouch(v);
    }

    private boolean shouldConsumeTouch(View v) {
        return (workspaceIconsCanBeDragged() && (workspaceInModalState() || indexOfChild(v) == this.mCurrentPage)) ? false : true;
    }

    public boolean isSwitchingState() {
        return this.mIsSwitchingState;
    }

    public boolean isFinishedSwitchingState() {
        return !this.mIsSwitchingState || this.mTransitionProgress > 0.5f;
    }

    @Override // com.android.launcher3.PagedView, android.view.ViewGroup, android.view.View
    public boolean dispatchUnhandledMove(View focused, int direction) {
        if (workspaceInModalState() || !isFinishedSwitchingState()) {
            return false;
        }
        return super.dispatchUnhandledMove(focused, direction);
    }

    @Override // com.android.launcher3.PagedView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked() == 0) {
            this.mXDown = ev.getX();
            this.mYDown = ev.getY();
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override // com.android.launcher3.PagedView
    protected void determineScrollingStart(MotionEvent ev) {
        if (isFinishedSwitchingState()) {
            float fAbs = Math.abs(ev.getX() - this.mXDown);
            float fAbs2 = Math.abs(ev.getY() - this.mYDown);
            if (Float.compare(fAbs, 0.0f) == 0) {
                return;
            }
            float fAtan = (float) Math.atan(fAbs2 / fAbs);
            if (fAbs > this.mTouchSlop || fAbs2 > this.mTouchSlop) {
                cancelCurrentPageLongPress();
            }
            if (fAtan > MAX_SWIPE_ANGLE) {
                return;
            }
            if (fAtan > START_DAMPING_TOUCH_SLOP_ANGLE) {
                super.determineScrollingStart(ev, (((float) Math.sqrt((fAtan - START_DAMPING_TOUCH_SLOP_ANGLE) / START_DAMPING_TOUCH_SLOP_ANGLE)) * TOUCH_SLOP_DAMPING_FACTOR) + 1.0f);
            } else {
                super.determineScrollingStart(ev);
            }
        }
    }

    @Override // com.android.launcher3.PagedView
    protected void onPageBeginTransition() {
        super.onPageBeginTransition();
        updateChildrenLayersEnabled();
    }

    @Override // com.android.launcher3.PagedView
    protected void onPageEndTransition() {
        super.onPageEndTransition();
        updateChildrenLayersEnabled();
        if (this.mDragController.isDragging() && workspaceInModalState()) {
            this.mDragController.forceTouchMove();
        }
        if (this.mStripScreensOnPageStopMoving) {
            stripEmptyScreens();
            this.mStripScreensOnPageStopMoving = false;
        }
    }

    @Override // com.android.launcher3.PagedView
    protected void onScrollInteractionBegin() {
        super.onScrollInteractionBegin();
        this.mScrollInteractionBegan = true;
    }

    @Override // com.android.launcher3.PagedView
    protected void onScrollInteractionEnd() {
        super.onScrollInteractionEnd();
        this.mScrollInteractionBegan = false;
        if (this.mStartedSendingScrollEvents) {
            this.mStartedSendingScrollEvents = false;
            this.mLauncherOverlay.onScrollInteractionEnd();
        }
    }

    public void setLauncherOverlay(Launcher.LauncherOverlay overlay) {
        this.mLauncherOverlay = overlay;
        this.mStartedSendingScrollEvents = false;
        onOverlayScrollChanged(0.0f);
    }

    private boolean isScrollingOverlay() {
        return this.mLauncherOverlay != null && ((this.mIsRtl && getUnboundedScrollX() > this.mMaxScrollX) || (!this.mIsRtl && getUnboundedScrollX() < this.mMinScrollX));
    }

    @Override // com.android.launcher3.PagedView
    protected void snapToDestination() {
        if (isScrollingOverlay()) {
            this.mWasInOverscroll = false;
            snapToPageImmediately(0);
        } else {
            super.snapToDestination();
        }
    }

    @Override // android.view.View
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (!(this.mIsSwitchingState || (getLayoutTransition() != null && getLayoutTransition().isRunning()))) {
            showPageIndicatorAtCurrentScroll();
        }
        updatePageAlphaValues();
        enableHwLayersOnVisiblePages();
    }

    public void showPageIndicatorAtCurrentScroll() {
        if (this.mPageIndicator != 0) {
            ((PageIndicatorDots) this.mPageIndicator).setScroll(getScrollX(), computeMaxScrollX());
        }
    }

    @Override // com.android.launcher3.PagedView
    protected void overScroll(int amount) {
        boolean z = false;
        boolean z2 = (this.mLauncherOverlay == null || this.mScroller.isSpringing() || ((amount > 0 || this.mIsRtl) && (amount < 0 || !this.mIsRtl))) ? false : true;
        if (this.mLauncherOverlay != null && this.mLastOverlayScroll != 0.0f && ((amount >= 0 && !this.mIsRtl) || (amount <= 0 && this.mIsRtl))) {
            z = true;
        }
        if (z2) {
            if (!this.mStartedSendingScrollEvents && this.mScrollInteractionBegan) {
                this.mStartedSendingScrollEvents = true;
                this.mLauncherOverlay.onScrollInteractionBegin();
            }
            float fAbs = Math.abs(amount / getMeasuredWidth());
            this.mLastOverlayScroll = fAbs;
            this.mLauncherOverlay.onScrollChange(fAbs, this.mIsRtl);
        } else {
            dampedOverScroll(amount);
        }
        if (z) {
            this.mLauncherOverlay.onScrollChange(0.0f, this.mIsRtl);
        }
    }

    @Override // com.android.launcher3.PagedView
    protected boolean shouldFlingForVelocity(int velocityX) {
        return Float.compare(Math.abs(this.mOverlayTranslation), 0.0f) == 0 && super.shouldFlingForVelocity(velocityX);
    }

    public void onOverlayScrollChanged(float scroll) {
        if (Float.compare(scroll, 1.0f) == 0) {
            if (!this.mOverlayShown) {
                this.mLauncher.getUserEventDispatcher().logActionOnContainer(3, 3, 1, 0);
            }
            this.mOverlayShown = true;
        } else if (Float.compare(scroll, 0.0f) == 0) {
            if (this.mOverlayShown) {
                this.mLauncher.getUserEventDispatcher().logActionOnContainer(3, 4, 1, -1);
            } else if (Float.compare(this.mOverlayTranslation, 0.0f) != 0) {
                announcePageForAccessibility();
            }
            this.mOverlayShown = false;
            tryRunOverlayCallback();
        }
        float fMin = Math.min(1.0f, Math.max(scroll - 0.0f, 0.0f) / 1.0f);
        float interpolation = 1.0f - Interpolators.DEACCEL_3.getInterpolation(fMin);
        float measuredWidth = this.mLauncher.getDragLayer().getMeasuredWidth() * fMin;
        if (this.mIsRtl) {
            measuredWidth = -measuredWidth;
        }
        this.mOverlayTranslation = measuredWidth;
        this.mLauncher.getDragLayer().setTranslationX(measuredWidth);
        this.mLauncher.getDragLayer().getAlphaProperty(0).setValue(interpolation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean tryRunOverlayCallback() {
        if (this.mOnOverlayHiddenCallback == null) {
            return true;
        }
        if (this.mOverlayShown || !hasWindowFocus()) {
            return false;
        }
        this.mOnOverlayHiddenCallback.run();
        this.mOnOverlayHiddenCallback = null;
        return true;
    }

    public boolean runOnOverlayHidden(final Runnable callback) {
        final Runnable runnable = this.mOnOverlayHiddenCallback;
        if (runnable == null) {
            this.mOnOverlayHiddenCallback = callback;
        } else {
            this.mOnOverlayHiddenCallback = new Runnable() { // from class: com.android.launcher3.-$$Lambda$Workspace$uxwu2zibulY53MFIyKTn-lRBFIc
                @Override // java.lang.Runnable
                public final void run() {
                    Workspace.lambda$runOnOverlayHidden$0(runnable, callback);
                }
            };
        }
        if (tryRunOverlayCallback()) {
            return false;
        }
        final ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            return true;
        }
        viewTreeObserver.addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() { // from class: com.android.launcher3.Workspace.4
            @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
            public void onWindowFocusChanged(boolean hasFocus) {
                if (Workspace.this.tryRunOverlayCallback() && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeOnWindowFocusChangeListener(this);
                }
            }
        });
        return true;
    }

    static /* synthetic */ void lambda$runOnOverlayHidden$0(Runnable runnable, Runnable runnable2) {
        runnable.run();
        runnable2.run();
    }

    @Override // com.android.launcher3.PagedView
    protected void notifyPageSwitchListener(int prevPage) {
        super.notifyPageSwitchListener(prevPage);
        if (prevPage != this.mCurrentPage) {
            this.mLauncher.getUserEventDispatcher().logActionOnContainer(3, prevPage < this.mCurrentPage ? 4 : 3, 1, prevPage);
        }
    }

    protected void setWallpaperDimension() {
        Utilities.THREAD_POOL_EXECUTOR.execute(new Runnable() { // from class: com.android.launcher3.Workspace.5
            @Override // java.lang.Runnable
            public void run() {
                Point point = LauncherAppState.getIDP(Workspace.this.getContext()).defaultWallpaperSize;
                if (point.x == Workspace.this.mWallpaperManager.getDesiredMinimumWidth() && point.y == Workspace.this.mWallpaperManager.getDesiredMinimumHeight()) {
                    return;
                }
                Workspace.this.mWallpaperManager.suggestDesiredDimensions(point.x, point.y);
            }
        });
    }

    public void lockWallpaperToDefaultPage() {
        this.mWallpaperOffset.setLockToDefaultPage(true);
    }

    public void unlockWallpaperFromDefaultPageOnNextLayout() {
        if (this.mWallpaperOffset.isLockedToDefaultPage()) {
            this.mUnlockWallpaperFromDefaultPageOnLayout = true;
            requestLayout();
        }
    }

    @Override // com.android.launcher3.PagedView, android.view.View
    public void computeScroll() {
        super.computeScroll();
        this.mWallpaperOffset.syncWithScroll();
    }

    public void computeScrollWithoutInvalidation() {
        computeScrollHelper(false);
    }

    @Override // com.android.launcher3.PagedView
    protected void determineScrollingStart(MotionEvent ev, float touchSlopScale) {
        if (isSwitchingState()) {
            return;
        }
        super.determineScrollingStart(ev, touchSlopScale);
    }

    @Override // android.view.View
    public void announceForAccessibility(CharSequence text) {
        if (this.mLauncher.isInState(LauncherState.ALL_APPS)) {
            return;
        }
        super.announceForAccessibility(text);
    }

    private void updatePageAlphaValues() {
        if (workspaceInModalState() || this.mIsSwitchingState || this.mDragController.isDragging()) {
            return;
        }
        int scrollX = getScrollX() + (getMeasuredWidth() / 2);
        for (int i = 0; i < getChildCount(); i++) {
            CellLayout cellLayout = (CellLayout) getChildAt(i);
            if (cellLayout != null) {
                float fAbs = 1.0f - Math.abs(getScrollProgress(scrollX, cellLayout, i));
                if (this.mWorkspaceFadeInAdjacentScreens) {
                    cellLayout.getShortcutsAndWidgets().setAlpha(fAbs);
                } else {
                    cellLayout.getShortcutsAndWidgets().setImportantForAccessibility(fAbs > 0.0f ? 0 : 4);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        IBinder windowToken = getWindowToken();
        this.mWallpaperOffset.setWindowToken(windowToken);
        computeScroll();
        this.mDragController.setWindowToken(windowToken);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mWallpaperOffset.setWindowToken(null);
    }

    @Override // com.android.launcher3.PagedView, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (this.mUnlockWallpaperFromDefaultPageOnLayout) {
            this.mWallpaperOffset.setLockToDefaultPage(false);
            this.mUnlockWallpaperFromDefaultPageOnLayout = false;
        }
        if (this.mFirstLayout && this.mCurrentPage >= 0 && this.mCurrentPage < getChildCount()) {
            this.mWallpaperOffset.syncWithScroll();
            this.mWallpaperOffset.jumpToFinal();
        }
        super.onLayout(changed, left, top, right, bottom);
        updatePageAlphaValues();
    }

    @Override // android.view.ViewGroup
    public int getDescendantFocusability() {
        if (workspaceInModalState()) {
            return 393216;
        }
        return super.getDescendantFocusability();
    }

    private boolean workspaceInModalState() {
        return !this.mLauncher.isInState(LauncherState.NORMAL);
    }

    public boolean workspaceIconsCanBeDragged() {
        return this.mLauncher.getStateManager().getState().workspaceIconsCanBeDragged;
    }

    private void updateChildrenLayersEnabled() {
        boolean z = this.mIsSwitchingState || isPageInTransition();
        if (z != this.mChildrenLayersEnabled) {
            this.mChildrenLayersEnabled = z;
            if (z) {
                enableHwLayersOnVisiblePages();
                return;
            }
            for (int i = 0; i < getPageCount(); i++) {
                ((CellLayout) getChildAt(i)).enableHardwareLayer(false);
            }
        }
    }

    private void enableHwLayersOnVisiblePages() {
        if (this.mChildrenLayersEnabled) {
            int childCount = getChildCount();
            int[] visibleChildrenRange = getVisibleChildrenRange();
            int iBoundToRange = visibleChildrenRange[0];
            int iBoundToRange2 = visibleChildrenRange[1];
            if (this.mForceDrawAdjacentPages) {
                iBoundToRange = Utilities.boundToRange(getCurrentPage() - 1, 0, iBoundToRange2);
                iBoundToRange2 = Utilities.boundToRange(getCurrentPage() + 1, iBoundToRange, getPageCount() - 1);
            }
            if (iBoundToRange == iBoundToRange2) {
                if (iBoundToRange2 < childCount - 1) {
                    iBoundToRange2++;
                } else if (iBoundToRange > 0) {
                    iBoundToRange--;
                }
            }
            int i = 0;
            while (i < childCount) {
                ((CellLayout) getPageAt(i)).enableHardwareLayer(iBoundToRange <= i && i <= iBoundToRange2);
                i++;
            }
        }
    }

    public void onWallpaperTap(MotionEvent ev) {
        int[] iArr = this.mTempXY;
        getLocationOnScreen(iArr);
        int actionIndex = ev.getActionIndex();
        iArr[0] = iArr[0] + ((int) ev.getX(actionIndex));
        iArr[1] = iArr[1] + ((int) ev.getY(actionIndex));
        this.mWallpaperManager.sendWallpaperCommand(getWindowToken(), ev.getAction() == 1 ? WallpaperManager.COMMAND_TAP : WallpaperManager.COMMAND_SECONDARY_TAP, iArr[0], iArr[1], 0, null);
    }

    public void prepareDragWithProvider(DragPreviewProvider outlineProvider) {
        this.mOutlineProvider = outlineProvider;
    }

    public void snapToPageFromOverView(int whichPage) {
        snapToPage(whichPage, 250, Interpolators.ZOOM_IN);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStartStateTransition(LauncherState state) {
        this.mIsSwitchingState = true;
        this.mTransitionProgress = 0.0f;
        updateChildrenLayersEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onEndStateTransition() {
        this.mIsSwitchingState = false;
        this.mForceDrawAdjacentPages = false;
        this.mTransitionProgress = 1.0f;
        updateChildrenLayersEnabled();
        updateAccessibilityFlags();
    }

    @Override // com.android.launcher3.LauncherStateManager.StateHandler
    public void setState(LauncherState toState) {
        onStartStateTransition(toState);
        this.mStateTransitionAnimation.setState(toState);
        onEndStateTransition();
    }

    @Override // com.android.launcher3.LauncherStateManager.StateHandler
    public void setStateWithAnimation(LauncherState toState, AnimatorSetBuilder builder, LauncherStateManager.AnimationConfig config) {
        StateTransitionListener stateTransitionListener = new StateTransitionListener(toState);
        this.mStateTransitionAnimation.setStateWithAnimation(toState, builder, config);
        if (toState.hasMultipleVisiblePages) {
            this.mForceDrawAdjacentPages = true;
        }
        invalidate();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addUpdateListener(stateTransitionListener);
        valueAnimatorOfFloat.setDuration(config.duration);
        valueAnimatorOfFloat.addListener(stateTransitionListener);
        builder.play(valueAnimatorOfFloat);
    }

    public WorkspaceStateTransitionAnimation getStateTransitionAnimation() {
        return this.mStateTransitionAnimation;
    }

    public void updateAccessibilityFlags() {
        int i = this.mLauncher.getStateManager().getState().workspaceAccessibilityFlag;
        if (this.mLauncher.getAccessibilityDelegate().isInAccessibleDrag()) {
            return;
        }
        int pageCount = getPageCount();
        for (int i2 = 0; i2 < pageCount; i2++) {
            updateAccessibilityFlags(i, (CellLayout) getPageAt(i2));
        }
        setImportantForAccessibility(i);
    }

    @Override // android.view.View
    public AccessibilityNodeInfo createAccessibilityNodeInfo() {
        if (getImportantForAccessibility() == 4) {
            return null;
        }
        return super.createAccessibilityNodeInfo();
    }

    private void updateAccessibilityFlags(int accessibilityFlag, CellLayout page) {
        page.setImportantForAccessibility(2);
        page.getShortcutsAndWidgets().setImportantForAccessibility(accessibilityFlag);
        page.setContentDescription(null);
        page.setAccessibilityDelegate(null);
    }

    public void startDrag(CellLayout.CellInfo cellInfo, DragOptions options) {
        View view = cellInfo.cell;
        this.mDragInfo = cellInfo;
        view.setVisibility(4);
        if (options.isAccessibleDrag) {
            this.mDragController.addDragListener(new AccessibleDragListenerAdapter(this, 2) { // from class: com.android.launcher3.Workspace.6
                @Override // com.android.launcher3.accessibility.AccessibleDragListenerAdapter
                protected void enableAccessibleDrag(boolean enable) {
                    super.enableAccessibleDrag(enable);
                    setEnableForLayout(Workspace.this.mLauncher.getHotseat(), enable);
                }
            });
        }
        beginDragShared(view, this, options);
    }

    public void beginDragShared(View child, DragSource source, DragOptions options) {
        Object tag = child.getTag();
        if (!(tag instanceof ItemInfo)) {
            throw new IllegalStateException("Drag started with a view that has no tag set. This will cause a crash (issue 11627249) down the line. View: " + child + "  tag: " + child.getTag());
        }
        beginDragShared(child, source, (ItemInfo) tag, new DragPreviewProvider(child), options);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.android.launcher3.dragndrop.DragView beginDragShared(android.view.View r16, com.android.launcher3.DragSource r17, com.android.launcher3.ItemInfo r18, com.android.launcher3.graphics.DragPreviewProvider r19, com.android.launcher3.dragndrop.DragOptions r20) {
        /*
            Method dump skipped, instruction units count: 219
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.Workspace.beginDragShared(android.view.View, com.android.launcher3.DragSource, com.android.launcher3.ItemInfo, com.android.launcher3.graphics.DragPreviewProvider, com.android.launcher3.dragndrop.DragOptions):com.android.launcher3.dragndrop.DragView");
    }

    private boolean transitionStateShouldAllowDrop() {
        return (!isSwitchingState() || this.mTransitionProgress > 0.25f) && workspaceIconsCanBeDragged();
    }

    @Override // com.android.launcher3.DropTarget
    public boolean acceptDrop(DropTarget.DragObject d) {
        CellLayout cellLayout;
        int i;
        int i2;
        int i3;
        int i4;
        CellLayout cellLayout2 = this.mDropToLayout;
        if (d.dragSource == this) {
            cellLayout = cellLayout2;
        } else {
            if (cellLayout2 == null || !transitionStateShouldAllowDrop()) {
                return false;
            }
            float[] visualCenter = d.getVisualCenter(this.mDragViewVisualCenter);
            this.mDragViewVisualCenter = visualCenter;
            mapPointFromDropLayout(cellLayout2, visualCenter);
            CellLayout.CellInfo cellInfo = this.mDragInfo;
            if (cellInfo != null) {
                int i5 = cellInfo.spanX;
                i2 = cellInfo.spanY;
                i = i5;
            } else {
                i = d.dragInfo.spanX;
                i2 = d.dragInfo.spanY;
            }
            if (d.dragInfo instanceof PendingAddWidgetInfo) {
                i3 = ((PendingAddWidgetInfo) d.dragInfo).minSpanX;
                i4 = ((PendingAddWidgetInfo) d.dragInfo).minSpanY;
            } else {
                i3 = i;
                i4 = i2;
            }
            float[] fArr = this.mDragViewVisualCenter;
            int[] iArrFindNearestArea = findNearestArea((int) fArr[0], (int) fArr[1], i3, i4, cellLayout2, this.mTargetCell);
            this.mTargetCell = iArrFindNearestArea;
            float[] fArr2 = this.mDragViewVisualCenter;
            float distanceFromCell = cellLayout2.getDistanceFromCell(fArr2[0], fArr2[1], iArrFindNearestArea);
            if (this.mCreateUserFolderOnDrop && willCreateUserFolder(d.dragInfo, cellLayout2, this.mTargetCell, distanceFromCell, true)) {
                return true;
            }
            if (this.mAddToExistingFolderOnDrop && willAddToExistingUserFolder(d.dragInfo, cellLayout2, this.mTargetCell, distanceFromCell)) {
                return true;
            }
            float[] fArr3 = this.mDragViewVisualCenter;
            cellLayout = cellLayout2;
            int[] iArrPerformReorder = cellLayout2.performReorder((int) fArr3[0], (int) fArr3[1], i3, i4, i, i2, null, this.mTargetCell, new int[2], 4);
            this.mTargetCell = iArrPerformReorder;
            if (!(iArrPerformReorder[0] >= 0 && iArrPerformReorder[1] >= 0)) {
                onNoCellFound(cellLayout);
                return false;
            }
        }
        if (getIdForScreen(cellLayout) == -201) {
            commitExtraEmptyScreen();
        }
        return true;
    }

    boolean willCreateUserFolder(ItemInfo info, CellLayout target, int[] targetCell, float distance, boolean considerTimeout) {
        if (distance > this.mMaxDistanceForFolderCreation) {
            return false;
        }
        return willCreateUserFolder(info, target.getChildAt(targetCell[0], targetCell[1]), considerTimeout);
    }

    boolean willCreateUserFolder(ItemInfo info, View dropOverView, boolean considerTimeout) {
        if (dropOverView != null) {
            CellLayout.LayoutParams layoutParams = (CellLayout.LayoutParams) dropOverView.getLayoutParams();
            if (layoutParams.useTmpCoords && (layoutParams.tmpCellX != layoutParams.cellX || layoutParams.tmpCellY != layoutParams.cellY)) {
                return false;
            }
        }
        CellLayout.CellInfo cellInfo = this.mDragInfo;
        boolean z = cellInfo != null && dropOverView == cellInfo.cell;
        if (dropOverView == null || z) {
            return false;
        }
        if (!considerTimeout || this.mCreateUserFolderOnDrop) {
            return (dropOverView.getTag() instanceof WorkspaceItemInfo) && (info.itemType == 0 || info.itemType == 1 || info.itemType == 6);
        }
        return false;
    }

    boolean willAddToExistingUserFolder(ItemInfo dragInfo, CellLayout target, int[] targetCell, float distance) {
        if (distance > this.mMaxDistanceForFolderCreation) {
            return false;
        }
        return willAddToExistingUserFolder(dragInfo, target.getChildAt(targetCell[0], targetCell[1]));
    }

    boolean willAddToExistingUserFolder(ItemInfo dragInfo, View dropOverView) {
        if (dropOverView != null) {
            CellLayout.LayoutParams layoutParams = (CellLayout.LayoutParams) dropOverView.getLayoutParams();
            if (layoutParams.useTmpCoords && (layoutParams.tmpCellX != layoutParams.cellX || layoutParams.tmpCellY != layoutParams.cellY)) {
                return false;
            }
        }
        return (dropOverView instanceof FolderIcon) && ((FolderIcon) dropOverView).acceptDrop(dragInfo);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    boolean createUserFolderIfNecessary(android.view.View r15, int r16, com.android.launcher3.CellLayout r17, int[] r18, float r19, boolean r20, com.android.launcher3.dragndrop.DragView r21) {
        /*
            r14 = this;
            r0 = r14
            r2 = r17
            float r1 = r0.mMaxDistanceForFolderCreation
            int r1 = (r19 > r1 ? 1 : (r19 == r1 ? 0 : -1))
            r7 = 0
            if (r1 <= 0) goto Lb
            return r7
        Lb:
            r1 = r18[r7]
            r8 = 1
            r3 = r18[r8]
            android.view.View r9 = r2.getChildAt(r1, r3)
            com.android.launcher3.CellLayout$CellInfo r1 = r0.mDragInfo
            if (r1 == 0) goto L32
            android.view.View r1 = r1.cell
            com.android.launcher3.CellLayout r1 = r14.getParentCellLayoutForView(r1)
            com.android.launcher3.CellLayout$CellInfo r3 = r0.mDragInfo
            int r3 = r3.cellX
            r4 = r18[r7]
            if (r3 != r4) goto L32
            com.android.launcher3.CellLayout$CellInfo r3 = r0.mDragInfo
            int r3 = r3.cellY
            r4 = r18[r8]
            if (r3 != r4) goto L32
            if (r1 != r2) goto L32
            r1 = r8
            goto L33
        L32:
            r1 = r7
        L33:
            if (r9 == 0) goto Lc6
            if (r1 != 0) goto Lc6
            boolean r1 = r0.mCreateUserFolderOnDrop
            if (r1 != 0) goto L3d
            goto Lc6
        L3d:
            r0.mCreateUserFolderOnDrop = r7
            int r4 = r14.getIdForScreen(r2)
            java.lang.Object r1 = r9.getTag()
            boolean r1 = r1 instanceof com.android.launcher3.WorkspaceItemInfo
            java.lang.Object r3 = r15.getTag()
            boolean r3 = r3 instanceof com.android.launcher3.WorkspaceItemInfo
            if (r1 == 0) goto Lc6
            if (r3 == 0) goto Lc6
            java.lang.Object r1 = r15.getTag()
            r10 = r1
            com.android.launcher3.WorkspaceItemInfo r10 = (com.android.launcher3.WorkspaceItemInfo) r10
            java.lang.Object r1 = r9.getTag()
            r11 = r1
            com.android.launcher3.WorkspaceItemInfo r11 = (com.android.launcher3.WorkspaceItemInfo) r11
            if (r20 != 0) goto L72
            com.android.launcher3.CellLayout$CellInfo r1 = r0.mDragInfo
            android.view.View r1 = r1.cell
            com.android.launcher3.CellLayout r1 = r14.getParentCellLayoutForView(r1)
            com.android.launcher3.CellLayout$CellInfo r3 = r0.mDragInfo
            android.view.View r3 = r3.cell
            r1.removeView(r3)
        L72:
            android.graphics.Rect r12 = new android.graphics.Rect
            r12.<init>()
            com.android.launcher3.Launcher r1 = r0.mLauncher
            com.android.launcher3.dragndrop.DragLayer r1 = r1.getDragLayer()
            float r13 = r1.getDescendantRectRelativeToSelf(r9, r12)
            r2.removeView(r9)
            com.android.launcher3.Launcher r1 = r0.mLauncher
            r5 = r18[r7]
            r6 = r18[r8]
            r2 = r17
            r3 = r16
            com.android.launcher3.folder.FolderIcon r1 = r1.addFolder(r2, r3, r4, r5, r6)
            r2 = -1
            r11.cellX = r2
            r11.cellY = r2
            r10.cellX = r2
            r10.cellY = r2
            if (r21 == 0) goto L9e
            r7 = r8
        L9e:
            if (r7 == 0) goto Lbc
            com.android.launcher3.folder.PreviewBackground r2 = r0.mFolderCreateBg
            r1.setFolderBackground(r2)
            com.android.launcher3.folder.PreviewBackground r2 = new com.android.launcher3.folder.PreviewBackground
            r2.<init>()
            r0.mFolderCreateBg = r2
            r14 = r1
            r15 = r11
            r16 = r9
            r17 = r10
            r18 = r21
            r19 = r12
            r20 = r13
            r14.performCreateAnimation(r15, r16, r17, r18, r19, r20)
            goto Lc5
        Lbc:
            r1.prepareCreateAnimation(r9)
            r1.addItem(r11)
            r1.addItem(r10)
        Lc5:
            return r8
        Lc6:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.Workspace.createUserFolderIfNecessary(android.view.View, int, com.android.launcher3.CellLayout, int[], float, boolean, com.android.launcher3.dragndrop.DragView):boolean");
    }

    boolean addToExistingFolderIfNecessary(View newView, CellLayout target, int[] targetCell, float distance, DropTarget.DragObject d, boolean external) {
        if (distance > this.mMaxDistanceForFolderCreation) {
            return false;
        }
        View childAt = target.getChildAt(targetCell[0], targetCell[1]);
        if (!this.mAddToExistingFolderOnDrop) {
            return false;
        }
        this.mAddToExistingFolderOnDrop = false;
        if (childAt instanceof FolderIcon) {
            FolderIcon folderIcon = (FolderIcon) childAt;
            if (folderIcon.acceptDrop(d.dragInfo)) {
                folderIcon.onDrop(d, false);
                if (!external) {
                    getParentCellLayoutForView(this.mDragInfo.cell).removeView(this.mDragInfo.cell);
                }
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:114:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x034b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:151:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01a0  */
    /* JADX WARN: Type inference failed for: r0v16, types: [com.android.launcher3.dragndrop.DragController] */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r14v1, types: [com.android.launcher3.CellLayout] */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.android.launcher3.dragndrop.DragLayer] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v54 */
    /* JADX WARN: Type inference failed for: r2v55 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r38v0, types: [android.view.View, com.android.launcher3.Workspace] */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [int] */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r6v1, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4, types: [boolean, int] */
    @Override // com.android.launcher3.DropTarget
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onDrop(com.android.launcher3.DropTarget.DragObject r39, com.android.launcher3.dragndrop.DragOptions r40) {
        /*
            Method dump skipped, instruction units count: 854
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.Workspace.onDrop(com.android.launcher3.DropTarget$DragObject, com.android.launcher3.dragndrop.DragOptions):void");
    }

    public void onNoCellFound(View dropTargetLayout) {
        int i = this.mLauncher.isHotseatLayout(dropTargetLayout) ? R.string.hotseat_out_of_space : R.string.out_of_space;
        Launcher launcher = this.mLauncher;
        Toast.makeText(launcher, launcher.getString(i), 0).show();
    }

    public void getPageAreaRelativeToDragLayer(Rect outArea) {
        CellLayout cellLayout = (CellLayout) getChildAt(getNextPage());
        if (cellLayout == null) {
            return;
        }
        this.mLauncher.getDragLayer().getDescendantRectRelativeToSelf(cellLayout.getShortcutsAndWidgets(), outArea);
    }

    @Override // com.android.launcher3.DropTarget
    public void onDragEnter(DropTarget.DragObject d) {
        this.mCreateUserFolderOnDrop = false;
        this.mAddToExistingFolderOnDrop = false;
        this.mDropToLayout = null;
        float[] visualCenter = d.getVisualCenter(this.mDragViewVisualCenter);
        this.mDragViewVisualCenter = visualCenter;
        setDropLayoutForDragObject(d, visualCenter[0], visualCenter[1]);
    }

    @Override // com.android.launcher3.DropTarget
    public void onDragExit(DropTarget.DragObject d) {
        this.mDropToLayout = this.mDragTargetLayout;
        int i = this.mDragMode;
        if (i == 1) {
            this.mCreateUserFolderOnDrop = true;
        } else if (i == 2) {
            this.mAddToExistingFolderOnDrop = true;
        }
        setCurrentDropLayout(null);
        setCurrentDragOverlappingLayout(null);
        this.mSpringLoadedDragController.cancel();
    }

    private void enforceDragParity(String event, int update, int expectedValue) {
        enforceDragParity(this, event, update, expectedValue);
        for (int i = 0; i < getChildCount(); i++) {
            enforceDragParity(getChildAt(i), event, update, expectedValue);
        }
    }

    private void enforceDragParity(View v, String event, int update, int expectedValue) {
        Object tag = v.getTag(R.id.drag_event_parity);
        int iIntValue = (tag == null ? 0 : ((Integer) tag).intValue()) + update;
        v.setTag(R.id.drag_event_parity, Integer.valueOf(iIntValue));
        if (iIntValue != expectedValue) {
            Log.e(WorkspaceLayoutManager.TAG, event + ": Drag contract violated: " + iIntValue);
        }
    }

    void setCurrentDropLayout(CellLayout layout) {
        CellLayout cellLayout = this.mDragTargetLayout;
        if (cellLayout != null) {
            cellLayout.revertTempState();
            this.mDragTargetLayout.onDragExit();
        }
        this.mDragTargetLayout = layout;
        if (layout != null) {
            layout.onDragEnter();
        }
        cleanupReorder(true);
        cleanupFolderCreation();
        setCurrentDropOverCell(-1, -1);
    }

    void setCurrentDragOverlappingLayout(CellLayout layout) {
        CellLayout cellLayout = this.mDragOverlappingLayout;
        if (cellLayout != null) {
            cellLayout.setIsDragOverlapping(false);
        }
        this.mDragOverlappingLayout = layout;
        if (layout != null) {
            layout.setIsDragOverlapping(true);
        }
        this.mLauncher.getDragLayer().getScrim().invalidate();
    }

    public CellLayout getCurrentDragOverlappingLayout() {
        return this.mDragOverlappingLayout;
    }

    void setCurrentDropOverCell(int x, int y) {
        if (x == this.mDragOverX && y == this.mDragOverY) {
            return;
        }
        this.mDragOverX = x;
        this.mDragOverY = y;
        setDragMode(0);
    }

    void setDragMode(int dragMode) {
        if (dragMode != this.mDragMode) {
            if (dragMode == 0) {
                cleanupAddToFolder();
                cleanupReorder(false);
                cleanupFolderCreation();
            } else if (dragMode == 2) {
                cleanupReorder(true);
                cleanupFolderCreation();
            } else if (dragMode == 1) {
                cleanupAddToFolder();
                cleanupReorder(true);
            } else if (dragMode == 3) {
                cleanupAddToFolder();
                cleanupFolderCreation();
            }
            this.mDragMode = dragMode;
        }
    }

    private void cleanupFolderCreation() {
        PreviewBackground previewBackground = this.mFolderCreateBg;
        if (previewBackground != null) {
            previewBackground.animateToRest();
        }
        this.mFolderCreationAlarm.setOnAlarmListener(null);
        this.mFolderCreationAlarm.cancelAlarm();
    }

    private void cleanupAddToFolder() {
        FolderIcon folderIcon = this.mDragOverFolderIcon;
        if (folderIcon != null) {
            folderIcon.onDragExit();
            this.mDragOverFolderIcon = null;
        }
    }

    private void cleanupReorder(boolean cancelAlarm) {
        if (cancelAlarm) {
            this.mReorderAlarm.cancelAlarm();
        }
        this.mLastReorderX = -1;
        this.mLastReorderY = -1;
    }

    private void mapPointFromSelfToChild(View v, float[] xy) {
        xy[0] = xy[0] - v.getLeft();
        xy[1] = xy[1] - v.getTop();
    }

    boolean isPointInSelfOverHotseat(int x, int y) {
        float[] fArr = this.mTempFXY;
        fArr[0] = x;
        fArr[1] = y;
        this.mLauncher.getDragLayer().getDescendantCoordRelativeToSelf(this, this.mTempFXY, true);
        Hotseat hotseat = this.mLauncher.getHotseat();
        return this.mTempFXY[0] >= ((float) hotseat.getLeft()) && this.mTempFXY[0] <= ((float) hotseat.getRight()) && this.mTempFXY[1] >= ((float) hotseat.getTop()) && this.mTempFXY[1] <= ((float) hotseat.getBottom());
    }

    private void mapPointFromDropLayout(CellLayout layout, float[] xy) {
        if (this.mLauncher.isHotseatLayout(layout)) {
            this.mLauncher.getDragLayer().getDescendantCoordRelativeToSelf(this, xy, true);
            this.mLauncher.getDragLayer().mapCoordInSelfToDescendant(layout, xy);
        } else {
            mapPointFromSelfToChild(layout, xy);
        }
    }

    private boolean isDragWidget(DropTarget.DragObject d) {
        return (d.dragInfo instanceof LauncherAppWidgetInfo) || (d.dragInfo instanceof PendingAddWidgetInfo);
    }

    @Override // com.android.launcher3.DropTarget
    public void onDragOver(DropTarget.DragObject d) {
        ItemInfo itemInfo;
        int i;
        int i2;
        int i3;
        int i4;
        CellLayout cellLayout;
        if (transitionStateShouldAllowDrop() && (itemInfo = d.dragInfo) != null) {
            if (itemInfo.spanX < 0 || itemInfo.spanY < 0) {
                throw new RuntimeException("Improper spans found");
            }
            this.mDragViewVisualCenter = d.getVisualCenter(this.mDragViewVisualCenter);
            CellLayout.CellInfo cellInfo = this.mDragInfo;
            View view = cellInfo == null ? null : cellInfo.cell;
            float[] fArr = this.mDragViewVisualCenter;
            if (setDropLayoutForDragObject(d, fArr[0], fArr[1])) {
                if (this.mLauncher.isHotseatLayout(this.mDragTargetLayout)) {
                    this.mSpringLoadedDragController.cancel();
                } else {
                    this.mSpringLoadedDragController.setAlarm(this.mDragTargetLayout);
                }
            }
            CellLayout cellLayout2 = this.mDragTargetLayout;
            if (cellLayout2 != null) {
                mapPointFromDropLayout(cellLayout2, this.mDragViewVisualCenter);
                int i5 = itemInfo.spanX;
                int i6 = itemInfo.spanY;
                if (itemInfo.minSpanX > 0 && itemInfo.minSpanY > 0) {
                    i5 = itemInfo.minSpanX;
                    i6 = itemInfo.minSpanY;
                }
                int i7 = i5;
                int i8 = i6;
                float[] fArr2 = this.mDragViewVisualCenter;
                int[] iArrFindNearestArea = findNearestArea((int) fArr2[0], (int) fArr2[1], i7, i8, this.mDragTargetLayout, this.mTargetCell);
                this.mTargetCell = iArrFindNearestArea;
                int i9 = iArrFindNearestArea[0];
                int i10 = iArrFindNearestArea[1];
                setCurrentDropOverCell(iArrFindNearestArea[0], iArrFindNearestArea[1]);
                CellLayout cellLayout3 = this.mDragTargetLayout;
                float[] fArr3 = this.mDragViewVisualCenter;
                manageFolderFeedback(this.mDragTargetLayout, this.mTargetCell, cellLayout3.getDistanceFromCell(fArr3[0], fArr3[1], this.mTargetCell), d);
                CellLayout cellLayout4 = this.mDragTargetLayout;
                float[] fArr4 = this.mDragViewVisualCenter;
                boolean zIsNearestDropLocationOccupied = cellLayout4.isNearestDropLocationOccupied((int) fArr4[0], (int) fArr4[1], itemInfo.spanX, itemInfo.spanY, view, this.mTargetCell);
                if (!zIsNearestDropLocationOccupied) {
                    CellLayout cellLayout5 = this.mDragTargetLayout;
                    DragPreviewProvider dragPreviewProvider = this.mOutlineProvider;
                    int[] iArr = this.mTargetCell;
                    i = 1;
                    cellLayout5.visualizeDropLocation(view, dragPreviewProvider, iArr[0], iArr[1], itemInfo.spanX, itemInfo.spanY, false, d);
                } else {
                    i = 1;
                    int i11 = this.mDragMode;
                    if ((i11 == 0 || i11 == 3) && !this.mReorderAlarm.alarmPending() && (this.mLastReorderX != i9 || this.mLastReorderY != i10)) {
                        CellLayout cellLayout6 = this.mDragTargetLayout;
                        float[] fArr5 = this.mDragViewVisualCenter;
                        cellLayout6.performReorder((int) fArr5[0], (int) fArr5[1], i7, i8, itemInfo.spanX, itemInfo.spanY, view, this.mTargetCell, new int[2], 0);
                        i2 = 2;
                        i3 = 1;
                        this.mReorderAlarm.setOnAlarmListener(new ReorderAlarmListener(this.mDragViewVisualCenter, i7, i8, itemInfo.spanX, itemInfo.spanY, d, view));
                        this.mReorderAlarm.setAlarm(650L);
                    }
                    i4 = this.mDragMode;
                    if ((i4 == i3 && i4 != i2 && zIsNearestDropLocationOccupied) || (cellLayout = this.mDragTargetLayout) == null) {
                        return;
                    }
                    cellLayout.revertTempState();
                }
                i3 = i;
                i2 = 2;
                i4 = this.mDragMode;
                if (i4 == i3) {
                }
                cellLayout.revertTempState();
            }
        }
    }

    private boolean setDropLayoutForDragObject(DropTarget.DragObject d, float centerX, float centerY) {
        CellLayout hotseat = (this.mLauncher.getHotseat() == null || isDragWidget(d) || !isPointInSelfOverHotseat(d.x, d.y)) ? null : this.mLauncher.getHotseat();
        int nextPage = getNextPage();
        if (hotseat == null && !isPageInTransition()) {
            this.mTempTouchCoordinates[0] = Math.min(centerX, d.x);
            this.mTempTouchCoordinates[1] = d.y;
            hotseat = verifyInsidePage((this.mIsRtl ? 1 : -1) + nextPage, this.mTempTouchCoordinates);
        }
        if (hotseat == null && !isPageInTransition()) {
            this.mTempTouchCoordinates[0] = Math.max(centerX, d.x);
            this.mTempTouchCoordinates[1] = d.y;
            hotseat = verifyInsidePage((this.mIsRtl ? -1 : 1) + nextPage, this.mTempTouchCoordinates);
        }
        if (hotseat == null && nextPage >= 0 && nextPage < getPageCount()) {
            hotseat = (CellLayout) getChildAt(nextPage);
        }
        if (hotseat == this.mDragTargetLayout) {
            return false;
        }
        setCurrentDropLayout(hotseat);
        setCurrentDragOverlappingLayout(hotseat);
        return true;
    }

    private CellLayout verifyInsidePage(int pageNo, float[] touchXy) {
        if (pageNo < 0 || pageNo >= getPageCount()) {
            return null;
        }
        CellLayout cellLayout = (CellLayout) getChildAt(pageNo);
        mapPointFromSelfToChild(cellLayout, touchXy);
        if (touchXy[0] < 0.0f || touchXy[0] > cellLayout.getWidth() || touchXy[1] < 0.0f || touchXy[1] > cellLayout.getHeight()) {
            return null;
        }
        return cellLayout;
    }

    private void manageFolderFeedback(CellLayout targetLayout, int[] targetCell, float distance, DropTarget.DragObject dragObject) {
        if (distance > this.mMaxDistanceForFolderCreation) {
            if (this.mDragMode != 0) {
                setDragMode(0);
                return;
            }
            return;
        }
        CellLayout cellLayout = this.mDragTargetLayout;
        int[] iArr = this.mTargetCell;
        View childAt = cellLayout.getChildAt(iArr[0], iArr[1]);
        ItemInfo itemInfo = dragObject.dragInfo;
        boolean zWillCreateUserFolder = willCreateUserFolder(itemInfo, childAt, false);
        if (this.mDragMode == 0 && zWillCreateUserFolder && !this.mFolderCreationAlarm.alarmPending()) {
            FolderCreationAlarmListener folderCreationAlarmListener = new FolderCreationAlarmListener(targetLayout, targetCell[0], targetCell[1]);
            if (!dragObject.accessibleDrag) {
                this.mFolderCreationAlarm.setOnAlarmListener(folderCreationAlarmListener);
                this.mFolderCreationAlarm.setAlarm(0L);
            } else {
                folderCreationAlarmListener.onAlarm(this.mFolderCreationAlarm);
            }
            if (dragObject.stateAnnouncer != null) {
                dragObject.stateAnnouncer.announce(WorkspaceAccessibilityHelper.getDescriptionForDropOver(childAt, getContext()));
                return;
            }
            return;
        }
        boolean zWillAddToExistingUserFolder = willAddToExistingUserFolder(itemInfo, childAt);
        if (zWillAddToExistingUserFolder && this.mDragMode == 0) {
            FolderIcon folderIcon = (FolderIcon) childAt;
            this.mDragOverFolderIcon = folderIcon;
            folderIcon.onDragEnter(itemInfo);
            if (targetLayout != null) {
                targetLayout.clearDragOutlines();
            }
            setDragMode(2);
            if (dragObject.stateAnnouncer != null) {
                dragObject.stateAnnouncer.announce(WorkspaceAccessibilityHelper.getDescriptionForDropOver(childAt, getContext()));
                return;
            }
            return;
        }
        if (this.mDragMode == 2 && !zWillAddToExistingUserFolder) {
            setDragMode(0);
        }
        if (this.mDragMode != 1 || zWillCreateUserFolder) {
            return;
        }
        setDragMode(0);
    }

    class FolderCreationAlarmListener implements OnAlarmListener {
        final PreviewBackground bg;
        final int cellX;
        final int cellY;
        final CellLayout layout;

        public FolderCreationAlarmListener(CellLayout layout, int cellX, int cellY) {
            PreviewBackground previewBackground = new PreviewBackground();
            this.bg = previewBackground;
            this.layout = layout;
            this.cellX = cellX;
            this.cellY = cellY;
            BubbleTextView bubbleTextView = (BubbleTextView) layout.getChildAt(cellX, cellY);
            previewBackground.setup(Workspace.this.mLauncher, Workspace.this.mLauncher, null, bubbleTextView.getMeasuredWidth(), bubbleTextView.getPaddingTop());
            previewBackground.isClipping = false;
        }

        @Override // com.android.launcher3.OnAlarmListener
        public void onAlarm(Alarm alarm) {
            Workspace.this.mFolderCreateBg = this.bg;
            Workspace.this.mFolderCreateBg.animateToAccept(this.layout, this.cellX, this.cellY);
            this.layout.clearDragOutlines();
            Workspace.this.setDragMode(1);
        }
    }

    class ReorderAlarmListener implements OnAlarmListener {
        final View child;
        final DropTarget.DragObject dragObject;
        final float[] dragViewCenter;
        final int minSpanX;
        final int minSpanY;
        final int spanX;
        final int spanY;

        public ReorderAlarmListener(float[] dragViewCenter, int minSpanX, int minSpanY, int spanX, int spanY, DropTarget.DragObject dragObject, View child) {
            this.dragViewCenter = dragViewCenter;
            this.minSpanX = minSpanX;
            this.minSpanY = minSpanY;
            this.spanX = spanX;
            this.spanY = spanY;
            this.child = child;
            this.dragObject = dragObject;
        }

        @Override // com.android.launcher3.OnAlarmListener
        public void onAlarm(Alarm alarm) {
            int[] iArr = new int[2];
            Workspace workspace = Workspace.this;
            workspace.mTargetCell = workspace.findNearestArea((int) workspace.mDragViewVisualCenter[0], (int) Workspace.this.mDragViewVisualCenter[1], this.minSpanX, this.minSpanY, Workspace.this.mDragTargetLayout, Workspace.this.mTargetCell);
            Workspace workspace2 = Workspace.this;
            workspace2.mLastReorderX = workspace2.mTargetCell[0];
            Workspace workspace3 = Workspace.this;
            workspace3.mLastReorderY = workspace3.mTargetCell[1];
            Workspace workspace4 = Workspace.this;
            workspace4.mTargetCell = workspace4.mDragTargetLayout.performReorder((int) Workspace.this.mDragViewVisualCenter[0], (int) Workspace.this.mDragViewVisualCenter[1], this.minSpanX, this.minSpanY, this.spanX, this.spanY, this.child, Workspace.this.mTargetCell, iArr, 1);
            if (Workspace.this.mTargetCell[0] < 0 || Workspace.this.mTargetCell[1] < 0) {
                Workspace.this.mDragTargetLayout.revertTempState();
            } else {
                Workspace.this.setDragMode(3);
            }
            Workspace.this.mDragTargetLayout.visualizeDropLocation(this.child, Workspace.this.mOutlineProvider, Workspace.this.mTargetCell[0], Workspace.this.mTargetCell[1], iArr[0], iArr[1], (iArr[0] == this.spanX && iArr[1] == this.spanY) ? false : true, this.dragObject);
        }
    }

    @Override // com.android.launcher3.DropTarget
    public void getHitRectRelativeToDragLayer(Rect outRect) {
        this.mLauncher.getDragLayer().getDescendantRectRelativeToSelf(this, outRect);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void onDropExternal(final int[] r25, final com.android.launcher3.CellLayout r26, com.android.launcher3.DropTarget.DragObject r27) {
        /*
            Method dump skipped, instruction units count: 665
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.Workspace.onDropExternal(int[], com.android.launcher3.CellLayout, com.android.launcher3.DropTarget$DragObject):void");
    }

    public Bitmap createWidgetBitmap(ItemInfo widgetInfo, View layout) {
        int[] iArrEstimateItemSize = estimateItemSize(widgetInfo);
        int visibility = layout.getVisibility();
        layout.setVisibility(0);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iArrEstimateItemSize[0], 1073741824);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iArrEstimateItemSize[1], 1073741824);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArrEstimateItemSize[0], iArrEstimateItemSize[1], Bitmap.Config.ARGB_8888);
        layout.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        layout.layout(0, 0, iArrEstimateItemSize[0], iArrEstimateItemSize[1]);
        layout.draw(new Canvas(bitmapCreateBitmap));
        layout.setVisibility(visibility);
        return bitmapCreateBitmap;
    }

    private void getFinalPositionForDropAnimation(int[] loc, float[] scaleXY, DragView dragView, CellLayout layout, ItemInfo info, int[] targetCell, boolean scale) {
        Rect rectEstimateItemPosition = estimateItemPosition(layout, targetCell[0], targetCell[1], info.spanX, info.spanY);
        if (info.itemType == 4) {
            DeviceProfile deviceProfile = this.mLauncher.getDeviceProfile();
            Utilities.shrinkRect(rectEstimateItemPosition, deviceProfile.appWidgetScale.x, deviceProfile.appWidgetScale.y);
        }
        this.mTempFXY[0] = rectEstimateItemPosition.left;
        this.mTempFXY[1] = rectEstimateItemPosition.top;
        setFinalTransitionTransform();
        float descendantCoordRelativeToSelf = this.mLauncher.getDragLayer().getDescendantCoordRelativeToSelf(layout, this.mTempFXY, true);
        resetTransitionTransform();
        Utilities.roundArray(this.mTempFXY, loc);
        if (scale) {
            loc[0] = (int) (((double) loc[0]) - (((double) ((dragView.getMeasuredWidth() - (rectEstimateItemPosition.width() * descendantCoordRelativeToSelf)) / 2.0f)) - Math.ceil(layout.getUnusedHorizontalSpace() / 2.0f)));
            loc[1] = (int) (loc[1] - ((dragView.getMeasuredHeight() - (rectEstimateItemPosition.height() * descendantCoordRelativeToSelf)) / 2.0f));
            scaleXY[0] = ((rectEstimateItemPosition.width() * 1.0f) / dragView.getMeasuredWidth()) * descendantCoordRelativeToSelf;
            scaleXY[1] = ((rectEstimateItemPosition.height() * 1.0f) / dragView.getMeasuredHeight()) * descendantCoordRelativeToSelf;
            return;
        }
        float initialScale = dragView.getInitialScale() * descendantCoordRelativeToSelf;
        float f = initialScale - 1.0f;
        loc[0] = (int) (loc[0] + ((dragView.getWidth() * f) / 2.0f));
        loc[1] = (int) (loc[1] + ((f * dragView.getHeight()) / 2.0f));
        scaleXY[1] = initialScale;
        scaleXY[0] = initialScale;
        if (dragView.getDragRegion() != null) {
            loc[0] = (int) (loc[0] + (r0.left * descendantCoordRelativeToSelf));
            loc[1] = (int) (loc[1] + (descendantCoordRelativeToSelf * r0.top));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void animateWidgetDrop(com.android.launcher3.ItemInfo r19, com.android.launcher3.CellLayout r20, final com.android.launcher3.dragndrop.DragView r21, final java.lang.Runnable r22, int r23, final android.view.View r24, boolean r25) {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.Workspace.animateWidgetDrop(com.android.launcher3.ItemInfo, com.android.launcher3.CellLayout, com.android.launcher3.dragndrop.DragView, java.lang.Runnable, int, android.view.View, boolean):void");
    }

    public void setFinalTransitionTransform() {
        if (isSwitchingState()) {
            this.mCurrentScale = getScaleX();
            setScaleX(this.mStateTransitionAnimation.getFinalScale());
            setScaleY(this.mStateTransitionAnimation.getFinalScale());
        }
    }

    public void resetTransitionTransform() {
        if (isSwitchingState()) {
            setScaleX(this.mCurrentScale);
            setScaleY(this.mCurrentScale);
        }
    }

    public CellLayout.CellInfo getDragInfo() {
        return this.mDragInfo;
    }

    int[] findNearestArea(int pixelX, int pixelY, int spanX, int spanY, CellLayout layout, int[] recycle) {
        return layout.findNearestArea(pixelX, pixelY, spanX, spanY, recycle);
    }

    void setup(DragController dragController) {
        this.mSpringLoadedDragController = new SpringLoadedDragController(this.mLauncher);
        this.mDragController = dragController;
        updateChildrenLayersEnabled();
    }

    @Override // com.android.launcher3.DragSource
    public void onDropCompleted(final View target, final DropTarget.DragObject d, final boolean success) {
        CellLayout cellLayout;
        CellLayout.CellInfo cellInfo;
        if (!success) {
            CellLayout.CellInfo cellInfo2 = this.mDragInfo;
            if (cellInfo2 != null && (cellLayout = this.mLauncher.getCellLayout(cellInfo2.container, this.mDragInfo.screenId)) != null) {
                cellLayout.onDropChild(this.mDragInfo.cell);
            }
        } else if (target != this && (cellInfo = this.mDragInfo) != null) {
            removeWorkspaceItem(cellInfo.cell);
        }
        View homescreenIconByItemId = getHomescreenIconByItemId(d.originalDragInfo.id);
        if (d.cancelled && homescreenIconByItemId != null) {
            homescreenIconByItemId.setVisibility(0);
        }
        this.mDragInfo = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void removeWorkspaceItem(View v) {
        CellLayout parentCellLayoutForView = getParentCellLayoutForView(v);
        if (parentCellLayoutForView != null) {
            parentCellLayoutForView.removeView(v);
        }
        if (v instanceof DropTarget) {
            this.mDragController.removeDropTarget((DropTarget) v);
        }
    }

    public void removeFolderListeners() {
        mapOverItems(false, new ItemOperator() { // from class: com.android.launcher3.Workspace.10
            @Override // com.android.launcher3.Workspace.ItemOperator
            public boolean evaluate(ItemInfo info, View view) {
                if (!(view instanceof FolderIcon)) {
                    return false;
                }
                ((FolderIcon) view).removeListeners();
                return false;
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        this.mSavedStates = container;
    }

    public void restoreInstanceStateForChild(int child) {
        if (this.mSavedStates != null) {
            this.mRestoredPages.add(child);
            CellLayout cellLayout = (CellLayout) getChildAt(child);
            if (cellLayout != null) {
                cellLayout.restoreInstanceState(this.mSavedStates);
            }
        }
    }

    public void restoreInstanceStateForRemainingPages() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (!this.mRestoredPages.contains(i)) {
                restoreInstanceStateForChild(i);
            }
        }
        this.mRestoredPages.clear();
        this.mSavedStates = null;
    }

    @Override // com.android.launcher3.PagedView
    public boolean scrollLeft() {
        boolean zScrollLeft = (workspaceInModalState() || this.mIsSwitchingState) ? false : super.scrollLeft();
        Folder open = Folder.getOpen(this.mLauncher);
        if (open != null) {
            open.completeDragExit();
        }
        return zScrollLeft;
    }

    @Override // com.android.launcher3.PagedView
    public boolean scrollRight() {
        boolean zScrollRight = (workspaceInModalState() || this.mIsSwitchingState) ? false : super.scrollRight();
        Folder open = Folder.getOpen(this.mLauncher);
        if (open != null) {
            open.completeDragExit();
        }
        return zScrollRight;
    }

    CellLayout getParentCellLayoutForView(View v) {
        for (CellLayout cellLayout : getWorkspaceAndHotseatCellLayouts()) {
            if (cellLayout.getShortcutsAndWidgets().indexOfChild(v) > -1) {
                return cellLayout;
            }
        }
        return null;
    }

    private CellLayout[] getWorkspaceAndHotseatCellLayouts() {
        CellLayout[] cellLayoutArr;
        int childCount = getChildCount();
        if (this.mLauncher.getHotseat() != null) {
            cellLayoutArr = new CellLayout[childCount + 1];
            cellLayoutArr[childCount] = this.mLauncher.getHotseat();
        } else {
            cellLayoutArr = new CellLayout[childCount];
        }
        for (int i = 0; i < childCount; i++) {
            cellLayoutArr[i] = (CellLayout) getChildAt(i);
        }
        return cellLayoutArr;
    }

    public View getFirstMatchForAppClose(final String packageName, final UserHandle user) {
        CellLayout cellLayout = (CellLayout) getPageAt(getCurrentPage());
        final ItemOperator itemOperator = new ItemOperator() { // from class: com.android.launcher3.-$$Lambda$Workspace$itGlSk0xy1FlzeB4_KDUrxTKpso
            @Override // com.android.launcher3.Workspace.ItemOperator
            public final boolean evaluate(ItemInfo itemInfo, View view) {
                return Workspace.lambda$getFirstMatchForAppClose$1(packageName, user, itemInfo, view);
            }
        };
        final ItemOperator itemOperator2 = new ItemOperator() { // from class: com.android.launcher3.-$$Lambda$Workspace$N1y84R0ZFyFYKYbQ813H-Bn_H8Q
            @Override // com.android.launcher3.Workspace.ItemOperator
            public final boolean evaluate(ItemInfo itemInfo, View view) {
                return Workspace.lambda$getFirstMatchForAppClose$2(itemOperator, itemInfo, view);
            }
        };
        return FeatureFlags.ADAPTIVE_ICON_WINDOW_ANIM.get() ? getFirstMatch(new CellLayout[]{getHotseat(), cellLayout}, itemOperator2, new ItemOperator() { // from class: com.android.launcher3.-$$Lambda$Workspace$0vxSPT-A0t2KT3X9DN2rWw7ztZ8
            @Override // com.android.launcher3.Workspace.ItemOperator
            public final boolean evaluate(ItemInfo itemInfo, View view) {
                return Workspace.lambda$getFirstMatchForAppClose$3(itemOperator2, itemInfo, view);
            }
        }) : getFirstMatch(new CellLayout[]{getHotseat(), cellLayout}, itemOperator2);
    }

    static /* synthetic */ boolean lambda$getFirstMatchForAppClose$1(String str, UserHandle userHandle, ItemInfo itemInfo, View view) {
        return itemInfo != null && itemInfo.getTargetComponent() != null && TextUtils.equals(itemInfo.getTargetComponent().getPackageName(), str) && itemInfo.user.equals(userHandle);
    }

    static /* synthetic */ boolean lambda$getFirstMatchForAppClose$2(ItemOperator itemOperator, ItemInfo itemInfo, View view) {
        return itemOperator.evaluate(itemInfo, view) && itemInfo.itemType == 0;
    }

    static /* synthetic */ boolean lambda$getFirstMatchForAppClose$3(ItemOperator itemOperator, ItemInfo itemInfo, View view) {
        if (!(itemInfo instanceof FolderInfo)) {
            return false;
        }
        Iterator<WorkspaceItemInfo> it = ((FolderInfo) itemInfo).contents.iterator();
        while (it.hasNext()) {
            if (itemOperator.evaluate(it.next(), view)) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ boolean lambda$getHomescreenIconByItemId$4(int i, ItemInfo itemInfo, View view) {
        return itemInfo != null && itemInfo.id == i;
    }

    public View getHomescreenIconByItemId(final int id) {
        return getFirstMatch(new ItemOperator() { // from class: com.android.launcher3.-$$Lambda$Workspace$Ygi_F-gnefJeTKUV2KNfwtzQi6o
            @Override // com.android.launcher3.Workspace.ItemOperator
            public final boolean evaluate(ItemInfo itemInfo, View view) {
                return Workspace.lambda$getHomescreenIconByItemId$4(id, itemInfo, view);
            }
        });
    }

    public LauncherAppWidgetHostView getWidgetForAppWidgetId(final int appWidgetId) {
        return (LauncherAppWidgetHostView) getFirstMatch(new ItemOperator() { // from class: com.android.launcher3.-$$Lambda$Workspace$1Flqd1h021p4tC4dzq5YQnPrEeA
            @Override // com.android.launcher3.Workspace.ItemOperator
            public final boolean evaluate(ItemInfo itemInfo, View view) {
                return Workspace.lambda$getWidgetForAppWidgetId$5(appWidgetId, itemInfo, view);
            }
        });
    }

    static /* synthetic */ boolean lambda$getWidgetForAppWidgetId$5(int i, ItemInfo itemInfo, View view) {
        return (itemInfo instanceof LauncherAppWidgetInfo) && ((LauncherAppWidgetInfo) itemInfo).appWidgetId == i;
    }

    public View getFirstMatch(final ItemOperator operator) {
        final View[] viewArr = new View[1];
        mapOverItems(false, new ItemOperator() { // from class: com.android.launcher3.Workspace.11
            @Override // com.android.launcher3.Workspace.ItemOperator
            public boolean evaluate(ItemInfo info, View v) {
                if (!operator.evaluate(info, v)) {
                    return false;
                }
                viewArr[0] = v;
                return true;
            }
        });
        return viewArr[0];
    }

    private View getFirstMatch(CellLayout[] cellLayouts, final ItemOperator... operators) {
        int length = operators.length;
        final View[] viewArr = new View[length];
        for (CellLayout cellLayout : cellLayouts) {
            mapOverCellLayout(false, cellLayout, new ItemOperator() { // from class: com.android.launcher3.-$$Lambda$Workspace$5SwDYdmuBOg1uzh-NWNMHEoTK8k
                @Override // com.android.launcher3.Workspace.ItemOperator
                public final boolean evaluate(ItemInfo itemInfo, View view) {
                    return Workspace.lambda$getFirstMatch$6(operators, viewArr, itemInfo, view);
                }
            });
            if (viewArr[0] != null) {
                break;
            }
        }
        for (int i = 0; i < length; i++) {
            View view = viewArr[i];
            if (view != null) {
                return view;
            }
        }
        return null;
    }

    static /* synthetic */ boolean lambda$getFirstMatch$6(ItemOperator[] itemOperatorArr, View[] viewArr, ItemInfo itemInfo, View view) {
        for (int i = 0; i < itemOperatorArr.length; i++) {
            if (viewArr[i] == null && itemOperatorArr[i].evaluate(itemInfo, view)) {
                viewArr[i] = view;
                if (i == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    void clearDropTargets() {
        mapOverItems(false, new ItemOperator() { // from class: com.android.launcher3.Workspace.12
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.android.launcher3.Workspace.ItemOperator
            public boolean evaluate(ItemInfo info, View v) {
                if (!(v instanceof DropTarget)) {
                    return false;
                }
                Workspace.this.mDragController.removeDropTarget((DropTarget) v);
                return false;
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void removeItemsByMatcher(final ItemInfoMatcher matcher) {
        View view;
        for (CellLayout cellLayout : getWorkspaceAndHotseatCellLayouts()) {
            ShortcutAndWidgetContainer shortcutsAndWidgets = cellLayout.getShortcutsAndWidgets();
            IntSparseArrayMap intSparseArrayMap = new IntSparseArrayMap();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < shortcutsAndWidgets.getChildCount(); i++) {
                View childAt = shortcutsAndWidgets.getChildAt(i);
                if (childAt.getTag() instanceof ItemInfo) {
                    ItemInfo itemInfo = (ItemInfo) childAt.getTag();
                    arrayList.add(itemInfo);
                    intSparseArrayMap.put(itemInfo.id, childAt);
                }
            }
            for (ItemInfo itemInfo2 : matcher.filterItemInfos(arrayList)) {
                View view2 = (View) intSparseArrayMap.get(itemInfo2.id);
                if (view2 != 0) {
                    cellLayout.removeViewInLayout(view2);
                    if (view2 instanceof DropTarget) {
                        this.mDragController.removeDropTarget((DropTarget) view2);
                    }
                } else if (itemInfo2.container >= 0 && (view = (View) intSparseArrayMap.get(itemInfo2.container)) != null) {
                    FolderInfo folderInfo = (FolderInfo) view.getTag();
                    folderInfo.prepareAutoUpdate();
                    folderInfo.remove((WorkspaceItemInfo) itemInfo2, false);
                }
            }
        }
        stripEmptyScreens();
    }

    public void mapOverItems(boolean recurse, ItemOperator op) {
        CellLayout[] workspaceAndHotseatCellLayouts = getWorkspaceAndHotseatCellLayouts();
        int length = workspaceAndHotseatCellLayouts.length;
        for (int i = 0; i < length && !mapOverCellLayout(recurse, workspaceAndHotseatCellLayouts[i], op); i++) {
        }
    }

    private boolean mapOverCellLayout(boolean recurse, CellLayout layout, ItemOperator op) {
        if (layout == null) {
            return false;
        }
        ShortcutAndWidgetContainer shortcutsAndWidgets = layout.getShortcutsAndWidgets();
        int childCount = shortcutsAndWidgets.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = shortcutsAndWidgets.getChildAt(i);
            ItemInfo itemInfo = (ItemInfo) childAt.getTag();
            if (recurse && (itemInfo instanceof FolderInfo) && (childAt instanceof FolderIcon)) {
                ArrayList<View> itemsInReadingOrder = ((FolderIcon) childAt).getFolder().getItemsInReadingOrder();
                int size = itemsInReadingOrder.size();
                for (int i2 = 0; i2 < size; i2++) {
                    View view = itemsInReadingOrder.get(i2);
                    if (op.evaluate((ItemInfo) view.getTag(), view)) {
                        return true;
                    }
                }
            } else if (op.evaluate(itemInfo, childAt)) {
                return true;
            }
        }
        return false;
    }

    void updateShortcuts(ArrayList<WorkspaceItemInfo> shortcuts) {
        int size = shortcuts.size();
        final HashSet hashSet = new HashSet(size);
        final IntSet intSet = new IntSet();
        for (int i = 0; i < size; i++) {
            WorkspaceItemInfo workspaceItemInfo = shortcuts.get(i);
            hashSet.add(workspaceItemInfo);
            intSet.add(workspaceItemInfo.container);
        }
        mapOverItems(true, new ItemOperator() { // from class: com.android.launcher3.Workspace.13
            @Override // com.android.launcher3.Workspace.ItemOperator
            public boolean evaluate(ItemInfo info, View v) {
                if ((info instanceof WorkspaceItemInfo) && (v instanceof BubbleTextView) && hashSet.contains(info)) {
                    WorkspaceItemInfo workspaceItemInfo2 = (WorkspaceItemInfo) info;
                    BubbleTextView bubbleTextView = (BubbleTextView) v;
                    Drawable icon = bubbleTextView.getIcon();
                    bubbleTextView.applyFromWorkspaceItem(workspaceItemInfo2, workspaceItemInfo2.isPromise() != ((icon instanceof PreloadIconDrawable) && ((PreloadIconDrawable) icon).hasNotCompleted()));
                }
                return false;
            }
        });
        mapOverItems(false, new ItemOperator() { // from class: com.android.launcher3.Workspace.14
            @Override // com.android.launcher3.Workspace.ItemOperator
            public boolean evaluate(ItemInfo info, View v) {
                if ((info instanceof FolderInfo) && intSet.contains(info.id)) {
                    ((FolderInfo) info).itemsChanged(false);
                }
                return false;
            }
        });
    }

    public void updateNotificationDots(final Predicate<PackageUserKey> updatedDots) {
        final PackageUserKey packageUserKey = new PackageUserKey(null, null);
        final IntSet intSet = new IntSet();
        mapOverItems(true, new ItemOperator() { // from class: com.android.launcher3.Workspace.15
            @Override // com.android.launcher3.Workspace.ItemOperator
            public boolean evaluate(ItemInfo info, View v) {
                if (!(info instanceof WorkspaceItemInfo) || !(v instanceof BubbleTextView)) {
                    return false;
                }
                if (packageUserKey.updateFromItemInfo(info) && !updatedDots.test(packageUserKey)) {
                    return false;
                }
                ((BubbleTextView) v).applyDotState(info, true);
                intSet.add(info.container);
                return false;
            }
        });
        mapOverItems(false, new ItemOperator() { // from class: com.android.launcher3.Workspace.16
            @Override // com.android.launcher3.Workspace.ItemOperator
            public boolean evaluate(ItemInfo info, View v) {
                if (!(info instanceof FolderInfo) || !intSet.contains(info.id) || !(v instanceof FolderIcon)) {
                    return false;
                }
                FolderDotInfo folderDotInfo = new FolderDotInfo();
                Iterator<WorkspaceItemInfo> it = ((FolderInfo) info).contents.iterator();
                while (it.hasNext()) {
                    folderDotInfo.addDotInfo(Workspace.this.mLauncher.getDotInfoForItem(it.next()));
                }
                ((FolderIcon) v).setDotInfo(folderDotInfo);
                return false;
            }
        });
    }

    public void removeAbandonedPromise(String packageName, UserHandle user) {
        HashSet hashSet = new HashSet(1);
        hashSet.add(packageName);
        ItemInfoMatcher itemInfoMatcherOfPackages = ItemInfoMatcher.ofPackages(hashSet, user);
        this.mLauncher.getModelWriter().deleteItemsFromDatabase(itemInfoMatcherOfPackages);
        removeItemsByMatcher(itemInfoMatcherOfPackages);
    }

    public void updateRestoreItems(final HashSet<ItemInfo> updates) {
        mapOverItems(true, new ItemOperator() { // from class: com.android.launcher3.Workspace.17
            @Override // com.android.launcher3.Workspace.ItemOperator
            public boolean evaluate(ItemInfo info, View v) {
                if ((info instanceof WorkspaceItemInfo) && (v instanceof BubbleTextView) && updates.contains(info)) {
                    ((BubbleTextView) v).applyPromiseState(false);
                } else if ((v instanceof PendingAppWidgetHostView) && (info instanceof LauncherAppWidgetInfo) && updates.contains(info)) {
                    ((PendingAppWidgetHostView) v).applyState();
                }
                return false;
            }
        });
    }

    public void widgetsRestored(final ArrayList<LauncherAppWidgetInfo> changedInfo) {
        LauncherAppWidgetProviderInfo launcherAppWidgetInfo;
        if (changedInfo.isEmpty()) {
            return;
        }
        DeferredWidgetRefresh deferredWidgetRefresh = new DeferredWidgetRefresh(changedInfo, this.mLauncher.getAppWidgetHost());
        LauncherAppWidgetInfo launcherAppWidgetInfo2 = changedInfo.get(0);
        if (launcherAppWidgetInfo2.hasRestoreFlag(1)) {
            launcherAppWidgetInfo = AppWidgetManagerCompat.getInstance(this.mLauncher).findProvider(launcherAppWidgetInfo2.providerName, launcherAppWidgetInfo2.user);
        } else {
            launcherAppWidgetInfo = AppWidgetManagerCompat.getInstance(this.mLauncher).getLauncherAppWidgetInfo(launcherAppWidgetInfo2.appWidgetId);
        }
        if (launcherAppWidgetInfo != null) {
            deferredWidgetRefresh.run();
        } else {
            mapOverItems(false, new ItemOperator() { // from class: com.android.launcher3.Workspace.18
                @Override // com.android.launcher3.Workspace.ItemOperator
                public boolean evaluate(ItemInfo info, View view) {
                    if (!(view instanceof PendingAppWidgetHostView) || !changedInfo.contains(info)) {
                        return false;
                    }
                    ((LauncherAppWidgetInfo) info).installProgress = 100;
                    ((PendingAppWidgetHostView) view).applyState();
                    return false;
                }
            });
        }
    }

    void moveToDefaultScreen() {
        if (!workspaceInModalState() && getNextPage() != 0) {
            snapToPage(0);
        }
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.requestFocus();
        }
    }

    @Override // com.android.launcher3.PagedView
    public int getExpectedHeight() {
        return (getMeasuredHeight() <= 0 || !this.mIsLayoutValid) ? this.mLauncher.getDeviceProfile().heightPx : getMeasuredHeight();
    }

    @Override // com.android.launcher3.PagedView
    public int getExpectedWidth() {
        return (getMeasuredWidth() <= 0 || !this.mIsLayoutValid) ? this.mLauncher.getDeviceProfile().widthPx : getMeasuredWidth();
    }

    @Override // com.android.launcher3.PagedView
    protected boolean canAnnouncePageDescription() {
        return Float.compare(this.mOverlayTranslation, 0.0f) == 0;
    }

    @Override // com.android.launcher3.PagedView
    protected String getCurrentPageDescription() {
        return getPageDescription(this.mNextPage != -1 ? this.mNextPage : this.mCurrentPage);
    }

    private String getPageDescription(int page) {
        int childCount = getChildCount();
        int iIndexOf = this.mScreenOrder.indexOf(WorkspaceLayoutManager.EXTRA_EMPTY_SCREEN_ID);
        if (iIndexOf >= 0 && childCount > 1) {
            if (page == iIndexOf) {
                return getContext().getString(R.string.workspace_new_page);
            }
            childCount--;
        }
        return childCount == 0 ? getContext().getString(R.string.all_apps_home_button_label) : getContext().getString(R.string.workspace_scroll_format, Integer.valueOf(page + 1), Integer.valueOf(childCount));
    }

    @Override // com.android.launcher3.logging.StatsLogUtils.LogContainerProvider
    public void fillInLogContainerData(View v, ItemInfo info, LauncherLogProto.Target target, LauncherLogProto.Target targetParent) {
        target.gridX = info.cellX;
        target.gridY = info.cellY;
        target.pageIndex = getCurrentPage();
        targetParent.containerType = 1;
        if (info.container == -101) {
            target.rank = info.rank;
            targetParent.containerType = 2;
        } else if (info.container >= 0) {
            targetParent.containerType = 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class DeferredWidgetRefresh implements Runnable, LauncherAppWidgetHost.ProviderChangedListener {
        private final Handler mHandler;
        private final LauncherAppWidgetHost mHost;
        private final ArrayList<LauncherAppWidgetInfo> mInfos;
        private boolean mRefreshPending;

        DeferredWidgetRefresh(ArrayList<LauncherAppWidgetInfo> infos, LauncherAppWidgetHost host) {
            this.mInfos = infos;
            this.mHost = host;
            Handler handler = Workspace.this.mLauncher.mHandler;
            this.mHandler = handler;
            this.mRefreshPending = true;
            host.addProviderChangeListener(this);
            Message messageObtain = Message.obtain(handler, this);
            messageObtain.obj = DeferredWidgetRefresh.class;
            handler.sendMessageDelayed(messageObtain, JobInfo.MIN_BACKOFF_MILLIS);
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mHost.removeProviderChangeListener(this);
            this.mHandler.removeCallbacks(this);
            if (this.mRefreshPending) {
                this.mRefreshPending = false;
                final ArrayList arrayList = new ArrayList(this.mInfos.size());
                Workspace.this.mapOverItems(false, new ItemOperator() { // from class: com.android.launcher3.-$$Lambda$Workspace$DeferredWidgetRefresh$HB2AKKKqiPXaylPZEHkSs_dxjRM
                    @Override // com.android.launcher3.Workspace.ItemOperator
                    public final boolean evaluate(ItemInfo itemInfo, View view) {
                        return this.f$0.lambda$run$0$Workspace$DeferredWidgetRefresh(arrayList, itemInfo, view);
                    }
                });
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((PendingAppWidgetHostView) it.next()).reInflate();
                }
            }
        }

        public /* synthetic */ boolean lambda$run$0$Workspace$DeferredWidgetRefresh(ArrayList arrayList, ItemInfo itemInfo, View view) {
            if (!(view instanceof PendingAppWidgetHostView) || !this.mInfos.contains(itemInfo)) {
                return false;
            }
            arrayList.add((PendingAppWidgetHostView) view);
            return false;
        }

        @Override // com.android.launcher3.LauncherAppWidgetHost.ProviderChangedListener
        public void notifyWidgetProvidersChanged() {
            run();
        }
    }

    private class StateTransitionListener extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {
        private final LauncherState mToState;

        StateTransitionListener(LauncherState toState) {
            this.mToState = toState;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator anim) {
            Workspace.this.mTransitionProgress = anim.getAnimatedFraction();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animation) {
            Workspace.this.onStartStateTransition(this.mToState);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            Workspace.this.onEndStateTransition();
        }
    }
}
