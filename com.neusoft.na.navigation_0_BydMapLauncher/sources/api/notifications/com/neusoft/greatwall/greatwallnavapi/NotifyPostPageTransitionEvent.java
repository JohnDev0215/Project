package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.PageTransitionInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyPostPageTransitionEvent extends BaseNotification {
    private static final long serialVersionUID = -1480913982;

    @SerializedName("args")
    private PageTransitionInfo postPageTransitionEvent;

    public NotifyPostPageTransitionEvent() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.PostPageTransitionEvent");
    }

    public NotifyPostPageTransitionEvent(PageTransitionInfo pageTransitionInfo) {
        this();
        this.postPageTransitionEvent = pageTransitionInfo;
    }

    public PageTransitionInfo getPostPageTransitionEvent() {
        return this.postPageTransitionEvent;
    }

    public NotifyPostPageTransitionEvent setPostPageTransitionEvent(PageTransitionInfo pageTransitionInfo) {
        this.postPageTransitionEvent = pageTransitionInfo;
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
