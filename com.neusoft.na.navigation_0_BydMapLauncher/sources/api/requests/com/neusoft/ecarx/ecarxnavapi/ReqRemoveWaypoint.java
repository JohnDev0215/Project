package api.requests.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.RequestRoutePlanOrNaviType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqRemoveWaypoint extends BaseRequest {
    private static final long serialVersionUID = -1481836606;

    @SerializedName("args")
    private Arguments arguments;

    public ReqRemoveWaypoint() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.RemoveWaypoint");
        this.arguments = new Arguments();
    }

    public ReqRemoveWaypoint(RequestRoutePlanOrNaviType requestRoutePlanOrNaviType, Integer num) {
        this();
        this.arguments.setAction(requestRoutePlanOrNaviType);
        this.arguments.setIndex(num);
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

        @SerializedName("Action")
        private RequestRoutePlanOrNaviType action = RequestRoutePlanOrNaviType.ROUTE_PLANNING;

        @SerializedName("Index")
        private Integer index = 0;

        public Arguments() {
        }

        public Arguments(RequestRoutePlanOrNaviType requestRoutePlanOrNaviType, Integer num) {
            setAction(requestRoutePlanOrNaviType);
            setIndex(num);
        }

        public RequestRoutePlanOrNaviType getAction() {
            return this.action;
        }

        public Arguments setAction(RequestRoutePlanOrNaviType requestRoutePlanOrNaviType) {
            this.action = requestRoutePlanOrNaviType;
            return this;
        }

        public Integer getIndex() {
            return this.index;
        }

        public Arguments setIndex(Integer num) {
            if (num == null) {
                throw new NullPointerException("index cannot be null");
            }
            this.index = num;
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
            return super.equals(obj) && arguments.action.equals(this.action) && arguments.index.equals(this.index);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.action, this.index);
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
        if (obj instanceof ReqRemoveWaypoint) {
            return super.equals(obj) && ((ReqRemoveWaypoint) obj).arguments.equals(this.arguments);
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
