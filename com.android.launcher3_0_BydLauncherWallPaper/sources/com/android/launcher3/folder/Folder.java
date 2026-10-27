package com.android.launcher3.folder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.appwidget.AppWidgetHostView;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.Selection;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.Alarm;
import com.android.launcher3.AppInfo;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.CellLayout;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.DragSource;
import com.android.launcher3.DropTarget;
import com.android.launcher3.ExtendedEditText;
import com.android.launcher3.FolderInfo;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.OnAlarmListener;
import com.android.launcher3.R;
import com.android.launcher3.ShortcutAndWidgetContainer;
import com.android.launcher3.Workspace;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.accessibility.AccessibleDragListenerAdapter;
import com.android.launcher3.compat.AccessibilityManagerCompat;
import com.android.launcher3.dragndrop.DragController;
import com.android.launcher3.dragndrop.DragLayer;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.folder.SelectedFolderApp;
import com.android.launcher3.logging.LoggerUtils;
import com.android.launcher3.pageindicators.PageIndicatorDots;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.views.BaseDragLayer;
import com.android.launcher3.views.ClipPathView;
import com.android.launcher3.widget.PendingAddShortcutInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Folder extends AbstractFloatingView implements ClipPathView, DragSource, View.OnLongClickListener, DropTarget, FolderInfo.FolderListener, TextView.OnEditorActionListener, View.OnFocusChangeListener, DragController.DragListener, ExtendedEditText.OnBackKeyListener {
    private static final int FOLDER_NAME_ANIMATION_DURATION = 633;
    private static final float ICON_OVERSCROLL_WIDTH_FACTOR = 0.45f;
    private static final int MIN_CONTENT_DIMEN = 5;
    private static final int MIN_FOLDERS_FOR_HARDWARE_OPTIMIZATION = 10;
    private static final int ON_EXIT_CLOSE_DELAY = 400;
    private static final int REORDER_DELAY = 250;
    public static final int RESCROLL_DELAY = 900;
    public static final int SCROLL_HINT_DURATION = 500;
    public static final int SCROLL_LEFT = 0;
    public static final int SCROLL_NONE = -1;
    public static final int SCROLL_RIGHT = 1;
    static final int STATE_ANIMATING = 1;
    static final int STATE_NONE = -1;
    static final int STATE_OPEN = 2;
    static final int STATE_SMALL = 0;
    private static final String TAG = "Launcher.Folder";
    private static String sDefaultFolderName;
    private static String sHintText;
    private final WorkspaceItemInfo mAddAppItem;
    private Path mClipPath;
    FolderPagedView mContent;
    private AnimatorSet mCurrentAnimator;
    private View mCurrentDragView;
    int mCurrentScrollDir;
    private boolean mDeleteFolderOnDropCompleted;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private boolean mDestroyed;
    protected DragController mDragController;
    private boolean mDragInProgress;
    int mEmptyCellRank;
    FolderIcon mFolderIcon;
    float mFolderIconPivotX;
    float mFolderIconPivotY;
    public ExtendedEditText mFolderName;
    private View mFooter;
    private int mFooterHeight;
    public FolderInfo mInfo;
    private boolean mIsEditingName;
    private boolean mIsExternalDrag;
    private boolean mItemAddedBackToSelfViaIcon;
    final ArrayList<View> mItemsInReadingOrder;
    boolean mItemsInvalidated;
    protected final Launcher mLauncher;
    private final Alarm mOnExitAlarm;
    OnAlarmListener mOnExitAlarmListener;
    private final Alarm mOnScrollHintAlarm;
    private PageIndicatorDots mPageIndicator;
    int mPrevTargetRank;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private boolean mRearrangeOnClose;
    private final Alarm mReorderAlarm;
    OnAlarmListener mReorderAlarmListener;
    private int mScrollAreaOffset;
    int mScrollHintDir;
    final Alarm mScrollPauseAlarm;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE, mapping = {@ViewDebug.IntToString(from = -1, to = "STATE_NONE"), @ViewDebug.IntToString(from = 0, to = "STATE_SMALL"), @ViewDebug.IntToString(from = 1, to = "STATE_ANIMATING"), @ViewDebug.IntToString(from = 2, to = "STATE_OPEN")})
    int mState;
    private boolean mSuppressFolderDeletion;
    int mTargetRank;
    private static final Rect sTempRect = new Rect();
    public static final Comparator<ItemInfo> ITEM_POS_COMPARATOR = new Comparator<ItemInfo>() { // from class: com.android.launcher3.folder.Folder.14
        @Override // java.util.Comparator
        public int compare(ItemInfo lhs, ItemInfo rhs) {
            int i;
            int i2;
            if (lhs.rank != rhs.rank) {
                i = lhs.rank;
                i2 = rhs.rank;
            } else if (lhs.cellY != rhs.cellY) {
                i = lhs.cellY;
                i2 = rhs.cellY;
            } else {
                i = lhs.cellX;
                i2 = rhs.cellX;
            }
            return i - i2;
        }
    };

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return true;
    }

    @Override // com.android.launcher3.AbstractFloatingView
    public int getLogContainerType() {
        return 3;
    }

    @Override // com.android.launcher3.AbstractFloatingView
    protected boolean isOfType(int type) {
        return (type & 1) != 0;
    }

    @Override // com.android.launcher3.FolderInfo.FolderListener
    public void onTitleChanged(CharSequence title) {
    }

    public Folder(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mReorderAlarm = new Alarm();
        this.mOnExitAlarm = new Alarm();
        this.mOnScrollHintAlarm = new Alarm();
        this.mScrollPauseAlarm = new Alarm();
        this.mItemsInReadingOrder = new ArrayList<>();
        this.mState = -1;
        this.mRearrangeOnClose = false;
        this.mItemsInvalidated = false;
        this.mDragInProgress = false;
        this.mDeleteFolderOnDropCompleted = false;
        this.mSuppressFolderDeletion = false;
        this.mItemAddedBackToSelfViaIcon = false;
        this.mIsEditingName = false;
        this.mScrollHintDir = -1;
        this.mCurrentScrollDir = -1;
        this.mReorderAlarmListener = new OnAlarmListener() { // from class: com.android.launcher3.folder.Folder.8
            @Override // com.android.launcher3.OnAlarmListener
            public void onAlarm(Alarm alarm) {
                Folder.this.mContent.realTimeReorder(Folder.this.mEmptyCellRank, Folder.this.mTargetRank);
                Folder folder = Folder.this;
                folder.mEmptyCellRank = folder.mTargetRank;
            }
        };
        this.mOnExitAlarmListener = new OnAlarmListener() { // from class: com.android.launcher3.folder.Folder.9
            @Override // com.android.launcher3.OnAlarmListener
            public void onAlarm(Alarm alarm) {
                Folder.this.completeDragExit();
            }
        };
        setAlwaysDrawnWithCacheEnabled(false);
        setLocaleDependentFields(getResources(), false);
        this.mLauncher = Launcher.getLauncher(context);
        setFocusableInTouchMode(true);
        WorkspaceItemInfo workspaceItemInfo = new WorkspaceItemInfo();
        this.mAddAppItem = workspaceItemInfo;
        workspaceItemInfo.id = -2;
        workspaceItemInfo.itemType = 7;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        FolderPagedView folderPagedView = (FolderPagedView) findViewById(R.id.folder_content);
        this.mContent = folderPagedView;
        folderPagedView.setFolder(this);
        this.mPageIndicator = (PageIndicatorDots) findViewById(R.id.folder_page_indicator);
        ExtendedEditText extendedEditText = (ExtendedEditText) findViewById(R.id.folder_name);
        this.mFolderName = extendedEditText;
        extendedEditText.setOnBackKeyListener(this);
        this.mFolderName.setOnFocusChangeListener(this);
        this.mFolderName.setOnEditorActionListener(this);
        this.mFolderName.setSelectAllOnFocus(true);
        ExtendedEditText extendedEditText2 = this.mFolderName;
        extendedEditText2.setInputType((extendedEditText2.getInputType() & (-32769) & (-524289)) | 8192);
        this.mFolderName.forceDisableSuggestions(true);
        View viewFindViewById = findViewById(R.id.folder_footer);
        this.mFooter = viewFindViewById;
        viewFindViewById.measure(0, 0);
        this.mFooterHeight = this.mFooter.getMeasuredHeight();
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View v) {
        if (this.mLauncher.isDraggingEnabled()) {
            return startDrag(v, new DragOptions());
        }
        return true;
    }

    public boolean startDrag(View v, DragOptions options) {
        Object tag = v.getTag();
        int i = 1;
        if (tag instanceof WorkspaceItemInfo) {
            this.mEmptyCellRank = ((WorkspaceItemInfo) tag).rank;
            this.mCurrentDragView = v;
            this.mDragController.addDragListener(this);
            if (options.isAccessibleDrag) {
                this.mDragController.addDragListener(new AccessibleDragListenerAdapter(this.mContent, i) { // from class: com.android.launcher3.folder.Folder.1
                    @Override // com.android.launcher3.accessibility.AccessibleDragListenerAdapter
                    protected void enableAccessibleDrag(boolean enable) {
                        super.enableAccessibleDrag(enable);
                        Folder.this.mFooter.setImportantForAccessibility(enable ? 4 : 0);
                    }
                });
            }
            this.mLauncher.getWorkspace().beginDragShared(v, this, options);
        }
        return true;
    }

    @Override // com.android.launcher3.dragndrop.DragController.DragListener
    public void onDragStart(DropTarget.DragObject dragObject, DragOptions options) {
        this.mInfo.remove(this.mAddAppItem, true);
        if (dragObject.dragSource != this) {
            return;
        }
        this.mContent.removeItem(this.mCurrentDragView);
        if (dragObject.dragInfo instanceof WorkspaceItemInfo) {
            this.mItemsInvalidated = true;
            SuppressInfoChanges suppressInfoChanges = new SuppressInfoChanges();
            try {
                this.mInfo.remove((WorkspaceItemInfo) dragObject.dragInfo, true);
                suppressInfoChanges.close();
            } catch (Throwable th) {
                try {
                    suppressInfoChanges.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        this.mDragInProgress = true;
        this.mItemAddedBackToSelfViaIcon = false;
    }

    @Override // com.android.launcher3.dragndrop.DragController.DragListener
    public void onDragEnd() {
        if (this.mState == 2 && !this.mInfo.contents.contains(this.mAddAppItem)) {
            this.mInfo.add(this.mAddAppItem, true);
        }
        if (this.mIsExternalDrag && this.mDragInProgress) {
            completeDragExit();
        }
        this.mDragInProgress = false;
        this.mDragController.removeDragListener(this);
    }

    public boolean isEditingName() {
        return this.mIsEditingName;
    }

    public void startEditingFolderName() {
        post(new Runnable() { // from class: com.android.launcher3.folder.Folder.2
            @Override // java.lang.Runnable
            public void run() {
                Folder.this.mFolderName.setHint("");
                Folder.this.mIsEditingName = true;
            }
        });
    }

    @Override // com.android.launcher3.ExtendedEditText.OnBackKeyListener
    public boolean onBackKey() {
        String string = this.mFolderName.getText().toString();
        this.mInfo.setTitle(string);
        this.mLauncher.getModelWriter().updateItemInDatabase(this.mInfo);
        this.mFolderName.setHint(sDefaultFolderName.contentEquals(string) ? sHintText : null);
        AccessibilityManagerCompat.sendCustomAccessibilityEvent(this, 32, getContext().getString(R.string.folder_renamed, string));
        this.mFolderName.clearFocus();
        Selection.setSelection(this.mFolderName.getText(), 0, 0);
        this.mIsEditingName = false;
        return true;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId != 6) {
            return false;
        }
        this.mFolderName.dispatchBackKey();
        return true;
    }

    public FolderIcon getFolderIcon() {
        return this.mFolderIcon;
    }

    public void setDragController(DragController dragController) {
        this.mDragController = dragController;
    }

    public void setFolderIcon(FolderIcon icon) {
        this.mFolderIcon = icon;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        requestFocus();
        super.onAttachedToWindow();
    }

    @Override // android.view.View
    public View focusSearch(int direction) {
        return FocusFinder.getInstance().findNextFocus(this, null, direction);
    }

    public FolderInfo getInfo() {
        return this.mInfo;
    }

    void bind(FolderInfo info) {
        this.mInfo = info;
        ArrayList<WorkspaceItemInfo> arrayList = info.contents;
        Collections.sort(arrayList, ITEM_POS_COMPARATOR);
        this.mContent.bindItems(arrayList);
        if (((BaseDragLayer.LayoutParams) getLayoutParams()) == null) {
            BaseDragLayer.LayoutParams layoutParams = new BaseDragLayer.LayoutParams(0, 0);
            layoutParams.customPosition = true;
            setLayoutParams(layoutParams);
        }
        centerAboutIcon();
        this.mItemsInvalidated = true;
        updateTextViewFocus();
        this.mInfo.addListener(this);
        if (!sDefaultFolderName.contentEquals(this.mInfo.title)) {
            this.mFolderName.setText(this.mInfo.title);
            this.mFolderName.setHint((CharSequence) null);
        } else {
            this.mFolderName.setText("");
            this.mFolderName.setHint(sHintText);
        }
        this.mFolderIcon.post(new Runnable() { // from class: com.android.launcher3.folder.Folder.3
            @Override // java.lang.Runnable
            public void run() {
                if (Folder.this.getItemCount() <= 1) {
                    Folder.this.replaceFolderWithFinalItem();
                }
            }
        });
    }

    static Folder fromXml(Launcher launcher) {
        return (Folder) launcher.getLayoutInflater().inflate(R.layout.user_folder_icon_normalized, (ViewGroup) null);
    }

    private void startAnimation(final AnimatorSet a) {
        AnimatorSet animatorSet = this.mCurrentAnimator;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.mCurrentAnimator.cancel();
        }
        Workspace workspace = this.mLauncher.getWorkspace();
        final CellLayout cellLayout = (CellLayout) workspace.getChildAt(workspace.getCurrentPage());
        final boolean zShouldUseHardwareLayerForAnimation = shouldUseHardwareLayerForAnimation(cellLayout);
        final boolean zIsHardwareLayerEnabled = cellLayout.isHardwareLayerEnabled();
        a.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.folder.Folder.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                if (zShouldUseHardwareLayerForAnimation) {
                    cellLayout.enableHardwareLayer(true);
                }
                Folder.this.mState = 1;
                Folder.this.mCurrentAnimator = a;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                if (zShouldUseHardwareLayerForAnimation) {
                    cellLayout.enableHardwareLayer(zIsHardwareLayerEnabled);
                }
                Folder.this.mCurrentAnimator = null;
            }
        });
        a.start();
    }

    private boolean shouldUseHardwareLayerForAnimation(CellLayout currentCellLayout) {
        ShortcutAndWidgetContainer shortcutsAndWidgets = currentCellLayout.getShortcutsAndWidgets();
        int i = 0;
        for (int childCount = shortcutsAndWidgets.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = shortcutsAndWidgets.getChildAt(childCount);
            if (childAt instanceof AppWidgetHostView) {
                return false;
            }
            if (childAt instanceof FolderIcon) {
                i++;
            }
        }
        return i >= 10;
    }

    public void animateOpen() {
        Folder open = getOpen(this.mLauncher);
        if (open != null && open != this) {
            open.close(true);
        }
        if (!this.mInfo.contents.contains(this.mAddAppItem) && !this.mDragInProgress) {
            this.mInfo.add(this.mAddAppItem, false);
        }
        this.mIsOpen = true;
        DragLayer dragLayer = this.mLauncher.getDragLayer();
        if (getParent() == null) {
            dragLayer.addView(this);
            this.mDragController.addDropTarget(this);
        }
        this.mContent.completePendingPageChanges();
        if (!this.mDragInProgress) {
            this.mContent.snapToPageImmediately(0);
        }
        this.mDeleteFolderOnDropCompleted = false;
        centerAboutIcon();
        AnimatorSet animator = new FolderAnimationManager(this, true).getAnimator();
        animator.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.folder.Folder.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Folder.this.mFolderIcon.setBackgroundVisible(false);
                Folder.this.mFolderIcon.drawLeaveBehindIfExists();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Folder.this.mState = 2;
                Folder.this.announceAccessibilityChanges();
                Folder.this.mLauncher.getUserEventDispatcher().resetElapsedContainerMillis("folder opened");
                Folder.this.mContent.setFocusOnFirstChild();
            }
        });
        if (this.mContent.getPageCount() > 1 && !this.mInfo.hasOption(4)) {
            float desiredWidth = (((this.mContent.getDesiredWidth() - this.mFooter.getPaddingLeft()) - this.mFooter.getPaddingRight()) - this.mFolderName.getPaint().measureText(this.mFolderName.getText().toString())) / 2.0f;
            ExtendedEditText extendedEditText = this.mFolderName;
            if (this.mContent.mIsRtl) {
                desiredWidth = -desiredWidth;
            }
            extendedEditText.setTranslationX(desiredWidth);
            this.mPageIndicator.prepareEntryAnimation();
            final boolean z = true ^ this.mDragInProgress;
            animator.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.folder.Folder.6
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    Folder.this.mFolderName.animate().setDuration(633L).translationX(0.0f).setInterpolator(AnimationUtils.loadInterpolator(Folder.this.mLauncher, 17563661));
                    Folder.this.mPageIndicator.playEntryAnimation();
                    if (z) {
                        Folder.this.mInfo.setOption(4, true, Folder.this.mLauncher.getModelWriter());
                    }
                }
            });
        } else {
            this.mFolderName.setTranslationX(0.0f);
        }
        this.mPageIndicator.stopAllAnimations();
        startAnimation(animator);
        if (this.mDragController.isDragging()) {
            this.mDragController.forceTouchMove();
        }
        FolderPagedView folderPagedView = this.mContent;
        folderPagedView.verifyVisibleHighResIcons(folderPagedView.getNextPage());
    }

    public void beginExternalDrag() {
        this.mEmptyCellRank = this.mContent.allocateRankForNewItem();
        this.mIsExternalDrag = true;
        this.mDragInProgress = true;
        this.mDragController.addDragListener(this);
    }

    @Override // com.android.launcher3.AbstractFloatingView
    protected void handleClose(boolean animate) {
        AnimatorSet animatorSet;
        this.mIsOpen = false;
        if (!animate && (animatorSet = this.mCurrentAnimator) != null && animatorSet.isRunning()) {
            this.mCurrentAnimator.cancel();
        }
        if (isEditingName()) {
            this.mFolderName.dispatchBackKey();
        }
        FolderIcon folderIcon = this.mFolderIcon;
        if (folderIcon != null) {
            folderIcon.clearLeaveBehindIfExists();
        }
        if (animate) {
            animateClosed();
        } else {
            closeComplete(false);
            post(new Runnable() { // from class: com.android.launcher3.folder.-$$Lambda$Folder$i9qu7RMX4e1NC_lN4l2-q2ofSWg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.announceAccessibilityChanges();
                }
            });
        }
        this.mLauncher.getDragLayer().sendAccessibilityEvent(32);
    }

    private void animateClosed() {
        AnimatorSet animator = new FolderAnimationManager(this, false).getAnimator();
        animator.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.folder.Folder.7
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Folder.this.closeComplete(true);
                Folder.this.announceAccessibilityChanges();
            }
        });
        startAnimation(animator);
    }

    @Override // com.android.launcher3.AbstractFloatingView
    protected Pair<View, String> getAccessibilityTarget() {
        return Pair.create(this.mContent, this.mIsOpen ? this.mContent.getAccessibilityDescription() : getContext().getString(R.string.folder_closed));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeComplete(boolean wasAnimated) {
        DragLayer dragLayer = (DragLayer) getParent();
        if (dragLayer != null) {
            dragLayer.removeView(this);
        }
        this.mDragController.removeDropTarget(this);
        clearFocus();
        FolderIcon folderIcon = this.mFolderIcon;
        if (folderIcon != null) {
            folderIcon.setVisibility(0);
            this.mFolderIcon.setBackgroundVisible(true);
            this.mFolderIcon.mFolderName.setTextVisibility(true);
            if (wasAnimated) {
                this.mFolderIcon.animateBgShadowAndStroke();
                this.mFolderIcon.onFolderClose(this.mContent.getCurrentPage());
                if (this.mFolderIcon.hasDot()) {
                    this.mFolderIcon.animateDotScale(0.0f, 1.0f);
                }
                this.mFolderIcon.requestFocus();
            }
        }
        if (this.mRearrangeOnClose) {
            rearrangeChildren();
            this.mRearrangeOnClose = false;
        }
        if (getItemCount() <= 1) {
            boolean z = this.mDragInProgress;
            if (!z && !this.mSuppressFolderDeletion) {
                replaceFolderWithFinalItem();
            } else if (z) {
                this.mDeleteFolderOnDropCompleted = true;
            }
        }
        this.mSuppressFolderDeletion = false;
        clearDragInfo();
        this.mState = 0;
        this.mContent.setCurrentPage(0);
        this.mInfo.remove(this.mAddAppItem, false);
    }

    @Override // com.android.launcher3.DropTarget
    public boolean acceptDrop(DropTarget.DragObject d) {
        int i = d.dragInfo.itemType;
        return i == 0 || i == 1 || i == 6;
    }

    @Override // com.android.launcher3.DropTarget
    public void onDragEnter(DropTarget.DragObject d) {
        this.mPrevTargetRank = -1;
        this.mOnExitAlarm.cancelAlarm();
        this.mScrollAreaOffset = (d.dragView.getDragRegionWidth() / 2) - d.xOffset;
    }

    @Override // android.view.View
    public boolean isLayoutRtl() {
        return getLayoutDirection() == 1;
    }

    private int getTargetRank(DropTarget.DragObject d, float[] recycle) {
        float[] visualCenter = d.getVisualCenter(recycle);
        return this.mContent.findNearestArea(((int) visualCenter[0]) - getPaddingLeft(), ((int) visualCenter[1]) - getPaddingTop());
    }

    @Override // com.android.launcher3.DropTarget
    public void onDragOver(DropTarget.DragObject d) {
        if (this.mScrollPauseAlarm.alarmPending()) {
            return;
        }
        float[] fArr = new float[2];
        int targetRank = getTargetRank(d, fArr);
        this.mTargetRank = targetRank;
        if (targetRank != this.mPrevTargetRank) {
            this.mReorderAlarm.cancelAlarm();
            this.mReorderAlarm.setOnAlarmListener(this.mReorderAlarmListener);
            this.mReorderAlarm.setAlarm(250L);
            this.mPrevTargetRank = this.mTargetRank;
            if (d.stateAnnouncer != null) {
                d.stateAnnouncer.announce(getContext().getString(R.string.move_to_position, Integer.valueOf(this.mTargetRank + 1)));
            }
        }
        float f = fArr[0];
        int nextPage = this.mContent.getNextPage();
        float cellWidth = this.mContent.getCurrentCellLayout().getCellWidth() * ICON_OVERSCROLL_WIDTH_FACTOR;
        boolean z = f < cellWidth;
        boolean z2 = f > ((float) getWidth()) - cellWidth;
        if (nextPage > 0 && (!this.mContent.mIsRtl ? z : z2)) {
            showScrollHint(0, d);
            return;
        }
        if (nextPage < this.mContent.getPageCount() - 1 && (!this.mContent.mIsRtl ? z2 : z)) {
            showScrollHint(1, d);
            return;
        }
        this.mOnScrollHintAlarm.cancelAlarm();
        if (this.mScrollHintDir != -1) {
            this.mContent.clearScrollHint();
            this.mScrollHintDir = -1;
        }
    }

    private void showScrollHint(int direction, DropTarget.DragObject d) {
        if (this.mScrollHintDir != direction) {
            this.mContent.showScrollHint(direction);
            this.mScrollHintDir = direction;
        }
        if (this.mOnScrollHintAlarm.alarmPending() && this.mCurrentScrollDir == direction) {
            return;
        }
        this.mCurrentScrollDir = direction;
        this.mOnScrollHintAlarm.cancelAlarm();
        this.mOnScrollHintAlarm.setOnAlarmListener(new OnScrollHintListener(d));
        this.mOnScrollHintAlarm.setAlarm(500L);
        this.mReorderAlarm.cancelAlarm();
        this.mTargetRank = this.mEmptyCellRank;
    }

    public void completeDragExit() {
        if (this.mIsOpen) {
            close(true);
            this.mRearrangeOnClose = true;
        } else if (this.mState == 1) {
            this.mRearrangeOnClose = true;
        } else {
            rearrangeChildren();
            clearDragInfo();
        }
    }

    private void clearDragInfo() {
        this.mCurrentDragView = null;
        this.mIsExternalDrag = false;
    }

    @Override // com.android.launcher3.DropTarget
    public void onDragExit(DropTarget.DragObject d) {
        if (!d.dragComplete) {
            this.mOnExitAlarm.setOnAlarmListener(this.mOnExitAlarmListener);
            this.mOnExitAlarm.setAlarm(400L);
        }
        this.mReorderAlarm.cancelAlarm();
        this.mOnScrollHintAlarm.cancelAlarm();
        this.mScrollPauseAlarm.cancelAlarm();
        if (this.mScrollHintDir != -1) {
            this.mContent.clearScrollHint();
            this.mScrollHintDir = -1;
        }
    }

    @Override // com.android.launcher3.DropTarget
    public void prepareAccessibilityDrop() {
        if (this.mReorderAlarm.alarmPending()) {
            this.mReorderAlarm.cancelAlarm();
            this.mReorderAlarmListener.onAlarm(this.mReorderAlarm);
        }
    }

    @Override // com.android.launcher3.DragSource
    public void onDropCompleted(final View target, final DropTarget.DragObject d, final boolean success) {
        if (success) {
            if (this.mDeleteFolderOnDropCompleted && !this.mItemAddedBackToSelfViaIcon && target != this) {
                replaceFolderWithFinalItem();
            }
        } else {
            WorkspaceItemInfo workspaceItemInfo = (WorkspaceItemInfo) d.dragInfo;
            View view = this.mCurrentDragView;
            View viewCreateNewView = (view == null || view.getTag() != workspaceItemInfo) ? this.mContent.createNewView(workspaceItemInfo) : this.mCurrentDragView;
            ArrayList<View> itemsInReadingOrder = getItemsInReadingOrder();
            itemsInReadingOrder.add(workspaceItemInfo.rank, viewCreateNewView);
            this.mContent.arrangeChildren(itemsInReadingOrder, itemsInReadingOrder.size());
            this.mItemsInvalidated = true;
            SuppressInfoChanges suppressInfoChanges = new SuppressInfoChanges();
            try {
                this.mFolderIcon.onDrop(d, true);
                suppressInfoChanges.close();
            } catch (Throwable th) {
                try {
                    suppressInfoChanges.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (target != this && this.mOnExitAlarm.alarmPending()) {
            this.mOnExitAlarm.cancelAlarm();
            if (!success) {
                this.mSuppressFolderDeletion = true;
            }
            this.mScrollPauseAlarm.cancelAlarm();
            completeDragExit();
        }
        this.mDeleteFolderOnDropCompleted = false;
        this.mDragInProgress = false;
        this.mItemAddedBackToSelfViaIcon = false;
        this.mCurrentDragView = null;
        updateItemLocationsInDatabaseBatch();
        if (getItemCount() <= this.mContent.itemsPerPage()) {
            this.mInfo.setOption(4, false, this.mLauncher.getModelWriter());
        }
    }

    private void updateItemLocationsInDatabaseBatch() {
        ArrayList<View> itemsInReadingOrder = getItemsInReadingOrder();
        ArrayList<ItemInfo> arrayList = new ArrayList<>();
        for (int i = 0; i < itemsInReadingOrder.size(); i++) {
            ItemInfo itemInfo = (ItemInfo) itemsInReadingOrder.get(i).getTag();
            itemInfo.rank = i;
            arrayList.add(itemInfo);
        }
        this.mLauncher.getModelWriter().moveItemsInDatabase(arrayList, this.mInfo.id, 0);
    }

    public void notifyDrop() {
        if (this.mDragInProgress) {
            this.mItemAddedBackToSelfViaIcon = true;
        }
    }

    @Override // com.android.launcher3.DropTarget
    public boolean isDropEnabled() {
        return this.mState != 1;
    }

    private void centerAboutIcon() {
        int iMax;
        DeviceProfile deviceProfile = this.mLauncher.getDeviceProfile();
        BaseDragLayer.LayoutParams layoutParams = (BaseDragLayer.LayoutParams) getLayoutParams();
        DragLayer dragLayer = this.mLauncher.getDragLayer();
        int folderWidth = getFolderWidth();
        int folderHeight = getFolderHeight();
        FolderIcon folderIcon = this.mFolderIcon;
        Rect rect = sTempRect;
        dragLayer.getDescendantRectRelativeToSelf(folderIcon, rect);
        int i = folderWidth / 2;
        int iCenterX = rect.centerX() - i;
        int i2 = folderHeight / 2;
        int iCenterY = rect.centerY() - i2;
        if (this.mLauncher.getStateManager().getState().overviewUi) {
            dragLayer.getDescendantRectRelativeToSelf(this.mLauncher.getOverviewPanel(), rect);
        } else {
            this.mLauncher.getWorkspace().getPageAreaRelativeToDragLayer(rect);
        }
        int iMin = Math.min(Math.max(rect.left, iCenterX), rect.right - folderWidth);
        int iMin2 = Math.min(Math.max(rect.top, iCenterY), rect.bottom - folderHeight);
        int paddingLeft = this.mLauncher.getWorkspace().getPaddingLeft() + getPaddingLeft();
        if (deviceProfile.isPhone && deviceProfile.availableWidthPx - folderWidth < paddingLeft * 4) {
            iMin = (deviceProfile.availableWidthPx - folderWidth) / 2;
        } else if (folderWidth >= rect.width()) {
            iMin = rect.left + ((rect.width() - folderWidth) / 2);
        }
        if (folderHeight >= rect.height()) {
            iMax = rect.top + ((rect.height() - folderHeight) / 2);
        } else {
            Rect absoluteOpenFolderBounds = deviceProfile.getAbsoluteOpenFolderBounds();
            iMin = Math.max(absoluteOpenFolderBounds.left, Math.min(iMin, absoluteOpenFolderBounds.right - folderWidth));
            iMax = Math.max(absoluteOpenFolderBounds.top, Math.min(iMin2, absoluteOpenFolderBounds.bottom - folderHeight));
        }
        setPivotX(i + (iCenterX - iMin));
        setPivotY(i2 + (iCenterY - iMax));
        this.mFolderIconPivotX = (int) (this.mFolderIcon.getMeasuredWidth() * ((r5 * 1.0f) / folderWidth));
        this.mFolderIconPivotY = (int) (this.mFolderIcon.getMeasuredHeight() * ((r6 * 1.0f) / folderHeight));
        layoutParams.width = folderWidth;
        layoutParams.height = folderHeight;
        layoutParams.x = iMin;
        layoutParams.y = iMax;
    }

    public float getPivotXForIconAnimation() {
        return this.mFolderIconPivotX;
    }

    public float getPivotYForIconAnimation() {
        return this.mFolderIconPivotY;
    }

    private int getContentAreaHeight() {
        DeviceProfile deviceProfile = this.mLauncher.getDeviceProfile();
        return Math.max(Math.min((deviceProfile.availableHeightPx - deviceProfile.getTotalWorkspacePadding().y) - this.mFooterHeight, this.mContent.getDesiredHeight()), 5);
    }

    private int getContentAreaWidth() {
        return Math.max(this.mContent.getDesiredWidth(), 5);
    }

    private int getFolderWidth() {
        return getPaddingLeft() + getPaddingRight() + this.mContent.getDesiredWidth();
    }

    private int getFolderHeight() {
        return getFolderHeight(getContentAreaHeight());
    }

    private int getFolderHeight(int contentAreaHeight) {
        return getPaddingTop() + getPaddingBottom() + contentAreaHeight + this.mFooterHeight;
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int contentAreaWidth = getContentAreaWidth();
        int contentAreaHeight = getContentAreaHeight();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(contentAreaWidth, 1073741824);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(contentAreaHeight, 1073741824);
        this.mContent.setFixedSize(contentAreaWidth, contentAreaHeight);
        this.mContent.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        if (this.mContent.getChildCount() > 0) {
            int cellWidth = (this.mContent.getPageAt(0).getCellWidth() - this.mLauncher.getDeviceProfile().iconSizePx) / 2;
            this.mFooter.setPadding(this.mContent.getPaddingLeft() + cellWidth, this.mFooter.getPaddingTop(), this.mContent.getPaddingRight() + cellWidth, this.mFooter.getPaddingBottom());
        }
        this.mFooter.measure(iMakeMeasureSpec, View.MeasureSpec.makeMeasureSpec(this.mFooterHeight, 1073741824));
        setMeasuredDimension(getPaddingLeft() + getPaddingRight() + contentAreaWidth, getFolderHeight(contentAreaHeight));
    }

    public void rearrangeChildren() {
        rearrangeChildren(-1);
    }

    public void rearrangeChildren(int itemCount) {
        ArrayList<View> itemsInReadingOrder = getItemsInReadingOrder();
        this.mContent.arrangeChildren(itemsInReadingOrder, Math.max(itemCount, itemsInReadingOrder.size()));
        this.mItemsInvalidated = true;
    }

    public int getItemCount() {
        return this.mContent.getItemCount();
    }

    void replaceFolderWithFinalItem() {
        Runnable runnable = new Runnable() { // from class: com.android.launcher3.folder.Folder.10
            @Override // java.lang.Runnable
            public void run() {
                int size = Folder.this.mInfo.contents.size();
                if (size <= 1) {
                    View viewCreateShortcut = null;
                    if (size == 1) {
                        CellLayout cellLayout = Folder.this.mLauncher.getCellLayout(Folder.this.mInfo.container, Folder.this.mInfo.screenId);
                        WorkspaceItemInfo workspaceItemInfoRemove = Folder.this.mInfo.contents.remove(0);
                        viewCreateShortcut = Folder.this.mLauncher.createShortcut(cellLayout, workspaceItemInfoRemove);
                        Folder.this.mLauncher.getModelWriter().addOrMoveItemInDatabase(workspaceItemInfoRemove, Folder.this.mInfo.container, Folder.this.mInfo.screenId, Folder.this.mInfo.cellX, Folder.this.mInfo.cellY);
                    }
                    Folder.this.mLauncher.removeItem(Folder.this.mFolderIcon, Folder.this.mInfo, true);
                    if (Folder.this.mFolderIcon instanceof DropTarget) {
                        Folder.this.mDragController.removeDropTarget((DropTarget) Folder.this.mFolderIcon);
                    }
                    if (viewCreateShortcut != null) {
                        Folder.this.mLauncher.getWorkspace().addInScreenFromBind(viewCreateShortcut, Folder.this.mInfo);
                        viewCreateShortcut.requestFocus();
                    }
                }
            }
        };
        if (this.mContent.getLastItem() != null) {
            this.mFolderIcon.performDestroyAnimation(runnable);
        } else {
            runnable.run();
        }
        this.mDestroyed = true;
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    public void updateTextViewFocus() {
        View firstItem = this.mContent.getFirstItem();
        final View lastItem = this.mContent.getLastItem();
        if (firstItem == null || lastItem == null) {
            return;
        }
        this.mFolderName.setNextFocusDownId(lastItem.getId());
        this.mFolderName.setNextFocusRightId(lastItem.getId());
        this.mFolderName.setNextFocusLeftId(lastItem.getId());
        this.mFolderName.setNextFocusUpId(lastItem.getId());
        this.mFolderName.setNextFocusForwardId(firstItem.getId());
        setNextFocusDownId(firstItem.getId());
        setNextFocusRightId(firstItem.getId());
        setNextFocusLeftId(firstItem.getId());
        setNextFocusUpId(firstItem.getId());
        setOnKeyListener(new View.OnKeyListener() { // from class: com.android.launcher3.folder.Folder.11
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == 61 && event.hasModifiers(1)) && Folder.this.isFocused()) {
                    return lastItem.requestFocus();
                }
                return false;
            }
        });
    }

    @Override // com.android.launcher3.DropTarget
    public void onDrop(DropTarget.DragObject d, DragOptions options) {
        View viewCreateAndAddViewForRank;
        if (!this.mContent.rankOnCurrentPage(this.mEmptyCellRank)) {
            this.mTargetRank = getTargetRank(d, null);
            this.mReorderAlarmListener.onAlarm(this.mReorderAlarm);
            this.mOnScrollHintAlarm.cancelAlarm();
            this.mScrollPauseAlarm.cancelAlarm();
        }
        this.mContent.completePendingPageChanges();
        PendingAddShortcutInfo pendingAddShortcutInfo = d.dragInfo instanceof PendingAddShortcutInfo ? (PendingAddShortcutInfo) d.dragInfo : null;
        WorkspaceItemInfo workspaceItemInfoCreateWorkspaceItemInfo = pendingAddShortcutInfo != null ? pendingAddShortcutInfo.activityInfo.createWorkspaceItemInfo() : null;
        if (pendingAddShortcutInfo != null && workspaceItemInfoCreateWorkspaceItemInfo == null) {
            pendingAddShortcutInfo.container = this.mInfo.id;
            pendingAddShortcutInfo.rank = this.mEmptyCellRank;
            this.mLauncher.addPendingItem(pendingAddShortcutInfo, pendingAddShortcutInfo.container, pendingAddShortcutInfo.screenId, null, pendingAddShortcutInfo.spanX, pendingAddShortcutInfo.spanY);
            d.deferDragViewCleanupPostAnimation = false;
            this.mRearrangeOnClose = true;
        } else {
            if (workspaceItemInfoCreateWorkspaceItemInfo == null) {
                if (d.dragInfo instanceof AppInfo) {
                    workspaceItemInfoCreateWorkspaceItemInfo = ((AppInfo) d.dragInfo).makeWorkspaceItem();
                } else {
                    workspaceItemInfoCreateWorkspaceItemInfo = (WorkspaceItemInfo) d.dragInfo;
                }
            }
            if (this.mIsExternalDrag) {
                viewCreateAndAddViewForRank = this.mContent.createAndAddViewForRank(workspaceItemInfoCreateWorkspaceItemInfo, this.mEmptyCellRank);
                this.mLauncher.getModelWriter().addOrMoveItemInDatabase(workspaceItemInfoCreateWorkspaceItemInfo, this.mInfo.id, 0, workspaceItemInfoCreateWorkspaceItemInfo.cellX, workspaceItemInfoCreateWorkspaceItemInfo.cellY);
                if (d.dragSource != this) {
                    updateItemLocationsInDatabaseBatch();
                }
                this.mIsExternalDrag = false;
            } else {
                viewCreateAndAddViewForRank = this.mCurrentDragView;
                this.mContent.addViewForRank(viewCreateAndAddViewForRank, workspaceItemInfoCreateWorkspaceItemInfo, this.mEmptyCellRank);
            }
            if (d.dragView.hasDrawn()) {
                float scaleX = getScaleX();
                float scaleY = getScaleY();
                setScaleX(1.0f);
                setScaleY(1.0f);
                this.mLauncher.getDragLayer().animateViewIntoPosition(d.dragView, viewCreateAndAddViewForRank, null);
                setScaleX(scaleX);
                setScaleY(scaleY);
            } else {
                d.deferDragViewCleanupPostAnimation = false;
                viewCreateAndAddViewForRank.setVisibility(0);
            }
            this.mItemsInvalidated = true;
            rearrangeChildren();
            SuppressInfoChanges suppressInfoChanges = new SuppressInfoChanges();
            try {
                this.mInfo.add(workspaceItemInfoCreateWorkspaceItemInfo, false);
                suppressInfoChanges.close();
            } catch (Throwable th) {
                try {
                    suppressInfoChanges.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        this.mDragInProgress = false;
        if (this.mContent.getPageCount() > 1) {
            this.mInfo.setOption(4, true, this.mLauncher.getModelWriter());
        }
        this.mLauncher.getStateManager().goToState(LauncherState.NORMAL, 500L);
        if (d.stateAnnouncer != null) {
            d.stateAnnouncer.completeAction(R.string.item_moved);
        }
    }

    public void hideItem(WorkspaceItemInfo info) {
        getViewForInfo(info).setVisibility(4);
    }

    public void showItem(WorkspaceItemInfo info) {
        getViewForInfo(info).setVisibility(0);
    }

    @Override // com.android.launcher3.FolderInfo.FolderListener
    public void onAdd(WorkspaceItemInfo item, int rank) {
        View viewCreateAndAddViewForRank = this.mContent.createAndAddViewForRank(item, rank);
        this.mLauncher.getModelWriter().addOrMoveItemInDatabase(item, this.mInfo.id, 0, item.cellX, item.cellY);
        ArrayList<View> arrayList = new ArrayList<>(getItemsInReadingOrder());
        arrayList.add(rank, viewCreateAndAddViewForRank);
        this.mContent.arrangeChildren(arrayList, arrayList.size());
        this.mItemsInvalidated = true;
    }

    @Override // com.android.launcher3.FolderInfo.FolderListener
    public void onRemove(WorkspaceItemInfo item) {
        this.mItemsInvalidated = true;
        this.mContent.removeItem(getViewForInfo(item));
        if (this.mState == 1) {
            this.mRearrangeOnClose = true;
        } else {
            rearrangeChildren();
        }
        if (getItemCount() <= 1) {
            if (this.mIsOpen) {
                close(true);
            } else {
                replaceFolderWithFinalItem();
            }
        }
    }

    private View getViewForInfo(final WorkspaceItemInfo item) {
        return this.mContent.iterateOverItems(new Workspace.ItemOperator() { // from class: com.android.launcher3.folder.Folder.12
            @Override // com.android.launcher3.Workspace.ItemOperator
            public boolean evaluate(ItemInfo info, View view) {
                return info == item;
            }
        });
    }

    @Override // com.android.launcher3.FolderInfo.FolderListener
    public void onItemsChanged(boolean animate) {
        updateTextViewFocus();
    }

    @Override // com.android.launcher3.FolderInfo.FolderListener
    public void prepareAutoUpdate() {
        close(false);
    }

    public ArrayList<View> getItemsInReadingOrder() {
        if (this.mItemsInvalidated) {
            this.mItemsInReadingOrder.clear();
            this.mContent.iterateOverItems(new Workspace.ItemOperator() { // from class: com.android.launcher3.folder.Folder.13
                @Override // com.android.launcher3.Workspace.ItemOperator
                public boolean evaluate(ItemInfo info, View view) {
                    Folder.this.mItemsInReadingOrder.add(view);
                    return false;
                }
            });
            this.mItemsInvalidated = false;
        }
        return this.mItemsInReadingOrder;
    }

    public List<BubbleTextView> getItemsOnPage(int page) {
        ArrayList<View> itemsInReadingOrder = getItemsInReadingOrder();
        int pageCount = this.mContent.getPageCount() - 1;
        int size = itemsInReadingOrder.size();
        int iItemsPerPage = this.mContent.itemsPerPage();
        int i = page == pageCount ? size - (iItemsPerPage * page) : iItemsPerPage;
        int i2 = page * iItemsPerPage;
        int iMin = Math.min(i2 + i, itemsInReadingOrder.size());
        ArrayList arrayList = new ArrayList(i);
        while (i2 < iMin) {
            arrayList.add((BubbleTextView) itemsInReadingOrder.get(i2));
            i2++;
        }
        return arrayList;
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View v, boolean hasFocus) {
        ExtendedEditText extendedEditText = this.mFolderName;
        if (v == extendedEditText) {
            if (hasFocus) {
                startEditingFolderName();
            } else {
                extendedEditText.dispatchBackKey();
            }
        }
    }

    @Override // com.android.launcher3.DropTarget
    public void getHitRectRelativeToDragLayer(Rect outRect) {
        getHitRect(outRect);
        outRect.left -= this.mScrollAreaOffset;
        outRect.right += this.mScrollAreaOffset;
    }

    @Override // com.android.launcher3.logging.StatsLogUtils.LogContainerProvider
    public void fillInLogContainerData(View v, ItemInfo info, LauncherLogProto.Target target, LauncherLogProto.Target targetParent) {
        target.gridX = info.cellX;
        target.gridY = info.cellY;
        target.pageIndex = this.mContent.getCurrentPage();
        targetParent.containerType = 3;
    }

    private class OnScrollHintListener implements OnAlarmListener {
        private final DropTarget.DragObject mDragObject;

        OnScrollHintListener(DropTarget.DragObject object) {
            this.mDragObject = object;
        }

        @Override // com.android.launcher3.OnAlarmListener
        public void onAlarm(Alarm alarm) {
            if (Folder.this.mCurrentScrollDir == 0) {
                Folder.this.mContent.scrollLeft();
                Folder.this.mScrollHintDir = -1;
            } else {
                if (Folder.this.mCurrentScrollDir != 1) {
                    return;
                }
                Folder.this.mContent.scrollRight();
                Folder.this.mScrollHintDir = -1;
            }
            Folder.this.mCurrentScrollDir = -1;
            Folder.this.mScrollPauseAlarm.setOnAlarmListener(Folder.this.new OnScrollFinishedListener(this.mDragObject));
            Folder.this.mScrollPauseAlarm.setAlarm(900L);
        }
    }

    private class OnScrollFinishedListener implements OnAlarmListener {
        private final DropTarget.DragObject mDragObject;

        OnScrollFinishedListener(DropTarget.DragObject object) {
            this.mDragObject = object;
        }

        @Override // com.android.launcher3.OnAlarmListener
        public void onAlarm(Alarm alarm) {
            Folder.this.onDragOver(this.mDragObject);
        }
    }

    private class SuppressInfoChanges implements AutoCloseable {
        SuppressInfoChanges() {
            Folder.this.mInfo.removeListener(Folder.this);
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            Folder.this.mInfo.addListener(Folder.this);
            Folder.this.updateTextViewFocus();
        }
    }

    public static Folder getOpen(Launcher launcher) {
        return (Folder) getOpenView(launcher, 1);
    }

    @Override // com.android.launcher3.AbstractFloatingView
    public void logActionCommand(int command) {
        this.mLauncher.getUserEventDispatcher().logActionCommand(command, getFolderIcon(), getLogContainerType());
    }

    @Override // com.android.launcher3.AbstractFloatingView
    public boolean onBackPressed() {
        if (isEditingName()) {
            this.mFolderName.dispatchBackKey();
            return true;
        }
        super.onBackPressed();
        return true;
    }

    @Override // com.android.launcher3.util.TouchController
    public boolean onControllerInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            DragLayer dragLayer = this.mLauncher.getDragLayer();
            if (isEditingName()) {
                if (dragLayer.isEventOverView(this.mFolderName, ev)) {
                    return false;
                }
                this.mFolderName.dispatchBackKey();
                return true;
            }
            if (!dragLayer.isEventOverView(this, ev)) {
                if (this.mLauncher.getAccessibilityDelegate().isInAccessibleDrag()) {
                    if (!dragLayer.isEventOverView(this.mLauncher.getDropTargetBar(), ev)) {
                        return true;
                    }
                } else {
                    this.mLauncher.getUserEventDispatcher().logActionTapOutside(LoggerUtils.newContainerTarget(3));
                    close(true);
                    return true;
                }
            }
        }
        return false;
    }

    public static void setLocaleDependentFields(Resources res, boolean force) {
        if (sDefaultFolderName == null || force) {
            sDefaultFolderName = res.getString(R.string.folder_name);
        }
        if (sHintText == null || force) {
            sHintText = res.getString(R.string.folder_hint_text);
        }
    }

    @Override // com.android.launcher3.views.ClipPathView
    public void setClipPath(Path clipPath) {
        this.mClipPath = clipPath;
        invalidate();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (this.mClipPath != null) {
            int iSave = canvas.save();
            canvas.clipPath(this.mClipPath);
            super.draw(canvas);
            canvas.restoreToCount(iSave);
            return;
        }
        super.draw(canvas);
    }

    public void showAddAppsView() {
        Launcher.getLauncher(getContext()).getSelectedFolderApp().showAddAppsView(this, new SelectedFolderApp.OnAddAppsListener() { // from class: com.android.launcher3.folder.Folder.15
            @Override // com.android.launcher3.folder.SelectedFolderApp.OnAddAppsListener
            public void onAdd(ArrayList<WorkspaceItemInfo> items) {
                Folder.this.updateContent(items);
            }
        });
    }

    public ArrayList<WorkspaceItemInfo> getItems() {
        ArrayList<WorkspaceItemInfo> arrayList = new ArrayList<>(this.mInfo.contents.size());
        for (WorkspaceItemInfo workspaceItemInfo : this.mInfo.contents) {
            if (workspaceItemInfo.itemType != 7) {
                arrayList.add(workspaceItemInfo);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateContent(ArrayList<WorkspaceItemInfo> items) {
        ArrayList<WorkspaceItemInfo> items2 = getItems();
        if (items.size() == items2.size() && items.equals(items2)) {
            return;
        }
        for (WorkspaceItemInfo workspaceItemInfo : new ArrayList(this.mInfo.contents)) {
            if (items.contains(workspaceItemInfo)) {
                items.remove(workspaceItemInfo);
            } else {
                this.mInfo.remove(workspaceItemInfo, false);
                if (workspaceItemInfo.itemType != 7) {
                    this.mLauncher.addInScreen(workspaceItemInfo);
                }
            }
        }
        for (WorkspaceItemInfo workspaceItemInfo2 : items) {
            ViewGroup viewGroup = (ViewGroup) this.mLauncher.getCellLayout(workspaceItemInfo2.container, workspaceItemInfo2.screenId).getChildAt(0);
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (true) {
                if (i < childCount) {
                    View childAt = viewGroup.getChildAt(i);
                    if (childAt instanceof BubbleTextView) {
                        BubbleTextView bubbleTextView = (BubbleTextView) childAt;
                        if (workspaceItemInfo2.equals(bubbleTextView.getTag())) {
                            this.mLauncher.removeItem(bubbleTextView, workspaceItemInfo2, false);
                            break;
                        }
                    }
                    i++;
                }
            }
            this.mInfo.add(workspaceItemInfo2, false);
        }
        this.mInfo.add(this.mAddAppItem, false);
        centerAboutIcon();
        this.mLauncher.getWorkspace().removeExtraEmptyScreen(true, true);
    }
}
