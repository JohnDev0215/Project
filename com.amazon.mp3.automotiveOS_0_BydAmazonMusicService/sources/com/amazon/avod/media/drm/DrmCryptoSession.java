package com.amazon.avod.media.drm;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.playback.renderer.shared.SampleMetadata;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface DrmCryptoSession {
    @Nullable
    MediaCodec.CryptoInfo decryptSample(@Nonnull ByteBuffer byteBuffer, @Nonnull SampleMetadata sampleMetadata) throws DrmLicensingException;

    @Nullable
    MediaCrypto getMediaCrypto();

    void release();
}
