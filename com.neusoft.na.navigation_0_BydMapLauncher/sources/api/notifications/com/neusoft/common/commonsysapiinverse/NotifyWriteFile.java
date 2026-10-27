package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.OutFile;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyWriteFile extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private OutFile writeFile;

    public NotifyWriteFile() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.WriteFile");
    }

    public NotifyWriteFile(OutFile outFile) {
        this();
        this.writeFile = outFile;
    }

    public OutFile getWriteFile() {
        return this.writeFile;
    }

    public NotifyWriteFile setWriteFile(OutFile outFile) {
        this.writeFile = outFile;
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
