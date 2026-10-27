package api.requests.com.neusoft.onecore.pos.sensordata;

import api.structs.com.neusoft.onecore.pos.sensordata.Odometer;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetOdometer extends BaseRequest {
    private static final long serialVersionUID = -1477222719;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetOdometer() {
        super(EMessageType.Request, "Positioning", "com.neusoft.onecore.pos.SensorData.SetOdometer");
        this.arguments = new Arguments();
    }

    public ReqSetOdometer(Odometer odometer) {
        this();
        this.arguments.setOdometer(odometer);
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

        @SerializedName("Odometer")
        private Odometer odometer = new Odometer();

        public Arguments() {
        }

        public Arguments(Odometer odometer) {
            setOdometer(odometer);
        }

        public Odometer getOdometer() {
            return this.odometer;
        }

        public Arguments setOdometer(Odometer odometer) {
            this.odometer = odometer;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).odometer.equals(this.odometer);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.odometer);
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
        if (obj instanceof ReqSetOdometer) {
            return super.equals(obj) && ((ReqSetOdometer) obj).arguments.equals(this.arguments);
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
