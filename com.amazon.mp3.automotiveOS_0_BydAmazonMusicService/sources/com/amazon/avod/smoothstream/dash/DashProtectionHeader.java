package com.amazon.avod.smoothstream.dash;

import com.amazon.avod.content.smoothstream.manifest.ProtectionHeader;
import com.amazon.avod.playback.drm.DrmScheme;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DashProtectionHeader implements ProtectionHeader {
    private final DrmScheme mDrmScheme;
    private final String mProtectionInfo;

    public DashProtectionHeader(@Nonnull String str, @Nonnull DrmScheme drmScheme) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(str), "protectionInfo");
        this.mProtectionInfo = str;
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(drmScheme, "drmScheme");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.ProtectionHeader
    public String getBase64EncodedData() {
        return this.mProtectionInfo;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.ProtectionHeader
    public DrmScheme getDrmScheme() {
        return this.mDrmScheme;
    }
}
