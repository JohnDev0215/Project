package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.NaviStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyNaviStatusUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064808;

    @SerializedName("args")
    private NaviStatus naviStatusUpdate;

    public NotifyNaviStatusUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.NaviStatusUpdate");
    }

    public NotifyNaviStatusUpdate(NaviStatus naviStatus) {
        this();
        this.naviStatusUpdate = naviStatus;
    }

    public NaviStatus getNaviStatusUpdate() {
        return this.naviStatusUpdate;
    }

    public NotifyNaviStatusUpdate setNaviStatusUpdate(NaviStatus naviStatus) {
        this.naviStatusUpdate = naviStatus;
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
