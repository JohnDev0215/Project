package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class UserProfileData implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479063969;

    @SerializedName("UserId")
    private String userId = "";

    @SerializedName("Data")
    private String data = "";

    public UserProfileData() {
    }

    public UserProfileData(String str, String str2) {
        setUserId(str);
        setData(str2);
    }

    public String getUserId() {
        return this.userId;
    }

    public UserProfileData setUserId(String str) {
        if (str == null) {
            throw new NullPointerException("userId cannot be null");
        }
        this.userId = str;
        return this;
    }

    public String getData() {
        return this.data;
    }

    public UserProfileData setData(String str) {
        if (str == null) {
            throw new NullPointerException("data cannot be null");
        }
        this.data = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserProfileData userProfileData = (UserProfileData) obj;
        return Objects.equals(this.userId, userProfileData.userId) && Objects.equals(this.data, userProfileData.data);
    }

    public int hashCode() {
        return Objects.hash(this.userId, this.data);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
