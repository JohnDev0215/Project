package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.DeleteFileStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqOnDeleteFile extends BaseRequest {
    private static final long serialVersionUID = -1478145347;

    @SerializedName("args")
    private Arguments arguments;

    public ReqOnDeleteFile() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.OnDeleteFile");
        this.arguments = new Arguments();
    }

    public ReqOnDeleteFile(Integer num, DeleteFileStatus deleteFileStatus) {
        this();
        this.arguments.setTokenId(num);
        this.arguments.setStatus(deleteFileStatus);
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

        @SerializedName("TokenId")
        private Integer tokenId = 0;

        @SerializedName("Status")
        private DeleteFileStatus status = DeleteFileStatus.E_SUCCESS;

        public Arguments() {
        }

        public Arguments(Integer num, DeleteFileStatus deleteFileStatus) {
            setTokenId(num);
            setStatus(deleteFileStatus);
        }

        public Integer getTokenId() {
            return this.tokenId;
        }

        public Arguments setTokenId(Integer num) {
            if (num == null) {
                throw new NullPointerException("tokenId cannot be null");
            }
            this.tokenId = num;
            return this;
        }

        public DeleteFileStatus getStatus() {
            return this.status;
        }

        public Arguments setStatus(DeleteFileStatus deleteFileStatus) {
            this.status = deleteFileStatus;
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
            return super.equals(obj) && arguments.tokenId.equals(this.tokenId) && arguments.status.equals(this.status);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.tokenId, this.status);
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
        if (obj instanceof ReqOnDeleteFile) {
            return super.equals(obj) && ((ReqOnDeleteFile) obj).arguments.equals(this.arguments);
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
