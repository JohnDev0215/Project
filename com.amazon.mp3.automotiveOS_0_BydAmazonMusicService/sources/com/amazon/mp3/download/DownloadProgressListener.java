package com.amazon.mp3.download;

import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.notification.DownloadListener;
import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public abstract class DownloadProgressListener extends DownloadListener {
    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onStatusUpdate(final DownloadState state, final String id, final Group group) {
    }

    @Override // com.amazon.music.downloads.notification.DownloadListener
    public void onStatusUpdate(final DownloadState state, final String id, final Item item) {
    }

    public DownloadProgressListener(Collection<String> requestIds) {
        super(requestIds);
    }
}
