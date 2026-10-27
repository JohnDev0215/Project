package api.responses.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.DetailPageStatus;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResGetPoiDetailStatus extends BaseResponse {
    private static final long serialVersionUID = -1480908222;

    @SerializedName("args")
    private Arguments arguments;

    public ResGetPoiDetailStatus() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.GetPoiDetailStatus");
        this.arguments = new Arguments();
    }

    public ResGetPoiDetailStatus(DetailPageStatus detailPageStatus) {
        this();
        this.arguments.setDetailPageStatus(detailPageStatus);
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

        @SerializedName("DetailPageStatus")
        private DetailPageStatus detailPageStatus = DetailPageStatus.CLOSE;

        public Arguments() {
        }

        public Arguments(DetailPageStatus detailPageStatus) {
            setDetailPageStatus(detailPageStatus);
        }

        public DetailPageStatus getDetailPageStatus() {
            return this.detailPageStatus;
        }

        public Arguments setDetailPageStatus(DetailPageStatus detailPageStatus) {
            this.detailPageStatus = detailPageStatus;
            return this;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Arguments) {
                return super.equals(obj) && ((Arguments) obj).detailPageStatus.equals(this.detailPageStatus);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.detailPageStatus);
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
        if (obj instanceof ResGetPoiDetailStatus) {
            return super.equals(obj) && ((ResGetPoiDetailStatus) obj).arguments.equals(this.arguments);
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
