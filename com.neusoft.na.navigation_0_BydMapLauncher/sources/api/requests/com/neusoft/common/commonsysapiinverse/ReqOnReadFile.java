package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.ReadFileStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqOnReadFile extends BaseRequest {
    private static final long serialVersionUID = -1478145347;

    @SerializedName("args")
    private Arguments arguments;

    public ReqOnReadFile() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.OnReadFile");
        this.arguments = new Arguments();
    }

    public ReqOnReadFile(Integer num, ReadFileStatus readFileStatus, String str) {
        this();
        this.arguments.setTokenId(num);
        this.arguments.setStatus(readFileStatus);
        this.arguments.setBuffer(str);
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
        private ReadFileStatus status = ReadFileStatus.E_SUCCESS;

        @SerializedName("Buffer")
        private String buffer = "";

        public Arguments() {
        }

        public Arguments(Integer num, ReadFileStatus readFileStatus, String str) {
            setTokenId(num);
            setStatus(readFileStatus);
            setBuffer(str);
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

        public ReadFileStatus getStatus() {
            return this.status;
        }

        public Arguments setStatus(ReadFileStatus readFileStatus) {
            this.status = readFileStatus;
            return this;
        }

        public String getBuffer() {
            return this.buffer;
        }

        public Arguments setBuffer(String str) {
            if (str == null) {
                throw new NullPointerException("buffer cannot be null");
            }
            this.buffer = str;
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
            return super.equals(obj) && arguments.tokenId.equals(this.tokenId) && arguments.status.equals(this.status) && arguments.buffer.equals(this.buffer);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.tokenId, this.status, this.buffer);
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
        if (obj instanceof ReqOnReadFile) {
            return super.equals(obj) && ((ReqOnReadFile) obj).arguments.equals(this.arguments);
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
