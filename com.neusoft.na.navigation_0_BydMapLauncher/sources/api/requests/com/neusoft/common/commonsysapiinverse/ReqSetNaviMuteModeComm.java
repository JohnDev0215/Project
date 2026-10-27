package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.MuteTypeComm;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetNaviMuteModeComm extends BaseRequest {
    private static final long serialVersionUID = -1478145193;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetNaviMuteModeComm() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SetNaviMuteModeComm");
        this.arguments = new Arguments();
    }

    public ReqSetNaviMuteModeComm(MuteTypeComm muteTypeComm) {
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

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ReqSetNaviMuteModeComm) {
            return super.equals(obj) && ((ReqSetNaviMuteModeComm) obj).arguments.equals(this.arguments);
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
