package api.responses.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.GuidedInfoStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetGuidanceInfoStatusForGreatWall extends BaseResponse {
    private static final long serialVersionUID = -1480916652;

    @SerializedName("args")
    private Arguments arguments;

    public ResGetGuidanceInfoStatusForGreatWall() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.GetGuidanceInfoStatusForGreatWall");
        this.arguments = new Arguments();
    }

    public ResGetGuidanceInfoStatusForGreatWall(GuidedInfoStatus guidedInfoStatus) {
        this();
        this.arguments.setGuidanceInfoStatus(guidedInfoStatus);
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

        @SerializedName("GuidanceInfoStatus")
        private GuidedInfoStatus guidanceInfoStatus = new GuidedInfoStatus();

        public Arguments() {
        }

        public Arguments(GuidedInfoStatus guidedInfoStatus) {
            setGuidanceInfoStatus(guidedInfoStatus);
        }

        public GuidedInfoStatus getGuidanceInfoStatus() {
            return this.guidanceInfoStatus;
        }

        public Arguments setGuidanceInfoStatus(GuidedInfoStatus guidedInfoStatus) {
            this.guidanceInfoStatus = guidedInfoStatus;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).guidanceInfoStatus.equals(this.guidanceInfoStatus);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.guidanceInfoStatus);
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
        if (obj instanceof ResGetGuidanceInfoStatusForGreatWall) {
            return super.equals(obj) && ((ResGetGuidanceInfoStatusForGreatWall) obj).arguments.equals(this.arguments);
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
