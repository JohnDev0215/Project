package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class RequestGwWTInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479063969;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    @SerializedName("GeoPosition")
    private GeoPosition geoPosition = new GeoPosition();

    public RequestGwWTInfo() {
    }

    public RequestGwWTInfo(Integer num, GeoPosition geoPosition) {
        setReqId(num);
        setGeoPosition(geoPosition);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public RequestGwWTInfo setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public GeoPosition getGeoPosition() {
        return this.geoPosition;
    }

    public RequestGwWTInfo setGeoPosition(GeoPosition geoPosition) {
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
        RequestGwWTInfo requestGwWTInfo = (RequestGwWTInfo) obj;
        return Objects.equals(this.reqId, requestGwWTInfo.reqId) && Objects.equals(this.geoPosition, requestGwWTInfo.geoPosition);
    }

    public int hashCode() {
        return Objects.hash(this.reqId, this.geoPosition);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
