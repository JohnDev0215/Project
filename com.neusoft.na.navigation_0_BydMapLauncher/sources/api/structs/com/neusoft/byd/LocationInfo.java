package api.structs.com.neusoft.byd;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import com.neusoft.na.ocservice.MotsBl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LocationInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1481838313;

    @SerializedName("GeoPosition")
    private GeoPosition geoPosition = new GeoPosition();

    @SerializedName("name")
    private String name = "";

    @SerializedName("type")
    private PlaceType type = PlaceType.UNKNOWN;

    @SerializedName("id")
    private String id = "";

    @SerializedName("address")
    private String address = "";

    @SerializedName("distance")
    private Double distance = Double.valueOf(0.0d);

    @SerializedName("categoryId")
    private List<String> categoryId = new ArrayList();

    @SerializedName(MotsBl.JS_PROPERTY_LOCALE_COUNTRY)
    private String country = "";

    @SerializedName("district")
    private String district = "";

    @SerializedName("city")
    private String city = "";

    @SerializedName("street")
    private String street = "";

    @SerializedName("house_number")
    private String house_number = "";

    public LocationInfo() {
    }

    public LocationInfo(GeoPosition geoPosition, String str, PlaceType placeType, String str2, String str3, Double d, List<String> list, String str4, String str5, String str6, String str7, String str8) {
        setGeoPosition(geoPosition);
        setName(str);
        setType(placeType);
        setId(str2);
        setAddress(str3);
        setDistance(d);
        setCategoryId(list);
        setCountry(str4);
        setDistrict(str5);
        setCity(str6);
        setStreet(str7);
        setHouse_number(str8);
    }

    public GeoPosition getGeoPosition() {
        return this.geoPosition;
    }

    public LocationInfo setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
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

    public PlaceType getType() {
        return this.type;
    }

    public LocationInfo setType(PlaceType placeType) {
        this.type = placeType;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public LocationInfo setId(String str) {
        if (str == null) {
            throw new NullPointerException("id cannot be null");
        }
        this.id = str;
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

    public Double getDistance() {
        return this.distance;
    }

    public LocationInfo setDistance(Double d) {
        if (d == null) {
            throw new NullPointerException("distance cannot be null");
        }
        this.distance = d;
        return this;
    }

    public List<String> getCategoryId() {
        return this.categoryId;
    }

    public LocationInfo setCategoryId(List<String> list) {
        if (list == null) {
            throw new NullPointerException("categoryId cannot be null");
        }
        this.categoryId = list;
        return this;
    }

    public LocationInfo setCategoryId(String... strArr) {
        return setCategoryId(Arrays.asList(strArr));
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

    public String getStreet() {
        return this.street;
    }

    public LocationInfo setStreet(String str) {
        if (str == null) {
            throw new NullPointerException("street cannot be null");
        }
        this.street = str;
        return this;
    }

    public String getHouse_number() {
        return this.house_number;
    }

    public LocationInfo setHouse_number(String str) {
        if (str == null) {
            throw new NullPointerException("house_number cannot be null");
        }
        this.house_number = str;
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
        return Objects.equals(this.geoPosition, locationInfo.geoPosition) && Objects.equals(this.name, locationInfo.name) && Objects.equals(this.type, locationInfo.type) && Objects.equals(this.id, locationInfo.id) && Objects.equals(this.address, locationInfo.address) && Objects.equals(this.distance, locationInfo.distance) && Objects.equals(this.categoryId, locationInfo.categoryId) && Objects.equals(this.country, locationInfo.country) && Objects.equals(this.district, locationInfo.district) && Objects.equals(this.city, locationInfo.city) && Objects.equals(this.street, locationInfo.street) && Objects.equals(this.house_number, locationInfo.house_number);
    }

    public int hashCode() {
        return Objects.hash(this.geoPosition, this.name, this.type, this.id, this.address, this.distance, this.categoryId, this.country, this.district, this.city, this.street, this.house_number);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
