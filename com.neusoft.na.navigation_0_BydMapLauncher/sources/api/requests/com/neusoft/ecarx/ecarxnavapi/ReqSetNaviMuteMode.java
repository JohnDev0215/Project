package api.requests.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.MuteType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetNaviMuteMode extends BaseRequest {
    private static final long serialVersionUID = -1481836606;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetNaviMuteMode() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.SetNaviMuteMode");
        this.arguments = new Arguments();
    }

    public ReqSetNaviMuteMode(MuteType muteType) {
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

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ReqSetNaviMuteMode) {
            return super.equals(obj) && ((ReqSetNaviMuteMode) obj).arguments.equals(this.arguments);
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
