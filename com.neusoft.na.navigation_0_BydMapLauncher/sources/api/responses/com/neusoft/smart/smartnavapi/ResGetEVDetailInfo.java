package api.responses.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.PoiDetailInfo;
import api.structs.com.neusoft.smart.ResultCode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetEVDetailInfo extends BaseResponse {
    private static final long serialVersionUID = -1479066849;

    @SerializedName("args")
    private Arguments arguments;

    @SerializedName("customError")
    private ResultCode customError;

    public ResGetEVDetailInfo() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.smart.SmartNavApi.GetEVDetailInfo");
        this.arguments = new Arguments();
    }

    public ResGetEVDetailInfo(PoiDetailInfo poiDetailInfo) {
        this();
        this.arguments.setPoiDetailInfo(poiDetailInfo);
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

        @SerializedName("PoiDetailInfo")
        private PoiDetailInfo poiDetailInfo = new PoiDetailInfo();

        public Arguments() {
        }

        public Arguments(PoiDetailInfo poiDetailInfo) {
            setPoiDetailInfo(poiDetailInfo);
        }

        public PoiDetailInfo getPoiDetailInfo() {
            return this.poiDetailInfo;
        }

        public Arguments setPoiDetailInfo(PoiDetailInfo poiDetailInfo) {
            this.poiDetailInfo = poiDetailInfo;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).poiDetailInfo.equals(this.poiDetailInfo);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.poiDetailInfo);
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
        if (obj instanceof ResGetEVDetailInfo) {
            return super.equals(obj) && ((ResGetEVDetailInfo) obj).arguments.equals(this.arguments);
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
