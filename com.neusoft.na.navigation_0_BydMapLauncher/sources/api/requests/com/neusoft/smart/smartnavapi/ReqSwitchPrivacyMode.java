package api.requests.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.PrivacyMode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqSwitchPrivacyMode extends BaseRequest {
    private static final long serialVersionUID = -1479067746;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSwitchPrivacyMode() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.SwitchPrivacyMode");
        this.arguments = new Arguments();
    }

    public ReqSwitchPrivacyMode(PrivacyMode privacyMode) {
        this();
        this.arguments.setPrivacyMode(privacyMode);
    }

    public Arguments getArguments() {
        return this.arguments;
    }

    public void setArguments(Arguments arguments) {
        if (arguments != null) {
            this.arguments = arguments;
            return;
        }
        throw new IllegalArgumentException("Arguments mustn't be null!");
    }

    public static class Arguments {

        @SerializedName("PrivacyMode")
        private PrivacyMode privacyMode = PrivacyMode.DEFAULT_MODE;

        public Arguments() {
        }

        public Arguments(PrivacyMode privacyMode) {
            setPrivacyMode(privacyMode);
        }

        public PrivacyMode getPrivacyMode() {
            return this.privacyMode;
        }

        public Arguments setPrivacyMode(PrivacyMode privacyMode) {
            this.privacyMode = privacyMode;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).privacyMode.equals(this.privacyMode);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.privacyMode);
        }

        public String toString() {
            return EProjectInterfaceType.OneCore.getGson().toJson(this);
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ReqSwitchPrivacyMode) {
            return super.equals(obj) && ((ReqSwitchPrivacyMode) obj).arguments.equals(this.arguments);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.arguments);
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
