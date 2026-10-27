package com.amazon.music.find.service;

import com.amazon.music.find.model.search.SearchLibrarySource;
import com.amazon.music.find.model.search.SearchRequest;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.model.search.SeeMoreRequest;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J&\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH&J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eH&J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H&¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/find/service/SearchService;", "", "catalog", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/amazon/music/find/model/search/SearchResponse;", "executionContext", "Lcom/amazon/music/find/service/ExecutionContext;", SearchIntents.EXTRA_QUERY, "", "library", "librarySource", "Lcom/amazon/music/find/model/search/SearchLibrarySource;", "search", "searchRequest", "Lcom/amazon/music/find/model/search/SearchRequest;", PageUriUtils.DO_SEE_MORE_VALUE, "Lcom/amazon/music/find/model/search/SeeMoreResponse;", "seeMoreRequest", "Lcom/amazon/music/find/model/search/SeeMoreRequest;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchService {
    Observable<SearchResponse> catalog(ExecutionContext executionContext, String query);

    Observable<SearchResponse> library(ExecutionContext executionContext, String query, SearchLibrarySource librarySource);

    Observable<SearchResponse> search(ExecutionContext executionContext, SearchRequest searchRequest);

    Observable<SeeMoreResponse> seeMore(ExecutionContext executionContext, SeeMoreRequest seeMoreRequest);
}
