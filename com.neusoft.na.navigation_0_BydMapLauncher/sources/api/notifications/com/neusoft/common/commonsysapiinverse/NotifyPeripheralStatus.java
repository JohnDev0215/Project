package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.PeripheralType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyPeripheralStatus extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private PeripheralType peripheralStatus;

    public NotifyPeripheralStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.PeripheralStatus");
    }

    public NotifyPeripheralStatus(PeripheralType peripheralType) {
        this();
        this.peripheralStatus = peripheralType;
    }

    public PeripheralType getPeripheralStatus() {
        return this.peripheralStatus;
    }

    public NotifyPeripheralStatus setPeripheralStatus(PeripheralType peripheralType) {
        this.peripheralStatus = peripheralType;
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
