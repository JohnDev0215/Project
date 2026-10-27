package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.RouteCalculationState;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyCalcPlanningRouteStatus extends BaseNotification {
    private static final long serialVersionUID = -1480914019;

    @SerializedName("args")
    private RouteCalculationState calcPlanningRouteStatus;

    public NotifyCalcPlanningRouteStatus() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.CalcPlanningRouteStatus");
    }

    public NotifyCalcPlanningRouteStatus(RouteCalculationState routeCalculationState) {
        this();
        this.calcPlanningRouteStatus = routeCalculationState;
    }

    public RouteCalculationState getCalcPlanningRouteStatus() {
        return this.calcPlanningRouteStatus;
    }

    public NotifyCalcPlanningRouteStatus setCalcPlanningRouteStatus(RouteCalculationState routeCalculationState) {
        this.calcPlanningRouteStatus = routeCalculationState;
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
