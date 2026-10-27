package org.chromium.mojo.bindings;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import org.chromium.mojo.system.MojoException;

/* JADX INFO: loaded from: classes3.dex */
public class DelegatingConnectionErrorHandler implements ConnectionErrorHandler {
    private final Set<ConnectionErrorHandler> mHandlers = Collections.newSetFromMap(new WeakHashMap());

    @Override // org.chromium.mojo.bindings.ConnectionErrorHandler
    public void onConnectionError(MojoException mojoException) {
        Iterator<ConnectionErrorHandler> it = this.mHandlers.iterator();
        while (it.hasNext()) {
            it.next().onConnectionError(mojoException);
        }
    }

    public void addConnectionErrorHandler(ConnectionErrorHandler connectionErrorHandler) {
        this.mHandlers.add(connectionErrorHandler);
    }

    public void removeConnectionErrorHandler(ConnectionErrorHandler connectionErrorHandler) {
        this.mHandlers.remove(connectionErrorHandler);
    }
}
