package org.chromium.mojo.system;

import java.io.Closeable;
import org.chromium.mojo.system.Core;

/* JADX INFO: loaded from: classes3.dex */
public interface Handle extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    Core getCore();

    boolean isValid();

    Handle pass();

    int releaseNativeHandle();

    UntypedHandle toUntypedHandle();

    Core.WaitResult wait(Core.HandleSignals handleSignals, long j);
}
