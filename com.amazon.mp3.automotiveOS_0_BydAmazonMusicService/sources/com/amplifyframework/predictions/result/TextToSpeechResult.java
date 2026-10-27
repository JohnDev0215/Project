package com.amplifyframework.predictions.result;

import androidx.annotation.NonNull;
import java.io.InputStream;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class TextToSpeechResult {
    private final InputStream audioData;

    private TextToSpeechResult(InputStream inputStream) {
        this.audioData = inputStream;
    }

    @NonNull
    public static TextToSpeechResult fromAudioData(@NonNull InputStream inputStream) {
        Objects.requireNonNull(inputStream);
        return new TextToSpeechResult(inputStream);
    }

    @NonNull
    public InputStream getAudioData() {
        return this.audioData;
    }
}
