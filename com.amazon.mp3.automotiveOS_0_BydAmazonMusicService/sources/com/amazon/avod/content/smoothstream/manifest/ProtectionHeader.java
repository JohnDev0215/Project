package com.amazon.avod.content.smoothstream.manifest;

import com.amazon.avod.playback.drm.DrmScheme;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface ProtectionHeader {
    @Nonnull
    String getBase64EncodedData();

    @Nonnull
    DrmScheme getDrmScheme();
}
