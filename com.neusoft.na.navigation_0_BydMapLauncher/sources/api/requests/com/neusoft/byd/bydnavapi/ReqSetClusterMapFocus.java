package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.ClusterMapFocusMode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetClusterMapFocus extends BaseRequest {
    private static final long serialVersionUID = -1480909025;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetClusterMapFocus() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.SetClusterMapFocus");
        this.arguments = new Arguments();
    }

    public ReqSetClusterMapFocus(ClusterMapFocusMode clusterMapFocusMode) {
        this();
        this.arguments.setFocusMode(clusterMapFocusMode);
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

        @SerializedName("FocusMode")
        private ClusterMapFocusMode focusMode = ClusterMapFocusMode.DEFAULT_MODE;

        public Arguments() {
        }

        public Arguments(ClusterMapFocusMode clusterMapFocusMode) {
            setFocusMode(clusterMapFocusMode);
        }

        public ClusterMapFocusMode getFocusMode() {
            return this.focusMode;
        }

        public Arguments setFocusMode(ClusterMapFocusMode clusterMapFocusMode) {
            this.focusMode = clusterMapFocusMode;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).focusMode.equals(this.focusMode);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.focusMode);
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
        if (obj instanceof ReqSetClusterMapFocus) {
            return super.equals(obj) && ((ReqSetClusterMapFocus) obj).arguments.equals(this.arguments);
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
