package com.facebook.callercontext;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface CallerContextVerifier {
    void verifyCallerContext(@Nullable Object callerContext, boolean isPrefetch);
}
