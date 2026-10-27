package com.amazon.music.downloads.notification;

import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.Item;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class DownloadListener implements DownloadCallbacks {
    private Set<String> mRequestIds;
    private boolean mShouldNotifyAll;

    public abstract /* synthetic */ void onProgressUpdate(String str, Group group, float f);

    public abstract /* synthetic */ void onProgressUpdate(String str, Item item, float f);

    public abstract /* synthetic */ void onStatusUpdate(DownloadState downloadState, String str, Group group);

    public abstract /* synthetic */ void onStatusUpdate(DownloadState downloadState, String str, Item item);

    public DownloadListener(boolean z) {
        this.mShouldNotifyAll = z;
    }

    public DownloadListener(Collection<String> collection) {
        if (collection == null) {
            throw new NullPointerException("Must provide a non null collection of request ids");
        }
        this.mRequestIds = Collections.synchronizedSet(new HashSet(collection));
    }

    public final boolean hasRequestId(String str) {
        Set<String> set;
        return this.mShouldNotifyAll || ((set = this.mRequestIds) != null && set.contains(str));
    }

    public final void addRequestId(String str) {
        if (this.mShouldNotifyAll) {
            return;
        }
        this.mRequestIds.add(str);
    }

    public final void removeRequestId(String str) {
        if (this.mShouldNotifyAll) {
            return;
        }
        this.mRequestIds.remove(str);
    }
}
