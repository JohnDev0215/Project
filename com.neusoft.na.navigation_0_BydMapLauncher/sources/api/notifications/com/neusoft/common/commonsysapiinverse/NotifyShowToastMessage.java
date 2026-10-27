package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.MessageInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyShowToastMessage extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private MessageInfo showToastMessage;

    public NotifyShowToastMessage() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.ShowToastMessage");
    }

    public NotifyShowToastMessage(MessageInfo messageInfo) {
        this();
        this.showToastMessage = messageInfo;
    }

    public MessageInfo getShowToastMessage() {
        return this.showToastMessage;
    }

    public NotifyShowToastMessage setShowToastMessage(MessageInfo messageInfo) {
        this.showToastMessage = messageInfo;
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
