package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.BatteryPreheatingInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyBatteryPreheatingInfoUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479063969;

    @SerializedName("args")
    private BatteryPreheatingInfo batteryPreheatingInfoUpdate;

    public NotifyBatteryPreheatingInfoUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.BatteryPreheatingInfoUpdate");
    }

    public NotifyBatteryPreheatingInfoUpdate(BatteryPreheatingInfo batteryPreheatingInfo) {
        this();
        this.batteryPreheatingInfoUpdate = batteryPreheatingInfo;
    }

    public BatteryPreheatingInfo getBatteryPreheatingInfoUpdate() {
        return this.batteryPreheatingInfoUpdate;
    }

    public NotifyBatteryPreheatingInfoUpdate setBatteryPreheatingInfoUpdate(BatteryPreheatingInfo batteryPreheatingInfo) {
        this.batteryPreheatingInfoUpdate = batteryPreheatingInfo;
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
