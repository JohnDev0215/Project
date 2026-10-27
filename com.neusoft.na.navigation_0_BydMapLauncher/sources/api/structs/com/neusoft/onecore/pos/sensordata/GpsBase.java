package api.structs.com.neusoft.onecore.pos.sensordata;

import api.structs.com.neusoft.onecore.pos.FixType;
import api.structs.com.neusoft.types.common.geopostypes.GeoPos3D;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GpsBase implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1477222849;

    @SerializedName("Heading")
    private Float heading;

    @SerializedName("Speed")
    private Float speed;

    @SerializedName("VelocityDown")
    private Float velocityDown;

    @SerializedName("VelocityEast")
    private Float velocityEast;

    @SerializedName("VelocityNorth")
    private Float velocityNorth;

    @SerializedName("Fix")
    private FixType fix = FixType.FIX_NO;

    @SerializedName("Year")
    private Long year = 0L;

    @SerializedName("Month")
    private Long month = 0L;

    @SerializedName("Day")
    private Long day = 0L;

    @SerializedName("Hour")
    private Long hour = 0L;

    @SerializedName("Minute")
    private Long minute = 0L;

    @SerializedName("Second")
    private Long second = 0L;

    @SerializedName("Micro")
    private Long micro = 0L;

    @SerializedName("LeapSecond")
    private Integer leapSecond = 0;

    @SerializedName("WeekTime")
    private Long weekTime = 0L;

    @SerializedName("PPS")
    private Long pPS = 0L;

    @SerializedName("Position")
    private GeoPos3D position = new GeoPos3D();

    public GpsBase() {
        Float fValueOf = Float.valueOf(0.0f);
        this.speed = fValueOf;
        this.heading = fValueOf;
        this.velocityNorth = fValueOf;
        this.velocityEast = fValueOf;
        this.velocityDown = fValueOf;
    }

    public GpsBase(FixType fixType, Long l, Long l2, Long l3, Long l4, Long l5, Long l6, Long l7, Integer num, Long l8, Long l9, GeoPos3D geoPos3D, Float f, Float f2, Float f3, Float f4, Float f5) {
        Float fValueOf = Float.valueOf(0.0f);
        this.speed = fValueOf;
        this.heading = fValueOf;
        this.velocityNorth = fValueOf;
        this.velocityEast = fValueOf;
        this.velocityDown = fValueOf;
        setFix(fixType);
        setYear(l);
        setMonth(l2);
        setDay(l3);
        setHour(l4);
        setMinute(l5);
        setSecond(l6);
        setMicro(l7);
        setLeapSecond(num);
        setWeekTime(l8);
        setPPS(l9);
        setPosition(geoPos3D);
        setSpeed(f);
        setHeading(f2);
        setVelocityNorth(f3);
        setVelocityEast(f4);
        setVelocityDown(f5);
    }

    public FixType getFix() {
        return this.fix;
    }

    public GpsBase setFix(FixType fixType) {
        this.fix = fixType;
        return this;
    }

    public Long getYear() {
        return this.year;
    }

    public GpsBase setYear(Long l) {
        if (l == null) {
            throw new NullPointerException("year cannot be null");
        }
        this.year = l;
        return this;
    }

    public Long getMonth() {
        return this.month;
    }

    public GpsBase setMonth(Long l) {
        if (l == null) {
            throw new NullPointerException("month cannot be null");
        }
        this.month = l;
        return this;
    }

    public Long getDay() {
        return this.day;
    }

    public GpsBase setDay(Long l) {
        if (l == null) {
            throw new NullPointerException("day cannot be null");
        }
        this.day = l;
        return this;
    }

    public Long getHour() {
        return this.hour;
    }

    public GpsBase setHour(Long l) {
        if (l == null) {
            throw new NullPointerException("hour cannot be null");
        }
        this.hour = l;
        return this;
    }

    public Long getMinute() {
        return this.minute;
    }

    public GpsBase setMinute(Long l) {
        if (l == null) {
            throw new NullPointerException("minute cannot be null");
        }
        this.minute = l;
        return this;
    }

    public Long getSecond() {
        return this.second;
    }

    public GpsBase setSecond(Long l) {
        if (l == null) {
            throw new NullPointerException("second cannot be null");
        }
        this.second = l;
        return this;
    }

    public Long getMicro() {
        return this.micro;
    }

    public GpsBase setMicro(Long l) {
        if (l == null) {
            throw new NullPointerException("micro cannot be null");
        }
        this.micro = l;
        return this;
    }

    public Integer getLeapSecond() {
        return this.leapSecond;
    }

    public GpsBase setLeapSecond(Integer num) {
        if (num == null) {
            throw new NullPointerException("leapSecond cannot be null");
        }
        this.leapSecond = num;
        return this;
    }

    public Long getWeekTime() {
        return this.weekTime;
    }

    public GpsBase setWeekTime(Long l) {
        if (l == null) {
            throw new NullPointerException("weekTime cannot be null");
        }
        this.weekTime = l;
        return this;
    }

    public Long getPPS() {
        return this.pPS;
    }

    public GpsBase setPPS(Long l) {
        if (l == null) {
            throw new NullPointerException("pPS cannot be null");
        }
        this.pPS = l;
        return this;
    }

    public GeoPos3D getPosition() {
        return this.position;
    }

    public GpsBase setPosition(GeoPos3D geoPos3D) {
        this.position = geoPos3D;
        return this;
    }

    public Float getSpeed() {
        return this.speed;
    }

    public GpsBase setSpeed(Float f) {
        if (f == null) {
            throw new NullPointerException("speed cannot be null");
        }
        this.speed = f;
        return this;
    }

    public Float getHeading() {
        return this.heading;
    }

    public GpsBase setHeading(Float f) {
        if (f == null) {
            throw new NullPointerException("heading cannot be null");
        }
        this.heading = f;
        return this;
    }

    public Float getVelocityNorth() {
        return this.velocityNorth;
    }

    public GpsBase setVelocityNorth(Float f) {
        if (f == null) {
            throw new NullPointerException("velocityNorth cannot be null");
        }
        this.velocityNorth = f;
        return this;
    }

    public Float getVelocityEast() {
        return this.velocityEast;
    }

    public GpsBase setVelocityEast(Float f) {
        if (f == null) {
            throw new NullPointerException("velocityEast cannot be null");
        }
        this.velocityEast = f;
        return this;
    }

    public Float getVelocityDown() {
        return this.velocityDown;
    }

    public GpsBase setVelocityDown(Float f) {
        if (f == null) {
            throw new NullPointerException("velocityDown cannot be null");
        }
        this.velocityDown = f;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GpsBase gpsBase = (GpsBase) obj;
        return Objects.equals(this.fix, gpsBase.fix) && Objects.equals(this.year, gpsBase.year) && Objects.equals(this.month, gpsBase.month) && Objects.equals(this.day, gpsBase.day) && Objects.equals(this.hour, gpsBase.hour) && Objects.equals(this.minute, gpsBase.minute) && Objects.equals(this.second, gpsBase.second) && Objects.equals(this.micro, gpsBase.micro) && Objects.equals(this.leapSecond, gpsBase.leapSecond) && Objects.equals(this.weekTime, gpsBase.weekTime) && Objects.equals(this.pPS, gpsBase.pPS) && Objects.equals(this.position, gpsBase.position) && Objects.equals(this.speed, gpsBase.speed) && Objects.equals(this.heading, gpsBase.heading) && Objects.equals(this.velocityNorth, gpsBase.velocityNorth) && Objects.equals(this.velocityEast, gpsBase.velocityEast) && Objects.equals(this.velocityDown, gpsBase.velocityDown);
    }

    public int hashCode() {
        return Objects.hash(this.fix, this.year, this.month, this.day, this.hour, this.minute, this.second, this.micro, this.leapSecond, this.weekTime, this.pPS, this.position, this.speed, this.heading, this.velocityNorth, this.velocityEast, this.velocityDown);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
