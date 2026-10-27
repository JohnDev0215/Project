package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.ResponseStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqOnDelPrivacyPolicyInfo extends BaseRequest {
    private static final long serialVersionUID = -1480909062;

    @SerializedName("args")
    private Arguments arguments;

    public ReqOnDelPrivacyPolicyInfo() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.OnDelPrivacyPolicyInfo");
        this.arguments = new Arguments();
    }

    public ReqOnDelPrivacyPolicyInfo(Integer num, ResponseStatus responseStatus) {
        this();
        this.arguments.setTokenId(num);
        this.arguments.setDelStatus(responseStatus);
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

        @SerializedName("TokenId")
        private Integer tokenId = 0;

        @SerializedName("DelStatus")
        private ResponseStatus delStatus = ResponseStatus.E_SUCCESS;

        public Arguments() {
        }

        public Arguments(Integer num, ResponseStatus responseStatus) {
            setTokenId(num);
            setDelStatus(responseStatus);
        }

        public Integer getTokenId() {
            return this.tokenId;
        }

        public Arguments setTokenId(Integer num) {
            if (num == null) {
                throw new NullPointerException("tokenId cannot be null");
            }
            this.tokenId = num;
            return this;
        }

        public ResponseStatus getDelStatus() {
            return this.delStatus;
        }

        public Arguments setDelStatus(ResponseStatus responseStatus) {
            this.delStatus = responseStatus;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.tokenId.equals(this.tokenId) && arguments.delStatus.equals(this.delStatus);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.tokenId, this.delStatus);
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
        if (obj instanceof ReqOnDelPrivacyPolicyInfo) {
            return super.equals(obj) && ((ReqOnDelPrivacyPolicyInfo) obj).arguments.equals(this.arguments);
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
