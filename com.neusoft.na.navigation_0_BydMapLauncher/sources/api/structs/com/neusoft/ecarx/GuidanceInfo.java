package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GuidanceInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480913772;

    @SerializedName("TurnId")
    private GuidanceInfoTurnId turnId = GuidanceInfoTurnId.INVALID;

    @SerializedName("RemainDistanceM")
    private Integer remainDistanceM = 0;

    @SerializedName("NextTurnDistanceM")
    private Integer nextTurnDistanceM = 0;

    @SerializedName("NextTurnTime")
    private Integer nextTurnTime = 0;

    @SerializedName("RemainTime")
    private Integer remainTime = 0;

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

    public GuidanceInfo(GuidanceInfoTurnId guidanceInfoTurnId, Integer num, Integer num2, Integer num3, Integer num4, String str, String str2, Float f, GuideType guideType, RoadType roadType, SAPAType sAPAType, String str3, Integer num5, CameraType cameraType, Integer num6, Integer num7) {
        setTurnId(guidanceInfoTurnId);
        setRemainDistanceM(num);
        setNextTurnDistanceM(num2);
        setNextTurnTime(num3);
        setRemainTime(num4);
        setNextRoadName(str);
        setCurRoadName(str2);
        setCarDirection(f);
        setGuideType(guideType);
        setRoadType(roadType);
        setSapaType(sAPAType);
        setSapaName(str3);
        setSapaDistanceM(num5);
        setCameratype(cameraType);
        setCameraDistanceM(num6);
        setCameraSpeed(num7);
    }

    public GuidanceInfoTurnId getTurnId() {
        return this.turnId;
    }

    public GuidanceInfo setTurnId(GuidanceInfoTurnId guidanceInfoTurnId) {
        this.turnId = guidanceInfoTurnId;
        return this;
    }

    public Integer getRemainDistanceM() {
        return this.remainDistanceM;
    }

    public GuidanceInfo setRemainDistanceM(Integer num) {
        if (num == null) {
            throw new NullPointerException("remainDistanceM cannot be null");
        }
        this.remainDistanceM = num;
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
        return Objects.equals(this.turnId, guidanceInfo.turnId) && Objects.equals(this.remainDistanceM, guidanceInfo.remainDistanceM) && Objects.equals(this.nextTurnDistanceM, guidanceInfo.nextTurnDistanceM) && Objects.equals(this.nextTurnTime, guidanceInfo.nextTurnTime) && Objects.equals(this.remainTime, guidanceInfo.remainTime) && Objects.equals(this.nextRoadName, guidanceInfo.nextRoadName) && Objects.equals(this.curRoadName, guidanceInfo.curRoadName) && Objects.equals(this.carDirection, guidanceInfo.carDirection) && Objects.equals(this.guideType, guidanceInfo.guideType) && Objects.equals(this.roadType, guidanceInfo.roadType) && Objects.equals(this.sapaType, guidanceInfo.sapaType) && Objects.equals(this.sapaName, guidanceInfo.sapaName) && Objects.equals(this.sapaDistanceM, guidanceInfo.sapaDistanceM) && Objects.equals(this.cameratype, guidanceInfo.cameratype) && Objects.equals(this.cameraDistanceM, guidanceInfo.cameraDistanceM) && Objects.equals(this.cameraSpeed, guidanceInfo.cameraSpeed);
    }

    public int hashCode() {
        return Objects.hash(this.turnId, this.remainDistanceM, this.nextTurnDistanceM, this.nextTurnTime, this.remainTime, this.nextRoadName, this.curRoadName, this.carDirection, this.guideType, this.roadType, this.sapaType, this.sapaName, this.sapaDistanceM, this.cameratype, this.cameraDistanceM, this.cameraSpeed);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
