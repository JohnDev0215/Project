package com.amplifyframework.predictions.result;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.CelebrityDetails;
import com.amplifyframework.util.Immutable;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class IdentifyCelebritiesResult implements IdentifyResult {
    private final List<CelebrityDetails> celebrities;

    private IdentifyCelebritiesResult(List<CelebrityDetails> list) {
        this.celebrities = list;
    }

    @NonNull
    public static IdentifyCelebritiesResult fromCelebrities(@NonNull List<CelebrityDetails> list) {
        Objects.requireNonNull(list);
        return new IdentifyCelebritiesResult(list);
    }

    @NonNull
    public List<CelebrityDetails> getCelebrities() {
        return Immutable.of(this.celebrities);
    }
}
