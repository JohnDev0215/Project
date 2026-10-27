package api.requests.com.neusoft.ecarx.ecarxnavapi;

import api.structs.com.neusoft.ecarx.SpecialPoiNaviType;
import api.structs.com.neusoft.ecarx.SpecialPoiType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqSpecialPoiNavi extends BaseRequest {
    private static final long serialVersionUID = -1481836606;

    @SerializedName("args")
    private Arguments arguments;

    public ReqSpecialPoiNavi() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.ecarx.EcarxNavApi.SpecialPoiNavi");
        this.arguments = new Arguments();
    }

    public ReqSpecialPoiNavi(SpecialPoiNaviType specialPoiNaviType, SpecialPoiType specialPoiType) {
        this();
        this.arguments.setAction(specialPoiNaviType);
        this.arguments.setPoiType(specialPoiType);
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

        @SerializedName("Action")
        private SpecialPoiNaviType action = SpecialPoiNaviType.NAVIGATE_DIRECT;

        @SerializedName("PoiType")
        private SpecialPoiType poiType = SpecialPoiType.GO_HOME;

        public Arguments() {
        }

        public Arguments(SpecialPoiNaviType specialPoiNaviType, SpecialPoiType specialPoiType) {
            setAction(specialPoiNaviType);
            setPoiType(specialPoiType);
        }

        public SpecialPoiNaviType getAction() {
            return this.action;
        }

        public Arguments setAction(SpecialPoiNaviType specialPoiNaviType) {
            this.action = specialPoiNaviType;
            return this;
        }

        public SpecialPoiType getPoiType() {
            return this.poiType;
        }

        public Arguments setPoiType(SpecialPoiType specialPoiType) {
            this.poiType = specialPoiType;
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
            return super.equals(obj) && arguments.action.equals(this.action) && arguments.poiType.equals(this.poiType);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.action, this.poiType);
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
        if (obj instanceof ReqSpecialPoiNavi) {
            return super.equals(obj) && ((ReqSpecialPoiNavi) obj).arguments.equals(this.arguments);
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
