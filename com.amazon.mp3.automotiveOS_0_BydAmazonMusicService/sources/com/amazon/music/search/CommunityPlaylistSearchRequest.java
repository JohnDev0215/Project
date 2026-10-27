package com.amazon.music.search;

import com.amazon.mp3.environment.Environment;
import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.CommunityPlaylist;
import com.amazon.music.search.SearchRequest;
import com.amazon.music.search.SingleSearchRequest;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class CommunityPlaylistSearchRequest extends SingleSearchRequest<CommunityPlaylist> {

    public static final class Builder extends SingleSearchRequest.Builder<Builder, CommunityPlaylist, CommunityPlaylistSearchRequest, CommunityPlaylist.AdditionalFields> {
        @Override // com.amazon.music.search.SearchRequest.Builder
        protected /* bridge */ /* synthetic */ SearchRequest create(List list, SearchRequest.Builder.PagerCreator pagerCreator, String str) {
            return create((List<com.amazon.tenzing.textsearch.v1_1.SearchRequest>) list, (SearchRequest.Builder.PagerCreator<CommunityPlaylist>) pagerCreator, str);
        }

        public Builder(String str, ResultType resultType) {
            super(str, resultType, Environment.PLAYLIST_PATH);
        }

        static String getResultLabel(ResultType resultType) {
            return SpecBuilder.getResultLabel(Environment.PLAYLIST_PATH, resultType);
        }

        @Override // com.amazon.music.search.SingleSearchRequest.Builder
        protected SpecBuilder createSpecBuilder() {
            return new SpecBuilder();
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SearchRequest.Builder.PagerCreator<CommunityPlaylist> getPagerCreator() {
            return new SearchRequest.Builder.PagerCreator<CommunityPlaylist>() { // from class: com.amazon.music.search.CommunityPlaylistSearchRequest.Builder.1
                @Override // com.amazon.music.search.SearchRequest.Builder.PagerCreator
                public PagerIterator<PageResult<CommunityPlaylist>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
                    return new CommunityPlaylistPagerIterator(searchService, searchRequest, resultSpecification);
                }
            };
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected CommunityPlaylistSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<CommunityPlaylist> pagerCreator, String str) {
            return new CommunityPlaylistSearchRequest(list, pagerCreator, str);
        }
    }

    CommunityPlaylistSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<CommunityPlaylist> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
