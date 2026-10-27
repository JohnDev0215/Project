package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class AppExitInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140328;

    @SerializedName("timeStamp")
    private String timeStamp = "";

    @SerializedName("Mode")
    private ExitMode mode = ExitMode.AEM_DEFAULT;

    public AppExitInfo() {
    }

    public AppExitInfo(String str, ExitMode exitMode) {
        setTimeStamp(str);
        setMode(exitMode);
    }

    public String getTimeStamp() {
        return this.timeStamp;
    }

    public AppExitInfo setTimeStamp(String str) {
        if (str == null) {
            throw new NullPointerException("timeStamp cannot be null");
        }
        this.timeStamp = str;
        return this;
    }

    public ExitMode getMode() {
        return this.mode;
    }

    public AppExitInfo setMode(ExitMode exitMode) {
        this.mode = exitMode;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppExitInfo appExitInfo = (AppExitInfo) obj;
        return Objects.equals(this.timeStamp, appExitInfo.timeStamp) && Objects.equals(this.mode, appExitInfo.mode);
    }

    public int hashCode() {
        return Objects.hash(this.timeStamp, this.mode);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
