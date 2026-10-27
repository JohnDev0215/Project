package org.chromium.mojo.bindings;

import org.chromium.mojo.system.MojoException;

/* JADX INFO: loaded from: classes3.dex */
public interface ConnectionErrorHandler {
    void onConnectionError(MojoException mojoException);
}
