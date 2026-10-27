package api.requests.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.PlaceInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseRequest;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class ReqShowPoiIconsFromVR extends BaseRequest {
    private static final long serialVersionUID = -1480909025;

    @SerializedName("args")
    private Arguments arguments;

    public ReqShowPoiIconsFromVR() {
        super(EMessageType.Request, "MotsBl", "com.neusoft.byd.BydNavApi.ShowPoiIconsFromVR");
        this.arguments = new Arguments();
    }

    public ReqShowPoiIconsFromVR(List<PlaceInfo> list) {
        this();
        this.arguments.setPoiIconsFromVRInfo(list);
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

        @SerializedName("PoiIconsFromVRInfo")
        private List<PlaceInfo> poiIconsFromVRInfo = new ArrayList();

        public Arguments() {
        }

        public Arguments(List<PlaceInfo> list) {
            setPoiIconsFromVRInfo(list);
        }

        public List<PlaceInfo> getPoiIconsFromVRInfo() {
            return this.poiIconsFromVRInfo;
        }

        public Arguments setPoiIconsFromVRInfo(List<PlaceInfo> list) {
            if (list == null) {
                throw new NullPointerException("poiIconsFromVRInfo cannot be null");
            }
            this.poiIconsFromVRInfo = list;
            return this;
        }

        public Arguments setPoiIconsFromVRInfo(PlaceInfo... placeInfoArr) {
            return setPoiIconsFromVRInfo(Arrays.asList(placeInfoArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).poiIconsFromVRInfo.equals(this.poiIconsFromVRInfo);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.poiIconsFromVRInfo);
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
        if (obj instanceof ReqShowPoiIconsFromVR) {
            return super.equals(obj) && ((ReqShowPoiIconsFromVR) obj).arguments.equals(this.arguments);
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
