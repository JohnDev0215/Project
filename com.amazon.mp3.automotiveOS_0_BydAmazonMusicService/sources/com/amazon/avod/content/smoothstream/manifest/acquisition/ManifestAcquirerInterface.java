package com.amazon.avod.content.smoothstream.manifest.acquisition;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.playback.LifecycleProfiler;
import com.amazon.avod.playback.drm.DrmScheme;
import java.io.File;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface ManifestAcquirerInterface {
    Manifest getManifestFromUrl(@Nonnull ContentUrlSelector contentUrlSelector, @Nonnull String str, @Nonnull File file, @Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType, boolean z, @Nonnull DrmScheme drmScheme, @Nonnull LifecycleProfiler lifecycleProfiler, @Nullable String str2, @Nonnull ContentType contentType) throws ContentException;

    void shutdown();
}
