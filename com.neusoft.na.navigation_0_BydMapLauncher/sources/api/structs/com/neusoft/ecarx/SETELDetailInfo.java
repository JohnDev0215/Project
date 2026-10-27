package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class SETELDetailInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912839;

    @SerializedName("IsActive")
    private Boolean isActive = false;

    @SerializedName("Address")
    private String address = "";

    @SerializedName("Name")
    private String name = "";

    @SerializedName("AtStation")
    private Boolean atStation = false;

    @SerializedName("Distance")
    private Integer distance = 0;

    @SerializedName("GeoPosition")
    private GeoPosition geoPosition = new GeoPosition();

    @SerializedName("GeoFencePosition")
    private GeoPosition geoFencePosition = new GeoPosition();

    @SerializedName("RadiusOfGeoFence")
    private Integer radiusOfGeoFence = 0;

    @SerializedName("PoiId")
    private String poiId = "";

    @SerializedName("IsNavigationDB")
    private Boolean isNavigationDB = false;

    @SerializedName("IndexOfNavigationDB")
    private Integer indexOfNavigationDB = 0;

    @SerializedName("OpeningHoursList")
    private List<OpeningHours> openingHoursList = new ArrayList();

    @SerializedName("Status")
    private String status = "";

    @SerializedName("AvailableNumOfOilGun")
    private Integer availableNumOfOilGun = 0;

    public SETELDetailInfo() {
    }

    public SETELDetailInfo(Boolean bool, String str, String str2, Boolean bool2, Integer num, GeoPosition geoPosition, GeoPosition geoPosition2, Integer num2, String str3, Boolean bool3, Integer num3, List<OpeningHours> list, String str4, Integer num4) {
        setIsActive(bool);
        setAddress(str);
        setName(str2);
        setAtStation(bool2);
        setDistance(num);
        setGeoPosition(geoPosition);
        setGeoFencePosition(geoPosition2);
        setRadiusOfGeoFence(num2);
        setPoiId(str3);
        setIsNavigationDB(bool3);
        setIndexOfNavigationDB(num3);
        setOpeningHoursList(list);
        setStatus(str4);
        setAvailableNumOfOilGun(num4);
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public SETELDetailInfo setIsActive(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("isActive cannot be null");
        }
        this.isActive = bool;
        return this;
    }

    public String getAddress() {
        return this.address;
    }

    public SETELDetailInfo setAddress(String str) {
        if (str == null) {
            throw new NullPointerException("address cannot be null");
        }
        this.address = str;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public SETELDetailInfo setName(String str) {
        if (str == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = str;
        return this;
    }

    public Boolean getAtStation() {
        return this.atStation;
    }

    public SETELDetailInfo setAtStation(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("atStation cannot be null");
        }
        this.atStation = bool;
        return this;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public SETELDetailInfo setDistance(Integer num) {
        if (num == null) {
            throw new NullPointerException("distance cannot be null");
        }
        this.distance = num;
        return this;
    }

    public GeoPosition getGeoPosition() {
        return this.geoPosition;
    }

    public SETELDetailInfo setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
        return this;
    }

    public GeoPosition getGeoFencePosition() {
        return this.geoFencePosition;
    }

    public SETELDetailInfo setGeoFencePosition(GeoPosition geoPosition) {
        this.geoFencePosition = geoPosition;
        return this;
    }

    public Integer getRadiusOfGeoFence() {
        return this.radiusOfGeoFence;
    }

    public SETELDetailInfo setRadiusOfGeoFence(Integer num) {
        if (num == null) {
            throw new NullPointerException("radiusOfGeoFence cannot be null");
        }
        this.radiusOfGeoFence = num;
        return this;
    }

    public String getPoiId() {
        return this.poiId;
    }

    public SETELDetailInfo setPoiId(String str) {
        if (str == null) {
            throw new NullPointerException("poiId cannot be null");
        }
        this.poiId = str;
        return this;
    }

    public Boolean getIsNavigationDB() {
        return this.isNavigationDB;
    }

    public SETELDetailInfo setIsNavigationDB(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("isNavigationDB cannot be null");
        }
        this.isNavigationDB = bool;
        return this;
    }

    public Integer getIndexOfNavigationDB() {
        return this.indexOfNavigationDB;
    }

    public SETELDetailInfo setIndexOfNavigationDB(Integer num) {
        if (num == null) {
            throw new NullPointerException("indexOfNavigationDB cannot be null");
        }
        this.indexOfNavigationDB = num;
        return this;
    }

    public List<OpeningHours> getOpeningHoursList() {
        return this.openingHoursList;
    }

    public SETELDetailInfo setOpeningHoursList(List<OpeningHours> list) {
        if (list == null) {
            throw new NullPointerException("openingHoursList cannot be null");
        }
        this.openingHoursList = list;
        return this;
    }

    public SETELDetailInfo setOpeningHoursList(OpeningHours... openingHoursArr) {
        return setOpeningHoursList(Arrays.asList(openingHoursArr));
    }

    public String getStatus() {
        return this.status;
    }

    public SETELDetailInfo setStatus(String str) {
        if (str == null) {
            throw new NullPointerException("status cannot be null");
        }
        this.status = str;
        return this;
    }

    public Integer getAvailableNumOfOilGun() {
        return this.availableNumOfOilGun;
    }

    public SETELDetailInfo setAvailableNumOfOilGun(Integer num) {
        if (num == null) {
            throw new NullPointerException("availableNumOfOilGun cannot be null");
        }
        this.availableNumOfOilGun = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SETELDetailInfo sETELDetailInfo = (SETELDetailInfo) obj;
        return Objects.equals(this.isActive, sETELDetailInfo.isActive) && Objects.equals(this.address, sETELDetailInfo.address) && Objects.equals(this.name, sETELDetailInfo.name) && Objects.equals(this.atStation, sETELDetailInfo.atStation) && Objects.equals(this.distance, sETELDetailInfo.distance) && Objects.equals(this.geoPosition, sETELDetailInfo.geoPosition) && Objects.equals(this.geoFencePosition, sETELDetailInfo.geoFencePosition) && Objects.equals(this.radiusOfGeoFence, sETELDetailInfo.radiusOfGeoFence) && Objects.equals(this.poiId, sETELDetailInfo.poiId) && Objects.equals(this.isNavigationDB, sETELDetailInfo.isNavigationDB) && Objects.equals(this.indexOfNavigationDB, sETELDetailInfo.indexOfNavigationDB) && Objects.equals(this.openingHoursList, sETELDetailInfo.openingHoursList) && Objects.equals(this.status, sETELDetailInfo.status) && Objects.equals(this.availableNumOfOilGun, sETELDetailInfo.availableNumOfOilGun);
    }

    public int hashCode() {
        return Objects.hash(this.isActive, this.address, this.name, this.atStation, this.distance, this.geoPosition, this.geoFencePosition, this.radiusOfGeoFence, this.poiId, this.isNavigationDB, this.indexOfNavigationDB, this.openingHoursList, this.status, this.availableNumOfOilGun);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
