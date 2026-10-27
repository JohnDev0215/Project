package api.requests.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.EVChargingStationInfo;
import api.structs.com.neusoft.smart.PoiDetailType;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ReqGetEVDetailInfo extends BaseRequest {
    private static final long serialVersionUID = -1479067840;

    @SerializedName("args")
    private Arguments arguments;

    public ReqGetEVDetailInfo() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.smart.SmartNavApi.GetEVDetailInfo");
        this.arguments = new Arguments();
    }

    public ReqGetEVDetailInfo(EVChargingStationInfo eVChargingStationInfo, PoiDetailType poiDetailType) {
        this();
        this.arguments.setEVChargingStationInfo(eVChargingStationInfo);
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

        @SerializedName("EVChargingStationInfo")
        private EVChargingStationInfo eVChargingStationInfo = new EVChargingStationInfo();

        @SerializedName("PoiDetailType")
        private PoiDetailType poiDetailType = PoiDetailType.SEARCH_TYPE_DEFAULT;

        public Arguments() {
        }

        public Arguments(EVChargingStationInfo eVChargingStationInfo, PoiDetailType poiDetailType) {
            setEVChargingStationInfo(eVChargingStationInfo);
            setPoiDetailType(poiDetailType);
        }

        public EVChargingStationInfo getEVChargingStationInfo() {
            return this.eVChargingStationInfo;
        }

        public Arguments setEVChargingStationInfo(EVChargingStationInfo eVChargingStationInfo) {
            this.eVChargingStationInfo = eVChargingStationInfo;
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
            return super.equals(obj) && arguments.eVChargingStationInfo.equals(this.eVChargingStationInfo) && arguments.poiDetailType.equals(this.poiDetailType);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.eVChargingStationInfo, this.poiDetailType);
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
        if (obj instanceof ReqGetEVDetailInfo) {
            return super.equals(obj) && ((ReqGetEVDetailInfo) obj).arguments.equals(this.arguments);
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
