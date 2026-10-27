package api.notifications.com.neusoft.onecore.pos.sensordata;

import api.structs.com.neusoft.onecore.pos.sensordata.MapMatchResult;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class NotifyMapMatchFeedback extends BaseNotification {
    private static final long serialVersionUID = -1477222661;

    @SerializedName("args")
    private Arguments arguments;

    public NotifyMapMatchFeedback() {
        super(EMessageType.Signal, "Positioning", "com.neusoft.onecore.pos.SensorData.MapMatchFeedback");
        this.arguments = new Arguments();
    }

    public NotifyMapMatchFeedback(MapMatchResult mapMatchResult) {
        this();
        this.arguments.setResult(mapMatchResult);
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

        @SerializedName("Result")
        private MapMatchResult result = new MapMatchResult();

        public Arguments() {
        }

        public Arguments(MapMatchResult mapMatchResult) {
            setResult(mapMatchResult);
        }

        public MapMatchResult getResult() {
            return this.result;
        }

        public Arguments setResult(MapMatchResult mapMatchResult) {
            this.result = mapMatchResult;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).result.equals(this.result);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.result);
        }

        public String toString() {
            return EProjectInterfaceType.OneCore.getGson().toJson(this);
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NotifyMapMatchFeedback) {
            return super.equals(obj) && ((NotifyMapMatchFeedback) obj).arguments.equals(this.arguments);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
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
