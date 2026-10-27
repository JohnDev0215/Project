package api.structs.com.neusoft.onecore.pos.sensordata;

import api.structs.com.neusoft.types.common.geopostypes.GeoPos3D;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DeadReckoning implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1477222849;

    @SerializedName("Acceleration")
    private Float acceleration;

    @SerializedName("Heading")
    private Float heading;

    @SerializedName("LateralAcceleration")
    private Float lateralAcceleration;

    @SerializedName("Offset")
    private Float offset;

    @SerializedName("Pitch")
    private Float pitch;

    @SerializedName("ReverseGear")
    private Boolean reverseGear;

    @SerializedName("Speed")
    private Float speed;

    @SerializedName("TurnRate")
    private Float turnRate;

    @SerializedName("Header")
    private Header header = new Header();

    @SerializedName("Position")
    private GeoPos3D position = new GeoPos3D();

    public DeadReckoning() {
        Float fValueOf = Float.valueOf(0.0f);
        this.speed = fValueOf;
        this.heading = fValueOf;
        this.offset = fValueOf;
        this.pitch = fValueOf;
        this.acceleration = fValueOf;
        this.turnRate = fValueOf;
        this.lateralAcceleration = fValueOf;
        this.reverseGear = false;
    }

    public DeadReckoning(Header header, GeoPos3D geoPos3D, Float f, Float f2, Float f3, Float f4, Float f5, Float f6, Float f7, Boolean bool) {
        Float fValueOf = Float.valueOf(0.0f);
        this.speed = fValueOf;
        this.heading = fValueOf;
        this.offset = fValueOf;
        this.pitch = fValueOf;
        this.acceleration = fValueOf;
        this.turnRate = fValueOf;
        this.lateralAcceleration = fValueOf;
        this.reverseGear = false;
        setHeader(header);
        setPosition(geoPos3D);
        setSpeed(f);
        setHeading(f2);
        setOffset(f3);
        setPitch(f4);
        setAcceleration(f5);
        setTurnRate(f6);
        setLateralAcceleration(f7);
        setReverseGear(bool);
    }

    public Header getHeader() {
        return this.header;
    }

    public DeadReckoning setHeader(Header header) {
        this.header = header;
        return this;
    }

    public GeoPos3D getPosition() {
        return this.position;
    }

    public DeadReckoning setPosition(GeoPos3D geoPos3D) {
        this.position = geoPos3D;
        return this;
    }

    public Float getSpeed() {
        return this.speed;
    }

    public DeadReckoning setSpeed(Float f) {
        if (f == null) {
            throw new NullPointerException("speed cannot be null");
        }
        this.speed = f;
        return this;
    }

    public Float getHeading() {
        return this.heading;
    }

    public DeadReckoning setHeading(Float f) {
        if (f == null) {
            throw new NullPointerException("heading cannot be null");
        }
        this.heading = f;
        return this;
    }

    public Float getOffset() {
        return this.offset;
    }

    public DeadReckoning setOffset(Float f) {
        if (f == null) {
            throw new NullPointerException("offset cannot be null");
        }
        this.offset = f;
        return this;
    }

    public Float getPitch() {
        return this.pitch;
    }

    public DeadReckoning setPitch(Float f) {
        if (f == null) {
            throw new NullPointerException("pitch cannot be null");
        }
        this.pitch = f;
        return this;
    }

    public Float getAcceleration() {
        return this.acceleration;
    }

    public DeadReckoning setAcceleration(Float f) {
        if (f == null) {
            throw new NullPointerException("acceleration cannot be null");
        }
        this.acceleration = f;
        return this;
    }

    public Float getTurnRate() {
        return this.turnRate;
    }

    public DeadReckoning setTurnRate(Float f) {
        if (f == null) {
            throw new NullPointerException("turnRate cannot be null");
        }
        this.turnRate = f;
        return this;
    }

    public Float getLateralAcceleration() {
        return this.lateralAcceleration;
    }

    public DeadReckoning setLateralAcceleration(Float f) {
        if (f == null) {
            throw new NullPointerException("lateralAcceleration cannot be null");
        }
        this.lateralAcceleration = f;
        return this;
    }

    public Boolean getReverseGear() {
        return this.reverseGear;
    }

    public DeadReckoning setReverseGear(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("reverseGear cannot be null");
        }
        this.reverseGear = bool;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DeadReckoning deadReckoning = (DeadReckoning) obj;
        return Objects.equals(this.header, deadReckoning.header) && Objects.equals(this.position, deadReckoning.position) && Objects.equals(this.speed, deadReckoning.speed) && Objects.equals(this.heading, deadReckoning.heading) && Objects.equals(this.offset, deadReckoning.offset) && Objects.equals(this.pitch, deadReckoning.pitch) && Objects.equals(this.acceleration, deadReckoning.acceleration) && Objects.equals(this.turnRate, deadReckoning.turnRate) && Objects.equals(this.lateralAcceleration, deadReckoning.lateralAcceleration) && Objects.equals(this.reverseGear, deadReckoning.reverseGear);
    }

    public int hashCode() {
        return Objects.hash(this.header, this.position, this.speed, this.heading, this.offset, this.pitch, this.acceleration, this.turnRate, this.lateralAcceleration, this.reverseGear);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
