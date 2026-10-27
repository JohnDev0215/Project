package api.requests.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.ProcessedSETELDetailDataInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetSETELDetailData extends BaseRequest {
    private static final long serialVersionUID = -1481836548;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetSETELDetailData() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.SetSETELDetailData");
        this.arguments = new Arguments();
    }

    public ReqSetSETELDetailData(ProcessedSETELDetailDataInfo processedSETELDetailDataInfo) {
        this();
        this.arguments.setSETELDetailDataInfo(processedSETELDetailDataInfo);
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

        @SerializedName("SETELDetailDataInfo")
        private ProcessedSETELDetailDataInfo sETELDetailDataInfo = new ProcessedSETELDetailDataInfo();

        public Arguments() {
        }

        public Arguments(ProcessedSETELDetailDataInfo processedSETELDetailDataInfo) {
            setSETELDetailDataInfo(processedSETELDetailDataInfo);
        }

        public ProcessedSETELDetailDataInfo getSETELDetailDataInfo() {
            return this.sETELDetailDataInfo;
        }

        public Arguments setSETELDetailDataInfo(ProcessedSETELDetailDataInfo processedSETELDetailDataInfo) {
            this.sETELDetailDataInfo = processedSETELDetailDataInfo;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).sETELDetailDataInfo.equals(this.sETELDetailDataInfo);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.sETELDetailDataInfo);
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
        if (obj instanceof ReqSetSETELDetailData) {
            return super.equals(obj) && ((ReqSetSETELDetailData) obj).arguments.equals(this.arguments);
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
