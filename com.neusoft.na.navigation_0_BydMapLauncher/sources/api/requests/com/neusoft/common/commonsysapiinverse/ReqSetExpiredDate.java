package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.ExpiredDateStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetExpiredDate extends BaseRequest {
    private static final long serialVersionUID = -1478145347;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetExpiredDate() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SetExpiredDate");
        this.arguments = new Arguments();
    }

    public ReqSetExpiredDate(Integer num, ExpiredDateStatus expiredDateStatus, Long l, Long l2) {
        this();
        this.arguments.setValidType(num);
        this.arguments.setStatus(expiredDateStatus);
        this.arguments.setStart_Date(l);
        this.arguments.setEnd_Date(l2);
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

        @SerializedName("ValidType")
        private Integer validType = 0;

        @SerializedName("Status")
        private ExpiredDateStatus status = ExpiredDateStatus.E_FAILURE;

        @SerializedName("Start_Date")
        private Long start_Date = 0L;

        @SerializedName("End_Date")
        private Long end_Date = 0L;

        public Arguments() {
        }

        public Arguments(Integer num, ExpiredDateStatus expiredDateStatus, Long l, Long l2) {
            setValidType(num);
            setStatus(expiredDateStatus);
            setStart_Date(l);
            setEnd_Date(l2);
        }

        public Integer getValidType() {
            return this.validType;
        }

        public Arguments setValidType(Integer num) {
            if (num == null) {
                throw new NullPointerException("validType cannot be null");
            }
            this.validType = num;
            return this;
        }

        public ExpiredDateStatus getStatus() {
            return this.status;
        }

        public Arguments setStatus(ExpiredDateStatus expiredDateStatus) {
            this.status = expiredDateStatus;
            return this;
        }

        public Long getStart_Date() {
            return this.start_Date;
        }

        public Arguments setStart_Date(Long l) {
            if (l == null) {
                throw new NullPointerException("start_Date cannot be null");
            }
            this.start_Date = l;
            return this;
        }

        public Long getEnd_Date() {
            return this.end_Date;
        }

        public Arguments setEnd_Date(Long l) {
            if (l == null) {
                throw new NullPointerException("end_Date cannot be null");
            }
            this.end_Date = l;
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
            return super.equals(obj) && arguments.validType.equals(this.validType) && arguments.status.equals(this.status) && arguments.start_Date.equals(this.start_Date) && arguments.end_Date.equals(this.end_Date);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.validType, this.status, this.start_Date, this.end_Date);
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
        if (obj instanceof ReqSetExpiredDate) {
            return super.equals(obj) && ((ReqSetExpiredDate) obj).arguments.equals(this.arguments);
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
