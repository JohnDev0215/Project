package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.ResultStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqOnCopyFiles extends BaseRequest {
    private static final long serialVersionUID = -1478145157;

    @SerializedName("args")
    private Arguments arguments;

    public ReqOnCopyFiles() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.OnCopyFiles");
        this.arguments = new Arguments();
    }

    public ReqOnCopyFiles(Integer num, ResultStatus resultStatus) {
        this();
        this.arguments.setTokenId(num);
        this.arguments.setCopyFilesStatus(resultStatus);
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

        @SerializedName("CopyFilesStatus")
        private ResultStatus copyFilesStatus = ResultStatus.E_SUCCESS;

        public Arguments() {
        }

        public Arguments(Integer num, ResultStatus resultStatus) {
            setTokenId(num);
            setCopyFilesStatus(resultStatus);
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

        public ResultStatus getCopyFilesStatus() {
            return this.copyFilesStatus;
        }

        public Arguments setCopyFilesStatus(ResultStatus resultStatus) {
            this.copyFilesStatus = resultStatus;
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
            return super.equals(obj) && arguments.tokenId.equals(this.tokenId) && arguments.copyFilesStatus.equals(this.copyFilesStatus);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.tokenId, this.copyFilesStatus);
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
        if (obj instanceof ReqOnCopyFiles) {
            return super.equals(obj) && ((ReqOnCopyFiles) obj).arguments.equals(this.arguments);
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
