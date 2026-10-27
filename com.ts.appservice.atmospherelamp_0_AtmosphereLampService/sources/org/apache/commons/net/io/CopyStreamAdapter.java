package org.apache.commons.net.io;

import java.util.EventListener;
import org.apache.commons.net.util.ListenerList;

/* JADX INFO: loaded from: classes3.dex */
public class CopyStreamAdapter implements CopyStreamListener {
    private final ListenerList internalListeners = new ListenerList();

    @Override // org.apache.commons.net.io.CopyStreamListener
    public void bytesTransferred(CopyStreamEvent event) {
        for (EventListener listener : this.internalListeners) {
            ((CopyStreamListener) listener).bytesTransferred(event);
        }
    }

    @Override // org.apache.commons.net.io.CopyStreamListener
    public void bytesTransferred(long totalBytesTransferred, int bytesTransferred, long streamSize) {
        for (EventListener listener : this.internalListeners) {
            ((CopyStreamListener) listener).bytesTransferred(totalBytesTransferred, bytesTransferred, streamSize);
        }
    }

    public void addCopyStreamListener(CopyStreamListener listener) {
        this.internalListeners.addListener(listener);
    }

    public void removeCopyStreamListener(CopyStreamListener listener) {
        this.internalListeners.removeListener(listener);
    }
}
