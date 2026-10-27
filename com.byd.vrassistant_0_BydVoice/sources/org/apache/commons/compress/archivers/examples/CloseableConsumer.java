package org.apache.commons.compress.archivers.examples;

import java.io.Closeable;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public interface CloseableConsumer {
    public static final CloseableConsumer CLOSING_CONSUMER = new CloseableConsumer() { // from class: org.apache.commons.compress.archivers.examples.CloseableConsumer$$ExternalSyntheticLambda0
        @Override // org.apache.commons.compress.archivers.examples.CloseableConsumer
        public final void accept(Closeable closeable) throws IOException {
            closeable.close();
        }
    };
    public static final CloseableConsumer NULL_CONSUMER = new CloseableConsumer() { // from class: org.apache.commons.compress.archivers.examples.CloseableConsumer$$ExternalSyntheticLambda1
        @Override // org.apache.commons.compress.archivers.examples.CloseableConsumer
        public final void accept(Closeable closeable) throws IOException {
            CloseableConsumer.lambda$static$0(closeable);
        }
    };

    static /* synthetic */ void lambda$static$0(Closeable closeable) throws IOException {
    }

    void accept(Closeable closeable) throws IOException;
}
