package com.amazon.avod.playback.renderer;

import com.amazon.avod.util.DLog;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public enum RendererSchemeType {
    OMXIL("OMX", true, true),
    MEDIACODEC_MEDIADRM("MCMD", true, true),
    MEDIACODEC_SOFTWAREPLAYREADY("MCPR", true, false),
    VISUAL_ON("VO", false, false),
    SYE("SYE", true, false),
    GENERIC("TEST", false, false);

    private final boolean mIsHardwareDecoder;
    private final boolean mIsHardwareDrm;
    private final String mShorthand;

    RendererSchemeType(@Nonnull String str, boolean z, boolean z2) {
        this.mShorthand = (String) Preconditions.checkNotNull(str, "shorthand");
        this.mIsHardwareDecoder = z;
        this.mIsHardwareDrm = z2;
    }

    @Nonnull
    public static Optional<RendererSchemeType> fromSchemeString(@Nullable String str) {
        if (str == null) {
            DLog.warnf("Input scheme string was null, returning absent!");
            return Optional.absent();
        }
        String strTrim = str.trim();
        RendererSchemeType rendererSchemeType = OMXIL;
        if (strTrim.equalsIgnoreCase(rendererSchemeType.toString())) {
            return Optional.of(rendererSchemeType);
        }
        RendererSchemeType rendererSchemeType2 = MEDIACODEC_MEDIADRM;
        if (strTrim.equalsIgnoreCase(rendererSchemeType2.toString())) {
            return Optional.of(rendererSchemeType2);
        }
        RendererSchemeType rendererSchemeType3 = MEDIACODEC_SOFTWAREPLAYREADY;
        if (strTrim.equalsIgnoreCase(rendererSchemeType3.toString())) {
            return Optional.of(rendererSchemeType3);
        }
        RendererSchemeType rendererSchemeType4 = VISUAL_ON;
        if (strTrim.equalsIgnoreCase(rendererSchemeType4.toString())) {
            return Optional.of(rendererSchemeType4);
        }
        DLog.warnf("No matching Renderer scheme type found for: %s, returning absent!", str);
        return Optional.absent();
    }

    @Override // java.lang.Enum
    @Nonnull
    public String toString() {
        return name();
    }

    @Nonnull
    public String getShorthand() {
        return this.mShorthand;
    }

    public boolean isHardwareAccelerated() {
        return this.mIsHardwareDecoder || this.mIsHardwareDrm;
    }

    public boolean isHardwareDrm() {
        return this.mIsHardwareDrm;
    }
}
