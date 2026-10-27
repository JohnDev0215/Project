package com.amazon.music.search;

import com.amazon.music.pager.PagerIterator;
import com.amazon.music.search.PodcastEpisode;
import com.amazon.music.search.SearchRequest;
import com.amazon.music.search.SingleSearchRequest;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class PodcastEpisodeSearchRequest extends SingleSearchRequest<PodcastEpisode> {

    public static final class Builder extends SingleSearchRequest.Builder<Builder, PodcastEpisode, PodcastEpisodeSearchRequest, PodcastEpisode.AdditionalFields> {
        @Override // com.amazon.music.search.SearchRequest.Builder
        protected /* bridge */ /* synthetic */ SearchRequest create(List list, SearchRequest.Builder.PagerCreator pagerCreator, String str) {
            return create((List<com.amazon.tenzing.textsearch.v1_1.SearchRequest>) list, (SearchRequest.Builder.PagerCreator<PodcastEpisode>) pagerCreator, str);
        }

        public Builder(String str, ResultType resultType) {
            super(str, resultType, "episode");
        }

        static String getResultLabel(ResultType resultType) {
            return SpecBuilder.getResultLabel("episode", resultType);
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected SearchRequest.Builder.PagerCreator<PodcastEpisode> getPagerCreator() {
            return new SearchRequest.Builder.PagerCreator<PodcastEpisode>() { // from class: com.amazon.music.search.PodcastEpisodeSearchRequest.Builder.1
                @Override // com.amazon.music.search.SearchRequest.Builder.PagerCreator
                public PagerIterator<PageResult<PodcastEpisode>> create(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
                    return new PodcastEpisodePagerIterator(searchService, searchRequest, resultSpecification);
                }
            };
        }

        @Override // com.amazon.music.search.SearchRequest.Builder
        protected PodcastEpisodeSearchRequest create(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<PodcastEpisode> pagerCreator, String str) {
            return new PodcastEpisodeSearchRequest(list, pagerCreator, str);
        }
    }

    PodcastEpisodeSearchRequest(List<com.amazon.tenzing.textsearch.v1_1.SearchRequest> list, SearchRequest.Builder.PagerCreator<PodcastEpisode> pagerCreator, String str) {
        super(list, pagerCreator, str);
    }
}
