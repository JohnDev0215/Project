package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.SpaceInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyStorageSpaceStatus extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private SpaceInfo storageSpaceStatus;

    public NotifyStorageSpaceStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.StorageSpaceStatus");
    }

    public NotifyStorageSpaceStatus(SpaceInfo spaceInfo) {
        this();
        this.storageSpaceStatus = spaceInfo;
    }

    public SpaceInfo getStorageSpaceStatus() {
        return this.storageSpaceStatus;
    }

    public NotifyStorageSpaceStatus setStorageSpaceStatus(SpaceInfo spaceInfo) {
        this.storageSpaceStatus = spaceInfo;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public EProjectInterfaceType getProjectInterfaceType() {
        return EProjectInterfaceType.OneCore;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public String toString() {
        return getProjectInterfaceType().getGson().toJson(this);
    }
}
