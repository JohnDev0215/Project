package api.structs.com.neusoft.onecore.pos.sensordata;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class Wheel implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1477222813;

    @SerializedName("Pulse")
    private Long pulse = 0L;

    @SerializedName("Direction")
    private Boolean direction = false;

    public Wheel() {
    }

    public Wheel(Long l, Boolean bool) {
        setPulse(l);
        setDirection(bool);
    }

    public Long getPulse() {
        return this.pulse;
    }

    public Wheel setPulse(Long l) {
        if (l == null) {
            throw new NullPointerException("pulse cannot be null");
        }
        this.pulse = l;
        return this;
    }

    public Boolean getDirection() {
        return this.direction;
    }

    public Wheel setDirection(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("direction cannot be null");
        }
        this.direction = bool;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Wheel wheel = (Wheel) obj;
        return Objects.equals(this.pulse, wheel.pulse) && Objects.equals(this.direction, wheel.direction);
    }

    public int hashCode() {
        return Objects.hash(this.pulse, this.direction);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
