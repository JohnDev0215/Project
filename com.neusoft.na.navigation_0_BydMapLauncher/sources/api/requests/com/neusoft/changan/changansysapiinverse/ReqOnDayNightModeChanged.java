package api.requests.com.neusoft.changan.changansysapiinverse;

import api.structs.com.neusoft.changan.DayNightModeStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqOnDayNightModeChanged extends BaseRequest {
    private static final long serialVersionUID = -1478141320;

    @SerializedName("args")
    private Arguments arguments;

    public ReqOnDayNightModeChanged() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.changan.ChangAnSysApiInverse.OnDayNightModeChanged");
        this.arguments = new Arguments();
    }

    public ReqOnDayNightModeChanged(DayNightModeStatus dayNightModeStatus) {
        this();
        this.arguments.setDayNightModeStatus(dayNightModeStatus);
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

        @SerializedName("DayNightModeStatus")
        private DayNightModeStatus dayNightModeStatus = DayNightModeStatus.DAYNIGHT_MODE_STATUS_AUTO;

        public Arguments() {
        }

        public Arguments(DayNightModeStatus dayNightModeStatus) {
            setDayNightModeStatus(dayNightModeStatus);
        }

        public DayNightModeStatus getDayNightModeStatus() {
            return this.dayNightModeStatus;
        }

        public Arguments setDayNightModeStatus(DayNightModeStatus dayNightModeStatus) {
            this.dayNightModeStatus = dayNightModeStatus;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).dayNightModeStatus.equals(this.dayNightModeStatus);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.dayNightModeStatus);
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
        if (obj instanceof ReqOnDayNightModeChanged) {
            return super.equals(obj) && ((ReqOnDayNightModeChanged) obj).arguments.equals(this.arguments);
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
