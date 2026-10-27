package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class TimeZoneInformation implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479990461;

    @SerializedName("TimeZoneValue")
    private Integer timeZoneValue = 0;

    @SerializedName("TimeOffsetForSummer")
    private Integer timeOffsetForSummer = 0;

    @SerializedName("Country")
    private String country = "";

    @SerializedName("CountryCode")
    private String countryCode = "";

    public TimeZoneInformation() {
    }

    public TimeZoneInformation(Integer num, Integer num2, String str, String str2) {
        setTimeZoneValue(num);
        setTimeOffsetForSummer(num2);
        setCountry(str);
        setCountryCode(str2);
    }

    public Integer getTimeZoneValue() {
        return this.timeZoneValue;
    }

    public TimeZoneInformation setTimeZoneValue(Integer num) {
        if (num == null) {
            throw new NullPointerException("timeZoneValue cannot be null");
        }
        this.timeZoneValue = num;
        return this;
    }

    public Integer getTimeOffsetForSummer() {
        return this.timeOffsetForSummer;
    }

    public TimeZoneInformation setTimeOffsetForSummer(Integer num) {
        if (num == null) {
            throw new NullPointerException("timeOffsetForSummer cannot be null");
        }
        this.timeOffsetForSummer = num;
        return this;
    }

    public String getCountry() {
        return this.country;
    }

    public TimeZoneInformation setCountry(String str) {
        if (str == null) {
            throw new NullPointerException("country cannot be null");
        }
        this.country = str;
        return this;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public TimeZoneInformation setCountryCode(String str) {
        if (str == null) {
            throw new NullPointerException("countryCode cannot be null");
        }
        this.countryCode = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TimeZoneInformation timeZoneInformation = (TimeZoneInformation) obj;
        return Objects.equals(this.timeZoneValue, timeZoneInformation.timeZoneValue) && Objects.equals(this.timeOffsetForSummer, timeZoneInformation.timeOffsetForSummer) && Objects.equals(this.country, timeZoneInformation.country) && Objects.equals(this.countryCode, timeZoneInformation.countryCode);
    }

    public int hashCode() {
        return Objects.hash(this.timeZoneValue, this.timeOffsetForSummer, this.country, this.countryCode);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
