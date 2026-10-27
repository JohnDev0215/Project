package com.amazon.music.downloads;

import com.amazon.music.downloads.RequestItem;
import com.amazon.music.downloads.notification.NotificationProvider;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class DownloadMonitor implements DownloadEventListener {
    private static final String TAG = "DownloadMonitor";
    private Downloader mDownloader;
    private final Map<String, ModifiableItem> mItemMap;
    private NotificationProvider mListener;
    private ModifiableGroupProvider mModifiableGroupProvider;
    private ModifiableItemProvider mModifiableItemProvider;
    private int mTotalCount;
    private int pauseByPolicyCount = 0;
    private Logger mLog = LoggerFactory.getLogger(TAG);

    @Override // com.amazon.music.downloads.DownloadEventListener
    public void onAdded(String str) {
    }

    public DownloadMonitor(Map<String, ModifiableItem> map, NotificationProvider notificationProvider, ModifiableItemProvider modifiableItemProvider, ModifiableGroupProvider modifiableGroupProvider, Downloader downloader) {
        this.mItemMap = map;
        this.mTotalCount = map.size();
        this.mDownloader = downloader;
        downloader.registerDownloadEventListener(this);
        this.mListener = notificationProvider;
        this.mModifiableGroupProvider = modifiableGroupProvider;
        this.mModifiableItemProvider = modifiableItemProvider;
    }

    @Override // com.amazon.music.downloads.DownloadEventListener
    public void onDownloaded(String str, String str2) {
        ModifiableItem modifiableItem = this.mItemMap.get(str);
        if (modifiableItem == null) {
            return;
        }
        modifiableItem.updateDestinationUriFileName(str2);
        modifiableItem.setDownloadState(DownloadState.DOWNLOADED);
        this.mListener.onDownloaded(new RequestItem(str, RequestItem.RequestType.ITEM, modifiableItem.isBackground()));
        computeAndNotify();
    }

    public boolean shouldWakeUp() {
        return this.mTotalCount <= 0;
    }

    private void computeAndNotify() {
        synchronized (this) {
            this.mTotalCount--;
            this.mLog.debug(" total count " + this.mTotalCount);
            if (shouldWakeUp()) {
                notify();
            }
            if (this.mTotalCount == 0) {
                this.mLog.debug("Unregistering listener because computeAndNotify finds total count == 0.");
                this.mDownloader.unregisterDownloadEventListener(this);
            }
        }
    }

    @Override // com.amazon.music.downloads.DownloadEventListener
    public void onProgress(String str, float f) {
        ModifiableItem modifiableItem = this.mItemMap.get(str);
        if (modifiableItem == null) {
            return;
        }
        if (modifiableItem.getDownloadState() == DownloadState.DOWNLOADING) {
            modifiableItem.setPercentageDownloaded(f);
            this.mListener.onProgressUpdated(new RequestItem(str, RequestItem.RequestType.ITEM, modifiableItem.isBackground()), f);
        }
        updateGroupProgress(modifiableItem.getGroupRequestId());
    }

    @Override // com.amazon.music.downloads.DownloadEventListener
    public void onError(String str, ErrorReason errorReason) {
        ModifiableItem modifiableItem = this.mItemMap.get(str);
        if (modifiableItem == null) {
            return;
        }
        this.mLog.info("Error downloading item with id {}, reason: {}", str, errorReason);
        ErrorReason errorReason2 = ErrorReason.CONTENT_NOT_ELIGIBLE;
        if (errorReason == errorReason2) {
            modifiableItem.setDownloadState(DownloadState.CANCELED);
            modifiableItem.setErrorReason(errorReason2);
            onCanceled(str);
        } else {
            markError(str, modifiableItem, errorReason);
        }
        computeAndNotify();
    }

    @Override // com.amazon.music.downloads.DownloadEventListener
    public void onPaused(String str) {
        ModifiableItem modifiableItem = this.mItemMap.get(str);
        if (modifiableItem == null) {
            return;
        }
        ModifiableGroup group = this.mModifiableGroupProvider.getGroup(modifiableItem.getGroupRequestId());
        String groupRequestId = modifiableItem.getGroupRequestId();
        if ((group != null && group.getDownloadState() == DownloadState.PAUSING) || modifiableItem.getDownloadState() == DownloadState.PAUSING) {
            modifiableItem.setDownloadState(DownloadState.PAUSED_USER);
        } else {
            modifiableItem.setDownloadState(DownloadState.PAUSED_POLICY);
        }
        this.mListener.onPaused(new RequestItem(str, RequestItem.RequestType.ITEM, modifiableItem.isBackground()), modifiableItem.getDownloadState());
        if (modifiableItem.getDownloadState() == DownloadState.PAUSED_USER) {
            computeAndNotify();
        }
        if (this.mTotalCount > 0 && modifiableItem.getDownloadState() == DownloadState.PAUSED_POLICY) {
            this.pauseByPolicyCount++;
        }
        int i = this.mTotalCount;
        if (i <= 0 || this.pauseByPolicyCount != i || group == null) {
            return;
        }
        group.setDownloadState(DownloadState.PAUSED_POLICY);
        this.mListener.onPaused(new RequestItem(groupRequestId, RequestItem.RequestType.GROUP, group.isBackground()), group.getDownloadState());
    }

    @Override // com.amazon.music.downloads.DownloadEventListener
    public void onCanceled(String str) {
        ModifiableGroup group;
        ModifiableItem modifiableItem = this.mItemMap.get(str);
        if (modifiableItem == null) {
            return;
        }
        DownloadState downloadState = DownloadState.CANCELED;
        modifiableItem.setDownloadState(downloadState);
        this.mListener.onCanceled(new RequestItem(str, RequestItem.RequestType.ITEM, modifiableItem.isBackground()));
        if (this.mTotalCount == 1 && (group = this.mModifiableGroupProvider.getGroup(modifiableItem.getGroupRequestId())) != null && group.getDownloadState() == DownloadState.CANCELING) {
            group.setDownloadState(downloadState);
            this.mListener.onCanceled(new RequestItem(modifiableItem.getGroupRequestId(), RequestItem.RequestType.GROUP, group.isBackground()));
        }
        computeAndNotify();
    }

    @Override // com.amazon.music.downloads.DownloadEventListener
    public void onResumed(String str) {
        ModifiableItem modifiableItem = this.mItemMap.get(str);
        if (modifiableItem == null) {
            return;
        }
        ModifiableGroup group = this.mModifiableGroupProvider.getGroup(modifiableItem.getGroupRequestId());
        String groupRequestId = modifiableItem.getGroupRequestId();
        DownloadState downloadState = DownloadState.DOWNLOADING;
        modifiableItem.setDownloadState(downloadState);
        this.mListener.onDownloadStarted(new RequestItem(str, RequestItem.RequestType.ITEM, modifiableItem.isBackground()));
        if (group != null && group.getDownloadState() == DownloadState.PAUSED_POLICY) {
            this.pauseByPolicyCount--;
        }
        if (this.pauseByPolicyCount == 0 && group != null && group.getDownloadState() == DownloadState.PAUSED_POLICY) {
            group.setDownloadState(downloadState);
            this.mListener.onDownloadStarted(new RequestItem(groupRequestId, RequestItem.RequestType.GROUP, group.isBackground()));
        }
    }

    public void updateGroupProgress(String str) {
        ModifiableGroup group = this.mModifiableGroupProvider.getGroup(str);
        if (group == null) {
            return;
        }
        float percentageDownloaded = group.getPercentageDownloaded();
        float fCalculate = GroupDownloadProgressCalculator.calculate(group.getItemMap().values());
        if (fCalculate != percentageDownloaded) {
            group.setPercentageDownloaded(fCalculate);
            this.mListener.onProgressUpdated(new RequestItem(str, RequestItem.RequestType.GROUP, group.isBackground()), fCalculate);
        }
    }

    public boolean markError(String str, ModifiableItem modifiableItem, ErrorReason errorReason) {
        DownloadState downloadState = modifiableItem.getDownloadState();
        DownloadState downloadState2 = DownloadState.ERROR;
        if (downloadState == downloadState2) {
            return true;
        }
        if (modifiableItem.getDownloadState() != DownloadState.DOWNLOADING && modifiableItem.getDownloadState() != DownloadState.QUEUED) {
            return false;
        }
        modifiableItem.setDownloadState(downloadState2);
        modifiableItem.setErrorReason(errorReason);
        this.mListener.onError(new RequestItem(str, RequestItem.RequestType.ITEM, modifiableItem.isBackground()), errorReason);
        return true;
    }
}
