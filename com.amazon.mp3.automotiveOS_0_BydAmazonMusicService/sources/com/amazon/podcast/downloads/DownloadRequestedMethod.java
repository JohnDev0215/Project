package com.amazon.podcast.downloads;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;

/* JADX INFO: loaded from: classes5.dex */
public final class DownloadRequestedMethod extends Method {
    private String id;

    public DownloadRequestedMethod(final String id, final Queue queue) {
        this.id = id;
        setQueue(queue);
    }

    public String getId() {
        return this.id;
    }
}
