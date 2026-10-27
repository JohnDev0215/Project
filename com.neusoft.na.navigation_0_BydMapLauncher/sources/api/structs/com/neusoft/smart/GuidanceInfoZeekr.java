package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GuidanceInfoZeekr implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479992197;

    @SerializedName("GuidanceInfo")
    private GuidanceInfo guidanceInfo = new GuidanceInfo();

    @SerializedName("RemainDistanceStr")
    private String remainDistanceStr = "";

    @SerializedName("RemainDistanceUnit")
    private String remainDistanceUnit = "";

    @SerializedName("NextTurnDistanceStr")
    private String nextTurnDistanceStr = "";

    @SerializedName("NextTurnDistanceUnit")
    private String nextTurnDistanceUnit = "";

    public GuidanceInfoZeekr() {
    }

    public GuidanceInfoZeekr(GuidanceInfo guidanceInfo, String str, String str2, String str3, String str4) {
        setGuidanceInfo(guidanceInfo);
        setRemainDistanceStr(str);
        setRemainDistanceUnit(str2);
        setNextTurnDistanceStr(str3);
        setNextTurnDistanceUnit(str4);
    }

    public GuidanceInfo getGuidanceInfo() {
        return this.guidanceInfo;
    }

    public GuidanceInfoZeekr setGuidanceInfo(GuidanceInfo guidanceInfo) {
        this.guidanceInfo = guidanceInfo;
        return this;
    }

    public String getRemainDistanceStr() {
        return this.remainDistanceStr;
    }

    public GuidanceInfoZeekr setRemainDistanceStr(String str) {
        if (str == null) {
            throw new NullPointerException("remainDistanceStr cannot be null");
        }
        this.remainDistanceStr = str;
        return this;
    }

    public String getRemainDistanceUnit() {
        return this.remainDistanceUnit;
    }

    public GuidanceInfoZeekr setRemainDistanceUnit(String str) {
        if (str == null) {
            throw new NullPointerException("remainDistanceUnit cannot be null");
        }
        this.remainDistanceUnit = str;
        return this;
    }

    public String getNextTurnDistanceStr() {
        return this.nextTurnDistanceStr;
    }

    public GuidanceInfoZeekr setNextTurnDistanceStr(String str) {
        if (str == null) {
            throw new NullPointerException("nextTurnDistanceStr cannot be null");
        }
        this.nextTurnDistanceStr = str;
        return this;
    }

    public String getNextTurnDistanceUnit() {
        return this.nextTurnDistanceUnit;
    }

    public GuidanceInfoZeekr setNextTurnDistanceUnit(String str) {
        if (str == null) {
            throw new NullPointerException("nextTurnDistanceUnit cannot be null");
        }
        this.nextTurnDistanceUnit = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GuidanceInfoZeekr guidanceInfoZeekr = (GuidanceInfoZeekr) obj;
        return Objects.equals(this.guidanceInfo, guidanceInfoZeekr.guidanceInfo) && Objects.equals(this.remainDistanceStr, guidanceInfoZeekr.remainDistanceStr) && Objects.equals(this.remainDistanceUnit, guidanceInfoZeekr.remainDistanceUnit) && Objects.equals(this.nextTurnDistanceStr, guidanceInfoZeekr.nextTurnDistanceStr) && Objects.equals(this.nextTurnDistanceUnit, guidanceInfoZeekr.nextTurnDistanceUnit);
    }

    public int hashCode() {
        return Objects.hash(this.guidanceInfo, this.remainDistanceStr, this.remainDistanceUnit, this.nextTurnDistanceStr, this.nextTurnDistanceUnit);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
