package api.structs.com.neusoft.onecore.pos.sensordata;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GpsQuality implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1477222849;

    @SerializedName("Flags")
    private Long flags;

    @SerializedName("GDOP")
    private Float gDOP;

    @SerializedName("HACC")
    private Float hACC;

    @SerializedName("HDOP")
    private Float hDOP;

    @SerializedName("PDOP")
    private Float pDOP;

    @SerializedName("TDOP")
    private Float tDOP;

    @SerializedName("VACC")
    private Float vACC;

    @SerializedName("VDOP")
    private Float vDOP;

    @SerializedName("Version")
    private Long version = 0L;

    @SerializedName("Antenna")
    private Antenna antenna = Antenna.UNKNOWN;

    public GpsQuality() {
        Float fValueOf = Float.valueOf(0.0f);
        this.hDOP = fValueOf;
        this.vDOP = fValueOf;
        this.pDOP = fValueOf;
        this.gDOP = fValueOf;
        this.tDOP = fValueOf;
        this.hACC = fValueOf;
        this.vACC = fValueOf;
        this.flags = 0L;
    }

    public GpsQuality(Long l, Antenna antenna, Float f, Float f2, Float f3, Float f4, Float f5, Float f6, Float f7, Long l2) {
        Float fValueOf = Float.valueOf(0.0f);
        this.hDOP = fValueOf;
        this.vDOP = fValueOf;
        this.pDOP = fValueOf;
        this.gDOP = fValueOf;
        this.tDOP = fValueOf;
        this.hACC = fValueOf;
        this.vACC = fValueOf;
        this.flags = 0L;
        setVersion(l);
        setAntenna(antenna);
        setHDOP(f);
        setVDOP(f2);
        setPDOP(f3);
        setGDOP(f4);
        setTDOP(f5);
        setHACC(f6);
        setVACC(f7);
        setFlags(l2);
    }

    public Long getVersion() {
        return this.version;
    }

    public GpsQuality setVersion(Long l) {
        if (l == null) {
            throw new NullPointerException("version cannot be null");
        }
        this.version = l;
        return this;
    }

    public Antenna getAntenna() {
        return this.antenna;
    }

    public GpsQuality setAntenna(Antenna antenna) {
        this.antenna = antenna;
        return this;
    }

    public Float getHDOP() {
        return this.hDOP;
    }

    public GpsQuality setHDOP(Float f) {
        if (f == null) {
            throw new NullPointerException("hDOP cannot be null");
        }
        this.hDOP = f;
        return this;
    }

    public Float getVDOP() {
        return this.vDOP;
    }

    public GpsQuality setVDOP(Float f) {
        if (f == null) {
            throw new NullPointerException("vDOP cannot be null");
        }
        this.vDOP = f;
        return this;
    }

    public Float getPDOP() {
        return this.pDOP;
    }

    public GpsQuality setPDOP(Float f) {
        if (f == null) {
            throw new NullPointerException("pDOP cannot be null");
        }
        this.pDOP = f;
        return this;
    }

    public Float getGDOP() {
        return this.gDOP;
    }

    public GpsQuality setGDOP(Float f) {
        if (f == null) {
            throw new NullPointerException("gDOP cannot be null");
        }
        this.gDOP = f;
        return this;
    }

    public Float getTDOP() {
        return this.tDOP;
    }

    public GpsQuality setTDOP(Float f) {
        if (f == null) {
            throw new NullPointerException("tDOP cannot be null");
        }
        this.tDOP = f;
        return this;
    }

    public Float getHACC() {
        return this.hACC;
    }

    public GpsQuality setHACC(Float f) {
        if (f == null) {
            throw new NullPointerException("hACC cannot be null");
        }
        this.hACC = f;
        return this;
    }

    public Float getVACC() {
        return this.vACC;
    }

    public GpsQuality setVACC(Float f) {
        if (f == null) {
            throw new NullPointerException("vACC cannot be null");
        }
        this.vACC = f;
        return this;
    }

    public Long getFlags() {
        return this.flags;
    }

    public GpsQuality setFlags(Long l) {
        if (l == null) {
            throw new NullPointerException("flags cannot be null");
        }
        this.flags = l;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GpsQuality gpsQuality = (GpsQuality) obj;
        return Objects.equals(this.version, gpsQuality.version) && Objects.equals(this.antenna, gpsQuality.antenna) && Objects.equals(this.hDOP, gpsQuality.hDOP) && Objects.equals(this.vDOP, gpsQuality.vDOP) && Objects.equals(this.pDOP, gpsQuality.pDOP) && Objects.equals(this.gDOP, gpsQuality.gDOP) && Objects.equals(this.tDOP, gpsQuality.tDOP) && Objects.equals(this.hACC, gpsQuality.hACC) && Objects.equals(this.vACC, gpsQuality.vACC) && Objects.equals(this.flags, gpsQuality.flags);
    }

    public int hashCode() {
        return Objects.hash(this.version, this.antenna, this.hDOP, this.vDOP, this.pDOP, this.gDOP, this.tDOP, this.hACC, this.vACC, this.flags);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
