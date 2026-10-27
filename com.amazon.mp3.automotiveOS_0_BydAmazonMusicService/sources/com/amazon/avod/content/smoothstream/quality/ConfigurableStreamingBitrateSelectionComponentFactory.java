package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionConfiguration;
import com.amazon.avod.content.config.LiveStreamingPlaybackConfig;
import com.amazon.avod.content.config.SmoothStreamingHeuristicConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig;
import com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration;
import com.amazon.avod.content.smoothstream.quality.override.HighestQualityPicker;
import com.amazon.avod.content.smoothstream.quality.override.LowestHighestQualityPicker;
import com.amazon.avod.content.smoothstream.quality.override.LowestQualityPicker;
import com.amazon.avod.content.smoothstream.quality.override.OscillatingAudioQualityPicker;
import com.amazon.avod.content.smoothstream.quality.override.OscillatingQualityPicker;
import com.amazon.avod.content.smoothstream.quality.override.RandomQualityPicker;
import com.amazon.avod.content.smoothstream.quality.override.UserControlledQualityPicker;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.playback.internal.config.MediaQualityConfig;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ConfigurableStreamingBitrateSelectionComponentFactory implements StreamingBitrateSelectionComponentFactory {
    private final QualityLevelClamperFactory mClamperFactory;
    private final SmoothStreamingContentStore mContentStore;
    private final DeviceCapability mDeviceCapability;
    private final SmoothStreamingHeuristicConfig mHeuristicConfig;
    private final HeuristicsPlaybackConfig mHeuristicsPlaybackConfig;
    private final HighFrameRateQualityHolderInterface mHighFrameRateQualityHolder;
    private final MediaQualityConfig mMediaQualityConfig;
    private final NetworkHistoryManager mNetworkHistoryManager;
    private final SmoothStreamingPlaybackConfig mPlaybackConfig;
    private final DefaultQualityConfiguration mQualityConfig;
    private final ContentSessionConfiguration mSessionConfig;

    public ConfigurableStreamingBitrateSelectionComponentFactory(DefaultQualityConfiguration defaultQualityConfiguration, SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, NetworkHistoryManager networkHistoryManager, MediaQualityConfig mediaQualityConfig, SmoothStreamingContentStore smoothStreamingContentStore, DeviceCapability deviceCapability, ContentSessionConfiguration contentSessionConfiguration, @Nullable HeuristicsPlaybackConfig heuristicsPlaybackConfig) {
        this(defaultQualityConfiguration, smoothStreamingHeuristicConfig, smoothStreamingPlaybackConfig, networkHistoryManager, mediaQualityConfig, new QualityLevelClamperFactory(contentSessionConfiguration), smoothStreamingContentStore, deviceCapability, contentSessionConfiguration, HighFrameRateQualityHolder.INSTANCE, heuristicsPlaybackConfig);
    }

    ConfigurableStreamingBitrateSelectionComponentFactory(@Nonnull DefaultQualityConfiguration defaultQualityConfiguration, @Nonnull SmoothStreamingHeuristicConfig smoothStreamingHeuristicConfig, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nonnull NetworkHistoryManager networkHistoryManager, @Nonnull MediaQualityConfig mediaQualityConfig, @Nonnull QualityLevelClamperFactory qualityLevelClamperFactory, @Nonnull SmoothStreamingContentStore smoothStreamingContentStore, @Nonnull DeviceCapability deviceCapability, @Nonnull ContentSessionConfiguration contentSessionConfiguration, @Nonnull HighFrameRateQualityHolderInterface highFrameRateQualityHolderInterface, @Nullable HeuristicsPlaybackConfig heuristicsPlaybackConfig) {
        this.mQualityConfig = (DefaultQualityConfiguration) Preconditions.checkNotNull(defaultQualityConfiguration, "qualityConfig");
        this.mHeuristicConfig = (SmoothStreamingHeuristicConfig) Preconditions.checkNotNull(smoothStreamingHeuristicConfig, "heuristicConfig");
        this.mPlaybackConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "playbackConfig");
        this.mNetworkHistoryManager = (NetworkHistoryManager) Preconditions.checkNotNull(networkHistoryManager, "networkHistoryManager");
        this.mMediaQualityConfig = (MediaQualityConfig) Preconditions.checkNotNull(mediaQualityConfig, "mediaQualityConfig");
        this.mClamperFactory = (QualityLevelClamperFactory) Preconditions.checkNotNull(qualityLevelClamperFactory, "clamperFactory");
        this.mContentStore = (SmoothStreamingContentStore) Preconditions.checkNotNull(smoothStreamingContentStore, "contentStore");
        this.mDeviceCapability = (DeviceCapability) Preconditions.checkNotNull(deviceCapability, "deviceCapability");
        this.mSessionConfig = (ContentSessionConfiguration) Preconditions.checkNotNull(contentSessionConfiguration, "sessionConfiguration");
        this.mHighFrameRateQualityHolder = (HighFrameRateQualityHolderInterface) Preconditions.checkNotNull(highFrameRateQualityHolderInterface, "highFrameRateQualityHolder");
        this.mHeuristicsPlaybackConfig = heuristicsPlaybackConfig;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponentFactory
    public StreamingBitrateSelectionComponentSet newVideoComponentSet(boolean z) {
        List<StreamingBitrateSelectionComponent> defaultDashVideoComponents;
        Optional<ContentSessionConfiguration.ABRMode> overrideVideoAbrMode = this.mSessionConfig.getOverrideVideoAbrMode();
        ContentSessionConfiguration.ABRMode aBRMode = ContentSessionConfiguration.ABRMode.HEURISTIC;
        ContentSessionConfiguration.ABRMode aBRModeOr = overrideVideoAbrMode.or(aBRMode);
        if (aBRModeOr != aBRMode) {
            defaultDashVideoComponents = getOverrideVideoComponents(aBRModeOr);
        } else {
            defaultDashVideoComponents = z ? getDefaultDashVideoComponents() : getDefaultVideoComponents();
        }
        return newComponentSet(defaultDashVideoComponents);
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponentFactory
    public StreamingBitrateSelectionComponentSet newAudioComponentSet(boolean z) {
        return newComponentSet(z ? getDefaultDashAudioComponents() : getDefaultAudioComponents());
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponentFactory
    public StreamingBitrateSelectionComponentSet newDownloadVideoComponentSet(boolean z) {
        List<StreamingBitrateSelectionComponent> defaultDashDownloadVideoComponents;
        Optional<ContentSessionConfiguration.ABRMode> overrideVideoAbrMode = this.mSessionConfig.getOverrideVideoAbrMode();
        ContentSessionConfiguration.ABRMode aBRMode = ContentSessionConfiguration.ABRMode.HEURISTIC;
        ContentSessionConfiguration.ABRMode aBRModeOr = overrideVideoAbrMode.or(aBRMode);
        if (aBRModeOr != aBRMode) {
            defaultDashDownloadVideoComponents = getOverrideVideoComponents(aBRModeOr);
        } else {
            defaultDashDownloadVideoComponents = z ? getDefaultDashDownloadVideoComponents() : getDefaultDownloadVideoComponents();
        }
        return newComponentSet(defaultDashDownloadVideoComponents);
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponentFactory
    public StreamingBitrateSelectionComponentSet newDownloadAudioComponentSet(boolean z) {
        return newComponentSet(z ? getDefaultDashDownloadAudioComponents() : getDefaultDownloadAudioComponents());
    }

    private StreamingBitrateSelectionComponentSet newComponentSet(List<StreamingBitrateSelectionComponent> list) {
        Iterator<StreamingBitrateSelectionComponent> it = list.iterator();
        while (it.hasNext()) {
            String simpleName = it.next().getClass().getSimpleName();
            if (this.mHeuristicConfig.getBlacklistedComponentClassNames().contains(simpleName)) {
                DLog.logf("Not using bitrate selection component of class %s because is it blacklisted. Blacklist: %s", simpleName, this.mHeuristicConfig.getBlacklistedComponentClassNames());
                it.remove();
            }
        }
        return new StreamingBitrateSelectionComponentSet(list);
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.smoothstream.quality.ConfigurableStreamingBitrateSelectionComponentFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentSessionConfiguration$ABRMode;

        static {
            int[] iArr = new int[ContentSessionConfiguration.ABRMode.values().length];
            $SwitchMap$com$amazon$avod$content$ContentSessionConfiguration$ABRMode = iArr;
            try {
                iArr[ContentSessionConfiguration.ABRMode.LOWEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionConfiguration$ABRMode[ContentSessionConfiguration.ABRMode.HIGHEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionConfiguration$ABRMode[ContentSessionConfiguration.ABRMode.LOWESTHIGHEST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionConfiguration$ABRMode[ContentSessionConfiguration.ABRMode.RANDOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionConfiguration$ABRMode[ContentSessionConfiguration.ABRMode.ZIGZAG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionConfiguration$ABRMode[ContentSessionConfiguration.ABRMode.USER_SELECTED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionConfiguration$ABRMode[ContentSessionConfiguration.ABRMode.LEGACY_FIXED_AUDIO_BIRATE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private List<StreamingBitrateSelectionComponent> getOverrideVideoComponents(ContentSessionConfiguration.ABRMode aBRMode) {
        Object lowestQualityPicker;
        switch (AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentSessionConfiguration$ABRMode[aBRMode.ordinal()]) {
            case 1:
                lowestQualityPicker = new LowestQualityPicker(this.mClamperFactory);
                break;
            case 2:
                lowestQualityPicker = new HighestQualityPicker(this.mClamperFactory);
                break;
            case 3:
                lowestQualityPicker = new LowestHighestQualityPicker(this.mClamperFactory, this.mSessionConfig.getOverrideVideoAbrInterval());
                break;
            case 4:
                lowestQualityPicker = new RandomQualityPicker(this.mClamperFactory, this.mSessionConfig.getOverrideVideoAbrInterval());
                break;
            case 5:
                lowestQualityPicker = new OscillatingQualityPicker(this.mClamperFactory, this.mSessionConfig.getOverrideVideoAbrInterval());
                break;
            case 6:
                lowestQualityPicker = new UserControlledQualityPicker(this.mClamperFactory, this.mSessionConfig.getUserQualityLevelSelector().get());
                break;
            default:
                throw new IllegalStateException("Unrecognized ABR Mode: " + aBRMode);
        }
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(1);
        arrayListNewArrayListWithCapacity.add(lowestQualityPicker);
        return arrayListNewArrayListWithCapacity;
    }

    private List<StreamingBitrateSelectionComponent> getDefaultAudioComponents() {
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(1);
        arrayListNewArrayListWithCapacity.add(new FixedBitrateSelectionComponent(this.mQualityConfig));
        return arrayListNewArrayListWithCapacity;
    }

    private List<StreamingBitrateSelectionComponent> getDefaultDashAudioComponents() {
        ContentSessionConfiguration.ABRMode aBRModeOr = this.mSessionConfig.getOverrideAudioAbrMode().or(ContentSessionConfiguration.ABRMode.LEGACY_FIXED_AUDIO_BIRATE);
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentSessionConfiguration$ABRMode[aBRModeOr.ordinal()];
        if (i == 5) {
            arrayListNewArrayList.add(new OscillatingAudioQualityPicker());
            return arrayListNewArrayList;
        }
        if (i == 7) {
            return getDefaultAudioComponents();
        }
        throw new IllegalStateException("Unrecognized Audio ABR Mode: " + aBRModeOr);
    }

    private List<StreamingBitrateSelectionComponent> getDefaultVideoComponents() {
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(14);
        arrayListNewArrayListWithCapacity.add(new DownshiftBitrateSelectionComponent(this.mHeuristicConfig));
        arrayListNewArrayListWithCapacity.add(new LowBufferBitrateSelectionComponent(this.mHeuristicConfig, this.mPlaybackConfig, this.mNetworkHistoryManager));
        arrayListNewArrayListWithCapacity.add(new PanicModeBitrateSelectionComponent(this.mHeuristicConfig));
        arrayListNewArrayListWithCapacity.add(new StabilizingBitrateSelectionComponent(this.mHeuristicConfig));
        arrayListNewArrayListWithCapacity.add(new ControlledHDBitrateSelectionComponent(this.mHeuristicConfig));
        arrayListNewArrayListWithCapacity.add(new RetryBitrateSelectionComponent(this.mHeuristicConfig));
        arrayListNewArrayListWithCapacity.add(new MinimalizingBitrateSelectionComponent(this.mHeuristicConfig));
        arrayListNewArrayListWithCapacity.add(new CappedQualityBitrateSelectionComponent(this.mMediaQualityConfig));
        arrayListNewArrayListWithCapacity.add(new CappedResolutionBandBitrateSelectionComponent(this.mMediaQualityConfig));
        arrayListNewArrayListWithCapacity.add(new RuntimeDeviceCapabilityBitrateSelectionComponent(this.mClamperFactory, this.mDeviceCapability));
        arrayListNewArrayListWithCapacity.add(new DeviceCapabilityBitrateSelectionComponent(this.mClamperFactory));
        arrayListNewArrayListWithCapacity.add(new PlaybackPerformanceBitrateSelectionComponent());
        arrayListNewArrayListWithCapacity.add(new HighFrameRateCappedQualitySelectionComponent(this.mHighFrameRateQualityHolder));
        arrayListNewArrayListWithCapacity.add(new MidstreamSwitchingBitrateSelectionComponent(this.mClamperFactory));
        return arrayListNewArrayListWithCapacity;
    }

    private List<StreamingBitrateSelectionComponent> getDefaultDashVideoComponents() {
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(9);
        arrayListNewArrayListWithCapacity.add(new CappedQualityBitrateSelectionComponent(this.mMediaQualityConfig));
        arrayListNewArrayListWithCapacity.add(new CappedResolutionBandBitrateSelectionComponent(this.mMediaQualityConfig));
        arrayListNewArrayListWithCapacity.add(new LiveStreamingCappedQualityBitrateSelectionComponent(this.mMediaQualityConfig, LiveStreamingPlaybackConfig.getInstance()));
        arrayListNewArrayListWithCapacity.add(new RuntimeDeviceCapabilityBitrateSelectionComponent(this.mClamperFactory, this.mDeviceCapability));
        arrayListNewArrayListWithCapacity.add(new DeviceCapabilityBitrateSelectionComponent(this.mClamperFactory));
        arrayListNewArrayListWithCapacity.add(new PlaybackPerformanceBitrateSelectionComponent());
        arrayListNewArrayListWithCapacity.add(new HighFrameRateCappedQualitySelectionComponent(this.mHighFrameRateQualityHolder));
        arrayListNewArrayListWithCapacity.add(new QualityScoreBitrateSelectionComponent(this.mHeuristicsPlaybackConfig));
        arrayListNewArrayListWithCapacity.add(new MidstreamSwitchingBitrateSelectionComponent(this.mClamperFactory));
        return arrayListNewArrayListWithCapacity;
    }

    private List<StreamingBitrateSelectionComponent> getDefaultDownloadAudioComponents() {
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(2);
        arrayListNewArrayListWithCapacity.add(new FixedBitrateSelectionComponent(this.mQualityConfig));
        arrayListNewArrayListWithCapacity.add(new PreviousSessionBitrateSelectionComponent(this.mContentStore));
        return arrayListNewArrayListWithCapacity;
    }

    private List<StreamingBitrateSelectionComponent> getDefaultDashDownloadAudioComponents() {
        return getDefaultDownloadAudioComponents();
    }

    private List<StreamingBitrateSelectionComponent> getDefaultDownloadVideoComponents() {
        ArrayList arrayListNewArrayListWithCapacity = Lists.newArrayListWithCapacity(4);
        arrayListNewArrayListWithCapacity.add(new FixedBitrateSelectionComponent(this.mQualityConfig));
        arrayListNewArrayListWithCapacity.add(new CappedResolutionBandBitrateSelectionComponent(this.mMediaQualityConfig));
        arrayListNewArrayListWithCapacity.add(new PreviousSessionBitrateSelectionComponent(this.mContentStore));
        arrayListNewArrayListWithCapacity.add(new RuntimeDeviceCapabilityBitrateSelectionComponent(this.mClamperFactory, this.mDeviceCapability));
        return arrayListNewArrayListWithCapacity;
    }

    private List<StreamingBitrateSelectionComponent> getDefaultDashDownloadVideoComponents() {
        return getDefaultDownloadVideoComponents();
    }
}
