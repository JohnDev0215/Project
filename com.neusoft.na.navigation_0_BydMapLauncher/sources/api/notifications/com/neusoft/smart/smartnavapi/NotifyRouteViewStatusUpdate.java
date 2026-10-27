package api.notifications.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.RouteViewStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRouteViewStatusUpdate extends BaseNotification {
    private static final long serialVersionUID = -1479064808;

    @SerializedName("args")
    private RouteViewStatus routeViewStatusUpdate;

    public NotifyRouteViewStatusUpdate() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.smart.SmartNavApi.RouteViewStatusUpdate");
    }

    public NotifyRouteViewStatusUpdate(RouteViewStatus routeViewStatus) {
        this();
        this.routeViewStatusUpdate = routeViewStatus;
    }

    public RouteViewStatus getRouteViewStatusUpdate() {
        return this.routeViewStatusUpdate;
    }

    public NotifyRouteViewStatusUpdate setRouteViewStatusUpdate(RouteViewStatus routeViewStatus) {
        this.routeViewStatusUpdate = routeViewStatus;
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
