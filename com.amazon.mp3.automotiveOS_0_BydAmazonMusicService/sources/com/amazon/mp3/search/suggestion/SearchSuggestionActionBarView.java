package com.amazon.mp3.search.suggestion;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Handler;
import android.os.Looper;
import android.widget.LinearLayout;
import android.widget.SearchView;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.mp3.util.Log;
import com.amazon.music.search.suggestion.OnSuggestionReadyListener;
import com.amazon.music.search.suggestion.SearchSuggestionException;
import com.amazon.music.search.suggestion.ThrottleSearchSuggestion;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SearchSuggestionActionBarView extends LinearLayout {
    private static final String TAG = SearchSuggestionActionBarView.class.getSimpleName();
    protected SearchView mSearchView;
    private ThrottleSearchSuggestion mThrottleSearchSuggestion;

    public SearchSuggestionActionBarView(final Context context) {
        super(context);
    }

    protected void initThrottleSearchSuggestion() {
        ThrottleSearchSuggestion throttleSearchSuggestion = getThrottleSearchSuggestion();
        this.mThrottleSearchSuggestion = throttleSearchSuggestion;
        if (throttleSearchSuggestion != null) {
            throttleSearchSuggestion.setListener(new OnSuggestionReadyListener() { // from class: com.amazon.mp3.search.suggestion.SearchSuggestionActionBarView.1
                @Override // com.amazon.music.search.suggestion.OnSuggestionReadyListener
                public void onSuggestionReady(final List<String> suggestions) {
                    SearchSuggestionActionBarView.this.setSuggestions(suggestions);
                }
            });
        }
    }

    protected void unregisterSearchSuggestionListener() {
        ThrottleSearchSuggestion throttleSearchSuggestion = this.mThrottleSearchSuggestion;
        if (throttleSearchSuggestion != null) {
            throttleSearchSuggestion.setListener(null);
        }
    }

    protected boolean querySearchSuggestions(final String query) {
        ThrottleSearchSuggestion throttleSearchSuggestion;
        if (query != null && !query.isEmpty() && (throttleSearchSuggestion = this.mThrottleSearchSuggestion) != null) {
            try {
                throttleSearchSuggestion.query(query);
                return true;
            } catch (SearchSuggestionException e) {
                Log.error(TAG, "Error when fetching search suggestions: " + e.getMessage());
            }
        }
        return false;
    }

    protected String getQueryAtPosition(final int position) {
        Cursor cursor = (Cursor) this.mSearchView.getSuggestionsAdapter().getItem(position);
        return cursor.getString(cursor.getColumnIndex("suggest_text_1"));
    }

    protected ThrottleSearchSuggestion getThrottleSearchSuggestion() {
        return new ThrottleSearchSuggestionProvider(getContext(), new RequestInterceptorFactory(getContext()).provideRequestInterceptor()).getThrottleSearchSuggestion();
    }

    protected void setSuggestions(final List<String> suggestions) {
        final MatrixCursor suggestionsCursor = SearchSuggestionProvider.getSuggestionsCursor(suggestions);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.mp3.search.suggestion.SearchSuggestionActionBarView.2
            @Override // java.lang.Runnable
            public void run() {
                SearchView searchView = SearchSuggestionActionBarView.this.mSearchView;
                if (searchView == null || searchView.getSuggestionsAdapter() == null) {
                    return;
                }
                SearchSuggestionActionBarView.this.mSearchView.getSuggestionsAdapter().changeCursor(suggestionsCursor);
            }
        });
    }
}
