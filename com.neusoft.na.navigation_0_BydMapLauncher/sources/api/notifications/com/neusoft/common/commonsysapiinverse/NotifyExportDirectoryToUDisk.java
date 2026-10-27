package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.ExportInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyExportDirectoryToUDisk extends BaseNotification {
    private static final long serialVersionUID = -1478141435;

    @SerializedName("args")
    private ExportInfo exportDirectoryToUDisk;

    public NotifyExportDirectoryToUDisk() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.ExportDirectoryToUDisk");
    }

    public NotifyExportDirectoryToUDisk(ExportInfo exportInfo) {
        this();
        this.exportDirectoryToUDisk = exportInfo;
    }

    public ExportInfo getExportDirectoryToUDisk() {
        return this.exportDirectoryToUDisk;
    }

    public NotifyExportDirectoryToUDisk setExportDirectoryToUDisk(ExportInfo exportInfo) {
        this.exportDirectoryToUDisk = exportInfo;
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
