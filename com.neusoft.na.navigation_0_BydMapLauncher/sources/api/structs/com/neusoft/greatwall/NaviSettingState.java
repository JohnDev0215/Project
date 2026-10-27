package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class NaviSettingState implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480910017;

    @SerializedName("NaviSettingState")
    private String naviSettingState = "";

    public NaviSettingState() {
    }

    public NaviSettingState(String str) {
        setNaviSettingState(str);
    }

    public String getNaviSettingState() {
        return this.naviSettingState;
    }

    public NaviSettingState setNaviSettingState(String str) {
        if (str == null) {
            throw new NullPointerException("naviSettingState cannot be null");
        }
        this.naviSettingState = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.naviSettingState, ((NaviSettingState) obj).naviSettingState);
    }

    public int hashCode() {
        return Objects.hash(this.naviSettingState);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
