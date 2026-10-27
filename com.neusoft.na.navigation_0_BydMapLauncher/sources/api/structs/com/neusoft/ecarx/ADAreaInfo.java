package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ADAreaInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912933;

    @SerializedName("City")
    private String city = "";

    @SerializedName("CityCode")
    private String cityCode = "";

    @SerializedName("Province")
    private String province = "";

    @SerializedName("Country")
    private String country = "";

    @SerializedName("CountryCode")
    private String countryCode = "";

    @SerializedName("AdCode")
    private String adCode = "";

    @SerializedName("AdName")
    private String adName = "";

    public ADAreaInfo() {
    }

    public ADAreaInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        setCity(str);
        setCityCode(str2);
        setProvince(str3);
        setCountry(str4);
        setCountryCode(str5);
        setAdCode(str6);
        setAdName(str7);
    }

    public String getCity() {
        return this.city;
    }

    public ADAreaInfo setCity(String str) {
        if (str == null) {
            throw new NullPointerException("city cannot be null");
        }
        this.city = str;
        return this;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public ADAreaInfo setCityCode(String str) {
        if (str == null) {
            throw new NullPointerException("cityCode cannot be null");
        }
        this.cityCode = str;
        return this;
    }

    public String getProvince() {
        return this.province;
    }

    public ADAreaInfo setProvince(String str) {
        if (str == null) {
            throw new NullPointerException("province cannot be null");
        }
        this.province = str;
        return this;
    }

    public String getCountry() {
        return this.country;
    }

    public ADAreaInfo setCountry(String str) {
        if (str == null) {
            throw new NullPointerException("country cannot be null");
        }
        this.country = str;
        return this;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public ADAreaInfo setCountryCode(String str) {
        if (str == null) {
            throw new NullPointerException("countryCode cannot be null");
        }
        this.countryCode = str;
        return this;
    }

    public String getAdCode() {
        return this.adCode;
    }

    public ADAreaInfo setAdCode(String str) {
        if (str == null) {
            throw new NullPointerException("adCode cannot be null");
        }
        this.adCode = str;
        return this;
    }

    public String getAdName() {
        return this.adName;
    }

    public ADAreaInfo setAdName(String str) {
        if (str == null) {
            throw new NullPointerException("adName cannot be null");
        }
        this.adName = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ADAreaInfo aDAreaInfo = (ADAreaInfo) obj;
        return Objects.equals(this.city, aDAreaInfo.city) && Objects.equals(this.cityCode, aDAreaInfo.cityCode) && Objects.equals(this.province, aDAreaInfo.province) && Objects.equals(this.country, aDAreaInfo.country) && Objects.equals(this.countryCode, aDAreaInfo.countryCode) && Objects.equals(this.adCode, aDAreaInfo.adCode) && Objects.equals(this.adName, aDAreaInfo.adName);
    }

    public int hashCode() {
        return Objects.hash(this.city, this.cityCode, this.province, this.country, this.countryCode, this.adCode, this.adName);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
