package api.structs.com.neusoft.byd;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GPSInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1481838255;

    @SerializedName("Status")
    private GPSAuthorizedStatus status = GPSAuthorizedStatus.E_UNKNOWN;

    @SerializedName("GeoPosition")
    private GeoPosition geoPosition = new GeoPosition();

    public GPSInfo() {
    }

    public GPSInfo(GPSAuthorizedStatus gPSAuthorizedStatus, GeoPosition geoPosition) {
        setStatus(gPSAuthorizedStatus);
        setGeoPosition(geoPosition);
    }

    public GPSAuthorizedStatus getStatus() {
        return this.status;
    }

    public GPSInfo setStatus(GPSAuthorizedStatus gPSAuthorizedStatus) {
        this.status = gPSAuthorizedStatus;
        return this;
    }

    public GeoPosition getGeoPosition() {
        return this.geoPosition;
    }

    public GPSInfo setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GPSInfo gPSInfo = (GPSInfo) obj;
        return Objects.equals(this.status, gPSInfo.status) && Objects.equals(this.geoPosition, gPSInfo.geoPosition);
    }

    public int hashCode() {
        return Objects.hash(this.status, this.geoPosition);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
