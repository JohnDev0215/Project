package org.chromium.mojo.bindings;

import java.io.Closeable;

/* JADX INFO: loaded from: classes3.dex */
public interface MessageReceiver extends Closeable {
    boolean accept(Message message);

    @Override // java.io.Closeable, java.lang.AutoCloseable, org.chromium.mojo.bindings.HandleOwner
    void close();
}
