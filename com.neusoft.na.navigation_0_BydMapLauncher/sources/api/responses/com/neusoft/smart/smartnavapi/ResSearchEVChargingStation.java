package api.responses.com.neusoft.smart.smartnavapi;

import api.structs.com.neusoft.smart.EVChargingStationInfo;
import api.structs.com.neusoft.smart.ResultCode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResSearchEVChargingStation extends BaseResponse {
    private static final long serialVersionUID = -1479066849;

    @SerializedName("args")
    private Arguments arguments;

    @SerializedName("customError")
    private ResultCode customError;

    public ResSearchEVChargingStation() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.smart.SmartNavApi.SearchEVChargingStation");
        this.arguments = new Arguments();
    }

    public ResSearchEVChargingStation(List<EVChargingStationInfo> list) {
        this();
        this.arguments.setEVChargingStationList(list);
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

        @SerializedName("EVChargingStationList")
        private List<EVChargingStationInfo> eVChargingStationList = new ArrayList();

        public Arguments() {
        }

        public Arguments(List<EVChargingStationInfo> list) {
            setEVChargingStationList(list);
        }

        public List<EVChargingStationInfo> getEVChargingStationList() {
            return this.eVChargingStationList;
        }

        public Arguments setEVChargingStationList(List<EVChargingStationInfo> list) {
            if (list == null) {
                throw new NullPointerException("eVChargingStationList cannot be null");
            }
            this.eVChargingStationList = list;
            return this;
        }

        public Arguments setEVChargingStationList(EVChargingStationInfo... eVChargingStationInfoArr) {
            return setEVChargingStationList(Arrays.asList(eVChargingStationInfoArr));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).eVChargingStationList.equals(this.eVChargingStationList);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.eVChargingStationList);
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
        if (obj instanceof ResSearchEVChargingStation) {
            return super.equals(obj) && ((ResSearchEVChargingStation) obj).arguments.equals(this.arguments);
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

    public void setCustomError(ResultCode resultCode) {
        this.customError = resultCode;
    }

    public ResultCode getCustomError() {
        return this.customError;
    }
}
