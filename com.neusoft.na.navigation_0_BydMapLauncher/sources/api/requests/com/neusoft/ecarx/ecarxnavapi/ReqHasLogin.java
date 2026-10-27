package api.requests.com.neusoft.ecarx.ecarxnavapi;

import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqHasLogin extends BaseRequest {
    private static final long serialVersionUID = -1481836548;

    @SerializedName("args")
    private Arguments arguments;

    public ReqHasLogin() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.HasLogin");
        this.arguments = new Arguments();
    }

    public ReqHasLogin(Boolean bool, String str) {
        this();
        this.arguments.setLogout(bool);
        this.arguments.setProtonId(str);
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

        @SerializedName("Logout")
        private Boolean logout = false;

        @SerializedName("ProtonId")
        private String protonId = "";

        public Arguments() {
        }

        public Arguments(Boolean bool, String str) {
            setLogout(bool);
            setProtonId(str);
        }

        public Boolean getLogout() {
            return this.logout;
        }

        public Arguments setLogout(Boolean bool) {
            if (bool == null) {
                throw new NullPointerException("logout cannot be null");
            }
            this.logout = bool;
            return this;
        }

        public String getProtonId() {
            return this.protonId;
        }

        public Arguments setProtonId(String str) {
            if (str == null) {
                throw new NullPointerException("protonId cannot be null");
            }
            this.protonId = str;
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
            return super.equals(obj) && arguments.logout.equals(this.logout) && arguments.protonId.equals(this.protonId);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.logout, this.protonId);
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
        if (obj instanceof ReqHasLogin) {
            return super.equals(obj) && ((ReqHasLogin) obj).arguments.equals(this.arguments);
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
