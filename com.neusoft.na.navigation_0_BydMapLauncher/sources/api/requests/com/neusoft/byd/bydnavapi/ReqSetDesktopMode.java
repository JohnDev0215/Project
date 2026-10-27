package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.DesktopMode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetDesktopMode extends BaseRequest {
    private static final long serialVersionUID = -1480909025;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetDesktopMode() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.SetDesktopMode");
        this.arguments = new Arguments();
    }

    public ReqSetDesktopMode(DesktopMode desktopMode) {
        this();
        this.arguments.setDesktopMode(desktopMode);
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

        @SerializedName("DesktopMode")
        private DesktopMode desktopMode = DesktopMode.WALLPAPER_DESKTOP;

        public Arguments() {
        }

        public Arguments(DesktopMode desktopMode) {
            setDesktopMode(desktopMode);
        }

        public DesktopMode getDesktopMode() {
            return this.desktopMode;
        }

        public Arguments setDesktopMode(DesktopMode desktopMode) {
            this.desktopMode = desktopMode;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).desktopMode.equals(this.desktopMode);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.desktopMode);
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
        if (obj instanceof ReqSetDesktopMode) {
            return super.equals(obj) && ((ReqSetDesktopMode) obj).arguments.equals(this.arguments);
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
