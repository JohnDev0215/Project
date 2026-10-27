package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class FavoriteCommandBase implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479990497;

    @SerializedName("ReqId")
    private Integer reqId = 0;

    public FavoriteCommandBase() {
    }

    public FavoriteCommandBase(Integer num) {
        setReqId(num);
    }

    public Integer getReqId() {
        return this.reqId;
    }

    public FavoriteCommandBase setReqId(Integer num) {
        if (num == null) {
            throw new NullPointerException("reqId cannot be null");
        }
        this.reqId = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.reqId, ((FavoriteCommandBase) obj).reqId);
    }

    public int hashCode() {
        return Objects.hash(this.reqId);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
