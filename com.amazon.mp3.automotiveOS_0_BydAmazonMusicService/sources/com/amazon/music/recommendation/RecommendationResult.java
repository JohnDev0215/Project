package com.amazon.music.recommendation;

import com.amazon.music.storeservice.model.RecommendedAlbumItem;
import com.amazon.music.storeservice.model.RecommendedPlaylistItem;
import com.amazon.music.storeservice.model.RecommendedStationItem;
import com.amazon.music.storeservice.model.RecommendedTrackItem;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class RecommendationResult {
    private final List<RecommendedAlbumItem> albums;
    private final List<RecommendedPlaylistItem> playlists;
    private List<RecommendationInfo> recommendationInfos;
    private final List<RecommendedStationItem> stations;
    private final List<RecommendedTrackItem> tracks;

    public RecommendationResult(List<RecommendedTrackItem> list, List<RecommendedAlbumItem> list2, List<RecommendedPlaylistItem> list3, List<RecommendedStationItem> list4, List<RecommendationInfo> list5) {
        this.tracks = validateIntegrity(list);
        this.albums = validateIntegrity(list2);
        this.playlists = validateIntegrity(list3);
        this.stations = validateIntegrity(list4);
        this.recommendationInfos = list5;
    }

    public List<RecommendedTrackItem> getTracks() {
        return this.tracks;
    }

    public List<RecommendedAlbumItem> getAlbums() {
        return this.albums;
    }

    public List<RecommendedPlaylistItem> getPlaylists() {
        return this.playlists;
    }

    public List<RecommendedStationItem> getStations() {
        return this.stations;
    }

    public List<RecommendationInfo> getRecommendationInfos() {
        return this.recommendationInfos;
    }

    private <T> List<T> validateIntegrity(List<T> list) {
        return list != null ? Collections.unmodifiableList(list) : Collections.emptyList();
    }
}
