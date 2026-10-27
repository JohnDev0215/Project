package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.LanesInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyLanesInfoUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064063;

    @SerializedName("args")
    private LanesInfo lanesInfoUpdate;

    public NotifyLanesInfoUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.LanesInfoUpdate");
    }

    public NotifyLanesInfoUpdate(LanesInfo lanesInfo) {
        this();
        this.lanesInfoUpdate = lanesInfo;
    }

    public LanesInfo getLanesInfoUpdate() {
        return this.lanesInfoUpdate;
    }

    public NotifyLanesInfoUpdate setLanesInfoUpdate(LanesInfo lanesInfo) {
        this.lanesInfoUpdate = lanesInfo;
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
