package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DashboardMirrorInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478139641;

    @SerializedName("DashboardId")
    private String dashboardId = "";

    @SerializedName("Type")
    private DashboardType type = DashboardType.DBT_DEFAULT;

    public DashboardMirrorInfo() {
    }

    public DashboardMirrorInfo(String str, DashboardType dashboardType) {
        setDashboardId(str);
        setType(dashboardType);
    }

    public String getDashboardId() {
        return this.dashboardId;
    }

    public DashboardMirrorInfo setDashboardId(String str) {
        if (str == null) {
            throw new NullPointerException("dashboardId cannot be null");
        }
        this.dashboardId = str;
        return this;
    }

    public DashboardType getType() {
        return this.type;
    }

    public DashboardMirrorInfo setType(DashboardType dashboardType) {
        this.type = dashboardType;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DashboardMirrorInfo dashboardMirrorInfo = (DashboardMirrorInfo) obj;
        return Objects.equals(this.dashboardId, dashboardMirrorInfo.dashboardId) && Objects.equals(this.type, dashboardMirrorInfo.type);
    }

    public int hashCode() {
        return Objects.hash(this.dashboardId, this.type);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
