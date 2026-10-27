package api.responses.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.NaviCode;
import api.structs.com.neusoft.byd.PlaceInfo;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResReqNaviAlongTheWaySearch extends BaseResponse {
    private static final long serialVersionUID = -1480908222;

    @SerializedName("args")
    private Arguments arguments;

    public ResReqNaviAlongTheWaySearch() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.ReqNaviAlongTheWaySearch");
        this.arguments = new Arguments();
    }

    public ResReqNaviAlongTheWaySearch(NaviCode naviCode, List<PlaceInfo> list) {
        this();
        this.arguments.setNaviCode(naviCode);
        this.arguments.setPlaceInfoList(list);
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

        @SerializedName("NaviCode")
        private NaviCode naviCode = NaviCode.OK;

        @SerializedName("PlaceInfoList")
        private List<PlaceInfo> placeInfoList = new ArrayList();

        public Arguments() {
        }

        public Arguments(NaviCode naviCode, List<PlaceInfo> list) {
            setNaviCode(naviCode);
            setPlaceInfoList(list);
        }

        public NaviCode getNaviCode() {
            return this.naviCode;
        }

        public Arguments setNaviCode(NaviCode naviCode) {
            this.naviCode = naviCode;
            return this;
        }

        public List<PlaceInfo> getPlaceInfoList() {
            return this.placeInfoList;
        }

        public Arguments setPlaceInfoList(List<PlaceInfo> list) {
            if (list == null) {
                throw new NullPointerException("placeInfoList cannot be null");
            }
            this.placeInfoList = list;
            return this;
        }

        public Arguments setPlaceInfoList(PlaceInfo... placeInfoArr) {
            return setPlaceInfoList(Arrays.asList(placeInfoArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Arguments)) {
                return false;
            }
            Arguments arguments = (Arguments) obj;
            return super.equals(obj) && arguments.naviCode.equals(this.naviCode) && arguments.placeInfoList.equals(this.placeInfoList);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.naviCode, this.placeInfoList);
        }

        public String toString() {
            return EProjectInterfaceType.OneCore.getGson().toJson(this);
        }
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public void setMessageType(EMessageType eMessageType) {
        super.setMessageType(eMessageType);
        EMessageType eMessageType2 = EMessageType.Error;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ResReqNaviAlongTheWaySearch) {
            return super.equals(obj) && ((ResReqNaviAlongTheWaySearch) obj).arguments.equals(this.arguments);
        }
        return false;
    }

    @Override // com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse, com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseCommand
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
