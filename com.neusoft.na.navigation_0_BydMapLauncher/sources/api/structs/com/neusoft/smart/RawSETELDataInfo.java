package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class RawSETELDataInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991242;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    @SerializedName("GeoPosition")
    private GeoPosition geoPosition = new GeoPosition();

    @SerializedName("Radius")
    private Integer radius = 0;

    @SerializedName("SETELPoiInfoList")
    private List<SETELPoiInfo> sETELPoiInfoList = new ArrayList();

    public RawSETELDataInfo() {
    }

    public RawSETELDataInfo(Integer num, GeoPosition geoPosition, Integer num2, List<SETELPoiInfo> list) {
        setReqId(num);
        setGeoPosition(geoPosition);
        setRadius(num2);
        setSETELPoiInfoList(list);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public RawSETELDataInfo setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public GeoPosition getGeoPosition() {
        return this.geoPosition;
    }

    public RawSETELDataInfo setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
        return this;
    }

    public Integer getRadius() {
        return this.radius;
    }

    public RawSETELDataInfo setRadius(Integer num) {
        if (num == null) {
            throw new NullPointerException("radius cannot be null");
        }
        this.radius = num;
        return this;
    }

    public List<SETELPoiInfo> getSETELPoiInfoList() {
        return this.sETELPoiInfoList;
    }

    public RawSETELDataInfo setSETELPoiInfoList(List<SETELPoiInfo> list) {
        if (list == null) {
            throw new NullPointerException("sETELPoiInfoList cannot be null");
        }
        this.sETELPoiInfoList = list;
        return this;
    }

    public RawSETELDataInfo setSETELPoiInfoList(SETELPoiInfo... sETELPoiInfoArr) {
        return setSETELPoiInfoList(Arrays.asList(sETELPoiInfoArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RawSETELDataInfo rawSETELDataInfo = (RawSETELDataInfo) obj;
        return Objects.equals(this.reqId, rawSETELDataInfo.reqId) && Objects.equals(this.geoPosition, rawSETELDataInfo.geoPosition) && Objects.equals(this.radius, rawSETELDataInfo.radius) && Objects.equals(this.sETELPoiInfoList, rawSETELDataInfo.sETELPoiInfoList);
    }

    public int hashCode() {
        return Objects.hash(this.reqId, this.geoPosition, this.radius, this.sETELPoiInfoList);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
