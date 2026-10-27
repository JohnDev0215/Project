package com.android.car.ui.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.android.car.ui.R;
import com.android.car.ui.imewidescreen.CarUiImeSearchListItem;
import com.android.car.ui.toolbar.TabLayout;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public final class Toolbar extends FrameLayout implements ToolbarController {
    private static final String TAG = "CarUiToolbar";
    private ToolbarControllerImpl mController;
    private boolean mEatingHover;
    private boolean mEatingTouch;

    public enum NavButtonMode {
        BACK,
        CLOSE,
        DOWN
    }

    public interface OnBackListener {
        boolean onBack();
    }

    public interface OnHeightChangedListener {
        void onHeightChanged(int i);
    }

    public interface OnSearchCompletedListener {
        void onSearchCompleted();
    }

    public interface OnSearchListener {
        void onSearch(String str);
    }

    public interface OnTabSelectedListener {
        void onTabSelected(TabLayout.Tab tab);
    }

    public enum State {
        HOME,
        SUBPAGE,
        SEARCH,
        EDIT
    }

    public Toolbar(Context context) {
        this(context, null);
    }

    public Toolbar(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.CarUiToolbarStyle);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mEatingTouch = false;
        this.mEatingHover = false;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService("layout_inflater");
        inflater.inflate(getToolbarLayout(), (ViewGroup) this, true);
        this.mController = new ToolbarControllerImpl(this);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CarUiToolbar, defStyleAttr, defStyleRes);
        try {
            setShowTabsInSubpage(a.getBoolean(R.styleable.CarUiToolbar_showTabsInSubpage, false));
            setTitle(a.getString(R.styleable.CarUiToolbar_title));
            setLogo(a.getResourceId(R.styleable.CarUiToolbar_logo, 0));
            setBackgroundShown(a.getBoolean(R.styleable.CarUiToolbar_showBackground, true));
            setMenuItems(a.getResourceId(R.styleable.CarUiToolbar_menuItems, 0));
            String searchHint = a.getString(R.styleable.CarUiToolbar_searchHint);
            if (searchHint != null) {
                setSearchHint(searchHint);
            }
            int i = a.getInt(R.styleable.CarUiToolbar_car_ui_state, 0);
            if (i == 0) {
                setState(State.HOME);
            } else if (i == 1) {
                setState(State.SUBPAGE);
            } else if (i == 2) {
                setState(State.SEARCH);
            } else if (Log.isLoggable(TAG, 5)) {
                Log.w(TAG, "Unknown initial state");
            }
            int i2 = a.getInt(R.styleable.CarUiToolbar_car_ui_navButtonMode, 0);
            if (i2 == 0) {
                setNavButtonMode(NavButtonMode.BACK);
            } else if (i2 == 1) {
                setNavButtonMode(NavButtonMode.CLOSE);
            } else if (i2 == 2) {
                setNavButtonMode(NavButtonMode.DOWN);
            } else if (Log.isLoggable(TAG, 5)) {
                Log.w(TAG, "Unknown navigation button style");
            }
        } finally {
            a.recycle();
        }
    }

    protected int getToolbarLayout() {
        if (getContext().getResources().getBoolean(R.bool.car_ui_toolbar_tabs_on_second_row)) {
            return R.layout.car_ui_toolbar_two_row;
        }
        return R.layout.car_ui_toolbar;
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean isTabsInSecondRow() {
        return this.mController.isTabsInSecondRow();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setTitle(int title) {
        this.mController.setTitle(title);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setTitle(CharSequence title) {
        this.mController.setTitle(title);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public CharSequence getTitle() {
        return this.mController.getTitle();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSubtitle(int title) {
        this.mController.setSubtitle(title);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSubtitle(CharSequence title) {
        this.mController.setSubtitle(title);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public CharSequence getSubtitle() {
        return this.mController.getSubtitle();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    @Deprecated
    public TabLayout getTabLayout() {
        return this.mController.getTabLayout();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public int getTabCount() {
        return this.mController.getTabCount();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public int getTabPosition(TabLayout.Tab tab) {
        return this.mController.getTabPosition(tab);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void addTab(TabLayout.Tab tab) {
        this.mController.addTab(tab);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void clearAllTabs() {
        this.mController.clearAllTabs();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public TabLayout.Tab getTab(int position) {
        return this.mController.getTab(position);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void selectTab(int position) {
        this.mController.selectTab(position);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setShowTabsInSubpage(boolean showTabs) {
        this.mController.setShowTabsInSubpage(showTabs);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean getShowTabsInSubpage() {
        return this.mController.getShowTabsInSubpage();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setLogo(int resId) {
        this.mController.setLogo(resId);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setLogo(Drawable drawable) {
        this.mController.setLogo(drawable);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSearchHint(int resId) {
        this.mController.setSearchHint(resId);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSearchHint(CharSequence hint) {
        this.mController.setSearchHint(hint);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public CharSequence getSearchHint() {
        return this.mController.getSearchHint();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSearchIcon(int resId) {
        this.mController.setSearchIcon(resId);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSearchIcon(Drawable d) {
        this.mController.setSearchIcon(d);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setNavButtonMode(NavButtonMode style) {
        this.mController.setNavButtonMode(style);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public NavButtonMode getNavButtonMode() {
        return this.mController.getNavButtonMode();
    }

    @Override // android.view.View
    public void setBackground(Drawable d) {
        throw new UnsupportedOperationException("You can not change the background of a CarUi toolbar, use setBackgroundShown(boolean) or an RRO instead.");
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setBackgroundShown(boolean shown) {
        this.mController.setBackgroundShown(shown);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean getBackgroundShown() {
        return this.mController.getBackgroundShown();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setMenuItems(List<MenuItem> items) {
        this.mController.setMenuItems(items);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public List<MenuItem> setMenuItems(int resId) {
        return this.mController.setMenuItems(resId);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public List<MenuItem> getMenuItems() {
        return this.mController.getMenuItems();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public MenuItem findMenuItemById(int id) {
        return this.mController.findMenuItemById(id);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public MenuItem requireMenuItemById(int id) {
        return this.mController.requireMenuItemById(id);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setShowMenuItemsWhileSearching(boolean showMenuItems) {
        this.mController.setShowMenuItemsWhileSearching(showMenuItems);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean getShowMenuItemsWhileSearching() {
        return this.mController.getShowMenuItemsWhileSearching();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSearchQuery(String query) {
        this.mController.setSearchQuery(query);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setState(State state) {
        this.mController.setState(state);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public State getState() {
        return this.mController.getState();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        if (action == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            boolean handled = super.onTouchEvent(ev);
            if (action == 0 && !handled) {
                this.mEatingTouch = true;
            }
        }
        if (action == 1 || action == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent ev) {
        int action = ev.getActionMasked();
        if (action == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean handled = super.onHoverEvent(ev);
            if (action == 9 && !handled) {
                this.mEatingHover = true;
            }
        }
        if (action == 10 || action == 3) {
            this.mEatingHover = false;
        }
        return true;
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void registerToolbarHeightChangeListener(OnHeightChangedListener listener) {
        this.mController.registerToolbarHeightChangeListener(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean unregisterToolbarHeightChangeListener(OnHeightChangedListener listener) {
        return this.mController.unregisterToolbarHeightChangeListener(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void registerOnTabSelectedListener(OnTabSelectedListener listener) {
        this.mController.registerOnTabSelectedListener(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean unregisterOnTabSelectedListener(OnTabSelectedListener listener) {
        return this.mController.unregisterOnTabSelectedListener(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void registerOnSearchListener(OnSearchListener listener) {
        this.mController.registerOnSearchListener(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean unregisterOnSearchListener(OnSearchListener listener) {
        return this.mController.unregisterOnSearchListener(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean canShowSearchResultItems() {
        return this.mController.canShowSearchResultItems();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean canShowSearchResultsView() {
        return this.mController.canShowSearchResultsView();
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSearchResultsView(View view) {
        this.mController.setSearchResultsView(view);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void setSearchResultItems(List<? extends CarUiImeSearchListItem> searchItems) {
        this.mController.setSearchResultItems(searchItems);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void registerOnSearchCompletedListener(OnSearchCompletedListener listener) {
        this.mController.registerOnSearchCompletedListener(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean unregisterOnSearchCompletedListener(OnSearchCompletedListener listener) {
        return this.mController.unregisterOnSearchCompletedListener(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public void registerOnBackListener(OnBackListener listener) {
        this.mController.registerOnBackListener(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public boolean unregisterOnBackListener(OnBackListener listener) {
        return this.mController.unregisterOnBackListener(listener);
    }

    @Override // com.android.car.ui.toolbar.ToolbarController
    public ProgressBarController getProgressBar() {
        return this.mController.getProgressBar();
    }
}
