package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GuidanceInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140444;

    @SerializedName("TurnId")
    private Integer turnId = 0;

    @SerializedName("RemainDistance")
    private Integer remainDistance = 0;

    @SerializedName("RemainingDistanceStr")
    private String remainingDistanceStr = "";

    @SerializedName("RemainingDistanceUnit")
    private String remainingDistanceUnit = "";

    @SerializedName("NextTurnDistanceM")
    private Integer nextTurnDistanceM = 0;

    @SerializedName("NextTurnDistanceStr")
    private String nextTurnDistanceStr = "";

    @SerializedName("NextTurnDistanceUnit")
    private String nextTurnDistanceUnit = "";

    @SerializedName("TurnDistance")
    private Integer turnDistance = 0;

    @SerializedName("TurnDistanceStr")
    private String turnDistanceStr = "";

    @SerializedName("TurnDistanceUnit")
    private String turnDistanceUnit = "";

    @SerializedName("NextTurnTime")
    private Integer nextTurnTime = 0;

    @SerializedName("NextTurnTimeStr")
    private String nextTurnTimeStr = "";

    @SerializedName("RemainTime")
    private Integer remainTime = 0;

    @SerializedName("RemainingTimeStr")
    private String remainingTimeStr = "";

    @SerializedName("ArrivalTimeStr")
    private String arrivalTimeStr = "";

    @SerializedName("NextRoadName")
    private String nextRoadName = "";

    @SerializedName("CurRoadName")
    private String curRoadName = "";

    @SerializedName("CarDirection")
    private Float carDirection = Float.valueOf(0.0f);

    @SerializedName("GuideType")
    private GuideType guideType = GuideType.GPS_NAVIGATION;

    @SerializedName("RoadType")
    private RoadType roadType = RoadType.UNKNOWN_ADMIN_ROAD_CLASS;

    @SerializedName("SapaType")
    private SAPAType sapaType = SAPAType.HIGHWAY_SAPA;

    @SerializedName("SapaName")
    private String sapaName = "";

    @SerializedName("SapaDistanceM")
    private Integer sapaDistanceM = 0;

    @SerializedName("Cameratype")
    private CameraType cameratype = CameraType.CAMERA_UNKNOWN;

    @SerializedName("CameraDistanceM")
    private Integer cameraDistanceM = 0;

    @SerializedName("CameraSpeed")
    private Integer cameraSpeed = 0;

    public GuidanceInfo() {
    }

    public GuidanceInfo(Integer num, Integer num2, String str, String str2, Integer num3, String str3, String str4, Integer num4, String str5, String str6, Integer num5, String str7, Integer num6, String str8, String str9, String str10, String str11, Float f, GuideType guideType, RoadType roadType, SAPAType sAPAType, String str12, Integer num7, CameraType cameraType, Integer num8, Integer num9) {
        setTurnId(num);
        setRemainDistance(num2);
        setRemainingDistanceStr(str);
        setRemainingDistanceUnit(str2);
        setNextTurnDistanceM(num3);
        setNextTurnDistanceStr(str3);
        setNextTurnDistanceUnit(str4);
        setTurnDistance(num4);
        setTurnDistanceStr(str5);
        setTurnDistanceUnit(str6);
        setNextTurnTime(num5);
        setNextTurnTimeStr(str7);
        setRemainTime(num6);
        setRemainingTimeStr(str8);
        setArrivalTimeStr(str9);
        setNextRoadName(str10);
        setCurRoadName(str11);
        setCarDirection(f);
        setGuideType(guideType);
        setRoadType(roadType);
        setSapaType(sAPAType);
        setSapaName(str12);
        setSapaDistanceM(num7);
        setCameratype(cameraType);
        setCameraDistanceM(num8);
        setCameraSpeed(num9);
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

    public Integer getRemainDistance() {
        return this.remainDistance;
    }

    public GuidanceInfo setRemainDistance(Integer num) {
        if (num == null) {
            throw new NullPointerException("remainDistance cannot be null");
        }
        this.remainDistance = num;
        return this;
    }

    public String getRemainingDistanceStr() {
        return this.remainingDistanceStr;
    }

    public GuidanceInfo setRemainingDistanceStr(String str) {
        if (str == null) {
            throw new NullPointerException("remainingDistanceStr cannot be null");
        }
        this.remainingDistanceStr = str;
        return this;
    }

    public String getRemainingDistanceUnit() {
        return this.remainingDistanceUnit;
    }

    public GuidanceInfo setRemainingDistanceUnit(String str) {
        if (str == null) {
            throw new NullPointerException("remainingDistanceUnit cannot be null");
        }
        this.remainingDistanceUnit = str;
        return this;
    }

    public Integer getNextTurnDistanceM() {
        return this.nextTurnDistanceM;
    }

    public GuidanceInfo setNextTurnDistanceM(Integer num) {
        if (num == null) {
            throw new NullPointerException("nextTurnDistanceM cannot be null");
        }
        this.nextTurnDistanceM = num;
        return this;
    }

    public String getNextTurnDistanceStr() {
        return this.nextTurnDistanceStr;
    }

    public GuidanceInfo setNextTurnDistanceStr(String str) {
        if (str == null) {
            throw new NullPointerException("nextTurnDistanceStr cannot be null");
        }
        this.nextTurnDistanceStr = str;
        return this;
    }

    public String getNextTurnDistanceUnit() {
        return this.nextTurnDistanceUnit;
    }

    public GuidanceInfo setNextTurnDistanceUnit(String str) {
        if (str == null) {
            throw new NullPointerException("nextTurnDistanceUnit cannot be null");
        }
        this.nextTurnDistanceUnit = str;
        return this;
    }

    public Integer getTurnDistance() {
        return this.turnDistance;
    }

    public GuidanceInfo setTurnDistance(Integer num) {
        if (num == null) {
            throw new NullPointerException("turnDistance cannot be null");
        }
        this.turnDistance = num;
        return this;
    }

    public String getTurnDistanceStr() {
        return this.turnDistanceStr;
    }

    public GuidanceInfo setTurnDistanceStr(String str) {
        if (str == null) {
            throw new NullPointerException("turnDistanceStr cannot be null");
        }
        this.turnDistanceStr = str;
        return this;
    }

    public String getTurnDistanceUnit() {
        return this.turnDistanceUnit;
    }

    public GuidanceInfo setTurnDistanceUnit(String str) {
        if (str == null) {
            throw new NullPointerException("turnDistanceUnit cannot be null");
        }
        this.turnDistanceUnit = str;
        return this;
    }

    public Integer getNextTurnTime() {
        return this.nextTurnTime;
    }

    public GuidanceInfo setNextTurnTime(Integer num) {
        if (num == null) {
            throw new NullPointerException("nextTurnTime cannot be null");
        }
        this.nextTurnTime = num;
        return this;
    }

    public String getNextTurnTimeStr() {
        return this.nextTurnTimeStr;
    }

    public GuidanceInfo setNextTurnTimeStr(String str) {
        if (str == null) {
            throw new NullPointerException("nextTurnTimeStr cannot be null");
        }
        this.nextTurnTimeStr = str;
        return this;
    }

    public Integer getRemainTime() {
        return this.remainTime;
    }

    public GuidanceInfo setRemainTime(Integer num) {
        if (num == null) {
            throw new NullPointerException("remainTime cannot be null");
        }
        this.remainTime = num;
        return this;
    }

    public String getRemainingTimeStr() {
        return this.remainingTimeStr;
    }

    public GuidanceInfo setRemainingTimeStr(String str) {
        if (str == null) {
            throw new NullPointerException("remainingTimeStr cannot be null");
        }
        this.remainingTimeStr = str;
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

    public String getCurRoadName() {
        return this.curRoadName;
    }

    public GuidanceInfo setCurRoadName(String str) {
        if (str == null) {
            throw new NullPointerException("curRoadName cannot be null");
        }
        this.curRoadName = str;
        return this;
    }

    public Float getCarDirection() {
        return this.carDirection;
    }

    public GuidanceInfo setCarDirection(Float f) {
        if (f == null) {
            throw new NullPointerException("carDirection cannot be null");
        }
        this.carDirection = f;
        return this;
    }

    public GuideType getGuideType() {
        return this.guideType;
    }

    public GuidanceInfo setGuideType(GuideType guideType) {
        this.guideType = guideType;
        return this;
    }

    public RoadType getRoadType() {
        return this.roadType;
    }

    public GuidanceInfo setRoadType(RoadType roadType) {
        this.roadType = roadType;
        return this;
    }

    public SAPAType getSapaType() {
        return this.sapaType;
    }

    public GuidanceInfo setSapaType(SAPAType sAPAType) {
        this.sapaType = sAPAType;
        return this;
    }

    public String getSapaName() {
        return this.sapaName;
    }

    public GuidanceInfo setSapaName(String str) {
        if (str == null) {
            throw new NullPointerException("sapaName cannot be null");
        }
        this.sapaName = str;
        return this;
    }

    public Integer getSapaDistanceM() {
        return this.sapaDistanceM;
    }

    public GuidanceInfo setSapaDistanceM(Integer num) {
        if (num == null) {
            throw new NullPointerException("sapaDistanceM cannot be null");
        }
        this.sapaDistanceM = num;
        return this;
    }

    public CameraType getCameratype() {
        return this.cameratype;
    }

    public GuidanceInfo setCameratype(CameraType cameraType) {
        this.cameratype = cameraType;
        return this;
    }

    public Integer getCameraDistanceM() {
        return this.cameraDistanceM;
    }

    public GuidanceInfo setCameraDistanceM(Integer num) {
        if (num == null) {
            throw new NullPointerException("cameraDistanceM cannot be null");
        }
        this.cameraDistanceM = num;
        return this;
    }

    public Integer getCameraSpeed() {
        return this.cameraSpeed;
    }

    public GuidanceInfo setCameraSpeed(Integer num) {
        if (num == null) {
            throw new NullPointerException("cameraSpeed cannot be null");
        }
        this.cameraSpeed = num;
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
        return Objects.equals(this.turnId, guidanceInfo.turnId) && Objects.equals(this.remainDistance, guidanceInfo.remainDistance) && Objects.equals(this.remainingDistanceStr, guidanceInfo.remainingDistanceStr) && Objects.equals(this.remainingDistanceUnit, guidanceInfo.remainingDistanceUnit) && Objects.equals(this.nextTurnDistanceM, guidanceInfo.nextTurnDistanceM) && Objects.equals(this.nextTurnDistanceStr, guidanceInfo.nextTurnDistanceStr) && Objects.equals(this.nextTurnDistanceUnit, guidanceInfo.nextTurnDistanceUnit) && Objects.equals(this.turnDistance, guidanceInfo.turnDistance) && Objects.equals(this.turnDistanceStr, guidanceInfo.turnDistanceStr) && Objects.equals(this.turnDistanceUnit, guidanceInfo.turnDistanceUnit) && Objects.equals(this.nextTurnTime, guidanceInfo.nextTurnTime) && Objects.equals(this.nextTurnTimeStr, guidanceInfo.nextTurnTimeStr) && Objects.equals(this.remainTime, guidanceInfo.remainTime) && Objects.equals(this.remainingTimeStr, guidanceInfo.remainingTimeStr) && Objects.equals(this.arrivalTimeStr, guidanceInfo.arrivalTimeStr) && Objects.equals(this.nextRoadName, guidanceInfo.nextRoadName) && Objects.equals(this.curRoadName, guidanceInfo.curRoadName) && Objects.equals(this.carDirection, guidanceInfo.carDirection) && Objects.equals(this.guideType, guidanceInfo.guideType) && Objects.equals(this.roadType, guidanceInfo.roadType) && Objects.equals(this.sapaType, guidanceInfo.sapaType) && Objects.equals(this.sapaName, guidanceInfo.sapaName) && Objects.equals(this.sapaDistanceM, guidanceInfo.sapaDistanceM) && Objects.equals(this.cameratype, guidanceInfo.cameratype) && Objects.equals(this.cameraDistanceM, guidanceInfo.cameraDistanceM) && Objects.equals(this.cameraSpeed, guidanceInfo.cameraSpeed);
    }

    public int hashCode() {
        return Objects.hash(this.turnId, this.remainDistance, this.remainingDistanceStr, this.remainingDistanceUnit, this.nextTurnDistanceM, this.nextTurnDistanceStr, this.nextTurnDistanceUnit, this.turnDistance, this.turnDistanceStr, this.turnDistanceUnit, this.nextTurnTime, this.nextTurnTimeStr, this.remainTime, this.remainingTimeStr, this.arrivalTimeStr, this.nextRoadName, this.curRoadName, this.carDirection, this.guideType, this.roadType, this.sapaType, this.sapaName, this.sapaDistanceM, this.cameratype, this.cameraDistanceM, this.cameraSpeed);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
