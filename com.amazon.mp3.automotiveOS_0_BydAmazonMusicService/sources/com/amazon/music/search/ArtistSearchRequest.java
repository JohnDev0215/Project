package com.amazon.music.search;

import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.Artist;
import com.amazon.music.search.SearchRequest;
import com.amazon.music.search.SingleSearchRequest;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class ArtistSearchRequest extends SingleSearchRequest<Artist> {

    public static final class Builder extends SingleSearchRequest.Builder<Builder, Artist, ArtistSearchRequest, Artist.AdditionalFields> {
        @Override // com.amazon.music.search.SearchRequest.Builder
        protected /* bridge */ /* synthetic */ SearchRequest create(List list, SearchRequest.Builder.PagerCreator pagerCreator, String str) {
            return create((List<com.amazon.tenzing.textsearch.v1_1.SearchRequest>) list, (SearchRequest.Builder.PagerCreator<Artist>) pagerCreator, str);
        }

        public Builder(String str, ResultType resultType) {
            super(str, resultType, "artist");
        }

        static String getResultLabel(ResultType resultType) {
            return SpecBuilder.getResultLabel("artist", resultType);
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SearchRequest.Builder.PagerCreator<Artist> getPagerCreator() {
            return new SearchRequest.Builder.PagerCreator<Artist>() { // from class: com.amazon.music.search.ArtistSearchRequest.Builder.1
                @Override // com.amazon.music.search.SearchRequest.Builder.PagerCreator
                public PagerIterator<PageResult<Artist>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
                    return new ArtistPagerIterator(searchService, searchRequest, resultSpecification);
                }
            };
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected ArtistSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<Artist> pagerCreator, String str) {
            return new ArtistSearchRequest(list, pagerCreator, str);
        }
    }

    ArtistSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<Artist> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
