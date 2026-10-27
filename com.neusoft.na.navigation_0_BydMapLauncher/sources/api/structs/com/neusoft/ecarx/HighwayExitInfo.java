package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class HighwayExitInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912991;

    @SerializedName("ExitCount")
    private Integer exitCount = 0;

    @SerializedName("ExitNumStr")
    private String exitNumStr = "";

    @SerializedName("ExitDirection")
    private String exitDirection = "";

    @SerializedName("EtaDistanceM")
    private Integer etaDistanceM = 0;

    @SerializedName("EtaTime")
    private Integer etaTime = 0;

    public HighwayExitInfo() {
    }

    public HighwayExitInfo(Integer num, String str, String str2, Integer num2, Integer num3) {
        setExitCount(num);
        setExitNumStr(str);
        setExitDirection(str2);
        setEtaDistanceM(num2);
        setEtaTime(num3);
    }

    public Integer getExitCount() {
        return this.exitCount;
    }

    public HighwayExitInfo setExitCount(Integer num) {
        if (num == null) {
            throw new NullPointerException("exitCount cannot be null");
        }
        this.exitCount = num;
        return this;
    }

    public String getExitNumStr() {
        return this.exitNumStr;
    }

    public HighwayExitInfo setExitNumStr(String str) {
        if (str == null) {
            throw new NullPointerException("exitNumStr cannot be null");
        }
        this.exitNumStr = str;
        return this;
    }

    public String getExitDirection() {
        return this.exitDirection;
    }

    public HighwayExitInfo setExitDirection(String str) {
        if (str == null) {
            throw new NullPointerException("exitDirection cannot be null");
        }
        this.exitDirection = str;
        return this;
    }

    public Integer getEtaDistanceM() {
        return this.etaDistanceM;
    }

    public HighwayExitInfo setEtaDistanceM(Integer num) {
        if (num == null) {
            throw new NullPointerException("etaDistanceM cannot be null");
        }
        this.etaDistanceM = num;
        return this;
    }

    public Integer getEtaTime() {
        return this.etaTime;
    }

    public HighwayExitInfo setEtaTime(Integer num) {
        if (num == null) {
            throw new NullPointerException("etaTime cannot be null");
        }
        this.etaTime = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HighwayExitInfo highwayExitInfo = (HighwayExitInfo) obj;
        return Objects.equals(this.exitCount, highwayExitInfo.exitCount) && Objects.equals(this.exitNumStr, highwayExitInfo.exitNumStr) && Objects.equals(this.exitDirection, highwayExitInfo.exitDirection) && Objects.equals(this.etaDistanceM, highwayExitInfo.etaDistanceM) && Objects.equals(this.etaTime, highwayExitInfo.etaTime);
    }

    public int hashCode() {
        return Objects.hash(this.exitCount, this.exitNumStr, this.exitDirection, this.etaDistanceM, this.etaTime);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
