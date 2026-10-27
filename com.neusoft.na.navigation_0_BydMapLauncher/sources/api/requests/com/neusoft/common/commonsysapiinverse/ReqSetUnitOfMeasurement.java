package api.requests.com.neusoft.common.commonsysapiinverse;

import api.structs.com.neusoft.common.LengthUnit;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSetUnitOfMeasurement extends BaseRequest {
    private static final long serialVersionUID = -1478145347;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSetUnitOfMeasurement() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.common.CommonSysApiInverse.SetUnitOfMeasurement");
        this.arguments = new Arguments();
    }

    public ReqSetUnitOfMeasurement(LengthUnit lengthUnit) {
        this();
        this.arguments.setBaseUnit(lengthUnit);
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

        @SerializedName("BaseUnit")
        private LengthUnit baseUnit = LengthUnit.INVALID;

        public Arguments() {
        }

        public Arguments(LengthUnit lengthUnit) {
            setBaseUnit(lengthUnit);
        }

        public LengthUnit getBaseUnit() {
            return this.baseUnit;
        }

        public Arguments setBaseUnit(LengthUnit lengthUnit) {
            this.baseUnit = lengthUnit;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).baseUnit.equals(this.baseUnit);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.baseUnit);
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
        if (obj instanceof ReqSetUnitOfMeasurement) {
            return super.equals(obj) && ((ReqSetUnitOfMeasurement) obj).arguments.equals(this.arguments);
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
