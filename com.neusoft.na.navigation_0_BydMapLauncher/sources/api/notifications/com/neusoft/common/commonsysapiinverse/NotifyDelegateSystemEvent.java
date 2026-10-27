package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.EventType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyDelegateSystemEvent extends BaseNotification {
    private static final long serialVersionUID = -1478141320;

    @SerializedName("args")
    private EventType delegateSystemEvent;

    public NotifyDelegateSystemEvent() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.DelegateSystemEvent");
    }

    public NotifyDelegateSystemEvent(EventType eventType) {
        this();
        this.delegateSystemEvent = eventType;
    }

    public EventType getDelegateSystemEvent() {
        return this.delegateSystemEvent;
    }

    public NotifyDelegateSystemEvent setDelegateSystemEvent(EventType eventType) {
        this.delegateSystemEvent = eventType;
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
