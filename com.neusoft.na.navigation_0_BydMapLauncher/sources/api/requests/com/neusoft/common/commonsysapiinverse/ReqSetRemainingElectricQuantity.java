package api.requests.com.neusoft.common.commonsysapiinverse;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetRemainingElectricQuantity extends BaseRequest {
    private static final long serialVersionUID = -1478145287;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetRemainingElectricQuantity() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SetRemainingElectricQuantity");
        this.arguments = new Arguments();
    }

    public ReqSetRemainingElectricQuantity(Long l) {
        this();
        this.arguments.setRemainingElectricQuantity(l);
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

        @SerializedName("RemainingElectricQuantity")
        private Long remainingElectricQuantity = 0L;

        public Arguments() {
        }

        public Arguments(Long l) {
            setRemainingElectricQuantity(l);
        }

        public Long getRemainingElectricQuantity() {
            return this.remainingElectricQuantity;
        }

        public Arguments setRemainingElectricQuantity(Long l) {
            if (l == null) {
                throw new NullPointerException("remainingElectricQuantity cannot be null");
            }
            this.remainingElectricQuantity = l;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).remainingElectricQuantity.equals(this.remainingElectricQuantity);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.remainingElectricQuantity);
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
        if (obj instanceof ReqSetRemainingElectricQuantity) {
            return super.equals(obj) && ((ReqSetRemainingElectricQuantity) obj).arguments.equals(this.arguments);
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
