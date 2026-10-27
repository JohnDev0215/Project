package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class SearchResult implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480910053;

    @SerializedName("LocationInfo")
    private LocationInfo locationInfo = new LocationInfo();

    public SearchResult() {
    }

    public SearchResult(LocationInfo locationInfo) {
        setLocationInfo(locationInfo);
    }

    public LocationInfo getLocationInfo() {
        return this.locationInfo;
    }

    public SearchResult setLocationInfo(LocationInfo locationInfo) {
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
        return Objects.equals(this.locationInfo, ((SearchResult) obj).locationInfo);
    }

    public int hashCode() {
        return Objects.hash(this.locationInfo);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
