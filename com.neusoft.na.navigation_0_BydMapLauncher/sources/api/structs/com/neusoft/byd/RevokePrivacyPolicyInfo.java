package api.structs.com.neusoft.byd;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class RevokePrivacyPolicyInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1481837567;

    @SerializedName("TokenId")
    private Integer tokenId = 0;

    @SerializedName("PrivacyPolicyInfo")
    private PrivacyPolicyInfo privacyPolicyInfo = new PrivacyPolicyInfo();

    public RevokePrivacyPolicyInfo() {
    }

    public RevokePrivacyPolicyInfo(Integer num, PrivacyPolicyInfo privacyPolicyInfo) {
        setTokenId(num);
        setPrivacyPolicyInfo(privacyPolicyInfo);
    }

    public Integer getTokenId() {
        return this.tokenId;
    }

    public RevokePrivacyPolicyInfo setTokenId(Integer num) {
        if (num == null) {
            throw new NullPointerException("tokenId cannot be null");
        }
        this.tokenId = num;
        return this;
    }

    public PrivacyPolicyInfo getPrivacyPolicyInfo() {
        return this.privacyPolicyInfo;
    }

    public RevokePrivacyPolicyInfo setPrivacyPolicyInfo(PrivacyPolicyInfo privacyPolicyInfo) {
        this.privacyPolicyInfo = privacyPolicyInfo;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RevokePrivacyPolicyInfo revokePrivacyPolicyInfo = (RevokePrivacyPolicyInfo) obj;
        return Objects.equals(this.tokenId, revokePrivacyPolicyInfo.tokenId) && Objects.equals(this.privacyPolicyInfo, revokePrivacyPolicyInfo.privacyPolicyInfo);
    }

    public int hashCode() {
        return Objects.hash(this.tokenId, this.privacyPolicyInfo);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
