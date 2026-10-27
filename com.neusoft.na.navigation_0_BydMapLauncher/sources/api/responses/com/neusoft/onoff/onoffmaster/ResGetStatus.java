package api.responses.com.neusoft.onoff.onoffmaster;

import api.structs.com.neusoft.onoff.onoffmaster.ProcessInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetStatus extends BaseResponse {
    private static final long serialVersionUID = -1477218845;

    @SerializedName("args")
    private Arguments arguments;

    public ResGetStatus() {
        super(EMessageType.Result, "OnOff", "com.neusoft.onoff.OnOffMaster.GetStatus");
        this.arguments = new Arguments();
        setRoutingKey("com.neusoft.onoff.OnOffMaster.GetStatus");
    }

    public ResGetStatus(List<ProcessInfo> list) {
        this();
        setRoutingKey("com.neusoft.onoff.OnOffMaster.GetStatus");
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
        if (obj instanceof ResGetStatus) {
            return super.equals(obj) && ((ResGetStatus) obj).arguments.equals(this.arguments);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
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
