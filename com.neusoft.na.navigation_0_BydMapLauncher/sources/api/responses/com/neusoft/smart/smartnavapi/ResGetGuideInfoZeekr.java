package api.responses.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.GuidanceInfoZeekr;
import api.structs.com.neusoft.smart.ResultCode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetGuideInfoZeekr extends BaseResponse {
    private static final long serialVersionUID = -1479066885;

    @SerializedName("args")
    private Arguments arguments;

    @SerializedName("customError")
    private ResultCode customError;

    public ResGetGuideInfoZeekr() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.smart.SmartNavApi.GetGuideInfoZeekr");
        this.arguments = new Arguments();
    }

    public ResGetGuideInfoZeekr(GuidanceInfoZeekr guidanceInfoZeekr) {
        this();
        this.arguments.setGuidanceInfoZeekr(guidanceInfoZeekr);
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

        @SerializedName("GuidanceInfoZeekr")
        private GuidanceInfoZeekr guidanceInfoZeekr = new GuidanceInfoZeekr();

        public Arguments() {
        }

        public Arguments(GuidanceInfoZeekr guidanceInfoZeekr) {
            setGuidanceInfoZeekr(guidanceInfoZeekr);
        }

        public GuidanceInfoZeekr getGuidanceInfoZeekr() {
            return this.guidanceInfoZeekr;
        }

        public Arguments setGuidanceInfoZeekr(GuidanceInfoZeekr guidanceInfoZeekr) {
            this.guidanceInfoZeekr = guidanceInfoZeekr;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).guidanceInfoZeekr.equals(this.guidanceInfoZeekr);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.guidanceInfoZeekr);
        }

        public String toString() {
            return EProjectInterfaceType.OneCore.getGson().toJson(this);
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        EMessageType eMessageType2 = EMessageType.Error;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ResGetGuideInfoZeekr) {
            return super.equals(obj) && ((ResGetGuideInfoZeekr) obj).arguments.equals(this.arguments);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
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

    public void setCustomError(ResultCode resultCode) {
        this.customError = resultCode;
    }

    public ResultCode getCustomError() {
        return this.customError;
    }
}
