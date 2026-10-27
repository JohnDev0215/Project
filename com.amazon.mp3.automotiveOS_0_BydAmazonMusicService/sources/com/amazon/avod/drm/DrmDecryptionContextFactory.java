package com.amazon.avod.drm;

import com.amazon.avod.content.ContentSession;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.media.playback.support.ConsumptionType;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DrmDecryptionContextFactory implements DecryptionContextFactory {
    private final BaseDrmSystem mBaseDrmSystem;

    public DrmDecryptionContextFactory(BaseDrmSystem baseDrmSystem) {
        this.mBaseDrmSystem = baseDrmSystem;
    }

    @Override // com.amazon.avod.drm.DecryptionContextFactory
    @Nonnull
    public DecryptionContext newDecryptionContext(VideoSpecification videoSpecification, boolean z, String str, @Nullable String str2, boolean z2, @Nonnull DrmScheme drmScheme, @Nonnull RendererSchemeType rendererSchemeType, @Nullable ContentSession contentSession) {
        if (str != null) {
            return new DrmDecryptionContext(this.mBaseDrmSystem, z ? ConsumptionType.Download : ConsumptionType.Streaming, videoSpecification, str, str2, z2, drmScheme, rendererSchemeType, contentSession);
        }
        return new ClearContentDecryptionContext();
    }
}
