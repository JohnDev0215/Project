package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.FileManagementInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyExtractFiles extends BaseNotification {
    private static final long serialVersionUID = -1478141377;

    @SerializedName("args")
    private FileManagementInfo extractFiles;

    public NotifyExtractFiles() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.ExtractFiles");
    }

    public NotifyExtractFiles(FileManagementInfo fileManagementInfo) {
        this();
        this.extractFiles = fileManagementInfo;
    }

    public FileManagementInfo getExtractFiles() {
        return this.extractFiles;
    }

    public NotifyExtractFiles setExtractFiles(FileManagementInfo fileManagementInfo) {
        this.extractFiles = fileManagementInfo;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public EProjectInterfaceType getProjectInterfaceType() {
        return EProjectInterfaceType.OneCore;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public String toString() {
        return getProjectInterfaceType().getGson().toJson(this);
    }
}
