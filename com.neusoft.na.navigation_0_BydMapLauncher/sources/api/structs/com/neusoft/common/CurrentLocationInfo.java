package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class CurrentLocationInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140386;

    @SerializedName("Longitude")
    private String longitude = "";

    @SerializedName("Latitude")
    private String latitude = "";

    @SerializedName("Heading")
    private String heading = "";

    public CurrentLocationInfo() {
    }

    public CurrentLocationInfo(String str, String str2, String str3) {
        setLongitude(str);
        setLatitude(str2);
        setHeading(str3);
    }

    public String getLongitude() {
        return this.longitude;
    }

    public CurrentLocationInfo setLongitude(String str) {
        if (str == null) {
            throw new NullPointerException("longitude cannot be null");
        }
        this.longitude = str;
        return this;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public CurrentLocationInfo setLatitude(String str) {
        if (str == null) {
            throw new NullPointerException("latitude cannot be null");
        }
        this.latitude = str;
        return this;
    }

    public String getHeading() {
        return this.heading;
    }

    public CurrentLocationInfo setHeading(String str) {
        if (str == null) {
            throw new NullPointerException("heading cannot be null");
        }
        this.heading = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CurrentLocationInfo currentLocationInfo = (CurrentLocationInfo) obj;
        return Objects.equals(this.longitude, currentLocationInfo.longitude) && Objects.equals(this.latitude, currentLocationInfo.latitude) && Objects.equals(this.heading, currentLocationInfo.heading);
    }

    public int hashCode() {
        return Objects.hash(this.longitude, this.latitude, this.heading);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
