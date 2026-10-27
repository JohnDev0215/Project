package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class TrafficInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991300;

    @SerializedName("TmcStatus")
    private RoadTrafficStatus tmcStatus = RoadTrafficStatus.NO_TMC_DATA;

    @SerializedName("SegIndex")
    private Integer segIndex = 0;

    @SerializedName("Percent")
    private Double percent = Double.valueOf(0.0d);

    @SerializedName("TmcTimeS")
    private Integer tmcTimeS = 0;

    @SerializedName("TmcDistance")
    private Integer tmcDistance = 0;

    public TrafficInfo() {
    }

    public TrafficInfo(RoadTrafficStatus roadTrafficStatus, Integer num, Double d, Integer num2, Integer num3) {
        setTmcStatus(roadTrafficStatus);
        setSegIndex(num);
        setPercent(d);
        setTmcTimeS(num2);
        setTmcDistance(num3);
    }

    public RoadTrafficStatus getTmcStatus() {
        return this.tmcStatus;
    }

    public TrafficInfo setTmcStatus(RoadTrafficStatus roadTrafficStatus) {
        this.tmcStatus = roadTrafficStatus;
        return this;
    }

    public Integer getSegIndex() {
        return this.segIndex;
    }

    public TrafficInfo setSegIndex(Integer num) {
        if (num == null) {
            throw new NullPointerException("segIndex cannot be null");
        }
        this.segIndex = num;
        return this;
    }

    public Double getPercent() {
        return this.percent;
    }

    public TrafficInfo setPercent(Double d) {
        if (d == null) {
            throw new NullPointerException("percent cannot be null");
        }
        this.percent = d;
        return this;
    }

    public Integer getTmcTimeS() {
        return this.tmcTimeS;
    }

    public TrafficInfo setTmcTimeS(Integer num) {
        if (num == null) {
            throw new NullPointerException("tmcTimeS cannot be null");
        }
        this.tmcTimeS = num;
        return this;
    }

    public Integer getTmcDistance() {
        return this.tmcDistance;
    }

    public TrafficInfo setTmcDistance(Integer num) {
        if (num == null) {
            throw new NullPointerException("tmcDistance cannot be null");
        }
        this.tmcDistance = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TrafficInfo trafficInfo = (TrafficInfo) obj;
        return Objects.equals(this.tmcStatus, trafficInfo.tmcStatus) && Objects.equals(this.segIndex, trafficInfo.segIndex) && Objects.equals(this.percent, trafficInfo.percent) && Objects.equals(this.tmcTimeS, trafficInfo.tmcTimeS) && Objects.equals(this.tmcDistance, trafficInfo.tmcDistance);
    }

    public int hashCode() {
        return Objects.hash(this.tmcStatus, this.segIndex, this.percent, this.tmcTimeS, this.tmcDistance);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
