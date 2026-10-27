package com.amazon.mp3.actionbar.view;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import com.amazon.mp3.actionbar.StoreMenuVisibilityListener;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.search.suggestion.SearchSuggestionActionBarView;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.events.NavigationAppEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ActionBarStoreTitleView extends SearchSuggestionActionBarView implements StoreMenuVisibilityListener {
    private static final String TAG = ActionBarStoreTitleView.class.getSimpleName();
    private static Bundle sStateBundle = new Bundle();
    private ImageView mArrowView;
    private ComponentName mComponentName;
    private boolean mIsStoreMenuVisible;
    private final List<StoreMenuVisibilityListener> mListeners;
    private TextView mSubtitleView;
    private TextView mTitleView;

    public ActionBarStoreTitleView(final Context context, ComponentName componentName) {
        super(context);
        this.mListeners = new ArrayList();
        this.mComponentName = componentName;
        addStoreMenuVisibilityListener(this);
        init();
    }

    private void init() {
        Context context = getContext();
        LayoutInflater.from(context).cloneInContext(new ContextThemeWrapper(context, R.style.StoreHeaderSearch)).inflate(R.layout.store_header, (ViewGroup) this, true);
        View viewFindViewById = findViewById(R.id.store_title_holder);
        this.mTitleView = (TextView) findViewById(R.id.store_title);
        this.mSubtitleView = (TextView) findViewById(R.id.store_subtitle);
        this.mArrowView = (ImageView) findViewById(R.id.store_header_arrow);
        this.mSearchView = (SearchView) findViewById(R.id.search_view);
        initThrottleSearchSuggestion();
        initSearchView();
        restoreSavedState();
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarStoreTitleView.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View v) {
                if (ActionBarStoreTitleView.this.mArrowView.isShown()) {
                    ActionBarStoreTitleView.this.mIsStoreMenuVisible = !r3.mIsStoreMenuVisible;
                    Iterator it = ActionBarStoreTitleView.this.mListeners.iterator();
                    while (it.hasNext()) {
                        ((StoreMenuVisibilityListener) it.next()).toggleStoreMenuVisibility(ActionBarStoreTitleView.this.mIsStoreMenuVisible);
                    }
                }
            }
        });
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unregisterSearchSuggestionListener();
        removeStoreMenuVisibilityListener(this);
    }

    public static boolean isTitleLoaded() {
        return sStateBundle.size() > 0;
    }

    private void restoreSavedState() {
        setTitle(sStateBundle.getString(FragmentState.TITLE));
        setSubtitle(sStateBundle.getString(FragmentState.SUBTITLE));
        setRefineMenuAvailable(sStateBundle.getBoolean(FragmentState.REFINE_MENU_AVAILABILITY));
        toggleStoreMenuVisibility(sStateBundle.getBoolean(FragmentState.STORE_MENU_VISIBILITY));
        SearchView searchView = this.mSearchView;
        if (searchView != null) {
            searchView.setIconified(sStateBundle.getBoolean(FragmentState.COLLAPSE_SEARCH_VIEW, true));
        }
    }

    private void initSearchView() {
        final Context context = this.mSearchView.getContext();
        this.mSearchView.setSearchableInfo(((SearchManager) context.getSystemService("search")).getSearchableInfo(this.mComponentName));
        this.mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarStoreTitleView.2
            @Override // android.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String query) {
                if (query == null || !"A2ZLOGGING".equalsIgnoreCase(query)) {
                    if (query != null) {
                        ((SearchSuggestionActionBarView) ActionBarStoreTitleView.this).mSearchView.setQuery(query, false);
                    }
                    return false;
                }
                Log.setNonCriticalLoggingEnabled(true);
                Log.info(ActionBarStoreTitleView.TAG, "Logging has been turned on");
                BauhausToastUtils.showDefaultToast(context, "Logging has been turned on", 0);
                ((SearchSuggestionActionBarView) ActionBarStoreTitleView.this).mSearchView.setQuery("", false);
                ((SearchSuggestionActionBarView) ActionBarStoreTitleView.this).mSearchView.setIconified(true);
                return true;
            }

            @Override // android.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String newText) {
                return ActionBarStoreTitleView.this.querySearchSuggestions(newText);
            }
        });
        this.mSearchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarStoreTitleView.3
            @Override // android.widget.SearchView.OnSuggestionListener
            public boolean onSuggestionSelect(final int position) {
                return false;
            }

            @Override // android.widget.SearchView.OnSuggestionListener
            public boolean onSuggestionClick(final int position) {
                ((SearchSuggestionActionBarView) ActionBarStoreTitleView.this).mSearchView.setQuery(ActionBarStoreTitleView.this.getQueryAtPosition(position), true);
                return true;
            }
        });
        this.mSearchView.setOnSearchClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarStoreTitleView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ViewGroup.LayoutParams layoutParams = ((SearchSuggestionActionBarView) ActionBarStoreTitleView.this).mSearchView.getLayoutParams();
                layoutParams.width = -1;
                ((SearchSuggestionActionBarView) ActionBarStoreTitleView.this).mSearchView.setLayoutParams(layoutParams);
                NavigationAppEvent.builder("SEARCH_TAB_PRIME").withOrientation(ScreenUtil.getScreenOrientation(ActionBarStoreTitleView.this.getContext())).publish();
            }
        });
        this.mSearchView.setOnCloseListener(new SearchView.OnCloseListener() { // from class: com.amazon.mp3.actionbar.view.ActionBarStoreTitleView.5
            @Override // android.widget.SearchView.OnCloseListener
            public boolean onClose() {
                ViewGroup.LayoutParams layoutParams = ((SearchSuggestionActionBarView) ActionBarStoreTitleView.this).mSearchView.getLayoutParams();
                layoutParams.width = -2;
                ((SearchSuggestionActionBarView) ActionBarStoreTitleView.this).mSearchView.setLayoutParams(layoutParams);
                return false;
            }
        });
        ((ImageView) this.mSearchView.findViewById(getResources().getIdentifier("android:id/search_button", null, null))).setImageResource(R.drawable.action_bar_search_button);
    }

    public void setTitle(String title) {
        TextView textView = this.mTitleView;
        if (textView != null) {
            textView.setText(title);
        }
        sStateBundle.putString(FragmentState.TITLE, title);
    }

    public void setSubtitle(String subtitle) {
        TextView textView = this.mSubtitleView;
        if (textView != null) {
            if (subtitle == null) {
                textView.setVisibility(8);
                this.mSubtitleView.setText("");
            } else {
                textView.setVisibility(0);
                this.mSubtitleView.setText(subtitle);
            }
        }
        if (subtitle == null || "".equals(subtitle)) {
            sStateBundle.remove(FragmentState.SUBTITLE);
            this.mTitleView.setTextSize(0, getResources().getDimension(R.dimen.action_bar_title_text_size));
        } else {
            sStateBundle.putString(FragmentState.SUBTITLE, subtitle);
            this.mTitleView.setTextSize(0, getResources().getDimension(R.dimen.action_bar_primary_text_size));
        }
    }

    public void onSearchRequested() {
        SearchView searchView = this.mSearchView;
        if (searchView == null || this.mIsStoreMenuVisible) {
            return;
        }
        searchView.setIconified(!searchView.isIconified());
        sStateBundle.putBoolean(FragmentState.COLLAPSE_SEARCH_VIEW, this.mSearchView.isIconified());
    }

    public boolean searchHasFocus() {
        SearchView searchView = this.mSearchView;
        if (searchView != null) {
            return searchView.hasFocus();
        }
        return false;
    }

    public void collapseSearchBar() {
        SearchView searchView = this.mSearchView;
        if (searchView != null) {
            searchView.setQuery("", false);
            this.mSearchView.setIconified(true);
            sStateBundle.putBoolean(FragmentState.COLLAPSE_SEARCH_VIEW, true);
        }
    }

    public void setRefineMenuAvailable(boolean refineAvailble) {
        ImageView imageView = this.mArrowView;
        if (imageView != null) {
            imageView.setVisibility(refineAvailble ? 0 : 4);
        }
        sStateBundle.putBoolean(FragmentState.REFINE_MENU_AVAILABILITY, refineAvailble);
    }

    public void addStoreMenuVisibilityListener(StoreMenuVisibilityListener listener) {
        this.mListeners.add(listener);
    }

    public void removeStoreMenuVisibilityListener(StoreMenuVisibilityListener listener) {
        this.mListeners.remove(listener);
    }

    @Override // com.amazon.mp3.actionbar.StoreMenuVisibilityListener
    public void toggleStoreMenuVisibility(boolean isVisible) {
        if (isVisible) {
            this.mSubtitleView.setTextColor(getResources().getColor(R.color.white));
            this.mArrowView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_drop_down));
        } else {
            this.mSubtitleView.setTextColor(getResources().getColor(R.color.grey));
            this.mArrowView.setImageDrawable(getResources().getDrawable(R.drawable.arrow_drop_down));
        }
        sStateBundle.putBoolean(FragmentState.STORE_MENU_VISIBILITY, isVisible);
    }

    private static class FragmentState {
        public static String COLLAPSE_SEARCH_VIEW = "COLLAPSE_SEARCH_VIEW";
        public static String REFINE_MENU_AVAILABILITY = "REFINE_MENU_AVAILABILITY";
        public static String STORE_MENU_VISIBILITY = "STORE_MENU_VISIBILITY";
        public static String SUBTITLE = "SUBTITLE";
        public static String TITLE = "TITLE";

        private FragmentState() {
        }
    }
}
