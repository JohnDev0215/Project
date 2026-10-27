package com.amazon.core.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes2.dex */
@JsonDeserialize(as = ImmutableCSRFHeader.class)
@JsonSerialize(as = ImmutableCSRFHeader.class)
@Value.Immutable
public abstract class CSRFHeader {
    public abstract String rndNonce();

    public abstract Long timestamp();

    public abstract String token();
}
