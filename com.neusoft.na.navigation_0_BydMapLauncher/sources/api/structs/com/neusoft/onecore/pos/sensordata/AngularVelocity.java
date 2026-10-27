package api.structs.com.neusoft.onecore.pos.sensordata;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class AngularVelocity implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1477222813;

    @SerializedName("Header")
    private Header header = new Header();

    @SerializedName("Roll")
    private Integer roll = 0;

    @SerializedName("Pitch")
    private Integer pitch = 0;

    @SerializedName("Yaw")
    private Integer yaw = 0;

    @SerializedName("Temperature")
    private Integer temperature = 0;

    public AngularVelocity() {
    }

    public AngularVelocity(Header header, Integer num, Integer num2, Integer num3, Integer num4) {
        setHeader(header);
        setRoll(num);
        setPitch(num2);
        setYaw(num3);
        setTemperature(num4);
    }

    public Header getHeader() {
        return this.header;
    }

    public AngularVelocity setHeader(Header header) {
        this.header = header;
        return this;
    }

    public Integer getRoll() {
        return this.roll;
    }

    public AngularVelocity setRoll(Integer num) {
        if (num == null) {
            throw new NullPointerException("roll cannot be null");
        }
        this.roll = num;
        return this;
    }

    public Integer getPitch() {
        return this.pitch;
    }

    public AngularVelocity setPitch(Integer num) {
        if (num == null) {
            throw new NullPointerException("pitch cannot be null");
        }
        this.pitch = num;
        return this;
    }

    public Integer getYaw() {
        return this.yaw;
    }

    public AngularVelocity setYaw(Integer num) {
        if (num == null) {
            throw new NullPointerException("yaw cannot be null");
        }
        this.yaw = num;
        return this;
    }

    public Integer getTemperature() {
        return this.temperature;
    }

    public AngularVelocity setTemperature(Integer num) {
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
        AngularVelocity angularVelocity = (AngularVelocity) obj;
        return Objects.equals(this.header, angularVelocity.header) && Objects.equals(this.roll, angularVelocity.roll) && Objects.equals(this.pitch, angularVelocity.pitch) && Objects.equals(this.yaw, angularVelocity.yaw) && Objects.equals(this.temperature, angularVelocity.temperature);
    }

    public int hashCode() {
        return Objects.hash(this.header, this.roll, this.pitch, this.yaw, this.temperature);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
