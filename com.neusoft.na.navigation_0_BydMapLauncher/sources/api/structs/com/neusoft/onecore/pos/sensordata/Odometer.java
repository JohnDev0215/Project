package api.structs.com.neusoft.onecore.pos.sensordata;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class Odometer implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1477222813;

    @SerializedName("Header")
    private Header header = new Header();

    @SerializedName("FrontLeft")
    private Wheel frontLeft = new Wheel();

    @SerializedName("FrontRight")
    private Wheel frontRight = new Wheel();

    @SerializedName("RearLeft")
    private Wheel rearLeft = new Wheel();

    @SerializedName("ReartRight")
    private Wheel reartRight = new Wheel();

    public Odometer() {
    }

    public Odometer(Header header, Wheel wheel, Wheel wheel2, Wheel wheel3, Wheel wheel4) {
        setHeader(header);
        setFrontLeft(wheel);
        setFrontRight(wheel2);
        setRearLeft(wheel3);
        setReartRight(wheel4);
    }

    public Header getHeader() {
        return this.header;
    }

    public Odometer setHeader(Header header) {
        this.header = header;
        return this;
    }

    public Wheel getFrontLeft() {
        return this.frontLeft;
    }

    public Odometer setFrontLeft(Wheel wheel) {
        this.frontLeft = wheel;
        return this;
    }

    public Wheel getFrontRight() {
        return this.frontRight;
    }

    public Odometer setFrontRight(Wheel wheel) {
        this.frontRight = wheel;
        return this;
    }

    public Wheel getRearLeft() {
        return this.rearLeft;
    }

    public Odometer setRearLeft(Wheel wheel) {
        this.rearLeft = wheel;
        return this;
    }

    public Wheel getReartRight() {
        return this.reartRight;
    }

    public Odometer setReartRight(Wheel wheel) {
        this.reartRight = wheel;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Odometer odometer = (Odometer) obj;
        return Objects.equals(this.header, odometer.header) && Objects.equals(this.frontLeft, odometer.frontLeft) && Objects.equals(this.frontRight, odometer.frontRight) && Objects.equals(this.rearLeft, odometer.rearLeft) && Objects.equals(this.reartRight, odometer.reartRight);
    }

    public int hashCode() {
        return Objects.hash(this.header, this.frontLeft, this.frontRight, this.rearLeft, this.reartRight);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
