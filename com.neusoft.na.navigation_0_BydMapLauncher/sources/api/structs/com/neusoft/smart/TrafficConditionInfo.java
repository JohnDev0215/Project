package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class TrafficConditionInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991300;

    @SerializedName("TotalDistanceM")
    private Integer totalDistanceM = 0;

    @SerializedName("RemainDistanceM")
    private Integer remainDistanceM = 0;

    @SerializedName("TotalTime")
    private Integer totalTime = 0;

    @SerializedName("RemainTime")
    private Integer remainTime = 0;

    @SerializedName("TrafficInfo")
    private List<TrafficInfo> trafficInfo = new ArrayList();

    public TrafficConditionInfo() {
    }

    public TrafficConditionInfo(Integer num, Integer num2, Integer num3, Integer num4, List<TrafficInfo> list) {
        setTotalDistanceM(num);
        setRemainDistanceM(num2);
        setTotalTime(num3);
        setRemainTime(num4);
        setTrafficInfo(list);
    }

    public Integer getTotalDistanceM() {
        return this.totalDistanceM;
    }

    public TrafficConditionInfo setTotalDistanceM(Integer num) {
        if (num == null) {
            throw new NullPointerException("totalDistanceM cannot be null");
        }
        this.totalDistanceM = num;
        return this;
    }

    public Integer getRemainDistanceM() {
        return this.remainDistanceM;
    }

    public TrafficConditionInfo setRemainDistanceM(Integer num) {
        if (num == null) {
            throw new NullPointerException("remainDistanceM cannot be null");
        }
        this.remainDistanceM = num;
        return this;
    }

    public Integer getTotalTime() {
        return this.totalTime;
    }

    public TrafficConditionInfo setTotalTime(Integer num) {
        if (num == null) {
            throw new NullPointerException("totalTime cannot be null");
        }
        this.totalTime = num;
        return this;
    }

    public Integer getRemainTime() {
        return this.remainTime;
    }

    public TrafficConditionInfo setRemainTime(Integer num) {
        if (num == null) {
            throw new NullPointerException("remainTime cannot be null");
        }
        this.remainTime = num;
        return this;
    }

    public List<TrafficInfo> getTrafficInfo() {
        return this.trafficInfo;
    }

    public TrafficConditionInfo setTrafficInfo(List<TrafficInfo> list) {
        if (list == null) {
            throw new NullPointerException("trafficInfo cannot be null");
        }
        this.trafficInfo = list;
        return this;
    }

    public TrafficConditionInfo setTrafficInfo(TrafficInfo... trafficInfoArr) {
        return setTrafficInfo(Arrays.asList(trafficInfoArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TrafficConditionInfo trafficConditionInfo = (TrafficConditionInfo) obj;
        return Objects.equals(this.totalDistanceM, trafficConditionInfo.totalDistanceM) && Objects.equals(this.remainDistanceM, trafficConditionInfo.remainDistanceM) && Objects.equals(this.totalTime, trafficConditionInfo.totalTime) && Objects.equals(this.remainTime, trafficConditionInfo.remainTime) && Objects.equals(this.trafficInfo, trafficConditionInfo.trafficInfo);
    }

    public int hashCode() {
        return Objects.hash(this.totalDistanceM, this.remainDistanceM, this.totalTime, this.remainTime, this.trafficInfo);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
