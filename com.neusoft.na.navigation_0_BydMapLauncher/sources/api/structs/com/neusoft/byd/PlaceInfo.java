package api.structs.com.neusoft.byd;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PlaceInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1481838349;

    @SerializedName("CategoryId")
    private List<String> categoryId;

    @SerializedName("Distance")
    private Integer distance;

    @SerializedName("Latitude")
    private Double latitude;

    @SerializedName("Longitude")
    private Double longitude;

    @SerializedName("Name")
    private String name = "";

    @SerializedName("Type")
    private Integer type = 0;

    @SerializedName("Uid")
    private String uid = "";

    @SerializedName("Address")
    private String address = "";

    public PlaceInfo() {
        Double dValueOf = Double.valueOf(0.0d);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
        this.distance = 0;
        this.categoryId = new ArrayList();
    }

    public PlaceInfo(String str, Integer num, String str2, String str3, Double d, Double d2, Integer num2, List<String> list) {
        Double dValueOf = Double.valueOf(0.0d);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
        this.distance = 0;
        this.categoryId = new ArrayList();
        setName(str);
        setType(num);
        setUid(str2);
        setAddress(str3);
        setLatitude(d);
        setLongitude(d2);
        setDistance(num2);
        setCategoryId(list);
    }

    public String getName() {
        return this.name;
    }

    public PlaceInfo setName(String str) {
        if (str == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = str;
        return this;
    }

    public Integer getType() {
        return this.type;
    }

    public PlaceInfo setType(Integer num) {
        if (num == null) {
            throw new NullPointerException("type cannot be null");
        }
        this.type = num;
        return this;
    }

    public String getUid() {
        return this.uid;
    }

    public PlaceInfo setUid(String str) {
        if (str == null) {
            throw new NullPointerException("uid cannot be null");
        }
        this.uid = str;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public PlaceInfo setAddress(String str) {
        if (str == null) {
            throw new NullPointerException("address cannot be null");
        }
        this.address = str;
        return this;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public PlaceInfo setLatitude(Double d) {
        if (d == null) {
            throw new NullPointerException("latitude cannot be null");
        }
        this.latitude = d;
        return this;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public PlaceInfo setLongitude(Double d) {
        if (d == null) {
            throw new NullPointerException("longitude cannot be null");
        }
        this.longitude = d;
        return this;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public PlaceInfo setDistance(Integer num) {
        if (num == null) {
            throw new NullPointerException("distance cannot be null");
        }
        this.distance = num;
        return this;
    }

    public List<String> getCategoryId() {
        return this.categoryId;
    }

    public PlaceInfo setCategoryId(List<String> list) {
        if (list == null) {
            throw new NullPointerException("categoryId cannot be null");
        }
        this.categoryId = list;
        return this;
    }

    public PlaceInfo setCategoryId(String... strArr) {
        return setCategoryId(Arrays.asList(strArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PlaceInfo placeInfo = (PlaceInfo) obj;
        return Objects.equals(this.name, placeInfo.name) && Objects.equals(this.type, placeInfo.type) && Objects.equals(this.uid, placeInfo.uid) && Objects.equals(this.address, placeInfo.address) && Objects.equals(this.latitude, placeInfo.latitude) && Objects.equals(this.longitude, placeInfo.longitude) && Objects.equals(this.distance, placeInfo.distance) && Objects.equals(this.categoryId, placeInfo.categoryId);
    }

    public int hashCode() {
        return Objects.hash(this.name, this.type, this.uid, this.address, this.latitude, this.longitude, this.distance, this.categoryId);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
