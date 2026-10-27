package api.responses.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.NavAPPActivityStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetMapAppStatus extends BaseResponse {
    private static final long serialVersionUID = -1478144259;

    @SerializedName("args")
    private Arguments arguments;

    public ResGetMapAppStatus() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.common.CommonSysApiInverse.getMapAppStatus");
        this.arguments = new Arguments();
    }

    public ResGetMapAppStatus(NavAPPActivityStatus navAPPActivityStatus) {
        this();
        this.arguments.setMapAppStatus(navAPPActivityStatus);
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

        @SerializedName("MapAppStatus")
        private NavAPPActivityStatus mapAppStatus = NavAPPActivityStatus.NAV_APP_FOREGROUND;

        public Arguments() {
        }

        public Arguments(NavAPPActivityStatus navAPPActivityStatus) {
            setMapAppStatus(navAPPActivityStatus);
        }

        public NavAPPActivityStatus getMapAppStatus() {
            return this.mapAppStatus;
        }

        public Arguments setMapAppStatus(NavAPPActivityStatus navAPPActivityStatus) {
            this.mapAppStatus = navAPPActivityStatus;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).mapAppStatus.equals(this.mapAppStatus);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.mapAppStatus);
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
        if (obj instanceof ResGetMapAppStatus) {
            return super.equals(obj) && ((ResGetMapAppStatus) obj).arguments.equals(this.arguments);
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
