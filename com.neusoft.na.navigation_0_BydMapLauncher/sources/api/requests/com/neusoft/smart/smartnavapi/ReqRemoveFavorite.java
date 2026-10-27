package api.requests.com.neusoft.smart.smartnavapi;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqRemoveFavorite extends BaseRequest {
    private static final long serialVersionUID = -1479067746;

    @SerializedName("args")
    private Arguments arguments;

    public ReqRemoveFavorite() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.RemoveFavorite");
        this.arguments = new Arguments();
    }

    public ReqRemoveFavorite(Integer num, Integer num2) {
        this();
        this.arguments.setReqId(num);
        this.arguments.setResult(num2);
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

        @SerializedName("ReqId")
        private Integer reqId = 0;

        @SerializedName("Result")
        private Integer result = 0;

        public Arguments() {
        }

        public Arguments(Integer num, Integer num2) {
            setReqId(num);
            setResult(num2);
        }

        public Integer getReqId() {
            return this.reqId;
        }

        public Arguments setReqId(Integer num) {
            if (num == null) {
                throw new NullPointerException("reqId cannot be null");
            }
            this.reqId = num;
            return this;
        }

        public Integer getResult() {
            return this.result;
        }

        public Arguments setResult(Integer num) {
            if (num == null) {
                throw new NullPointerException("result cannot be null");
            }
            this.result = num;
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
            return super.equals(obj) && arguments.reqId.equals(this.reqId) && arguments.result.equals(this.result);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.reqId, this.result);
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
        if (obj instanceof ReqRemoveFavorite) {
            return super.equals(obj) && ((ReqRemoveFavorite) obj).arguments.equals(this.arguments);
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
