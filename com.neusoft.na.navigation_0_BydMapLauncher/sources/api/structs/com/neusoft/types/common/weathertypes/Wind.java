package api.structs.com.neusoft.types.common.weathertypes;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class Wind implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137469;

    @SerializedName("Direction")
    private Float direction;

    @SerializedName("SpeedUnit")
    private SpeedUnit speedUnit;

    @SerializedName("SpeedVal")
    private Float speedVal;

    public Wind() {
        Float fValueOf = Float.valueOf(0.0f);
        this.speedVal = fValueOf;
        this.speedUnit = SpeedUnit.SU_UNKNOWN;
        this.direction = fValueOf;
    }

    public Wind(Float f, SpeedUnit speedUnit, Float f2) {
        Float fValueOf = Float.valueOf(0.0f);
        this.speedVal = fValueOf;
        this.speedUnit = SpeedUnit.SU_UNKNOWN;
        this.direction = fValueOf;
        setSpeedVal(f);
        setSpeedUnit(speedUnit);
        setDirection(f2);
    }

    public Float getSpeedVal() {
        return this.speedVal;
    }

    public Wind setSpeedVal(Float f) {
        if (f == null) {
            throw new NullPointerException("speedVal cannot be null");
        }
        this.speedVal = f;
        return this;
    }

    public SpeedUnit getSpeedUnit() {
        return this.speedUnit;
    }

    public Wind setSpeedUnit(SpeedUnit speedUnit) {
        this.speedUnit = speedUnit;
        return this;
    }

    public Float getDirection() {
        return this.direction;
    }

    public Wind setDirection(Float f) {
        if (f == null) {
            throw new NullPointerException("direction cannot be null");
        }
        this.direction = f;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Wind wind = (Wind) obj;
        return Objects.equals(this.speedVal, wind.speedVal) && Objects.equals(this.speedUnit, wind.speedUnit) && Objects.equals(this.direction, wind.direction);
    }

    public int hashCode() {
        return Objects.hash(this.speedVal, this.speedUnit, this.direction);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
