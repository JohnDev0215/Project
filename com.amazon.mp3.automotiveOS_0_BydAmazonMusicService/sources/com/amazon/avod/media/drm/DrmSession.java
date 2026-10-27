package com.amazon.avod.media.drm;

import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.playback.support.DrmFramework;
import com.amazon.avod.playback.drm.DrmScheme;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface DrmSession extends DrmFramework {
    @Nonnull
    DrmCryptoSession openDrmCryptoSession(@Nonnull byte[] bArr, @Nullable byte[] bArr2, @Nullable byte[] bArr3, @Nullable String str, @Nonnull DrmScheme drmScheme) throws DrmLicensingException;
}
