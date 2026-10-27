package api.structs.com.neusoft.byd;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;

/* JADX INFO: loaded from: classes2.dex */
public class RouteInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1481838349;

    @SerializedName("Id")
    private String id = "";

    @SerializedName(AnnotatedPrivateKey.LABEL)
    private String label = "";

    @SerializedName("routeOption")
    private StrategyType routeOption = StrategyType.INVALID;

    @SerializedName("time")
    private Integer time = 0;

    @SerializedName("distance")
    private Double distance = Double.valueOf(0.0d);

    public RouteInfo() {
    }

    public RouteInfo(String str, String str2, StrategyType strategyType, Integer num, Double d) {
        setId(str);
        setLabel(str2);
        setRouteOption(strategyType);
        setTime(num);
        setDistance(d);
    }

    public String getId() {
        return this.id;
    }

    public RouteInfo setId(String str) {
        if (str == null) {
            throw new NullPointerException("id cannot be null");
        }
        this.id = str;
        return this;
    }

    public String getLabel() {
        return this.label;
    }

    public RouteInfo setLabel(String str) {
        if (str == null) {
            throw new NullPointerException("label cannot be null");
        }
        this.label = str;
        return this;
    }

    public StrategyType getRouteOption() {
        return this.routeOption;
    }

    public RouteInfo setRouteOption(StrategyType strategyType) {
        this.routeOption = strategyType;
        return this;
    }

    public Integer getTime() {
        return this.time;
    }

    public RouteInfo setTime(Integer num) {
        if (num == null) {
            throw new NullPointerException("time cannot be null");
        }
        this.time = num;
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

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RouteInfo routeInfo = (RouteInfo) obj;
        return Objects.equals(this.id, routeInfo.id) && Objects.equals(this.label, routeInfo.label) && Objects.equals(this.routeOption, routeInfo.routeOption) && Objects.equals(this.time, routeInfo.time) && Objects.equals(this.distance, routeInfo.distance);
    }

    public int hashCode() {
        return Objects.hash(this.id, this.label, this.routeOption, this.time, this.distance);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
