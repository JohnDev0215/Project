package api.notifications.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.LanguageOnOff;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes.dex */
public class NotifySwitchNavigationLanguageSetting extends BaseNotification {
    private static final long serialVersionUID = -1480914727;

    @SerializedName("args")
    private LanguageOnOff switchNavigationLanguageSetting;

    public NotifySwitchNavigationLanguageSetting() {
        super(EMessageType.Signal, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.SwitchNavigationLanguageSetting");
    }

    public NotifySwitchNavigationLanguageSetting(LanguageOnOff languageOnOff) {
        this();
        this.switchNavigationLanguageSetting = languageOnOff;
    }

    public LanguageOnOff getSwitchNavigationLanguageSetting() {
        return this.switchNavigationLanguageSetting;
    }

    public NotifySwitchNavigationLanguageSetting setSwitchNavigationLanguageSetting(LanguageOnOff languageOnOff) {
        this.switchNavigationLanguageSetting = languageOnOff;
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
