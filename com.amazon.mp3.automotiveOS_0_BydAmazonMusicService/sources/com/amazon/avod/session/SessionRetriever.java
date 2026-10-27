package com.amazon.avod.session;

import android.content.Context;
import com.amazon.avod.session.SessionIdMetrics;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
interface SessionRetriever {
    void initialize(@Nonnull Context context);

    void retrieveSessionId(@Nonnull SessionIdFetchingCallback sessionIdFetchingCallback) throws ExecutionException, InterruptedException;

    @Nonnull
    SessionIdMetrics.RetrieverType type();
}
