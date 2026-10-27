package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.ModuleName;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqStartUpState extends BaseRequest {
    private static final long serialVersionUID = -1478145404;

    @SerializedName("args")
    private Arguments arguments;

    public ReqStartUpState() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.StartUpState");
        this.arguments = new Arguments();
    }

    public ReqStartUpState(Map<ModuleName, Boolean> map, Boolean bool) {
        this();
        this.arguments.setState(map);
        this.arguments.setCreateTestFileSuccess(bool);
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

        @SerializedName("State")
        private Map<ModuleName, Boolean> state = new LinkedHashMap();

        @SerializedName("CreateTestFileSuccess")
        private Boolean createTestFileSuccess = false;

        public Arguments() {
        }

        public Arguments(Map<ModuleName, Boolean> map, Boolean bool) {
            setState(map);
            setCreateTestFileSuccess(bool);
        }

        public Map<ModuleName, Boolean> getState() {
            return this.state;
        }

        public Arguments setState(Map<ModuleName, Boolean> map) {
            if (map == null) {
                throw new NullPointerException("state cannot be null");
            }
            this.state = map;
            return this;
        }

        public Boolean getCreateTestFileSuccess() {
            return this.createTestFileSuccess;
        }

        public Arguments setCreateTestFileSuccess(Boolean bool) {
            if (bool == null) {
                throw new NullPointerException("createTestFileSuccess cannot be null");
            }
            this.createTestFileSuccess = bool;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.state.equals(this.state) && arguments.createTestFileSuccess.equals(this.createTestFileSuccess);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.state, this.createTestFileSuccess);
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
        if (obj instanceof ReqStartUpState) {
            return super.equals(obj) && ((ReqStartUpState) obj).arguments.equals(this.arguments);
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
