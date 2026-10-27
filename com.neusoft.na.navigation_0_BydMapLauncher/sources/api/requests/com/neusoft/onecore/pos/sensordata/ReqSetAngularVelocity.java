package api.requests.com.neusoft.onecore.pos.sensordata;

import api.structs.com.neusoft.onecore.pos.sensordata.AngularVelocity;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetAngularVelocity extends BaseRequest {
    private static final long serialVersionUID = -1477222719;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetAngularVelocity() {
        super(EMessageType.Request, "Positioning", "com.neusoft.onecore.pos.SensorData.SetAngularVelocity");
        this.arguments = new Arguments();
    }

    public ReqSetAngularVelocity(AngularVelocity angularVelocity) {
        this();
        this.arguments.setAngularVelocity(angularVelocity);
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

        @SerializedName("AngularVelocity")
        private AngularVelocity angularVelocity = new AngularVelocity();

        public Arguments() {
        }

        public Arguments(AngularVelocity angularVelocity) {
            setAngularVelocity(angularVelocity);
        }

        public AngularVelocity getAngularVelocity() {
            return this.angularVelocity;
        }

        public Arguments setAngularVelocity(AngularVelocity angularVelocity) {
            this.angularVelocity = angularVelocity;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).angularVelocity.equals(this.angularVelocity);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.angularVelocity);
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
        if (obj instanceof ReqSetAngularVelocity) {
            return super.equals(obj) && ((ReqSetAngularVelocity) obj).arguments.equals(this.arguments);
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
