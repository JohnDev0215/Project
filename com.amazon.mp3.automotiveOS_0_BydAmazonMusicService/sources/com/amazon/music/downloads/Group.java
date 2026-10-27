package com.amazon.music.downloads;

import android.net.Uri;
import com.amazon.music.downloads.notification.NotificationInfo;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class Group {
    protected DownloadState mDownloadState;
    protected ErrorReason mErrorReason;
    private final boolean mIsBackground;
    protected final Map<String, ModifiableItem> mItemMap;
    private final NotificationInfo mNotificationInfo;
    protected float mPercentageDownloaded;
    private Uri mUri;

    public Group(Map<String, ModifiableItem> map, NotificationInfo notificationInfo, boolean z) {
        this.mDownloadState = DownloadState.UNKNOWN;
        this.mErrorReason = ErrorReason.UNKNOWN;
        this.mUri = null;
        this.mNotificationInfo = notificationInfo;
        this.mItemMap = map;
        this.mIsBackground = z;
    }

    public Group(Uri uri, Map<String, ModifiableItem> map, NotificationInfo notificationInfo, boolean z) {
        this.mDownloadState = DownloadState.UNKNOWN;
        this.mErrorReason = ErrorReason.UNKNOWN;
        this.mUri = uri;
        this.mNotificationInfo = notificationInfo;
        this.mItemMap = map;
        this.mIsBackground = z;
    }

    public NotificationInfo getNotificationInfo() {
        return this.mNotificationInfo;
    }

    public boolean isBackground() {
        return this.mIsBackground;
    }

    public DownloadState getDownloadState() {
        return this.mDownloadState;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public ErrorReason getErrorReason() {
        return this.mErrorReason;
    }

    public float getPercentageDownloaded() {
        return this.mPercentageDownloaded;
    }

    public int size() {
        return this.mItemMap.size();
    }

    public Iterator<Item> iterator() {
        return new ItemIterator(this.mItemMap.entrySet().iterator());
    }

    protected Map<String, ModifiableItem> getItemMap() {
        return this.mItemMap;
    }

    public String toString() {
        return "Group{mIsBackground=" + this.mIsBackground + ", mItemMap=" + this.mItemMap + ", mDownloadState=" + this.mDownloadState + ", mPercentageloaded=" + this.mPercentageDownloaded + '}';
    }

    public int getDownloadedItemCount() {
        Map<String, ModifiableItem> map = this.mItemMap;
        int i = 0;
        if (map != null) {
            Iterator<ModifiableItem> it = map.values().iterator();
            while (it.hasNext()) {
                if (it.next().mDownloadState == DownloadState.DOWNLOADED) {
                    i++;
                }
            }
        }
        return i;
    }

    public static class Builder {
        private boolean mIsBackground;
        protected Map<String, ModifiableItem> mItemMap = new LinkedHashMap();
        private NotificationInfo mNotificationInfo;
        private Uri mUri;

        public Builder setNotificationInfo(NotificationInfo notificationInfo) {
            this.mNotificationInfo = notificationInfo;
            return this;
        }

        public Builder setUri(Uri uri) {
            this.mUri = uri;
            return this;
        }

        public Builder addItem(String str, Item item) {
            this.mItemMap.put(str, new ModifiableItem(item));
            return this;
        }

        public Group build() {
            if (this.mItemMap.isEmpty()) {
                throw new NullPointerException("A group should have at least 1 item");
            }
            return new Group(this.mUri, this.mItemMap, this.mNotificationInfo, this.mIsBackground);
        }
    }

    private static class ItemIterator implements Iterator<Item> {
        Iterator<Map.Entry<String, ModifiableItem>> mIterator;

        public ItemIterator(Iterator<Map.Entry<String, ModifiableItem>> it) {
            this.mIterator = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.mIterator.hasNext();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public Item next() {
            return this.mIterator.next().getValue();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove items");
        }
    }
}
