package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.BeepTone;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyPlayBeepTone extends BaseNotification {
    private static final long serialVersionUID = -1478141435;

    @SerializedName("args")
    private BeepTone playBeepTone;

    public NotifyPlayBeepTone() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.PlayBeepTone");
    }

    public NotifyPlayBeepTone(BeepTone beepTone) {
        this();
        this.playBeepTone = beepTone;
    }

    public BeepTone getPlayBeepTone() {
        return this.playBeepTone;
    }

    public NotifyPlayBeepTone setPlayBeepTone(BeepTone beepTone) {
        this.playBeepTone = beepTone;
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
