package api.responses.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.LanguageOnOff;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetSwitchNavigationLanguageSetting extends BaseResponse {
    private static final long serialVersionUID = -1480915944;

    @SerializedName("args")
    private LanguageOnOff switchNavigationLanguageSetting;

    public ResGetSwitchNavigationLanguageSetting() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.GetSwitchNavigationLanguageSetting");
    }

    public ResGetSwitchNavigationLanguageSetting(LanguageOnOff languageOnOff) {
        this();
        this.switchNavigationLanguageSetting = languageOnOff;
    }

    public LanguageOnOff getSwitchNavigationLanguageSetting() {
        return this.switchNavigationLanguageSetting;
    }

    public ResGetSwitchNavigationLanguageSetting setSwitchNavigationLanguageSetting(LanguageOnOff languageOnOff) {
        this.switchNavigationLanguageSetting = languageOnOff;
        return this;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        if (eMessageType == EMessageType.Error) {
            this.switchNavigationLanguageSetting = null;
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResGetSwitchNavigationLanguageSetting)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()));
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
