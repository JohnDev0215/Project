package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class QueryWeatherInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478139641;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    @SerializedName("GeoPosition")
    private GeoPosition geoPosition = new GeoPosition();

    public QueryWeatherInfo() {
    }

    public QueryWeatherInfo(Integer num, GeoPosition geoPosition) {
        setReqId(num);
        setGeoPosition(geoPosition);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public QueryWeatherInfo setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public GeoPosition getGeoPosition() {
        return this.geoPosition;
    }

    public QueryWeatherInfo setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        QueryWeatherInfo queryWeatherInfo = (QueryWeatherInfo) obj;
        return Objects.equals(this.reqId, queryWeatherInfo.reqId) && Objects.equals(this.geoPosition, queryWeatherInfo.geoPosition);
    }

    public int hashCode() {
        return Objects.hash(this.reqId, this.geoPosition);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
