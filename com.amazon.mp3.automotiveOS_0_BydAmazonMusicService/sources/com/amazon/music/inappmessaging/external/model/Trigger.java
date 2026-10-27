package com.amazon.music.inappmessaging.external.model;

/* JADX INFO: loaded from: classes4.dex */
public enum Trigger {
    APP_START("trigger:/appStart", false),
    APP_START_SEQUENTIAL("trigger:/appStartSequential", false),
    INELIGIBLE_CONTENT_REQUEST("trigger:/ineligibleContentRequest", true),
    FEATURE_UNAVAILABLE("trigger:/featureUnavailable", true),
    SKIP_LIMIT_REACHED("trigger:/skipLimitReached", true),
    PERSISTENT_UPSELL("trigger:/persistentUpsell", true),
    FIRST_STREAM("trigger:/firstStream", true),
    STREAM_UPSELL("trigger:/streamUpsell", true),
    ADS_UPSELL("trigger:/adsUpsell", true),
    SETTINGS_UPSELL("trigger:/settingsUpsell", true),
    ON_DEMAND_LISTENING_UPSELL("trigger:/onDemandListeningUpsell", true),
    LIBRARY_TAB_OPEN("trigger:/libraryTabOpen", false),
    TRACK_SHUFFLE_PLAYBACK_UPSELL("trigger:/trackShufflePlaybackUpsell", true),
    BROWSE_HOME_WIDGET_UPSELL("trigger:/browseHomeWidgetUpsell", true),
    CONCURRENCY_UPSELL("trigger:/concurrencyUpsell", true);

    private static final String ADS_UPSELL_NAME = "adsUpsell";
    private static final String APP_START_NAME = "appStart";
    private static final String APP_START_SEQUENTIAL_NAME = "appStartSequential";
    private static final String BROWSE_HOME_WIDGET_UPSELL_NAME = "browseHomeWidgetUpsell";
    private static final String CONCURRENCY_UPSELL_NAME = "concurrencyUpsell";
    private static final String FEATURE_UNAVAILABLE_NAME = "featureUnavailable";
    private static final String FIRST_STREAM_NAME = "firstStream";
    private static final String INELIGIBLE_CONTENT_REQUEST_NAME = "ineligibleContentRequest";
    private static final String LIBRARY_TAB_OPEN_NAME = "libraryTabOpen";
    private static final String ON_DEMAND_LISTENING_UPSELL_NAME = "onDemandListeningUpsell";
    private static final String PERSISTENT_UPSELL_NAME = "persistentUpsell";
    private static final String PREFIX = "trigger:/";
    private static final String SETTINGS_UPSELL_NAME = "settingsUpsell";
    private static final String SKIP_LIMIT_REACHED_NAME = "skipLimitReached";
    private static final String STREAM_UPSELL_NAME = "streamUpsell";
    private static final String TRACK_SHUFFLE_PLAYBACK_UPSELL_NAME = "trackShufflePlaybackUpsell";
    private final boolean isCritical;
    private final String name;

    Trigger(final String name, final boolean isCritical) {
        this.name = name;
        this.isCritical = isCritical;
    }

    public String getName() {
        return this.name;
    }

    public boolean isCritical() {
        return this.isCritical;
    }

    public static Trigger fromName(final String triggerName) {
        String uriWithoutParams = getUriWithoutParams(triggerName);
        for (Trigger trigger : values()) {
            if (trigger.getName().contains(uriWithoutParams)) {
                return trigger;
            }
        }
        throw new IllegalArgumentException(triggerName + " is not a valid Trigger.");
    }

    private static String getUriWithoutParams(String uri) {
        return uri.split("\\?")[0];
    }
}
