package api.structs.com.neusoft.onecore.common;

import api.structs.com.neusoft.types.common.weathertypes.SpeedUnit;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class Speed implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137564;

    @SerializedName("Unit")
    private SpeedUnit unit = SpeedUnit.SU_UNKNOWN;

    @SerializedName("Speed")
    private Float speed = Float.valueOf(0.0f);

    public Speed() {
    }

    public Speed(SpeedUnit speedUnit, Float f) {
        setUnit(speedUnit);
        setSpeed(f);
    }

    public SpeedUnit getUnit() {
        return this.unit;
    }

    public Speed setUnit(SpeedUnit speedUnit) {
        this.unit = speedUnit;
        return this;
    }

    public Float getSpeed() {
        return this.speed;
    }

    public Speed setSpeed(Float f) {
        if (f == null) {
            throw new NullPointerException("speed cannot be null");
        }
        this.speed = f;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Speed speed = (Speed) obj;
        return Objects.equals(this.unit, speed.unit) && Objects.equals(this.speed, speed.speed);
    }

    public int hashCode() {
        return Objects.hash(this.unit, this.speed);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
