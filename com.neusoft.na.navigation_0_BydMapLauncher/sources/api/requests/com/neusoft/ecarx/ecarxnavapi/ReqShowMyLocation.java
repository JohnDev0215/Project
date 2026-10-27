package api.requests.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.ShowLocationActionType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqShowMyLocation extends BaseRequest {
    private static final long serialVersionUID = -1481837379;

    @SerializedName("args")
    private Arguments arguments;

    public ReqShowMyLocation() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.ShowMyLocation");
        this.arguments = new Arguments();
    }

    public ReqShowMyLocation(ShowLocationActionType showLocationActionType) {
        this();
        this.arguments.setActionType(showLocationActionType);
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
        private ShowLocationActionType actionType = ShowLocationActionType.SHOW_LOCATION_MAP_AND_GET_LOCATION_INFO;

        public Arguments() {
        }

        public Arguments(ShowLocationActionType showLocationActionType) {
            setActionType(showLocationActionType);
        }

        public ShowLocationActionType getActionType() {
            return this.actionType;
        }

        public Arguments setActionType(ShowLocationActionType showLocationActionType) {
            this.actionType = showLocationActionType;
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
        if (obj instanceof ReqShowMyLocation) {
            return super.equals(obj) && ((ReqShowMyLocation) obj).arguments.equals(this.arguments);
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
