package com.amazon.music.search;

import com.amazon.mp3.environment.Environment;
import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.Playlist;
import com.amazon.music.search.SearchRequest;
import com.amazon.music.search.SingleSearchRequest;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class PlaylistSearchRequest extends SingleSearchRequest<Playlist> {

    public static final class Builder extends SingleSearchRequest.Builder<Builder, Playlist, PlaylistSearchRequest, Playlist.AdditionalFields> {
        @Override // com.amazon.music.search.SearchRequest.Builder
        protected /* bridge */ /* synthetic */ SearchRequest create(List list, SearchRequest.Builder.PagerCreator pagerCreator, String str) {
            return create((List<com.amazon.tenzing.textsearch.v1_1.SearchRequest>) list, (SearchRequest.Builder.PagerCreator<Playlist>) pagerCreator, str);
        }

        public Builder(String str, ResultType resultType) {
            super(str, resultType, Environment.PLAYLIST_PATH);
        }

        static String getResultLabel(ResultType resultType) {
            return SpecBuilder.getResultLabel(Environment.PLAYLIST_PATH, resultType);
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SearchRequest.Builder.PagerCreator<Playlist> getPagerCreator() {
            return new SearchRequest.Builder.PagerCreator<Playlist>() { // from class: com.amazon.music.search.PlaylistSearchRequest.Builder.1
                @Override // com.amazon.music.search.SearchRequest.Builder.PagerCreator
                public PagerIterator<PageResult<Playlist>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
                    return new PlaylistPagerIterator(searchService, searchRequest, resultSpecification);
                }
            };
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected PlaylistSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<Playlist> pagerCreator, String str) {
            return new PlaylistSearchRequest(list, pagerCreator, str);
        }
    }

    PlaylistSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<Playlist> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
