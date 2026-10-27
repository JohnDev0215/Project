package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.CustomEventInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyPostCustomEvent extends BaseNotification {
    private static final long serialVersionUID = -1478141435;

    @SerializedName("args")
    private CustomEventInfo postCustomEvent;

    public NotifyPostCustomEvent() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.PostCustomEvent");
    }

    public NotifyPostCustomEvent(CustomEventInfo customEventInfo) {
        this();
        this.postCustomEvent = customEventInfo;
    }

    public CustomEventInfo getPostCustomEvent() {
        return this.postCustomEvent;
    }

    public NotifyPostCustomEvent setPostCustomEvent(CustomEventInfo customEventInfo) {
        this.postCustomEvent = customEventInfo;
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
