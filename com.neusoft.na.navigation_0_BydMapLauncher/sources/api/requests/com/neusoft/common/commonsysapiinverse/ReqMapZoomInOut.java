package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.ZoomInOutType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqMapZoomInOut extends BaseRequest {
    private static final long serialVersionUID = -1478145287;

    @SerializedName("args")
    private Arguments arguments;

    public ReqMapZoomInOut() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.MapZoomInOut");
        this.arguments = new Arguments();
    }

    public ReqMapZoomInOut(ZoomInOutType zoomInOutType) {
        this();
        this.arguments.setZoomInOutType(zoomInOutType);
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

        @SerializedName("ZoomInOutType")
        private ZoomInOutType zoomInOutType = ZoomInOutType.ZOOM_DEFAULT;

        public Arguments() {
        }

        public Arguments(ZoomInOutType zoomInOutType) {
            setZoomInOutType(zoomInOutType);
        }

        public ZoomInOutType getZoomInOutType() {
            return this.zoomInOutType;
        }

        public Arguments setZoomInOutType(ZoomInOutType zoomInOutType) {
            this.zoomInOutType = zoomInOutType;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).zoomInOutType.equals(this.zoomInOutType);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.zoomInOutType);
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
        if (obj instanceof ReqMapZoomInOut) {
            return super.equals(obj) && ((ReqMapZoomInOut) obj).arguments.equals(this.arguments);
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
