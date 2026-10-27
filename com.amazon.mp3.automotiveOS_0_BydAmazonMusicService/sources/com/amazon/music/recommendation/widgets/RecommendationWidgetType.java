package com.amazon.music.recommendation.widgets;

import com.amazon.music.recommendation.RecommendationType;
import com.amazon.music.storeservice.model.BaseRecommendations;

/* JADX INFO: loaded from: classes4.dex */
public enum RecommendationWidgetType {
    STATIONS,
    PLAYLISTS,
    ALBUMS,
    ALBUMS_UPSELL,
    TRACKS,
    UNKNOWN;

    public static RecommendationWidgetType fromRecommendationType(RecommendationType recommendationType) {
        if (recommendationType == null) {
            return UNKNOWN;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$recommendation$RecommendationType[recommendationType.ordinal()];
        if (i == 1) {
            return STATIONS;
        }
        if (i == 2) {
            return PLAYLISTS;
        }
        if (i == 3) {
            return ALBUMS;
        }
        if (i == 4) {
            return TRACKS;
        }
        return UNKNOWN;
    }

    /* JADX INFO: renamed from: com.amazon.music.recommendation.widgets.RecommendationWidgetType$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$recommendation$RecommendationType;

        static {
            int[] iArr = new int[RecommendationType.values().length];
            $SwitchMap$com$amazon$music$recommendation$RecommendationType = iArr;
            try {
                iArr[RecommendationType.STATION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$recommendation$RecommendationType[RecommendationType.PLAYLIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$recommendation$RecommendationType[RecommendationType.ALBUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$recommendation$RecommendationType[RecommendationType.TRACK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static RecommendationWidgetType fromRecommendation(BaseRecommendations baseRecommendations) {
        return fromRecommendationType(RecommendationType.fromString(baseRecommendations.getRecommendationType()));
    }
}
