package com.amazon.music.search;

import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.ResultItem;
import com.amazon.tenzing.textsearch.v1_1.Hit;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import com.android.volley.VolleyError;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
abstract class ResultItemPagerIterator<TResultItem extends ResultItem> implements PagerIterator<PageResult<TResultItem>> {
    private boolean mFirstRequest;
    private String mNextToken;
    private final com.amazon.tenzing.textsearch.v1_1.SearchRequest mOriginalRequest;
    private final ResultSpecification mResultSpec;
    private final SearchService mService;
    private List<TResultItem> mCurrentPage = null;
    private long mTotalHits = 0;
    private ExecutionException mException = null;

    protected abstract TResultItem getResultItem(Hit hit, Map<String, String> map);

    public ResultItemPagerIterator(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
        this.mNextToken = null;
        this.mFirstRequest = true;
        this.mService = searchService;
        this.mOriginalRequest = searchRequest;
        this.mResultSpec = resultSpecification;
        String pageToken = resultSpecification.getPageToken();
        this.mNextToken = pageToken;
        this.mFirstRequest = StringUtils.isEmpty(pageToken);
    }

    private List<TResultItem> convertResultsList(List<Hit> list, Map<String, String> map) {
        ArrayList arrayList = new ArrayList(list != null ? list.size() : 0);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(getResultItem(list.get(i), map));
            }
        }
        return arrayList;
    }

    private com.amazon.tenzing.textsearch.v1_1.SearchRequest createRequest() {
        if (this.mFirstRequest) {
            this.mResultSpec.setMaxResults(Integer.valueOf(this.mOriginalRequest.getResultSpecs().get(0).getMaxResults().intValue()));
            this.mResultSpec.setPageToken(null);
        } else if (!StringUtils.isEmpty(this.mNextToken)) {
            this.mResultSpec.setMaxResults(null);
            this.mResultSpec.setPageToken(this.mNextToken);
        }
        com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequestBuild = com.amazon.tenzing.textsearch.v1_1.SearchRequest.builder().withQuery(this.mOriginalRequest.getQuery()).withFeatures(this.mOriginalRequest.getFeatures()).withQueryMetadata(this.mOriginalRequest.getQueryMetadata()).build();
        searchRequestBuild.setResultSpecs(Arrays.asList(this.mResultSpec));
        return searchRequestBuild;
    }

    @Override // com.amazon.music.pager.PagerIterator
    public boolean hasNext() {
        if (this.mException != null) {
            return false;
        }
        List<TResultItem> list = this.mCurrentPage;
        if (list != null) {
            return list.size() > 0;
        }
        if (!this.mFirstRequest && StringUtils.isEmpty(this.mNextToken)) {
            return false;
        }
        try {
            com.amazon.tenzing.textsearch.v1_1.SearchResult searchResult = this.mService.search(createRequest()).getResults().get(0);
            List<TResultItem> listConvertResultsList = convertResultsList(searchResult.getHits(), SearchResult.getContentInfo(searchResult));
            this.mFirstRequest = false;
            this.mNextToken = searchResult.getNextPage();
            this.mCurrentPage = listConvertResultsList;
            this.mTotalHits = searchResult.getTotalHitCount().longValue();
            return this.mCurrentPage.size() > 0;
        } catch (VolleyError e) {
            this.mException = new ExecutionException(e.getMessage(), e);
            return true;
        }
    }

    @Override // com.amazon.music.pager.PagerIterator
    public PageResult<TResultItem> next() throws ExecutionException, NoSuchElementException {
        ExecutionException executionException = this.mException;
        if (executionException != null) {
            this.mException = null;
            this.mCurrentPage = null;
            throw executionException;
        }
        List<TResultItem> list = this.mCurrentPage;
        if (list == null) {
            throw new ExecutionException("The current page is null.That usually mans hasNext() was never called.", new Exception());
        }
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }
        List<TResultItem> list2 = this.mCurrentPage;
        this.mCurrentPage = null;
        return new PageResult<>(list2, this.mTotalHits, this.mNextToken);
    }
}
