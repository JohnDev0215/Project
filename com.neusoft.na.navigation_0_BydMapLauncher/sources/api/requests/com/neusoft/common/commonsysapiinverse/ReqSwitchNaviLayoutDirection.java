package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.NaviLayoutDirection;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSwitchNaviLayoutDirection extends BaseRequest {
    private static final long serialVersionUID = -1478145157;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSwitchNaviLayoutDirection() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SwitchNaviLayoutDirection");
        this.arguments = new Arguments();
    }

    public ReqSwitchNaviLayoutDirection(NaviLayoutDirection naviLayoutDirection) {
        this();
        this.arguments.setLayoutDirection(naviLayoutDirection);
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

        @SerializedName("LayoutDirection")
        private NaviLayoutDirection layoutDirection = NaviLayoutDirection.UNDEFINED;

        public Arguments() {
        }

        public Arguments(NaviLayoutDirection naviLayoutDirection) {
            setLayoutDirection(naviLayoutDirection);
        }

        public NaviLayoutDirection getLayoutDirection() {
            return this.layoutDirection;
        }

        public Arguments setLayoutDirection(NaviLayoutDirection naviLayoutDirection) {
            this.layoutDirection = naviLayoutDirection;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).layoutDirection.equals(this.layoutDirection);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.layoutDirection);
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
        if (obj instanceof ReqSwitchNaviLayoutDirection) {
            return super.equals(obj) && ((ReqSwitchNaviLayoutDirection) obj).arguments.equals(this.arguments);
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
