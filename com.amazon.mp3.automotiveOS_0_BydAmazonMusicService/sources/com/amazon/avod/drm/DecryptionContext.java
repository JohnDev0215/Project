package com.amazon.avod.drm;

import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.drm.DrmScheme;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface DecryptionContext {
    @Nonnull
    DrmScheme getDrmScheme();

    void initialize() throws MediaException;

    void repairDecryptionInitializationFailure(MediaException mediaException) throws MediaException;

    void shutdown();
}
