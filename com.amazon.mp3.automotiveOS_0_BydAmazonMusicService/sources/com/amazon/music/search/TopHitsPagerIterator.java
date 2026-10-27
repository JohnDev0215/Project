package com.amazon.music.search;

import com.amazon.music.platform.model.CatalogPodcastEpisode;
import com.amazon.music.platform.model.CatalogPodcastShow;
import com.amazon.music.platform.model.LiveSportsCompetition;
import com.amazon.music.platform.model.LiveSportsProgram;
import com.amazon.tenzing.textsearch.v1_1.Hit;
import com.amazon.tenzing.textsearch.v1_1.ResultSpecification;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
final class TopHitsPagerIterator extends ResultItemPagerIterator<ResultItem> {
    @Override // com.amazon.music.search.ResultItemPagerIterator
    protected ResultItem getResultItem(Hit hit, Map<String, String> map) {
        if (hit.getDocument() instanceof com.amazon.music.platform.model.Artist) {
            return new Artist(hit, map);
        }
        if (hit.getDocument() instanceof com.amazon.music.platform.model.Album) {
            return new Album(hit, map);
        }
        if (hit.getDocument() instanceof com.amazon.music.platform.model.Playlist) {
            return new Playlist(hit, map);
        }
        if (hit.getDocument() instanceof com.amazon.music.platform.model.Station) {
            return new Station(hit, map);
        }
        if (hit.getDocument() instanceof com.amazon.music.platform.model.Track) {
            return new Track(hit, map);
        }
        if (hit.getDocument() instanceof LiveSportsProgram) {
            return new Program(hit, map);
        }
        if (hit.getDocument() instanceof LiveSportsCompetition) {
            return new Competition(hit, map);
        }
        if (hit.getDocument() instanceof CatalogPodcastShow) {
            return new PodcastShow(hit, map);
        }
        if (hit.getDocument() instanceof CatalogPodcastEpisode) {
            return new PodcastEpisode(hit, map);
        }
        throw new UnsupportedOperationException("Unrecognized Tenzing document type: " + hit.getDocument().getClass().getSimpleName());
    }

    @Override // com.amazon.music.search.ResultItemPagerIterator, com.amazon.music.pager.PagerIterator
    public boolean hasNext() {
        return super.hasNext();
    }

    TopHitsPagerIterator(SearchService searchService, com.amazon.tenzing.textsearch.v1_1.SearchRequest searchRequest, ResultSpecification resultSpecification) {
        super(searchService, searchRequest, resultSpecification);
    }
}
