package api.requests.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.SETELServiceStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetSETELServiceStatus extends BaseRequest {
    private static final long serialVersionUID = -1481836548;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetSETELServiceStatus() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.SetSETELServiceStatus");
        this.arguments = new Arguments();
    }

    public ReqSetSETELServiceStatus(SETELServiceStatus sETELServiceStatus) {
        this();
        this.arguments.setSETELServiceStatus(sETELServiceStatus);
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

        @SerializedName("SETELServiceStatus")
        private SETELServiceStatus sETELServiceStatus = new SETELServiceStatus();

        public Arguments() {
        }

        public Arguments(SETELServiceStatus sETELServiceStatus) {
            setSETELServiceStatus(sETELServiceStatus);
        }

        public SETELServiceStatus getSETELServiceStatus() {
            return this.sETELServiceStatus;
        }

        public Arguments setSETELServiceStatus(SETELServiceStatus sETELServiceStatus) {
            this.sETELServiceStatus = sETELServiceStatus;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).sETELServiceStatus.equals(this.sETELServiceStatus);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.sETELServiceStatus);
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
        if (obj instanceof ReqSetSETELServiceStatus) {
            return super.equals(obj) && ((ReqSetSETELServiceStatus) obj).arguments.equals(this.arguments);
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
