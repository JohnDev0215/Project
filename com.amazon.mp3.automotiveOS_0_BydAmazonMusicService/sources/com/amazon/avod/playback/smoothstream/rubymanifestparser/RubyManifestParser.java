package com.amazon.avod.playback.smoothstream.rubymanifestparser;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.playback.smoothstream.Manifest;
import com.amazon.avod.playback.smoothstream.ManifestParser;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class RubyManifestParser implements ManifestParser {
    private boolean mInitialized;
    private final RubyManifestParserJni mJni;
    private final PlaybackNativeLibrariesLoader mLibrariesLoader;
    private final SmoothStreamingPlaybackConfig mPlaybackConfig;

    public RubyManifestParser(PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        this(new RubyManifestParserJni(), playbackNativeLibrariesLoader, SmoothStreamingPlaybackConfig.INSTANCE);
    }

    RubyManifestParser(RubyManifestParserJni rubyManifestParserJni, PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mJni = rubyManifestParserJni;
        this.mLibrariesLoader = playbackNativeLibrariesLoader;
        this.mPlaybackConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "playbackConfig");
    }

    @Override // com.amazon.avod.playback.smoothstream.ManifestParser
    public Manifest parse(byte[] bArr, int i, int i2) throws MediaException {
        if (!this.mInitialized) {
            initialize();
        }
        long bufferModed = this.mJni.parseBufferModed(bArr, i, i2, this.mPlaybackConfig.shouldUseNativeMemoryForManifest());
        if (bufferModed != 0) {
            return new RubyManifest(bufferModed, this.mJni);
        }
        throw new ContentException(ContentException.ContentError.FAILED_TO_PARSE_MANIFEST, "Native code return null but not throw any exception!!!");
    }

    private void initialize() {
        this.mLibrariesLoader.waitForInitialization();
        String libraryVersion = this.mJni.getLibraryVersion();
        Preconditions.checkState(Objects.equal("1.3", libraryVersion), "Smooth streaming manifest parser mismatched: java expects version of %s while native version is %s", "1.3", libraryVersion);
        this.mInitialized = true;
    }
}
