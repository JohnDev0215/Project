package com.amazon.avod.media.download.plugin;

import com.amazon.atvplaybackdevice.types.ConsumptionType;
import com.amazon.avod.content.image.TrickplayManifest;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.io.File;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ContentFetcherPluginContext {
    private static final ImmutableMap<PluginSessionType, ConsumptionType> SESSION_TO_CONSUMTION_TYPE = (ImmutableMap) Preconditions2.checkFullKeyMapping(PluginSessionType.class, ImmutableMap.builder().put(PluginSessionType.PLAYBACK, ConsumptionType.Streaming).put(PluginSessionType.DOWNLOAD, ConsumptionType.Download).build());
    private final PlaybackEventReporter mEventReporter;
    private final boolean mIsLocalPlayback;
    private final TrickplayManifest mLiveTrickplayManifest;

    @Nullable
    private File mPlaybackStoragePath = null;
    private final Map<String, String> mSessionContext;
    private final PluginSessionType mSessionType;
    private final File mStoragePath;
    private final VideoSpecification mVideoSpec;

    public enum PluginSessionType {
        PLAYBACK,
        DOWNLOAD
    }

    public ContentFetcherPluginContext(@Nonnull VideoSpecification videoSpecification, @Nonnull File file, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull PluginSessionType pluginSessionType, boolean z, @Nullable TrickplayManifest trickplayManifest, @Nonnull Map<String, String> map, @Nullable AdvisoryRatingModel advisoryRatingModel) {
        this.mVideoSpec = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpec");
        this.mStoragePath = (File) Preconditions.checkNotNull(file, "storagePath");
        this.mEventReporter = (PlaybackEventReporter) Preconditions.checkNotNull(playbackEventReporter, "eventReporter");
        this.mSessionType = (PluginSessionType) Preconditions.checkNotNull(pluginSessionType, "sessionType");
        this.mIsLocalPlayback = z;
        this.mLiveTrickplayManifest = trickplayManifest;
        this.mSessionContext = (Map) Preconditions.checkNotNull(map, "mSessionContext");
    }

    @Nonnull
    @Deprecated
    public String getTitleId() {
        return this.mVideoSpec.getTitleId();
    }

    @Deprecated
    public boolean isTrailer() {
        return this.mVideoSpec.isTrailer();
    }

    @Nonnull
    public VideoSpecification getVideoSpecification() {
        return this.mVideoSpec;
    }

    @Nonnull
    public Map<String, String> getSessionContext() {
        return this.mSessionContext;
    }

    @Nonnull
    public File getStoragePath() {
        return this.mStoragePath;
    }

    @Nonnull
    public PlaybackEventReporter getEventReporter() {
        return this.mEventReporter;
    }

    @Nullable
    public TrickplayManifest getLiveTrickplayManifest() {
        return this.mLiveTrickplayManifest;
    }

    @Nonnull
    public ConsumptionType getConsumptionType() {
        return (ConsumptionType) Preconditions.checkNotNull(SESSION_TO_CONSUMTION_TYPE.get(this.mSessionType), String.format("No valid ConsumptionType mapping found for %s", this.mSessionType.toString()));
    }
}
