package api.requests.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.PoiDetailType;
import api.structs.com.neusoft.smart.PoiInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqGetDetailInfo extends BaseRequest {
    private static final long serialVersionUID = -1479067840;

    @SerializedName("args")
    private Arguments arguments;

    public ReqGetDetailInfo() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.GetDetailInfo");
        this.arguments = new Arguments();
    }

    public ReqGetDetailInfo(PoiInfo poiInfo, PoiDetailType poiDetailType) {
        this();
        this.arguments.setPoiInfo(poiInfo);
        this.arguments.setPoiDetailType(poiDetailType);
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

        @SerializedName("PoiInfo")
        private PoiInfo poiInfo = new PoiInfo();

        @SerializedName("PoiDetailType")
        private PoiDetailType poiDetailType = PoiDetailType.SEARCH_TYPE_DEFAULT;

        public Arguments() {
        }

        public Arguments(PoiInfo poiInfo, PoiDetailType poiDetailType) {
            setPoiInfo(poiInfo);
            setPoiDetailType(poiDetailType);
        }

        public PoiInfo getPoiInfo() {
            return this.poiInfo;
        }

        public Arguments setPoiInfo(PoiInfo poiInfo) {
            this.poiInfo = poiInfo;
            return this;
        }

        public PoiDetailType getPoiDetailType() {
            return this.poiDetailType;
        }

        public Arguments setPoiDetailType(PoiDetailType poiDetailType) {
            this.poiDetailType = poiDetailType;
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
            return super.equals(obj) && arguments.poiInfo.equals(this.poiInfo) && arguments.poiDetailType.equals(this.poiDetailType);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.poiInfo, this.poiDetailType);
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
        if (obj instanceof ReqGetDetailInfo) {
            return super.equals(obj) && ((ReqGetDetailInfo) obj).arguments.equals(this.arguments);
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
