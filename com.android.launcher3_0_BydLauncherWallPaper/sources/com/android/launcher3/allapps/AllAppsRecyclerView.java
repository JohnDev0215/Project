package com.android.launcher3.allapps;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.BaseRecyclerView;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.R;
import com.android.launcher3.allapps.AlphabeticalAppsList;
import com.android.launcher3.compat.AccessibilityManagerCompat;
import com.android.launcher3.logging.StatsLogUtils;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.views.RecyclerViewFastScroller;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AllAppsRecyclerView extends BaseRecyclerView implements StatsLogUtils.LogContainerProvider {
    private AlphabeticalAppsList mApps;
    private SparseIntArray mCachedScrollPositions;
    private AllAppsBackgroundDrawable mEmptySearchBackground;
    private int mEmptySearchBackgroundTopOffset;
    private AllAppsFastScrollHelper mFastScrollHelper;
    private final int mNumAppsPerRow;
    private SparseIntArray mViewHeights;

    @Override // android.view.View
    protected float getBottomFadingEdgeStrength() {
        return 0.0f;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    protected boolean isPaddingOffsetRequired() {
        return true;
    }

    public AllAppsRecyclerView(Context context) {
        this(context, null);
    }

    public AllAppsRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AllAppsRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public AllAppsRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        this.mViewHeights = new SparseIntArray();
        this.mCachedScrollPositions = new SparseIntArray();
        this.mEmptySearchBackgroundTopOffset = getResources().getDimensionPixelSize(R.dimen.all_apps_empty_search_bg_top_offset);
        this.mNumAppsPerRow = LauncherAppState.getIDP(context).numColumns;
    }

    public void setApps(AlphabeticalAppsList apps, boolean usingTabs) {
        this.mApps = apps;
        this.mFastScrollHelper = new AllAppsFastScrollHelper(this, apps);
    }

    public AlphabeticalAppsList getApps() {
        return this.mApps;
    }

    private void updatePoolSize() {
        DeviceProfile deviceProfile = Launcher.getLauncher(getContext()).getDeviceProfile();
        RecyclerView.RecycledViewPool recycledViewPool = getRecycledViewPool();
        int iCeil = (int) Math.ceil(deviceProfile.availableHeightPx / deviceProfile.allAppsIconSizePx);
        recycledViewPool.setMaxRecycledViews(4, 1);
        recycledViewPool.setMaxRecycledViews(16, 1);
        recycledViewPool.setMaxRecycledViews(8, 1);
        recycledViewPool.setMaxRecycledViews(2, iCeil * this.mNumAppsPerRow);
        this.mViewHeights.clear();
        this.mViewHeights.put(2, deviceProfile.allAppsCellHeightPx);
    }

    public void scrollToTop() {
        if (this.mScrollbar != null) {
            this.mScrollbar.reattachThumbToScroll();
        }
        scrollToPosition(0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onDraw(Canvas c) {
        AllAppsBackgroundDrawable allAppsBackgroundDrawable = this.mEmptySearchBackground;
        if (allAppsBackgroundDrawable != null && allAppsBackgroundDrawable.getAlpha() > 0) {
            this.mEmptySearchBackground.draw(c);
        }
        super.onDraw(c);
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable who) {
        return who == this.mEmptySearchBackground || super.verifyDrawable(who);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        updateEmptySearchBackgroundBounds();
        updatePoolSize();
    }

    @Override // com.android.launcher3.logging.StatsLogUtils.LogContainerProvider
    public void fillInLogContainerData(View v, ItemInfo info, LauncherLogProto.Target target, LauncherLogProto.Target targetParent) {
        if (this.mApps.hasFilter()) {
            targetParent.containerType = 8;
        } else {
            targetParent.containerType = 4;
        }
    }

    public void onSearchResultsChanged() {
        scrollToTop();
        if (this.mApps.hasNoFilteredResults()) {
            if (this.mEmptySearchBackground == null) {
                AllAppsBackgroundDrawable allAppsBackgroundDrawable = new AllAppsBackgroundDrawable(getContext());
                this.mEmptySearchBackground = allAppsBackgroundDrawable;
                allAppsBackgroundDrawable.setAlpha(0);
                this.mEmptySearchBackground.setCallback(this);
                updateEmptySearchBackgroundBounds();
            }
            this.mEmptySearchBackground.animateBgAlpha(1.0f, 150);
            return;
        }
        AllAppsBackgroundDrawable allAppsBackgroundDrawable2 = this.mEmptySearchBackground;
        if (allAppsBackgroundDrawable2 != null) {
            allAppsBackgroundDrawable2.setBgAlpha(0.0f);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent e) {
        AllAppsBackgroundDrawable allAppsBackgroundDrawable;
        boolean zOnInterceptTouchEvent = super.onInterceptTouchEvent(e);
        if (!zOnInterceptTouchEvent && e.getAction() == 0 && (allAppsBackgroundDrawable = this.mEmptySearchBackground) != null && allAppsBackgroundDrawable.getAlpha() > 0) {
            this.mEmptySearchBackground.setHotspot(e.getX(), e.getY());
        }
        return zOnInterceptTouchEvent;
    }

    @Override // com.android.launcher3.BaseRecyclerView
    public String scrollToPositionAtProgress(float touchFraction) {
        if (this.mApps.getNumAppRows() == 0) {
            return "";
        }
        stopScroll();
        List<AlphabeticalAppsList.FastScrollSectionInfo> fastScrollerSections = this.mApps.getFastScrollerSections();
        AlphabeticalAppsList.FastScrollSectionInfo fastScrollSectionInfo = fastScrollerSections.get(0);
        int i = 1;
        while (i < fastScrollerSections.size()) {
            AlphabeticalAppsList.FastScrollSectionInfo fastScrollSectionInfo2 = fastScrollerSections.get(i);
            if (fastScrollSectionInfo2.touchFraction > touchFraction) {
                break;
            }
            i++;
            fastScrollSectionInfo = fastScrollSectionInfo2;
        }
        this.mFastScrollHelper.smoothScrollToSection(getCurrentScrollY(), getAvailableScrollHeight(), fastScrollSectionInfo);
        return fastScrollSectionInfo.sectionName;
    }

    @Override // com.android.launcher3.BaseRecyclerView
    public void onFastScrollCompleted() {
        super.onFastScrollCompleted();
        this.mFastScrollHelper.onFastScrollCompleted();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        super.setAdapter(adapter);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() { // from class: com.android.launcher3.allapps.AllAppsRecyclerView.1
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                AllAppsRecyclerView.this.mCachedScrollPositions.clear();
            }
        });
        this.mFastScrollHelper.onSetAdapter((AllAppsGridAdapter) adapter);
    }

    @Override // android.view.View
    protected int getTopPaddingOffset() {
        return -getPaddingTop();
    }

    @Override // com.android.launcher3.BaseRecyclerView
    public void onUpdateScrollbar(int dy) {
        int iMin;
        AlphabeticalAppsList alphabeticalAppsList = this.mApps;
        if (alphabeticalAppsList == null) {
            return;
        }
        if (alphabeticalAppsList.getAdapterItems().isEmpty() || this.mNumAppsPerRow == 0) {
            this.mScrollbar.setThumbOffsetY(-1);
            return;
        }
        int currentScrollY = getCurrentScrollY();
        if (currentScrollY < 0) {
            this.mScrollbar.setThumbOffsetY(-1);
            return;
        }
        int availableScrollBarHeight = getAvailableScrollBarHeight();
        int availableScrollHeight = getAvailableScrollHeight();
        if (availableScrollHeight <= 0) {
            this.mScrollbar.setThumbOffsetY(-1);
            return;
        }
        if (this.mScrollbar.isThumbDetached()) {
            if (this.mScrollbar.isDraggingThumb()) {
                return;
            }
            int i = (int) ((currentScrollY / availableScrollHeight) * availableScrollBarHeight);
            int thumbOffsetY = this.mScrollbar.getThumbOffsetY();
            int i2 = i - thumbOffsetY;
            if (i2 * dy > 0.0f) {
                if (dy < 0) {
                    iMin = Math.max((int) ((dy * thumbOffsetY) / i), i2);
                } else {
                    iMin = Math.min((int) ((dy * (availableScrollBarHeight - thumbOffsetY)) / (availableScrollBarHeight - i)), i2);
                }
                int iMax = Math.max(0, Math.min(availableScrollBarHeight, thumbOffsetY + iMin));
                this.mScrollbar.setThumbOffsetY(iMax);
                if (i == iMax) {
                    this.mScrollbar.reattachThumbToScroll();
                    return;
                }
                return;
            }
            this.mScrollbar.setThumbOffsetY(thumbOffsetY);
            return;
        }
        synchronizeScrollBarThumbOffsetToViewScroll(currentScrollY, availableScrollHeight);
    }

    @Override // com.android.launcher3.BaseRecyclerView
    public boolean supportsFastScrolling() {
        return !this.mApps.hasFilter();
    }

    @Override // com.android.launcher3.BaseRecyclerView
    public int getCurrentScrollY() {
        View childAt;
        int childPosition;
        if (this.mApps.getAdapterItems().isEmpty() || this.mNumAppsPerRow == 0 || getChildCount() == 0 || (childPosition = getChildPosition((childAt = getChildAt(0)))) == -1) {
            return -1;
        }
        return getPaddingTop() + getCurrentScrollY(childPosition, getLayoutManager().getDecoratedTop(childAt));
    }

    public int getCurrentScrollY(int position, int offset) {
        List<AlphabeticalAppsList.AdapterItem> adapterItems = this.mApps.getAdapterItems();
        AlphabeticalAppsList.AdapterItem adapterItem = position < adapterItems.size() ? adapterItems.get(position) : null;
        int i = this.mCachedScrollPositions.get(position, -1);
        if (i < 0) {
            int i2 = 0;
            for (int i3 = 0; i3 < position; i3++) {
                AlphabeticalAppsList.AdapterItem adapterItem2 = adapterItems.get(i3);
                if (AllAppsGridAdapter.isIconViewType(adapterItem2.viewType)) {
                    if (adapterItem != null && adapterItem.viewType == adapterItem2.viewType && adapterItem.rowIndex == adapterItem2.rowIndex) {
                        break;
                    }
                    if (adapterItem2.rowAppIndex == 0) {
                        i2 += this.mViewHeights.get(adapterItem2.viewType, 0);
                    }
                } else {
                    int measuredHeight = this.mViewHeights.get(adapterItem2.viewType);
                    if (measuredHeight == 0) {
                        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = findViewHolderForAdapterPosition(i3);
                        if (viewHolderFindViewHolderForAdapterPosition == null) {
                            RecyclerView.ViewHolder viewHolderCreateViewHolder = getAdapter().createViewHolder(this, adapterItem2.viewType);
                            getAdapter().onBindViewHolder(viewHolderCreateViewHolder, i3);
                            viewHolderCreateViewHolder.itemView.measure(0, 0);
                            measuredHeight = viewHolderCreateViewHolder.itemView.getMeasuredHeight();
                            getRecycledViewPool().putRecycledView(viewHolderCreateViewHolder);
                        } else {
                            measuredHeight = viewHolderFindViewHolderForAdapterPosition.itemView.getMeasuredHeight();
                        }
                    }
                    i2 += measuredHeight;
                }
            }
            this.mCachedScrollPositions.put(position, i2);
            i = i2;
        }
        return i - offset;
    }

    @Override // com.android.launcher3.BaseRecyclerView
    protected int getAvailableScrollHeight() {
        return ((getPaddingTop() + getCurrentScrollY(getAdapter().getItemCount(), 0)) - getHeight()) + getPaddingBottom();
    }

    @Override // com.android.launcher3.BaseRecyclerView
    public int getScrollBarTop() {
        return getResources().getDimensionPixelOffset(R.dimen.all_apps_header_top_padding);
    }

    @Override // com.android.launcher3.BaseRecyclerView
    public RecyclerViewFastScroller getScrollbar() {
        return this.mScrollbar;
    }

    private void updateEmptySearchBackgroundBounds() {
        if (this.mEmptySearchBackground == null) {
            return;
        }
        int measuredWidth = (getMeasuredWidth() - this.mEmptySearchBackground.getIntrinsicWidth()) / 2;
        int i = this.mEmptySearchBackgroundTopOffset;
        AllAppsBackgroundDrawable allAppsBackgroundDrawable = this.mEmptySearchBackground;
        allAppsBackgroundDrawable.setBounds(measuredWidth, i, allAppsBackgroundDrawable.getIntrinsicWidth() + measuredWidth, this.mEmptySearchBackground.getIntrinsicHeight() + i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == 0) {
            AccessibilityManagerCompat.sendScrollFinishedEventToTest(getContext());
        }
    }
}
