package com.amazon.video.sdk.stream;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.media.playback.reporting.aloysius.AloysiusInteractionReporter;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleConfig;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleLanguage;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.SDKPlayerLogger;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.video.sdk.avod.playbackclient.activity.feature.PlaybackSubtitleFeature;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: StreamGroup.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 ?2\u00020\u00012\u00020\u0002:\u0001?BG\b\u0000\u0012\u0006\u00108\u001a\u000207\u0012\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010;\u001a\u00020:\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u000e\b\u0002\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0004\b=\u0010>J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0006\u0010\n\u001a\u00020\u0003J\u0006\u0010\u000b\u001a\u00020\u0003J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\fH\u0016J\b\u0010\u000f\u001a\u00020\fH\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\fH\u0016J\u000e\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0012R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u001c\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00190\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u001a\u0010,\u001a\u00020+8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\u0014\u00102\u001a\u00020&8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u001a\u00106\u001a\b\u0012\u0004\u0012\u00020&038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u00105¨\u0006@"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextStreamGroupImpl;", "Lcom/amazon/video/sdk/stream/TimedTextStreamGroup;", "", "", "updateActiveStream", "Lcom/amazon/avod/media/playback/VideoPresentation;", "videoPresentation", "Lcom/amazon/avod/media/playback/VideoRenderingSettings;", "videoRenderingSettings", "onPrepared", "notifyStart", "reset", "", "isDestroyed", "onTerminate", "isShowingCaptions", "value", "showCaptions", "Lcom/amazon/video/sdk/stream/TimedTextConfig;", "timedTextConfig", "setTimedTextConfig", "Lcom/amazon/avod/media/playback/reporting/aloysius/AloysiusInteractionReporter;", "aloysiusInteractionReporter", "Lcom/amazon/avod/media/playback/reporting/aloysius/AloysiusInteractionReporter;", "", "", "defaultSubtitleLanguageCodes", "Ljava/util/Set;", "Lcom/amazon/video/sdk/stream/StreamPreferencesProvider;", "Lcom/amazon/video/sdk/stream/TimedTextStreamMatcher;", "subtitlePreferencesProvider", "Lcom/amazon/video/sdk/stream/StreamPreferencesProvider;", "Lcom/amazon/video/sdk/avod/playbackclient/activity/feature/PlaybackSubtitleFeature;", "playbackSubtitleFeature", "Lcom/amazon/video/sdk/avod/playbackclient/activity/feature/PlaybackSubtitleFeature;", "Landroid/os/Handler;", "mainThreadHandler", "Landroid/os/Handler;", "Lcom/amazon/video/sdk/stream/TimedTextStream;", "currentActiveStream", "Lcom/amazon/video/sdk/stream/TimedTextStream;", "currentTimedTextConfig", "Lcom/amazon/video/sdk/stream/TimedTextConfig;", "Lcom/amazon/video/sdk/stream/StreamType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/video/sdk/stream/StreamType;", "getType", "()Lcom/amazon/video/sdk/stream/StreamType;", "getActiveStream", "()Lcom/amazon/video/sdk/stream/TimedTextStream;", "activeStream", "", "getStreams", "()Ljava/util/List;", "streams", "Landroid/content/Context;", "context", "timedTextStreamPreferencesProvider", "Lcom/amazon/video/sdk/stream/AudioStreamGroup;", "audioStreamGroup", "defaultTimedTextLanguageCodes", "<init>", "(Landroid/content/Context;Lcom/amazon/video/sdk/stream/StreamPreferencesProvider;Lcom/amazon/video/sdk/stream/TimedTextConfig;Lcom/amazon/video/sdk/stream/AudioStreamGroup;Lcom/amazon/avod/media/playback/reporting/aloysius/AloysiusInteractionReporter;Ljava/util/Set;)V", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class TimedTextStreamGroupImpl implements TimedTextStreamGroup {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final AloysiusInteractionReporter aloysiusInteractionReporter;
    private TimedTextStream currentActiveStream;
    private TimedTextConfig currentTimedTextConfig;
    private Set<String> defaultSubtitleLanguageCodes;
    private final Handler mainThreadHandler;
    private final PlaybackSubtitleFeature playbackSubtitleFeature;
    private final StreamPreferencesProvider<TimedTextStreamMatcher> subtitlePreferencesProvider;
    private final StreamType type;

    public TimedTextStreamGroupImpl(Context context, StreamPreferencesProvider<TimedTextStreamMatcher> timedTextStreamPreferencesProvider, TimedTextConfig timedTextConfig, AudioStreamGroup audioStreamGroup, AloysiusInteractionReporter aloysiusInteractionReporter, Set<String> defaultTimedTextLanguageCodes) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(timedTextStreamPreferencesProvider, "timedTextStreamPreferencesProvider");
        Intrinsics.checkNotNullParameter(timedTextConfig, "timedTextConfig");
        Intrinsics.checkNotNullParameter(audioStreamGroup, "audioStreamGroup");
        Intrinsics.checkNotNullParameter(aloysiusInteractionReporter, "aloysiusInteractionReporter");
        Intrinsics.checkNotNullParameter(defaultTimedTextLanguageCodes, "defaultTimedTextLanguageCodes");
        this.aloysiusInteractionReporter = aloysiusInteractionReporter;
        this.defaultSubtitleLanguageCodes = defaultTimedTextLanguageCodes;
        this.subtitlePreferencesProvider = timedTextStreamPreferencesProvider;
        this.playbackSubtitleFeature = new PlaybackSubtitleFeature(context, timedTextStreamPreferencesProvider, timedTextConfig, audioStreamGroup);
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
        this.currentActiveStream = new TimedTextStreamData("", "", TimedTextType.INSTANCE.from(""), TimedTextSubtype.INSTANCE.from(""), new TimedTextStreamMatcher("", null, 2, null), null, null, null, 224, null);
        this.currentTimedTextConfig = timedTextConfig;
        this.type = StreamType.TimedText;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TimedTextStreamGroupImpl(Context context, StreamPreferencesProvider streamPreferencesProvider, TimedTextConfig timedTextConfig, AudioStreamGroup audioStreamGroup, AloysiusInteractionReporter aloysiusInteractionReporter, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 32) != 0) {
            set = SubtitleConfig.getInstance().getDefaultLanguageCodes();
            Intrinsics.checkNotNullExpressionValue(set, "getInstance().defaultLanguageCodes");
        }
        this(context, streamPreferencesProvider, timedTextConfig, audioStreamGroup, aloysiusInteractionReporter, set);
    }

    @Override // com.amazon.video.sdk.stream.StreamGroup
    /* JADX INFO: renamed from: getActiveStream, reason: from getter */
    public TimedTextStream getCurrentActiveStream() {
        return this.currentActiveStream;
    }

    @Override // com.amazon.video.sdk.stream.StreamGroup
    public List<TimedTextStream> getStreams() {
        ImmutableList<TimedTextStream> currentTimedTextStreams = this.playbackSubtitleFeature.getCurrentTimedTextStreams();
        Intrinsics.checkNotNullExpressionValue(currentTimedTextStreams, "playbackSubtitleFeature.currentTimedTextStreams");
        return currentTimedTextStreams;
    }

    /* JADX INFO: compiled from: StreamGroup.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0007¨\u0006\f"}, d2 = {"Lcom/amazon/video/sdk/stream/TimedTextStreamGroupImpl$Companion;", "", "()V", "getTimedTextActiveStream", "Lcom/amazon/video/sdk/stream/TimedTextStream;", "streams", "", "timedTextStreamPreferences", "Lcom/amazon/video/sdk/stream/TimedTextStreamMatcher;", "defaultSubtitleLanguageCodes", "", "", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        @VisibleForTesting
        public final TimedTextStream getTimedTextActiveStream(List<? extends TimedTextStream> streams, List<TimedTextStreamMatcher> timedTextStreamPreferences, Set<String> defaultSubtitleLanguageCodes) {
            Intrinsics.checkNotNullParameter(streams, "streams");
            Intrinsics.checkNotNullParameter(timedTextStreamPreferences, "timedTextStreamPreferences");
            Intrinsics.checkNotNullParameter(defaultSubtitleLanguageCodes, "defaultSubtitleLanguageCodes");
            TimedTextStream timedTextStream = null;
            if (streams.size() == 0) {
                return new TimedTextStreamData("", "", TimedTextType.INSTANCE.from(""), TimedTextSubtype.INSTANCE.from(""), new TimedTextStreamMatcher("", null, 2, null), null, null, null, 224, null);
            }
            if (streams.size() == 1) {
                return (TimedTextStream) CollectionsKt___CollectionsKt.first((List) streams);
            }
            for (TimedTextStreamMatcher timedTextStreamMatcher : timedTextStreamPreferences) {
                for (TimedTextStream timedTextStream2 : streams) {
                    if (Intrinsics.areEqual(timedTextStreamMatcher.getLanguage(), timedTextStream2.getLanguage())) {
                        if (timedTextStreamMatcher.getSubType() == timedTextStream2.getSubtype()) {
                            return timedTextStream2;
                        }
                        timedTextStream = timedTextStream2;
                    }
                }
            }
            if (timedTextStream != null) {
                Intrinsics.checkNotNull(timedTextStream);
                return timedTextStream;
            }
            for (String str : defaultSubtitleLanguageCodes) {
                for (TimedTextStream timedTextStream3 : streams) {
                    if (StringsKt__StringsJVMKt.equals(timedTextStream3.getLanguage(), str, true)) {
                        return timedTextStream3;
                    }
                }
            }
            return (TimedTextStream) CollectionsKt___CollectionsKt.first((List) streams);
        }
    }

    private final void updateActiveStream() {
        SubtitleLanguage currentSubtitleLanguage = this.playbackSubtitleFeature.getCurrentSubtitleLanguage();
        if (currentSubtitleLanguage == null) {
            this.currentActiveStream = INSTANCE.getTimedTextActiveStream(getStreams(), this.subtitlePreferencesProvider.get(), this.defaultSubtitleLanguageCodes);
            return;
        }
        TimedTextSubtype timedTextSubtypeFrom = TimedTextSubtype.INSTANCE.from(String.valueOf(currentSubtitleLanguage.getSubtitleSubtype()));
        String displayName = currentSubtitleLanguage.getDisplayName();
        if (displayName == null) {
            displayName = "";
        }
        String languageCode = currentSubtitleLanguage.getLanguageCode();
        Intrinsics.checkNotNullExpressionValue(languageCode, "it.languageCode");
        TimedTextType timedTextTypeFrom = TimedTextType.INSTANCE.from(currentSubtitleLanguage.getSubtitleType().toString());
        String languageCode2 = currentSubtitleLanguage.getLanguageCode();
        Intrinsics.checkNotNullExpressionValue(languageCode2, "it.languageCode");
        this.currentActiveStream = new TimedTextStreamData(displayName, languageCode, timedTextTypeFrom, timedTextSubtypeFrom, new TimedTextStreamMatcher(languageCode2, timedTextSubtypeFrom), currentSubtitleLanguage.getDisplayName(), null, null, 192, null);
        DLog.logf("current Active TimedText Stream Language: %s", getCurrentActiveStream().getLanguage());
    }

    public void onPrepared(final VideoPresentation videoPresentation, final VideoRenderingSettings videoRenderingSettings) {
        Intrinsics.checkNotNullParameter(videoPresentation, "videoPresentation");
        Intrinsics.checkNotNullParameter(videoRenderingSettings, "videoRenderingSettings");
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.video.sdk.stream.TimedTextStreamGroupImpl$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                TimedTextStreamGroupImpl.m2495onPrepared$lambda2(this.f$0, videoPresentation, videoRenderingSettings);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onPrepared$lambda-2, reason: not valid java name */
    public static final void m2495onPrepared$lambda2(TimedTextStreamGroupImpl this$0, VideoPresentation videoPresentation, VideoRenderingSettings videoRenderingSettings) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(videoPresentation, "$videoPresentation");
        Intrinsics.checkNotNullParameter(videoRenderingSettings, "$videoRenderingSettings");
        PlaybackSubtitleFeature playbackSubtitleFeature = this$0.playbackSubtitleFeature;
        ViewGroup parentView = videoRenderingSettings.getParentView();
        Intrinsics.checkNotNull(parentView);
        playbackSubtitleFeature.prepareForPlayback(videoPresentation, parentView);
        this$0.updateActiveStream();
    }

    public final void notifyStart() {
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.video.sdk.stream.TimedTextStreamGroupImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                TimedTextStreamGroupImpl.m2494notifyStart$lambda3(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: notifyStart$lambda-3, reason: not valid java name */
    public static final void m2494notifyStart$lambda3(TimedTextStreamGroupImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.playbackSubtitleFeature.notifyStart();
        this$0.playbackSubtitleFeature.setTimedTextConfig(this$0.currentTimedTextConfig);
    }

    public final void reset() {
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.video.sdk.stream.TimedTextStreamGroupImpl$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                TimedTextStreamGroupImpl.m2497reset$lambda4(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: reset$lambda-4, reason: not valid java name */
    public static final void m2497reset$lambda4(TimedTextStreamGroupImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.playbackSubtitleFeature.reset();
    }

    public void onTerminate(boolean isDestroyed) {
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.video.sdk.stream.TimedTextStreamGroupImpl$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                TimedTextStreamGroupImpl.m2496onTerminate$lambda5(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onTerminate$lambda-5, reason: not valid java name */
    public static final void m2496onTerminate$lambda5(TimedTextStreamGroupImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.playbackSubtitleFeature.destroy();
    }

    @Override // com.amazon.video.sdk.stream.TimedTextStreamGroup
    public boolean isShowingCaptions() {
        return this.playbackSubtitleFeature.isShowingSubtitles();
    }

    @Override // com.amazon.video.sdk.stream.TimedTextStreamGroup
    public void showCaptions(final boolean value) {
        SDKPlayerLogger.log("StreamFeature.TimedTextStreamGroup.showCaptions(%s)", Boolean.valueOf(value));
        this.aloysiusInteractionReporter.reportEvent(value ? AloysiusInteractionReporter.Type.TimedTextOn : AloysiusInteractionReporter.Type.TimedTextOff, AloysiusInteractionReporter.Source.PlayerSDK);
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.video.sdk.stream.TimedTextStreamGroupImpl$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                TimedTextStreamGroupImpl.m2499showCaptions$lambda6(this.f$0, value);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showCaptions$lambda-6, reason: not valid java name */
    public static final void m2499showCaptions$lambda6(TimedTextStreamGroupImpl this$0, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.playbackSubtitleFeature.showSubtitles(z);
    }

    public final void setTimedTextConfig(TimedTextConfig timedTextConfig) {
        Intrinsics.checkNotNullParameter(timedTextConfig, "timedTextConfig");
        this.currentTimedTextConfig = timedTextConfig;
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.video.sdk.stream.TimedTextStreamGroupImpl$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                TimedTextStreamGroupImpl.m2498setTimedTextConfig$lambda7(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setTimedTextConfig$lambda-7, reason: not valid java name */
    public static final void m2498setTimedTextConfig$lambda7(TimedTextStreamGroupImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.playbackSubtitleFeature.setTimedTextConfig(this$0.currentTimedTextConfig);
    }
}
