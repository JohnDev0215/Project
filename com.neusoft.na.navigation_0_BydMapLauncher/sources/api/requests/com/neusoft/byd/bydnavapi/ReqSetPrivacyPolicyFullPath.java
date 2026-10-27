package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.FileFullPath;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetPrivacyPolicyFullPath extends BaseRequest {
    private static final long serialVersionUID = -1480909025;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetPrivacyPolicyFullPath() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.SetPrivacyPolicyFullPath");
        this.arguments = new Arguments();
    }

    public ReqSetPrivacyPolicyFullPath(FileFullPath fileFullPath) {
        this();
        this.arguments.setFileFullPath(fileFullPath);
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

        @SerializedName("FileFullPath")
        private FileFullPath fileFullPath = new FileFullPath();

        public Arguments() {
        }

        public Arguments(FileFullPath fileFullPath) {
            setFileFullPath(fileFullPath);
        }

        public FileFullPath getFileFullPath() {
            return this.fileFullPath;
        }

        public Arguments setFileFullPath(FileFullPath fileFullPath) {
            this.fileFullPath = fileFullPath;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).fileFullPath.equals(this.fileFullPath);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.fileFullPath);
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
        if (obj instanceof ReqSetPrivacyPolicyFullPath) {
            return super.equals(obj) && ((ReqSetPrivacyPolicyFullPath) obj).arguments.equals(this.arguments);
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
