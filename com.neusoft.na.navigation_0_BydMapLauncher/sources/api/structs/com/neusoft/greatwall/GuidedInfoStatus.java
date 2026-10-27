package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GuidedInfoStatus implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480910017;

    @SerializedName("TurnId")
    private Integer turnId = 0;

    @SerializedName("RemainingDistance")
    private Integer remainingDistance = 0;

    @SerializedName("RemainingDistanceStr")
    private String remainingDistanceStr = "";

    @SerializedName("TurnDistance")
    private Integer turnDistance = 0;

    @SerializedName("TurnDistanceStr")
    private String turnDistanceStr = "";

    @SerializedName("RemainingTime")
    private Integer remainingTime = 0;

    @SerializedName("RemainingTimeStr")
    private String remainingTimeStr = "";

    @SerializedName("ArrivalTimeStr")
    private String arrivalTimeStr = "";

    @SerializedName("CurrentRoadName")
    private String currentRoadName = "";

    @SerializedName("ExitNumOfHighway")
    private String exitNumOfHighway = "";

    @SerializedName("NextRoadName")
    private String nextRoadName = "";

    @SerializedName("NextTurnId")
    private Integer nextTurnId = 0;

    @SerializedName("NextTurnDistance")
    private Integer nextTurnDistance = 0;

    @SerializedName("NextTurnDistanceStr")
    private String nextTurnDistanceStr = "";

    @SerializedName("RemainingBatteryPower")
    private Integer remainingBatteryPower = 0;

    @SerializedName("RemainingBatteryPowerStr")
    private String remainingBatteryPowerStr = "";

    public GuidedInfoStatus() {
    }

    public GuidedInfoStatus(Integer num, Integer num2, String str, Integer num3, String str2, Integer num4, String str3, String str4, String str5, String str6, String str7, Integer num5, Integer num6, String str8, Integer num7, String str9) {
        setTurnId(num);
        setRemainingDistance(num2);
        setRemainingDistanceStr(str);
        setTurnDistance(num3);
        setTurnDistanceStr(str2);
        setRemainingTime(num4);
        setRemainingTimeStr(str3);
        setArrivalTimeStr(str4);
        setCurrentRoadName(str5);
        setExitNumOfHighway(str6);
        setNextRoadName(str7);
        setNextTurnId(num5);
        setNextTurnDistance(num6);
        setNextTurnDistanceStr(str8);
        setRemainingBatteryPower(num7);
        setRemainingBatteryPowerStr(str9);
    }

    public Integer getTurnId() {
        return this.turnId;
    }

    public GuidedInfoStatus setTurnId(Integer num) {
        if (num == null) {
            throw new NullPointerException("turnId cannot be null");
        }
        this.turnId = num;
        return this;
    }

    public Integer getRemainingDistance() {
        return this.remainingDistance;
    }

    public GuidedInfoStatus setRemainingDistance(Integer num) {
        if (num == null) {
            throw new NullPointerException("remainingDistance cannot be null");
        }
        this.remainingDistance = num;
        return this;
    }

    public String getRemainingDistanceStr() {
        return this.remainingDistanceStr;
    }

    public GuidedInfoStatus setRemainingDistanceStr(String str) {
        if (str == null) {
            throw new NullPointerException("remainingDistanceStr cannot be null");
        }
        this.remainingDistanceStr = str;
        return this;
    }

    public Integer getTurnDistance() {
        return this.turnDistance;
    }

    public GuidedInfoStatus setTurnDistance(Integer num) {
        if (num == null) {
            throw new NullPointerException("turnDistance cannot be null");
        }
        this.turnDistance = num;
        return this;
    }

    public String getTurnDistanceStr() {
        return this.turnDistanceStr;
    }

    public GuidedInfoStatus setTurnDistanceStr(String str) {
        if (str == null) {
            throw new NullPointerException("turnDistanceStr cannot be null");
        }
        this.turnDistanceStr = str;
        return this;
    }

    public Integer getRemainingTime() {
        return this.remainingTime;
    }

    public GuidedInfoStatus setRemainingTime(Integer num) {
        if (num == null) {
            throw new NullPointerException("remainingTime cannot be null");
        }
        this.remainingTime = num;
        return this;
    }

    public String getRemainingTimeStr() {
        return this.remainingTimeStr;
    }

    public GuidedInfoStatus setRemainingTimeStr(String str) {
        if (str == null) {
            throw new NullPointerException("remainingTimeStr cannot be null");
        }
        this.remainingTimeStr = str;
        return this;
    }

    public String getArrivalTimeStr() {
        return this.arrivalTimeStr;
    }

    public GuidedInfoStatus setArrivalTimeStr(String str) {
        if (str == null) {
            throw new NullPointerException("arrivalTimeStr cannot be null");
        }
        this.arrivalTimeStr = str;
        return this;
    }

    public String getCurrentRoadName() {
        return this.currentRoadName;
    }

    public GuidedInfoStatus setCurrentRoadName(String str) {
        if (str == null) {
            throw new NullPointerException("currentRoadName cannot be null");
        }
        this.currentRoadName = str;
        return this;
    }

    public String getExitNumOfHighway() {
        return this.exitNumOfHighway;
    }

    public GuidedInfoStatus setExitNumOfHighway(String str) {
        if (str == null) {
            throw new NullPointerException("exitNumOfHighway cannot be null");
        }
        this.exitNumOfHighway = str;
        return this;
    }

    public String getNextRoadName() {
        return this.nextRoadName;
    }

    public GuidedInfoStatus setNextRoadName(String str) {
        if (str == null) {
            throw new NullPointerException("nextRoadName cannot be null");
        }
        this.nextRoadName = str;
        return this;
    }

    public Integer getNextTurnId() {
        return this.nextTurnId;
    }

    public GuidedInfoStatus setNextTurnId(Integer num) {
        if (num == null) {
            throw new NullPointerException("nextTurnId cannot be null");
        }
        this.nextTurnId = num;
        return this;
    }

    public Integer getNextTurnDistance() {
        return this.nextTurnDistance;
    }

    public GuidedInfoStatus setNextTurnDistance(Integer num) {
        if (num == null) {
            throw new NullPointerException("nextTurnDistance cannot be null");
        }
        this.nextTurnDistance = num;
        return this;
    }

    public String getNextTurnDistanceStr() {
        return this.nextTurnDistanceStr;
    }

    public GuidedInfoStatus setNextTurnDistanceStr(String str) {
        if (str == null) {
            throw new NullPointerException("nextTurnDistanceStr cannot be null");
        }
        this.nextTurnDistanceStr = str;
        return this;
    }

    public Integer getRemainingBatteryPower() {
        return this.remainingBatteryPower;
    }

    public GuidedInfoStatus setRemainingBatteryPower(Integer num) {
        if (num == null) {
            throw new NullPointerException("remainingBatteryPower cannot be null");
        }
        this.remainingBatteryPower = num;
        return this;
    }

    public String getRemainingBatteryPowerStr() {
        return this.remainingBatteryPowerStr;
    }

    public GuidedInfoStatus setRemainingBatteryPowerStr(String str) {
        if (str == null) {
            throw new NullPointerException("remainingBatteryPowerStr cannot be null");
        }
        this.remainingBatteryPowerStr = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GuidedInfoStatus guidedInfoStatus = (GuidedInfoStatus) obj;
        return Objects.equals(this.turnId, guidedInfoStatus.turnId) && Objects.equals(this.remainingDistance, guidedInfoStatus.remainingDistance) && Objects.equals(this.remainingDistanceStr, guidedInfoStatus.remainingDistanceStr) && Objects.equals(this.turnDistance, guidedInfoStatus.turnDistance) && Objects.equals(this.turnDistanceStr, guidedInfoStatus.turnDistanceStr) && Objects.equals(this.remainingTime, guidedInfoStatus.remainingTime) && Objects.equals(this.remainingTimeStr, guidedInfoStatus.remainingTimeStr) && Objects.equals(this.arrivalTimeStr, guidedInfoStatus.arrivalTimeStr) && Objects.equals(this.currentRoadName, guidedInfoStatus.currentRoadName) && Objects.equals(this.exitNumOfHighway, guidedInfoStatus.exitNumOfHighway) && Objects.equals(this.nextRoadName, guidedInfoStatus.nextRoadName) && Objects.equals(this.nextTurnId, guidedInfoStatus.nextTurnId) && Objects.equals(this.nextTurnDistance, guidedInfoStatus.nextTurnDistance) && Objects.equals(this.nextTurnDistanceStr, guidedInfoStatus.nextTurnDistanceStr) && Objects.equals(this.remainingBatteryPower, guidedInfoStatus.remainingBatteryPower) && Objects.equals(this.remainingBatteryPowerStr, guidedInfoStatus.remainingBatteryPowerStr);
    }

    public int hashCode() {
        return Objects.hash(this.turnId, this.remainingDistance, this.remainingDistanceStr, this.turnDistance, this.turnDistanceStr, this.remainingTime, this.remainingTimeStr, this.arrivalTimeStr, this.currentRoadName, this.exitNumOfHighway, this.nextRoadName, this.nextTurnId, this.nextTurnDistance, this.nextTurnDistanceStr, this.remainingBatteryPower, this.remainingBatteryPowerStr);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
