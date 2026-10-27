package com.android.launcher3.folder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import com.android.launcher3.BubbleTextView;
import com.android.launcher3.CellLayout;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.ExtendedEditText;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.PagedView;
import com.android.launcher3.R;
import com.android.launcher3.ShortcutAndWidgetContainer;
import com.android.launcher3.Utilities;
import com.android.launcher3.Workspace;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.keyboard.ViewGroupFocusHelper;
import com.android.launcher3.pageindicators.PageIndicatorDots;
import com.android.launcher3.touch.ItemClickHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class FolderPagedView extends PagedView<PageIndicatorDots> {
    private static final int REORDER_ANIMATION_DURATION = 230;
    private static final float SCROLL_HINT_FRACTION = 0.07f;
    private static final int START_VIEW_REORDER_DELAY = 30;
    private static final String TAG = "FolderPagedView";
    private static final float VIEW_REORDER_DELAY_FACTOR = 0.9f;
    private static final int[] sTmpArray = new int[2];
    private int mAllocatedContentSize;
    private final ViewGroupFocusHelper mFocusIndicatorHelper;
    private Folder mFolder;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private int mGridCountX;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private int mGridCountY;
    private final LayoutInflater mInflater;
    public final boolean mIsRtl;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private final int mMaxCountX;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private final int mMaxCountY;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    private final int mMaxItemsPerPage;
    final ArrayMap<View, Runnable> mPendingAnimations;

    public FolderPagedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPendingAnimations = new ArrayMap<>();
        LauncherAppState.getIDP(context);
        this.mMaxCountX = 3;
        this.mMaxCountY = 3;
        this.mMaxItemsPerPage = 3 * 3;
        this.mInflater = LayoutInflater.from(context);
        this.mIsRtl = Utilities.isRtl(getResources());
        setImportantForAccessibility(1);
        this.mFocusIndicatorHelper = new ViewGroupFocusHelper(this);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T extends android.view.View & com.android.launcher3.pageindicators.PageIndicator, android.view.View] */
    public void setFolder(Folder folder) {
        this.mFolder = folder;
        this.mPageIndicator = folder.findViewById(R.id.folder_page_indicator);
        initParentViews(folder);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void calculateGridSize(int r3, int r4, int r5, int r6, int r7, int r8, int[] r9) {
        /*
            r0 = 0
            r1 = 1
            if (r3 < r8) goto L8
            r4 = r6
            r5 = r7
            r8 = r1
            goto L9
        L8:
            r8 = r0
        L9:
            if (r8 != 0) goto L4d
            int r8 = r4 * r5
            if (r8 >= r3) goto L26
            if (r4 <= r5) goto L13
            if (r5 != r7) goto L19
        L13:
            if (r4 >= r6) goto L19
            int r8 = r4 + 1
            r2 = r8
            goto L20
        L19:
            if (r5 >= r7) goto L1f
            int r8 = r5 + 1
            r2 = r4
            goto L21
        L1f:
            r2 = r4
        L20:
            r8 = r5
        L21:
            if (r8 != 0) goto L42
            int r8 = r8 + 1
            goto L42
        L26:
            int r8 = r5 + (-1)
            int r2 = r8 * r4
            if (r2 < r3) goto L34
            if (r5 < r4) goto L34
            int r8 = java.lang.Math.max(r0, r8)
            r2 = r4
            goto L42
        L34:
            int r8 = r4 + (-1)
            int r2 = r8 * r5
            if (r2 < r3) goto L40
            int r8 = java.lang.Math.max(r0, r8)
            r2 = r8
            goto L41
        L40:
            r2 = r4
        L41:
            r8 = r5
        L42:
            if (r2 != r4) goto L48
            if (r8 != r5) goto L48
            r4 = r1
            goto L49
        L48:
            r4 = r0
        L49:
            r5 = r8
            r8 = r4
            r4 = r2
            goto L9
        L4d:
            r9[r0] = r4
            r9[r1] = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.folder.FolderPagedView.calculateGridSize(int, int, int, int, int, int, int[]):void");
    }

    public void setupContentDimensions(int count) {
        this.mAllocatedContentSize = count;
        this.mGridCountX = this.mMaxCountX;
        this.mGridCountY = this.mMaxCountY;
        for (int pageCount = getPageCount() - 1; pageCount >= 0; pageCount--) {
            getPageAt(pageCount).setGridSize(this.mGridCountX, this.mGridCountY);
        }
    }

    @Override // com.android.launcher3.PagedView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        this.mFocusIndicatorHelper.draw(canvas);
        super.dispatchDraw(canvas);
    }

    public void bindItems(ArrayList<WorkspaceItemInfo> items) {
        ArrayList<View> arrayList = new ArrayList<>();
        Iterator<WorkspaceItemInfo> it = items.iterator();
        while (it.hasNext()) {
            arrayList.add(createNewView(it.next()));
        }
        arrangeChildren(arrayList, arrayList.size(), false);
    }

    public void allocateSpaceForRank(int rank) {
        ArrayList<View> arrayList = new ArrayList<>(this.mFolder.getItemsInReadingOrder());
        arrayList.add(rank, null);
        arrangeChildren(arrayList, arrayList.size(), false);
    }

    public int allocateRankForNewItem() {
        int itemCount = getItemCount();
        allocateSpaceForRank(itemCount);
        setCurrentPage(itemCount / this.mMaxItemsPerPage);
        return itemCount;
    }

    public View createAndAddViewForRank(WorkspaceItemInfo item, int rank) {
        View viewCreateNewView = createNewView(item);
        allocateSpaceForRank(rank);
        addViewForRank(viewCreateNewView, item, rank);
        return viewCreateNewView;
    }

    public void addViewForRank(View view, WorkspaceItemInfo item, int rank) {
        int i = this.mMaxItemsPerPage;
        int i2 = rank % i;
        item.rank = rank;
        item.cellX = i2 % this.mGridCountX;
        item.cellY = i2 / this.mGridCountX;
        CellLayout.LayoutParams layoutParams = (CellLayout.LayoutParams) view.getLayoutParams();
        layoutParams.cellX = item.cellX;
        layoutParams.cellY = item.cellY;
        getPageAt(rank / i).addViewToCellLayout(view, -1, item.getViewId(), layoutParams, true);
    }

    public View createNewView(WorkspaceItemInfo item) {
        if (item.itemType == 7) {
            return createAddAppView(item);
        }
        BubbleTextView bubbleTextView = (BubbleTextView) this.mInflater.inflate(R.layout.folder_application, (ViewGroup) null, false);
        bubbleTextView.applyFromWorkspaceItem(item);
        bubbleTextView.setHapticFeedbackEnabled(false);
        bubbleTextView.setOnClickListener(ItemClickHandler.INSTANCE);
        bubbleTextView.setOnLongClickListener(this.mFolder);
        bubbleTextView.setOnFocusChangeListener(this.mFocusIndicatorHelper);
        bubbleTextView.setLayoutParams(new CellLayout.LayoutParams(item.cellX, item.cellY, item.spanX, item.spanY));
        return bubbleTextView;
    }

    private View createAddAppView(WorkspaceItemInfo item) {
        BubbleTextView bubbleTextView = (BubbleTextView) this.mInflater.inflate(R.layout.launcher_folder_add_app_layout, (ViewGroup) null, false);
        int iconSize = bubbleTextView.getIconSize();
        Drawable drawable = getResources().getDrawable(R.drawable.launcher_add_folder_app_ic, getContext().getTheme());
        drawable.setBounds(0, 0, iconSize, iconSize);
        bubbleTextView.setCompoundDrawables(null, drawable, null, null);
        bubbleTextView.setTag(item);
        bubbleTextView.setHapticFeedbackEnabled(false);
        bubbleTextView.setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.folder.FolderPagedView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                FolderPagedView.this.mFolder.showAddAppsView();
            }
        });
        bubbleTextView.setLayoutParams(new CellLayout.LayoutParams(item.cellX, item.cellY, item.spanX, item.spanY));
        return bubbleTextView;
    }

    @Override // com.android.launcher3.PagedView
    public CellLayout getPageAt(int index) {
        return (CellLayout) getChildAt(index);
    }

    public CellLayout getCurrentCellLayout() {
        return getPageAt(getNextPage());
    }

    private CellLayout createAndAddNewPage() {
        DeviceProfile deviceProfile = Launcher.getLauncher(getContext()).getDeviceProfile();
        CellLayout cellLayout = (CellLayout) this.mInflater.inflate(R.layout.folder_page, (ViewGroup) this, false);
        cellLayout.setCellDimensions(deviceProfile.folderCellWidthPx, deviceProfile.folderCellHeightPx);
        cellLayout.getShortcutsAndWidgets().setMotionEventSplittingEnabled(false);
        cellLayout.setInvertIfRtl(true);
        cellLayout.setGridSize(this.mGridCountX, this.mGridCountY);
        addView(cellLayout, -1, generateDefaultLayoutParams());
        return cellLayout;
    }

    @Override // com.android.launcher3.PagedView
    protected int getChildGap() {
        return getPaddingLeft() + getPaddingRight();
    }

    public void setFixedSize(int width, int height) {
        int paddingLeft = width - (getPaddingLeft() + getPaddingRight());
        int paddingTop = height - (getPaddingTop() + getPaddingBottom());
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            ((CellLayout) getChildAt(childCount)).setFixedSize(paddingLeft, paddingTop);
        }
    }

    public void removeItem(View v) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            getPageAt(childCount).removeView(v);
        }
    }

    @Override // android.view.View
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        ((PageIndicatorDots) this.mPageIndicator).setScroll(l, this.mMaxScrollX);
    }

    public void arrangeChildren(ArrayList<View> list, int itemCount) {
        arrangeChildren(list, itemCount, true);
    }

    private void arrangeChildren(ArrayList<View> list, int itemCount, boolean saveChanges) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            CellLayout cellLayout = (CellLayout) getChildAt(i);
            cellLayout.removeAllViews();
            arrayList.add(cellLayout);
        }
        setupContentDimensions(itemCount);
        Iterator it = arrayList.iterator();
        FolderIconPreviewVerifier folderIconPreviewVerifier = new FolderIconPreviewVerifier(Launcher.getLauncher(getContext()).getDeviceProfile().inv);
        folderIconPreviewVerifier.setFolderInfo(this.mFolder.getInfo());
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        CellLayout cellLayoutCreateAndAddNewPage = null;
        while (i2 < itemCount) {
            View view = list.size() > i2 ? list.get(i2) : null;
            if (cellLayoutCreateAndAddNewPage == null || i3 >= this.mMaxItemsPerPage) {
                if (it.hasNext()) {
                    cellLayoutCreateAndAddNewPage = (CellLayout) it.next();
                } else {
                    cellLayoutCreateAndAddNewPage = createAndAddNewPage();
                }
                i3 = 0;
            }
            if (view != null) {
                CellLayout.LayoutParams layoutParams = (CellLayout.LayoutParams) view.getLayoutParams();
                int i5 = this.mGridCountX;
                int i6 = i3 % i5;
                int i7 = i3 / i5;
                ItemInfo itemInfo = (ItemInfo) view.getTag();
                if (itemInfo.cellX != i6 || itemInfo.cellY != i7 || itemInfo.rank != i4) {
                    itemInfo.cellX = i6;
                    itemInfo.cellY = i7;
                    itemInfo.rank = i4;
                    if (saveChanges) {
                        this.mFolder.mLauncher.getModelWriter().addOrMoveItemInDatabase(itemInfo, this.mFolder.mInfo.id, 0, itemInfo.cellX, itemInfo.cellY);
                    }
                }
                layoutParams.cellX = itemInfo.cellX;
                layoutParams.cellY = itemInfo.cellY;
                cellLayoutCreateAndAddNewPage.addViewToCellLayout(view, -1, itemInfo.getViewId(), layoutParams, true);
                if (folderIconPreviewVerifier.isItemInPreview(i4) && (view instanceof BubbleTextView)) {
                    ((BubbleTextView) view).verifyHighRes();
                }
            }
            i4++;
            i3++;
            i2++;
        }
        int i8 = 1;
        boolean z = false;
        while (it.hasNext()) {
            removeView((View) it.next());
            z = true;
        }
        if (z) {
            setCurrentPage(0);
        }
        setEnableOverscroll(getPageCount() > 1);
        ((PageIndicatorDots) this.mPageIndicator).setVisibility(getPageCount() <= 1 ? 8 : 0);
        ExtendedEditText extendedEditText = this.mFolder.mFolderName;
        if (getPageCount() > 1) {
            i8 = this.mIsRtl ? 5 : 3;
        }
        extendedEditText.setGravity(i8);
    }

    public int getDesiredWidth() {
        if (getPageCount() > 0) {
            return getPageAt(0).getDesiredWidth() + getPaddingLeft() + getPaddingRight();
        }
        return 0;
    }

    public int getDesiredHeight() {
        if (getPageCount() > 0) {
            return getPageAt(0).getDesiredHeight() + getPaddingTop() + getPaddingBottom();
        }
        return 0;
    }

    public int getItemCount() {
        int childCount = getChildCount() - 1;
        if (childCount < 0) {
            return 0;
        }
        return getPageAt(childCount).getShortcutsAndWidgets().getChildCount() + (childCount * this.mMaxItemsPerPage);
    }

    public int findNearestArea(int pixelX, int pixelY) {
        int nextPage = getNextPage();
        CellLayout pageAt = getPageAt(nextPage);
        int[] iArr = sTmpArray;
        pageAt.findNearestArea(pixelX, pixelY, 1, 1, iArr);
        if (this.mFolder.isLayoutRtl()) {
            iArr[0] = (pageAt.getCountX() - iArr[0]) - 1;
        }
        return Math.min(this.mAllocatedContentSize - 1, (nextPage * this.mMaxItemsPerPage) + (iArr[1] * this.mGridCountX) + iArr[0]);
    }

    public View getFirstItem() {
        if (getChildCount() < 1) {
            return null;
        }
        ShortcutAndWidgetContainer shortcutsAndWidgets = getCurrentCellLayout().getShortcutsAndWidgets();
        if (this.mGridCountX > 0) {
            return shortcutsAndWidgets.getChildAt(0, 0);
        }
        return shortcutsAndWidgets.getChildAt(0);
    }

    public View getLastItem() {
        if (getChildCount() < 1) {
            return null;
        }
        ShortcutAndWidgetContainer shortcutsAndWidgets = getCurrentCellLayout().getShortcutsAndWidgets();
        int childCount = shortcutsAndWidgets.getChildCount() - 1;
        int i = this.mGridCountX;
        if (i > 0) {
            return shortcutsAndWidgets.getChildAt(childCount % i, childCount / i);
        }
        return shortcutsAndWidgets.getChildAt(childCount);
    }

    public View iterateOverItems(Workspace.ItemOperator op) {
        for (int i = 0; i < getChildCount(); i++) {
            CellLayout pageAt = getPageAt(i);
            for (int i2 = 0; i2 < pageAt.getCountY(); i2++) {
                for (int i3 = 0; i3 < pageAt.getCountX(); i3++) {
                    View childAt = pageAt.getChildAt(i3, i2);
                    if (childAt != null && op.evaluate((ItemInfo) childAt.getTag(), childAt)) {
                        return childAt;
                    }
                }
            }
        }
        return null;
    }

    public String getAccessibilityDescription() {
        return getContext().getString(R.string.folder_opened, Integer.valueOf(this.mGridCountX), Integer.valueOf(this.mGridCountY));
    }

    public void setFocusOnFirstChild() {
        View childAt = getCurrentCellLayout().getChildAt(0, 0);
        if (childAt != null) {
            childAt.requestFocus();
        }
    }

    @Override // com.android.launcher3.PagedView
    protected void notifyPageSwitchListener(int prevPage) {
        super.notifyPageSwitchListener(prevPage);
        Folder folder = this.mFolder;
        if (folder != null) {
            folder.updateTextViewFocus();
        }
    }

    public void showScrollHint(int direction) {
        int scrollForPage = (getScrollForPage(getNextPage()) + ((int) (((direction == 0) ^ this.mIsRtl ? -0.07f : 0.07f) * getWidth()))) - getScrollX();
        if (scrollForPage != 0) {
            this.mScroller.setInterpolator(Interpolators.DEACCEL);
            this.mScroller.startScroll(getScrollX(), scrollForPage, 500);
            invalidate();
        }
    }

    public void clearScrollHint() {
        if (getScrollX() != getScrollForPage(getNextPage())) {
            snapToPage(getNextPage());
        }
    }

    public void completePendingPageChanges() {
        if (this.mPendingAnimations.isEmpty()) {
            return;
        }
        for (Map.Entry entry : new ArrayMap(this.mPendingAnimations).entrySet()) {
            ((View) entry.getKey()).animate().cancel();
            ((Runnable) entry.getValue()).run();
        }
    }

    public boolean rankOnCurrentPage(int rank) {
        return rank / this.mMaxItemsPerPage == getNextPage();
    }

    @Override // com.android.launcher3.PagedView
    protected void onPageBeginTransition() {
        super.onPageBeginTransition();
        verifyVisibleHighResIcons(getCurrentPage() - 1);
        verifyVisibleHighResIcons(getCurrentPage() + 1);
    }

    public void verifyVisibleHighResIcons(int pageNo) {
        CellLayout pageAt = getPageAt(pageNo);
        if (pageAt != null) {
            ShortcutAndWidgetContainer shortcutsAndWidgets = pageAt.getShortcutsAndWidgets();
            for (int childCount = shortcutsAndWidgets.getChildCount() - 1; childCount >= 0; childCount--) {
                BubbleTextView bubbleTextView = (BubbleTextView) shortcutsAndWidgets.getChildAt(childCount);
                bubbleTextView.verifyHighRes();
                Drawable drawable = bubbleTextView.getCompoundDrawables()[1];
                if (drawable != null) {
                    drawable.setCallback(bubbleTextView);
                }
            }
        }
    }

    public int getAllocatedContentSize() {
        return this.mAllocatedContentSize;
    }

    public void realTimeReorder(int empty, int target) {
        int i;
        int i2;
        final int i3 = empty;
        completePendingPageChanges();
        int nextPage = getNextPage();
        int i4 = this.mMaxItemsPerPage;
        int i5 = target / i4;
        int i6 = target % i4;
        if (i5 != nextPage) {
            Log.e(TAG, "Cannot animate when the target cell is invisible");
        }
        int i7 = this.mMaxItemsPerPage;
        int i8 = i3 % i7;
        int i9 = i3 / i7;
        if (target == i3) {
            return;
        }
        int i10 = -1;
        int i11 = 0;
        if (target > i3) {
            if (i9 < nextPage) {
                i10 = nextPage * i7;
                i8 = 0;
            } else {
                i3 = -1;
            }
            i2 = 1;
        } else {
            if (i9 > nextPage) {
                i = ((nextPage + 1) * i7) - 1;
                i8 = i7 - 1;
            } else {
                i3 = -1;
                i = -1;
            }
            i10 = i;
            i2 = -1;
        }
        while (i3 != i10) {
            int i12 = i3 + i2;
            int i13 = this.mMaxItemsPerPage;
            int i14 = i12 / i13;
            int i15 = i12 % i13;
            int i16 = this.mGridCountX;
            int i17 = i15 % i16;
            int i18 = i15 / i16;
            CellLayout pageAt = getPageAt(i14);
            final View childAt = pageAt.getChildAt(i17, i18);
            if (childAt != null) {
                if (nextPage != i14) {
                    pageAt.removeView(childAt);
                    addViewForRank(childAt, (WorkspaceItemInfo) childAt.getTag(), i3);
                } else {
                    final float translationX = childAt.getTranslationX();
                    Runnable runnable = new Runnable() { // from class: com.android.launcher3.folder.FolderPagedView.2
                        @Override // java.lang.Runnable
                        public void run() {
                            FolderPagedView.this.mPendingAnimations.remove(childAt);
                            childAt.setTranslationX(translationX);
                            ((CellLayout) childAt.getParent().getParent()).removeView(childAt);
                            FolderPagedView folderPagedView = FolderPagedView.this;
                            View view = childAt;
                            folderPagedView.addViewForRank(view, (WorkspaceItemInfo) view.getTag(), i3);
                        }
                    };
                    childAt.animate().translationXBy((i2 > 0) ^ this.mIsRtl ? -childAt.getWidth() : childAt.getWidth()).setDuration(230L).setStartDelay(0L).withEndAction(runnable);
                    this.mPendingAnimations.put(childAt, runnable);
                }
            }
            i3 = i12;
        }
        if ((i6 - i8) * i2 <= 0) {
            return;
        }
        CellLayout pageAt2 = getPageAt(nextPage);
        float f = 30.0f;
        while (i8 != i6) {
            int i19 = i8 + i2;
            int i20 = this.mGridCountX;
            View childAt2 = pageAt2.getChildAt(i19 % i20, i19 / i20);
            if (childAt2 != null) {
                ((ItemInfo) childAt2.getTag()).rank -= i2;
            }
            int i21 = this.mGridCountX;
            if (pageAt2.animateChildToPosition(childAt2, i8 % i21, i8 / i21, 230, i11, true, true)) {
                int i22 = (int) (i11 + f);
                f *= VIEW_REORDER_DELAY_FACTOR;
                i11 = i22;
            }
            i8 = i19;
        }
    }

    public int itemsPerPage() {
        return this.mMaxItemsPerPage;
    }
}
