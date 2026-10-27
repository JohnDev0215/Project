package com.amazon.music.search;

import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.SearchRequest;
import com.amazon.music.search.SingleSearchRequest;
import com.amazon.music.search.Station;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class StationSearchRequest extends SingleSearchRequest<Station> {

    public static final class Builder extends SingleSearchRequest.Builder<Builder, Station, StationSearchRequest, Station.AdditionalFields> {
        @Override // com.amazon.music.search.SearchRequest.Builder
        protected /* bridge */ /* synthetic */ SearchRequest create(List list, SearchRequest.Builder.PagerCreator pagerCreator, String str) {
            return create((List<com.amazon.tenzing.textsearch.v1_1.SearchRequest>) list, (SearchRequest.Builder.PagerCreator<Station>) pagerCreator, str);
        }

        public Builder(String str) {
            super(str, ResultType.Catalog, "station");
        }

        static String getResultLabel() {
            return SpecBuilder.getResultLabel("station", ResultType.Catalog);
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SearchRequest.Builder.PagerCreator<Station> getPagerCreator() {
            return new SearchRequest.Builder.PagerCreator<Station>() { // from class: com.amazon.music.search.StationSearchRequest.Builder.1
                @Override // com.amazon.music.search.SearchRequest.Builder.PagerCreator
                public PagerIterator<PageResult<Station>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
                    return new StationPagerIterator(searchService, searchRequest, resultSpecification);
                }
            };
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected StationSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<Station> pagerCreator, String str) {
            return new StationSearchRequest(list, pagerCreator, str);
        }
    }

    StationSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<Station> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
