package com.amazon.avod.smoothstream.dash;

import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DashVideoQualityLevel extends DashQualityLevel implements VideoQualityLevel {
    public DashVideoQualityLevel(@Nonnull DashManifestJni dashManifestJni, long j, boolean z) {
        super(dashManifestJni, j, z);
    }

    @Override // com.amazon.avod.smoothstream.dash.DashQualityLevel, com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel
    public boolean isHD() {
        return super.isHD();
    }

    @Override // com.amazon.avod.smoothstream.dash.DashQualityLevel, com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public int getMaxWidth() {
        return super.getMaxWidth();
    }

    @Override // com.amazon.avod.smoothstream.dash.DashQualityLevel, com.amazon.avod.content.smoothstream.manifest.QualityLevel
    public int getMaxHeight() {
        return super.getMaxHeight();
    }
}
