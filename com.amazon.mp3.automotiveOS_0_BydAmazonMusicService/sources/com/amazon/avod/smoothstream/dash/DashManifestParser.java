package com.amazon.avod.smoothstream.dash;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.ManifestParser;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.playback.drm.DrmScheme;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DashManifestParser implements ManifestParser {
    private final DashManifestJni mManifestJni;

    public DashManifestParser(@Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        this.mManifestJni = new DashManifestJni(playbackNativeLibrariesLoader);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.ManifestParser
    @Nonnull
    public Manifest parse(@Nonnull ByteBuffer byteBuffer, @Nonnull DrmScheme drmScheme) throws ContentException {
        return this.mManifestJni.parse(byteBuffer, drmScheme);
    }
}
