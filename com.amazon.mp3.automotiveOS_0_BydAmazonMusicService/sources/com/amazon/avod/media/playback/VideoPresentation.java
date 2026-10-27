package com.amazon.avod.media.playback;

import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.PlaybackEventReporter;
import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface VideoPresentation {
    @Nonnull
    DiagnosticsController getDiagnosticsController();

    @Nonnull
    VideoPlayer getPlayer();

    @Nullable
    PlaybackEventReporter getReporter();

    @Nonnull
    VideoSpecification getSpecification();

    @Nullable
    File getStoragePath();

    @Nonnull
    VideoOptions getVideoOptions();

    void prepareAsync() throws MediaException;

    void setListener(@Nonnull VideoPresentationEventListener videoPresentationEventListener);

    void setRenderingSettings(@Nonnull VideoRenderingSettings videoRenderingSettings);

    void terminate(boolean z);
}
