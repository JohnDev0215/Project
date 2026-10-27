package api.structs.com.neusoft.byd;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class AuthConditionInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1481837510;

    @SerializedName("Vid")
    private String vid = "";

    @SerializedName("PubKey")
    private String pubKey = "";

    public AuthConditionInfo() {
    }

    public AuthConditionInfo(String str, String str2) {
        setVid(str);
        setPubKey(str2);
    }

    public String getVid() {
        return this.vid;
    }

    public AuthConditionInfo setVid(String str) {
        if (str == null) {
            throw new NullPointerException("vid cannot be null");
        }
        this.vid = str;
        return this;
    }

    public String getPubKey() {
        return this.pubKey;
    }

    public AuthConditionInfo setPubKey(String str) {
        if (str == null) {
            throw new NullPointerException("pubKey cannot be null");
        }
        this.pubKey = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AuthConditionInfo authConditionInfo = (AuthConditionInfo) obj;
        return Objects.equals(this.vid, authConditionInfo.vid) && Objects.equals(this.pubKey, authConditionInfo.pubKey);
    }

    public int hashCode() {
        return Objects.hash(this.vid, this.pubKey);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
