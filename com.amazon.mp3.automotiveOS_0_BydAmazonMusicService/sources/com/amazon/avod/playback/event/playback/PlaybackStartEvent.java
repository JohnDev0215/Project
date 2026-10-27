package com.amazon.avod.playback.event.playback;

import com.amazon.avod.content.smoothstream.StreamSelections;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.capability.DeviceCapability;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.amazon.avod.pmet.ContentStoreType;
import com.amazon.avod.qos.model.internal.AudioAdaptationSetSwitchContext;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackStartEvent extends PlaybackEvent {
    private final AudioAdaptationSetSwitchContext mAudioAdaptationSetSwitchContext;
    private final String mAudioTrackId;
    private final ContentStoreType mContentStoreType;
    private final DeviceCapability mDeviceCapability;
    private final String mDrmAssetId;
    private final String mDrmScheme;
    private final String mHeuristicsSettingsId;
    private final boolean mIsHdr;
    private final boolean mIsLiveStream;
    private final String mLanguageCode;
    private final String mRendererInfo;
    private final String mRendererScheme;
    private final int mRendererStatusFlag;

    @Nonnull
    private final StreamSelections mStreamSelections;

    public PlaybackStartEvent(@Nonnull TimeSpan timeSpan, @Nullable DeviceCapability deviceCapability, @Nullable String str, int i, @Nonnull String str2, @Nonnull String str3, @Nonnull String str4, @Nonnull String str5, @Nonnull AudioAdaptationSetSwitchContext audioAdaptationSetSwitchContext, @Nonnull StreamSelections streamSelections, boolean z, @Nullable String str6, @Nullable String str7, @Nullable String str8, boolean z2, @Nonnull ContentStoreType contentStoreType) {
        super(timeSpan, str6);
        this.mDrmAssetId = str;
        this.mDeviceCapability = deviceCapability;
        this.mRendererStatusFlag = i;
        this.mRendererScheme = (String) Preconditions.checkNotNull(str2, "rendererScheme");
        this.mDrmScheme = (String) Preconditions.checkNotNull(str3, "drmScheme");
        this.mRendererInfo = (String) Preconditions.checkNotNull(str4, "rendererInfo");
        this.mHeuristicsSettingsId = (String) Preconditions.checkNotNull(str5, "heuristicsSettingsId");
        this.mAudioAdaptationSetSwitchContext = (AudioAdaptationSetSwitchContext) Preconditions.checkNotNull(audioAdaptationSetSwitchContext, "audioAdaptationSetSwitchContext");
        this.mStreamSelections = (StreamSelections) Preconditions.checkNotNull(streamSelections, "streamSelections");
        this.mIsHdr = z;
        this.mAudioTrackId = str7;
        this.mLanguageCode = str8;
        this.mIsLiveStream = z2;
        this.mContentStoreType = (ContentStoreType) Preconditions.checkNotNull(contentStoreType, "contentStoreType");
    }

    @Nullable
    public DeviceCapability getDeviceCapability() {
        return this.mDeviceCapability;
    }

    public int getRendererStatusFlags() {
        return this.mRendererStatusFlag;
    }

    @Nonnull
    public String getRendererScheme() {
        return this.mRendererScheme;
    }

    @Nonnull
    public String getDrmScheme() {
        return this.mDrmScheme;
    }

    @Nonnull
    public String getRendererInfo() {
        return this.mRendererInfo;
    }

    @Nonnull
    public String getHeuristicsSettingsId() {
        return this.mHeuristicsSettingsId;
    }

    @Nonnull
    public AudioAdaptationSetSwitchContext getAudioAdaptationSetSwitchContext() {
        return this.mAudioAdaptationSetSwitchContext;
    }

    @Nonnull
    public StreamSelections getStreamSelections() {
        return this.mStreamSelections;
    }

    public boolean isHdr() {
        return this.mIsHdr;
    }

    @Nullable
    public String getLanguageCode() {
        return this.mLanguageCode;
    }

    @Nullable
    public String getAudioTrackId() {
        return this.mAudioTrackId;
    }

    public boolean isLiveStream() {
        return this.mIsLiveStream;
    }

    @Nonnull
    public ContentStoreType getContentStoreType() {
        return this.mContentStoreType;
    }
}
