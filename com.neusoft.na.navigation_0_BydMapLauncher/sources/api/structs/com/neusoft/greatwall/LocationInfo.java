package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LocationInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480910111;

    @SerializedName("Accuracy")
    private Float accuracy = Float.valueOf(0.0f);

    @SerializedName("AdCode")
    private String adCode = "";

    @SerializedName("Address")
    private String address = "";

    @SerializedName("AreaCode")
    private String areaCode = "";

    @SerializedName("City")
    private String city = "";

    @SerializedName("CityCode")
    private String cityCode = "";

    @SerializedName("Distance")
    private Integer distance = 0;

    @SerializedName("District")
    private String district = "";

    @SerializedName("DistrictAdCode")
    private String districtAdCode = "";

    @SerializedName("GeoPosition")
    private GeoPosition geoPosition = new GeoPosition();

    @SerializedName("Heading")
    private Double heading = Double.valueOf(0.0d);

    @SerializedName("Name")
    private String name = "";

    @SerializedName("PoiId")
    private String poiId = "";

    @SerializedName("Province")
    private String province = "";

    @SerializedName("ProvinceAdCode")
    private String provinceAdCode = "";

    public LocationInfo() {
    }

    public LocationInfo(Float f, String str, String str2, String str3, String str4, String str5, Integer num, String str6, String str7, GeoPosition geoPosition, Double d, String str8, String str9, String str10, String str11) {
        setAccuracy(f);
        setAdCode(str);
        setAddress(str2);
        setAreaCode(str3);
        setCity(str4);
        setCityCode(str5);
        setDistance(num);
        setDistrict(str6);
        setDistrictAdCode(str7);
        setGeoPosition(geoPosition);
        setHeading(d);
        setName(str8);
        setPoiId(str9);
        setProvince(str10);
        setProvinceAdCode(str11);
    }

    public Float getAccuracy() {
        return this.accuracy;
    }

    public LocationInfo setAccuracy(Float f) {
        if (f == null) {
            throw new NullPointerException("accuracy cannot be null");
        }
        this.accuracy = f;
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

    public String getAreaCode() {
        return this.areaCode;
    }

    public LocationInfo setAreaCode(String str) {
        if (str == null) {
            throw new NullPointerException("areaCode cannot be null");
        }
        this.areaCode = str;
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

    public Integer getDistance() {
        return this.distance;
    }

    public LocationInfo setDistance(Integer num) {
        if (num == null) {
            throw new NullPointerException("distance cannot be null");
        }
        this.distance = num;
        return this;
    }

    public String getDistrict() {
        return this.district;
    }

    public LocationInfo setDistrict(String str) {
        if (str == null) {
            throw new NullPointerException("district cannot be null");
        }
        this.district = str;
        return this;
    }

    public String getDistrictAdCode() {
        return this.districtAdCode;
    }

    public LocationInfo setDistrictAdCode(String str) {
        if (str == null) {
            throw new NullPointerException("districtAdCode cannot be null");
        }
        this.districtAdCode = str;
        return this;
    }

    public GeoPosition getGeoPosition() {
        return this.geoPosition;
    }

    public LocationInfo setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
        return this;
    }

    public Double getHeading() {
        return this.heading;
    }

    public LocationInfo setHeading(Double d) {
        if (d == null) {
            throw new NullPointerException("heading cannot be null");
        }
        this.heading = d;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public LocationInfo setName(String str) {
        if (str == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = str;
        return this;
    }

    public String getPoiId() {
        return this.poiId;
    }

    public LocationInfo setPoiId(String str) {
        if (str == null) {
            throw new NullPointerException("poiId cannot be null");
        }
        this.poiId = str;
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

    public String getProvinceAdCode() {
        return this.provinceAdCode;
    }

    public LocationInfo setProvinceAdCode(String str) {
        if (str == null) {
            throw new NullPointerException("provinceAdCode cannot be null");
        }
        this.provinceAdCode = str;
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
        return Objects.equals(this.accuracy, locationInfo.accuracy) && Objects.equals(this.adCode, locationInfo.adCode) && Objects.equals(this.address, locationInfo.address) && Objects.equals(this.areaCode, locationInfo.areaCode) && Objects.equals(this.city, locationInfo.city) && Objects.equals(this.cityCode, locationInfo.cityCode) && Objects.equals(this.distance, locationInfo.distance) && Objects.equals(this.district, locationInfo.district) && Objects.equals(this.districtAdCode, locationInfo.districtAdCode) && Objects.equals(this.geoPosition, locationInfo.geoPosition) && Objects.equals(this.heading, locationInfo.heading) && Objects.equals(this.name, locationInfo.name) && Objects.equals(this.poiId, locationInfo.poiId) && Objects.equals(this.province, locationInfo.province) && Objects.equals(this.provinceAdCode, locationInfo.provinceAdCode);
    }

    public int hashCode() {
        return Objects.hash(this.accuracy, this.adCode, this.address, this.areaCode, this.city, this.cityCode, this.distance, this.district, this.districtAdCode, this.geoPosition, this.heading, this.name, this.poiId, this.province, this.provinceAdCode);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
