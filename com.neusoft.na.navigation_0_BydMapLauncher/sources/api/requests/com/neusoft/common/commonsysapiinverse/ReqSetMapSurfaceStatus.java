package api.requests.com.neusoft.common.commonsysapiinverse;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetMapSurfaceStatus extends BaseRequest {
    private static final long serialVersionUID = -1478145193;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetMapSurfaceStatus() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SetMapSurfaceStatus");
        this.arguments = new Arguments();
    }

    public ReqSetMapSurfaceStatus(Boolean bool) {
        this();
        this.arguments.setMapSurfaceStatus(bool);
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

        @SerializedName("MapSurfaceStatus")
        private Boolean mapSurfaceStatus = false;

        public Arguments() {
        }

        public Arguments(Boolean bool) {
            setMapSurfaceStatus(bool);
        }

        public Boolean getMapSurfaceStatus() {
            return this.mapSurfaceStatus;
        }

        public Arguments setMapSurfaceStatus(Boolean bool) {
            if (bool == null) {
                throw new NullPointerException("mapSurfaceStatus cannot be null");
            }
            this.mapSurfaceStatus = bool;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).mapSurfaceStatus.equals(this.mapSurfaceStatus);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.mapSurfaceStatus);
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
        if (obj instanceof ReqSetMapSurfaceStatus) {
            return super.equals(obj) && ((ReqSetMapSurfaceStatus) obj).arguments.equals(this.arguments);
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
