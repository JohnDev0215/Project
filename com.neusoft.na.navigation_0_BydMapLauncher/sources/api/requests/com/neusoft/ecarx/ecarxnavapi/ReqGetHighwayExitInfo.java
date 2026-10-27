package api.requests.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.HighwayExitType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqGetHighwayExitInfo extends BaseRequest {
    private static final long serialVersionUID = -1481837321;

    @SerializedName("args")
    private Arguments arguments;

    public ReqGetHighwayExitInfo() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.GetHighwayExitInfo");
        this.arguments = new Arguments();
    }

    public ReqGetHighwayExitInfo(HighwayExitType highwayExitType) {
        this();
        this.arguments.setActionType(highwayExitType);
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

        @SerializedName("ActionType")
        private HighwayExitType actionType = HighwayExitType.HIGHWAY_EXIT;

        public Arguments() {
        }

        public Arguments(HighwayExitType highwayExitType) {
            setActionType(highwayExitType);
        }

        public HighwayExitType getActionType() {
            return this.actionType;
        }

        public Arguments setActionType(HighwayExitType highwayExitType) {
            this.actionType = highwayExitType;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).actionType.equals(this.actionType);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.actionType);
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
        if (obj instanceof ReqGetHighwayExitInfo) {
            return super.equals(obj) && ((ReqGetHighwayExitInfo) obj).arguments.equals(this.arguments);
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
