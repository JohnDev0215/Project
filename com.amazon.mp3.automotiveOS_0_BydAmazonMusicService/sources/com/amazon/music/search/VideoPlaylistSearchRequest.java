package com.amazon.music.search;

import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.SearchRequest;
import com.amazon.music.search.SingleSearchRequest;
import com.amazon.music.search.VideoPlaylist;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class VideoPlaylistSearchRequest extends SingleSearchRequest<VideoPlaylist> {

    public static final class Builder extends SingleSearchRequest.Builder<Builder, VideoPlaylist, VideoPlaylistSearchRequest, VideoPlaylist.AdditionalFields> {
        @Override // com.amazon.music.search.SearchRequest.Builder
        protected /* bridge */ /* synthetic */ SearchRequest create(List list, SearchRequest.Builder.PagerCreator pagerCreator, String str) {
            return create((List<com.amazon.tenzing.textsearch.v1_1.SearchRequest>) list, (SearchRequest.Builder.PagerCreator<VideoPlaylist>) pagerCreator, str);
        }

        public Builder(String str, ResultType resultType) {
            super(str, resultType, "video_playlist");
        }

        static String getResultLabel(ResultType resultType) {
            return SpecBuilder.getResultLabel("video_playlist", resultType);
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SearchRequest.Builder.PagerCreator<VideoPlaylist> getPagerCreator() {
            return new SearchRequest.Builder.PagerCreator<VideoPlaylist>() { // from class: com.amazon.music.search.VideoPlaylistSearchRequest.Builder.1
                @Override // com.amazon.music.search.SearchRequest.Builder.PagerCreator
                public PagerIterator<PageResult<VideoPlaylist>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
                    return new VideoPlaylistPagerIterator(searchService, searchRequest, resultSpecification);
                }
            };
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected VideoPlaylistSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<VideoPlaylist> pagerCreator, String str) {
            return new VideoPlaylistSearchRequest(list, pagerCreator, str);
        }
    }

    VideoPlaylistSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<VideoPlaylist> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
