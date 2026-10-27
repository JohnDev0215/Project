package api.structs.com.neusoft.onecore.pos.sensordata;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class Nmea implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1477222813;

    @SerializedName("Header")
    private Header header = new Header();

    @SerializedName("NmeaString")
    private String nmeaString = "";

    public Nmea() {
    }

    public Nmea(Header header, String str) {
        setHeader(header);
        setNmeaString(str);
    }

    public Header getHeader() {
        return this.header;
    }

    public Nmea setHeader(Header header) {
        this.header = header;
        return this;
    }

    public String getNmeaString() {
        return this.nmeaString;
    }

    public Nmea setNmeaString(String str) {
        if (str == null) {
            throw new NullPointerException("nmeaString cannot be null");
        }
        this.nmeaString = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Nmea nmea = (Nmea) obj;
        return Objects.equals(this.header, nmea.header) && Objects.equals(this.nmeaString, nmea.nmeaString);
    }

    public int hashCode() {
        return Objects.hash(this.header, this.nmeaString);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
