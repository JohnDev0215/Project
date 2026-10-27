package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class InFile implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140575;

    @SerializedName("TokenId")
    private Integer tokenId = 0;

    @SerializedName("Type")
    private MediaType type = MediaType.E_FILESYSTEM;

    @SerializedName("Path")
    private String path = "";

    public InFile() {
    }

    public InFile(Integer num, MediaType mediaType, String str) {
        setTokenId(num);
        setType(mediaType);
        setPath(str);
    }

    public Integer getTokenId() {
        return this.tokenId;
    }

    public InFile setTokenId(Integer num) {
        if (num == null) {
            throw new NullPointerException("tokenId cannot be null");
        }
        this.tokenId = num;
        return this;
    }

    public MediaType getType() {
        return this.type;
    }

    public InFile setType(MediaType mediaType) {
        this.type = mediaType;
        return this;
    }

    public String getPath() {
        return this.path;
    }

    public InFile setPath(String str) {
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
        InFile inFile = (InFile) obj;
        return Objects.equals(this.tokenId, inFile.tokenId) && Objects.equals(this.type, inFile.type) && Objects.equals(this.path, inFile.path);
    }

    public int hashCode() {
        return Objects.hash(this.tokenId, this.type, this.path);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
