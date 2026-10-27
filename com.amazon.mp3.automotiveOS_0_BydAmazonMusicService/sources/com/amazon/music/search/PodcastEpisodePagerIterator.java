package com.amazon.music.search;

import com.amazon.tenzing.textsearch.v1_1.Hit;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public final class PodcastEpisodePagerIterator extends ResultItemPagerIterator<PodcastEpisode> {
    @Override // com.amazon.music.search.ResultItemPagerIterator
    protected /* bridge */ /* synthetic */ ResultItem getResultItem(Hit hit, Map map) {
        return getResultItem(hit, (Map<String, String>) map);
    }

    @Override // com.amazon.music.search.ResultItemPagerIterator, com.amazon.music.pager.PagerIterator
    public /* bridge */ /* synthetic */ boolean hasNext() {
        return super.hasNext();
    }

    @Override // com.amazon.music.search.ResultItemPagerIterator, com.amazon.music.pager.PagerIterator
    public /* bridge */ /* synthetic */ PageResult next() throws ExecutionException, NoSuchElementException {
        return super.next();
    }

    PodcastEpisodePagerIterator(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
        super(searchService, searchRequest, resultSpecification);
    }

    @Override // com.amazon.music.search.ResultItemPagerIterator
    protected PodcastEpisode getResultItem(Hit hit, Map<String, String> map) {
        return new PodcastEpisode(hit, map);
    }
}
