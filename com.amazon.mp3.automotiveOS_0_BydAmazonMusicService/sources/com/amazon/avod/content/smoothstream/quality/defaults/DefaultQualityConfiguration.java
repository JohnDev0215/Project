package com.amazon.avod.content.smoothstream.quality.defaults;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;

/* JADX INFO: loaded from: classes2.dex */
public interface DefaultQualityConfiguration {
    AudioQualityLevel getAudioQuality(ContentSessionContext contentSessionContext, StreamIndex streamIndex);

    QualityLevel getDefaultQualityLevel(ContentSessionContext contentSessionContext, StreamIndex streamIndex);

    VideoQualityLevel getVideoQuality(ContentSessionContext contentSessionContext);
}
