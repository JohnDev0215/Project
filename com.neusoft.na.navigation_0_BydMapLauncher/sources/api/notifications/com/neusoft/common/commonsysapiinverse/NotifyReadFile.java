package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.InFile;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyReadFile extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private InFile readFile;

    public NotifyReadFile() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.ReadFile");
    }

    public NotifyReadFile(InFile inFile) {
        this();
        this.readFile = inFile;
    }

    public InFile getReadFile() {
        return this.readFile;
    }

    public NotifyReadFile setReadFile(InFile inFile) {
        this.readFile = inFile;
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
