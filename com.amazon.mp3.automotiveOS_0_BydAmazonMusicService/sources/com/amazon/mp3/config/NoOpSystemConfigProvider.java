package com.amazon.mp3.config;

import com.amazon.music.arcus.config.android.SystemConfigProvider;
import java.util.Date;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class NoOpSystemConfigProvider implements SystemConfigProvider {
    @Override // com.amazon.music.arcus.config.android.SystemConfigProvider
    public String getLogLevel() {
        return null;
    }

    @Override // com.amazon.music.arcus.config.android.SystemConfigProvider
    public Map getLogLevelTagMap() {
        return null;
    }

    @Override // com.amazon.music.arcus.config.android.SystemConfigProvider
    public Date getUploadLogRequestDate(final String deviceId) {
        return null;
    }
}
