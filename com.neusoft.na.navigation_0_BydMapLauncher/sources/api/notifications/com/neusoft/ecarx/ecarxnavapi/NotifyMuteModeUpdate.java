package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.MuteStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyMuteModeUpdate extends BaseNotification {
    private static final long serialVersionUID = -1481832640;

    @SerializedName("args")
    private MuteStatus muteModeUpdate;

    public NotifyMuteModeUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.MuteModeUpdate");
    }

    public NotifyMuteModeUpdate(MuteStatus muteStatus) {
        this();
        this.muteModeUpdate = muteStatus;
    }

    public MuteStatus getMuteModeUpdate() {
        return this.muteModeUpdate;
    }

    public NotifyMuteModeUpdate setMuteModeUpdate(MuteStatus muteStatus) {
        this.muteModeUpdate = muteStatus;
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
