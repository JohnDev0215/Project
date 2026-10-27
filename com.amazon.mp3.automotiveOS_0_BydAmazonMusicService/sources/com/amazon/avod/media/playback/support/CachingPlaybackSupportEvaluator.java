package com.amazon.avod.media.playback.support;

import amazon.android.config.ConfigType;
import amazon.android.config.ConfigurationValue;
import com.amazon.avod.media.framework.config.MediaConfigBase;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.service.PlaybackResourceServiceConstants$HdrFormat;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.util.InitializationLatch;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CachingPlaybackSupportEvaluator implements PlaybackSupportEvaluator {
    private final CachedPlaybackSupportConfig mCachedPlaybackSupportConfig;
    private final InitializationLatch mInitializationLatch;
    private PlaybackSupportEvaluator mPlaybackSupportEvaluatorDelegate;

    public static CachingPlaybackSupportEvaluator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        public static final CachingPlaybackSupportEvaluator INSTANCE = new CachingPlaybackSupportEvaluator();

        private SingletonHolder() {
        }
    }

    public CachingPlaybackSupportEvaluator() {
        this(CachedPlaybackSupportConfig.getInstance());
    }

    CachingPlaybackSupportEvaluator(@Nonnull CachedPlaybackSupportConfig cachedPlaybackSupportConfig) {
        this.mInitializationLatch = new InitializationLatch(this);
        this.mCachedPlaybackSupportConfig = (CachedPlaybackSupportConfig) Preconditions.checkNotNull(cachedPlaybackSupportConfig, "cachedPlaybackSupportConfig");
    }

    public void initialize(@Nonnull PlaybackSupportEvaluator playbackSupportEvaluator) {
        Preconditions.checkNotNull(playbackSupportEvaluator, "playbackSupportEvaluator");
        this.mInitializationLatch.start(180L, TimeUnit.SECONDS);
        this.mPlaybackSupportEvaluatorDelegate = playbackSupportEvaluator;
        this.mCachedPlaybackSupportConfig.setPlaybackSupportInfo(playbackSupportEvaluator.getPlaybackSupportInfo(null));
        this.mCachedPlaybackSupportConfig.setIsUhdSupported(this.mPlaybackSupportEvaluatorDelegate.isUhdSupported(null));
        this.mCachedPlaybackSupportConfig.setIsHdSupported(this.mPlaybackSupportEvaluatorDelegate.isHdSupported(null));
        this.mCachedPlaybackSupportConfig.setIsHdrSupported(this.mPlaybackSupportEvaluatorDelegate.isHdrSupported(null));
        this.mCachedPlaybackSupportConfig.setIsDolbyDigitalPlusSupported(this.mPlaybackSupportEvaluatorDelegate.isDolbyDigitalPlusSupported(null));
        this.mCachedPlaybackSupportConfig.setIsHevcSupported(this.mPlaybackSupportEvaluatorDelegate.isHevcSupported(null));
        this.mCachedPlaybackSupportConfig.setDeviceVideoCodecOverride(this.mPlaybackSupportEvaluatorDelegate.getDeviceVideoCodecOverride(null));
        this.mCachedPlaybackSupportConfig.setDeviceBitrateAdaptionsOverride(this.mPlaybackSupportEvaluatorDelegate.getDeviceBitrateAdaptationsOverride(null));
        this.mCachedPlaybackSupportConfig.onCachedData();
        this.mInitializationLatch.complete();
    }

    public boolean requiresMediaSystemInitialization() {
        return !this.mCachedPlaybackSupportConfig.hasCachedData();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    @Nonnull
    public PlaybackSupportInfo getPlaybackSupportInfo(@Nullable RendererSchemeType rendererSchemeType) {
        if (this.mInitializationLatch.isInitialized()) {
            PlaybackSupportInfo playbackSupportInfo = this.mPlaybackSupportEvaluatorDelegate.getPlaybackSupportInfo(rendererSchemeType);
            if (playbackSupportInfo != this.mCachedPlaybackSupportConfig.getPlaybackSupportInfo()) {
                this.mCachedPlaybackSupportConfig.setPlaybackSupportInfo(playbackSupportInfo);
            }
            return playbackSupportInfo;
        }
        return this.mCachedPlaybackSupportConfig.getPlaybackSupportInfo();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    public boolean isUhdSupported(@Nullable RendererSchemeType rendererSchemeType) {
        if (this.mInitializationLatch.isInitialized()) {
            boolean zIsUhdSupported = this.mPlaybackSupportEvaluatorDelegate.isUhdSupported(rendererSchemeType);
            if (zIsUhdSupported != this.mCachedPlaybackSupportConfig.isUhdSupported()) {
                this.mCachedPlaybackSupportConfig.setIsUhdSupported(zIsUhdSupported);
            }
            return zIsUhdSupported;
        }
        return this.mCachedPlaybackSupportConfig.isUhdSupported();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    public boolean isHdSupported(@Nullable RendererSchemeType rendererSchemeType) {
        if (this.mInitializationLatch.isInitialized()) {
            boolean zIsHdSupported = this.mPlaybackSupportEvaluatorDelegate.isHdSupported(rendererSchemeType);
            if (zIsHdSupported != this.mCachedPlaybackSupportConfig.isHdSupported()) {
                this.mCachedPlaybackSupportConfig.setIsHdSupported(zIsHdSupported);
            }
            return zIsHdSupported;
        }
        return this.mCachedPlaybackSupportConfig.isHdSupported();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    public boolean isHdrSupported(@Nullable RendererSchemeType rendererSchemeType) {
        if (this.mInitializationLatch.isInitialized()) {
            boolean zIsHdrSupported = this.mPlaybackSupportEvaluatorDelegate.isHdrSupported(rendererSchemeType);
            if (zIsHdrSupported != this.mCachedPlaybackSupportConfig.isHdrSupported()) {
                this.mCachedPlaybackSupportConfig.setIsHdrSupported(zIsHdrSupported);
            }
            return zIsHdrSupported;
        }
        return this.mCachedPlaybackSupportConfig.isHdrSupported();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    public boolean isDolbyDigitalPlusSupported(@Nullable RendererSchemeType rendererSchemeType) {
        if (this.mInitializationLatch.isInitialized()) {
            boolean zIsDolbyDigitalPlusSupported = this.mPlaybackSupportEvaluatorDelegate.isDolbyDigitalPlusSupported(rendererSchemeType);
            if (zIsDolbyDigitalPlusSupported != this.mCachedPlaybackSupportConfig.isDolbyDigitalPlusSupported()) {
                this.mCachedPlaybackSupportConfig.setIsDolbyDigitalPlusSupported(zIsDolbyDigitalPlusSupported);
            }
            return zIsDolbyDigitalPlusSupported;
        }
        return this.mCachedPlaybackSupportConfig.isDolbyDigitalPlusSupported();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    public boolean isHevcSupported(@Nullable RendererSchemeType rendererSchemeType) {
        if (this.mInitializationLatch.isInitialized()) {
            boolean zIsHevcSupported = this.mPlaybackSupportEvaluatorDelegate.isHevcSupported(rendererSchemeType);
            if (zIsHevcSupported != this.mCachedPlaybackSupportConfig.isHevcSupported()) {
                this.mCachedPlaybackSupportConfig.setIsHevcSupported(zIsHevcSupported);
            }
            return zIsHevcSupported;
        }
        return this.mCachedPlaybackSupportConfig.isHevcSupported();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    public boolean isAv1Supported(@Nullable RendererSchemeType rendererSchemeType) {
        if (this.mInitializationLatch.isInitialized()) {
            boolean zIsAv1Supported = this.mPlaybackSupportEvaluatorDelegate.isAv1Supported(rendererSchemeType);
            if (zIsAv1Supported != this.mCachedPlaybackSupportConfig.isAv1Supported()) {
                this.mCachedPlaybackSupportConfig.setIsAv1Supported(zIsAv1Supported);
            }
            return zIsAv1Supported;
        }
        return this.mCachedPlaybackSupportConfig.isAv1Supported();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    @Nonnull
    public List<String> getDeviceVideoCodecOverride(@Nullable RendererSchemeType rendererSchemeType) {
        if (this.mInitializationLatch.isInitialized()) {
            List<String> deviceVideoCodecOverride = this.mPlaybackSupportEvaluatorDelegate.getDeviceVideoCodecOverride(rendererSchemeType);
            if (!Objects.equal(deviceVideoCodecOverride, this.mCachedPlaybackSupportConfig.getDeviceVideoCodecOverride())) {
                this.mCachedPlaybackSupportConfig.setDeviceVideoCodecOverride(deviceVideoCodecOverride);
            }
            return deviceVideoCodecOverride;
        }
        return this.mCachedPlaybackSupportConfig.getDeviceVideoCodecOverride();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    @Nonnull
    public List<String> getDeviceBitrateAdaptationsOverride(@Nullable RendererSchemeType rendererSchemeType) {
        if (this.mInitializationLatch.isInitialized()) {
            List<String> deviceBitrateAdaptationsOverride = this.mPlaybackSupportEvaluatorDelegate.getDeviceBitrateAdaptationsOverride(rendererSchemeType);
            if (!Objects.equal(deviceBitrateAdaptationsOverride, this.mCachedPlaybackSupportConfig.getDeviceBitrateAdaptationsOverride())) {
                this.mCachedPlaybackSupportConfig.setDeviceBitrateAdaptionsOverride(deviceBitrateAdaptationsOverride);
            }
            return deviceBitrateAdaptationsOverride;
        }
        return this.mCachedPlaybackSupportConfig.getDeviceBitrateAdaptationsOverride();
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    public boolean isMultiKeyDecryptionSupported(@Nullable RendererSchemeType rendererSchemeType, @Nonnull ContentType contentType) {
        this.mInitializationLatch.waitOnInitializationUninterruptibly();
        return this.mPlaybackSupportEvaluatorDelegate.isMultiKeyDecryptionSupported(rendererSchemeType, contentType);
    }

    @Override // com.amazon.avod.media.playback.support.PlaybackSupportEvaluator
    @Nonnull
    public ImmutableList<PlaybackResourceServiceConstants$HdrFormat> getSupportedHdrFormats(@Nullable RendererSchemeType rendererSchemeType) {
        if (this.mInitializationLatch.isInitialized()) {
            ImmutableList<PlaybackResourceServiceConstants$HdrFormat> supportedHdrFormats = this.mPlaybackSupportEvaluatorDelegate.getSupportedHdrFormats(rendererSchemeType);
            if (!Objects.equal(supportedHdrFormats, this.mCachedPlaybackSupportConfig.getSupportedHdrFormats())) {
                this.mCachedPlaybackSupportConfig.setDeviceSupportedHdrFormats(supportedHdrFormats);
            }
            return supportedHdrFormats;
        }
        return this.mCachedPlaybackSupportConfig.getSupportedHdrFormats();
    }

    static class CachedPlaybackSupportConfig extends MediaConfigBase {
        private final ConfigurationValue<List<String>> mDeviceBitrateAdaptationsOverride;
        private final ConfigurationValue<List<String>> mDeviceSupportedHdrFormats;
        private final ConfigurationValue<List<String>> mDeviceVideoCodecOverride;
        private final ConfigurationValue<Boolean> mHasCachedData;
        private final ConfigurationValue<Boolean> mIsAv1Supported;
        private final ConfigurationValue<Boolean> mIsDolbyDigitalPlusSupported;
        private final ConfigurationValue<Boolean> mIsDolbyDigitalPlusWithAtmosSupported;
        private final ConfigurationValue<Boolean> mIsHdSupported;
        private final ConfigurationValue<Boolean> mIsHdrSupported;
        private final ConfigurationValue<Boolean> mIsHevcSupported;
        private final ConfigurationValue<Boolean> mIsUhdSupported;
        private final ConfigurationValue<PlaybackSupportInfo> mPlaybackSupportInfo;

        public static CachedPlaybackSupportConfig getInstance() {
            return SingletonHolder.INSTANCE;
        }

        private static final class SingletonHolder {
            public static final CachedPlaybackSupportConfig INSTANCE = new CachedPlaybackSupportConfig();

            private SingletonHolder() {
            }
        }

        private CachedPlaybackSupportConfig() {
            ConfigType configType = ConfigType.PERSISTENT;
            this.mHasCachedData = newBooleanConfigValue("isPlaybackSupportCached", false, configType);
            this.mPlaybackSupportInfo = newEnumConfigValue("playbackSupportInfo_Cache", null, PlaybackSupportInfo.class, configType);
            this.mIsUhdSupported = newBooleanConfigValue("isUhdSupported_Cache", false, configType);
            this.mIsHdSupported = newBooleanConfigValue("isHdSupported_Cache", false, configType);
            this.mIsHdrSupported = newBooleanConfigValue("isHdrSupported_Cache", false, configType);
            this.mIsDolbyDigitalPlusSupported = newBooleanConfigValue("isDolyDigitalPlusSupported_Cache", false, configType);
            this.mIsDolbyDigitalPlusWithAtmosSupported = newBooleanConfigValue("isDolyDigitalPlusWithAtmosSupported_Cache", false, configType);
            this.mIsHevcSupported = newBooleanConfigValue("isHevcSupported_Cache", false, configType);
            this.mIsAv1Supported = newBooleanConfigValue("isAv1Supported_Cache", false, configType);
            this.mDeviceVideoCodecOverride = newStringListConfigValue("deviceVideoCodecOverride_Cache", "", ";", configType);
            this.mDeviceBitrateAdaptationsOverride = newStringListConfigValue("deviceBitrateAdaptationsOverride_Cache", "", ";", configType);
            this.mDeviceSupportedHdrFormats = newStringListConfigValue("deviceSupportedHdrFormats_Cache", PlaybackResourceServiceConstants$HdrFormat.None.name(), ";", configType);
        }

        public boolean hasCachedData() {
            return this.mHasCachedData.getValue().booleanValue();
        }

        public void onCachedData() {
            this.mHasCachedData.updateValue(Boolean.TRUE);
        }

        public PlaybackSupportInfo getPlaybackSupportInfo() {
            return this.mPlaybackSupportInfo.getValue();
        }

        public void setPlaybackSupportInfo(PlaybackSupportInfo playbackSupportInfo) {
            this.mPlaybackSupportInfo.updateValue(playbackSupportInfo);
        }

        public boolean isUhdSupported() {
            return this.mIsUhdSupported.getValue().booleanValue();
        }

        public void setIsUhdSupported(boolean z) {
            this.mIsUhdSupported.updateValue(Boolean.valueOf(z));
        }

        public boolean isHdSupported() {
            return this.mIsHdSupported.getValue().booleanValue();
        }

        public void setIsHdSupported(boolean z) {
            this.mIsHdSupported.updateValue(Boolean.valueOf(z));
        }

        public boolean isHdrSupported() {
            return this.mIsHdrSupported.getValue().booleanValue();
        }

        public void setIsHdrSupported(boolean z) {
            this.mIsHdrSupported.updateValue(Boolean.valueOf(z));
        }

        public boolean isDolbyDigitalPlusSupported() {
            return this.mIsDolbyDigitalPlusSupported.getValue().booleanValue();
        }

        public void setIsDolbyDigitalPlusSupported(boolean z) {
            this.mIsDolbyDigitalPlusSupported.updateValue(Boolean.valueOf(z));
        }

        public boolean isDolbyDigitalPlusWithAtmosSupported() {
            return this.mIsDolbyDigitalPlusWithAtmosSupported.getValue().booleanValue();
        }

        public void setIsDolbyDigitalPlusWithAtmosSupported(boolean z) {
            this.mIsDolbyDigitalPlusWithAtmosSupported.updateValue(Boolean.valueOf(z));
        }

        public boolean isHevcSupported() {
            return this.mIsHevcSupported.getValue().booleanValue();
        }

        public void setIsHevcSupported(boolean z) {
            this.mIsHevcSupported.updateValue(Boolean.valueOf(z));
        }

        public boolean isAv1Supported() {
            return this.mIsAv1Supported.getValue().booleanValue();
        }

        public void setIsAv1Supported(boolean z) {
            this.mIsAv1Supported.updateValue(Boolean.valueOf(z));
        }

        public List<String> getDeviceVideoCodecOverride() {
            return this.mDeviceVideoCodecOverride.getValue();
        }

        public void setDeviceVideoCodecOverride(List<String> list) {
            this.mDeviceVideoCodecOverride.updateValue(list);
        }

        public List<String> getDeviceBitrateAdaptationsOverride() {
            return this.mDeviceBitrateAdaptationsOverride.getValue();
        }

        public void setDeviceBitrateAdaptionsOverride(List<String> list) {
            this.mDeviceBitrateAdaptationsOverride.updateValue(list);
        }

        public void setDeviceSupportedHdrFormats(ImmutableList<PlaybackResourceServiceConstants$HdrFormat> immutableList) {
            ArrayList arrayListNewArrayList = Lists.newArrayList();
            UnmodifiableIterator<PlaybackResourceServiceConstants$HdrFormat> it = immutableList.iterator();
            while (it.hasNext()) {
                arrayListNewArrayList.add(it.next().name());
            }
            this.mDeviceSupportedHdrFormats.updateValue(arrayListNewArrayList);
        }

        public ImmutableList<PlaybackResourceServiceConstants$HdrFormat> getSupportedHdrFormats() {
            ImmutableList.Builder builder = ImmutableList.builder();
            Iterator<String> it = this.mDeviceSupportedHdrFormats.getValue().iterator();
            while (it.hasNext()) {
                builder.add(Enum.valueOf(PlaybackResourceServiceConstants$HdrFormat.class, it.next()));
            }
            return builder.build();
        }
    }
}
