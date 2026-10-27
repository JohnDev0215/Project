package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.UITouchInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyPostUITouchEvent extends BaseNotification {
    private static final long serialVersionUID = -1480913982;

    @SerializedName("args")
    private UITouchInfo postUITouchEvent;

    public NotifyPostUITouchEvent() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.PostUITouchEvent");
    }

    public NotifyPostUITouchEvent(UITouchInfo uITouchInfo) {
        this();
        this.postUITouchEvent = uITouchInfo;
    }

    public UITouchInfo getPostUITouchEvent() {
        return this.postUITouchEvent;
    }

    public NotifyPostUITouchEvent setPostUITouchEvent(UITouchInfo uITouchInfo) {
        this.postUITouchEvent = uITouchInfo;
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
