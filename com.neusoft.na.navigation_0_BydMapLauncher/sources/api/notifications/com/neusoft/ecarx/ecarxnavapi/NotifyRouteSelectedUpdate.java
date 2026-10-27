package api.notifications.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.RouteUpdateInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRouteSelectedUpdate extends BaseNotification {
    private static final long serialVersionUID = -1481832640;

    @SerializedName("args")
    private RouteUpdateInfo routeSelectedUpdate;

    public NotifyRouteSelectedUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.RouteSelectedUpdate");
    }

    public NotifyRouteSelectedUpdate(RouteUpdateInfo routeUpdateInfo) {
        this();
        this.routeSelectedUpdate = routeUpdateInfo;
    }

    public RouteUpdateInfo getRouteSelectedUpdate() {
        return this.routeSelectedUpdate;
    }

    public NotifyRouteSelectedUpdate setRouteSelectedUpdate(RouteUpdateInfo routeUpdateInfo) {
        this.routeSelectedUpdate = routeUpdateInfo;
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
