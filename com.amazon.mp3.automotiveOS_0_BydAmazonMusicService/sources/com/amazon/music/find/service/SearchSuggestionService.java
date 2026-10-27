package com.amazon.music.find.service;

import com.amazon.music.find.api.searchsuggestion.SearchSuggestionResponse;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchSuggestionService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/amazon/music/find/service/SearchSuggestionService;", "", "getSearchSuggestions", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/music/find/api/searchsuggestion/SearchSuggestionResponse;", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", SearchIntents.EXTRA_QUERY, "", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchSuggestionService {
    Observable<SearchSuggestionResponse> getSearchSuggestions(ExecutionContext executionContext, String query);
}
