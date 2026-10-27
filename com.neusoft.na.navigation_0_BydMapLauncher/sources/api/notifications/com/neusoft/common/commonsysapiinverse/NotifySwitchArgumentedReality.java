package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.AROnOff;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifySwitchArgumentedReality extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private AROnOff switchArgumentedReality;

    public NotifySwitchArgumentedReality() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SwitchArgumentedReality");
    }

    public NotifySwitchArgumentedReality(AROnOff aROnOff) {
        this();
        this.switchArgumentedReality = aROnOff;
    }

    public AROnOff getSwitchArgumentedReality() {
        return this.switchArgumentedReality;
    }

    public NotifySwitchArgumentedReality setSwitchArgumentedReality(AROnOff aROnOff) {
        this.switchArgumentedReality = aROnOff;
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
