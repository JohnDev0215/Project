package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LaneInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912875;

    @SerializedName("LaneIndex")
    private Integer laneIndex = 0;

    @SerializedName("LaneIconId")
    private Integer laneIconId = 0;

    public LaneInfo() {
    }

    public LaneInfo(Integer num, Integer num2) {
        setLaneIndex(num);
        setLaneIconId(num2);
    }

    public Integer getLaneIndex() {
        return this.laneIndex;
    }

    public LaneInfo setLaneIndex(Integer num) {
        if (num == null) {
            throw new NullPointerException("laneIndex cannot be null");
        }
        this.laneIndex = num;
        return this;
    }

    public Integer getLaneIconId() {
        return this.laneIconId;
    }

    public LaneInfo setLaneIconId(Integer num) {
        if (num == null) {
            throw new NullPointerException("laneIconId cannot be null");
        }
        this.laneIconId = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LaneInfo laneInfo = (LaneInfo) obj;
        return Objects.equals(this.laneIndex, laneInfo.laneIndex) && Objects.equals(this.laneIconId, laneInfo.laneIconId);
    }

    public int hashCode() {
        return Objects.hash(this.laneIndex, this.laneIconId);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
