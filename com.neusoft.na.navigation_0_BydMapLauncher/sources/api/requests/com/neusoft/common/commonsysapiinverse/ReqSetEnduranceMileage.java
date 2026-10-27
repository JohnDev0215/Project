package api.requests.com.neusoft.common.commonsysapiinverse;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetEnduranceMileage extends BaseRequest {
    private static final long serialVersionUID = -1478145287;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetEnduranceMileage() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SetEnduranceMileage");
        this.arguments = new Arguments();
    }

    public ReqSetEnduranceMileage(Long l) {
        this();
        this.arguments.setAvailableMileage(l);
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

        @SerializedName("AvailableMileage")
        private Long availableMileage = 0L;

        public Arguments() {
        }

        public Arguments(Long l) {
            setAvailableMileage(l);
        }

        public Long getAvailableMileage() {
            return this.availableMileage;
        }

        public Arguments setAvailableMileage(Long l) {
            if (l == null) {
                throw new NullPointerException("availableMileage cannot be null");
            }
            this.availableMileage = l;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).availableMileage.equals(this.availableMileage);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.availableMileage);
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
        if (obj instanceof ReqSetEnduranceMileage) {
            return super.equals(obj) && ((ReqSetEnduranceMileage) obj).arguments.equals(this.arguments);
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
