package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DBPath;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyDBDataIntegrityCheck extends BaseNotification {
    private static final long serialVersionUID = -1478141377;

    @SerializedName("args")
    private DBPath dBDataIntegrityCheck;

    public NotifyDBDataIntegrityCheck() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.DBDataIntegrityCheck");
    }

    public NotifyDBDataIntegrityCheck(DBPath dBPath) {
        this();
        this.dBDataIntegrityCheck = dBPath;
    }

    public DBPath getDBDataIntegrityCheck() {
        return this.dBDataIntegrityCheck;
    }

    public NotifyDBDataIntegrityCheck setDBDataIntegrityCheck(DBPath dBPath) {
        this.dBDataIntegrityCheck = dBPath;
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
