package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GeoPositionType implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140328;

    @SerializedName("Latitude")
    private Double latitude;

    @SerializedName("Longitude")
    private Double longitude;

    public GeoPositionType() {
        Double dValueOf = Double.valueOf(0.0d);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
    }

    public GeoPositionType(Double d, Double d2) {
        Double dValueOf = Double.valueOf(0.0d);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
        setLatitude(d);
        setLongitude(d2);
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public GeoPositionType setLatitude(Double d) {
        if (d == null) {
            throw new NullPointerException("latitude cannot be null");
        }
        this.latitude = d;
        return this;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public GeoPositionType setLongitude(Double d) {
        if (d == null) {
            throw new NullPointerException("longitude cannot be null");
        }
        this.longitude = d;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GeoPositionType geoPositionType = (GeoPositionType) obj;
        return Objects.equals(this.latitude, geoPositionType.latitude) && Objects.equals(this.longitude, geoPositionType.longitude);
    }

    public int hashCode() {
        return Objects.hash(this.latitude, this.longitude);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
