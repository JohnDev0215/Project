package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.LaneInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyLaneInfoStatus extends BaseNotification {
    private static final long serialVersionUID = -1480914019;

    @SerializedName("args")
    private LaneInfo laneInfoStatus;

    public NotifyLaneInfoStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.LaneInfoStatus");
    }

    public NotifyLaneInfoStatus(LaneInfo laneInfo) {
        this();
        this.laneInfoStatus = laneInfo;
    }

    public LaneInfo getLaneInfoStatus() {
        return this.laneInfoStatus;
    }

    public NotifyLaneInfoStatus setLaneInfoStatus(LaneInfo laneInfo) {
        this.laneInfoStatus = laneInfo;
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
