package com.amazon.music.recommendation.widgets;

import com.amazon.music.recommendation.RecommendationInfo;
import com.amazon.music.recommendation.RecommendationType;
import com.amazon.music.storeservice.model.AlbumRecommendations;
import com.amazon.music.storeservice.model.BaseRecommendations;
import com.amazon.music.storeservice.model.PlaylistRecommendations;
import com.amazon.music.storeservice.model.StationRecommendations;
import com.amazon.music.storeservice.model.TrackRecommendations;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RecommendationWidgetResult {
    private List<RecommendationWidget> recommendations;

    public RecommendationWidgetResult(List<BaseRecommendations> list) {
        this.recommendations = asRecommendationWidgets(list);
    }

    private List<RecommendationWidget> asRecommendationWidgets(List<BaseRecommendations> list) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<BaseRecommendations> it = list.iterator();
        while (it.hasNext()) {
            RecommendationWidget<?> recommendationWidgetAsRecommendationWidget = asRecommendationWidget(it.next());
            if (recommendationWidgetAsRecommendationWidget != null) {
                arrayList.add(recommendationWidgetAsRecommendationWidget);
            }
        }
        return arrayList;
    }

    private RecommendationWidget<?> asRecommendationWidget(BaseRecommendations baseRecommendations) {
        String widgetId = baseRecommendations.getWidgetId();
        RecommendationType recommendationTypeFromString = RecommendationType.fromString(baseRecommendations.getRecommendationType());
        RecommendationWidgetType recommendationWidgetTypeFromRecommendation = RecommendationWidgetType.fromRecommendation(baseRecommendations);
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$recommendation$widgets$RecommendationWidgetType[recommendationWidgetTypeFromRecommendation.ordinal()];
        if (i == 1) {
            TrackRecommendations trackRecommendations = (TrackRecommendations) baseRecommendations;
            return new RecommendationWidget<>(widgetId, recommendationWidgetTypeFromRecommendation, trackRecommendations.getTracks(), new RecommendationInfo(recommendationTypeFromString, widgetId, trackRecommendations.getContentInfo()));
        }
        if (i == 2 || i == 3) {
            AlbumRecommendations albumRecommendations = (AlbumRecommendations) baseRecommendations;
            return new RecommendationWidget<>(widgetId, recommendationWidgetTypeFromRecommendation, albumRecommendations.getAlbums(), new RecommendationInfo(recommendationTypeFromString, widgetId, albumRecommendations.getContentInfo()));
        }
        if (i == 4) {
            PlaylistRecommendations playlistRecommendations = (PlaylistRecommendations) baseRecommendations;
            return new RecommendationWidget<>(widgetId, recommendationWidgetTypeFromRecommendation, playlistRecommendations.getPlaylists(), new RecommendationInfo(recommendationTypeFromString, widgetId, playlistRecommendations.getContentInfo()));
        }
        if (i != 5) {
            return null;
        }
        StationRecommendations stationRecommendations = (StationRecommendations) baseRecommendations;
        return new RecommendationWidget<>(widgetId, recommendationWidgetTypeFromRecommendation, stationRecommendations.getStations(), new RecommendationInfo(recommendationTypeFromString, widgetId, stationRecommendations.getContentInfo()));
    }

    /* JADX INFO: renamed from: com.amazon.music.recommendation.widgets.RecommendationWidgetResult$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$recommendation$widgets$RecommendationWidgetType;

        static {
            int[] iArr = new int[RecommendationWidgetType.values().length];
            $SwitchMap$com$amazon$music$recommendation$widgets$RecommendationWidgetType = iArr;
            try {
                iArr[RecommendationWidgetType.TRACKS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$recommendation$widgets$RecommendationWidgetType[RecommendationWidgetType.ALBUMS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$recommendation$widgets$RecommendationWidgetType[RecommendationWidgetType.ALBUMS_UPSELL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$recommendation$widgets$RecommendationWidgetType[RecommendationWidgetType.PLAYLISTS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$recommendation$widgets$RecommendationWidgetType[RecommendationWidgetType.STATIONS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public List<RecommendationWidget> getRecommendations() {
        return this.recommendations;
    }
}
