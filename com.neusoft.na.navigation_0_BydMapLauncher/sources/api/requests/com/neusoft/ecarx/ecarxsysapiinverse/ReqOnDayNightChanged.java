package api.requests.com.neusoft.ecarx.ecarxsysapiinverse;

import api.structs.com.neusoft.ecarx.DayNightStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqOnDayNightChanged extends BaseRequest {
    private static final long serialVersionUID = -1478146149;

    @SerializedName("args")
    private Arguments arguments;

    public ReqOnDayNightChanged() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxSysApiInverse.OnDayNightChanged");
        this.arguments = new Arguments();
    }

    public ReqOnDayNightChanged(DayNightStatus dayNightStatus) {
        this();
        this.arguments.setDayNightStatus(dayNightStatus);
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

        @SerializedName("DayNightStatus")
        private DayNightStatus dayNightStatus = DayNightStatus.DAYNIGHT_STATUS_DAY;

        public Arguments() {
        }

        public Arguments(DayNightStatus dayNightStatus) {
            setDayNightStatus(dayNightStatus);
        }

        public DayNightStatus getDayNightStatus() {
            return this.dayNightStatus;
        }

        public Arguments setDayNightStatus(DayNightStatus dayNightStatus) {
            this.dayNightStatus = dayNightStatus;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).dayNightStatus.equals(this.dayNightStatus);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.dayNightStatus);
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
        if (obj instanceof ReqOnDayNightChanged) {
            return super.equals(obj) && ((ReqOnDayNightChanged) obj).arguments.equals(this.arguments);
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
