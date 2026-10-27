package androidx.leanback.app;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.leanback.R;
import androidx.leanback.widget.SearchOrbView;
import androidx.leanback.widget.TitleHelper;
import androidx.leanback.widget.TitleViewAdapter;

/* JADX INFO: loaded from: classes3.dex */
public class BrandedSupportFragment extends Fragment {
    private static final String TITLE_SHOW = "titleShow";
    private Drawable mBadgeDrawable;
    private View.OnClickListener mExternalOnSearchClickedListener;
    private boolean mSearchAffordanceColorSet;
    private SearchOrbView.Colors mSearchAffordanceColors;
    private boolean mShowingTitle = true;
    private CharSequence mTitle;
    private TitleHelper mTitleHelper;
    private View mTitleView;
    private TitleViewAdapter mTitleViewAdapter;

    public View onInflateTitleView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        TypedValue typedValue = new TypedValue();
        boolean found = parent.getContext().getTheme().resolveAttribute(R.attr.browseTitleViewLayout, typedValue, true);
        return inflater.inflate(found ? typedValue.resourceId : R.layout.lb_browse_title, parent, false);
    }

    public void installTitleView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View titleLayoutRoot = onInflateTitleView(inflater, parent, savedInstanceState);
        if (titleLayoutRoot != null) {
            parent.addView(titleLayoutRoot);
            setTitleView(titleLayoutRoot.findViewById(R.id.browse_title_group));
        } else {
            setTitleView(null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setTitleView(View view) {
        this.mTitleView = view;
        if (view == 0) {
            this.mTitleViewAdapter = null;
            this.mTitleHelper = null;
            return;
        }
        TitleViewAdapter titleViewAdapter = ((TitleViewAdapter.Provider) view).getTitleViewAdapter();
        this.mTitleViewAdapter = titleViewAdapter;
        titleViewAdapter.setTitle(this.mTitle);
        this.mTitleViewAdapter.setBadgeDrawable(this.mBadgeDrawable);
        if (this.mSearchAffordanceColorSet) {
            this.mTitleViewAdapter.setSearchAffordanceColors(this.mSearchAffordanceColors);
        }
        View.OnClickListener onClickListener = this.mExternalOnSearchClickedListener;
        if (onClickListener != null) {
            setOnSearchClickedListener(onClickListener);
        }
        if (getView() instanceof ViewGroup) {
            this.mTitleHelper = new TitleHelper((ViewGroup) getView(), this.mTitleView);
        }
    }

    public View getTitleView() {
        return this.mTitleView;
    }

    public TitleViewAdapter getTitleViewAdapter() {
        return this.mTitleViewAdapter;
    }

    TitleHelper getTitleHelper() {
        return this.mTitleHelper;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(TITLE_SHOW, this.mShowingTitle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            this.mShowingTitle = savedInstanceState.getBoolean(TITLE_SHOW);
        }
        View view2 = this.mTitleView;
        if (view2 != null && (view instanceof ViewGroup)) {
            TitleHelper titleHelper = new TitleHelper((ViewGroup) view, view2);
            this.mTitleHelper = titleHelper;
            titleHelper.showTitle(this.mShowingTitle);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mTitleHelper = null;
    }

    public void showTitle(boolean show) {
        if (show == this.mShowingTitle) {
            return;
        }
        this.mShowingTitle = show;
        TitleHelper titleHelper = this.mTitleHelper;
        if (titleHelper != null) {
            titleHelper.showTitle(show);
        }
    }

    public void showTitle(int flags) {
        TitleViewAdapter titleViewAdapter = this.mTitleViewAdapter;
        if (titleViewAdapter != null) {
            titleViewAdapter.updateComponentsVisibility(flags);
        }
        showTitle(true);
    }

    public void setBadgeDrawable(Drawable drawable) {
        if (this.mBadgeDrawable != drawable) {
            this.mBadgeDrawable = drawable;
            TitleViewAdapter titleViewAdapter = this.mTitleViewAdapter;
            if (titleViewAdapter != null) {
                titleViewAdapter.setBadgeDrawable(drawable);
            }
        }
    }

    public Drawable getBadgeDrawable() {
        return this.mBadgeDrawable;
    }

    public void setTitle(CharSequence title) {
        this.mTitle = title;
        TitleViewAdapter titleViewAdapter = this.mTitleViewAdapter;
        if (titleViewAdapter != null) {
            titleViewAdapter.setTitle(title);
        }
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public void setOnSearchClickedListener(View.OnClickListener listener) {
        this.mExternalOnSearchClickedListener = listener;
        TitleViewAdapter titleViewAdapter = this.mTitleViewAdapter;
        if (titleViewAdapter != null) {
            titleViewAdapter.setOnSearchClickedListener(listener);
        }
    }

    public void setSearchAffordanceColors(SearchOrbView.Colors colors) {
        this.mSearchAffordanceColors = colors;
        this.mSearchAffordanceColorSet = true;
        TitleViewAdapter titleViewAdapter = this.mTitleViewAdapter;
        if (titleViewAdapter != null) {
            titleViewAdapter.setSearchAffordanceColors(colors);
        }
    }

    public SearchOrbView.Colors getSearchAffordanceColors() {
        if (this.mSearchAffordanceColorSet) {
            return this.mSearchAffordanceColors;
        }
        TitleViewAdapter titleViewAdapter = this.mTitleViewAdapter;
        if (titleViewAdapter == null) {
            throw new IllegalStateException("Fragment views not yet created");
        }
        return titleViewAdapter.getSearchAffordanceColors();
    }

    public void setSearchAffordanceColor(int color) {
        setSearchAffordanceColors(new SearchOrbView.Colors(color));
    }

    public int getSearchAffordanceColor() {
        return getSearchAffordanceColors().color;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.mTitleViewAdapter != null) {
            showTitle(this.mShowingTitle);
            this.mTitleViewAdapter.setAnimationEnabled(true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        TitleViewAdapter titleViewAdapter = this.mTitleViewAdapter;
        if (titleViewAdapter != null) {
            titleViewAdapter.setAnimationEnabled(false);
        }
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        TitleViewAdapter titleViewAdapter = this.mTitleViewAdapter;
        if (titleViewAdapter != null) {
            titleViewAdapter.setAnimationEnabled(true);
        }
    }

    public final boolean isShowingTitle() {
        return this.mShowingTitle;
    }
}
