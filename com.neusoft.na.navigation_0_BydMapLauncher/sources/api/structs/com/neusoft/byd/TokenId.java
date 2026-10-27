package api.structs.com.neusoft.byd;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class TokenId implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1481838313;

    @SerializedName("TokenId")
    private Integer tokenId = 0;

    public TokenId() {
    }

    public TokenId(Integer num) {
        setTokenId(num);
    }

    public Integer getTokenId() {
        return this.tokenId;
    }

    public TokenId setTokenId(Integer num) {
        if (num == null) {
            throw new NullPointerException("tokenId cannot be null");
        }
        this.tokenId = num;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.tokenId, ((TokenId) obj).tokenId);
    }

    public int hashCode() {
        return Objects.hash(this.tokenId);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
