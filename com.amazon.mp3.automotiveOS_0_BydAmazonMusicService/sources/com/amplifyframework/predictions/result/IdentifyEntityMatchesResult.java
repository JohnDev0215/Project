package com.amplifyframework.predictions.result;

import androidx.annotation.NonNull;
import com.amplifyframework.predictions.models.EntityMatch;
import com.amplifyframework.util.Immutable;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class IdentifyEntityMatchesResult implements IdentifyResult {
    private final List<EntityMatch> entityMatches;

    private IdentifyEntityMatchesResult(List<EntityMatch> list) {
        this.entityMatches = list;
    }

    @NonNull
    public static IdentifyEntityMatchesResult fromEntityMatches(@NonNull List<EntityMatch> list) {
        Objects.requireNonNull(list);
        return new IdentifyEntityMatchesResult(list);
    }

    @NonNull
    public List<EntityMatch> getEntityMatches() {
        return Immutable.of(this.entityMatches);
    }
}
