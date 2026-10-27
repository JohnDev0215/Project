package api.requests.com.neusoft.common.commonsysapiinverse;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetExtCloudServerStatus extends BaseRequest {
    private static final long serialVersionUID = -1478145193;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetExtCloudServerStatus() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SetExtCloudServerStatus");
        this.arguments = new Arguments();
    }

    public ReqSetExtCloudServerStatus(Boolean bool) {
        this();
        this.arguments.setServerStatus(bool);
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

        @SerializedName("ServerStatus")
        private Boolean serverStatus = false;

        public Arguments() {
        }

        public Arguments(Boolean bool) {
            setServerStatus(bool);
        }

        public Boolean getServerStatus() {
            return this.serverStatus;
        }

        public Arguments setServerStatus(Boolean bool) {
            if (bool == null) {
                throw new NullPointerException("serverStatus cannot be null");
            }
            this.serverStatus = bool;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).serverStatus.equals(this.serverStatus);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.serverStatus);
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
        if (obj instanceof ReqSetExtCloudServerStatus) {
            return super.equals(obj) && ((ReqSetExtCloudServerStatus) obj).arguments.equals(this.arguments);
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
