package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.HMIPageMode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqMigrateNaviHMIPage extends BaseRequest {
    private static final long serialVersionUID = -1478145157;

    @SerializedName("args")
    private Arguments arguments;

    public ReqMigrateNaviHMIPage() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.MigrateNaviHMIPage");
        this.arguments = new Arguments();
    }

    public ReqMigrateNaviHMIPage(HMIPageMode hMIPageMode) {
        this();
        this.arguments.setPageMode(hMIPageMode);
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

        @SerializedName("PageMode")
        private HMIPageMode pageMode = HMIPageMode.LOCATION;

        public Arguments() {
        }

        public Arguments(HMIPageMode hMIPageMode) {
            setPageMode(hMIPageMode);
        }

        public HMIPageMode getPageMode() {
            return this.pageMode;
        }

        public Arguments setPageMode(HMIPageMode hMIPageMode) {
            this.pageMode = hMIPageMode;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).pageMode.equals(this.pageMode);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.pageMode);
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
        if (obj instanceof ReqMigrateNaviHMIPage) {
            return super.equals(obj) && ((ReqMigrateNaviHMIPage) obj).arguments.equals(this.arguments);
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
