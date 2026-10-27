package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class SETELPoiInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991242;

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

    @SerializedName("IsNavigationDB")
    private Boolean isNavigationDB = false;

    @SerializedName("IndexOfNavigationDB")
    private Integer indexOfNavigationDB = 0;

    public SETELPoiInfo() {
    }

    public SETELPoiInfo(String str, String str2, Integer num, String str3, PoiType poiType, LatLng latLng, DataType dataType, Boolean bool, Integer num2) {
        setName(str);
        setUid(str2);
        setDistance(num);
        setAddress(str3);
        setType(poiType);
        setLatLng(latLng);
        setDataType(dataType);
        setIsNavigationDB(bool);
        setIndexOfNavigationDB(num2);
    }

    public String getName() {
        return this.name;
    }

    public SETELPoiInfo setName(String str) {
        if (str == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = str;
        return this;
    }

    public String getUid() {
        return this.uid;
    }

    public SETELPoiInfo setUid(String str) {
        if (str == null) {
            throw new NullPointerException("uid cannot be null");
        }
        this.uid = str;
        return this;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public SETELPoiInfo setDistance(Integer num) {
        if (num == null) {
            throw new NullPointerException("distance cannot be null");
        }
        this.distance = num;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public SETELPoiInfo setAddress(String str) {
        if (str == null) {
            throw new NullPointerException("address cannot be null");
        }
        this.address = str;
        return this;
    }

    public PoiType getType() {
        return this.type;
    }

    public SETELPoiInfo setType(PoiType poiType) {
        this.type = poiType;
        return this;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public SETELPoiInfo setLatLng(LatLng latLng) {
        this.latLng = latLng;
        return this;
    }

    public DataType getDataType() {
        return this.dataType;
    }

    public SETELPoiInfo setDataType(DataType dataType) {
        this.dataType = dataType;
        return this;
    }

    public Boolean getIsNavigationDB() {
        return this.isNavigationDB;
    }

    public SETELPoiInfo setIsNavigationDB(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("isNavigationDB cannot be null");
        }
        this.isNavigationDB = bool;
        return this;
    }

    public Integer getIndexOfNavigationDB() {
        return this.indexOfNavigationDB;
    }

    public SETELPoiInfo setIndexOfNavigationDB(Integer num) {
        if (num == null) {
            throw new NullPointerException("indexOfNavigationDB cannot be null");
        }
        this.indexOfNavigationDB = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SETELPoiInfo sETELPoiInfo = (SETELPoiInfo) obj;
        return Objects.equals(this.name, sETELPoiInfo.name) && Objects.equals(this.uid, sETELPoiInfo.uid) && Objects.equals(this.distance, sETELPoiInfo.distance) && Objects.equals(this.address, sETELPoiInfo.address) && Objects.equals(this.type, sETELPoiInfo.type) && Objects.equals(this.latLng, sETELPoiInfo.latLng) && Objects.equals(this.dataType, sETELPoiInfo.dataType) && Objects.equals(this.isNavigationDB, sETELPoiInfo.isNavigationDB) && Objects.equals(this.indexOfNavigationDB, sETELPoiInfo.indexOfNavigationDB);
    }

    public int hashCode() {
        return Objects.hash(this.name, this.uid, this.distance, this.address, this.type, this.latLng, this.dataType, this.isNavigationDB, this.indexOfNavigationDB);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
