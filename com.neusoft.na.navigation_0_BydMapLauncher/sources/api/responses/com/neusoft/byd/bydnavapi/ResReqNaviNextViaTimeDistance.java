package api.responses.com.neusoft.byd.bydnavapi;

import api.structs.com.neusoft.byd.NaviCode;
import com.google.gson.annotations.SerializedName;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.BaseResponse;
import com.neusoft.de.nts.OneCoreTest.communication.messageModel.EMessageType;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class ResReqNaviNextViaTimeDistance extends BaseResponse {
    private static final long serialVersionUID = -1480908968;

    @SerializedName("args")
    private Arguments arguments;

    public ResReqNaviNextViaTimeDistance() {
        super(EMessageType.Result, "MotsBl", "com.neusoft.byd.BydNavApi.reqNaviNextViaTimeDistance");
        this.arguments = new Arguments();
    }

    public ResReqNaviNextViaTimeDistance(NaviCode naviCode, Integer num, Integer num2) {
        this();
        this.arguments.setNaviCode(naviCode);
        this.arguments.setViaRemainTime(num);
        this.arguments.setViaRemainDistance(num2);
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

        @SerializedName("ViaRemainTime")
        private Integer viaRemainTime = 0;

        @SerializedName("ViaRemainDistance")
        private Integer viaRemainDistance = 0;

        public Arguments() {
        }

        public Arguments(NaviCode naviCode, Integer num, Integer num2) {
            setNaviCode(naviCode);
            setViaRemainTime(num);
            setViaRemainDistance(num2);
        }

        public NaviCode getNaviCode() {
            return this.naviCode;
        }

        public Arguments setNaviCode(NaviCode naviCode) {
            this.naviCode = naviCode;
            return this;
        }

        public Integer getViaRemainTime() {
            return this.viaRemainTime;
        }

        public Arguments setViaRemainTime(Integer num) {
            if (num == null) {
                throw new NullPointerException("viaRemainTime cannot be null");
            }
            this.viaRemainTime = num;
            return this;
        }

        public Integer getViaRemainDistance() {
            return this.viaRemainDistance;
        }

        public Arguments setViaRemainDistance(Integer num) {
            if (num == null) {
                throw new NullPointerException("viaRemainDistance cannot be null");
            }
            this.viaRemainDistance = num;
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
            return super.equals(obj) && arguments.naviCode.equals(this.naviCode) && arguments.viaRemainTime.equals(this.viaRemainTime) && arguments.viaRemainDistance.equals(this.viaRemainDistance);
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.naviCode, this.viaRemainTime, this.viaRemainDistance);
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
        if (obj instanceof ResReqNaviNextViaTimeDistance) {
            return super.equals(obj) && ((ResReqNaviNextViaTimeDistance) obj).arguments.equals(this.arguments);
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
