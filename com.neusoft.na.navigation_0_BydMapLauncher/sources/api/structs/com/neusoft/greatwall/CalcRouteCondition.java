package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class CalcRouteCondition implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480909959;

    @SerializedName("ActionType")
    private CalcRouteActionType actionType = CalcRouteActionType.CALC_PLANNING_ROUTE;

    @SerializedName("WaypointList")
    private List<WaypointInfo> waypointList = new ArrayList();

    public CalcRouteCondition() {
    }

    public CalcRouteCondition(CalcRouteActionType calcRouteActionType, List<WaypointInfo> list) {
        setActionType(calcRouteActionType);
        setWaypointList(list);
    }

    public CalcRouteActionType getActionType() {
        return this.actionType;
    }

    public CalcRouteCondition setActionType(CalcRouteActionType calcRouteActionType) {
        this.actionType = calcRouteActionType;
        return this;
    }

    public List<WaypointInfo> getWaypointList() {
        return this.waypointList;
    }

    public CalcRouteCondition setWaypointList(List<WaypointInfo> list) {
        if (list == null) {
            throw new NullPointerException("waypointList cannot be null");
        }
        this.waypointList = list;
        return this;
    }

    public CalcRouteCondition setWaypointList(WaypointInfo... waypointInfoArr) {
        return setWaypointList(Arrays.asList(waypointInfoArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CalcRouteCondition calcRouteCondition = (CalcRouteCondition) obj;
        return Objects.equals(this.actionType, calcRouteCondition.actionType) && Objects.equals(this.waypointList, calcRouteCondition.waypointList);
    }

    public int hashCode() {
        return Objects.hash(this.actionType, this.waypointList);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
