package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.VRStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifySwitchVRCommand extends BaseNotification {
    private static final long serialVersionUID = -1481831801;

    @SerializedName("args")
    private VRStatus switchVRCommand;

    public NotifySwitchVRCommand() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.SwitchVRCommand");
    }

    public NotifySwitchVRCommand(VRStatus vRStatus) {
        this();
        this.switchVRCommand = vRStatus;
    }

    public VRStatus getSwitchVRCommand() {
        return this.switchVRCommand;
    }

    public NotifySwitchVRCommand setSwitchVRCommand(VRStatus vRStatus) {
        this.switchVRCommand = vRStatus;
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
