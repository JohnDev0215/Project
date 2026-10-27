package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PrivacyAgreementInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479990461;

    @SerializedName("AuthorizedStatus")
    private AuthorizedStatus authorizedStatus = AuthorizedStatus.DEFAULT;

    @SerializedName("FilePath")
    private String filePath = "";

    public PrivacyAgreementInfo() {
    }

    public PrivacyAgreementInfo(AuthorizedStatus authorizedStatus, String str) {
        setAuthorizedStatus(authorizedStatus);
        setFilePath(str);
    }

    public AuthorizedStatus getAuthorizedStatus() {
        return this.authorizedStatus;
    }

    public PrivacyAgreementInfo setAuthorizedStatus(AuthorizedStatus authorizedStatus) {
        this.authorizedStatus = authorizedStatus;
        return this;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public PrivacyAgreementInfo setFilePath(String str) {
        if (str == null) {
            throw new NullPointerException("filePath cannot be null");
        }
        this.filePath = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PrivacyAgreementInfo privacyAgreementInfo = (PrivacyAgreementInfo) obj;
        return Objects.equals(this.authorizedStatus, privacyAgreementInfo.authorizedStatus) && Objects.equals(this.filePath, privacyAgreementInfo.filePath);
    }

    public int hashCode() {
        return Objects.hash(this.authorizedStatus, this.filePath);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
