package com.amazon.music.find.service;

import com.amazon.music.find.model.search.GenericSearchResponse;
import com.amazon.music.find.model.search.PlaylistType;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.find.model.search.SearchResponse;
import com.amazon.music.find.model.search.SeeMoreResponse;
import com.amazon.music.search.SectionType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeSearchResultTransformer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\f\u001a\u00020\bH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/find/service/CarModeSearchResultTransformer;", "Lcom/amazon/music/find/service/SearchResultTransformerService;", "()V", "addToResults", "", "results", "", "", "Lcom/amazon/music/find/model/search/SearchItem;", "toAdd", "", "createHashKey", "item", "transform", "Lcom/amazon/music/find/model/search/SearchResponse;", "searchResult", "seeMoreResult", "Lcom/amazon/music/find/model/search/SeeMoreResponse;", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeSearchResultTransformer implements SearchResultTransformerService {
    public static final int GENERIC_SEARCH_PAGE_SIZE = 10;
    public static final int SEE_MORE_PAGE_SIZE = 5;

    /* JADX INFO: compiled from: CarModeSearchResultTransformer.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[SectionType.values().length];
            iArr[SectionType.TRACKS_SECTION.ordinal()] = 1;
            iArr[SectionType.ALBUMS_SECTION.ordinal()] = 2;
            iArr[SectionType.ARTISTS_SECTION.ordinal()] = 3;
            iArr[SectionType.PLAYLISTS_SECTION.ordinal()] = 4;
            iArr[SectionType.STATIONS_SECTION.ordinal()] = 5;
            iArr[SectionType.PODCAST_EPISODE_SECTION.ordinal()] = 6;
            iArr[SectionType.PODCAST_SHOW_SECTION.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[PlaylistType.values().length];
            iArr2[PlaylistType.PRIME_PLAYLIST.ordinal()] = 1;
            iArr2[PlaylistType.USER_PLAYLIST.ordinal()] = 2;
            iArr2[PlaylistType.SHARED_USER_PLAYLIST.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @Override // com.amazon.music.find.service.SearchResultTransformerService
    public SearchResponse transform(SearchResponse searchResult) {
        Intrinsics.checkNotNullParameter(searchResult, "searchResult");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (SearchItem searchItem : searchResult.getTopHitItems()) {
            String strCreateHashKey = createHashKey(searchItem);
            if (strCreateHashKey != null) {
                linkedHashMap.put(strCreateHashKey, searchItem);
            }
        }
        List<SectionType> sectionOrdering = searchResult.getSectionOrdering();
        if (sectionOrdering != null) {
            for (SectionType sectionType : sectionOrdering) {
                if (linkedHashMap.size() < 10) {
                    switch (WhenMappings.$EnumSwitchMapping$0[sectionType.ordinal()]) {
                        case 1:
                            addToResults(linkedHashMap, searchResult.getTracks());
                            break;
                        case 2:
                            addToResults(linkedHashMap, searchResult.getAlbums());
                            break;
                        case 3:
                            addToResults(linkedHashMap, searchResult.getArtists());
                            break;
                        case 4:
                            addToResults(linkedHashMap, searchResult.getPlaylists());
                            break;
                        case 5:
                            addToResults(linkedHashMap, searchResult.getStations());
                            break;
                        case 6:
                            addToResults(linkedHashMap, searchResult.getPodcastEpisodes());
                            break;
                        case 7:
                            addToResults(linkedHashMap, searchResult.getPodcastShows());
                            break;
                    }
                }
            }
        }
        GenericSearchResponse genericSearchResponse = new GenericSearchResponse();
        genericSearchResponse.setTopHitItems(CollectionsKt___CollectionsKt.toList(linkedHashMap.values()));
        return genericSearchResponse;
    }

    private final void addToResults(Map<String, SearchItem> results, List<? extends SearchItem> toAdd) {
        int i = 0;
        while (results.size() < 10 && i < toAdd.size()) {
            SearchItem searchItem = toAdd.get(i);
            String strCreateHashKey = createHashKey(searchItem);
            if (strCreateHashKey != null) {
                results.put(strCreateHashKey, searchItem);
                i++;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.String createHashKey(com.amazon.music.find.model.search.SearchItem r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.amazon.music.find.model.search.TrackSearchItem
            r1 = 0
            if (r0 == 0) goto L11
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.ASIN
            com.amazon.music.find.model.search.TrackSearchItem r6 = (com.amazon.music.find.model.search.TrackSearchItem) r6
            java.lang.String r6 = r6.getAsin()
            com.amazon.music.platform.find.common.EntityType r2 = com.amazon.music.platform.find.common.EntityType.TRACK
            goto L97
        L11:
            boolean r0 = r6 instanceof com.amazon.music.find.model.search.AlbumSearchItem
            if (r0 == 0) goto L21
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.ASIN
            com.amazon.music.find.model.search.AlbumSearchItem r6 = (com.amazon.music.find.model.search.AlbumSearchItem) r6
            java.lang.String r6 = r6.getAsin()
            com.amazon.music.platform.find.common.EntityType r2 = com.amazon.music.platform.find.common.EntityType.ALBUM
            goto L97
        L21:
            boolean r0 = r6 instanceof com.amazon.music.find.model.search.ArtistSearchItem
            if (r0 == 0) goto L31
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.ASIN
            com.amazon.music.find.model.search.ArtistSearchItem r6 = (com.amazon.music.find.model.search.ArtistSearchItem) r6
            java.lang.String r6 = r6.getAsin()
            com.amazon.music.platform.find.common.EntityType r2 = com.amazon.music.platform.find.common.EntityType.ARTIST
            goto L97
        L31:
            boolean r0 = r6 instanceof com.amazon.music.find.model.search.PlaylistSearchItem
            if (r0 == 0) goto L67
            com.amazon.music.find.model.search.PlaylistSearchItem r6 = (com.amazon.music.find.model.search.PlaylistSearchItem) r6
            com.amazon.music.find.model.search.PlaylistType r0 = r6.getPlaylistType()
            java.lang.String r2 = r6.getAsin()
            java.lang.String r6 = r6.getPlaylistId()
            int[] r3 = com.amazon.music.find.service.CarModeSearchResultTransformer.WhenMappings.$EnumSwitchMapping$1
            int r0 = r0.ordinal()
            r0 = r3[r0]
            r3 = 1
            if (r0 == r3) goto L5f
            r2 = 2
            if (r0 == r2) goto L5a
            r2 = 3
            if (r0 == r2) goto L55
            goto L94
        L55:
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.SHARED_PLAYLIST_ID
            com.amazon.music.platform.find.common.EntityType r2 = com.amazon.music.platform.find.common.EntityType.PLAYLIST
            goto L97
        L5a:
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.PLAYLIST_ID
            com.amazon.music.platform.find.common.EntityType r2 = com.amazon.music.platform.find.common.EntityType.PLAYLIST
            goto L97
        L5f:
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.ASIN
            com.amazon.music.platform.find.common.EntityType r6 = com.amazon.music.platform.find.common.EntityType.PLAYLIST
            r4 = r2
            r2 = r6
            r6 = r4
            goto L97
        L67:
            boolean r0 = r6 instanceof com.amazon.music.find.model.search.StationSearchItem
            if (r0 == 0) goto L76
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.STATION_KEY
            com.amazon.music.find.model.search.StationSearchItem r6 = (com.amazon.music.find.model.search.StationSearchItem) r6
            java.lang.String r6 = r6.getKey()
            com.amazon.music.platform.find.common.EntityType r2 = com.amazon.music.platform.find.common.EntityType.STATION
            goto L97
        L76:
            boolean r0 = r6 instanceof com.amazon.music.find.model.search.PodcastEpisodeSearchItem
            if (r0 == 0) goto L85
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.PODCAST_EPISODE_ID
            com.amazon.music.find.model.search.PodcastEpisodeSearchItem r6 = (com.amazon.music.find.model.search.PodcastEpisodeSearchItem) r6
            java.lang.String r6 = r6.getPodcastEpisodeId()
            com.amazon.music.platform.find.common.EntityType r2 = com.amazon.music.platform.find.common.EntityType.PODCAST_EPISODE
            goto L97
        L85:
            boolean r0 = r6 instanceof com.amazon.music.find.model.search.PodcastShowSearchItem
            if (r0 == 0) goto L94
            com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType r0 = com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType.PODCAST_SHOW_ID
            com.amazon.music.find.model.search.PodcastShowSearchItem r6 = (com.amazon.music.find.model.search.PodcastShowSearchItem) r6
            java.lang.String r6 = r6.getPodcastShowId()
            com.amazon.music.platform.find.common.EntityType r2 = com.amazon.music.platform.find.common.EntityType.PODCAST_SHOW
            goto L97
        L94:
            r6 = r1
            r0 = r6
            r2 = r0
        L97:
            if (r6 == 0) goto Lad
            if (r0 == 0) goto Lad
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r6)
            r1.append(r0)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
        Lad:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.find.service.CarModeSearchResultTransformer.createHashKey(com.amazon.music.find.model.search.SearchItem):java.lang.String");
    }

    @Override // com.amazon.music.find.service.SearchResultTransformerService
    public SearchResponse transform(SeeMoreResponse seeMoreResult) {
        Intrinsics.checkNotNullParameter(seeMoreResult, "seeMoreResult");
        List<SearchItem> searchItems = seeMoreResult.getSearchItems();
        List<SearchItem> listSubList = searchItems.subList(0, Math.min(searchItems.size(), 5));
        GenericSearchResponse genericSearchResponse = new GenericSearchResponse();
        genericSearchResponse.setTopHitItems(listSubList);
        return genericSearchResponse;
    }
}
