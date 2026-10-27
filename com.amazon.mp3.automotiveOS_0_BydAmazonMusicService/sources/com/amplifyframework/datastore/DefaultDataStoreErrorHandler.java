package com.amplifyframework.datastore;

import androidx.annotation.NonNull;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.logging.Logger;

/* JADX INFO: loaded from: classes5.dex */
public final class DefaultDataStoreErrorHandler implements DataStoreErrorHandler {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");

    private DefaultDataStoreErrorHandler() {
    }

    @NonNull
    public static DataStoreErrorHandler instance() {
        return new DefaultDataStoreErrorHandler();
    }

    @Override // com.amplifyframework.core.Consumer
    public void accept(@NonNull DataStoreException dataStoreException) {
        LOG.error("Error encountered in the DataStore.", dataStoreException);
    }
}
