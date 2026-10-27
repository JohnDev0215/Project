package api.structs.com.neusoft.byd;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class FileFullPath implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1481837567;

    @SerializedName("FullPath")
    private String fullPath = "";

    public FileFullPath() {
    }

    public FileFullPath(String str) {
        setFullPath(str);
    }

    public String getFullPath() {
        return this.fullPath;
    }

    public FileFullPath setFullPath(String str) {
        if (str == null) {
            throw new NullPointerException("fullPath cannot be null");
        }
        this.fullPath = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.fullPath, ((FileFullPath) obj).fullPath);
    }

    public int hashCode() {
        return Objects.hash(this.fullPath);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
