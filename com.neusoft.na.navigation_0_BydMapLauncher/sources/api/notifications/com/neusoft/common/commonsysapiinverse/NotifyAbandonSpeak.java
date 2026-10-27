package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DummyStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyAbandonSpeak extends BaseNotification {
    private static final long serialVersionUID = -1478141435;

    @SerializedName("args")
    private DummyStatus abandonSpeak;

    public NotifyAbandonSpeak() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.AbandonSpeak");
    }

    public NotifyAbandonSpeak(DummyStatus dummyStatus) {
        this();
        this.abandonSpeak = dummyStatus;
    }

    public DummyStatus getAbandonSpeak() {
        return this.abandonSpeak;
    }

    public NotifyAbandonSpeak setAbandonSpeak(DummyStatus dummyStatus) {
        this.abandonSpeak = dummyStatus;
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
