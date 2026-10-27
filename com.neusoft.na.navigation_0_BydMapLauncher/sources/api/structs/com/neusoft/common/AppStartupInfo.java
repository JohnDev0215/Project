package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class AppStartupInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140328;

    @SerializedName("NaviVer")
    private String naviVer = "";

    @SerializedName("DBVer")
    private String dBVer = "";

    @SerializedName("IVIVer")
    private String iVIVer = "";

    @SerializedName("Mode")
    private StartupMode mode = StartupMode.ASM_DEFAULT;

    @SerializedName("IsFirstStarted")
    private Boolean isFirstStarted = false;

    @SerializedName("CarModelID")
    private String carModelID = "";

    public AppStartupInfo() {
    }

    public AppStartupInfo(String str, String str2, String str3, StartupMode startupMode, Boolean bool, String str4) {
        setNaviVer(str);
        setDBVer(str2);
        setIVIVer(str3);
        setMode(startupMode);
        setIsFirstStarted(bool);
        setCarModelID(str4);
    }

    public String getNaviVer() {
        return this.naviVer;
    }

    public AppStartupInfo setNaviVer(String str) {
        if (str == null) {
            throw new NullPointerException("naviVer cannot be null");
        }
        this.naviVer = str;
        return this;
    }

    public String getDBVer() {
        return this.dBVer;
    }

    public AppStartupInfo setDBVer(String str) {
        if (str == null) {
            throw new NullPointerException("dBVer cannot be null");
        }
        this.dBVer = str;
        return this;
    }

    public String getIVIVer() {
        return this.iVIVer;
    }

    public AppStartupInfo setIVIVer(String str) {
        if (str == null) {
            throw new NullPointerException("iVIVer cannot be null");
        }
        this.iVIVer = str;
        return this;
    }

    public StartupMode getMode() {
        return this.mode;
    }

    public AppStartupInfo setMode(StartupMode startupMode) {
        this.mode = startupMode;
        return this;
    }

    public Boolean getIsFirstStarted() {
        return this.isFirstStarted;
    }

    public AppStartupInfo setIsFirstStarted(Boolean bool) {
        if (bool == null) {
            throw new NullPointerException("isFirstStarted cannot be null");
        }
        this.isFirstStarted = bool;
        return this;
    }

    public String getCarModelID() {
        return this.carModelID;
    }

    public AppStartupInfo setCarModelID(String str) {
        if (str == null) {
            throw new NullPointerException("carModelID cannot be null");
        }
        this.carModelID = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AppStartupInfo appStartupInfo = (AppStartupInfo) obj;
        return Objects.equals(this.naviVer, appStartupInfo.naviVer) && Objects.equals(this.dBVer, appStartupInfo.dBVer) && Objects.equals(this.iVIVer, appStartupInfo.iVIVer) && Objects.equals(this.mode, appStartupInfo.mode) && Objects.equals(this.isFirstStarted, appStartupInfo.isFirstStarted) && Objects.equals(this.carModelID, appStartupInfo.carModelID);
    }

    public int hashCode() {
        return Objects.hash(this.naviVer, this.dBVer, this.iVIVer, this.mode, this.isFirstStarted, this.carModelID);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
