package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LocationInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480913085;

    @SerializedName("Address")
    private String address = "";

    @SerializedName("FormatedAddress")
    private String formatedAddress = "";

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

    @SerializedName("GeoPosition")
    private GeoPosition geoPosition = new GeoPosition();

    public LocationInfo() {
    }

    public LocationInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, GeoPosition geoPosition) {
        setAddress(str);
        setFormatedAddress(str2);
        setCity(str3);
        setCityCode(str4);
        setProvince(str5);
        setCountry(str6);
        setCountryCode(str7);
        setAdCode(str8);
        setAdName(str9);
        setGeoPosition(geoPosition);
    }

    public String getAddress() {
        return this.address;
    }

    public LocationInfo setAddress(String str) {
        if (str == null) {
            throw new NullPointerException("address cannot be null");
        }
        this.address = str;
        return this;
    }

    public String getFormatedAddress() {
        return this.formatedAddress;
    }

    public LocationInfo setFormatedAddress(String str) {
        if (str == null) {
            throw new NullPointerException("formatedAddress cannot be null");
        }
        this.formatedAddress = str;
        return this;
    }

    public String getCity() {
        return this.city;
    }

    public LocationInfo setCity(String str) {
        if (str == null) {
            throw new NullPointerException("city cannot be null");
        }
        this.city = str;
        return this;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public LocationInfo setCityCode(String str) {
        if (str == null) {
            throw new NullPointerException("cityCode cannot be null");
        }
        this.cityCode = str;
        return this;
    }

    public String getProvince() {
        return this.province;
    }

    public LocationInfo setProvince(String str) {
        if (str == null) {
            throw new NullPointerException("province cannot be null");
        }
        this.province = str;
        return this;
    }

    public String getCountry() {
        return this.country;
    }

    public LocationInfo setCountry(String str) {
        if (str == null) {
            throw new NullPointerException("country cannot be null");
        }
        this.country = str;
        return this;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public LocationInfo setCountryCode(String str) {
        if (str == null) {
            throw new NullPointerException("countryCode cannot be null");
        }
        this.countryCode = str;
        return this;
    }

    public String getAdCode() {
        return this.adCode;
    }

    public LocationInfo setAdCode(String str) {
        if (str == null) {
            throw new NullPointerException("adCode cannot be null");
        }
        this.adCode = str;
        return this;
    }

    public String getAdName() {
        return this.adName;
    }

    public LocationInfo setAdName(String str) {
        if (str == null) {
            throw new NullPointerException("adName cannot be null");
        }
        this.adName = str;
        return this;
    }

    public GeoPosition getGeoPosition() {
        return this.geoPosition;
    }

    public LocationInfo setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LocationInfo locationInfo = (LocationInfo) obj;
        return Objects.equals(this.address, locationInfo.address) && Objects.equals(this.formatedAddress, locationInfo.formatedAddress) && Objects.equals(this.city, locationInfo.city) && Objects.equals(this.cityCode, locationInfo.cityCode) && Objects.equals(this.province, locationInfo.province) && Objects.equals(this.country, locationInfo.country) && Objects.equals(this.countryCode, locationInfo.countryCode) && Objects.equals(this.adCode, locationInfo.adCode) && Objects.equals(this.adName, locationInfo.adName) && Objects.equals(this.geoPosition, locationInfo.geoPosition);
    }

    public int hashCode() {
        return Objects.hash(this.address, this.formatedAddress, this.city, this.cityCode, this.province, this.country, this.countryCode, this.adCode, this.adName, this.geoPosition);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
