package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class RemainTimeDist implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140444;

    @SerializedName("code")
    private NaviCode code = NaviCode.OK;

    @SerializedName("RemainTime")
    private Integer remainTime = 0;

    @SerializedName("RemainingTimeStr")
    private String remainingTimeStr = "";

    @SerializedName("RemainDistance")
    private Integer remainDistance = 0;

    @SerializedName("RemainingDistanceStr")
    private String remainingDistanceStr = "";

    @SerializedName("RemainingDistanceUnit")
    private String remainingDistanceUnit = "";

    public RemainTimeDist() {
    }

    public RemainTimeDist(NaviCode naviCode, Integer num, String str, Integer num2, String str2, String str3) {
        setCode(naviCode);
        setRemainTime(num);
        setRemainingTimeStr(str);
        setRemainDistance(num2);
        setRemainingDistanceStr(str2);
        setRemainingDistanceUnit(str3);
    }

    public NaviCode getCode() {
        return this.code;
    }

    public RemainTimeDist setCode(NaviCode naviCode) {
        this.code = naviCode;
        return this;
    }

    public Integer getRemainTime() {
        return this.remainTime;
    }

    public RemainTimeDist setRemainTime(Integer num) {
        if (num == null) {
            throw new NullPointerException("remainTime cannot be null");
        }
        this.remainTime = num;
        return this;
    }

    public String getRemainingTimeStr() {
        return this.remainingTimeStr;
    }

    public RemainTimeDist setRemainingTimeStr(String str) {
        if (str == null) {
            throw new NullPointerException("remainingTimeStr cannot be null");
        }
        this.remainingTimeStr = str;
        return this;
    }

    public Integer getRemainDistance() {
        return this.remainDistance;
    }

    public RemainTimeDist setRemainDistance(Integer num) {
        if (num == null) {
            throw new NullPointerException("remainDistance cannot be null");
        }
        this.remainDistance = num;
        return this;
    }

    public String getRemainingDistanceStr() {
        return this.remainingDistanceStr;
    }

    public RemainTimeDist setRemainingDistanceStr(String str) {
        if (str == null) {
            throw new NullPointerException("remainingDistanceStr cannot be null");
        }
        this.remainingDistanceStr = str;
        return this;
    }

    public String getRemainingDistanceUnit() {
        return this.remainingDistanceUnit;
    }

    public RemainTimeDist setRemainingDistanceUnit(String str) {
        if (str == null) {
            throw new NullPointerException("remainingDistanceUnit cannot be null");
        }
        this.remainingDistanceUnit = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RemainTimeDist remainTimeDist = (RemainTimeDist) obj;
        return Objects.equals(this.code, remainTimeDist.code) && Objects.equals(this.remainTime, remainTimeDist.remainTime) && Objects.equals(this.remainingTimeStr, remainTimeDist.remainingTimeStr) && Objects.equals(this.remainDistance, remainTimeDist.remainDistance) && Objects.equals(this.remainingDistanceStr, remainTimeDist.remainingDistanceStr) && Objects.equals(this.remainingDistanceUnit, remainTimeDist.remainingDistanceUnit);
    }

    public int hashCode() {
        return Objects.hash(this.code, this.remainTime, this.remainingTimeStr, this.remainDistance, this.remainingDistanceStr, this.remainingDistanceUnit);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
