package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.ViewModeType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqReqNaviMapViewMode extends BaseRequest {
    private static final long serialVersionUID = -1478145251;

    @SerializedName("args")
    private Arguments arguments;

    public ReqReqNaviMapViewMode() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.reqNaviMapViewMode");
        this.arguments = new Arguments();
    }

    public ReqReqNaviMapViewMode(ViewModeType viewModeType) {
        this();
        this.arguments.setViewMode(viewModeType);
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

        @SerializedName("ViewMode")
        private ViewModeType viewMode = ViewModeType.HEAD_UP;

        public Arguments() {
        }

        public Arguments(ViewModeType viewModeType) {
            setViewMode(viewModeType);
        }

        public ViewModeType getViewMode() {
            return this.viewMode;
        }

        public Arguments setViewMode(ViewModeType viewModeType) {
            this.viewMode = viewModeType;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).viewMode.equals(this.viewMode);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.viewMode);
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
        if (obj instanceof ReqReqNaviMapViewMode) {
            return super.equals(obj) && ((ReqReqNaviMapViewMode) obj).arguments.equals(this.arguments);
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
