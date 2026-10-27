package api.requests.com.neusoft.greatwall.greatwallnavapi;

import api.structs.com.neusoft.greatwall.IVIConfiguration;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetIVIConfiguration extends BaseRequest {
    private static final long serialVersionUID = -1481831744;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetIVIConfiguration() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.greatwall.GreatWallNavApi.SetIVIConfiguration");
        this.arguments = new Arguments();
    }

    public ReqSetIVIConfiguration(IVIConfiguration iVIConfiguration) {
        this();
        this.arguments.setIVIConfiguration(iVIConfiguration);
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

        @SerializedName("IVIConfiguration")
        private IVIConfiguration iVIConfiguration = IVIConfiguration.UNKNOWN;

        public Arguments() {
        }

        public Arguments(IVIConfiguration iVIConfiguration) {
            setIVIConfiguration(iVIConfiguration);
        }

        public IVIConfiguration getIVIConfiguration() {
            return this.iVIConfiguration;
        }

        public Arguments setIVIConfiguration(IVIConfiguration iVIConfiguration) {
            this.iVIConfiguration = iVIConfiguration;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).iVIConfiguration.equals(this.iVIConfiguration);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.iVIConfiguration);
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
        if (obj instanceof ReqSetIVIConfiguration) {
            return super.equals(obj) && ((ReqSetIVIConfiguration) obj).arguments.equals(this.arguments);
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
