package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class EVChargingStationInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991394;

    @SerializedName("Name")
    private String name = "";

    @SerializedName("Uid")
    private String uid = "";

    @SerializedName("EVPoiType")
    private EVPoiType eVPoiType = EVPoiType.SEARCH_EV_CHARGING_STATION_ONLINE;

    @SerializedName("Distance")
    private Integer distance = 0;

    @SerializedName("Address")
    private String address = "";

    @SerializedName("LatLng")
    private LatLng latLng = new LatLng();

    @SerializedName("ChargingStationCount")
    private ChargingStationCount chargingStationCount = new ChargingStationCount();

    public EVChargingStationInfo() {
    }

    public EVChargingStationInfo(String str, String str2, EVPoiType eVPoiType, Integer num, String str3, LatLng latLng, ChargingStationCount chargingStationCount) {
        setName(str);
        setUid(str2);
        setEVPoiType(eVPoiType);
        setDistance(num);
        setAddress(str3);
        setLatLng(latLng);
        setChargingStationCount(chargingStationCount);
    }

    public String getName() {
        return this.name;
    }

    public EVChargingStationInfo setName(String str) {
        if (str == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = str;
        return this;
    }

    public String getUid() {
        return this.uid;
    }

    public EVChargingStationInfo setUid(String str) {
        if (str == null) {
            throw new NullPointerException("uid cannot be null");
        }
        this.uid = str;
        return this;
    }

    public EVPoiType getEVPoiType() {
        return this.eVPoiType;
    }

    public EVChargingStationInfo setEVPoiType(EVPoiType eVPoiType) {
        this.eVPoiType = eVPoiType;
        return this;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public EVChargingStationInfo setDistance(Integer num) {
        if (num == null) {
            throw new NullPointerException("distance cannot be null");
        }
        this.distance = num;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public EVChargingStationInfo setAddress(String str) {
        if (str == null) {
            throw new NullPointerException("address cannot be null");
        }
        this.address = str;
        return this;
    }

    public LatLng getLatLng() {
        return this.latLng;
    }

    public EVChargingStationInfo setLatLng(LatLng latLng) {
        this.latLng = latLng;
        return this;
    }

    public ChargingStationCount getChargingStationCount() {
        return this.chargingStationCount;
    }

    public EVChargingStationInfo setChargingStationCount(ChargingStationCount chargingStationCount) {
        this.chargingStationCount = chargingStationCount;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EVChargingStationInfo eVChargingStationInfo = (EVChargingStationInfo) obj;
        return Objects.equals(this.name, eVChargingStationInfo.name) && Objects.equals(this.uid, eVChargingStationInfo.uid) && Objects.equals(this.eVPoiType, eVChargingStationInfo.eVPoiType) && Objects.equals(this.distance, eVChargingStationInfo.distance) && Objects.equals(this.address, eVChargingStationInfo.address) && Objects.equals(this.latLng, eVChargingStationInfo.latLng) && Objects.equals(this.chargingStationCount, eVChargingStationInfo.chargingStationCount);
    }

    public int hashCode() {
        return Objects.hash(this.name, this.uid, this.eVPoiType, this.distance, this.address, this.latLng, this.chargingStationCount);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
