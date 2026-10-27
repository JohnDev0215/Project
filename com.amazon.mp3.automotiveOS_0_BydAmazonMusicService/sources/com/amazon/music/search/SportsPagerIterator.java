package com.amazon.music.search;

import com.amazon.music.platform.model.Document;
import com.amazon.music.platform.model.LiveSportsCompetition;
import com.amazon.music.platform.model.LiveSportsProgram;
import com.amazon.tenzing.textsearch.v1_1.Hit;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
final class SportsPagerIterator extends ResultItemPagerIterator<SportsItem> {
    @Override // com.amazon.music.search.ResultItemPagerIterator
    protected /* bridge */ /* synthetic */ ResultItem getResultItem(Hit hit, Map map) {
        return getResultItem(hit, (Map<String, String>) map);
    }

    @Override // com.amazon.music.search.ResultItemPagerIterator
    protected SportsItem getResultItem(Hit hit, Map<String, String> map) {
        Document document = hit.getDocument();
        if (document instanceof LiveSportsCompetition) {
            return new Competition(hit, map);
        }
        if (document instanceof LiveSportsProgram) {
            return new Program(hit, map);
        }
        return null;
    }

    SportsPagerIterator(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
        super(searchService, searchRequest, resultSpecification);
    }
}
