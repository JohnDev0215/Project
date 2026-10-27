package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class WaypointInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480910017;

    @SerializedName("Index")
    private Integer index = 0;

    @SerializedName("Name")
    private String name = "";

    @SerializedName("LocationInfo")
    private LocationInfo locationInfo = new LocationInfo();

    public WaypointInfo() {
    }

    public WaypointInfo(Integer num, String str, LocationInfo locationInfo) {
        setIndex(num);
        setName(str);
        setLocationInfo(locationInfo);
    }

    public Integer getIndex() {
        return this.index;
    }

    public WaypointInfo setIndex(Integer num) {
        if (num == null) {
            throw new NullPointerException("index cannot be null");
        }
        this.index = num;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public WaypointInfo setName(String str) {
        if (str == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = str;
        return this;
    }

    public LocationInfo getLocationInfo() {
        return this.locationInfo;
    }

    public WaypointInfo setLocationInfo(LocationInfo locationInfo) {
        this.locationInfo = locationInfo;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WaypointInfo waypointInfo = (WaypointInfo) obj;
        return Objects.equals(this.index, waypointInfo.index) && Objects.equals(this.name, waypointInfo.name) && Objects.equals(this.locationInfo, waypointInfo.locationInfo);
    }

    public int hashCode() {
        return Objects.hash(this.index, this.name, this.locationInfo);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
