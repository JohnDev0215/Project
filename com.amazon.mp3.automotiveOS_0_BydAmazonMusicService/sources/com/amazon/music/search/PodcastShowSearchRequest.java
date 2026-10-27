package com.amazon.music.search;

import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.PodcastShow;
import com.amazon.music.search.SearchRequest;
import com.amazon.music.search.SingleSearchRequest;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class PodcastShowSearchRequest extends SingleSearchRequest<PodcastShow> {

    public static final class Builder extends SingleSearchRequest.Builder<Builder, PodcastShow, PodcastShowSearchRequest, PodcastShow.AdditionalFields> {
        @Override // com.amazon.music.search.SearchRequest.Builder
        protected /* bridge */ /* synthetic */ SearchRequest create(List list, SearchRequest.Builder.PagerCreator pagerCreator, String str) {
            return create((List<com.amazon.tenzing.textsearch.v1_1.SearchRequest>) list, (SearchRequest.Builder.PagerCreator<PodcastShow>) pagerCreator, str);
        }

        public Builder(String str, ResultType resultType) {
            super(str, resultType, "show");
        }

        static String getResultLabel(ResultType resultType) {
            return SpecBuilder.getResultLabel("show", resultType);
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SearchRequest.Builder.PagerCreator<PodcastShow> getPagerCreator() {
            return new SearchRequest.Builder.PagerCreator<PodcastShow>() { // from class: com.amazon.music.search.PodcastShowSearchRequest.Builder.1
                @Override // com.amazon.music.search.SearchRequest.Builder.PagerCreator
                public PagerIterator<PageResult<PodcastShow>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
                    return new PodcastShowPagerIterator(searchService, searchRequest, resultSpecification);
                }
            };
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected PodcastShowSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<PodcastShow> pagerCreator, String str) {
            return new PodcastShowSearchRequest(list, pagerCreator, str);
        }
    }

    PodcastShowSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<PodcastShow> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
