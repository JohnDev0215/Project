package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.DeadReckoningData;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyDeadReckoningStatus extends BaseNotification {
    private static final long serialVersionUID = -1479064026;

    @SerializedName("args")
    private DeadReckoningData deadReckoningStatus;

    public NotifyDeadReckoningStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.DeadReckoningStatus");
    }

    public NotifyDeadReckoningStatus(DeadReckoningData deadReckoningData) {
        this();
        this.deadReckoningStatus = deadReckoningData;
    }

    public DeadReckoningData getDeadReckoningStatus() {
        return this.deadReckoningStatus;
    }

    public NotifyDeadReckoningStatus setDeadReckoningStatus(DeadReckoningData deadReckoningData) {
        this.deadReckoningStatus = deadReckoningData;
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
