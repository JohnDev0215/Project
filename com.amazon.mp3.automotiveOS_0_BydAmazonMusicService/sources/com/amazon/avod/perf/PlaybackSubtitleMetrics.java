package com.amazon.avod.perf;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackSubtitleMetrics extends RegistrableProfilerMetric {
    public static final Marker BEGIN_SUBTITLE_DOWNLOAD_TASK;
    public static final Marker END_SUBTITLE_DOWNLOAD_TASK;
    public static final Marker PLAYBACK_START;
    public static final Marker PLAYSTATE_CHANGED_TO_SUBTITLES_OFF;
    public static final Marker PLAYSTATE_CHANGED_TO_SUBTITLES_ON;
    public static final Marker SUBTITLE_DATA_LOADED;
    private static final MarkerMetric SUBTITLE_DOWNLOADTIME;
    private static final MarkerMetric SUBTITLE_ON_SWITCH_METRIC;
    private static final MarkerMetric SUBTITLE_RENDERTIME;
    private static final MarkerMetric SUBTITLE_ZERO_DISPLAY_TIME;

    static {
        Marker marker = new Marker("BEGIN_SUBTITLE_DOWNLOAD_TASK");
        BEGIN_SUBTITLE_DOWNLOAD_TASK = marker;
        Marker marker2 = new Marker("END_SUBTITLE_DOWNLOAD_TASK");
        END_SUBTITLE_DOWNLOAD_TASK = marker2;
        Marker marker3 = new Marker("SUBTITLE_DATA_LOADED");
        SUBTITLE_DATA_LOADED = marker3;
        Marker marker4 = new Marker("PLAYBACK_START");
        PLAYBACK_START = marker4;
        SUBTITLE_DOWNLOADTIME = new StateMachineMetric("Subtitle-DownloadTime", Conditional.sequence(Conditional.is(marker), Conditional.is(marker2)));
        SUBTITLE_RENDERTIME = new StateMachineMetric("Subtitle-RenderTime", Conditional.sequence(Conditional.is(marker4), Conditional.is(marker3)));
        Marker marker5 = new Marker("PLAYSTATE_CHANGED_TO_CLOSED_CAPTIONS_ON");
        PLAYSTATE_CHANGED_TO_SUBTITLES_ON = marker5;
        PLAYSTATE_CHANGED_TO_SUBTITLES_OFF = new Marker("PLAYSTATE_CHANGED_TO_CLOSED_CAPTIONS_OFF");
        SUBTITLE_ZERO_DISPLAY_TIME = new CounterMetricStateMachine("Subtitles-ZeroDisplayTime", Conditional.sequence(Conditional.is(marker3), Conditional.is(marker5)));
        SUBTITLE_ON_SWITCH_METRIC = new CounterMetricStateMachine("Subtitles-OnSwitchMetric", Conditional.is(marker5));
    }
}
