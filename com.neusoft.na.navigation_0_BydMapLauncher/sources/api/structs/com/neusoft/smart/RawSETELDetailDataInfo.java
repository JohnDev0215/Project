package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class RawSETELDetailDataInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991206;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    @SerializedName("GasStationId")
    private String gasStationId = "";

    public RawSETELDetailDataInfo() {
    }

    public RawSETELDetailDataInfo(Integer num, String str) {
        setReqId(num);
        setGasStationId(str);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public RawSETELDetailDataInfo setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public String getGasStationId() {
        return this.gasStationId;
    }

    public RawSETELDetailDataInfo setGasStationId(String str) {
        if (str == null) {
            throw new NullPointerException("gasStationId cannot be null");
        }
        this.gasStationId = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RawSETELDetailDataInfo rawSETELDetailDataInfo = (RawSETELDetailDataInfo) obj;
        return Objects.equals(this.reqId, rawSETELDetailDataInfo.reqId) && Objects.equals(this.gasStationId, rawSETELDetailDataInfo.gasStationId);
    }

    public int hashCode() {
        return Objects.hash(this.reqId, this.gasStationId);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
