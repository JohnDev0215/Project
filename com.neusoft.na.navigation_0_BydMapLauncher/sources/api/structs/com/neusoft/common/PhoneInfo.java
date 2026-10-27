package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PhoneInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140575;

    @SerializedName("PhoneNumber")
    private String phoneNumber = "";

    public PhoneInfo() {
    }

    public PhoneInfo(String str) {
        setPhoneNumber(str);
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public PhoneInfo setPhoneNumber(String str) {
        if (str == null) {
            throw new NullPointerException("phoneNumber cannot be null");
        }
        this.phoneNumber = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.phoneNumber, ((PhoneInfo) obj).phoneNumber);
    }

    public int hashCode() {
        return Objects.hash(this.phoneNumber);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
