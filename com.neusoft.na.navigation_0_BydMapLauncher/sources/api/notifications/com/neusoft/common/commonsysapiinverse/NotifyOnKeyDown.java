package api.notifications.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.KeyEvent;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyOnKeyDown extends BaseNotification {
    private static final long serialVersionUID = -1478141472;

    @SerializedName("args")
    private KeyEvent onKeyDown;

    public NotifyOnKeyDown() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.common.CommonSysApiInverse.OnKeyDown");
    }

    public NotifyOnKeyDown(KeyEvent keyEvent) {
        this();
        this.onKeyDown = keyEvent;
    }

    public KeyEvent getOnKeyDown() {
        return this.onKeyDown;
    }

    public NotifyOnKeyDown setOnKeyDown(KeyEvent keyEvent) {
        this.onKeyDown = keyEvent;
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
