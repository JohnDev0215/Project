package api.structs.com.neusoft.common;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class FileManagementInfo implements Serializable {
    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private static final long serialVersionUID = -1478140328;

    @SerializedName("SourceFilesInfo")
    private InFile sourceFilesInfo = new InFile();

    @SerializedName("TargetPath")
    private String targetPath = "";

    public FileManagementInfo() {
    }

    public FileManagementInfo(InFile inFile, String str) {
        setSourceFilesInfo(inFile);
        setTargetPath(str);
    }

    public InFile getSourceFilesInfo() {
        return this.sourceFilesInfo;
    }

    public FileManagementInfo setSourceFilesInfo(InFile inFile) {
        this.sourceFilesInfo = inFile;
        return this;
    }

    public String getTargetPath() {
        return this.targetPath;
    }

    public FileManagementInfo setTargetPath(String str) {
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
        FileManagementInfo fileManagementInfo = (FileManagementInfo) obj;
        return Objects.equals(this.sourceFilesInfo, fileManagementInfo.sourceFilesInfo) && Objects.equals(this.targetPath, fileManagementInfo.targetPath);
    }

    public int hashCode() {
        return Objects.hash(this.sourceFilesInfo, this.targetPath);
    }

    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
