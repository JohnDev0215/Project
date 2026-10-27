package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.ServiceStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyServiceStatusUpdate extends BaseNotification {
    private static final long serialVersionUID = -1481832583;

    @SerializedName("args")
    private ServiceStatus serviceStatusUpdate;

    public NotifyServiceStatusUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.ServiceStatusUpdate");
    }

    public NotifyServiceStatusUpdate(ServiceStatus serviceStatus) {
        this();
        this.serviceStatusUpdate = serviceStatus;
    }

    public ServiceStatus getServiceStatusUpdate() {
        return this.serviceStatusUpdate;
    }

    public NotifyServiceStatusUpdate setServiceStatusUpdate(ServiceStatus serviceStatus) {
        this.serviceStatusUpdate = serviceStatus;
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
