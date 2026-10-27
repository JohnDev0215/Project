package com.amazon.avod.content.smoothstream.quality.defaults;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DefaultQualityConfigurationBase implements DefaultQualityConfiguration {
    @Override // com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration
    public QualityLevel getDefaultQualityLevel(ContentSessionContext contentSessionContext, StreamIndex streamIndex) {
        if (streamIndex.isAudio()) {
            return getAudioQuality(contentSessionContext, streamIndex);
        }
        if (streamIndex.isVideo()) {
            return getVideoQuality(contentSessionContext);
        }
        return null;
    }
}
