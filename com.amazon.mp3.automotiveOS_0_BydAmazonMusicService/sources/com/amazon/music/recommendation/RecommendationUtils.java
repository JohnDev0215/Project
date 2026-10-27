package com.amazon.music.recommendation;

import com.amazon.music.storeservice.model.AlbumRecommendations;
import com.amazon.music.storeservice.model.BaseRecommendations;
import com.amazon.music.storeservice.model.PlaylistRecommendations;
import com.amazon.music.storeservice.model.RecommendedAlbumItem;
import com.amazon.music.storeservice.model.RecommendedPlaylistItem;
import com.amazon.music.storeservice.model.RecommendedStationItem;
import com.amazon.music.storeservice.model.RecommendedTrackItem;
import com.amazon.music.storeservice.model.StationRecommendations;
import com.amazon.music.storeservice.model.TrackRecommendations;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
final class RecommendationUtils {
    private static final Logger LOG = LoggerFactory.getLogger(RecommendationUtils.class.getSimpleName());

    RecommendationUtils() {
    }

    static final RecommendationResult fromResponse(List<BaseRecommendations> list) {
        ArrayList arrayList = new ArrayList();
        List<RecommendedTrackItem> tracks = null;
        List<RecommendedAlbumItem> albums = null;
        List<RecommendedPlaylistItem> playlists = null;
        List<RecommendedStationItem> stations = null;
        for (BaseRecommendations baseRecommendations : list) {
            RecommendationType recommendationTypeFromString = RecommendationType.fromString(baseRecommendations.getRecommendationType());
            if (recommendationTypeFromString != null) {
                int i = AnonymousClass1.$SwitchMap$com$amazon$music$recommendation$RecommendationType[recommendationTypeFromString.ordinal()];
                if (i == 1) {
                    TrackRecommendations trackRecommendations = (TrackRecommendations) baseRecommendations;
                    tracks = trackRecommendations.getTracks();
                    arrayList.add(new RecommendationInfo(RecommendationType.TRACK, trackRecommendations.getWidgetId(), trackRecommendations.getContentInfo()));
                } else if (i == 2) {
                    AlbumRecommendations albumRecommendations = (AlbumRecommendations) baseRecommendations;
                    albums = albumRecommendations.getAlbums();
                    arrayList.add(new RecommendationInfo(RecommendationType.ALBUM, albumRecommendations.getWidgetId(), albumRecommendations.getContentInfo()));
                } else if (i == 3) {
                    PlaylistRecommendations playlistRecommendations = (PlaylistRecommendations) baseRecommendations;
                    playlists = playlistRecommendations.getPlaylists();
                    arrayList.add(new RecommendationInfo(RecommendationType.PLAYLIST, playlistRecommendations.getWidgetId(), playlistRecommendations.getContentInfo()));
                } else if (i == 4) {
                    StationRecommendations stationRecommendations = (StationRecommendations) baseRecommendations;
                    stations = stationRecommendations.getStations();
                    arrayList.add(new RecommendationInfo(RecommendationType.STATION, stationRecommendations.getWidgetId(), stationRecommendations.getContentInfo()));
                } else {
                    LOG.error("Recommendation type not recognised. Type: " + recommendationTypeFromString);
                }
            } else {
                LOG.error("Recommendation type is null.");
            }
        }
        return new RecommendationResult(tracks, albums, playlists, stations, arrayList);
    }

    /* JADX INFO: renamed from: com.amazon.music.recommendation.RecommendationUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$recommendation$RecommendationType;

        static {
            int[] iArr = new int[RecommendationType.values().length];
            $SwitchMap$com$amazon$music$recommendation$RecommendationType = iArr;
            try {
                iArr[RecommendationType.TRACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$recommendation$RecommendationType[RecommendationType.ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$recommendation$RecommendationType[RecommendationType.PLAYLIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$recommendation$RecommendationType[RecommendationType.STATION.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static final <T> List<T> fromRecommendations(BaseRecommendations baseRecommendations) {
        RecommendationType recommendationTypeFromString = RecommendationType.fromString(baseRecommendations.getRecommendationType());
        if (recommendationTypeFromString != null) {
            int i = AnonymousClass1.$SwitchMap$com$amazon$music$recommendation$RecommendationType[recommendationTypeFromString.ordinal()];
            if (i == 1) {
                return (List<T>) ((TrackRecommendations) baseRecommendations).getTracks();
            }
            if (i == 2) {
                return (List<T>) ((AlbumRecommendations) baseRecommendations).getAlbums();
            }
            if (i == 3) {
                return (List<T>) ((PlaylistRecommendations) baseRecommendations).getPlaylists();
            }
            if (i == 4) {
                return (List<T>) ((StationRecommendations) baseRecommendations).getStations();
            }
        }
        return Collections.emptyList();
    }
}
