package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class SoftwareVerInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140538;

    @SerializedName("VersionInfo")
    private String versionInfo = "";

    @SerializedName("DatabaseVerInfo")
    private String databaseVerInfo = "";

    public SoftwareVerInfo() {
    }

    public SoftwareVerInfo(String str, String str2) {
        setVersionInfo(str);
        setDatabaseVerInfo(str2);
    }

    public String getVersionInfo() {
        return this.versionInfo;
    }

    public SoftwareVerInfo setVersionInfo(String str) {
        if (str == null) {
            throw new NullPointerException("versionInfo cannot be null");
        }
        this.versionInfo = str;
        return this;
    }

    public String getDatabaseVerInfo() {
        return this.databaseVerInfo;
    }

    public SoftwareVerInfo setDatabaseVerInfo(String str) {
        if (str == null) {
            throw new NullPointerException("databaseVerInfo cannot be null");
        }
        this.databaseVerInfo = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SoftwareVerInfo softwareVerInfo = (SoftwareVerInfo) obj;
        return Objects.equals(this.versionInfo, softwareVerInfo.versionInfo) && Objects.equals(this.databaseVerInfo, softwareVerInfo.databaseVerInfo);
    }

    public int hashCode() {
        return Objects.hash(this.versionInfo, this.databaseVerInfo);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
