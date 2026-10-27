package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.SpeakMsg;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifySpeak extends BaseNotification {
    private static final long serialVersionUID = -1478141435;

    @SerializedName("args")
    private SpeakMsg speak;

    public NotifySpeak() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.Speak");
    }

    public NotifySpeak(SpeakMsg speakMsg) {
        this();
        this.speak = speakMsg;
    }

    public SpeakMsg getSpeak() {
        return this.speak;
    }

    public NotifySpeak setSpeak(SpeakMsg speakMsg) {
        this.speak = speakMsg;
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
