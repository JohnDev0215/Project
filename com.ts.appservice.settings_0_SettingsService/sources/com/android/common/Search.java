package com.android.common;

import android.app.SearchableInfo;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.android.common.speech.LoggingEvents;

/* JADX INFO: loaded from: classes.dex */
public class Search {
    public static final String SOURCE = "source";
    public static final String SUGGEST_COLUMN_LAST_ACCESS_HINT = "suggest_last_access_hint";

    private Search() {
    }

    public static Cursor getSuggestions(Context context, SearchableInfo searchable, String query) {
        return getSuggestions(context, searchable, query, -1);
    }

    public static Cursor getSuggestions(Context context, SearchableInfo searchable, String query, int limit) {
        String authority;
        String[] selArgs;
        if (searchable == null || (authority = searchable.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder uriBuilder = new Uri.Builder().scheme("content").authority(authority).query(LoggingEvents.EXTRA_CALLING_APP_NAME).fragment(LoggingEvents.EXTRA_CALLING_APP_NAME);
        String contentPath = searchable.getSuggestPath();
        if (contentPath != null) {
            uriBuilder.appendEncodedPath(contentPath);
        }
        uriBuilder.appendPath("search_suggest_query");
        String selection = searchable.getSuggestSelection();
        if (selection != null) {
            String[] selArgs2 = {query};
            selArgs = selArgs2;
        } else {
            uriBuilder.appendPath(query);
            selArgs = null;
        }
        if (limit > 0) {
            uriBuilder.appendQueryParameter("limit", String.valueOf(limit));
        }
        Uri uri = uriBuilder.build();
        return context.getContentResolver().query(uri, null, selection, selArgs, null);
    }
}
