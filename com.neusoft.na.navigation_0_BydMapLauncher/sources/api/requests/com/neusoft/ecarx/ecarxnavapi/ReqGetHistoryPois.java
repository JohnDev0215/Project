package api.requests.com.neusoft.ecarx.ecarxnavapi;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqGetHistoryPois extends BaseRequest {
    private static final long serialVersionUID = -1481837321;

    @SerializedName("args")
    private Arguments arguments;

    public ReqGetHistoryPois() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.GetHistoryPois");
        this.arguments = new Arguments();
    }

    public ReqGetHistoryPois(Integer num) {
        this();
        this.arguments.setMaxCount(num);
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

        @SerializedName("MaxCount")
        private Integer maxCount = 0;

        public Arguments() {
        }

        public Arguments(Integer num) {
            setMaxCount(num);
        }

        public Integer getMaxCount() {
            return this.maxCount;
        }

        public Arguments setMaxCount(Integer num) {
            if (num == null) {
                throw new NullPointerException("maxCount cannot be null");
            }
            this.maxCount = num;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).maxCount.equals(this.maxCount);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.maxCount);
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
        if (obj instanceof ReqGetHistoryPois) {
            return super.equals(obj) && ((ReqGetHistoryPois) obj).arguments.equals(this.arguments);
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
