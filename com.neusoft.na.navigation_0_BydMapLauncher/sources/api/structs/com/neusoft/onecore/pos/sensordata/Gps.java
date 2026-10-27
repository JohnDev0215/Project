package api.structs.com.neusoft.onecore.pos.sensordata;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class Gps implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1477222849;

    @SerializedName("Header")
    private Header header = new Header();

    @SerializedName("GpsBase")
    private GpsBase gpsBase = new GpsBase();

    @SerializedName("GpsQuality")
    private GpsQuality gpsQuality = new GpsQuality();

    @SerializedName("GpsSatellites")
    private GpsSatellites gpsSatellites = new GpsSatellites();

    public Gps() {
    }

    public Gps(Header header, GpsBase gpsBase, GpsQuality gpsQuality, GpsSatellites gpsSatellites) {
        setHeader(header);
        setGpsBase(gpsBase);
        setGpsQuality(gpsQuality);
        setGpsSatellites(gpsSatellites);
    }

    public Header getHeader() {
        return this.header;
    }

    public Gps setHeader(Header header) {
        this.header = header;
        return this;
    }

    public GpsBase getGpsBase() {
        return this.gpsBase;
    }

    public Gps setGpsBase(GpsBase gpsBase) {
        this.gpsBase = gpsBase;
        return this;
    }

    public GpsQuality getGpsQuality() {
        return this.gpsQuality;
    }

    public Gps setGpsQuality(GpsQuality gpsQuality) {
        this.gpsQuality = gpsQuality;
        return this;
    }

    public GpsSatellites getGpsSatellites() {
        return this.gpsSatellites;
    }

    public Gps setGpsSatellites(GpsSatellites gpsSatellites) {
        this.gpsSatellites = gpsSatellites;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Gps gps = (Gps) obj;
        return Objects.equals(this.header, gps.header) && Objects.equals(this.gpsBase, gps.gpsBase) && Objects.equals(this.gpsQuality, gps.gpsQuality) && Objects.equals(this.gpsSatellites, gps.gpsSatellites);
    }

    public int hashCode() {
        return Objects.hash(this.header, this.gpsBase, this.gpsQuality, this.gpsSatellites);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
