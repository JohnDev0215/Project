package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class RouteOverviewInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480910053;

    @SerializedName("DistanceToDestination")
    private Integer distanceToDestination = 0;

    @SerializedName("ETA")
    private Integer eTA = 0;

    public RouteOverviewInfo() {
    }

    public RouteOverviewInfo(Integer num, Integer num2) {
        setDistanceToDestination(num);
        setETA(num2);
    }

    public Integer getDistanceToDestination() {
        return this.distanceToDestination;
    }

    public RouteOverviewInfo setDistanceToDestination(Integer num) {
        if (num == null) {
            throw new NullPointerException("distanceToDestination cannot be null");
        }
        this.distanceToDestination = num;
        return this;
    }

    public Integer getETA() {
        return this.eTA;
    }

    public RouteOverviewInfo setETA(Integer num) {
        if (num == null) {
            throw new NullPointerException("eTA cannot be null");
        }
        this.eTA = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RouteOverviewInfo routeOverviewInfo = (RouteOverviewInfo) obj;
        return Objects.equals(this.distanceToDestination, routeOverviewInfo.distanceToDestination) && Objects.equals(this.eTA, routeOverviewInfo.eTA);
    }

    public int hashCode() {
        return Objects.hash(this.distanceToDestination, this.eTA);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
