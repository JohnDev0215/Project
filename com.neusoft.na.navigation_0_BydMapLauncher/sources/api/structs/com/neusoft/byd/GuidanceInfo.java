package api.structs.com.neusoft.byd;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GuidanceInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1481838501;

    @SerializedName("TurnId")
    private Integer turnId = 0;

    @SerializedName("NextRoadName")
    private String nextRoadName = "";

    @SerializedName("NextTurnDist")
    private String nextTurnDist = "";

    @SerializedName("NextTurnUnit")
    private String nextTurnUnit = "";

    @SerializedName("NextTurnId")
    private Integer nextTurnId = 0;

    @SerializedName("nextNextTurnDist")
    private String nextNextTurnDist = "";

    @SerializedName("nextNextTurnUnit")
    private String nextNextTurnUnit = "";

    @SerializedName("RemainDist")
    private String remainDist = "";

    @SerializedName("RemainUnit")
    private String remainUnit = "";

    @SerializedName("ArrivalTimeD")
    private String arrivalTimeD = "";

    @SerializedName("ArrivalTimeH")
    private String arrivalTimeH = "";

    @SerializedName("ArrivalTimeM")
    private String arrivalTimeM = "";

    @SerializedName("ArrivalTimeS")
    private String arrivalTimeS = "";

    @SerializedName("RemainTime")
    private String remainTime = "";

    @SerializedName("targetDisUnit")
    private String targetDisUnit = "";

    @SerializedName("toDestDays")
    private String toDestDays = "";

    @SerializedName("ArrivalTimeStr")
    private String arrivalTimeStr = "";

    @SerializedName("multipleText")
    private String multipleText = "";

    @SerializedName("multipleText1")
    private String multipleText1 = "";

    @SerializedName("multipleText2")
    private String multipleText2 = "";

    public GuidanceInfo() {
    }

    public GuidanceInfo(Integer num, String str, String str2, String str3, Integer num2, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18) {
        setTurnId(num);
        setNextRoadName(str);
        setNextTurnDist(str2);
        setNextTurnUnit(str3);
        setNextTurnId(num2);
        setNextNextTurnDist(str4);
        setNextNextTurnUnit(str5);
        setRemainDist(str6);
        setRemainUnit(str7);
        setArrivalTimeD(str8);
        setArrivalTimeH(str9);
        setArrivalTimeM(str10);
        setArrivalTimeS(str11);
        setRemainTime(str12);
        setTargetDisUnit(str13);
        setToDestDays(str14);
        setArrivalTimeStr(str15);
        setMultipleText(str16);
        setMultipleText1(str17);
        setMultipleText2(str18);
    }

    public Integer getTurnId() {
        return this.turnId;
    }

    public GuidanceInfo setTurnId(Integer num) {
        if (num == null) {
            throw new NullPointerException("turnId cannot be null");
        }
        this.turnId = num;
        return this;
    }

    public String getNextRoadName() {
        return this.nextRoadName;
    }

    public GuidanceInfo setNextRoadName(String str) {
        if (str == null) {
            throw new NullPointerException("nextRoadName cannot be null");
        }
        this.nextRoadName = str;
        return this;
    }

    public String getNextTurnDist() {
        return this.nextTurnDist;
    }

    public GuidanceInfo setNextTurnDist(String str) {
        if (str == null) {
            throw new NullPointerException("nextTurnDist cannot be null");
        }
        this.nextTurnDist = str;
        return this;
    }

    public String getNextTurnUnit() {
        return this.nextTurnUnit;
    }

    public GuidanceInfo setNextTurnUnit(String str) {
        if (str == null) {
            throw new NullPointerException("nextTurnUnit cannot be null");
        }
        this.nextTurnUnit = str;
        return this;
    }

    public Integer getNextTurnId() {
        return this.nextTurnId;
    }

    public GuidanceInfo setNextTurnId(Integer num) {
        if (num == null) {
            throw new NullPointerException("nextTurnId cannot be null");
        }
        this.nextTurnId = num;
        return this;
    }

    public String getNextNextTurnDist() {
        return this.nextNextTurnDist;
    }

    public GuidanceInfo setNextNextTurnDist(String str) {
        if (str == null) {
            throw new NullPointerException("nextNextTurnDist cannot be null");
        }
        this.nextNextTurnDist = str;
        return this;
    }

    public String getNextNextTurnUnit() {
        return this.nextNextTurnUnit;
    }

    public GuidanceInfo setNextNextTurnUnit(String str) {
        if (str == null) {
            throw new NullPointerException("nextNextTurnUnit cannot be null");
        }
        this.nextNextTurnUnit = str;
        return this;
    }

    public String getRemainDist() {
        return this.remainDist;
    }

    public GuidanceInfo setRemainDist(String str) {
        if (str == null) {
            throw new NullPointerException("remainDist cannot be null");
        }
        this.remainDist = str;
        return this;
    }

    public String getRemainUnit() {
        return this.remainUnit;
    }

    public GuidanceInfo setRemainUnit(String str) {
        if (str == null) {
            throw new NullPointerException("remainUnit cannot be null");
        }
        this.remainUnit = str;
        return this;
    }

    public String getArrivalTimeD() {
        return this.arrivalTimeD;
    }

    public GuidanceInfo setArrivalTimeD(String str) {
        if (str == null) {
            throw new NullPointerException("arrivalTimeD cannot be null");
        }
        this.arrivalTimeD = str;
        return this;
    }

    public String getArrivalTimeH() {
        return this.arrivalTimeH;
    }

    public GuidanceInfo setArrivalTimeH(String str) {
        if (str == null) {
            throw new NullPointerException("arrivalTimeH cannot be null");
        }
        this.arrivalTimeH = str;
        return this;
    }

    public String getArrivalTimeM() {
        return this.arrivalTimeM;
    }

    public GuidanceInfo setArrivalTimeM(String str) {
        if (str == null) {
            throw new NullPointerException("arrivalTimeM cannot be null");
        }
        this.arrivalTimeM = str;
        return this;
    }

    public String getArrivalTimeS() {
        return this.arrivalTimeS;
    }

    public GuidanceInfo setArrivalTimeS(String str) {
        if (str == null) {
            throw new NullPointerException("arrivalTimeS cannot be null");
        }
        this.arrivalTimeS = str;
        return this;
    }

    public String getRemainTime() {
        return this.remainTime;
    }

    public GuidanceInfo setRemainTime(String str) {
        if (str == null) {
            throw new NullPointerException("remainTime cannot be null");
        }
        this.remainTime = str;
        return this;
    }

    public String getTargetDisUnit() {
        return this.targetDisUnit;
    }

    public GuidanceInfo setTargetDisUnit(String str) {
        if (str == null) {
            throw new NullPointerException("targetDisUnit cannot be null");
        }
        this.targetDisUnit = str;
        return this;
    }

    public String getToDestDays() {
        return this.toDestDays;
    }

    public GuidanceInfo setToDestDays(String str) {
        if (str == null) {
            throw new NullPointerException("toDestDays cannot be null");
        }
        this.toDestDays = str;
        return this;
    }

    public String getArrivalTimeStr() {
        return this.arrivalTimeStr;
    }

    public GuidanceInfo setArrivalTimeStr(String str) {
        if (str == null) {
            throw new NullPointerException("arrivalTimeStr cannot be null");
        }
        this.arrivalTimeStr = str;
        return this;
    }

    public String getMultipleText() {
        return this.multipleText;
    }

    public GuidanceInfo setMultipleText(String str) {
        if (str == null) {
            throw new NullPointerException("multipleText cannot be null");
        }
        this.multipleText = str;
        return this;
    }

    public String getMultipleText1() {
        return this.multipleText1;
    }

    public GuidanceInfo setMultipleText1(String str) {
        if (str == null) {
            throw new NullPointerException("multipleText1 cannot be null");
        }
        this.multipleText1 = str;
        return this;
    }

    public String getMultipleText2() {
        return this.multipleText2;
    }

    public GuidanceInfo setMultipleText2(String str) {
        if (str == null) {
            throw new NullPointerException("multipleText2 cannot be null");
        }
        this.multipleText2 = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GuidanceInfo guidanceInfo = (GuidanceInfo) obj;
        return Objects.equals(this.turnId, guidanceInfo.turnId) && Objects.equals(this.nextRoadName, guidanceInfo.nextRoadName) && Objects.equals(this.nextTurnDist, guidanceInfo.nextTurnDist) && Objects.equals(this.nextTurnUnit, guidanceInfo.nextTurnUnit) && Objects.equals(this.nextTurnId, guidanceInfo.nextTurnId) && Objects.equals(this.nextNextTurnDist, guidanceInfo.nextNextTurnDist) && Objects.equals(this.nextNextTurnUnit, guidanceInfo.nextNextTurnUnit) && Objects.equals(this.remainDist, guidanceInfo.remainDist) && Objects.equals(this.remainUnit, guidanceInfo.remainUnit) && Objects.equals(this.arrivalTimeD, guidanceInfo.arrivalTimeD) && Objects.equals(this.arrivalTimeH, guidanceInfo.arrivalTimeH) && Objects.equals(this.arrivalTimeM, guidanceInfo.arrivalTimeM) && Objects.equals(this.arrivalTimeS, guidanceInfo.arrivalTimeS) && Objects.equals(this.remainTime, guidanceInfo.remainTime) && Objects.equals(this.targetDisUnit, guidanceInfo.targetDisUnit) && Objects.equals(this.toDestDays, guidanceInfo.toDestDays) && Objects.equals(this.arrivalTimeStr, guidanceInfo.arrivalTimeStr) && Objects.equals(this.multipleText, guidanceInfo.multipleText) && Objects.equals(this.multipleText1, guidanceInfo.multipleText1) && Objects.equals(this.multipleText2, guidanceInfo.multipleText2);
    }

    public int hashCode() {
        return Objects.hash(this.turnId, this.nextRoadName, this.nextTurnDist, this.nextTurnUnit, this.nextTurnId, this.nextNextTurnDist, this.nextNextTurnUnit, this.remainDist, this.remainUnit, this.arrivalTimeD, this.arrivalTimeH, this.arrivalTimeM, this.arrivalTimeS, this.remainTime, this.targetDisUnit, this.toDestDays, this.arrivalTimeStr, this.multipleText, this.multipleText1, this.multipleText2);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
