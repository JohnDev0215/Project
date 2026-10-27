package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class OutFile implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140575;

    @SerializedName("TokenId")
    private Integer tokenId = 0;

    @SerializedName("Path")
    private String path = "";

    @SerializedName("Name")
    private String name = "";

    @SerializedName("Value")
    private String value = "";

    public OutFile() {
    }

    public OutFile(Integer num, String str, String str2, String str3) {
        setTokenId(num);
        setPath(str);
        setName(str2);
        setValue(str3);
    }

    public Integer getTokenId() {
        return this.tokenId;
    }

    public OutFile setTokenId(Integer num) {
        if (num == null) {
            throw new NullPointerException("tokenId cannot be null");
        }
        this.tokenId = num;
        return this;
    }

    public String getPath() {
        return this.path;
    }

    public OutFile setPath(String str) {
        if (str == null) {
            throw new NullPointerException("path cannot be null");
        }
        this.path = str;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public OutFile setName(String str) {
        if (str == null) {
            throw new NullPointerException("name cannot be null");
        }
        this.name = str;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public OutFile setValue(String str) {
        if (str == null) {
            throw new NullPointerException("value cannot be null");
        }
        this.value = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OutFile outFile = (OutFile) obj;
        return Objects.equals(this.tokenId, outFile.tokenId) && Objects.equals(this.path, outFile.path) && Objects.equals(this.name, outFile.name) && Objects.equals(this.value, outFile.value);
    }

    public int hashCode() {
        return Objects.hash(this.tokenId, this.path, this.name, this.value);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
