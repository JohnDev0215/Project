package api.structs.com.neusoft.onecore.pos.sensordata;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GpsSatellites implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1477222813;

    @SerializedName("Information")
    private List<GpsSatInfo> information = new ArrayList();

    @SerializedName("InUse")
    private Long inUse = 0L;

    @SerializedName("InView")
    private Long inView = 0L;

    @SerializedName("GNSSSystems")
    private Long gNSSSystems = 0L;

    public GpsSatellites() {
    }

    public GpsSatellites(List<GpsSatInfo> list, Long l, Long l2, Long l3) {
        setInformation(list);
        setInUse(l);
        setInView(l2);
        setGNSSSystems(l3);
    }

    public List<GpsSatInfo> getInformation() {
        return this.information;
    }

    public GpsSatellites setInformation(List<GpsSatInfo> list) {
        if (list == null) {
            throw new NullPointerException("information cannot be null");
        }
        this.information = list;
        return this;
    }

    public GpsSatellites setInformation(GpsSatInfo... gpsSatInfoArr) {
        return setInformation(Arrays.asList(gpsSatInfoArr));
    }

    public Long getInUse() {
        return this.inUse;
    }

    public GpsSatellites setInUse(Long l) {
        if (l == null) {
            throw new NullPointerException("inUse cannot be null");
        }
        this.inUse = l;
        return this;
    }

    public Long getInView() {
        return this.inView;
    }

    public GpsSatellites setInView(Long l) {
        if (l == null) {
            throw new NullPointerException("inView cannot be null");
        }
        this.inView = l;
        return this;
    }

    public Long getGNSSSystems() {
        return this.gNSSSystems;
    }

    public GpsSatellites setGNSSSystems(Long l) {
        if (l == null) {
            throw new NullPointerException("gNSSSystems cannot be null");
        }
        this.gNSSSystems = l;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GpsSatellites gpsSatellites = (GpsSatellites) obj;
        return Objects.equals(this.information, gpsSatellites.information) && Objects.equals(this.inUse, gpsSatellites.inUse) && Objects.equals(this.inView, gpsSatellites.inView) && Objects.equals(this.gNSSSystems, gpsSatellites.gNSSSystems);
    }

    public int hashCode() {
        return Objects.hash(this.information, this.inUse, this.inView, this.gNSSSystems);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
