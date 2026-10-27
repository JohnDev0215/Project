package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.SpeedInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifySpeedLimitInfoUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064026;

    @SerializedName("args")
    private SpeedInfo speedLimitInfoUpdate;

    public NotifySpeedLimitInfoUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.SpeedLimitInfoUpdate");
    }

    public NotifySpeedLimitInfoUpdate(SpeedInfo speedInfo) {
        this();
        this.speedLimitInfoUpdate = speedInfo;
    }

    public SpeedInfo getSpeedLimitInfoUpdate() {
        return this.speedLimitInfoUpdate;
    }

    public NotifySpeedLimitInfoUpdate setSpeedLimitInfoUpdate(SpeedInfo speedInfo) {
        this.speedLimitInfoUpdate = speedInfo;
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
