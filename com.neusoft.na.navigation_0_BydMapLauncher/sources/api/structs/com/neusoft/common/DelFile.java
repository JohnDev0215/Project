package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class DelFile implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140538;

    @SerializedName("Path")
    private String path = "";

    @SerializedName("Name")
    private String name = "";

    public DelFile() {
    }

    public DelFile(String str, String str2) {
        setPath(str);
        setName(str2);
    }

    public String getPath() {
        return this.path;
    }

    public DelFile setPath(String str) {
        if (str == null) {
            throw new NullPointerException("path cannot be null");
        }
        this.path = str;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public DelFile setName(String str) {
        if (str == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DelFile delFile = (DelFile) obj;
        return Objects.equals(this.path, delFile.path) && Objects.equals(this.name, delFile.name);
    }

    public int hashCode() {
        return Objects.hash(this.path, this.name);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
