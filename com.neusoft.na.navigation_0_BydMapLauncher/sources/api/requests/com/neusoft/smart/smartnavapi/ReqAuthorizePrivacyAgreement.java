package api.requests.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.PrivacyAgreementInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqAuthorizePrivacyAgreement extends BaseRequest {
    private static final long serialVersionUID = -1479067746;

    @SerializedName("args")
    private Arguments arguments;

    public ReqAuthorizePrivacyAgreement() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.AuthorizePrivacyAgreement");
        this.arguments = new Arguments();
    }

    public ReqAuthorizePrivacyAgreement(PrivacyAgreementInfo privacyAgreementInfo) {
        this();
        this.arguments.setPrivacyAgreementInfo(privacyAgreementInfo);
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

        @SerializedName("PrivacyAgreementInfo")
        private PrivacyAgreementInfo privacyAgreementInfo = new PrivacyAgreementInfo();

        public Arguments() {
        }

        public Arguments(PrivacyAgreementInfo privacyAgreementInfo) {
            setPrivacyAgreementInfo(privacyAgreementInfo);
        }

        public PrivacyAgreementInfo getPrivacyAgreementInfo() {
            return this.privacyAgreementInfo;
        }

        public Arguments setPrivacyAgreementInfo(PrivacyAgreementInfo privacyAgreementInfo) {
            this.privacyAgreementInfo = privacyAgreementInfo;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).privacyAgreementInfo.equals(this.privacyAgreementInfo);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.privacyAgreementInfo);
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
        if (obj instanceof ReqAuthorizePrivacyAgreement) {
            return super.equals(obj) && ((ReqAuthorizePrivacyAgreement) obj).arguments.equals(this.arguments);
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
