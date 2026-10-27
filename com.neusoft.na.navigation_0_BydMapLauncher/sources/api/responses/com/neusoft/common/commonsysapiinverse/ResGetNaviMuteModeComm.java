package api.responses.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.MuteTypeComm;
import api.structs.com.neusoft.common.NaviCode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetNaviMuteModeComm extends BaseResponse {
    private static final long serialVersionUID = -1478144296;

    @SerializedName("args")
    private Arguments arguments;

    @SerializedName("customError")
    private NaviCode customError;

    public ResGetNaviMuteModeComm() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.common.CommonSysApiInverse.GetNaviMuteModeComm");
        this.arguments = new Arguments();
    }

    public ResGetNaviMuteModeComm(MuteTypeComm muteTypeComm) {
        this();
        this.arguments.setMuteState(muteTypeComm);
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

        @SerializedName("MuteState")
        private MuteTypeComm muteState = MuteTypeComm.Navi_Com_Silent;

        public Arguments() {
        }

        public Arguments(MuteTypeComm muteTypeComm) {
            setMuteState(muteTypeComm);
        }

        public MuteTypeComm getMuteState() {
            return this.muteState;
        }

        public Arguments setMuteState(MuteTypeComm muteTypeComm) {
            this.muteState = muteTypeComm;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).muteState.equals(this.muteState);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.muteState);
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
        if (obj instanceof ResGetNaviMuteModeComm) {
            return super.equals(obj) && ((ResGetNaviMuteModeComm) obj).arguments.equals(this.arguments);
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

    public void setCustomError(NaviCode naviCode) {
        this.customError = naviCode;
    }

    public NaviCode getCustomError() {
        return this.customError;
    }
}
