package com.android.launcher3.allapps.search;

import android.content.Context;
import android.graphics.Rect;
import android.text.Selection;
import android.text.SpannableStringBuilder;
import android.text.method.TextKeyListener;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.ExtendedEditText;
import com.android.launcher3.Insettable;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.allapps.AllAppsContainerView;
import com.android.launcher3.allapps.AllAppsStore;
import com.android.launcher3.allapps.AlphabeticalAppsList;
import com.android.launcher3.allapps.SearchUiManager;
import com.android.launcher3.allapps.search.AllAppsSearchBarController;
import com.android.launcher3.anim.PropertySetter;
import com.android.launcher3.util.ComponentKey;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class AppsSearchContainerLayout extends ExtendedEditText implements SearchUiManager, AllAppsSearchBarController.Callbacks, AllAppsStore.OnUpdateListener, Insettable {
    private AlphabeticalAppsList mApps;
    private AllAppsContainerView mAppsView;
    private final float mFixedTranslationY;
    private final Launcher mLauncher;
    private final float mMarginTopAdjusting;
    private final AllAppsSearchBarController mSearchBarController;
    private final SpannableStringBuilder mSearchQueryBuilder;

    public AppsSearchContainerLayout(Context context) {
        this(context, null);
    }

    public AppsSearchContainerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppsSearchContainerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mLauncher = Launcher.getLauncher(context);
        this.mSearchBarController = new AllAppsSearchBarController();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        this.mSearchQueryBuilder = spannableStringBuilder;
        Selection.setSelection(spannableStringBuilder, 0);
        float translationY = getTranslationY();
        this.mFixedTranslationY = translationY;
        this.mMarginTopAdjusting = translationY - getPaddingTop();
        setHint(Utilities.prefixTextWithIcon(getContext(), R.drawable.ic_allapps_search, getHint()));
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mLauncher.getAppsView().getAppsStore().addUpdateListener(this);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mLauncher.getAppsView().getAppsStore().removeUpdateListener(this);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        DeviceProfile deviceProfile = this.mLauncher.getDeviceProfile();
        int size = (View.MeasureSpec.getSize(widthMeasureSpec) - this.mAppsView.getActiveRecyclerView().getPaddingLeft()) - this.mAppsView.getActiveRecyclerView().getPaddingRight();
        super.onMeasure(View.MeasureSpec.makeMeasureSpec((size - (DeviceProfile.calculateCellWidth(size, deviceProfile.inv.numHotseatIcons) - Math.round(deviceProfile.iconSizePx * 0.92f))) + getPaddingLeft() + getPaddingRight(), 1073741824), heightMeasureSpec);
    }

    @Override // com.android.launcher3.ExtendedEditText, android.widget.TextView, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        View view = (View) getParent();
        setTranslationX((view.getPaddingLeft() + ((((view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight()) - (right - left)) / 2)) - left);
    }

    @Override // com.android.launcher3.allapps.SearchUiManager
    public void initialize(AllAppsContainerView appsView) {
        this.mApps = appsView.getApps();
        this.mAppsView = appsView;
        this.mSearchBarController.initialize(new DefaultAppSearchAlgorithm(this.mApps.getApps()), this, this.mLauncher, this);
    }

    @Override // com.android.launcher3.allapps.AllAppsStore.OnUpdateListener
    public void onAppsUpdated() {
        this.mSearchBarController.refreshSearchResult();
    }

    @Override // com.android.launcher3.allapps.SearchUiManager
    public void resetSearch() {
        this.mSearchBarController.reset();
    }

    @Override // com.android.launcher3.allapps.SearchUiManager
    public void preDispatchKeyEvent(KeyEvent event) {
        if (this.mSearchBarController.isSearchFieldFocused() || event.getAction() != 0) {
            return;
        }
        int unicodeChar = event.getUnicodeChar();
        if (((unicodeChar <= 0 || Character.isWhitespace(unicodeChar) || Character.isSpaceChar(unicodeChar)) ? false : true) && TextKeyListener.getInstance().onKeyDown(this, this.mSearchQueryBuilder, event.getKeyCode(), event) && this.mSearchQueryBuilder.length() > 0) {
            this.mSearchBarController.focusSearchField();
        }
    }

    @Override // com.android.launcher3.allapps.search.AllAppsSearchBarController.Callbacks
    public void onSearchResult(String query, ArrayList<ComponentKey> apps) {
        if (apps != null) {
            this.mApps.setOrderedFilter(apps);
            notifyResultChanged();
            this.mAppsView.setLastSearchQuery(query);
        }
    }

    @Override // com.android.launcher3.allapps.search.AllAppsSearchBarController.Callbacks
    public void clearSearchResult() {
        if (this.mApps.setOrderedFilter(null)) {
            notifyResultChanged();
        }
        this.mSearchQueryBuilder.clear();
        this.mSearchQueryBuilder.clearSpans();
        Selection.setSelection(this.mSearchQueryBuilder, 0);
        this.mAppsView.onClearSearchResult();
    }

    private void notifyResultChanged() {
        this.mAppsView.onSearchResultsChanged();
    }

    @Override // com.android.launcher3.Insettable
    public void setInsets(Rect insets) {
        ((ViewGroup.MarginLayoutParams) getLayoutParams()).topMargin = Math.round(Math.max(-this.mFixedTranslationY, insets.top - this.mMarginTopAdjusting));
        requestLayout();
    }

    @Override // com.android.launcher3.allapps.SearchUiManager
    public float getScrollRangeDelta(Rect insets) {
        if (this.mLauncher.getDeviceProfile().isVerticalBarLayout()) {
            return 0.0f;
        }
        return insets.bottom + Math.round(Math.max(-this.mFixedTranslationY, insets.top - this.mMarginTopAdjusting)) + this.mFixedTranslationY;
    }

    @Override // com.android.launcher3.allapps.SearchUiManager
    public void setContentVisibility(int visibleElements, PropertySetter setter, Interpolator interpolator) {
        setter.setViewAlpha(this, (visibleElements & 4) != 0 ? 1.0f : 0.0f, interpolator);
    }
}
