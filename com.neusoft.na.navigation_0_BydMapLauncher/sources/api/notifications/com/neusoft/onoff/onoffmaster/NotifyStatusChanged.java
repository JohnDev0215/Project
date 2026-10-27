package api.notifications.com.neusoft.onoff.onoffmaster;

import api.structs.com.neusoft.onoff.onoffmaster.ProcessInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class NotifyStatusChanged extends BaseNotification {
    private static final long serialVersionUID = -1477218788;

    @SerializedName("args")
    private Arguments arguments;

    public NotifyStatusChanged() {
        super(EMessageType.Signal, "OnOff", "com.neusoft.onoff.OnOffMaster.StatusChanged");
        this.arguments = new Arguments();
        setRoutingKey("com.neusoft.onoff.OnOffMaster.status.StatusChanged");
    }

    public NotifyStatusChanged(List<ProcessInfo> list) {
        this();
        setRoutingKey("com.neusoft.onoff.OnOffMaster.status.StatusChanged");
        this.arguments.setProcessInfos(list);
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

        @SerializedName("ProcessInfos")
        private List<ProcessInfo> processInfos = new ArrayList();

        public Arguments() {
        }

        public Arguments(List<ProcessInfo> list) {
            setProcessInfos(list);
        }

        public List<ProcessInfo> getProcessInfos() {
            return this.processInfos;
        }

        public Arguments setProcessInfos(List<ProcessInfo> list) {
            if (list == null) {
                throw new NullPointerException("processInfos cannot be null");
            }
            this.processInfos = list;
            return this;
        }

        public Arguments setProcessInfos(ProcessInfo... processInfoArr) {
            return setProcessInfos(Arrays.asList(processInfoArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).processInfos.equals(this.processInfos);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.processInfos);
        }

        public String toString() {
            return EProjectInterfaceType.OCTA.getGson().toJson(this);
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NotifyStatusChanged) {
            return super.equals(obj) && ((NotifyStatusChanged) obj).arguments.equals(this.arguments);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseNotification, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public int hashCode() {
        return Objects.hash(Integer.valueOf(super.hashCode()), this.arguments);
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public EProjectInterfaceType getProjectInterfaceType() {
        return EProjectInterfaceType.OCTA;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public String toString() {
        return getProjectInterfaceType().getGson().toJson(this);
    }
}
