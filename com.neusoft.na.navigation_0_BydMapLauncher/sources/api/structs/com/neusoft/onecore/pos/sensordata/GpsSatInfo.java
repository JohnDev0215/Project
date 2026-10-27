package api.structs.com.neusoft.onecore.pos.sensordata;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GpsSatInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1477222813;

    @SerializedName("Id")
    private Long id = 0L;

    @SerializedName("Elevation")
    private Long elevation = 0L;

    @SerializedName("Azimuth")
    private Long azimuth = 0L;

    @SerializedName("SNR")
    private Long sNR = 0L;

    @SerializedName("Frequency")
    private Float frequency = Float.valueOf(0.0f);

    @SerializedName("Residual")
    private Integer residual = 0;

    @SerializedName("Flags")
    private Long flags = 0L;

    public GpsSatInfo() {
    }

    public GpsSatInfo(Long l, Long l2, Long l3, Long l4, Float f, Integer num, Long l5) {
        setId(l);
        setElevation(l2);
        setAzimuth(l3);
        setSNR(l4);
        setFrequency(f);
        setResidual(num);
        setFlags(l5);
    }

    public Long getId() {
        return this.id;
    }

    public GpsSatInfo setId(Long l) {
        if (l == null) {
            throw new NullPointerException("id cannot be null");
        }
        this.id = l;
        return this;
    }

    public Long getElevation() {
        return this.elevation;
    }

    public GpsSatInfo setElevation(Long l) {
        if (l == null) {
            throw new NullPointerException("elevation cannot be null");
        }
        this.elevation = l;
        return this;
    }

    public Long getAzimuth() {
        return this.azimuth;
    }

    public GpsSatInfo setAzimuth(Long l) {
        if (l == null) {
            throw new NullPointerException("azimuth cannot be null");
        }
        this.azimuth = l;
        return this;
    }

    public Long getSNR() {
        return this.sNR;
    }

    public GpsSatInfo setSNR(Long l) {
        if (l == null) {
            throw new NullPointerException("sNR cannot be null");
        }
        this.sNR = l;
        return this;
    }

    public Float getFrequency() {
        return this.frequency;
    }

    public GpsSatInfo setFrequency(Float f) {
        if (f == null) {
            throw new NullPointerException("frequency cannot be null");
        }
        this.frequency = f;
        return this;
    }

    public Integer getResidual() {
        return this.residual;
    }

    public GpsSatInfo setResidual(Integer num) {
        if (num == null) {
            throw new NullPointerException("residual cannot be null");
        }
        this.residual = num;
        return this;
    }

    public Long getFlags() {
        return this.flags;
    }

    public GpsSatInfo setFlags(Long l) {
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
        GpsSatInfo gpsSatInfo = (GpsSatInfo) obj;
        return Objects.equals(this.id, gpsSatInfo.id) && Objects.equals(this.elevation, gpsSatInfo.elevation) && Objects.equals(this.azimuth, gpsSatInfo.azimuth) && Objects.equals(this.sNR, gpsSatInfo.sNR) && Objects.equals(this.frequency, gpsSatInfo.frequency) && Objects.equals(this.residual, gpsSatInfo.residual) && Objects.equals(this.flags, gpsSatInfo.flags);
    }

    public int hashCode() {
        return Objects.hash(this.id, this.elevation, this.azimuth, this.sNR, this.frequency, this.residual, this.flags);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
