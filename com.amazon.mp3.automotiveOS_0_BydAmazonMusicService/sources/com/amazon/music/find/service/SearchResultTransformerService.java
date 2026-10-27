package com.amazon.music.find.service;

import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.model.search.SeeMoreResponse;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchResultTransformerService.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/find/service/SearchResultTransformerService;", "", "transform", "Lcom/amazon/music/find/model/search/SearchResponse;", "searchResult", "seeMoreResult", "Lcom/amazon/music/find/model/search/SeeMoreResponse;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SearchResultTransformerService {
    SearchResponse transform(SearchResponse searchResult);

    SearchResponse transform(SeeMoreResponse seeMoreResult);
}
