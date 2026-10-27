package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ImmersionStatus implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140386;

    @SerializedName("ImmersionStatus")
    private Boolean immersionStatus = false;

    public ImmersionStatus() {
    }

    public ImmersionStatus(Boolean bool) {
        setImmersionStatus(bool);
    }

    public Boolean getImmersionStatus() {
        return this.immersionStatus;
    }

    public ImmersionStatus setImmersionStatus(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("immersionStatus cannot be null");
        }
        this.immersionStatus = bool;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.immersionStatus, ((ImmersionStatus) obj).immersionStatus);
    }

    public int hashCode() {
        return Objects.hash(this.immersionStatus);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
