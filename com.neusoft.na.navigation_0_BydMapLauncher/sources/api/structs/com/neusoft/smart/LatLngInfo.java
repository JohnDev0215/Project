package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LatLngInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479992291;

    @SerializedName("Position")
    private GeoPosition position = new GeoPosition();

    @SerializedName("CoordType")
    private String coordType = "";

    public LatLngInfo() {
    }

    public LatLngInfo(GeoPosition geoPosition, String str) {
        setPosition(geoPosition);
        setCoordType(str);
    }

    public GeoPosition getPosition() {
        return this.position;
    }

    public LatLngInfo setPosition(GeoPosition geoPosition) {
        this.position = geoPosition;
        return this;
    }

    public String getCoordType() {
        return this.coordType;
    }

    public LatLngInfo setCoordType(String str) {
        if (str == null) {
            throw new NullPointerException("coordType cannot be null");
        }
        this.coordType = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LatLngInfo latLngInfo = (LatLngInfo) obj;
        return Objects.equals(this.position, latLngInfo.position) && Objects.equals(this.coordType, latLngInfo.coordType);
    }

    public int hashCode() {
        return Objects.hash(this.position, this.coordType);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
