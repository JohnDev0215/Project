package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.TrafficConditionInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyTrafficConditionUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064121;

    @SerializedName("args")
    private TrafficConditionInfo trafficConditionUpdate;

    public NotifyTrafficConditionUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.TrafficConditionUpdate");
    }

    public NotifyTrafficConditionUpdate(TrafficConditionInfo trafficConditionInfo) {
        this();
        this.trafficConditionUpdate = trafficConditionInfo;
    }

    public TrafficConditionInfo getTrafficConditionUpdate() {
        return this.trafficConditionUpdate;
    }

    public NotifyTrafficConditionUpdate setTrafficConditionUpdate(TrafficConditionInfo trafficConditionInfo) {
        this.trafficConditionUpdate = trafficConditionInfo;
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
