package api.requests.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.UserDestinationInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqSetUserDestinationData extends BaseRequest {
    private static final long serialVersionUID = -1479067840;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetUserDestinationData() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.SetUserDestinationData");
        this.arguments = new Arguments();
    }

    public ReqSetUserDestinationData(UserDestinationInfo userDestinationInfo) {
        this();
        this.arguments.setUserDestinationInfo(userDestinationInfo);
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

        @SerializedName("UserDestinationInfo")
        private UserDestinationInfo userDestinationInfo = new UserDestinationInfo();

        public Arguments() {
        }

        public Arguments(UserDestinationInfo userDestinationInfo) {
            setUserDestinationInfo(userDestinationInfo);
        }

        public UserDestinationInfo getUserDestinationInfo() {
            return this.userDestinationInfo;
        }

        public Arguments setUserDestinationInfo(UserDestinationInfo userDestinationInfo) {
            this.userDestinationInfo = userDestinationInfo;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).userDestinationInfo.equals(this.userDestinationInfo);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.userDestinationInfo);
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
        if (obj instanceof ReqSetUserDestinationData) {
            return super.equals(obj) && ((ReqSetUserDestinationData) obj).arguments.equals(this.arguments);
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
