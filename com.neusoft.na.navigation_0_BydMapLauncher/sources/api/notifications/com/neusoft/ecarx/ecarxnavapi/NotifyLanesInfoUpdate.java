package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.LanesInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyLanesInfoUpdate extends BaseNotification {
    private static final long serialVersionUID = -1481832583;

    @SerializedName("args")
    private LanesInfo lanesInfoUpdate;

    public NotifyLanesInfoUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.LanesInfoUpdate");
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
