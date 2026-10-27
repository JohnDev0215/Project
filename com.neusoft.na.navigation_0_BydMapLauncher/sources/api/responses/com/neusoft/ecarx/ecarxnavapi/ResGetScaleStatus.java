package api.responses.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.ResultCode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetScaleStatus extends BaseResponse {
    private static final long serialVersionUID = -1481835557;

    @SerializedName("args")
    private Arguments arguments;

    @SerializedName("customError")
    private ResultCode customError;

    public ResGetScaleStatus() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.GetScaleStatus");
        this.arguments = new Arguments();
    }

    public ResGetScaleStatus(Integer num, Integer num2, Integer num3, ResultCode resultCode) {
        this();
        this.arguments.setMaxScale(num);
        this.arguments.setMinScale(num2);
        this.arguments.setCurScale(num3);
        this.arguments.setResult(resultCode);
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

        @SerializedName("MaxScale")
        private Integer maxScale = 0;

        @SerializedName("MinScale")
        private Integer minScale = 0;

        @SerializedName("CurScale")
        private Integer curScale = 0;

        @SerializedName("Result")
        private ResultCode result = ResultCode.FAILURE_CURRENT_MAP_NOT_SUPPORT;

        public Arguments() {
        }

        public Arguments(Integer num, Integer num2, Integer num3, ResultCode resultCode) {
            setMaxScale(num);
            setMinScale(num2);
            setCurScale(num3);
            setResult(resultCode);
        }

        public Integer getMaxScale() {
            return this.maxScale;
        }

        public Arguments setMaxScale(Integer num) {
            if (num == null) {
                throw new NullPointerException("maxScale cannot be null");
            }
            this.maxScale = num;
            return this;
        }

        public Integer getMinScale() {
            return this.minScale;
        }

        public Arguments setMinScale(Integer num) {
            if (num == null) {
                throw new NullPointerException("minScale cannot be null");
            }
            this.minScale = num;
            return this;
        }

        public Integer getCurScale() {
            return this.curScale;
        }

        public Arguments setCurScale(Integer num) {
            if (num == null) {
                throw new NullPointerException("curScale cannot be null");
            }
            this.curScale = num;
            return this;
        }

        public ResultCode getResult() {
            return this.result;
        }

        public Arguments setResult(ResultCode resultCode) {
            this.result = resultCode;
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
            return super.equals(obj) && arguments.maxScale.equals(this.maxScale) && arguments.minScale.equals(this.minScale) && arguments.curScale.equals(this.curScale) && arguments.result.equals(this.result);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.maxScale, this.minScale, this.curScale, this.result);
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
        if (obj instanceof ResGetScaleStatus) {
            return super.equals(obj) && ((ResGetScaleStatus) obj).arguments.equals(this.arguments);
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
