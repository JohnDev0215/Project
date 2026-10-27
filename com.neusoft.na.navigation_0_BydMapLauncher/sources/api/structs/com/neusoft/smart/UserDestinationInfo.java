package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class UserDestinationInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991394;

    @SerializedName("UserId")
    private String userId = "";

    @SerializedName("IsHostUser")
    private Boolean isHostUser = false;

    @SerializedName("UserDestinationData")
    private String userDestinationData = "";

    public UserDestinationInfo() {
    }

    public UserDestinationInfo(String str, Boolean bool, String str2) {
        setUserId(str);
        setIsHostUser(bool);
        setUserDestinationData(str2);
    }

    public String getUserId() {
        return this.userId;
    }

    public UserDestinationInfo setUserId(String str) {
        if (str == null) {
            throw new NullPointerException("userId cannot be null");
        }
        this.userId = str;
        return this;
    }

    public Boolean getIsHostUser() {
        return this.isHostUser;
    }

    public UserDestinationInfo setIsHostUser(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("isHostUser cannot be null");
        }
        this.isHostUser = bool;
        return this;
    }

    public String getUserDestinationData() {
        return this.userDestinationData;
    }

    public UserDestinationInfo setUserDestinationData(String str) {
        if (str == null) {
            throw new NullPointerException("userDestinationData cannot be null");
        }
        this.userDestinationData = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserDestinationInfo userDestinationInfo = (UserDestinationInfo) obj;
        return Objects.equals(this.userId, userDestinationInfo.userId) && Objects.equals(this.isHostUser, userDestinationInfo.isHostUser) && Objects.equals(this.userDestinationData, userDestinationInfo.userDestinationData);
    }

    public int hashCode() {
        return Objects.hash(this.userId, this.isHostUser, this.userDestinationData);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
