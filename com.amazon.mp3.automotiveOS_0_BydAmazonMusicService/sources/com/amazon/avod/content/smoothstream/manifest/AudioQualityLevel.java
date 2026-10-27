package com.amazon.avod.content.smoothstream.manifest;

import com.amazon.avod.media.AudioFormat;
import javax.annotation.Nonnull;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface AudioQualityLevel extends QualityLevel {
    @Nonnull
    AudioFormat getAudioFormat();

    int getAudioTag();

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    /* synthetic */ int getBitrate();

    int getBitsPerSample();

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    /* synthetic */ float getFrameRate();

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    /* bridge */ /* synthetic */ default int getMaxHeight() {
        return super.getMaxHeight();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    /* bridge */ /* synthetic */ default int getMaxWidth() {
        return super.getMaxWidth();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.QualityLevel
    /* synthetic */ int getNalUnitlengthField();

    int getNumChannels();

    int getSampleRate();

    @Nonnull
    JSONObject toJson();
}
