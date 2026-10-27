package api.responses.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.NaviCode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResReqOpenHistoryDestination extends BaseResponse {
    private static final long serialVersionUID = -1480908968;

    @SerializedName("args")
    private Arguments arguments;

    public ResReqOpenHistoryDestination() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.ReqOpenHistoryDestination");
        this.arguments = new Arguments();
    }

    public ResReqOpenHistoryDestination(NaviCode naviCode) {
        this();
        this.arguments.setNaviCode(naviCode);
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

        @SerializedName("NaviCode")
        private NaviCode naviCode = NaviCode.OK;

        public Arguments() {
        }

        public Arguments(NaviCode naviCode) {
            setNaviCode(naviCode);
        }

        public NaviCode getNaviCode() {
            return this.naviCode;
        }

        public Arguments setNaviCode(NaviCode naviCode) {
            this.naviCode = naviCode;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).naviCode.equals(this.naviCode);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.naviCode);
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
        if (obj instanceof ResReqOpenHistoryDestination) {
            return super.equals(obj) && ((ResReqOpenHistoryDestination) obj).arguments.equals(this.arguments);
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
}
