package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class RouteUpdateInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912991;

    @SerializedName("RouteSelectedIndex")
    private Integer routeSelectedIndex = 0;

    @SerializedName("RouteInfoList")
    private List<RouteInfo> routeInfoList = new ArrayList();

    public RouteUpdateInfo() {
    }

    public RouteUpdateInfo(Integer num, List<RouteInfo> list) {
        setRouteSelectedIndex(num);
        setRouteInfoList(list);
    }

    public Integer getRouteSelectedIndex() {
        return this.routeSelectedIndex;
    }

    public RouteUpdateInfo setRouteSelectedIndex(Integer num) {
        if (num == null) {
            throw new NullPointerException("routeSelectedIndex cannot be null");
        }
        this.routeSelectedIndex = num;
        return this;
    }

    public List<RouteInfo> getRouteInfoList() {
        return this.routeInfoList;
    }

    public RouteUpdateInfo setRouteInfoList(List<RouteInfo> list) {
        if (list == null) {
            throw new NullPointerException("routeInfoList cannot be null");
        }
        this.routeInfoList = list;
        return this;
    }

    public RouteUpdateInfo setRouteInfoList(RouteInfo... routeInfoArr) {
        return setRouteInfoList(Arrays.asList(routeInfoArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RouteUpdateInfo routeUpdateInfo = (RouteUpdateInfo) obj;
        return Objects.equals(this.routeSelectedIndex, routeUpdateInfo.routeSelectedIndex) && Objects.equals(this.routeInfoList, routeUpdateInfo.routeInfoList);
    }

    public int hashCode() {
        return Objects.hash(this.routeSelectedIndex, this.routeInfoList);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
