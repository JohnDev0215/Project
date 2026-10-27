package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyGetLowFuelStatus extends BaseNotification {
    private static final long serialVersionUID = -1478141320;

    @SerializedName("args")
    private DummyStatus getLowFuelStatus;

    public NotifyGetLowFuelStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.GetLowFuelStatus");
    }

    public NotifyGetLowFuelStatus(DummyStatus dummyStatus) {
        this();
        this.getLowFuelStatus = dummyStatus;
    }

    public DummyStatus getGetLowFuelStatus() {
        return this.getLowFuelStatus;
    }

    public NotifyGetLowFuelStatus setGetLowFuelStatus(DummyStatus dummyStatus) {
        this.getLowFuelStatus = dummyStatus;
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
