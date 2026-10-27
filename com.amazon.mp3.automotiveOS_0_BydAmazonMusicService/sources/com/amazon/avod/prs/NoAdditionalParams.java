package com.amazon.avod.prs;

import java.util.Collections;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class NoAdditionalParams implements AdditionalParams {
    private static final NoAdditionalParams INSTANCE = new NoAdditionalParams();

    @Override // com.amazon.avod.prs.AdditionalParams
    public boolean hasPostBodyParams() {
        return false;
    }

    @Override // com.amazon.avod.prs.AdditionalParams
    public boolean hasQueryStringParams() {
        return false;
    }

    public static NoAdditionalParams instance() {
        return INSTANCE;
    }

    private NoAdditionalParams() {
    }

    @Override // com.amazon.avod.prs.AdditionalParams
    @Nonnull
    public Map<String, String> getPostBodyParams() {
        return Collections.emptyMap();
    }

    @Override // com.amazon.avod.prs.AdditionalParams
    @Nonnull
    public Map<String, String> getQueryStringParams() {
        return Collections.emptyMap();
    }
}
