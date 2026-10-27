package com.amazon.avod.session;

import android.content.Context;
import com.amazon.avod.session.SessionIdMetrics;
import java.util.concurrent.ExecutionException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class NoopSessionRetriever implements SessionRetriever {
    @Override // com.amazon.avod.session.SessionRetriever
    public void initialize(@Nonnull Context context) {
    }

    @Override // com.amazon.avod.session.SessionRetriever
    @Nonnull
    public SessionIdMetrics.RetrieverType type() {
        return SessionIdMetrics.RetrieverType.NO_OP;
    }

    @Override // com.amazon.avod.session.SessionRetriever
    @Nullable
    public void retrieveSessionId(SessionIdFetchingCallback sessionIdFetchingCallback) throws ExecutionException, InterruptedException {
        sessionIdFetchingCallback.onSessionIdFetched(null);
    }
}
