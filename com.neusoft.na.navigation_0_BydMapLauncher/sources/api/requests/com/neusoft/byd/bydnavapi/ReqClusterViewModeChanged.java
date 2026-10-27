package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.ClusterViewMode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqClusterViewModeChanged extends BaseRequest {
    private static final long serialVersionUID = -1480909025;

    @SerializedName("args")
    private Arguments arguments;

    public ReqClusterViewModeChanged() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.ClusterViewModeChanged");
        this.arguments = new Arguments();
    }

    public ReqClusterViewModeChanged(ClusterViewMode clusterViewMode) {
        this();
        this.arguments.setViewMode(clusterViewMode);
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
        private ClusterViewMode viewMode = ClusterViewMode.DEFAULT_MODE;

        public Arguments() {
        }

        public Arguments(ClusterViewMode clusterViewMode) {
            setViewMode(clusterViewMode);
        }

        public ClusterViewMode getViewMode() {
            return this.viewMode;
        }

        public Arguments setViewMode(ClusterViewMode clusterViewMode) {
            this.viewMode = clusterViewMode;
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
        if (obj instanceof ReqClusterViewModeChanged) {
            return super.equals(obj) && ((ReqClusterViewModeChanged) obj).arguments.equals(this.arguments);
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
