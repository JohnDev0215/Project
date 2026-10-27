package org.apache.commons.compress.archivers.examples;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
final class CloseableConsumerAdapter implements Closeable {
    private Closeable closeable;
    private final CloseableConsumer consumer;

    CloseableConsumerAdapter(CloseableConsumer closeableConsumer) {
        this.consumer = (CloseableConsumer) Objects.requireNonNull(closeableConsumer, "consumer");
    }

    <C extends Closeable> C track(C c) {
        this.closeable = c;
        return c;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Closeable closeable = this.closeable;
        if (closeable != null) {
            this.consumer.accept(closeable);
        }
    }
}
