package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ProcessedSETELDetailDataInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991206;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    @SerializedName("Code")
    private Integer code = 0;

    @SerializedName("Message")
    private String message = "";

    @SerializedName("AvailableGunPumps")
    private Integer availableGunPumps = 0;

    public ProcessedSETELDetailDataInfo() {
    }

    public ProcessedSETELDetailDataInfo(Integer num, Integer num2, String str, Integer num3) {
        setReqId(num);
        setCode(num2);
        setMessage(str);
        setAvailableGunPumps(num3);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public ProcessedSETELDetailDataInfo setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public Integer getCode() {
        return this.code;
    }

    public ProcessedSETELDetailDataInfo setCode(Integer num) {
        if (num == null) {
            throw new NullPointerException("code cannot be null");
        }
        this.code = num;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ProcessedSETELDetailDataInfo setMessage(String str) {
        if (str == null) {
            throw new NullPointerException("message cannot be null");
        }
        this.message = str;
        return this;
    }

    public Integer getAvailableGunPumps() {
        return this.availableGunPumps;
    }

    public ProcessedSETELDetailDataInfo setAvailableGunPumps(Integer num) {
        if (num == null) {
            throw new NullPointerException("availableGunPumps cannot be null");
        }
        this.availableGunPumps = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProcessedSETELDetailDataInfo processedSETELDetailDataInfo = (ProcessedSETELDetailDataInfo) obj;
        return Objects.equals(this.reqId, processedSETELDetailDataInfo.reqId) && Objects.equals(this.code, processedSETELDetailDataInfo.code) && Objects.equals(this.message, processedSETELDetailDataInfo.message) && Objects.equals(this.availableGunPumps, processedSETELDetailDataInfo.availableGunPumps);
    }

    public int hashCode() {
        return Objects.hash(this.reqId, this.code, this.message, this.availableGunPumps);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
