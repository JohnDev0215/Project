package com.amazon.avod.download;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import java.io.File;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface Downloadable {

    public enum DownloadableState {
        NOT_DOWNLOADABLE,
        QUEUED,
        ERRORED,
        COMPLETED,
        TOMBSTONED,
        DELETE_IMMEDIATELY,
        DELETED
    }

    @Nullable
    AudioVideoUrls getAudioVideoUrls();

    @Nonnull
    DownloadableState getDownloadableState();

    @Nullable
    DrmScheme getDrmScheme();

    @Nullable
    String getOfflineKeyId();

    @Nullable
    String getQosNote();

    @Nullable
    RendererSchemeType getRendererSchemeType();

    @Nonnull
    Map<String, String> getSessionContext();

    @Nonnull
    ContentSessionType getSessionType();

    @Nonnull
    VideoSpecification getSpecification();

    @Nonnull
    File getStoragePath();

    boolean isLicensable();
}
