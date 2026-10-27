package com.amazon.music.downloads;

import com.amazon.music.downloads.notification.NotificationInfo;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class ModifiableGroup extends Group implements Modifiable {
    public ModifiableGroup(Group group) {
        super(group.getUri(), group.getItemMap(), group.getNotificationInfo(), group.isBackground());
    }

    public ModifiableGroup(Map<String, ModifiableItem> map, NotificationInfo notificationInfo, boolean z) {
        super(map, notificationInfo, z);
    }

    @Override // com.amazon.music.downloads.Group
    public Map<String, ModifiableItem> getItemMap() {
        return this.mItemMap;
    }

    public void setDownloadState(DownloadState downloadState) {
        this.mDownloadState = downloadState;
    }

    public void setErrorReason(ErrorReason errorReason) {
        this.mErrorReason = errorReason;
    }

    @Override // com.amazon.music.downloads.Modifiable
    public void setPercentageDownloaded(float f) {
        this.mPercentageDownloaded = f;
    }
}
