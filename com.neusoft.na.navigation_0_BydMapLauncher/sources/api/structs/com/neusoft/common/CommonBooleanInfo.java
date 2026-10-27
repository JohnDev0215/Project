package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class CommonBooleanInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140386;

    @SerializedName("InfoBool")
    private Boolean infoBool = false;

    public CommonBooleanInfo() {
    }

    public CommonBooleanInfo(Boolean bool) {
        setInfoBool(bool);
    }

    public Boolean getInfoBool() {
        return this.infoBool;
    }

    public CommonBooleanInfo setInfoBool(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("infoBool cannot be null");
        }
        this.infoBool = bool;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.infoBool, ((CommonBooleanInfo) obj).infoBool);
    }

    public int hashCode() {
        return Objects.hash(this.infoBool);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
