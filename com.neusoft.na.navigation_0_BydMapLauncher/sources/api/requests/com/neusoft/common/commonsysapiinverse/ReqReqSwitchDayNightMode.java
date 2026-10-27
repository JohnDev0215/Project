package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.MapStyleType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqReqSwitchDayNightMode extends BaseRequest {
    private static final long serialVersionUID = -1478145251;

    @SerializedName("args")
    private Arguments arguments;

    public ReqReqSwitchDayNightMode() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.reqSwitchDayNightMode");
        this.arguments = new Arguments();
    }

    public ReqReqSwitchDayNightMode(MapStyleType mapStyleType) {
        this();
        this.arguments.setMapStyleType(mapStyleType);
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

        @SerializedName("MapStyleType")
        private MapStyleType mapStyleType = MapStyleType.NIGHT;

        public Arguments() {
        }

        public Arguments(MapStyleType mapStyleType) {
            setMapStyleType(mapStyleType);
        }

        public MapStyleType getMapStyleType() {
            return this.mapStyleType;
        }

        public Arguments setMapStyleType(MapStyleType mapStyleType) {
            this.mapStyleType = mapStyleType;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).mapStyleType.equals(this.mapStyleType);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.mapStyleType);
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
        if (obj instanceof ReqReqSwitchDayNightMode) {
            return super.equals(obj) && ((ReqReqSwitchDayNightMode) obj).arguments.equals(this.arguments);
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
