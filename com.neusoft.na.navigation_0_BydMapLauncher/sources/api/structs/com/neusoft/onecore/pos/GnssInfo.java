package api.structs.com.neusoft.onecore.pos;

import api.structs.com.neusoft.types.common.geopostypes.GeoPos3D;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GnssInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478137658;

    @SerializedName("Timestamp")
    private Long timestamp = 0L;

    @SerializedName("FixType")
    private FixType fixType = FixType.FIX_NO;

    @SerializedName("Position")
    private GeoPos3D position = new GeoPos3D();

    @SerializedName("Heading")
    private Double heading = Double.valueOf(0.0d);

    public GnssInfo() {
    }

    public GnssInfo(Long l, FixType fixType, GeoPos3D geoPos3D, Double d) {
        setTimestamp(l);
        setFixType(fixType);
        setPosition(geoPos3D);
        setHeading(d);
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public GnssInfo setTimestamp(Long l) {
        if (l == null) {
            throw new NullPointerException("timestamp cannot be null");
        }
        this.timestamp = l;
        return this;
    }

    public FixType getFixType() {
        return this.fixType;
    }

    public GnssInfo setFixType(FixType fixType) {
        this.fixType = fixType;
        return this;
    }

    public GeoPos3D getPosition() {
        return this.position;
    }

    public GnssInfo setPosition(GeoPos3D geoPos3D) {
        this.position = geoPos3D;
        return this;
    }

    public Double getHeading() {
        return this.heading;
    }

    public GnssInfo setHeading(Double d) {
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
        GnssInfo gnssInfo = (GnssInfo) obj;
        return Objects.equals(this.timestamp, gnssInfo.timestamp) && Objects.equals(this.fixType, gnssInfo.fixType) && Objects.equals(this.position, gnssInfo.position) && Objects.equals(this.heading, gnssInfo.heading);
    }

    public int hashCode() {
        return Objects.hash(this.timestamp, this.fixType, this.position, this.heading);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
