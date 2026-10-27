package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.FileManagementInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyCopyFiles extends BaseNotification {
    private static final long serialVersionUID = -1478141377;

    @SerializedName("args")
    private FileManagementInfo copyFiles;

    public NotifyCopyFiles() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.CopyFiles");
    }

    public NotifyCopyFiles(FileManagementInfo fileManagementInfo) {
        this();
        this.copyFiles = fileManagementInfo;
    }

    public FileManagementInfo getCopyFiles() {
        return this.copyFiles;
    }

    public NotifyCopyFiles setCopyFiles(FileManagementInfo fileManagementInfo) {
        this.copyFiles = fileManagementInfo;
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
