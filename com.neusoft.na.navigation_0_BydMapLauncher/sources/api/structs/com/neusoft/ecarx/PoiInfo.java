package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PoiInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480913027;

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

    public PoiInfo() {
    }

    public PoiInfo(String str, String str2, Integer num, String str3, PoiType poiType, LatLng latLng, DataType dataType) {
        setName(str);
        setUid(str2);
        setDistance(num);
        setAddress(str3);
        setType(poiType);
        setLatLng(latLng);
        setDataType(dataType);
    }

    public String getName() {
        return this.name;
    }

    public PoiInfo setName(String str) {
        if (str == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = str;
        return this;
    }

    public String getUid() {
        return this.uid;
    }

    public PoiInfo setUid(String str) {
        if (str == null) {
            throw new NullPointerException("uid cannot be null");
        }
        this.uid = str;
        return this;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public PoiInfo setDistance(Integer num) {
        if (num == null) {
            throw new NullPointerException("distance cannot be null");
        }
        this.distance = num;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public PoiInfo setAddress(String str) {
        if (str == null) {
            throw new NullPointerException("address cannot be null");
        }
        this.address = str;
        return this;
    }

    public PoiType getType() {
        return this.type;
    }

    public PoiInfo setType(PoiType poiType) {
        this.type = poiType;
        return this;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public PoiInfo setLatLng(LatLng latLng) {
        this.latLng = latLng;
        return this;
    }

    public DataType getDataType() {
        return this.dataType;
    }

    public PoiInfo setDataType(DataType dataType) {
        this.dataType = dataType;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PoiInfo poiInfo = (PoiInfo) obj;
        return Objects.equals(this.name, poiInfo.name) && Objects.equals(this.uid, poiInfo.uid) && Objects.equals(this.distance, poiInfo.distance) && Objects.equals(this.address, poiInfo.address) && Objects.equals(this.type, poiInfo.type) && Objects.equals(this.latLng, poiInfo.latLng) && Objects.equals(this.dataType, poiInfo.dataType);
    }

    public int hashCode() {
        return Objects.hash(this.name, this.uid, this.distance, this.address, this.type, this.latLng, this.dataType);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
