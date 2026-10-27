package com.amazon.music.castingviews;

import com.amazon.music.casting.CastingCategory;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.events.ContentViewedAppEvent;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.events.types.EntityIdType;
import com.amazon.music.events.types.EntityType;
import com.amazon.music.events.types.InteractionType;
import com.amazon.music.events.types.Orientation;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class CastingUIMetricsLogger {

    public enum ContentName {
        NONE(""),
        NOW_PLAYING("nowPlaying"),
        NOW_PLAYING_STAGE("nowPlayingStage"),
        MINI_PLAYER("miniPlayer"),
        CASTING_RIBBON("castingRibbon"),
        ACTION_BAR("actionBar"),
        SETTINGS("settings"),
        REMOTE_CONTROL_DIALOG("controlOrCastDialog"),
        TROUBLESHOOT_BUTTON("castingHelpButton"),
        CONTENT_UNAVAILABLE_ALEXA_DIALOG("contentUnavailableWhileCastingAlexaDialog"),
        CONTENT_UNAVAILABLE_CHROMECAST_DIALOG("contentUnavailableWhileCastingChromecastDialog"),
        UNSUPPORTED_CONTENT_DIALOG("castingUnsupportedContentErrorDialog"),
        ALEXA_UNAVAILABLE_DIALOG("alexaOnMobileCastingErrorDialog"),
        WAKE_WORD_DISABLED("wakeWordCastingToast"),
        CASTING_NETWORK_ERROR_DIALOG("castingNetworkErrorDialog"),
        CASTING_DISCONNECTED_DIALOG("castingDeviceDisconnectedDialog"),
        CASTING_COACHMARK("castingCoachmarkView");

        private final String metricName;

        ContentName(String str) {
            this.metricName = str;
        }

        public String getMetricName() {
            return this.metricName;
        }
    }

    public static void recordCastingButtonClickMetric(ContentName contentName) {
        new ContentInfoBuilder();
        UserInteractionAppEvent.builder(ActionType.GO_CASTING_DEVICE_LIST.getMetricsValue()).withInteractionType(InteractionType.TAP);
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$castingviews$CastingUIMetricsLogger$ContentName[contentName.ordinal()];
        if (i == 1) {
            recordCastingButtonClickMetric(ContentName.NOW_PLAYING, PageType.NOW_PLAYING);
            return;
        }
        if (i == 2) {
            recordCastingButtonClickMetric(ContentName.NOW_PLAYING_STAGE, PageType.NOW_PLAYING_STAGE);
        } else if (i == 3) {
            recordCastingButtonClickMetric(ContentName.SETTINGS, PageType.SETTINGS);
        } else {
            if (i != 4) {
                return;
            }
            recordCastingButtonClickMetric(ContentName.MINI_PLAYER, PageType.MINI_TRANSPORT);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.castingviews.CastingUIMetricsLogger$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$castingviews$CastingUIMetricsLogger$ContentName;

        static {
            int[] iArr = new int[ContentName.values().length];
            $SwitchMap$com$amazon$music$castingviews$CastingUIMetricsLogger$ContentName = iArr;
            try {
                iArr[ContentName.NOW_PLAYING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$castingviews$CastingUIMetricsLogger$ContentName[ContentName.NOW_PLAYING_STAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$castingviews$CastingUIMetricsLogger$ContentName[ContentName.ACTION_BAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$castingviews$CastingUIMetricsLogger$ContentName[ContentName.MINI_PLAYER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static void recordCastingButtonClickMetric(ContentName contentName, PageType pageType) {
        ContentInfoBuilder contentInfoBuilder = new ContentInfoBuilder();
        UserInteractionAppEvent.Builder builderWithInteractionType = UserInteractionAppEvent.builder(ActionType.GO_CASTING_DEVICE_LIST.getMetricsValue()).withInteractionType(InteractionType.TAP);
        if (contentName != null) {
            contentInfoBuilder.withContentName(contentName.getMetricName());
        }
        if (pageType != null) {
            builderWithInteractionType.withPageType(pageType.name());
        }
        builderWithInteractionType.withContentInfo(contentInfoBuilder.build()).publish();
    }

    public static void recordCastingHeaderClickMetric() {
        UserInteractionAppEvent.builder(ActionType.GO_CASTING_DEVICE_LIST.getMetricsValue()).withInteractionType(InteractionType.TAP).withContentInfo(new ContentInfoBuilder().withContentName(ContentName.CASTING_RIBBON.getMetricName()).build()).publish();
    }

    public static void recordDeviceListPageView(Orientation orientation) {
        NavigationAppEvent.builder(PageType.CASTING_DEVICE_LIST.getMetricsValue()).withOrientation(orientation).publish();
    }

    static void recordCastingDeviceListContentView(int i) {
        ContentViewedAppEvent.builder().withContentInfo(new ContentInfoBuilder().withFirstViewableIndex(0).withLastViewableIndex(i).build()).publish();
    }

    public static void recordDismissDeviceListClickMetric() {
        UserInteractionAppEvent.builder(ActionType.GO_BACK.getMetricsValue()).withInteractionType(InteractionType.TAP).withPageType(PageType.CASTING_DEVICE_LIST.name()).withContentInfo(new ContentInfoBuilder().build()).publish();
    }

    static void recordCastingDeviceClickMetric(String str, int i) {
        UserInteractionAppEvent.builder(ActionType.CONNECT_CASTING_DEVICE.getMetricsValue()).withInteractionType(InteractionType.TAP).withPageType(PageType.CASTING_DEVICE_LIST.name()).withEntityId(str).withEntityIdType(EntityIdType.DEVICE_TARGET_ID).withEntityType(EntityType.DEVICE).withEntityPos(i).publish();
    }

    static void recordTroubleshootButtonClickMetric() {
        UserInteractionAppEvent.builder(ActionType.CASTING_HELP.getMetricsValue()).withInteractionType(InteractionType.TAP).withPageType(PageType.CASTING_DEVICE_LIST.name()).withContentInfo(new ContentInfoBuilder().withContentName(ContentName.TROUBLESHOOT_BUTTON.getMetricName()).build()).publish();
    }

    static void recordTroubleshootPageView(Orientation orientation) {
        NavigationAppEvent.builder(PageType.CASTING_HELP.getMetricsValue()).withOrientation(orientation).publish();
    }

    static void recordCastingDeviceDisconnectClickMetric(String str, int i) {
        UserInteractionAppEvent.builder(ActionType.DISCONNECT_CASTING_DEVICE.getMetricsValue()).withInteractionType(InteractionType.TAP).withPageType(PageType.CASTING_DEVICE_LIST.name()).withEntityId(str).withEntityIdType(EntityIdType.DEVICE_TARGET_ID).withEntityType(EntityType.DEVICE).withEntityPos(i).publish();
    }

    static void recordControlDeviceClickMetric() {
        UserInteractionAppEvent.builder(ActionType.CONTROL_CASTING_DEVICE.getMetricsValue()).withInteractionType(InteractionType.TAP).withPageType(PageType.CASTING_DEVICE_LIST.name()).withContentInfo(new ContentInfoBuilder().withContentName(ContentName.REMOTE_CONTROL_DIALOG.getMetricName()).withContainerIndex(1).build()).publish();
    }

    static void recordCastToDeviceClickMetric() {
        UserInteractionAppEvent.builder(ActionType.CAST_TO_CASTING_DEVICE.getMetricsValue()).withInteractionType(InteractionType.TAP).withPageType(PageType.CASTING_DEVICE_LIST.name()).withContentInfo(new ContentInfoBuilder().withContentName(ContentName.REMOTE_CONTROL_DIALOG.getMetricName()).withContainerIndex(0).build()).publish();
    }

    static void recordRemoteControlDialogContentView() {
        ContentViewedAppEvent.builder().withContentInfo(new ContentInfoBuilder().withContentName(ContentName.REMOTE_CONTROL_DIALOG.getMetricName()).withContainerIndex(0).withContainerType("DIALOG").withNumItemsAccessible(2).build()).publish();
    }

    public static void recordDeviceDisconnectedDialogOkClick() {
        UserInteractionAppEvent.builder(ActionType.SELECT_OK.getMetricsValue()).withInteractionType(InteractionType.TAP).withContentInfo(new ContentInfoBuilder().withContentName(ContentName.CASTING_DISCONNECTED_DIALOG.getMetricName()).build()).publish();
    }

    public static void recordDeviceDisconnectedDialogContentView() {
        ContentViewedAppEvent.builder().withContentInfo(new ContentInfoBuilder().withContentName(ContentName.CASTING_DISCONNECTED_DIALOG.getMetricName()).withContainerIndex(0).withContainerType("DIALOG").withNumItemsAccessible(1).build()).publish();
    }

    public static void recordContentUnavailableDialogContentView(CastingCategory castingCategory) {
        ContentName contentName;
        boolean zEquals = CastingCategory.CHROMECAST.equals(castingCategory);
        ContentInfoBuilder contentInfoBuilder = new ContentInfoBuilder();
        if (zEquals) {
            contentName = ContentName.CONTENT_UNAVAILABLE_CHROMECAST_DIALOG;
        } else {
            contentName = ContentName.CONTENT_UNAVAILABLE_ALEXA_DIALOG;
        }
        ContentViewedAppEvent.builder().withContentInfo(contentInfoBuilder.withContentName(contentName.getMetricName()).withNumItemsAccessible(zEquals ? 1 : 2).withContainerType("DIALOG").withContainerIndex(0).build()).publish();
    }

    public static void recordContentUnavailableDialogOkClick(CastingCategory castingCategory) {
        ContentName contentName;
        ContentInfoBuilder contentInfoBuilder = new ContentInfoBuilder();
        if (CastingCategory.CHROMECAST.equals(castingCategory)) {
            contentName = ContentName.CONTENT_UNAVAILABLE_CHROMECAST_DIALOG;
        } else {
            contentName = ContentName.CONTENT_UNAVAILABLE_ALEXA_DIALOG;
        }
        UserInteractionAppEvent.builder(ActionType.SELECT_OK.getMetricsValue()).withInteractionType(InteractionType.TAP).withContentInfo(contentInfoBuilder.withContentName(contentName.getMetricName()).build()).publish();
    }

    public static void recordContentUnavailableDialogDisconnectClick() {
        UserInteractionAppEvent.builder(ActionType.DISCONNECT_CASTING_DEVICE.getMetricsValue()).withInteractionType(InteractionType.TAP).withContentInfo(new ContentInfoBuilder().withContentName(ContentName.CONTENT_UNAVAILABLE_ALEXA_DIALOG.getMetricName()).build()).publish();
    }

    public static void recordAlexaUnavailableDialogContentView() {
        ContentViewedAppEvent.builder().withContentInfo(new ContentInfoBuilder().withContentName(ContentName.ALEXA_UNAVAILABLE_DIALOG.getMetricName()).withContainerIndex(0).withContainerType("DIALOG").withNumItemsAccessible(2).build()).publish();
    }

    public static void recordAlexaUnavailableDialogOkClick() {
        UserInteractionAppEvent.builder(ActionType.SELECT_OK.getMetricsValue()).withInteractionType(InteractionType.TAP).withContentInfo(new ContentInfoBuilder().withContentName(ContentName.ALEXA_UNAVAILABLE_DIALOG.getMetricName()).build()).publish();
    }

    public static void recordAlexaUnavailableDialogDisconnectClick() {
        UserInteractionAppEvent.builder(ActionType.DISCONNECT_CASTING_DEVICE.getMetricsValue()).withInteractionType(InteractionType.TAP).withContentInfo(new ContentInfoBuilder().withContentName(ContentName.ALEXA_UNAVAILABLE_DIALOG.getMetricName()).build()).publish();
    }

    public static void recordUnsupportedContentDialogContentView() {
        ContentViewedAppEvent.builder().withContentInfo(new ContentInfoBuilder().withContentName(ContentName.UNSUPPORTED_CONTENT_DIALOG.getMetricName()).withContainerIndex(0).withContainerType("DIALOG").build()).publish();
    }

    public static void recordUnsupportedContentDialogOkClick() {
        UserInteractionAppEvent.builder(ActionType.SELECT_OK.getMetricsValue()).withInteractionType(InteractionType.TAP).withContentInfo(new ContentInfoBuilder().withContentName(ContentName.UNSUPPORTED_CONTENT_DIALOG.getMetricName()).build()).publish();
    }

    public static void recordWakeWordDisabledContentView() {
        ContentViewedAppEvent.builder().withContentInfo(new ContentInfoBuilder().withContentName(ContentName.WAKE_WORD_DISABLED.getMetricName()).withContainerIndex(0).withContainerType("TOAST").build()).publish();
    }

    private static class ContentInfoBuilder {
        private Map<String, String> contentInfo;

        public ContentInfoBuilder() {
            HashMap map = new HashMap();
            this.contentInfo = map;
            map.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
        }

        public ContentInfoBuilder withContentName(String str) {
            if (str != null && str != "") {
                this.contentInfo.put("contentName", str);
            }
            return this;
        }

        public ContentInfoBuilder withContainerType(String str) {
            if (str != null && str != "") {
                this.contentInfo.put("containerType", str);
            }
            return this;
        }

        public ContentInfoBuilder withContainerIndex(int i) {
            this.contentInfo.put("containerIndex", String.valueOf(i));
            return this;
        }

        public ContentInfoBuilder withNumItemsAccessible(int i) {
            this.contentInfo.put("numItemsAccessible", String.valueOf(i));
            return this;
        }

        public ContentInfoBuilder withFirstViewableIndex(int i) {
            this.contentInfo.put("firstViewableIndex", String.valueOf(i));
            return this;
        }

        public ContentInfoBuilder withLastViewableIndex(int i) {
            this.contentInfo.put("lastViewableIndex", String.valueOf(i));
            return this;
        }

        public List<Map<String, String>> build() {
            return Collections.singletonList(this.contentInfo);
        }
    }
}
