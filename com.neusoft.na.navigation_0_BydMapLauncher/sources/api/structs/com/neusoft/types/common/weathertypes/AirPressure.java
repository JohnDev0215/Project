package api.structs.com.neusoft.types.common.weathertypes;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class AirPressure implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137469;

    @SerializedName("Val")
    private Float val = Float.valueOf(0.0f);

    @SerializedName("AirPressureUnit")
    private AirPressureUnit airPressureUnit = AirPressureUnit.APU_UNKNOWN;

    public AirPressure() {
    }

    public AirPressure(Float f, AirPressureUnit airPressureUnit) {
        setVal(f);
        setAirPressureUnit(airPressureUnit);
    }

    public Float getVal() {
        return this.val;
    }

    public AirPressure setVal(Float f) {
        if (f == null) {
            throw new NullPointerException("val cannot be null");
        }
        this.val = f;
        return this;
    }

    public AirPressureUnit getAirPressureUnit() {
        return this.airPressureUnit;
    }

    public AirPressure setAirPressureUnit(AirPressureUnit airPressureUnit) {
        this.airPressureUnit = airPressureUnit;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AirPressure airPressure = (AirPressure) obj;
        return Objects.equals(this.val, airPressure.val) && Objects.equals(this.airPressureUnit, airPressure.airPressureUnit);
    }

    public int hashCode() {
        return Objects.hash(this.val, this.airPressureUnit);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
