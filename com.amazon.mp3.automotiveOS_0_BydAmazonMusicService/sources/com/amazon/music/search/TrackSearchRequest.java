package com.amazon.music.search;

import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.SearchRequest;
import com.amazon.music.search.SingleSearchRequest;
import com.amazon.music.search.Track;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class TrackSearchRequest extends SingleSearchRequest<Track> {

    public static final class Builder extends SingleSearchRequest.Builder<Builder, Track, TrackSearchRequest, Track.AdditionalFields> {
        @Override // com.amazon.music.search.SearchRequest.Builder
        protected /* bridge */ /* synthetic */ SearchRequest create(List list, SearchRequest.Builder.PagerCreator pagerCreator, String str) {
            return create((List<com.amazon.tenzing.textsearch.v1_1.SearchRequest>) list, (SearchRequest.Builder.PagerCreator<Track>) pagerCreator, str);
        }

        public Builder(String str, ResultType resultType) {
            super(str, resultType, "track");
        }

        static String getResultLabel(ResultType resultType) {
            return SpecBuilder.getResultLabel("track", resultType);
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SearchRequest.Builder.PagerCreator<Track> getPagerCreator() {
            return new SearchRequest.Builder.PagerCreator<Track>() { // from class: com.amazon.music.search.TrackSearchRequest.Builder.1
                @Override // com.amazon.music.search.SearchRequest.Builder.PagerCreator
                public PagerIterator<PageResult<Track>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
                    return new TrackPagerIterator(searchService, searchRequest, resultSpecification);
                }
            };
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected TrackSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<Track> pagerCreator, String str) {
            return new TrackSearchRequest(list, pagerCreator, str);
        }
    }

    TrackSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<Track> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
