package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class FutureWeatherForecastInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478139583;

    @SerializedName("Forecast_Date")
    private String forecast_Date = "";

    @SerializedName("Day_Wind_Direction")
    private String day_Wind_Direction = "";

    @SerializedName("Day_Wind_Force")
    private String day_Wind_Force = "";

    @SerializedName("Day_Weather_Phenomena")
    private String day_Weather_Phenomena = "";

    @SerializedName("Day_Temperature")
    private String day_Temperature = "";

    @SerializedName("Night_Weather_Phenomena")
    private String night_Weather_Phenomena = "";

    @SerializedName("Night_Temperature")
    private String night_Temperature = "";

    @SerializedName("Night_Wind_Direction")
    private String night_Wind_Direction = "";

    @SerializedName("Night_Wind_Force")
    private String night_Wind_Force = "";

    @SerializedName("Day_Weather_Code")
    private String day_Weather_Code = "";

    @SerializedName("Day_Weather_Image")
    private String day_Weather_Image = "";

    @SerializedName("Night_Weather_Code")
    private String night_Weather_Code = "";

    @SerializedName("Night_Weather_Image")
    private String night_Weather_Image = "";

    @SerializedName("Sunrise_Sundown_Time")
    private String sunrise_Sundown_Time = "";

    public FutureWeatherForecastInfo() {
    }

    public FutureWeatherForecastInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        setForecast_Date(str);
        setDay_Wind_Direction(str2);
        setDay_Wind_Force(str3);
        setDay_Weather_Phenomena(str4);
        setDay_Temperature(str5);
        setNight_Weather_Phenomena(str6);
        setNight_Temperature(str7);
        setNight_Wind_Direction(str8);
        setNight_Wind_Force(str9);
        setDay_Weather_Code(str10);
        setDay_Weather_Image(str11);
        setNight_Weather_Code(str12);
        setNight_Weather_Image(str13);
        setSunrise_Sundown_Time(str14);
    }

    public String getForecast_Date() {
        return this.forecast_Date;
    }

    public FutureWeatherForecastInfo setForecast_Date(String str) {
        if (str == null) {
            throw new NullPointerException("forecast_Date cannot be null");
        }
        this.forecast_Date = str;
        return this;
    }

    public String getDay_Wind_Direction() {
        return this.day_Wind_Direction;
    }

    public FutureWeatherForecastInfo setDay_Wind_Direction(String str) {
        if (str == null) {
            throw new NullPointerException("day_Wind_Direction cannot be null");
        }
        this.day_Wind_Direction = str;
        return this;
    }

    public String getDay_Wind_Force() {
        return this.day_Wind_Force;
    }

    public FutureWeatherForecastInfo setDay_Wind_Force(String str) {
        if (str == null) {
            throw new NullPointerException("day_Wind_Force cannot be null");
        }
        this.day_Wind_Force = str;
        return this;
    }

    public String getDay_Weather_Phenomena() {
        return this.day_Weather_Phenomena;
    }

    public FutureWeatherForecastInfo setDay_Weather_Phenomena(String str) {
        if (str == null) {
            throw new NullPointerException("day_Weather_Phenomena cannot be null");
        }
        this.day_Weather_Phenomena = str;
        return this;
    }

    public String getDay_Temperature() {
        return this.day_Temperature;
    }

    public FutureWeatherForecastInfo setDay_Temperature(String str) {
        if (str == null) {
            throw new NullPointerException("day_Temperature cannot be null");
        }
        this.day_Temperature = str;
        return this;
    }

    public String getNight_Weather_Phenomena() {
        return this.night_Weather_Phenomena;
    }

    public FutureWeatherForecastInfo setNight_Weather_Phenomena(String str) {
        if (str == null) {
            throw new NullPointerException("night_Weather_Phenomena cannot be null");
        }
        this.night_Weather_Phenomena = str;
        return this;
    }

    public String getNight_Temperature() {
        return this.night_Temperature;
    }

    public FutureWeatherForecastInfo setNight_Temperature(String str) {
        if (str == null) {
            throw new NullPointerException("night_Temperature cannot be null");
        }
        this.night_Temperature = str;
        return this;
    }

    public String getNight_Wind_Direction() {
        return this.night_Wind_Direction;
    }

    public FutureWeatherForecastInfo setNight_Wind_Direction(String str) {
        if (str == null) {
            throw new NullPointerException("night_Wind_Direction cannot be null");
        }
        this.night_Wind_Direction = str;
        return this;
    }

    public String getNight_Wind_Force() {
        return this.night_Wind_Force;
    }

    public FutureWeatherForecastInfo setNight_Wind_Force(String str) {
        if (str == null) {
            throw new NullPointerException("night_Wind_Force cannot be null");
        }
        this.night_Wind_Force = str;
        return this;
    }

    public String getDay_Weather_Code() {
        return this.day_Weather_Code;
    }

    public FutureWeatherForecastInfo setDay_Weather_Code(String str) {
        if (str == null) {
            throw new NullPointerException("day_Weather_Code cannot be null");
        }
        this.day_Weather_Code = str;
        return this;
    }

    public String getDay_Weather_Image() {
        return this.day_Weather_Image;
    }

    public FutureWeatherForecastInfo setDay_Weather_Image(String str) {
        if (str == null) {
            throw new NullPointerException("day_Weather_Image cannot be null");
        }
        this.day_Weather_Image = str;
        return this;
    }

    public String getNight_Weather_Code() {
        return this.night_Weather_Code;
    }

    public FutureWeatherForecastInfo setNight_Weather_Code(String str) {
        if (str == null) {
            throw new NullPointerException("night_Weather_Code cannot be null");
        }
        this.night_Weather_Code = str;
        return this;
    }

    public String getNight_Weather_Image() {
        return this.night_Weather_Image;
    }

    public FutureWeatherForecastInfo setNight_Weather_Image(String str) {
        if (str == null) {
            throw new NullPointerException("night_Weather_Image cannot be null");
        }
        this.night_Weather_Image = str;
        return this;
    }

    public String getSunrise_Sundown_Time() {
        return this.sunrise_Sundown_Time;
    }

    public FutureWeatherForecastInfo setSunrise_Sundown_Time(String str) {
        if (str == null) {
            throw new NullPointerException("sunrise_Sundown_Time cannot be null");
        }
        this.sunrise_Sundown_Time = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FutureWeatherForecastInfo futureWeatherForecastInfo = (FutureWeatherForecastInfo) obj;
        return Objects.equals(this.forecast_Date, futureWeatherForecastInfo.forecast_Date) && Objects.equals(this.day_Wind_Direction, futureWeatherForecastInfo.day_Wind_Direction) && Objects.equals(this.day_Wind_Force, futureWeatherForecastInfo.day_Wind_Force) && Objects.equals(this.day_Weather_Phenomena, futureWeatherForecastInfo.day_Weather_Phenomena) && Objects.equals(this.day_Temperature, futureWeatherForecastInfo.day_Temperature) && Objects.equals(this.night_Weather_Phenomena, futureWeatherForecastInfo.night_Weather_Phenomena) && Objects.equals(this.night_Temperature, futureWeatherForecastInfo.night_Temperature) && Objects.equals(this.night_Wind_Direction, futureWeatherForecastInfo.night_Wind_Direction) && Objects.equals(this.night_Wind_Force, futureWeatherForecastInfo.night_Wind_Force) && Objects.equals(this.day_Weather_Code, futureWeatherForecastInfo.day_Weather_Code) && Objects.equals(this.day_Weather_Image, futureWeatherForecastInfo.day_Weather_Image) && Objects.equals(this.night_Weather_Code, futureWeatherForecastInfo.night_Weather_Code) && Objects.equals(this.night_Weather_Image, futureWeatherForecastInfo.night_Weather_Image) && Objects.equals(this.sunrise_Sundown_Time, futureWeatherForecastInfo.sunrise_Sundown_Time);
    }

    public int hashCode() {
        return Objects.hash(this.forecast_Date, this.day_Wind_Direction, this.day_Wind_Force, this.day_Weather_Phenomena, this.day_Temperature, this.night_Weather_Phenomena, this.night_Temperature, this.night_Wind_Direction, this.night_Wind_Force, this.day_Weather_Code, this.day_Weather_Image, this.night_Weather_Code, this.night_Weather_Image, this.sunrise_Sundown_Time);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
