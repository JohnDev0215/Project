package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyApplyUpdateStatus extends BaseNotification {
    private static final long serialVersionUID = -1478141529;

    @SerializedName("args")
    private DummyStatus applyUpdateStatus;

    public NotifyApplyUpdateStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.ApplyUpdateStatus");
    }

    public NotifyApplyUpdateStatus(DummyStatus dummyStatus) {
        this();
        this.applyUpdateStatus = dummyStatus;
    }

    public DummyStatus getApplyUpdateStatus() {
        return this.applyUpdateStatus;
    }

    public NotifyApplyUpdateStatus setApplyUpdateStatus(DummyStatus dummyStatus) {
        this.applyUpdateStatus = dummyStatus;
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
