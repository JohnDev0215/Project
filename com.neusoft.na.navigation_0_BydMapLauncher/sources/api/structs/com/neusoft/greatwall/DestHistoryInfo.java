package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DestHistoryInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480910017;

    @SerializedName("Name")
    private String name = "";

    @SerializedName("LocationInfo")
    private LocationInfo locationInfo = new LocationInfo();

    public DestHistoryInfo() {
    }

    public DestHistoryInfo(String str, LocationInfo locationInfo) {
        setName(str);
        setLocationInfo(locationInfo);
    }

    public String getName() {
        return this.name;
    }

    public DestHistoryInfo setName(String str) {
        if (str == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = str;
        return this;
    }

    public LocationInfo getLocationInfo() {
        return this.locationInfo;
    }

    public DestHistoryInfo setLocationInfo(LocationInfo locationInfo) {
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
        DestHistoryInfo destHistoryInfo = (DestHistoryInfo) obj;
        return Objects.equals(this.name, destHistoryInfo.name) && Objects.equals(this.locationInfo, destHistoryInfo.locationInfo);
    }

    public int hashCode() {
        return Objects.hash(this.name, this.locationInfo);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
