package api.requests.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.PowerSavingModeStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqPowerSavingModeChanged extends BaseRequest {
    private static final long serialVersionUID = -1481831555;

    @SerializedName("args")
    private Arguments arguments;

    public ReqPowerSavingModeChanged() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.PowerSavingModeChanged");
        this.arguments = new Arguments();
    }

    public ReqPowerSavingModeChanged(PowerSavingModeStatus powerSavingModeStatus) {
        this();
        this.arguments.setMode(powerSavingModeStatus);
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

        @SerializedName("Mode")
        private PowerSavingModeStatus mode = PowerSavingModeStatus.UNKNOWN;

        public Arguments() {
        }

        public Arguments(PowerSavingModeStatus powerSavingModeStatus) {
            setMode(powerSavingModeStatus);
        }

        public PowerSavingModeStatus getMode() {
            return this.mode;
        }

        public Arguments setMode(PowerSavingModeStatus powerSavingModeStatus) {
            this.mode = powerSavingModeStatus;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).mode.equals(this.mode);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.mode);
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
        if (obj instanceof ReqPowerSavingModeChanged) {
            return super.equals(obj) && ((ReqPowerSavingModeChanged) obj).arguments.equals(this.arguments);
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
