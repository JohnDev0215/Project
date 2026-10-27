package com.amazon.music.platform.playback;

import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.inappmessaging.internal.model.Splash;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: PlaybackRequestFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/platform/playback/PlaybackRequestFactory;", "", "()V", "Companion", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class PlaybackRequestFactory {
    private static final boolean DEFAULT_IS_USER_INITIATED = false;
    private static final double DEFAULT_START_TIME_IN_MS = 0.0d;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String MRN_KEY = "mrn";
    private static final String SESSION_ID_KEY = Splash.PARAMS_SESSION_ID;
    private static final String INITIATED_FROM_KEY = "initiatedFrom";
    private static final String START_TIME_IN_MS_KEY = "startTimeInMs";
    private static final String IS_USER_INITIATED_KEY = "isUserInitiated";
    private static final String LOOP_MODE_KEY = "loopMode";
    private static final String SHUFFLE_MODE_KEY = "shuffleMode";
    private static final String SHOW_NOW_PLAYING_KEY = "showNowPlaying";
    private static final String PLAY_MODE_KEY = ContextMappingConstants.PLAY_MODE;
    private static final String DEFAULT_LOOP_SETTING = "LOOP_OFF";
    private static final String DEFAULT_SHUFFLE_SETTING = "SHUFFLE_OFF";
    private static final boolean DEFAULT_SHOW_NOW_PLAYING = true;
    private static final String DEFAULT_INITIATED_FROM = "RN";

    /* JADX INFO: compiled from: PlaybackRequestFactory.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JT\u0010'\u001a\u00020(2\u0018\u0010)\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010*2\u0018\u0010+\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010*2\u0018\u0010,\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010*R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0012X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0014\u0010\u001d\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0014\u0010\u001f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0014\u0010!\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u0014\u0010#\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0006R\u0014\u0010%\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006¨\u0006-"}, d2 = {"Lcom/amazon/music/platform/playback/PlaybackRequestFactory$Companion;", "", "()V", "DEFAULT_INITIATED_FROM", "", "getDEFAULT_INITIATED_FROM", "()Ljava/lang/String;", "DEFAULT_IS_USER_INITIATED", "", "getDEFAULT_IS_USER_INITIATED", "()Z", "DEFAULT_LOOP_SETTING", "getDEFAULT_LOOP_SETTING", "DEFAULT_SHOW_NOW_PLAYING", "getDEFAULT_SHOW_NOW_PLAYING", "DEFAULT_SHUFFLE_SETTING", "getDEFAULT_SHUFFLE_SETTING", "DEFAULT_START_TIME_IN_MS", "", "getDEFAULT_START_TIME_IN_MS", "()D", "INITIATED_FROM_KEY", "getINITIATED_FROM_KEY", "IS_USER_INITIATED_KEY", "getIS_USER_INITIATED_KEY", "LOOP_MODE_KEY", "getLOOP_MODE_KEY", "MRN_KEY", "getMRN_KEY", "PLAY_MODE_KEY", "getPLAY_MODE_KEY", "SESSION_ID_KEY", "getSESSION_ID_KEY", "SHOW_NOW_PLAYING_KEY", "getSHOW_NOW_PLAYING_KEY", "SHUFFLE_MODE_KEY", "getSHUFFLE_MODE_KEY", "START_TIME_IN_MS_KEY", "getSTART_TIME_IN_MS_KEY", "from", "Lcom/amazon/music/platform/playback/data/PlaybackRequest;", "playbackRequest", "", "playbackOptions", "metricsContext", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getMRN_KEY() {
            return PlaybackRequestFactory.MRN_KEY;
        }

        public final String getSESSION_ID_KEY() {
            return PlaybackRequestFactory.SESSION_ID_KEY;
        }

        public final String getINITIATED_FROM_KEY() {
            return PlaybackRequestFactory.INITIATED_FROM_KEY;
        }

        public final String getSTART_TIME_IN_MS_KEY() {
            return PlaybackRequestFactory.START_TIME_IN_MS_KEY;
        }

        public final String getIS_USER_INITIATED_KEY() {
            return PlaybackRequestFactory.IS_USER_INITIATED_KEY;
        }

        public final String getLOOP_MODE_KEY() {
            return PlaybackRequestFactory.LOOP_MODE_KEY;
        }

        public final String getSHUFFLE_MODE_KEY() {
            return PlaybackRequestFactory.SHUFFLE_MODE_KEY;
        }

        public final String getSHOW_NOW_PLAYING_KEY() {
            return PlaybackRequestFactory.SHOW_NOW_PLAYING_KEY;
        }

        public final String getPLAY_MODE_KEY() {
            return PlaybackRequestFactory.PLAY_MODE_KEY;
        }

        public final String getDEFAULT_LOOP_SETTING() {
            return PlaybackRequestFactory.DEFAULT_LOOP_SETTING;
        }

        public final String getDEFAULT_SHUFFLE_SETTING() {
            return PlaybackRequestFactory.DEFAULT_SHUFFLE_SETTING;
        }

        public final boolean getDEFAULT_SHOW_NOW_PLAYING() {
            return PlaybackRequestFactory.DEFAULT_SHOW_NOW_PLAYING;
        }

        public final String getDEFAULT_INITIATED_FROM() {
            return PlaybackRequestFactory.DEFAULT_INITIATED_FROM;
        }

        public final double getDEFAULT_START_TIME_IN_MS() {
            return PlaybackRequestFactory.DEFAULT_START_TIME_IN_MS;
        }

        public final boolean getDEFAULT_IS_USER_INITIATED() {
            return PlaybackRequestFactory.DEFAULT_IS_USER_INITIATED;
        }

        /* JADX WARN: Removed duplicated region for block: B:55:0x00b1  */
        /* JADX WARN: Removed duplicated region for block: B:70:0x00e1  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x011c  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final com.amazon.music.platform.playback.data.PlaybackRequest from(java.util.Map<java.lang.String, ? extends java.lang.Object> r18, java.util.Map<java.lang.String, ? extends java.lang.Object> r19, java.util.Map<java.lang.String, ? extends java.lang.Object> r20) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 444
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.platform.playback.PlaybackRequestFactory.Companion.from(java.util.Map, java.util.Map, java.util.Map):com.amazon.music.platform.playback.data.PlaybackRequest");
        }
    }
}
