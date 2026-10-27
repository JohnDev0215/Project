package api.responses.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.ExportInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetExportDirectoryToUDisk extends BaseResponse {
    private static final long serialVersionUID = -1478144202;

    @SerializedName("args")
    private ExportInfo exportDirectoryToUDisk;

    public ResGetExportDirectoryToUDisk() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.common.CommonSysApiInverse.GetExportDirectoryToUDisk");
    }

    public ResGetExportDirectoryToUDisk(ExportInfo exportInfo) {
        this();
        this.exportDirectoryToUDisk = exportInfo;
    }

    public ExportInfo getExportDirectoryToUDisk() {
        return this.exportDirectoryToUDisk;
    }

    public ResGetExportDirectoryToUDisk setExportDirectoryToUDisk(ExportInfo exportInfo) {
        this.exportDirectoryToUDisk = exportInfo;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.exportDirectoryToUDisk = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetExportDirectoryToUDisk)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()));
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
