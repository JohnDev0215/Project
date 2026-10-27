package com.amazon.avod.drm;

import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.drm.DrmScheme;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class ClearContentDecryptionContext implements DecryptionContext {
    @Override // com.amazon.avod.drm.DecryptionContext
    public void initialize() {
    }

    @Override // com.amazon.avod.drm.DecryptionContext
    public void repairDecryptionInitializationFailure(MediaException mediaException) {
    }

    @Override // com.amazon.avod.drm.DecryptionContext
    public void shutdown() {
    }

    ClearContentDecryptionContext() {
    }

    @Override // com.amazon.avod.drm.DecryptionContext
    @Nonnull
    public DrmScheme getDrmScheme() {
        return DrmScheme.NONE;
    }
}
