package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.PlaceInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqReqOpenPoiDetail extends BaseRequest {
    private static final long serialVersionUID = -1480909119;

    @SerializedName("args")
    private Arguments arguments;

    public ReqReqOpenPoiDetail() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.ReqOpenPoiDetail");
        this.arguments = new Arguments();
    }

    public ReqReqOpenPoiDetail(PlaceInfo placeInfo) {
        this();
        this.arguments.setPlaceInfo(placeInfo);
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

        @SerializedName("PlaceInfo")
        private PlaceInfo placeInfo = new PlaceInfo();

        public Arguments() {
        }

        public Arguments(PlaceInfo placeInfo) {
            setPlaceInfo(placeInfo);
        }

        public PlaceInfo getPlaceInfo() {
            return this.placeInfo;
        }

        public Arguments setPlaceInfo(PlaceInfo placeInfo) {
            this.placeInfo = placeInfo;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).placeInfo.equals(this.placeInfo);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.placeInfo);
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
        if (obj instanceof ReqReqOpenPoiDetail) {
            return super.equals(obj) && ((ReqReqOpenPoiDetail) obj).arguments.equals(this.arguments);
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
