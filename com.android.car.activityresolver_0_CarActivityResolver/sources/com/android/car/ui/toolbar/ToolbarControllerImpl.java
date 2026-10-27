package com.android.car.ui.toolbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.util.Consumer;
import com.android.car.ui.AlertDialogBuilder;
import com.android.car.ui.R;
import com.android.car.ui.imewidescreen.CarUiImeSearchListItem;
import com.android.car.ui.recyclerview.CarUiContentListItem;
import com.android.car.ui.recyclerview.CarUiListItem;
import com.android.car.ui.recyclerview.CarUiListItemAdapter;
import com.android.car.ui.toolbar.MenuItem;
import com.android.car.ui.toolbar.TabLayout;
import com.android.car.ui.toolbar.Toolbar;
import com.android.car.ui.utils.CarUiUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public final class ToolbarControllerImpl implements ToolbarController {
    private static final String TAG = "CarUiToolbarController";
    private View mBackground;
    private final Context mContext;
    private ImageView mLogoInNavIconSpace;
    private View[] mMenuItemViews;
    private ViewGroup mMenuItemsContainer;
    private ImageView mNavIcon;
    private ViewGroup mNavIconContainer;
    private final CarUiListItemAdapter mOverflowAdapter;
    private AlertDialog mOverflowDialog;
    private final ProgressBarController mProgressBar;
    private Drawable mSearchIcon;
    private List<? extends CarUiImeSearchListItem> mSearchItems;
    private String mSearchQuery;
    private SearchView mSearchView;
    private FrameLayout mSearchViewContainer;
    private boolean mShowMenuItemsWhileSearching;
    private TextView mSubtitle;
    private TabLayout mTabLayout;
    private TextView mTitle;
    private ViewGroup mTitleContainer;
    private ImageView mTitleLogo;
    private ViewGroup mTitleLogoContainer;
    private View mViewForContentAreaInWideScreenMode;
    private final Set<Toolbar.OnSearchListener> mOnSearchListeners = new HashSet();
    private final Set<Toolbar.OnSearchCompletedListener> mOnSearchCompletedListeners = new HashSet();
    private final Set<Toolbar.OnBackListener> mOnBackListeners = new HashSet();
    private final Set<Toolbar.OnTabSelectedListener> mOnTabSelectedListeners = new HashSet();
    private final Set<Toolbar.OnHeightChangedListener> mOnHeightChangedListeners = new HashSet();
    private boolean mShowTabsInSubpage = false;
    private boolean mHasLogo = false;
    private Toolbar.State mState = Toolbar.State.HOME;
    private Toolbar.NavButtonMode mNavButtonMode = Toolbar.NavButtonMode.BACK;
    private List<MenuItem> mMenuItems = Collections.emptyList();
    private List<MenuItem> mOverflowItems = new ArrayList();
    private final List<CarUiListItem> mUiOverflowItems = new ArrayList();
    private final List<MenuItemRenderer> mMenuItemRenderers = new ArrayList();
    private int mMenuItemsXmlId = 0;
    private final MenuItem.Listener mOverflowItemListener = new MenuItem.Listener() { // from class: com.android.car.ui.toolbar.-$$Lambda$ToolbarControllerImpl$1IOFQtCJ5rtG6IDzmeG4aoQaKKw
        @Override // com.android.car.ui.toolbar.MenuItem.Listener
        public final void onMenuItemChanged(MenuItem menuItem) {
            this.f$0.lambda$new$0$ToolbarControllerImpl(menuItem);
        }
    };
    private final MenuItem mOverflowButton = MenuItem.builder(getContext()).setIcon(R.drawable.car_ui_icon_overflow_menu).setTitle(R.string.car_ui_toolbar_menu_item_overflow_title).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.ui.toolbar.-$$Lambda$ToolbarControllerImpl$ay_vPD-OMbt1G-sMGIWC32NBGWA
        @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
        public final void onClick(MenuItem menuItem) {
            this.f$0.lambda$new$1$ToolbarControllerImpl(menuItem);
        }
    }).build();
    private final boolean mIsTabsInSecondRow = getContext().getResources().getBoolean(R.bool.car_ui_toolbar_tabs_on_second_row);
    private boolean mNavIconSpaceReserved = getContext().getResources().getBoolean(R.bool.car_ui_toolbar_nav_icon_reserve_space);
    private boolean mLogoFillsNavIconSpace = getContext().getResources().getBoolean(R.bool.car_ui_toolbar_logo_fills_nav_icon_space);
    private boolean mShowLogo = getContext().getResources().getBoolean(R.bool.car_ui_toolbar_show_logo);
    private CharSequence mSearchHint = getContext().getString(R.string.car_ui_toolbar_default_search_hint);

    public /* synthetic */ void lambda$new$0$ToolbarControllerImpl(MenuItem item) {
        updateOverflowDialog(item);
        setState(getState());
    }

    public ToolbarControllerImpl(View view) {
        this.mContext = view.getContext();
        this.mBackground = CarUiUtils.findViewByRefId(view, R.id.car_ui_toolbar_background);
        this.mTabLayout = (TabLayout) CarUiUtils.requireViewByRefId(view, R.id.car_ui_toolbar_tabs);
        this.mNavIcon = (ImageView) CarUiUtils.requireViewByRefId(view, R.id.car_ui_toolbar_nav_icon);
        this.mLogoInNavIconSpace = (ImageView) CarUiUtils.requireViewByRefId(view, R.id.car_ui_toolbar_logo);
        this.mNavIconContainer = (ViewGroup) CarUiUtils.requireViewByRefId(view, R.id.car_ui_toolbar_nav_icon_container);
        this.mMenuItemsContainer = (ViewGroup) CarUiUtils.requireViewByRefId(view, R.id.car_ui_toolbar_menu_items_container);
        this.mTitleContainer = (ViewGroup) CarUiUtils.requireViewByRefId(view, R.id.car_ui_toolbar_title_container);
        this.mSubtitle = (TextView) CarUiUtils.requireViewByRefId(view, R.id.car_ui_toolbar_subtitle);
        this.mTitle = (TextView) CarUiUtils.requireViewByRefId(view, R.id.car_ui_toolbar_title);
        this.mTitleLogoContainer = (ViewGroup) CarUiUtils.requireViewByRefId(view, R.id.car_ui_toolbar_title_logo_container);
        this.mTitleLogo = (ImageView) CarUiUtils.requireViewByRefId(view, R.id.car_ui_toolbar_title_logo);
        this.mSearchViewContainer = (FrameLayout) CarUiUtils.requireViewByRefId(view, R.id.car_ui_toolbar_search_view_container);
        this.mProgressBar = new ProgressBarControllerImpl((ProgressBar) CarUiUtils.requireViewByRefId(view, R.id.car_ui_toolbar_progress_bar));
        this.mTabLayout.addListener(new TabLayout.Listener() { // from class: com.android.car.ui.toolbar.ToolbarControllerImpl.1
            @Override // com.android.car.ui.toolbar.TabLayout.Listener
            public void onTabSelected(TabLayout.Tab tab) {
                for (Toolbar.OnTabSelectedListener listener : ToolbarControllerImpl.this.mOnTabSelectedListeners) {
                    listener.onTabSelected(tab);
                }
            }
        });
        View view2 = this.mBackground;
        if (view2 != null) {
            view2.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.car.ui.toolbar.-$$Lambda$ToolbarControllerImpl$Orp-2OdqEnLYSBBlolr3E6irThg
                @Override // android.view.View.OnLayoutChangeListener
                public final void onLayoutChange(View view3, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    this.f$0.lambda$new$2$ToolbarControllerImpl(view3, i, i2, i3, i4, i5, i6, i7, i8);
                }
            });
        }
        setBackgroundShown(true);
        this.mOverflowAdapter = new CarUiListItemAdapter(this.mUiOverflowItems);
    }

    public /* synthetic */ void lambda$new$1$ToolbarControllerImpl(MenuItem v) {
        AlertDialog alertDialog = this.mOverflowDialog;
        if (alertDialog == null) {
            if (Log.isLoggable(TAG, 6)) {
                Log.e(TAG, "Overflow dialog was null when trying to show it!");
                return;
            }
            return;
        }
        alertDialog.show();
    }

    public /* synthetic */ void lambda$new$2$ToolbarControllerImpl(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (oldBottom - oldTop != bottom - top) {
            for (Toolbar.OnHeightChangedListener listener : this.mOnHeightChangedListeners) {
                listener.onHeightChanged(this.mBackground.getHeight());
            }
        }
    }

    private Context getContext() {
        return this.mContext;
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean isTabsInSecondRow() {
        return this.mIsTabsInSecondRow;
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setTitle(int title) {
        this.mTitle.setText(title);
        setState(getState());
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setTitle(CharSequence title) {
        this.mTitle.setText(title);
        setState(getState());
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public CharSequence getTitle() {
        return this.mTitle.getText();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSubtitle(int title) {
        this.mSubtitle.setText(title);
        setState(getState());
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSubtitle(CharSequence title) {
        this.mSubtitle.setText(title);
        setState(getState());
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public CharSequence getSubtitle() {
        return this.mSubtitle.getText();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    @Deprecated
    public TabLayout getTabLayout() {
        return this.mTabLayout;
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public int getTabCount() {
        return this.mTabLayout.getTabCount();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public int getTabPosition(TabLayout.Tab tab) {
        return this.mTabLayout.getTabPosition(tab);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void addTab(TabLayout.Tab tab) {
        this.mTabLayout.addTab(tab);
        setState(getState());
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void clearAllTabs() {
        this.mTabLayout.clearAllTabs();
        setState(getState());
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public TabLayout.Tab getTab(int position) {
        return this.mTabLayout.get(position);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void selectTab(int position) {
        this.mTabLayout.selectTab(position);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setShowTabsInSubpage(boolean showTabs) {
        if (showTabs != this.mShowTabsInSubpage) {
            this.mShowTabsInSubpage = showTabs;
            setState(getState());
        }
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean getShowTabsInSubpage() {
        return this.mShowTabsInSubpage;
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setLogo(int resId) {
        setLogo(resId != 0 ? getContext().getDrawable(resId) : null);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setLogo(Drawable drawable) {
        if (!this.mShowLogo) {
            return;
        }
        if (drawable != null) {
            this.mLogoInNavIconSpace.setImageDrawable(drawable);
            this.mTitleLogo.setImageDrawable(drawable);
            this.mHasLogo = true;
        } else {
            this.mHasLogo = false;
        }
        setState(this.mState);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSearchHint(int resId) {
        setSearchHint(getContext().getString(resId));
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSearchHint(CharSequence hint) {
        this.mSearchHint = hint;
        SearchView searchView = this.mSearchView;
        if (searchView != null) {
            searchView.setHint(hint);
        }
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public CharSequence getSearchHint() {
        return this.mSearchHint;
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSearchIcon(int resId) {
        setSearchIcon(getContext().getDrawable(resId));
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSearchIcon(Drawable d) {
        if (!Objects.equals(d, this.mSearchIcon)) {
            this.mSearchIcon = d;
            SearchView searchView = this.mSearchView;
            if (searchView != null) {
                searchView.setIcon(d);
            }
        }
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setNavButtonMode(Toolbar.NavButtonMode style) {
        if (style != this.mNavButtonMode) {
            this.mNavButtonMode = style;
            setState(this.mState);
        }
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public Toolbar.NavButtonMode getNavButtonMode() {
        return this.mNavButtonMode;
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setBackgroundShown(boolean shown) {
        View view = this.mBackground;
        if (view == null) {
            return;
        }
        if (shown) {
            view.setBackground(getContext().getDrawable(R.drawable.car_ui_toolbar_background));
        } else {
            view.setBackground(null);
        }
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean getBackgroundShown() {
        View view = this.mBackground;
        return view == null || view.getBackground() != null;
    }

    private void setMenuItemsInternal(List<MenuItem> items) {
        if (items == null) {
            items = Collections.emptyList();
        }
        List<MenuItem> visibleMenuItems = new ArrayList<>();
        List<MenuItem> overflowItems = new ArrayList<>();
        final AtomicInteger loadedMenuItems = new AtomicInteger(0);
        synchronized (this) {
            if (items.equals(this.mMenuItems)) {
                return;
            }
            for (MenuItem item : items) {
                if (item.getDisplayBehavior() == MenuItem.DisplayBehavior.NEVER) {
                    overflowItems.add(item);
                    item.setListener(this.mOverflowItemListener);
                } else {
                    visibleMenuItems.add(item);
                }
            }
            this.mMenuItems = new ArrayList(items);
            this.mOverflowItems = overflowItems;
            this.mMenuItemRenderers.clear();
            this.mMenuItemsContainer.removeAllViews();
            if (!overflowItems.isEmpty()) {
                visibleMenuItems.add(this.mOverflowButton);
                createOverflowDialog();
            }
            final View[] menuItemViews = new View[visibleMenuItems.size()];
            this.mMenuItemViews = menuItemViews;
            for (int i = 0; i < visibleMenuItems.size(); i++) {
                final int index = i;
                MenuItemRenderer renderer = new MenuItemRenderer(visibleMenuItems.get(i), this.mMenuItemsContainer);
                this.mMenuItemRenderers.add(renderer);
                renderer.createView(new Consumer() { // from class: com.android.car.ui.toolbar.-$$Lambda$ToolbarControllerImpl$Qx_YGph5FmzzpLGweATWLliR74Q
                    @Override // androidx.core.util.Consumer
                    public final void accept(Object obj) {
                        this.f$0.lambda$setMenuItemsInternal$3$ToolbarControllerImpl(menuItemViews, index, loadedMenuItems, (View) obj);
                    }
                });
            }
            setState(this.mState);
        }
    }

    public /* synthetic */ void lambda$setMenuItemsInternal$3$ToolbarControllerImpl(View[] menuItemViews, int index, AtomicInteger loadedMenuItems, View view) {
        synchronized (this) {
            if (menuItemViews != this.mMenuItemViews) {
                return;
            }
            menuItemViews[index] = view;
            if (loadedMenuItems.addAndGet(1) == menuItemViews.length) {
                for (View v : menuItemViews) {
                    this.mMenuItemsContainer.addView(v);
                }
            }
        }
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setMenuItems(List<MenuItem> items) {
        this.mMenuItemsXmlId = 0;
        setMenuItemsInternal(items);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public List<MenuItem> setMenuItems(int resId) {
        int i = this.mMenuItemsXmlId;
        if (i != 0 && i == resId) {
            return this.mMenuItems;
        }
        this.mMenuItemsXmlId = resId;
        List<MenuItem> menuItems = MenuItemRenderer.readMenuItemList(getContext(), resId);
        setMenuItemsInternal(menuItems);
        return menuItems;
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public List<MenuItem> getMenuItems() {
        return Collections.unmodifiableList(this.mMenuItems);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public MenuItem findMenuItemById(int id) {
        for (MenuItem item : this.mMenuItems) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public MenuItem requireMenuItemById(int id) {
        MenuItem result = findMenuItemById(id);
        if (result == null) {
            throw new IllegalArgumentException("ID does not reference a MenuItem on this Toolbar");
        }
        return result;
    }

    private int countVisibleOverflowItems() {
        int numVisibleItems = 0;
        for (MenuItem item : this.mOverflowItems) {
            if (item.isVisible()) {
                numVisibleItems++;
            }
        }
        return numVisibleItems;
    }

    private void createOverflowDialog() {
        this.mUiOverflowItems.clear();
        for (MenuItem menuItem : this.mOverflowItems) {
            if (menuItem.isVisible()) {
                this.mUiOverflowItems.add(toCarUiContentListItem(menuItem));
            }
        }
        this.mOverflowDialog = new AlertDialogBuilder(getContext()).setAdapter(this.mOverflowAdapter).create();
    }

    private void updateOverflowDialog(MenuItem changedItem) {
        int itemIndex = this.mOverflowItems.indexOf(changedItem);
        if (itemIndex >= 0) {
            this.mUiOverflowItems.set(itemIndex, toCarUiContentListItem(changedItem));
            this.mOverflowAdapter.notifyItemChanged(itemIndex);
        } else {
            createOverflowDialog();
        }
    }

    private CarUiContentListItem toCarUiContentListItem(final MenuItem menuItem) {
        CarUiContentListItem carUiItem;
        if (menuItem.isCheckable()) {
            carUiItem = new CarUiContentListItem(CarUiContentListItem.Action.SWITCH);
        } else {
            carUiItem = new CarUiContentListItem(CarUiContentListItem.Action.NONE);
        }
        carUiItem.setIcon(menuItem.getIcon());
        carUiItem.setActivated(menuItem.isActivated());
        carUiItem.setChecked(menuItem.isChecked());
        carUiItem.setEnabled(menuItem.isEnabled());
        carUiItem.setTitle(menuItem.getTitle());
        carUiItem.setOnItemClickedListener(new CarUiContentListItem.OnClickListener() { // from class: com.android.car.ui.toolbar.-$$Lambda$ToolbarControllerImpl$IXnR37kDL1ZF2_Cq3V16gQ1xn9M
            @Override // com.android.car.ui.recyclerview.CarUiContentListItem.OnClickListener
            public final void onClick(CarUiContentListItem carUiContentListItem) {
                this.f$0.lambda$toCarUiContentListItem$4$ToolbarControllerImpl(menuItem, carUiContentListItem);
            }
        });
        return carUiItem;
    }

    public /* synthetic */ void lambda$toCarUiContentListItem$4$ToolbarControllerImpl(MenuItem menuItem, CarUiContentListItem item) {
        menuItem.performClick();
        this.mOverflowDialog.hide();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setShowMenuItemsWhileSearching(boolean showMenuItems) {
        this.mShowMenuItemsWhileSearching = showMenuItems;
        setState(this.mState);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean getShowMenuItemsWhileSearching() {
        return this.mShowMenuItemsWhileSearching;
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSearchQuery(String query) {
        SearchView searchView = this.mSearchView;
        if (searchView != null) {
            searchView.setSearchQuery(query);
            return;
        }
        this.mSearchQuery = query;
        for (Toolbar.OnSearchListener listener : this.mOnSearchListeners) {
            listener.onSearch(query);
        }
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setState(Toolbar.State state) {
        this.mState = state;
        if (this.mSearchView == null && (state == Toolbar.State.SEARCH || state == Toolbar.State.EDIT)) {
            SearchView searchView = new SearchView(getContext());
            searchView.setHint(this.mSearchHint);
            searchView.setIcon(this.mSearchIcon);
            searchView.setSearchQuery(this.mSearchQuery);
            searchView.setSearchListeners(this.mOnSearchListeners);
            searchView.setSearchCompletedListeners(this.mOnSearchCompletedListeners);
            searchView.setVisibility(8);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            this.mSearchViewContainer.addView(searchView, layoutParams);
            if (canShowSearchResultsView()) {
                searchView.setViewToImeWideScreenSurface(this.mViewForContentAreaInWideScreenMode);
            }
            searchView.installWindowInsetsListener(this.mSearchViewContainer);
            List<? extends CarUiImeSearchListItem> list = this.mSearchItems;
            if (list != null) {
                searchView.setSearchItemsForWideScreen(list);
            }
            this.mSearchView = searchView;
        }
        for (MenuItemRenderer renderer : this.mMenuItemRenderers) {
            renderer.setToolbarState(this.mState);
        }
        View.OnClickListener backClickListener = new View.OnClickListener() { // from class: com.android.car.ui.toolbar.-$$Lambda$ToolbarControllerImpl$Wttigz3VUMl9HLRcu-WRfjlUqCo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setState$5$ToolbarControllerImpl(view);
            }
        };
        if (state == Toolbar.State.SEARCH) {
            this.mNavIcon.setImageResource(R.drawable.car_ui_icon_search_nav_icon);
        } else {
            int i = AnonymousClass2.$SwitchMap$com$android$car$ui$toolbar$Toolbar$NavButtonMode[this.mNavButtonMode.ordinal()];
            if (i == 1) {
                this.mNavIcon.setImageResource(R.drawable.car_ui_icon_close);
            } else if (i == 2) {
                this.mNavIcon.setImageResource(R.drawable.car_ui_icon_down);
            } else {
                this.mNavIcon.setImageResource(R.drawable.car_ui_icon_arrow_back);
            }
        }
        this.mNavIcon.setVisibility(state != Toolbar.State.HOME ? 0 : 8);
        int i2 = 4;
        this.mLogoInNavIconSpace.setVisibility((this.mHasLogo && state == Toolbar.State.HOME && this.mLogoFillsNavIconSpace) ? 0 : 4);
        this.mTitleLogoContainer.setVisibility((!this.mHasLogo || (state != Toolbar.State.SUBPAGE && (state != Toolbar.State.HOME || this.mLogoFillsNavIconSpace))) ? 8 : 0);
        ViewGroup viewGroup = this.mNavIconContainer;
        if (state != Toolbar.State.HOME || (this.mHasLogo && this.mLogoFillsNavIconSpace)) {
            i2 = 0;
        } else if (!this.mNavIconSpaceReserved) {
            i2 = 8;
        }
        viewGroup.setVisibility(i2);
        this.mNavIconContainer.setOnClickListener(state != Toolbar.State.HOME ? backClickListener : null);
        this.mNavIconContainer.setClickable(state != Toolbar.State.HOME);
        this.mNavIconContainer.setContentDescription(state != Toolbar.State.HOME ? this.mContext.getString(R.string.car_ui_toolbar_nav_icon_content_description) : null);
        boolean hasTabs = this.mTabLayout.getTabCount() > 0 && (state == Toolbar.State.HOME || (state == Toolbar.State.SUBPAGE && this.mShowTabsInSubpage));
        int visibility = ((state == Toolbar.State.SUBPAGE || state == Toolbar.State.HOME) && (!hasTabs || this.mIsTabsInSecondRow)) ? 0 : 8;
        this.mTitleContainer.setVisibility(visibility);
        TextView textView = this.mSubtitle;
        textView.setVisibility(TextUtils.isEmpty(textView.getText()) ? 8 : 0);
        this.mTabLayout.setVisibility(hasTabs ? 0 : 8);
        if (this.mSearchView != null) {
            if (state == Toolbar.State.SEARCH || state == Toolbar.State.EDIT) {
                this.mSearchView.setPlainText(state == Toolbar.State.EDIT);
                this.mSearchView.setVisibility(0);
            } else {
                this.mSearchView.setVisibility(8);
            }
        }
        boolean showButtons = !(state == Toolbar.State.SEARCH || state == Toolbar.State.EDIT) || this.mShowMenuItemsWhileSearching;
        this.mMenuItemsContainer.setVisibility(showButtons ? 0 : 8);
        this.mOverflowButton.setVisible(showButtons && countVisibleOverflowItems() > 0);
    }

    public /* synthetic */ void lambda$setState$5$ToolbarControllerImpl(View v) {
        Activity activity;
        boolean absorbed = false;
        List<Toolbar.OnBackListener> listenersCopy = new ArrayList<>(this.mOnBackListeners);
        for (Toolbar.OnBackListener listener : listenersCopy) {
            absorbed = absorbed || listener.onBack();
        }
        if (!absorbed && (activity = CarUiUtils.getActivity(getContext())) != null) {
            activity.onBackPressed();
        }
    }

    /* JADX INFO: renamed from: com.android.car.ui.toolbar.ToolbarControllerImpl$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$android$car$ui$toolbar$Toolbar$NavButtonMode;

        static {
            int[] iArr = new int[Toolbar.NavButtonMode.values().length];
            $SwitchMap$com$android$car$ui$toolbar$Toolbar$NavButtonMode = iArr;
            try {
                iArr[Toolbar.NavButtonMode.CLOSE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$car$ui$toolbar$Toolbar$NavButtonMode[Toolbar.NavButtonMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    private List<String> allowPackageList(Context context) {
        String[] packages = context.getResources().getStringArray(R.array.car_ui_ime_wide_screen_allowed_package_list);
        return Arrays.asList(packages);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean canShowSearchResultItems() {
        return isWideScreenMode(this.mContext);
    }

    private static boolean isWideScreenMode(Context context) {
        return CarUiUtils.getBooleanSystemProperty(context.getResources(), R.string.car_ui_ime_wide_screen_system_property_name, false);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean canShowSearchResultsView() {
        boolean allowAppsToHideContentArea = this.mContext.getResources().getBoolean(R.bool.car_ui_ime_wide_screen_allow_app_hide_content_area);
        return isWideScreenMode(this.mContext) && (allowPackageList(this.mContext).contains(this.mContext.getPackageName()) || allowAppsToHideContentArea);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSearchResultsView(View view) {
        if (!canShowSearchResultsView()) {
            throw new IllegalStateException("not allowed to add view to wide screen IME, package name: " + this.mContext.getPackageName());
        }
        SearchView searchView = this.mSearchView;
        if (searchView != null) {
            searchView.setViewToImeWideScreenSurface(view);
        }
        this.mViewForContentAreaInWideScreenMode = view;
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSearchResultItems(List<? extends CarUiImeSearchListItem> searchItems) {
        if (!canShowSearchResultItems()) {
            throw new IllegalStateException("system not in wide screen mode, not allowed to set search result items ");
        }
        this.mSearchItems = searchItems;
        SearchView searchView = this.mSearchView;
        if (searchView != null) {
            searchView.setSearchItemsForWideScreen(searchItems);
        }
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public Toolbar.State getState() {
        return this.mState;
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void registerToolbarHeightChangeListener(Toolbar.OnHeightChangedListener listener) {
        this.mOnHeightChangedListeners.add(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean unregisterToolbarHeightChangeListener(Toolbar.OnHeightChangedListener listener) {
        return this.mOnHeightChangedListeners.remove(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void registerOnTabSelectedListener(Toolbar.OnTabSelectedListener listener) {
        this.mOnTabSelectedListeners.add(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean unregisterOnTabSelectedListener(Toolbar.OnTabSelectedListener listener) {
        return this.mOnTabSelectedListeners.remove(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void registerOnSearchListener(Toolbar.OnSearchListener listener) {
        this.mOnSearchListeners.add(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean unregisterOnSearchListener(Toolbar.OnSearchListener listener) {
        return this.mOnSearchListeners.remove(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void registerOnSearchCompletedListener(Toolbar.OnSearchCompletedListener listener) {
        this.mOnSearchCompletedListeners.add(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean unregisterOnSearchCompletedListener(Toolbar.OnSearchCompletedListener listener) {
        return this.mOnSearchCompletedListeners.remove(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void registerOnBackListener(Toolbar.OnBackListener listener) {
        this.mOnBackListeners.add(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean unregisterOnBackListener(Toolbar.OnBackListener listener) {
        return this.mOnBackListeners.remove(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public ProgressBarController getProgressBar() {
        return this.mProgressBar;
    }
}
