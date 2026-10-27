package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionConfiguration;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration;
import com.amazon.avod.content.smoothstream.quality.timeout.FragmentTimeoutSelectorFactory;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.playback.internal.config.MediaQualityConfig;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class BitrateSelectorFactory {
    private final FragmentTimeoutSelectorFactory mFragmentTimeoutSelectorFactory;
    private final SmoothStreamingHeuristicConfig mHeuristicConfig;
    private final DefaultQualityConfiguration mQualityConfig;
    private final StreamingBitrateSelectionComponentFactory mStreamingComponentsFactory;

    public BitrateSelectorFactory(DefaultQualityConfiguration defaultQualityConfiguration, ContentSessionConfiguration contentSessionConfiguration, SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, NetworkHistoryManager networkHistoryManager, MediaQualityConfig mediaQualityConfig, SmoothStreamingContentStore smoothStreamingContentStore, DeviceCapability deviceCapability, @Nullable HeuristicsPlaybackConfig heuristicsPlaybackConfig) {
        this(defaultQualityConfiguration, smoothStreamingHeuristicConfig, new FragmentTimeoutSelectorFactory(smoothStreamingPlaybackConfig), new ConfigurableStreamingBitrateSelectionComponentFactory(defaultQualityConfiguration, smoothStreamingHeuristicConfig, smoothStreamingPlaybackConfig, networkHistoryManager, mediaQualityConfig, smoothStreamingContentStore, deviceCapability, contentSessionConfiguration, heuristicsPlaybackConfig));
    }

    BitrateSelectorFactory(DefaultQualityConfiguration defaultQualityConfiguration, SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig, FragmentTimeoutSelectorFactory fragmentTimeoutSelectorFactory, StreamingBitrateSelectionComponentFactory streamingBitrateSelectionComponentFactory) {
        this.mQualityConfig = defaultQualityConfiguration;
        this.mHeuristicConfig = smoothStreamingHeuristicConfig;
        this.mFragmentTimeoutSelectorFactory = fragmentTimeoutSelectorFactory;
        this.mStreamingComponentsFactory = streamingBitrateSelectionComponentFactory;
    }

    public StreamingBitrateSelector newBitrateSelector(ContentSessionContext contentSessionContext, StreamIndex streamIndex) {
        ComponentizedStreamingBitrateSelector componentizedStreamingBitrateSelector = new ComponentizedStreamingBitrateSelector(this.mQualityConfig, this.mHeuristicConfig, SmoothStreamingPlaybackConfig.INSTANCE);
        componentizedStreamingBitrateSelector.initialize(contentSessionContext, streamIndex, newComponentSet(contentSessionContext, streamIndex, false), this.mFragmentTimeoutSelectorFactory.newTimeoutSelector(contentSessionContext, streamIndex));
        return componentizedStreamingBitrateSelector;
    }

    public StreamingBitrateSelectionComponentSet newBitrateSelectionComponentSet(ContentSessionContext contentSessionContext, StreamIndex streamIndex, boolean z) {
        return newComponentSet(contentSessionContext, streamIndex, z);
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.smoothstream.quality.BitrateSelectorFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentSessionType;

        static {
            int[] iArr = new int[ContentSessionType.values().length];
            $SwitchMap$com$amazon$avod$content$ContentSessionType = iArr;
            try {
                iArr[ContentSessionType.LIVE_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING_CONTINUOUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.CONTENT_CACHE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.DOWNLOAD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.PROGRESSIVE_PLAYBACK.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private StreamingBitrateSelectionComponentSet newComponentSet(ContentSessionContext contentSessionContext, StreamIndex streamIndex, boolean z) {
        StreamingBitrateSelectionComponentSet streamingBitrateSelectionComponentSetNewAudioComponentSet;
        switch (AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentSessionType[contentSessionContext.getSessionType().ordinal()]) {
            case 1:
            case 2:
            case 3:
                if (streamIndex.isVideo()) {
                    streamingBitrateSelectionComponentSetNewAudioComponentSet = this.mStreamingComponentsFactory.newVideoComponentSet(z);
                } else if (streamIndex.isAudio()) {
                    streamingBitrateSelectionComponentSetNewAudioComponentSet = this.mStreamingComponentsFactory.newAudioComponentSet(z);
                } else {
                    streamingBitrateSelectionComponentSetNewAudioComponentSet = StreamingBitrateSelectionComponentSet.EMPTY_SET;
                }
                break;
            case 4:
            case 5:
            case 6:
                if (streamIndex.isVideo()) {
                    streamingBitrateSelectionComponentSetNewAudioComponentSet = this.mStreamingComponentsFactory.newDownloadVideoComponentSet(z);
                } else if (streamIndex.isAudio()) {
                    streamingBitrateSelectionComponentSetNewAudioComponentSet = this.mStreamingComponentsFactory.newDownloadAudioComponentSet(z);
                } else {
                    streamingBitrateSelectionComponentSetNewAudioComponentSet = StreamingBitrateSelectionComponentSet.EMPTY_SET;
                }
                break;
            default:
                throw new IllegalStateException(String.format(Locale.US, "Unsupported session type %s for bitrate selection", contentSessionContext.getSessionType()));
        }
        Preconditions.checkNotNull(streamingBitrateSelectionComponentSetNewAudioComponentSet, "smoothStreamingComponentSet should not be null here, please check assignment logic above.");
        streamingBitrateSelectionComponentSetNewAudioComponentSet.initialize(contentSessionContext);
        return streamingBitrateSelectionComponentSetNewAudioComponentSet;
    }
}
