package com.amazon.music.find.api.searchsuggestion;

import com.amazon.music.find.service.ExecutionContext;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EmptySearchSuggestionApi.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/music/find/api/searchsuggestion/EmptySearchSuggestionApi;", "Lcom/amazon/music/find/api/searchsuggestion/SearchSuggestionApi;", "()V", "getSearchSuggestions", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/music/find/api/searchsuggestion/SearchSuggestionResponse;", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", "request", "Lcom/amazon/music/find/api/searchsuggestion/SearchSuggestionRequest;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EmptySearchSuggestionApi implements SearchSuggestionApi {
    @Override // com.amazon.music.find.api.searchsuggestion.SearchSuggestionApi
    public Observable<SearchSuggestionResponse> getSearchSuggestions(ExecutionContext executionContext, SearchSuggestionRequest request) {
        Intrinsics.checkNotNullParameter(executionContext, "executionContext");
        Intrinsics.checkNotNullParameter(request, "request");
        Observable<SearchSuggestionResponse> observableJust = Observable.just(new SearchSuggestionResponse(null, null, 3, null));
        Intrinsics.checkNotNullExpressionValue(observableJust, "just(SearchSuggestionResponse())");
        return observableJust;
    }
}
