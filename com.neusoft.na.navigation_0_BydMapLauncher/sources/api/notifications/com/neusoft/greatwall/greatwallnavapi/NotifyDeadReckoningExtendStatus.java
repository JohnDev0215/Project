package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.DeadReckoningDataExtend;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyDeadReckoningExtendStatus extends BaseNotification {
    private static final long serialVersionUID = -1480913982;

    @SerializedName("args")
    private DeadReckoningDataExtend deadReckoningExtendStatus;

    public NotifyDeadReckoningExtendStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.DeadReckoningExtendStatus");
    }

    public NotifyDeadReckoningExtendStatus(DeadReckoningDataExtend deadReckoningDataExtend) {
        this();
        this.deadReckoningExtendStatus = deadReckoningDataExtend;
    }

    public DeadReckoningDataExtend getDeadReckoningExtendStatus() {
        return this.deadReckoningExtendStatus;
    }

    public NotifyDeadReckoningExtendStatus setDeadReckoningExtendStatus(DeadReckoningDataExtend deadReckoningDataExtend) {
        this.deadReckoningExtendStatus = deadReckoningDataExtend;
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
