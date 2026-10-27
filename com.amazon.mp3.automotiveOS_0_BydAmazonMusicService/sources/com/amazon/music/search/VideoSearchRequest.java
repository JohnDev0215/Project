package com.amazon.music.search;

import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.SearchRequest;
import com.amazon.music.search.SingleSearchRequest;
import com.amazon.music.search.Video;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import com.visualon.OSMPUtils.voMimeTypes;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class VideoSearchRequest extends SingleSearchRequest<Video> {

    public static final class Builder extends SingleSearchRequest.Builder<Builder, Video, VideoSearchRequest, Video.AdditionalFields> {
        @Override // com.amazon.music.search.SearchRequest.Builder
        protected /* bridge */ /* synthetic */ SearchRequest create(List list, SearchRequest.Builder.PagerCreator pagerCreator, String str) {
            return create((List<com.amazon.tenzing.textsearch.v1_1.SearchRequest>) list, (SearchRequest.Builder.PagerCreator<Video>) pagerCreator, str);
        }

        public Builder(String str, ResultType resultType) {
            super(str, resultType, voMimeTypes.VOBASE_TYPE_VIDEO);
        }

        static String getResultLabel(ResultType resultType) {
            return SpecBuilder.getResultLabel(voMimeTypes.VOBASE_TYPE_VIDEO, resultType);
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SearchRequest.Builder.PagerCreator<Video> getPagerCreator() {
            return new SearchRequest.Builder.PagerCreator<Video>() { // from class: com.amazon.music.search.VideoSearchRequest.Builder.1
                @Override // com.amazon.music.search.SearchRequest.Builder.PagerCreator
                public PagerIterator<PageResult<Video>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
                    return new VideoPagerIterator(searchService, searchRequest, resultSpecification);
                }
            };
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected VideoSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<Video> pagerCreator, String str) {
            return new VideoSearchRequest(list, pagerCreator, str);
        }
    }

    VideoSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<Video> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
