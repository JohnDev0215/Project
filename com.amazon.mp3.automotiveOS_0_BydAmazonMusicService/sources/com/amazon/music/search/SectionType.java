package com.amazon.music.search;

import com.amazon.music.search.AlbumSearchRequest;
import com.amazon.music.search.ArtistSearchRequest;
import com.amazon.music.search.CommunityPlaylistSearchRequest;
import com.amazon.music.search.LiveEventSearchRequest;
import com.amazon.music.search.PlaylistSearchRequest;
import com.amazon.music.search.PodcastEpisodeSearchRequest;
import com.amazon.music.search.PodcastShowSearchRequest;
import com.amazon.music.search.SportsSearchRequest;
import com.amazon.music.search.StationSearchRequest;
import com.amazon.music.search.TopHitsSearchRequest;
import com.amazon.music.search.TrackSearchRequest;
import com.amazon.music.search.VideoPlaylistSearchRequest;
import com.amazon.music.search.VideoSearchRequest;

/* JADX INFO: loaded from: classes4.dex */
public enum SectionType {
    SPELL_CORRECTION_SECTION,
    TOP_HIT_SECTION,
    ARTISTS_SECTION,
    ALBUMS_SECTION,
    TRACKS_SECTION,
    STATIONS_SECTION,
    PLAYLISTS_SECTION,
    SOCCER_SECTION,
    VIDEO_SECTION,
    COMMUNITY_PLAYLIST_SECTION,
    VIDEO_PLAYLIST_SECTION,
    PODCAST_SHOW_SECTION,
    PODCAST_EPISODE_SECTION,
    LIVE_EVENT_SECTION;

    static SectionType fromResultLabel(String str) {
        if (CommunityPlaylistSearchRequest.Builder.getResultLabel(ResultType.Community).equals(str)) {
            return COMMUNITY_PLAYLIST_SECTION;
        }
        ResultType resultType = ResultType.Catalog;
        if (AlbumSearchRequest.Builder.getResultLabel(resultType).equals(str)) {
            return ALBUMS_SECTION;
        }
        if (ArtistSearchRequest.Builder.getResultLabel(resultType).equals(str)) {
            return ARTISTS_SECTION;
        }
        if (PlaylistSearchRequest.Builder.getResultLabel(resultType).equals(str)) {
            return PLAYLISTS_SECTION;
        }
        if (SportsSearchRequest.Builder.getResultLabel().equals(str)) {
            return SOCCER_SECTION;
        }
        if (StationSearchRequest.Builder.getResultLabel().equals(str)) {
            return STATIONS_SECTION;
        }
        if (TrackSearchRequest.Builder.getResultLabel(resultType).equals(str)) {
            return TRACKS_SECTION;
        }
        if (TopHitsSearchRequest.Builder.getResultLabel().equals(str)) {
            return TOP_HIT_SECTION;
        }
        if (VideoSearchRequest.Builder.getResultLabel(resultType).equals(str)) {
            return VIDEO_SECTION;
        }
        if (VideoPlaylistSearchRequest.Builder.getResultLabel(resultType).equals(str)) {
            return VIDEO_PLAYLIST_SECTION;
        }
        ResultType resultType2 = ResultType.Podcast_Catalog;
        if (PodcastShowSearchRequest.Builder.getResultLabel(resultType2).equals(str)) {
            return PODCAST_SHOW_SECTION;
        }
        if (PodcastEpisodeSearchRequest.Builder.getResultLabel(resultType2).equals(str)) {
            return PODCAST_EPISODE_SECTION;
        }
        if (LiveEventSearchRequest.Builder.getResultLabel().equals(str)) {
            return LIVE_EVENT_SECTION;
        }
        return null;
    }
}
