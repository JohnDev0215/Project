package api.structs.com.neusoft.onecore.pos.sensordata;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class Acceleration implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1477222755;

    @SerializedName("Header")
    private Header header = new Header();

    @SerializedName("X")
    private Integer x = 0;

    @SerializedName("Y")
    private Integer y = 0;

    @SerializedName("Z")
    private Integer z = 0;

    @SerializedName("Temperature")
    private Integer temperature = 0;

    public Acceleration() {
    }

    public Acceleration(Header header, Integer num, Integer num2, Integer num3, Integer num4) {
        setHeader(header);
        setX(num);
        setY(num2);
        setZ(num3);
        setTemperature(num4);
    }

    public Header getHeader() {
        return this.header;
    }

    public Acceleration setHeader(Header header) {
        this.header = header;
        return this;
    }

    public Integer getX() {
        return this.x;
    }

    public Acceleration setX(Integer num) {
        if (num == null) {
            throw new NullPointerException("x cannot be null");
        }
        this.x = num;
        return this;
    }

    public Integer getY() {
        return this.y;
    }

    public Acceleration setY(Integer num) {
        if (num == null) {
            throw new NullPointerException("y cannot be null");
        }
        this.y = num;
        return this;
    }

    public Integer getZ() {
        return this.z;
    }

    public Acceleration setZ(Integer num) {
        if (num == null) {
            throw new NullPointerException("z cannot be null");
        }
        this.z = num;
        return this;
    }

    public Integer getTemperature() {
        return this.temperature;
    }

    public Acceleration setTemperature(Integer num) {
        if (num == null) {
            throw new NullPointerException("temperature cannot be null");
        }
        this.temperature = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Acceleration acceleration = (Acceleration) obj;
        return Objects.equals(this.header, acceleration.header) && Objects.equals(this.x, acceleration.x) && Objects.equals(this.y, acceleration.y) && Objects.equals(this.z, acceleration.z) && Objects.equals(this.temperature, acceleration.temperature);
    }

    public int hashCode() {
        return Objects.hash(this.header, this.x, this.y, this.z, this.temperature);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
