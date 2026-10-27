package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.TrafficStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyTrafficStatusUpdate extends BaseNotification {
    private static final long serialVersionUID = -1481832698;

    @SerializedName("args")
    private TrafficStatus trafficStatusUpdate;

    public NotifyTrafficStatusUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.TrafficStatusUpdate");
    }

    public NotifyTrafficStatusUpdate(TrafficStatus trafficStatus) {
        this();
        this.trafficStatusUpdate = trafficStatus;
    }

    public TrafficStatus getTrafficStatusUpdate() {
        return this.trafficStatusUpdate;
    }

    public NotifyTrafficStatusUpdate setTrafficStatusUpdate(TrafficStatus trafficStatus) {
        this.trafficStatusUpdate = trafficStatus;
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
