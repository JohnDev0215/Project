package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LaneInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480909959;

    @SerializedName("Display")
    private Boolean display = false;

    @SerializedName("LaneIcon")
    private List<Integer> laneIcon = new ArrayList();

    public LaneInfo() {
    }

    public LaneInfo(Boolean bool, List<Integer> list) {
        setDisplay(bool);
        setLaneIcon(list);
    }

    public Boolean getDisplay() {
        return this.display;
    }

    public LaneInfo setDisplay(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("display cannot be null");
        }
        this.display = bool;
        return this;
    }

    public List<Integer> getLaneIcon() {
        return this.laneIcon;
    }

    public LaneInfo setLaneIcon(List<Integer> list) {
        if (list == null) {
            throw new NullPointerException("laneIcon cannot be null");
        }
        this.laneIcon = list;
        return this;
    }

    public LaneInfo setLaneIcon(Integer... numArr) {
        return setLaneIcon(Arrays.asList(numArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LaneInfo laneInfo = (LaneInfo) obj;
        return Objects.equals(this.display, laneInfo.display) && Objects.equals(this.laneIcon, laneInfo.laneIcon);
    }

    public int hashCode() {
        return Objects.hash(this.display, this.laneIcon);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
