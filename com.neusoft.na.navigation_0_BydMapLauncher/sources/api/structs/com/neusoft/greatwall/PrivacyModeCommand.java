package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class PrivacyModeCommand implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480909959;

    @SerializedName("OnOff")
    private Boolean onOff = false;

    public PrivacyModeCommand() {
    }

    public PrivacyModeCommand(Boolean bool) {
        setOnOff(bool);
    }

    public Boolean getOnOff() {
        return this.onOff;
    }

    public PrivacyModeCommand setOnOff(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("onOff cannot be null");
        }
        this.onOff = bool;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.onOff, ((PrivacyModeCommand) obj).onOff);
    }

    public int hashCode() {
        return Objects.hash(this.onOff);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
