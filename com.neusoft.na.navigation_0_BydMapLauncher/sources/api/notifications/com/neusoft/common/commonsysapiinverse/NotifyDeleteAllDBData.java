package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DelPath;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyDeleteAllDBData extends BaseNotification {
    private static final long serialVersionUID = -1478141377;

    @SerializedName("args")
    private DelPath deleteAllDBData;

    public NotifyDeleteAllDBData() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.DeleteAllDBData");
    }

    public NotifyDeleteAllDBData(DelPath delPath) {
        this();
        this.deleteAllDBData = delPath;
    }

    public DelPath getDeleteAllDBData() {
        return this.deleteAllDBData;
    }

    public NotifyDeleteAllDBData setDeleteAllDBData(DelPath delPath) {
        this.deleteAllDBData = delPath;
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
