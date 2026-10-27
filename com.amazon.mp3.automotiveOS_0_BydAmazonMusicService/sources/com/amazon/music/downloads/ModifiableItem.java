package com.amazon.music.downloads;

/* JADX INFO: loaded from: classes4.dex */
public class ModifiableItem extends Item implements Modifiable {
    private String mGroupRequestId;

    public ModifiableItem(Item item) {
        super(item.getAsin(), item.getLuid(), item.isOwned(), item.getDestinationUri(), item.getDestinationDirectory(), item.getNotificationInfo(), item.getPriorityInfo(), item.isBackground(), item.getOriginalDownloadState());
    }

    public String getGroupRequestId() {
        return this.mGroupRequestId;
    }

    public void setGroupRequestId(String str) {
        this.mGroupRequestId = str;
    }

    public void setDownloadState(DownloadState downloadState) {
        this.mDownloadState = downloadState;
    }

    @Override // com.amazon.music.downloads.Modifiable
    public void setPercentageDownloaded(float f) {
        this.mPercentageDownloaded = f;
    }

    public void setErrorReason(ErrorReason errorReason) {
        this.mErrorReason = errorReason;
    }
}
