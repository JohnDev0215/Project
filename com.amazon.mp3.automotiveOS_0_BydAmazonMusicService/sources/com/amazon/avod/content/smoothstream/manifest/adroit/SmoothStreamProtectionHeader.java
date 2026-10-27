package com.amazon.avod.content.smoothstream.manifest.adroit;

import com.amazon.avod.content.smoothstream.manifest.ProtectionHeader;
import com.amazon.avod.playback.drm.DrmScheme;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamProtectionHeader implements ProtectionHeader {
    private final com.amazon.avod.playback.smoothstream.ProtectionHeader mHeader;

    public SmoothStreamProtectionHeader(@Nonnull com.amazon.avod.playback.smoothstream.ProtectionHeader protectionHeader) {
        Preconditions.checkNotNull(protectionHeader, "header");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(protectionHeader.getEncodedProtectionData()), "encodedProtectionData");
        this.mHeader = protectionHeader;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.ProtectionHeader
    public String getBase64EncodedData() {
        return this.mHeader.getEncodedProtectionData();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.ProtectionHeader
    public DrmScheme getDrmScheme() {
        return DrmScheme.PLAYREADY;
    }
}
