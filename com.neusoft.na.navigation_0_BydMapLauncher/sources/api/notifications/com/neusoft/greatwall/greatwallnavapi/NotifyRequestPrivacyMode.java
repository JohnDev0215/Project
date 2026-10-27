package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.PrivacyModeCommand;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifyRequestPrivacyMode extends BaseNotification {
    private static final long serialVersionUID = -1480914019;

    @SerializedName("args")
    private PrivacyModeCommand requestPrivacyMode;

    public NotifyRequestPrivacyMode() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.RequestPrivacyMode");
    }

    public NotifyRequestPrivacyMode(PrivacyModeCommand privacyModeCommand) {
        this();
        this.requestPrivacyMode = privacyModeCommand;
    }

    public PrivacyModeCommand getRequestPrivacyMode() {
        return this.requestPrivacyMode;
    }

    public NotifyRequestPrivacyMode setRequestPrivacyMode(PrivacyModeCommand privacyModeCommand) {
        this.requestPrivacyMode = privacyModeCommand;
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
