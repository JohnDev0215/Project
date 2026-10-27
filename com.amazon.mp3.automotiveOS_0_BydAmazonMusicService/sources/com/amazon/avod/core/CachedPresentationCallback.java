package com.amazon.avod.core;

import com.amazon.avod.playbackclient.presentation.CachedVideoPresentation;
import com.amazon.avod.playbackclient.presentation.PrepareFailedException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface CachedPresentationCallback {
    void onCachedPresentationCreated(@Nonnull CachedVideoPresentation cachedVideoPresentation);

    void onPresentationError(@Nonnull PrepareFailedException prepareFailedException);
}
