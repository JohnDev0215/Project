package api.structs.com.neusoft.byd;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class VerifyConditionInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1481837510;

    @SerializedName("VerCondition")
    private String verCondition = "";

    public VerifyConditionInfo() {
    }

    public VerifyConditionInfo(String str) {
        setVerCondition(str);
    }

    public String getVerCondition() {
        return this.verCondition;
    }

    public VerifyConditionInfo setVerCondition(String str) {
        if (str == null) {
            throw new NullPointerException("verCondition cannot be null");
        }
        this.verCondition = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.verCondition, ((VerifyConditionInfo) obj).verCondition);
    }

    public int hashCode() {
        return Objects.hash(this.verCondition);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
