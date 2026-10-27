package com.amazon.avod.drm;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface DecryptionContextFactory {
    @Nonnull
    DecryptionContext newDecryptionContext(VideoSpecification videoSpecification, boolean z, String str, @Nullable String str2, boolean z2, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nullable ContentSession contentSession);
}
