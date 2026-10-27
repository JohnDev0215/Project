package com.android.launcher3.folder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.launcher3.Alarm;
import com.android.launcher3.AppInfo;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.CellLayout;
import com.android.launcher3.CheckLongPressHelper;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.DropTarget;
import com.android.launcher3.FolderInfo;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.OnAlarmListener;
import com.android.launcher3.R;
import com.android.launcher3.SimpleOnStylusPressListener;
import com.android.launcher3.StylusEventHelper;
import com.android.launcher3.Utilities;
import com.android.launcher3.Workspace;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.dot.FolderDotInfo;
import com.android.launcher3.dragndrop.BaseItemDragListener;
import com.android.launcher3.dragndrop.DragLayer;
import com.android.launcher3.dragndrop.DragView;
import com.android.launcher3.icons.DotRenderer;
import com.android.launcher3.touch.ItemClickHandler;
import com.android.launcher3.widget.PendingAddShortcutInfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class FolderIcon extends FrameLayout implements FolderInfo.FolderListener {
    private static final Property<FolderIcon, Float> DOT_SCALE_PROPERTY = new Property<FolderIcon, Float>(Float.TYPE, "dotScale") { // from class: com.android.launcher3.folder.FolderIcon.1
        @Override // android.util.Property
        public Float get(FolderIcon folderIcon) {
            return Float.valueOf(folderIcon.mDotScale);
        }

        @Override // android.util.Property
        public void set(FolderIcon folderIcon, Float value) {
            folderIcon.mDotScale = value.floatValue();
            folderIcon.invalidate();
        }
    };
    static final int DROP_IN_ANIMATION_DURATION = 400;
    private static final int ON_OPEN_DELAY = 800;
    public static final boolean SPRING_LOADING_ENABLED = true;
    boolean mAnimating;
    PreviewBackground mBackground;
    private boolean mBackgroundIsVisible;
    private List<BubbleTextView> mCurrentPreviewItems;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE, deepExport = true)
    private FolderDotInfo mDotInfo;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE, deepExport = true)
    private DotRenderer.DrawParams mDotParams;
    private DotRenderer mDotRenderer;
    private float mDotScale;
    private Animator mDotScaleAnim;
    Folder mFolder;
    BubbleTextView mFolderName;
    private FolderInfo mInfo;
    Launcher mLauncher;
    private CheckLongPressHelper mLongPressHelper;
    OnAlarmListener mOnOpenListener;
    private Alarm mOpenAlarm;
    private PreviewItemManager mPreviewItemManager;
    ClippedFolderIconLayoutRule mPreviewLayoutRule;
    FolderIconPreviewVerifier mPreviewVerifier;
    private float mSlop;
    private StylusEventHelper mStylusEventHelper;
    private PreviewItemDrawingParams mTmpParams;

    @Override // com.android.launcher3.FolderInfo.FolderListener
    public void prepareAutoUpdate() {
    }

    public FolderIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mBackground = new PreviewBackground();
        this.mBackgroundIsVisible = true;
        this.mTmpParams = new PreviewItemDrawingParams(0.0f, 0.0f, 0.0f, 0.0f);
        this.mCurrentPreviewItems = new ArrayList();
        this.mAnimating = false;
        this.mOpenAlarm = new Alarm();
        this.mDotInfo = new FolderDotInfo();
        this.mOnOpenListener = new OnAlarmListener() { // from class: com.android.launcher3.folder.FolderIcon.2
            @Override // com.android.launcher3.OnAlarmListener
            public void onAlarm(Alarm alarm) {
                FolderIcon.this.mFolder.beginExternalDrag();
                FolderIcon.this.mFolder.animateOpen();
            }
        };
        init();
    }

    public FolderIcon(Context context) {
        super(context);
        this.mBackground = new PreviewBackground();
        this.mBackgroundIsVisible = true;
        this.mTmpParams = new PreviewItemDrawingParams(0.0f, 0.0f, 0.0f, 0.0f);
        this.mCurrentPreviewItems = new ArrayList();
        this.mAnimating = false;
        this.mOpenAlarm = new Alarm();
        this.mDotInfo = new FolderDotInfo();
        this.mOnOpenListener = new OnAlarmListener() { // from class: com.android.launcher3.folder.FolderIcon.2
            @Override // com.android.launcher3.OnAlarmListener
            public void onAlarm(Alarm alarm) {
                FolderIcon.this.mFolder.beginExternalDrag();
                FolderIcon.this.mFolder.animateOpen();
            }
        };
        init();
    }

    private void init() {
        this.mLongPressHelper = new CheckLongPressHelper(this);
        this.mStylusEventHelper = new StylusEventHelper(new SimpleOnStylusPressListener(this), this);
        this.mPreviewLayoutRule = new ClippedFolderIconLayoutRule();
        this.mSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.mPreviewItemManager = new PreviewItemManager(this);
        this.mDotParams = new DotRenderer.DrawParams();
    }

    public static FolderIcon fromXml(int resId, Launcher launcher, ViewGroup group, FolderInfo folderInfo) {
        DeviceProfile wallpaperDeviceProfile = launcher.getWallpaperDeviceProfile();
        FolderIcon folderIcon = (FolderIcon) LayoutInflater.from(group.getContext()).inflate(resId, group, false);
        folderIcon.setClipToPadding(false);
        BubbleTextView bubbleTextView = (BubbleTextView) folderIcon.findViewById(R.id.folder_icon_name);
        folderIcon.mFolderName = bubbleTextView;
        bubbleTextView.setText(folderInfo.title);
        folderIcon.mFolderName.setCompoundDrawablePadding(0);
        ((FrameLayout.LayoutParams) folderIcon.mFolderName.getLayoutParams()).topMargin = wallpaperDeviceProfile.iconSizePx + wallpaperDeviceProfile.iconDrawablePaddingPx;
        folderIcon.setTag(folderInfo);
        folderIcon.setOnClickListener(ItemClickHandler.INSTANCE);
        folderIcon.mInfo = folderInfo;
        folderIcon.mLauncher = launcher;
        folderIcon.mDotRenderer = wallpaperDeviceProfile.mDotRenderer;
        folderIcon.setContentDescription(launcher.getString(R.string.folder_name_format, folderInfo.title));
        Folder folderFromXml = Folder.fromXml(launcher);
        folderFromXml.setDragController(launcher.getDragController());
        folderFromXml.setFolderIcon(folderIcon);
        folderFromXml.bind(folderInfo);
        folderIcon.setFolder(folderFromXml);
        folderIcon.setAccessibilityDelegate(launcher.getAccessibilityDelegate());
        folderInfo.addListener(folderIcon);
        folderIcon.setOnFocusChangeListener(launcher.mFocusHandler);
        return folderIcon;
    }

    public void animateBgShadowAndStroke() {
        this.mBackground.fadeInBackgroundShadow();
        this.mBackground.animateBackgroundStroke();
    }

    public BubbleTextView getFolderName() {
        return this.mFolderName;
    }

    public void getPreviewBounds(Rect outBounds) {
        this.mPreviewItemManager.recomputePreviewDrawingParams();
        this.mBackground.getBounds(outBounds);
    }

    public float getBackgroundStrokeWidth() {
        return this.mBackground.getStrokeWidth();
    }

    public Folder getFolder() {
        return this.mFolder;
    }

    private void setFolder(Folder folder) {
        this.mFolder = folder;
        FolderIconPreviewVerifier folderIconPreviewVerifier = new FolderIconPreviewVerifier(this.mLauncher.getDeviceProfile().inv);
        this.mPreviewVerifier = folderIconPreviewVerifier;
        folderIconPreviewVerifier.setFolderInfo(this.mFolder.getInfo());
        updatePreviewItems(false);
    }

    private boolean willAcceptItem(ItemInfo item) {
        int i = item.itemType;
        return ((i != 0 && i != 1 && i != 6) || item == this.mInfo || this.mFolder.isOpen()) ? false : true;
    }

    public boolean acceptDrop(ItemInfo dragInfo) {
        return !this.mFolder.isDestroyed() && willAcceptItem(dragInfo);
    }

    public void addItem(WorkspaceItemInfo item) {
        addItem(item, true);
    }

    public void addItem(WorkspaceItemInfo item, boolean animate) {
        this.mInfo.add(item, animate);
    }

    public void removeItem(WorkspaceItemInfo item, boolean animate) {
        this.mInfo.remove(item, animate);
    }

    public void onDragEnter(ItemInfo dragInfo) {
        if (this.mFolder.isDestroyed() || !willAcceptItem(dragInfo)) {
            return;
        }
        CellLayout.LayoutParams layoutParams = (CellLayout.LayoutParams) getLayoutParams();
        this.mBackground.animateToAccept((CellLayout) getParent().getParent(), layoutParams.cellX, layoutParams.cellY);
        this.mOpenAlarm.setOnAlarmListener(this.mOnOpenListener);
        if ((dragInfo instanceof AppInfo) || (dragInfo instanceof WorkspaceItemInfo) || (dragInfo instanceof PendingAddShortcutInfo)) {
            this.mOpenAlarm.setAlarm(800L);
        }
    }

    public Drawable prepareCreateAnimation(final View destView) {
        return this.mPreviewItemManager.prepareCreateAnimation(destView);
    }

    public void performCreateAnimation(final WorkspaceItemInfo destInfo, final View destView, final WorkspaceItemInfo srcInfo, final DragView srcView, Rect dstRect, float scaleRelativeToDragLayer) {
        prepareCreateAnimation(destView);
        addItem(destInfo);
        this.mPreviewItemManager.createFirstItemAnimation(false, null).start();
        onDrop(srcInfo, srcView, dstRect, scaleRelativeToDragLayer, 1, false);
    }

    public void performDestroyAnimation(Runnable onCompleteRunnable) {
        this.mPreviewItemManager.createFirstItemAnimation(true, onCompleteRunnable).start();
    }

    public void onDragExit() {
        this.mBackground.animateToRest();
        this.mOpenAlarm.cancelAlarm();
    }

    private void onDrop(final WorkspaceItemInfo item, DragView animateView, Rect finalRect, float scaleRelativeToDragLayer, int index, boolean itemReturnedOnFailedDrop) {
        Rect rect;
        float descendantRectRelativeToSelf;
        int i;
        boolean z;
        final int i2;
        int i3 = index;
        item.cellX = -1;
        item.cellY = -1;
        if (animateView != null) {
            DragLayer dragLayer = this.mLauncher.getDragLayer();
            Rect rect2 = new Rect();
            dragLayer.getViewRectRelativeToSelf(animateView, rect2);
            if (finalRect == null) {
                rect = new Rect();
                Workspace workspace = this.mLauncher.getWorkspace();
                workspace.setFinalTransitionTransform();
                float scaleX = getScaleX();
                float scaleY = getScaleY();
                setScaleX(1.0f);
                setScaleY(1.0f);
                descendantRectRelativeToSelf = dragLayer.getDescendantRectRelativeToSelf(this, rect);
                setScaleX(scaleX);
                setScaleY(scaleY);
                workspace.resetTransitionTransform();
            } else {
                rect = finalRect;
                descendantRectRelativeToSelf = scaleRelativeToDragLayer;
            }
            int iMin = Math.min(9, i3 + 1);
            if (itemReturnedOnFailedDrop || i3 >= 9) {
                ArrayList arrayList = new ArrayList(this.mCurrentPreviewItems);
                addItem(item, false);
                this.mCurrentPreviewItems.clear();
                this.mCurrentPreviewItems.addAll(getPreviewItems());
                if (!arrayList.equals(this.mCurrentPreviewItems)) {
                    for (int i4 = 0; i4 < this.mCurrentPreviewItems.size(); i4++) {
                        if (this.mCurrentPreviewItems.get(i4).getTag().equals(item)) {
                            i3 = i4;
                        }
                    }
                    this.mPreviewItemManager.hidePreviewItem(i3, true);
                    this.mPreviewItemManager.onDrop(arrayList, this.mCurrentPreviewItems, item);
                    i = i3;
                    z = true;
                } else {
                    removeItem(item, false);
                    i = i3;
                    z = false;
                }
            } else {
                i = i3;
                z = false;
            }
            if (!z) {
                addItem(item);
            }
            int[] iArr = new int[2];
            float localCenterForIndex = getLocalCenterForIndex(i, iMin, iArr);
            iArr[0] = Math.round(iArr[0] * descendantRectRelativeToSelf);
            iArr[1] = Math.round(iArr[1] * descendantRectRelativeToSelf);
            rect.offset(iArr[0] - (animateView.getMeasuredWidth() / 2), iArr[1] - (animateView.getMeasuredHeight() / 2));
            float f = descendantRectRelativeToSelf * localCenterForIndex;
            int i5 = i;
            dragLayer.animateView(animateView, rect2, rect, i < 9 ? 0.5f : 0.0f, 1.0f, 1.0f, f, f, 400, Interpolators.DEACCEL_2, Interpolators.ACCEL_2, null, 0, null);
            this.mFolder.hideItem(item);
            if (z) {
                i2 = i5;
            } else {
                i2 = i5;
                this.mPreviewItemManager.hidePreviewItem(i2, true);
            }
            postDelayed(new Runnable() { // from class: com.android.launcher3.folder.FolderIcon.3
                @Override // java.lang.Runnable
                public void run() {
                    FolderIcon.this.mPreviewItemManager.hidePreviewItem(i2, false);
                    FolderIcon.this.mFolder.showItem(item);
                    FolderIcon.this.invalidate();
                }
            }, 400L);
            return;
        }
        addItem(item);
    }

    public void onDrop(DropTarget.DragObject d, boolean itemReturnedOnFailedDrop) {
        WorkspaceItemInfo workspaceItemInfo;
        if (d.dragInfo instanceof AppInfo) {
            workspaceItemInfo = ((AppInfo) d.dragInfo).makeWorkspaceItem();
        } else if (d.dragSource instanceof BaseItemDragListener) {
            workspaceItemInfo = new WorkspaceItemInfo((WorkspaceItemInfo) d.dragInfo);
        } else {
            workspaceItemInfo = (WorkspaceItemInfo) d.dragInfo;
        }
        WorkspaceItemInfo workspaceItemInfo2 = workspaceItemInfo;
        this.mFolder.notifyDrop();
        onDrop(workspaceItemInfo2, d.dragView, null, 1.0f, this.mInfo.contents.size(), itemReturnedOnFailedDrop);
    }

    public void setDotInfo(FolderDotInfo dotInfo) {
        updateDotScale(this.mDotInfo.hasDot(), dotInfo.hasDot());
        this.mDotInfo = dotInfo;
    }

    public ClippedFolderIconLayoutRule getLayoutRule() {
        return this.mPreviewLayoutRule;
    }

    private void updateDotScale(boolean wasDotted, boolean isDotted) {
        float f = isDotted ? 1.0f : 0.0f;
        if ((wasDotted ^ isDotted) && isShown()) {
            animateDotScale(f);
            return;
        }
        cancelDotScaleAnim();
        this.mDotScale = f;
        invalidate();
    }

    private void cancelDotScaleAnim() {
        Animator animator = this.mDotScaleAnim;
        if (animator != null) {
            animator.cancel();
        }
    }

    public void animateDotScale(float... dotScales) {
        cancelDotScaleAnim();
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, DOT_SCALE_PROPERTY, dotScales);
        this.mDotScaleAnim = objectAnimatorOfFloat;
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.folder.FolderIcon.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                FolderIcon.this.mDotScaleAnim = null;
            }
        });
        this.mDotScaleAnim.start();
    }

    public boolean hasDot() {
        FolderDotInfo folderDotInfo = this.mDotInfo;
        return folderDotInfo != null && folderDotInfo.hasDot();
    }

    private float getLocalCenterForIndex(int index, int curNumItems, int[] center) {
        PreviewItemDrawingParams previewItemDrawingParamsComputePreviewItemDrawingParams = this.mPreviewItemManager.computePreviewItemDrawingParams(Math.min(9, index), curNumItems, this.mTmpParams);
        this.mTmpParams = previewItemDrawingParamsComputePreviewItemDrawingParams;
        previewItemDrawingParamsComputePreviewItemDrawingParams.transX += this.mBackground.basePreviewOffsetX;
        this.mTmpParams.transY += this.mBackground.basePreviewOffsetY;
        float intrinsicIconSize = this.mPreviewItemManager.getIntrinsicIconSize();
        float f = this.mTmpParams.transX + ((this.mTmpParams.scale * intrinsicIconSize) / 2.0f);
        float f2 = this.mTmpParams.transY + ((this.mTmpParams.scale * intrinsicIconSize) / 2.0f);
        center[0] = Math.round(f);
        center[1] = Math.round(f2);
        return this.mTmpParams.scale;
    }

    public void setFolderBackground(PreviewBackground bg) {
        this.mBackground = bg;
        bg.setInvalidateDelegate(this);
    }

    public void setBackgroundVisible(boolean visible) {
        this.mBackgroundIsVisible = visible;
        invalidate();
    }

    public PreviewBackground getFolderBackground() {
        return this.mBackground;
    }

    public PreviewItemManager getPreviewItemManager() {
        return this.mPreviewItemManager;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mBackgroundIsVisible) {
            this.mPreviewItemManager.recomputePreviewDrawingParams();
            if (!this.mBackground.drawingDelegated()) {
                this.mBackground.drawBackground(canvas);
            }
            Folder folder = this.mFolder;
            if (folder == null) {
                return;
            }
            if (folder.getItemCount() != 0 || this.mAnimating) {
                int iSave = canvas.save();
                canvas.clipPath(this.mBackground.getClipPath());
                this.mPreviewItemManager.draw(canvas);
                canvas.restoreToCount(iSave);
                if (!this.mBackground.drawingDelegated()) {
                    this.mBackground.drawBackgroundStroke(canvas);
                }
                drawDot(canvas);
            }
        }
    }

    public void drawDot(Canvas canvas) {
        FolderDotInfo folderDotInfo = this.mDotInfo;
        if ((folderDotInfo == null || !folderDotInfo.hasDot()) && this.mDotScale <= 0.0f) {
            return;
        }
        Rect rect = this.mDotParams.iconBounds;
        BubbleTextView.getIconBounds(this, rect, this.mLauncher.getWallpaperDeviceProfile().iconSizePx);
        Utilities.scaleRectAboutCenter(rect, this.mBackground.previewSize / rect.width());
        this.mDotParams.scale = Math.max(0.0f, this.mDotScale - this.mBackground.getScaleProgress());
        this.mDotParams.color = this.mBackground.getDotColor();
        this.mDotRenderer.draw(canvas, this.mDotParams);
    }

    public void setTextVisible(boolean visible) {
        if (visible) {
            this.mFolderName.setVisibility(0);
        } else {
            this.mFolderName.setVisibility(4);
        }
    }

    public boolean getTextVisible() {
        return this.mFolderName.getVisibility() == 0;
    }

    public List<BubbleTextView> getPreviewItems() {
        return getPreviewItemsOnPage(0);
    }

    public List<BubbleTextView> getPreviewItemsOnPage(int page) {
        this.mPreviewVerifier.setFolderInfo(this.mFolder.getInfo());
        ArrayList arrayList = new ArrayList();
        List<BubbleTextView> itemsOnPage = this.mFolder.getItemsOnPage(page);
        int size = itemsOnPage.size();
        for (int i = 0; i < size; i++) {
            if (this.mPreviewVerifier.isItemInPreview(page, i)) {
                arrayList.add(itemsOnPage.get(i));
            }
            if (arrayList.size() == 9) {
                break;
            }
        }
        return arrayList;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable who) {
        return this.mPreviewItemManager.verifyDrawable(who) || super.verifyDrawable(who);
    }

    @Override // com.android.launcher3.FolderInfo.FolderListener
    public void onItemsChanged(boolean animate) {
        updatePreviewItems(animate);
        invalidate();
        requestLayout();
    }

    private void updatePreviewItems(boolean animate) {
        this.mPreviewItemManager.updatePreviewItems(animate);
        this.mCurrentPreviewItems.clear();
        this.mCurrentPreviewItems.addAll(getPreviewItems());
    }

    @Override // com.android.launcher3.FolderInfo.FolderListener
    public void onAdd(WorkspaceItemInfo item, int rank) {
        boolean zHasDot = this.mDotInfo.hasDot();
        this.mDotInfo.addDotInfo(this.mLauncher.getDotInfoForItem(item));
        updateDotScale(zHasDot, this.mDotInfo.hasDot());
        invalidate();
        requestLayout();
    }

    @Override // com.android.launcher3.FolderInfo.FolderListener
    public void onRemove(WorkspaceItemInfo item) {
        boolean zHasDot = this.mDotInfo.hasDot();
        this.mDotInfo.subtractDotInfo(this.mLauncher.getDotInfoForItem(item));
        updateDotScale(zHasDot, this.mDotInfo.hasDot());
        invalidate();
        requestLayout();
    }

    @Override // com.android.launcher3.FolderInfo.FolderListener
    public void onTitleChanged(CharSequence title) {
        this.mFolderName.setText(title);
        setContentDescription(getContext().getString(R.string.folder_name_format, title));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            boolean r0 = super.onTouchEvent(r4)
            com.android.launcher3.StylusEventHelper r1 = r3.mStylusEventHelper
            boolean r1 = r1.onMotionEvent(r4)
            r2 = 1
            if (r1 == 0) goto L13
            com.android.launcher3.CheckLongPressHelper r3 = r3.mLongPressHelper
            r3.cancelLongPress()
            return r2
        L13:
            int r1 = r4.getAction()
            if (r1 == 0) goto L3e
            if (r1 == r2) goto L38
            r2 = 2
            if (r1 == r2) goto L22
            r4 = 3
            if (r1 == r4) goto L38
            goto L43
        L22:
            float r1 = r4.getX()
            float r4 = r4.getY()
            float r2 = r3.mSlop
            boolean r4 = com.android.launcher3.Utilities.pointInView(r3, r1, r4, r2)
            if (r4 != 0) goto L43
            com.android.launcher3.CheckLongPressHelper r3 = r3.mLongPressHelper
            r3.cancelLongPress()
            goto L43
        L38:
            com.android.launcher3.CheckLongPressHelper r3 = r3.mLongPressHelper
            r3.cancelLongPress()
            goto L43
        L3e:
            com.android.launcher3.CheckLongPressHelper r3 = r3.mLongPressHelper
            r3.postCheckForLongPress()
        L43:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.folder.FolderIcon.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public void cancelLongPress() {
        super.cancelLongPress();
        this.mLongPressHelper.cancelLongPress();
    }

    public void removeListeners() {
        this.mInfo.removeListener(this);
        this.mInfo.removeListener(this.mFolder);
    }

    public void clearLeaveBehindIfExists() {
        ((CellLayout.LayoutParams) getLayoutParams()).canReorder = true;
        if (this.mInfo.container == -101) {
            ((CellLayout) getParent().getParent()).clearFolderLeaveBehind();
        }
    }

    public void drawLeaveBehindIfExists() {
        CellLayout.LayoutParams layoutParams = (CellLayout.LayoutParams) getLayoutParams();
        layoutParams.canReorder = false;
        if (this.mInfo.container == -101) {
            ((CellLayout) getParent().getParent()).setFolderLeaveBehindCell(layoutParams.cellX, layoutParams.cellY);
        }
    }

    public void onFolderClose(int currentPage) {
        this.mPreviewItemManager.onFolderClose(currentPage);
    }
}
