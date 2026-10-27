package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class AppFocusInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478139641;

    @SerializedName("Focus")
    private Boolean focus = false;

    @SerializedName("RunForeground")
    private Boolean runForeground = false;

    public AppFocusInfo() {
    }

    public AppFocusInfo(Boolean bool, Boolean bool2) {
        setFocus(bool);
        setRunForeground(bool2);
    }

    public Boolean getFocus() {
        return this.focus;
    }

    public AppFocusInfo setFocus(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("focus cannot be null");
        }
        this.focus = bool;
        return this;
    }

    public Boolean getRunForeground() {
        return this.runForeground;
    }

    public AppFocusInfo setRunForeground(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("runForeground cannot be null");
        }
        this.runForeground = bool;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppFocusInfo appFocusInfo = (AppFocusInfo) obj;
        return Objects.equals(this.focus, appFocusInfo.focus) && Objects.equals(this.runForeground, appFocusInfo.runForeground);
    }

    public int hashCode() {
        return Objects.hash(this.focus, this.runForeground);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
