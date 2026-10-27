package com.amazon.avod.prs;

import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface AdditionalParams {
    @Nonnull
    Map<String, String> getPostBodyParams();

    @Nonnull
    Map<String, String> getQueryStringParams();

    boolean hasPostBodyParams();

    boolean hasQueryStringParams();
}
