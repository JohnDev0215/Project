package com.amazon.music.search;

import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.SearchRequest;
import com.amazon.music.search.SingleSearchRequest;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class LiveEventSearchRequest extends SingleSearchRequest<LiveEvent> {

    public static final class Builder extends SingleSearchRequest.Builder<Builder, LiveEvent, LiveEventSearchRequest, Object> {
        static String getResultLabel() {
            return "live_event";
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected /* bridge */ /* synthetic */ SearchRequest create(List list, SearchRequest.Builder.PagerCreator pagerCreator, String str) {
            return create((List<com.amazon.tenzing.textsearch.v1_1.SearchRequest>) list, (SearchRequest.Builder.PagerCreator<LiveEvent>) pagerCreator, str);
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SearchRequest.Builder.PagerCreator<LiveEvent> getPagerCreator() {
            return new SearchRequest.Builder.PagerCreator<LiveEvent>() { // from class: com.amazon.music.search.LiveEventSearchRequest.Builder.1
                @Override // com.amazon.music.search.SearchRequest.Builder.PagerCreator
                public PagerIterator<PageResult<LiveEvent>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
                    return new LiveEventPagerIterator(searchService, searchRequest, resultSpecification);
                }
            };
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected LiveEventSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<LiveEvent> pagerCreator, String str) {
            return new LiveEventSearchRequest(list, pagerCreator, str);
        }
    }

    LiveEventSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<LiveEvent> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
