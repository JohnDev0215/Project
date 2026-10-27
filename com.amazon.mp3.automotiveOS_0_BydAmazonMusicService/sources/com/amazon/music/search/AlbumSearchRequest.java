package com.amazon.music.search;

import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.Album;
import com.amazon.music.search.SearchRequest;
import com.amazon.music.search.SingleSearchRequest;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class AlbumSearchRequest extends SingleSearchRequest<Album> {

    public static final class Builder extends SingleSearchRequest.Builder<Builder, Album, AlbumSearchRequest, Album.AdditionalFields> {
        @Override // com.amazon.music.search.SearchRequest.Builder
        protected /* bridge */ /* synthetic */ SearchRequest create(List list, SearchRequest.Builder.PagerCreator pagerCreator, String str) {
            return create((List<com.amazon.tenzing.textsearch.v1_1.SearchRequest>) list, (SearchRequest.Builder.PagerCreator<Album>) pagerCreator, str);
        }

        public Builder(String str, ResultType resultType) {
            super(str, resultType, "album");
        }

        static String getResultLabel(ResultType resultType) {
            return SpecBuilder.getResultLabel("album", resultType);
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SearchRequest.Builder.PagerCreator<Album> getPagerCreator() {
            return new SearchRequest.Builder.PagerCreator<Album>() { // from class: com.amazon.music.search.AlbumSearchRequest.Builder.1
                @Override // com.amazon.music.search.SearchRequest.Builder.PagerCreator
                public PagerIterator<PageResult<Album>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
                    return new AlbumPagerIterator(searchService, searchRequest, resultSpecification);
                }
            };
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected AlbumSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<Album> pagerCreator, String str) {
            return new AlbumSearchRequest(list, pagerCreator, str);
        }
    }

    AlbumSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<Album> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
