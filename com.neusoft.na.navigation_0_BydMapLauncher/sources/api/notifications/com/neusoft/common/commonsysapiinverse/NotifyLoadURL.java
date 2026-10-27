package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.URIInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyLoadURL extends BaseNotification {
    private static final long serialVersionUID = -1478141320;

    @SerializedName("args")
    private URIInfo loadURL;

    public NotifyLoadURL() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.LoadURL");
    }

    public NotifyLoadURL(URIInfo uRIInfo) {
        this();
        this.loadURL = uRIInfo;
    }

    public URIInfo getLoadURL() {
        return this.loadURL;
    }

    public NotifyLoadURL setLoadURL(URIInfo uRIInfo) {
        this.loadURL = uRIInfo;
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
