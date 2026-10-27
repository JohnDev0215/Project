package api.structs.com.neusoft.ecarx;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class SETELServiceStatus implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480912875;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    @SerializedName("SETELServiceStatus")
    private Boolean sETELServiceStatus = false;

    public SETELServiceStatus() {
    }

    public SETELServiceStatus(Integer num, Boolean bool) {
        setReqId(num);
        setSETELServiceStatus(bool);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public SETELServiceStatus setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public Boolean getSETELServiceStatus() {
        return this.sETELServiceStatus;
    }

    public SETELServiceStatus setSETELServiceStatus(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("sETELServiceStatus cannot be null");
        }
        this.sETELServiceStatus = bool;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SETELServiceStatus sETELServiceStatus = (SETELServiceStatus) obj;
        return Objects.equals(this.reqId, sETELServiceStatus.reqId) && Objects.equals(this.sETELServiceStatus, sETELServiceStatus.sETELServiceStatus);
    }

    public int hashCode() {
        return Objects.hash(this.reqId, this.sETELServiceStatus);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
