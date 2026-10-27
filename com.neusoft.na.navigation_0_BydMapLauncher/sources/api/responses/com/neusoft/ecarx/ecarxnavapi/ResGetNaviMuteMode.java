package api.responses.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.MuteType;
import api.structs.com.neusoft.ecarx.ResultCode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetNaviMuteMode extends BaseResponse {
    private static final long serialVersionUID = -1481835614;

    @SerializedName("args")
    private Arguments arguments;

    @SerializedName("customError")
    private ResultCode customError;

    public ResGetNaviMuteMode() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.GetNaviMuteMode");
        this.arguments = new Arguments();
    }

    public ResGetNaviMuteMode(MuteType muteType) {
        this();
        this.arguments.setMuteState(muteType);
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
        private MuteType muteState = MuteType.MUTE_PERMANENT;

        public Arguments() {
        }

        public Arguments(MuteType muteType) {
            setMuteState(muteType);
        }

        public MuteType getMuteState() {
            return this.muteState;
        }

        public Arguments setMuteState(MuteType muteType) {
            this.muteState = muteType;
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
        if (obj instanceof ResGetNaviMuteMode) {
            return super.equals(obj) && ((ResGetNaviMuteMode) obj).arguments.equals(this.arguments);
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
