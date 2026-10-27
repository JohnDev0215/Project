package api.structs.com.neusoft.types.common.weathertypes;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class Temperature implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137469;

    @SerializedName("TemperatureUnit")
    private TemperatureUnit temperatureUnit;

    @SerializedName("Val")
    private Float val;

    @SerializedName("ValMax")
    private Float valMax;

    @SerializedName("ValMin")
    private Float valMin;

    public Temperature() {
        Float fValueOf = Float.valueOf(0.0f);
        this.val = fValueOf;
        this.valMin = fValueOf;
        this.valMax = fValueOf;
        this.temperatureUnit = TemperatureUnit.TU_UNKNOWN;
    }

    public Temperature(Float f, Float f2, Float f3, TemperatureUnit temperatureUnit) {
        Float fValueOf = Float.valueOf(0.0f);
        this.val = fValueOf;
        this.valMin = fValueOf;
        this.valMax = fValueOf;
        this.temperatureUnit = TemperatureUnit.TU_UNKNOWN;
        setVal(f);
        setValMin(f2);
        setValMax(f3);
        setTemperatureUnit(temperatureUnit);
    }

    public Float getVal() {
        return this.val;
    }

    public Temperature setVal(Float f) {
        if (f == null) {
            throw new NullPointerException("val cannot be null");
        }
        this.val = f;
        return this;
    }

    public Float getValMin() {
        return this.valMin;
    }

    public Temperature setValMin(Float f) {
        if (f == null) {
            throw new NullPointerException("valMin cannot be null");
        }
        this.valMin = f;
        return this;
    }

    public Float getValMax() {
        return this.valMax;
    }

    public Temperature setValMax(Float f) {
        if (f == null) {
            throw new NullPointerException("valMax cannot be null");
        }
        this.valMax = f;
        return this;
    }

    public TemperatureUnit getTemperatureUnit() {
        return this.temperatureUnit;
    }

    public Temperature setTemperatureUnit(TemperatureUnit temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Temperature temperature = (Temperature) obj;
        return Objects.equals(this.val, temperature.val) && Objects.equals(this.valMin, temperature.valMin) && Objects.equals(this.valMax, temperature.valMax) && Objects.equals(this.temperatureUnit, temperature.temperatureUnit);
    }

    public int hashCode() {
        return Objects.hash(this.val, this.valMin, this.valMax, this.temperatureUnit);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
