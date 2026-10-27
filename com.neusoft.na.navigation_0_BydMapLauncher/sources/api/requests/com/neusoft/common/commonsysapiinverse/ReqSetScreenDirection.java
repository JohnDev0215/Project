package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.ScreenDirectionStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetScreenDirection extends BaseRequest {
    private static final long serialVersionUID = -1478145251;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetScreenDirection() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SetScreenDirection");
        this.arguments = new Arguments();
    }

    public ReqSetScreenDirection(ScreenDirectionStatus screenDirectionStatus) {
        this();
        this.arguments.setStatus(screenDirectionStatus);
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

        @SerializedName("status")
        private ScreenDirectionStatus status = ScreenDirectionStatus.HORIZONTAL;

        public Arguments() {
        }

        public Arguments(ScreenDirectionStatus screenDirectionStatus) {
            setStatus(screenDirectionStatus);
        }

        public ScreenDirectionStatus getStatus() {
            return this.status;
        }

        public Arguments setStatus(ScreenDirectionStatus screenDirectionStatus) {
            this.status = screenDirectionStatus;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).status.equals(this.status);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.status);
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
        if (obj instanceof ReqSetScreenDirection) {
            return super.equals(obj) && ((ReqSetScreenDirection) obj).arguments.equals(this.arguments);
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
