package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.FocusStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqHMIFocusChanged extends BaseRequest {
    private static final long serialVersionUID = -1478145157;

    @SerializedName("args")
    private Arguments arguments;

    public ReqHMIFocusChanged() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.HMIFocusChanged");
        this.arguments = new Arguments();
    }

    public ReqHMIFocusChanged(FocusStatus focusStatus) {
        this();
        this.arguments.setHMIFocusStatus(focusStatus);
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

        @SerializedName("HMIFocusStatus")
        private FocusStatus hMIFocusStatus = FocusStatus.E_UNKNOWN;

        public Arguments() {
        }

        public Arguments(FocusStatus focusStatus) {
            setHMIFocusStatus(focusStatus);
        }

        public FocusStatus getHMIFocusStatus() {
            return this.hMIFocusStatus;
        }

        public Arguments setHMIFocusStatus(FocusStatus focusStatus) {
            this.hMIFocusStatus = focusStatus;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).hMIFocusStatus.equals(this.hMIFocusStatus);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.hMIFocusStatus);
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
        if (obj instanceof ReqHMIFocusChanged) {
            return super.equals(obj) && ((ReqHMIFocusChanged) obj).arguments.equals(this.arguments);
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
