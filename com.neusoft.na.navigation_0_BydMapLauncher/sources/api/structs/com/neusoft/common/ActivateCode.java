package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ActivateCode implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140575;

    @SerializedName("TokenId")
    private Integer tokenId = 0;

    @SerializedName("File")
    private InFile file = new InFile();

    @SerializedName("Code")
    private String code = "";

    public ActivateCode() {
    }

    public ActivateCode(Integer num, InFile inFile, String str) {
        setTokenId(num);
        setFile(inFile);
        setCode(str);
    }

    public Integer getTokenId() {
        return this.tokenId;
    }

    public ActivateCode setTokenId(Integer num) {
        if (num == null) {
            throw new NullPointerException("tokenId cannot be null");
        }
        this.tokenId = num;
        return this;
    }

    public InFile getFile() {
        return this.file;
    }

    public ActivateCode setFile(InFile inFile) {
        this.file = inFile;
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public ActivateCode setCode(String str) {
        if (str == null) {
            throw new NullPointerException("code cannot be null");
        }
        this.code = str;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ActivateCode activateCode = (ActivateCode) obj;
        return Objects.equals(this.tokenId, activateCode.tokenId) && Objects.equals(this.file, activateCode.file) && Objects.equals(this.code, activateCode.code);
    }

    public int hashCode() {
        return Objects.hash(this.tokenId, this.file, this.code);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
