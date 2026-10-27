package com.amazon.music.search.suggestion;

import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
class ThrottleQueryTask implements Runnable {
    OnSuggestionReadyListener listener;
    SearchSuggestion mSearchSuggestion;
    String query;

    ThrottleQueryTask(SearchSuggestion searchSuggestion, String str, OnSuggestionReadyListener onSuggestionReadyListener) {
        this.mSearchSuggestion = searchSuggestion;
        this.query = str;
        this.listener = onSuggestionReadyListener;
    }

    @Override // java.lang.Runnable
    public void run() {
        List<String> listQuery = null;
        try {
            listQuery = this.mSearchSuggestion.query(this.query);
        } catch (SearchSuggestionException unused) {
            this.listener.onSuggestionReady(null);
        }
        this.listener.onSuggestionReady(listQuery);
    }
}
