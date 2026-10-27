package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class GwCurrentWTInfoState implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479063911;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    @SerializedName("Status")
    private String status = "";

    @SerializedName("WTBasic")
    private GwWTBasic wTBasic = new GwWTBasic();

    @SerializedName("WTInfo")
    private GwWTInfo wTInfo = new GwWTInfo();

    public GwCurrentWTInfoState() {
    }

    public GwCurrentWTInfoState(Integer num, String str, GwWTBasic gwWTBasic, GwWTInfo gwWTInfo) {
        setReqId(num);
        setStatus(str);
        setWTBasic(gwWTBasic);
        setWTInfo(gwWTInfo);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public GwCurrentWTInfoState setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public GwCurrentWTInfoState setStatus(String str) {
        if (str == null) {
            throw new NullPointerException("status cannot be null");
        }
        this.status = str;
        return this;
    }

    public GwWTBasic getWTBasic() {
        return this.wTBasic;
    }

    public GwCurrentWTInfoState setWTBasic(GwWTBasic gwWTBasic) {
        this.wTBasic = gwWTBasic;
        return this;
    }

    public GwWTInfo getWTInfo() {
        return this.wTInfo;
    }

    public GwCurrentWTInfoState setWTInfo(GwWTInfo gwWTInfo) {
        this.wTInfo = gwWTInfo;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GwCurrentWTInfoState gwCurrentWTInfoState = (GwCurrentWTInfoState) obj;
        return Objects.equals(this.reqId, gwCurrentWTInfoState.reqId) && Objects.equals(this.status, gwCurrentWTInfoState.status) && Objects.equals(this.wTBasic, gwCurrentWTInfoState.wTBasic) && Objects.equals(this.wTInfo, gwCurrentWTInfoState.wTInfo);
    }

    public int hashCode() {
        return Objects.hash(this.reqId, this.status, this.wTBasic, this.wTInfo);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
