package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.AddressType;
import api.structs.com.neusoft.byd.PlaceInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqReqSetAddress extends BaseRequest {
    private static final long serialVersionUID = -1480909177;

    @SerializedName("args")
    private Arguments arguments;

    public ReqReqSetAddress() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.ReqSetAddress");
        this.arguments = new Arguments();
    }

    public ReqReqSetAddress(PlaceInfo placeInfo, AddressType addressType) {
        this();
        this.arguments.setPlaceInfo(placeInfo);
        this.arguments.setAddressType(addressType);
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

        @SerializedName("AddressType")
        private AddressType addressType = AddressType.HOME;

        public Arguments() {
        }

        public Arguments(PlaceInfo placeInfo, AddressType addressType) {
            setPlaceInfo(placeInfo);
            setAddressType(addressType);
        }

        public PlaceInfo getPlaceInfo() {
            return this.placeInfo;
        }

        public Arguments setPlaceInfo(PlaceInfo placeInfo) {
            this.placeInfo = placeInfo;
            return this;
        }

        public AddressType getAddressType() {
            return this.addressType;
        }

        public Arguments setAddressType(AddressType addressType) {
            this.addressType = addressType;
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
            return super.equals(obj) && arguments.placeInfo.equals(this.placeInfo) && arguments.addressType.equals(this.addressType);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.placeInfo, this.addressType);
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
        if (obj instanceof ReqReqSetAddress) {
            return super.equals(obj) && ((ReqReqSetAddress) obj).arguments.equals(this.arguments);
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
