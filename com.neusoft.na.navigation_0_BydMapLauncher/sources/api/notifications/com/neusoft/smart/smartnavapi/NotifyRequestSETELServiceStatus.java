package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.SETELServiceStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRequestSETELServiceStatus extends BaseNotification {
    private static final long serialVersionUID = -1479064063;

    @SerializedName("args")
    private SETELServiceStatus requestSETELServiceStatus;

    public NotifyRequestSETELServiceStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.RequestSETELServiceStatus");
    }

    public NotifyRequestSETELServiceStatus(SETELServiceStatus sETELServiceStatus) {
        this();
        this.requestSETELServiceStatus = sETELServiceStatus;
    }

    public SETELServiceStatus getRequestSETELServiceStatus() {
        return this.requestSETELServiceStatus;
    }

    public NotifyRequestSETELServiceStatus setRequestSETELServiceStatus(SETELServiceStatus sETELServiceStatus) {
        this.requestSETELServiceStatus = sETELServiceStatus;
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
