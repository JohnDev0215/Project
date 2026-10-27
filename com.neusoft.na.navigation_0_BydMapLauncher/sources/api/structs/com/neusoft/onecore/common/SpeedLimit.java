package api.structs.com.neusoft.onecore.common;

import api.structs.com.neusoft.types.common.weathertypes.SpeedUnit;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class SpeedLimit implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137564;

    @SerializedName("Unit")
    private SpeedUnit unit = SpeedUnit.SU_UNKNOWN;

    @SerializedName("Value")
    private Integer value = 0;

    @SerializedName("Advisory")
    private Long advisory = 0L;

    @SerializedName("WeatherRestricted")
    private Long weatherRestricted = 0L;

    @SerializedName("TimeDependent")
    private Long timeDependent = 0L;

    public SpeedLimit() {
    }

    public SpeedLimit(SpeedUnit speedUnit, Integer num, Long l, Long l2, Long l3) {
        setUnit(speedUnit);
        setValue(num);
        setAdvisory(l);
        setWeatherRestricted(l2);
        setTimeDependent(l3);
    }

    public SpeedUnit getUnit() {
        return this.unit;
    }

    public SpeedLimit setUnit(SpeedUnit speedUnit) {
        this.unit = speedUnit;
        return this;
    }

    public Integer getValue() {
        return this.value;
    }

    public SpeedLimit setValue(Integer num) {
        if (num == null) {
            throw new NullPointerException("value cannot be null");
        }
        this.value = num;
        return this;
    }

    public Long getAdvisory() {
        return this.advisory;
    }

    public SpeedLimit setAdvisory(Long l) {
        if (l == null) {
            throw new NullPointerException("advisory cannot be null");
        }
        this.advisory = l;
        return this;
    }

    public Long getWeatherRestricted() {
        return this.weatherRestricted;
    }

    public SpeedLimit setWeatherRestricted(Long l) {
        if (l == null) {
            throw new NullPointerException("weatherRestricted cannot be null");
        }
        this.weatherRestricted = l;
        return this;
    }

    public Long getTimeDependent() {
        return this.timeDependent;
    }

    public SpeedLimit setTimeDependent(Long l) {
        if (l == null) {
            throw new NullPointerException("timeDependent cannot be null");
        }
        this.timeDependent = l;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SpeedLimit speedLimit = (SpeedLimit) obj;
        return Objects.equals(this.unit, speedLimit.unit) && Objects.equals(this.value, speedLimit.value) && Objects.equals(this.advisory, speedLimit.advisory) && Objects.equals(this.weatherRestricted, speedLimit.weatherRestricted) && Objects.equals(this.timeDependent, speedLimit.timeDependent);
    }

    public int hashCode() {
        return Objects.hash(this.unit, this.value, this.advisory, this.weatherRestricted, this.timeDependent);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
