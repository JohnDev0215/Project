package api.requests.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.ViewModeType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqSetMapViewMode extends BaseRequest {
    private static final long serialVersionUID = -1479067934;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetMapViewMode() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.SetMapViewMode");
        this.arguments = new Arguments();
    }

    public ReqSetMapViewMode(ViewModeType viewModeType) {
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
        private ViewModeType viewMode = ViewModeType.HEAD_UP_2D;

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
        if (obj instanceof ReqSetMapViewMode) {
            return super.equals(obj) && ((ReqSetMapViewMode) obj).arguments.equals(this.arguments);
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
