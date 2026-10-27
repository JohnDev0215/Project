package api.structs.com.neusoft.smart;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LanesInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1479991242;

    @SerializedName("LanesEnable")
    private Boolean lanesEnable = false;

    @SerializedName("LanesInfo")
    private List<LaneInfo> lanesInfo = new ArrayList();

    public LanesInfo() {
    }

    public LanesInfo(Boolean bool, List<LaneInfo> list) {
        setLanesEnable(bool);
        setLanesInfo(list);
    }

    public Boolean getLanesEnable() {
        return this.lanesEnable;
    }

    public LanesInfo setLanesEnable(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("lanesEnable cannot be null");
        }
        this.lanesEnable = bool;
        return this;
    }

    public List<LaneInfo> getLanesInfo() {
        return this.lanesInfo;
    }

    public LanesInfo setLanesInfo(List<LaneInfo> list) {
        if (list == null) {
            throw new NullPointerException("lanesInfo cannot be null");
        }
        this.lanesInfo = list;
        return this;
    }

    public LanesInfo setLanesInfo(LaneInfo... laneInfoArr) {
        return setLanesInfo(Arrays.asList(laneInfoArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LanesInfo lanesInfo = (LanesInfo) obj;
        return Objects.equals(this.lanesEnable, lanesInfo.lanesEnable) && Objects.equals(this.lanesInfo, lanesInfo.lanesInfo);
    }

    public int hashCode() {
        return Objects.hash(this.lanesEnable, this.lanesInfo);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
