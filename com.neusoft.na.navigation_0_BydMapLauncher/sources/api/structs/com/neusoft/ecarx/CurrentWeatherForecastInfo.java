package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class CurrentWeatherForecastInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478139583;

    @SerializedName("ServerTime")
    private BigInteger serverTime = BigInteger.ZERO;

    @SerializedName("Visibility")
    private Integer visibility = 0;

    @SerializedName("Wind_Direction_Angle")
    private String wind_Direction_Angle = "";

    @SerializedName("AirPressure")
    private String airPressure = "";

    @SerializedName("Sensible_Temperature")
    private String sensible_Temperature = "";

    @SerializedName("Weather_Image")
    private String weather_Image = "";

    @SerializedName("Wind_Speed")
    private String wind_Speed = "";

    @SerializedName("Weather_Code")
    private String weather_Code = "";

    @SerializedName("Weather_Phenomena")
    private String weather_Phenomena = "";

    @SerializedName("Temperature")
    private String temperature = "";

    @SerializedName("Wind_Direction")
    private String wind_Direction = "";

    @SerializedName("Wind_Force")
    private String wind_Force = "";

    @SerializedName("Relative_Humidity")
    private String relative_Humidity = "";

    @SerializedName("Observe_Publish_Time")
    private String observe_Publish_Time = "";

    @SerializedName("Publish_Time")
    private String publish_Time = "";

    public CurrentWeatherForecastInfo() {
    }

    public CurrentWeatherForecastInfo(BigInteger bigInteger, Integer num, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13) {
        setServerTime(bigInteger);
        setVisibility(num);
        setWind_Direction_Angle(str);
        setAirPressure(str2);
        setSensible_Temperature(str3);
        setWeather_Image(str4);
        setWind_Speed(str5);
        setWeather_Code(str6);
        setWeather_Phenomena(str7);
        setTemperature(str8);
        setWind_Direction(str9);
        setWind_Force(str10);
        setRelative_Humidity(str11);
        setObserve_Publish_Time(str12);
        setPublish_Time(str13);
    }

    public BigInteger getServerTime() {
        return this.serverTime;
    }

    public CurrentWeatherForecastInfo setServerTime(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullPointerException("serverTime cannot be null");
        }
        this.serverTime = bigInteger;
        return this;
    }

    public Integer getVisibility() {
        return this.visibility;
    }

    public CurrentWeatherForecastInfo setVisibility(Integer num) {
        if (num == null) {
            throw new NullPointerException("visibility cannot be null");
        }
        this.visibility = num;
        return this;
    }

    public String getWind_Direction_Angle() {
        return this.wind_Direction_Angle;
    }

    public CurrentWeatherForecastInfo setWind_Direction_Angle(String str) {
        if (str == null) {
            throw new NullPointerException("wind_Direction_Angle cannot be null");
        }
        this.wind_Direction_Angle = str;
        return this;
    }

    public String getAirPressure() {
        return this.airPressure;
    }

    public CurrentWeatherForecastInfo setAirPressure(String str) {
        if (str == null) {
            throw new NullPointerException("airPressure cannot be null");
        }
        this.airPressure = str;
        return this;
    }

    public String getSensible_Temperature() {
        return this.sensible_Temperature;
    }

    public CurrentWeatherForecastInfo setSensible_Temperature(String str) {
        if (str == null) {
            throw new NullPointerException("sensible_Temperature cannot be null");
        }
        this.sensible_Temperature = str;
        return this;
    }

    public String getWeather_Image() {
        return this.weather_Image;
    }

    public CurrentWeatherForecastInfo setWeather_Image(String str) {
        if (str == null) {
            throw new NullPointerException("weather_Image cannot be null");
        }
        this.weather_Image = str;
        return this;
    }

    public String getWind_Speed() {
        return this.wind_Speed;
    }

    public CurrentWeatherForecastInfo setWind_Speed(String str) {
        if (str == null) {
            throw new NullPointerException("wind_Speed cannot be null");
        }
        this.wind_Speed = str;
        return this;
    }

    public String getWeather_Code() {
        return this.weather_Code;
    }

    public CurrentWeatherForecastInfo setWeather_Code(String str) {
        if (str == null) {
            throw new NullPointerException("weather_Code cannot be null");
        }
        this.weather_Code = str;
        return this;
    }

    public String getWeather_Phenomena() {
        return this.weather_Phenomena;
    }

    public CurrentWeatherForecastInfo setWeather_Phenomena(String str) {
        if (str == null) {
            throw new NullPointerException("weather_Phenomena cannot be null");
        }
        this.weather_Phenomena = str;
        return this;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public CurrentWeatherForecastInfo setTemperature(String str) {
        if (str == null) {
            throw new NullPointerException("temperature cannot be null");
        }
        this.temperature = str;
        return this;
    }

    public String getWind_Direction() {
        return this.wind_Direction;
    }

    public CurrentWeatherForecastInfo setWind_Direction(String str) {
        if (str == null) {
            throw new NullPointerException("wind_Direction cannot be null");
        }
        this.wind_Direction = str;
        return this;
    }

    public String getWind_Force() {
        return this.wind_Force;
    }

    public CurrentWeatherForecastInfo setWind_Force(String str) {
        if (str == null) {
            throw new NullPointerException("wind_Force cannot be null");
        }
        this.wind_Force = str;
        return this;
    }

    public String getRelative_Humidity() {
        return this.relative_Humidity;
    }

    public CurrentWeatherForecastInfo setRelative_Humidity(String str) {
        if (str == null) {
            throw new NullPointerException("relative_Humidity cannot be null");
        }
        this.relative_Humidity = str;
        return this;
    }

    public String getObserve_Publish_Time() {
        return this.observe_Publish_Time;
    }

    public CurrentWeatherForecastInfo setObserve_Publish_Time(String str) {
        if (str == null) {
            throw new NullPointerException("observe_Publish_Time cannot be null");
        }
        this.observe_Publish_Time = str;
        return this;
    }

    public String getPublish_Time() {
        return this.publish_Time;
    }

    public CurrentWeatherForecastInfo setPublish_Time(String str) {
        if (str == null) {
            throw new NullPointerException("publish_Time cannot be null");
        }
        this.publish_Time = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CurrentWeatherForecastInfo currentWeatherForecastInfo = (CurrentWeatherForecastInfo) obj;
        return Objects.equals(this.serverTime, currentWeatherForecastInfo.serverTime) && Objects.equals(this.visibility, currentWeatherForecastInfo.visibility) && Objects.equals(this.wind_Direction_Angle, currentWeatherForecastInfo.wind_Direction_Angle) && Objects.equals(this.airPressure, currentWeatherForecastInfo.airPressure) && Objects.equals(this.sensible_Temperature, currentWeatherForecastInfo.sensible_Temperature) && Objects.equals(this.weather_Image, currentWeatherForecastInfo.weather_Image) && Objects.equals(this.wind_Speed, currentWeatherForecastInfo.wind_Speed) && Objects.equals(this.weather_Code, currentWeatherForecastInfo.weather_Code) && Objects.equals(this.weather_Phenomena, currentWeatherForecastInfo.weather_Phenomena) && Objects.equals(this.temperature, currentWeatherForecastInfo.temperature) && Objects.equals(this.wind_Direction, currentWeatherForecastInfo.wind_Direction) && Objects.equals(this.wind_Force, currentWeatherForecastInfo.wind_Force) && Objects.equals(this.relative_Humidity, currentWeatherForecastInfo.relative_Humidity) && Objects.equals(this.observe_Publish_Time, currentWeatherForecastInfo.observe_Publish_Time) && Objects.equals(this.publish_Time, currentWeatherForecastInfo.publish_Time);
    }

    public int hashCode() {
        return Objects.hash(this.serverTime, this.visibility, this.wind_Direction_Angle, this.airPressure, this.sensible_Temperature, this.weather_Image, this.wind_Speed, this.weather_Code, this.weather_Phenomena, this.temperature, this.wind_Direction, this.wind_Force, this.relative_Humidity, this.observe_Publish_Time, this.publish_Time);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
