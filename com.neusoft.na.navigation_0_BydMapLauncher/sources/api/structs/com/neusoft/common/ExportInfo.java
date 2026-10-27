package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ExportInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140480;

    @SerializedName("TokenId")
    private Integer tokenId = 0;

    @SerializedName("SourcePath")
    private String sourcePath = "";

    @SerializedName("TargetPath")
    private String targetPath = "";

    public ExportInfo() {
    }

    public ExportInfo(Integer num, String str, String str2) {
        setTokenId(num);
        setSourcePath(str);
        setTargetPath(str2);
    }

    public Integer getTokenId() {
        return this.tokenId;
    }

    public ExportInfo setTokenId(Integer num) {
        if (num == null) {
            throw new NullPointerException("tokenId cannot be null");
        }
        this.tokenId = num;
        return this;
    }

    public String getSourcePath() {
        return this.sourcePath;
    }

    public ExportInfo setSourcePath(String str) {
        if (str == null) {
            throw new NullPointerException("sourcePath cannot be null");
        }
        this.sourcePath = str;
        return this;
    }

    public String getTargetPath() {
        return this.targetPath;
    }

    public ExportInfo setTargetPath(String str) {
        if (str == null) {
            throw new NullPointerException("targetPath cannot be null");
        }
        this.targetPath = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ExportInfo exportInfo = (ExportInfo) obj;
        return Objects.equals(this.tokenId, exportInfo.tokenId) && Objects.equals(this.sourcePath, exportInfo.sourcePath) && Objects.equals(this.targetPath, exportInfo.targetPath);
    }

    public int hashCode() {
        return Objects.hash(this.tokenId, this.sourcePath, this.targetPath);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
