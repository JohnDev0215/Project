package com.amazon.mp3.view.lyrics.sharing.utils;

import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsSharingMetricsSender.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b#\u0010$J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u001e\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\r\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u0002J.\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006J\u001e\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J(\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u0016J\u000e\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0014R\u0014\u0010\u001d\u001a\u00020\u001a8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010 \u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\"\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b!\u0010\u001f¨\u0006%"}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricsSharingMetricsSender;", "", "Lcom/amazon/music/media/playback/MediaItem;", "mediaItem", "", "getEntityId", "", "lyricsStartIndex", "numOfLines", "", "sendLyricsShareIntentFromTrackShareMetrics", "sendLyricsPresetSelectorPageViewMetrics", "sendLyricsLineSelectionPageViewMetrics", "sendLyricsShareButtonClickMetrics", "sharePlatformSelected", "presetSelected", "sendLyricsPresetShareMetrics", "sendNextButtonClickMetrics", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", "interactionType", "sendUIClickMetrics", "sendUIPageViewMetrics", "Lcom/amazon/music/metrics/MetricsManager;", "getMetricsManager", "()Lcom/amazon/music/metrics/MetricsManager;", "metricsManager", "getEntityType", "()Ljava/lang/String;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "getEntityIdType", ContextMappingConstants.ENTITY_ID_TYPE, "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsSharingMetricsSender {
    public static final LyricsSharingMetricsSender INSTANCE = new LyricsSharingMetricsSender();

    private LyricsSharingMetricsSender() {
    }

    public final String getEntityType() {
        return EntityType.TRACK.name();
    }

    public final String getEntityIdType() {
        String metricsValue = EntityIdType.ASIN.getMetricsValue();
        Intrinsics.checkNotNullExpressionValue(metricsValue, "ASIN.metricValue");
        return metricsValue;
    }

    private final MetricsManager getMetricsManager() {
        MetricsManager manager = MetricsHolder.getManager();
        Intrinsics.checkNotNullExpressionValue(manager, "getManager()");
        return manager;
    }

    public final void sendLyricsShareIntentFromTrackShareMetrics(MediaItem mediaItem, int lyricsStartIndex, int numOfLines) {
        Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
        MetricsLogger.sendEvent(FlexEvent.builder(LyricsSharingMetricsEvent.EVENT_NAME_LYRICS_SHARE_INTENT_FROM_TRACK_SHARE.getMetricValue()).withEmitterId("em_230").withFlexStr1(getEntityId(mediaItem)).withFlexStr2(getEntityIdType()).withFlexStr3(getEntityType()).withFlexNum1(Float.valueOf(lyricsStartIndex)).withFlexNum2(Float.valueOf(numOfLines)).build());
    }

    public final void sendLyricsPresetSelectorPageViewMetrics(MediaItem mediaItem, int lyricsStartIndex, int numOfLines) {
        Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
        MetricsLogger.sendEvent(FlexEvent.builder(LyricsSharingMetricsEvent.EVENT_NAME_LYRICS_PRESET_SELECTOR_PAGE.getMetricValue()).withEmitterId("em_230").withFlexStr1(getEntityId(mediaItem)).withFlexStr2(getEntityIdType()).withFlexStr3(getEntityType()).withFlexNum1(Float.valueOf(lyricsStartIndex)).withFlexNum2(Float.valueOf(numOfLines)).build());
    }

    public final void sendLyricsLineSelectionPageViewMetrics(MediaItem mediaItem) {
        Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
        MetricsLogger.sendEvent(FlexEvent.builder(LyricsSharingMetricsEvent.EVENT_NAME_LYRICS_LINE_SELECTION_PAGE.getMetricValue()).withEmitterId("em_230").withFlexStr1(getEntityId(mediaItem)).withFlexStr2(getEntityIdType()).withFlexStr3(getEntityType()).build());
    }

    public final void sendLyricsShareButtonClickMetrics(MediaItem mediaItem) {
        Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
        MetricsLogger.sendEvent(FlexEvent.builder(LyricsSharingMetricsEvent.EVENT_NAME_LYRICS_SHARE_BUTTON_CLICK.getMetricValue()).withEmitterId("em_230").withFlexStr1(getEntityId(mediaItem)).withFlexStr2(getEntityIdType()).withFlexStr3(getEntityType()).build());
    }

    public final void sendLyricsPresetShareMetrics(MediaItem mediaItem, String sharePlatformSelected, int lyricsStartIndex, int numOfLines, int presetSelected) {
        Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
        Intrinsics.checkNotNullParameter(sharePlatformSelected, "sharePlatformSelected");
        MetricsLogger.sendEvent(FlexEvent.builder(LyricsSharingMetricsEvent.EVENT_NAME_LYRICS_PRESET_SHARE.getMetricValue()).withEmitterId("em_230").withFlexStr1(getEntityId(mediaItem)).withFlexStr2(getEntityIdType()).withFlexStr3(getEntityType()).withFlexStr4(sharePlatformSelected).withFlexNum1(Float.valueOf(lyricsStartIndex)).withFlexNum2(Float.valueOf(numOfLines)).withFlexNum3(Float.valueOf(presetSelected)).build());
    }

    public final void sendNextButtonClickMetrics(MediaItem mediaItem, int lyricsStartIndex, int numOfLines) {
        Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
        MetricsLogger.sendEvent(FlexEvent.builder(LyricsSharingMetricsEvent.EVENT_NAME_LYRICS_SELECTION_NEXT_BUTTON_CLICK.getMetricValue()).withEmitterId("em_230").withFlexStr1(getEntityId(mediaItem)).withFlexStr2(getEntityIdType()).withFlexStr3(getEntityType()).withFlexNum1(Float.valueOf(lyricsStartIndex)).withFlexNum2(Float.valueOf(numOfLines)).build());
    }

    public static /* synthetic */ void sendUIClickMetrics$default(LyricsSharingMetricsSender lyricsSharingMetricsSender, MediaItem mediaItem, ActionType actionType, PageType pageType, InteractionType interactionType, int i, Object obj) {
        if ((i & 8) != 0) {
            interactionType = InteractionType.TAP;
        }
        lyricsSharingMetricsSender.sendUIClickMetrics(mediaItem, actionType, pageType, interactionType);
    }

    public final void sendUIClickMetrics(MediaItem mediaItem, ActionType actionType, PageType pageType, InteractionType interactionType) {
        Intrinsics.checkNotNullParameter(mediaItem, "mediaItem");
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(interactionType, "interactionType");
        getMetricsManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(interactionType).withPageType(pageType).withEntityIdType(EntityIdType.ASIN).withEntityId(getEntityId(mediaItem)).withEntityType(EntityType.TRACK).build());
    }

    public final void sendUIPageViewMetrics(PageType pageType) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        getMetricsManager().handleEvent(UiPageViewEvent.builder(pageType.getMetricsValue()).build());
    }

    private final String getEntityId(MediaItem mediaItem) {
        MediaCollectionId id;
        MediaCollectionInfo sourceMediaCollectionInfo = mediaItem.getSourceMediaCollectionInfo();
        if (sourceMediaCollectionInfo == null || (id = sourceMediaCollectionInfo.getId()) == null) {
            return null;
        }
        return id.getId();
    }
}
