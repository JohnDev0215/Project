package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class URIInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140328;

    @SerializedName("TokenId")
    private Integer tokenId = 0;

    @SerializedName("URL")
    private String uRL = "";

    public URIInfo() {
    }

    public URIInfo(Integer num, String str) {
        setTokenId(num);
        setURL(str);
    }

    public Integer getTokenId() {
        return this.tokenId;
    }

    public URIInfo setTokenId(Integer num) {
        if (num == null) {
            throw new NullPointerException("tokenId cannot be null");
        }
        this.tokenId = num;
        return this;
    }

    public String getURL() {
        return this.uRL;
    }

    public URIInfo setURL(String str) {
        if (str == null) {
            throw new NullPointerException("uRL cannot be null");
        }
        this.uRL = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        URIInfo uRIInfo = (URIInfo) obj;
        return Objects.equals(this.tokenId, uRIInfo.tokenId) && Objects.equals(this.uRL, uRIInfo.uRL);
    }

    public int hashCode() {
        return Objects.hash(this.tokenId, this.uRL);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
