package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.ADAreaInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyADAreaUpdate extends BaseNotification {
    private static final long serialVersionUID = -1481832583;

    @SerializedName("args")
    private ADAreaInfo aDAreaUpdate;

    public NotifyADAreaUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.ADAreaUpdate");
    }

    public NotifyADAreaUpdate(ADAreaInfo aDAreaInfo) {
        this();
        this.aDAreaUpdate = aDAreaInfo;
    }

    public ADAreaInfo getADAreaUpdate() {
        return this.aDAreaUpdate;
    }

    public NotifyADAreaUpdate setADAreaUpdate(ADAreaInfo aDAreaInfo) {
        this.aDAreaUpdate = aDAreaInfo;
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
