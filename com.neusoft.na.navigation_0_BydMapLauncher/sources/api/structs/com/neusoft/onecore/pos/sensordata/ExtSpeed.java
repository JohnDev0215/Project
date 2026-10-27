package api.structs.com.neusoft.onecore.pos.sensordata;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ExtSpeed implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1477222813;

    @SerializedName("Header")
    private Header header = new Header();

    @SerializedName("Speed")
    private Float speed = Float.valueOf(0.0f);

    public ExtSpeed() {
    }

    public ExtSpeed(Header header, Float f) {
        setHeader(header);
        setSpeed(f);
    }

    public Header getHeader() {
        return this.header;
    }

    public ExtSpeed setHeader(Header header) {
        this.header = header;
        return this;
    }

    public Float getSpeed() {
        return this.speed;
    }

    public ExtSpeed setSpeed(Float f) {
        if (f == null) {
            throw new NullPointerException("speed cannot be null");
        }
        this.speed = f;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExtSpeed extSpeed = (ExtSpeed) obj;
        return Objects.equals(this.header, extSpeed.header) && Objects.equals(this.speed, extSpeed.speed);
    }

    public int hashCode() {
        return Objects.hash(this.header, this.speed);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
