package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GeoAddressInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991300;

    @SerializedName("Address")
    private String address = "";

    @SerializedName("formatedAddress")
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

    @SerializedName("AdName")
    private String adName = "";

    @SerializedName("AdCode")
    private String adCode = "";

    @SerializedName("LatLng")
    private LatLng latLng = new LatLng();

    public GeoAddressInfo() {
    }

    public GeoAddressInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, LatLng latLng) {
        setAddress(str);
        setFormatedAddress(str2);
        setCity(str3);
        setCityCode(str4);
        setProvince(str5);
        setCountry(str6);
        setCountryCode(str7);
        setAdName(str8);
        setAdCode(str9);
        setLatLng(latLng);
    }

    public String getAddress() {
        return this.address;
    }

    public GeoAddressInfo setAddress(String str) {
        if (str == null) {
            throw new NullPointerException("address cannot be null");
        }
        this.address = str;
        return this;
    }

    public String getFormatedAddress() {
        return this.formatedAddress;
    }

    public GeoAddressInfo setFormatedAddress(String str) {
        if (str == null) {
            throw new NullPointerException("formatedAddress cannot be null");
        }
        this.formatedAddress = str;
        return this;
    }

    public String getCity() {
        return this.city;
    }

    public GeoAddressInfo setCity(String str) {
        if (str == null) {
            throw new NullPointerException("city cannot be null");
        }
        this.city = str;
        return this;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public GeoAddressInfo setCityCode(String str) {
        if (str == null) {
            throw new NullPointerException("cityCode cannot be null");
        }
        this.cityCode = str;
        return this;
    }

    public String getProvince() {
        return this.province;
    }

    public GeoAddressInfo setProvince(String str) {
        if (str == null) {
            throw new NullPointerException("province cannot be null");
        }
        this.province = str;
        return this;
    }

    public String getCountry() {
        return this.country;
    }

    public GeoAddressInfo setCountry(String str) {
        if (str == null) {
            throw new NullPointerException("country cannot be null");
        }
        this.country = str;
        return this;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public GeoAddressInfo setCountryCode(String str) {
        if (str == null) {
            throw new NullPointerException("countryCode cannot be null");
        }
        this.countryCode = str;
        return this;
    }

    public String getAdName() {
        return this.adName;
    }

    public GeoAddressInfo setAdName(String str) {
        if (str == null) {
            throw new NullPointerException("adName cannot be null");
        }
        this.adName = str;
        return this;
    }

    public String getAdCode() {
        return this.adCode;
    }

    public GeoAddressInfo setAdCode(String str) {
        if (str == null) {
            throw new NullPointerException("adCode cannot be null");
        }
        this.adCode = str;
        return this;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public GeoAddressInfo setLatLng(LatLng latLng) {
        this.latLng = latLng;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeoAddressInfo geoAddressInfo = (GeoAddressInfo) obj;
        return Objects.equals(this.address, geoAddressInfo.address) && Objects.equals(this.formatedAddress, geoAddressInfo.formatedAddress) && Objects.equals(this.city, geoAddressInfo.city) && Objects.equals(this.cityCode, geoAddressInfo.cityCode) && Objects.equals(this.province, geoAddressInfo.province) && Objects.equals(this.country, geoAddressInfo.country) && Objects.equals(this.countryCode, geoAddressInfo.countryCode) && Objects.equals(this.adName, geoAddressInfo.adName) && Objects.equals(this.adCode, geoAddressInfo.adCode) && Objects.equals(this.latLng, geoAddressInfo.latLng);
    }

    public int hashCode() {
        return Objects.hash(this.address, this.formatedAddress, this.city, this.cityCode, this.province, this.country, this.countryCode, this.adName, this.adCode, this.latLng);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
