package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class CommutePushInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912875;

    @SerializedName("Type")
    private CommuteType type = CommuteType.COMMUTE_GO_HOME;

    @SerializedName("TotalDistanceM")
    private Integer totalDistanceM = 0;

    @SerializedName("EtaS")
    private Integer etaS = 0;

    @SerializedName("TrafficInfo")
    private List<TrafficInfo> trafficInfo = new ArrayList();

    public CommutePushInfo() {
    }

    public CommutePushInfo(CommuteType commuteType, Integer num, Integer num2, List<TrafficInfo> list) {
        setType(commuteType);
        setTotalDistanceM(num);
        setEtaS(num2);
        setTrafficInfo(list);
    }

    public CommuteType getType() {
        return this.type;
    }

    public CommutePushInfo setType(CommuteType commuteType) {
        this.type = commuteType;
        return this;
    }

    public Integer getTotalDistanceM() {
        return this.totalDistanceM;
    }

    public CommutePushInfo setTotalDistanceM(Integer num) {
        if (num == null) {
            throw new NullPointerException("totalDistanceM cannot be null");
        }
        this.totalDistanceM = num;
        return this;
    }

    public Integer getEtaS() {
        return this.etaS;
    }

    public CommutePushInfo setEtaS(Integer num) {
        if (num == null) {
            throw new NullPointerException("etaS cannot be null");
        }
        this.etaS = num;
        return this;
    }

    public List<TrafficInfo> getTrafficInfo() {
        return this.trafficInfo;
    }

    public CommutePushInfo setTrafficInfo(List<TrafficInfo> list) {
        if (list == null) {
            throw new NullPointerException("trafficInfo cannot be null");
        }
        this.trafficInfo = list;
        return this;
    }

    public CommutePushInfo setTrafficInfo(TrafficInfo... trafficInfoArr) {
        return setTrafficInfo(Arrays.asList(trafficInfoArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CommutePushInfo commutePushInfo = (CommutePushInfo) obj;
        return Objects.equals(this.type, commutePushInfo.type) && Objects.equals(this.totalDistanceM, commutePushInfo.totalDistanceM) && Objects.equals(this.etaS, commutePushInfo.etaS) && Objects.equals(this.trafficInfo, commutePushInfo.trafficInfo);
    }

    public int hashCode() {
        return Objects.hash(this.type, this.totalDistanceM, this.etaS, this.trafficInfo);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
