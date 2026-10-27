package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyMonitorCOMMStatus extends BaseNotification {
    private static final long serialVersionUID = -1478141435;

    @SerializedName("args")
    private DummyStatus monitorCOMMStatus;

    public NotifyMonitorCOMMStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.MonitorCOMMStatus");
    }

    public NotifyMonitorCOMMStatus(DummyStatus dummyStatus) {
        this();
        this.monitorCOMMStatus = dummyStatus;
    }

    public DummyStatus getMonitorCOMMStatus() {
        return this.monitorCOMMStatus;
    }

    public NotifyMonitorCOMMStatus setMonitorCOMMStatus(DummyStatus dummyStatus) {
        this.monitorCOMMStatus = dummyStatus;
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
