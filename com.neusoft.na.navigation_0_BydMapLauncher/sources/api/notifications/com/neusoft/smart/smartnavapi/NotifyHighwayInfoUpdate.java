package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.HighwayExitInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyHighwayInfoUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064121;

    @SerializedName("args")
    private HighwayExitInfo highwayInfoUpdate;

    public NotifyHighwayInfoUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.HighwayInfoUpdate");
    }

    public NotifyHighwayInfoUpdate(HighwayExitInfo highwayExitInfo) {
        this();
        this.highwayInfoUpdate = highwayExitInfo;
    }

    public HighwayExitInfo getHighwayInfoUpdate() {
        return this.highwayInfoUpdate;
    }

    public NotifyHighwayInfoUpdate setHighwayInfoUpdate(HighwayExitInfo highwayExitInfo) {
        this.highwayInfoUpdate = highwayExitInfo;
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
