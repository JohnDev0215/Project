package com.amazon.avod.content.smoothstream.manifest;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.playback.drm.DrmScheme;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface ManifestParser {
    @Nonnull
    Manifest parse(@Nonnull ByteBuffer byteBuffer, @Nonnull DrmScheme drmScheme) throws ContentException;
}
