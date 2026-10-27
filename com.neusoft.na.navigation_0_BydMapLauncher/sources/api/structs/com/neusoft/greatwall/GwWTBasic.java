package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GwWTBasic implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479063911;

    @SerializedName("Cname")
    private String cname = "";

    @SerializedName("Cnty")
    private String cnty = "";

    @SerializedName("Lat")
    private String lat = "";

    @SerializedName("Lon")
    private String lon = "";

    @SerializedName("Tz")
    private String tz = "";

    @SerializedName("LocTime")
    private String locTime = "";

    @SerializedName("UTCTime")
    private String uTCTime = "";

    public GwWTBasic() {
    }

    public GwWTBasic(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        setCname(str);
        setCnty(str2);
        setLat(str3);
        setLon(str4);
        setTz(str5);
        setLocTime(str6);
        setUTCTime(str7);
    }

    public String getCname() {
        return this.cname;
    }

    public GwWTBasic setCname(String str) {
        if (str == null) {
            throw new NullPointerException("cname cannot be null");
        }
        this.cname = str;
        return this;
    }

    public String getCnty() {
        return this.cnty;
    }

    public GwWTBasic setCnty(String str) {
        if (str == null) {
            throw new NullPointerException("cnty cannot be null");
        }
        this.cnty = str;
        return this;
    }

    public String getLat() {
        return this.lat;
    }

    public GwWTBasic setLat(String str) {
        if (str == null) {
            throw new NullPointerException("lat cannot be null");
        }
        this.lat = str;
        return this;
    }

    public String getLon() {
        return this.lon;
    }

    public GwWTBasic setLon(String str) {
        if (str == null) {
            throw new NullPointerException("lon cannot be null");
        }
        this.lon = str;
        return this;
    }

    public String getTz() {
        return this.tz;
    }

    public GwWTBasic setTz(String str) {
        if (str == null) {
            throw new NullPointerException("tz cannot be null");
        }
        this.tz = str;
        return this;
    }

    public String getLocTime() {
        return this.locTime;
    }

    public GwWTBasic setLocTime(String str) {
        if (str == null) {
            throw new NullPointerException("locTime cannot be null");
        }
        this.locTime = str;
        return this;
    }

    public String getUTCTime() {
        return this.uTCTime;
    }

    public GwWTBasic setUTCTime(String str) {
        if (str == null) {
            throw new NullPointerException("uTCTime cannot be null");
        }
        this.uTCTime = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GwWTBasic gwWTBasic = (GwWTBasic) obj;
        return Objects.equals(this.cname, gwWTBasic.cname) && Objects.equals(this.cnty, gwWTBasic.cnty) && Objects.equals(this.lat, gwWTBasic.lat) && Objects.equals(this.lon, gwWTBasic.lon) && Objects.equals(this.tz, gwWTBasic.tz) && Objects.equals(this.locTime, gwWTBasic.locTime) && Objects.equals(this.uTCTime, gwWTBasic.uTCTime);
    }

    public int hashCode() {
        return Objects.hash(this.cname, this.cnty, this.lat, this.lon, this.tz, this.locTime, this.uTCTime);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
