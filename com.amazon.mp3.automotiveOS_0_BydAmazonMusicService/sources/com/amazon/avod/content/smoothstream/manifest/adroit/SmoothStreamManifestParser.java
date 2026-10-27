package com.amazon.avod.content.smoothstream.manifest.adroit;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.ManifestParser;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamManifestParser implements ManifestParser {
    private final com.amazon.avod.playback.smoothstream.ManifestParser mParser;

    public SmoothStreamManifestParser(@Nonnull com.amazon.avod.playback.smoothstream.ManifestParser manifestParser) {
        this.mParser = (com.amazon.avod.playback.smoothstream.ManifestParser) Preconditions.checkNotNull(manifestParser);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.ManifestParser
    @Nonnull
    public Manifest parse(@Nonnull ByteBuffer byteBuffer, @Nonnull DrmScheme drmScheme) throws ContentException {
        try {
            int iCapacity = byteBuffer.capacity();
            byte[] bArr = new byte[iCapacity];
            byteBuffer.get(bArr);
            return new SmoothStreamManifest(this.mParser.parse(bArr, 0, iCapacity));
        } catch (MediaException e) {
            DLog.errorf("Manifest parsing failed: %s", e);
            throw new ContentException(ContentException.ContentError.CDN_ERROR, "manifest parsing failed", e);
        }
    }
}
