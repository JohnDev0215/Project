package com.amazon.avod.media.drm;

import android.content.Context;
import com.amazon.avod.drm.SoftwarePlayReadyDrmFramework;
import com.amazon.avod.drm.playready.PlayReadyException;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.playback.drm.DrmScheme;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class SoftwarePlayReadyDrmSession extends SoftwarePlayReadyDrmFramework implements DrmSession {
    public SoftwarePlayReadyDrmSession(@Nonnull Context context, @Nonnull FileSystem fileSystem, @Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        super(context, fileSystem, playbackNativeLibrariesLoader);
    }

    @Override // com.amazon.avod.media.drm.DrmSession
    @Nonnull
    public DrmCryptoSession openDrmCryptoSession(@Nonnull byte[] bArr, @Nullable byte[] bArr2, @Nullable byte[] bArr3, @Nullable String str, @Nonnull DrmScheme drmScheme) throws DrmLicensingException {
        try {
            configureDrm();
            setPlayReadyHeader(bArr);
            waitForSetPlayReadyHeader();
            return new SoftwarePlayReadyCryptoSession(this);
        } catch (PlayReadyException e) {
            throw new DrmLicensingException(e);
        }
    }
}
