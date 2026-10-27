package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DelPath implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140386;

    @SerializedName("Path")
    private String path = "";

    public DelPath() {
    }

    public DelPath(String str) {
        setPath(str);
    }

    public String getPath() {
        return this.path;
    }

    public DelPath setPath(String str) {
        if (str == null) {
            throw new NullPointerException("path cannot be null");
        }
        this.path = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.path, ((DelPath) obj).path);
    }

    public int hashCode() {
        return Objects.hash(this.path);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
