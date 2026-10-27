package api.structs.com.neusoft.byd;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PrivacyPolicyInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1481838255;

    @SerializedName("Index")
    private Integer index = 0;

    @SerializedName("Timestamp")
    private BigInteger timestamp = BigInteger.ZERO;

    @SerializedName("Status")
    private Boolean status = false;

    @SerializedName("Version")
    private String version = "";

    @SerializedName("VinCode")
    private String vinCode = "";

    @SerializedName("CountryCode")
    private String countryCode = "";

    @SerializedName("IsSync")
    private Boolean isSync = false;

    public PrivacyPolicyInfo() {
    }

    public PrivacyPolicyInfo(Integer num, BigInteger bigInteger, Boolean bool, String str, String str2, String str3, Boolean bool2) {
        setIndex(num);
        setTimestamp(bigInteger);
        setStatus(bool);
        setVersion(str);
        setVinCode(str2);
        setCountryCode(str3);
        setIsSync(bool2);
    }

    public Integer getIndex() {
        return this.index;
    }

    public PrivacyPolicyInfo setIndex(Integer num) {
        if (num == null) {
            throw new NullPointerException("index cannot be null");
        }
        this.index = num;
        return this;
    }

    public BigInteger getTimestamp() {
        return this.timestamp;
    }

    public PrivacyPolicyInfo setTimestamp(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullPointerException("timestamp cannot be null");
        }
        this.timestamp = bigInteger;
        return this;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public PrivacyPolicyInfo setStatus(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("status cannot be null");
        }
        this.status = bool;
        return this;
    }

    public String getVersion() {
        return this.version;
    }

    public PrivacyPolicyInfo setVersion(String str) {
        if (str == null) {
            throw new NullPointerException("version cannot be null");
        }
        this.version = str;
        return this;
    }

    public String getVinCode() {
        return this.vinCode;
    }

    public PrivacyPolicyInfo setVinCode(String str) {
        if (str == null) {
            throw new NullPointerException("vinCode cannot be null");
        }
        this.vinCode = str;
        return this;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public PrivacyPolicyInfo setCountryCode(String str) {
        if (str == null) {
            throw new NullPointerException("countryCode cannot be null");
        }
        this.countryCode = str;
        return this;
    }

    public Boolean getIsSync() {
        return this.isSync;
    }

    public PrivacyPolicyInfo setIsSync(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("isSync cannot be null");
        }
        this.isSync = bool;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PrivacyPolicyInfo privacyPolicyInfo = (PrivacyPolicyInfo) obj;
        return Objects.equals(this.index, privacyPolicyInfo.index) && Objects.equals(this.timestamp, privacyPolicyInfo.timestamp) && Objects.equals(this.status, privacyPolicyInfo.status) && Objects.equals(this.version, privacyPolicyInfo.version) && Objects.equals(this.vinCode, privacyPolicyInfo.vinCode) && Objects.equals(this.countryCode, privacyPolicyInfo.countryCode) && Objects.equals(this.isSync, privacyPolicyInfo.isSync);
    }

    public int hashCode() {
        return Objects.hash(this.index, this.timestamp, this.status, this.version, this.vinCode, this.countryCode, this.isSync);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
