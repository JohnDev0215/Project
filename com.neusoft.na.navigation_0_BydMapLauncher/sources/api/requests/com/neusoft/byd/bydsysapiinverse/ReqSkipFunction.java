package api.requests.com.neusoft.byd.bydsysapiinverse;

import api.structs.com.neusoft.byd.FunctionTypes;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSkipFunction extends BaseRequest {
    private static final long serialVersionUID = -1481837473;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSkipFunction() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydSysApiInverse.SkipFunction");
        this.arguments = new Arguments();
    }

    public ReqSkipFunction(Boolean bool, FunctionTypes functionTypes) {
        this();
        this.arguments.setIsSkip(bool);
        this.arguments.setFuncType(functionTypes);
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

        @SerializedName("isSkip")
        private Boolean isSkip = false;

        @SerializedName("funcType")
        private FunctionTypes funcType = FunctionTypes.GPS_FUNC;

        public Arguments() {
        }

        public Arguments(Boolean bool, FunctionTypes functionTypes) {
            setIsSkip(bool);
            setFuncType(functionTypes);
        }

        public Boolean getIsSkip() {
            return this.isSkip;
        }

        public Arguments setIsSkip(Boolean bool) {
            if (bool == null) {
                throw new NullPointerException("isSkip cannot be null");
            }
            this.isSkip = bool;
            return this;
        }

        public FunctionTypes getFuncType() {
            return this.funcType;
        }

        public Arguments setFuncType(FunctionTypes functionTypes) {
            this.funcType = functionTypes;
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
            return super.equals(obj) && arguments.isSkip.equals(this.isSkip) && arguments.funcType.equals(this.funcType);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.isSkip, this.funcType);
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
        if (obj instanceof ReqSkipFunction) {
            return super.equals(obj) && ((ReqSkipFunction) obj).arguments.equals(this.arguments);
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
