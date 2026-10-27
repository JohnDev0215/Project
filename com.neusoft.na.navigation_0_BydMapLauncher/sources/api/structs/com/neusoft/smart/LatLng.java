package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LatLng implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991431;

    @SerializedName("CoordType")
    private String coordType;

    @SerializedName("Latitude")
    private Double latitude;

    @SerializedName("Longitude")
    private Double longitude;

    public LatLng() {
        Double dValueOf = Double.valueOf(0.0d);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
        this.coordType = "";
    }

    public LatLng(Double d, Double d2, String str) {
        Double dValueOf = Double.valueOf(0.0d);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
        this.coordType = "";
        setLatitude(d);
        setLongitude(d2);
        setCoordType(str);
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public LatLng setLatitude(Double d) {
        if (d == null) {
            throw new NullPointerException("latitude cannot be null");
        }
        this.latitude = d;
        return this;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public LatLng setLongitude(Double d) {
        if (d == null) {
            throw new NullPointerException("longitude cannot be null");
        }
        this.longitude = d;
        return this;
    }

    public String getCoordType() {
        return this.coordType;
    }

    public LatLng setCoordType(String str) {
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
        LatLng latLng = (LatLng) obj;
        return Objects.equals(this.latitude, latLng.latitude) && Objects.equals(this.longitude, latLng.longitude) && Objects.equals(this.coordType, latLng.coordType);
    }

    public int hashCode() {
        return Objects.hash(this.latitude, this.longitude, this.coordType);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
