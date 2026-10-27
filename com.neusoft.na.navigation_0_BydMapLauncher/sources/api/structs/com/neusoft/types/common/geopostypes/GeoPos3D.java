package api.structs.com.neusoft.types.common.geopostypes;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GeoPos3D implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137506;

    @SerializedName("Altitude")
    private Double altitude;

    @SerializedName("Latitude")
    private Double latitude;

    @SerializedName("Longitude")
    private Double longitude;

    public GeoPos3D() {
        Double dValueOf = Double.valueOf(0.0d);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
        this.altitude = dValueOf;
    }

    public GeoPos3D(Double d, Double d2, Double d3) {
        Double dValueOf = Double.valueOf(0.0d);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
        this.altitude = dValueOf;
        setLatitude(d);
        setLongitude(d2);
        setAltitude(d3);
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public GeoPos3D setLatitude(Double d) {
        if (d == null) {
            throw new NullPointerException("latitude cannot be null");
        }
        this.latitude = d;
        return this;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public GeoPos3D setLongitude(Double d) {
        if (d == null) {
            throw new NullPointerException("longitude cannot be null");
        }
        this.longitude = d;
        return this;
    }

    public Double getAltitude() {
        return this.altitude;
    }

    public GeoPos3D setAltitude(Double d) {
        if (d == null) {
            throw new NullPointerException("altitude cannot be null");
        }
        this.altitude = d;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeoPos3D geoPos3D = (GeoPos3D) obj;
        return Objects.equals(this.latitude, geoPos3D.latitude) && Objects.equals(this.longitude, geoPos3D.longitude) && Objects.equals(this.altitude, geoPos3D.altitude);
    }

    public int hashCode() {
        return Objects.hash(this.latitude, this.longitude, this.altitude);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
