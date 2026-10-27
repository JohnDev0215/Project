package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DeadReckoningDataExtend implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480909214;

    @SerializedName("altitude")
    private Double altitude;

    @SerializedName("heading")
    private Double heading;

    @SerializedName("Latitude")
    private Double latitude;

    @SerializedName("Longitude")
    private Double longitude;

    @SerializedName("slope")
    private Float slope;

    public DeadReckoningDataExtend() {
        Double dValueOf = Double.valueOf(0.0d);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
        this.heading = dValueOf;
        this.altitude = dValueOf;
        this.slope = Float.valueOf(0.0f);
    }

    public DeadReckoningDataExtend(Double d, Double d2, Double d3, Double d4, Float f) {
        Double dValueOf = Double.valueOf(0.0d);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
        this.heading = dValueOf;
        this.altitude = dValueOf;
        this.slope = Float.valueOf(0.0f);
        setLatitude(d);
        setLongitude(d2);
        setHeading(d3);
        setAltitude(d4);
        setSlope(f);
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public DeadReckoningDataExtend setLatitude(Double d) {
        if (d == null) {
            throw new NullPointerException("latitude cannot be null");
        }
        this.latitude = d;
        return this;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public DeadReckoningDataExtend setLongitude(Double d) {
        if (d == null) {
            throw new NullPointerException("longitude cannot be null");
        }
        this.longitude = d;
        return this;
    }

    public Double getHeading() {
        return this.heading;
    }

    public DeadReckoningDataExtend setHeading(Double d) {
        if (d == null) {
            throw new NullPointerException("heading cannot be null");
        }
        this.heading = d;
        return this;
    }

    public Double getAltitude() {
        return this.altitude;
    }

    public DeadReckoningDataExtend setAltitude(Double d) {
        if (d == null) {
            throw new NullPointerException("altitude cannot be null");
        }
        this.altitude = d;
        return this;
    }

    public Float getSlope() {
        return this.slope;
    }

    public DeadReckoningDataExtend setSlope(Float f) {
        if (f == null) {
            throw new NullPointerException("slope cannot be null");
        }
        this.slope = f;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DeadReckoningDataExtend deadReckoningDataExtend = (DeadReckoningDataExtend) obj;
        return Objects.equals(this.latitude, deadReckoningDataExtend.latitude) && Objects.equals(this.longitude, deadReckoningDataExtend.longitude) && Objects.equals(this.heading, deadReckoningDataExtend.heading) && Objects.equals(this.altitude, deadReckoningDataExtend.altitude) && Objects.equals(this.slope, deadReckoningDataExtend.slope);
    }

    public int hashCode() {
        return Objects.hash(this.latitude, this.longitude, this.heading, this.altitude, this.slope);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
