package api.requests.com.neusoft.onecore.pos.sensordata;

import api.structs.com.neusoft.onecore.pos.sensordata.DeadReckoning;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetDeadReckoning extends BaseRequest {
    private static final long serialVersionUID = -1477222755;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetDeadReckoning() {
        super(EMessageType.Request, "Positioning", "com.neusoft.onecore.pos.SensorData.SetDeadReckoning");
        this.arguments = new Arguments();
    }

    public ReqSetDeadReckoning(DeadReckoning deadReckoning) {
        this();
        this.arguments.setDeadReckoning(deadReckoning);
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

        @SerializedName("DeadReckoning")
        private DeadReckoning deadReckoning = new DeadReckoning();

        public Arguments() {
        }

        public Arguments(DeadReckoning deadReckoning) {
            setDeadReckoning(deadReckoning);
        }

        public DeadReckoning getDeadReckoning() {
            return this.deadReckoning;
        }

        public Arguments setDeadReckoning(DeadReckoning deadReckoning) {
            this.deadReckoning = deadReckoning;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).deadReckoning.equals(this.deadReckoning);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.deadReckoning);
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
        if (obj instanceof ReqSetDeadReckoning) {
            return super.equals(obj) && ((ReqSetDeadReckoning) obj).arguments.equals(this.arguments);
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
