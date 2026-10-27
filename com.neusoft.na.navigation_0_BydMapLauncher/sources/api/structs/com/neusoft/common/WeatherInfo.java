package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class WeatherInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140328;

    @SerializedName("Position")
    private GeoPositionType position = new GeoPositionType();

    @SerializedName("WeatherType")
    private WeatherType weatherType = WeatherType.WT_SUNNY;

    public WeatherInfo() {
    }

    public WeatherInfo(GeoPositionType geoPositionType, WeatherType weatherType) {
        setPosition(geoPositionType);
        setWeatherType(weatherType);
    }

    public GeoPositionType getPosition() {
        return this.position;
    }

    public WeatherInfo setPosition(GeoPositionType geoPositionType) {
        this.position = geoPositionType;
        return this;
    }

    public WeatherType getWeatherType() {
        return this.weatherType;
    }

    public WeatherInfo setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WeatherInfo weatherInfo = (WeatherInfo) obj;
        return Objects.equals(this.position, weatherInfo.position) && Objects.equals(this.weatherType, weatherInfo.weatherType);
    }

    public int hashCode() {
        return Objects.hash(this.position, this.weatherType);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
