package com.amazon.avod.util.json;

import com.fasterxml.jackson.core.JsonLocation;
import com.google.common.base.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class JsonContractException extends Exception {
    private final Optional<JsonLocation> mJsonLocation;

    public JsonContractException(@Nonnull String str) {
        super(str);
        this.mJsonLocation = Optional.absent();
    }

    public JsonContractException(@Nonnull String str, @Nullable JsonLocation jsonLocation) {
        super(str);
        this.mJsonLocation = Optional.fromNullable(jsonLocation);
    }

    @Override // java.lang.Throwable
    public String toString() {
        if (this.mJsonLocation.isPresent()) {
            return super.toString() + " jsonLocation: " + this.mJsonLocation.get();
        }
        return super.toString();
    }
}
