package com.amazon.video.sdk.stream;

import androidx.annotation.VisibleForTesting;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.media.playback.VideoPresentation;
import com.amazon.avod.media.playback.VideoRenderingSettings;
import com.amazon.avod.playback.IllegalPlayerStateException;
import com.amazon.avod.playback.PlaybackExperienceController;
import com.amazon.avod.playbackclient.live.LiveLanguageTransformer;
import com.amazon.avod.util.DLog;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.video.sdk.stream.util.MultiTrackAudioUtilsKt;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: StreamGroup.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B!\b\u0000\u0012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001c\u0012\b\b\u0002\u0010 \u001a\u00020\u001f¢\u0006\u0004\b2\u00103J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J2\u0010\u0011\u001a\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J\u0018\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u0006\u0010\u0018\u001a\u00020\u0016J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0019H\u0016R\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0014\u0010 \u001a\u00020\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0014\u0010\"\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010%R\u001c\u0010&\u001a\b\u0012\u0004\u0012\u00020\t0\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010(\u001a\u00020\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010#R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010)R\u001a\u0010+\u001a\u00020*8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R\u0014\u00101\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u00100¨\u00064"}, d2 = {"Lcom/amazon/video/sdk/stream/AudioStreamGroupImpl;", "Lcom/amazon/video/sdk/stream/AudioStreamGroup;", "", "Lcom/amazon/video/sdk/stream/AudioStream;", "getCurrentAudioStream", "Lcom/google/common/base/Optional;", "", "getDefaultAudioTrackId", "Lcom/google/common/collect/ImmutableList;", "Lcom/amazon/avod/content/urlvending/AudioTrackMetadata;", "getAllAudioTrackMetadata", "getLiveAudioTrackMetadata", "", "audioStreams", "Lcom/amazon/video/sdk/stream/AudioStreamMatcher;", "audioStreamPreferences", "defaultAudioTrackId", "getDefaultActiveStream", "Lcom/amazon/avod/media/playback/VideoPresentation;", "videoPresentation", "Lcom/amazon/avod/media/playback/VideoRenderingSettings;", "videoRenderingSettings", "", "onPrepared", "updateActiveStream", "", "isDestroyed", "onTerminate", "Lcom/amazon/video/sdk/stream/StreamPreferencesProvider;", "audioStreamPreferencesProvider", "Lcom/amazon/video/sdk/stream/StreamPreferencesProvider;", "Lcom/amazon/avod/playbackclient/live/LiveLanguageTransformer;", "liveLanguageTransformer", "Lcom/amazon/avod/playbackclient/live/LiveLanguageTransformer;", "defaultAudioStream", "Lcom/amazon/video/sdk/stream/AudioStream;", "", "Ljava/util/List;", "audioTrackMetadataList", "Lcom/google/common/collect/ImmutableList;", "currentAudioStream", "Lcom/amazon/avod/media/playback/VideoPresentation;", "Lcom/amazon/video/sdk/stream/StreamType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lcom/amazon/video/sdk/stream/StreamType;", "getType", "()Lcom/amazon/video/sdk/stream/StreamType;", "getActiveStream", "()Lcom/amazon/video/sdk/stream/AudioStream;", "activeStream", "<init>", "(Lcom/amazon/video/sdk/stream/StreamPreferencesProvider;Lcom/amazon/avod/playbackclient/live/LiveLanguageTransformer;)V", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public final class AudioStreamGroupImpl implements AudioStreamGroup {
    private final StreamPreferencesProvider<AudioStreamMatcher> audioStreamPreferencesProvider;
    private final List<AudioStream> audioStreams;
    private ImmutableList<AudioTrackMetadata> audioTrackMetadataList;
    private AudioStream currentAudioStream;
    private final AudioStream defaultAudioStream;
    private final LiveLanguageTransformer liveLanguageTransformer;
    private final StreamType type;
    private VideoPresentation videoPresentation;

    public AudioStreamGroupImpl(StreamPreferencesProvider<AudioStreamMatcher> audioStreamPreferencesProvider, LiveLanguageTransformer liveLanguageTransformer) {
        Intrinsics.checkNotNullParameter(audioStreamPreferencesProvider, "audioStreamPreferencesProvider");
        Intrinsics.checkNotNullParameter(liveLanguageTransformer, "liveLanguageTransformer");
        this.audioStreamPreferencesProvider = audioStreamPreferencesProvider;
        this.liveLanguageTransformer = liveLanguageTransformer;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        AudioType audioType = AudioType.Dialog;
        String str = String.format("%s_%s_%s", Arrays.copyOf(new Object[]{"default", audioType, 0}, 3));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        Locale US = Locale.US;
        Intrinsics.checkNotNullExpressionValue(US, "US");
        String lowerCase = str.toLowerCase(US);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        AudioStreamData audioStreamData = new AudioStreamData(lowerCase, "default", audioType, new AudioStreamMatcher("default", null, 2, null), null, null, null, 112, null);
        this.defaultAudioStream = audioStreamData;
        this.audioStreams = new ArrayList();
        ImmutableList<AudioTrackMetadata> immutableListOf = ImmutableList.of();
        Intrinsics.checkNotNullExpressionValue(immutableListOf, "of()");
        this.audioTrackMetadataList = immutableListOf;
        this.currentAudioStream = audioStreamData;
        this.type = StreamType.Audio;
    }

    public /* synthetic */ AudioStreamGroupImpl(StreamPreferencesProvider streamPreferencesProvider, LiveLanguageTransformer liveLanguageTransformer, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(streamPreferencesProvider, (i & 2) != 0 ? new LiveLanguageTransformer() : liveLanguageTransformer);
    }

    @Override // com.amazon.video.sdk.stream.StreamGroup
    /* JADX INFO: renamed from: getActiveStream, reason: from getter */
    public AudioStream getCurrentAudioStream() {
        return this.currentAudioStream;
    }

    private final AudioStream getCurrentAudioStream() {
        VideoPresentation videoPresentation = this.videoPresentation;
        if (videoPresentation != null) {
            Intrinsics.checkNotNull(videoPresentation);
            PlaybackExperienceController playbackExperienceController = videoPresentation.getPlayer().getPlaybackExperienceController();
            VideoPresentation videoPresentation2 = this.videoPresentation;
            Intrinsics.checkNotNull(videoPresentation2);
            try {
                if (videoPresentation2.getSpecification().isLiveStream()) {
                    DLog.logf("AudioStreamGroup: stream identified as liveStream");
                    if (playbackExperienceController.getCurrentAudioLanguage().isPresent()) {
                        for (Object obj : this.audioStreams) {
                            if (Intrinsics.areEqual(((AudioStream) obj).getId(), playbackExperienceController.getCurrentAudioLanguage().get())) {
                                return (AudioStream) obj;
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                } else if (playbackExperienceController.getAudioTrackId().isPresent() && !Intrinsics.areEqual(playbackExperienceController.getAudioTrackId().orNull(), "ALL")) {
                    DLog.logf("AudioStreamGroup: stream not identified as liveStream");
                    for (Object obj2 : this.audioStreams) {
                        if (Intrinsics.areEqual(((AudioStream) obj2).getId(), playbackExperienceController.getAudioTrackId().get())) {
                            return (AudioStream) obj2;
                        }
                    }
                    throw new NoSuchElementException("Collection contains no element matching the predicate.");
                }
            } catch (NoSuchElementException unused) {
                DLog.logf("AudioStreamGroup: No matching AudioStream found. Resuming to get default active stream");
            }
        }
        return getDefaultActiveStream(this.audioStreams, this.audioStreamPreferencesProvider.get(), getDefaultAudioTrackId());
    }

    public void onPrepared(VideoPresentation videoPresentation, VideoRenderingSettings videoRenderingSettings) {
        Intrinsics.checkNotNullParameter(videoPresentation, "videoPresentation");
        Intrinsics.checkNotNullParameter(videoRenderingSettings, "videoRenderingSettings");
        this.videoPresentation = videoPresentation;
        ImmutableList<AudioTrackMetadata> allAudioTrackMetadata = getAllAudioTrackMetadata();
        this.audioTrackMetadataList = allAudioTrackMetadata;
        this.audioStreams.addAll(MultiTrackAudioUtilsKt.generateAudioStreamsList(allAudioTrackMetadata));
        updateActiveStream();
    }

    public final void updateActiveStream() {
        this.currentAudioStream = getCurrentAudioStream();
        DLog.logf("Current audio language is %s", getCurrentAudioStream().getLanguage());
    }

    public void onTerminate(boolean isDestroyed) {
        this.videoPresentation = null;
    }

    private final Optional<String> getDefaultAudioTrackId() {
        for (AudioTrackMetadata audioTrackMetadata : this.audioTrackMetadataList) {
            if (audioTrackMetadata.getIsDefaultAudioTrack()) {
                Optional<String> optionalOf = Optional.of(audioTrackMetadata.getAudioTrackId());
                Intrinsics.checkNotNullExpressionValue(optionalOf, "of(it.audioTrackId)");
                return optionalOf;
            }
        }
        Optional<String> optionalAbsent = Optional.absent();
        Intrinsics.checkNotNullExpressionValue(optionalAbsent, "absent()");
        return optionalAbsent;
    }

    private final ImmutableList<AudioTrackMetadata> getAllAudioTrackMetadata() {
        ImmutableList<AudioTrackMetadata> immutableListOf;
        VideoPresentation videoPresentation = this.videoPresentation;
        if (videoPresentation != null) {
            Intrinsics.checkNotNull(videoPresentation);
            if (videoPresentation.getSpecification().isLiveStream()) {
                return getLiveAudioTrackMetadata();
            }
            try {
                VideoPresentation videoPresentation2 = this.videoPresentation;
                Intrinsics.checkNotNull(videoPresentation2);
                immutableListOf = videoPresentation2.getPlayer().getPlaybackExperienceController().getAudioTrackMetadataList();
            } catch (IllegalPlayerStateException unused) {
                immutableListOf = ImmutableList.of();
            }
            Intrinsics.checkNotNullExpressionValue(immutableListOf, "{\n                try {\n…          }\n            }");
            return immutableListOf;
        }
        ImmutableList<AudioTrackMetadata> immutableListOf2 = ImmutableList.of();
        Intrinsics.checkNotNullExpressionValue(immutableListOf2, "of()");
        return immutableListOf2;
    }

    private final ImmutableList<AudioTrackMetadata> getLiveAudioTrackMetadata() {
        VideoPresentation videoPresentation = this.videoPresentation;
        if (videoPresentation != null) {
            Intrinsics.checkNotNull(videoPresentation);
            PlaybackExperienceController playbackExperienceController = videoPresentation.getPlayer().getPlaybackExperienceController();
            Set<String> availableAudioLanguages = playbackExperienceController.getAvailableAudioLanguages();
            Intrinsics.checkNotNullExpressionValue(availableAudioLanguages, "playbackExperienceContro…r.availableAudioLanguages");
            DLog.logf("Available audio languages (from manifest) are %s", availableAudioLanguages);
            ImmutableList.Builder builder = ImmutableList.builder();
            for (String str : availableAudioLanguages) {
                String strTransformManifestCodeToLanguageCode = this.liveLanguageTransformer.transformManifestCodeToLanguageCode(str);
                String strTransformManifestCodeToDisplayName = this.liveLanguageTransformer.transformManifestCodeToDisplayName(str);
                boolean zAreEqual = Intrinsics.areEqual(str, playbackExperienceController.getAudioTrackId().orNull());
                if (strTransformManifestCodeToLanguageCode != null && strTransformManifestCodeToDisplayName != null) {
                    builder.add(new AudioTrackMetadata(str, "", strTransformManifestCodeToDisplayName, strTransformManifestCodeToLanguageCode, zAreEqual, "", ""));
                }
            }
            ImmutableList<AudioTrackMetadata> immutableListBuild = builder.build();
            Intrinsics.checkNotNullExpressionValue(immutableListBuild, "languageAssets.build()");
            return immutableListBuild;
        }
        ImmutableList<AudioTrackMetadata> immutableListOf = ImmutableList.of();
        Intrinsics.checkNotNullExpressionValue(immutableListOf, "of()");
        return immutableListOf;
    }

    @VisibleForTesting
    public final AudioStream getDefaultActiveStream(List<? extends AudioStream> audioStreams, List<AudioStreamMatcher> audioStreamPreferences, Optional<String> defaultAudioTrackId) {
        Intrinsics.checkNotNullParameter(audioStreams, "audioStreams");
        Intrinsics.checkNotNullParameter(audioStreamPreferences, "audioStreamPreferences");
        Intrinsics.checkNotNullParameter(defaultAudioTrackId, "defaultAudioTrackId");
        Object obj = null;
        AudioStream audioStream = null;
        for (AudioStreamMatcher audioStreamMatcher : audioStreamPreferences) {
            for (AudioStream audioStream2 : audioStreams) {
                if (Intrinsics.areEqual(audioStreamMatcher.getLanguage(), audioStream2.getLanguage())) {
                    if (audioStreamMatcher.getSubType() == audioStream2.getType()) {
                        DLog.logf("AudioStreamGroup: Client preferences are mapped completely with language: %s, subtype: %s", audioStream2.getLanguage(), audioStream2.getType());
                        return audioStream2;
                    }
                    if (audioStream == null) {
                        audioStream = audioStream2;
                    }
                }
            }
        }
        if (audioStream != null) {
            DLog.logf("AudioStreamGroup: Client preferences are mapped partially with language: %s, subtype: %s", audioStream.getLanguage(), audioStream.getType());
            return audioStream;
        }
        if (audioStreams.isEmpty()) {
            DLog.logf("AudioStreamGroup: Client preferences are not mapped. Selecting generic track with trackId: %s", this.defaultAudioStream.getId());
            return this.defaultAudioStream;
        }
        Iterator<T> it = audioStreams.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (Intrinsics.areEqual(((AudioStream) next).getId(), defaultAudioTrackId.orNull())) {
                obj = next;
                break;
            }
        }
        AudioStream audioStream3 = (AudioStream) obj;
        if (audioStream3 != null) {
            DLog.logf("AudioStreamGroup: Client preferences are not mapped. so selecting the default track with trackId: %s", audioStream3.getId());
            return audioStream3;
        }
        AudioStream audioStream4 = (AudioStream) CollectionsKt___CollectionsKt.first((List) audioStreams);
        DLog.logf("AudioStreamGroup: Client preferences are not mapped. No default track is defined for any stream. So falling back to first track in the list. TrackId: %s", audioStream4.getId());
        return audioStream4;
    }
}
