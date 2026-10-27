package androidx.leanback.app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.speech.SpeechRecognizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.CompletionInfo;
import androidx.fragment.app.Fragment;
import androidx.leanback.R;
import androidx.leanback.widget.BrowseFrameLayout;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.SearchBar;
import androidx.leanback.widget.SearchOrbView;
import androidx.leanback.widget.SpeechRecognitionCallback;
import androidx.leanback.widget.VerticalGridView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class SearchSupportFragment extends Fragment {
    static final int AUDIO_PERMISSION_REQUEST_CODE = 0;
    static final boolean DEBUG = false;
    private static final String EXTRA_LEANBACK_BADGE_PRESENT = "LEANBACK_BADGE_PRESENT";
    static final int QUERY_COMPLETE = 2;
    static final int RESULTS_CHANGED = 1;
    static final long SPEECH_RECOGNITION_DELAY_MS = 300;
    private Drawable mBadgeDrawable;
    private ExternalQuery mExternalQuery;
    private boolean mIsPaused;
    private OnItemViewClickedListener mOnItemViewClickedListener;
    OnItemViewSelectedListener mOnItemViewSelectedListener;
    private boolean mPendingStartRecognitionWhenPaused;
    SearchResultProvider mProvider;
    ObjectAdapter mResultAdapter;
    RowsSupportFragment mRowsSupportFragment;
    SearchBar mSearchBar;
    private SpeechRecognitionCallback mSpeechRecognitionCallback;
    private SpeechRecognizer mSpeechRecognizer;
    int mStatus;
    private String mTitle;
    static final String TAG = SearchSupportFragment.class.getSimpleName();
    private static final String ARG_PREFIX = SearchSupportFragment.class.getCanonicalName();
    private static final String ARG_QUERY = ARG_PREFIX + ".query";
    private static final String ARG_TITLE = ARG_PREFIX + ".title";
    final ObjectAdapter.DataObserver mAdapterObserver = new ObjectAdapter.DataObserver() { // from class: androidx.leanback.app.SearchSupportFragment.1
        @Override // androidx.leanback.widget.ObjectAdapter.DataObserver
        public void onChanged() {
            SearchSupportFragment.this.mHandler.removeCallbacks(SearchSupportFragment.this.mResultsChangedCallback);
            SearchSupportFragment.this.mHandler.post(SearchSupportFragment.this.mResultsChangedCallback);
        }
    };
    final Handler mHandler = new Handler();
    final Runnable mResultsChangedCallback = new Runnable() { // from class: androidx.leanback.app.SearchSupportFragment.2
        @Override // java.lang.Runnable
        public void run() {
            if (SearchSupportFragment.this.mRowsSupportFragment != null && SearchSupportFragment.this.mRowsSupportFragment.getAdapter() != SearchSupportFragment.this.mResultAdapter && (SearchSupportFragment.this.mRowsSupportFragment.getAdapter() != null || SearchSupportFragment.this.mResultAdapter.size() != 0)) {
                SearchSupportFragment.this.mRowsSupportFragment.setAdapter(SearchSupportFragment.this.mResultAdapter);
                SearchSupportFragment.this.mRowsSupportFragment.setSelectedPosition(0);
            }
            SearchSupportFragment.this.updateSearchBarVisibility();
            SearchSupportFragment.this.mStatus |= 1;
            if ((SearchSupportFragment.this.mStatus & 2) != 0) {
                SearchSupportFragment.this.updateFocus();
            }
        }
    };
    private final Runnable mSetSearchResultProvider = new Runnable() { // from class: androidx.leanback.app.SearchSupportFragment.3
        @Override // java.lang.Runnable
        public void run() {
            if (SearchSupportFragment.this.mRowsSupportFragment == null) {
                return;
            }
            ObjectAdapter adapter = SearchSupportFragment.this.mProvider.getResultsAdapter();
            if (adapter != SearchSupportFragment.this.mResultAdapter) {
                boolean firstTime = SearchSupportFragment.this.mResultAdapter == null;
                SearchSupportFragment.this.releaseAdapter();
                SearchSupportFragment.this.mResultAdapter = adapter;
                if (SearchSupportFragment.this.mResultAdapter != null) {
                    SearchSupportFragment.this.mResultAdapter.registerObserver(SearchSupportFragment.this.mAdapterObserver);
                }
                if (!firstTime || (SearchSupportFragment.this.mResultAdapter != null && SearchSupportFragment.this.mResultAdapter.size() != 0)) {
                    SearchSupportFragment.this.mRowsSupportFragment.setAdapter(SearchSupportFragment.this.mResultAdapter);
                }
                SearchSupportFragment.this.executePendingQuery();
            }
            if (SearchSupportFragment.this.mAutoStartRecognition) {
                SearchSupportFragment.this.mHandler.removeCallbacks(SearchSupportFragment.this.mStartRecognitionRunnable);
                SearchSupportFragment.this.mHandler.postDelayed(SearchSupportFragment.this.mStartRecognitionRunnable, SearchSupportFragment.SPEECH_RECOGNITION_DELAY_MS);
            } else {
                SearchSupportFragment.this.updateFocus();
            }
        }
    };
    final Runnable mStartRecognitionRunnable = new Runnable() { // from class: androidx.leanback.app.SearchSupportFragment.4
        @Override // java.lang.Runnable
        public void run() {
            SearchSupportFragment.this.mAutoStartRecognition = false;
            SearchSupportFragment.this.mSearchBar.startRecognition();
        }
    };
    String mPendingQuery = null;
    boolean mAutoStartRecognition = true;
    private SearchBar.SearchBarPermissionListener mPermissionListener = new SearchBar.SearchBarPermissionListener() { // from class: androidx.leanback.app.SearchSupportFragment.5
        @Override // androidx.leanback.widget.SearchBar.SearchBarPermissionListener
        public void requestAudioPermission() {
            SearchSupportFragment.this.requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 0);
        }
    };

    public interface SearchResultProvider {
        ObjectAdapter getResultsAdapter();

        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0 && permissions.length > 0 && permissions[0].equals("android.permission.RECORD_AUDIO") && grantResults[0] == 0) {
            startRecognition();
        }
    }

    public static Bundle createArgs(Bundle args, String query) {
        return createArgs(args, query, null);
    }

    public static Bundle createArgs(Bundle args, String query, String title) {
        if (args == null) {
            args = new Bundle();
        }
        args.putString(ARG_QUERY, query);
        args.putString(ARG_TITLE, title);
        return args;
    }

    public static SearchSupportFragment newInstance(String query) {
        SearchSupportFragment fragment = new SearchSupportFragment();
        Bundle args = createArgs(null, query);
        fragment.setArguments(args);
        return fragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        if (this.mAutoStartRecognition) {
            this.mAutoStartRecognition = savedInstanceState == null;
        }
        super.onCreate(savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.lb_search_fragment, container, false);
        BrowseFrameLayout searchFrame = (BrowseFrameLayout) root.findViewById(R.id.lb_search_frame);
        SearchBar searchBar = (SearchBar) searchFrame.findViewById(R.id.lb_search_bar);
        this.mSearchBar = searchBar;
        searchBar.setSearchBarListener(new SearchBar.SearchBarListener() { // from class: androidx.leanback.app.SearchSupportFragment.6
            @Override // androidx.leanback.widget.SearchBar.SearchBarListener
            public void onSearchQueryChange(String query) {
                if (SearchSupportFragment.this.mProvider != null) {
                    SearchSupportFragment.this.retrieveResults(query);
                } else {
                    SearchSupportFragment.this.mPendingQuery = query;
                }
            }

            @Override // androidx.leanback.widget.SearchBar.SearchBarListener
            public void onSearchQuerySubmit(String query) {
                SearchSupportFragment.this.submitQuery(query);
            }

            @Override // androidx.leanback.widget.SearchBar.SearchBarListener
            public void onKeyboardDismiss(String query) {
                SearchSupportFragment.this.queryComplete();
            }
        });
        this.mSearchBar.setSpeechRecognitionCallback(this.mSpeechRecognitionCallback);
        this.mSearchBar.setPermissionListener(this.mPermissionListener);
        applyExternalQuery();
        readArguments(getArguments());
        Drawable drawable = this.mBadgeDrawable;
        if (drawable != null) {
            setBadgeDrawable(drawable);
        }
        String str = this.mTitle;
        if (str != null) {
            setTitle(str);
        }
        if (getChildFragmentManager().findFragmentById(R.id.lb_results_frame) == null) {
            this.mRowsSupportFragment = new RowsSupportFragment();
            getChildFragmentManager().beginTransaction().replace(R.id.lb_results_frame, this.mRowsSupportFragment).commit();
        } else {
            this.mRowsSupportFragment = (RowsSupportFragment) getChildFragmentManager().findFragmentById(R.id.lb_results_frame);
        }
        this.mRowsSupportFragment.setOnItemViewSelectedListener(new OnItemViewSelectedListener() { // from class: androidx.leanback.app.SearchSupportFragment.7
            @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
            public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                SearchSupportFragment.this.updateSearchBarVisibility();
                if (SearchSupportFragment.this.mOnItemViewSelectedListener != null) {
                    SearchSupportFragment.this.mOnItemViewSelectedListener.onItemSelected(itemViewHolder, item, rowViewHolder, row);
                }
            }
        });
        this.mRowsSupportFragment.setOnItemViewClickedListener(this.mOnItemViewClickedListener);
        this.mRowsSupportFragment.setExpand(true);
        if (this.mProvider != null) {
            onSetSearchResultProvider();
        }
        searchFrame.setOnFocusSearchListener(new BrowseFrameLayout.OnFocusSearchListener() { // from class: androidx.leanback.app.SearchSupportFragment.8
            @Override // androidx.leanback.widget.BrowseFrameLayout.OnFocusSearchListener
            public View onFocusSearch(View focused, int direction) {
                if (SearchSupportFragment.this.mRowsSupportFragment != null && SearchSupportFragment.this.mRowsSupportFragment.getView() != null && SearchSupportFragment.this.mRowsSupportFragment.getView().hasFocus()) {
                    if (direction == 33) {
                        return SearchSupportFragment.this.mSearchBar.findViewById(R.id.lb_search_bar_speech_orb);
                    }
                    return null;
                }
                if (SearchSupportFragment.this.mSearchBar.hasFocus() && direction == 130 && SearchSupportFragment.this.mRowsSupportFragment.getView() != null && SearchSupportFragment.this.mResultAdapter != null && SearchSupportFragment.this.mResultAdapter.size() > 0) {
                    return SearchSupportFragment.this.mRowsSupportFragment.getView();
                }
                return null;
            }
        });
        return root;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        VerticalGridView list = this.mRowsSupportFragment.getVerticalGridView();
        int mContainerListAlignTop = getResources().getDimensionPixelSize(R.dimen.lb_search_browse_rows_align_top);
        list.setItemAlignmentOffset(0);
        list.setItemAlignmentOffsetPercent(-1.0f);
        list.setWindowAlignmentOffset(mContainerListAlignTop);
        list.setWindowAlignmentOffsetPercent(-1.0f);
        list.setWindowAlignment(0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mIsPaused = false;
        if (this.mSpeechRecognitionCallback == null && this.mSpeechRecognizer == null) {
            SpeechRecognizer speechRecognizerCreateSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(getContext());
            this.mSpeechRecognizer = speechRecognizerCreateSpeechRecognizer;
            this.mSearchBar.setSpeechRecognizer(speechRecognizerCreateSpeechRecognizer);
        }
        if (this.mPendingStartRecognitionWhenPaused) {
            this.mPendingStartRecognitionWhenPaused = false;
            this.mSearchBar.startRecognition();
        } else {
            this.mSearchBar.stopRecognition();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        releaseRecognizer();
        this.mIsPaused = true;
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        releaseAdapter();
        super.onDestroy();
    }

    public RowsSupportFragment getRowsSupportFragment() {
        return this.mRowsSupportFragment;
    }

    private void releaseRecognizer() {
        if (this.mSpeechRecognizer != null) {
            this.mSearchBar.setSpeechRecognizer(null);
            this.mSpeechRecognizer.destroy();
            this.mSpeechRecognizer = null;
        }
    }

    public void startRecognition() {
        if (this.mIsPaused) {
            this.mPendingStartRecognitionWhenPaused = true;
        } else {
            this.mSearchBar.startRecognition();
        }
    }

    public void setSearchResultProvider(SearchResultProvider searchResultProvider) {
        if (this.mProvider != searchResultProvider) {
            this.mProvider = searchResultProvider;
            onSetSearchResultProvider();
        }
    }

    public void setOnItemViewSelectedListener(OnItemViewSelectedListener listener) {
        this.mOnItemViewSelectedListener = listener;
    }

    public void setOnItemViewClickedListener(OnItemViewClickedListener listener) {
        if (listener != this.mOnItemViewClickedListener) {
            this.mOnItemViewClickedListener = listener;
            RowsSupportFragment rowsSupportFragment = this.mRowsSupportFragment;
            if (rowsSupportFragment != null) {
                rowsSupportFragment.setOnItemViewClickedListener(listener);
            }
        }
    }

    public void setTitle(String title) {
        this.mTitle = title;
        SearchBar searchBar = this.mSearchBar;
        if (searchBar != null) {
            searchBar.setTitle(title);
        }
    }

    public String getTitle() {
        SearchBar searchBar = this.mSearchBar;
        if (searchBar != null) {
            return searchBar.getTitle();
        }
        return null;
    }

    public void setBadgeDrawable(Drawable drawable) {
        this.mBadgeDrawable = drawable;
        SearchBar searchBar = this.mSearchBar;
        if (searchBar != null) {
            searchBar.setBadgeDrawable(drawable);
        }
    }

    public Drawable getBadgeDrawable() {
        SearchBar searchBar = this.mSearchBar;
        if (searchBar != null) {
            return searchBar.getBadgeDrawable();
        }
        return null;
    }

    public void setSearchAffordanceColors(SearchOrbView.Colors colors) {
        SearchBar searchBar = this.mSearchBar;
        if (searchBar != null) {
            searchBar.setSearchAffordanceColors(colors);
        }
    }

    public void setSearchAffordanceColorsInListening(SearchOrbView.Colors colors) {
        SearchBar searchBar = this.mSearchBar;
        if (searchBar != null) {
            searchBar.setSearchAffordanceColorsInListening(colors);
        }
    }

    public void displayCompletions(List<String> completions) {
        this.mSearchBar.displayCompletions(completions);
    }

    public void displayCompletions(CompletionInfo[] completions) {
        this.mSearchBar.displayCompletions(completions);
    }

    @Deprecated
    public void setSpeechRecognitionCallback(SpeechRecognitionCallback callback) {
        this.mSpeechRecognitionCallback = callback;
        SearchBar searchBar = this.mSearchBar;
        if (searchBar != null) {
            searchBar.setSpeechRecognitionCallback(callback);
        }
        if (callback != null) {
            releaseRecognizer();
        }
    }

    public void setSearchQuery(String query, boolean submit) {
        if (query == null) {
            return;
        }
        this.mExternalQuery = new ExternalQuery(query, submit);
        applyExternalQuery();
        if (this.mAutoStartRecognition) {
            this.mAutoStartRecognition = false;
            this.mHandler.removeCallbacks(this.mStartRecognitionRunnable);
        }
    }

    public void setSearchQuery(Intent intent, boolean submit) {
        ArrayList<String> matches = intent.getStringArrayListExtra("android.speech.extra.RESULTS");
        if (matches != null && matches.size() > 0) {
            setSearchQuery(matches.get(0), submit);
        }
    }

    public Intent getRecognizerIntent() {
        Intent recognizerIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        recognizerIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        recognizerIntent.putExtra("android.speech.extra.PARTIAL_RESULTS", true);
        SearchBar searchBar = this.mSearchBar;
        if (searchBar != null && searchBar.getHint() != null) {
            recognizerIntent.putExtra("android.speech.extra.PROMPT", this.mSearchBar.getHint());
        }
        recognizerIntent.putExtra(EXTRA_LEANBACK_BADGE_PRESENT, this.mBadgeDrawable != null);
        return recognizerIntent;
    }

    void retrieveResults(String searchQuery) {
        if (this.mProvider.onQueryTextChange(searchQuery)) {
            this.mStatus &= -3;
        }
    }

    void submitQuery(String query) {
        queryComplete();
        SearchResultProvider searchResultProvider = this.mProvider;
        if (searchResultProvider != null) {
            searchResultProvider.onQueryTextSubmit(query);
        }
    }

    void queryComplete() {
        this.mStatus |= 2;
        focusOnResults();
    }

    void updateSearchBarVisibility() {
        ObjectAdapter objectAdapter;
        RowsSupportFragment rowsSupportFragment = this.mRowsSupportFragment;
        int position = rowsSupportFragment != null ? rowsSupportFragment.getSelectedPosition() : -1;
        this.mSearchBar.setVisibility((position <= 0 || (objectAdapter = this.mResultAdapter) == null || objectAdapter.size() == 0) ? 0 : 8);
    }

    void updateFocus() {
        RowsSupportFragment rowsSupportFragment;
        ObjectAdapter objectAdapter = this.mResultAdapter;
        if (objectAdapter != null && objectAdapter.size() > 0 && (rowsSupportFragment = this.mRowsSupportFragment) != null && rowsSupportFragment.getAdapter() == this.mResultAdapter) {
            focusOnResults();
        } else {
            this.mSearchBar.requestFocus();
        }
    }

    private void focusOnResults() {
        RowsSupportFragment rowsSupportFragment = this.mRowsSupportFragment;
        if (rowsSupportFragment != null && rowsSupportFragment.getVerticalGridView() != null && this.mResultAdapter.size() != 0 && this.mRowsSupportFragment.getVerticalGridView().requestFocus()) {
            this.mStatus &= -2;
        }
    }

    private void onSetSearchResultProvider() {
        this.mHandler.removeCallbacks(this.mSetSearchResultProvider);
        this.mHandler.post(this.mSetSearchResultProvider);
    }

    void releaseAdapter() {
        ObjectAdapter objectAdapter = this.mResultAdapter;
        if (objectAdapter != null) {
            objectAdapter.unregisterObserver(this.mAdapterObserver);
            this.mResultAdapter = null;
        }
    }

    void executePendingQuery() {
        if (this.mPendingQuery != null && this.mResultAdapter != null) {
            String query = this.mPendingQuery;
            this.mPendingQuery = null;
            retrieveResults(query);
        }
    }

    private void applyExternalQuery() {
        SearchBar searchBar;
        ExternalQuery externalQuery = this.mExternalQuery;
        if (externalQuery == null || (searchBar = this.mSearchBar) == null) {
            return;
        }
        searchBar.setSearchQuery(externalQuery.mQuery);
        if (this.mExternalQuery.mSubmit) {
            submitQuery(this.mExternalQuery.mQuery);
        }
        this.mExternalQuery = null;
    }

    private void readArguments(Bundle args) {
        if (args == null) {
            return;
        }
        if (args.containsKey(ARG_QUERY)) {
            setSearchQuery(args.getString(ARG_QUERY));
        }
        if (args.containsKey(ARG_TITLE)) {
            setTitle(args.getString(ARG_TITLE));
        }
    }

    private void setSearchQuery(String query) {
        this.mSearchBar.setSearchQuery(query);
    }

    static class ExternalQuery {
        String mQuery;
        boolean mSubmit;

        ExternalQuery(String query, boolean submit) {
            this.mQuery = query;
            this.mSubmit = submit;
        }
    }
}
