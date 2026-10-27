package com.amazon.music.downloads;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.amazon.music.downloads.RequestItem;
import com.amazon.music.downloads.notification.NotificationProvider;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.PriorityBlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
class DownloadQueueWorker implements QueueWorker {
    private static final String TAG = "DownloadQueueWorker";
    private RequestItem mCurrentItem;
    private final DownloadProcessor mDownloadProcessor;
    private final ModifiableGroupProvider mGroupProvider;
    private final ModifiableItemProvider mItemProvider;
    private final NotificationProvider mListener;
    private Thread mQueueThread;
    private final WifiManager.WifiLock mWifiLock;
    private Logger mLog = LoggerFactory.getLogger(TAG);
    private Comparator<RequestItem> mRequestItemComparator = new Comparator<RequestItem>() { // from class: com.amazon.music.downloads.DownloadQueueWorker.1
        @Override // java.util.Comparator
        public int compare(RequestItem requestItem, RequestItem requestItem2) {
            return ((requestItem.isBackground() || requestItem2.isBackground()) && !(requestItem.isBackground() && requestItem2.isBackground())) ? !requestItem.isBackground() ? -1 : 1 : requestItem.getTimestamp() < requestItem2.getTimestamp() ? -1 : 1;
        }
    };
    private final PriorityBlockingQueue<RequestItem> mRequestQueue = new PriorityBlockingQueue<>(8, this.mRequestItemComparator);
    private boolean mCanRun = true;

    public DownloadQueueWorker(Context context, DMMDownloadCoordinator dMMDownloadCoordinator, ModifiableGroupProvider modifiableGroupProvider, ModifiableItemProvider modifiableItemProvider, NotificationProvider notificationProvider, DownloadableItemConverter downloadableItemConverter) {
        this.mGroupProvider = modifiableGroupProvider;
        this.mItemProvider = modifiableItemProvider;
        this.mListener = notificationProvider;
        this.mDownloadProcessor = new DownloadProcessor(dMMDownloadCoordinator, modifiableGroupProvider, modifiableItemProvider, notificationProvider, downloadableItemConverter);
        this.mWifiLock = ((WifiManager) context.getSystemService("wifi")).createWifiLock(3, "download lock");
    }

    @Override // com.amazon.music.downloads.QueueWorker
    public synchronized void start() {
        this.mWifiLock.acquire();
        Thread threadCreateQueueThread = createQueueThread();
        this.mQueueThread = threadCreateQueueThread;
        threadCreateQueueThread.start();
    }

    @Override // com.amazon.music.downloads.QueueWorker
    public synchronized boolean isDownloading() {
        return this.mCurrentItem != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0013  */
    @Override // com.amazon.music.downloads.QueueWorker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized boolean isIdle() {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.mCanRun     // Catch: java.lang.Throwable -> L16
            if (r0 == 0) goto L13
            java.util.concurrent.PriorityBlockingQueue<com.amazon.music.downloads.RequestItem> r0 = r1.mRequestQueue     // Catch: java.lang.Throwable -> L16
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L16
            if (r0 == 0) goto L13
            com.amazon.music.downloads.RequestItem r0 = r1.mCurrentItem     // Catch: java.lang.Throwable -> L16
            if (r0 != 0) goto L13
            r0 = 1
            goto L14
        L13:
            r0 = 0
        L14:
            monitor-exit(r1)
            return r0
        L16:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.downloads.DownloadQueueWorker.isIdle():boolean");
    }

    private Thread createQueueThread() {
        return new Thread() { // from class: com.amazon.music.downloads.DownloadQueueWorker.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (DownloadQueueWorker.this.mCanRun && !isInterrupted()) {
                    try {
                        RequestItem requestItem = (RequestItem) DownloadQueueWorker.this.mRequestQueue.take();
                        synchronized (DownloadQueueWorker.this) {
                            DownloadQueueWorker.this.mCurrentItem = requestItem;
                            DownloadQueueWorker.this.updateRequestStateToQueued(requestItem);
                        }
                        DownloadQueueWorker.this.mDownloadProcessor.download(requestItem);
                        ErrorReason errorReasonHasInstantFailReason = DownloadQueueWorker.this.mDownloadProcessor.hasInstantFailReason(DownloadQueueWorker.this.mCurrentItem);
                        if (errorReasonHasInstantFailReason != null) {
                            while (!DownloadQueueWorker.this.mRequestQueue.isEmpty()) {
                                DownloadQueueWorker.this.mDownloadProcessor.markAsInstantError((RequestItem) DownloadQueueWorker.this.mRequestQueue.poll(), errorReasonHasInstantFailReason);
                            }
                        }
                        synchronized (DownloadQueueWorker.this) {
                            DownloadQueueWorker.this.mCurrentItem = null;
                        }
                    } catch (InterruptedException unused) {
                    }
                }
                synchronized (DownloadQueueWorker.this) {
                    if (DownloadQueueWorker.this.mCanRun) {
                        DownloadQueueWorker.this.start();
                    }
                }
            }
        };
    }

    @Override // com.amazon.music.downloads.QueueWorker
    public synchronized void add(RequestItem requestItem) {
        this.mRequestQueue.add(requestItem);
        RequestItem requestItem2 = this.mCurrentItem;
        if (requestItem2 != null && requestItem2.isBackground() && !requestItem.isBackground() && updateRequestStateToPauseDueToPriority(requestItem2)) {
            this.mRequestQueue.add(requestItem2);
        }
    }

    @Override // com.amazon.music.downloads.QueueWorker
    public synchronized void pause(RequestItem requestItem) {
        RequestItem requestItem2 = this.mCurrentItem;
        if (requestItem2 == null || !requestItem2.getRequestId().equals(requestItem.getRequestId())) {
            pauseImmediately(requestItem);
        } else {
            RequestItem requestItem3 = this.mCurrentItem;
            if (requestItem3 != null && requestItem3.getRequestId().equals(requestItem.getRequestId())) {
                this.mDownloadProcessor.pause(requestItem);
            }
        }
        this.mRequestQueue.remove(requestItem);
    }

    @Override // com.amazon.music.downloads.QueueWorker
    public synchronized void cancel(RequestItem requestItem) {
        RequestItem requestItem2 = this.mCurrentItem;
        if (requestItem2 == null || !requestItem2.getRequestId().equals(requestItem.getRequestId())) {
            cancelImmediately(requestItem);
        } else {
            RequestItem requestItem3 = this.mCurrentItem;
            if (requestItem3 != null && requestItem3.getRequestId().equals(requestItem.getRequestId())) {
                this.mDownloadProcessor.cancel(requestItem);
            }
        }
        this.mRequestQueue.remove(requestItem);
    }

    private void cancelImmediately(RequestItem requestItem) {
        ModifiableGroup group = this.mGroupProvider.getGroup(requestItem.getRequestId());
        ModifiableItem item = this.mItemProvider.getItem(requestItem.getRequestId());
        if (group != null) {
            for (Map.Entry<String, ModifiableItem> entry : group.getItemMap().entrySet()) {
                if (entry.getValue().getDownloadState() != DownloadState.DOWNLOADED) {
                    entry.getValue().setDownloadState(DownloadState.CANCELED);
                    this.mListener.onCanceled(new RequestItem(entry.getKey(), RequestItem.RequestType.ITEM, entry.getValue().isBackground()));
                }
            }
        }
        if (group != null) {
            group.setDownloadState(DownloadState.CANCELED);
            this.mListener.onCanceled(requestItem);
        } else if (item != null) {
            item.setDownloadState(DownloadState.CANCELED);
            this.mListener.onCanceled(requestItem);
        }
    }

    private void pauseImmediately(RequestItem requestItem) {
        ModifiableGroup group = this.mGroupProvider.getGroup(requestItem.getRequestId());
        ModifiableItem item = this.mItemProvider.getItem(requestItem.getRequestId());
        if (group != null) {
            for (Map.Entry<String, ModifiableItem> entry : group.getItemMap().entrySet()) {
                if (entry.getValue().getDownloadState() != DownloadState.DOWNLOADED) {
                    entry.getValue().setDownloadState(DownloadState.PAUSED_USER);
                    this.mListener.onPaused(new RequestItem(entry.getKey(), RequestItem.RequestType.ITEM, entry.getValue().isBackground()), entry.getValue().getDownloadState());
                }
            }
        }
        if (group != null) {
            group.setDownloadState(DownloadState.PAUSED_USER);
            this.mListener.onPaused(requestItem, group.getDownloadState());
        } else if (item != null) {
            item.setDownloadState(DownloadState.PAUSED_USER);
            this.mListener.onPaused(requestItem, item.getDownloadState());
        }
    }

    @Override // com.amazon.music.downloads.QueueWorker
    public synchronized void exit() {
        if (this.mCanRun) {
            this.mCanRun = false;
            if (!this.mQueueThread.isInterrupted()) {
                this.mQueueThread.interrupt();
            }
            this.mWifiLock.release();
        }
    }

    @Override // com.amazon.music.downloads.QueueWorker
    public synchronized boolean hasTerminated() {
        return !this.mCanRun;
    }

    private boolean updateRequestStateToPauseDueToPriority(RequestItem requestItem) {
        return updateRequestState(requestItem, new DownloadState[]{DownloadState.DOWNLOADING, DownloadState.QUEUED}, DownloadState.PAUSED_PRIORITY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean updateRequestStateToQueued(RequestItem requestItem) {
        return updateRequestState(requestItem, new DownloadState[]{DownloadState.PAUSED_POLICY, DownloadState.PAUSED_PRIORITY}, DownloadState.QUEUED);
    }

    private boolean updateRequestState(RequestItem requestItem, DownloadState[] downloadStateArr, DownloadState downloadState) {
        int i = AnonymousClass3.$SwitchMap$com$amazon$music$downloads$RequestItem$RequestType[requestItem.getRequestType().ordinal()];
        if (i == 1) {
            return updateGroupState(requestItem.getRequestId(), downloadStateArr, downloadState);
        }
        if (i != 2) {
            return false;
        }
        return updateItemState(requestItem.getRequestId(), downloadStateArr, downloadState, null);
    }

    private boolean updateGroupState(String str, DownloadState[] downloadStateArr, DownloadState downloadState) {
        ModifiableGroup group = this.mGroupProvider.getGroup(str);
        if (group == null) {
            this.mLog.error("updateGroupState() found null group for requestId = " + str);
            return false;
        }
        for (DownloadState downloadState2 : downloadStateArr) {
            if (group.getDownloadState() == downloadState2) {
                boolean z = false;
                for (Map.Entry<String, ModifiableItem> entry : group.getItemMap().entrySet()) {
                    if (updateItemState(entry.getKey(), downloadStateArr, downloadState, entry.getValue().getGroupRequestId())) {
                        z = true;
                    }
                }
                if (z) {
                    group.setDownloadState(downloadState);
                    notify(new RequestItem(str, RequestItem.RequestType.GROUP, group.isBackground()), downloadState);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean updateItemState(String str, DownloadState[] downloadStateArr, DownloadState downloadState, String str2) {
        ModifiableItem item = this.mItemProvider.getItem(str);
        if (item == null) {
            return false;
        }
        String groupRequestId = item.getGroupRequestId();
        if ((str2 != null && !str2.equals(groupRequestId)) || (groupRequestId != null && !groupRequestId.equals(str2))) {
            return false;
        }
        for (DownloadState downloadState2 : downloadStateArr) {
            if (item.getDownloadState() == downloadState2) {
                item.setDownloadState(downloadState);
                notify(new RequestItem(str, RequestItem.RequestType.ITEM, item.isBackground()), downloadState);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: com.amazon.music.downloads.DownloadQueueWorker$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$DownloadState;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$RequestItem$RequestType;

        static {
            int[] iArr = new int[DownloadState.values().length];
            $SwitchMap$com$amazon$music$downloads$DownloadState = iArr;
            try {
                iArr[DownloadState.PAUSED_POLICY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_PRIORITY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.QUEUED.ordinal()] = 3;
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

    private void notify(RequestItem requestItem, DownloadState downloadState) {
        int i = AnonymousClass3.$SwitchMap$com$amazon$music$downloads$DownloadState[downloadState.ordinal()];
        if (i == 1) {
            this.mListener.onPausedDueToPolicy(requestItem);
            return;
        }
        if (i == 2) {
            this.mListener.onPausedDueToPriority(requestItem);
        } else if (i == 3) {
            this.mListener.onQueued(requestItem);
        } else {
            this.mLog.warn("notify for download state: {} is not valid.", downloadState);
        }
    }
}
