package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.VRCommand;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyVRMsgCommandUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064063;

    @SerializedName("args")
    private VRCommand vRMsgCommandUpdate;

    public NotifyVRMsgCommandUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.VRMsgCommandUpdate");
    }

    public NotifyVRMsgCommandUpdate(VRCommand vRCommand) {
        this();
        this.vRMsgCommandUpdate = vRCommand;
    }

    public VRCommand getVRMsgCommandUpdate() {
        return this.vRMsgCommandUpdate;
    }

    public NotifyVRMsgCommandUpdate setVRMsgCommandUpdate(VRCommand vRCommand) {
        this.vRMsgCommandUpdate = vRCommand;
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
