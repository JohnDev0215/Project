package com.amazon.avod.userdownload;

import com.amazon.avod.content.DownloadStoreType;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.drm.db.DrmRecord;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.io.File;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface PVDownloadPlayerShim {
    @Nonnull
    AudioFormat getAudioFormat();

    @Nonnull
    ImmutableList<String> getAudioTrackIds();

    @Nonnull
    ImmutableList<AudioTrackMetadata> getAudioTrackMetadataList();

    @Nonnull
    MediaQuality getDownloadQuality();

    @Nonnull
    DownloadStoreType getDownloadStoreType();

    @Nonnull
    Optional<DrmRecord> getDrmRecord();

    @Nonnull
    @Deprecated
    Optional<DrmScheme> getDrmScheme();

    @Nonnull
    @Deprecated
    Optional<String> getOfflineKeyId();

    @Nonnull
    @Deprecated
    Optional<RendererSchemeType> getRendererSchemeType();

    @Nonnull
    Optional<String> getSessionId();

    @Nonnull
    UserDownloadState getState();

    @Nonnull
    File getStoragePath();

    @Nonnull
    Optional<String> getUrl();
}
