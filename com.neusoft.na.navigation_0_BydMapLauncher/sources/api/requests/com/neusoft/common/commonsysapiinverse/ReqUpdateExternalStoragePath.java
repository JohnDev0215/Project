package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.ExternalStorageStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqUpdateExternalStoragePath extends BaseRequest {
    private static final long serialVersionUID = -1478145404;

    @SerializedName("args")
    private Arguments arguments;

    public ReqUpdateExternalStoragePath() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.UpdateExternalStoragePath");
        this.arguments = new Arguments();
    }

    public ReqUpdateExternalStoragePath(String str, ExternalStorageStatus externalStorageStatus) {
        this();
        this.arguments.setMountPath(str);
        this.arguments.setStatus(externalStorageStatus);
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

        @SerializedName("MountPath")
        private String mountPath = "";

        @SerializedName("Status")
        private ExternalStorageStatus status = ExternalStorageStatus.USB_MOUNTED;

        public Arguments() {
        }

        public Arguments(String str, ExternalStorageStatus externalStorageStatus) {
            setMountPath(str);
            setStatus(externalStorageStatus);
        }

        public String getMountPath() {
            return this.mountPath;
        }

        public Arguments setMountPath(String str) {
            if (str == null) {
                throw new NullPointerException("mountPath cannot be null");
            }
            this.mountPath = str;
            return this;
        }

        public ExternalStorageStatus getStatus() {
            return this.status;
        }

        public Arguments setStatus(ExternalStorageStatus externalStorageStatus) {
            this.status = externalStorageStatus;
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
            return super.equals(obj) && arguments.mountPath.equals(this.mountPath) && arguments.status.equals(this.status);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.mountPath, this.status);
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
        if (obj instanceof ReqUpdateExternalStoragePath) {
            return super.equals(obj) && ((ReqUpdateExternalStoragePath) obj).arguments.equals(this.arguments);
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
