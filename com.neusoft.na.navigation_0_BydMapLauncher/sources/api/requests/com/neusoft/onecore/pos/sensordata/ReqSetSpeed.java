package api.requests.com.neusoft.onecore.pos.sensordata;

import api.structs.com.neusoft.onecore.pos.sensordata.ExtSpeed;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetSpeed extends BaseRequest {
    private static final long serialVersionUID = -1477222719;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetSpeed() {
        super(EMessageType.Request, "Positioning", "com.neusoft.onecore.pos.SensorData.SetSpeed");
        this.arguments = new Arguments();
    }

    public ReqSetSpeed(ExtSpeed extSpeed) {
        this();
        this.arguments.setSpeed(extSpeed);
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

        @SerializedName("speed")
        private ExtSpeed speed = new ExtSpeed();

        public Arguments() {
        }

        public Arguments(ExtSpeed extSpeed) {
            setSpeed(extSpeed);
        }

        public ExtSpeed getSpeed() {
            return this.speed;
        }

        public Arguments setSpeed(ExtSpeed extSpeed) {
            this.speed = extSpeed;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).speed.equals(this.speed);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.speed);
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
        if (obj instanceof ReqSetSpeed) {
            return super.equals(obj) && ((ReqSetSpeed) obj).arguments.equals(this.arguments);
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
