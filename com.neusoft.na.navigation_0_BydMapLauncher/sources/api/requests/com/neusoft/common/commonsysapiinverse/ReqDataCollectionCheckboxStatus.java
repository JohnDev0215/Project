package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.CheckboxBtnStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqDataCollectionCheckboxStatus extends BaseRequest {
    private static final long serialVersionUID = -1478145157;

    @SerializedName("args")
    private Arguments arguments;

    public ReqDataCollectionCheckboxStatus() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.DataCollectionCheckboxStatus");
        this.arguments = new Arguments();
    }

    public ReqDataCollectionCheckboxStatus(CheckboxBtnStatus checkboxBtnStatus) {
        this();
        this.arguments.setBtnStatus(checkboxBtnStatus);
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

        @SerializedName("BtnStatus")
        private CheckboxBtnStatus btnStatus = CheckboxBtnStatus.STATUS_UNCHECKED;

        public Arguments() {
        }

        public Arguments(CheckboxBtnStatus checkboxBtnStatus) {
            setBtnStatus(checkboxBtnStatus);
        }

        public CheckboxBtnStatus getBtnStatus() {
            return this.btnStatus;
        }

        public Arguments setBtnStatus(CheckboxBtnStatus checkboxBtnStatus) {
            this.btnStatus = checkboxBtnStatus;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).btnStatus.equals(this.btnStatus);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.btnStatus);
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
        if (obj instanceof ReqDataCollectionCheckboxStatus) {
            return super.equals(obj) && ((ReqDataCollectionCheckboxStatus) obj).arguments.equals(this.arguments);
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
