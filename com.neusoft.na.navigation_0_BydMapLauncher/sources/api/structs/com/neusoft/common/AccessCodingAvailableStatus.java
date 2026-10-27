package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class AccessCodingAvailableStatus implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140538;

    @SerializedName("TokenId")
    private Integer tokenId = 0;

    @SerializedName("ACStatus")
    private AccessCodingStatus aCStatus = AccessCodingStatus.E_SUCCESS;

    public AccessCodingAvailableStatus() {
    }

    public AccessCodingAvailableStatus(Integer num, AccessCodingStatus accessCodingStatus) {
        setTokenId(num);
        setACStatus(accessCodingStatus);
    }

    public Integer getTokenId() {
        return this.tokenId;
    }

    public AccessCodingAvailableStatus setTokenId(Integer num) {
        if (num == null) {
            throw new NullPointerException("tokenId cannot be null");
        }
        this.tokenId = num;
        return this;
    }

    public AccessCodingStatus getACStatus() {
        return this.aCStatus;
    }

    public AccessCodingAvailableStatus setACStatus(AccessCodingStatus accessCodingStatus) {
        this.aCStatus = accessCodingStatus;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AccessCodingAvailableStatus accessCodingAvailableStatus = (AccessCodingAvailableStatus) obj;
        return Objects.equals(this.tokenId, accessCodingAvailableStatus.tokenId) && Objects.equals(this.aCStatus, accessCodingAvailableStatus.aCStatus);
    }

    public int hashCode() {
        return Objects.hash(this.tokenId, this.aCStatus);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
