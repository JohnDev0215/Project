package api.requests.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.NaviFullViewType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqNaviFullView extends BaseRequest {
    private static final long serialVersionUID = -1479067934;

    @SerializedName("args")
    private Arguments arguments;

    public ReqNaviFullView() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.NaviFullView");
        this.arguments = new Arguments();
    }

    public ReqNaviFullView(NaviFullViewType naviFullViewType) {
        this();
        this.arguments.setType(naviFullViewType);
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

        @SerializedName("Type")
        private NaviFullViewType type = NaviFullViewType.FULLVIEW_IN;

        public Arguments() {
        }

        public Arguments(NaviFullViewType naviFullViewType) {
            setType(naviFullViewType);
        }

        public NaviFullViewType getType() {
            return this.type;
        }

        public Arguments setType(NaviFullViewType naviFullViewType) {
            this.type = naviFullViewType;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).type.equals(this.type);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.type);
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
        if (obj instanceof ReqNaviFullView) {
            return super.equals(obj) && ((ReqNaviFullView) obj).arguments.equals(this.arguments);
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
