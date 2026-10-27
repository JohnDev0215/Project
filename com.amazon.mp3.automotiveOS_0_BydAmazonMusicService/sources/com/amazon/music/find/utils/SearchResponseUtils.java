package com.amazon.music.find.utils;

import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.model.search.TrackSearchItem;
import com.amazon.music.search.SectionType;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: SearchResponseUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/find/utils/SearchResponseUtils;", "", "()V", "isTrackSearch", "", "searchResponse", "Lcom/amazon/music/find/model/search/SearchResponse;", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SearchResponseUtils {
    public static final SearchResponseUtils INSTANCE = new SearchResponseUtils();

    /* JADX INFO: compiled from: SearchResponseUtils.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SectionType.values().length];
            iArr[SectionType.SPELL_CORRECTION_SECTION.ordinal()] = 1;
            iArr[SectionType.TOP_HIT_SECTION.ordinal()] = 2;
            iArr[SectionType.TRACKS_SECTION.ordinal()] = 3;
            iArr[SectionType.ALBUMS_SECTION.ordinal()] = 4;
            iArr[SectionType.ARTISTS_SECTION.ordinal()] = 5;
            iArr[SectionType.STATIONS_SECTION.ordinal()] = 6;
            iArr[SectionType.PLAYLISTS_SECTION.ordinal()] = 7;
            iArr[SectionType.COMMUNITY_PLAYLIST_SECTION.ordinal()] = 8;
            iArr[SectionType.PODCAST_EPISODE_SECTION.ordinal()] = 9;
            iArr[SectionType.PODCAST_SHOW_SECTION.ordinal()] = 10;
            iArr[SectionType.VIDEO_PLAYLIST_SECTION.ordinal()] = 11;
            iArr[SectionType.VIDEO_SECTION.ordinal()] = 12;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private SearchResponseUtils() {
    }

    public final boolean isTrackSearch(SearchResponse searchResponse) {
        List<SectionType> sectionOrdering;
        if (searchResponse == null || (sectionOrdering = searchResponse.getSectionOrdering()) == null || sectionOrdering.isEmpty()) {
            return false;
        }
        int size = sectionOrdering.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            switch (WhenMappings.$EnumSwitchMapping$0[sectionOrdering.get(i).ordinal()]) {
                case 1:
                    break;
                case 2:
                    if (!searchResponse.getTopHitItems().isEmpty()) {
                        return searchResponse.getTopHitItems().get(0) instanceof TrackSearchItem;
                    }
                    break;
                case 3:
                    if (!searchResponse.getTracks().isEmpty()) {
                        return true;
                    }
                    break;
                case 4:
                    if (!searchResponse.getAlbums().isEmpty()) {
                        return false;
                    }
                    break;
                case 5:
                    if (!searchResponse.getArtists().isEmpty()) {
                        return false;
                    }
                    break;
                case 6:
                    if (!searchResponse.getStations().isEmpty()) {
                        return false;
                    }
                    break;
                case 7:
                    if (!searchResponse.getPlaylists().isEmpty()) {
                        return false;
                    }
                    break;
                case 8:
                    if (!searchResponse.getCommunityPlaylists().isEmpty()) {
                        return false;
                    }
                    break;
                case 9:
                    if (!searchResponse.getPodcastEpisodes().isEmpty()) {
                        return false;
                    }
                    break;
                case 10:
                    if (!searchResponse.getPodcastShows().isEmpty()) {
                        return false;
                    }
                    break;
                case 11:
                    if (!searchResponse.getVideoPlaylists().isEmpty()) {
                        return false;
                    }
                    break;
                case 12:
                    if (!searchResponse.getVideos().isEmpty()) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
            i = i2;
        }
        return false;
    }
}
