package com.amazon.music.downloads.notification;

import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.ErrorReason;
import com.amazon.music.downloads.Group;
import com.amazon.music.downloads.GroupProvider;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.ItemProvider;
import com.amazon.music.downloads.RequestItem;

/* JADX INFO: loaded from: classes4.dex */
public class NotificationProvider {
    private final GroupProvider mGroupProvider;
    private final ItemProvider mItemProvider;
    private final MessageThread mMessageThread = new MessageThread();

    public NotificationProvider(GroupProvider groupProvider, ItemProvider itemProvider) {
        this.mGroupProvider = groupProvider;
        this.mItemProvider = itemProvider;
    }

    public void registerDownloadListener(DownloadListener downloadListener) {
        this.mMessageThread.registerDownloadListener(downloadListener);
    }

    public void unregisterDownloadListener(DownloadListener downloadListener) {
        this.mMessageThread.unregisterDownloadListener(downloadListener);
    }

    public void onQueued(RequestItem requestItem) {
        Item item;
        if (requestItem == null) {
            return;
        }
        String requestId = requestItem.getRequestId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i != 1) {
            if (i == 2 && (item = this.mItemProvider.getItem(requestId)) != null) {
                this.mMessageThread.onStatusUpdate(DownloadState.QUEUED, requestId, item);
                return;
            }
            return;
        }
        Group group = this.mGroupProvider.getGroup(requestId);
        if (group != null) {
            this.mMessageThread.onStatusUpdate(DownloadState.QUEUED, requestId, group);
        }
    }

    public void onPausedByUser(RequestItem requestItem) {
        Item item;
        if (requestItem == null) {
            return;
        }
        String requestId = requestItem.getRequestId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i != 1) {
            if (i == 2 && (item = this.mItemProvider.getItem(requestId)) != null) {
                this.mMessageThread.onStatusUpdate(DownloadState.PAUSED_USER, requestId, item);
                return;
            }
            return;
        }
        Group group = this.mGroupProvider.getGroup(requestId);
        if (group != null) {
            this.mMessageThread.onStatusUpdate(DownloadState.PAUSED_USER, requestId, group);
        }
    }

    public void onPausedDueToPolicy(RequestItem requestItem) {
        Item item;
        if (requestItem == null) {
            return;
        }
        String requestId = requestItem.getRequestId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i != 1) {
            if (i == 2 && (item = this.mItemProvider.getItem(requestId)) != null) {
                this.mMessageThread.onStatusUpdate(DownloadState.PAUSED_POLICY, requestId, item);
                return;
            }
            return;
        }
        Group group = this.mGroupProvider.getGroup(requestId);
        if (group != null) {
            this.mMessageThread.onStatusUpdate(DownloadState.PAUSED_POLICY, requestId, group);
        }
    }

    public void onPausedDueToPriority(RequestItem requestItem) {
        Item item;
        if (requestItem == null) {
            return;
        }
        String requestId = requestItem.getRequestId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i != 1) {
            if (i == 2 && (item = this.mItemProvider.getItem(requestId)) != null) {
                this.mMessageThread.onStatusUpdate(DownloadState.PAUSED_PRIORITY, requestId, item);
                return;
            }
            return;
        }
        Group group = this.mGroupProvider.getGroup(requestId);
        if (group != null) {
            this.mMessageThread.onStatusUpdate(DownloadState.PAUSED_PRIORITY, requestId, group);
        }
    }

    public void onCanceled(RequestItem requestItem) {
        Item item;
        if (requestItem == null) {
            return;
        }
        String requestId = requestItem.getRequestId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i != 1) {
            if (i == 2 && (item = this.mItemProvider.getItem(requestId)) != null) {
                this.mMessageThread.onStatusUpdate(DownloadState.CANCELED, requestId, item);
                return;
            }
            return;
        }
        Group group = this.mGroupProvider.getGroup(requestId);
        if (group != null) {
            this.mMessageThread.onStatusUpdate(DownloadState.CANCELED, requestId, group);
        }
    }

    public void onDownloadStarted(RequestItem requestItem) {
        Item item;
        if (requestItem == null) {
            return;
        }
        String requestId = requestItem.getRequestId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i != 1) {
            if (i == 2 && (item = this.mItemProvider.getItem(requestId)) != null) {
                this.mMessageThread.onStatusUpdate(DownloadState.DOWNLOADING, requestId, item);
                return;
            }
            return;
        }
        Group group = this.mGroupProvider.getGroup(requestId);
        if (group != null) {
            this.mMessageThread.onStatusUpdate(DownloadState.DOWNLOADING, requestId, group);
        }
    }

    public void onProgressUpdated(RequestItem requestItem, float f) {
        Item item;
        if (requestItem == null) {
            return;
        }
        String requestId = requestItem.getRequestId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i != 1) {
            if (i == 2 && (item = this.mItemProvider.getItem(requestId)) != null) {
                this.mMessageThread.onProgressUpdate(requestId, item, f);
                return;
            }
            return;
        }
        Group group = this.mGroupProvider.getGroup(requestId);
        if (group != null) {
            this.mMessageThread.onProgressUpdate(requestId, group, f);
        }
    }

    public void onDownloaded(RequestItem requestItem) {
        Item item;
        if (requestItem == null) {
            return;
        }
        String requestId = requestItem.getRequestId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i != 1) {
            if (i == 2 && (item = this.mItemProvider.getItem(requestId)) != null) {
                this.mMessageThread.onStatusUpdate(DownloadState.DOWNLOADED, requestId, item);
                return;
            }
            return;
        }
        Group group = this.mGroupProvider.getGroup(requestId);
        if (group != null) {
            this.mMessageThread.onStatusUpdate(DownloadState.DOWNLOADED, requestId, group);
        }
    }

    public void onError(RequestItem requestItem, ErrorReason errorReason) {
        Item item;
        if (requestItem == null || errorReason == null) {
            return;
        }
        String requestId = requestItem.getRequestId();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i != 1) {
            if (i == 2 && (item = this.mItemProvider.getItem(requestId)) != null) {
                this.mMessageThread.onStatusUpdate(DownloadState.ERROR, requestId, item);
                return;
            }
            return;
        }
        Group group = this.mGroupProvider.getGroup(requestId);
        if (group != null) {
            this.mMessageThread.onStatusUpdate(DownloadState.ERROR, requestId, group);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.downloads.notification.NotificationProvider$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$DownloadState;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$RequestItem$RequestType;

        static {
            int[] iArr = new int[DownloadState.values().length];
            $SwitchMap$com$amazon$music$downloads$DownloadState = iArr;
            try {
                iArr[DownloadState.PAUSED_USER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_PRIORITY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_POLICY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[RequestItem.RequestType.values().length];
            $SwitchMap$com$amazon$music$downloads$RequestItem$RequestType = iArr2;
            try {
                iArr2[RequestItem.RequestType.GROUP.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[RequestItem.RequestType.ITEM.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public void onPaused(RequestItem requestItem, DownloadState downloadState) {
        if (requestItem == null) {
            return;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$downloads$DownloadState[downloadState.ordinal()];
        if (i == 1) {
            onPausedByUser(requestItem);
        } else if (i == 2) {
            onPausedDueToPriority(requestItem);
        } else {
            if (i != 3) {
                return;
            }
            onPausedDueToPolicy(requestItem);
        }
    }
}
