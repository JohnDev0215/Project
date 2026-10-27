package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.InFile;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyDeleteFile extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private InFile deleteFile;

    public NotifyDeleteFile() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.DeleteFile");
    }

    public NotifyDeleteFile(InFile inFile) {
        this();
        this.deleteFile = inFile;
    }

    public InFile getDeleteFile() {
        return this.deleteFile;
    }

    public NotifyDeleteFile setDeleteFile(InFile inFile) {
        this.deleteFile = inFile;
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
