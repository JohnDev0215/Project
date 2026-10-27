package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DeletePathStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqOnDeleteAllDBData extends BaseRequest {
    private static final long serialVersionUID = -1478145193;

    @SerializedName("args")
    private Arguments arguments;

    public ReqOnDeleteAllDBData() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.OnDeleteAllDBData");
        this.arguments = new Arguments();
    }

    public ReqOnDeleteAllDBData(String str, DeletePathStatus deletePathStatus) {
        this();
        this.arguments.setRequestDelPath(str);
        this.arguments.setStatus(deletePathStatus);
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

        @SerializedName("requestDelPath")
        private String requestDelPath = "";

        @SerializedName("status")
        private DeletePathStatus status = DeletePathStatus.E_SUCCESS;

        public Arguments() {
        }

        public Arguments(String str, DeletePathStatus deletePathStatus) {
            setRequestDelPath(str);
            setStatus(deletePathStatus);
        }

        public String getRequestDelPath() {
            return this.requestDelPath;
        }

        public Arguments setRequestDelPath(String str) {
            if (str == null) {
                throw new NullPointerException("requestDelPath cannot be null");
            }
            this.requestDelPath = str;
            return this;
        }

        public DeletePathStatus getStatus() {
            return this.status;
        }

        public Arguments setStatus(DeletePathStatus deletePathStatus) {
            this.status = deletePathStatus;
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
            return super.equals(obj) && arguments.requestDelPath.equals(this.requestDelPath) && arguments.status.equals(this.status);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.requestDelPath, this.status);
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
        if (obj instanceof ReqOnDeleteAllDBData) {
            return super.equals(obj) && ((ReqOnDeleteAllDBData) obj).arguments.equals(this.arguments);
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
