package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ExtPoiInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912991;

    @SerializedName("Name")
    private String name = "";

    @SerializedName("Uid")
    private String uid = "";

    @SerializedName("Distance")
    private Integer distance = 0;

    @SerializedName("Address")
    private String address = "";

    @SerializedName("Type")
    private PoiType type = new PoiType();

    @SerializedName("LatLng")
    private LatLng latLng = new LatLng();

    @SerializedName("DataType")
    private DataType dataType = DataType.TYPE_POI;

    @SerializedName("PoiId")
    private String poiId = "";

    @SerializedName("OpeningHours")
    private List<OpeningHours> openingHours = new ArrayList();

    @SerializedName("Status")
    private String status = "";

    @SerializedName("AvailableNumOfOilGun")
    private Integer availableNumOfOilGun = 0;

    @SerializedName("CategoryId")
    private String categoryId = "";

    @SerializedName("CategoryName")
    private String categoryName = "";

    public ExtPoiInfo() {
    }

    public ExtPoiInfo(String str, String str2, Integer num, String str3, PoiType poiType, LatLng latLng, DataType dataType, String str4, List<OpeningHours> list, String str5, Integer num2, String str6, String str7) {
        setName(str);
        setUid(str2);
        setDistance(num);
        setAddress(str3);
        setType(poiType);
        setLatLng(latLng);
        setDataType(dataType);
        setPoiId(str4);
        setOpeningHours(list);
        setStatus(str5);
        setAvailableNumOfOilGun(num2);
        setCategoryId(str6);
        setCategoryName(str7);
    }

    public String getName() {
        return this.name;
    }

    public ExtPoiInfo setName(String str) {
        if (str == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = str;
        return this;
    }

    public String getUid() {
        return this.uid;
    }

    public ExtPoiInfo setUid(String str) {
        if (str == null) {
            throw new NullPointerException("uid cannot be null");
        }
        this.uid = str;
        return this;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public ExtPoiInfo setDistance(Integer num) {
        if (num == null) {
            throw new NullPointerException("distance cannot be null");
        }
        this.distance = num;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public ExtPoiInfo setAddress(String str) {
        if (str == null) {
            throw new NullPointerException("address cannot be null");
        }
        this.address = str;
        return this;
    }

    public PoiType getType() {
        return this.type;
    }

    public ExtPoiInfo setType(PoiType poiType) {
        this.type = poiType;
        return this;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public ExtPoiInfo setLatLng(LatLng latLng) {
        this.latLng = latLng;
        return this;
    }

    public DataType getDataType() {
        return this.dataType;
    }

    public ExtPoiInfo setDataType(DataType dataType) {
        this.dataType = dataType;
        return this;
    }

    public String getPoiId() {
        return this.poiId;
    }

    public ExtPoiInfo setPoiId(String str) {
        if (str == null) {
            throw new NullPointerException("poiId cannot be null");
        }
        this.poiId = str;
        return this;
    }

    public List<OpeningHours> getOpeningHours() {
        return this.openingHours;
    }

    public ExtPoiInfo setOpeningHours(List<OpeningHours> list) {
        if (list == null) {
            throw new NullPointerException("openingHours cannot be null");
        }
        this.openingHours = list;
        return this;
    }

    public ExtPoiInfo setOpeningHours(OpeningHours... openingHoursArr) {
        return setOpeningHours(Arrays.asList(openingHoursArr));
    }

    public String getStatus() {
        return this.status;
    }

    public ExtPoiInfo setStatus(String str) {
        if (str == null) {
            throw new NullPointerException("status cannot be null");
        }
        this.status = str;
        return this;
    }

    public Integer getAvailableNumOfOilGun() {
        return this.availableNumOfOilGun;
    }

    public ExtPoiInfo setAvailableNumOfOilGun(Integer num) {
        if (num == null) {
            throw new NullPointerException("availableNumOfOilGun cannot be null");
        }
        this.availableNumOfOilGun = num;
        return this;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public ExtPoiInfo setCategoryId(String str) {
        if (str == null) {
            throw new NullPointerException("categoryId cannot be null");
        }
        this.categoryId = str;
        return this;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public ExtPoiInfo setCategoryName(String str) {
        if (str == null) {
            throw new NullPointerException("categoryName cannot be null");
        }
        this.categoryName = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExtPoiInfo extPoiInfo = (ExtPoiInfo) obj;
        return Objects.equals(this.name, extPoiInfo.name) && Objects.equals(this.uid, extPoiInfo.uid) && Objects.equals(this.distance, extPoiInfo.distance) && Objects.equals(this.address, extPoiInfo.address) && Objects.equals(this.type, extPoiInfo.type) && Objects.equals(this.latLng, extPoiInfo.latLng) && Objects.equals(this.dataType, extPoiInfo.dataType) && Objects.equals(this.poiId, extPoiInfo.poiId) && Objects.equals(this.openingHours, extPoiInfo.openingHours) && Objects.equals(this.status, extPoiInfo.status) && Objects.equals(this.availableNumOfOilGun, extPoiInfo.availableNumOfOilGun) && Objects.equals(this.categoryId, extPoiInfo.categoryId) && Objects.equals(this.categoryName, extPoiInfo.categoryName);
    }

    public int hashCode() {
        return Objects.hash(this.name, this.uid, this.distance, this.address, this.type, this.latLng, this.dataType, this.poiId, this.openingHours, this.status, this.availableNumOfOilGun, this.categoryId, this.categoryName);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
