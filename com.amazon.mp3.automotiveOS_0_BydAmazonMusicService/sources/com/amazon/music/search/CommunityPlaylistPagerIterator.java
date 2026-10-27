package com.amazon.music.search;

import com.amazon.tenzing.textsearch.v1_1.Hit;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
final class CommunityPlaylistPagerIterator extends ResultItemPagerIterator<CommunityPlaylist> {
    @Override // com.amazon.music.search.ResultItemPagerIterator
    protected /* bridge */ /* synthetic */ ResultItem getResultItem(Hit hit, Map map) {
        return getResultItem(hit, (Map<String, String>) map);
    }

    CommunityPlaylistPagerIterator(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
        super(searchService, searchRequest, resultSpecification);
    }

    @Override // com.amazon.music.search.ResultItemPagerIterator
    protected CommunityPlaylist getResultItem(Hit hit, Map<String, String> map) {
        return new CommunityPlaylist(hit, map);
    }
}
