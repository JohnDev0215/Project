package com.amazon.music.search;

import com.amazon.tenzing.textsearch.v1_1.Hit;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
final class ArtistPagerIterator extends ResultItemPagerIterator<Artist> {
    @Override // com.amazon.music.search.ResultItemPagerIterator
    protected /* bridge */ /* synthetic */ ResultItem getResultItem(Hit hit, Map map) {
        return getResultItem(hit, (Map<String, String>) map);
    }

    ArtistPagerIterator(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
        super(searchService, searchRequest, resultSpecification);
    }

    @Override // com.amazon.music.search.ResultItemPagerIterator
    protected Artist getResultItem(Hit hit, Map<String, String> map) {
        return new Artist(hit, map);
    }
}
