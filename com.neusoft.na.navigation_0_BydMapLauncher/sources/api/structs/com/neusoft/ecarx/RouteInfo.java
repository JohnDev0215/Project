package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class RouteInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912933;

    @SerializedName("Distance")
    private Double distance;

    @SerializedName("DistanceAutoM")
    private String distanceAutoM;

    @SerializedName("RouteTag")
    private String routeTag = "";

    @SerializedName("Strategy")
    private Integer strategy = 0;

    @SerializedName("Time")
    private Double time;

    @SerializedName("TimeAuto")
    private String timeAuto;

    @SerializedName("Tolls")
    private Integer tolls;

    @SerializedName("TrafficInfos")
    private List<TrafficInfo> trafficInfos;

    @SerializedName("TrafficLights")
    private Integer trafficLights;

    public RouteInfo() {
        Double dValueOf = Double.valueOf(0.0d);
        this.time = dValueOf;
        this.distance = dValueOf;
        this.timeAuto = "";
        this.distanceAutoM = "";
        this.trafficLights = 0;
        this.tolls = 0;
        this.trafficInfos = new ArrayList();
    }

    public RouteInfo(String str, Integer num, Double d, Double d2, String str2, String str3, Integer num2, Integer num3, List<TrafficInfo> list) {
        Double dValueOf = Double.valueOf(0.0d);
        this.time = dValueOf;
        this.distance = dValueOf;
        this.timeAuto = "";
        this.distanceAutoM = "";
        this.trafficLights = 0;
        this.tolls = 0;
        this.trafficInfos = new ArrayList();
        setRouteTag(str);
        setStrategy(num);
        setTime(d);
        setDistance(d2);
        setTimeAuto(str2);
        setDistanceAutoM(str3);
        setTrafficLights(num2);
        setTolls(num3);
        setTrafficInfos(list);
    }

    public String getRouteTag() {
        return this.routeTag;
    }

    public RouteInfo setRouteTag(String str) {
        if (str == null) {
            throw new NullPointerException("routeTag cannot be null");
        }
        this.routeTag = str;
        return this;
    }

    public Integer getStrategy() {
        return this.strategy;
    }

    public RouteInfo setStrategy(Integer num) {
        if (num == null) {
            throw new NullPointerException("strategy cannot be null");
        }
        this.strategy = num;
        return this;
    }

    public Double getTime() {
        return this.time;
    }

    public RouteInfo setTime(Double d) {
        if (d == null) {
            throw new NullPointerException("time cannot be null");
        }
        this.time = d;
        return this;
    }

    public Double getDistance() {
        return this.distance;
    }

    public RouteInfo setDistance(Double d) {
        if (d == null) {
            throw new NullPointerException("distance cannot be null");
        }
        this.distance = d;
        return this;
    }

    public String getTimeAuto() {
        return this.timeAuto;
    }

    public RouteInfo setTimeAuto(String str) {
        if (str == null) {
            throw new NullPointerException("timeAuto cannot be null");
        }
        this.timeAuto = str;
        return this;
    }

    public String getDistanceAutoM() {
        return this.distanceAutoM;
    }

    public RouteInfo setDistanceAutoM(String str) {
        if (str == null) {
            throw new NullPointerException("distanceAutoM cannot be null");
        }
        this.distanceAutoM = str;
        return this;
    }

    public Integer getTrafficLights() {
        return this.trafficLights;
    }

    public RouteInfo setTrafficLights(Integer num) {
        if (num == null) {
            throw new NullPointerException("trafficLights cannot be null");
        }
        this.trafficLights = num;
        return this;
    }

    public Integer getTolls() {
        return this.tolls;
    }

    public RouteInfo setTolls(Integer num) {
        if (num == null) {
            throw new NullPointerException("tolls cannot be null");
        }
        this.tolls = num;
        return this;
    }

    public List<TrafficInfo> getTrafficInfos() {
        return this.trafficInfos;
    }

    public RouteInfo setTrafficInfos(List<TrafficInfo> list) {
        if (list == null) {
            throw new NullPointerException("trafficInfos cannot be null");
        }
        this.trafficInfos = list;
        return this;
    }

    public RouteInfo setTrafficInfos(TrafficInfo... trafficInfoArr) {
        return setTrafficInfos(Arrays.asList(trafficInfoArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RouteInfo routeInfo = (RouteInfo) obj;
        return Objects.equals(this.routeTag, routeInfo.routeTag) && Objects.equals(this.strategy, routeInfo.strategy) && Objects.equals(this.time, routeInfo.time) && Objects.equals(this.distance, routeInfo.distance) && Objects.equals(this.timeAuto, routeInfo.timeAuto) && Objects.equals(this.distanceAutoM, routeInfo.distanceAutoM) && Objects.equals(this.trafficLights, routeInfo.trafficLights) && Objects.equals(this.tolls, routeInfo.tolls) && Objects.equals(this.trafficInfos, routeInfo.trafficInfos);
    }

    public int hashCode() {
        return Objects.hash(this.routeTag, this.strategy, this.time, this.distance, this.timeAuto, this.distanceAutoM, this.trafficLights, this.tolls, this.trafficInfos);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
