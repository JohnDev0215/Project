package com.google.android.exoplayer2;

/* JADX INFO: loaded from: classes6.dex */
public interface RendererCapabilities {
    int getTrackType();

    int supportsFormat(Format format) throws ExoPlaybackException;

    int supportsMixedMimeTypeAdaptation() throws ExoPlaybackException;
}
