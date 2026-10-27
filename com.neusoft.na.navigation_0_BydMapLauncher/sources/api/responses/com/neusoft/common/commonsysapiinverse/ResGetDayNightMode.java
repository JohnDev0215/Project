package api.responses.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.MapStyleType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetDayNightMode extends BaseResponse {
    private static final long serialVersionUID = -1478144296;

    @SerializedName("args")
    private Arguments arguments;

    public ResGetDayNightMode() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.common.CommonSysApiInverse.getDayNightMode");
        this.arguments = new Arguments();
    }

    public ResGetDayNightMode(MapStyleType mapStyleType) {
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

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        EMessageType eMessageType2 = EMessageType.Error;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ResGetDayNightMode) {
            return super.equals(obj) && ((ResGetDayNightMode) obj).arguments.equals(this.arguments);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
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
