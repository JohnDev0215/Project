package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.RouteUpdateInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRouteInfoUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064121;

    @SerializedName("args")
    private RouteUpdateInfo routeInfoUpdate;

    public NotifyRouteInfoUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.RouteInfoUpdate");
    }

    public NotifyRouteInfoUpdate(RouteUpdateInfo routeUpdateInfo) {
        this();
        this.routeInfoUpdate = routeUpdateInfo;
    }

    public RouteUpdateInfo getRouteInfoUpdate() {
        return this.routeInfoUpdate;
    }

    public NotifyRouteInfoUpdate setRouteInfoUpdate(RouteUpdateInfo routeUpdateInfo) {
        this.routeInfoUpdate = routeUpdateInfo;
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
