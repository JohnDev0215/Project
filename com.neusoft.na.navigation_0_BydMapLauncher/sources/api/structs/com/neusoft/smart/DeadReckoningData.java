package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DeadReckoningData implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479990497;

    @SerializedName("heading")
    private Double heading;

    @SerializedName("Latitude")
    private Double latitude;

    @SerializedName("Longitude")
    private Double longitude;

    public DeadReckoningData() {
        Double dValueOf = Double.valueOf(0.0d);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
        this.heading = dValueOf;
    }

    public DeadReckoningData(Double d, Double d2, Double d3) {
        Double dValueOf = Double.valueOf(0.0d);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
        this.heading = dValueOf;
        setLatitude(d);
        setLongitude(d2);
        setHeading(d3);
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public DeadReckoningData setLatitude(Double d) {
        if (d == null) {
            throw new NullPointerException("latitude cannot be null");
        }
        this.latitude = d;
        return this;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public DeadReckoningData setLongitude(Double d) {
        if (d == null) {
            throw new NullPointerException("longitude cannot be null");
        }
        this.longitude = d;
        return this;
    }

    public Double getHeading() {
        return this.heading;
    }

    public DeadReckoningData setHeading(Double d) {
        if (d == null) {
            throw new NullPointerException("heading cannot be null");
        }
        this.heading = d;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DeadReckoningData deadReckoningData = (DeadReckoningData) obj;
        return Objects.equals(this.latitude, deadReckoningData.latitude) && Objects.equals(this.longitude, deadReckoningData.longitude) && Objects.equals(this.heading, deadReckoningData.heading);
    }

    public int hashCode() {
        return Objects.hash(this.latitude, this.longitude, this.heading);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
