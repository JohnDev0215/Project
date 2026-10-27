package api.structs.com.neusoft.greatwall;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ViewInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1480909959;

    @SerializedName("ViewInfo")
    private String viewInfo = "";

    public ViewInfo() {
    }

    public ViewInfo(String str) {
        setViewInfo(str);
    }

    public String getViewInfo() {
        return this.viewInfo;
    }

    public ViewInfo setViewInfo(String str) {
        if (str == null) {
            throw new NullPointerException("viewInfo cannot be null");
        }
        this.viewInfo = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.viewInfo, ((ViewInfo) obj).viewInfo);
    }

    public int hashCode() {
        return Objects.hash(this.viewInfo);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
