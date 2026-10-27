package com.android.launcher3.allapps;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Process;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.launcher3.AppInfo;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.DragSource;
import com.android.launcher3.DropTarget;
import com.android.launcher3.Insettable;
import com.android.launcher3.InsettableFrameLayout;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.allapps.AllAppsStore;
import com.android.launcher3.compat.AccessibilityManagerCompat;
import com.android.launcher3.keyboard.FocusedItemDecorator;
import com.android.launcher3.testing.TestProtocol;
import com.android.launcher3.userevent.nano.LauncherLogProto;
import com.android.launcher3.util.ItemInfoMatcher;
import com.android.launcher3.util.MultiValueAlpha;
import com.android.launcher3.util.Themes;
import com.android.launcher3.views.BottomUserEducationView;
import com.android.launcher3.views.RecyclerViewFastScroller;
import com.android.launcher3.views.SpringRelativeLayout;
import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
public class AllAppsContainerView extends SpringRelativeLayout implements DragSource, Insettable, DeviceProfile.OnDeviceProfileChangeListener {
    private static final int ALPHA_CHANNEL_COUNT = 2;
    private static final float FLING_ANIMATION_THRESHOLD = 0.55f;
    private static final float FLING_VELOCITY_MULTIPLIER = 135.0f;
    private final AdapterHolder[] mAH;
    private final AllAppsStore mAllAppsStore;
    private final Point mFastScrollerOffset;
    private FloatingHeaderView mHeader;
    private final Launcher mLauncher;
    private final MultiValueAlpha mMultiValueAlpha;
    private int mNavBarScrimHeight;
    private final Paint mNavBarScrimPaint;
    private final ItemInfoMatcher mPersonalMatcher;
    private View mSearchContainer;
    private boolean mSearchModeWhileUsingTabs;
    private SpannableStringBuilder mSearchQueryBuilder;
    private SearchUiManager mSearchUiManager;
    private RecyclerViewFastScroller mTouchHandler;
    private boolean mUsingTabs;
    private AllAppsPagedView mViewPager;
    private final ItemInfoMatcher mWorkMatcher;

    @Override // com.android.launcher3.logging.StatsLogUtils.LogContainerProvider
    public void fillInLogContainerData(View v, ItemInfo info, LauncherLogProto.Target target, LauncherLogProto.Target targetParent) {
    }

    @Override // com.android.launcher3.DragSource
    public void onDropCompleted(View target, DropTarget.DragObject d, boolean success) {
    }

    public AllAppsContainerView(Context context) {
        this(context, null);
    }

    public AllAppsContainerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AllAppsContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ItemInfoMatcher itemInfoMatcherOfUser = ItemInfoMatcher.ofUser(Process.myUserHandle());
        this.mPersonalMatcher = itemInfoMatcherOfUser;
        this.mWorkMatcher = ItemInfoMatcher.not(itemInfoMatcherOfUser);
        AllAppsStore allAppsStore = new AllAppsStore();
        this.mAllAppsStore = allAppsStore;
        this.mNavBarScrimHeight = 0;
        this.mSearchQueryBuilder = null;
        this.mSearchModeWhileUsingTabs = false;
        this.mFastScrollerOffset = new Point();
        Launcher launcher = Launcher.getLauncher(context);
        this.mLauncher = launcher;
        launcher.addOnDeviceProfileChangeListener(this);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        this.mSearchQueryBuilder = spannableStringBuilder;
        Selection.setSelection(spannableStringBuilder, 0);
        this.mAH = new AdapterHolder[]{new AdapterHolder(false), new AdapterHolder(true)};
        Paint paint = new Paint();
        this.mNavBarScrimPaint = paint;
        paint.setColor(Themes.getAttrColor(context, R.attr.allAppsNavBarScrimColor));
        allAppsStore.addUpdateListener(new AllAppsStore.OnUpdateListener() { // from class: com.android.launcher3.allapps.-$$Lambda$AllAppsContainerView$fDTuxO4KJb2iDZA9T2chplqjtqw
            @Override // com.android.launcher3.allapps.AllAppsStore.OnUpdateListener
            public final void onAppsUpdated() {
                this.f$0.onAppsUpdated();
            }
        });
        addSpringView(R.id.all_apps_header);
        addSpringView(R.id.apps_list_view);
        addSpringView(R.id.all_apps_tabs_view_pager);
        this.mMultiValueAlpha = new MultiValueAlpha(this, 2);
    }

    public AllAppsStore getAppsStore() {
        return this.mAllAppsStore;
    }

    public MultiValueAlpha.AlphaProperty getAlphaProperty(int index) {
        return this.mMultiValueAlpha.getProperty(index);
    }

    @Override // com.android.launcher3.views.SpringRelativeLayout
    protected void setDampedScrollShift(float shift) {
        float height = getSearchView().getHeight() / 2.0f;
        super.setDampedScrollShift(Utilities.boundToRange(shift, -height, height));
    }

    @Override // com.android.launcher3.DeviceProfile.OnDeviceProfileChangeListener
    public void onDeviceProfileChanged(DeviceProfile dp) {
        for (AdapterHolder adapterHolder : this.mAH) {
            if (adapterHolder.recyclerView != null) {
                adapterHolder.recyclerView.swapAdapter(adapterHolder.recyclerView.getAdapter(), true);
                adapterHolder.recyclerView.getRecycledViewPool().clear();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAppsUpdated() {
        boolean z;
        Iterator<AppInfo> it = this.mAllAppsStore.getApps().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            if (this.mWorkMatcher.matches(it.next(), null)) {
                z = true;
                break;
            }
        }
        rebindAdapters(z);
    }

    public boolean shouldContainerScroll(MotionEvent ev) {
        AllAppsRecyclerView activeRecyclerView;
        if (this.mLauncher.getDragLayer().isEventOverView(this.mSearchContainer, ev) || (activeRecyclerView = getActiveRecyclerView()) == null) {
            return true;
        }
        if (activeRecyclerView.getScrollbar().getThumbOffsetY() < 0 || !this.mLauncher.getDragLayer().isEventOverView(activeRecyclerView.getScrollbar(), ev)) {
            return activeRecyclerView.shouldContainerScroll(ev, this.mLauncher.getDragLayer());
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!this.mLauncher.isInState(LauncherState.ALL_APPS)) {
            return false;
        }
        if (ev.getAction() == 0) {
            AllAppsRecyclerView activeRecyclerView = getActiveRecyclerView();
            if (activeRecyclerView != null && activeRecyclerView.getScrollbar().isHitInParent(ev.getX(), ev.getY(), this.mFastScrollerOffset)) {
                this.mTouchHandler = activeRecyclerView.getScrollbar();
            } else {
                this.mTouchHandler = null;
            }
        }
        RecyclerViewFastScroller recyclerViewFastScroller = this.mTouchHandler;
        if (recyclerViewFastScroller != null) {
            return recyclerViewFastScroller.handleTouchEvent(ev, this.mFastScrollerOffset);
        }
        return false;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        RecyclerViewFastScroller recyclerViewFastScroller = this.mTouchHandler;
        if (recyclerViewFastScroller == null) {
            return false;
        }
        recyclerViewFastScroller.handleTouchEvent(ev, this.mFastScrollerOffset);
        return true;
    }

    public String getDescription() {
        int i;
        if (this.mUsingTabs) {
            i = this.mViewPager.getNextPage() == 0 ? R.string.all_apps_button_personal_label : R.string.all_apps_button_work_label;
        } else {
            i = R.string.all_apps_button_label;
        }
        return getContext().getString(i);
    }

    public AllAppsRecyclerView getActiveRecyclerView() {
        if (!this.mUsingTabs || this.mViewPager.getNextPage() == 0) {
            return this.mAH[0].recyclerView;
        }
        return this.mAH[1].recyclerView;
    }

    public void reset(boolean animate) {
        int i = 0;
        while (true) {
            AdapterHolder[] adapterHolderArr = this.mAH;
            if (i >= adapterHolderArr.length) {
                break;
            }
            if (adapterHolderArr[i].recyclerView != null) {
                this.mAH[i].recyclerView.scrollToTop();
            }
            i++;
        }
        if (isHeaderVisible()) {
            this.mHeader.reset(animate);
        }
        this.mSearchUiManager.resetSearch();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.android.launcher3.allapps.-$$Lambda$AllAppsContainerView$CrjOqFq0Rfb8-WMynHt8ag3_ZfM
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                this.f$0.lambda$onFinishInflate$0$AllAppsContainerView(view, z);
            }
        });
        this.mHeader = (FloatingHeaderView) findViewById(R.id.all_apps_header);
        rebindAdapters(this.mUsingTabs, true);
        View viewFindViewById = findViewById(R.id.search_container_all_apps);
        this.mSearchContainer = viewFindViewById;
        SearchUiManager searchUiManager = (SearchUiManager) viewFindViewById;
        this.mSearchUiManager = searchUiManager;
        searchUiManager.initialize(this);
    }

    public /* synthetic */ void lambda$onFinishInflate$0$AllAppsContainerView(View view, boolean z) {
        if (!z || getActiveRecyclerView() == null) {
            return;
        }
        getActiveRecyclerView().requestFocus();
    }

    public SearchUiManager getSearchUiManager() {
        return this.mSearchUiManager;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        this.mSearchUiManager.preDispatchKeyEvent(event);
        return super.dispatchKeyEvent(event);
    }

    @Override // com.android.launcher3.Insettable
    public void setInsets(Rect insets) {
        DeviceProfile deviceProfile = this.mLauncher.getDeviceProfile();
        int i = deviceProfile.desiredWorkspaceLeftRightMarginPx + deviceProfile.cellLayoutPaddingLeftRightPx;
        int i2 = 0;
        while (true) {
            AdapterHolder[] adapterHolderArr = this.mAH;
            if (i2 >= adapterHolderArr.length) {
                break;
            }
            adapterHolderArr[i2].padding.bottom = insets.bottom;
            Rect rect = this.mAH[i2].padding;
            this.mAH[i2].padding.right = i;
            rect.left = i;
            this.mAH[i2].applyPadding();
            i2++;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        if (deviceProfile.isVerticalBarLayout()) {
            marginLayoutParams.leftMargin = insets.left;
            marginLayoutParams.rightMargin = insets.right;
            setPadding(deviceProfile.workspacePadding.left, 0, deviceProfile.workspacePadding.right, 0);
        } else {
            marginLayoutParams.rightMargin = 0;
            marginLayoutParams.leftMargin = 0;
            setPadding(0, 0, 0, 0);
        }
        setLayoutParams(marginLayoutParams);
        InsettableFrameLayout.dispatchInsets(this, insets);
        this.mLauncher.getAllAppsController().setScrollRangeDelta(this.mSearchUiManager.getScrollRangeDelta(insets));
    }

    @Override // android.view.ViewGroup, android.view.View
    public WindowInsets dispatchApplyWindowInsets(WindowInsets insets) {
        if (Utilities.ATLEAST_Q) {
            this.mNavBarScrimHeight = insets.getTappableElementInsets().bottom;
        } else {
            this.mNavBarScrimHeight = insets.getStableInsetBottom();
        }
        return super.dispatchApplyWindowInsets(insets);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mNavBarScrimHeight > 0) {
            canvas.drawRect(0.0f, getHeight() - this.mNavBarScrimHeight, getWidth(), getHeight(), this.mNavBarScrimPaint);
        }
    }

    @Override // com.android.launcher3.views.SpringRelativeLayout
    public int getCanvasClipTopForOverscroll() {
        if (this.mSpringViews.get(getSearchView().getId())) {
            return 0;
        }
        return this.mHeader.getTop();
    }

    private void rebindAdapters(boolean showTabs) {
        rebindAdapters(showTabs, false);
    }

    private void rebindAdapters(boolean showTabs, boolean force) {
        if (showTabs != this.mUsingTabs || force) {
            replaceRVContainer(showTabs);
            this.mUsingTabs = showTabs;
            this.mAllAppsStore.unregisterIconContainer(this.mAH[0].recyclerView);
            this.mAllAppsStore.unregisterIconContainer(this.mAH[1].recyclerView);
            if (this.mUsingTabs) {
                this.mAH[0].setup(this.mViewPager.getChildAt(0), this.mPersonalMatcher);
                this.mAH[1].setup(this.mViewPager.getChildAt(1), this.mWorkMatcher);
                onTabChanged(this.mViewPager.getNextPage());
            } else {
                this.mAH[0].setup(findViewById(R.id.apps_list_view), null);
                this.mAH[1].recyclerView = null;
            }
            setupHeader();
            this.mAllAppsStore.registerIconContainer(this.mAH[0].recyclerView);
            this.mAllAppsStore.registerIconContainer(this.mAH[1].recyclerView);
        }
    }

    private void replaceRVContainer(boolean showTabs) {
        int i = 0;
        while (true) {
            AdapterHolder[] adapterHolderArr = this.mAH;
            if (i >= adapterHolderArr.length) {
                break;
            }
            if (adapterHolderArr[i].recyclerView != null) {
                this.mAH[i].recyclerView.setLayoutManager(null);
            }
            i++;
        }
        View recyclerViewContainer = getRecyclerViewContainer();
        int iIndexOfChild = indexOfChild(recyclerViewContainer);
        removeView(recyclerViewContainer);
        View viewInflate = LayoutInflater.from(getContext()).inflate(showTabs ? R.layout.all_apps_tabs : R.layout.all_apps_rv_layout, (ViewGroup) this, false);
        addView(viewInflate, iIndexOfChild);
        if (showTabs) {
            AllAppsPagedView allAppsPagedView = (AllAppsPagedView) viewInflate;
            this.mViewPager = allAppsPagedView;
            allAppsPagedView.initParentViews(this);
            this.mViewPager.getPageIndicator().setContainerView(this);
            return;
        }
        this.mViewPager = null;
    }

    public View getRecyclerViewContainer() {
        AllAppsPagedView allAppsPagedView = this.mViewPager;
        return allAppsPagedView != null ? allAppsPagedView : findViewById(R.id.apps_list_view);
    }

    public void onTabChanged(int pos) {
        this.mHeader.setMainActive(pos == 0);
        reset(true);
        if (this.mAH[pos].recyclerView != null) {
            this.mAH[pos].recyclerView.bindFastScrollbar();
            findViewById(R.id.tab_personal).setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.allapps.-$$Lambda$AllAppsContainerView$pDtt-CPem5I9Hyi3w4HmhFuobL0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onTabChanged$1$AllAppsContainerView(view);
                }
            });
            findViewById(R.id.tab_work).setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.allapps.-$$Lambda$AllAppsContainerView$unCc4v21nQkfUZF0ClBi180XH24
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onTabChanged$2$AllAppsContainerView(view);
                }
            });
        }
        if (pos == 1) {
            BottomUserEducationView.showIfNeeded(this.mLauncher);
        }
    }

    public /* synthetic */ void lambda$onTabChanged$1$AllAppsContainerView(View view) {
        this.mViewPager.snapToPage(0);
    }

    public /* synthetic */ void lambda$onTabChanged$2$AllAppsContainerView(View view) {
        this.mViewPager.snapToPage(1);
    }

    private boolean isDescendantViewVisible(int viewId) {
        View viewFindViewById = findViewById(viewId);
        if (viewFindViewById != null && viewFindViewById.isShown()) {
            return viewFindViewById.getGlobalVisibleRect(new Rect());
        }
        return false;
    }

    public boolean isPersonalTabVisible() {
        return isDescendantViewVisible(R.id.tab_personal);
    }

    public boolean isWorkTabVisible() {
        return isDescendantViewVisible(R.id.tab_work);
    }

    public AlphabeticalAppsList getApps() {
        return this.mAH[0].appsList;
    }

    public FloatingHeaderView getFloatingHeaderView() {
        return this.mHeader;
    }

    public View getSearchView() {
        return this.mSearchContainer;
    }

    public View getContentView() {
        AllAppsPagedView allAppsPagedView = this.mViewPager;
        return allAppsPagedView == null ? getActiveRecyclerView() : allAppsPagedView;
    }

    public RecyclerViewFastScroller getScrollBar() {
        AllAppsRecyclerView activeRecyclerView = getActiveRecyclerView();
        if (activeRecyclerView == null) {
            return null;
        }
        return activeRecyclerView.getScrollbar();
    }

    public void setupHeader() {
        int i = 0;
        this.mHeader.setVisibility(0);
        FloatingHeaderView floatingHeaderView = this.mHeader;
        AdapterHolder[] adapterHolderArr = this.mAH;
        floatingHeaderView.setup(adapterHolderArr, adapterHolderArr[1].recyclerView == null);
        int maxTranslation = this.mHeader.getMaxTranslation();
        while (true) {
            AdapterHolder[] adapterHolderArr2 = this.mAH;
            if (i >= adapterHolderArr2.length) {
                return;
            }
            adapterHolderArr2[i].padding.top = maxTranslation;
            this.mAH[i].applyPadding();
            i++;
        }
    }

    public void setLastSearchQuery(String query) {
        int i = 0;
        while (true) {
            AdapterHolder[] adapterHolderArr = this.mAH;
            if (i >= adapterHolderArr.length) {
                break;
            }
            adapterHolderArr[i].adapter.setLastSearchQuery(query);
            i++;
        }
        if (this.mUsingTabs) {
            this.mSearchModeWhileUsingTabs = true;
            rebindAdapters(false);
        }
    }

    public void onClearSearchResult() {
        if (this.mSearchModeWhileUsingTabs) {
            rebindAdapters(true);
            this.mSearchModeWhileUsingTabs = false;
        }
    }

    public void onSearchResultsChanged() {
        int i = 0;
        while (true) {
            AdapterHolder[] adapterHolderArr = this.mAH;
            if (i >= adapterHolderArr.length) {
                return;
            }
            if (adapterHolderArr[i].recyclerView != null) {
                this.mAH[i].recyclerView.onSearchResultsChanged();
            }
            i++;
        }
    }

    public void setRecyclerViewVerticalFadingEdgeEnabled(boolean enabled) {
        int i = 0;
        while (true) {
            AdapterHolder[] adapterHolderArr = this.mAH;
            if (i >= adapterHolderArr.length) {
                return;
            }
            adapterHolderArr[i].applyVerticalFadingEdgeEnabled(enabled);
            i++;
        }
    }

    public void addElevationController(RecyclerView.OnScrollListener scrollListener) {
        if (this.mUsingTabs) {
            return;
        }
        this.mAH[0].recyclerView.addOnScrollListener(scrollListener);
    }

    public boolean isHeaderVisible() {
        FloatingHeaderView floatingHeaderView = this.mHeader;
        return floatingHeaderView != null && floatingHeaderView.getVisibility() == 0;
    }

    public void onScrollUpEnd() {
        highlightWorkTabIfNecessary();
    }

    void highlightWorkTabIfNecessary() {
        if (this.mUsingTabs) {
            ((PersonalWorkSlidingTabStrip) findViewById(R.id.tabs)).highlightWorkTabIfNecessary();
        }
    }

    public void addSpringFromFlingUpdateListener(ValueAnimator animator, final float velocity) {
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.launcher3.allapps.AllAppsContainerView.1
            boolean shouldSpring = true;

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (!this.shouldSpring || valueAnimator.getAnimatedFraction() < AllAppsContainerView.FLING_ANIMATION_THRESHOLD) {
                    return;
                }
                final int id = AllAppsContainerView.this.getSearchView().getId();
                AllAppsContainerView.this.addSpringView(id);
                AllAppsContainerView.this.finishWithShiftAndVelocity(1.0f, velocity * AllAppsContainerView.FLING_VELOCITY_MULTIPLIER, new DynamicAnimation.OnAnimationEndListener() { // from class: com.android.launcher3.allapps.AllAppsContainerView.1.1
                    @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
                    public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity2) {
                        AllAppsContainerView.this.removeSpringView(id);
                    }
                });
                this.shouldSpring = false;
            }
        });
    }

    @Override // android.view.View
    public void getDrawingRect(Rect outRect) {
        super.getDrawingRect(outRect);
        outRect.offset(0, (int) getTranslationY());
    }

    public class AdapterHolder {
        public static final int MAIN = 0;
        public static final int WORK = 1;
        public final AllAppsGridAdapter adapter;
        final AlphabeticalAppsList appsList;
        final LinearLayoutManager layoutManager;
        final Rect padding = new Rect();
        AllAppsRecyclerView recyclerView;
        boolean verticalFadingEdge;

        AdapterHolder(boolean isWork) {
            AlphabeticalAppsList alphabeticalAppsList = new AlphabeticalAppsList(AllAppsContainerView.this.mLauncher, AllAppsContainerView.this.mAllAppsStore, isWork);
            this.appsList = alphabeticalAppsList;
            AllAppsGridAdapter allAppsGridAdapter = new AllAppsGridAdapter(AllAppsContainerView.this.mLauncher, alphabeticalAppsList);
            this.adapter = allAppsGridAdapter;
            alphabeticalAppsList.setAdapter(allAppsGridAdapter);
            this.layoutManager = allAppsGridAdapter.getLayoutManager();
        }

        void setup(View rv, ItemInfoMatcher matcher) {
            this.appsList.updateItemFilter(matcher);
            AllAppsRecyclerView allAppsRecyclerView = (AllAppsRecyclerView) rv;
            this.recyclerView = allAppsRecyclerView;
            allAppsRecyclerView.setEdgeEffectFactory(AllAppsContainerView.this.createEdgeEffectFactory());
            this.recyclerView.setApps(this.appsList, AllAppsContainerView.this.mUsingTabs);
            this.recyclerView.setLayoutManager(this.layoutManager);
            this.recyclerView.setAdapter(this.adapter);
            this.recyclerView.setHasFixedSize(true);
            this.recyclerView.setItemAnimator(null);
            FocusedItemDecorator focusedItemDecorator = new FocusedItemDecorator(this.recyclerView);
            this.recyclerView.addItemDecoration(focusedItemDecorator);
            this.adapter.setIconFocusListener(focusedItemDecorator.getFocusListener());
            applyVerticalFadingEdgeEnabled(this.verticalFadingEdge);
            applyPadding();
        }

        void applyPadding() {
            AllAppsRecyclerView allAppsRecyclerView = this.recyclerView;
            if (allAppsRecyclerView != null) {
                allAppsRecyclerView.setPadding(this.padding.left, this.padding.top, this.padding.right, this.padding.bottom);
            }
        }

        public void applyVerticalFadingEdgeEnabled(boolean enabled) {
            this.verticalFadingEdge = enabled;
            boolean z = false;
            AllAppsRecyclerView allAppsRecyclerView = AllAppsContainerView.this.mAH[0].recyclerView;
            if (!AllAppsContainerView.this.mUsingTabs && this.verticalFadingEdge) {
                z = true;
            }
            allAppsRecyclerView.setVerticalFadingEdgeEnabled(z);
        }
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int action, Bundle arguments) {
        if (AccessibilityManagerCompat.processTestRequest(this.mLauncher, TestProtocol.GET_SCROLL_MESSAGE, action, arguments, new Consumer() { // from class: com.android.launcher3.allapps.-$$Lambda$AllAppsContainerView$i3bo2bV2OQDSGXIAQCQnYj7LmJg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$performAccessibilityAction$3$AllAppsContainerView((Bundle) obj);
            }
        })) {
            return true;
        }
        return super.performAccessibilityAction(action, arguments);
    }

    public /* synthetic */ void lambda$performAccessibilityAction$3$AllAppsContainerView(Bundle bundle) {
        bundle.putInt(TestProtocol.SCROLL_Y_FIELD, getActiveRecyclerView().getCurrentScrollY());
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_START_TAG, "AllAppsContainerView.dispatchTouchEvent " + ev);
        }
        boolean zDispatchTouchEvent = super.dispatchTouchEvent(ev);
        int actionMasked = ev.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1 || actionMasked == 3) {
                this.mAllAppsStore.disableDeferUpdates(2);
            }
        } else if (zDispatchTouchEvent) {
            this.mAllAppsStore.enableDeferUpdates(2);
        }
        return zDispatchTouchEvent;
    }
}
