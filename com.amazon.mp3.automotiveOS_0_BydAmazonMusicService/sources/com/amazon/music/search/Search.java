package com.amazon.music.search;

import com.amazon.music.pager.PagerIterator;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public final class Search {
    private SearchService mService;

    public Search(SearchService searchService) {
        this.mService = searchService;
    }

    public SearchResult search(EverythingSearchRequest everythingSearchRequest) throws VolleyError {
        if (everythingSearchRequest == null || everythingSearchRequest.getTerm() == null || everythingSearchRequest.getTerm().length() < 1) {
            return new SearchResult();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList(everythingSearchRequest.getRequests().size());
        for (com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest : everythingSearchRequest.getRequests()) {
            arrayList2.add(this.mService.search(searchRequest));
            arrayList.add(searchRequest.getQuery());
        }
        return new SearchResult(arrayList2, arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <TResultItem extends ResultItem> PageResult<TResultItem> seeMoreSync(SeeMoreRequest<TResultItem> seeMoreRequest) throws ExecutionException {
        SearchRequest searchRequest = (SearchRequest) seeMoreRequest;
        if (searchRequest != null && searchRequest.getTerm() != null && searchRequest.getTerm().length() >= 1 && searchRequest.getRequests().size() == 1) {
            com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest2 = searchRequest.getRequests().get(0);
            PagerIterator<PageResult<TResultItem>> pagerIterator = searchRequest.getPagerIterator(this.mService, searchRequest2, searchRequest2.getResultSpecs().get(0));
            if (pagerIterator.hasNext()) {
                return pagerIterator.next();
            }
        }
        return null;
    }
}
